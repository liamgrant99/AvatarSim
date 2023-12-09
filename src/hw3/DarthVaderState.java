package hw3;

import java.util.List;

import edu.du.dudraw.Draw;

//Abstract state class for DarthVader
public abstract class DarthVaderState {
	protected DarthVader darthVader;
	
	//The instance variable is a the darthVader object.
	public DarthVaderState(DarthVader darthVader) {
		this.darthVader = darthVader;
	}
	
	//Includes same methods, except for draw, and darthVader.
	abstract public void moveInitiator(PathFinder newPath, GridPoint Target);
	
	abstract public void move();
	
	
}
