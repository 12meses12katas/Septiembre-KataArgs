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
    $this->ar->parser($args);
    foreach ($testValue as $key => $test) {
      $this->assertEquals($test, $this->ar->getValue($key));
    }
  }

}
/*
 * Stage2
 * Añadir soporte para listas
 */