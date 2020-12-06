package teamProject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;

/**
 * GUI with 4 squares and a left and right button that transfer 
 * a blue background from square to square.
 * @author Dana Kendall
 * @author Erin Mortensen
 */
@SuppressWarnings("serial")
public class MinesweeperWindow extends JFrame {

	//fields
	private JPanel contentPane;
	private MenuBarPanel menuBar;
	private MineFieldPanel mineFieldBoard;
	private JPanel trackerPanel;
	private JLabel mineTracker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MinesweeperWindow frame = new MinesweeperWindow();
					//.pack() method ensures all elements are at their preferred size
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MinesweeperWindow() {
		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 240, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBounds(0,0,240,300);
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		menuBar = createMenuBar();
		contentPane.add(menuBar);
		
		trackerPanel = createTrackerPanel();
		contentPane.add(trackerPanel);
		mineTracker = createMineTracker();
		trackerPanel.add(mineTracker);
		
		mineFieldBoard = new MineFieldPanel(this,9,9);
		mineFieldBoard.setBorder(new EmptyBorder(20, 15, 20, 15));
		contentPane.add(mineFieldBoard);
	}
	
	/**
	 * Creates the menu bar panel.
	 * @return the menu bar of type MenuBarPanel
	 */
	private MenuBarPanel createMenuBar() {
		MenuBarPanel menuBar = new MenuBarPanel(this);
		menuBar.setAlignmentX(0.6f);
		return menuBar;
	}
	
	/**
	 * Creates the mine tracker panel.
	 * @return JPanel trackerPanel
	 */
	private JPanel createTrackerPanel() {
		JPanel trackerPanel = new JPanel();
		trackerPanel.setBounds(0,0,200,40);
		trackerPanel.setPreferredSize(new Dimension(200, 40));
		trackerPanel.setMinimumSize(new Dimension(200, 40));
		trackerPanel.setMaximumSize(new Dimension(200, 40));
		return trackerPanel;
	}
	
	/**
	 * Creates the mine tracker label.
	 * @return JLabel mineTracker
	 */
	private JLabel createMineTracker() {
		JLabel mineTracker = new JLabel("10");
		mineTracker.setForeground(Color.RED);
		mineTracker.setMinimumSize(new Dimension(140, 35));
		mineTracker.setMaximumSize(new Dimension(140, 35));
		mineTracker.setPreferredSize(new Dimension(140, 35));
		mineTracker.setBorder(new EmptyBorder(0, 0, 0, 0));
		mineTracker.setBackground(Color.DARK_GRAY);
		mineTracker.setOpaque(true);
		mineTracker.setFont(new Font("Monospaced", Font.PLAIN, 23));
		mineTracker.setHorizontalAlignment(SwingConstants.CENTER);
		return mineTracker;
	}
	
	/**
	 * Updates the mine tracker label.
	 */
	public void updateMineTracker() {
		
		if (mineFieldBoard.getGameBoard().getIfWonGame()) {
			mineTracker.setText("Game Won!");
		}
		
		else if (mineFieldBoard.getGameBoard().getIfLost()) {
			mineTracker.setText("Game Over");
		}
		
		else {
			int numberOfFlags = mineFieldBoard.getGameBoard().getNumberOfFlagsLaid();
			int numOfMines = mineFieldBoard.getGameBoard().getNumberOfMines();
			mineTracker.setText(numOfMines - numberOfFlags + "");
		}
	}
	
	/**
	 * Returns the MineFieldPanel.
	 * @return MineFieldPanel game
	 */
	public MineFieldPanel getGame()
	{
		return mineFieldBoard;
	}
		
	/**
	 * Updates the MinesweeperWindow so that it has an updated
	 * MineField game / MineFieldPanel.
	 * @param the MineFieldPanel we need to replace the current MineFieldPanel with
	 */
	public void updateMinesweeperWindow(MineFieldPanel gameBoard)
	{
		
		contentPane.remove(mineFieldBoard);
		mineFieldBoard.setVisible(false);
		this.mineFieldBoard = gameBoard;
		
		contentPane.add(mineFieldBoard);
		mineFieldBoard.setBorder(new EmptyBorder(20, 15, 20, 15));
		mineFieldBoard.setVisible(true);
		updateMineTracker();
		mineFieldBoard.drawBoard();
	}
	
}




