package console_apps;

public class MileageAppUtilities {
	public static final int CHICAGO 	= 0;
	public static final int BOSTON 	= 1;
	public static final int NEW_YORK 	= 2;
	public static final int ATLANTA 	= 3;
	public static final int MIAMI 	= 4;
	public static final int DALLAS 	= 5;
	public static final int HOUSTON 	= 6;
	
	/* Helper method for returning the corresponding index of a city. */
	public static int indexOf(String city) {
		int index = -1;
		
		if(city.equals("Chicago")) {
			index = MileageAppUtilities.CHICAGO;
		}
		else if(city.equals("Boston")) {
			index = MileageAppUtilities.BOSTON;
		}
		else if(city.equals("New York")) {
			index = MileageAppUtilities.NEW_YORK;
		}
		else if(city.equals("Atlanta")) {
			index = MileageAppUtilities.ATLANTA;
		}
		else if(city.equals("Miami")) {
			index = MileageAppUtilities.MIAMI;
		}
		else if(city.equals("Dallas")) {
			index = MileageAppUtilities.DALLAS;
		}
		else if(city.equals("Houston")) {
			index = MileageAppUtilities.HOUSTON;
		}
		
		return index;
	}
}
