# mineSweeper
Basic Minesweeper Game

New Game

Load Game

Save Game
 -need to add toString methods to MineField and Cell

Think about adding enum for BEGINNER, INTERMEDIATE, EXPERT
 - adding this to the MinesweeperWindow constructor to determine rows/columns used
   Beginner = MineFieldPanel(this, 9, 9);
   Intermediate = MineFieldPanel(this, 16, 16);
   Expert = MineFieldPanel(this, 16, 30);
- Would need to incorporate in the new, load, and save game buttons.  
  Could have private Level field in MinesweeperWindow.  Have new and save game get this from MinesweeperWindow
- Would need to add Level selector to menuBar (how to do dropdown)
