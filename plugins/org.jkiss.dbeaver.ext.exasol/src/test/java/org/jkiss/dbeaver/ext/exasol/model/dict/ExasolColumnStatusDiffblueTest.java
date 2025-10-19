package org.jkiss.dbeaver.ext.exasol.model.dict;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExasolColumnStatusDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ExasolColumnStatus#getName()}
   *   <li>{@link ExasolColumnStatus#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ExasolColumnStatus.getName()", "String ExasolColumnStatus.toString()"})
  public void testGettersAndSetters() {
    // Arrange
    ExasolColumnStatus valueOfResult = ExasolColumnStatus.valueOf("OUTDATED");

    // Act
    String actualName = valueOfResult.getName();

    // Assert
    assertEquals("Column definition is outdated", actualName);
    assertEquals("Column definition is outdated", valueOfResult.toString());
  }
}
