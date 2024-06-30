FROM eclipse-temurin:21-alpine

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

COPY target/scala-*/image-train-filters-*.jar ${runningFolder}/image-train-filters-service.jar

COPY entrypoint.sh ${runningFolder}

EXPOSE 5000

ENTRYPOINT ["entrypoint.sh"]
