package hw3;

import edu.du.dudraw.Draw;

public class Water extends TerrainTile{
	private GridPoint location;
	
	public Water(GridPoint location) {
		super(location);
	}

	@Override
	public void setColor(Draw duDwin) {
		//Water maintains a constant color. 
		duDwin.setPenColor(0, 100, 255);
	}

	@Override
	public int getVeg() {
		//Water does not have any vegetation. 
		return 0;
	}

	@Override
	public int getWet() {
		//Water has maximum wetness
		return 100;
	}

	@Override
	public int getBumpy() {
		//Water is not bumpy
		return 0;
	}
	
	
}
