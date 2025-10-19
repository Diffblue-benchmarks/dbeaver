package org.jkiss.dbeaver.ext.exasol.model.dict;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExasolScriptResultTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ExasolScriptResultType#getName()}
   *   <li>{@link ExasolScriptResultType#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String ExasolScriptResultType.getName()",
    "String ExasolScriptResultType.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ExasolScriptResultType valueOfResult = ExasolScriptResultType.valueOf("TABLE");

    // Act
    String actualName = valueOfResult.getName();

    // Assert
    assertEquals("Table", actualName);
    assertEquals("Table", valueOfResult.toString());
  }
}
