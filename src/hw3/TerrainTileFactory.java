package hw3;

public class TerrainTileFactory {
	public static TerrainTile newTerrainTile(String tileStr, GridPoint loc, boolean[] all_terrains_used) throws InvalidTerrainTypeException{
		//Grass tile is instantiated if the letter is g.
		if (tileStr.equals("g")) {
			TerrainTile newtile = new Grass(loc);
			if(all_terrains_used[0]==false) {
				all_terrains_used[0] = true;
			}
			return newtile;
			
		//Road tile is instantiated if the letter is r.
		}else if(tileStr.equals("r")){
			TerrainTile newtile = new Road(loc);
			if(all_terrains_used[1]==false) {
				all_terrains_used[1] = true;
			}
			return newtile;
		
		//Grass tile is instantiated if the letter is m.
		}else if(tileStr.equals("m")) {
			TerrainTile newtile = new Mountain(loc);
			if(all_terrains_used[2]==false) {
				all_terrains_used[2] = true;
			}
			return newtile;
		
		//Grass tile is instantiated if the letter is w.
		}else if(tileStr.equals("w")) {
			TerrainTile newtile = new Water(loc);
			if(all_terrains_used[3]==false) {
				all_terrains_used[3] = true;
			}
			return newtile;
				
		}else {
			//If the letter does not match, a terrain type exception is thrown. 
			throw new InvalidTerrainTypeException("Item at location ["+loc+"] is invalid.");
		}
	}
}
