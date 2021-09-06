package com.lc.match;

public class FruitJuicing {

	/**
	 * 最短榨汁时间，本方法按照竞赛题目要求完成
	 * @param appleJuicingCostTime 每榨50克苹果所需的时间（秒）
	 * @param appleWeights 需要榨汁的苹果重量数组
	 * @param pearJuicingCostTime 每榨50克梨子所需的时间（秒）
	 * @param pearWeights 需要榨汁的梨子重量数组
	 * @return 将所有水果榨成汁的最少时间（秒）
	 */
	public int minJuicingTime(int appleJuicingCostTime, int[] appleWeights, int pearJuicingCostTime, int[] pearWeights) {
		int appleSeconds = 0;
		int pearSeconds = 0;
		for (int apple:appleWeights){
			appleSeconds += apple/appleJuicingCostTime;
		}
		for (int pear:pearWeights){
			pearSeconds += pear/pearJuicingCostTime;
		}
		return appleSeconds+pearSeconds;
	}

	public static void main(String[] args) {
		FruitJuicing fruitJuicing = new FruitJuicing();
		int[] appleWeights = {100,200};
		int[] pearWeights = {100,200};
		int time = fruitJuicing.minJuicingTime(10, appleWeights, 20, pearWeights);
		System.out.println(time+"秒");

	}

}
