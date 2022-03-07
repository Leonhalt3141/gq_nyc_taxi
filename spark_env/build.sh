#!/bin/bash

#BASE_IMAGE=gq-env/spark-base
#IMAGE_TAG=3.2.0-hadoop3.2

set -e

SPARK_VERSION=3.2.0
HADOOP_VERSION=3.2
TAG=${SPARK_VERSION}-hadoop${HADOOP_VERSION}

build() {
    NAME=$1
    IMAGE=gq-env/spark-$NAME:$TAG
    cd $([ -z "$2" ] && echo "./$NAME" || echo "$2")
    echo '--------------------------' building $IMAGE in $(pwd)
#    docker build --build-arg IMAGE=${BASE_IMAGE} --build-arg TAG=${IMAGE_TAG} -t $IMAGE .
    docker build -t $IMAGE .

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