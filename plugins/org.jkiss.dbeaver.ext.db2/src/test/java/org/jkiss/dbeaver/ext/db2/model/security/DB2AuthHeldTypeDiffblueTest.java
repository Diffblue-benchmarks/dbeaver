package org.jkiss.dbeaver.ext.db2.model.security;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2AuthHeldTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DB2AuthHeldType#getName()}
   *   <li>{@link DB2AuthHeldType#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DB2AuthHeldType.getName()", "String DB2AuthHeldType.toString()"})
  public void testGettersAndSetters() {
    // Arrange
    DB2AuthHeldType valueOfResult = DB2AuthHeldType.valueOf("Y");

    // Act
    String actualName = valueOfResult.getName();

    // Assert
    assertEquals("Y", actualName);
    assertEquals("Y", valueOfResult.toString());
  }
}
