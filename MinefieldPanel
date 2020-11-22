package teamProject;

import java.awt.Color;
import java.awt.Dimension;
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
public class MineFieldPanel extends JPanel {
	//fields
	private Minefield game;
	private List<JButton> buttons;
	private MinesweeperWindow parentWindow;

	/**
	 * Creates the panel so that it is a row x column grid. For a 9 x 9 game :
	 * MinefieldPanel(9,9);
	 * 
	 * Takes a Minefield game and dynamically creates the cells of the Minefiled
	 * and represents them as buttons on the panel.
	 */
	public MineFieldPanel(MinesweeperWindow parentWindow, int row, int column) {
		game = new Minefield(row, column);
		buttons = new ArrayList<>();
		this.parentWindow = parentWindow;

		setLayout(new GridLayout(row, column, 0, 0));
		//panel border not working??
		setBorder(new LineBorder(new Color(169,169,169), 2));

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
	 *                     Minefiled cell based on their placement in the the game board
	 *                     list and the button list as they will match up.
	 *                     
	 * @return Returns a button that will interacte with a specific minefield cell in the game
	 */
	public JButton createButton(int buttonNumber)
	{
		JButton button = new JButton(" ");
		button.setPreferredSize(new Dimension(25, 25));
		button.setMinimumSize(new Dimension(25, 25));
		button.setMaximumSize(new Dimension(25, 25));
		button.setOpaque(true);
		button.setBackground(new Color(220,220,220));
		button.setBorder(new LineBorder(new Color(169,169,169), 2));
		
		button.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					rightClick(buttonNumber);
				}
				else if (SwingUtilities.isLeftMouseButton(e)) {
					leftClick(buttonNumber);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				return;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				return;
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				return;
			}

			@Override
			public void mouseExited(MouseEvent e) {
				return;
			}

		});

		// button. add listener (maybe mouse listener) for right click button when it is called
		// call rightClick(buttonNumber);
		
		//might have to change actionListener for leftClick to mouseListener as well

		return button;
	}

	/**
	 * Flags or unflags a minefield cell.   
	 * @param correspondingCell Specifies which element (cell) in the minefield board needs to be flagged
	 */
	public void rightClick(int correspondingCell)
	{
		game.flagCell(correspondingCell);
		drawBoard();
		parentWindow.updateMineTracker();
	}

	/**
	 * Steps on a minefield cell
	 * @param correspondingCell Specifies which element (cell) in the minefield board needs to be stepped on
	 * 
	 */
	public void leftClick(int correspondingCell)
	{
		game.stepOnCell(correspondingCell);
		drawBoard();
		parentWindow.updateMineTracker();
	}

	/**
	 * Draws the minefield.  Cells that are visible (have been stepped on) or flagged appear different than other cells
	 */
	public void drawBoard()
	{
		for (int cell = 0; cell < buttons.size(); cell++)
		{
			Cell currentCell = game.getBoard().get(cell);
			JButton currentButton = buttons.get(cell);

			if(currentCell.isFlagged())
			{
				currentButton.setIcon(new ImageIcon(MineFieldPanel.class.getResource(
						"/images/flagIcon.png"))); 
				currentButton.setHorizontalAlignment(SwingConstants.LEFT);
				
				if (game.getIfGameOver() && currentCell.getValue() != 9) {
					currentButton.setText("X");
					currentButton.setIcon(null);
				}
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
					// replace this with the button style you have for a number cell that has been clicked
					currentButton.setText(currentCell.getValue() + ""); 
					
				}
			}
		}

	}
	
	public Minefield getGameBoard() {
		return this.game;
	}

}

