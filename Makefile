#
#
#
#SHELL := /usr/local/bin/bash


all:
	@echo all

clean:
	echo rm -rf tmp/*

#
# misc-lib relate targets
#

# to make project independent from misc-lib, copy files from misc-lib
build-yokwe:
	cd ../yokwe-root/; mvn clean ant:ant install

build:
	mvn clean ant:ant install

#
# ANTLR
#
compile-antlr-symbol:
	rm    -rf tmp/antlr
	mkdir -p  tmp/antlr
	ant       compile-antlr-symbol
	rm    -rf src/yokwe/majuro/symbol/antlr/*
	cp    -p  tmp/antlr/*.java src/yokwe/majuro/symbol/antlr
