package com.lc.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSearch {

	/**
	 * 单词搜索，本方法按照竞赛题目要求完成
	 * @param board 给定的m x n 二维字符网格
	 * @param word 给定的单词字符串
	 * @return 单词word在board中的坐标序列（坐标值从0开始）
	 */
	public int[][] findWord(char[][] board, String word) {
		int[][] result ={};
		List<Map<Integer,Integer>> list = new ArrayList<>();
		int n=0;
		char[] wchar = word.toCharArray();
		for (int k=0;k<wchar.length;k++){
			for (int i =0;i<board.length;i++){
				char[] boa = board[i];
				n = i;
				for (int j=0;j<boa.length;j++){
					if(boa[j]==wchar[k]){
						System.out.println(n+","+j);
						Map<Integer,Integer> map = new HashMap<>();
						map.put(n,j);
						list.add(map);
					}
				}
			}
		}
		return result;
	}

	static char[][] board = {{'a','b','c','d'},{'e','f','g','h'},{'i','j','k','l'},{'m','n','o','p'},{'q','r','s','t'},{'u','v','w','x'},{'y','z'}};

	static String[] words = {"oath","pea","eat","rain"};

	public static void main(String[] args) {
		String word = "rain";
		WordSearch wordSearch = new WordSearch();
		wordSearch.findWord(board,word);
	}




}
