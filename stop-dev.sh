#!/bin/bash

# Stop Webapp "workroom"
mvn jetty:stop -Pdev

# Stop Postgres DB
docker-compose down