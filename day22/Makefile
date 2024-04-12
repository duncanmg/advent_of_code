JAR_DIR = /home/duncan/java
COMMON=${JAR_DIR}/AdventOfCode2022/common
export CLASSPATH=.:${JAR_DIR}/junit/junit-4.10.jar:${JAR_DIR}/junit/junit-jupiter-api-5.9.1.jar:${JAR_DIR}/junit/apiguardian-api-1.1.2.jar:${COMMON}

all: application_classes test_classes TestRunner.class

application_classes: RunInstructions.class Cube.class CubeOne.class CubeTwo.class Side.class CubeFactory.class

test_classes: TestRunInstructions.class TestCubeFactory.class TestSideChanges.class TestSideChanges2.class

.SUFFIXES: .java .class

.java.class:
	javac $< -classpath ${CLASSPATH} -Xlint

CubeOne.class: CubeOne.java Cube.class Side.class

CubeTwo.class: CubeTwo.java Cube.class Side.class

TestRunner.class: 	TestRunner.java test_classes

TestRunInstructions.class: TestRunInstructions.java RunInstructions.class

TestSideChanges.class: TestSideChanges.java RunInstructions.class

TestSideChanges2.class: TestSideChanges2.java RunInstructions.class

TestCubeFactory.class: TestCubeFactory.java CubeFactory.class
