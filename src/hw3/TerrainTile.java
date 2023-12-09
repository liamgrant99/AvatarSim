package hw3;

import edu.du.dudraw.Draw;

// This is an abstract class
public abstract class TerrainTile implements Drawable {
	
	// Every tile has a location
	private GridPoint location;
	
	protected TerrainTile (GridPoint location) {
		this.location = location;
	}

	// Draws the tile on the given Window
	public void draw(Draw duDwin) {
		// Gets the derived class to set whatever color it wants by calling 
		// the abstract method setColor.
		setColor(duDwin);
		
		//A rectangle is drawn at the x,y location. 
		duDwin.filledRectangle(location.getX()+.5, location.getY()+.5, .5, .5);
		
	}
	
	// Part of the draw template for concrete tiles to set the color
	public abstract void setColor(Draw duDwin);
	
	public abstract int getVeg();
	
	public abstract int getWet();
	
	public abstract int getBumpy();
	
}
