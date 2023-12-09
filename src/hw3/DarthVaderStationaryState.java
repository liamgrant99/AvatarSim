package hw3;

public class DarthVaderStationaryState extends DarthVaderState{
	public DarthVaderStationaryState(DarthVader darthVader) {
		super(darthVader);
	}

	@Override
	public void moveInitiator(PathFinder newPath, GridPoint Target) {
		//A path is found to the new target. 
		darthVader.path = newPath.findPath(darthVader.location, Target);
		
		//The state is changed to moving state. 
		darthVader.changeState(new DarthVaderMoveState(darthVader));
		
	}

	@Override
	public void move() {
		//If the DarthVader is in a stationary state, it does not need to move. 
	}
}
