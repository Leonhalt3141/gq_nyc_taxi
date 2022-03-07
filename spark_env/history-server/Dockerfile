ARG IMAGE=gq-env-x85
ARG TAG=latest
FROM ${IMAGE}:${TAG}

COPY history-server.sh /spark/

EXPOSE 18081

CMD ["/bin/bash", "/spark/history-server.sh"]