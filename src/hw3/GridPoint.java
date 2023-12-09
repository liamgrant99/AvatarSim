package hw3;

import java.util.ArrayList;
import java.util.List;

public class GridPoint implements Comparable<GridPoint>{
	private int x_val;
	private int y_val;
	
	public GridPoint(int x_val, int y_val){
		this.x_val = x_val;
		this.y_val = y_val;
	}
	
	public int compareTo(GridPoint that)
	{
		//The distances from the origin for each gridpoint is taken.
		int distance1 = (int) Math.sqrt(Math.pow(this.x_val,2)+Math.pow(this.y_val,2));
		int distance2 = (int) Math.sqrt(Math.pow(that.x_val,2)+Math.pow(that.y_val,2));
		
		if (distance1 != distance2) {
			return distance1-distance2;
		}else if(this.x_val!=that.x_val) {
			return this.x_val-that.x_val;
		}else if(this.y_val!=that.y_val) {
			return this.y_val-that.y_val;
		}else {
			return 0;
		}
		
		//If the distances between the origin and gridpoints are equivalent, and the x vals and y vals are equal, 0 is returned. 
//		return (distance1-distance2)+(this.x_val-that.x_val)+(this.y_val-that.y_val);
	}
	
	public String toString() {
		//Returns string form of instance variables. 
		return "x value: " + x_val + " y value: " + y_val;
	}
	
	public int hashcode() {
		//The x_val and y_val are multiplied by different numbers, and the distance from the point to the origin are added together to get the hash code. 
		return (int) ((int) x_val*TerrainMap.gridWidth+y_val);
	}
	
	public boolean equals(Object o) {
		if (o instanceof GridPoint) {
			GridPoint that = (GridPoint) o;
		 	
			//If the x values and y values are equal, equals to returns true. IF not, false is returned. 
			return (this.x_val==that.x_val)&&(this.y_val == that.y_val);
		}
		return false;
		
	}
	
	public int getX() {
		return this.x_val;
	}
	
	public int getY() {
		return this.y_val;
	}
	
	public List<GridPoint> getNeighbors(int levels){
		//Function returns a list of the neighboring gridpoints, 
		ArrayList<GridPoint> listOfPoints = new ArrayList<GridPoint>();
		
		//The first level has a width of three and two middle tiles. 
		int width = 3;
		int middleSection = 2;
		
		//The x and y values of the current tile are set to the location of the gridpoint. 
		int x = this.x_val;
		int y = this.y_val;
		
		
		GridPoint newGridPoint = null;
		
		for (int i = 0; i<levels;i++) {
	
			//The middle gridpoints of the neighboring square are added to the listOfPoints. 
			for (int j = 0;j<middleSection/2;j++) {
				//Middle tiles on the left are added, as long as they are in range of the coordinate plane.  
				newGridPoint = new GridPoint(x+1+i,y+j);		
				if ((!(x+1+i>=TerrainMap.gridWidth||x+1+i<0))&&(!(y+j>=TerrainMap.gridHeight||y+j<0))) {
					listOfPoints.add(newGridPoint);
				}
				
				//If there are more than one middle gridpoints, the middle gridpoints below the previously added gridpoint are added to the list.  
				if(j!=0) {
					newGridPoint = new GridPoint(x+1+i,y-j);
					if ((!(x+1+i>=TerrainMap.gridWidth||x+1+i<0))&&(!(y-j>=TerrainMap.gridHeight||y-j<0))) {
						listOfPoints.add(newGridPoint);
					}
				}
				
				//Middle tiles on the right are added, as long as they are in range of the coordinate plane.  
				newGridPoint = new GridPoint(x-1-i,y-j);		
				if ((!(x-1-i>=TerrainMap.gridWidth||x-1-i<0))&&(!(y-j>=TerrainMap.gridHeight||y-j<0))) {
					listOfPoints.add(newGridPoint);
				}
				
				//If there are more than one middle gridpoints, the middle gridpoints below the previously added gridpoint are added to the list.  
				if(j!=0) {
					newGridPoint = new GridPoint(x-1-i,y+j);		
					if ((!(x-1-i>=TerrainMap.gridWidth||x-1-i<0))&&(!(y+j>=TerrainMap.gridHeight||y+j<0))) {
						listOfPoints.add(newGridPoint);
					}
				}
			}
			
			for (int j = 0;j<width;j++) {
				//The gridpoints on the top level of the square are added. 
				newGridPoint = new GridPoint((x-(width-1)/2)+j,y+1+i);
				if ((!((x-(width-1)/2)+j>=TerrainMap.gridWidth||(x-(width-1)/2)+j<0))&&(!(y+1+i>=TerrainMap.gridHeight||y+1+i<0))) {
					listOfPoints.add(newGridPoint);
				}
				
				//The gridpoints on the bottom level of the square are added. 
				newGridPoint = new GridPoint((x-(width-1)/2)+j,y-1-i);
				if ((!((x-(width-1)/2)+j>=TerrainMap.gridWidth||(x-(width-1)/2)+j<0))&&(!(y-1-i>=TerrainMap.gridHeight||y-1-i<0))) {
					listOfPoints.add(newGridPoint);
				}
				
			}
			//2 is added to width of the square
			width += 2;
			
			//Four is added to the middle section. 
			middleSection += 4;
		}
		//The list of neighboring gridpoints is returned. 
		return listOfPoints;
	}
}

