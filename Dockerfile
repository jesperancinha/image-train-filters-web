FROM jesperancinha/je-all-build:0.0.1

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY docker-files/default.conf /etc/nginx/conf.d/default.conf

COPY docker-files/nginx.conf /etc/nginx/nginx.conf

COPY image-train-filters-service/target/scala-2.13/image-train-filters-service-assembly-1.0.0-SNAPSHOT.jar ${runningFolder}

COPY image-train-filters-fe/dist /usr/share/nginx/html

COPY entrypoint.sh ${runningFolder}

RUN nginx -t

EXPOSE 5000

EXPOSE 8080

ENTRYPOINT ["entrypoint.sh"]
