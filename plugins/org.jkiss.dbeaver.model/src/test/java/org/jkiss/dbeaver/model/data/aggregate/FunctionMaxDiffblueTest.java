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

public class FunctionMaxDiffblueTest {
  /**
   * Test {@link FunctionMax#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link FunctionMax} (default constructor) {@link FunctionMax#result} doubleValue is
   *       forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMax#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMax.accumulate(Object, boolean)"})
  public void testAccumulate_when42_thenFunctionMaxResultDoubleValueIsFortyTwo() {
    // Arrange
    FunctionMax functionMax = new FunctionMax();

    // Act
    boolean actualAccumulateResult = functionMax.accumulate("42", false);

    // Assert
    assertEquals(42.0d, ((Double) functionMax.result).doubleValue(), 0.0);
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMax#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link FunctionMax} (default constructor) {@link FunctionMax#result} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMax#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMax.accumulate(Object, boolean)"})
  public void testAccumulate_when42_thenFunctionMaxResultIs42() {
    // Arrange
    FunctionMax functionMax = new FunctionMax();

    // Act
    boolean actualAccumulateResult = functionMax.accumulate("42", true);

    // Assert
    assertEquals("42", functionMax.result);
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMax#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then {@link FunctionMax} (default constructor) {@link FunctionMax#result} intValue is
   *       forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMax#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMax.accumulate(Object, boolean)"})
  public void testAccumulate_whenFortyTwo_thenFunctionMaxResultIntValueIsFortyTwo() {
    // Arrange
    FunctionMax functionMax = new FunctionMax();

    // Act
    boolean actualAccumulateResult = functionMax.accumulate(42, false);

    // Assert
    assertEquals(42, ((Integer) functionMax.result).intValue());
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMax#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link FunctionMax} (default constructor) {@link FunctionMax#result} is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMax#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMax.accumulate(Object, boolean)"})
  public void testAccumulate_whenNull_thenFunctionMaxResultIsNull() {
    // Arrange
    FunctionMax functionMax = new FunctionMax();

    // Act
    boolean actualAccumulateResult = functionMax.accumulate(null, false);

    // Assert
    assertNull(functionMax.result);
    assertFalse(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMax#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then {@link FunctionMax} (default constructor) {@link FunctionMax#result} is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMax#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMax.accumulate(Object, boolean)"})
  public void testAccumulate_whenRename_thenFunctionMaxResultIsNull() {
    // Arrange
    FunctionMax functionMax = new FunctionMax();

    // Act
    boolean actualAccumulateResult = functionMax.accumulate(DBPEvent.RENAME, true);

    // Assert
    assertNull(functionMax.result);
    assertFalse(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMax#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then {@link FunctionMax} (default constructor) {@link FunctionMax#result} is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMax#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMax.accumulate(Object, boolean)"})
  public void testAccumulate_whenRename_thenFunctionMaxResultIsNull2() {
    // Arrange
    FunctionMax functionMax = new FunctionMax();

    // Act
    boolean actualAccumulateResult = functionMax.accumulate(DBPEvent.RENAME, false);

    // Assert
    assertNull(functionMax.result);
    assertFalse(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMax#getResult(int)}.
   *
   * <p>Method under test: {@link FunctionMax#getResult(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FunctionMax.getResult(int)"})
  public void testGetResult() {
    // Arrange, Act and Assert
    assertNull(new FunctionMax().getResult(3));
  }

  /**
   * Test new {@link FunctionMax} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link FunctionMax}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FunctionMax.<init>()"})
  public void testNewFunctionMax() {
    // Arrange, Act and Assert
    assertNull(new FunctionMax().result);
  }
}
