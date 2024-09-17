#!/bin/bash

# Update package lists
echo "Updating package lists..."
sudo apt-get update

# Upgrade packages
echo "Upgrading packages..."
sudo apt-get upgrade -y

# Upgrade distribution
echo "Upgrading distribution..."
sudo apt-get dist-upgrade -y

# Clean up
echo "Cleaning up..."
sudo apt-get autoremove -y
sudo apt-get clean

echo "Update complete."