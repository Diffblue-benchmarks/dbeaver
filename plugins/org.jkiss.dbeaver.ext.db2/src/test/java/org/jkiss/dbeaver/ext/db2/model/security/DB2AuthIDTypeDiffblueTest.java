package org.jkiss.dbeaver.ext.db2.model.security;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2AuthIDTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DB2AuthIDType#getName()}
   *   <li>{@link DB2AuthIDType#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DB2AuthIDType.getName()", "String DB2AuthIDType.toString()"})
  public void testGettersAndSetters() {
    // Arrange
    DB2AuthIDType valueOfResult = DB2AuthIDType.valueOf("U");

    // Act
    String actualName = valueOfResult.getName();

    // Assert
    assertEquals("User", actualName);
    assertEquals("User", valueOfResult.toString());
  }
}
