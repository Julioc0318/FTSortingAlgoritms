package sic.sw.src.ft;

import sic.sw.src.basic.AbstractSortingAlgorithm;

/**
 * NVersionProgramming.
 */
public class NVersionProgramming extends FTAbstractSortingAlgorithm {

  public NVersionProgramming() {
    super();
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
    // 1. Antes de ordernar, hacer una copia de input datos - 排序前备份原始输入数据.
    int[] originalInput = this.getData();
    // 2. Datos vacios, return directamente - 如果数据为空则直接返回.
    if (originalInput.length == 0) {
      return;
    }
    // Obtener las versiones - 获取所有版本.
    AbstractSortingAlgorithm[] versions = this.getVersions();

    // 3. Crear un hilo para cada versión y empezar - 为每个版本创建一个线程并启动.
    Hilo[] hilos = new Hilo[versions.length];
    for (int i = 0; i < versions.length; i++) {
      versions[i].setData(originalInput);
      hilos[i] = new Hilo();
      hilos[i].setVersion(versions[i]);
      hilos[i].start();
    }

    // 3.1. Esperar a que terminen todos los hilos - 等待所有线程结束.
    for (Hilo hilo : hilos) {
      try {
        hilo.join();
      } catch (InterruptedException e) {
        // El hilo fue interrumpido - 线程被中断.
        throw new CannotBeSortedException("Hilo interrumpido.");
      }
    }

    // 4. Votar: si dos resultados son iguales, es la mayoría - 表决：两个结果相同即为多数.
    for (int i = 0; i < versions.length; i++) {
      for (int j = i + 1; j < versions.length; j++) {
        int[] a = versions[i].getData();
        int[] b = versions[j].getData();
        if (sonIguales(a, b)) {
          // Guardar el resultado y salir - 保存结果并退出.
          this.setData(a);
          return;
        }
      }
    }

    // 5. No hay mayoría, lanzar excepción - 没有多数，抛出异常.
    throw new CannotBeSortedException("No hay mayoría.");
  }

  /**
   * Comparar dos arrays - 比较两个数组是否相同.
   */
  private boolean sonIguales(int[] a, int[] b) {
    // Si la longitud es diferente, no son iguales - 长度不同则不相同.
    if (a.length != b.length) {
      return false;
    }
    // Comparar elemento por elemento - 逐个元素比较.
    for (int i = 0; i < a.length; i++) {
      if (a[i] != b[i]) {
        return false;
      }
    }
    // Todos los elementos son iguales - 所有元素都相同.
    return true;
  }

  /**
   * Returns the name of the sorting method.
   */
  @Override
  public String getName() {
    return "NVersionProgramming";
  }
}
