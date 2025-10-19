package org.jkiss.dbeaver.model.impl.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.data.DBDAttributeConstraint;
import org.jkiss.dbeaver.model.data.DBDDataFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StandardSQLDialectQueryGeneratorDiffblueTest {
  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryConditions(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendQueryConditions(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryConditions(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryConditions_givenEmptyString() throws DBException {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dataFilter = new DBDDataFilter(constraints);
    dataFilter.setWhere("");

    // Act
    standardSQLDialectQueryGenerator.appendQueryConditions(
        dataSource, query, "Table Alias", dataFilter);

    // Assert that nothing has changed
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryConditions(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       WHERE Data Filter}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendQueryConditions(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryConditions(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryConditions_thenStringBuilderWithFooToStringIsFooWhereDataFilter()
      throws DBException {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dataFilter = new DBDDataFilter(constraints);
    dataFilter.setWhere("Data Filter");

    // Act
    standardSQLDialectQueryGenerator.appendQueryConditions(
        dataSource, query, "Table Alias", dataFilter);

    // Assert
    assertEquals("foo\nWHERE Data Filter", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryConditions(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       WHERE Data Filter}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendQueryConditions(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryConditions(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryConditions_thenStringBuilderWithFooToStringIsFooWhereDataFilter2()
      throws DBException {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dataFilter = new DBDDataFilter(constraints);
    dataFilter.setWhere("Data Filter");

    // Act
    standardSQLDialectQueryGenerator.appendQueryConditions(
        dataSource, query, "Table Alias", dataFilter);

    // Assert
    assertEquals("foo\nWHERE Data Filter", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryConditions(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       WHERE Where}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendQueryConditions(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryConditions(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryConditions_thenStringBuilderWithFooToStringIsFooWhereWhere()
      throws DBException {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    DBDDataFilter dataFilter = new DBDDataFilter();
    dataFilter.setWhere("Where");

    // Act
    standardSQLDialectQueryGenerator.appendQueryConditions(
        dataSource, query, "Table Alias", dataFilter);

    // Assert
    assertEquals("foo\nWHERE Where", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryConditions(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>When {@link DBDDataFilter#DBDDataFilter()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendQueryConditions(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryConditions(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryConditions_whenDBDDataFilter() throws DBException {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendQueryConditions(
        dataSource, query, "Table Alias", new DBDDataFilter());

    // Assert that nothing has changed
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryConditions(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendQueryConditions(DBPDataSource, StringBuilder, String,
   * DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryConditions(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryConditions_whenNull_thenStringBuilderWithFooToStringIsFoo()
      throws DBException {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendQueryConditions(dataSource, query, "Table Alias", null);

    // Assert that nothing has changed
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}.
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("\nORDER BY ", 1));
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dataFilter = new DBDDataFilter(constraints);
    dataFilter.setOrder("");

    // Act
    standardSQLDialectQueryGenerator.appendQueryOrder(dataSource, query, null, dataFilter);

    // Assert
    assertEquals("foo\nORDER BY 2", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder_givenEmptyString_thenStringBuilderWithFooToStringIsFoo() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dataFilter = new DBDDataFilter(constraints);
    dataFilter.setOrder("");

    // Act
    standardSQLDialectQueryGenerator.appendQueryOrder(dataSource, query, null, dataFilter);

    // Assert that nothing has changed
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       ORDER BY 1}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder_thenStringBuilderWithFooToStringIsFooOrderBy1() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dataFilter = new DBDDataFilter(constraints);
    dataFilter.setOrder("");

    // Act
    standardSQLDialectQueryGenerator.appendQueryOrder(dataSource, query, null, dataFilter);

    // Assert
    assertEquals("foo\nORDER BY 1", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       ORDER BY 1, ORDER BY}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder_thenStringBuilderWithFooToStringIsFooOrderBy1OrderBy() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dataFilter = new DBDDataFilter(constraints);
    dataFilter.setOrder("\nORDER BY ");

    // Act
    standardSQLDialectQueryGenerator.appendQueryOrder(dataSource, query, null, dataFilter);

    // Assert
    assertEquals("foo\nORDER BY 1,\nORDER BY ", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       ORDER BY 2}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder_thenStringBuilderWithFooToStringIsFooOrderBy2() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dataFilter = new DBDDataFilter(constraints);
    dataFilter.setOrder("");

    // Act
    standardSQLDialectQueryGenerator.appendQueryOrder(dataSource, query, null, dataFilter);

    // Assert
    assertEquals("foo\nORDER BY 2", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       ORDER BY 1,1}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder_thenStringBuilderWithFooToStringIsFooOrderBy11() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    DBDAttributeConstraint dbdAttributeConstraint2 =
        new DBDAttributeConstraint("Attribute Name", 1);
    dbdAttributeConstraint2.setOrderPosition(1);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(dbdAttributeConstraint2);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dataFilter = new DBDDataFilter(constraints);
    dataFilter.setOrder("");

    // Act
    standardSQLDialectQueryGenerator.appendQueryOrder(dataSource, query, null, dataFilter);

    // Assert
    assertEquals("foo\nORDER BY 1,1", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       ORDER BY Data Filter}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder_thenStringBuilderWithFooToStringIsFooOrderByDataFilter() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dataFilter = new DBDDataFilter(constraints);
    dataFilter.setOrder("Data Filter");

    // Act
    standardSQLDialectQueryGenerator.appendQueryOrder(dataSource, query, null, dataFilter);

    // Assert
    assertEquals("foo\nORDER BY Data Filter", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code foo
   *       ORDER BY ORDER BY}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder_thenStringBuilderWithFooToStringIsFooOrderByOrderBy() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    DBDAttributeConstraint dbdAttributeConstraint = new DBDAttributeConstraint("\nORDER BY ", 1);
    dbdAttributeConstraint.setOrderPosition(1);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(dbdAttributeConstraint);
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));
    constraints.add(new DBDAttributeConstraint("Attribute Name", 1));

    DBDDataFilter dataFilter = new DBDDataFilter(constraints);
    dataFilter.setOrder("");

    // Act
    standardSQLDialectQueryGenerator.appendQueryOrder(dataSource, query, null, dataFilter);

    // Assert
    assertEquals("foo\nORDER BY \nORDER BY ", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder_whenNull_thenStringBuilderWithFooToStringIsFoo() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendQueryOrder(dataSource, query, null, null);

    // Assert that nothing has changed
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource, StringBuilder,
   * String, DBDDataFilter)}.
   *
   * <ul>
   *   <li>When {@code Table Alias}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendQueryOrder(DBPDataSource,
   * StringBuilder, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendQueryOrder(DBPDataSource, StringBuilder, String, DBDDataFilter)"
  })
  public void testAppendQueryOrder_whenTableAlias_thenStringBuilderWithFooToStringIsFoo() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendQueryOrder(
        dataSource, query, "Table Alias", new DBDDataFilter());

    // Assert that nothing has changed
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List,
   * DBPDataSource, String, StringBuilder, boolean, boolean)} with {@code filter}, {@code
   * constraints}, {@code dataSource}, {@code conditionTable}, {@code query}, {@code
   * inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBDDataFilter filter = new DBDDataFilter();
    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert that nothing has changed
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List,
   * DBPDataSource, String, StringBuilder, boolean, boolean)} with {@code filter}, {@code
   * constraints}, {@code dataSource}, {@code conditionTable}, {@code query}, {@code
   * inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery2() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBDDataFilter filter = new DBDDataFilter();

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint(" AND ", 3));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo\" AND \" null", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List,
   * DBPDataSource, String, StringBuilder, boolean, boolean)} with {@code filter}, {@code
   * constraints}, {@code dataSource}, {@code conditionTable}, {@code query}, {@code
   * inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery3() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBDDataFilter filter = new DBDDataFilter();

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint(" AND ", 3));
    constraints.add(new DBDAttributeConstraint(" AND ", 3));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("foo(\" AND \" null) AND (\" AND \" null)", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List,
   * DBPDataSource, String, StringBuilder, boolean, boolean)} with {@code filter}, {@code
   * constraints}, {@code dataSource}, {@code conditionTable}, {@code query}, {@code
   * inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery4() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();

    DBDDataFilter filter = new DBDDataFilter();
    filter.setAnyConstraint(true);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint(" AND ", 3));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo\" AND \" null", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List,
   * DBPDataSource, String, StringBuilder, boolean, boolean)} with {@code filter}, {@code
   * constraints}, {@code dataSource}, {@code conditionTable}, {@code query}, {@code
   * inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery5() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();

    DBDDataFilter filter = new DBDDataFilter();
    filter.setWhere(" AND ");

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint(" AND ", 3));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo\" AND \" null AND ( AND )", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List,
   * DBPDataSource, String, StringBuilder, boolean, boolean)} with {@code filter}, {@code
   * constraints}, {@code dataSource}, {@code conditionTable}, {@code query}, {@code
   * inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery6() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();

    DBDDataFilter filter = new DBDDataFilter();
    filter.setUseDisjunctiveNormalForm(true);

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint(" AND ", 3));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo\" AND \" null", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List,
   * DBPDataSource, String, StringBuilder, boolean, boolean)} with {@code filter}, {@code
   * constraints}, {@code dataSource}, {@code conditionTable}, {@code query}, {@code
   * inlineCriteria}, {@code subQuery}.
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#appendConditionString(DBDDataFilter, List, DBPDataSource,
   * String, StringBuilder, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendConditionString(DBDDataFilter, List, DBPDataSource, String, StringBuilder, boolean, boolean)"
  })
  public void
      testAppendConditionStringWithFilterConstraintsDataSourceConditionTableQueryInlineCriteriaSubQuery7() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();

    DBDDataFilter filter = new DBDDataFilter();
    filter.setWhere("");

    ArrayList<DBDAttributeConstraint> constraints = new ArrayList<>();
    constraints.add(new DBDAttributeConstraint(" AND ", 3));

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendConditionString(
        filter, constraints, dataSource, "Condition Table", query, true, true);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("foo\" AND \" null", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#getWrappedFilterQuery(DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#getWrappedFilterQuery(DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StandardSQLDialectQueryGenerator.getWrappedFilterQuery(DBPDataSource, String, DBDDataFilter)"
  })
  public void testGetWrappedFilterQuery() throws DBException {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBDDataFilter dataFilter = new DBDDataFilter();
    dataFilter.setWhere("SELECT * FROM (\n");
    dataFilter.setOrder("SELECT * FROM (\n");

    // Act
    String actualWrappedFilterQuery =
        standardSQLDialectQueryGenerator.getWrappedFilterQuery(dataSource, "Sql Query", dataFilter);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals(
        "SELECT * FROM (\nSql Query\n) z_q WHERE SELECT * FROM (\n ORDER BY SELECT * FROM (\n",
        actualWrappedFilterQuery);
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#getWrappedFilterQuery(DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link DBDDataFilter#DBDDataFilter()} Order is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#getWrappedFilterQuery(DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StandardSQLDialectQueryGenerator.getWrappedFilterQuery(DBPDataSource, String, DBDDataFilter)"
  })
  public void testGetWrappedFilterQuery_givenEmptyString_whenDBDDataFilterOrderIsEmptyString()
      throws DBException {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    DBDDataFilter dataFilter = new DBDDataFilter();
    dataFilter.setOrder("");

    // Act and Assert
    assertEquals(
        "SELECT * FROM (\nSql Query\n) z_q",
        standardSQLDialectQueryGenerator.getWrappedFilterQuery(
            dataSource, "Sql Query", dataFilter));
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#getWrappedFilterQuery(DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link DBDDataFilter#DBDDataFilter()} AnyConstraint is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#getWrappedFilterQuery(DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StandardSQLDialectQueryGenerator.getWrappedFilterQuery(DBPDataSource, String, DBDDataFilter)"
  })
  public void testGetWrappedFilterQuery_givenTrue_whenDBDDataFilterAnyConstraintIsTrue()
      throws DBException {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBDDataFilter dataFilter = new DBDDataFilter();
    dataFilter.setAnyConstraint(true);
    dataFilter.setWhere("SELECT * FROM (\n");
    dataFilter.setOrder("SELECT * FROM (\n");

    // Act
    String actualWrappedFilterQuery =
        standardSQLDialectQueryGenerator.getWrappedFilterQuery(dataSource, "Sql Query", dataFilter);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals(
        "SELECT * FROM (\nSql Query\n) z_q WHERE SELECT * FROM (\n ORDER BY SELECT * FROM (\n",
        actualWrappedFilterQuery);
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#getWrappedFilterQuery(DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>Then return {@code SELECT * FROM ( Sql Query ) z_q ORDER BY SELECT * FROM (}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#getWrappedFilterQuery(DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StandardSQLDialectQueryGenerator.getWrappedFilterQuery(DBPDataSource, String, DBDDataFilter)"
  })
  public void testGetWrappedFilterQuery_thenReturnSelectFromSqlQueryZQOrderBySelectFrom()
      throws DBException {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBDDataFilter dataFilter = new DBDDataFilter();
    dataFilter.setOrder("SELECT * FROM (\n");

    // Act
    String actualWrappedFilterQuery =
        standardSQLDialectQueryGenerator.getWrappedFilterQuery(dataSource, "Sql Query", dataFilter);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals(
        "SELECT * FROM (\nSql Query\n) z_q ORDER BY SELECT * FROM (\n", actualWrappedFilterQuery);
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#getWrappedFilterQuery(DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>When {@link DBDDataFilter#DBDDataFilter()} UseDisjunctiveNormalForm is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#getWrappedFilterQuery(DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StandardSQLDialectQueryGenerator.getWrappedFilterQuery(DBPDataSource, String, DBDDataFilter)"
  })
  public void testGetWrappedFilterQuery_whenDBDDataFilterUseDisjunctiveNormalFormIsTrue()
      throws DBException {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBDDataFilter dataFilter = new DBDDataFilter();
    dataFilter.setUseDisjunctiveNormalForm(true);
    dataFilter.setWhere("SELECT * FROM (\n");
    dataFilter.setOrder("SELECT * FROM (\n");

    // Act
    String actualWrappedFilterQuery =
        standardSQLDialectQueryGenerator.getWrappedFilterQuery(dataSource, "Sql Query", dataFilter);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals(
        "SELECT * FROM (\nSql Query\n) z_q WHERE SELECT * FROM (\n ORDER BY SELECT * FROM (\n",
        actualWrappedFilterQuery);
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#getWrappedFilterQuery(DBPDataSource, String,
   * DBDDataFilter)}.
   *
   * <ul>
   *   <li>When {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then return {@code SELECT * FROM ( Sql Query ) z_q}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#getWrappedFilterQuery(DBPDataSource, String, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StandardSQLDialectQueryGenerator.getWrappedFilterQuery(DBPDataSource, String, DBDDataFilter)"
  })
  public void testGetWrappedFilterQuery_whenDBDDataFilter_thenReturnSelectFromSqlQueryZQ()
      throws DBException {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act and Assert
    assertEquals(
        "SELECT * FROM (\nSql Query\n) z_q",
        standardSQLDialectQueryGenerator.getWrappedFilterQuery(
            dataSource, "Sql Query", new DBDDataFilter()));
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendOrderString(DBDDataFilter, DBPDataSource,
   * String, boolean, StringBuilder)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link DBDDataFilter#DBDDataFilter()} Order is empty string.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendOrderString(DBDDataFilter,
   * DBPDataSource, String, boolean, StringBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendOrderString(DBDDataFilter, DBPDataSource, String, boolean, StringBuilder)"
  })
  public void testAppendOrderString_givenEmptyString_whenDBDDataFilterOrderIsEmptyString() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();

    DBDDataFilter filter = new DBDDataFilter();
    filter.setOrder("");
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendOrderString(
        filter, dataSource, "Condition Table", true, query);

    // Assert that nothing has changed
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendOrderString(DBDDataFilter, DBPDataSource,
   * String, boolean, StringBuilder)}.
   *
   * <ul>
   *   <li>Given {@code Order}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       fooOrder}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendOrderString(DBDDataFilter,
   * DBPDataSource, String, boolean, StringBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendOrderString(DBDDataFilter, DBPDataSource, String, boolean, StringBuilder)"
  })
  public void testAppendOrderString_givenOrder_thenStringBuilderWithFooToStringIsFooOrder() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();

    DBDDataFilter filter = new DBDDataFilter();
    filter.setOrder("Order");
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendOrderString(
        filter, dataSource, "Condition Table", true, query);

    // Assert
    assertEquals("fooOrder", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#appendOrderString(DBDDataFilter, DBPDataSource,
   * String, boolean, StringBuilder)}.
   *
   * <ul>
   *   <li>When {@link DBDDataFilter#DBDDataFilter()}.
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#appendOrderString(DBDDataFilter,
   * DBPDataSource, String, boolean, StringBuilder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StandardSQLDialectQueryGenerator.appendOrderString(DBDDataFilter, DBPDataSource, String, boolean, StringBuilder)"
  })
  public void testAppendOrderString_whenDBDDataFilter_thenStringBuilderWithFooToStringIsFoo() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBDDataFilter filter = new DBDDataFilter();
    DBPDataSource dataSource = mock(DBPDataSource.class);
    StringBuilder query = new StringBuilder("foo");

    // Act
    standardSQLDialectQueryGenerator.appendOrderString(
        filter, dataSource, "Condition Table", true, query);

    // Assert that nothing has changed
    assertEquals("foo", query.toString());
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#canOrderByName(DBPDataSource,
   * DBDAttributeConstraint, String)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#canOrderByName(DBPDataSource,
   * DBDAttributeConstraint, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean StandardSQLDialectQueryGenerator.canOrderByName(DBPDataSource, DBDAttributeConstraint, String)"
  })
  public void testCanOrderByName_thenReturnTrue() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    boolean actualCanOrderByNameResult =
        StandardSQLDialectQueryGenerator.canOrderByName(
            dataSource, new DBDAttributeConstraint("Attribute Name", 1), "Constraint Name");

    // Assert
    assertTrue(actualCanOrderByNameResult);
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#getConstraintCondition(DBPDataSource,
   * DBDAttributeConstraint, String, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#getConstraintCondition(DBPDataSource, DBDAttributeConstraint,
   * String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StandardSQLDialectQueryGenerator.getConstraintCondition(DBPDataSource, DBDAttributeConstraint, String, boolean)"
  })
  public void testGetConstraintCondition_thenReturnNull() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act and Assert
    assertNull(
        standardSQLDialectQueryGenerator.getConstraintCondition(
            dataSource, new DBDAttributeConstraint("Attribute Name", 1), "Condition Table", true));
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#getStringValue(DBPDataSource,
   * DBDAttributeConstraint, boolean, Object)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code '42'}.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#getStringValue(DBPDataSource,
   * DBDAttributeConstraint, boolean, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StandardSQLDialectQueryGenerator.getStringValue(DBPDataSource, DBDAttributeConstraint, boolean, Object)"
  })
  public void testGetStringValue_givenInstance_thenReturn42() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualStringValue =
        StandardSQLDialectQueryGenerator.getStringValue(
            dataSource, new DBDAttributeConstraint("Attribute Name", 1), true, "42");

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("'42'", actualStringValue);
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#getStringValue(DBPDataSource,
   * DBDAttributeConstraint, boolean, Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link StandardSQLDialectQueryGenerator#getStringValue(DBPDataSource,
   * DBDAttributeConstraint, boolean, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StandardSQLDialectQueryGenerator.getStringValue(DBPDataSource, DBDAttributeConstraint, boolean, Object)"
  })
  public void testGetStringValue_whenNull_thenReturnEmptyString() {
    // Arrange
    DBPDataSource dataSource = mock(DBPDataSource.class);

    // Act
    String actualStringValue =
        StandardSQLDialectQueryGenerator.getStringValue(
            dataSource, new DBDAttributeConstraint("Attribute Name", 1), true, null);

    // Assert
    assertEquals("", actualStringValue);
  }

  /**
   * Test {@link StandardSQLDialectQueryGenerator#getConstraintAttributeName(DBPDataSource, String,
   * DBDAttributeConstraint, boolean, boolean)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code "Attribute Name"}.
   * </ul>
   *
   * <p>Method under test: {@link
   * StandardSQLDialectQueryGenerator#getConstraintAttributeName(DBPDataSource, String,
   * DBDAttributeConstraint, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String StandardSQLDialectQueryGenerator.getConstraintAttributeName(DBPDataSource, String, DBDAttributeConstraint, boolean, boolean)"
  })
  public void testGetConstraintAttributeName_givenInstance_thenReturnAttributeName() {
    // Arrange
    StandardSQLDialectQueryGenerator standardSQLDialectQueryGenerator =
        new StandardSQLDialectQueryGenerator();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualConstraintAttributeName =
        standardSQLDialectQueryGenerator.getConstraintAttributeName(
            dataSource,
            "Condition Table",
            new DBDAttributeConstraint("Attribute Name", 1),
            true,
            true);

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("\"Attribute Name\"", actualConstraintAttributeName);
  }
}
