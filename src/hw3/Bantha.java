package hw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import edu.du.dudraw.Draw;

public class Bantha extends VegetationAvatar{
	private int stepCount;
	private GridPoint target;
	
	public Bantha(TerrainMap terrainMap, GridPoint location) {
		super(terrainMap, location);
		target = location;
		stepCount = 0;
	}

	@Override
	public void draw(Draw duDwin) {
		//The picture is drawn at the location.
		duDwin.picture(location.getX()+.5, location.getY()+.5, "bantha.png", 1, 1);		
	}

	@Override
	public void move() {
		//Moves the bantha. 
		List<GridPoint> neighbors = null;
		boolean grassFound = false;
		
		//Moves bantha toward target 
		//Bantha only moves if not at target or if current vegetation level is zero. 
		
		//Searches for new target
		//Moves every 5 calls.
		if (stepCount%5==0) {
			//Vegetation is eaten on the tile
			eatVegetation(20);
			
			//Searches for new place to go if there is no vegetation left on the tile. 
			if (terrainMap.getVegOfTile(location)==0) {
				neighbors = location.getNeighbors(1);
				Collections.shuffle(neighbors);
				
				//Target is set to the tile with vegetation, if one exists. 
				for (GridPoint gp: neighbors) {
					if(terrainMap.getVegOfTile(terrainMap.getGridPoint(gp))>0&&terrainMap.getBumpyOfTile(terrainMap.getGridPoint(gp))==0) {
						target = terrainMap.getGridPoint(gp);
						grassFound = true;
					}
				}
				
				//If a grass tile with vegetation is not found, a random location that is not water or mountain is chosen. 
				if(!grassFound) {
					Collections.shuffle(neighbors);
					for (GridPoint gp: neighbors) {
						if(terrainMap.getWetOfTile(terrainMap.getGridPoint(gp))<=25&&terrainMap.getBumpyOfTile(terrainMap.getGridPoint(gp))==0) {
							target = terrainMap.getGridPoint(gp);
						}
					}
				}
			}
			
			//Bantha is moved to the target. 
			moveToTarget(this.target);
		}
		
		//Step count is incremented each call. 
		stepCount++;
	}

}
