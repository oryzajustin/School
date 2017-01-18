
public class Achi 
{
	private int board_size;
	private int max_levels;
	private char[][] gameBoard;
	//constructor that takes board size and makes a table
	// of n*n
	public Achi(int board_size, int max_levels)
	{
		this.board_size = board_size;
		this.gameBoard = new char[board_size][board_size];
		for(int i = 0; i < board_size; i++)
		 {
			 for(int j= 0; j < board_size; j++)
			 {
				 gameBoard[i][j] = ' ';
			 }
		 }
	}
	public Dictionary createDictionary()
	{
		Dictionary dictionary = new Dictionary(board_size);
		return dictionary;
	}
	//checks if the gameboard is already in the dictionary
	public int repeatedConfig(Dictionary configurations)
	{
		return configurations.find(boardToString(gameBoard));
	}
	//inserts configuration into the dictionary
	public void insertConfig(Dictionary configurations, int score) throws DictionaryException
	{
		String board = boardToString(gameBoard);
		ConfigData item = new ConfigData(board, score);
		configurations.insert(item);
	}
	//stores an X or O into the game board
	public void storePlay(int row, int col, char symbol)
	{
		gameBoard[row][col] = symbol;
	}
	/*
	 * checks if a tile is empty
	 */
	public boolean tileIsEmpty (int row, int col)
	{
		if(gameBoard[row][col] == ' ')
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//checks if the tile is an O
	public boolean tileIsComputer (int row, int col)
	{
		if(gameBoard[row][col] == 'O')
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	//checks if the tile is an X
	 public boolean tileIsHuman (int row, int col)
	 {
		 if(gameBoard[row][col] == 'X')
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	 }
	 //lays out the conditions for a win
	 //3 in a row horizontally, vertically and diagonally both ways
	 public boolean wins (char symbol)
	 {
		 int rowCount = 0;
		 int colCount = 0;
		 int diagonalCount = 0;
		 int diagonalCount2 = 0;
		 //row
		 for(int i = 0; i < board_size; i++)
		 {
			 for(int j= 0; j < board_size; j++)
			 {
				 if(gameBoard[i][j] == symbol)
				 {
					 rowCount = rowCount + 1;
				 }
			 }
		 }
		 //column
		 for(int i = 0; i < board_size; i++)
		 {
			for(int j = 0; j < board_size; j++)
			{
				if(gameBoard[j][i] == symbol)
				{
					colCount = colCount + 1;
				}
			}
		 }
		 //diagonal
		 for(int i = 0; i < board_size; i++)
		 {
			 if(gameBoard[i][i] == symbol)
			 {
				 diagonalCount = diagonalCount + 1;
			 }
		 }
		 for(int i = board_size - 1; i > 0; i--)
		 {
			 if(gameBoard[i][i] == symbol)
			 {
				 diagonalCount2 = diagonalCount2 + 1;
			 }
		 }
		 if(rowCount == board_size || colCount == board_size || diagonalCount == board_size || diagonalCount2 == board_size)
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	 }
	 //lays out the conditions for a draw where there are no
	 //3 in a row
	 public boolean isDraw(char symbol)
	 {
		 int rowCount = 0;
		 int colCount = 0;
		 int diagonalCount = 0;
		 int diagonalCount2 = 0;
		 int emptyCount = 0;
		 
		 //empty
		 for(int i = 0; i < board_size; i++)
		 {
			 for(int j = 0; j < board_size; j++)
			 {
				 if (gameBoard[i][j] == ' ')
				 {
					 emptyCount = emptyCount + 1;
				 }
				 if(emptyCount > 1)
				 {
					 return false;
				 }
			 }
		 }
		 //row
		 for(int i = 0; i < board_size; i++)
		 {
			 for(int j= 0; j < board_size; j++)
			 {
				 if(gameBoard[i][j] == symbol)
				 {
					 rowCount = rowCount + 1;
				 }
			 }
		 }
		 //column
		 for(int i = 0; i < board_size; i++)
		 {
			for(int j = 0; j < board_size; j++)
			{
				if(gameBoard[j][i] == symbol)
				{
					colCount = colCount + 1;
				}
			}
		 }
		 //diagonal
		 for(int i = 0; i < board_size; i++)
		 {
			 if(gameBoard[i][i] == symbol)
			 {
				 diagonalCount = diagonalCount + 1;
			 }
		 }
		 for(int i = board_size - 1; i > 0; i--)
		 {
			 if(gameBoard[i][i] == symbol)
			 {
				 diagonalCount2 = diagonalCount2 + 1;
			 }
		 }
		 if(rowCount < board_size && colCount < board_size && diagonalCount < board_size && diagonalCount2 < board_size)
		 {
			 return true;
		 }
		 else
		 {
			 return false;
		 }
	 }
	 //returns the score based on who won the game
	 //0 for human win, 1 for undecided, 2 for draw, 3 for computer win 
	 public int evalBoard(char symbol)
	 {
		 int val = 9;
		 if(symbol == 'X')
		 {
			 if(wins(symbol))
			 {
				 val = 0;
			 }
		 }
		 else if(symbol == 'O')
		 {
			 if(wins(symbol))
			 {
				 val = 3;
			 }
		 }
		 else if(isDraw(symbol))
		 {
			 val = 2;
		 }
		 else
		 {
			 val = 1;
		 }
		 return val;
	 }
	 /*
	  * appends the characters in the array to a string
	  */
	 private String boardToString(char[][] gameBoard)
	 {
		 String arrayContent = "";
		 for(int i=0; i < gameBoard.length - 1; i++)
		 {
			 for(int j=0; j < gameBoard[i].length; j++)
			 {
				 arrayContent += gameBoard[i][j];
			 }
		 }
		 return arrayContent;
	 }
}
