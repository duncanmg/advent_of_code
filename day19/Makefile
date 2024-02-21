JAR_DIR = /home/duncan/java
COMMON=${JAR_DIR}/AdventOfCode2022/common
export CLASSPATH=.:${JAR_DIR}/junit/junit-4.10.jar:${JAR_DIR}/junit/junit-jupiter-api-5.9.1.jar:${JAR_DIR}/junit/apiguardian-api-1.1.2.jar:${COMMON}

all: TestRunner.class BlueprintFactory.class Blueprint.class RobotStrategy.class TestRobotStrategy.class TestBlueprint.class TestBlueprintFactory.class StrategyIterator.class TestStrategyIterator.class TestOptimizer.class Optimizer.class TestOptimizerBlueprintOne.class TestRobotStrategyBlueprintOne.class RunBlueprintOptimization.class Calculator.class TestCalculator.class TestOptimizerVariousBlueprints.class Robot.class OreRobot.class ClayRobot.class ObsidianRobot.class GeodeRobot.class NoneRobot.class Stock.class

.SUFFIXES: .java .class

.java.class:
	javac $< -classpath ${CLASSPATH} -Xlint

# Data.class: ${COMMON}/Data.java

# Logger.class: ${COMMON}/Logger.java

BlueprintFactory.class: BlueprintFactory.java Blueprint.class

Optimizer.class: Optimizer.java Blueprint.class RobotStrategy.class StrategyIterator.class Calculator.class Robot.class OreRobot.class

RunBlueprintOptimization.class: RunBlueprintOptimization.java Optimizer.class Blueprint.class RobotStrategy.class

Robot.class: Robot.java ClayRobot.class ObsidianRobot.class GeodeRobot.class NoneRobot.class Stock.class

ClayRobot.class: Stock.class

TestRunner.class: TestRunner.java BlueprintFactory.java TestBlueprintFactory.class TestBlueprint.class TestRobotStrategy.class TestStrategyIterator.class TestOptimizer.class TestOptimizerBlueprintOne.class TestRobotStrategyBlueprintOne.class TestCalculator.class TestOptimizerVariousBlueprints.class

