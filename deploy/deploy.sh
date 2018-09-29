#!/bin/bash

set -e

cd "$(dirname "$0")"

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

TAG_NAME=$(<../VERSION)
echo -e "TAG_NAME=$TAG_NAME" > env.txt

echo "docker login"
docker login --username=cjmason8 --password=$PASSWORD
echo "docker pull"
docker pull cjmason8/auth-service:$TAG_NAME
echo "docker compose"
pwd
docker-compose -e env.txt -f ${ENV}/docker-compose-${ENV}.yml up -d authService
echo "finished"
