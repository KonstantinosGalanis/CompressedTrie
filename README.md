# CompressedTrie

This is a program writeen in java that implements a dictionary of the English language through a [compressed Trie](https://www.cs.usfca.edu/~galles/visualization/RadixTree.html) based (radix) on the English alphabet (a-z). 
Compressed is any Trie whose nodes that are not terminals and have only one child are joined to their child. Joined nodes represent a string instead of a single character.

# Features 

● -i word: Inserts word into Trie.

● -r word: Deletes the word word from the Trie.

● -f word: Searches the word in the Trie.

● -p: Prints the pre-order penetration of Trie.

● -d: Prints all the words of the stored dictionary in alphabetical order.

● -w word X: Searches the Trie for all words of the same length as word that are exactly X characters away from the given word.

● -s suffix: Searches the Trie for words containing the given suffix.

● -q: Prints the string “Bye bye!” followed by a newline character and terminates the program.

# Compilation and Execution

Compile the files using the command:

```
javac CompressedTrie/HW1.java
```

Run the files using the Command:

```
java HW1
```
