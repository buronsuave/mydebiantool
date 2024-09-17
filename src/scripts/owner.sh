#!/bin/bash
if [ "$#" -lt 1 ] || [ "$#" -gt 3 ]; then
    echo "Usage: $0 <path> [<owner> [<group>]]"
    exit 1
fi

PATH_TO_CHANGE="$1"
OWNER="${2:-}"
GROUP="${3:-}"

# Change owner and/or group
if [ -n "$OWNER" ] && [ -n "$GROUP" ]; then
    sudo chown "$OWNER:$GROUP" "$PATH_TO_CHANGE"
elif [ -n "$OWNER" ]; then
    sudo chown "$OWNER" "$PATH_TO_CHANGE"
elif [ -n "$GROUP" ]; then
    sudo chown :"$GROUP" "$PATH_TO_CHANGE"
else
    echo "No owner or group specified."
    exit 1
fi

if [ $? -eq 0 ]; then
    echo "Owner and/or group changed successfully."
else
    echo "Error changing owner and/or group."
    exit 1
fi