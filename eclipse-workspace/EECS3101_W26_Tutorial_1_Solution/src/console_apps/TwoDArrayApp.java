package console_apps;

public class TwoDArrayApp {

	public static void main(String[] args) { 
		int[][] list2 = {
			{1, 2, 3, 4},
			{5, 6, 7, 8},
			{9, 10, 11, 12}
		};
		
		System.out.println("list2:");
		TwoDArrayApp.printMatrix(list2);
		
		int[][] list2a = new int[3][4]; 
		
		System.out.println("list2a (default):");
		TwoDArrayApp.printMatrix(list2a);
		
		int[] row1 = {1, 2 , 3 , 4 };
		int[] row2 = {5, 6 , 7 , 8 };
		int[] row3 = {9, 10, 11, 12};
		
		list2a[0] = row1;
		list2a[1] = row2;
		list2a[2] = row3;
		
		System.out.println("list2a (after chagne 1):");
		TwoDArrayApp.printMatrix(list2a);
		
		list2a[1][2] = -1;
		
		System.out.println("list2a (after chagne 2):");
		TwoDArrayApp.printMatrix(list2a);
	}
	
	public static void printMatrix(int[][] m) {
		for(int r = 0; r < m.length; r ++) {
			System.out.print("Row " + r + ": ");
			System.out.print("[");
			for(int c = 0; c < m[r].length; c ++) {
				System.out.print(m[r][c]);
				if(c < m[r].length - 1) {
					System.out.print(", ");
				}
			}
			System.out.println("]");
		}
	}

}