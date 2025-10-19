package org.jkiss.dbeaver.model.impl.jdbc;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCArrayImplDiffblueTest {
  /**
   * Test {@link JDBCArrayImpl#getResultSet(long, int)} with {@code index}, {@code count}.
   *
   * <p>Method under test: {@link JDBCArrayImpl#getResultSet(long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.sql.ResultSet JDBCArrayImpl.getResultSet(long, int)"})
  public void testGetResultSetWithIndexCount() throws SQLException {
    // Arrange
    Object[] items = new Object[] {"Items"};

    // Act and Assert
    assertNull(new JDBCArrayImpl("Type Name", 1, items).getResultSet(1L, 3));
  }

  /**
   * Test {@link JDBCArrayImpl#getResultSet(long, int, Map)} with {@code index}, {@code count},
   * {@code map}.
   *
   * <p>Method under test: {@link JDBCArrayImpl#getResultSet(long, int, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.sql.ResultSet JDBCArrayImpl.getResultSet(long, int, Map)"})
  public void testGetResultSetWithIndexCountMap() throws SQLException {
    // Arrange
    Object[] items = new Object[] {"Items"};
    JDBCArrayImpl jdbcArrayImpl = new JDBCArrayImpl("Type Name", 1, items);

    // Act and Assert
    assertNull(jdbcArrayImpl.getResultSet(1L, 3, new HashMap<>()));
  }

  /**
   * Test {@link JDBCArrayImpl#getResultSet(Map)} with {@code map}.
   *
   * <p>Method under test: {@link JDBCArrayImpl#getResultSet(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.sql.ResultSet JDBCArrayImpl.getResultSet(Map)"})
  public void testGetResultSetWithMap() throws SQLException {
    // Arrange
    Object[] items = new Object[] {"Items"};
    JDBCArrayImpl jdbcArrayImpl = new JDBCArrayImpl("Type Name", 1, items);

    // Act and Assert
    assertNull(jdbcArrayImpl.getResultSet(new HashMap<>()));
  }
}
