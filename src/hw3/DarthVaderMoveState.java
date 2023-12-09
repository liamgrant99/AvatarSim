package hw3;

public class DarthVaderMoveState extends DarthVaderState{
	public DarthVaderMoveState(DarthVader darthVader) {
		super(darthVader);
	}

	@Override
	public void moveInitiator(PathFinder newPath, GridPoint Target) {
		//When the DarthVader is moving, it does not need to initiate movement.
	}

	@Override
	public void move() {
		//The next location in the A star list is removed.
		GridPoint nextLocation = darthVader.path.remove(0);
		
		//The DarthVader object is moved to the next location.
		darthVader.moveToTarget(nextLocation);
		
		//If there are no more tiles left in list, the state is changed to stationary.
		if (darthVader.path.size()==0) {
			darthVader.changeState(new DarthVaderStationaryState(darthVader));
		}	
	}
}
