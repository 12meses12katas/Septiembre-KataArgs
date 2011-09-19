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

    public function testSimpleInput()
    {
        $actual   = $this->args->decode("-l");
        $expected = array('l' => true);
        $this->assertEquals($expected, $actual);
    }
}