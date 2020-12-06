package teamProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

import javax.swing.JButton;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.GridLayout;

/**
 * Takes a minefield board and draws it out in a grid of buttons. These buttons
 * can be "stepped on" (clicked) to show the value of the cell below it. Flags
 * can also be flagged to prevent stepping on them.
 * 
 * @author Erin Mortensen
 * @author Dana Kendall
 *
 */
@SuppressWarnings("serial")
public class MineFieldPanel extends JPanel
{
	// fields
	private MineField game;
	private List<JButton> buttons;
	private MinesweeperWindow parentWindow;

	/**
	 * Creates the panel so that it is a row x column grid. For a 9 x 9 game :
	 * MinefieldPanel(9,9);
	 * 
	 * Takes a Minefield game and dynamically creates the cells of the Minefield
	 * and represents them as buttons on the panel.
	 * 
	 * @param parentWindow the MinesweeperWindow to place the MineFieldPanel into
	 * @param row          the number of rows in the panel
	 * @param column       the number of columns in the panel
	 * @throws IllegalArgumnetException rows and columns cannot be zero
	 */
	public MineFieldPanel(MinesweeperWindow parentWindow, int row, int column) throws IllegalArgumentException
	{
		if(row == 0 || column == 0)
		{
			throw new IllegalArgumentException();
		}

		this.game = new MineField(row, column);
		buttons = new ArrayList<>();
		this.parentWindow = parentWindow;

		setLayout(new GridLayout(row, column, 0, 0));
		// panel border not working??
		setBorder(new LineBorder(new Color(169, 169, 169), 2));

		for (int c = 0; c < game.getBoard().size(); c++)
		{
			buttons.add(createButton(c));

			add(buttons.get(c));

		}

	}

	/**
	 * Creates a panel for a pre-existing Minefield game and assigns buttons to
	 * it.
	 * 
	 * @param parentWindow the MinesweeperWindow to place the MineFieldPanel into
	 * @param row          the number of rows in the panel
	 * @param column       the number of columns in the panel
	 * @param game         an ongoing MineField game
	 * @throws IllegalArgumnetException rows and columns cannot be zero
	 */
	public MineFieldPanel(MinesweeperWindow parentWindow, int row, int column, MineField game) throws IllegalArgumentException
	{
		if(row == 0 || column == 0)
		{
			throw new IllegalArgumentException();
		}
		this.game = game;
		this.buttons = new ArrayList<>();
		this.parentWindow = parentWindow;

		setLayout(new GridLayout(row, column, 0, 0));
		// panel border not working??
		setBorder(new LineBorder(new Color(169, 169, 169), 2));

		for (int c = 0; c < this.game.getBoard().size(); c++)
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
	 *                     Minefield cell based on their placement in the the
	 *                     game board list and the button list as they will
	 *                     match up.
	 * 
	 * @return Returns a button that will interact with a specific minefield
	 *         cell in the game
	 */
	public JButton createButton(int buttonNumber)
	{
		JButton button = new JButton(" ");
		button.setPreferredSize(new Dimension(25, 25));
		button.setMinimumSize(new Dimension(25, 25));
		button.setMaximumSize(new Dimension(25, 25));
		button.setOpaque(true);
		button.setBackground(new Color(220, 220, 220));
		button.setBorder(new LineBorder(new Color(169, 169, 169), 2));

		button.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(SwingUtilities.isRightMouseButton(e))
				{
					rightClick(buttonNumber);
				}
				else if(SwingUtilities.isLeftMouseButton(e))
				{
					leftClick(buttonNumber);
				}
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				return;
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				return;
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				return;
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				return;
			}

		});

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
		parentWindow.updateMineTracker();
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
		parentWindow.updateMineTracker();
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
				currentButton.setIcon(new ImageIcon(MineFieldPanel.class
						.getResource("/images/flagIcon.png")));
				currentButton.setHorizontalAlignment(SwingConstants.LEFT);

				// Mark flagged non-bombs with X
				if(game.getIfGameOver() && currentCell.getValue() != 9)
				{
					currentButton.setText("X");
					currentButton.setFont(new Font("Arial", Font.BOLD, 15));
					currentButton.setHorizontalAlignment(SwingConstants.CENTER);
					currentButton.setVerticalAlignment(SwingConstants.CENTER);
					currentButton.setForeground(Color.RED);
					currentButton.setIcon(null);
				}
			}
			else
			{
				currentButton.setIcon(null);
				currentButton.setText(" ");
			}
			if(currentCell.isVisible())
			{
				if(currentCell.getValue() == 9)
				{
					currentButton.setIcon(new ImageIcon(MineFieldPanel.class
							.getResource("/images/mineIcon.png")));
					currentButton.setText(null);
					// Alignment not working here
					currentButton.setHorizontalAlignment(SwingConstants.CENTER);
				}
				else if(currentCell.getValue() == 10) {
					currentButton.setIcon(new ImageIcon(MineFieldPanel.class
							.getResource("/images/redMine.png")));
					currentButton.setText(null);
				}
				else
				{
					if(currentCell.getValue() != 0)
					{
						currentButton.setText(currentCell.getValue() + "");

					}

					currentButton.setHorizontalAlignment(SwingConstants.CENTER);
					currentButton.setBackground(new Color(185, 185, 185));
					currentButton.setForeground(Color.BLACK);

				}
			}
			if(!currentCell.isVisible() && !currentCell.isFlagged())
			{
				currentButton.setIcon(null);
				currentButton.setText("");
			}
		}

	}

	/**
	 * Returns the Minefield game board
	 * 
	 * @return Minefield game board
	 */
	public MineField getGameBoard()
	{
		return this.game;
	}

}



