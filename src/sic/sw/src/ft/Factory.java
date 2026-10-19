package sic.sw.src.ft;

import sic.sw.src.basic.AbstractSortingAlgorithm;
import sic.sw.src.basic.Heapsort;
import sic.sw.src.basic.Mergesort;
import sic.sw.src.basic.Quicksort;

/**
 * Factory.
 */
public final class Factory {

  /**
   * Private constructor to prevent instantiation of utility class.
   */
  private Factory() {
    throw new UnsupportedOperationException("Utility class cannot be instantiated");
  }

  /**
   * Type of sorting algorithms.
   */
  public enum Type {
    QUICKSORT, HEAPSORT, MERGESORT
  }

  /**
   * AbstractSortingAlgorithm 实例化.
   *
   * @param type.
   * @return Sort Function.
   */
  public static AbstractSortingAlgorithm newAlgorithm(Type type) {
    return switch (type) {
      case QUICKSORT -> new Quicksort();
      case HEAPSORT -> new Heapsort();
      case MERGESORT -> new Mergesort();
      default -> null;
    };

  }

  /**
   * AbstractSortingAlgorithm.
   *
   * @return algos.clone()
   */
  public static AbstractSortingAlgorithm[] newAlgorithmSet() {
    int numberOfSortAlgos = 3;
    AbstractSortingAlgorithm[] algos = new AbstractSortingAlgorithm[numberOfSortAlgos];
    algos[0] = newAlgorithm(Factory.Type.QUICKSORT);
    algos[1] = newAlgorithm(Factory.Type.HEAPSORT);
    algos[2] = newAlgorithm(Factory.Type.MERGESORT);
    return algos.clone();
  }

}
