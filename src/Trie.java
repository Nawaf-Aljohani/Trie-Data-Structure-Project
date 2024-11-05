import java.util.ArrayList;

public class Trie {

	protected TrieNode root;
	protected final int alphabets = 26;
	protected int countOfNodes = 0;

	public Trie() {
		root = new TrieNode();
	}

	// (a)
	public boolean contains(String word) {
		word = word.toUpperCase();

		int level, index;
		int length = word.length();
		TrieNode node = root;
		for (level = 0; level < length; level++) {
			index = word.charAt(level) - 'A';

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
		for (int i = 0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			int index = ch - 'A';
			if (node.children[index] == null) {
				return false;
			}
			node = node.children[index];
		}
		System.out.println("True");
		return true;
	}

	// (c)
	public void insert(String word) {
		if (word == null || word.isEmpty())
			return;

		word = word.toUpperCase();

		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {

			char c = word.charAt(i);
			int index = c - 'A';
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
		if (root == null)
			return null;

		if (depth == key.length()) {
			if (root.isEndOfWord)
				root.isEndOfWord = false;

			if (isEmpty()) {
				root = null;
			}
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
		for (int x = 0; x < alphabets; x++)
			if (root.children[x] == null)
				counter += 1;

		if (counter == 26)
			return true;
		else
			return false;
	}

	// (f)
	public void clear() {
		for (int x = 0; x < alphabets; x++)
			root.children[x] = null;
	}

	// (h)
	
	public String[] allWordsPrefix(String prefix) {
		prefix = prefix.toUpperCase();
		if (!isPrefix(prefix)) {
			return null;
		}
		TrieNode current = root;
		for (int i = 0; i < prefix.length(); i++) {
			char ch = prefix.charAt(i);
			int index = ch - 'A';
			if (current.children[index] == null)
				return null;

			else
				current = current.children[index];
			StringBuilder sb = new StringBuilder();
			allWordsPrefixHelper(current, sb, 0, prefix.substring(0, prefix.length()));
		}
		
		
		String[] x = {};
		x =  allwords.toArray(x);
		return x;

	}

	// (h)
	private ArrayList<String> allwords = new ArrayList<String>();
	private void allWordsPrefixHelper(TrieNode node, StringBuilder str, int level, String initial) {
		if (node.isEndOfWord) {
			// end of word is reached so we clear the string for the new word
			str.delete(level, str.length());

			if (this.contains(initial + str.toString()))
				this.allwords.add(initial + str.toString());
		}

		// we can do this iterativaly...
		for (int i = 0; i < alphabets; i++) {
			if (node.children[i] != null) {
				str.insert(level, Character.toString((char) (i + 'A')));
				allWordsPrefixHelper(node.children[i], str, level + 1, initial);
			}
		}
	}

	// (i)
	// has the least complexity.
	public int size() {
		return countOfNodes;
	}

}
