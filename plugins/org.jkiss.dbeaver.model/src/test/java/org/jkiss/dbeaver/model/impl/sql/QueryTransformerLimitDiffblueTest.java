package org.jkiss.dbeaver.model.impl.sql;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QueryTransformerLimitDiffblueTest {
  /**
   * Test {@link QueryTransformerLimit#transformQueryString(SQLQuery)}.
   *
   * <ul>
   *   <li>Given {@code Empty query}.
   *   <li>Then return {@code Empty query}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerLimit#transformQueryString(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QueryTransformerLimit.transformQueryString(SQLQuery)"})
  public void testTransformQueryString_givenEmptyQuery_thenReturnEmptyQuery() throws DBCException {
    // Arrange
    QueryTransformerLimit queryTransformerLimit = new QueryTransformerLimit(true);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    SQLQuery query = new SQLQuery(dataSource, "");
    query.setText("Empty query");

    // Act
    String actualTransformQueryStringResult = queryTransformerLimit.transformQueryString(query);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("Empty query", actualTransformQueryStringResult);
  }

  /**
   * Test {@link QueryTransformerLimit#transformQueryString(SQLQuery)}.
   *
   * <ul>
   *   <li>Given {@code LIMIT}.
   *   <li>Then return {@code LIMIT}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerLimit#transformQueryString(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QueryTransformerLimit.transformQueryString(SQLQuery)"})
  public void testTransformQueryString_givenLimit_thenReturnLimit() throws DBCException {
    // Arrange
    QueryTransformerLimit queryTransformerLimit = new QueryTransformerLimit(true);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    SQLQuery query = new SQLQuery(dataSource, "SELECT");
    query.setText(" LIMIT ");

    // Act
    String actualTransformQueryStringResult = queryTransformerLimit.transformQueryString(query);

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals(" LIMIT ", actualTransformQueryStringResult);
  }

  /**
   * Test {@link QueryTransformerLimit#transformQueryString(SQLQuery)}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerLimit#transformQueryString(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QueryTransformerLimit.transformQueryString(SQLQuery)"})
  public void testTransformQueryString_thenReturnEmptyString() throws DBCException {
    // Arrange
    QueryTransformerLimit queryTransformerLimit = new QueryTransformerLimit(true);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualTransformQueryStringResult =
        queryTransformerLimit.transformQueryString(new SQLQuery(dataSource, ""));

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("", actualTransformQueryStringResult);
  }

  /**
   * Test {@link QueryTransformerLimit#transformQueryString(SQLQuery)}.
   *
   * <ul>
   *   <li>Then return {@code [}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerLimit#transformQueryString(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QueryTransformerLimit.transformQueryString(SQLQuery)"})
  public void testTransformQueryString_thenReturnLeftSquareBracket() throws DBCException {
    // Arrange
    QueryTransformerLimit queryTransformerLimit = new QueryTransformerLimit(true);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualTransformQueryStringResult =
        queryTransformerLimit.transformQueryString(new SQLQuery(dataSource, "["));

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("[", actualTransformQueryStringResult);
  }

  /**
   * Test {@link QueryTransformerLimit#transformQueryString(SQLQuery)}.
   *
   * <ul>
   *   <li>Then return {@code SELECT LIMIT null, null}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerLimit#transformQueryString(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QueryTransformerLimit.transformQueryString(SQLQuery)"})
  public void testTransformQueryString_thenReturnSelectLimitNullNull() throws DBCException {
    // Arrange
    QueryTransformerLimit queryTransformerLimit = new QueryTransformerLimit(true);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualTransformQueryStringResult =
        queryTransformerLimit.transformQueryString(new SQLQuery(dataSource, "SELECT"));

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("SELECT\nLIMIT null, null", actualTransformQueryStringResult);
  }

  /**
   * Test {@link QueryTransformerLimit#transformQueryString(SQLQuery)}.
   *
   * <ul>
   *   <li>Then return {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerLimit#transformQueryString(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QueryTransformerLimit.transformQueryString(SQLQuery)"})
  public void testTransformQueryString_thenReturnText() throws DBCException {
    // Arrange
    QueryTransformerLimit queryTransformerLimit = new QueryTransformerLimit(true);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualTransformQueryStringResult =
        queryTransformerLimit.transformQueryString(new SQLQuery(dataSource, "Text"));

    // Assert
    verify(dataSource, atLeast(1)).getSQLDialect();
    assertEquals("Text", actualTransformQueryStringResult);
  }
}
