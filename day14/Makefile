all: Data.class Grain.class Rock.class Grid.class Particle.class Empty.class Coords.class Detector.class

.SUFFIXES: .java .class

CLASSPATH = .:/home/duncan.garland/java/junit/junit-4.10.jar:../../junit/jupiter-api-5.9.1.jar:../../junit/apiguardian-api-1.1.2.jar

.java.class:
	javac Data.java -classpath ${CLASSPATH} -Xlint

Detector.class:	Detector.java Rock.class Grain.class Empty.class Coords.class
	javac Detector.java -classpath ${CLASSPATH} -Xlint

Grid.class:	Grid.java Rock.class Grain.class Empty.class Coords.class
	javac Grid.java -classpath ${CLASSPATH} -Xlint

Grain.class:	Grain.java Particle.class
	javac Grain.java -classpath ${CLASSPATH} -Xlint

Rock.class:	Rock.java Particle.class
	javac Rock.java -classpath ${CLASSPATH} -Xlint

Particle.class:	Particle.java
	javac Particle.java -classpath ${CLASSPATH} -Xlint

Empty.class:	Particle.java
	javac Empty.java -classpath ${CLASSPATH} -Xlint

Coords.class:	Coords.java
	javac Coords.java -classpath ${CLASSPATH} -Xlint
