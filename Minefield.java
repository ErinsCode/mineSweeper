package teamProject;

import java.util.*;

/**
 * Makes a 9x9 Minesweeper game.
 * 
 * @author Erin Mortensen
 *
 */
public class Minefield
{
	private List<Cell> board;
	private boolean wonGame;
	private boolean lostGame;
	private boolean gameOver;
	private int cellsLeftToClick; //number of non-mine cells that must be clicked to win the game
	private int columns;
	private int rows;
	private int numOfMines;
	private int numberOfFlagsLaid;

	/**
	 * Starts a new minefield game with a board of dimension rows x columns. and initializes the fields.
	 * Rows and columns must be at least 9, and must be less than 100.  If they are not they are set to a default of 9.
	 * @param rows Number of cell rows for the minefield board will have.
	 * @param columns Number of cell columns for the nimefield board.  Cannot exceed 100
	 */
	public Minefield(int rows, int columns)
	{
		board = new ArrayList<>();
		wonGame = false;
		lostGame = false;
		gameOver = false;
		numberOfFlagsLaid = 0;
		
		//limit the size of the minefield board
		if(rows < 9 || rows > 100)
		{
			this.rows = 9;
		}
		if(columns < 9 || columns  > 100)
		{
			this.columns = 9;
		}
		else
		{
			this.rows = rows;
			this.columns = columns;
		}
		
		//initialize the minefield board
		for (int r = 0; r < this.rows; r++)
		{
			for (int c = 0; c < this.columns; c++)
			{
				board.add(new Cell(0, r, c));
			}
		}
		
		setNumberOfMines();
		cellsLeftToClick = (rows * columns) - numOfMines;
		
		layMines();
		countNeighboringMines();

	}

	/**
	 * Sets the number of mines depending on the size of the Minefield board.
	 * The number of mines range from 10 mines to 200.
	 */
	private void setNumberOfMines()
	{
		int numberOfCells = columns * rows;
		
		numOfMines = 10;
		
		/*
		 * In future, not necessarily for this assignment
		 * we can make the number of mines variable based on the size of the
		 * board
		 * if(numberOfCells < 101)
		{
			this.numOfMines = 10;
		}
		else if(numberOfCells < 256)
		{
			this.numOfMines = 40;
		}
		else if(numberOfCells < 480)
		{
			this.numOfMines = 99;
		}
		else
		{
			this.numOfMines = 200;
		}
		
		*/
		
	}
	
	/**
	 * Determines the value of the current cell by seeing how many mines it is
	 * touching.
	 */
	private void countNeighboringMines()
	{
		// if not mine
		for (int x = 0; x < board.size(); x++)
		{
			if(board.get(x).getValue() != 9)
			{
				// get Neighbors
				List<Cell> n = getNeighbors(x);

				// if Neighbors are mines add to value
				for (Cell c : n)
				{
					if(c.getValue() == 9)
					{
						board.get(x).setValue(1);
					}
				}
			}

		}

	}

	/**
	 * Returns a list of Cells that touches the current one.
	 * 
	 * @param position The current Cell's position on the board that we need the neighbors of
	 * @return Returns a list of this Cell's neighbors
	 */
	public List<Cell> getNeighbors(int position)
	{
		List<Cell> neighbors = new ArrayList<>();
		int x = position % columns;
		int y = position / columns;


		// get above cells
		if(y > 0)
		{
			if(x > 0)
			{
				// add upper left
				neighbors.add(board.get(position - (columns + 1)));
			}
			// get right above this cell
			neighbors.add(board.get(position - columns));

			// get upper right
			if(x < columns - 1)
			{
				neighbors.add(board.get(position - (columns - 1)));
			}
		}
		// get ones next to cell
		// get left
		if(x > 0)
		{
			neighbors.add(board.get(position - 1));
		}

		// get right
		if(x < columns - 1)
		{
			neighbors.add(board.get(position + 1));
		}

		// get below
		if(y < rows - 1)
		{
			// get lower right
			if(x > 0)
			{
				neighbors.add(board.get(position + (columns - 1)));
			}
			// directly below
			neighbors.add(board.get(position + columns));

			// lower left
			if(x < columns - 1)
			{
				neighbors.add(board.get(position + (columns + 1)));
			}
		}

		return neighbors;
	}

	/**
	 * Set the mines on the board.
	 */
	private void layMines()
	{ 
		board.get(0).setValue(9);
		board.get(12).setValue(9);
		board.get(30).setValue(9);
		board.get(26).setValue(9);
		board.get(60).setValue(9);
		board.get(42).setValue(9);
		board.get(80).setValue(9);
		board.get(64).setValue(9);
		board.get(79).setValue(9);
		board.get(6).setValue(9);
		
		//to do, make it so it randomly sets mines.  use method
		//setNumberOfMines() to determine how many mines to use

	}

	/**
	 * Returns the minefield board full of Cells.
	 * 
	 * @return Returns a list of Cells contained in the minefield
	 */
	public List<Cell> getBoard()
	{
		return board;
	}

	/**
	 * Returns whether or not the game has been won.
	 * 
	 * @return Returns true if the game has been won.
	 */
	public boolean getIfWonGame()
	{
		return wonGame;

	}

	/**
	 * Returns whether or not the game has been lost.
	 * 
	 * @return Returns true if the game has been lost.
	 */
	public boolean getIfLost()
	{
		return lostGame;
	}

	/**
	 * Updates how many cells still need to be shown that are not mines. When
	 * this reaches zero and a mine has not been clicked then the game is won.
	 * 
	 * @param cellsShown Number of non-mine cells that have been clicked
	 */
	public void updateCellsLeftToClick()
	{
		cellsLeftToClick--;

		if(cellsLeftToClick == 0)
		{
			wonGame = true;
			gameOver = true;
		}
	}

	/**
	 * Sets it so the game is over and lost game is true. Sets all mines to
	 * visible, unless they have been flagged
	 */
	private void setLostGame()
	{
		lostGame = true;
		gameOver = true;

		for (Cell c : getBoard())
		{
			if(c.getValue() == 9 && c.isFlagged == false)
			{
				c.setVisable(true);
			}
		}

	}

	/**
	 * Returns if the game is over
	 * @return Returns a boolean value for if the game is over
	 */
	public boolean getIfGameOver()
	{
		return gameOver;
	}
	
	/**
	 * Returns the number of mines on the minefield.  This number does not change
	 * as mines still exist even when they are flagged.
	 * @return Returns the number of mines. Set at the beginning of the game.
	 */
	public int getNumberOfMines()
	{
		return numOfMines;
	}
	
	
	/**
	 * Returns the number of cells who are flagged
	 * @return Returns number of flags laid on the minefield
	 */
	public int getNumberOfFlagsLaid()
	{
		return numberOfFlagsLaid;
	}

	/**
	 * Sets a cell so that it is visible (i.e. stepped on). Cells can only be made visible, not
	 * invisible again. Cells that have already been clicked on or are flagged
	 * cannot be stepped on. If a cell is zero, all of its non-mine neighbors
	 * are also stepped on.  If the game is over you are not allowed to step
	 * on another cell
	 * 
	 * @param cellClicked Cell to make visible.
	 */
	public void stepOnCell(int cellClicked)
	{
		Cell clickedCell = board.get(cellClicked);
		
		if(clickedCell.isFlagged() || clickedCell.isVisable() || getIfGameOver())
		{
			return;
		}

		clickedCell.setVisable(true);

		if(clickedCell.getValue() == 9)
		{
			setLostGame();
		}
		else
		{
			updateCellsLeftToClick();
		}

		if(clickedCell.getValue() == 0)
		{
			List<Cell> zerosNeighbors = getNeighbors(cellClicked);

			for (Cell c : zerosNeighbors)
			{
				if(c.getValue() != 9)
				{
					int positionOnBoard = (c.getxCoordinate() * columns) + c.getyCoordinate();
					stepOnCell(positionOnBoard);
				}

			}

		}
	}

	/**
	 * Flag or unflag a Cell.  You can only flag a Cell that is not visible.
	 */
	public void flagCell(int cellToBeFlagged)
	{
		Cell cellToFlag = board.get(cellToBeFlagged);
		
		if(cellToFlag.isVisable())
		{
			return;
		}
		if(cellToFlag.isFlagged())
		{
			cellToFlag.setFlagged(false);
			numberOfFlagsLaid--;
		}
		else
		{
			cellToFlag.setFlagged(true);
			numberOfFlagsLaid++;
		}

	}

	/**
	 * Used for testing the minefield class with the terminal.
	 * Prints the minefield board, but shows all the values
	 */
	private void printBoardShowValues()
	{
		int row = 0;
		for (int x = 0; x < rows; x++)
		{
			for (int y = 0; y < columns; y++)
			{
				System.out.print(getBoard().get(row).getValue() + " ");
				row++;
			}

			System.out.println();

		}
	}

	/**
	 * Used for testing the minefield class in the terminal.
	 * See minefield if not visible hides the value of the cell, shows flagged cells
	 */
	private void printMineField()
	{
		int row = 0;
		for (int x = 0; x < rows; x++)
		{
			for (int y = 0; y < columns; y++)
			{
				if(getBoard().get(row).isVisible == false)
				{
					if(getBoard().get(row).isFlagged == true)
					{
						System.out.print("F ");
					}
					else
					{
						System.out.print("- ");
					}

				}
				else
				{
					System.out.print(getBoard().get(row).getValue() + " ");
				}

				row++;
			}

			System.out.println();

		}
	}
/*
//Allows you to test the funcionality of the game in the terminal
	public static void main(String[] args)
	{
		
		Minefield game = new Minefield(11, 11);
		List<Cell> cells = game.getNeighbors(62);
		
		for(Cell c : cells)
		{
			System.out.println(c.getxCoordinate() * game.columns + c.getyCoordinate());
		}
		
		Scanner input = new Scanner(System.in);
		int selection;
		int toFlag = 0;

		game.printBoardShowValues();
		System.out.println();

		while (!game.gameOver)
		{
			game.printMineField();
			
			System.out.println("Would you like to flag a cell (enter 0) or stepOnCell (enter 1)");
			toFlag = input.nextInt();
			
			if(toFlag == 0)
			{
				System.out.println("Enter cell to flag");
				selection = input.nextInt();
				game.flagCell(selection);
				System.out.println();
				
			}
			
			else
			{
				System.out.println("Enter cell to select");
				selection = input.nextInt();

				game.stepOnCell(selection);
				System.out.println();
			}
			
			

		}

		if(game.getIfLost())
		{
			System.out.println("Sorry you lost");
			game.printMineField();
		}

		if(game.getIfWonGame())
		{
			System.out.println("Hurray! You won!");
			game.printMineField(); 
		}
		

	}*/
}