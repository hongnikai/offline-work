package com.lc.match;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lc 5/15/21 9:34 AM
 */
public class Solution {

    public class TrieTree{
        TrieTree[] next;
        String end;

        public TrieTree(){
            next = new TrieTree[26];
            end = null;
        }
    }
    public List<String> findWords(char[][] board, String[] words) {
        List<String> ans = new ArrayList<>();

        if(board.length == 0 || board[0].length == 0) return ans;

        TrieTree root = new TrieTree();
        TrieTree p = root;

        // 构建前缀树
        int n = words.length;
        for(int i = 0; i < n; i++) {
            p = root;
            for(int j = 0; j < words[i].length(); j++) {
                int ind = words[i].charAt(j) - 'a';
                if(p.next[ind] == null) p.next[ind] = new TrieTree();
                p = p.next[ind];
            }
            p.end = words[i];
        }

        System.out.println(p.end);

        n = board.length;
        int m = board[0].length;
        is = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(root.next[board[i][j] - 'a'] != null) {
                    check(root, board, i, j, ans, n, m);
                }
            }
        }

        return ans;
    }

    boolean[][] is;

    private void check(TrieTree root, char[][] board, int i, int j, List<String> ans, int n, int m) {
        if(i < 0 || j < 0 || i >= n || j >= m || is[i][j] || root.next[board[i][j] - 'a'] == null) return;
        int ind = board[i][j] - 'a';
        if(root.next[ind].end != null) {
            ans.add(root.next[ind].end);
            root.next[ind].end = null;
        }
        is[i][j] = true;
        check(root.next[ind], board, i + 1, j, ans, n, m);
        check(root.next[ind], board, i - 1, j, ans, n, m);
        check(root.next[ind], board, i, j + 1, ans, n, m);
        check(root.next[ind], board, i, j - 1, ans, n, m);
        is[i][j] = false;
    }

}
