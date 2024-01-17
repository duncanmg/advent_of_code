all: TestRunner.class Boulder.class SurfaceAreaCalculator.class

.SUFFIXES: .java .class

JAR_DIR = /home/duncan/java
export CLASSPATH=.:${JAR_DIR}/junit/junit-4.10.jar:${JAR_DIR}/junit/junit-jupiter-api-5.9.1.jar:${JAR_DIR}/junit/apiguardian-api-1.1.2.jar:${JAR_DIR}/AdventOfCode2022/common

.java.class:
	javac $< -classpath ${CLASSPATH} -Xlint

SurfaceAreaCalculator.class: SurfaceAreaCalculator.java Boulder.class

TestRunner.class: TestRunner.java
