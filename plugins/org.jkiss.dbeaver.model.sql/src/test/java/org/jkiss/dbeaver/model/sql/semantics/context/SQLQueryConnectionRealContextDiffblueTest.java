package org.jkiss.dbeaver.model.sql.semantics.context;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.parser.SQLIdentifierDetector;
import org.jkiss.dbeaver.model.sql.semantics.model.select.SQLQueryRowsSourceModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLQueryConnectionRealContextDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLQueryConnectionRealContext#SQLQueryConnectionRealContext(SQLDialect,
   *       SQLIdentifierDetector, DBCExecutionContext, boolean, Map, Function)}
   *   <li>{@link SQLQueryConnectionRealContext#isDummy()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLQueryConnectionRealContext.<init>(SQLDialect, SQLIdentifierDetector, DBCExecutionContext, boolean, Map, Function)",
    "boolean SQLQueryConnectionRealContext.isDummy()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    SQLIdentifierDetector identifierDetector = new SQLIdentifierDetector(mock(SQLDialect.class));
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act
    SQLQueryConnectionRealContext actualSqlQueryConnectionRealContext =
        new SQLQueryConnectionRealContext(
            dialect,
            identifierDetector,
            executionContext,
            true,
            new HashMap<>(),
            mock(Function.class));

    // Assert
    assertFalse(actualSqlQueryConnectionRealContext.isDummy());
  }

  /**
   * Test {@link SQLQueryConnectionRealContext#obtainRowsetPseudoColumns(SQLQueryRowsSourceModel)}.
   *
   * <ul>
   *   <li>Given {@link Function} {@link Function#apply(Object)} return {@link
   *       ArrayList#ArrayList()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionRealContext#obtainRowsetPseudoColumns(SQLQueryRowsSourceModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionRealContext.obtainRowsetPseudoColumns(SQLQueryRowsSourceModel)"
  })
  public void testObtainRowsetPseudoColumns_givenFunctionApplyReturnArrayList_thenReturnEmpty() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});
    SQLIdentifierDetector identifierDetector = new SQLIdentifierDetector(dialect);

    Function<SQLQueryRowsSourceModel, List<SQLQueryResultPseudoColumn>>
        rowsetPseudoColumnsProvider = mock(Function.class);
    when(rowsetPseudoColumnsProvider.apply(Mockito.<SQLQueryRowsSourceModel>any()))
        .thenReturn(new ArrayList<>());
    SQLDialect dialect2 = mock(SQLDialect.class);
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    SQLQueryConnectionRealContext sqlQueryConnectionRealContext =
        new SQLQueryConnectionRealContext(
            dialect2,
            identifierDetector,
            executionContext,
            true,
            new HashMap<>(),
            rowsetPseudoColumnsProvider);

    // Act
    List<SQLQueryResultPseudoColumn> actualObtainRowsetPseudoColumnsResult =
        sqlQueryConnectionRealContext.obtainRowsetPseudoColumns(null);

    // Assert
    verify(rowsetPseudoColumnsProvider).apply(isNull());
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertTrue(actualObtainRowsetPseudoColumnsResult.isEmpty());
  }

  /**
   * Test {@link SQLQueryConnectionRealContext#obtainRowsetPseudoColumns(SQLQueryRowsSourceModel)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionRealContext#obtainRowsetPseudoColumns(SQLQueryRowsSourceModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionRealContext.obtainRowsetPseudoColumns(SQLQueryRowsSourceModel)"
  })
  public void testObtainRowsetPseudoColumns_thenThrowUnsupportedOperationException() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});
    SQLIdentifierDetector identifierDetector = new SQLIdentifierDetector(dialect);

    Function<SQLQueryRowsSourceModel, List<SQLQueryResultPseudoColumn>>
        rowsetPseudoColumnsProvider = mock(Function.class);
    when(rowsetPseudoColumnsProvider.apply(Mockito.<SQLQueryRowsSourceModel>any()))
        .thenThrow(new UnsupportedOperationException());
    SQLDialect dialect2 = mock(SQLDialect.class);
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    SQLQueryConnectionRealContext sqlQueryConnectionRealContext =
        new SQLQueryConnectionRealContext(
            dialect2,
            identifierDetector,
            executionContext,
            true,
            new HashMap<>(),
            rowsetPseudoColumnsProvider);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> sqlQueryConnectionRealContext.obtainRowsetPseudoColumns(null));
    verify(rowsetPseudoColumnsProvider).apply(isNull());
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
  }

  /**
   * Test {@link SQLQueryConnectionRealContext#findRealObjectsImpl(DBRProgressMonitor, List)}.
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionRealContext#findRealObjectsImpl(DBRProgressMonitor, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionRealContext.findRealObjectsImpl(DBRProgressMonitor, List)"
  })
  public void testFindRealObjectsImpl() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});
    SQLIdentifierDetector identifierDetector = new SQLIdentifierDetector(dialect);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenThrow(new UnsupportedOperationException());
    SQLDialect dialect2 = mock(SQLDialect.class);

    SQLQueryConnectionRealContext sqlQueryConnectionRealContext =
        new SQLQueryConnectionRealContext(
            dialect2,
            identifierDetector,
            executionContext,
            true,
            new HashMap<>(),
            mock(Function.class));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> sqlQueryConnectionRealContext.findRealObjectsImpl(monitor, new ArrayList<>()));
    verify(executionContext).getDataSource();
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
  }

  /**
   * Test {@link SQLQueryConnectionRealContext#findRealObjectsImpl(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionRealContext#findRealObjectsImpl(DBRProgressMonitor, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionRealContext.findRealObjectsImpl(DBRProgressMonitor, List)"
  })
  public void testFindRealObjectsImpl_given42_whenArrayListAdd42() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});
    SQLIdentifierDetector identifierDetector = new SQLIdentifierDetector(dialect);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    SQLDialect dialect2 = mock(SQLDialect.class);

    SQLQueryConnectionRealContext sqlQueryConnectionRealContext =
        new SQLQueryConnectionRealContext(
            dialect2,
            identifierDetector,
            executionContext,
            true,
            new HashMap<>(),
            mock(Function.class));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<String> objectName = new ArrayList<>();
    objectName.add("42");
    objectName.add(
        "Semantic analyser should never be used for databases, which doesn't support table lookup");

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> sqlQueryConnectionRealContext.findRealObjectsImpl(monitor, objectName));
    verify(executionContext).getDataSource();
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
  }

  /**
   * Test {@link SQLQueryConnectionRealContext#findRealObjectsImpl(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionContext} {@link DBCExecutionContext#getDataSource()} return
   *       {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLQueryConnectionRealContext#findRealObjectsImpl(DBRProgressMonitor, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List SQLQueryConnectionRealContext.findRealObjectsImpl(DBRProgressMonitor, List)"
  })
  public void testFindRealObjectsImpl_givenDBCExecutionContextGetDataSourceReturnDBPDataSource() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});
    SQLIdentifierDetector identifierDetector = new SQLIdentifierDetector(dialect);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));
    SQLDialect dialect2 = mock(SQLDialect.class);

    SQLQueryConnectionRealContext sqlQueryConnectionRealContext =
        new SQLQueryConnectionRealContext(
            dialect2,
            identifierDetector,
            executionContext,
            true,
            new HashMap<>(),
            mock(Function.class));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> sqlQueryConnectionRealContext.findRealObjectsImpl(monitor, new ArrayList<>()));
    verify(executionContext).getDataSource();
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
  }

  /**
   * Test {@link SQLQueryConnectionRealContext#resolveGlobalPseudoColumn(String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLQueryConnectionRealContext#resolveGlobalPseudoColumn(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLQueryResultPseudoColumn SQLQueryConnectionRealContext.resolveGlobalPseudoColumn(String)"
  })
  public void testResolveGlobalPseudoColumn_thenReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getStructSeparator()).thenReturn('A');
    when(dialect.getIdentifierQuoteStrings())
        .thenReturn(new String[][] {new String[] {"Identifier Quote Strings"}});
    when(dialect.getStringQuoteStrings())
        .thenReturn(new String[][] {new String[] {"String Quote Strings"}});
    SQLIdentifierDetector identifierDetector = new SQLIdentifierDetector(dialect);
    SQLDialect dialect2 = mock(SQLDialect.class);
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    SQLQueryConnectionRealContext sqlQueryConnectionRealContext =
        new SQLQueryConnectionRealContext(
            dialect2,
            identifierDetector,
            executionContext,
            true,
            new HashMap<>(),
            mock(Function.class));

    // Act
    SQLQueryResultPseudoColumn actualResolveGlobalPseudoColumnResult =
        sqlQueryConnectionRealContext.resolveGlobalPseudoColumn("Name");

    // Assert
    verify(dialect).getIdentifierQuoteStrings();
    verify(dialect).getStringQuoteStrings();
    verify(dialect).getStructSeparator();
    assertNull(actualResolveGlobalPseudoColumnResult);
  }
}
