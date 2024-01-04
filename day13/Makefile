all: Data.class Packet.class TestPacket.class

.SUFFIXES: .java .class

CLASSPATH = .:/home/duncan.garland/java/junit/junit-4.10.jar:../../junit/jupiter-api-5.9.1.jar:../../junit/apiguardian-api-1.1.2.jar

.java.class:
	javac Data.java -classpath ${CLASSPATH} -Xlint

TestPacket.class:	TestPacket.java Packet.class
	javac TestPacket.java -classpath ${CLASSPATH} -Xlint

Packet.class:	Packet.java
	javac Packet.java -classpath ${CLASSPATH} -Xlint
