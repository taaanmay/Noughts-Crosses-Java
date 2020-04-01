import java.util.Scanner;

public class NoughtsAndClasses {
	public final static int NUMBER_OF_ROWS=3;
	public final static int NUMBER_OF_COLUMNS=3;
	public final static int MOVE_NUMBER = 0;
	public final static char CROSS = 'X';
	public final static char NOUGHT = '0';
	public final static char SPACE = ' ';

	public static void clearBoard(char[][]board) {
		for(int i=0 ; i<NUMBER_OF_ROWS;i++) {
			for(int j=0 ; j<NUMBER_OF_ROWS;j++) {
				board[i][j]= SPACE;
			}
		}

	}

	public static void printBoard(char[][]board) {

		System.out.println("    1     2     3     ");
		for(int i =-1 ; i<=board.length ; i++) 
		{
			if(i==0||i==1||i==2) {
				System.out.print("|"+(i+1)+ "|");
			}else 
			{
				System.out.print("| |");	
			}

			for(int j=0;j<board[0].length;j++) {

				if(i==-1||i==board.length)
				{
					System.out.print("------");
				}else System.out.print("  "+board[i][j]+"  |");
			}	
			System.out.println("|");		
		}
	}

	public static boolean canMakeMove(char[][]board,int row, int column) {
		boolean canMakeMove=true;
		if (board[row-1][column-1]==SPACE)canMakeMove=true;
		else canMakeMove = false;

		return canMakeMove;
	}


	public static void makeMove(char[][] board, char currentPlayerPiece , int row, int column) {
		board[row-1][column-1]=currentPlayerPiece;
	}

	public static boolean isBoardFull(char[][]board) {
		boolean noSpaceLeft = false;
		int numberOfSpaces = 9;
		for(int i = 0;i<NUMBER_OF_ROWS;i++) {
			for(int j=0;j<NUMBER_OF_COLUMNS;j++) {
				if (board[i][j]==CROSS||board[i][j]==NOUGHT) numberOfSpaces--; 
			}
		}
		if (numberOfSpaces==0)noSpaceLeft=true;
		return noSpaceLeft;
	}

	public static char winner(char[][]board) {
		char winner = 'D';
		for(int i=0;i<NUMBER_OF_ROWS;i++) {
			if((board[i][0]==board[i][2]&&board[i][0]==board[i][1]&&board[i][1]!=SPACE)) {
				winner = board[i][0];

			}else if  (board[0][i]==board[2][i]&&board[0][i]==board[1][i]&&board[0][i]!=SPACE) {
				winner = board[0][i];

			}else if ((board[0][0]==board[1][1]&&board[0][0]==board[2][2]&&board[1][1]!=SPACE)|| (board[0][2]!=SPACE &&board[0][2]==board[1][1]&&board[1][1]==board[2][0]) ) {
				winner = board[1][1];
			}
		}return winner;
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		boolean end = false;

		int chance = 0;
		char[][]board = new char[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
		clearBoard(board);
		printBoard(board);
		char currentPlayerPiece = NOUGHT;


		do {

			try {
				System.out.println("\nEnter row and column seperated by space");

				int row =scanner.nextInt();
				int column = scanner.nextInt();

				if(row<=3&&column<=3&&row>0&&column>0)  {

					if (isBoardFull(board))end=true;

					if(canMakeMove(board,row,column)) {
						if(chance%2==0) {
							currentPlayerPiece = NOUGHT;
						}else currentPlayerPiece = CROSS;

						makeMove(board,currentPlayerPiece,row,column);
						printBoard(board);	
						chance++;
					}else {
						System.out.println("PLACE ALREADY FILLED. Enter row and column seperated by space");
						printBoard(board);
					}

				}else {
					System.out.println("INVALID ENTRY.Please enter a value b/w 1 to 3");
					//scanner.nextLine();
				}

				if(winner(board)==NOUGHT||winner(board)==CROSS) {
					System.out.println("THE WINNER IS "+winner(board));
					end =true;

				}
			}catch (java.util.NoSuchElementException exception){
				System.out.println("Invalid Entry. Start the game again.");
				scanner.nextLine();

			}
		}while(end==false);


	}
}

/* SELF ASSESSMENT  
1.  clearBoard:
Did I use the correct method definition?
Mark out of 5:5
Comment:Yes, the correct method defination is used.
Did I use loops to set each position to the BLANK character?
Mark out of 5:5
Comment:Yes for loops are used to set each position to blank characters.

2.  printBoard
Did I use the correct method definition?
Mark out of 5:5
Comment:Yes, the correct method defination is used.
Did I loop through the array and prints out the board in a way that it looked like a board?
Mark out of 5:5
Comment:Yes.A loop is made tomake it like a board.

3.  canMakeMove
Did I have the correct function definition and returned the correct item?
Mark out of 5:5
Comment: Yes, the correct method defination is used.
Did I check if a specified location was BLANK?
Mark out of 5:5
Comment:Yes, if it is blank,it is returned that a move can be made.


4.  makeMove
Did I have the correct function definition?
Mark out of 5:5
Comment:Yes, I have the correct function defination.
Did I set the  currentPlayerPiece in the specified location?
Mark out of 5:5
Comment:Yes, currentPlayerPiece isset in the specified location.    


5.  isBoardFull
Did I have the correct function definition and returned the correct item?
Mark out of 5:5
Comment:Yes  I have the correct function definition and the correct item is returned.        
Did I loop through the board to check if there are any BLANK characters?
Mark out of 5:5
Comment:Yes a loop is made.


6.  winner
Did I have the correct function definition and returned the winning character
Mark out of 5:5
Comment: Yes I have the correct function defination and only the winning character is returned    
Did I identify all possible horizontal, vertical and diagonal winners  
Mark out of 15:15
Comment:Yes all the possible casses are considered.


7.main
Did I create a board of size 3 by 3 and use the clearBoard method to set all the positions to the BLANK character ('  ')?
Mark out of 3:3
Comments:Yes. printBoard and clearBoard is used for that.
Did I loop asking the user for a location until wither the board was full or there was a winner?
Mark out of 5:5
Comments:Yes a loop is created and keeps on going on till the board is full r if there is a winner.
Did I call all of the methods above?
Mark out of 5:5
Comments:All the methods are called.
Did I handle incorrect locations provided by the user (either occupied or invalid locations)?
Mark out of 3:3
Comments:Yes it is taken care of.
Did I switch the current player piece from cross to nought and vice versa after every valid move?
Mark out of 3:3
Comments:Yes the character changes after every VALID move.
Did I display the winning player piece or a draw at the end of the game?
Mark out of 3:3
Comments:Yes, the winneris displayed after the game.

8.  Overall
Is my code indented correctly?
Mark out of 3:3
Comments:Yes the code is indented.
Do my variable names and Constants (at least four of them) make sense?
Mark out of 3:3
Comments:Yes.
Do my variable names, method names and class name follow the Java coding standard
Mark out of 2:2
Comments:Yes.
   Total Mark out of 100 (Add all the previous marks):  100
 */

