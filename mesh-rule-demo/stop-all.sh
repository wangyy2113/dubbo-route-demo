#!/bin/bash

echo "
----------stopping all container...------------"

docker stop $(docker ps -q)

docker rm zkserver

echo "
----------stopped------------------------------"