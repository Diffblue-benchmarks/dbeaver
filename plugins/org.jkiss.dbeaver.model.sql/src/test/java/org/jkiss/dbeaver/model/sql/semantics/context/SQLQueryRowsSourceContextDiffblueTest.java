package org.jkiss.dbeaver.model.sql.semantics.context;

import static org.junit.Assert.assertFalse;
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
import java.util.function.Supplier;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.semantics.SQLQuerySymbolEntry;
import org.jkiss.dbeaver.model.sql.semantics.context.SQLQueryRowsDataContext.JoinInfo;
import org.jkiss.dbeaver.model.sql.semantics.model.select.SQLQueryRowsSourceModel;
import org.jkiss.utils.Pair;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLQueryRowsSourceContextDiffblueTest {
  /**
   * Test {@link SQLQueryRowsSourceContext#SQLQueryRowsSourceContext(SQLQueryConnectionContext)}.
   *
   * <p>Method under test: {@link
   * SQLQueryRowsSourceContext#SQLQueryRowsSourceContext(SQLQueryConnectionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLQueryRowsSourceContext.<init>(SQLQueryConnectionContext)"})
  public void testNewSQLQueryRowsSourceContext() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());

    // Act
    SQLQueryRowsSourceContext actualSqlQueryRowsSourceContext =
        new SQLQueryRowsSourceContext(connectionInfo);

    // Assert
    assertNull(actualSqlQueryRowsSourceContext.getRelatedContextProvider());
    assertFalse(actualSqlQueryRowsSourceContext.hasUnresolvedSource());
    assertSame(connectionInfo, actualSqlQueryRowsSourceContext.getConnectionInfo());
    assertSame(connectionInfo.dialect, actualSqlQueryRowsSourceContext.getDialect());
  }

  /**
   * Test {@link SQLQueryRowsSourceContext#getDialect()}.
   *
   * <p>Method under test: {@link SQLQueryRowsSourceContext#getDialect()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLDialect SQLQueryRowsSourceContext.getDialect()"})
  public void testGetDialect() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());

    // Act and Assert
    assertSame(connectionInfo.dialect, new SQLQueryRowsSourceContext(connectionInfo).getDialect());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLQueryRowsSourceContext#getConnectionInfo()}
   *   <li>{@link SQLQueryRowsSourceContext#getRelatedContextProvider()}
   *   <li>{@link SQLQueryRowsSourceContext#hasUnresolvedSource()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryConnectionContext SQLQueryRowsSourceContext.getConnectionInfo()",
    "Supplier SQLQueryRowsSourceContext.getRelatedContextProvider()",
    "boolean SQLQueryRowsSourceContext.hasUnresolvedSource()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext sqlQueryRowsSourceContext =
        new SQLQueryRowsSourceContext(connectionInfo);

    // Act
    SQLQueryConnectionContext actualConnectionInfo = sqlQueryRowsSourceContext.getConnectionInfo();
    Supplier<SQLQueryRowsDataContext> actualRelatedContextProvider =
        sqlQueryRowsSourceContext.getRelatedContextProvider();

    // Assert
    assertNull(actualRelatedContextProvider);
    assertFalse(sqlQueryRowsSourceContext.hasUnresolvedSource());
    assertSame(connectionInfo, actualConnectionInfo);
  }

  /**
   * Test {@link SQLQueryRowsSourceContext#reset()}.
   *
   * <p>Method under test: {@link SQLQueryRowsSourceContext#reset()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryRowsSourceContext SQLQueryRowsSourceContext.reset()"})
  public void testReset() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());

    // Act
    SQLQueryRowsSourceContext actualResetResult =
        new SQLQueryRowsSourceContext(connectionInfo).reset();

    // Assert
    assertNull(actualResetResult.getRelatedContextProvider());
    assertFalse(actualResetResult.hasUnresolvedSource());
    assertSame(connectionInfo, actualResetResult.getConnectionInfo());
    assertSame(connectionInfo.dialect, actualResetResult.getDialect());
  }

  /**
   * Test {@link SQLQueryRowsSourceContext#resetAsUnresolved()}.
   *
   * <p>Method under test: {@link SQLQueryRowsSourceContext#resetAsUnresolved()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryRowsSourceContext SQLQueryRowsSourceContext.resetAsUnresolved()"})
  public void testResetAsUnresolved() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());

    // Act
    SQLQueryRowsSourceContext actualResetAsUnresolvedResult =
        new SQLQueryRowsSourceContext(connectionInfo).resetAsUnresolved();

    // Assert
    assertNull(actualResetAsUnresolvedResult.getRelatedContextProvider());
    assertTrue(actualResetAsUnresolvedResult.hasUnresolvedSource());
    assertSame(connectionInfo, actualResetAsUnresolvedResult.getConnectionInfo());
    assertSame(connectionInfo.dialect, actualResetAsUnresolvedResult.getDialect());
  }

  /**
   * Test {@link SQLQueryRowsSourceContext#combine(SQLQueryRowsSourceContext)}.
   *
   * <p>Method under test: {@link SQLQueryRowsSourceContext#combine(SQLQueryRowsSourceContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryRowsSourceContext SQLQueryRowsSourceContext.combine(SQLQueryRowsSourceContext)"
  })
  public void testCombine() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext sqlQueryRowsSourceContext =
        new SQLQueryRowsSourceContext(connectionInfo);
    SQLDialect dialect2 = mock(SQLDialect.class);
    HashSet<String> knownColumnNames2 = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo2 =
        new SQLQueryConnectionDummyContext(dialect2, knownColumnNames2, new HashSet<>());

    // Act
    SQLQueryRowsSourceContext actualCombineResult =
        sqlQueryRowsSourceContext.combine(new SQLQueryRowsSourceContext(connectionInfo2));

    // Assert
    assertNull(actualCombineResult.getRelatedContextProvider());
    assertFalse(actualCombineResult.hasUnresolvedSource());
    assertSame(connectionInfo, actualCombineResult.getConnectionInfo());
    assertSame(connectionInfo.dialect, actualCombineResult.getDialect());
  }

  /**
   * Test {@link SQLQueryRowsSourceContext#appendCteSources(List)}.
   *
   * <ul>
   *   <li>Given {@link Pair#Pair(Object, Object)} with first is {@code null} and second is {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryRowsSourceContext#appendCteSources(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryRowsSourceContext SQLQueryRowsSourceContext.appendCteSources(List)"})
  public void testAppendCteSources_givenPairWithFirstIsNullAndSecondIsNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext sqlQueryRowsSourceContext =
        new SQLQueryRowsSourceContext(connectionInfo);

    ArrayList<Pair<SQLQuerySymbolEntry, SQLQueryRowsSourceModel>> sources = new ArrayList<>();
    Pair<SQLQuerySymbolEntry, SQLQueryRowsSourceModel> pair = new Pair<>(null, null);
    sources.add(pair);

    // Act
    SQLQueryRowsSourceContext actualAppendCteSourcesResult =
        sqlQueryRowsSourceContext.appendCteSources(sources);

    // Assert
    assertNull(actualAppendCteSourcesResult.getRelatedContextProvider());
    assertFalse(actualAppendCteSourcesResult.hasUnresolvedSource());
    assertSame(connectionInfo, actualAppendCteSourcesResult.getConnectionInfo());
    assertSame(connectionInfo.dialect, actualAppendCteSourcesResult.getDialect());
  }

  /**
   * Test {@link SQLQueryRowsSourceContext#appendCteSources(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryRowsSourceContext#appendCteSources(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryRowsSourceContext SQLQueryRowsSourceContext.appendCteSources(List)"})
  public void testAppendCteSources_whenArrayList() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext sqlQueryRowsSourceContext =
        new SQLQueryRowsSourceContext(connectionInfo);

    // Act
    SQLQueryRowsSourceContext actualAppendCteSourcesResult =
        sqlQueryRowsSourceContext.appendCteSources(new ArrayList<>());

    // Assert
    assertNull(actualAppendCteSourcesResult.getRelatedContextProvider());
    assertFalse(actualAppendCteSourcesResult.hasUnresolvedSource());
    assertSame(connectionInfo, actualAppendCteSourcesResult.getConnectionInfo());
    assertSame(connectionInfo.dialect, actualAppendCteSourcesResult.getDialect());
  }

  /**
   * Test {@link SQLQueryRowsSourceContext#setCteSourcesFrom(SQLQueryRowsSourceContext)}.
   *
   * <p>Method under test: {@link
   * SQLQueryRowsSourceContext#setCteSourcesFrom(SQLQueryRowsSourceContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryRowsSourceContext SQLQueryRowsSourceContext.setCteSourcesFrom(SQLQueryRowsSourceContext)"
  })
  public void testSetCteSourcesFrom() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext sqlQueryRowsSourceContext =
        new SQLQueryRowsSourceContext(connectionInfo);
    SQLDialect dialect2 = mock(SQLDialect.class);
    HashSet<String> knownColumnNames2 = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo2 =
        new SQLQueryConnectionDummyContext(dialect2, knownColumnNames2, new HashSet<>());

    // Act
    SQLQueryRowsSourceContext actualSetCteSourcesFromResult =
        sqlQueryRowsSourceContext.setCteSourcesFrom(new SQLQueryRowsSourceContext(connectionInfo2));

    // Assert
    assertNull(actualSetCteSourcesFromResult.getRelatedContextProvider());
    assertFalse(actualSetCteSourcesFromResult.hasUnresolvedSource());
    assertSame(connectionInfo, actualSetCteSourcesFromResult.getConnectionInfo());
    assertSame(connectionInfo.dialect, actualSetCteSourcesFromResult.getDialect());
  }

  /**
   * Test {@link SQLQueryRowsSourceContext#makeEmptyTuple()}.
   *
   * <p>Method under test: {@link SQLQueryRowsSourceContext#makeEmptyTuple()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryRowsDataContext SQLQueryRowsSourceContext.makeEmptyTuple()"})
  public void testMakeEmptyTuple() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext sqlQueryRowsSourceContext =
        new SQLQueryRowsSourceContext(connectionInfo);

    // Act
    SQLQueryRowsDataContext actualMakeEmptyTupleResult = sqlQueryRowsSourceContext.makeEmptyTuple();

    // Assert
    assertNull(actualMakeEmptyTupleResult.getJoinInfo());
    List<SQLQueryResultColumn> columnsList = actualMakeEmptyTupleResult.getColumnsList();
    assertTrue(columnsList.isEmpty());
    assertSame(connectionInfo, actualMakeEmptyTupleResult.getConnection());
    assertSame(sqlQueryRowsSourceContext, actualMakeEmptyTupleResult.getRowsSources());
    assertSame(columnsList, actualMakeEmptyTupleResult.getPseudoColumnsList());
  }

  /**
   * Test {@link SQLQueryRowsSourceContext#makeTuple(List, List)} with {@code columns}, {@code
   * pseudoColumns}.
   *
   * <p>Method under test: {@link SQLQueryRowsSourceContext#makeTuple(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLQueryRowsDataContext SQLQueryRowsSourceContext.makeTuple(List, List)"})
  public void testMakeTupleWithColumnsPseudoColumns() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext sqlQueryRowsSourceContext =
        new SQLQueryRowsSourceContext(connectionInfo);
    ArrayList<SQLQueryResultColumn> columns = new ArrayList<>();

    // Act
    SQLQueryRowsDataContext actualMakeTupleResult =
        sqlQueryRowsSourceContext.makeTuple(columns, new ArrayList<>());

    // Assert
    assertNull(actualMakeTupleResult.getJoinInfo());
    assertTrue(actualMakeTupleResult.getColumnsList().isEmpty());
    assertTrue(actualMakeTupleResult.getPseudoColumnsList().isEmpty());
    assertSame(connectionInfo, actualMakeTupleResult.getConnection());
    assertSame(sqlQueryRowsSourceContext, actualMakeTupleResult.getRowsSources());
  }

  /**
   * Test {@link SQLQueryRowsSourceContext#makeTuple(SQLQueryRowsSourceModel, Pair)} with {@code
   * source}, {@code columnsAndPseudoColumns}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return JoinInfo is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryRowsSourceContext#makeTuple(SQLQueryRowsSourceModel,
   * Pair)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryRowsDataContext SQLQueryRowsSourceContext.makeTuple(SQLQueryRowsSourceModel, Pair)"
  })
  public void testMakeTupleWithSourceColumnsAndPseudoColumns_whenNull_thenReturnJoinInfoIsNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext sqlQueryRowsSourceContext =
        new SQLQueryRowsSourceContext(connectionInfo);
    ArrayList<SQLQueryResultColumn> sqlQueryResultColumnList = new ArrayList<>();
    Pair<List<SQLQueryResultColumn>, List<SQLQueryResultPseudoColumn>> columnsAndPseudoColumns =
        new Pair<>(sqlQueryResultColumnList, new ArrayList<>());

    // Act
    SQLQueryRowsDataContext actualMakeTupleResult =
        sqlQueryRowsSourceContext.makeTuple(null, columnsAndPseudoColumns);

    // Assert
    assertNull(actualMakeTupleResult.getJoinInfo());
    assertTrue(actualMakeTupleResult.getColumnsList().isEmpty());
    assertTrue(actualMakeTupleResult.getPseudoColumnsList().isEmpty());
    assertSame(connectionInfo, actualMakeTupleResult.getConnection());
    assertSame(sqlQueryRowsSourceContext, actualMakeTupleResult.getRowsSources());
  }

  /**
   * Test {@link SQLQueryRowsSourceContext#makeTuple(SQLQueryRowsSourceModel, List, List)} with
   * {@code source}, {@code columns}, {@code pseudoColumns}.
   *
   * <p>Method under test: {@link SQLQueryRowsSourceContext#makeTuple(SQLQueryRowsSourceModel, List,
   * List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryRowsDataContext SQLQueryRowsSourceContext.makeTuple(SQLQueryRowsSourceModel, List, List)"
  })
  public void testMakeTupleWithSourceColumnsPseudoColumns() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext sqlQueryRowsSourceContext =
        new SQLQueryRowsSourceContext(connectionInfo);
    ArrayList<SQLQueryResultColumn> columns = new ArrayList<>();

    // Act
    SQLQueryRowsDataContext actualMakeTupleResult =
        sqlQueryRowsSourceContext.makeTuple(null, columns, new ArrayList<>());

    // Assert
    assertNull(actualMakeTupleResult.getJoinInfo());
    assertTrue(actualMakeTupleResult.getColumnsList().isEmpty());
    assertTrue(actualMakeTupleResult.getPseudoColumnsList().isEmpty());
    assertSame(connectionInfo, actualMakeTupleResult.getConnection());
    assertSame(sqlQueryRowsSourceContext, actualMakeTupleResult.getRowsSources());
  }

  /**
   * Test {@link SQLQueryRowsSourceContext#makeJoinTuple(List, List, JoinInfo)}.
   *
   * <p>Method under test: {@link SQLQueryRowsSourceContext#makeJoinTuple(List, List, JoinInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryRowsDataContext SQLQueryRowsSourceContext.makeJoinTuple(List, List, JoinInfo)"
  })
  public void testMakeJoinTuple() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    HashSet<String> knownColumnNames = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo =
        new SQLQueryConnectionDummyContext(dialect, knownColumnNames, new HashSet<>());
    SQLQueryRowsSourceContext sqlQueryRowsSourceContext =
        new SQLQueryRowsSourceContext(connectionInfo);
    ArrayList<SQLQueryResultColumn> columns = new ArrayList<>();
    ArrayList<SQLQueryResultPseudoColumn> pseudoColumns = new ArrayList<>();
    SQLDialect dialect2 = mock(SQLDialect.class);
    HashSet<String> knownColumnNames2 = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo2 =
        new SQLQueryConnectionDummyContext(dialect2, knownColumnNames2, new HashSet<>());
    SQLQueryRowsSourceContext rowsSources = new SQLQueryRowsSourceContext(connectionInfo2);
    ArrayList<SQLQueryResultColumn> columns2 = new ArrayList<>();

    SQLQueryRowsDataContext left =
        new SQLQueryRowsDataContext(rowsSources, columns2, new ArrayList<>());
    SQLDialect dialect3 = mock(SQLDialect.class);
    HashSet<String> knownColumnNames3 = new HashSet<>();

    SQLQueryConnectionDummyContext connectionInfo3 =
        new SQLQueryConnectionDummyContext(dialect3, knownColumnNames3, new HashSet<>());
    SQLQueryRowsSourceContext rowsSources2 = new SQLQueryRowsSourceContext(connectionInfo3);
    ArrayList<SQLQueryResultColumn> columns3 = new ArrayList<>();

    SQLQueryRowsDataContext right =
        new SQLQueryRowsDataContext(rowsSources2, columns3, new ArrayList<>());

    JoinInfo joinInfo = new JoinInfo(left, right);

    // Act
    SQLQueryRowsDataContext actualMakeJoinTupleResult =
        sqlQueryRowsSourceContext.makeJoinTuple(columns, pseudoColumns, joinInfo);

    // Assert
    assertTrue(actualMakeJoinTupleResult.getColumnsList().isEmpty());
    assertTrue(actualMakeJoinTupleResult.getPseudoColumnsList().isEmpty());
    assertSame(connectionInfo, actualMakeJoinTupleResult.getConnection());
    assertSame(joinInfo, actualMakeJoinTupleResult.getJoinInfo());
    assertSame(sqlQueryRowsSourceContext, actualMakeJoinTupleResult.getRowsSources());
  }
}
