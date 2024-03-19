#! /usr/bin/bash

FROM_DIR=$1
echo Setting up from $FROM_DIR
cp $FROM_DIR/run.sh .
cp $FROM_DIR/Makefile .
cp $FROM_DIR/TestRunner.java .
cp $FROM_DIR/logger.conf .

