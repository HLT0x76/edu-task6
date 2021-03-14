package com.netcracker.edu;

public class ArraysMultiplier {

  public static int[][] multiply(int[][] a, int[][] b) {
    int r1 = a.length;
    int c1 = a[0].length;
    int c2 = b[0].length;
    int[][] mul = new int[r1][c2];
    for (int i = 0; i < r1; i++) {
      for (int j = 0; j < c2; j++) {
        for (int k = 0; k < c1; k++) {
          mul[i][j] += a[i][k] * b[k][j];
        }
      }
    }
    return mul;
  }
}
