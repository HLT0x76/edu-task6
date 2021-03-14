package com.netcracker.edu;

import java.util.concurrent.Callable;

public class Worker implements Callable<WorkerResult> {

  private final int[][] a;
  private final int[][] b;
  private final int row;
  private final int[] result;

  Worker(int[][] a, int[][] b, int row) {
    this.a = a;
    this.b = b;
    this.row = row;
    this.result = new int[b[0].length];
  }

  @Override
  public WorkerResult call() {
    for (int i = 0; i < b[0].length; i++) {
      for (int j = 0; j < a[row].length; j++) {
        result[i] += a[row][j] * b[j][i];
      }
    }
    return new WorkerResult(result, row);
  }
}
