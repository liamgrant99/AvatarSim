package hw3;

import edu.du.dudraw.Draw;

public class Road extends TerrainTile{
	private GridPoint location;
	
	public Road(GridPoint location) {
		super(location);
	}

	@Override
	public void setColor(Draw duDwin) {
		//Road maintains a constant color. 
		duDwin.setPenColor(255, 255, 0);
	}

	@Override
	public int getVeg() {
		//The road has no vegetation
		return 0;
	}

	@Override
	public int getWet() {
		// The road is not wet
		return 0;
	}

	@Override
	public int getBumpy() {
		//The road is not bumpy
		return 0;
	}
	
	
	
}
