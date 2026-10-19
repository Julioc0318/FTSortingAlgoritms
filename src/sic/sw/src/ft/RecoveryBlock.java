package sic.sw.src.ft;

import sic.sw.src.basic.AbstractSortingAlgorithm;

/**
 * RecoveryBlock.
 */
public class RecoveryBlock extends FTAbstractSortingAlgorithm {

  /**
   * La activación del constructor de FTAbstractSortingAlgorithm ya instancia.
   * Las versiones de los algoritmos de ordenación necesarias (revisar código).
   */
  public RecoveryBlock() {
    super();
  }

  /**
   * Sorts the array of integers.
   *
   * @throws CannotBeSortedException if an error is detected during operation.
   */
  @Override
  public void sort() {
    // 1. Antes de ordernar, hacer una copia de input datos - 排序前备份原始输入数据.
    int[] originalInput = this.getData();
    // 2. Datos vacios, return directamente - 如果数据为空则直接返回.
    if (originalInput == null || originalInput.length == 0) {
      return;
    }
    // 3. Clone originalInput - 克隆原始输入.
    int[] originalInputClone = originalInput.clone();
    // 4. For cada algoritmo.
    for (AbstractSortingAlgorithm algorithm : this.getVersions()) {
      // 5. Protejer.
      try {
        // 6. Clone otra vez originalInput, y poner en setData - 再次克隆原始输入.
        algorithm.setData(originalInputClone.clone());
        // 7. Sort.
        algorithm.sort();
        // 8. Ejecutar la prueba de aceptación - 执行测试.
        // 8.1. Si la prueba de aceptación es exitosa, poner en setData de this y salir
        // del método - 测试成功则退出.
        if (acceptanceTest(this.getData(), originalInput)) {
          setData(this.getData());
          return;
        } else {
          continue;
        }
      } catch (Exception e) {
        continue;
      }
    }
    // 9. Si todo falla, lanzar una excepción - 如果所有算法都失败则抛出异常.
    throw new CannotBeSortedException("Todos los algoritmos de ordenación fallaron.");
  }

  /**
   * Acceptance test to verify if the sorted data matches the original data.
   *
   * @param sortedData   The sorted array of integers.
   * @param originalData The original array of integers.
   * @return true if the sorted data matches the original data, false otherwise.
   */
  private boolean acceptanceTest(int[] sortedData, int[] originalData) {
    if (sortedData == null || sortedData.length == 0 || sortedData.length != originalData.length) {
      return false;
    }
    for (int i = 0; i < sortedData.length - 1; i++) {
      if (sortedData[i] > sortedData[i + 1]) {
        return false;
      }
    }
    return true;
  }

  /**
   * Returns the name of the sorting method.
   */
  @Override
  public String getName() {
    return "RecoveryBlock";
  }

}
