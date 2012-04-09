/*
 * Autor: Andres F. Cardenas (Andrewnix)
 * This program, is write on C++, is a parser command-line.
*/

#include <string>
#include "headerKataArgs.hpp"

int main(int argc, char **argv)
{
    bool loggin = false;
    string port = "0";
    string dir = "";

    for(int i = 0; i < argc; ++i)
    {
        setLoggin(argv, i, argc, &loggin);
        setPort(argv, i, argc, &port);
        setDirectory(argv, i, argc, &dir);
    }

    printInfo(&loggin, &port, &dir);

    return 0;
}
