
public class TrieNode {
	protected final int ALPHAPETS = 26;
	protected TrieNode[] children = new TrieNode[ALPHAPETS];
	protected boolean isEndOfWord;

	public TrieNode() {
		this.isEndOfWord = false;
		for (int x = 0; x < ALPHAPETS; x++)
			children[x] = null;
	}


}
