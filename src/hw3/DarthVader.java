package hw3;

import java.util.List;
import java.util.Map;

import edu.du.dudraw.Draw;

public class DarthVader extends Avatar{
	//path holds the list of gridpoints leading to the target location. 
	protected List<GridPoint> path;		
	private DarthVaderState state; 
	
	//Darth Vader implements a State behavorial pattern. If includes two states: Moving and Stationary.
	
	
	public DarthVader(TerrainMap terrainMap, GridPoint location) {
		super(terrainMap, location);
		path = null;
		//State is initially set to stationary.
		this.state = new DarthVaderStationaryState(this);
	}

	@Override
	public void draw(Draw duDwin) {
		//Draws the darth vader picture at the x and y location.
		duDwin.picture(location.getX()+.5, location.getY()+.5,"darthVader.png",1,1);
		
	}

	public void moveInitatior(PathFinder newPath, GridPoint Target) {
		//moveInitiator is called on the state.
		state.moveInitiator(newPath, Target);
	}
	
	@Override
	public void move() {
		//move is called on the state.
		state.move();
	}
	
	public void changeState(DarthVaderState state) {
		//Changes the state of DarthVader to either stationary or moving. 
		this.state = state;
	}

	
}
