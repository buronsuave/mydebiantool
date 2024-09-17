#!/bin/bash
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <path> <permissions>"
    exit 1
fi

PATH_TO_CHANGE="$1"
PERMISSIONS="$2"

# Change permissions
chmod "$PERMISSIONS" "$PATH_TO_CHANGE"

if [ $? -eq 0 ]; then
    echo "Permissions changed successfully."
else
    echo "Error changing permissions."
    exit 1
fi