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

import java.security.SecureRandom;
import java.util.Arrays;
import sic.sw.src.ft.CannotBeSortedException;

/**
 * Stores an array of integers that can be sorted by implementing the abstract
 * sort method. The occurrence of a fault during the sort operation can be
 * emulated by calling the injectFault() method.
 */
public abstract class AbstractSortingAlgorithm {

  /**
   * Array with a single element.
   */
  private static final int SINGLE_ELEMENT = 1;

  /**
   * Constant to subtract to the length of the array and access the element before
   * the last.
   */
  private static final int TWO = 2;

  /**
   * Array of integers to be sorted.
   */
  private int[] data;

  /**
   * Array of booleans stating whether each of the available sorting methods is
   * faulty.
   */
  private boolean faultyBehavior;

  /**
   * Secure random number generator. It is only used to inject faults in the
   * sorted array.
   */
  private SecureRandom random;

  /**
   * Constructs an AbstractSortingAlgorithm with no integer array to be sorted.
   */
  protected AbstractSortingAlgorithm() {
    // No initialization required
    random = new SecureRandom();
    faultyBehavior = false;
    this.data = null;
  }

  /**
   * Makes a copy of the integer array to be sorted.
   *
   * @param array The array to be copied. Null arrays are treated as being empty.
   */
  public void setData(final int[] array) {

    if (array == null) {
      this.data = new int[] {};
    } else {
      // Make a copy of the numbers to be sorted
      this.data = Arrays.copyOf(array, array.length);
    }
  }

  /**
   * Returns a copy of the array of integer numbers in its current state (sorted
   * or not). If null then returns an empty array.
   *
   * @return The array of integer numbers.
   */
  public int[] getData() {
    int[] result;
    if (this.data == null) {
      result = new int[] {};
    } else {
      result = Arrays.copyOf(this.data, this.data.length);
    }
    return result;
  }

  /**
   * Makes a copy of the boolean array that configures whether each of the
   * internally used SortingAlgorithms will fail (true) or not.
   *
   * @param faulty The array to be copied.
   */
  public void setFaultyBehavior(final boolean faulty) {
    this.faultyBehavior = faulty;
  }

  /**
   * Returns a copy of the array of boolean values that configures whether each of
   * the internally used SortingAlgorithms will fail (true) or not. If null then
   * returns an empty array.
   *
   * @return The array of boolean values.
   */
  public boolean getFaultyBehavior() {
    return this.faultyBehavior;
  }

  /**
   * Emulates the occurrence of an error while sorting the array of integers. It
   * should be called just before ending the sort() method if the selected sorting
   * method is faulty.
   */
  public void injectFault() {

    if (this.data.length == 0) {
      // Empty arrays turn into one integer arrays
      this.data = new int[] { random.nextInt(0, Integer.MAX_VALUE) };
    } else if (this.data.length == SINGLE_ELEMENT) {
      // One integer arrays just change its value
      final int value = this.data[0];
      int tmp = value;
      while (tmp == value) {
        tmp = random.nextInt(0, Integer.MAX_VALUE);
      }
      this.data[0] = tmp;
    } else {
      // Arrays with more than one element change the maximum and minimum values
      this.data[0] = random.nextInt(this.data[random.nextInt(1, this.data.length)],
          Integer.MAX_VALUE);
      this.data[this.data.length - 1] = random.nextInt(Integer.MIN_VALUE,
          this.data[this.data.length - TWO]);
    }
  }

  /**
   * Sorts the array of integers.
   *
   * @throws CannotBeSortedException if an error is detected during operation.
   */
  public abstract void sort();

  /**
   * Returns the name of the sorting method.
   */
  public abstract String getName();

}
