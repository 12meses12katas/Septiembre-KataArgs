<?php
include ('../Args.php');

class ArgstTest extends PHPUnit_Framework_TestCase
{
    /** @var Args */
    private $args;

    public function setUp()
    {
        $schema = array(
            'l'  => Args::BOOLEAN,
            'll' => Args::BOOLEAN,
            'h'  => Args::STRING,
            'n'  => Args::STRING,
            'p'  => Args::INTEGER,
            'g'  => Args::ARRAY_STRING,
            'd'  => Args::ARRAY_NUMBER
        );
        $this->args = new Args($schema);
    }

    public function inputDataProvider()
    {
        return array(
            'single parameter 1'  => array('-l', array('l' => true)),
            'multiple parameter 1' => array('-l -ll', array('l' => true, 'll' => true)),

            'parameter with value 1' => array('-h host', array('h' => 'host')),

            'mix 1' => array('-h host -l', array('h' => 'host', 'l' => true)),

            'mix 2' => array('-l -p 8080 -n /usr/logs', array(
                'l' => true,
                'p' => 8080,
                'n' => '/usr/logs'
            )),

            'lists 1' => array('-g this,is,a,list -d 1,2,-3,5', array(
                'g' => array('this', 'is', 'a', 'list'),
                'd' => array(1, 2, -3, 5),
            )),

            'parameter with -' => array('-p -1', array('p' => -1)),

            'lists 2' => array('-g this,is,a,list -p -1 -d 1,2,-3,5', array(
                'g' => array('this', 'is', 'a', 'list'),
                'd' => array(1, 2, -3, 5),
                'p' => -1,
            )),
        );
    }

    /** @dataProvider inputDataProvider */
    public function testInput($input, $expected)
    {
        $actual = $this->args->decode($input);
        foreach($expected as $key => $value) {
            $this->assertEquals($value, $actual[$key]);
        }
    }
}