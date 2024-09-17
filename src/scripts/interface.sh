#!/bin/bash

# Example script to output network configuration in JSON format
echo '['
# Read the network configuration file
while IFS= read -r line; do
    if [[ $line != \#* && ! -z $line ]]; then  # Skip comments and empty lines
        param=$(echo $line | cut -d ' ' -f 1)
        value=$(echo $line | cut -d ' ' -f 2-)
        echo "{\"parameter\": \"$param\", \"value\": \"$value\"},"
    fi
done < /etc/network/interfaces
echo ']'
