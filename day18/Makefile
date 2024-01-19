all: TestRunner.class Cube.class SurfaceAreaCalculator.class Context.class AirPocketIdentifier.class Lava.class Air.class Outside.class

.SUFFIXES: .java .class

JAR_DIR = /home/duncan/java
export CLASSPATH=.:${JAR_DIR}/junit/junit-4.10.jar:${JAR_DIR}/junit/junit-jupiter-api-5.9.1.jar:${JAR_DIR}/junit/apiguardian-api-1.1.2.jar:${JAR_DIR}/AdventOfCode2022/common

.java.class:
	javac $< -classpath ${CLASSPATH} -Xlint

Cube.class: Cube.java Lava.class Air.class

SurfaceAreaCalculator.class: SurfaceAreaCalculator.java Cube.class Context.class AirPocketIdentifier.class Lava.class Air.class Outside.class

TestRunner.class: TestRunner.java SurfaceAreaCalculator.class TestCube.class TestContext.class TestLava.class Air.class TestAirPocketIdentifier.class
