package org.jkiss.dbeaver.model.struct.rdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSProcedureParameterKindDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSProcedureParameterKind#getTitle()}
   *   <li>{@link DBSProcedureParameterKind#isInput()}
   *   <li>{@link DBSProcedureParameterKind#isOutput()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBSProcedureParameterKind.getTitle()",
    "boolean DBSProcedureParameterKind.isInput()",
    "boolean DBSProcedureParameterKind.isOutput()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBSProcedureParameterKind valueOfResult = DBSProcedureParameterKind.valueOf("UNKNOWN");

    // Act
    String actualTitle = valueOfResult.getTitle();
    boolean actualIsInputResult = valueOfResult.isInput();

    // Assert
    assertEquals("Unknown", actualTitle);
    assertFalse(actualIsInputResult);
    assertFalse(valueOfResult.isOutput());
  }
}
