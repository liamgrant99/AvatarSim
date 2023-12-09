package hw3;

import edu.du.dudraw.Draw;

public class Grass extends TerrainTile{
	private GridPoint location;
	private int vegetation_level;
	private int[] rgb_colors;
	
	public Grass(GridPoint location) {
		super(location);
		
		//Vegetation level automatically set to 100.
		vegetation_level = 100;
		//A list that represents the rgb colors is created. 
		rgb_colors = new int[] {0,250,0};
	}
	
	@Override
	public void setColor(Draw duDwin) {
		//Vegetation levels is subtracted from 250 to get the specified shade of green. 
		rgb_colors[1] = 250-vegetation_level;
		
		//The pen color is set based off of the rgb_colors array. 
		duDwin.setPenColor(rgb_colors[0],rgb_colors[1],rgb_colors[2]);
	}

	@Override
	public int getVeg() {
		//The vegetation level is returned for the amount of vegetation on the tile. 
		return vegetation_level;
	}

	@Override
	public int getWet() {
		//Tile has some wetness for path finding algorithm
		return 25;
	}

	@Override
	public int getBumpy() {
		//The grass tile is not bumpy. 
		return 0;
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
