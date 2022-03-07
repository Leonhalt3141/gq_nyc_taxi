ARG IMAGE=gq-env/spark-base
ARG TAG=3.2.0-hadoop3.2
FROM ${IMAGE}:${TAG}

COPY master.sh /spark/

ENV SPARK_MASTER_PORT 7077
ENV SPARK_MASTER_WEBUI_PORT 8080
ENV SPARK_MASTER_LOG /spark/logs

EXPOSE 8080 7077 6066
USER root
CMD ["/bin/bash", "/spark/master.sh"]