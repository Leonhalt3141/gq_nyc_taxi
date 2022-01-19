ARG IMAGE=arm64v8/ubuntu
ARG TAG=20.04
FROM ${IMAGE}:${TAG}

ARG SBT_VERSION=1.4.9

# Install sbt
RUN \
  mkdir /working/ && \
  cd /working/ && \
  apt-get update && \
  apt-get install -y curl openjdk-8-jdk && \
  curl -L -o sbt-$SBT_VERSION.deb https://repo.scala-sbt.org/scalasbt/debian/sbt-$SBT_VERSION.deb && \
  dpkg -i sbt-$SBT_VERSION.deb && \
  rm sbt-$SBT_VERSION.deb && \
  apt-get update && \
  apt-get install -y sbt && \
  cd && \
  rm -r /working/ && \
  sbt sbtVersion #   && \
  # remove cash
  && apt-get autoremove -y && \
  apt-get clean -y all && \
  rm -rf /var/lib/apt/lists/* /var/cache/apt/archives/*


WORKDIR /home/