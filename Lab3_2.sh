#!/bin/bash

generate_random_number() {
    echo $(( RANDOM % 100 + 1 ))
}

play_game() {
    local target=$(generate_random_number)
    local attempts=0
    local guess

    echo "Welcome to the Number Guessing Game! I have selected a number between 1 and 100. Can you guess it? Enter your guess (or type ’exit’ to quit)"
    
    while true; do
        read -p "Enter your guess: " guess
        
        if [[ "$guess" == "exit" ]]; then
            echo "Thanks for playing! The number was $target."
            break
        fi
        
        if ! [[ "$guess" =~ ^[0-9]+$ ]]; then
            echo "Invalid input. Please enter a positive integer."
            continue
        fi
        
        guess=$((guess))
        ((attempts++))
        
        if (( guess < target )); then
            echo "Too low! Try again."
        elif (( guess > target )); then
            echo "Too high! Try again."
        else
            echo "Congratulations! You guessed the number in $attempts attempts."
            break
        fi
    done
}
e
play_game

