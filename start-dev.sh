#!/bin/bash

# Start Postgres DB (only postgres-workroomdb service) in the background (-d option)
docker compose up -d postgres-workroomdb

# Start Webapp "workroom" use jetty maven plugin (profile "dev" (-Pdev))
mvn clean install -Pdev

