
public class TrieNode {
	protected final int alphabets = 26;
	protected TrieNode[] children = new TrieNode[alphabets];
	protected boolean isEndOfWord;

	public TrieNode() {
		this.isEndOfWord = false;
		for (int x = 0; x < alphabets; x++)
			children[x] = null;
	}


}
