all: Director.class Grid.class Position.class Move.class

CLASSPATH = .:/home/duncan.garland/java/junit/junit-4.10.jar

Director.class: Grid.java Position.java Move.class
	export CLASSPATH=.:/home/duncan.garland/java/junit/junit-4.10.jar
	javac Director.java -classpath . -Xlint

Grid.class: Position.java Move.java
	export CLASSPATH=.:/home/duncan.garland/java/junit/junit-4.10.jar
	javac Grid.java -classpath ${CLASSPATH} -Xlint


Position.class: Move.java
	export CLASSPATH=.:/home/duncan.garland/java/junit/junit-4.10.jar
	javac Position.java -classpath . -Xlint

Move.class:
	export CLASSPATH=.:/home/duncan.garland/java/junit/junit-4.10.jar
	javac Move.java -classpath . -Xlint

