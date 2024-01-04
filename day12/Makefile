all: TestRunner.class Grid.class Position.class Selector.class Data.class

CLASSPATH = .:/home/duncan.garland/java/junit/junit-4.10.jar:../../junit/jupiter-api-5.9.1.jar:../../junit/apiguardian-api-1.1.2.jar

Finder.class: Grid.java Position.class Data.class
	javac Finder.java -classpath ${CLASSPATH} -Xlint

Grid.class: Grid.java Position.class Data.class
	javac Grid.java -classpath ${CLASSPATH} -Xlint

Position.class: Position.java Data.class Selector.class
	javac Position.java -classpath ${CLASSPATH} -Xlint

Selector.class: Selector.java
	javac Selector.java -classpath ${CLASSPATH} -Xlint

Data.class: Data.java
	javac Data.java -classpath ${CLASSPATH} -Xlint

TestRunner.class: TestRunner.java TestPosition.class
	javac TestRunner.java -classpath ${CLASSPATH} -Xlint

TestPosition.class: TestPosition.java Grid.class Position.class
	javac TestPosition.java -classpath ${CLASSPATH} -Xlint

