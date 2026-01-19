package console_apps;

import java.util.Scanner;

public class MileageApp1DArrays {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		 
		int[] fromChicago 	= {0	, 983	, 787	, 714	, 1375	, 967	, 1087	};
		int[] fromBoston 	= {983	, 0		, 214	, 1102	, 1763	, 1723	, 1842	};
		int[] fromNewYork 	= {787	, 214	, 0		, 888	, 1549	, 1548	, 1627	};
		int[] fromAtlanta 	= {714	, 1102	, 888	, 0		, 661	, 781	, 810	};
		int[] fromMiami 	= {1375	, 1763	, 1549	, 661	, 0		, 1426	, 1187	};
		int[] fromDallas 	= {967	, 1723	, 1548	, 781	, 1426	, 0		, 239	};
		int[] fromHouston 	= {1087	, 1842	, 1627	, 810	, 1187	, 239	, 0		};
		
		System.out.println("How many cities in your itinerary?");
		int howMany = input.nextInt();
		input.nextLine(); // consume the newline character so that later reading a string would be ok.
		
		String[] trip = new String[howMany];
		
		/* Read cities from the user and store them to `trip`. */
		for(int i = 0; i < howMany; i ++) {
			System.out.println("Enter a city:");
			String city = input.nextLine();
			trip[i] = city;
		}
		
		/* Add up departure-to-destination distances */
		int dist = 0;
		for(int i = 0; i < howMany - 1; i++) {
			String dep = trip[i]; // departure
			String dst = trip[i + 1]; // destination
			
			int depIndex = MileageAppUtilities.indexOf(dep);
			/* In this improved version,
			 * we still have to use a conditional on `depIndex` to figure out
			 * which separate array variable to index into. 
			 * It would be very effective if all severn array variables can be combined into one.
			 */
			int dstIndex = MileageAppUtilities.indexOf(dst);
			
			int currentDist = 0;
			
			if(depIndex == MileageAppUtilities.CHICAGO) {
				currentDist = fromChicago[dstIndex];
			}
			else if(depIndex == MileageAppUtilities.BOSTON) {
				currentDist = fromBoston[dstIndex];
			}
			else if(depIndex == MileageAppUtilities.NEW_YORK) {
				currentDist = fromNewYork[dstIndex];
			}
			else if(depIndex == MileageAppUtilities.ATLANTA) {
				currentDist = fromAtlanta[dstIndex];
			}
			else if(depIndex == MileageAppUtilities.MIAMI) {
				currentDist = fromMiami[dstIndex];
			}
			else if(depIndex == MileageAppUtilities.DALLAS) {
				currentDist = fromDallas[dstIndex];
			}
			else if(depIndex == MileageAppUtilities.HOUSTON) {
				currentDist = fromHouston[dstIndex];
			}
			
			dist += currentDist;
			System.out.println("From " + dep + " to " + dst + ": " + currentDist);
		}
		System.out.println("Distance: " + dist);
		System.out.println("Bye!");
		
		input.close();
	}

}