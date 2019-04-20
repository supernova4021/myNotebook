class Trie {
    
    private TrieNode root;
    
    class TrieNode{
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
        char val;
        
        TrieNode(char val){
            this.val = val;
        }
    }

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode('#');
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode node = root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            if(!node.children.containsKey(ch)){
                node.children.put(ch, new TrieNode(ch));
            }
            node = node.children.get(ch);
        }
        node.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode node = root;
        for(int i=0; i<word.length(); i++){
            char ch = word.charAt(i);
            if(!node.children.containsKey(ch)){
                return false;
            }
            node = node.children.get(ch);
        }
        return node.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for(int i=0; i<prefix.length(); i++){
            char ch = prefix.charAt(i);
            if(!node.children.containsKey(ch)){
                return false;
            }
            node = node.children.get(ch);
        }
        return true;    
    }
}
