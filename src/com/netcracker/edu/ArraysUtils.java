package com.netcracker.edu;

import java.util.Random;

public class ArraysUtils {

  public static int[][] generateArrays(int rows, int columns, int maxValue) {
    int[][] result = new int[rows][columns];
    Random random = new Random();

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        result[i][j] = random.nextInt(maxValue);
      }
    }
    return result;
  }

  public static void printArray(String message, int[][] x) {
    System.out.println(message);
    for (int[] row : x) {
      for (int column : row) {
        System.out.print(column + "\t");
      }
      System.out.println();
    }
  }

  public static boolean isArraysCanBeMultiplied(int[][] a, int[][] b) {
    int c1 = a[0].length;
    int r2 = b.length;
    return (c1 == r2);
  }
}
