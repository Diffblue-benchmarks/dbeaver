package org.jkiss.dbeaver.model.data.aggregate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FunctionMedianDiffblueTest {
  /**
   * Test {@link FunctionMedian#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link FunctionMedian} (default constructor) fourth Result doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMedian#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMedian.accumulate(Object, boolean)"})
  public void testAccumulate_when42_thenFunctionMedianFourthResultDoubleValueIsFortyTwo() {
    // Arrange
    FunctionMedian functionMedian = new FunctionMedian();

    // Act
    boolean actualAccumulateResult = functionMedian.accumulate("42", false);

    // Assert
    assertEquals(42.0d, ((Double) functionMedian.getResult(3)).doubleValue(), 0.0);
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMedian#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link FunctionMedian} (default constructor) fourth Result is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMedian#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMedian.accumulate(Object, boolean)"})
  public void testAccumulate_when42_thenFunctionMedianFourthResultIs42() {
    // Arrange
    FunctionMedian functionMedian = new FunctionMedian();

    // Act
    boolean actualAccumulateResult = functionMedian.accumulate("42", true);

    // Assert
    assertEquals("42", functionMedian.getResult(3));
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMedian#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then {@link FunctionMedian} (default constructor) fourth Result intValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMedian#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMedian.accumulate(Object, boolean)"})
  public void testAccumulate_whenFortyTwo_thenFunctionMedianFourthResultIntValueIsFortyTwo() {
    // Arrange
    FunctionMedian functionMedian = new FunctionMedian();

    // Act
    boolean actualAccumulateResult = functionMedian.accumulate(42, false);

    // Assert
    assertEquals(42, ((Integer) functionMedian.getResult(3)).intValue());
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMedian#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMedian#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMedian.accumulate(Object, boolean)"})
  public void testAccumulate_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new FunctionMedian().accumulate(null, false));
  }

  /**
   * Test {@link FunctionMedian#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMedian#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMedian.accumulate(Object, boolean)"})
  public void testAccumulate_whenRename_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new FunctionMedian().accumulate(DBPEvent.RENAME, true));
  }

  /**
   * Test {@link FunctionMedian#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMedian#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMedian.accumulate(Object, boolean)"})
  public void testAccumulate_whenRename_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse(new FunctionMedian().accumulate(DBPEvent.RENAME, false));
  }
}
