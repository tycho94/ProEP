NODE_MODULES := /usr/lib/node_modules/*/node_modules/*
PROJ_MODULES := /home/vagrant/frontend/node_modules

all: install run

install: clean-node-links
	sudo npm install -g && \
	mkdir -p $(PROJ_MODULES) && \
	ln -fsv $(NODE_MODULES) $(PROJ_MODULES)

build:
	npm run gulp

clean:
	if [ -d "app" ]; then rm -fr "app/*.js"; fi
	if [ -d "typings" ]; then rm -fr "typings"; fi

clean-dist: clean clean-node-links
	npm ls -gp --depth=0 | awk -F/ '/node_modules/ && !/\/npm$/ {print $NF}' | xargs sudo npm -g rm

clean-node-links:
	if [ -d $(PROJ_MODULES) ]; then sudo rm -fr $(PROJ_MODULES); fi

dist-clean: clean-dist clean

run:
	npm start

.PHONY:
	all \
	clean \
	clean-dist \
	dist-clean \
	clean-node-links \
	run
