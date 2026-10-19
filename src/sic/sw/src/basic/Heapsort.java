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

/**
 * Orders the data array in an iterative manner. Although somewhat slower in
 * practice on most machines than a well-implemented quicksort, it has the
 * advantage of a more favorable worst-case O(n log n) runtime.
 */
public class Heapsort extends AbstractSortingAlgorithm {

  /**
   * Constant to work with half the array.
   */
  private static final int TWO = 2;

  @Override
  public void sort() {
    final int[] data = getData();
    heapSort(data, data.length);
    setData(data);

    // Emulate the occurrence of a fault if required
    if (this.getFaultyBehavior()) {
      this.injectFault();
    }
  }

  /**
   * Orders the data in an iterative manner. First, a heap is built and then a
   * sorted array is created by repeatedly removing the largest element from the
   * heap. The heap is updated to maintain the heap property.
   *
   * @param array The array to be sorted.
   * @param count Size of the portion of the array storing the heap.
   */
  private static void heapSort(final int[] array, final int count) {
    heapify(array, count);

    int end = count - 1;
    while (end > 0) {
      swap(array, end, 0);
      end--;
      siftDown(array, 0, end);
    }
  }

  /**
   * Builds a heap from an array of elements.
   *
   * @param array The array to be heapified.
   * @param count Size of the array to translate into heap form.
   */
  private static void heapify(final int[] array, final int count) {
    int start = (count - TWO) / TWO;

    while (start >= 0) {
      siftDown(array, start, count - 1);
      start--;
    }

  }

  /**
   * Repairs the heap, assuming that heaps rooted at its children are valid.
   *
   * @param array The array containing the heap to be repaired.
   * @param start Index of the root element.
   * @param end   Index of the last element.
   */
  private static void siftDown(final int[] array, final int start, final int end) {
    int root = start;

    int child;
    int swap;
    while ((TWO * root) + 1 <= end) {
      child = TWO * root + 1;
      swap = root;

      if (array[swap] < array[child]) {
        swap = child;
      }
      if (child + 1 <= end && array[swap] < array[child + 1]) {
        swap = child + 1;
      }
      if (swap == root) {
        return;
      } else {
        swap(array, root, swap);
        root = swap;
      }
    }
  }

  /**
   * Swaps the position of two elements within the data array.
   *
   * @param array         The array containing the elements to be swapped.
   * @param firstElement  The index of the first element within the data array.
   * @param secondElement The index of the second element within the data array.
   */
  private static void swap(final int[] array, final int firstElement, final int secondElement) {
    final int temp = array[firstElement];
    array[firstElement] = array[secondElement];
    array[secondElement] = temp;
  }

  @Override
  public String getName() {
    return "Heapsort";
  }

}
