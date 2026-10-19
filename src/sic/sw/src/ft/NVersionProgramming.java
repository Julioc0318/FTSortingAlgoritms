package sic.sw.src.ft;

import sic.sw.src.basic.AbstractSortingAlgorithm;

public class NVersionProgramming extends FTAbstractSortingAlgorithm {

  public NVersionProgramming() {
    super(); // La activación del constructor de FTAbstractSortingAlgorithm ya instancia
             // las versiones de los algoritmos de ordenación necesarias (revisar código)
  }

  /**
   * La clase hilo permite ejecutar la ordenación en un hilo de ejecución
   * independiente. En el hilo principal, sobre la instancia de Hilo: - Activar la
   * ejecución del hilo con start() - Esperar a que termine la ejecución del hilo
   * con join()
   * 
   * @throws CannotBeSortedException if an error is detected during operation.
   */
  public class Hilo extends Thread {
    AbstractSortingAlgorithm version = null;

    public void setVersion(AbstractSortingAlgorithm v) {
      version = v;
    }

    public AbstractSortingAlgorithm getVersion() {
      return version;
    }

    @Override
    public void run() {
      if (version != null) {
        version.sort();
      }
    }
  }

  /**
   * Sorts the array of integers.
   *
   * @throws CannotBeSortedException if an error is detected during operation.
   */
  @Override
  public void sort() {
    // TODO : Recordar que en el caso del NVersionProgramming no existen los test de
    // aceptación,
    // sino que se vota el resultado mayoritario
  }

  /**
   * Returns the name of the sorting method.
   */
  @Override
  public String getName() {
    return "NVersionProgramming";
  }
}
