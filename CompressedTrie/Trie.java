package CompressedTrie;
import java.util.Arrays;
import java.util.Objects;

public class Trie {
    private final char CASE;    // 'a' for lower, 'A' for upper
    TrieNode root;
	
	public Trie() {
        root = new TrieNode();
        CASE = 'a'; 
    }
 
    public Trie(char CASE) {
        this.CASE = CASE;   // constructor accepting the starting symbol
    }
    
    public int insert(String text) {
        return Insert(text, root);
    }

    public int Insert(String text, TrieNode current) {
        if (text == null || text.length() == 0) return 0;
        TrieNode node = current.nodes[text.charAt(0) - CASE];
        
        if (node == null) { 
            node = new TrieNode();
            node.is_end = true;
            node.val = text;
        } 
        else if (node.val.equals(text)) { 
            if(node.is_end == false) {
                node.is_end = true;
                return 1;
            }
            else {
                return 0;
            }
        } 
        else if (text.startsWith(node.val)) {
            int ret = Insert(text.substring(node.val.length()), node);
            if(ret == 0) {
                return 0;
            }
        } 
        else if (node.val.startsWith(text)) { 
            TrieNode new_node = new TrieNode();
            new_node.val = node.val.substring(text.length());
            new_node.is_end = node.is_end;
            new_node.nodes = node.nodes;
            node.is_end = true;
            node.val = text;
            node.nodes = new TrieNode[26];
            node.nodes[new_node.val.charAt(0) - CASE] = new_node;
        } 
        else { 
            TrieNode new_node = new TrieNode();
            int i = 0;
            while (node.val.charAt(i) == text.charAt(i)) i++;
            new_node.val = node.val.substring(i);
            new_node.is_end = node.is_end;
            new_node.nodes = node.nodes;
            node.is_end = false;
            node.val = node.val.substring(0, i);
            node.nodes = new TrieNode[26];
            node.nodes[new_node.val.charAt(0) - CASE] = new_node;
            int ret = Insert(text.substring(node.val.length()), node);
            if(ret == 0) {
                return 0;
            }
        }
        current.nodes[text.charAt(0) - CASE] = node; 
        return 1;
    }

    public int remove(String text) {
        if(find(text) == false) {
            return 0;
        }
        return Remove(text, root);
    }

    public int Remove(String text, TrieNode current) {
        if (text == null || text.length() == 0) return 0; 
        TrieNode node = current.nodes[text.charAt(0) - CASE]; 
        
        if (node == null) {
            return 0;
        } 
        else if (node.val.equals(text)) {
            long count = Arrays.stream(node.nodes).filter(Objects::nonNull).count();
            if (count == 0) {
                node = null;
            }
            else if (count == 1) { 
                TrieNode new_node = Arrays.stream(node.nodes).filter(Objects::nonNull).findFirst().get();
                node.val += new_node.val;
                node.nodes = new_node.nodes;
                node.is_end = new_node.is_end;
            } 
            else { 
                node.is_end = false;
            }
        } 
        else if (text.startsWith(node.val)) { 
            Remove(text.substring(node.val.length()), node);
        } 
        else { 
            return 0;
        }
        current.nodes[text.charAt(0) - CASE] = node;
        int new_count = (int) Arrays.stream(current.nodes).filter(Objects::nonNull).count();
        if(current.is_end == false && new_count == 1 && current!=root) { 
            TrieNode new_child = Arrays.stream(current.nodes).filter(Objects::nonNull).findFirst().get();
            current.val += new_child.val;
            current.nodes = new_child.nodes;
            current.is_end = new_child.is_end;
        }
        return 1;
    }
    
    public boolean find(String text) {
        return Find(text, root);
    }

    public boolean Find(String text, TrieNode node) {
        if (text == null || text.length() == 0) return false; 
        TrieNode curr = node.nodes[text.charAt(0) - CASE];
        if (curr == null) return false;
        if (curr.val.equals(text)) return curr.is_end; 
        if (text.startsWith(curr.val)) return Find(text.substring(curr.val.length()), curr); 
        return false; 
    }
    
    public void preorder() {
        System.out.print("PreOrder:");
        pre0rder(root,"");
        System.out.print("\n");
    }
 
    private void pre0rder(TrieNode node, String text) {
        System.out.print(text);
        if (node.is_end) {
            System.out.print("#");
        }
        System.out.print(" ");
        for (TrieNode curr : node.nodes) {
            if (curr != null) {
                pre0rder(curr, curr.val);
            }
        }
    }

    public void dictionary() {
        System.out.print("\n***** Dictionary *****\n");
        dicti0nary(root,"");
        System.out.print("\n");
    }
 
    private void dicti0nary(TrieNode node, String text) {
        
        if (node.is_end) {
            System.out.print(text+"\n");
        }
        for (TrieNode curr : node.nodes) {
            if (curr != null) {
                dicti0nary(curr,text + curr.val);
            }
        }
    }

    static int hamming_distance(String str1, String str2) {
        int i = 0, count = 0;
        while (i < str1.length()) {
            if (str1.charAt(i) != str2.charAt(i))
                count++;
            i++;
        }
        return count;
    }
    
    public void distance(String text,int diff) {
        System.out.print("\nDistant words of "+text+" ("+diff+"):\n");
        Distance(root,text,"",diff);
        System.out.print("\n");
    }

    private void Distance(TrieNode node, String text,String alter,int diff) {
        if(text.length() == alter.length() && node.is_end == true) {
            int ret = hamming_distance(text,alter);
            if(ret == diff) {
                System.out.println(alter);
            }
        }
        
        for (TrieNode curr : node.nodes) {
            if (curr != null) {
                Distance(curr,text,alter + curr.val,diff);
            }
        }
    }
    
    public void suffix(String suffix) {
        System.out.print("\nWords with suffix "+ suffix +":\n");
        Suffix(root,"",suffix);
        System.out.print("\n");
    }
 
    private void Suffix(TrieNode node, String text,String suffix) { 

        if(node!=root) {
            if(text.endsWith(suffix) && node.is_end == true) {
                System.out.print(text+"\n");
            }  
        }
        for (TrieNode curr : node.nodes) {
            if (curr != null) {
                Suffix(curr,text+curr.val,suffix);
            }
        }
    }

}
    	