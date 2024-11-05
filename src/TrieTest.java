import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TrieTest {

	public static void main(String[] args) throws FileNotFoundException {

		// Open file dictionary
		File dictionary = new File("dictionary.txt");
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
					+ "6) List all words that begin with a prefix\r\n" + "7) Size of the trie\r\n" + "8) End\r\n" + "");

			
			// scanner to take operation input
			Scanner operationInput = new Scanner(System.in);
			String operation = operationInput.next();

			if (operation.equals("1")) { // Creating an Empty Trie
				// clears the trie is equal to creating a new trie
				trie.clear();

			} else if (operation.equals("2")) {// Create a trie with initial letters //must insert all permutations of
												// the
				// letter/word
				trie.clear();
				System.out.println("Enter your list of letters: ");
				// splits the letters by spaces and inserts them to an array
				//  by using " " or \\s+
				String[] input = userInput.nextLine().split(" ");

				// get all permutations

				
				String finalString="";
				for (int x = 0; x< input.length; x++) 
					finalString += input[x];
				
				permArrayFun(finalString.toUpperCase(), "");
				
				
				
				// checking if the permutatin is in the dictionary file
				for (int x= 0; x < permutations.size(); x++) {
					for (int y = 0; y < dictionaryArrayList.size(); y++) {
						
						if(dictionaryArrayList.get(y).equals(permutations.get(x))) {
							if (!trie.contains(permutations.get(x))) {
							trie.insert(permutations.get(x));
							//System.out.println(permutations.get(x));
							}
						}
					}
				}

			
			} else if (operation.equals("3")) {// inserts a word/letter in the Trie.
				System.out.println("Enter a word or letter to insert > ");
				String input = userInput.nextLine();
				if(!input.isEmpty())
					trie.insert(input);

			} else if (operation.equals("4")) { // Enter a word to search for
				System.out.println("Enter a word to search for > ");
				String input = userInput.nextLine();

				if (trie.contains(input))
					System.out.println("Word Found > " + input);
				else
					System.out.println("Word not found.");

			} else if (operation.equals("5")) { // delete a word
				System.out.println("Enter a word to delete > ");
				String input = userInput.nextLine();
				if (trie.contains(input)) {
					trie.delete(input);
					System.out.println("Word deleted");
				}
				else
					System.out.println("Unable to Delete; Word does not exist.");

			} else if (operation.equals("6")) { // list all words that begin with a prefix
				
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
					
					System.out.println(input + " < Is Not a Correct Prefix/word does not exist.");

				
				
			} else if (operation.equals("7")) { // Size of the Trie
				System.out.println("Size of the Trie is > " + trie.size());
				//System.out.println(permutations);
			} else if (operation.equals("8")) { // End
				System.out.print("Ending Program.");
				userInput.close();
				stopProgram = true;
				
			} else {
				System.out.println("incorrect choice");
			}
		}

	}

	public static ArrayList<String> permutations = new ArrayList<String>();

	public static void permArrayFun(String word, String newW) {
		
		// if the new perm is empty it will not add it to the arrayList
		if(!newW.isEmpty())
			permutations.add(newW);
		// if input is empty will return
		if (word.isEmpty())
			return;
		for (int x = 0; x < word.length(); x++)
			//
			permArrayFun(word.substring(0, x) + word.substring(x + 1), newW + word.charAt(x));
		
		//AB,""
		//B, A
		//"", AB
		//AB,""
		//A,, B
		//"",BA
		
	}
	
	

}
