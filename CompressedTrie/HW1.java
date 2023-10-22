package CompressedTrie;
import java.util.Scanner;

public class HW1 {
	public static void main(String[] args) {
        Trie trie = new Trie();
        int ret = 0;
        boolean find_ret;
        Scanner sc = new Scanner(System.in);
    	while(sc.hasNextLine()) {
            System.out.print("?: \n");
            String word = sc.nextLine();
            String operation = word.substring(0,2);
            String remain = word.substring(2);
            String[] parts = remain.trim().split("\\s+");
    		switch(operation) {
    		    case "-i":
    		        ret = trie.insert(parts[0]);
                    if(ret == 0) {
                        System.out.println("ADD " + parts[0] + " NOK");
                    }
                    else if(ret == 1) {
                        System.out.println("ADD " + parts[0] + " OK");
                    }
    		        continue;
    	        case "-r" :
    		        ret = trie.remove(parts[0]);
                    if(ret == 0) {
                        System.out.println("RMV " + parts[0] + " NOK");
                    }
                    else if(ret == 1) {
                        System.out.println("RMV " + parts[0] + " OK");
                    }
    		        continue;
    		    case "-f" :
    		        find_ret = trie.find(parts[0]);
                    if(find_ret == false) {
                        System.out.println("FND " + parts[0] + " NOK");
                    }
                    else if(find_ret == true) {
                        System.out.println("FND " + parts[0] + " OK");
                    }
    		        continue;
    		    case "-p" :
    		        trie.preorder();
    		        continue;
    		    case "-d" :
    		        trie.dictionary();
    		        continue;
    		    case "-w" :
    		        trie.distance(parts[0],Integer.parseInt(parts[1]));
    		        continue;
    		    case "-s" :
    		        trie.suffix(parts[0]);
    		        continue;
    		    case "-q" :
                    sc.close();
    		        System.out.print("Bye bye!\n");
    		        return;
    		}
    	}
	}
}
