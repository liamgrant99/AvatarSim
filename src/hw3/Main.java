package hw3;

public class Main {

	public static void main(String[] args) {
		// Load in the map		
		try {
			TerrainMap tm = new TerrainMap("map1.txt");
			// Make the display panel
			SimWindow window = new SimWindow(tm);
			
			// Start the simulation
			window.runSimulation();
		}catch(MalformedTerrainFileException MTFE) {
			System.out.println(MTFE);
		}catch(InvalidTerrainTypeException ITTE) {
			System.out.println(ITTE);
		}
		
	}

}
