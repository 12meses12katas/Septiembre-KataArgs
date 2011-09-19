<?php
include ('../Args.php');

class ArgstTest extends PHPUnit_Framework_TestCase
{
    /** @var Args */
    private $args;

    public function setUp()
    {
        $this->args = new Args();
    }
    public function testInstalation()
    {
        $this->assertTrue(true);
    }

    public function simpleInputDataProvider()
    {
        return array(
            array('-l', array('l' => true)),
            array('-p', array('p' => true))
        );
    }

    /** @dataProvider simpleInputDataProvider */
    public function testSimpleInput($input, $expected)
    {
        $this->assertEquals($expected, $this->args->decode($input));
    }
}