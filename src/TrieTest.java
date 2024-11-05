import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TrieTest {

	public static void main(String[] args) throws FileNotFoundException {


		// Open file dictionary
		File dictionary = new File("./dictionary.txt");
		ArrayList<String> dictionaryArrayList = new ArrayList<String>();

		// Read dictionary.txt and add to array while ignoring words that contain
		// non-letters.
		Scanner readFile = new Scanner(dictionary);
		while (readFile.hasNext()) {
			String dictionaryWord = readFile.nextLine();
			boolean result = dictionaryWord.matches("[a-zA-Z]+"); // a regular expression to match a string
			if (result)
				dictionaryArrayList.add(dictionaryWord);
		}
		// close file after reading
		readFile.close();

		
		
		Scanner userInput = new Scanner(System.in);
		Trie trie = new Trie();
		boolean stopProgram = false;
		while (stopProgram == false) {

			System.out.println("\nEnter your choice");
			System.out.println("1) Create an empty trie\r\n" + "2) Create a trie with initial letters\r\n"
					+ "3) Insert a word\r\n" + "4) Search for a word\r\n" + "5) Delete a word\r\n"
					+ "6) List all words that begin with a prefix\r\n" + "7) Size of the trie\r\n"
					 + "8) End\r\n" + "");

			Scanner operationInput = new Scanner(System.in);
			int operation = operationInput.nextInt();

			if (operation == 1) { // Creating an Empty Trie
				trie.clear();

			} else if (operation == 2) {// Create a trie with initial letters //must insert all permutations of the
										// letter/word
				trie.clear();
				System.out.println("Enter a word or letter: ");
				String[] input = userInput.nextLine().split("");

			
				
				ArrayList<String> permutations = new ArrayList<String>();
				

			} else if (operation == 3) {// inserts a word/letter in the Trie.
				System.out.println("Enter a word or letter to insert > ");
				String input = userInput.nextLine();
				trie.insert(input);

			} else if (operation == 4) { // Enter a word to search for
				System.out.println("Enter a word to search for > ");
				String input = userInput.nextLine();

				if (trie.contains(input))
					System.out.println("Word Found.");
				else
					System.out.println("Word not found.");

			} else if (operation == 5) { // delete a word
				System.out.println("Enter a word to delete > ");
				String input = userInput.nextLine();
				trie.delete(input);

			} else if (operation == 6) { // list all words that begin with a prefix
				System.out.println("Enter a prefix > ");
				String input = userInput.nextLine();
				if (trie.isPrefix(input)) {
					String[] allWordsPrefixes = trie.allWordsPrefix(input);
					for (int x = 0; x < allWordsPrefixes.length; x++)
						if (x == allWordsPrefixes.length - 1)
							System.out.print(allWordsPrefixes[x]);
						else
							System.out.print(allWordsPrefixes[x] + ", ");
				} else
					System.out.println(input + "Is Not a Correct Prefix");
				
			} else if (operation == 7) { // Size of the Trie
				System.out.println("Size of the Trie is > " + trie.size());

			} else if (operation == 8) { // End
				System.out.print("Ending Program.");
				stopProgram = true;
			}

		}

	}
}
