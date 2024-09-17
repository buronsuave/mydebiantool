#!/bin/bash

network_info=$(ip -j addr)
echo "$network_info"
