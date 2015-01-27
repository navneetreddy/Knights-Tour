///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Find Knight's Path (P3)
// Files:            FindKnightsPath.java, SimpleQueue.java, QueueADT.java
//					         SimpleStack.java, StackADT.java, EmptyQueueException.java,
//					         EmptyStackException.java
// Semester:         CS367 Fall 2013
//
// Author:           Navneet Reddy
// CS Login:         navneet
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Jason Tiedt
// CS Login:         jtiedt
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          N/A
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;

/**
 * Finds a sequence of moves for the knight's tour problem 
 * with a given starting and ending locations.
 * 
 * @author NavneetReddy
 * @author Jason Tiedt
 */
public class FindKnightsPath {

	/**
	 * Main method to find a sequence of moves for the knight's path.
	 * 
	 * @param args starting and ending locations of the knight and 
	 * whether or not to print the numbered board
	 */
	public static void main(String[] args) {

		//Check if the third command is either '-n' or blank
		if (args.length > 2)
		{
			if (!args[2].equals("-n"))
			{
				System.out.println("The third command must be either '-n' or left blank.");
				System.exit(-1);
			}
		}
		else if (args.length < 2)
		{
			System.out.println("Need a starting location and an ending location.");
			System.exit(-1);
		}

		//Stores locations from command line arguments
		String[] command1 = new String[2];
		String[] command2 = new String[2];

		//Check if the starting and ending locations are separated by commas
		if (!args[0].contains(",") || !args[1].contains(","))
		{
			System.out.println("Starting and ending locations need to be separated by commas.");
			System.exit(-1);
		}

		//Get locations from command line arguments
		command1 = args[0].split(",");
		command2 = args[1].split(",");

		//Stores starting location
		Integer[] start = new Integer[2];
		//Stores ending location
		Integer[] end = new Integer[2];

		//Get the starting and ending locations from the command line arguments
		try {
			start[0] = Integer.parseInt(command1[0]);
			start[1] = Integer.parseInt(command1[1]);
			end[0] = Integer.parseInt(command2[0]);
			end[1] = Integer.parseInt(command2[1]);
		} catch (NumberFormatException e) {
			System.out.println("The starting and ending locations need to be integers.");
			System.exit(-1);
		}

		//Check if the starting and ending locations are on the board
		if (locationBounds(start[0],start[1]) == false)
		{
			System.out.println("The starting location is off the board.");
			System.exit(-1);
		}
		else if (locationBounds(end[0],end[1]) == false)
		{
			System.out.println("The ending location is off the board.");
			System.exit(-1);
		}

		//Variable to manipulate end position
		Integer[] nextLoc = end;

		//Simple queue 
		SimpleQueue<Integer[]> queue = new SimpleQueue<Integer[]>();
		//Simple stack
		SimpleStack<Integer[]> stack = new SimpleStack<Integer[]>();

		//Creates a new grid
		Integer[][] grid = new Integer[8][8];

		//Sets all the values of the grid to 0
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				grid[i][j] = 0;

		//Sets the starting value of the grid
		grid[start[0]][start[1]] = 1;

		//Stores a location from the queue
		Integer[] location = new Integer[2];
		//Number given to each location after the starting location
		Integer locationNumber = 1;
		//Number of the current location
		Integer currNum = 0;
		//Temporary variable to store the location
		Integer[] tempLoc = new Integer[2];
		//Stores the location of the given currNum
		Integer[] currNumLoc = new Integer[2];
		//Stores the location at the top of the stack
		Integer[] peek = new Integer[2];
		//Stores all the positions with the currNum
		ArrayList<Integer[]> currentPosition = new ArrayList<Integer[]>();

		queue.enqueue(start);
		
		//Stores the size of the queue
		int size = 0;
		
		//Loop condition check
		boolean done = false;

		//Number the board with the possible moves for the knight
		while (!done) 
		{
			try {
				//Size of the queue
				size = queue.size();

				for (int k = 0; k < size; k++)
				{
					//Gets the next location from the queue
					location = queue.dequeue();

					//Checks if the next location in the queue is the ending location
					if (location[0] == end[0] && location[1] == end[1])
					{
						done = true;
						break;
					}
				}

				currNum = locationNumber;
				currentPosition = currentPosition(grid,currNum);

				//Checks if each neighboring position is on the board and
				//assigns a number to the neighboring positions that haven't been numbered
				//and enqueues the neighbor into the queue

				for (int i = 0; i < currentPosition.size(); i++)
				{
					currNumLoc = currentPosition.get(i);

					if (locationBounds(currNumLoc[0] - 1, currNumLoc[1] - 2) == true && 
							grid[currNumLoc[0] - 1][currNumLoc[1] - 2] == 0)
					{
						grid[currNumLoc[0] - 1][currNumLoc[1] - 2] = currNum + 1;

						tempLoc[0] = currNumLoc[0] - 1;
						tempLoc[1] = currNumLoc[1] - 2;

						queue.enqueue(tempLoc);

						tempLoc = new Integer[2];
					}

					if (locationBounds(currNumLoc[0] - 1, currNumLoc[1] + 2) == true && 
							grid[currNumLoc[0] - 1][currNumLoc[1] + 2] == 0)
					{
						grid[currNumLoc[0] - 1][currNumLoc[1] + 2] = currNum + 1;

						tempLoc[0] = currNumLoc[0] - 1;
						tempLoc[1] = currNumLoc[1] + 2;

						queue.enqueue(tempLoc);

						tempLoc = new Integer[2];
					}

					if (locationBounds(currNumLoc[0] + 1, currNumLoc[1] - 2) == true && 
							grid[currNumLoc[0] + 1][currNumLoc[1] - 2] == 0)
					{
						grid[currNumLoc[0] + 1][currNumLoc[1] - 2] = currNum + 1;

						tempLoc[0] = currNumLoc[0] + 1;
						tempLoc[1] = currNumLoc[1] - 2;

						queue.enqueue(tempLoc);

						tempLoc = new Integer[2];
					}

					if (locationBounds(currNumLoc[0] + 1, currNumLoc[1] + 2) == true && 
							grid[currNumLoc[0] + 1][currNumLoc[1] + 2] == 0)
					{
						grid[currNumLoc[0] + 1][currNumLoc[1] + 2] = currNum + 1;

						tempLoc[0] = currNumLoc[0] + 1;
						tempLoc[1] = currNumLoc[1] + 2;

						queue.enqueue(tempLoc);

						tempLoc = new Integer[2];
					}

					if (locationBounds(currNumLoc[0] - 2, currNumLoc[1] - 1) == true && 
							grid[currNumLoc[0] - 2][currNumLoc[1] - 1] == 0)
					{
						grid[currNumLoc[0] - 2][currNumLoc[1] - 1] = currNum + 1;

						tempLoc[0] = currNumLoc[0] - 2;
						tempLoc[1] = currNumLoc[1] - 1;

						queue.enqueue(tempLoc);

						tempLoc = new Integer[2];
					}

					if (locationBounds(currNumLoc[0] - 2, currNumLoc[1] + 1) == true && 
							grid[currNumLoc[0] - 2][currNumLoc[1] + 1] == 0)
					{
						grid[currNumLoc[0] - 2][currNumLoc[1] + 1] = currNum + 1;

						tempLoc[0] = currNumLoc[0] - 2;
						tempLoc[1] = currNumLoc[1] + 1;

						queue.enqueue(tempLoc);

						tempLoc = new Integer[2];
					}

					if (locationBounds(currNumLoc[0] + 2, currNumLoc[1] - 1) == true && 
							grid[currNumLoc[0] + 2][currNumLoc[1] - 1] == 0)
					{
						grid[currNumLoc[0] + 2][currNumLoc[1] - 1] = currNum + 1;

						tempLoc[0] = currNumLoc[0] + 2;
						tempLoc[1] = currNumLoc[1] - 1;

						queue.enqueue(tempLoc);

						tempLoc = new Integer[2];
					}

					if (locationBounds(currNumLoc[0] + 2, currNumLoc[1] + 1) == true && 
							grid[currNumLoc[0] + 2][currNumLoc[1] + 1] == 0)
					{
						grid[currNumLoc[0] + 2][currNumLoc[1] + 1] = currNum + 1;

						tempLoc[0] = currNumLoc[0] + 2;
						tempLoc[1] = currNumLoc[1] + 1;

						queue.enqueue(tempLoc);

						tempLoc = new Integer[2];
					}
				}

				locationNumber++;

			} catch (EmptyQueueException e) { System.out.println("EmptyQueueException"); }
		}

		currNum = grid[end[0]][end[1]];

		stack.push(end);
		done = false;

		//Find the knight's path
		while (!done)
		{
			try {
				peek = stack.peek();

				//Checks if the last position to be added to the stack was the starting position
				if (peek[0] == start[0] && peek[1] == start[1])
				{
					done = true;
				}
				else
				{
					//Checks if each neighboring position is on the board and has the number one less
					//than the number at the current position and puts those positions into the stack 
					//in the order specified for searching neighbors

					nextLoc = peek;

					if (locationBounds(nextLoc[0] - 1, nextLoc[1] - 2) == true && 
							grid[nextLoc[0] - 1][nextLoc[1] - 2] == currNum - 1)
					{
						tempLoc[0] = nextLoc[0] - 1;
						tempLoc[1] = nextLoc[1] - 2;

						stack.push(tempLoc);
					}
					else if (locationBounds(nextLoc[0] - 1, nextLoc[1] + 2) == true && 
							grid[nextLoc[0] - 1][nextLoc[1] + 2] == currNum - 1)
					{
						tempLoc[0] = nextLoc[0] - 1;
						tempLoc[1] = nextLoc[1] + 2;

						stack.push(tempLoc);
					}
					else if (locationBounds(nextLoc[0] + 1, nextLoc[1] - 2) == true && 
							grid[nextLoc[0] + 1][nextLoc[1] - 2] == currNum - 1)
					{
						tempLoc[0] = nextLoc[0] + 1;
						tempLoc[1] = nextLoc[1] - 2;

						stack.push(tempLoc);
					}
					else if (locationBounds(nextLoc[0] + 1, nextLoc[1] + 2) == true && 
							grid[nextLoc[0] + 1][nextLoc[1] + 2] == currNum - 1)
					{
						tempLoc[0] = nextLoc[0] + 1;
						tempLoc[1] = nextLoc[1] + 2;

						stack.push(tempLoc);
					}
					else if (locationBounds(nextLoc[0] - 2, nextLoc[1] - 1) == true && 
							grid[nextLoc[0] - 2][nextLoc[1] - 1] == currNum - 1)
					{
						tempLoc[0] = nextLoc[0] - 2;
						tempLoc[1] = nextLoc[1] - 1;

						stack.push(tempLoc);
					}
					else if (locationBounds(nextLoc[0] - 2, nextLoc[1] + 1) == true && 
							grid[nextLoc[0] - 2][nextLoc[1] + 1] == currNum - 1)
					{
						tempLoc[0] = nextLoc[0] - 2;
						tempLoc[1] = nextLoc[1] + 1;

						stack.push(tempLoc);
					}
					else if (locationBounds(nextLoc[0] + 2, nextLoc[1] - 1) == true && 
							grid[nextLoc[0] + 2][nextLoc[1] - 1] == currNum - 1)
					{
						tempLoc[0] = nextLoc[0] + 2;
						tempLoc[1] = nextLoc[1] - 1;

						stack.push(tempLoc);
					}
					else if (locationBounds(nextLoc[0] + 2, nextLoc[1] + 1) == true && 
							grid[nextLoc[0] + 2][nextLoc[1] + 1] == currNum - 1)
					{
						tempLoc[0] = nextLoc[0] + 2;
						tempLoc[1] = nextLoc[1] + 1;

						stack.push(tempLoc);
					}

					tempLoc = new Integer[2];
					currNum--;
				}
			} catch (EmptyStackException e) { System.out.println("EmptyStackException"); }
		}

		//Print the board(s)
		if (args.length > 2)
		{
			if (args[2].equals("-n"));
			{
				printGrid(start, end, grid);
				System.out.println();
			}
		}

		printGridPath(stack, start, end, grid);

	}

	/**
	 * Check if the given location is within the parameters of the grid.
	 * 
	 * @param col column of the given location
	 * @param row row of the given location
	 * @return true if and only if the position is within the parameters
	 * of the grid
	 */
	private static boolean locationBounds(int col, int row) {

		if (col >= 0 && row >= 0 &&
				col < 8 && row < 8)
			return true;
		return false;
	}

	/**
	 * Displays the numbered board.
	 * 
	 * @param start starting location of the knight
	 * @param end ending location of the knight
	 * @param grid the numbers on the board
	 */
	private static void printGrid(Integer[] start, Integer[] end, Integer[][] grid) {

		System.out.println("The numbered board for " + start[0] + "," + start[1]
				+ " to " + end[0] + "," + end [1] + " is:");

		System.out.println("    0   1   2   3   4   5   6   7");
		System.out.println("   -------------------------------");

		//Run through all the positions of the grid and display its number
		for (int i = 0 ; i < grid.length; i++)
		{
			System.out.print(i + " | ");

			for (int j = 0; j < grid[i].length; j++)
			{
				if (grid[i][j] == 0)
					System.out.print("  | ");
				else
					System.out.print(grid[i][j] + " | ");
			}

			System.out.println();
			System.out.println("   -------------------------------");
		}
	}

	/**
	 * Displays a path for the knight.
	 * 
	 * @param stack positions for the path to display
	 * @param start starting position
	 * @param end ending position
	 * @param grid the numbers on the board
	 */
	private static void printGridPath(SimpleStack<Integer[]> stack, Integer[] start, 
			Integer[] end, Integer[][] grid) {

		//Stores all the locations from the stack
		ArrayList<Integer[]> stackLocation = new ArrayList<Integer[]>();
		//Stores a location from the stack
		Integer[] locationx = new Integer[2];
		//Determines if the stack contains the current location
		boolean s = false;

		System.out.println("A path from " + start[0] + "," + start[1]
				+ " to " + end[0] + "," + end [1] + " is:");

		System.out.println("    0   1   2   3   4   5   6   7");
		System.out.println("   -------------------------------");

		try {
			while (!stack.isEmpty())
				stackLocation.add(stack.pop());

			//Run through all the positions of the grid and 
			//find and display the number of the path
			for (int i = 0 ; i < grid.length; i++)
			{
				System.out.print(i + " | ");


				for (int j = 0; j < grid[i].length; j++)
				{
					for (int k = 0; k < stackLocation.size(); k++)
					{
						locationx = stackLocation.get(k);

						if (locationx[0] == i && locationx[1] == j)
						{
							System.out.print(grid[i][j]);
							s = true;
						}
					}
					if (s == true)
					{
						System.out.print(" | ");
						s = false;
					}
					else
						System.out.print("  | ");
				}

				System.out.println();
				System.out.println("   -------------------------------");

			}
		} catch (EmptyStackException e) {
			System.out.println("EmptyStackException");
		}
	}

	/**
	 * Get the locations of the current number from the grid.
	 * 
	 * @param grid grid to search through for the current number
	 * @param currNum current number to search for
	 * @return locations at which the current number is found on the grid
	 */
	private static ArrayList<Integer[]> currentPosition(Integer[][] grid, Integer currNum) {

		//Stores the positions of all the places currNum is found on the grid
		ArrayList<Integer[]> storePos = new ArrayList<Integer[]>();
		//Stores the position at which the currNum is found
		Integer[] posFound = new Integer[2];

		//Run though all the positions on the grid
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[i].length; j++)
			{
				//Check if the number of the current position is currNum
				if (grid[i][j] == currNum)
				{
					//Add the position to the ArrayList to be returned
					posFound[0] = i;
					posFound[1] = j;
					storePos.add(posFound);

					posFound = new Integer[2];
				}
			}
		}
		
		return storePos;
	}
}
