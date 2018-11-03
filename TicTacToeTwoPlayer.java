import java.util.Scanner;
import java.util.Random;

/* Complete the following as indicated by the comments.
   Do not delete the comments.
   You will be required to submit a hard copy as well as the zyBook submission.
*/

/*
   Your name: Tevye Cowan
   
   Your cNumber:C0478793
   
*/
public class TicTacToeTwoPlayer {
    static final int SIZE = 3;
    /* DONE
       Declare a 2-d character array called board with SIZE rows and SIZE columns.
       Be sure to use the static modifier in the declaration.
    */
	static final char[][] board = new char[SIZE][SIZE];
	static Scanner userInput = new Scanner(System.in);
    static final char PLAYER1 = 'X';
    static final char PLAYER2 = 'O';
    
	//initizalize DONE
    public static void initializeBoard() {
        /*
           Initialize each position of the board array to a space character.
        */
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				board[i][j] = ' ';
			}
		}
    }
	
    //display DONE
    public static void displayBoard() {
        /*
            Complete this method so that it displays the tic-tac-toe board
            to the screen. If a board position is available, that is, if it is
            a space, output an asterisk followed by a space ("* ".)
        */
		System.out.print("Board is: ");
		for (int i = 0; i < board.length; i++){
			System.out.print("\n");
			for (int j = 0; j < board[i].length; j++){
				if (board[i][j]== ' '){
					System.out.print("*" + " ");
				}
				else{
				System.out.print(board[i][j] + " ");}
			}
		}
     System.out.println("");   
        
    }
    //isTied DONE
    public static boolean isTied(){
	/*
        Write a method called isTied.
        The method will return true if the game is tied and
        false otherwise.
        The method has no formal parameters.
        The method assumes that there is no winning pattern on the board.
    
	*/
	int freeSpace = 0;
	
	boolean tie = false;
	for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				if (board[i][j] == ' '){
				freeSpace++;
				}
			}
		}
		
	if (freeSpace > 0){
		tie = false;
	}
	else {tie = true;}
	return tie;
	}
    //playermove DONE
    public static void playerMove(char player) {
        /*
            Complete this method so that it makes a move for the
            player indicated by the formal parameter.
            The prompts have been supplied for you.
            Prompt the user to enter a row and column until they
            enter a legal row and column (The row and column must
            be between 1 and 3, includsive.)
            
            If, once a legal row and column has been entered,
            the specified position is already occupied (ie not a space)
            then inform the user and prompt them to enter another
            row and column.
    
            Here are the prompts for you to use:
       
            System.out.println("Enter the move for player: " + player);
            System.out.print("\nRow number: ");
            System.out.print("\nColumn number: ");
            System.out.println("That position is taken. Try again.");
        */
		
		
		int column;
		int row;
		boolean valid = false;
		do{
		System.out.println("Enter the move for player: " + player + "\n");
		System.out.println("Row number: ");
		row = userInput.nextInt();		
		System.out.print("Column number: ");
		column = userInput.nextInt();
		
		while ((row > 3) || (row <= 0) || (column > 3) || (column <= 0)){
			System.out.println("Illegal row/column: " + row + ", " + column);
				System.out.println("Enter the move for player: " + player + "\n");
			System.out.println("Row number: ");
			row = userInput.nextInt();
			System.out.print("Column number: ");
			column = userInput.nextInt();
			}
		
		valid = false;
		
		if(board[row-1][column-1] != ' '){
			System.out.println("That position is taken. Try again.");
			valid = true;
		}
		else{board[row-1][column-1] = player;
		
		}
		
		}
		while(valid == true);
        
    }
    //DONE
    public static char getWinner() {
        /*
            Complete this method, getWinner(), so that it returns a character
            indicating which player has won. If no player has won, the method
            will return a space.
            The check for a row win has been done for you.
            You must write the checks for a column win and a diagnoal win.
        */
        char whoWon=' ';
        boolean won=false;
        
        // check for a row win
		for (int i=0; i < SIZE && !won; i++) {
			if ( (board[i][0] == board[i][1]) && (board[i][1] == board[i][2]) && board[i][0] != ' ') {
				whoWon = board[i][0];
                won = true;
			}
		}
			
		// check for a column win
        for (int i=0; i < SIZE && !won; i++) {
			if ( (board[0][i] == board[1][i]) && (board[1][i] == board[2][i]) && board[0][i] != ' ') {
				whoWon = board[0][i];
                won = true;
			}
		}
        
        // check for a diagonal win
         for (int i=0; i < SIZE && !won; i+=2) {
			if (i == 0){
			if ( (board[0][i] == board[1][1]) && (board[1][1] == board[2][2]) && board[0][i] != ' ') {
				whoWon = board[0][i];
                won = true;
			}}
			else if (i == 2){
				if ( (board[i][0] == board[1][1]) && (board[1][1] == board[0][i]) && board[i][0] != ' ') {
				whoWon = board[i][0];
                won = true;
			}
			}
		 }   
		 
		 
            
        return(whoWon);
    }
    
    /*
        The main method is complete. Do not change the main method.
    */
    
    public static void main(String args[]) {
      
        char whoWon;
        boolean won, tie;
        String again;
        
        do {
            won = false;
            tie = false;
            initializeBoard();
            System.out.println("Welcome to TicTacToe.");
            displayBoard();
            
            do {
                playerMove(PLAYER1);
                displayBoard();
            
                whoWon = getWinner();
           
                if (!(whoWon == ' '))
                    won = true;
            
                if (!won)
                    tie = isTied();
          
                if (!won && !tie) {
                    playerMove(PLAYER2);
                    displayBoard();
                }
                
                whoWon = getWinner();
                if (!(whoWon == ' '))
                    won = true;
                if (!won)
                    tie = isTied();
            } while (!won && !tie);
            
            if (tie) 
                System.out.println("Tie game.");
            else if (whoWon == PLAYER1)
                System.out.println("Player 1 wins.(" + PLAYER1 + ")");
            else
                System.out.println("Player 2 wins.(" + PLAYER2 + ")");
            
            System.out.print("Care to play again? (y/n) ");
            again = userInput.next();
        } while (again.equalsIgnoreCase("y")); 
    }
}