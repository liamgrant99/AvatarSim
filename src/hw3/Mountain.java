package hw3;

import edu.du.dudraw.Draw;


public class Mountain extends TerrainTile{
	private GridPoint location;
	private int vegetation_level;
	private int[] rgb_colors;
	
	public Mountain(GridPoint location) {
		super(location);
		this.vegetation_level = (int)(Math.random()*50);
		this.rgb_colors = new int[] {100,50+this.vegetation_level,100};
	}
	
	@Override
	public void setColor(Draw duDwin) {
		//The amount of green on the tile is set by adding the vegetation level to 50. 
		rgb_colors[1] = 50+this.vegetation_level;
		duDwin.setPenColor(rgb_colors[0],rgb_colors[1],rgb_colors[2]);
	}

	@Override
	public int getVeg() {
		// There is vegetation_level amount of vegetation on the tile. 
		return vegetation_level;
	}

	@Override
	public int getWet() {
		// The mountain tile is dry
		return 0;
	}

	@Override
	public int getBumpy() {
		// The mountain tile is bumpy
		return 100;
	}
	
	public void consumeVegetation(int reduceAmount) {
		//Vegetation on the tile is reduced by the inputed amount. If the inputed amount is greater than the amount left, the vegetation level is set to 0. 
		if(vegetation_level>=reduceAmount) {
			vegetation_level -= reduceAmount;
		}else {
			vegetation_level = 0;
		}
	}
	
}
