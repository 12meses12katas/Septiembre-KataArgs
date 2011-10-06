<?php
/**
  * Class for parser comand-line arguments
  */
class Args
{
    private $lineInput;
    private $gen;

    const INT = 0;
    const BOOL = 1;
    const STRING = 2;

    private $schema = array();
    private $defaults = array(
            self::BOOL   => false,
            self::INT    => '0',
            self::STRING => '',
    );

    /**
     * parser for line with arguments and data
     *
     * @param string $argsIn string with arguments and data
     *
     * @return void
     */
    public function parser($argsIn)
    {
        $this->lineInput = preg_split('/(-[a-z] *)/', $argsIn, -1, PREG_SPLIT_NO_EMPTY+PREG_SPLIT_DELIM_CAPTURE);
        $this->generateArray();
    }

    /**
     * Make array with flags and values for this flags
     *
     * @return void
     */
    public function generateArray()
    {
        $this->gen = array();
        $lastFlag = false;
        foreach ($this->lineInput as $frame) {
            if (preg_match('/(-[a-z])/', $frame)) {
              if ($lastFlag != false) {
                if ($this->flagInSchema($lastFlag) === true ) {
                  $this->gen[$lastFlag] = $this->DefaultValueIfExist($lastFlag);
                }
              }
              $lastFlag = $this->removeMinus($frame);
            } else {
                $this->gen[$lastFlag] = trim($frame);
                $lastFlag = false;
            }
        }
    }

    /**
     * get Value for a flag give
     *
     * @param string $flag character or character with minus sign
     *
     * @return value of this flag
     */
    public function getValue($flag)
    {
      $flag = $this->removeMinus($flag);
      $flagInSchema = $this->flagInSchema($flag);

      if ($flagInSchema === true) {
        return $this->valueForFlag($flag);
      } else {
        return $flagInSchema;
      }
    }

    /**
     * Add new flags in schema
     *
     * @param string $flag character
     * @param int    $type type constant defined in class
     *
     * @return void
     */
    public function addSchema($flag, $type)
    {
       $this->schema[$flag] = $type;
    }
    /**
     * Return default value if exist value in schema
     *
     * @param string $flag character or character with minus sign
     *
     * @return default value
     */
    public function schemaDefaultValue($flag)
    {
      $flag = $this->removeMinus($flag);
      return $this->defaults[$this->schema[$flag]];
    }
    /**
     * Return the default value if exist the flag,
     *
     * @param string $flag character or character with minus sign
     *
     * @return default Value when flag exist
     */
    public function DefaultValueIfExist($flag)
    {
      $flag = $this->removeMinus($flag);
      if ($this->schema[$flag] == self::BOOL) {
        $return = true;
      } else {
        $return = $this->schemaDefaultValue($flag);
      }
      return $return;
    }
    /**
     * Find the flag in schema if not exist retrun error
     *
     * @param string $flag character or character with minus sign
     *
     * @return True or string with Error
     */
    private function flagInSchema($flag)
    {
      $flag = $this->removeMinus($flag);
      if (!array_key_exists($flag, $this->schema)) {
        return "The $flag flag is incorrect";
      }
      return true;
    }
    /**
     * return value for a flag given or default value if not exist this
     *
     * @param string $flag character
     *
     * @return value for flag
     */
    private function valueForFlag($flag)
    {
      if (array_key_exists($flag, $this->gen)) {
        $return = $this->gen[$flag];
      } else {
        $return = $this->schemaDefaultValue($flag);
      }
      return $return;
    }
    /**
     * remove minus sign of a flag give if hava minus sign
     *
     * @param string $flag character or character with minus sign
     *
     * @return flag without minus sign
     */
    private function removeMinus($flag)
    {
      if (strlen($flag)>1) {
        $val = explode('-', $flag);
        $flag = trim($val[1]);
      }
      return $flag;
    }
}