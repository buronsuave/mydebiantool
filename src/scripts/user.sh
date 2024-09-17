#!/bin/bash

# Output user information as JSON
echo '['
cut -d: -f1,3,4,6 /etc/passwd | while IFS=: read -r username uid gid home; do
    echo "{\"username\": \"$username\", \"uid\": \"$uid\", \"gid\": \"$gid\", \"home\": \"$home\"},"
done
echo ']'
