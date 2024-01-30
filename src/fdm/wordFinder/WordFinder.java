package fdm.wordFinder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordFinder {

	private char[][] board;
    private Trie trie;

    public WordFinder(String boardString, List<String> dictionary) {
        initializeBoard(boardString);
        initializeTrie(dictionary);
    }

    private void initializeBoard(String boardString) {
        String[] rows = boardString.split("\n");
        int numRows = rows.length;
        int numCols = rows[0].length();

        board = new char[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                board[i][j] = rows[i].charAt(j);
            }
        }
    }

    private void initializeTrie(List<String> dictionary) {
        trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word.toLowerCase());
        }
    }

    public void printBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public void searchWords() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a word to search (or type 'exit' to quit): ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("exit")) {
                break;
            }

            if (isValidWord(input)) {
                if (trie.search(input)) {
                    System.out.println("Word found!");
                } else {
                    System.out.println("Word not found.");
                }
            } else {
                System.out.println("Invalid word. Words must be at least three letters in length.");
            }
        }

        scanner.close();
    }

    private boolean isValidWord(String word) {
        return word.length() >= 3;
    }
    

    public static void main(String[] args) {
        String boardString = "EATE\nLXRR\nARTR\nITSE\n";
        List<String> dictionary = new ArrayList<>();
        dictionary.add("art");
        dictionary.add("later");
        dictionary.add("extraterrestrial");
        dictionary.add("eat");
        dictionary.add("ate");

        WordFinder wordSearch = new WordFinder(boardString, dictionary);
        wordSearch.printBoard();
        wordSearch.searchWords();
    }
}