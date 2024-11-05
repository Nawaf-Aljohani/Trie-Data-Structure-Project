import java.util.ArrayList;


public class Trie {

	protected TrieNode root;
	protected final int ALPHAPETS = 26;
	protected int countOfNodes = 0;

	public Trie() {
		root = new TrieNode();
	}

	// (a)
	public boolean contains(String word) {
		// we change the letters of the word into upper case
		word = word.toUpperCase();

		int index;
		TrieNode node = root;
		for (int x = 0; x < word.length(); x++) {
			index = word.charAt(x) - 'A';
			
			if (node.children[index] == null)
				return false;

			node = node.children[index];
		}

		return (node.isEndOfWord);
	}

	// (b)
	public boolean isPrefix(String prefix) {

		prefix = prefix.toUpperCase();
		TrieNode node = root;
		for (int x = 0; x < prefix.length(); x++) {
			int index = prefix.charAt(x) - 'A';
			if (node.children[index] == null) {
				return false;
			}
			node = node.children[index];
		}
		return true;
	}

	// (c)
	public void insert(String word) {

		word = word.toUpperCase();
		TrieNode current = root;
		for (int x = 0; x < word.length(); x++) {
			int index = word.charAt(x) - 'A';
			if (current.children[index] == null) {
				TrieNode node = new TrieNode();
				current.children[index] = node;
				current = node;
				countOfNodes = countOfNodes + 1;

			} else {
				current = current.children[index];

			}
		}
		current.isEndOfWord = true;

	}

	// (d)
	public void delete(String key) {
		key = key.toUpperCase();
		deleteHelper(root, key, 0);

	}

	public TrieNode deleteHelper(TrieNode root, String key, int depth) {


		if (depth == key.length()) {
			if (root.isEndOfWord)
				root.isEndOfWord = false;

			if (isEmpty()) 
				root = null;
			
			return root;
		}

		int index = key.charAt(depth) - 'A';
		root.children[index] = deleteHelper(root.children[index], key, depth + 1);

		if (isEmpty() && root.isEndOfWord == false) {
			root = null;
		}
		return root;
	}

	// (e)
	public boolean isEmpty() {
		int counter = 0;
		for (int x = 0; x < ALPHAPETS; x++)
			if (root.children[x] == null)
				counter += 1;

		if (counter == 26)
			return true;
		else
			return false;
	}

	// (f)
	public void clear() {
		for (int x = 0; x < ALPHAPETS; x++)
			root.children[x] = null;
		
		this.countOfNodes = 0;
	}

	// (h)
	public String[] allWordsPrefix(String prefix) {
		prefix = prefix.toUpperCase();
		if (!isPrefix(prefix)) {
			return null;
		}
		TrieNode current = root;
		for (int x = 0; x < prefix.length(); x++) {
			int index = prefix.charAt(x) - 'A';
			if (current.children[index] == null)
				return null;

			else
				current = current.children[index];
			StringBuilder sb = new StringBuilder();
			allWordsPrefixHelper(current, sb, 0, prefix.substring(0, prefix.length()));
		}
		// to conform with the requirements I used an array here
		String[] x = {};
		x =  this.allwords.toArray(x);
		allwords.clear();
		return x;

	}

	// (h)
	
	public ArrayList<String> allwords = new ArrayList<String>();
	public void allWordsPrefixHelper(TrieNode node, StringBuilder str, int level, String initial) {
		if (node.isEndOfWord) {
			// end of word is reached so we clear the string for the new word
			str.delete(level, str.length());

			if (this.contains(initial + str.toString()))
				this.allwords.add(initial + str.toString());
		}

		for (int x = 0; x < ALPHAPETS; x++) {
			if (node.children[x] != null) {
				str.insert(level, Character.toString((char) (x + 'A')));
				allWordsPrefixHelper(node.children[x], str, level + 1, initial);
			}
		}
	}

	// (i)
	// has the least complexity.
	public int size() {
		return countOfNodes;
	}
	

}
