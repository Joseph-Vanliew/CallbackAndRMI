#!/bin/bash

# Ensure the output directories exist
mkdir -p ../out/production/Server
mkdir -p ../out/production/Interfaces

# Check if the interfaces directory exists
if [ ! -d ../out/production/Interfaces/interfaces ]; then
    # Compile the interfaces if the directory does not exist
    javac -d ../out/production/Interfaces ../Interfaces/src/interfaces/*.java
    echo "Compiling the Interfaces module..."
else
    echo "Interfaces package is already compiled."
fi

echo "Compiling the Server module..."
# Compile the server code
javac -d ../out/production/Server -cp ../out/production/Interfaces ../Server/src/server/*.java

echo "Starting the Server..."
# Run the server
java -cp ../out/production/Server:../out/production/Interfaces server.MyServer