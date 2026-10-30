package sic.sw.test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import sic.sw.src.ft.CannotBeSortedException;
import sic.sw.src.ft.FTAbstractSortingAlgorithm;
import sic.sw.src.ft.NVersionProgramming;
import sic.sw.src.ft.RecoveryBlock;

public class TestFTSortingAlgorithms {

  static Stream<Arguments> ftDataProvider() {
    boolean[][] faultScenarios = { { false, false, false }, // 0 个故障
        { true, false, false }, // 1 个故障 (3 种位置)
        { false, true, false }, { false, false, true }, { true, true, false }, // 2 个故障 (3 种位置)
        { true, false, true }, { false, true, true }, { true, true, true } // 3 个故障
    };

    String[] algoTypes = { "RecoveryBlock", "NVersionProgramming" };

    return Stream.of(algoTypes).flatMap(algoType -> Stream.of(faultScenarios).flatMap(faults ->
    // 复用已有数据源，组合故障配置和算法类型
    TestSingleSortingAlgorithms.dataProvider().map(arg -> {
      Object[] dataArgs = arg.get();
      int[] inputData = (int[]) dataArgs[0];
      int[] expectedData = (int[]) dataArgs[1];
      String title = (String) dataArgs[2];
      return Arguments.of(inputData, expectedData, title, faults, algoType);
    })));
  }

  @ParameterizedTest(name = "[{index}] {4} | {2} | Faults: {3}")
  @DisplayName("Test FT Algorithms with Reused Data Provider")
  @MethodSource("ftDataProvider")
  void testFTAlgorithms(int[] data, int[] expected, String title, boolean[] faults, String algoType)
      throws CannotBeSortedException {

    // 1. 统计当前故障配置中的 fallo 数量
    int faultCount = 0;
    for (boolean f : faults) {
      if (f) {
        faultCount++;
      }
    }

    // 2. 实例化对应算法并根据故障数自动推导理论崩溃边界
    FTAbstractSortingAlgorithm algorithm;
    boolean expectException;

    if ("RecoveryBlock".equals(algoType)) {
      algorithm = new RecoveryBlock();
      // RecoveryBlock: 仅当 3 个算法全挂时抛出异常
      expectException = (faultCount == 3);
    } else {
      algorithm = new NVersionProgramming();
      // NVersionProgramming: 挂 2 个或以上时失去多数派，抛出异常
      expectException = (faultCount >= 2);
    }

    algorithm.setData(data);
    algorithm.setFaultyBehavior(faults);

    // 3. 自动断言逻辑
    if (expectException) {
      // 仅对非空且非空数组断言异常（null 或空数组在算法逻辑中会提前安全返回）
      if (data != null && data.length > 0) {
        assertThrows(CannotBeSortedException.class, () -> algorithm.sort());
      }
    } else {
      algorithm.sort();
      assertArrayEquals(expected, algorithm.getData());
    }
  }
}