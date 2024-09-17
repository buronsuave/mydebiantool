#!/bin/bash
USERNAME=$1
FULLNAME=$2
PASSWORD=$3

# Add the user with full name
sudo useradd -m -c "$FULLNAME" $USERNAME

# Set the password for the user
echo "$USERNAME:$PASSWORD" | sudo chpasswd
