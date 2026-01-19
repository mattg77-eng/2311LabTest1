package console_apps;

import java.util.Scanner;

public class MileageApp2DArray {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);		 

		int[] fromChicago 	= {0	, 983	, 787	, 714	, 1375	, 967	, 1087	};
		int[] fromBoston 	= {983	, 0		, 214	, 1102	, 1763	, 1723	, 1842	};
		int[] fromNewYork 	= {787	, 214	, 0		, 888	, 1549	, 1548	, 1627	};
		int[] fromAtlanta 	= {714	, 1102	, 888	, 0		, 661	, 781	, 810	};
		int[] fromMiami 	= {1375	, 1763	, 1549	, 661	, 0		, 1426	, 1187	};
		int[] fromDallas 	= {967	, 1723	, 1548	, 781	, 1426	, 0		, 239	};
		int[] fromHouston 	= {1087	, 1842	, 1627	, 810	, 1187	, 239	, 0		};

		int[][] distance = {
				fromChicago, 
				fromBoston, 
				fromNewYork, 
				fromAtlanta, 
				fromMiami, 
				fromDallas, 
				fromHouston
		};

		System.out.println("How many cities in your itinerary?");
		int howMany = input.nextInt();
		input.nextLine(); // consume the newline character so that later reading a string would be ok.

		String[] trip = new String[howMany];

		/* Read cities from the user and store them to `trip`. */
		for(int i = 0; i < howMany; i ++) {
			System.out.println("Enter a city:");
			/* Assumption: user enters a valid city */
			String city = input.nextLine();
			
			trip[i] = city;
		}

		/* Add up departure-to-destination distances */
		int dist = 0;
		for(int i = 0; i < howMany - 1; i++) {
			String dep = trip[i]; // departure
			String dst = trip[i + 1]; // destination

			int depIndex = MileageAppUtilities.indexOf(dep); 
			int dstIndex = MileageAppUtilities.indexOf(dst);

			int currentDist = distance[depIndex][dstIndex];
			dist += currentDist;
			System.out.println("From " + dep + " to " + dst + ": " + currentDist);
		}
		System.out.println("Distance: " + dist);


		System.out.println("Bye!");

		input.close();
	}
}