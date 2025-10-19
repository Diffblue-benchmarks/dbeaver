package org.jkiss.dbeaver.model.impl.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCStructImplDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCStructImpl#JDBCStructImpl(String, Object[], String)}
   *   <li>{@link JDBCStructImpl#getAttributes()}
   *   <li>{@link JDBCStructImpl#getSQLTypeName()}
   *   <li>{@link JDBCStructImpl#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCStructImpl.<init>(String, Object[], String)",
    "Object[] JDBCStructImpl.getAttributes()",
    "String JDBCStructImpl.getSQLTypeName()",
    "String JDBCStructImpl.toString()"
  })
  public void testGettersAndSetters() throws SQLException {
    // Arrange
    Object[] attributes = new Object[] {"Attributes"};

    // Act
    JDBCStructImpl actualJdbcStructImpl = new JDBCStructImpl("Type Name", attributes, "42");
    Object[] actualAttributes = actualJdbcStructImpl.getAttributes();
    String actualSQLTypeName = actualJdbcStructImpl.getSQLTypeName();

    // Assert
    assertEquals("42", actualJdbcStructImpl.toString());
    assertEquals("Attributes", actualAttributes[0]);
    assertEquals("Type Name", actualSQLTypeName);
    assertEquals(1, actualAttributes.length);
    assertSame(attributes, actualAttributes);
  }

  /**
   * Test {@link JDBCStructImpl#getAttributes(Map)} with {@code Map}.
   *
   * <p>Method under test: {@link JDBCStructImpl#getAttributes(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] JDBCStructImpl.getAttributes(Map)"})
  public void testGetAttributesWithMap() throws SQLException {
    // Arrange
    Object[] attributes = new Object[] {"Attributes"};
    JDBCStructImpl jdbcStructImpl = new JDBCStructImpl("Type Name", attributes, "42");

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcStructImpl.getAttributes(new HashMap<>()));
  }
}
