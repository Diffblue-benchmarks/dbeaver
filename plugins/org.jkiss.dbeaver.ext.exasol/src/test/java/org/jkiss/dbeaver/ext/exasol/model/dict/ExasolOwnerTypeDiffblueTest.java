package org.jkiss.dbeaver.ext.exasol.model.dict;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExasolOwnerTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ExasolOwnerType#getName()}
   *   <li>{@link ExasolOwnerType#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ExasolOwnerType.getName()", "String ExasolOwnerType.toString()"})
  public void testGettersAndSetters() {
    // Arrange
    ExasolOwnerType valueOfResult = ExasolOwnerType.valueOf("S");

    // Act
    String actualName = valueOfResult.getName();

    // Assert
    assertEquals("System", actualName);
    assertEquals("System", valueOfResult.toString());
  }
}
