#!/bin/bash

set -e

while getopts ":p:e:" opt; do
  case $opt in
    # Provide commands to run
    p)
      PASSWORD="${OPTARG}"
    ;;
    e)
      ENV="${OPTARG}"
    ;;
    \?)
      echo "Invalid option -$OPTARG" >&2
    ;;
  esac
done

echo "docker login"
docker login --username=cjmason8 --password=$PASSWORD
echo "docker pull"
docker pull cjmason8/auth-service:latest
echo "docker compose"
docker-compose up -d -f ${ENV}/docker-compose.${ENV}.yml authService
echo "finished"
