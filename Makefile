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
