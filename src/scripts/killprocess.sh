#!/bin/bash
if [ -z "$1" ]; then
    echo "No PID provided"
    exit 1
fi

kill "$1"