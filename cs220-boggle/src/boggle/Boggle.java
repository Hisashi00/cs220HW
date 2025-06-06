package boggle;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Boggle
{
    private String[][] board = new String[4][4];
    private Die[] dice;
    private Random random;
    
    /**
     * Get the letter (or letters in the case of Qu) located at the given
     * row and column.
     * @param row
     * @param col
     * @return The letter or letters at the given row and column.
     */
    public String get(int row, int col) {
        if (row<0 || col<0 || row>=board.length || col>=board.length) {
        	// should we throw an exception instead?
            return null;
        }
        return board[row][col];
    }
    
    
    
    /**
     * Load these words into a set.
     * @param filename Name of the file to read.
     * @throws IOException
     */
    private void loadWords(String filename)
    throws IOException
    {
        Scanner scan = new Scanner(new FileInputStream(filename));
        while (scan.hasNext()) 
        {
            String s = scan.next().toLowerCase().trim();
            if (s.length()<3) {
                // skip words less than length 3
                continue;
            }
        }
        scan.close();
    }
    
    /**
     * Create a new Boggle board using the given filename full of words
     * as the list of legal words.  The board is not ready to be used until
     * calling the configureBoard() method.
     * @param filename The name of the file containing the legal dictionary words.
     * @throws IOException
     */
    public Boggle(String filename, Random random)
    {
    	this.random = random;
        // Create the dice
        dice=new Die[] {
            // 16 boggle dice
            new Die("J", "B", "O", "A", "B", "O"),
            new Die("H", "W", "V", "E", "T", "R"),
            new Die("M", "U", "O", "C", "T", "I"),
            new Die("N", "E", "E", "G", "A", "A"),
            new Die("C", "S", "O", "A", "P", "H"),
            new Die("I", "T", "E", "S", "O", "S"),
            new Die("E", "E", "H", "N", "W", "G"),
            new Die("A", "W", "T", "O", "T", "O"),
            new Die("I", "U", "N", "E", "E", "S"),
            new Die("A", "F", "P", "S", "K", "F"),
            new Die("E", "Y", "L", "D", "R", "V"),
            new Die("Z", "H", "R", "L", "N", "N"),
            new Die("Y", "T", "L", "E", "T", "R"), 
            new Die("I", "T", "S", "T", "D", "Y"),
            new Die("U", "N", "H", "I", "M", "QU"),
            new Die("D", "R", "X", "I", "L", "E")
        };
        // load the words
        try {
        	loadWords(filename);
        } catch (IOException e) {
        	// no way to recover from this, so just repackage as runtime exception
        	throw new RuntimeException(e);
        }
        
        // configure the board for a new game
        configureBoard();
    }
    
    /**
     * Shuffle the dice using the given random number generator.
     * @param r A Random number generator.
     */
    private void shuffleDice() {
        // Probably not the best shuffle algorithm, but hopefully good enough
        for (int i=0; i<dice.length; i++) {
            int index=random.nextInt(16);
            Die temp=dice[i];
            dice[i]=dice[index];
            dice[index]=temp;
        }
    }
    
    
    /**
     * Create a collection of all the words in the dictionary
     * of words passed to the constructor that can be created
     * using the current configuration of letters on the board.
     * 
     * Hint:    Use recursion.
     * Hint2:   Use a helper method.
     * Hint3:   Consider adding more efficient data structures---repeated
     *      linear scans through a list are really, really slow.
     * 
     * @return A collection of all the words in the dictionary
     * passed to the constructor that can be created using the current
     * configuration of letters on the board. 
     */
    public Collection<String> findValidWords()
    {
        throw new UnsupportedOperationException("implement this");
    }
    
    private String toCoord(int row, int col) {
    	return "" + row + col;
    }
    
    /**
     * Shuffle the dice, assign them to a spot on the board, and roll them.  Use
     * the given random number generator for shuffling and rolling the dice.
     * 
     * Passing the Random so that we can get predictable results
     * 
     * @param r
     */
    public void configureBoard() {
        // Shuffle the dice
        shuffleDice();
        for (int i=0; i<dice.length; i++) 
        {
            // there are 16 dice split into 4x4
            // i ranges over all the dice 0 to 15
            // i / 4 is the row 
            // i % 4 is the column
            int row = i / board.length;
            int col = i % board.length;
            board[row][col] = dice[i].roll(random);
        }
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer buf=new StringBuffer();
        for (int row=0; row<board.length; row++) {
            for (int col=0; col<3; col++) {
                buf.append(board[row][col]);
                buf.append("\t");
            }
            buf.append(board[row][3]);
            buf.append("\n");
        }
        return buf.toString();
    }
}
