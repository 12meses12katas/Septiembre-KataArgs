<?php

include_once '../kataArgs.php';
/**
 * Description of ArgsTest
 *
 */
class ArgsTest extends PHPUnit_Framework_TestCase
{
  /**
   * basic setup
   *
   * @return void
   */
  public function setUp()
  {
    $this->ar = new Args();
  }

  /**
   * Generate a basic schema for test
   *
   * @return void
   */
  public function basicSchema()
  {
    $this->ar->addSchema('l', Args::BOOL);
    $this->ar->addSchema('p', Args::INT);
    $this->ar->addSchema('d', Args::STRING);
  }
  /**
   * Generate a advance schema for test
   *
   * @return void
   */
  public function advanceSchema()
  {
    $this->ar->addSchema('l', Args::BOOL);
    $this->ar->addSchema('p', Args::INT);
    $this->ar->addSchema('d', Args::ARRAY_INT);
    $this->ar->addSchema('g', Args::ARRAY_STRING);
  }
  /**
   * Data provaider for test
   *
   * @return void
   */
  public function generateDataTestCase()
  {
    return array(
                 "args complete in order" => array(
                                                   '-l -p 8080 -d /usr/logs',
                                                   array(
                                                         'l'=>true,
                                                         'p'=>'8080',
                                                         'd'=>'/usr/logs'
                                                         )
                                                   ),
                 "args in random order" => array(
                                                 '-d /usr/logs -l -p 8080',
                                                 array(
                                                       'l'=>true,
                                                       'p'=>'8080',
                                                       'd'=>'/usr/logs'
                                                       )
                                                 ),
                 "args negative int" => array(
                                              '-d /usr/logs -l -p -8080',
                                              array(
                                                    'l'=>true,
                                                    'p'=>'-8080',
                                                    'd'=>'/usr/logs'
                                                    )
                                              ),
                 "args  with duplicate values" => array(
                                                        '-p 50 -d /usr/logs -l -p 8080',
                                                        array(
                                                              'l'=>true,
                                                              'p'=>'8080',
                                                              'd'=>'/usr/logs'
                                                              )
                                                        ),
                 "args with empty args" => array(
                                                 '-d -p',
                                                 array(
                                                       'l'=>false,
                                                       'p'=>'0',
                                                       'd'=>''
                                                       )
                                                 ),
                 "without arg" => array(
                                        '',
                                        array(
                                              'l'=>false,
                                              'p'=>'0',
                                              'd'=>''
                                              )
                                        ),

                 "with flag incorrect" => array(
                                                '-d /usr/logs -l -p 8080 -w',
                                                array(
                                                      'l'=>true,
                                                      'p'=>'8080',
                                                      'd'=>'/usr/logs',
                                                      'w'=>'The w flag is incorrect'
                                                      )
                                                ),
                 );
  }

  /**
   * Data provaider for test
   *
   * @return void
   */
  public function generateAdvanceDataTestCase()
  {
    return array(
                 "args complete in order" => array(
                                                   '-l -p 8080 -d 8,7,5,3 -g this,is,a,list',
                                                   array(
                                                         'l'=>true,
                                                         'p'=>'8080',
                                                         'd'=>array(8,7,5,3),
                                                         'g'=>array('this','is','a','list'),
                                                         )
                                                   ),
                 "args empty" => array(
                                       '-l -d -g',
                                       array(
                                             'l'=>true,
                                             'd'=>array('0'),
                                             'g'=>array(''),
                                             ),
                                       ),
                 );
  }
  /**
   * Take data of data provider and generate test with basic schema
   *
   * @param string $args      line of arguments to analize
   * @param array  $testValue experated returns
   *
   * @return void
   *
   * @dataProvider generateDataTestCase
   *
   */
  public function testArgsGenerico($args, $testValue)
  {
    $this->basicSchema();
    $this->checkValues($args, $testValue);
  }

  /**
   * Take data of data provider and generate test with advance schema
   *
   * @param string $args      line of arguments to analize
   * @param array  $testValue experated returns
   *
   * @return void
   *
   * @dataProvider generateAdvanceDataTestCase
   *
   */
  public function testArgsAdvance($args, $testValue)
  {
    $this->advanceSchema();
    $this->checkValues($args, $testValue);
  }

  /**
   * make test with arguments and experated values
   *
   * @param string $args      line of arguments to analize
   * @param array  $testValue experated returns
   *
   * @return void
   */
  public function checkValues($args, $testValue)
  {
    $this->ar->parser($args);
    foreach ($testValue as $key => $test) {
      $this->assertEquals($test, $this->ar->getValue($key));
    }
  }
}

