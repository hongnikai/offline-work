package com.lc.learn.java.arraylist;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lc 2021-01-15 15:03
 */
public class ArrayListTest {

    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        names.add("tom");
        names.add("jack");
        names = names.subList(0, 1);
        System.out.println(names);

        int[][] matrix = {{1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        boolean numberIn2DArray = findNumberIn2DArray(matrix, 5);
        System.out.println(numberIn2DArray);

    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        boolean flag = false;

        int rows = matrix.length,columns = matrix[0].length;
        int row = 0, column = columns - 1;

        while (row < rows && column >= 0) {
            int num = matrix[row][column];
            if (num == target) {
                return true;
            } else if (num > target) {
                column--;
            } else {
                row++;
            }
        }
        return false;

    }


}
