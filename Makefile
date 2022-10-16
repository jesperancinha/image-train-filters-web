before:
	bash setup.sh
test:
	sbt test
build: build-sbt build-npm build-cypress
build-cypress:
	cd e2e && yarn
build-npm: before
	cd image-train-filters-fe && yarn && npm run build
build-sbt:
	sbt compile
	sbt clean assembly
	cp -r target service/release
test-sbt:
	sbt test
test-npm:
	cd image-train-filters-fe && npm run test
run-sbt: build-sbt
	sbt run
coverage:
	export OMNI_LOG=true
	./setup.sh
	cd image-train-filters-fe && npm run coverage
	sbt package jacoco omniReport -v
audit:
	cd image-train-filters-fe && npm audit fix && yarn
update-snyk:
	npm i -g snyk
update:
	git pull
	curl --compressed -o- -L https://yarnpkg.com/install.sh | bash
	npm install -g npm-check-updates
	cd image-train-filters-fe && npx browserslist --update-db && ncu -u && yarn
npm-test:
	cd image-train-filters-fe && npm run coverage
stop:
	docker ps -a -q --filter="name=itf" | xargs -I {} docker stop {}
	docker ps -a -q --filter="name=itf" | xargs -I {} docker rm {}
docker-clean: stop
	docker-compose down -v
	docker-compose rm -svf
docker-stop-all:
	docker ps -a --format '{{.ID}}' | xargs -I {}  docker stop {}
	docker ps -a --format '{{.ID}}' | xargs -I {}  docker rm {}
	docker network prune
docker-build:
	docker-compose build
dcd: stop docker-clean
	docker-compose -f docker-compose.yml -f docker-compose.override.yml down
dcup: dcd
	docker-compose up -d
	make itf-wait
itf-wait:
	bash itf_wait.sh
dcup-action: before dcup
dcup-full-action: before dcd docker-clean build dcup
local-pipeline: before dcd docker-clean build test-sbt test-npm
build-backend:
	docker-compose stop itf-backend
	docker-compose rm itf-backend
	make build-sbt
	docker-compose build --no-cache itf-backend
	docker-compose up -d itf-backend
