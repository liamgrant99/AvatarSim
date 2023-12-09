package hw3;

import java.util.List;
import java.util.Map;

import edu.du.dudraw.Draw;

public abstract class Avatar implements Drawable{
	protected TerrainMap terrainMap;
	protected GridPoint location;

	protected Avatar(TerrainMap terrainMap, GridPoint location) {
		this.terrainMap = terrainMap;
		this.location = location;
	}
	
	public abstract void move();
	
	public abstract void draw(Draw duDwin);
	
	public void moveToTarget(GridPoint theTarget) {
		//The x and y coordinate of the new gridpoint are instatiated.
		int x = location.getX();
		int y = location.getY();
		
		//Moves if current location does not equal the target location. 
		if (!location.equals(theTarget)) {  
			//If the target gridpoint's x val is greater or less than the current, one is added or substracted to the current location.
			if(location.getX()>theTarget.getX()) {
				x -= 1;
			}else if(location.getX()<theTarget.getX()) {
				x += 1;
			}
			
			//If the target gridpoint's y val is greater or less than the current, one is added or subtracted from the current location. 
			if(location.getY()>theTarget.getY()) {
				y -= 1;
			}else if(location.getY()<theTarget.getY()) {
				y += 1;
			}
			//The avatar's location is set to the new gridpoint.  
			location = terrainMap.getGridPoint(new GridPoint(x,y));
		}
	}
	
}
