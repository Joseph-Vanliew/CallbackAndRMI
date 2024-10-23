#!/bin/bash

# Ensure the output directories exist
mkdir -p ../out/production/Client
mkdir -p ../out/production/Interfaces

# Check if the interfaces directory exists
if [ ! -d ../out/production/Interfaces/interfaces ]; then
    # Compile the interfaces if the directory does not exist
    javac -d ../out/production/Interfaces ../Interfaces/src/interfaces/*.java
    echo "Compiling the Interfaces module..."
else
    echo "Interfaces package is already compiled."
fi

# Check if the client class files exist
if [ ! -f ../out/production/Client/client/MyClient.class ]; then
    echo "Compiling the Client module..."
    # Compile the client code only if the compiled class files don't exist
    javac -d ../out/production/Client -cp ../out/production/Interfaces ../Client/src/client/*.java
else
    echo "Client is already compiled."
fi

echo "Running the Client..."
# Run the client
java -cp ../out/production/Client:../out/production/Interfaces client.MyClient
