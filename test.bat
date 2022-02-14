#!/bin/bash
set arg1=%1
ECHO "Aby skrypt zadzialal nalezy podac bezwzgledna sciezke do pliku tekstowego jako argument w konsoli. Przyklad: test 'C:\\Users\\Robert\\tadeusz.txt'"
ECHO OFF
curl -X POST -F file=@%arg1% "localhost:8080"
pause