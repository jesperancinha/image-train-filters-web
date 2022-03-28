test:
	sbt test
build:
	sbt compile
run: build
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
