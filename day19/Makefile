JAR_DIR = /home/duncan/java
COMMON=${JAR_DIR}/AdventOfCode2022/common
export CLASSPATH=.:${JAR_DIR}/junit/junit-4.10.jar:${JAR_DIR}/junit/junit-jupiter-api-5.9.1.jar:${JAR_DIR}/junit/apiguardian-api-1.1.2.jar:${COMMON}

# all: TestRunner.class BlueprintFactory.class Blueprint.class RobotStrategy.class TestRobotStrategy.class TestBlueprint.class TestBlueprintFactory.class StrategyIterator.class TestStrategyIterator.class TestOptimizer.class Optimizer.class TestOptimizerBlueprintOne.class TestRobotStrategyBlueprintOne.class RunBlueprintOptimization.class Calculator.class TestCalculator.class TestOptimizerVariousBlueprints.class Robot.class OreRobot.class ClayRobot.class ObsidianRobot.class GeodeRobot.class NoneRobot.class Stock.class

all: application_classes test_classes TestRunner.class

application_classes: Blueprint.class BlueprintFactory.class Calculator.class ClayRobot.class GeodeRobot.class NoneRobot.class ObsidianRobot.class Optimizer.class OreRobot.class Robot.class RobotStrategy.class RunBlueprintOptimization.class Stock.class StrategyIterator.class Cache.class

test_classes: TestBlueprint.class TestBlueprintFactory.class TestCalculator.class TestOptimizer.class TestOptimizerBlueprintOne.class TestOptimizerVariousBlueprints.class TestRobotStrategy.class TestRobotStrategyBlueprintOne.class  TestStrategyIterator.class

.SUFFIXES: .java .class

.java.class:
	javac $< -classpath ${CLASSPATH} -Xlint

TestRunner.class: TestRunner.java BlueprintFactory.java TestBlueprintFactory.class TestBlueprint.class TestRobotStrategy.class TestStrategyIterator.class TestOptimizer.class TestOptimizerBlueprintOne.class TestRobotStrategyBlueprintOne.class TestCalculator.class TestOptimizerVariousBlueprints.class

Blueprint.class: Blueprint.java

BlueprintFactory.class: BlueprintFactory.java Blueprint.class

Calculator.class: Calculator.java RobotStrategy.class

ClayRobot.class: ClayRobot.java Robot.class

GeodeRobot.class: GeodeRobot.java Robot.class

NoneRobot.class: NoneRobot.java Robot.class

ObsidianRobot.class: ObsidianRobot.java Robot.class

Optimizer.class: Optimizer.java RobotStrategy.class

OptimizerWithCalculator: OptimizerWithCalculator.java Optimizer.class

OptimizerWithDepthFirstSearch: OptimizerWithDepthFirstSearch.java Optimizer.class

OptimizerWithCache: OptimizerWithCache.java Optimizer.class

OreRobot.class: OreRobot.java Robot.class

Robot.class: Robot.java

RobotStrategy.class: RobotStrategy.java OreRobot.class ClayRobot.class ObsidianRobot.class GeodeRobot.class

RunBlueprintOptimization.class: RunBlueprintOptimization.java Optimizer.class BlueprintFactory.class OptimizerWithCache.class OptimizerWithDepthFirstSearch.class OptimizerWithCalculator.class

Stock.class: Stock.java

StrategyIterator.class: StrategyIterator.java

TestBlueprint.class: TestBlueprint.java Blueprint.class

TestBlueprintFactory.class: TestBlueprintFactory.java BlueprintFactory.class

TestCalculator.class: TestCalculator.java Calculator.class

TestOptimizer.class: TestOptimizer.java Optimizer.class

TestOptimizerBlueprintOne.class: TestOptimizerBlueprintOne.java Optimizer.class

TestOptimizerVariousBlueprints.class: TestOptimizerVariousBlueprints.java Optimizer.class

TestRobotStrategy.class: TestRobotStrategy.java RobotStrategy.class

TestRobotStrategyBlueprintOne.class: TestRobotStrategyBlueprintOne.java RobotStrategy.class

TestRunner.class: TestRunner.java test_classes

TestStrategyIterator.class: TestStrategyIterator.java StrategyIterator.class

Cache.class: Cache.java
