test:
	sbt test
build:
	sbt compile
run: build
	sbt run
coverage:
	export OMNI_LOG=true
	cd image-train-filters-fe && npm run coverage
	sbt assembly package jacoco omniReport -v
install-update:
	npm i -g snyk
	npm install -g npm-check-updates
	cd image-train-filters-fe && ncu -u && yarn
audit:
	cd image-train-filters-fe && npm audit fix && yarn
