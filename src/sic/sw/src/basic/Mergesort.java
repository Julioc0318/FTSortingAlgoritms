/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2015-2025 Universitat Politècnica de València
 *  Dependable and Secure Computer-based Systems
 *  David de Andrés and Juan Carlos Ruiz
 *  mailto: ddandres AT disca DOT upv DOT es / jcruizg AT disca DOT upv DOT es
 *
 */

package sic.sw.src.basic;

import java.util.Arrays;

/**
 * Efficient general-purpose, comparison-based sorting algorithm. In sorting n
 * objects, merge sort has an average and worst-case performance of O(n log n).
 */
public class Mergesort extends AbstractSortingAlgorithm {

  /**
   * Minimum size of arrays to be sorted is 1.
   */
  private static final int MIN_SIZE = 1;

  /**
   * Constant to split the array in half.
   */
  private static final int TWO = 2;

  @Override
  public void sort() {
    final int[] data = getData();
    setData(mergesort(data));

    // Emulate the occurrence of a fault if required
    if (this.getFaultyBehavior()) {
      this.injectFault();
    }
  }

  /**
   * Divides the unsorted array into two equally sized sub-arrays recursively. An
   * array of one element is considered sorted. Repeatedly merge sub-arrays to
   * produce sorted sub-arrays, until only one sub-array remains. This is the
   * sorted array.
   *
   * @param array The sub-array to be sorted.
   */
  private static int[] mergesort(final int[] array) {

    int[] leftArray = Arrays.copyOfRange(array, 0, array.length / TWO);
    int[] rightArray = Arrays.copyOfRange(array, array.length / TWO, array.length);

    // Arrays of 1 element are already sorted
    if (leftArray.length > MIN_SIZE) {
      leftArray = mergesort(leftArray);
    }
    if (rightArray.length > MIN_SIZE) {
      rightArray = mergesort(rightArray);
    }

    return merge(leftArray, rightArray);
  }

  /**
   * Merges two sub-arrays into a single sorted sub-array.
   *
   * @param leftArray  The first ordered sub-array.
   * @param rightArray The second ordered sub-array.
   * @return The sorted array resulting from merging leftArray and rightArray.
   */
  private static int[] merge(final int[] leftArray, final int[] rightArray) {
    // Merge left and right array into the result array
    final var resultArray = new int[leftArray.length + rightArray.length];

    var leftIndex = 0;
    var rightIndex = 0;
    var resultIndex = 0;
    // Go through left and right arrays until reaching the end of one of them
    while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
      if (leftArray[leftIndex] > rightArray[rightIndex]) {
        resultArray[resultIndex] = rightArray[rightIndex];
        rightIndex++;
      } else {
        resultArray[resultIndex] = leftArray[leftIndex];
        leftIndex++;
      }
      resultIndex++;
    }
    // Copy the rest of the left array into the result array
    System.arraycopy(leftArray, leftIndex, resultArray, resultIndex, leftArray.length - leftIndex);
    resultIndex = resultIndex + leftArray.length - leftIndex;
    // Copy the rest of the right array into the result array
    System.arraycopy(rightArray, rightIndex, resultArray, resultIndex,
        rightArray.length - rightIndex);

    return resultArray;
  }

  @Override
  public String getName() {
    return "Mergesort";
  }

}
