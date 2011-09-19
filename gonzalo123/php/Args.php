<?php
class Args
{
    public function decode($input)
    {
        if ($input == '-l') {
            $output = array('l' => true);
        }

        return $output;
    }
}
