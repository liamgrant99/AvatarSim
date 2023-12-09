package hw3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import edu.du.dudraw.Draw;

// The TerrainMap represents a 2D grid of Tiles

public class TerrainMap implements Drawable {

	private Map<GridPoint, TerrainTile> theTiles;
	
	// public static constants set in the constructor.
	public static int gridWidth;
	public static int gridHeight;

	// Constructor to read from file
	public TerrainMap(String filename) throws MalformedTerrainFileException, InvalidTerrainTypeException {
		this.theTiles = new HashMap<GridPoint, TerrainTile>();
		
		//Boolean array is created to store whether all terrain tiles have been used. 
		boolean[] all_terrains_used = new boolean[] {false, false, false, false};
		
		
		try {	
			FileReader fr = new FileReader(filename);
			
			Scanner scan = new Scanner(fr);
			String start = scan.nextLine();
			
			//Array is created to store the contents of the line.
			String line_list[] = null;
			
			//Contents of the line are split up by whitespace and put into an array. 
			line_list = start.trim().split(" ");
				
			//If the first line does not contain the dimensions of the list, an exception is thrown. 	
			if (line_list.length!=2) {
				throw new MalformedTerrainFileException("File does not begin with integers representing length and width of array.");
			}
				//Add exception for first two not being ints
			
			//Static variables are set. 
			gridWidth = Integer.valueOf(line_list[0]);
			gridHeight = Integer.valueOf(line_list[1]);
				
			//Variables are created to store x and y coordinates of each point that is drawn. 
			int y_val = gridHeight-1;
			int x_val = 0;
			
			
			String current_terrain = null;
				
			while(scan.hasNext()) {
				//X val is reset to 0. 
				x_val = 0;
							
				start = scan.nextLine();
				
				//Contents of the line are split up by whitespace and put into an array. 
				line_list = start.trim().split(" ");
				
				for (int i = 0;i<gridWidth;i++) {
					//Gridpoint created with the current x, y coordinate.
					GridPoint newgridpoint = new GridPoint(x_val,y_val);
					
					//The letter at the current index is set to lower case.
					current_terrain = line_list[i].toLowerCase();
					
					
					
					//current_terrain is checked to see if it is equal to g,r,m, or w. If there is a match, the corresponding terrain tile is instantiated.
					//If it is the first instance of the type of terrain tile, the corresponding index in all_terrains_used is set to true. 
					TerrainTile newtile = TerrainTileFactory.newTerrainTile(current_terrain, newgridpoint,all_terrains_used);
					
					theTiles.put(newgridpoint, newtile);
					
					x_val +=1;	
					}
				
				y_val -= 1;
				
				}
				
				//If one of the terrains is not used, an exception is thrown. 
				for (boolean t_or_f : all_terrains_used) {
					if (!t_or_f) {
						throw new InvalidTerrainTypeException("Not all terrains are used.");
					}
				}
				
				//Scanner and file reader are both closed. 
				scan.close();
				
				
				try {
					fr.close();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
		
				//File not found exception is caught. 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}	
	}
	
	public void draw(Draw duDwin) {
		//A set is created to store the key of the map.
		
		Set<GridPoint> gpList = theTiles.keySet();
		
		//Each item in the map is drawn. 
		for (GridPoint gp : gpList) {
			theTiles.get(gp).draw(duDwin);
		}
	}
	
	public int getWetOfTile(GridPoint gp) {
		//Returns the wetness of the tile at the gridpoint.
		return theTiles.get(gp).getWet();
	}
	
	public int getBumpyOfTile(GridPoint gp) {
		//Returns the bumpiness of the tile at the gridpoint.
		return theTiles.get(gp).getBumpy();
	}
	
	public int getVegOfTile(GridPoint gp) {
		//Returns the vegetation level of the tile at the gridpoint. 
		return theTiles.get(gp).getVeg();
	}

	public void reduceVegetationOnTile(GridPoint location, int amountEaten) {
		//Corresponding terrain tile is fetched from the map. 
		TerrainTile theTile = theTiles.get(getGridPoint(location));
		
		//Tile is determined to be grass or mountain, and then it has the vegetation consumed on its tile. 
		if (theTile.getVeg()>0&&theTile.getBumpy()==0) {
			Grass grassTile = (Grass) theTile;
			grassTile.consumeVegetation(amountEaten);
		}else if(theTile.getVeg()>0&&theTile.getBumpy()>0) {
			Mountain mountainTile = (Mountain) theTile;
			mountainTile.consumeVegetation(amountEaten);
		}
	}
	
	public GridPoint getGridPoint(GridPoint gp1){
		Set<GridPoint> gpList = theTiles.keySet();
		
		//The gridpoint that corresponds to gp1 is returned. 
		for (GridPoint gp : gpList) {
			if (gp.equals(gp1)) {
				return gp;
			}
		}
		//If the gridpoint is not found, nothing is returned. 
		return null;
		
	}

}
