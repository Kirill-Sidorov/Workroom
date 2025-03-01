#!/bin/bash

# Build Webapp "workroom"
mvn clean install

# Build "workroom" image
docker-compose build workroom

# Launch Postgres DB and Webapp "workroom" in the background (-d option)
docker-compose up -d