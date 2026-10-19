package sic.sw.src.ft;

import sic.sw.src.basic.AbstractSortingAlgorithm;

public class RecoveryBlock extends FTAbstractSortingAlgorithm {

  public RecoveryBlock() {
    super(); // La activación del constructor de FTAbstractSortingAlgorithm ya instancia
             // las versiones de los algoritmos de ordenación necesarias (revisar código)

  }

  /**
   * Sorts the array of integers.
   *
   * @throws CannotBeSortedException if an error is detected during operation.
   */
  @Override
  public void sort() {
    // TODO: Recordar que en el caso del RecoveryBlock tras cada ordenación
    // se ejecutará una prueba de aceptación que servirá para determinar si
    // el resultado es correcto o no.
  }

  /**
   * Returns the name of the sorting method.
   */
  @Override
  public String getName() {
    return "RecoveryBlock";
  }

}
