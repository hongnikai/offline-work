package com.lc.match;

import java.util.HashMap;

public class HarmoniouslySequence {
	
	
	/**
	 * 查找最长和谐子序列的长度，本方法按照竞赛题目要求完成
	 * @param nums 给定的整数数组
	 * @return 最长和谐子序列的长度
	 */
	public int maxHarmoniouslySequence(int[] nums) {
		int max=0;//最大和谐序列长度
		HashMap<Integer,Integer> map = new HashMap<>();
		for(int num:nums){
			if(map.containsKey(num)){
				map.put(num,map.get(num)+1);
			}else{
				map.put(num,1);
			}
		}
		for(Integer num:map.keySet()){
			int value = map.get(num);
			if(map.containsKey(num+1)){
				int value2 = map.get(num+1);
				if(value+value2>max){
					max = value+value2;
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		int[] nums = {1,3,2,2,5,2,3,7};
		HarmoniouslySequence sequence = new HarmoniouslySequence();
		int i = sequence.maxHarmoniouslySequence(nums);
		System.out.println(i);

	}

}
