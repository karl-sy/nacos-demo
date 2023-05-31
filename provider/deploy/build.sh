#!/bin/sh
#set -e

export REGISTRY=localhost:5001/

#cd "$(dirname "$0")"

docker build . -t ${REGISTRY}provider:1.0.0

if [ -n "${REGISTRY}" ]; then
    docker push ${REGISTRY}provider:1.0.0
fi