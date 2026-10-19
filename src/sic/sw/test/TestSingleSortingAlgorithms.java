/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2015-2025 Universitat Politècnica de València
 *  Dependable and Secure Computer-based Systems
 *  David de Andrés and Juan Carlos Ruiz
 *  mailto: ddandres AT disca DOT upv DOT es / jcruizg AT disca DOT upv DOT es
 *
 */

package sic.sw.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sic.sw.src.basic.AbstractSortingAlgorithm;
import sic.sw.src.basic.Heapsort;
import sic.sw.src.basic.Mergesort;
import sic.sw.src.basic.Quicksort;
import sic.sw.src.ft.CannotBeSortedException;

/**
 * TestSingleSortingAlgorithms.
 */
public class TestSingleSortingAlgorithms {

  static Stream<Arguments> dataProvider() {
    return Stream.of(Arguments.of(null, new int[] {}, "Test null array"),
        Arguments.of(new int[] {}, new int[] {}, "Test empty array"),
        Arguments.of(new int[] { 0 }, new int[] { 0 }, "Test array with a single element"),
        Arguments.of(new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 },
            new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 },
            "Test array with an even number of elements"),
        Arguments.of(new int[] { 9, 8, 7, 6, 5, 4, 3, 2, 1 },
            new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, "Test array with an odd number of elements"),
        Arguments.of(new int[] { 1, 2, 3, 4, 5, 6 }, new int[] { 1, 2, 3, 4, 5, 6 },
            "Test array already sorted"),
        Arguments.of(new int[] { 5, 5, 5 }, new int[] { 5, 5, 5 },
            "Test array with equal elements"));
  }

  @ParameterizedTest(name = "[{index}] {2}]")
  @DisplayName("Test Quicksort")
  @MethodSource("dataProvider")
  void testQuicksort(int[] data, int[] result, String title) throws CannotBeSortedException {
    AbstractSortingAlgorithm algorithm = new Quicksort();
    algorithm.setData(data);
    algorithm.setFaultyBehavior(false);
    algorithm.sort();
    assertArrayEquals(result, algorithm.getData());
  }

  @Test
  @DisplayName("Test Quicksort name")
  void testQuicksortName() {
    AbstractSortingAlgorithm algorithm = new Quicksort();
    assertEquals("Quicksort", algorithm.getName());
  }

  @Test
  @DisplayName("Test Quicksort fails")
  void testQuicksortFails() throws CannotBeSortedException {
    AbstractSortingAlgorithm algorithm = new Quicksort();
    algorithm.setData(new int[] { 1, 2, 3, 4, 5 });
    algorithm.setFaultyBehavior(true);
    algorithm.sort();
    assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, algorithm.getData());
  }

  @ParameterizedTest(name = "[{index}] {2}]")
  @DisplayName("Test Mergesort")
  @MethodSource("dataProvider")
  void testMergesort(int[] data, int[] result, String title) throws CannotBeSortedException {
    AbstractSortingAlgorithm algorithm = new Mergesort();
    algorithm.setData(data);
    algorithm.setFaultyBehavior(false);
    algorithm.sort();
    assertArrayEquals(result, algorithm.getData());
  }

  @Test
  @DisplayName("Test Mergesort name")
  void testMergesortName() {
    AbstractSortingAlgorithm algorithm = new Mergesort();
    assertEquals("Mergesort", algorithm.getName());
  }

  @Test
  @DisplayName("Test Mergesort fails")
  void testMergesortFails() throws CannotBeSortedException {
    AbstractSortingAlgorithm algorithm = new Mergesort();
    algorithm.setData(new int[] { 1, 2, 3, 4, 5 });
    algorithm.setFaultyBehavior(true);
    algorithm.sort();
    assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, algorithm.getData());
  }

  @ParameterizedTest(name = "[{index}]{2}]")
  @DisplayName("Test Heapsort")
  @MethodSource("dataProvider")
  void testHeapsort(int[] data, int[] result, String title) throws CannotBeSortedException {
    AbstractSortingAlgorithm algorithm = new Heapsort();
    algorithm.setData(data);
    algorithm.setFaultyBehavior(false);
    algorithm.sort();
    assertArrayEquals(result, algorithm.getData());
  }

  @Test
  @DisplayName("Test Heapsort name but it fails")
  void testHeapsortName() {
    AbstractSortingAlgorithm algorithm = new Heapsort();
    assertEquals("Quicksort", algorithm.getName());
  }

  @Test
  @DisplayName("Test Heapsort fails")
  void testHeapsortFails() throws CannotBeSortedException {
    AbstractSortingAlgorithm algorithm = new Heapsort();
    algorithm.setData(new int[] { 1, 2, 3, 4, 5 });
    algorithm.setFaultyBehavior(true);
    algorithm.sort();
    assertArrayEquals(new int[] { 1, 2, 3, 4, 5 }, algorithm.getData());
  }

}
