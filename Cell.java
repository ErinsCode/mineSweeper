package minesweeperGame;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

/**
 * Takes a minefield board and draws it out in a grid of buttons. These buttons
 * can be "stepped on" (clicked) to show the value of the cell below it. Flags
 * can also be flagged to prevent stepping on them.
 * 
 * @author Erin Mortensen
 * @author Dana Kendall
 *
 */
public class MinefieldPanel extends JPanel
{

	private Minefield game;
	private List<JButton> buttons;
	private MinesweeperWindow parentComponent;

	/**
	 * Creates the panel so that it is a row x column grid. For a 9 x 9 game :
	 * MinefieldPanel(9,9);
	 * 
	 * Takes a Minefield game and dynamically creates the cells of the Minefiled
	 * and represents them as buttons on the panel.
	 */
	public MinefieldPanel(MinesweeperWindow p, int row, int column)
	{
		parentComponent = p;
		game = new Minefield(row, column);
		buttons = new ArrayList<>();

		setLayout(new GridLayout(row, column, 1, 1));

		for (int c = 0; c < game.getBoard().size(); c++)
		{
			buttons.add(createButton(c));
			add(buttons.get(c));

		}

	}

	/**
	 * Creates a button for each individual minefield cell. Each button is given
	 * action listeners for what to do if they are right clicked or left
	 * clicked.
	 * 
	 * @param buttonNumber The number of button this is in the buttons List.
	 *                     This allows us to map the button to its corresponding
	 *                     Minefiled cell based on their placement in the the
	 *                     game board list and the button list as they will
	 *                     match up.
	 * 
	 * @return Returns a button that will interacte with a specific minefield
	 *         cell in the game
	 */
	public JButton createButton(int buttonNumber)
	{
		JButton button = new JButton(" ");
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				leftClick(buttonNumber);
			}
		});

		// button. add listener (maybe mouse listener) for right click button
		// when it is called
		// call rightClick(buttonNumber);

		// might have to change actionListener for leftClick to mouseListener as
		// well

		return button;
	}

	/**
	 * Flags or unflags a minefield cell.
	 * 
	 * @param correspondingCell Specifies which element (cell) in the minefield
	 *                          board needs to be flagged
	 */
	public void rightClick(int correspondingCell)
	{
		game.flagCell(correspondingCell);
		drawBoard();
		tellParent();
	}

	/**
	 * Steps on a minefield cell
	 * 
	 * @param correspondingCell Specifies which element (cell) in the minefield
	 *                          board needs to be stepped on
	 * 
	 */
	public void leftClick(int correspondingCell)
	{
		game.stepOnCell(correspondingCell);
		drawBoard();
		tellParent();

	}

	/**
	 * Draws the minefield. Cells that are visible (have been stepped on) or
	 * flagged appear different than other cells
	 */
	public void drawBoard()
	{
		for (int cell = 0; cell < buttons.size(); cell++)
		{
			Cell currentCell = game.getBoard().get(cell);
			JButton currentButton = buttons.get(cell);

			if(currentCell.isFlagged())
			{
				currentButton.setText("F"); // replace this with the button
											// style you picked for a flagged
											// button
				// maybe make an extracted method for it
			}
			else
			{
				currentButton.setText(" "); // need to undo the flag if
											// previously flagged
				// replace this with the button style you picked for a regular
				// non-clicked button
			}
			if(currentCell.isVisable())
			{
				if(currentCell.getValue() == 9)
				{
					currentButton.setText("*"); // replace this with button
												// style for mine
				}
				else
				{
					// replace this with the button style you have for a number
					// cell that has been clicked
					currentButton.setText(currentCell.getValue() + "");

				}
			}
		}

	}

	/**
	 * Returns the Minefield game board.
	 * @return Returns Minefield game
	 */
	public Minefield getGameBoard()
	{
		return game;
	}

/**
 * Calls the updateGameStatus method of the MinesweeperWindow every time 
 * a cell is clicked.  Allows the MinesweeperWindow to use this information to 
 * update information in other components.
 */
	public void tellParent()
	{
		parentComponent.updateGameStatus();
	}
}
