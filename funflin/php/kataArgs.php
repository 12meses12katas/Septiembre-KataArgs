<?php
  /**
   * Class for parser comand-line arguments
   */
class Args
{
  private $dataArray = array();

  const INT = 0;
  const BOOL = 1;
  const STRING = 2;
  const ARRAY_STRING = 3;
  const ARRAY_INT = 4;

  private $schema = array();
  private $typeProperties = array(
                                  self::BOOL   => array('default' =>false, 'empty' =>true),
                                  self::INT    => array('default' =>'0'),
                                  self::STRING  => array('default' =>''),
                                  self::ARRAY_STRING => array('default' => array(''), 'array' => true),
                                  self::ARRAY_INT => array('default' =>array('0'),'array' => true),
                                  );

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
   * parser for line with arguments and data
   *
   * @param string $argsIn string with arguments and data
   *
   * @return void
   */
  public function parser($argsIn)
  {
    $lineInput = preg_split('/(-[a-z] *)/', $argsIn, -1, PREG_SPLIT_NO_EMPTY+PREG_SPLIT_DELIM_CAPTURE);
    $lastFlag = false;
    foreach ($lineInput as $frame) {
      if (preg_match('/(-[a-z])/', $frame)) {
        if ($lastFlag != false) {
          if ($this->flagInSchema($lastFlag) === true ) {
            $this->dataArray[$lastFlag] = $this->DefaultIfEmpty($lastFlag);
          }
        }
        $lastFlag = $this->removeMinus($frame);
      } else {
        $this->insertValue($lastFlag, $frame);
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
   * Insert value in array of result
   *
   * @param string $flag flag to insert
   * @param string $data with value of argument
   *
   * @return void
   */
  private function insertValue($flag, $data)
  {
    $data = trim($data);
    if ($this->getProperty($flag, 'array')) {
      $this->dataArray[$flag] = explode(',', $data);
    } else {
      $this->dataArray[$flag] = $data;
    }
  }
  /**
   * find property of flag in type of data
   *
   * @param string $flag     character
   * @param string $property to find
   *
   * @return value of property or false
   */
  private function getProperty($flag, $property)
  {
    $data = $this->typeProperties[$this->schema[$flag]];
    if (array_key_exists($property, $data)) {
      $value = $data[$property];
    } else {
      $value = false;
    }
    return $value;
  }

  /**
   * Return default value if exist value in schema
   *
   * @param string $flag character or character with minus sign
   *
   * @return default value
   */
  private function schemaDefaultValue($flag)
  {
    $flag = $this->removeMinus($flag);
    return $this->getProperty($flag, 'default');
  }
  /**
   * Return the default value if exist the flag,
   *
   * @param string $flag character or character with minus sign
   *
   * @return default Value when flag exist
   */
  private function DefaultIfEmpty($flag)
  {
    $flag = $this->removeMinus($flag);
    $property = $this->getProperty($flag, 'empty');

    if ($property) {
      $return = $property;
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
    if (array_key_exists($flag, $this->dataArray)) {
      $return = $this->dataArray[$flag];
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