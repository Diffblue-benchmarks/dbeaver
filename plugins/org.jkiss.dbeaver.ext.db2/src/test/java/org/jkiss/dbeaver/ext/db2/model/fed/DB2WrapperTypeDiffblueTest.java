package org.jkiss.dbeaver.ext.db2.model.fed;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DB2WrapperTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DB2WrapperType#getName()}
   *   <li>{@link DB2WrapperType#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DB2WrapperType.getName()", "String DB2WrapperType.toString()"})
  public void testGettersAndSetters() {
    // Arrange
    DB2WrapperType valueOfResult = DB2WrapperType.valueOf("N");

    // Act
    String actualName = valueOfResult.getName();

    // Assert
    assertEquals("Non Relational", actualName);
    assertEquals("Non Relational", valueOfResult.toString());
  }
}
