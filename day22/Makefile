JAR_DIR = /home/duncan/java
COMMON=${JAR_DIR}/AdventOfCode2022/common
export CLASSPATH=.:${JAR_DIR}/junit/junit-4.10.jar:${JAR_DIR}/junit/junit-jupiter-api-5.9.1.jar:${JAR_DIR}/junit/apiguardian-api-1.1.2.jar:${COMMON}

all: application_classes test_classes TestRunner.class

application_classes: Layout.class RunInstructions.class

test_classes: TestLayout.class TestRunInstructions.class

.SUFFIXES: .java .class

.java.class:
	javac $< -classpath ${CLASSPATH} -Xlint

TestRunner.class: 	TestRunner.java test_classes

TestLayout.class:	TestLayout.java Layout.class

TestRunInstructions.class: TestRunInstructions.java RunInstructions.class
