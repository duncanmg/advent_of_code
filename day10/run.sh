#! /usr/local/bin/bash
# set -x
make
export CLASSPATH=.:/home/duncan.garland/java/junit/junit-4.10.jar
FILE=$1
DATAFILE=$2
# javac $1.java -classpath . -Xlint
javac $1.java
RES=$?
if [ $RES == 0 ]
then
  java $FILE $DATAFILE
fi
