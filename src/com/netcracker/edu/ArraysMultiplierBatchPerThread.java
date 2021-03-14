package com.netcracker.edu;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ArraysMultiplierBatchPerThread {
  private final static int THREADS = 16;

  public static int[][] multiply(int[][] a, int[][] b) {
    // a[MxN] * b[NxP] = c[MxP]
    //   r c      r c      r c
    int r1 = a.length;
    int c2 = b[0].length;
    int[][] mul = new int[r1][c2];
    ExecutorService executorService = Executors.newCachedThreadPool();

    int currentRow;
    int batches = r1 / THREADS;
    for (int i = 0; i < batches; i++) {
      ArrayList<Future<WorkerResult>> batch = new ArrayList<>();
      for (int r = 0; r < THREADS; r++) {
        currentRow = i * THREADS + r;
        batch.add(executorService.submit(new Worker(a, b, currentRow)));
      }
      Future<WorkerResult> fs;
      int doneCount = 0;
      while (doneCount < batch.size()) {
        for (int r = batch.size() - 1; r >= 0; r--) {
          fs = batch.get(r);
          try {
            mul[fs.get().getRow()] = fs.get().getResult();
            batch.remove(fs);
            doneCount++;
          } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
          }
        }
      }
    }

    if (r1 % THREADS != 0) {
      ArrayList<Future<WorkerResult>> batch = new ArrayList<>();
      for (int r = 0; r < (r1 % THREADS); r++) {
        currentRow = r + batches * 8;
        batch.add(executorService.submit(new Worker(a, b, currentRow)));
      }
      Future<WorkerResult> fs;
      int doneCount = 0;
      while (doneCount < batch.size()) {
        for (int r = batch.size() - 1; r >= 0; r--) {
          fs = batch.get(r);
          try {
            mul[fs.get().getRow()] = fs.get().getResult();
            batch.remove(fs);
            doneCount++;
          } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
          }
        }
      }
    }
    executorService.shutdown();
    return mul;
  }

}