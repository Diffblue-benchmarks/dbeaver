package org.jkiss.dbeaver.model.impl.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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

public class QueryTransformerTopDiffblueTest {
  /**
   * Test {@link QueryTransformerTop#transformQueryString(SQLQuery)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerTop#transformQueryString(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QueryTransformerTop.transformQueryString(SQLQuery)"})
  public void testTransformQueryString_givenInstance() throws DBCException {
    // Arrange
    QueryTransformerTop queryTransformerTop = new QueryTransformerTop();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    String actualTransformQueryStringResult =
        queryTransformerTop.transformQueryString(new SQLQuery(dataSource, "Text"));

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("Text", actualTransformQueryStringResult);
  }

  /**
   * Test {@link QueryTransformerTop#transformQueryString(SQLQuery)}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerTop#transformQueryString(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QueryTransformerTop.transformQueryString(SQLQuery)"})
  public void testTransformQueryString_thenReturnEmptyString() throws DBCException {
    // Arrange
    QueryTransformerTop queryTransformerTop = new QueryTransformerTop();

    // Act
    String actualTransformQueryStringResult =
        queryTransformerTop.transformQueryString(new SQLQuery(mock(DBPDataSource.class), ""));

    // Assert
    assertEquals("", actualTransformQueryStringResult);
  }

  /**
   * Test {@link QueryTransformerTop#transformQueryString(SQLQuery)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerTop#transformQueryString(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QueryTransformerTop.transformQueryString(SQLQuery)"})
  public void testTransformQueryString_thenReturnNull() throws DBCException {
    // Arrange
    QueryTransformerTop queryTransformerTop = new QueryTransformerTop();

    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    query.setText(null);

    // Act
    String actualTransformQueryStringResult = queryTransformerTop.transformQueryString(query);

    // Assert
    assertNull(actualTransformQueryStringResult);
  }

  /**
   * Test {@link QueryTransformerTop#transformQueryString(SQLQuery)}.
   *
   * <ul>
   *   <li>When {@link DBPDataSource} {@link DBPDataSource#getSQLDialect()} return {@code null}.
   *   <li>Then return {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerTop#transformQueryString(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QueryTransformerTop.transformQueryString(SQLQuery)"})
  public void testTransformQueryString_whenDBPDataSourceGetSQLDialectReturnNull_thenReturnText()
      throws DBCException {
    // Arrange
    QueryTransformerTop queryTransformerTop = new QueryTransformerTop();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(null);

    // Act
    String actualTransformQueryStringResult =
        queryTransformerTop.transformQueryString(new SQLQuery(dataSource, "Text"));

    // Assert
    verify(dataSource).getSQLDialect();
    assertEquals("Text", actualTransformQueryStringResult);
  }

  /**
   * Test {@link QueryTransformerTop#isApplicableTo(SQLQuery)}.
   *
   * <ul>
   *   <li>Given {@link BasicSQLDialect#INSTANCE}.
   *   <li>When {@link DBPDataSource} {@link DBPDataSource#getSQLDialect()} return {@link
   *       BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerTop#isApplicableTo(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QueryTransformerTop.isApplicableTo(SQLQuery)"})
  public void testIsApplicableTo_givenInstance_whenDBPDataSourceGetSQLDialectReturnInstance() {
    // Arrange
    QueryTransformerTop queryTransformerTop = new QueryTransformerTop();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    // Act
    boolean actualIsApplicableToResult =
        queryTransformerTop.isApplicableTo(new SQLQuery(dataSource, "Text"));

    // Assert
    verify(dataSource).getSQLDialect();
    assertFalse(actualIsApplicableToResult);
  }

  /**
   * Test {@link QueryTransformerTop#isApplicableTo(SQLQuery)}.
   *
   * <ul>
   *   <li>When {@link DBPDataSource} {@link DBPDataSource#getSQLDialect()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerTop#isApplicableTo(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QueryTransformerTop.isApplicableTo(SQLQuery)"})
  public void testIsApplicableTo_whenDBPDataSourceGetSQLDialectReturnNull() {
    // Arrange
    QueryTransformerTop queryTransformerTop = new QueryTransformerTop();

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(null);

    // Act
    boolean actualIsApplicableToResult =
        queryTransformerTop.isApplicableTo(new SQLQuery(dataSource, "Text"));

    // Assert
    verify(dataSource).getSQLDialect();
    assertFalse(actualIsApplicableToResult);
  }

  /**
   * Test {@link QueryTransformerTop#isApplicableTo(SQLQuery)}.
   *
   * <ul>
   *   <li>When {@link SQLQuery#SQLQuery(DBPDataSource, String)} with dataSource is {@link
   *       DBPDataSource} and text is empty string.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerTop#isApplicableTo(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QueryTransformerTop.isApplicableTo(SQLQuery)"})
  public void testIsApplicableTo_whenSQLQueryWithDataSourceIsDBPDataSourceAndTextIsEmptyString() {
    // Arrange
    QueryTransformerTop queryTransformerTop = new QueryTransformerTop();

    // Act
    boolean actualIsApplicableToResult =
        queryTransformerTop.isApplicableTo(new SQLQuery(mock(DBPDataSource.class), ""));

    // Assert
    assertFalse(actualIsApplicableToResult);
  }

  /**
   * Test {@link QueryTransformerTop#isApplicableTo(SQLQuery)}.
   *
   * <ul>
   *   <li>When {@link SQLQuery#SQLQuery(DBPDataSource, String)} with dataSource is {@link
   *       DBPDataSource} and {@code Text} Text is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QueryTransformerTop#isApplicableTo(SQLQuery)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean QueryTransformerTop.isApplicableTo(SQLQuery)"})
  public void testIsApplicableTo_whenSQLQueryWithDataSourceIsDBPDataSourceAndTextTextIsNull() {
    // Arrange
    QueryTransformerTop queryTransformerTop = new QueryTransformerTop();

    SQLQuery query = new SQLQuery(mock(DBPDataSource.class), "Text");
    query.setText(null);

    // Act
    boolean actualIsApplicableToResult = queryTransformerTop.isApplicableTo(query);

    // Assert
    assertFalse(actualIsApplicableToResult);
  }
}
