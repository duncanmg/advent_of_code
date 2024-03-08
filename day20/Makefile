JAR_DIR = /home/duncan/java
COMMON=${JAR_DIR}/AdventOfCode2022/common
export CLASSPATH=.:${JAR_DIR}/junit/junit-4.10.jar:${JAR_DIR}/junit/junit-jupiter-api-5.9.1.jar:${JAR_DIR}/junit/apiguardian-api-1.1.2.jar:${COMMON}

all: application_classes test_classes TestRunner.class

application_classes: RunMixer.class Mixer.class State.class

test_classes: TestMixer.class TestState.class

.SUFFIXES: .java .class

.java.class:
	javac $< -classpath ${CLASSPATH} -Xlint

Mixer.class: Mixer.java State.class

RunMixer: RunMixer.java Mixer.class

TestRunner.class: TestRunner.java test_classes

TestMixer.class: TestMixer.java Mixer.class State.class

TestState.class: TestState.java State.class
