NODE_MODULES := /usr/lib/node_modules/*/node_modules/*
PROJ_MODULES := /home/vagrant/frontend/node_modules

all: run

install: clean
	npm install

build:
	npm run gulp

clean:
	if [ -d "app" ]; then rm -fr "app/*.js"; fi
	if [ -d "typings" ]; then rm -fr "typings"; fi

dist-clean: clean
	if [ -d "node_modules" ]; then rm -fr "node_modules"; fi
	if [ -d "typings" ]; then rm -fr "typings"; fi

run:
	npm start

.PHONY:
	all \
	clean \
	clean-dist \
	dist-clean \
	clean-node-links \
	run
