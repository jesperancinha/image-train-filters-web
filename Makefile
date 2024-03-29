b: build
before:
	bash setup.sh
test:
	sbt test
build: build-sbt build-npm
build-cypress:
	cd e2e && yarn
build-npm: before build-cypress
	cd image-train-filters-fe && yarn && npm run build
build-sbt:
	mvn dependency:go-offline
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
	find . -name "package-lock.json" | xargs rm; \
	find . -name "yarn.lock" | xargs rm; \
	git pull; \
	curl --compressed -o- -L https://yarnpkg.com/install.sh | bash; \
	npm install caniuse-lite; \
	npm install -g npm-check-updates; \
	cd image-train-filters-fe; \
		yarn; \
		npx browserslist --update-db; \
		ncu -u; \
		yarn
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
cypress-open-docker:
	cd e2e && yarn && npm run cypress:open:docker
cypress-open:
	cd e2e && yarn && npm run cypress:open:electron
cypress-electron: status-containers
	cd e2e && make cypress-electron
cypress-chrome: status-containers
	cd e2e && make cypress-chrome
cypress-firefox: status-containers
	cd e2e && make cypress-firefox
cypress-edge: status-containers
	cd e2e && make cypress-edge
status-containers:
	docker ps
sec-check-npm:
	cd image-train-filters-fe; \
	yarn install; \
	sudo npm i -g snyk; \
	npm run snyk
