package hw3;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import edu.du.dudraw.Draw;

public class Wookie extends VegetationAvatar{
	private int stepCount;
	private GridPoint target;
	
	public Wookie(TerrainMap terrainMap, GridPoint location) {
		super(terrainMap, location);
		stepCount = 0;
		target = location;
	}

	@Override
	public void draw(Draw duDwin) {
		//The picture is drawn at the location.
		duDwin.picture(location.getX()+.5, location.getY()+.5, "wookie.png", 1, 1);
		
	}

	@Override
	public void move() {
		//Moves the wookie.
		//neighbors tracks the the neighboring gridpoints
		//mountainFound tracks whether a mountain tile has been found yet. 
		List<GridPoint> neighbors = null;
		boolean mountainFound = false;
		
		//Wookie moves every five stepCounts. 
		if(stepCount%5==0) {
			//5 vegetation is eaten from the current tile. 
			eatVegetation(5);
			
			//Searches for new place to go if there is no vegetation left on the tile. 
			if (terrainMap.getVegOfTile(location)==0) {
				neighbors = location.getNeighbors(1);
				Collections.shuffle(neighbors);
				
				//Target is set to the tile with vegetation that is also a mountain tile, if one exists. 
				for (GridPoint gp: neighbors) {
					if(terrainMap.getVegOfTile(terrainMap.getGridPoint(gp))>0&&terrainMap.getBumpyOfTile(terrainMap.getGridPoint(gp))>0) {
						target = terrainMap.getGridPoint(gp);
						mountainFound = true;
					}
				}
				
				//If a mountain tile with vegetation is not found, a random location that is not water or grass is chosen. 
				if(!mountainFound) {
					Collections.shuffle(neighbors);
					for (GridPoint gp: neighbors) {
						if(terrainMap.getWetOfTile(terrainMap.getGridPoint(gp))<=25&&terrainMap.getBumpyOfTile(terrainMap.getGridPoint(gp))>0) {
							target = terrainMap.getGridPoint(gp);
						}
					}
				}
			}
			//Wookie is moved to the target. 
			moveToTarget(this.target);
		}
		
		//Step count is incremented each call. 
		stepCount++;

	}

}
