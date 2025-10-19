package org.jkiss.dbeaver.model.sql.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.data.DBDDataReceiver;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCExecutionPurpose;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatistics;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.SubTaskProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLQuery;
import org.jkiss.dbeaver.model.sql.SQLScriptCommitType;
import org.jkiss.dbeaver.model.sql.SQLScriptContext;
import org.jkiss.dbeaver.model.sql.SQLScriptElement;
import org.jkiss.dbeaver.model.sql.SQLScriptErrorHandling;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SQLScriptProcessorDiffblueTest {
  @Mock private DBCExecutionContext dBCExecutionContext;

  @Mock private DBDDataReceiver dBDDataReceiver;

  @Mock private List<SQLScriptElement> list;

  @Mock private Log log;

  @Mock private SQLScriptContext sQLScriptContext;

  @InjectMocks private SQLScriptProcessor sQLScriptProcessor;

  /**
   * Test {@link SQLScriptProcessor#SQLScriptProcessor(DBCExecutionContext, List, SQLScriptContext,
   * DBDDataReceiver, Log)}.
   *
   * <ul>
   *   <li>Given {@link SQLQuery#SQLQuery(DBPDataSource, String)} with dataSource is {@link
   *       DBPDataSource} and {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptProcessor#SQLScriptProcessor(DBCExecutionContext, List,
   * SQLScriptContext, DBDDataReceiver, Log)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLScriptProcessor.<init>(DBCExecutionContext, List, SQLScriptContext, DBDDataReceiver, Log)"
  })
  public void testNewSQLScriptProcessor_givenSQLQueryWithDataSourceIsDBPDataSourceAndText() {
    // Arrange
    ArrayList<SQLScriptElement> queries = new ArrayList<>();
    queries.add(new SQLQuery(mock(DBPDataSource.class), "Text"));

    // Act
    SQLScriptProcessor actualSqlScriptProcessor =
        new SQLScriptProcessor(
            dBCExecutionContext, queries, sQLScriptContext, dBDDataReceiver, log);

    // Assert
    DBCStatistics totalStatistics = actualSqlScriptProcessor.getTotalStatistics();
    assertNull(totalStatistics.getQueryText());
    assertNull(totalStatistics.getError());
    assertNull(totalStatistics.getMessages());
    assertNull(totalStatistics.getWarnings());
    assertEquals(-1L, totalStatistics.getRowsFetched());
    assertEquals(-1L, totalStatistics.getRowsUpdated());
    assertEquals(0, totalStatistics.getStatementsCount());
    assertEquals(0L, totalStatistics.getExecuteTime());
    assertEquals(0L, totalStatistics.getFetchTime());
    assertEquals(0L, totalStatistics.getTotalTime());
    assertEquals(SQLScriptCommitType.AUTOCOMMIT, actualSqlScriptProcessor.getCommitType());
    assertEquals(SQLScriptErrorHandling.STOP_ROLLBACK, actualSqlScriptProcessor.getErrorHandling());
    assertTrue(totalStatistics.getInfo().isEmpty());
    assertTrue(totalStatistics.isEmpty());
  }

  /**
   * Test {@link SQLScriptProcessor#SQLScriptProcessor(DBCExecutionContext, List, SQLScriptContext,
   * DBDDataReceiver, Log)}.
   *
   * <ul>
   *   <li>Given {@link SQLQuery#SQLQuery(DBPDataSource, String)} with dataSource is {@link
   *       DBPDataSource} and {@code Text}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptProcessor#SQLScriptProcessor(DBCExecutionContext, List,
   * SQLScriptContext, DBDDataReceiver, Log)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLScriptProcessor.<init>(DBCExecutionContext, List, SQLScriptContext, DBDDataReceiver, Log)"
  })
  public void testNewSQLScriptProcessor_givenSQLQueryWithDataSourceIsDBPDataSourceAndText2() {
    // Arrange
    ArrayList<SQLScriptElement> queries = new ArrayList<>();
    queries.add(new SQLQuery(mock(DBPDataSource.class), "Text"));
    queries.add(new SQLQuery(mock(DBPDataSource.class), "Text"));

    // Act
    SQLScriptProcessor actualSqlScriptProcessor =
        new SQLScriptProcessor(
            dBCExecutionContext, queries, sQLScriptContext, dBDDataReceiver, log);

    // Assert
    DBCStatistics totalStatistics = actualSqlScriptProcessor.getTotalStatistics();
    assertNull(totalStatistics.getQueryText());
    assertNull(totalStatistics.getError());
    assertNull(totalStatistics.getMessages());
    assertNull(totalStatistics.getWarnings());
    assertEquals(-1L, totalStatistics.getRowsFetched());
    assertEquals(-1L, totalStatistics.getRowsUpdated());
    assertEquals(0, totalStatistics.getStatementsCount());
    assertEquals(0L, totalStatistics.getExecuteTime());
    assertEquals(0L, totalStatistics.getFetchTime());
    assertEquals(0L, totalStatistics.getTotalTime());
    assertEquals(SQLScriptCommitType.AUTOCOMMIT, actualSqlScriptProcessor.getCommitType());
    assertEquals(SQLScriptErrorHandling.STOP_ROLLBACK, actualSqlScriptProcessor.getErrorHandling());
    assertTrue(totalStatistics.getInfo().isEmpty());
    assertTrue(totalStatistics.isEmpty());
  }

  /**
   * Test {@link SQLScriptProcessor#SQLScriptProcessor(DBCExecutionContext, List, SQLScriptContext,
   * DBDDataReceiver, Log)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptProcessor#SQLScriptProcessor(DBCExecutionContext, List,
   * SQLScriptContext, DBDDataReceiver, Log)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLScriptProcessor.<init>(DBCExecutionContext, List, SQLScriptContext, DBDDataReceiver, Log)"
  })
  public void testNewSQLScriptProcessor_whenArrayList() {
    // Arrange and Act
    SQLScriptProcessor actualSqlScriptProcessor =
        new SQLScriptProcessor(
            dBCExecutionContext, new ArrayList<>(), sQLScriptContext, dBDDataReceiver, log);

    // Assert
    DBCStatistics totalStatistics = actualSqlScriptProcessor.getTotalStatistics();
    assertNull(totalStatistics.getQueryText());
    assertNull(totalStatistics.getError());
    assertNull(totalStatistics.getMessages());
    assertNull(totalStatistics.getWarnings());
    assertEquals(-1L, totalStatistics.getRowsFetched());
    assertEquals(-1L, totalStatistics.getRowsUpdated());
    assertEquals(0, totalStatistics.getStatementsCount());
    assertEquals(0L, totalStatistics.getExecuteTime());
    assertEquals(0L, totalStatistics.getFetchTime());
    assertEquals(0L, totalStatistics.getTotalTime());
    assertEquals(SQLScriptCommitType.AUTOCOMMIT, actualSqlScriptProcessor.getCommitType());
    assertEquals(SQLScriptErrorHandling.STOP_ROLLBACK, actualSqlScriptProcessor.getErrorHandling());
    assertTrue(totalStatistics.getInfo().isEmpty());
    assertTrue(totalStatistics.isEmpty());
  }

  /**
   * Test {@link SQLScriptProcessor#runScript(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionContext} {@link DBCExecutionContext#isConnected()} return {@code
   *       false}.
   *   <li>Then calls {@link List#iterator()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptProcessor#runScript(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptProcessor.runScript(DBRProgressMonitor)"})
  public void testRunScript_givenDBCExecutionContextIsConnectedReturnFalse_thenCallsIterator()
      throws DBCException {
    // Arrange
    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.isLoggingEnabled()).thenReturn(false);
    doNothing().when(dbcSession).close();
    when(dBCExecutionContext.isConnected()).thenReturn(false);
    when(dBCExecutionContext.openSession(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBCExecutionPurpose>any(),
            Mockito.<String>any()))
        .thenReturn(dbcSession);
    when(list.size()).thenReturn(3);

    ArrayList<SQLScriptElement> sqlScriptElementList = new ArrayList<>();
    when(list.iterator()).thenReturn(sqlScriptElementList.iterator());

    // Act
    sQLScriptProcessor.runScript(new LoggingProgressMonitor());

    // Assert
    verify(list).iterator();
    verify(list, atLeast(1)).size();
    verify(dbcSession).close();
    verify(dBCExecutionContext).isConnected();
    verify(dBCExecutionContext)
        .openSession(
            isA(DBRProgressMonitor.class), eq(DBCExecutionPurpose.USER_SCRIPT), eq("SQL Query"));
    verify(dbcSession, atLeast(1)).isLoggingEnabled();
  }

  /**
   * Test {@link SQLScriptProcessor#runScript(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBCSession} {@link DBCSession#isLoggingEnabled()} return {@code false}.
   *   <li>Then calls {@link List#iterator()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptProcessor#runScript(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptProcessor.runScript(DBRProgressMonitor)"})
  public void testRunScript_givenDBCSessionIsLoggingEnabledReturnFalse_thenCallsIterator()
      throws DBCException {
    // Arrange
    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.isLoggingEnabled()).thenReturn(false);
    doNothing().when(dbcSession).close();
    when(dBCExecutionContext.isConnected()).thenReturn(true);
    when(dBCExecutionContext.openSession(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBCExecutionPurpose>any(),
            Mockito.<String>any()))
        .thenReturn(dbcSession);
    when(list.size()).thenReturn(3);

    ArrayList<SQLScriptElement> sqlScriptElementList = new ArrayList<>();
    when(list.iterator()).thenReturn(sqlScriptElementList.iterator());

    // Act
    sQLScriptProcessor.runScript(new LoggingProgressMonitor());

    // Assert
    verify(list).iterator();
    verify(list, atLeast(1)).size();
    verify(dbcSession).close();
    verify(dBCExecutionContext).isConnected();
    verify(dBCExecutionContext)
        .openSession(
            isA(DBRProgressMonitor.class), eq(DBCExecutionPurpose.USER_SCRIPT), eq("SQL Query"));
    verify(dbcSession, atLeast(1)).isLoggingEnabled();
  }

  /**
   * Test {@link SQLScriptProcessor#runScript(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link SubTaskProgressMonitor#SubTaskProgressMonitor(DBRProgressMonitor)} with
   *       original is {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptProcessor#runScript(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptProcessor.runScript(DBRProgressMonitor)"})
  public void testRunScript_whenSubTaskProgressMonitorWithOriginalIsLoggingProgressMonitor()
      throws DBCException {
    // Arrange
    DBCSession dbcSession = mock(DBCSession.class);
    when(dbcSession.isLoggingEnabled()).thenReturn(false);
    doNothing().when(dbcSession).close();
    when(dBCExecutionContext.isConnected()).thenReturn(false);
    when(dBCExecutionContext.openSession(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBCExecutionPurpose>any(),
            Mockito.<String>any()))
        .thenReturn(dbcSession);
    when(list.size()).thenReturn(3);

    ArrayList<SQLScriptElement> sqlScriptElementList = new ArrayList<>();
    when(list.iterator()).thenReturn(sqlScriptElementList.iterator());

    // Act
    sQLScriptProcessor.runScript(new SubTaskProgressMonitor(new LoggingProgressMonitor()));

    // Assert
    verify(list).iterator();
    verify(list, atLeast(1)).size();
    verify(dbcSession).close();
    verify(dBCExecutionContext).isConnected();
    verify(dBCExecutionContext)
        .openSession(
            isA(DBRProgressMonitor.class), eq(DBCExecutionPurpose.USER_SCRIPT), eq("SQL Query"));
    verify(dbcSession, atLeast(1)).isLoggingEnabled();
  }
}
