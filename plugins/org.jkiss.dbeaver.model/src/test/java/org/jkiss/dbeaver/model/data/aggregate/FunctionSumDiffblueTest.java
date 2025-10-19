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

public class FunctionSumDiffblueTest {
  /**
   * Test {@link FunctionSum#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then {@link FunctionSum} (default constructor) {@link FunctionSum#result} is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionSum#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionSum.accumulate(Object, boolean)"})
  public void testAccumulate_whenFortyTwo_thenFunctionSumResultIsFortyTwo() {
    // Arrange
    FunctionSum functionSum = new FunctionSum();

    // Act
    boolean actualAccumulateResult = functionSum.accumulate(42, true);

    // Assert
    assertEquals(42.0d, functionSum.result, 0.0);
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionSum#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionSum#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionSum.accumulate(Object, boolean)"})
  public void testAccumulate_whenRename_thenReturnFalse() {
    // Arrange
    FunctionSum functionSum = new FunctionSum();

    // Act and Assert
    assertFalse(functionSum.accumulate(DBPEvent.RENAME, true));
    assertEquals(Double.NaN, functionSum.result, 0.0);
  }

  /**
   * Test {@link FunctionSum#getResult(int)}.
   *
   * <ul>
   *   <li>Given {@link FunctionSum} (default constructor).
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionSum#getResult(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FunctionSum.getResult(int)"})
  public void testGetResult_givenFunctionSum_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new FunctionSum().getResult(3));
  }

  /**
   * Test {@link FunctionSum#getResult(int)}.
   *
   * <ul>
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionSum#getResult(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FunctionSum.getResult(int)"})
  public void testGetResult_thenReturnDoubleValueIsFortyTwo() {
    // Arrange
    FunctionSum functionSum = new FunctionSum();
    functionSum.accumulate(42, true);

    // Act and Assert
    assertEquals(42.0d, ((Double) functionSum.getResult(3)).doubleValue(), 0.0);
  }

  /**
   * Test new {@link FunctionSum} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link FunctionSum}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FunctionSum.<init>()"})
  public void testNewFunctionSum() {
    // Arrange, Act and Assert
    assertEquals(Double.NaN, new FunctionSum().result, 0.0);
  }
}
