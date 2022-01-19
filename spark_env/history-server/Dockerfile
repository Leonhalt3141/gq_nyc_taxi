ARG SPARK_VERSION=3.1.1
ARG HADOOP_VERSION=3.2
FROM gq-env/spark-base:${SPARK_VERSION}-hadoop${HADOOP_VERSION}

COPY history-server.sh /

EXPOSE 18081

CMD ["/bin/bash", "/history-server.sh"]