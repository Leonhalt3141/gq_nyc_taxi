ARG IMAGE=gq-env/spark-base
ARG TAG=3.2.0-hadoop3.2
FROM ${IMAGE}:${TAG}

COPY worker.sh /spark/

ENV SPARK_WORKER_WEBUI_PORT 8081
ENV SPARK_WORKER_LOG /spark/logs
ENV SPARK_MASTER "spark://spark-master:7077"

EXPOSE 8081

CMD ["/bin/bash", "/spark/worker.sh"]