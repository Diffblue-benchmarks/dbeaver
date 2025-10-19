package org.jkiss.dbeaver.model.sql.semantics.context;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.semantics.context.SQLQueryRowsDataContext.JoinInfo;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryRowsDataContextDiffblueTest {
  /**
   * Test {@link SQLQueryRowsDataContext#SQLQueryRowsDataContext(SQLQueryRowsSourceContext, List,
   * List, JoinInfo)}.
   *
   * <ul>
   *   <li>Then return ColumnsList Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryRowsDataContext#SQLQueryRowsDataContext(SQLQueryRowsSourceContext, List, List,
   * JoinInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLQueryRowsDataContext.<init>(SQLQueryRowsSourceContext, List, List, JoinInfo)"
  })
  public void testNewSQLQueryRowsDataContext_thenReturnColumnsListEmpty() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext rowsSources = new SQLQueryRowsSourceContext(connectionInfo);
    ArrayList<SQLQueryResultColumn> columns = new ArrayList<>();
    ArrayList<SQLQueryResultPseudoColumn> pseudoColumns = new ArrayList<>();
    SQLDialect dialect2 = mock(SQLDialect.class);
    HashSet<String> knownColumnNames2 = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo2 =
        new SQLQueryConnectionDummyContext(dialect2, knownColumnNames2, new HashSet<>());
    SQLQueryRowsSourceContext rowsSources2 = new SQLQueryRowsSourceContext(connectionInfo2);
    ArrayList<SQLQueryResultColumn> columns2 = new ArrayList<>();

    SQLQueryRowsDataContext left =
        new SQLQueryRowsDataContext(rowsSources2, columns2, new ArrayList<>());
    SQLDialect dialect3 = mock(SQLDialect.class);
    HashSet<String> knownColumnNames3 = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo3 =
        new SQLQueryConnectionDummyContext(dialect3, knownColumnNames3, new HashSet<>());
    SQLQueryRowsSourceContext rowsSources3 = new SQLQueryRowsSourceContext(connectionInfo3);
    ArrayList<SQLQueryResultColumn> columns3 = new ArrayList<>();

    SQLQueryRowsDataContext right =
        new SQLQueryRowsDataContext(rowsSources3, columns3, new ArrayList<>());

    JoinInfo joinInfo = new JoinInfo(left, right);

    // Act
    SQLQueryRowsDataContext actualSqlQueryRowsDataContext =
        new SQLQueryRowsDataContext(rowsSources, columns, pseudoColumns, joinInfo);

    // Assert
    assertTrue(actualSqlQueryRowsDataContext.getColumnsList().isEmpty());
    assertTrue(actualSqlQueryRowsDataContext.getPseudoColumnsList().isEmpty());
    assertSame(connectionInfo, actualSqlQueryRowsDataContext.getConnection());
    assertSame(joinInfo, actualSqlQueryRowsDataContext.getJoinInfo());
    assertSame(rowsSources, actualSqlQueryRowsDataContext.getRowsSources());
  }

  /**
   * Test {@link SQLQueryRowsDataContext#SQLQueryRowsDataContext(SQLQueryRowsSourceContext, List,
   * List)}.
   *
   * <ul>
   *   <li>Then return JoinInfo is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryRowsDataContext#SQLQueryRowsDataContext(SQLQueryRowsSourceContext, List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryRowsDataContext.<init>(SQLQueryRowsSourceContext, List, List)"})
  public void testNewSQLQueryRowsDataContext_thenReturnJoinInfoIsNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext rowsSources = new SQLQueryRowsSourceContext(connectionInfo);
    ArrayList<SQLQueryResultColumn> columns = new ArrayList<>();

    // Act
    SQLQueryRowsDataContext actualSqlQueryRowsDataContext =
        new SQLQueryRowsDataContext(rowsSources, columns, new ArrayList<>());

    // Assert
    assertNull(actualSqlQueryRowsDataContext.getJoinInfo());
    assertTrue(actualSqlQueryRowsDataContext.getColumnsList().isEmpty());
    assertTrue(actualSqlQueryRowsDataContext.getPseudoColumnsList().isEmpty());
    assertSame(connectionInfo, actualSqlQueryRowsDataContext.getConnection());
    assertSame(rowsSources, actualSqlQueryRowsDataContext.getRowsSources());
  }

  /**
   * Test {@link SQLQueryRowsDataContext#getConnection()}.
   *
   * <p>Method under test: {@link SQLQueryRowsDataContext#getConnection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryConnectionContext SQLQueryRowsDataContext.getConnection()"})
  public void testGetConnection() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext rowsSources = new SQLQueryRowsSourceContext(connectionInfo);
    ArrayList<SQLQueryResultColumn> columns = new ArrayList<>();

    SQLQueryRowsDataContext sqlQueryRowsDataContext =
        new SQLQueryRowsDataContext(rowsSources, columns, new ArrayList<>());

    // Act
    SQLQueryConnectionContext actualConnection = sqlQueryRowsDataContext.getConnection();

    // Assert
    assertTrue(actualConnection instanceof SQLQueryConnectionDummyContext);
    assertTrue(actualConnection.isDummy());
    assertSame(connectionInfo, actualConnection);
    assertSame(actualConnection.dialect, sqlQueryRowsDataContext.getRowsSources().getDialect());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLQueryRowsDataContext#getColumnsList()}
   *   <li>{@link SQLQueryRowsDataContext#getJoinInfo()}
   *   <li>{@link SQLQueryRowsDataContext#getPseudoColumnsList()}
   *   <li>{@link SQLQueryRowsDataContext#getRowsSources()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryRowsDataContext.getColumnsList()",
    "JoinInfo SQLQueryRowsDataContext.getJoinInfo()",
    "List SQLQueryRowsDataContext.getPseudoColumnsList()",
    "SQLQueryRowsSourceContext SQLQueryRowsDataContext.getRowsSources()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext rowsSources = new SQLQueryRowsSourceContext(connectionInfo);
    ArrayList<SQLQueryResultColumn> columns = new ArrayList<>();
    ArrayList<SQLQueryResultPseudoColumn> pseudoColumns = new ArrayList<>();

    SQLQueryRowsDataContext sqlQueryRowsDataContext =
        new SQLQueryRowsDataContext(rowsSources, columns, pseudoColumns);

    // Act
    List<SQLQueryResultColumn> actualColumnsList = sqlQueryRowsDataContext.getColumnsList();
    JoinInfo actualJoinInfo = sqlQueryRowsDataContext.getJoinInfo();
    List<SQLQueryResultPseudoColumn> actualPseudoColumnsList =
        sqlQueryRowsDataContext.getPseudoColumnsList();
    SQLQueryRowsSourceContext actualRowsSources = sqlQueryRowsDataContext.getRowsSources();

    // Assert
    assertNull(actualJoinInfo);
    assertTrue(actualColumnsList.isEmpty());
    assertTrue(actualPseudoColumnsList.isEmpty());
    assertSame(columns, actualColumnsList);
    assertSame(pseudoColumns, actualPseudoColumnsList);
    assertSame(rowsSources, actualRowsSources);
  }

  /**
   * Test {@link SQLQueryRowsDataContext#resolvePseudoColumn(String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryRowsDataContext#resolvePseudoColumn(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryResultPseudoColumn SQLQueryRowsDataContext.resolvePseudoColumn(String)"
  })
  public void testResolvePseudoColumn_thenReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext rowsSources = new SQLQueryRowsSourceContext(connectionInfo);
    ArrayList<SQLQueryResultColumn> columns = new ArrayList<>();

    SQLQueryRowsDataContext sqlQueryRowsDataContext =
        new SQLQueryRowsDataContext(rowsSources, columns, new ArrayList<>());

    // Act and Assert
    assertNull(sqlQueryRowsDataContext.resolvePseudoColumn("Name"));
  }
}
