package org.jkiss.dbeaver.model.impl.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.struct.DBSObjectFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCUtilsDiffblueTest {
  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    JDBCUtils.appendFilterClause(
        sql, new DBSObjectFilter("Include String", "Exclude String"), "Column Alias", true);

    // Assert
    assertEquals("foo WHERE (Column Alias=?) AND NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause2() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter =
        new DBSObjectFilter(new DBSObjectFilter("Include String", "Exclude String"));
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, null, true);

    // Assert
    assertEquals("foo(=? OR =?)NOT (=? OR =?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause3() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    JDBCUtils.appendFilterClause(sql, new DBSObjectFilter(), "Column Alias", true);

    // Assert that nothing has changed
    assertEquals("foo", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause4() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter = new DBSObjectFilter();
    filter.addInclude("42");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true);

    // Assert
    assertEquals("foo WHERE Column Alias=?", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause5() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter = new DBSObjectFilter();
    filter.addExclude("42");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true);

    // Assert
    assertEquals("foo WHERE NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause6() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter = new DBSObjectFilter();
    filter.addInclude("%");
    filter.addInclude("42");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true);

    // Assert
    assertEquals("foo WHERE (Column Alias LIKE ? OR Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause7() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter = new DBSObjectFilter();
    filter.addInclude("?");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true);

    // Assert
    assertEquals("foo WHERE Column Alias LIKE ?", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause8() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    JDBCUtils.appendFilterClause(
        sql, new DBSObjectFilter("", "Exclude String"), "Column Alias", true);

    // Assert
    assertEquals("foo WHERE NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause9() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    JDBCUtils.appendFilterClause(
        sql, new DBSObjectFilter("Include String", "Exclude String"), "Column Alias", true);

    // Assert
    assertEquals("foo WHERE (Column Alias=?) AND NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause10() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter =
        new DBSObjectFilter(new DBSObjectFilter("Include String", "Exclude String"));
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, null, true);

    // Assert
    assertEquals("foo(=? OR =?)NOT (=? OR =?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause11() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    JDBCUtils.appendFilterClause(sql, new DBSObjectFilter(), "Column Alias", true);

    // Assert that nothing has changed
    assertEquals("foo", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause12() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter = new DBSObjectFilter();
    filter.addInclude("42");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true);

    // Assert
    assertEquals("foo WHERE Column Alias=?", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause13() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter = new DBSObjectFilter();
    filter.addExclude("42");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true);

    // Assert
    assertEquals("foo WHERE NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause14() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter = new DBSObjectFilter();
    filter.addInclude("%");
    filter.addInclude("42");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true);

    // Assert
    assertEquals("foo WHERE (Column Alias LIKE ? OR Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause15() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter = new DBSObjectFilter();
    filter.addInclude("?");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true);

    // Assert
    assertEquals("foo WHERE Column Alias LIKE ?", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)} with
   * {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClause16() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    JDBCUtils.appendFilterClause(
        sql, new DBSObjectFilter("", "Exclude String"), "Column Alias", true);

    // Assert
    assertEquals("foo WHERE NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter =
        new DBSObjectFilter(new DBSObjectFilter("Include String", "Exclude String"));
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, null, true, null);

    // Assert
    assertEquals("foo(=? OR =?)NOT (=? OR =?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource2() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter =
        new DBSObjectFilter(new DBSObjectFilter("Include String", "Exclude String"));
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, null);

    // Assert
    assertEquals(
        "foo WHERE (Column Alias=? OR Column Alias=?) AND NOT (Column Alias=? OR Column Alias=?)",
        sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource3() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("Include String", "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("foo WHERE (Column Alias=?) AND NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource4() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("Include String", "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource));
    verify(dataSource).getSQLDialect();
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource5() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    JDBCUtils.appendFilterClause(
        sql, new DBSObjectFilter(), "Column Alias", true, mock(DBPDataSource.class));

    // Assert that nothing has changed
    assertEquals("foo", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource6() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("*", "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("foo WHERE (Column Alias LIKE ?) AND NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource7() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("?", "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("foo WHERE (Column Alias LIKE ?) AND NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource8() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter(null, "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo WHERE NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource9() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("", "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo WHERE NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource10() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("Include String", null);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo WHERE Column Alias=?", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource11() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter(null, "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource));
    verify(dataSource).getSQLDialect();
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource12() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("Include String", null);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource));
    verify(dataSource).getSQLDialect();
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource13() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter = new DBSObjectFilter("Include String", null);
    filter.addInclude("%");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("foo WHERE (Column Alias=? OR Column Alias LIKE ?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource14() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter =
        new DBSObjectFilter(new DBSObjectFilter("Include String", "Exclude String"));
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, null, true, null);

    // Assert
    assertEquals("foo(=? OR =?)NOT (=? OR =?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource15() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter =
        new DBSObjectFilter(new DBSObjectFilter("Include String", "Exclude String"));
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addInclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");
    filter.addExclude("Filter");

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, null);

    // Assert
    assertEquals(
        "foo WHERE (Column Alias=? OR Column Alias=?) AND NOT (Column Alias=? OR Column Alias=?)",
        sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource16() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("Include String", "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("foo WHERE (Column Alias=?) AND NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource17() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("Include String", "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource));
    verify(dataSource).getSQLDialect();
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource18() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    // Act
    JDBCUtils.appendFilterClause(
        sql, new DBSObjectFilter(), "Column Alias", true, mock(DBPDataSource.class));

    // Assert that nothing has changed
    assertEquals("foo", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource19() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("*", "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("foo WHERE (Column Alias LIKE ?) AND NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource20() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("?", "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("foo WHERE (Column Alias LIKE ?) AND NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource21() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter(null, "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo WHERE NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource22() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("", "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo WHERE NOT (Column Alias=?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource23() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("Include String", null);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo WHERE Column Alias=?", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource24() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter(null, "Exclude String");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource));
    verify(dataSource).getSQLDialect();
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource25() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");
    DBSObjectFilter filter = new DBSObjectFilter("Include String", null);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenThrow(new UnsupportedOperationException());

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource));
    verify(dataSource).getSQLDialect();
  }

  /**
   * Test {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean,
   * DBPDataSource)} with {@code sql}, {@code filter}, {@code columnAlias}, {@code firstClause},
   * {@code dataSource}.
   *
   * <p>Method under test: {@link JDBCUtils#appendFilterClause(StringBuilder, DBSObjectFilter,
   * String, boolean, DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCUtils.appendFilterClause(StringBuilder, DBSObjectFilter, String, boolean, DBPDataSource)"
  })
  public void testAppendFilterClauseWithSqlFilterColumnAliasFirstClauseDataSource26() {
    // Arrange
    StringBuilder sql = new StringBuilder("foo");

    DBSObjectFilter filter = new DBSObjectFilter("Include String", null);
    filter.addInclude("%");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    JDBCUtils.appendFilterClause(sql, filter, "Column Alias", true, dataSource);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("foo WHERE (Column Alias=? OR Column Alias LIKE ?)", sql.toString());
  }

  /**
   * Test {@link JDBCUtils#escapeWildCards(JDBCSession, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCUtils#escapeWildCards(JDBCSession, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCUtils.escapeWildCards(JDBCSession, String)"})
  public void testEscapeWildCards_whenEmptyString_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", JDBCUtils.escapeWildCards(mock(JDBCSession.class), ""));
  }

  /**
   * Test {@link JDBCUtils#escapeWildCards(JDBCSession, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link JDBCUtils#escapeWildCards(JDBCSession, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCUtils.escapeWildCards(JDBCSession, String)"})
  public void testEscapeWildCards_whenEmptyString_thenReturnEmptyString2() {
    // Arrange, Act and Assert
    assertEquals("", JDBCUtils.escapeWildCards(mock(JDBCSession.class), ""));
  }

  /**
   * Test {@link JDBCUtils#escapeWildCards(JDBCSession, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCUtils#escapeWildCards(JDBCSession, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCUtils.escapeWildCards(JDBCSession, String)"})
  public void testEscapeWildCards_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(JDBCUtils.escapeWildCards(mock(JDBCSession.class), null));
  }

  /**
   * Test {@link JDBCUtils#escapeWildCards(JDBCSession, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCUtils#escapeWildCards(JDBCSession, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCUtils.escapeWildCards(JDBCSession, String)"})
  public void testEscapeWildCards_whenNull_thenReturnNull2() {
    // Arrange, Act and Assert
    assertNull(JDBCUtils.escapeWildCards(mock(JDBCSession.class), null));
  }

  /**
   * Test {@link JDBCUtils#escapeWildCards(JDBCSession, String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCUtils#escapeWildCards(JDBCSession, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCUtils.escapeWildCards(JDBCSession, String)"})
  public void testEscapeWildCards_whenString_thenReturnString() {
    // Arrange, Act and Assert
    assertEquals("String", JDBCUtils.escapeWildCards(mock(JDBCSession.class), "String"));
  }

  /**
   * Test {@link JDBCUtils#escapeWildCards(JDBCSession, String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCUtils#escapeWildCards(JDBCSession, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCUtils.escapeWildCards(JDBCSession, String)"})
  public void testEscapeWildCards_whenString_thenReturnString2() {
    // Arrange, Act and Assert
    assertEquals("String", JDBCUtils.escapeWildCards(mock(JDBCSession.class), "String"));
  }
}
