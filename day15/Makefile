all: Data.class GenericGrid.class Empty.class Coords.class Item.class Sensor.class TestSensor.class Beacon.class NoBeacon.class BeaconLocator.class ItemXComparator.class ItemYComparator.class IntArrayComparator.class TestGrid.class IntArrayColComparator.class

.SUFFIXES: .java .class

CLASSPATH = .:/home/duncan.garland/java/junit/junit-4.10.jar:../../junit/jupiter-api-5.9.1.jar:../../junit/apiguardian-api-1.1.2.jar

.java.class:
	javac $< -classpath ${CLASSPATH} -Xlint

BeaconLocator.class: Grid.class

Grid.class: GenericGrid.class Beacon.class NoBeacon.class Sensor.class
	javac Grid.java -classpath ${CLASSPATH} -Xlint

GenericGrid.class:	GenericGrid.java Empty.class Coords.class Item.class
	javac GenericGrid.java -classpath ${CLASSPATH} -Xlint

Empty.class:	Empty.java Item.java
	javac Empty.java -classpath ${CLASSPATH} -Xlint

Coords.class:	Coords.java
	javac Coords.java -classpath ${CLASSPATH} -Xlint

Sensor.class:	Coords.java
	javac Sensor.java -classpath ${CLASSPATH} -Xlint
