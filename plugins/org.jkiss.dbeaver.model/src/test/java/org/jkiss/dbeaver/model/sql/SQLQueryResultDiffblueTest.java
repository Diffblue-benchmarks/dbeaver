package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.sql.SQLQueryResult.ExecuteResult;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryResultDiffblueTest {
  /**
   * Test ExecuteResult getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ExecuteResult#ExecuteResult(boolean)}
   *   <li>{@link ExecuteResult#setResultSetName(String)}
   *   <li>{@link ExecuteResult#setRowCount(Long)}
   *   <li>{@link ExecuteResult#setUpdateCount(Long)}
   *   <li>{@link ExecuteResult#getResultSetName()}
   *   <li>{@link ExecuteResult#getRowCount()}
   *   <li>{@link ExecuteResult#getUpdateCount()}
   *   <li>{@link ExecuteResult#isResultSet()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ExecuteResult.<init>(boolean)",
    "String ExecuteResult.getResultSetName()",
    "Long ExecuteResult.getRowCount()",
    "Long ExecuteResult.getUpdateCount()",
    "boolean ExecuteResult.isResultSet()",
    "void ExecuteResult.setResultSetName(String)",
    "void ExecuteResult.setRowCount(Long)",
    "void ExecuteResult.setUpdateCount(Long)"
  })
  public void testExecuteResultGettersAndSetters() {
    // Arrange and Act
    ExecuteResult actualExecuteResult = new ExecuteResult(true);
    actualExecuteResult.setResultSetName("Result Set Name");
    actualExecuteResult.setRowCount(3L);
    actualExecuteResult.setUpdateCount(3L);
    String actualResultSetName = actualExecuteResult.getResultSetName();
    Long actualRowCount = actualExecuteResult.getRowCount();
    Long actualUpdateCount = actualExecuteResult.getUpdateCount();
    boolean actualIsResultSetResult = actualExecuteResult.isResultSet();

    // Assert
    assertEquals("Result Set Name", actualResultSetName);
    assertEquals(3L, actualRowCount.longValue());
    assertEquals(3L, actualUpdateCount.longValue());
    assertTrue(actualIsResultSetResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLQueryResult#SQLQueryResult(SQLQuery)}
   *   <li>{@link SQLQueryResult#setError(Throwable)}
   *   <li>{@link SQLQueryResult#setHasResultSet(boolean)}
   *   <li>{@link SQLQueryResult#setQueryTime(long)}
   *   <li>{@link SQLQueryResult#setRowOffset(Long)}
   *   <li>{@link SQLQueryResult#getError()}
   *   <li>{@link SQLQueryResult#getExecuteResults()}
   *   <li>{@link SQLQueryResult#getQueryTime()}
   *   <li>{@link SQLQueryResult#getRowOffset()}
   *   <li>{@link SQLQueryResult#getStatement()}
   *   <li>{@link SQLQueryResult#getWarnings()}
   *   <li>{@link SQLQueryResult#hasResultSet()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLQueryResult.<init>(SQLQuery)",
    "Throwable SQLQueryResult.getError()",
    "List SQLQueryResult.getExecuteResults()",
    "long SQLQueryResult.getQueryTime()",
    "Long SQLQueryResult.getRowOffset()",
    "SQLQuery SQLQueryResult.getStatement()",
    "List SQLQueryResult.getWarnings()",
    "boolean SQLQueryResult.hasResultSet()",
    "void SQLQueryResult.setError(Throwable)",
    "void SQLQueryResult.setHasResultSet(boolean)",
    "void SQLQueryResult.setQueryTime(long)",
    "void SQLQueryResult.setRowOffset(Long)"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLQuery statement = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act
    SQLQueryResult actualSqlQueryResult = new SQLQueryResult(statement);
    Throwable error = new Throwable();
    actualSqlQueryResult.setError(error);
    actualSqlQueryResult.setHasResultSet(true);
    actualSqlQueryResult.setQueryTime(1L);
    actualSqlQueryResult.setRowOffset(1L);
    Throwable actualError = actualSqlQueryResult.getError();
    List<ExecuteResult> actualExecuteResults = actualSqlQueryResult.getExecuteResults();
    long actualQueryTime = actualSqlQueryResult.getQueryTime();
    Long actualRowOffset = actualSqlQueryResult.getRowOffset();
    SQLQuery actualStatement = actualSqlQueryResult.getStatement();
    List<Throwable> actualWarnings = actualSqlQueryResult.getWarnings();
    boolean actualHasResultSetResult = actualSqlQueryResult.hasResultSet();

    // Assert
    assertNull(actualWarnings);
    assertEquals(1L, actualRowOffset.longValue());
    assertEquals(1L, actualQueryTime);
    assertTrue(actualExecuteResults.isEmpty());
    assertTrue(actualHasResultSetResult);
    assertSame(error, actualError);
    assertSame(statement, actualStatement);
  }

  /**
   * Test {@link SQLQueryResult#hasError()}.
   *
   * <ul>
   *   <li>Given {@link SQLQueryResult#SQLQueryResult(SQLQuery)} with statement is {@link
   *       SQLQuery#SQLQuery(DBPDataSource, String)}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryResult#hasError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryResult.hasError()"})
  public void testHasError_givenSQLQueryResultWithStatementIsSQLQuery_thenReturnFalse() {
    // Arrange
    SQLQuery statement = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act and Assert
    assertFalse(new SQLQueryResult(statement).hasError());
  }

  /**
   * Test {@link SQLQueryResult#hasError()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryResult#hasError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLQueryResult.hasError()"})
  public void testHasError_thenReturnTrue() {
    // Arrange
    SQLQuery statement = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLQueryResult sqlQueryResult = new SQLQueryResult(statement);
    sqlQueryResult.setError(new Throwable());

    // Act and Assert
    assertTrue(sqlQueryResult.hasError());
  }

  /**
   * Test {@link SQLQueryResult#addWarnings(Throwable[])}.
   *
   * <ul>
   *   <li>Then {@link SQLQueryResult#SQLQueryResult(SQLQuery)} with statement is {@link
   *       SQLQuery#SQLQuery(DBPDataSource, String)} Warnings size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryResult#addWarnings(Throwable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryResult.addWarnings(Throwable[])"})
  public void testAddWarnings_thenSQLQueryResultWithStatementIsSQLQueryWarningsSizeIsOne() {
    // Arrange
    SQLQuery statement = new SQLQuery(mock(DBPDataSource.class), "Text");
    SQLQueryResult sqlQueryResult = new SQLQueryResult(statement);
    Throwable throwable = new Throwable();

    // Act
    sqlQueryResult.addWarnings(new Throwable[] {throwable});

    // Assert
    List<Throwable> warnings = sqlQueryResult.getWarnings();
    assertEquals(1, warnings.size());
    assertSame(throwable, warnings.get(0));
  }

  /**
   * Test {@link SQLQueryResult#addWarnings(Throwable[])}.
   *
   * <ul>
   *   <li>Then {@link SQLQueryResult#SQLQueryResult(SQLQuery)} with statement is {@link
   *       SQLQuery#SQLQuery(DBPDataSource, String)} Warnings size is two.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryResult#addWarnings(Throwable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryResult.addWarnings(Throwable[])"})
  public void testAddWarnings_thenSQLQueryResultWithStatementIsSQLQueryWarningsSizeIsTwo() {
    // Arrange
    SQLQuery statement = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLQueryResult sqlQueryResult = new SQLQueryResult(statement);
    Throwable throwable = new Throwable();
    sqlQueryResult.addWarnings(new Throwable[] {throwable});
    Throwable throwable2 = new Throwable();

    // Act
    sqlQueryResult.addWarnings(new Throwable[] {throwable2});

    // Assert
    List<Throwable> warnings = sqlQueryResult.getWarnings();
    assertEquals(2, warnings.size());
    assertSame(throwable, warnings.get(0));
    assertSame(throwable2, warnings.get(1));
  }

  /**
   * Test {@link SQLQueryResult#addWarnings(Throwable[])}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link SQLQueryResult#SQLQueryResult(SQLQuery)} with statement is {@link
   *       SQLQuery#SQLQuery(DBPDataSource, String)} Warnings is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryResult#addWarnings(Throwable[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryResult.addWarnings(Throwable[])"})
  public void testAddWarnings_whenNull_thenSQLQueryResultWithStatementIsSQLQueryWarningsIsNull() {
    // Arrange
    SQLQuery statement = new SQLQuery(mock(DBPDataSource.class), "Text");
    SQLQueryResult sqlQueryResult = new SQLQueryResult(statement);

    // Act
    sqlQueryResult.addWarnings(null);

    // Assert that nothing has changed
    assertNull(sqlQueryResult.getWarnings());
  }

  /**
   * Test {@link SQLQueryResult#addExecuteResult(boolean)}.
   *
   * <p>Method under test: {@link SQLQueryResult#addExecuteResult(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ExecuteResult SQLQueryResult.addExecuteResult(boolean)"})
  public void testAddExecuteResult() {
    // Arrange
    SQLQuery statement = new SQLQuery(mock(DBPDataSource.class), "Text");
    SQLQueryResult sqlQueryResult = new SQLQueryResult(statement);

    // Act
    ExecuteResult actualAddExecuteResultResult = sqlQueryResult.addExecuteResult(true);

    // Assert
    assertNull(actualAddExecuteResultResult.getRowCount());
    assertNull(actualAddExecuteResultResult.getUpdateCount());
    assertNull(actualAddExecuteResultResult.getResultSetName());
    assertEquals(1, sqlQueryResult.getExecuteResults().size());
    assertTrue(actualAddExecuteResultResult.isResultSet());
  }

  /**
   * Test {@link SQLQueryResult#getExecuteResults(int, boolean)} with {@code int}, {@code boolean}.
   *
   * <p>Method under test: {@link SQLQueryResult#getExecuteResults(int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ExecuteResult SQLQueryResult.getExecuteResults(int, boolean)"})
  public void testGetExecuteResultsWithIntBoolean() {
    // Arrange
    SQLQuery statement = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLQueryResult sqlQueryResult = new SQLQueryResult(statement);
    sqlQueryResult.addExecuteResult(false);
    sqlQueryResult.addExecuteResult(true);

    // Act and Assert
    assertNull(sqlQueryResult.getExecuteResults(1, true));
  }

  /**
   * Test {@link SQLQueryResult#getExecuteResults(int, boolean)} with {@code int}, {@code boolean}.
   *
   * <ul>
   *   <li>Given {@link SQLQueryResult#SQLQueryResult(SQLQuery)} with statement is {@link
   *       SQLQuery#SQLQuery(DBPDataSource, String)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryResult#getExecuteResults(int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ExecuteResult SQLQueryResult.getExecuteResults(int, boolean)"})
  public void testGetExecuteResultsWithIntBoolean_givenSQLQueryResultWithStatementIsSQLQuery() {
    // Arrange
    SQLQuery statement = new SQLQuery(mock(DBPDataSource.class), "Text");

    // Act and Assert
    assertNull(new SQLQueryResult(statement).getExecuteResults(1, true));
  }

  /**
   * Test {@link SQLQueryResult#getExecuteResults(int, boolean)} with {@code int}, {@code boolean}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryResult#getExecuteResults(int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ExecuteResult SQLQueryResult.getExecuteResults(int, boolean)"})
  public void testGetExecuteResultsWithIntBoolean_thenReturnNull() {
    // Arrange
    SQLQuery statement = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLQueryResult sqlQueryResult = new SQLQueryResult(statement);
    sqlQueryResult.addExecuteResult(true);

    // Act and Assert
    assertNull(sqlQueryResult.getExecuteResults(1, true));
  }

  /**
   * Test {@link SQLQueryResult#getExecuteResults(int, boolean)} with {@code int}, {@code boolean}.
   *
   * <ul>
   *   <li>Then return RowCount is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryResult#getExecuteResults(int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ExecuteResult SQLQueryResult.getExecuteResults(int, boolean)"})
  public void testGetExecuteResultsWithIntBoolean_thenReturnRowCountIsNull() {
    // Arrange
    SQLQuery statement = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLQueryResult sqlQueryResult = new SQLQueryResult(statement);
    sqlQueryResult.addExecuteResult(true);
    sqlQueryResult.addExecuteResult(false);
    sqlQueryResult.addExecuteResult(true);

    // Act
    ExecuteResult actualExecuteResults = sqlQueryResult.getExecuteResults(1, true);

    // Assert
    assertNull(actualExecuteResults.getRowCount());
    assertNull(actualExecuteResults.getUpdateCount());
    assertNull(actualExecuteResults.getResultSetName());
    assertTrue(actualExecuteResults.isResultSet());
  }

  /**
   * Test {@link SQLQueryResult#getExecuteResults(int, boolean)} with {@code int}, {@code boolean}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryResult#getExecuteResults(int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ExecuteResult SQLQueryResult.getExecuteResults(int, boolean)"})
  public void testGetExecuteResultsWithIntBoolean_whenFalse_thenReturnNull() {
    // Arrange
    SQLQuery statement = new SQLQuery(mock(DBPDataSource.class), "Text");

    SQLQueryResult sqlQueryResult = new SQLQueryResult(statement);
    sqlQueryResult.addExecuteResult(true);

    // Act and Assert
    assertNull(sqlQueryResult.getExecuteResults(1, false));
  }
}
