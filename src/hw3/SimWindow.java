package hw3;

import edu.du.dudraw.Draw;
import edu.du.dudraw.DrawListener;
import java.util.ArrayList;

public class SimWindow implements DrawListener{

	// Some static constants that everyone can use
	//   the represent the window size
	public final static int windowWidth = 1050;
	public final static int windowHeight = 700;

	private TerrainMap tm;
	private Draw duDwin;
	private ArrayList<Avatar> avatarList;
	private ArrayList<DarthVader> darthVaderList;

	public SimWindow(TerrainMap tm) {
		avatarList = new ArrayList<Avatar>();
		darthVaderList = new ArrayList<DarthVader>();
		// Setup the DuDraw window
		duDwin = new Draw("COMP2381 Animal Simulation"); // The OO version of DUDraw
		duDwin.setCanvasSize(SimWindow.windowWidth, SimWindow.windowHeight);
		duDwin.enableDoubleBuffering(); // Too slow otherwise -- need to use .show() later

		// Scale is set to the width and height of the grid. 
		duDwin.setXscale(0, TerrainMap.gridWidth);
		duDwin.setYscale(0, TerrainMap.gridHeight);
		
		//Adds simwindow as a listener to the window. Will handle key and mouse inputs. 
		duDwin.addListener(this);
		
		
		this.tm = tm;
		
		addAvatar("XWing", tm.getGridPoint(new GridPoint(9,15)));
		addAvatar("Wookie",tm.getGridPoint(new GridPoint(10,9)));
		addAvatar("Bantha",tm.getGridPoint(new GridPoint(10,1)));
		addAvatar("DarthVader",tm.getGridPoint(new GridPoint(2,1)));
	}

	public void update() {
		// Clears the entire window to white and draw the TerrainMap
		duDwin.clear();
		tm.draw(duDwin);
		
		//Avatars are moved and drawn.
		moveAvatars();
		drawAvatars();
		
		duDwin.show();  // used in double buffering
	}

	public void runSimulation() {
		// This is the main game loop
		update(); // Initial positing

		while(true) {

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			update();
		}	
	}
	
	public void addAvatar(String avaname, GridPoint loc) {
		try {
			//Avatar factory creates a new avatar and adds it to the list of avatars. 
			Avatar newAvatar = AvatarFactory.newAvatar(avaname, tm, loc);
			avatarList.add(newAvatar);
			
			//If the new avatar is a DarthVader, add the avatar to darthVaderList.
			if(avaname.equals("DarthVader")) {
				darthVaderList.add((DarthVader)newAvatar);
			}
		}catch(BadAvatarException e) {
			System.out.println(e);
		}
	}
	
	public void drawAvatars() {
		//Each avatar in the avatar list is drawn.
		for (Avatar avatar : avatarList) {
			avatar.draw(duDwin);
		}
	}
	
	public void moveAvatars() {
		//Each avatar in the avatar list is moved. 
		for (Avatar avatar : avatarList) {
			avatar.move();
		}
	}

	@Override
	public void keyPressed(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(char arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(double x, double y) {
		//Each DarthVader in darthVaderList has movement initiated toward the pressed coordinates. 
		for (DarthVader darthVader:darthVaderList) {
			darthVader.moveInitatior(new PathFinder(tm), new GridPoint((int)x,(int)y));
		}	
	}

	@Override
	public void mouseReleased(double arg0, double arg1) {
		// TODO Auto-generated method stub
		
	}
}