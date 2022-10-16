#!/usr/bin/env bash

machine='dev'

if [[ ! -z "$1" ]]
then
      let machine=$1
fi

docker-machine start ${machine}

docker-machine env ${machine}

eval $(docker-machine env  ${machine})

docker-compose down

docker stop image-train-filters-scala_itf_1
docker rm image-train-filters-scala_itf_1

cd image-train-filters-service
sbt clean assembly
cd ..

cd image-train-filters-fe
yarn autoclean --init --force
yarn build
cd ..

docker-compose up -d --build --remove-orphans
