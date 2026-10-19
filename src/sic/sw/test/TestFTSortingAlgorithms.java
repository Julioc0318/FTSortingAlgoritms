package sic.sw.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import sic.sw.src.ft.CannotBeSortedException;
import sic.sw.src.ft.NVersionProgramming;
import sic.sw.src.ft.RecoveryBlock;

/**
 * TestFTSortingAlgorithms.
 */
public class TestFTSortingAlgorithms {

  // TODO: Completar basándose en los casos de test dados en
  // TestSingleSortingAlgorithm.java
  // Hay que tener en cuenta tanto las situaciones sin fallo, como las situaciones
  // con fallo tolerado
  // y las situaciones de avería. En estas últimas el algoritmo de ordenación
  // devuelve la excepción CannotBeSortedException
  // El alumno debe investigar cómo capturar la excepción en un caso de test con
  // JUnit y usar esa información para implementar
  // los casos de test en los que se verifique la avería del algorithmo.

}
