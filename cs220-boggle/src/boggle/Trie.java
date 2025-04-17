package boggle;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * Practice building this Trie here:
 * 
 * https://leetcode.com/problems/implement-trie-prefix-tree/
 * 
 * @author jspacco
 *
 */
class Trie {
    
    
    private Boolean isWord = false;
    private Map<Character, Trie> children = new HashMap<>();
    
    
    /** Inserts a word into the trie. */
    public void insert(String word) 
    {
        if (word.equals("")){
            isWord = true;
            return;
        }

        char ch = word.charAt(0);
        children.putIfAbsent(ch, new Trie());
        Trie child = children.get(ch);
        child.insert(word.substring(1));
    }
    
    
    
    /** Returns if the word is in the trie. */
    public boolean contains(String word) 
    {
        if (word.equals("")){
            return isWord;
        }

        char ch = word.charAt(0);
        if (!children.containsKey(ch)) return false;
        Trie child = children.get(ch);
        return child.contains(word.substring(1));

    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) 
    {
        if (prefix.equals("")){
            return true;
        }

        char ch = prefix.charAt(0);
        if (!children.containsKey(ch)) return false;
        Trie child = children.get(ch);
        return child.contains(prefix.substring(1));

    }
}