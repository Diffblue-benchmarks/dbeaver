package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLStateDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLState#getCode()}
   *   <li>{@link SQLState#getDescription()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLState.getCode()", "String SQLState.getDescription()"})
  public void testGettersAndSetters() {
    // Arrange
    SQLState valueOfResult = SQLState.valueOf("SQL_00000");

    // Act
    String actualCode = valueOfResult.getCode();

    // Assert
    assertEquals("00000", actualCode);
    assertEquals("Success", valueOfResult.getDescription());
  }
}
