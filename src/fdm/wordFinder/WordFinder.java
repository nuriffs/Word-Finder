package fdm.wordFinder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordFinder {

    private char[][] board;
    private Trie trie;

    public WordFinder(String boardString, List<String> dictionary) {
        initialiseBoard(boardString);
        initialiseTrie(dictionary);
    }

    private void initialiseBoard(String boardString) {
        boardString = boardString.replace("\\n", "\n");
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

    private void initialiseTrie(List<String> dictionary) {
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

    private static List<String> readWordListFromFile(String filePath) throws IOException {
        List<String> wordList = new ArrayList<>();
        Scanner fileScanner = new Scanner(new File(filePath));

        while (fileScanner.hasNext()) {
            wordList.add(fileScanner.next().toLowerCase());
        }

        fileScanner.close();
        return wordList;
    }
    
    public static void main(String[] args) {
        try {
			@SuppressWarnings("resource")
			String boardString = new Scanner(new File("src/wordBoard.txt")).useDelimiter("\\Z").next();

			List<String> dictionary = readWordListFromFile("src/wordLists.txt");

            WordFinder wordSearch = new WordFinder(boardString, dictionary);
            wordSearch.printBoard();
            wordSearch.searchWords();
        } catch (IOException e) {
            System.err.println("Error reading the board file: " + e.getMessage());
        }
    }

    
}
