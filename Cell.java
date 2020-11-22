package teamProject;

/**
 * A minesweeper cell.
 * 
 * @author Erin Mortensen
 *
 */
public class Cell
{
	int value;
	boolean isFlagged;
	boolean isVisible;
	int xCoordinate;
	int yCoordinate;

	/**
	 * Initializes the value of the cell. If the value is 9 then the cell holds
	 * a mine. Values 0 - 8 stand for the number of mines that the cell touches.
	 * 
	 * @param value Value of the cell. 9 is a mine, and 0-8 represents the number of mines it touches.
	 */
	public Cell(int value, int x, int y)
	{
		this.value = value;
		this.xCoordinate = x;
		this.yCoordinate = y;
		this.isFlagged = false;
		this.isVisible = false;
	}

	/**
	 * 
	 * @param value Value of the cell. 9 is a mine, and 0-8 represents the number of mines it touches.
	 * @param isFlagged Flags the cell so that individual knows that a mine is underneath
	 * @param isVisable Says if the user can see the value of the cell or not, or if it is covered.
	 */
	public Cell(int value, int x, int y, boolean isFlagged, boolean isVisable)
	{
		this(value, x, y);
		this.isFlagged = isFlagged;
		this.isVisible = isVisable;
	}

	/**
	 * Gets the value of the mine.
	 * 
	 * @return the mines value
	 */
	public int getValue()
	{
		return value;
	}

	/**
	 * Updates the cells value.
	 * 
	 * @param value Sets the new value of the cell. 9 makes the cell a mine, 0-8
	 *              stands for the number of other mines that this cell is
	 *              touching.
	 */
	public void setValue(int value)
	{
		this.value += value;
	}

	/**
	 * Returns if the cell has been flagged
	 * 
	 * @return the if cell is flagged
	 */
	public boolean isFlagged()
	{
		return isFlagged;
	}

	/**
	 * Changes if the cell is flagged or not.
	 * 
	 * @param isFlagged sets if the cell is flagged.
	 */
	public void setFlagged(boolean isFlagged)
	{
		this.isFlagged = isFlagged;
	}

	/**
	 * Returns if the cell's value is visible.
	 * 
	 * @return the if cell is visible.
	 */
	public boolean isVisable()
	{
		return isVisible;
	}

	/**
	 * Sets the visibility of the cell's value.
	 * 
	 * @param isVisable If the visibility is true or false.
	 */
	public void setVisable(boolean isVisable)
	{
		this.isVisible = isVisable;
	}

	/**
	 * @return the xCoordinate
	 */
	public int getxCoordinate()
	{
		return xCoordinate;
	}

	/**
	 * @return the yCoordinate
	 */
	public int getyCoordinate()
	{
		return yCoordinate;
	}

}