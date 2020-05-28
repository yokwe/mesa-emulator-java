#
#
#
SHELL := /bin/bash

all:
	@echo all

clean:
	echo rm -rf tmp/*

#
# misc-lib relate targets
#

# to make project independent from misc-lib, copy files from misc-lib
copy-misc-lib-files:
	mkdir -p data/jar data/market
	cp ../misc-lib/tmp/build/jar/misc-lib.jar data/jar
	cp ../misc-lib/data/jar/*                 data/jar
	cp ../misc-lib/data/market/*              data/market

build-misc-lib:
	pushd ../misc-lib/; ant build ; popd; make copy-misc-lib-files


#
# ANTLR
#
compile-antlr-symbol:
	rm    -rf tmp/antlr
	mkdir -p  tmp/antlr
	ant       compile-antlr-symbol
	rm    -rf src/yokwe/majuro/symbol/antlr/*
	cp    -p  tmp/antlr/*.java src/yokwe/majuro/symbol/antlr
