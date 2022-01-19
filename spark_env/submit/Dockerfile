ARG SPARK_VERSION=3.1.1
ARG HADOOP_VERSION=3.2
FROM gq-env/spark-base:${SPARK_VERSION}-hadoop${HADOOP_VERSION}

ENV SPARK_MASTER_NAME spark-master
ENV SPARK_MASTER_PORT 7077

COPY submit.sh /

CMD ["/bin/bash", "/submit.sh"]