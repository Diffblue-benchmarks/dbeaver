package org.jkiss.dbeaver.ext.db2.model.module;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2ModuleTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DB2ModuleType#getName()}
   *   <li>{@link DB2ModuleType#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DB2ModuleType.getName()", "String DB2ModuleType.toString()"})
  public void testGettersAndSetters() {
    // Arrange
    DB2ModuleType valueOfResult = DB2ModuleType.valueOf("A");

    // Act
    String actualName = valueOfResult.getName();

    // Assert
    assertEquals("Alias", actualName);
    assertEquals("Alias", valueOfResult.toString());
  }
}
