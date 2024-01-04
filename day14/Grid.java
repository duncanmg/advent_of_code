import java.io.IOException;
import java.util.*;

// The grid can hold Rocks or Grains, both of which are subclasses of Particle.
@SuppressWarnings("serial")
class Grid {

    public static void main(String[] args) {
		   Grid obj = new Grid();
     }

    public Grid() {
    }

	private ArrayList<Particle> particles = new ArrayList<Particle>(0);

	public void addRock(Rock rock) {
		this.particles.add(rock);
	}

	public void addGrain(Grain rock) {
		this.particles.add(rock);
	}

	public void addRock(Integer xRight, Integer yDown) {
		this.particles.add(new Rock(xRight, yDown));
	}

	public Particle getParticle(int xRight, int yDown) {
		for (int x=0; x<this.particles.size(); x++) {
			Particle p = this.particles.get(x);
			if (p.xRight == xRight && p.yDown == yDown) {
				return p;
			}
		}
		return new Empty();
	}

	public Particle getParticle(Coords c) {
		return this.getParticle(c.xRight, c.yDown);
	}

	public void addPaths(String paths) {
		String[] bits = paths.split(" ");

		String from = bits[0];
		for (int x=1; x<bits.length; x++){
			if (bits[x].equals("->")) {
				continue;
			}
			this.addPath(new Coords(from), "->", new Coords(bits[x]));
			from = bits[x];
		}
	}

	public void addPath(Coords from, String separator, Coords to) {
	   Coords current = from;
	   Boolean more = true;
       while ( more == true ) {
		   if (current.isEqual(to) == true) {
			   more = false;
		   }
		   if (this.isEmpty(current) == true) {
		       this.addRock(current.xRight, current.yDown);
		   }
		   if (current.xRight < to.xRight) {
			   current = new Coords(current.xRight+1, current.yDown);
		   }
		   else if (current.xRight > to.xRight) {
			   current = new Coords(current.xRight-1, current.yDown);
		   }
		   else if ( current.yDown < to.yDown ) {
			   current = new Coords(current.xRight, current.yDown+1);
		   }
		   else if ( current.yDown > to.yDown ) {
			   current = new Coords(current.xRight, current.yDown-1);
		   }
	   }

	}

	Boolean isFloor(Coords c) {
		if (c.yDown >= this.floor.yDown) {
			return true;
		}
		return false;
	}

	Boolean isEmpty(Coords c) {
		Particle p = this.getParticle(c.xRight, c.yDown);
		if ( p instanceof Empty) {
			return true;
		}
		return false;
	}

	Boolean isDownEmpty(Coords c) {
		Coords down = new Coords(c.xRight, c.yDown + 1);
		if (this.isFloor(down) == true) {
			return false;
		}
		return this.isEmpty(down);
	}

	Boolean isDiagLeftEmpty(Coords c) {
		Coords down = new Coords(c.xRight-1, c.yDown + 1);
		if (this.isFloor(down) == true) {
			return false;
		}
		return this.isEmpty(down);
	}

	Boolean isDiagRightEmpty(Coords c) {
		Coords down = new Coords(c.xRight+1, c.yDown + 1);
		if (this.isFloor(down) == true) {
			return false;
		}
		return this.isEmpty(down);
	}

	public void findLowestRock() {
		Particle lowest = new Particle(0,0);
		for (int x=0; x<this.particles.size();x++) {
           Particle r = this.particles.get(x);
		   if (r.yDown > lowest.yDown) {
			   lowest = r;
		   }
		}
		this.floor = new Particle(lowest.xRight, lowest.yDown+2);
	}

	Particle floor;

	public Boolean dropGrain() {
		Coords start = new Coords(500, 0);

		Coords current = new Coords(start.xRight, start.yDown);
		System.out.println("lowestRock: " + this.floor.yDown);
		while (this.isEmpty(start)) {
			// System.out.println("current: " + current);
			if (this.isDownEmpty(current) == true) {
				// System.out.println("Down is empty");
				current = new Coords(current.xRight, current.yDown+1);
			}
			else if (this.isDiagLeftEmpty(current) == true) {
				current = new Coords(current.xRight-1, current.yDown+1);
			}
			else if (this.isDiagRightEmpty(current) == true) {
				current = new Coords(current.xRight+1, current.yDown+1);
			}
			else {
				Grain grain = new Grain(current.xRight, current.yDown);
				System.out.println("Add grain at " + current);
				this.addGrain(grain);
				return true;
			}
			
		}

		return false;
	}

	public String toString() {
		return this.particles.toString();
	}
}
