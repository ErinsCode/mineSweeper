package teamProject;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

/**
 * Creates the menu bar panel containing three buttons: New Game, Save, and Load
 * 
 * @author Erin Mortensen
 * @author Dana Kendall
 *
 */
@SuppressWarnings("serial")
public class MenuBarPanel extends JPanel
{

	// fields
	private JButton newGameButton;
	private JButton saveButton;
	private JButton loadButton;
	private MinesweeperWindow parentWindow;

	/**
	 * Create the panel with 3 buttons.
	 */
	public MenuBarPanel(MinesweeperWindow parentWindow)
	{
		setOpaque(false);
		setBackground(Color.ORANGE);
		setBounds(0, 0, 200, 30);
		setPreferredSize(new Dimension(200, 30));
		setMinimumSize(new Dimension(200, 30));
		setMaximumSize(new Dimension(200, 30));
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		newGameButton = createNewGameButton();
		add(newGameButton);

		saveButton = createSaveButton();
		add(saveButton);

		loadButton = createLoadButton();
		add(loadButton);

		this.parentWindow = parentWindow;

	}

	/**
	 * Create the New Game button.
	 * 
	 * @return a button that will start a new game
	 */
	private JButton createNewGameButton()
	{
		JButton newGameButton = new JButton("New Game");
		newGameButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		newGameButton.setBorder(null);
		newGameButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				// TO DO: Start new game
			}
		});
		newGameButton.setHorizontalTextPosition(SwingConstants.LEFT);
		newGameButton.setHorizontalAlignment(SwingConstants.LEFT);
		return newGameButton;
	}

	/**
	 * Create the Save button.
	 * 
	 * @return a button that will save a game
	 */
	private JButton createSaveButton()
	{
		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		saveButton.setBorder(null);
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// TO DO: Save game
			}
		});
		return saveButton;
	}

	/**
	 * Create the Load button.
	 * 
	 * @return a button that will load a saved game
	 */
	private JButton createLoadButton()
	{
		JButton loadButton = new JButton("Load");
		loadButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		loadButton.setBorder(null);
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				loadGame();
				// TO DO: Load game
			}
		});
		return loadButton;
	}

	/**
	 * Starts a new game.
	 */
	public void startNewGame()
	{

	}

	/**
	 * Saves a game to a text file.
	 */
	public void saveGame()
	{
		boolean gameOver = parentWindow.getGame().getGameBoard().getIfGameOver();

		if(gameOver)
		{
			// message stating that you cannot save a game that has already been
			// won/lost
			return;
		}

		// to do save game
	}

	/**
	 * Loads the saved Minesweeper game.
	 */
	public void loadGame()
	{
		int row = 0;
		int column = 0;
		List<Cell> cells = new ArrayList<>();

		try (Scanner input = new Scanner(MenuBarPanel.class
				.getResourceAsStream("/images/minesweeperSavedGame.txt")))
		{
			// parse first line
			String rowsColumns = input.nextLine();
			String[] words = rowsColumns.split(",");

			row = Integer.parseInt(words[0]);
			column = Integer.parseInt(words[1]);

			while (input.hasNextLine())
			{
				Cell c = getCell(input.nextLine());

				if(c != null)
				{
					cells.add(c);
				}

			}

			if(cells.size() != (row * column))
			{
				throw new NullPointerException("Corrupted saved file");
			}

			MineField game = new MineField(row, column, cells);
			MineFieldPanel gameBoard = new MineFieldPanel(parentWindow, row, column, game);

			parentWindow.updateMinesweeperWindow(gameBoard);
		}

		catch (NullPointerException e)
		{
			String message = "";

			if(e.getMessage() != null)
			{
				message = e.getMessage();
			}
			else
			{
				message = "Could not find saved game file";
			}
			
			createLoadGameError(message);
		}
		catch (IllegalArgumentException | ArrayIndexOutOfBoundsException e)
		{
			createLoadGameError("Corrupted saved file");

		}
		catch (NoSuchElementException e)
		{
			createLoadGameError("Saved file is empty");

		}


	}

	/**
	 * Creates an error message that is on top of the Minesweeper Window
	 * @param message Message to be displayed in the Error dialog box
	 */
	private void createLoadGameError(String message)
	{
		JOptionPane optionPane = new JOptionPane(message,
				JOptionPane.ERROR_MESSAGE);
		JDialog dialog = optionPane.createDialog("Error");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	/**
	 * Parse cell objects from String
	 * 
	 * @param line String to parse from
	 * @return Returns a Cell object with a set value, x coordinate, y
	 *         coordinate, and booleans if it it is flagged or visible.
	 */
	private static Cell getCell(String line) throws NumberFormatException, ArrayIndexOutOfBoundsException
	{
		String[] words = line.split(",");
		Cell cell;
		int value;
		int x;
		int y;
		boolean flagged;
		boolean visibility;

		value = Integer.parseInt(words[0]);
		x = Integer.parseInt(words[1]);
		y = Integer.parseInt(words[2]);
		flagged = Boolean.parseBoolean(words[3]);
		visibility = Boolean.parseBoolean(words[4]);

		cell = new Cell(value, x, y, flagged, visibility);
		return cell;

	}

}
