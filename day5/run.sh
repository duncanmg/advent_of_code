#! /usr/local/bin/bash
# set -x
FILE=$1
javac $1.java -classpath . -Xlint
RES=$?
if [ $RES == 0 ]
then
  java $FILE
fi
