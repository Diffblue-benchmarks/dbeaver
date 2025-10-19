package org.jkiss.dbeaver.ext.db2.model.fed;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2NicknameRemoteTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DB2NicknameRemoteType#getName()}
   *   <li>{@link DB2NicknameRemoteType#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DB2NicknameRemoteType.getName()",
    "String DB2NicknameRemoteType.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DB2NicknameRemoteType valueOfResult = DB2NicknameRemoteType.valueOf("A");

    // Act
    String actualName = valueOfResult.getName();

    // Assert
    assertEquals("Alias", actualName);
    assertEquals("Alias", valueOfResult.toString());
  }
}
