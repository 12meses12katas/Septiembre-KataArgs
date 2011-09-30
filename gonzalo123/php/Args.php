<?php
class Args
{
    const BOOLEAN = 0;
    const STRING  = 1;
    const INTEGER = 2;
    const ARRAY_STRING = 3;
    const ARRAY_NUMBER = 4;

    private $schema = array();
    public function __construct($schema)
    {
        $filters = $this->initFilters();
        foreach ($schema as $key => $type) {
            $this->schema[$key] = $filters[$type];
        }
    }

    private function initFilters()
    {
        return array(
            self::BOOLEAN => array('filter' => FILTER_VALIDATE_BOOLEAN),
            self::STRING  => array('filter' => FILTER_SANITIZE_STRING),
            self::INTEGER => array('filter' => FILTER_SANITIZE_NUMBER_INT),
            self::ARRAY_STRING => array('filter' => FILTER_SANITIZE_STRING, 'flags' => FILTER_FORCE_ARRAY),
            self::ARRAY_NUMBER => array('filter' => FILTER_VALIDATE_INT, 'flags' => FILTER_FORCE_ARRAY),
        );
    }

    public function decode($input)
    {
        $arr = explode(' ', $input);
        $decodedInput = $output = array();
        for ($i = 0; $i < count($arr); $i++) {
            $nextElement = $i+1;
            $key = substr($arr[$i], 1);

            if ($this->parameterExistsInSchema($key)) {
                if ($this->parameterHasValue($arr, $nextElement)) {
                    if ($this->parameterIsArray($key)) {
                        $value = explode(',', $arr[$nextElement]);
                    } else {
                        $value = $arr[$nextElement];
                    }
                    $i++;
                } else {
                   $value = true;
                }
            } else {
                throw new Exception("Parameter {$key} not defined");
            }
            
            $decodedInput[$key] = $value;
        }

        return filter_var_array($decodedInput, $this->schema);
    }

    private function parameterIsArray($key)
    {
        return isset($this->schema[$key]['flags']) && $this->schema[$key]['flags'] == FILTER_FORCE_ARRAY;
    }

    private function parameterExistsInSchema($key)
    {
        return array_key_exists($key, $this->schema);
    }

    private function parameterHasValue($arr, $nextElement)
    {
        return isset($arr[$nextElement]) && !$this->isParameter($arr[$nextElement]);
    }

    private function isParameter($parameter)
    {
        if (substr($parameter, 0, 1) == '-' && array_key_exists(substr($parameter, 1), $this->schema)) {
            return true;
        } else {
            return false;
        }
    }
}
