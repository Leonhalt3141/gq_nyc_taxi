#!/bin/bash

BASE_IMAGE=arm64v8/ubuntu
IMAGE_TAG=20.04

set -e

SPARK_VERSION=3.1.1
HADOOP_VERSION=3.2
TAG=${SPARK_VERSION}-hadoop${HADOOP_VERSION}

build() {
    NAME=$1
    IMAGE=gq-env/spark-$NAME:$TAG
    cd $([ -z "$2" ] && echo "./$NAME" || echo "$2")
    echo '--------------------------' building $IMAGE in $(pwd)
    docker build --build-arg IMAGE=${BASE_IMAGE} --build-arg TAG=${IMAGE_TAG} -t $IMAGE .
    cd -
}

if [ $# -eq 0 ]
  then
    build base
    build master
    build worker
    build history-server
    build submit
    build maven-template template/maven
    build sbt-template template/sbt
    build python-template template/python

    build python-example examples/python
  else
    build $1 $2
fi