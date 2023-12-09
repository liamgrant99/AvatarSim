package hw3;

import java.util.Map;

public abstract class VegetationAvatar extends Avatar{
	//This class is for avatars that consume vegetation.
	public VegetationAvatar(TerrainMap terrainMap, GridPoint startingLocation) {
		super(terrainMap, startingLocation);
	}

	//Vegetation avatars can eat vegetation which calls the corresponding method on terrainMap.
	public void eatVegetation(int amountEaten) {
		terrainMap.reduceVegetationOnTile(location, amountEaten);
	}
}
