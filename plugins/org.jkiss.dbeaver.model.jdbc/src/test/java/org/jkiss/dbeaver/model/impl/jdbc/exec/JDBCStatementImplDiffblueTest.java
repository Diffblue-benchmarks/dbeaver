package org.jkiss.dbeaver.model.impl.jdbc.exec;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCDatabaseMetaData;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCResultSet;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCStatement;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSourceInfo;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCExecutionContext;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCRemoteInstance;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.VoidProgressMonitor;
import org.jkiss.dbeaver.model.sql.DBSQLException;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCStatementImplDiffblueTest {
  /**
   * Test {@link JDBCStatementImpl#JDBCStatementImpl(JDBCSession, JDBCObjectSupplier, String,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#JDBCStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCStatementImpl.<init>(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testNewJDBCStatementImpl_givenSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> new JDBCStatementImpl<>(connection, original, "Query String", true));
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#JDBCStatementImpl(JDBCSession, JDBCObjectSupplier, String,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link Statement}.
   *   <li>Then return {@code Query String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#JDBCStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCStatementImpl.<init>(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testNewJDBCStatementImpl_givenStatement_thenReturnQueryString()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    // Act
    JDBCStatementImpl<Statement> actualJdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Assert
    verify(original).get();
    assertEquals("Query String", actualJdbcStatementImpl.getQueryString());
    assertNull(actualJdbcStatementImpl.getBlockThread());
    assertNull(actualJdbcStatementImpl.getStatementWarnings());
    assertNull(actualJdbcStatementImpl.getGeneratedKeys());
    assertNull(actualJdbcStatementImpl.getWarnings());
    assertNull(actualJdbcStatementImpl.getStatementSource());
    assertNull(actualJdbcStatementImpl.getResultSet());
    assertEquals(0, actualJdbcStatementImpl.getFetchDirection());
    assertEquals(0, actualJdbcStatementImpl.getFetchSize());
    assertEquals(0, actualJdbcStatementImpl.getMaxFieldSize());
    assertEquals(0, actualJdbcStatementImpl.getMaxRows());
    assertEquals(0, actualJdbcStatementImpl.getQueryTimeout());
    assertEquals(0, actualJdbcStatementImpl.getResultSetConcurrency());
    assertEquals(0, actualJdbcStatementImpl.getResultSetHoldability());
    assertEquals(0, actualJdbcStatementImpl.getResultSetType());
    assertEquals(0, actualJdbcStatementImpl.getUpdateCount());
    assertEquals(0L, actualJdbcStatementImpl.getLargeMaxRows());
    assertEquals(0L, actualJdbcStatementImpl.getLargeUpdateCount());
    assertEquals(0L, actualJdbcStatementImpl.getUpdateRowCount());
    assertFalse(actualJdbcStatementImpl.getMoreResults());
    assertFalse(actualJdbcStatementImpl.isCloseOnCompletion());
    assertFalse(actualJdbcStatementImpl.isClosed());
    assertFalse(actualJdbcStatementImpl.isPoolable());
    assertFalse(actualJdbcStatementImpl.isQMLoggingEnabled());
    assertFalse(actualJdbcStatementImpl.isStatementClosed());
    assertTrue(actualJdbcStatementImpl.disableLogging);
    assertSame(connection, actualJdbcStatementImpl.getSession());
    assertSame(connection, actualJdbcStatementImpl.getConnection());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCStatementImpl#handleExecuteResult(int)}
   *   <li>{@link JDBCStatementImpl#setQueryString(String)}
   *   <li>{@link JDBCStatementImpl#toString()}
   *   <li>{@link JDBCStatementImpl#getOriginal()}
   *   <li>{@link JDBCStatementImpl#getQueryString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Statement JDBCStatementImpl.getOriginal()",
    "String JDBCStatementImpl.getQueryString()",
    "int JDBCStatementImpl.handleExecuteResult(int)",
    "void JDBCStatementImpl.setQueryString(String)",
    "String JDBCStatementImpl.toString()"
  })
  public void testGettersAndSetters() throws SQLException {
    // Arrange
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(
            mock(JDBCSession.class), mock(JDBCObjectSupplier.class), "Query String", true);

    // Act
    int actualHandleExecuteResultResult = jdbcStatementImpl.handleExecuteResult(1);
    jdbcStatementImpl.setQueryString("Query");
    String actualToStringResult = jdbcStatementImpl.toString();
    Statement actualOriginal = jdbcStatementImpl.getOriginal();

    // Assert
    assertEquals("JDBC Statement [Query]", actualToStringResult);
    assertEquals("Query", jdbcStatementImpl.getQueryString());
    assertNull(actualOriginal);
    assertEquals(1, actualHandleExecuteResultResult);
  }

  /**
   * Test {@link JDBCStatementImpl#isQMLoggingEnabled()}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#isQMLoggingEnabled()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isQMLoggingEnabled()"})
  public void testIsQMLoggingEnabled() throws SQLException {
    // Arrange
    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualIsQMLoggingEnabledResult = jdbcStatementImpl.isQMLoggingEnabled();

    // Assert
    verify(original).get();
    assertFalse(actualIsQMLoggingEnabledResult);
  }

  /**
   * Test {@link JDBCStatementImpl#startBlock()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getProgressMonitor()} return {@link
   *       LoggingProgressMonitor#LoggingProgressMonitor()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#startBlock()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.startBlock()"})
  public void testStartBlock_givenJDBCSessionGetProgressMonitorReturnLoggingProgressMonitor()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    jdbcStatementImpl.startBlock();

    // Assert
    verify(connection).getProgressMonitor();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#startBlock()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#startBlock()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.startBlock()"})
  public void testStartBlock_thenThrowUnsupportedOperationException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenThrow(new UnsupportedOperationException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.startBlock());
    verify(connection).getProgressMonitor();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#endBlock()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getProgressMonitor()} return {@link
   *       LoggingProgressMonitor#LoggingProgressMonitor()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#endBlock()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.endBlock()"})
  public void testEndBlock_givenJDBCSessionGetProgressMonitorReturnLoggingProgressMonitor()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    jdbcStatementImpl.endBlock();

    // Assert
    verify(connection).getProgressMonitor();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#endBlock()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getProgressMonitor()} return {@link
   *       VoidProgressMonitor} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#endBlock()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.endBlock()"})
  public void testEndBlock_givenJDBCSessionGetProgressMonitorReturnVoidProgressMonitor()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new VoidProgressMonitor());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    jdbcStatementImpl.endBlock();

    // Assert
    verify(connection).getProgressMonitor();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#endBlock()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#endBlock()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.endBlock()"})
  public void testEndBlock_thenThrowUnsupportedOperationException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenThrow(new UnsupportedOperationException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.endBlock());
    verify(connection).getProgressMonitor();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#cancelBlock(DBRProgressMonitor, Thread)}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCObjectSupplier#get()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#cancelBlock(DBRProgressMonitor, Thread)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.cancelBlock(DBRProgressMonitor, Thread)"})
  public void testCancelBlock_thenCallsGet() throws SQLException, DBException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    doNothing()
        .when(jdbcDataSource)
        .cancelStatementExecute(Mockito.<DBRProgressMonitor>any(), Mockito.<JDBCStatement>any());

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    jdbcStatementImpl.cancelBlock(monitor, new Thread());

    // Assert
    verify(original).get();
    verify(connection).getDataSource();
    verify(jdbcDataSource)
        .cancelStatementExecute(isA(DBRProgressMonitor.class), isA(JDBCStatement.class));
  }

  /**
   * Test {@link JDBCStatementImpl#getConnection()}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#getConnection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCSession JDBCStatementImpl.getConnection()"})
  public void testGetConnection() throws SQLException {
    // Arrange
    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.getConnection();

    // Assert
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#executeStatement()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code false}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.executeStatement()"})
  public void testExecuteStatement_givenDBPDriverIsThreadSafeDriverReturnFalse_thenReturnTrue()
      throws SQLException, DBCException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any())).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteStatementResult = jdbcStatementImpl.executeStatement();

    // Assert
    verify(statement).execute("Query String");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertTrue(actualExecuteStatementResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeStatement()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.executeStatement()"})
  public void testExecuteStatement_givenDBPDriverIsThreadSafeDriverReturnTrue_thenReturnTrue()
      throws SQLException, DBCException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any())).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteStatementResult = jdbcStatementImpl.executeStatement();

    // Assert
    verify(statement).execute("Query String");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertTrue(actualExecuteStatementResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeStatement()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#execute(String)} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.executeStatement()"})
  public void testExecuteStatement_givenStatementExecuteReturnFalse_thenReturnFalse()
      throws SQLException, DBCException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any())).thenReturn(false);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteStatementResult = jdbcStatementImpl.executeStatement();

    // Assert
    verify(statement).execute("Query String");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertFalse(actualExecuteStatementResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeStatement()}.
   *
   * <ul>
   *   <li>Then throw {@link DBSQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.executeStatement()"})
  public void testExecuteStatement_thenThrowDBSQLException() throws SQLException, DBCException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any())).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(DBSQLException.class, () -> jdbcStatementImpl.executeStatement());
    verify(statement).execute("Query String");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer2).getPreferenceStore();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource2).getContainer();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#addToBatch()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#addBatch(String)} does nothing.
   *   <li>Then calls {@link Statement#addBatch(String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#addToBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.addToBatch()"})
  public void testAddToBatch_givenStatementAddBatchDoesNothing_thenCallsAddBatch()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).addBatch(Mockito.<String>any());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.addToBatch();

    // Assert
    verify(statement).addBatch("Query String");
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#addToBatch()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#addBatch(String)} throw {@link
   *       UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#addToBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.addToBatch()"})
  public void testAddToBatch_givenStatementAddBatchThrowUnsupportedOperationException()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new UnsupportedOperationException()).when(statement).addBatch(Mockito.<String>any());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.addToBatch());
    verify(statement).addBatch("Query String");
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#addToBatch()}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCSession#getExecutionContext()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#addToBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.addToBatch()"})
  public void testAddToBatch_thenCallsGetExecutionContext() throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getExecutionContext()).thenThrow(new UnsupportedOperationException());

    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).addBatch(Mockito.<String>any());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.addToBatch());
    verify(statement).addBatch("Query String");
    verify(original).get();
    verify(connection).getExecutionContext();
  }

  /**
   * Test {@link JDBCStatementImpl#executeStatementBatch()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeStatementBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long[] JDBCStatementImpl.executeStatementBatch()"})
  public void testExecuteStatementBatch_givenDBPDriverIsThreadSafeDriverReturnFalse()
      throws SQLException, DBCException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeBatch()).thenReturn(new int[] {1, -1, 1, -1});

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    long[] actualExecuteStatementBatchResult = jdbcStatementImpl.executeStatementBatch();

    // Assert
    verify(statement).executeBatch();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertArrayEquals(new long[] {1L, -1L, 1L, -1L}, actualExecuteStatementBatchResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeStatementBatch()}.
   *
   * <ul>
   *   <li>Then return array of {@code long} with one and minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeStatementBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long[] JDBCStatementImpl.executeStatementBatch()"})
  public void testExecuteStatementBatch_thenReturnArrayOfLongWithOneAndMinusOne()
      throws SQLException, DBCException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeBatch()).thenReturn(new int[] {1, -1, 1, -1});

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    long[] actualExecuteStatementBatchResult = jdbcStatementImpl.executeStatementBatch();

    // Assert
    verify(statement).executeBatch();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertArrayEquals(new long[] {1L, -1L, 1L, -1L}, actualExecuteStatementBatchResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeStatementBatch()}.
   *
   * <ul>
   *   <li>Then throw {@link DBSQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeStatementBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long[] JDBCStatementImpl.executeStatementBatch()"})
  public void testExecuteStatementBatch_thenThrowDBSQLException()
      throws SQLException, DBCException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeBatch()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(DBSQLException.class, () -> jdbcStatementImpl.executeStatementBatch());
    verify(statement).executeBatch();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer2).getPreferenceStore();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource2).getContainer();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#openResultSet()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getResultSet()} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#openResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.openResultSet()"})
  public void testOpenResultSet_givenStatementGetResultSetReturnNull_thenReturnNull()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());

    Statement statement = mock(Statement.class);
    when(statement.getResultSet()).thenReturn(null);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    JDBCResultSet actualOpenResultSetResult = jdbcStatementImpl.openResultSet();

    // Assert
    verify(statement).getResultSet();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    assertNull(actualOpenResultSetResult);
  }

  /**
   * Test {@link JDBCStatementImpl#openResultSet()}.
   *
   * <ul>
   *   <li>Then return {@link JDBCResultSetImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#openResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.openResultSet()"})
  public void testOpenResultSet_thenReturnJDBCResultSetImpl() throws SQLException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());

    JDBCSession connection2 = mock(JDBCSession.class);
    when(connection2.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection2, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    Statement statement2 = mock(Statement.class);
    when(statement2.getResultSet()).thenReturn(jdbcResultSetCallable);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement2);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    JDBCResultSet actualOpenResultSetResult = jdbcStatementImpl.openResultSet();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(statement2).getResultSet();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(stmtSupplier).get();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection2).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertTrue(actualOpenResultSetResult instanceof JDBCResultSetImpl);
  }

  /**
   * Test {@link JDBCStatementImpl#openResultSet()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#openResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.openResultSet()"})
  public void testOpenResultSet_thenThrowUnsupportedOperationException()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());

    Statement statement = mock(Statement.class);
    when(statement.getResultSet()).thenThrow(new UnsupportedOperationException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.openResultSet());
    verify(statement).getResultSet();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#openGeneratedKeysResultSet()}.
   *
   * <ul>
   *   <li>Then return {@link JDBCResultSetImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#openGeneratedKeysResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.openGeneratedKeysResultSet()"})
  public void testOpenGeneratedKeysResultSet_thenReturnJDBCResultSetImpl()
      throws SQLException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource);

    JDBCSession connection2 = mock(JDBCSession.class);
    when(connection2.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection2, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    Statement statement2 = mock(Statement.class);
    when(statement2.getGeneratedKeys()).thenReturn(jdbcResultSetCallable);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement2);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    JDBCResultSet actualOpenGeneratedKeysResultSetResult =
        jdbcStatementImpl.openGeneratedKeysResultSet();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(statement2).getGeneratedKeys();
    verify(stmtSupplier).get();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection2).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertTrue(actualOpenGeneratedKeysResultSetResult instanceof JDBCResultSetImpl);
  }

  /**
   * Test {@link JDBCStatementImpl#openGeneratedKeysResultSet()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#openGeneratedKeysResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.openGeneratedKeysResultSet()"})
  public void testOpenGeneratedKeysResultSet_thenReturnNull() throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getGeneratedKeys()).thenReturn(null);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    JDBCResultSet actualOpenGeneratedKeysResultSetResult =
        jdbcStatementImpl.openGeneratedKeysResultSet();

    // Assert
    verify(statement).getGeneratedKeys();
    verify(original).get();
    assertNull(actualOpenGeneratedKeysResultSetResult);
  }

  /**
   * Test {@link JDBCStatementImpl#openGeneratedKeysResultSet()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#openGeneratedKeysResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.openGeneratedKeysResultSet()"})
  public void testOpenGeneratedKeysResultSet_thenThrowUnsupportedOperationException()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getGeneratedKeys()).thenThrow(new UnsupportedOperationException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> jdbcStatementImpl.openGeneratedKeysResultSet());
    verify(statement).getGeneratedKeys();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getUpdateRowCount()}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#getUpdateRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCStatementImpl.getUpdateRowCount()"})
  public void testGetUpdateRowCount() throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getUpdateCount()).thenThrow(new UnsupportedOperationException());
    when(statement.getLargeUpdateCount()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.getUpdateRowCount());
    verify(statement).getLargeUpdateCount();
    verify(statement).getUpdateCount();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getUpdateRowCount()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getLargeUpdateCount()} return minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getUpdateRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCStatementImpl.getUpdateRowCount()"})
  public void testGetUpdateRowCount_givenStatementGetLargeUpdateCountReturnMinusOne()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getLargeUpdateCount()).thenReturn(-1L);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    long actualUpdateRowCount = jdbcStatementImpl.getUpdateRowCount();

    // Assert
    verify(statement).getLargeUpdateCount();
    verify(original).get();
    assertEquals(-1L, actualUpdateRowCount);
  }

  /**
   * Test {@link JDBCStatementImpl#getUpdateRowCount()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getLargeUpdateCount()} return zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getUpdateRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCStatementImpl.getUpdateRowCount()"})
  public void testGetUpdateRowCount_givenStatementGetLargeUpdateCountReturnZero_thenReturnZero()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getLargeUpdateCount()).thenReturn(0L);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    long actualUpdateRowCount = jdbcStatementImpl.getUpdateRowCount();

    // Assert
    verify(statement).getLargeUpdateCount();
    verify(original).get();
    assertEquals(0L, actualUpdateRowCount);
  }

  /**
   * Test {@link JDBCStatementImpl#getUpdateRowCount()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getUpdateCount()} return minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getUpdateRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCStatementImpl.getUpdateRowCount()"})
  public void testGetUpdateRowCount_givenStatementGetUpdateCountReturnMinusOne()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getUpdateCount()).thenReturn(-1);
    when(statement.getLargeUpdateCount()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    long actualUpdateRowCount = jdbcStatementImpl.getUpdateRowCount();

    // Assert
    verify(statement).getLargeUpdateCount();
    verify(statement).getUpdateCount();
    verify(original).get();
    assertEquals(-1L, actualUpdateRowCount);
  }

  /**
   * Test {@link JDBCStatementImpl#getUpdateRowCount()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getUpdateCount()} return zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getUpdateRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCStatementImpl.getUpdateRowCount()"})
  public void testGetUpdateRowCount_givenStatementGetUpdateCountReturnZero_thenReturnZero()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getUpdateCount()).thenReturn(0);
    when(statement.getLargeUpdateCount()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    long actualUpdateRowCount = jdbcStatementImpl.getUpdateRowCount();

    // Assert
    verify(statement).getLargeUpdateCount();
    verify(statement).getUpdateCount();
    verify(original).get();
    assertEquals(0L, actualUpdateRowCount);
  }

  /**
   * Test {@link JDBCStatementImpl#getUpdateRowCount()}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCSession#getExecutionContext()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getUpdateRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCStatementImpl.getUpdateRowCount()"})
  public void testGetUpdateRowCount_thenCallsGetExecutionContext()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getExecutionContext()).thenThrow(new UnsupportedOperationException());

    Statement statement = mock(Statement.class);
    when(statement.getUpdateCount()).thenThrow(new SQLException());
    when(statement.getLargeUpdateCount()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.getUpdateRowCount());
    verify(statement).getLargeUpdateCount();
    verify(statement).getUpdateCount();
    verify(original).get();
    verify(connection).getExecutionContext();
  }

  /**
   * Test {@link JDBCStatementImpl#nextResults()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getMoreResults()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#nextResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.nextResults()"})
  public void testNextResults_givenStatementGetMoreResultsReturnTrue_thenReturnTrue()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getMoreResults()).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualNextResultsResult = jdbcStatementImpl.nextResults();

    // Assert
    verify(statement).getMoreResults();
    verify(original).get();
    assertTrue(actualNextResultsResult);
  }

  /**
   * Test {@link JDBCStatementImpl#nextResults()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getMoreResults()} throw {@link
   *       UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#nextResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.nextResults()"})
  public void testNextResults_givenStatementGetMoreResultsThrowUnsupportedOperationException()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getMoreResults()).thenThrow(new UnsupportedOperationException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.nextResults());
    verify(statement).getMoreResults();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#nextResults()}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCSession#getExecutionContext()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#nextResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.nextResults()"})
  public void testNextResults_thenCallsGetExecutionContext() throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getExecutionContext()).thenThrow(new UnsupportedOperationException());

    Statement statement = mock(Statement.class);
    when(statement.getMoreResults()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.nextResults());
    verify(statement).getMoreResults();
    verify(original).get();
    verify(connection).getExecutionContext();
  }

  /**
   * Test {@link JDBCStatementImpl#setLimit(long, long)}.
   *
   * <ul>
   *   <li>Given {@link JDBCDataSource} {@link JDBCDataSource#getInfo()} throw {@link
   *       UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setLimit(long, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setLimit(long, long)"})
  public void testSetLimit_givenJDBCDataSourceGetInfoThrowUnsupportedOperationException()
      throws SQLException, DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getInfo()).thenThrow(new UnsupportedOperationException());

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.setLimit(1L, 1L));
    verify(original).get();
    verify(connection).getDataSource();
    verify(jdbcDataSource).getInfo();
  }

  /**
   * Test {@link JDBCStatementImpl#setLimit(long, long)}.
   *
   * <ul>
   *   <li>Given {@link JDBCDataSourceInfo#JDBCDataSourceInfo(JDBCDatabaseMetaData)} with metaData
   *       is {@link JDBCDatabaseMetaData}.
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setLimit(long, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setLimit(long, long)"})
  public void testSetLimit_givenJDBCDataSourceInfoWithMetaDataIsJDBCDatabaseMetaData_whenZero()
      throws SQLException, DBCException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));
    new JDBCDataSourceInfo(metaData);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.setLimit(1L, 0L);

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setLimit(long, long)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} throw {@link
   *       UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setLimit(long, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setLimit(long, long)"})
  public void testSetLimit_givenJDBCSessionGetDataSourceThrowUnsupportedOperationException()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenThrow(new UnsupportedOperationException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.setLimit(0L, 1L));
    verify(original).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#setLimit(long, long)}.
   *
   * <ul>
   *   <li>When {@link Long#MAX_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setLimit(long, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setLimit(long, long)"})
  public void testSetLimit_whenMax_value() throws SQLException, DBCException {
    // Arrange
    JDBCDatabaseMetaData metaData = mock(JDBCDatabaseMetaData.class);
    when(metaData.supportsTransactionIsolationLevel(anyInt())).thenReturn(true);
    when(metaData.supportsBatchUpdates()).thenReturn(true);
    when(metaData.supportsTransactions()).thenReturn(true);
    when(metaData.getCatalogTerm()).thenReturn("Catalog Term");
    when(metaData.getDatabaseProductName()).thenReturn("Database Product Name");
    when(metaData.getDatabaseProductVersion()).thenReturn("1.0.2");
    when(metaData.getDriverName()).thenReturn("Driver Name");
    when(metaData.getDriverVersion()).thenReturn("1.0.2");
    when(metaData.getProcedureTerm()).thenReturn("Procedure Term");
    when(metaData.getSchemaTerm()).thenReturn("Schema Term");
    when(metaData.getDataSource()).thenReturn(mock(JDBCDataSource.class));
    new JDBCDataSourceInfo(metaData);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.setLimit(Long.MAX_VALUE, 1L);

    // Assert
    verify(metaData).getCatalogTerm();
    verify(metaData).getDatabaseProductName();
    verify(metaData).getDatabaseProductVersion();
    verify(metaData).getDriverName();
    verify(metaData).getDriverVersion();
    verify(metaData).getProcedureTerm();
    verify(metaData).getSchemaTerm();
    verify(metaData).supportsBatchUpdates();
    verify(metaData, atLeast(1)).supportsTransactionIsolationLevel(anyInt());
    verify(metaData).supportsTransactions();
    verify(metaData).getDataSource();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#makeResultSet(ResultSet)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCResultSetImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#makeResultSet(ResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.makeResultSet(ResultSet)"})
  public void testMakeResultSet_thenReturnJDBCResultSetImpl() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    JDBCSession connection2 = mock(JDBCSession.class);
    when(connection2.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection2, stmtSupplier, "Query", true);
    JDBCResultSetCallable resultSet = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCResultSet actualMakeResultSetResult = jdbcStatementImpl.makeResultSet(resultSet);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection2).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertTrue(actualMakeResultSetResult instanceof JDBCResultSetImpl);
  }

  /**
   * Test {@link JDBCStatementImpl#makeResultSet(ResultSet)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#makeResultSet(ResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.makeResultSet(ResultSet)"})
  public void testMakeResultSet_whenNull_thenReturnNull() throws SQLException {
    // Arrange
    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    JDBCResultSet actualMakeResultSetResult = jdbcStatementImpl.makeResultSet(null);

    // Assert
    verify(original).get();
    assertNull(actualMakeResultSetResult);
  }

  /**
   * Test {@link JDBCStatementImpl#createResultSetImpl(ResultSet)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCResultSetImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#createResultSetImpl(ResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.createResultSetImpl(ResultSet)"})
  public void testCreateResultSetImpl_thenReturnJDBCResultSetImpl() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    JDBCSession connection2 = mock(JDBCSession.class);
    when(connection2.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection2, stmtSupplier, "Query", true);
    JDBCResultSetCallable resultSet = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCResultSet actualCreateResultSetImplResult =
        jdbcStatementImpl.createResultSetImpl(resultSet);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection2).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertTrue(actualCreateResultSetImplResult instanceof JDBCResultSetImpl);
  }

  /**
   * Test {@link JDBCStatementImpl#handleExecuteResult(boolean)} with {@code boolean}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#handleExecuteResult(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.handleExecuteResult(boolean)"})
  public void testHandleExecuteResultWithBoolean_whenFalse_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualHandleExecuteResultResult = jdbcStatementImpl.handleExecuteResult(false);

    // Assert
    verify(original).get();
    assertFalse(actualHandleExecuteResultResult);
  }

  /**
   * Test {@link JDBCStatementImpl#handleExecuteResult(boolean)} with {@code boolean}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#handleExecuteResult(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.handleExecuteResult(boolean)"})
  public void testHandleExecuteResultWithBoolean_whenTrue_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualHandleExecuteResultResult = jdbcStatementImpl.handleExecuteResult(true);

    // Assert
    verify(original).get();
    assertTrue(actualHandleExecuteResultResult);
  }

  /**
   * Test {@link JDBCStatementImpl#handleExecuteError(Throwable)}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#handleExecuteError(Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLException JDBCStatementImpl.handleExecuteError(Throwable)"})
  public void testHandleExecuteError() throws SQLException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> jdbcStatementImpl.handleExecuteError(new Throwable()));
    verify(dbpDataSourceContainer).getPreferenceStore();
    verify(original).get();
    verify(connection).getDataSource();
    verify(jdbcDataSource).getContainer();
  }

  /**
   * Test {@link JDBCStatementImpl#beforeExecute()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code false}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDriver()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#beforeExecute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.beforeExecute()"})
  public void testBeforeExecute_givenDBPDriverIsThreadSafeDriverReturnFalse_thenCallsGetDriver()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    jdbcStatementImpl.beforeExecute();

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection).getProgressMonitor();
    verify(original).get();
    verify(connection).getExecutionContext();
    verify(connection).setBlockThread(isA(Thread.class));
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#beforeExecute()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   *   <li>Then calls {@link DBPDataSourceContainer#getDriver()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#beforeExecute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.beforeExecute()"})
  public void testBeforeExecute_givenDBPDriverIsThreadSafeDriverReturnTrue_thenCallsGetDriver()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    jdbcStatementImpl.beforeExecute();

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection).getProgressMonitor();
    verify(original).get();
    verify(connection).getExecutionContext();
    verify(connection).setBlockThread(isA(Thread.class));
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#afterExecute()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getProgressMonitor()} return {@link
   *       VoidProgressMonitor} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#afterExecute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.afterExecute()"})
  public void testAfterExecute_givenJDBCSessionGetProgressMonitorReturnVoidProgressMonitor()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new VoidProgressMonitor());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    jdbcStatementImpl.afterExecute();

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection).getProgressMonitor();
    verify(original).get();
    verify(connection).getExecutionContext();
    verify(connection).setBlockThread(isNull());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#afterExecute()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getDriver()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#afterExecute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.afterExecute()"})
  public void testAfterExecute_thenCallsGetDriver() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    jdbcStatementImpl.afterExecute();

    // Assert
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection).getProgressMonitor();
    verify(original).get();
    verify(connection).getExecutionContext();
    verify(connection).setBlockThread(isNull());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#afterExecute()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#afterExecute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.afterExecute()"})
  public void testAfterExecute_thenThrowUnsupportedOperationException() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenThrow(new UnsupportedOperationException());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(mock(Statement.class));

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.afterExecute());
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection).getProgressMonitor();
    verify(original).get();
    verify(connection).getExecutionContext();
    verify(connection).setBlockThread(isNull());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String, int)"})
  public void testExecuteWithSqlAutoGeneratedKeys_givenDBPDriverIsThreadSafeDriverReturnFalse()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any(), anyInt())).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteResult = jdbcStatementImpl.execute("Sql", 1);

    // Assert
    verify(statement).execute("Sql", 1);
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertTrue(actualExecuteResult);
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String, int)"})
  public void testExecuteWithSqlAutoGeneratedKeys_thenReturnFalse() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any(), anyInt())).thenReturn(false);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteResult = jdbcStatementImpl.execute("Sql", 1);

    // Assert
    verify(statement).execute("Sql", 1);
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertFalse(actualExecuteResult);
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String, int)"})
  public void testExecuteWithSqlAutoGeneratedKeys_thenReturnTrue() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any(), anyInt())).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteResult = jdbcStatementImpl.execute("Sql", 1);

    // Assert
    verify(statement).execute("Sql", 1);
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertTrue(actualExecuteResult);
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String, int)"})
  public void testExecuteWithSqlAutoGeneratedKeys_thenThrowUnsupportedOperationException()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any(), anyInt())).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.execute("Sql", 1));
    verify(statement).execute("Sql", 1);
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer2).getPreferenceStore();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource2).getContainer();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String, int[])} with {@code sql}, {@code columnIndexes}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String, int[])"})
  public void testExecuteWithSqlColumnIndexes_givenDBPDriverIsThreadSafeDriverReturnFalse()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any(), Mockito.<int[]>any())).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteResult = jdbcStatementImpl.execute("Sql", new int[] {1, -1, 1, -1});

    // Assert
    verify(statement).execute(eq("Sql"), isA(int[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertTrue(actualExecuteResult);
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String, int[])} with {@code sql}, {@code columnIndexes}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#execute(String, int[])} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String, int[])"})
  public void testExecuteWithSqlColumnIndexes_givenStatementExecuteReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any(), Mockito.<int[]>any())).thenReturn(false);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteResult = jdbcStatementImpl.execute("Sql", new int[] {1, -1, 1, -1});

    // Assert
    verify(statement).execute(eq("Sql"), isA(int[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertFalse(actualExecuteResult);
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String, int[])} with {@code sql}, {@code columnIndexes}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String, int[])"})
  public void testExecuteWithSqlColumnIndexes_thenReturnTrue() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any(), Mockito.<int[]>any())).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteResult = jdbcStatementImpl.execute("Sql", new int[] {1, -1, 1, -1});

    // Assert
    verify(statement).execute(eq("Sql"), isA(int[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertTrue(actualExecuteResult);
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String, int[])} with {@code sql}, {@code columnIndexes}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String, int[])"})
  public void testExecuteWithSqlColumnIndexes_thenThrowUnsupportedOperationException()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> jdbcStatementImpl.execute("Sql", new int[] {1, -1, 1, -1}));
    verify(statement).execute(eq("Sql"), isA(int[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer2).getPreferenceStore();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource2).getContainer();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String, String[])} with {@code sql}, {@code columnNames}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String, String[])"})
  public void testExecuteWithSqlColumnNames_givenDBPDriverIsThreadSafeDriverReturnFalse()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any(), Mockito.<String[]>any())).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteResult = jdbcStatementImpl.execute("Sql", new String[] {"Column Names"});

    // Assert
    verify(statement).execute(eq("Sql"), isA(String[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertTrue(actualExecuteResult);
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String, String[])} with {@code sql}, {@code columnNames}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#execute(String, String[])} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String, String[])"})
  public void testExecuteWithSqlColumnNames_givenStatementExecuteReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any(), Mockito.<String[]>any())).thenReturn(false);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteResult = jdbcStatementImpl.execute("Sql", new String[] {"Column Names"});

    // Assert
    verify(statement).execute(eq("Sql"), isA(String[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertFalse(actualExecuteResult);
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String, String[])} with {@code sql}, {@code columnNames}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String, String[])"})
  public void testExecuteWithSqlColumnNames_thenReturnTrue() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any(), Mockito.<String[]>any())).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteResult = jdbcStatementImpl.execute("Sql", new String[] {"Column Names"});

    // Assert
    verify(statement).execute(eq("Sql"), isA(String[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertTrue(actualExecuteResult);
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String, String[])} with {@code sql}, {@code columnNames}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String, String[])"})
  public void testExecuteWithSqlColumnNames_thenThrowUnsupportedOperationException()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> jdbcStatementImpl.execute("Sql", new String[] {"Column Names"}));
    verify(statement).execute(eq("Sql"), isA(String[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer2).getPreferenceStore();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource2).getContainer();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code false}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String)"})
  public void testExecuteWithSql_givenDBPDriverIsThreadSafeDriverReturnFalse_thenReturnTrue()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any())).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteResult = jdbcStatementImpl.execute("Sql");

    // Assert
    verify(statement).execute("Sql");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertTrue(actualExecuteResult);
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String)"})
  public void testExecuteWithSql_givenDBPDriverIsThreadSafeDriverReturnTrue_thenReturnTrue()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any())).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteResult = jdbcStatementImpl.execute("Sql");

    // Assert
    verify(statement).execute("Sql");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertTrue(actualExecuteResult);
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#execute(String)} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String)"})
  public void testExecuteWithSql_givenStatementExecuteReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any())).thenReturn(false);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    boolean actualExecuteResult = jdbcStatementImpl.execute("Sql");

    // Assert
    verify(statement).execute("Sql");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertFalse(actualExecuteResult);
  }

  /**
   * Test {@link JDBCStatementImpl#execute(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#execute(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.execute(String)"})
  public void testExecuteWithSql_thenThrowUnsupportedOperationException() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.execute(Mockito.<String>any())).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.execute("Sql"));
    verify(statement).execute("Sql");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer2).getPreferenceStore();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource2).getContainer();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#executeQuery(String)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#executeQuery(String)} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeQuery(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.executeQuery(String)"})
  public void testExecuteQuery_givenStatementExecuteQueryReturnNull_thenReturnNull()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeQuery(Mockito.<String>any())).thenReturn(null);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    JDBCResultSet actualExecuteQueryResult = jdbcStatementImpl.executeQuery("Sql");

    // Assert
    verify(statement).executeQuery("Sql");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertNull(actualExecuteQueryResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeQuery(String)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCResultSetImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeQuery(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.executeQuery(String)"})
  public void testExecuteQuery_thenReturnJDBCResultSetImpl() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    JDBCSession connection2 = mock(JDBCSession.class);
    when(connection2.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection2, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    Statement statement2 = mock(Statement.class);
    when(statement2.executeQuery(Mockito.<String>any())).thenReturn(jdbcResultSetCallable);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement2);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    JDBCResultSet actualExecuteQueryResult = jdbcStatementImpl.executeQuery("Sql");

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(statement2).executeQuery("Sql");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(stmtSupplier).get();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection2).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(jdbcDataSource2).getJdbcFactory();
    verify(instance, atLeast(1)).getDataSource();
    assertTrue(actualExecuteQueryResult instanceof JDBCResultSetImpl);
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
  }

  /**
   * Test {@link JDBCStatementImpl#executeQuery(String)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeQuery(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.executeQuery(String)"})
  public void testExecuteQuery_thenThrowUnsupportedOperationException() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeQuery(Mockito.<String>any())).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.executeQuery("Sql"));
    verify(statement).executeQuery("Sql");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer2).getPreferenceStore();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource2).getContainer();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#executeUpdate(String)} with {@code sql}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeUpdate(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.executeUpdate(String)"})
  public void testExecuteUpdateWithSql() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeUpdate(Mockito.<String>any())).thenReturn(1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    int actualExecuteUpdateResult = jdbcStatementImpl.executeUpdate("Sql");

    // Assert
    verify(statement).executeUpdate("Sql");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertEquals(1, actualExecuteUpdateResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeUpdate(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeUpdate(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.executeUpdate(String, int)"})
  public void testExecuteUpdateWithSqlAutoGeneratedKeys() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeUpdate(Mockito.<String>any(), anyInt())).thenReturn(1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    int actualExecuteUpdateResult = jdbcStatementImpl.executeUpdate("Sql", 1);

    // Assert
    verify(statement).executeUpdate("Sql", 1);
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertEquals(1, actualExecuteUpdateResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeUpdate(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeUpdate(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.executeUpdate(String, int)"})
  public void testExecuteUpdateWithSqlAutoGeneratedKeys2() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeUpdate(Mockito.<String>any(), anyInt())).thenReturn(1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    int actualExecuteUpdateResult = jdbcStatementImpl.executeUpdate("Sql", 1);

    // Assert
    verify(statement).executeUpdate("Sql", 1);
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertEquals(1, actualExecuteUpdateResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeUpdate(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeUpdate(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.executeUpdate(String, int)"})
  public void testExecuteUpdateWithSqlAutoGeneratedKeys_thenThrowUnsupportedOperationException()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeUpdate(Mockito.<String>any(), anyInt())).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> jdbcStatementImpl.executeUpdate("Sql", 1));
    verify(statement).executeUpdate("Sql", 1);
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer2).getPreferenceStore();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource2).getContainer();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#executeUpdate(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeUpdate(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.executeUpdate(String, int[])"})
  public void testExecuteUpdateWithSqlColumnIndexes() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeUpdate(Mockito.<String>any(), Mockito.<int[]>any())).thenReturn(1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    int actualExecuteUpdateResult =
        jdbcStatementImpl.executeUpdate("Sql", new int[] {1, -1, 1, -1});

    // Assert
    verify(statement).executeUpdate(eq("Sql"), isA(int[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertEquals(1, actualExecuteUpdateResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeUpdate(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeUpdate(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.executeUpdate(String, int[])"})
  public void testExecuteUpdateWithSqlColumnIndexes2() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeUpdate(Mockito.<String>any(), Mockito.<int[]>any())).thenReturn(1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    int actualExecuteUpdateResult =
        jdbcStatementImpl.executeUpdate("Sql", new int[] {1, -1, 1, -1});

    // Assert
    verify(statement).executeUpdate(eq("Sql"), isA(int[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertEquals(1, actualExecuteUpdateResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeUpdate(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeUpdate(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.executeUpdate(String, int[])"})
  public void testExecuteUpdateWithSqlColumnIndexes_thenThrowUnsupportedOperationException()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeUpdate(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> jdbcStatementImpl.executeUpdate("Sql", new int[] {1, -1, 1, -1}));
    verify(statement).executeUpdate(eq("Sql"), isA(int[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer2).getPreferenceStore();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource2).getContainer();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#executeUpdate(String, String[])} with {@code sql}, {@code
   * columnNames}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeUpdate(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.executeUpdate(String, String[])"})
  public void testExecuteUpdateWithSqlColumnNames() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeUpdate(Mockito.<String>any(), Mockito.<String[]>any())).thenReturn(1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    int actualExecuteUpdateResult =
        jdbcStatementImpl.executeUpdate("Sql", new String[] {"Column Names"});

    // Assert
    verify(statement).executeUpdate(eq("Sql"), isA(String[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertEquals(1, actualExecuteUpdateResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeUpdate(String, String[])} with {@code sql}, {@code
   * columnNames}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeUpdate(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.executeUpdate(String, String[])"})
  public void testExecuteUpdateWithSqlColumnNames_givenDBPDriverIsThreadSafeDriverReturnFalse()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeUpdate(Mockito.<String>any(), Mockito.<String[]>any())).thenReturn(1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    int actualExecuteUpdateResult =
        jdbcStatementImpl.executeUpdate("Sql", new String[] {"Column Names"});

    // Assert
    verify(statement).executeUpdate(eq("Sql"), isA(String[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertEquals(1, actualExecuteUpdateResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeUpdate(String, String[])} with {@code sql}, {@code
   * columnNames}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeUpdate(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.executeUpdate(String, String[])"})
  public void testExecuteUpdateWithSqlColumnNames_thenThrowUnsupportedOperationException()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeUpdate(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> jdbcStatementImpl.executeUpdate("Sql", new String[] {"Column Names"}));
    verify(statement).executeUpdate(eq("Sql"), isA(String[].class));
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer2).getPreferenceStore();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource2).getContainer();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#executeUpdate(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeUpdate(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.executeUpdate(String)"})
  public void testExecuteUpdateWithSql_givenDBPDriverIsThreadSafeDriverReturnFalse()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeUpdate(Mockito.<String>any())).thenReturn(1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    int actualExecuteUpdateResult = jdbcStatementImpl.executeUpdate("Sql");

    // Assert
    verify(statement).executeUpdate("Sql");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals("Sql", jdbcStatementImpl.getQueryString());
    assertEquals(1, actualExecuteUpdateResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeUpdate(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeUpdate(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.executeUpdate(String)"})
  public void testExecuteUpdateWithSql_thenThrowUnsupportedOperationException()
      throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeUpdate(Mockito.<String>any())).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.executeUpdate("Sql"));
    verify(statement).executeUpdate("Sql");
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer2).getPreferenceStore();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource2).getContainer();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#executeBatch()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] JDBCStatementImpl.executeBatch()"})
  public void testExecuteBatch_givenDBPDriverIsThreadSafeDriverReturnFalse() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(false);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeBatch()).thenReturn(new int[] {1, -1, 1, -1});

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    int[] actualExecuteBatchResult = jdbcStatementImpl.executeBatch();

    // Assert
    verify(statement).executeBatch();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertArrayEquals(new int[] {1, -1, 1, -1}, actualExecuteBatchResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeBatch()}.
   *
   * <ul>
   *   <li>Then return array of {@code int} with one and minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] JDBCStatementImpl.executeBatch()"})
  public void testExecuteBatch_thenReturnArrayOfIntWithOneAndMinusOne() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeBatch()).thenReturn(new int[] {1, -1, 1, -1});

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    int[] actualExecuteBatchResult = jdbcStatementImpl.executeBatch();

    // Assert
    verify(statement).executeBatch();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertArrayEquals(new int[] {1, -1, 1, -1}, actualExecuteBatchResult);
  }

  /**
   * Test {@link JDBCStatementImpl#executeBatch()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#executeBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int[] JDBCStatementImpl.executeBatch()"})
  public void testExecuteBatch_thenThrowUnsupportedOperationException() throws SQLException {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext jdbcExecutionContext = new JDBCExecutionContext(instance, "Purpose");

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.getPreferenceStore())
        .thenThrow(new UnsupportedOperationException());

    JDBCDataSource jdbcDataSource2 = mock(JDBCDataSource.class);
    when(jdbcDataSource2.getContainer()).thenReturn(dbpDataSourceContainer2);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource2);
    when(connection.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    doNothing().when(connection).setBlockThread(Mockito.<Thread>any());
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    Statement statement = mock(Statement.class);
    when(statement.executeBatch()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.executeBatch());
    verify(statement).executeBatch();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDataSourceContainer2).getPreferenceStore();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource2).getContainer();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCStatementImpl#close()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#close()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.close()"})
  public void testClose_givenStatementCloseDoesNothing() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).close();

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    try (JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true)) {}

    // Act and Assert
    verify(statement).close();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#close()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#close()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.close()"})
  public void testClose_givenStatementCloseThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).close();

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    try (JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true)) {}

    // Act and Assert
    verify(statement).close();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#close()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#close()} throw {@link
   *       UnsupportedOperationException#UnsupportedOperationException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.close()"})
  public void testClose_givenStatementCloseThrowUnsupportedOperationException()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new UnsupportedOperationException()).when(statement).close();

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    try (JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true)) {}

    // Act and Assert
    verify(statement).close();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getMaxFieldSize()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getMaxFieldSize()} return three.
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getMaxFieldSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getMaxFieldSize()"})
  public void testGetMaxFieldSize_givenStatementGetMaxFieldSizeReturnThree_thenReturnThree()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getMaxFieldSize()).thenReturn(3);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    int actualMaxFieldSize = jdbcStatementImpl.getMaxFieldSize();

    // Assert
    verify(statement).getMaxFieldSize();
    verify(original).get();
    assertEquals(3, actualMaxFieldSize);
  }

  /**
   * Test {@link JDBCStatementImpl#getMaxFieldSize()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getMaxFieldSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getMaxFieldSize()"})
  public void testGetMaxFieldSize_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getMaxFieldSize()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getMaxFieldSize());
    verify(statement).getMaxFieldSize();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setMaxFieldSize(int)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#setMaxFieldSize(int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setMaxFieldSize(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setMaxFieldSize(int)"})
  public void testSetMaxFieldSize_givenStatementSetMaxFieldSizeDoesNothing() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).setMaxFieldSize(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.setMaxFieldSize(3);

    // Assert
    verify(statement).setMaxFieldSize(3);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setMaxFieldSize(int)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setMaxFieldSize(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setMaxFieldSize(int)"})
  public void testSetMaxFieldSize_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).setMaxFieldSize(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.setMaxFieldSize(3));
    verify(statement).setMaxFieldSize(3);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getMaxRows()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getMaxRows()} return three.
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getMaxRows()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getMaxRows()"})
  public void testGetMaxRows_givenStatementGetMaxRowsReturnThree_thenReturnThree()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getMaxRows()).thenReturn(3);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    int actualMaxRows = jdbcStatementImpl.getMaxRows();

    // Assert
    verify(statement).getMaxRows();
    verify(original).get();
    assertEquals(3, actualMaxRows);
  }

  /**
   * Test {@link JDBCStatementImpl#getMaxRows()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getMaxRows()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getMaxRows()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getMaxRows()"})
  public void testGetMaxRows_givenStatementGetMaxRowsThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getMaxRows()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getMaxRows());
    verify(statement).getMaxRows();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setMaxRows(int)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#setMaxRows(int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setMaxRows(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setMaxRows(int)"})
  public void testSetMaxRows_givenStatementSetMaxRowsDoesNothing() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).setMaxRows(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.setMaxRows(3);

    // Assert
    verify(statement).setMaxRows(3);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setMaxRows(int)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#setMaxRows(int)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setMaxRows(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setMaxRows(int)"})
  public void testSetMaxRows_givenStatementSetMaxRowsThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).setMaxRows(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.setMaxRows(3));
    verify(statement).setMaxRows(3);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setEscapeProcessing(boolean)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#setEscapeProcessing(boolean)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setEscapeProcessing(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setEscapeProcessing(boolean)"})
  public void testSetEscapeProcessing_givenStatementSetEscapeProcessingDoesNothing()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).setEscapeProcessing(anyBoolean());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.setEscapeProcessing(true);

    // Assert
    verify(statement).setEscapeProcessing(true);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setEscapeProcessing(boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setEscapeProcessing(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setEscapeProcessing(boolean)"})
  public void testSetEscapeProcessing_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).setEscapeProcessing(anyBoolean());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.setEscapeProcessing(true));
    verify(statement).setEscapeProcessing(true);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getQueryTimeout()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getQueryTimeout()} return ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getQueryTimeout()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getQueryTimeout()"})
  public void testGetQueryTimeout_givenStatementGetQueryTimeoutReturnTen_thenReturnTen()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getQueryTimeout()).thenReturn(10);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    int actualQueryTimeout = jdbcStatementImpl.getQueryTimeout();

    // Assert
    verify(statement).getQueryTimeout();
    verify(original).get();
    assertEquals(10, actualQueryTimeout);
  }

  /**
   * Test {@link JDBCStatementImpl#getQueryTimeout()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getQueryTimeout()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getQueryTimeout()"})
  public void testGetQueryTimeout_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getQueryTimeout()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getQueryTimeout());
    verify(statement).getQueryTimeout();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setQueryTimeout(int)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#setQueryTimeout(int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setQueryTimeout(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setQueryTimeout(int)"})
  public void testSetQueryTimeout_givenStatementSetQueryTimeoutDoesNothing() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).setQueryTimeout(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.setQueryTimeout(1);

    // Assert
    verify(statement).setQueryTimeout(1);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setQueryTimeout(int)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setQueryTimeout(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setQueryTimeout(int)"})
  public void testSetQueryTimeout_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).setQueryTimeout(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.setQueryTimeout(1));
    verify(statement).setQueryTimeout(1);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#cancel()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#cancel()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#cancel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.cancel()"})
  public void testCancel_givenStatementCancelDoesNothing() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).cancel();

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.cancel();

    // Assert
    verify(statement).cancel();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#cancel()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#cancel()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#cancel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.cancel()"})
  public void testCancel_givenStatementCancelThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).cancel();

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.cancel());
    verify(statement).cancel();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getWarnings()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getWarnings()} return {@link
   *       SQLWarning#SQLWarning()}.
   *   <li>Then return {@link SQLWarning#SQLWarning()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLWarning JDBCStatementImpl.getWarnings()"})
  public void testGetWarnings_givenStatementGetWarningsReturnSQLWarning_thenReturnSQLWarning()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    SQLWarning sqlWarning = new SQLWarning();
    when(statement.getWarnings()).thenReturn(sqlWarning);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    SQLWarning actualWarnings = jdbcStatementImpl.getWarnings();

    // Assert
    verify(statement).getWarnings();
    verify(original).get();
    assertSame(sqlWarning, actualWarnings);
  }

  /**
   * Test {@link JDBCStatementImpl#getWarnings()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getWarnings()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLWarning JDBCStatementImpl.getWarnings()"})
  public void testGetWarnings_givenStatementGetWarningsThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getWarnings()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getWarnings());
    verify(statement).getWarnings();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#clearWarnings()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#clearWarnings()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#clearWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.clearWarnings()"})
  public void testClearWarnings_givenStatementClearWarningsDoesNothing() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).clearWarnings();

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.clearWarnings();

    // Assert
    verify(statement).clearWarnings();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#clearWarnings()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#clearWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.clearWarnings()"})
  public void testClearWarnings_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).clearWarnings();

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.clearWarnings());
    verify(statement).clearWarnings();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setCursorName(String)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#setCursorName(String)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setCursorName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setCursorName(String)"})
  public void testSetCursorName_givenStatementSetCursorNameDoesNothing() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).setCursorName(Mockito.<String>any());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.setCursorName("Name");

    // Assert
    verify(statement).setCursorName("Name");
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setCursorName(String)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setCursorName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setCursorName(String)"})
  public void testSetCursorName_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).setCursorName(Mockito.<String>any());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.setCursorName("Name"));
    verify(statement).setCursorName("Name");
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getResultSet()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getResultSet()} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.getResultSet()"})
  public void testGetResultSet_givenStatementGetResultSetReturnNull_thenReturnNull()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getResultSet()).thenReturn(null);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    JDBCResultSet actualResultSet = jdbcStatementImpl.getResultSet();

    // Assert
    verify(statement).getResultSet();
    verify(original).get();
    assertNull(actualResultSet);
  }

  /**
   * Test {@link JDBCStatementImpl#getResultSet()}.
   *
   * <ul>
   *   <li>Then return {@link JDBCResultSetImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.getResultSet()"})
  public void testGetResultSet_thenReturnJDBCResultSetImpl() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource);

    JDBCSession connection2 = mock(JDBCSession.class);
    when(connection2.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection2, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    Statement statement2 = mock(Statement.class);
    when(statement2.getResultSet()).thenReturn(jdbcResultSetCallable);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement2);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    JDBCResultSet actualResultSet = jdbcStatementImpl.getResultSet();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(statement2).getResultSet();
    verify(stmtSupplier).get();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection2).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertTrue(actualResultSet instanceof JDBCResultSetImpl);
  }

  /**
   * Test {@link JDBCStatementImpl#getResultSet()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCStatementImpl.getResultSet()"})
  public void testGetResultSet_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getResultSet()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getResultSet());
    verify(statement).getResultSet();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getStatementWarnings()}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#getStatementWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Throwable[] JDBCStatementImpl.getStatementWarnings()"})
  public void testGetStatementWarnings() throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getWarnings()).thenThrow(new UnsupportedOperationException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> jdbcStatementImpl.getStatementWarnings());
    verify(statement).getWarnings();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getStatementWarnings()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getWarnings()} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getStatementWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Throwable[] JDBCStatementImpl.getStatementWarnings()"})
  public void testGetStatementWarnings_givenStatementGetWarningsReturnNull_thenReturnNull()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getWarnings()).thenReturn(null);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    Throwable[] actualStatementWarnings = jdbcStatementImpl.getStatementWarnings();

    // Assert
    verify(statement).getWarnings();
    verify(original).get();
    assertNull(actualStatementWarnings);
  }

  /**
   * Test {@link JDBCStatementImpl#getStatementWarnings()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getWarnings()} return {@link
   *       SQLWarning#SQLWarning()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getStatementWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Throwable[] JDBCStatementImpl.getStatementWarnings()"})
  public void testGetStatementWarnings_givenStatementGetWarningsReturnSQLWarning()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getWarnings()).thenReturn(new SQLWarning());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    Throwable[] actualStatementWarnings = jdbcStatementImpl.getStatementWarnings();

    // Assert
    verify(statement).getWarnings();
    verify(original).get();
    assertNull(actualStatementWarnings);
  }

  /**
   * Test {@link JDBCStatementImpl#getStatementWarnings()}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCSession#getExecutionContext()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getStatementWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Throwable[] JDBCStatementImpl.getStatementWarnings()"})
  public void testGetStatementWarnings_thenCallsGetExecutionContext()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getExecutionContext()).thenThrow(new UnsupportedOperationException());

    Statement statement = mock(Statement.class);
    when(statement.getWarnings()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> jdbcStatementImpl.getStatementWarnings());
    verify(statement).getWarnings();
    verify(original).get();
    verify(connection).getExecutionContext();
  }

  /**
   * Test {@link JDBCStatementImpl#setStatementTimeout(int)}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#setStatementTimeout(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setStatementTimeout(int)"})
  public void testSetStatementTimeout() throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new UnsupportedOperationException()).when(statement).setQueryTimeout(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> jdbcStatementImpl.setStatementTimeout(10));
    verify(statement).setQueryTimeout(10);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setStatementTimeout(int)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#setQueryTimeout(int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setStatementTimeout(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setStatementTimeout(int)"})
  public void testSetStatementTimeout_givenStatementSetQueryTimeoutDoesNothing()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).setQueryTimeout(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.setStatementTimeout(10);

    // Assert
    verify(statement).setQueryTimeout(10);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setStatementTimeout(int)}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCSession#getExecutionContext()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setStatementTimeout(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setStatementTimeout(int)"})
  public void testSetStatementTimeout_thenCallsGetExecutionContext()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getExecutionContext()).thenThrow(new UnsupportedOperationException());

    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).setQueryTimeout(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> jdbcStatementImpl.setStatementTimeout(10));
    verify(statement).setQueryTimeout(10);
    verify(original).get();
    verify(connection).getExecutionContext();
  }

  /**
   * Test {@link JDBCStatementImpl#setResultsFetchSize(int)}.
   *
   * <p>Method under test: {@link JDBCStatementImpl#setResultsFetchSize(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setResultsFetchSize(int)"})
  public void testSetResultsFetchSize() throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new UnsupportedOperationException()).when(statement).setFetchSize(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> jdbcStatementImpl.setResultsFetchSize(3));
    verify(statement).setFetchSize(3);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setResultsFetchSize(int)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#setFetchSize(int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setResultsFetchSize(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setResultsFetchSize(int)"})
  public void testSetResultsFetchSize_givenStatementSetFetchSizeDoesNothing()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).setFetchSize(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.setResultsFetchSize(3);

    // Assert
    verify(statement).setFetchSize(3);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setResultsFetchSize(int)}.
   *
   * <ul>
   *   <li>Then calls {@link JDBCSession#getExecutionContext()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setResultsFetchSize(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setResultsFetchSize(int)"})
  public void testSetResultsFetchSize_thenCallsGetExecutionContext()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getExecutionContext()).thenThrow(new UnsupportedOperationException());

    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).setFetchSize(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class, () -> jdbcStatementImpl.setResultsFetchSize(3));
    verify(statement).setFetchSize(3);
    verify(original).get();
    verify(connection).getExecutionContext();
  }

  /**
   * Test {@link JDBCStatementImpl#getUpdateCount()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getUpdateCount()} return minus one.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getUpdateCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getUpdateCount()"})
  public void testGetUpdateCount_givenStatementGetUpdateCountReturnMinusOne_thenReturnMinusOne()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getUpdateCount()).thenReturn(-1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    int actualUpdateCount = jdbcStatementImpl.getUpdateCount();

    // Assert
    verify(statement).getUpdateCount();
    verify(original).get();
    assertEquals(-1, actualUpdateCount);
  }

  /**
   * Test {@link JDBCStatementImpl#getUpdateCount()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getUpdateCount()} return zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getUpdateCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getUpdateCount()"})
  public void testGetUpdateCount_givenStatementGetUpdateCountReturnZero_thenReturnZero()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getUpdateCount()).thenReturn(0);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    int actualUpdateCount = jdbcStatementImpl.getUpdateCount();

    // Assert
    verify(statement).getUpdateCount();
    verify(original).get();
    assertEquals(0, actualUpdateCount);
  }

  /**
   * Test {@link JDBCStatementImpl#getUpdateCount()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getUpdateCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getUpdateCount()"})
  public void testGetUpdateCount_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getUpdateCount()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getUpdateCount());
    verify(statement).getUpdateCount();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getLargeUpdateCount()}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getLargeUpdateCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCStatementImpl.getLargeUpdateCount()"})
  public void testGetLargeUpdateCount_thenReturnMinusOne() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getLargeUpdateCount()).thenReturn(-1L);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    long actualLargeUpdateCount = jdbcStatementImpl.getLargeUpdateCount();

    // Assert
    verify(statement).getLargeUpdateCount();
    verify(original).get();
    assertEquals(-1L, actualLargeUpdateCount);
  }

  /**
   * Test {@link JDBCStatementImpl#getLargeUpdateCount()}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getLargeUpdateCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCStatementImpl.getLargeUpdateCount()"})
  public void testGetLargeUpdateCount_thenReturnZero() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getLargeUpdateCount()).thenReturn(0L);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    long actualLargeUpdateCount = jdbcStatementImpl.getLargeUpdateCount();

    // Assert
    verify(statement).getLargeUpdateCount();
    verify(original).get();
    assertEquals(0L, actualLargeUpdateCount);
  }

  /**
   * Test {@link JDBCStatementImpl#getLargeUpdateCount()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getLargeUpdateCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCStatementImpl.getLargeUpdateCount()"})
  public void testGetLargeUpdateCount_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getLargeUpdateCount()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getLargeUpdateCount());
    verify(statement).getLargeUpdateCount();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getMoreResults(int)} with {@code int}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getMoreResults(int)} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getMoreResults(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.getMoreResults(int)"})
  public void testGetMoreResultsWithInt_givenStatementGetMoreResultsReturnTrue_thenReturnTrue()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getMoreResults(anyInt())).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualMoreResults = jdbcStatementImpl.getMoreResults(1);

    // Assert
    verify(statement).getMoreResults(1);
    verify(original).get();
    assertTrue(actualMoreResults);
  }

  /**
   * Test {@link JDBCStatementImpl#getMoreResults(int)} with {@code int}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getMoreResults(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.getMoreResults(int)"})
  public void testGetMoreResultsWithInt_thenReturnFalse() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getMoreResults(anyInt())).thenReturn(false);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualMoreResults = jdbcStatementImpl.getMoreResults(1);

    // Assert
    verify(statement).getMoreResults(1);
    verify(original).get();
    assertFalse(actualMoreResults);
  }

  /**
   * Test {@link JDBCStatementImpl#getMoreResults(int)} with {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getMoreResults(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.getMoreResults(int)"})
  public void testGetMoreResultsWithInt_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getMoreResults(anyInt())).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getMoreResults(1));
    verify(statement).getMoreResults(1);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getMoreResults()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getMoreResults()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getMoreResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.getMoreResults()"})
  public void testGetMoreResults_givenStatementGetMoreResultsReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getMoreResults()).thenReturn(false);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualMoreResults = jdbcStatementImpl.getMoreResults();

    // Assert
    verify(statement).getMoreResults();
    verify(original).get();
    assertFalse(actualMoreResults);
  }

  /**
   * Test {@link JDBCStatementImpl#getMoreResults()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getMoreResults()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getMoreResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.getMoreResults()"})
  public void testGetMoreResults_givenStatementGetMoreResultsReturnTrue_thenReturnTrue()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getMoreResults()).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualMoreResults = jdbcStatementImpl.getMoreResults();

    // Assert
    verify(statement).getMoreResults();
    verify(original).get();
    assertTrue(actualMoreResults);
  }

  /**
   * Test {@link JDBCStatementImpl#getMoreResults()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getMoreResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.getMoreResults()"})
  public void testGetMoreResults_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getMoreResults()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getMoreResults());
    verify(statement).getMoreResults();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setFetchDirection(int)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#setFetchDirection(int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setFetchDirection(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setFetchDirection(int)"})
  public void testSetFetchDirection_givenStatementSetFetchDirectionDoesNothing()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).setFetchDirection(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.setFetchDirection(1);

    // Assert
    verify(statement).setFetchDirection(1);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setFetchDirection(int)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setFetchDirection(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setFetchDirection(int)"})
  public void testSetFetchDirection_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).setFetchDirection(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.setFetchDirection(1));
    verify(statement).setFetchDirection(1);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getFetchDirection()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getFetchDirection()} return one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getFetchDirection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getFetchDirection()"})
  public void testGetFetchDirection_givenStatementGetFetchDirectionReturnOne_thenReturnOne()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getFetchDirection()).thenReturn(1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    int actualFetchDirection = jdbcStatementImpl.getFetchDirection();

    // Assert
    verify(statement).getFetchDirection();
    verify(original).get();
    assertEquals(1, actualFetchDirection);
  }

  /**
   * Test {@link JDBCStatementImpl#getFetchDirection()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getFetchDirection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getFetchDirection()"})
  public void testGetFetchDirection_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getFetchDirection()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getFetchDirection());
    verify(statement).getFetchDirection();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setFetchSize(int)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#setFetchSize(int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setFetchSize(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setFetchSize(int)"})
  public void testSetFetchSize_givenStatementSetFetchSizeDoesNothing() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).setFetchSize(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.setFetchSize(1);

    // Assert
    verify(statement).setFetchSize(1);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setFetchSize(int)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setFetchSize(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setFetchSize(int)"})
  public void testSetFetchSize_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).setFetchSize(anyInt());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.setFetchSize(1));
    verify(statement).setFetchSize(1);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getFetchSize()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getFetchSize()} return three.
   *   <li>Then return three.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getFetchSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getFetchSize()"})
  public void testGetFetchSize_givenStatementGetFetchSizeReturnThree_thenReturnThree()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getFetchSize()).thenReturn(3);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    int actualFetchSize = jdbcStatementImpl.getFetchSize();

    // Assert
    verify(statement).getFetchSize();
    verify(original).get();
    assertEquals(3, actualFetchSize);
  }

  /**
   * Test {@link JDBCStatementImpl#getFetchSize()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getFetchSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getFetchSize()"})
  public void testGetFetchSize_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getFetchSize()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getFetchSize());
    verify(statement).getFetchSize();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getResultSetConcurrency()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getResultSetConcurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getResultSetConcurrency()"})
  public void testGetResultSetConcurrency_thenReturnOne() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getResultSetConcurrency()).thenReturn(1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    int actualResultSetConcurrency = jdbcStatementImpl.getResultSetConcurrency();

    // Assert
    verify(statement).getResultSetConcurrency();
    verify(original).get();
    assertEquals(1, actualResultSetConcurrency);
  }

  /**
   * Test {@link JDBCStatementImpl#getResultSetConcurrency()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getResultSetConcurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getResultSetConcurrency()"})
  public void testGetResultSetConcurrency_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getResultSetConcurrency()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getResultSetConcurrency());
    verify(statement).getResultSetConcurrency();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getResultSetType()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getResultSetType()} return one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getResultSetType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getResultSetType()"})
  public void testGetResultSetType_givenStatementGetResultSetTypeReturnOne_thenReturnOne()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getResultSetType()).thenReturn(1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    int actualResultSetType = jdbcStatementImpl.getResultSetType();

    // Assert
    verify(statement).getResultSetType();
    verify(original).get();
    assertEquals(1, actualResultSetType);
  }

  /**
   * Test {@link JDBCStatementImpl#getResultSetType()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getResultSetType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getResultSetType()"})
  public void testGetResultSetType_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getResultSetType()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getResultSetType());
    verify(statement).getResultSetType();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#addBatch(String)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#addBatch(String)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#addBatch(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.addBatch(String)"})
  public void testAddBatch_givenStatementAddBatchDoesNothing() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).addBatch(Mockito.<String>any());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.addBatch("Sql");

    // Assert
    verify(statement).addBatch("Sql");
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#addBatch(String)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#addBatch(String)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#addBatch(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.addBatch(String)"})
  public void testAddBatch_givenStatementAddBatchThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).addBatch(Mockito.<String>any());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.addBatch("Sql"));
    verify(statement).addBatch("Sql");
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#clearBatch()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#clearBatch()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#clearBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.clearBatch()"})
  public void testClearBatch_givenStatementClearBatchDoesNothing() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).clearBatch();

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.clearBatch();

    // Assert
    verify(statement).clearBatch();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#clearBatch()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#clearBatch()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#clearBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.clearBatch()"})
  public void testClearBatch_givenStatementClearBatchThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).clearBatch();

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.clearBatch());
    verify(statement).clearBatch();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getGeneratedKeys()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#getGeneratedKeys()} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getGeneratedKeys()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ResultSet JDBCStatementImpl.getGeneratedKeys()"})
  public void testGetGeneratedKeys_givenStatementGetGeneratedKeysReturnNull_thenReturnNull()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getGeneratedKeys()).thenReturn(null);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    ResultSet actualGeneratedKeys = jdbcStatementImpl.getGeneratedKeys();

    // Assert
    verify(statement).getGeneratedKeys();
    verify(original).get();
    assertNull(actualGeneratedKeys);
  }

  /**
   * Test {@link JDBCStatementImpl#getGeneratedKeys()}.
   *
   * <ul>
   *   <li>Then return {@link JDBCResultSetImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getGeneratedKeys()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ResultSet JDBCStatementImpl.getGeneratedKeys()"})
  public void testGetGeneratedKeys_thenReturnJDBCResultSetImpl() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource);

    JDBCSession connection2 = mock(JDBCSession.class);
    when(connection2.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection2, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    Statement statement2 = mock(Statement.class);
    when(statement2.getGeneratedKeys()).thenReturn(jdbcResultSetCallable);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement2);

    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(connection, original, "Query String", true);

    // Act
    ResultSet actualGeneratedKeys = jdbcStatementImpl.getGeneratedKeys();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(statement2).getGeneratedKeys();
    verify(stmtSupplier).get();
    verify(original).get();
    verify(connection).getDataSource();
    verify(connection2).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertTrue(actualGeneratedKeys instanceof JDBCResultSetImpl);
  }

  /**
   * Test {@link JDBCStatementImpl#getGeneratedKeys()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getGeneratedKeys()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ResultSet JDBCStatementImpl.getGeneratedKeys()"})
  public void testGetGeneratedKeys_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getGeneratedKeys()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getGeneratedKeys());
    verify(statement).getGeneratedKeys();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#getResultSetHoldability()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getResultSetHoldability()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getResultSetHoldability()"})
  public void testGetResultSetHoldability_thenReturnOne() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getResultSetHoldability()).thenReturn(1);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    int actualResultSetHoldability = jdbcStatementImpl.getResultSetHoldability();

    // Assert
    verify(statement).getResultSetHoldability();
    verify(original).get();
    assertEquals(1, actualResultSetHoldability);
  }

  /**
   * Test {@link JDBCStatementImpl#getResultSetHoldability()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#getResultSetHoldability()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCStatementImpl.getResultSetHoldability()"})
  public void testGetResultSetHoldability_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.getResultSetHoldability()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.getResultSetHoldability());
    verify(statement).getResultSetHoldability();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#isClosed()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#isClosed()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isClosed()"})
  public void testIsClosed_givenStatementIsClosedReturnFalse_thenReturnFalse() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isClosed()).thenReturn(false);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualIsClosedResult = jdbcStatementImpl.isClosed();

    // Assert
    verify(statement).isClosed();
    verify(original).get();
    assertFalse(actualIsClosedResult);
  }

  /**
   * Test {@link JDBCStatementImpl#isClosed()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#isClosed()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isClosed()"})
  public void testIsClosed_givenStatementIsClosedReturnTrue_thenReturnTrue() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isClosed()).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualIsClosedResult = jdbcStatementImpl.isClosed();

    // Assert
    verify(statement).isClosed();
    verify(original).get();
    assertTrue(actualIsClosedResult);
  }

  /**
   * Test {@link JDBCStatementImpl#isClosed()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#isClosed()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isClosed()"})
  public void testIsClosed_givenStatementIsClosedThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isClosed()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.isClosed());
    verify(statement).isClosed();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#isStatementClosed()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#isClosed()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isStatementClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isStatementClosed()"})
  public void testIsStatementClosed_givenStatementIsClosedReturnFalse_thenReturnFalse()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isClosed()).thenReturn(false);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualIsStatementClosedResult = jdbcStatementImpl.isStatementClosed();

    // Assert
    verify(statement).isClosed();
    verify(original).get();
    assertFalse(actualIsStatementClosedResult);
  }

  /**
   * Test {@link JDBCStatementImpl#isStatementClosed()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#isClosed()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isStatementClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isStatementClosed()"})
  public void testIsStatementClosed_givenStatementIsClosedReturnTrue_thenReturnTrue()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isClosed()).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualIsStatementClosedResult = jdbcStatementImpl.isStatementClosed();

    // Assert
    verify(statement).isClosed();
    verify(original).get();
    assertTrue(actualIsStatementClosedResult);
  }

  /**
   * Test {@link JDBCStatementImpl#isStatementClosed()}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isStatementClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isStatementClosed()"})
  public void testIsStatementClosed_thenThrowDBCException() throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isClosed()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(DBCException.class, () -> jdbcStatementImpl.isStatementClosed());
    verify(statement).isClosed();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#isStatementClosed()}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isStatementClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isStatementClosed()"})
  public void testIsStatementClosed_thenThrowUnsupportedOperationException()
      throws SQLException, DBCException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isClosed()).thenThrow(new UnsupportedOperationException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> jdbcStatementImpl.isStatementClosed());
    verify(statement).isClosed();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setPoolable(boolean)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#setPoolable(boolean)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setPoolable(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setPoolable(boolean)"})
  public void testSetPoolable_givenStatementSetPoolableDoesNothing() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).setPoolable(anyBoolean());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.setPoolable(true);

    // Assert
    verify(statement).setPoolable(true);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#setPoolable(boolean)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#setPoolable(boolean)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#setPoolable(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.setPoolable(boolean)"})
  public void testSetPoolable_givenStatementSetPoolableThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).setPoolable(anyBoolean());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.setPoolable(true));
    verify(statement).setPoolable(true);
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#isPoolable()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#isPoolable()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isPoolable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isPoolable()"})
  public void testIsPoolable_givenStatementIsPoolableReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isPoolable()).thenReturn(false);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualIsPoolableResult = jdbcStatementImpl.isPoolable();

    // Assert
    verify(statement).isPoolable();
    verify(original).get();
    assertFalse(actualIsPoolableResult);
  }

  /**
   * Test {@link JDBCStatementImpl#isPoolable()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#isPoolable()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isPoolable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isPoolable()"})
  public void testIsPoolable_givenStatementIsPoolableReturnTrue_thenReturnTrue()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isPoolable()).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualIsPoolableResult = jdbcStatementImpl.isPoolable();

    // Assert
    verify(statement).isPoolable();
    verify(original).get();
    assertTrue(actualIsPoolableResult);
  }

  /**
   * Test {@link JDBCStatementImpl#isPoolable()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#isPoolable()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isPoolable()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isPoolable()"})
  public void testIsPoolable_givenStatementIsPoolableThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isPoolable()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.isPoolable());
    verify(statement).isPoolable();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#closeOnCompletion()}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#closeOnCompletion()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#closeOnCompletion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.closeOnCompletion()"})
  public void testCloseOnCompletion_givenStatementCloseOnCompletionDoesNothing()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doNothing().when(statement).closeOnCompletion();

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    jdbcStatementImpl.closeOnCompletion();

    // Assert
    verify(statement).closeOnCompletion();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#closeOnCompletion()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#closeOnCompletion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCStatementImpl.closeOnCompletion()"})
  public void testCloseOnCompletion_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    doThrow(new SQLException()).when(statement).closeOnCompletion();

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.closeOnCompletion());
    verify(statement).closeOnCompletion();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#isCloseOnCompletion()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isCloseOnCompletion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isCloseOnCompletion()"})
  public void testIsCloseOnCompletion_thenReturnFalse() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isCloseOnCompletion()).thenReturn(false);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualIsCloseOnCompletionResult = jdbcStatementImpl.isCloseOnCompletion();

    // Assert
    verify(statement).isCloseOnCompletion();
    verify(original).get();
    assertFalse(actualIsCloseOnCompletionResult);
  }

  /**
   * Test {@link JDBCStatementImpl#isCloseOnCompletion()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isCloseOnCompletion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isCloseOnCompletion()"})
  public void testIsCloseOnCompletion_thenReturnTrue() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isCloseOnCompletion()).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act
    boolean actualIsCloseOnCompletionResult = jdbcStatementImpl.isCloseOnCompletion();

    // Assert
    verify(statement).isCloseOnCompletion();
    verify(original).get();
    assertTrue(actualIsCloseOnCompletionResult);
  }

  /**
   * Test {@link JDBCStatementImpl#isCloseOnCompletion()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isCloseOnCompletion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isCloseOnCompletion()"})
  public void testIsCloseOnCompletion_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isCloseOnCompletion()).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.isCloseOnCompletion());
    verify(statement).isCloseOnCompletion();
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#unwrap(Class)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#unwrap(Class)} return {@code Unwrap}.
   *   <li>Then return {@code Unwrap}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCStatementImpl.unwrap(Class)"})
  public void testUnwrap_givenStatementUnwrapReturnUnwrap_thenReturnUnwrap() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.unwrap(Object.class)).thenReturn("Unwrap");

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);
    Class<Object> iface = Object.class;

    // Act
    Object actualUnwrapResult = jdbcStatementImpl.unwrap(iface);

    // Assert
    verify(statement).unwrap(isA(Class.class));
    verify(original).get();
    assertEquals("Unwrap", actualUnwrapResult);
  }

  /**
   * Test {@link JDBCStatementImpl#unwrap(Class)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#unwrap(Class)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCStatementImpl.unwrap(Class)"})
  public void testUnwrap_givenStatementUnwrapThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.unwrap(Object.class)).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);
    Class<Object> iface = Object.class;

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.unwrap(iface));
    verify(statement).unwrap(isA(Class.class));
    verify(original).get();
  }

  /**
   * Test {@link JDBCStatementImpl#isWrapperFor(Class)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#isWrapperFor(Class)} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isWrapperFor(Class)"})
  public void testIsWrapperFor_givenStatementIsWrapperForReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isWrapperFor(Mockito.<Class<?>>any())).thenReturn(false);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);
    Class<Object> iface = Object.class;

    // Act
    boolean actualIsWrapperForResult = jdbcStatementImpl.isWrapperFor(iface);

    // Assert
    verify(statement).isWrapperFor(isA(Class.class));
    verify(original).get();
    assertFalse(actualIsWrapperForResult);
  }

  /**
   * Test {@link JDBCStatementImpl#isWrapperFor(Class)}.
   *
   * <ul>
   *   <li>Given {@link Statement} {@link Statement#isWrapperFor(Class)} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isWrapperFor(Class)"})
  public void testIsWrapperFor_givenStatementIsWrapperForReturnTrue_thenReturnTrue()
      throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isWrapperFor(Mockito.<Class<?>>any())).thenReturn(true);

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);
    Class<Object> iface = Object.class;

    // Act
    boolean actualIsWrapperForResult = jdbcStatementImpl.isWrapperFor(iface);

    // Assert
    verify(statement).isWrapperFor(isA(Class.class));
    verify(original).get();
    assertTrue(actualIsWrapperForResult);
  }

  /**
   * Test {@link JDBCStatementImpl#isWrapperFor(Class)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCStatementImpl#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCStatementImpl.isWrapperFor(Class)"})
  public void testIsWrapperFor_thenThrowSQLException() throws SQLException {
    // Arrange
    Statement statement = mock(Statement.class);
    when(statement.isWrapperFor(Mockito.<Class<?>>any())).thenThrow(new SQLException());

    JDBCObjectSupplier<Statement> original = mock(JDBCObjectSupplier.class);
    when(original.get()).thenReturn(statement);
    JDBCStatementImpl<Statement> jdbcStatementImpl =
        new JDBCStatementImpl<>(mock(JDBCSession.class), original, "Query String", true);
    Class<Object> iface = Object.class;

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcStatementImpl.isWrapperFor(iface));
    verify(statement).isWrapperFor(isA(Class.class));
    verify(original).get();
  }
}
