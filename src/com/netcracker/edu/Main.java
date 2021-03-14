package com.netcracker.edu;

public class Main {

  public static void main(String[] args) {

    int c1 = 2500, r1 = 2500;
    int c2 = 2500, r2 = 2500;
    int maxValueInArray = 1000;
    long startTime = 0L;

    final int[][] a = ArraysUtils.generateArrays(c1, r1, maxValueInArray);
    final int[][] b = ArraysUtils.generateArrays(c2, r2, maxValueInArray);
    // set true if you want to check result array output
    final boolean printArrays = false;
    int[][] result;

    if (!ArraysUtils.isArraysCanBeMultiplied(a, b)) {
      System.out.println("Arrays a and b can't be multiplied");
      System.exit(1);
    }

    System.out.print("Without threading: ");
    startTime = System.currentTimeMillis();
    result = ArraysMultiplier.multiply(a, b);
    System.out.printf("%d ms\n", System.currentTimeMillis() - startTime);
    if (printArrays) {
      ArraysUtils.printArray("Result: ", result);
    }

    // On r1 > 1500 system may seem unresponsive for quite a while
    System.out.print("With threading (naive): ");
    startTime = System.currentTimeMillis();
    result = ArraysMultiplierRowPerThread.multiply(a, b);
    System.out.printf("%d ms\n", System.currentTimeMillis() - startTime);
    if (printArrays) {
      ArraysUtils.printArray("Result: ", result);
    }

    System.out.print("With threading (batches): ");
    startTime = System.currentTimeMillis();
    result = ArraysMultiplierBatchPerThread.multiply(a, b);
    System.out.printf("%d ms\n", System.currentTimeMillis() - startTime);
    if (printArrays) {
      ArraysUtils.printArray("Result: ", result);
    }
  }
}
