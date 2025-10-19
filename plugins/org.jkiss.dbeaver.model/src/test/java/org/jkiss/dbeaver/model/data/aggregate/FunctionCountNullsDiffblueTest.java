package org.jkiss.dbeaver.model.data.aggregate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.data.DBDDocumentXML;
import org.jkiss.dbeaver.model.impl.data.DBDValueError;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FunctionCountNullsDiffblueTest {
  /**
   * Test {@link FunctionCountNulls#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBDDocumentXML#DBDDocumentXML(Document)} with document is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionCountNulls#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionCountNulls.accumulate(Object, boolean)"})
  public void testAccumulate_whenDBDDocumentXMLWithDocumentIsNull() {
    // Arrange
    FunctionCountNulls functionCountNulls = new FunctionCountNulls();

    // Act
    boolean actualAccumulateResult = functionCountNulls.accumulate(new DBDDocumentXML(null), true);

    // Assert
    assertEquals(1L, ((Long) functionCountNulls.getResult(3)).longValue());
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionCountNulls#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBDValueError#DBDValueError(Throwable)} with error is {@link
   *       Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link FunctionCountNulls#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionCountNulls.accumulate(Object, boolean)"})
  public void testAccumulate_whenDBDValueErrorWithErrorIsThrowable() {
    // Arrange
    FunctionCountNulls functionCountNulls = new FunctionCountNulls();

    // Act
    boolean actualAccumulateResult =
        functionCountNulls.accumulate(new DBDValueError(new Throwable()), true);

    // Assert
    assertEquals(0L, ((Long) functionCountNulls.getResult(3)).longValue());
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionCountNulls#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link FunctionCountNulls} (default constructor) fourth Result longValue is one.
   * </ul>
   *
   * <p>Method under test: {@link FunctionCountNulls#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionCountNulls.accumulate(Object, boolean)"})
  public void testAccumulate_whenNull_thenFunctionCountNullsFourthResultLongValueIsOne() {
    // Arrange
    FunctionCountNulls functionCountNulls = new FunctionCountNulls();

    // Act
    boolean actualAccumulateResult = functionCountNulls.accumulate(null, true);

    // Assert
    assertEquals(1L, ((Long) functionCountNulls.getResult(3)).longValue());
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionCountNulls#accumulate(Object, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then {@link FunctionCountNulls} (default constructor) fourth Result longValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link FunctionCountNulls#accumulate(Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FunctionCountNulls.accumulate(Object, boolean)"})
  public void testAccumulate_whenRename_thenFunctionCountNullsFourthResultLongValueIsZero() {
    // Arrange
    FunctionCountNulls functionCountNulls = new FunctionCountNulls();

    // Act
    boolean actualAccumulateResult = functionCountNulls.accumulate(DBPEvent.RENAME, true);

    // Assert
    assertEquals(0L, ((Long) functionCountNulls.getResult(3)).longValue());
    assertTrue(actualAccumulateResult);
  }

  /**
   * Test {@link FunctionCountNulls#getResult(int)}.
   *
   * <p>Method under test: {@link FunctionCountNulls#getResult(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object FunctionCountNulls.getResult(int)"})
  public void testGetResult() {
    // Arrange, Act and Assert
    assertEquals(0L, ((Long) new FunctionCountNulls().getResult(3)).longValue());
  }
}
