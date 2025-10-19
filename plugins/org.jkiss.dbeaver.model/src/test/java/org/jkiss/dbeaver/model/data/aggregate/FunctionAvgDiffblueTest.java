package org.jkiss.dbeaver.model.data.aggregate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FunctionAvgDiffblueTest {
  /**
   * Test {@link FunctionAvg#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then {@link FunctionAvg} (default constructor) {@link FunctionAvg#result} is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionAvg#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionAvg.accumulate(Object, boolean)"})
  public void testAccumulate_whenFortyTwo_thenFunctionAvgResultIsFortyTwo() {
    // Arrange
    FunctionAvg functionAvg = new FunctionAvg();

    // Act
    boolean actualAccumulateResult = functionAvg.accumulate(42, true);

    // Assert
    assertEquals(42.0d, functionAvg.result, 0.0);
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionAvg#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionAvg#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionAvg.accumulate(Object, boolean)"})
  public void testAccumulate_whenRename_thenReturnFalse() {
    // Arrange
    FunctionAvg functionAvg = new FunctionAvg();

    // Act and Assert
    assertFalse(functionAvg.accumulate(DBPEvent.RENAME, true));
    assertEquals(Double.NaN, functionAvg.result, 0.0);
  }

  /**
   * Test {@link FunctionAvg#getResult(int)}.
   *
   * <ul>
   *   <li>Given {@link FunctionAvg} (default constructor).
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionAvg#getResult(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FunctionAvg.getResult(int)"})
  public void testGetResult_givenFunctionAvg_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new FunctionAvg().getResult(3));
  }

  /**
   * Test {@link FunctionAvg#getResult(int)}.
   *
   * <ul>
   *   <li>Then return doubleValue is fourteen.
   * </ul>
   *
   * <p>Method under test: {@link FunctionAvg#getResult(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FunctionAvg.getResult(int)"})
  public void testGetResult_thenReturnDoubleValueIsFourteen() {
    // Arrange
    FunctionAvg functionAvg = new FunctionAvg();
    functionAvg.accumulate(42, true);

    // Act and Assert
    assertEquals(14.0d, ((Double) functionAvg.getResult(3)).doubleValue(), 0.0);
  }

  /**
   * Test new {@link FunctionAvg} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link FunctionAvg}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FunctionAvg.<init>()"})
  public void testNewFunctionAvg() {
    // Arrange, Act and Assert
    assertEquals(Double.NaN, new FunctionAvg().result, 0.0);
  }
}
