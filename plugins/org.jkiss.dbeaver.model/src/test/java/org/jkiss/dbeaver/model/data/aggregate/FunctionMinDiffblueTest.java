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

public class FunctionMinDiffblueTest {
  /**
   * Test {@link FunctionMin#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link FunctionMin} (default constructor) {@link FunctionMin#result} doubleValue is
   *       forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMin#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMin.accumulate(Object, boolean)"})
  public void testAccumulate_when42_thenFunctionMinResultDoubleValueIsFortyTwo() {
    // Arrange
    FunctionMin functionMin = new FunctionMin();

    // Act
    boolean actualAccumulateResult = functionMin.accumulate("42", false);

    // Assert
    assertEquals(42.0d, ((Double) functionMin.result).doubleValue(), 0.0);
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMin#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link FunctionMin} (default constructor) {@link FunctionMin#result} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMin#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMin.accumulate(Object, boolean)"})
  public void testAccumulate_when42_thenFunctionMinResultIs42() {
    // Arrange
    FunctionMin functionMin = new FunctionMin();

    // Act
    boolean actualAccumulateResult = functionMin.accumulate("42", true);

    // Assert
    assertEquals("42", functionMin.result);
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMin#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then {@link FunctionMin} (default constructor) {@link FunctionMin#result} intValue is
   *       forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMin#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMin.accumulate(Object, boolean)"})
  public void testAccumulate_whenFortyTwo_thenFunctionMinResultIntValueIsFortyTwo() {
    // Arrange
    FunctionMin functionMin = new FunctionMin();

    // Act
    boolean actualAccumulateResult = functionMin.accumulate(42, false);

    // Assert
    assertEquals(42, ((Integer) functionMin.result).intValue());
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMin#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link FunctionMin} (default constructor) {@link FunctionMin#result} is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMin#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMin.accumulate(Object, boolean)"})
  public void testAccumulate_whenNull_thenFunctionMinResultIsNull() {
    // Arrange
    FunctionMin functionMin = new FunctionMin();

    // Act
    boolean actualAccumulateResult = functionMin.accumulate(null, false);

    // Assert
    assertNull(functionMin.result);
    assertFalse(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMin#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then {@link FunctionMin} (default constructor) {@link FunctionMin#result} is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMin#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMin.accumulate(Object, boolean)"})
  public void testAccumulate_whenRename_thenFunctionMinResultIsNull() {
    // Arrange
    FunctionMin functionMin = new FunctionMin();

    // Act
    boolean actualAccumulateResult = functionMin.accumulate(DBPEvent.RENAME, true);

    // Assert
    assertNull(functionMin.result);
    assertFalse(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMin#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then {@link FunctionMin} (default constructor) {@link FunctionMin#result} is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMin#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMin.accumulate(Object, boolean)"})
  public void testAccumulate_whenRename_thenFunctionMinResultIsNull2() {
    // Arrange
    FunctionMin functionMin = new FunctionMin();

    // Act
    boolean actualAccumulateResult = functionMin.accumulate(DBPEvent.RENAME, false);

    // Assert
    assertNull(functionMin.result);
    assertFalse(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMin#getResult(int)}.
   *
   * <p>Method under test: {@link FunctionMin#getResult(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FunctionMin.getResult(int)"})
  public void testGetResult() {
    // Arrange, Act and Assert
    assertNull(new FunctionMin().getResult(3));
  }

  /**
   * Test new {@link FunctionMin} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link FunctionMin}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FunctionMin.<init>()"})
  public void testNewFunctionMin() {
    // Arrange, Act and Assert
    assertNull(new FunctionMin().result);
  }
}
