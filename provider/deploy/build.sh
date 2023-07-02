#!/bin/sh
#set -e

export REGISTRY=localhost:5001/


# Zookeeper本地启动
# docker run --name some-zookeeper -p 2181:2181 -d zookeeper:3.6.2

#cd "$(dirname "$0")"

docker build . -t ${REGISTRY}provider:1.0.0

if [ -n "${REGISTRY}" ]; then
    docker push ${REGISTRY}provider:1.0.0
fi