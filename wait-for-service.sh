#!/usr/bin/env sh

#until $(curl --output /dev/null --silent --head --fail http://localhost:8080/api-docs); do
until $(curl --output /dev/null --head --fail http://localhost:8080/api-docs); do
    printf '.'
    sleep 5
done
