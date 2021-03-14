package com.netcracker.edu;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ArraysMultiplierRowPerThread {

  public static int[][] multiply(int[][] a, int[][] b) {
    // a[MxN] * b[NxP] = c[MxP]
    //   r c      r c      r c
    int r1 = a.length;
    int c2 = b[0].length;
    int[][] mul = new int[r1][c2];
    ExecutorService executorService = Executors.newCachedThreadPool();
    ArrayList<Future<WorkerResult>> results = new ArrayList<>();
    for (int r = 0; r < r1; r++) {
      results.add(executorService.submit(new Worker(a, b, r)));
    }
    Future<WorkerResult> fs;
    int doneCount = 0;
    while (doneCount < results.size()) {
      for (int r = results.size() - 1; r >= 0; r--) {
        fs = results.get(r);
        try {
          mul[fs.get().getRow()] = fs.get().getResult();
          results.remove(fs);
          doneCount++;
        } catch (InterruptedException | ExecutionException e) {
          e.printStackTrace();
        }
      }
    }
    executorService.shutdown();
    return mul;
  }

}