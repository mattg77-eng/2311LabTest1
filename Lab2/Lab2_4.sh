#!/bin/bash

if [ "$#" -eq 0 ]; then 
	echo "No arguments provided."
	exit 1
fi

echo -n "Please enter an integer: "
read x

if ! [[ "$x" =~ ^[0-9]+$ ]]; then
	echo "Invalid input. Please enter a valid integer."
	exit 1
fi

if [ "$x" -le "$#" ]; then 
	echo "The argument at position $x is: ${!x}"
	else
	echo "No argument has been provided at position $x."
fi
