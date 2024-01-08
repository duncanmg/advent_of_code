all:Data.class Logger.class TestRunner.class Chamber.class Rock.class Square.class Horizontal.class Cross.class Step.class Vertical.class Coords.class RepetitionFinder.class ArgumentProcessor.class StateMachine.class Simulator.class JetGenerator.class RockGenerator.class EndTester.class

.SUFFIXES: .java .class

JAR_DIR = /home/duncan/java
export CLASSPATH=.:${JAR_DIR}/junit/junit-4.10.jar:${JAR_DIR}/junit/junit-jupiter-api-5.9.1.jar:${JAR_DIR}/junit/apiguardian-api-1.1.2.jar

# CLASSPATH = .:/home/duncan.garland/java/junit/junit-4.10.jar:../../junit/jupiter-api-5.9.1.jar:../../junit/apiguardian-api-1.1.2.jar

.java.class:
	javac $< -classpath ${CLASSPATH} -Xlint

Square.class: Rock.class

Horizontal.class: Rock.class

Cross.class: Rock.class

Step.class: Rock.class

Vertical.class: Rock.class

Rock.class: Rock.java Coords.class

Simulator.class: Chamber.class Rock.class Square.class Horizontal.class Cross.class Step.class Vertical.class ArgumentProcessor.class StateMachine.class JetGenerator.class RockGenerator.class EndTester.class

# TestSimulator.class: TestSimulator.java Simulator.class TestChamber.class TestCycle.class

RepetitionFinder.class: RepetitionFinder.java Chamber.class

TestRepetitionFinder.class: TestRepetitionFinder.java RepetitionFinder.class

TestChamber.class: TestChamber.java Chamber.class

TestRunner.class: TestRunner.java TestSimulator01Basic.class TestSimulator02PartOne.class TestSimulator03Repetition.class TestChamber.class TestRock.class TestCycle.class Simulator.class TestRepetitionFinder.class
