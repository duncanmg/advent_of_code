JAR_DIR = /home/duncan/java
COMMON=${JAR_DIR}/AdventOfCode2022/common
export CLASSPATH=.:${JAR_DIR}/junit/junit-4.10.jar:${JAR_DIR}/junit/junit-jupiter-api-5.9.1.jar:${JAR_DIR}/junit/apiguardian-api-1.1.2.jar:${COMMON}

all: application_classes test_classes TestRunner.class

application_classes: Operation.class Add.class Subtract.class Multiply.class Divide.class Constant.class RunCalculator.class

test_classes: TestOperation.class TestAdd.class TestSubtract.class TestMultiply.class TestDivide.class TestConstant.class

.SUFFIXES: .java .class

.java.class:
	javac $< -classpath ${CLASSPATH} -Xlint

TestRunner.class: TestRunner.java test_classes

Add.class: Add.java Operation.class

Subtract.class: Subtract.java Operation.class

Multiply.class: Multiply.java Operation.class

Divide.class: Divide.java Operation.class

Constant.class: Constant.java Operation.class


