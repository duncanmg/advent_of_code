import java.util.*;

class Network {

    public static void main(String[] args) {
		if (args.length == 0) {
		   Network obj = new Network();
		}
		else {
		   Network obj = new Network(args[0]);
		}
     }

    public Network(String file) {
		Data dataObj = new Data(file);
		data = dataObj.data;
		run();
    }

    public Network() {
		System.out.println("Network compiled");
    }

	ArrayList<String> data;

	ArrayList<Route> routes = new ArrayList<Route>(0);

	HashMap<String, Cave> cavesByName = new HashMap<String, Cave>();

	public void run() {

        ArrayList<Cave> caves = new ArrayList<Cave>(0);
		System.out.println(this.data.size() + " data lines to load");
		for (int x=0; x<this.data.size(); x++) {
			Cave cave = new Cave(this.data.get(x));
			caves.add(cave);
			cavesByName.put(cave.name, cave);
		}
		System.out.println(caves.size() + " caves loaded");

		try {
        	// RouteMapper mapper = new RouteMapper(caves.get(0), caves);
        	RouteMapper mapper = new RouteMapper(this.cavesByName.get("AA"), caves);

//			mapper.humanCavesToKeep.add(new Cave(this.cavesByName.get("TM")));
//			mapper.humanCavesToKeep.add(new Cave(this.cavesByName.get("TK")));
//			mapper.humanCavesToKeep.add(new Cave(this.cavesByName.get("OV")));
//			mapper.humanCavesToKeep.add(new Cave(this.cavesByName.get("OV")));
//			mapper.humanCavesToKeep.add(new Cave(this.cavesByName.get("YW")));
//			
//			mapper.elephantCavesToKeep.add(new Cave(this.cavesByName.get("TM")));
//			mapper.elephantCavesToKeep.add(new Cave(this.cavesByName.get("TK")));
//			mapper.elephantCavesToKeep.add(new Cave(this.cavesByName.get("OV")));
//			mapper.elephantCavesToKeep.add(new Cave(this.cavesByName.get("NN")));
//			mapper.elephantCavesToKeep.add(new Cave(this.cavesByName.get("FJ")));

			// mapper.debug = true;
        	ArrayList<RoutePair> routes = mapper.map(26);
        	// mapper.dumpRoutes(routes);

			System.out.println("01 Back in Network");
			RoutePair firstRoute = routes.get(0);
			System.out.println("02 Back in Network");
			System.out.println(firstRoute.toString());
			System.out.println("03 Back in Network");
			System.out.println(firstRoute.estimatedTotalFlow());
			System.out.println("04 Back in Network");
		}
		catch (Exception e) {
			 System.out.println(e);
		}

		System.out.println("Done");
	}

}
