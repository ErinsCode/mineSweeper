package teamProject;

import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.border.EmptyBorder;

import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;


/**
 * Creates the menu bar panel containing three buttons:
 * New Game, Save, and Load
 * 
 * @author Erin Mortensen
 * @author Dana Kendall
 *
 */
@SuppressWarnings("serial")
public class MenuBarPanel extends JPanel {
	
	//fields
	private JButton newGameButton;
	private JButton saveButton;
	private JButton loadButton;
	
	/**
	 * Create the panel with 3 buttons.
	 */
	public MenuBarPanel() {
		setOpaque(false);
		setBackground(Color.ORANGE);
		setBounds(0,0,200,30);
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

	}

	
	/**
	 * Create the New Game button.
	 * @return a button that will start a new game
	 */
	private JButton createNewGameButton() {
		JButton newGameButton = new JButton("New Game");
		newGameButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		newGameButton.setBorder(null);
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TO DO: Start new game
			}
		});
		newGameButton.setHorizontalTextPosition(SwingConstants.LEFT);
		newGameButton.setHorizontalAlignment(SwingConstants.LEFT);
		return newGameButton;
	}
	
	/**
	 * Create the Save button.
	 * @return a button that will save a game
	 */
	private JButton createSaveButton() {
		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		saveButton.setBorder(null);
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TO DO: Save game
			}
		});
		return saveButton;
	}
	
	/**
	 * Create the Load button.
	 * @return a button that will load a saved game
	 */
	private JButton createLoadButton() {
		JButton loadButton = new JButton("Load");
		loadButton.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		loadButton.setBorder(null);
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TO DO: Load game
			}
		});
		return loadButton;
	}
	
	/**
	 * Starts a new game.
	 */
	public void startNewGame() {
		
	}
	
	/**
	 * Saves a game to a text file.
	 */
	public void saveGame() {
		
	}
	
	/**
	 * Loads a game from a text file.
	 */
	/*public void loadGame(File gameFile) {
		
	}*/


}
