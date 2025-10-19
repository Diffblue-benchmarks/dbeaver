package org.jkiss.dbeaver.ext.exasol.model.dict;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExasolScriptLanguageDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ExasolScriptLanguage#getName()}
   *   <li>{@link ExasolScriptLanguage#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String ExasolScriptLanguage.getName()",
    "String ExasolScriptLanguage.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ExasolScriptLanguage valueOfResult = ExasolScriptLanguage.valueOf("R");

    // Act
    String actualName = valueOfResult.getName();

    // Assert
    assertEquals("R", actualName);
    assertEquals("R", valueOfResult.toString());
  }
}
