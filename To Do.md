# mineSweeper
Basic Minesweeper Game


I added the minefieldPanel class so that it is now properly setting buttons dynamically.  Have set it already so that it is in the gridLayout.
The constructor now takes in  ints, rows and columns.  When you initialize it in the MinesweeperWindow you will need to set it to MinefieldPanel(9,9);

A right click action listener (possibly mouseListener) needs to be added to each button.  The left click may need to be changed from actionListener to mouseListener.
Also, the createButton() method needs the exact styling you picked out for the non-visible buttons.

On the drawBoard() method I've left notes where you can add the styling for the flagged, shown, and mine buttons.  You can make these separate methods if you want.
We might technically be able to get rid of drawBoard all together and do the modifications in the leftClick and rightClick methods if we want.  See what you think

Also added a getNumberOfFlagsLaid() to the Minefield class.  This can be called from the game status JLabel by doing mineFieldBoard.getGame().getNumberofFlagsLaid();
It updates everytime a cell is flagged so you just need to update the status.
