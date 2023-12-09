package hw3;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import edu.du.dudraw.Draw;

public class XWing extends Avatar{
	//inWater determines whether the avatar should be in the water or not. 
	private boolean inWater = true;
	private int stepCount;
	private GridPoint target;

	public XWing(TerrainMap terrainMap, GridPoint location) {
		super(terrainMap, location);
		target = location;
		stepCount = 0;
	}

	@Override
	public void move() {

		boolean waterFound = false;
		boolean landFound = false;
		List<GridPoint> neighbors = null;
		int levels = 1;
		
		if(stepCount == 50) {
			inWater = !inWater;
			stepCount = 0;
		}
		
		
			//If the XWing is in water but looking to get out of the water, and its current target is water, its look for a target not in water.
			if(terrainMap.getWetOfTile(location)==100&&!inWater&&terrainMap.getWetOfTile(target)>25) {
				//XWing looks at increasing levels of neighbors until a tile that is not water is found. 
				//When said tile is found, the target is set to that tile. 
				while(!landFound) {
					neighbors = location.getNeighbors(levels);
					for (GridPoint gp: neighbors) {
						if(terrainMap.getWetOfTile(terrainMap.getGridPoint(gp))<=25) {
							target = terrainMap.getGridPoint(gp);
							landFound = true;
						}
					}
					levels++;
				}	
			}
			
			//If the XWing is on land but looking to be in water, and its current target is on land, it looks to find water. 
			if(terrainMap.getWetOfTile(location)<100&&inWater&&terrainMap.getWetOfTile(target)<=25) {
				//XWing looks at increasing levels of neighbors until a water tile is found. 
				//When said tile is found, the target is set to that tile. 
				while(!waterFound) {
					neighbors = location.getNeighbors(levels);
					for (GridPoint gp: neighbors) {
						if(terrainMap.getWetOfTile(terrainMap.getGridPoint(gp))>25) {
							target = terrainMap.getGridPoint(gp);
							waterFound = true;
						}
					}
					levels++;
				}	
			}
		
		//Normal behavior 
		//If the XWing is in water and wants to be in water, find another spot in water. 
		if (terrainMap.getWetOfTile(location)==100&&inWater) {
			neighbors = location.getNeighbors(1);
			Collections.shuffle(neighbors);
			for (GridPoint gp: neighbors) {
				if(terrainMap.getWetOfTile(terrainMap.getGridPoint(gp))==100) {
					target = terrainMap.getGridPoint(gp);
				}
			}
		}
		
		//Normal behavior 
		//If the XWing is not in water and does wants to be in water, find another spot not in water. 
		if (terrainMap.getWetOfTile(location)<=25&&!inWater) {
			neighbors = location.getNeighbors(1);
			Collections.shuffle(neighbors);
			for (GridPoint gp: neighbors) {
				if(terrainMap.getWetOfTile(terrainMap.getGridPoint(gp))<=25) {
					target = terrainMap.getGridPoint(gp);
				}
			}
		}
		
		//XWing is moved to the target.
		moveToTarget(this.target);
		stepCount++;
		
	}

	@Override
	public void draw(Draw duDwin) {
		//If the XWing is in water, draw the water png, if not in water, draw the not in water png.
		if(inWater) {
			duDwin.picture(location.getX()+.5, location.getY()+.5, "xWingClosed.png", 1, 1);
		}else {
			duDwin.picture(location.getX()+.5, location.getY()+.5, "xWingOpen.png", 1, 1);
		}
	}
}
