ARG IMAGE=gq-env-x85
ARG TAG=latest
FROM ${IMAGE}:${TAG}

ENV SPARK_MASTER_NAME spark-master
ENV SPARK_MASTER_PORT 7077

COPY submit.sh /spark/

CMD ["/bin/bash", "/spark/submit.sh"]