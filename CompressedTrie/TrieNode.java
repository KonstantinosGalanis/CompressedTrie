package CompressedTrie;
public class TrieNode {
    boolean is_end;
    String val;
    TrieNode[] nodes;

    public TrieNode() {
        nodes = new TrieNode[26];
        val = "";
        is_end = false;
    }
}