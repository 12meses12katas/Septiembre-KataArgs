/*
 * Autor: Andres F. Cardenas (Andrewnix)
 * This program, is write on C++, is a parser command-line.
 * This is the header file, contains the functions that use main cpp
*/

#include <iostream>
#include <string>
#include <cstdlib>

using namespace std;

const string L = "-l";
const string P = "-p";
const string D = "-d";

void imprimirInfo(bool *, string *, string *);
void setPort(char **, int, int, string *);
void setLoggin(char **, int, int, bool *);
void setDirectory(char **, int, int, string *);

// Esta funcion imprime la informacion legiblemente
// this function print the info legibly
void printInfo(bool *loggin, string *port, string *dir)
{
    if (*loggin == false)
    {
        cout << "Loggin: false" << endl;
    }

    else if (*loggin == true)
    {
        cout << "Loggin: true" << endl;
    }

    cout << "Port: " << *port << endl;
    cout << "Directory: " << *dir << endl;
}

// Esta funcion asigna el valor adecuado a loggin
// this function assign the value suitable to loggin
void setLoggin(char **argv, int i, int argc, bool *loggin)
{
    if (argv[i] == L)
    {
        if (argv[argc-1] == L)
        {
            *loggin = false;
        }
        else if (argv[i+1] != P && argv[i+1] != D && (atoi(argv[i+1])) > 0)
        {
            *loggin = true;
        }
        else
        {
            *loggin = false;
        }
    }
}

// Esta funcion asigna el valor adecuado a port
// this function assign the value suitable to port
void setPort(char **argv, int i, int argc, string *port)
{
    if (argv[i] == P)
    {
        if (argv[argc-1] == P)
        {
            *port = "0";
        }
        else if (argv[i+1] != L && argv[i+1] != D && (atoi(argv[i+1])) > 0)
        {
            *port = argv[i+1];
        }
        else
        {
            *port = "0";
        }
    }
}

// Esta funcion asigna el valor adecuado a dir
// this function assign the value suitable to dir
void setDirectory(char **argv, int i, int argc, string *dir)
{
    if (argv[i] == D)
    {
        if (argv[argc-1] == D)
        {
            *dir = "";
        }
        else if (argv[i+1] != P && argv[i+1] != L)
        {
            *dir = argv[i+1];
        }
        else
        {
            *dir = "";
        }
    }
}
