all: Thrower.class Parser.class Monkey.class TestItem.class Operation.class Data.class

CLASSPATH = .:/home/duncan.garland/java/junit/junit-4.10.jar

Thrower.class: Monkey.class TestItem.class Operation.class Data.class Parser.class
	javac Parser.java -classpath ${CLASSPATH} -Xlint

Parser.class: Monkey.class TestItem.class Operation.class Data.class
	javac Parser.java -classpath ${CLASSPATH} -Xlint

Monkey.class: TestItem.class
	javac Monkey.java -classpath ${CLASSPATH} -Xlint

TestItem.class:
	javac TestItem.java -classpath ${CLASSPATH} -Xlint

Operation.class:
	javac Operation.java -classpath ${CLASSPATH} -Xlint

Data.class:
	javac Data.java -classpath ${CLASSPATH} -Xlint

