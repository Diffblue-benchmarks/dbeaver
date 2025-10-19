package org.jkiss.dbeaver.tools.transfer.database;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseMappingTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DatabaseMappingType#isAttrOnly()}
   *   <li>{@link DatabaseMappingType#isValid()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DatabaseMappingType.isAttrOnly()",
    "boolean DatabaseMappingType.isValid()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DatabaseMappingType valueOfResult = DatabaseMappingType.valueOf("unspecified");

    // Act
    boolean actualIsAttrOnlyResult = valueOfResult.isAttrOnly();

    // Assert
    assertFalse(actualIsAttrOnlyResult);
    assertFalse(valueOfResult.isValid());
  }
}
