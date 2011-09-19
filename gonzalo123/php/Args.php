<?php
class Args
{
    public function decode($input)
    {
        if ($input == '-l') {
            $output = array('l' => true);
        } elseif ($input == '-p') {
            $output = array('p' => true);
        }

        return $output;
    }
}
