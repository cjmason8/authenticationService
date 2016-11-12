#!/bin/bash

set -e

while getopts ":p:" opt; do
  case $opt in
    # Provide commands to run
    p)
      PASSWORD="${OPTARG}"
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
echo "docker run"
docker run -p 8082:8080 -d cjmason8/auth-service:latest
echo "finished"
