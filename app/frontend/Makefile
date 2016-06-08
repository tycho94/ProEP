all: install run

install:
	npm install

build:
	npm run gulp

clean:
	if [ -d "app" ]; then rm -fr "app/"; fi
	if [ -d "typings" ]; then rm -fr "typings/"; fi

dist-clean: clean
	if [ -d "npm_modules" ]; then rm -fr "npm_modules/"; fi

run:
	npm start

.PHONY:
	all \
	clean \
	run
