package org.jkiss.dbeaver.ext.oracle.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ext.oracle.model.OracleSchema.SpecialPosition;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleSchemaDiffblueTest {
  /**
   * Test SpecialPosition getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SpecialPosition#SpecialPosition(String, int)}
   *   <li>{@link SpecialPosition#getColumn()}
   *   <li>{@link SpecialPosition#getPos()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SpecialPosition.<init>(String, int)",
    "String SpecialPosition.getColumn()",
    "int SpecialPosition.getPos()"
  })
  public void testSpecialPositionGettersAndSetters() {
    // Arrange and Act
    SpecialPosition actualSpecialPosition = new SpecialPosition("Column", 1);
    String actualColumn = actualSpecialPosition.getColumn();

    // Assert
    assertEquals("Column", actualColumn);
    assertEquals(1, actualSpecialPosition.getPos());
  }

  /**
   * Test SpecialPosition {@link SpecialPosition#SpecialPosition(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return Column is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SpecialPosition#SpecialPosition(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SpecialPosition.<init>(String)"})
  public void testSpecialPositionNewSpecialPosition_when42_thenReturnColumnIs42() {
    // Arrange and Act
    SpecialPosition actualSpecialPosition = new SpecialPosition("42");

    // Assert
    assertEquals("42", actualSpecialPosition.getColumn());
    assertEquals(0, actualSpecialPosition.getPos());
  }

  /**
   * Test SpecialPosition {@link SpecialPosition#SpecialPosition(String)}.
   *
   * <ul>
   *   <li>When {@code ::42}.
   *   <li>Then return Column is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SpecialPosition#SpecialPosition(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SpecialPosition.<init>(String)"})
  public void testSpecialPositionNewSpecialPosition_when42_thenReturnColumnIsEmptyString() {
    // Arrange and Act
    SpecialPosition actualSpecialPosition = new SpecialPosition("::42");

    // Assert
    assertEquals("", actualSpecialPosition.getColumn());
    assertEquals(0, actualSpecialPosition.getPos());
  }

  /**
   * Test SpecialPosition {@link SpecialPosition#SpecialPosition(String)}.
   *
   * <ul>
   *   <li>When {@code :42}.
   *   <li>Then return Pos is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link SpecialPosition#SpecialPosition(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SpecialPosition.<init>(String)"})
  public void testSpecialPositionNewSpecialPosition_when42_thenReturnPosIsFortyTwo() {
    // Arrange and Act
    SpecialPosition actualSpecialPosition = new SpecialPosition(":42");

    // Assert
    assertEquals("", actualSpecialPosition.getColumn());
    assertEquals(42, actualSpecialPosition.getPos());
  }

  /**
   * Test SpecialPosition {@link SpecialPosition#SpecialPosition(String)}.
   *
   * <ul>
   *   <li>When {@code foo:bar}.
   *   <li>Then return Column is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link SpecialPosition#SpecialPosition(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SpecialPosition.<init>(String)"})
  public void testSpecialPositionNewSpecialPosition_whenFooBar_thenReturnColumnIsFoo() {
    // Arrange and Act
    SpecialPosition actualSpecialPosition = new SpecialPosition("foo:bar");

    // Assert
    assertEquals("foo", actualSpecialPosition.getColumn());
    assertEquals(0, actualSpecialPosition.getPos());
  }
}
