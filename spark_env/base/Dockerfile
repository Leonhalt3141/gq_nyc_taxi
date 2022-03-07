ARG IMAGE=gq-env-x85
ARG TAG=latest
FROM ${IMAGE}:${TAG}

USER root
ENV ENABLE_INIT_DAEMON false
ENV INIT_DAEMON_BASE_URI http://identifier/init-daemon
ENV INIT_DAEMON_STEP spark_master_init

ENV SPARK_VERSION=3.2.0 \
    HADOOP_VERSION=3.2 \
    DEBIAN_FRONTEND=noniteractive

USER ubuntu
WORKDIR /home
COPY wait-for-step.sh /
COPY execute-step.sh /
COPY finish-step.sh /

USER root
WORKDIR /
RUN apt-get update && apt-get install -y \
      curl wget openjdk-8-jdk python3 python3-pip coreutils procps \
      && ln -s /lib64/ld-linux-x86-64.so.2 /lib/ld-linux-x86-64.so.2 \
      && chmod +x *.sh \
      && wget https://archive.apache.org/dist/spark/spark-${SPARK_VERSION}/spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz \
      && tar -xvzf spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz \
      && mv spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION} spark \
      && rm spark-${SPARK_VERSION}-bin-hadoop${HADOOP_VERSION}.tgz \
      && cd /

#Give permission to execute scripts
RUN chmod +x /wait-for-step.sh && chmod +x /execute-step.sh && chmod +x /finish-step.sh

# Fix the value of PYTHONHASHSEED
# Note: this is needed when you use Python 3.3 or greater
ENV PYTHONHASHSEED 1
USER ubuntu