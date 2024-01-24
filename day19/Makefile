JAR_DIR = /home/duncan/java
COMMON=${JAR_DIR}/AdventOfCode2022/common
export CLASSPATH=.:${JAR_DIR}/junit/junit-4.10.jar:${JAR_DIR}/junit/junit-jupiter-api-5.9.1.jar:${JAR_DIR}/junit/apiguardian-api-1.1.2.jar:${COMMON}

all: TestRunner.class BlueprintFactory.class Blueprint.class RobotStrategy.class TestRobotStrategy.class TestBlueprint.class TestBlueprintFactory.class StrategyIterator.class TestStrategyIterator.class

.SUFFIXES: .java .class

.java.class:
	javac $< -classpath ${CLASSPATH} -Xlint

# Data.class: ${COMMON}/Data.java

# Logger.class: ${COMMON}/Logger.java

BlueprintFactory.class: BlueprintFactory.java Blueprint.class


TestRunner.class: TestRunner.java BlueprintFactory.java TestBlueprintFactory.class TestBlueprint.class TestRobotStrategy.class TestStrategyIterator.class

