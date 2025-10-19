package org.jkiss.dbeaver.ext.db2.model.security;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2GrantorGranteeTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DB2GrantorGranteeType#getName()}
   *   <li>{@link DB2GrantorGranteeType#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DB2GrantorGranteeType.getName()",
    "String DB2GrantorGranteeType.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DB2GrantorGranteeType valueOfResult = DB2GrantorGranteeType.valueOf("S");

    // Act
    String actualName = valueOfResult.getName();

    // Assert
    assertEquals("System", actualName);
    assertEquals("System", valueOfResult.toString());
  }
}
