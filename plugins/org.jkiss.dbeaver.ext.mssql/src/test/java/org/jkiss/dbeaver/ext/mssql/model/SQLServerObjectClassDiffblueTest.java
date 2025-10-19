package org.jkiss.dbeaver.ext.mssql.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerObjectClassDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLServerObjectClass#getClassId()}
   *   <li>{@link SQLServerObjectClass#getClassName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int SQLServerObjectClass.getClassId()",
    "java.lang.String SQLServerObjectClass.getClassName()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLServerObjectClass valueOfResult = SQLServerObjectClass.valueOf("DATABASE");

    // Act
    int actualClassId = valueOfResult.getClassId();

    // Assert
    assertEquals("Database", valueOfResult.getClassName());
    assertEquals(0, actualClassId);
  }
}
