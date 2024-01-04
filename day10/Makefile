all: SignalStrength.class

CLASSPATH = .:/home/duncan.garland/java/junit/junit-4.10.jar
SignalStrength.class: Instruction.class
	export CLASSPATH=.:/home/duncan.garland/java/junit/junit-4.10.jar
	javac SignalStrength.java -classpath . -Xlint

Instruction.class:
	export CLASSPATH=.:/home/duncan.garland/java/junit/junit-4.10.jar
	javac Instruction.java -classpath . -Xlint


