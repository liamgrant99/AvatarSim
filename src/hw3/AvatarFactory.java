package hw3;

public class AvatarFactory {
	public static Avatar newAvatar(String avaName, TerrainMap tm, GridPoint loc) throws BadAvatarException {
		//DarthVader is instatiated if input is DarthVader
		if (avaName.equals("DarthVader")) {
			Avatar newDarthVader = new DarthVader(tm, loc);
			return newDarthVader;
		//Bantha is instantiated if input is Bantha
		}else if(avaName.equals("Bantha")){
			Avatar newBantha = new Bantha(tm, loc);
			return newBantha;
		//Wookie is instantiated if input is Wookie
		}else if(avaName.equals("Wookie")) {
			Avatar newWookie = new Wookie(tm, loc);
			return newWookie;
		//XWing is instantiated if input is XWing.
		}else if(avaName.equals("XWing")) {
			Avatar newXWing = new XWing(tm, loc);
			return newXWing;
		//If the input name does not match, an exception is thrown. 
		}else {
			throw new BadAvatarException("Avatar "+avaName+" is not a valid avatar.");
		}
	}
}
