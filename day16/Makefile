all: Network.class Data.class Cave.class RouteMapper.class Route.class RouteComparator.class Logger.class TestRunner.class TestRouteMapper.class TestRouteMapperWithOpen.class RoutePair.class RoutePairComparator.class

.SUFFIXES: .java .class

CLASSPATH = .:/home/duncan.garland/java/junit/junit-4.10.jar:../../junit/jupiter-api-5.9.1.jar:../../junit/apiguardian-api-1.1.2.jar

.java.class:
	javac $< -classpath ${CLASSPATH} -Xlint

RouteMapper.class:	RouteMapper.java Route.class RouteComparator.class Cave.class RoutePair.class RoutePairComparator.class

TestRouteMapper.class: TestRouteMapper.java RouteMapper.class Route.class Cave.java

TestRouteMapperWithOpen.class: TestRouteMapperWithOpen.java RouteMapper.class Route.class Cave.java

# TestValve.class: TestValve.java Valve.class

# TestRoute.class:	TestRoute.java

TestRunner.class: TestRunner.java TestRouteMapper.class TestRouteMapperWithOpen.class RouteMapper.class
