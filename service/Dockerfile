FROM openjdk:17-alpine

ENV runningFolder /usr/local/bin/

WORKDIR ${runningFolder}

RUN apk --no-cache add curl

COPY target/scala-*/image-train-filters-*.jar ${runningFolder}/image-train-filters-service.jar

COPY entrypoint.sh ${runningFolder}

EXPOSE 5000

ENTRYPOINT ["entrypoint.sh"]
