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
 * Orders the data array in a recursive manner. On average, the algorithm takes
 * O(n log n) comparisons to sort n items. In the worst case, it makes O(n^2)
 * comparisons, though this behavior is rare.
 */
public class Quicksort extends AbstractSortingAlgorithm {

  @Override
  public void sort() {
    final int[] data = getData();
    quicksort(data, 0, data.length - 1);
    setData(data);

    // Emulate the occurrence of a fault if required
    if (this.getFaultyBehavior()) {
      this.injectFault();
    }
  }

  /**
   * Orders the data array in a recursive manner. First it divides the array into
   * two smaller sub-arrays (low elements and high elements). Then it recursively
   * orders the sub-arrays.
   *
   * @param array The array to be sorted.
   * @param low   The index of the left element within the sub-array.
   * @param high  The index of the right elements within the sub-array.
   */
  private static void quicksort(final int[] array, final int low, final int high) {
    if (low < high) {
      final int pivotLocation = partition(array, low, high);
      quicksort(array, low, pivotLocation - 1);
      quicksort(array, pivotLocation + 1, high);
    }
  }

  /**
   * Reorders elements of the array to position those greater than the pivot
   * before it, and those smaller than the pivot after it.
   *
   * @param array The array to be sorted.
   * @param low   The index of the left element within the array.
   * @param high  The index of the right element within the array.
   * @return The index of the pivot.
   */
  private static int partition(final int[] array, final int low, final int high) {
    int leftwall = low;

    for (int i = low + 1; i <= high; i++) {
      if (array[i] < array[low]) {
        leftwall++;
        swap(array, i, leftwall);
      }
    }
    swap(array, low, leftwall);

    return leftwall;
  }

  /**
   * Swaps the position of two elements within the data array.
   *
   * @param array         The array containing the data to be swapped.
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
    return "Quicksort";
  }

}
