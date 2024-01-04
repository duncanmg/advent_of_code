#! /usr/local/bin/bash
# set -x
make
RES=$?
if [ $RES != 0 ]
then
	exit $RES
fi
export CLASSPATH=.:/home/duncan.garland/java/junit/junit-4.10.jar:../../junit/jupiter-api-5.9.1.jar:../../junit/apiguardian-api-1.1.2.jar
FILE=$1
DATAFILE=$2
# javac $1.java -classpath . -Xlint
javac $1.java
RES=$?
if [ $RES == 0 ]
then
  java $FILE $DATAFILE
fi
