package org.jkiss.dbeaver.model.impl.jdbc;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCUnknownTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCUnknownType#JDBCUnknownType(int, Object)}
   *   <li>{@link JDBCUnknownType#toString()}
   *   <li>{@link JDBCUnknownType#getType()}
   *   <li>{@link JDBCUnknownType#getValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUnknownType.<init>(int, Object)",
    "int JDBCUnknownType.getType()",
    "Object JDBCUnknownType.getValue()",
    "String JDBCUnknownType.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    JDBCUnknownType actualJdbcUnknownType = new JDBCUnknownType(1, "Value");
    String actualToStringResult = actualJdbcUnknownType.toString();
    int actualType = actualJdbcUnknownType.getType();

    // Assert
    assertEquals("Unsupported JDBC type: 1", actualToStringResult);
    assertEquals("Value", actualJdbcUnknownType.getValue());
    assertEquals(1, actualType);
  }
}
