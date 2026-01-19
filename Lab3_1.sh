#!/bin/bash

read -r num1 num2

if [[ -z "$num1" || -z "$num2" ]]; then
    echo "You should enter two numbers!"
    exit 1
fi

if [[ "$num1" -eq "$num2" ]]; then
    echo "These two numbers are equal!"
    exit 0
fi

if [[ "$num1" -gt "$num2" ]]; then
    big=$num1
    small=$num2
else
    big=$num2
    small=$num1
fi

if (( big % small == 0 )); then
    n=$(( big / small ))
    echo "$big is $n times $small!"
else
    echo "No relation!"
fi

