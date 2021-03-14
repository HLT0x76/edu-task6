package com.netcracker.edu;

/**
 * Wrapper object, that's
 * return result and row for a worker,
 * which unties us from tracking "currentRow"
 */
public class WorkerResult {
  private final int[] result;
  private final int row;

  WorkerResult(int[] result, int row) {
    this.result = result;
    this.row = row;
  }

  public int[] getResult() {
    return result;
  }

  public int getRow() {
    return row;
  }
}
