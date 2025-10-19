package org.jkiss.dbeaver.model.data.aggregate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FunctionModeDiffblueTest {
  /**
   * Test {@link FunctionMode#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then {@link FunctionMode} (default constructor) fourth Result doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMode#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMode.accumulate(Object, boolean)"})
  public void testAccumulate_when42_thenFunctionModeFourthResultDoubleValueIsFortyTwo() {
    // Arrange
    FunctionMode functionMode = new FunctionMode();

    // Act
    boolean actualAccumulateResult = functionMode.accumulate("42", true);

    // Assert
    assertEquals(42.0d, ((Double) functionMode.getResult(3)).doubleValue(), 0.0);
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMode#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then {@link FunctionMode} (default constructor) fourth Result intValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMode#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMode.accumulate(Object, boolean)"})
  public void testAccumulate_whenFortyTwo_thenFunctionModeFourthResultIntValueIsFortyTwo() {
    // Arrange
    FunctionMode functionMode = new FunctionMode();

    // Act
    boolean actualAccumulateResult = functionMode.accumulate(42, true);

    // Assert
    assertEquals(42, ((Integer) functionMode.getResult(3)).intValue());
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMode#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link FunctionMode} (default constructor) fourth Result is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMode#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMode.accumulate(Object, boolean)"})
  public void testAccumulate_whenNull_thenFunctionModeFourthResultIsNull() {
    // Arrange
    FunctionMode functionMode = new FunctionMode();

    // Act
    boolean actualAccumulateResult = functionMode.accumulate(null, true);

    // Assert
    assertNull(functionMode.getResult(3));
    assertFalse(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionMode#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then {@link FunctionMode} (default constructor) fourth Result is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionMode#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionMode.accumulate(Object, boolean)"})
  public void testAccumulate_whenRename_thenFunctionModeFourthResultIsRename() {
    // Arrange
    FunctionMode functionMode = new FunctionMode();
    Object object = DBPEvent.RENAME;

    // Act and Assert
    assertTrue(functionMode.accumulate(object, true));
    assertSame(object, functionMode.getResult(3));
  }

  /**
   * Test {@link FunctionMode#getResult(int)}.
   *
   * <p>Method under test: {@link FunctionMode#getResult(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FunctionMode.getResult(int)"})
  public void testGetResult() {
    // Arrange, Act and Assert
    assertNull(new FunctionMode().getResult(3));
  }
}
