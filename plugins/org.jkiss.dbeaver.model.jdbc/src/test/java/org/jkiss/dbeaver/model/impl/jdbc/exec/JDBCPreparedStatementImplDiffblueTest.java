package org.jkiss.dbeaver.model.impl.jdbc.exec;

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
import static org.mockito.Mockito.anyByte;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyFloat;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.anyShort;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCResultSet;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCArrayImpl;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCExecutionContext;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCRemoteInstance;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCPreparedStatementImpl.ContentParameter;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCPreparedStatementImplDiffblueTest {
  /**
   * Test ContentParameter {@link ContentParameter#ContentParameter(JDBCSession, Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return toString is {@code DATA([NULL])}.
   * </ul>
   *
   * <p>Method under test: {@link ContentParameter#ContentParameter(JDBCSession, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ContentParameter.<init>(JDBCSession, Object)"})
  public void testContentParameterNewContentParameter_whenNull_thenReturnToStringIsDataNull() {
    // Arrange and Act
    ContentParameter actualContentParameter = new ContentParameter(mock(JDBCSession.class), null);

    // Assert
    assertEquals("DATA([NULL])", actualContentParameter.toString());
  }

  /**
   * Test ContentParameter {@link ContentParameter#ContentParameter(JDBCSession, Object)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return toString is {@code DATA(String)}.
   * </ul>
   *
   * <p>Method under test: {@link ContentParameter#ContentParameter(JDBCSession, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ContentParameter.<init>(JDBCSession, Object)"})
  public void testContentParameterNewContentParameter_whenValue_thenReturnToStringIsDataString() {
    // Arrange, Act and Assert
    assertEquals("DATA(String)", new ContentParameter(mock(JDBCSession.class), "Value").toString());
  }

  /**
   * Test ContentParameter {@link ContentParameter#toString()}.
   *
   * <p>Method under test: {@link ContentParameter#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ContentParameter.toString()"})
  public void testContentParameterToString() {
    // Arrange, Act and Assert
    assertEquals("DATA(String)", new ContentParameter(mock(JDBCSession.class), "Value").toString());
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#JDBCPreparedStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#JDBCPreparedStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCPreparedStatementImpl.<init>(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testNewJDBCPreparedStatementImpl_givenSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> new JDBCPreparedStatementImpl<>(connection, stmtSupplier, "Query", true));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#JDBCPreparedStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}.
   *
   * <ul>
   *   <li>Then return FormattedQuery is {@code Query}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#JDBCPreparedStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCPreparedStatementImpl.<init>(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testNewJDBCPreparedStatementImpl_thenReturnFormattedQueryIsQuery()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(mock(PreparedStatement.class));

    // Act
    JDBCPreparedStatementImpl<PreparedStatement> actualJdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(connection, stmtSupplier, "Query", true);

    // Assert
    verify(stmtSupplier).get();
    assertEquals("Query", actualJdbcPreparedStatementImpl.getFormattedQuery());
    assertEquals("Query", actualJdbcPreparedStatementImpl.getQueryString());
    assertNull(actualJdbcPreparedStatementImpl.getBlockThread());
    assertNull(actualJdbcPreparedStatementImpl.getStatementWarnings());
    assertNull(actualJdbcPreparedStatementImpl.getParameterMetaData());
    assertNull(actualJdbcPreparedStatementImpl.getGeneratedKeys());
    assertNull(actualJdbcPreparedStatementImpl.getMetaData());
    assertNull(actualJdbcPreparedStatementImpl.getWarnings());
    assertNull(actualJdbcPreparedStatementImpl.getStatementSource());
    assertNull(actualJdbcPreparedStatementImpl.getResultSet());
    assertEquals(0, actualJdbcPreparedStatementImpl.getFetchDirection());
    assertEquals(0, actualJdbcPreparedStatementImpl.getFetchSize());
    assertEquals(0, actualJdbcPreparedStatementImpl.getMaxFieldSize());
    assertEquals(0, actualJdbcPreparedStatementImpl.getMaxRows());
    assertEquals(0, actualJdbcPreparedStatementImpl.getQueryTimeout());
    assertEquals(0, actualJdbcPreparedStatementImpl.getResultSetConcurrency());
    assertEquals(0, actualJdbcPreparedStatementImpl.getResultSetHoldability());
    assertEquals(0, actualJdbcPreparedStatementImpl.getResultSetType());
    assertEquals(0, actualJdbcPreparedStatementImpl.getUpdateCount());
    assertEquals(0L, actualJdbcPreparedStatementImpl.getLargeMaxRows());
    assertEquals(0L, actualJdbcPreparedStatementImpl.getLargeUpdateCount());
    assertEquals(0L, actualJdbcPreparedStatementImpl.getUpdateRowCount());
    assertFalse(actualJdbcPreparedStatementImpl.getMoreResults());
    assertFalse(actualJdbcPreparedStatementImpl.isCloseOnCompletion());
    assertFalse(actualJdbcPreparedStatementImpl.isClosed());
    assertFalse(actualJdbcPreparedStatementImpl.isPoolable());
    assertFalse(actualJdbcPreparedStatementImpl.isQMLoggingEnabled());
    assertFalse(actualJdbcPreparedStatementImpl.isStatementClosed());
    assertTrue(actualJdbcPreparedStatementImpl.disableLogging);
    assertSame(connection, actualJdbcPreparedStatementImpl.getSession());
    assertSame(connection, actualJdbcPreparedStatementImpl.getConnection());
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#close()}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#close()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.close()"})
  public void testClose_givenPreparedStatementCloseDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).close();

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    try (JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true)) {}

    // Act and Assert
    verify(preparedStatement).close();
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#close()}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#close()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.close()"})
  public void testClose_givenPreparedStatementCloseThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).close();

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    try (JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true)) {}

    // Act and Assert
    verify(preparedStatement).close();
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#getFormattedQuery()}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#getFormattedQuery()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCPreparedStatementImpl.getFormattedQuery()"})
  public void testGetFormattedQuery() throws SQLException {
    // Arrange
    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(mock(PreparedStatement.class));
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    String actualFormattedQuery = jdbcPreparedStatementImpl.getFormattedQuery();

    // Assert
    verify(stmtSupplier).get();
    assertEquals("Query", actualFormattedQuery);
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#handleStatementBind(Object, Object)}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#handleStatementBind(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.handleStatementBind(Object, Object)"})
  public void testHandleStatementBind() throws SQLException {
    // Arrange
    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(mock(PreparedStatement.class));
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.handleStatementBind("Parameter", "42");

    // Assert
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#executeStatement()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#executeStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCPreparedStatementImpl.executeStatement()"})
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

    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.execute()).thenReturn(true);

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);

    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(connection, stmtSupplier, "Query", true);

    // Act
    boolean actualExecuteStatementResult = jdbcPreparedStatementImpl.executeStatement();

    // Assert
    verify(preparedStatement).execute();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertTrue(actualExecuteStatementResult);
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#executeStatement()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#executeStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCPreparedStatementImpl.executeStatement()"})
  public void testExecuteStatement_thenReturnFalse() throws SQLException, DBCException {
    // Arrange
    JDBCFakeStatementImpl jdbcFakeStatementImpl =
        new JDBCFakeStatementImpl(mock(JDBCSession.class), "Query Text", true);

    // Act and Assert
    assertFalse(jdbcFakeStatementImpl.executeStatement());
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#addToBatch()}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#addBatch()} does nothing.
   *   <li>Then calls {@link PreparedStatement#addBatch()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#addToBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.addToBatch()"})
  public void testAddToBatch_givenPreparedStatementAddBatchDoesNothing_thenCallsAddBatch()
      throws SQLException, DBCException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).addBatch();

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.addToBatch();

    // Assert
    verify(preparedStatement).addBatch();
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#executeQuery()}.
   *
   * <ul>
   *   <li>Then return {@link JDBCResultSetImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#executeQuery()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCPreparedStatementImpl.executeQuery()"})
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

    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.executeQuery()).thenReturn(jdbcResultSetCallable);

    JDBCObjectSupplier<PreparedStatement> stmtSupplier2 = mock(JDBCObjectSupplier.class);
    when(stmtSupplier2.get()).thenReturn(preparedStatement);

    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(connection, stmtSupplier2, "Query", true);

    // Act
    JDBCResultSet actualExecuteQueryResult = jdbcPreparedStatementImpl.executeQuery();

    // Assert
    verify(preparedStatement).executeQuery();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(stmtSupplier).get();
    verify(stmtSupplier2).get();
    verify(connection).getDataSource();
    verify(connection2).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(jdbcDataSource2).getJdbcFactory();
    verify(instance, atLeast(1)).getDataSource();
    assertTrue(actualExecuteQueryResult instanceof JDBCResultSetImpl);
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#executeUpdate()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#executeUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCPreparedStatementImpl.executeUpdate()"})
  public void testExecuteUpdate_givenDBPDriverIsThreadSafeDriverReturnTrue_thenReturnOne()
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

    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.executeUpdate()).thenReturn(1);

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);

    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(connection, stmtSupplier, "Query", true);

    // Act
    int actualExecuteUpdateResult = jdbcPreparedStatementImpl.executeUpdate();

    // Assert
    verify(preparedStatement).executeUpdate();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertEquals(1, actualExecuteUpdateResult);
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#execute()}.
   *
   * <ul>
   *   <li>Given {@link DBPDriver} {@link DBPDriver#isThreadSafeDriver()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#execute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCPreparedStatementImpl.execute()"})
  public void testExecute_givenDBPDriverIsThreadSafeDriverReturnTrue_thenReturnTrue()
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

    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.execute()).thenReturn(true);

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);

    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(connection, stmtSupplier, "Query", true);

    // Act
    boolean actualExecuteResult = jdbcPreparedStatementImpl.execute();

    // Assert
    verify(preparedStatement).execute();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertTrue(actualExecuteResult);
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#execute()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#execute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCPreparedStatementImpl.execute()"})
  public void testExecute_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCFakeStatementImpl jdbcFakeStatementImpl =
        new JDBCFakeStatementImpl(mock(JDBCSession.class), "Query Text", true);

    // Act and Assert
    assertFalse(jdbcFakeStatementImpl.execute());
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNull(int, int, String)} with {@code parameterIndex},
   * {@code sqlType}, {@code typeName}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNull(int, int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNull(int, int, String)"})
  public void testSetNullWithParameterIndexSqlTypeTypeName() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setNull(anyInt(), anyInt(), Mockito.<String>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setNull(1, 1, "Type Name");

    // Assert
    verify(preparedStatement).setNull(1, 1, "Type Name");
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNull(int, int, String)} with {@code parameterIndex},
   * {@code sqlType}, {@code typeName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNull(int, int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNull(int, int, String)"})
  public void testSetNullWithParameterIndexSqlTypeTypeName_thenThrowSQLException()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setNull(anyInt(), anyInt(), Mockito.<String>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setNull(1, 1, "Type Name"));
    verify(preparedStatement).setNull(1, 1, "Type Name");
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNull(int, int)} with {@code parameterIndex}, {@code
   * sqlType}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setNull(int, int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNull(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNull(int, int)"})
  public void testSetNullWithParameterIndexSqlType_givenPreparedStatementSetNullDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setNull(anyInt(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setNull(1, 1);

    // Assert
    verify(preparedStatement).setNull(1, 1);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNull(int, int)} with {@code parameterIndex}, {@code
   * sqlType}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNull(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNull(int, int)"})
  public void testSetNullWithParameterIndexSqlType_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setNull(anyInt(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setNull(1, 1));
    verify(preparedStatement).setNull(1, 1);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setURL(int, URL)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setURL(int, URL)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setURL(int, URL)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setURL(int, URL)"})
  public void testSetURL_givenPreparedStatementSetURLDoesNothing()
      throws MalformedURLException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setURL(anyInt(), Mockito.<URL>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setURL(
        1, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Assert
    verify(preparedStatement).setURL(eq(1), isA(URL.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setURL(int, URL)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setURL(int, URL)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setURL(int, URL)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setURL(int, URL)"})
  public void testSetURL_givenPreparedStatementSetURLThrowSQLException_thenThrowSQLException()
      throws MalformedURLException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setURL(anyInt(), Mockito.<URL>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcPreparedStatementImpl.setURL(
                1, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()));
    verify(preparedStatement).setURL(eq(1), isA(URL.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBoolean(int, boolean)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setBoolean(int, boolean)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBoolean(int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBoolean(int, boolean)"})
  public void testSetBoolean_givenPreparedStatementSetBooleanDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setBoolean(anyInt(), anyBoolean());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setBoolean(1, true);

    // Assert
    verify(preparedStatement).setBoolean(1, true);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBoolean(int, boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBoolean(int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBoolean(int, boolean)"})
  public void testSetBoolean_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setBoolean(anyInt(), anyBoolean());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setBoolean(1, true));
    verify(preparedStatement).setBoolean(1, true);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setByte(int, byte)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setByte(int, byte)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setByte(int, byte)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setByte(int, byte)"})
  public void testSetByte_givenPreparedStatementSetByteDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setByte(anyInt(), anyByte());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setByte(1, (byte) 'A');

    // Assert
    verify(preparedStatement).setByte(1, (byte) 65);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setByte(int, byte)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setByte(int, byte)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setByte(int, byte)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setByte(int, byte)"})
  public void testSetByte_givenPreparedStatementSetByteThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setByte(anyInt(), anyByte());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setByte(1, (byte) 'A'));
    verify(preparedStatement).setByte(1, (byte) 65);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setShort(int, short)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setShort(int, short)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setShort(int, short)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setShort(int, short)"})
  public void testSetShort_givenPreparedStatementSetShortDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setShort(anyInt(), anyShort());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setShort(1, (short) 1);

    // Assert
    verify(preparedStatement).setShort(1, (short) 1);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setShort(int, short)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setShort(int, short)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setShort(int, short)"})
  public void testSetShort_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setShort(anyInt(), anyShort());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setShort(1, (short) 1));
    verify(preparedStatement).setShort(1, (short) 1);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setInt(int, int)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setInt(int, int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setInt(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setInt(int, int)"})
  public void testSetInt_givenPreparedStatementSetIntDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setInt(anyInt(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setInt(1, 2);

    // Assert
    verify(preparedStatement).setInt(1, 2);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setInt(int, int)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setInt(int, int)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setInt(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setInt(int, int)"})
  public void testSetInt_givenPreparedStatementSetIntThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setInt(anyInt(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setInt(1, 2));
    verify(preparedStatement).setInt(1, 2);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setLong(int, long)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setLong(int, long)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setLong(int, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setLong(int, long)"})
  public void testSetLong_givenPreparedStatementSetLongDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setLong(anyInt(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setLong(1, 1L);

    // Assert
    verify(preparedStatement).setLong(1, 1L);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setLong(int, long)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setLong(int, long)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setLong(int, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setLong(int, long)"})
  public void testSetLong_givenPreparedStatementSetLongThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setLong(anyInt(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setLong(1, 1L));
    verify(preparedStatement).setLong(1, 1L);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setFloat(int, float)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setFloat(int, float)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setFloat(int, float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setFloat(int, float)"})
  public void testSetFloat_givenPreparedStatementSetFloatDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setFloat(anyInt(), anyFloat());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setFloat(1, 10.0f);

    // Assert
    verify(preparedStatement).setFloat(1, 10.0f);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setFloat(int, float)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setFloat(int, float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setFloat(int, float)"})
  public void testSetFloat_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setFloat(anyInt(), anyFloat());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setFloat(1, 10.0f));
    verify(preparedStatement).setFloat(1, 10.0f);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setDouble(int, double)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setDouble(int, double)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setDouble(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setDouble(int, double)"})
  public void testSetDouble_givenPreparedStatementSetDoubleDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setDouble(anyInt(), anyDouble());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setDouble(1, 2.0d);

    // Assert
    verify(preparedStatement).setDouble(1, 2.0d);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setDouble(int, double)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setDouble(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setDouble(int, double)"})
  public void testSetDouble_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setDouble(anyInt(), anyDouble());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setDouble(1, 2.0d));
    verify(preparedStatement).setDouble(1, 2.0d);
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBigDecimal(int, BigDecimal)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setBigDecimal(int, BigDecimal)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBigDecimal(int, BigDecimal)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBigDecimal(int, BigDecimal)"})
  public void testSetBigDecimal_givenPreparedStatementSetBigDecimalDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setBigDecimal(anyInt(), Mockito.<BigDecimal>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setBigDecimal(1, new BigDecimal("2.3"));

    // Assert
    verify(preparedStatement).setBigDecimal(eq(1), isA(BigDecimal.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBigDecimal(int, BigDecimal)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBigDecimal(int, BigDecimal)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBigDecimal(int, BigDecimal)"})
  public void testSetBigDecimal_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setBigDecimal(anyInt(), Mockito.<BigDecimal>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setBigDecimal(1, new BigDecimal("2.3")));
    verify(preparedStatement).setBigDecimal(eq(1), isA(BigDecimal.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setString(int, String)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setString(int, String)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setString(int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setString(int, String)"})
  public void testSetString_givenPreparedStatementSetStringDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setString(anyInt(), Mockito.<String>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setString(1, "foo");

    // Assert
    verify(preparedStatement).setString(1, "foo");
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setString(int, String)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setString(int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setString(int, String)"})
  public void testSetString_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setString(anyInt(), Mockito.<String>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setString(1, "foo"));
    verify(preparedStatement).setString(1, "foo");
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBytes(int, byte[])}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setBytes(int, byte[])} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBytes(int, byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBytes(int, byte[])"})
  public void testSetBytes_givenPreparedStatementSetBytesDoesNothing()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setBytes(anyInt(), Mockito.<byte[]>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setBytes(1, "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    verify(preparedStatement).setBytes(eq(1), isA(byte[].class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBytes(int, byte[])}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBytes(int, byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBytes(int, byte[])"})
  public void testSetBytes_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setBytes(anyInt(), Mockito.<byte[]>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setBytes(1, "AXAXAXAX".getBytes("UTF-8")));
    verify(preparedStatement).setBytes(eq(1), isA(byte[].class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setDate(int, Date, Calendar)} with {@code
   * parameterIndex}, {@code x}, {@code cal}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setDate(int, Date, Calendar)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setDate(int, Date, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setDate(int, Date, Calendar)"})
  public void testSetDateWithParameterIndexXCal_givenPreparedStatementSetDateDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing()
        .when(preparedStatement)
        .setDate(anyInt(), Mockito.<Date>any(), Mockito.<Calendar>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);
    Date x = new Date(1L);

    // Act
    jdbcPreparedStatementImpl.setDate(1, x, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(preparedStatement).setDate(eq(1), isA(Date.class), isA(Calendar.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setDate(int, Date, Calendar)} with {@code
   * parameterIndex}, {@code x}, {@code cal}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setDate(int, Date, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setDate(int, Date, Calendar)"})
  public void testSetDateWithParameterIndexXCal_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setDate(anyInt(), Mockito.<Date>any(), Mockito.<Calendar>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);
    Date x = new Date(1L);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setDate(1, x, new GregorianCalendar(1, 1, 1)));
    verify(preparedStatement).setDate(eq(1), isA(Date.class), isA(Calendar.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setDate(int, Date)} with {@code parameterIndex}, {@code
   * x}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setDate(int, Date)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setDate(int, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setDate(int, Date)"})
  public void testSetDateWithParameterIndexX_givenPreparedStatementSetDateDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setDate(anyInt(), Mockito.<Date>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setDate(1, new Date(1L));

    // Assert
    verify(preparedStatement).setDate(eq(1), isA(Date.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setDate(int, Date)} with {@code parameterIndex}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setDate(int, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setDate(int, Date)"})
  public void testSetDateWithParameterIndexX_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setDate(anyInt(), Mockito.<Date>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setDate(1, new Date(1L)));
    verify(preparedStatement).setDate(eq(1), isA(Date.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setTime(int, Time, Calendar)} with {@code
   * parameterIndex}, {@code x}, {@code cal}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setTime(int, Time, Calendar)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setTime(int, Time, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setTime(int, Time, Calendar)"})
  public void testSetTimeWithParameterIndexXCal_givenPreparedStatementSetTimeDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing()
        .when(preparedStatement)
        .setTime(anyInt(), Mockito.<Time>any(), Mockito.<Calendar>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setTime(1, null, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(preparedStatement).setTime(eq(1), isNull(), isA(Calendar.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setTime(int, Time, Calendar)} with {@code
   * parameterIndex}, {@code x}, {@code cal}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setTime(int, Time, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setTime(int, Time, Calendar)"})
  public void testSetTimeWithParameterIndexXCal_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setTime(anyInt(), Mockito.<Time>any(), Mockito.<Calendar>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setTime(1, null, new GregorianCalendar(1, 1, 1)));
    verify(preparedStatement).setTime(eq(1), isNull(), isA(Calendar.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setTime(int, Time)} with {@code parameterIndex}, {@code
   * x}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setTime(int, Time)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setTime(int, Time)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setTime(int, Time)"})
  public void testSetTimeWithParameterIndexX_givenPreparedStatementSetTimeDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setTime(anyInt(), Mockito.<Time>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setTime(1, null);

    // Assert
    verify(preparedStatement).setTime(eq(1), isNull());
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setTime(int, Time)} with {@code parameterIndex}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setTime(int, Time)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setTime(int, Time)"})
  public void testSetTimeWithParameterIndexX_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setTime(anyInt(), Mockito.<Time>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setTime(1, null));
    verify(preparedStatement).setTime(eq(1), isNull());
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setTimestamp(int, Timestamp)} with {@code
   * parameterIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setTimestamp(int, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setTimestamp(int, Timestamp)"})
  public void testSetTimestampWithParameterIndexX() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setTimestamp(anyInt(), Mockito.<Timestamp>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setTimestamp(1, null);

    // Assert
    verify(preparedStatement).setTimestamp(eq(1), isNull());
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setTimestamp(int, Timestamp, Calendar)} with {@code
   * parameterIndex}, {@code x}, {@code cal}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setTimestamp(int, Timestamp, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setTimestamp(int, Timestamp, Calendar)"})
  public void testSetTimestampWithParameterIndexXCal() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing()
        .when(preparedStatement)
        .setTimestamp(anyInt(), Mockito.<Timestamp>any(), Mockito.<Calendar>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setTimestamp(1, null, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(preparedStatement).setTimestamp(eq(1), isNull(), isA(Calendar.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setTimestamp(int, Timestamp, Calendar)} with {@code
   * parameterIndex}, {@code x}, {@code cal}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setTimestamp(int, Timestamp, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setTimestamp(int, Timestamp, Calendar)"})
  public void testSetTimestampWithParameterIndexXCal_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setTimestamp(anyInt(), Mockito.<Timestamp>any(), Mockito.<Calendar>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setTimestamp(1, null, new GregorianCalendar(1, 1, 1)));
    verify(preparedStatement).setTimestamp(eq(1), isNull(), isA(Calendar.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setTimestamp(int, Timestamp)} with {@code
   * parameterIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setTimestamp(int, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setTimestamp(int, Timestamp)"})
  public void testSetTimestampWithParameterIndexX_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setTimestamp(anyInt(), Mockito.<Timestamp>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setTimestamp(1, null));
    verify(preparedStatement).setTimestamp(eq(1), isNull());
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setAsciiStream(int, InputStream)} with {@code int},
   * {@code InputStream}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setAsciiStream(int, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setAsciiStream(int, InputStream)"})
  public void testSetAsciiStreamWithIntInputStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setAsciiStream(anyInt(), Mockito.<InputStream>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setAsciiStream(
        1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(preparedStatement).setAsciiStream(eq(1), isA(InputStream.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setAsciiStream(int, InputStream, int)} with {@code int},
   * {@code InputStream}, {@code int}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setAsciiStream(int, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setAsciiStream(int, InputStream, int)"})
  public void testSetAsciiStreamWithIntInputStreamInt()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing()
        .when(preparedStatement)
        .setAsciiStream(anyInt(), Mockito.<InputStream>any(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setAsciiStream(
        1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3);

    // Assert
    verify(preparedStatement).setAsciiStream(eq(1), isA(InputStream.class), eq(3));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setAsciiStream(int, InputStream, int)} with {@code int},
   * {@code InputStream}, {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setAsciiStream(int, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setAsciiStream(int, InputStream, int)"})
  public void testSetAsciiStreamWithIntInputStreamInt_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setAsciiStream(anyInt(), Mockito.<InputStream>any(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcPreparedStatementImpl.setAsciiStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3));
    verify(preparedStatement).setAsciiStream(eq(1), isA(InputStream.class), eq(3));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setAsciiStream(int, InputStream, long)} with {@code int},
   * {@code InputStream}, {@code long}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setAsciiStream(int, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setAsciiStream(int, InputStream, long)"})
  public void testSetAsciiStreamWithIntInputStreamLong()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing()
        .when(preparedStatement)
        .setAsciiStream(anyInt(), Mockito.<InputStream>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setAsciiStream(
        1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Assert
    verify(preparedStatement).setAsciiStream(eq(1), isA(InputStream.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setAsciiStream(int, InputStream, long)} with {@code int},
   * {@code InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setAsciiStream(int, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setAsciiStream(int, InputStream, long)"})
  public void testSetAsciiStreamWithIntInputStreamLong_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setAsciiStream(anyInt(), Mockito.<InputStream>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcPreparedStatementImpl.setAsciiStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L));
    verify(preparedStatement).setAsciiStream(eq(1), isA(InputStream.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setAsciiStream(int, InputStream)} with {@code int},
   * {@code InputStream}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setAsciiStream(int, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setAsciiStream(int, InputStream)"})
  public void testSetAsciiStreamWithIntInputStream_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setAsciiStream(anyInt(), Mockito.<InputStream>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcPreparedStatementImpl.setAsciiStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(preparedStatement).setAsciiStream(eq(1), isA(InputStream.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setUnicodeStream(int, InputStream, int)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setUnicodeStream(int,
   *       InputStream, int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setUnicodeStream(int, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setUnicodeStream(int, InputStream, int)"})
  public void testSetUnicodeStream_givenPreparedStatementSetUnicodeStreamDoesNothing()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing()
        .when(preparedStatement)
        .setUnicodeStream(anyInt(), Mockito.<InputStream>any(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setUnicodeStream(
        1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3);

    // Assert
    verify(preparedStatement).setUnicodeStream(eq(1), isA(InputStream.class), eq(3));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setUnicodeStream(int, InputStream, int)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setUnicodeStream(int, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setUnicodeStream(int, InputStream, int)"})
  public void testSetUnicodeStream_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setUnicodeStream(anyInt(), Mockito.<InputStream>any(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcPreparedStatementImpl.setUnicodeStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3));
    verify(preparedStatement).setUnicodeStream(eq(1), isA(InputStream.class), eq(3));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBinaryStream(int, InputStream)} with {@code int},
   * {@code InputStream}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBinaryStream(int, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBinaryStream(int, InputStream)"})
  public void testSetBinaryStreamWithIntInputStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setBinaryStream(anyInt(), Mockito.<InputStream>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setBinaryStream(
        1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(preparedStatement).setBinaryStream(eq(1), isA(InputStream.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBinaryStream(int, InputStream, int)} with {@code int},
   * {@code InputStream}, {@code int}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBinaryStream(int, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBinaryStream(int, InputStream, int)"})
  public void testSetBinaryStreamWithIntInputStreamInt()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing()
        .when(preparedStatement)
        .setBinaryStream(anyInt(), Mockito.<InputStream>any(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setBinaryStream(
        1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3);

    // Assert
    verify(preparedStatement).setBinaryStream(eq(1), isA(InputStream.class), eq(3));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBinaryStream(int, InputStream, int)} with {@code int},
   * {@code InputStream}, {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBinaryStream(int, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBinaryStream(int, InputStream, int)"})
  public void testSetBinaryStreamWithIntInputStreamInt_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setBinaryStream(anyInt(), Mockito.<InputStream>any(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcPreparedStatementImpl.setBinaryStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3));
    verify(preparedStatement).setBinaryStream(eq(1), isA(InputStream.class), eq(3));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBinaryStream(int, InputStream, long)} with {@code
   * int}, {@code InputStream}, {@code long}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBinaryStream(int, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBinaryStream(int, InputStream, long)"})
  public void testSetBinaryStreamWithIntInputStreamLong()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing()
        .when(preparedStatement)
        .setBinaryStream(anyInt(), Mockito.<InputStream>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setBinaryStream(
        1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Assert
    verify(preparedStatement).setBinaryStream(eq(1), isA(InputStream.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBinaryStream(int, InputStream, long)} with {@code
   * int}, {@code InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBinaryStream(int, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBinaryStream(int, InputStream, long)"})
  public void testSetBinaryStreamWithIntInputStreamLong_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setBinaryStream(anyInt(), Mockito.<InputStream>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcPreparedStatementImpl.setBinaryStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L));
    verify(preparedStatement).setBinaryStream(eq(1), isA(InputStream.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBinaryStream(int, InputStream)} with {@code int},
   * {@code InputStream}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBinaryStream(int, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBinaryStream(int, InputStream)"})
  public void testSetBinaryStreamWithIntInputStream_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setBinaryStream(anyInt(), Mockito.<InputStream>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcPreparedStatementImpl.setBinaryStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(preparedStatement).setBinaryStream(eq(1), isA(InputStream.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#clearParameters()}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#clearParameters()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#clearParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.clearParameters()"})
  public void testClearParameters_givenPreparedStatementClearParametersDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).clearParameters();

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.clearParameters();

    // Assert
    verify(preparedStatement).clearParameters();
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#clearParameters()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#clearParameters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.clearParameters()"})
  public void testClearParameters_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).clearParameters();

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.clearParameters());
    verify(preparedStatement).clearParameters();
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setObject(int, Object, int, int)} with {@code int},
   * {@code Object}, {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setObject(int, Object, int,
   *       int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setObject(int, Object, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setObject(int, Object, int, int)"})
  public void testSetObjectWithIntObjectIntInt_givenPreparedStatementSetObjectDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing()
        .when(preparedStatement)
        .setObject(anyInt(), Mockito.<Object>any(), anyInt(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setObject(1, "42", 1, 3);

    // Assert
    verify(preparedStatement).setObject(eq(1), isA(Object.class), eq(1), eq(3));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setObject(int, Object, int, int)} with {@code int},
   * {@code Object}, {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setObject(int, Object, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setObject(int, Object, int, int)"})
  public void testSetObjectWithIntObjectIntInt_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setObject(anyInt(), Mockito.<Object>any(), anyInt(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setObject(1, "42", 1, 3));
    verify(preparedStatement).setObject(eq(1), isA(Object.class), eq(1), eq(3));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setObject(int, Object, int)} with {@code int}, {@code
   * Object}, {@code int}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setObject(int, Object, int)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setObject(int, Object, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setObject(int, Object, int)"})
  public void testSetObjectWithIntObjectInt_givenPreparedStatementSetObjectDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setObject(anyInt(), Mockito.<Object>any(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setObject(1, "42", 1);

    // Assert
    verify(preparedStatement).setObject(eq(1), isA(Object.class), eq(1));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setObject(int, Object, int)} with {@code int}, {@code
   * Object}, {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setObject(int, Object, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setObject(int, Object, int)"})
  public void testSetObjectWithIntObjectInt_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setObject(anyInt(), Mockito.<Object>any(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setObject(1, "42", 1));
    verify(preparedStatement).setObject(eq(1), isA(Object.class), eq(1));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setObject(int, Object)} with {@code int}, {@code Object}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setObject(int, Object)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setObject(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setObject(int, Object)"})
  public void testSetObjectWithIntObject_givenPreparedStatementSetObjectDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setObject(anyInt(), Mockito.<Object>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setObject(1, "42");

    // Assert
    verify(preparedStatement).setObject(eq(1), isA(Object.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setObject(int, Object)} with {@code int}, {@code Object}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setObject(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setObject(int, Object)"})
  public void testSetObjectWithIntObject_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setObject(anyInt(), Mockito.<Object>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setObject(1, "42"));
    verify(preparedStatement).setObject(eq(1), isA(Object.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#addBatch()}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#addBatch()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#addBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.addBatch()"})
  public void testAddBatch_givenPreparedStatementAddBatchDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).addBatch();

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.addBatch();

    // Assert
    verify(preparedStatement).addBatch();
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#addBatch()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#addBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.addBatch()"})
  public void testAddBatch_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).addBatch();

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.addBatch());
    verify(preparedStatement).addBatch();
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setCharacterStream(int, Reader, int)} with {@code
   * parameterIndex}, {@code reader}, {@code length}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setCharacterStream(int, Reader, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setCharacterStream(int, Reader, int)"})
  public void testSetCharacterStreamWithParameterIndexReaderLength() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing()
        .when(preparedStatement)
        .setCharacterStream(anyInt(), Mockito.<Reader>any(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setCharacterStream(1, new StringReader("foo"), 3);

    // Assert
    verify(preparedStatement).setCharacterStream(eq(1), isA(Reader.class), eq(3));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setCharacterStream(int, Reader, int)} with {@code
   * parameterIndex}, {@code reader}, {@code length}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setCharacterStream(int, Reader, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setCharacterStream(int, Reader, int)"})
  public void testSetCharacterStreamWithParameterIndexReaderLength_thenThrowSQLException()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setCharacterStream(anyInt(), Mockito.<Reader>any(), anyInt());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setCharacterStream(1, new StringReader("foo"), 3));
    verify(preparedStatement).setCharacterStream(eq(1), isA(Reader.class), eq(3));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setCharacterStream(int, Reader)} with {@code
   * parameterIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setCharacterStream(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setCharacterStream(int, Reader)"})
  public void testSetCharacterStreamWithParameterIndexX() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setCharacterStream(anyInt(), Mockito.<Reader>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setCharacterStream(1, new StringReader("foo"));

    // Assert
    verify(preparedStatement).setCharacterStream(eq(1), isA(Reader.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setCharacterStream(int, Reader, long)} with {@code
   * parameterIndex}, {@code x}, {@code length}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setCharacterStream(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setCharacterStream(int, Reader, long)"})
  public void testSetCharacterStreamWithParameterIndexXLength() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing()
        .when(preparedStatement)
        .setCharacterStream(anyInt(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setCharacterStream(1, new StringReader("foo"), 3L);

    // Assert
    verify(preparedStatement).setCharacterStream(eq(1), isA(Reader.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setCharacterStream(int, Reader, long)} with {@code
   * parameterIndex}, {@code x}, {@code length}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setCharacterStream(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setCharacterStream(int, Reader, long)"})
  public void testSetCharacterStreamWithParameterIndexXLength_thenThrowSQLException()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setCharacterStream(anyInt(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setCharacterStream(1, new StringReader("foo"), 3L));
    verify(preparedStatement).setCharacterStream(eq(1), isA(Reader.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setCharacterStream(int, Reader)} with {@code
   * parameterIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setCharacterStream(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setCharacterStream(int, Reader)"})
  public void testSetCharacterStreamWithParameterIndexX_thenThrowSQLException()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setCharacterStream(anyInt(), Mockito.<Reader>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setCharacterStream(1, new StringReader("foo")));
    verify(preparedStatement).setCharacterStream(eq(1), isA(Reader.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setRef(int, Ref)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setRef(int, Ref)} does nothing.
   *   <li>When {@code null}.
   *   <li>Then calls {@link PreparedStatement#setRef(int, Ref)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setRef(int, Ref)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setRef(int, Ref)"})
  public void testSetRef_givenPreparedStatementSetRefDoesNothing_whenNull_thenCallsSetRef()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setRef(anyInt(), Mockito.<Ref>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setRef(1, null);

    // Assert
    verify(preparedStatement).setRef(eq(1), isNull());
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setRef(int, Ref)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setRef(int, Ref)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setRef(int, Ref)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setRef(int, Ref)"})
  public void testSetRef_givenPreparedStatementSetRefThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setRef(anyInt(), Mockito.<Ref>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setRef(1, null));
    verify(preparedStatement).setRef(eq(1), isNull());
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBlob(int, Blob)} with {@code int}, {@code Blob}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setBlob(int, Blob)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBlob(int, Blob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBlob(int, Blob)"})
  public void testSetBlobWithIntBlob_givenPreparedStatementSetBlobDoesNothing()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setBlob(anyInt(), Mockito.<Blob>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setBlob(1, new SerialBlob("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(preparedStatement).setBlob(eq(1), isA(Blob.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBlob(int, Blob)} with {@code int}, {@code Blob}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBlob(int, Blob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBlob(int, Blob)"})
  public void testSetBlobWithIntBlob_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setBlob(anyInt(), Mockito.<Blob>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setBlob(1, new SerialBlob("AXAXAXAX".getBytes("UTF-8"))));
    verify(preparedStatement).setBlob(eq(1), isA(Blob.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBlob(int, InputStream, long)} with {@code int}, {@code
   * InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setBlob(int, InputStream, long)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBlob(int, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBlob(int, InputStream, long)"})
  public void testSetBlobWithIntInputStreamLong_givenPreparedStatementSetBlobDoesNothing()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setBlob(anyInt(), Mockito.<InputStream>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setBlob(
        1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Assert
    verify(preparedStatement).setBlob(eq(1), isA(InputStream.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBlob(int, InputStream, long)} with {@code int}, {@code
   * InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBlob(int, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBlob(int, InputStream, long)"})
  public void testSetBlobWithIntInputStreamLong_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setBlob(anyInt(), Mockito.<InputStream>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcPreparedStatementImpl.setBlob(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L));
    verify(preparedStatement).setBlob(eq(1), isA(InputStream.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBlob(int, InputStream)} with {@code int}, {@code
   * InputStream}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setBlob(int, InputStream)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBlob(int, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBlob(int, InputStream)"})
  public void testSetBlobWithIntInputStream_givenPreparedStatementSetBlobDoesNothing()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setBlob(anyInt(), Mockito.<InputStream>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setBlob(1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(preparedStatement).setBlob(eq(1), isA(InputStream.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setBlob(int, InputStream)} with {@code int}, {@code
   * InputStream}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setBlob(int, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setBlob(int, InputStream)"})
  public void testSetBlobWithIntInputStream_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setBlob(anyInt(), Mockito.<InputStream>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcPreparedStatementImpl.setBlob(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(preparedStatement).setBlob(eq(1), isA(InputStream.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setClob(int, Clob)} with {@code int}, {@code Clob}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setClob(int, Clob)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setClob(int, Clob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setClob(int, Clob)"})
  public void testSetClobWithIntClob_givenPreparedStatementSetClobDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setClob(anyInt(), Mockito.<Clob>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setClob(1, new SerialClob("AZAZ".toCharArray()));

    // Assert
    verify(preparedStatement).setClob(eq(1), isA(Clob.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setClob(int, Clob)} with {@code int}, {@code Clob}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setClob(int, Clob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setClob(int, Clob)"})
  public void testSetClobWithIntClob_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setClob(anyInt(), Mockito.<Clob>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setClob(1, new SerialClob("AZAZ".toCharArray())));
    verify(preparedStatement).setClob(eq(1), isA(Clob.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setClob(int, Reader, long)} with {@code int}, {@code
   * Reader}, {@code long}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setClob(int, Reader, long)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setClob(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setClob(int, Reader, long)"})
  public void testSetClobWithIntReaderLong_givenPreparedStatementSetClobDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setClob(anyInt(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setClob(1, new StringReader("foo"), 3L);

    // Assert
    verify(preparedStatement).setClob(eq(1), isA(Reader.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setClob(int, Reader, long)} with {@code int}, {@code
   * Reader}, {@code long}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setClob(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setClob(int, Reader, long)"})
  public void testSetClobWithIntReaderLong_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setClob(anyInt(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setClob(1, new StringReader("foo"), 3L));
    verify(preparedStatement).setClob(eq(1), isA(Reader.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setClob(int, Reader)} with {@code int}, {@code Reader}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setClob(int, Reader)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setClob(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setClob(int, Reader)"})
  public void testSetClobWithIntReader_givenPreparedStatementSetClobDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setClob(anyInt(), Mockito.<Reader>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setClob(1, new StringReader("foo"));

    // Assert
    verify(preparedStatement).setClob(eq(1), isA(Reader.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setClob(int, Reader)} with {@code int}, {@code Reader}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setClob(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setClob(int, Reader)"})
  public void testSetClobWithIntReader_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setClob(anyInt(), Mockito.<Reader>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcPreparedStatementImpl.setClob(1, new StringReader("foo")));
    verify(preparedStatement).setClob(eq(1), isA(Reader.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setArray(int, Array)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setArray(int, Array)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setArray(int, Array)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setArray(int, Array)"})
  public void testSetArray_givenPreparedStatementSetArrayDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setArray(anyInt(), Mockito.<Array>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);
    Object[] items = new Object[] {"Items"};

    // Act
    jdbcPreparedStatementImpl.setArray(1, new JDBCArrayImpl("Type Name", 1, items));

    // Assert
    verify(preparedStatement).setArray(eq(1), isA(Array.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setArray(int, Array)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setArray(int, Array)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setArray(int, Array)"})
  public void testSetArray_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setArray(anyInt(), Mockito.<Array>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);
    Object[] items = new Object[] {"Items"};

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setArray(1, new JDBCArrayImpl("Type Name", 1, items)));
    verify(preparedStatement).setArray(eq(1), isA(Array.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#getMetaData()}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#getMetaData()} return {@code
   *       null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#getMetaData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ResultSetMetaData JDBCPreparedStatementImpl.getMetaData()"})
  public void testGetMetaData_givenPreparedStatementGetMetaDataReturnNull_thenReturnNull()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.getMetaData()).thenReturn(null);

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    ResultSetMetaData actualMetaData = jdbcPreparedStatementImpl.getMetaData();

    // Assert
    verify(preparedStatement).getMetaData();
    verify(stmtSupplier).get();
    assertNull(actualMetaData);
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#getMetaData()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#getMetaData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ResultSetMetaData JDBCPreparedStatementImpl.getMetaData()"})
  public void testGetMetaData_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.getMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.getMetaData());
    verify(preparedStatement).getMetaData();
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#getParameterMetaData()}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#getParameterMetaData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ParameterMetaData JDBCPreparedStatementImpl.getParameterMetaData()"})
  public void testGetParameterMetaData() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.getParameterMetaData()).thenReturn(mock(ParameterMetaData.class));

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.getParameterMetaData();

    // Assert
    verify(preparedStatement).getParameterMetaData();
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#getParameterMetaData()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#getParameterMetaData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ParameterMetaData JDBCPreparedStatementImpl.getParameterMetaData()"})
  public void testGetParameterMetaData_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    when(preparedStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.getParameterMetaData());
    verify(preparedStatement).getParameterMetaData();
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setRowId(int, RowId)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setRowId(int, RowId)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setRowId(int, RowId)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setRowId(int, RowId)"})
  public void testSetRowId_givenPreparedStatementSetRowIdDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setRowId(anyInt(), Mockito.<RowId>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setRowId(1, null);

    // Assert
    verify(preparedStatement).setRowId(eq(1), isNull());
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setRowId(int, RowId)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setRowId(int, RowId)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setRowId(int, RowId)"})
  public void testSetRowId_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setRowId(anyInt(), Mockito.<RowId>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setRowId(1, null));
    verify(preparedStatement).setRowId(eq(1), isNull());
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNString(int, String)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setNString(int, String)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNString(int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNString(int, String)"})
  public void testSetNString_givenPreparedStatementSetNStringDoesNothing() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setNString(anyInt(), Mockito.<String>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setNString(1, "foo");

    // Assert
    verify(preparedStatement).setNString(1, "foo");
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNString(int, String)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNString(int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNString(int, String)"})
  public void testSetNString_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setNString(anyInt(), Mockito.<String>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setNString(1, "foo"));
    verify(preparedStatement).setNString(1, "foo");
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNCharacterStream(int, Reader)} with {@code
   * parameterIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNCharacterStream(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNCharacterStream(int, Reader)"})
  public void testSetNCharacterStreamWithParameterIndexX() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setNCharacterStream(anyInt(), Mockito.<Reader>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setNCharacterStream(1, new StringReader("foo"));

    // Assert
    verify(preparedStatement).setNCharacterStream(eq(1), isA(Reader.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNCharacterStream(int, Reader, long)} with {@code
   * parameterIndex}, {@code x}, {@code length}.
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNCharacterStream(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNCharacterStream(int, Reader, long)"})
  public void testSetNCharacterStreamWithParameterIndexXLength() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing()
        .when(preparedStatement)
        .setNCharacterStream(anyInt(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setNCharacterStream(1, new StringReader("foo"), 3L);

    // Assert
    verify(preparedStatement).setNCharacterStream(eq(1), isA(Reader.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNCharacterStream(int, Reader, long)} with {@code
   * parameterIndex}, {@code x}, {@code length}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNCharacterStream(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNCharacterStream(int, Reader, long)"})
  public void testSetNCharacterStreamWithParameterIndexXLength_thenThrowSQLException()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setNCharacterStream(anyInt(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setNCharacterStream(1, new StringReader("foo"), 3L));
    verify(preparedStatement).setNCharacterStream(eq(1), isA(Reader.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNCharacterStream(int, Reader)} with {@code
   * parameterIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNCharacterStream(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNCharacterStream(int, Reader)"})
  public void testSetNCharacterStreamWithParameterIndexX_thenThrowSQLException()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setNCharacterStream(anyInt(), Mockito.<Reader>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setNCharacterStream(1, new StringReader("foo")));
    verify(preparedStatement).setNCharacterStream(eq(1), isA(Reader.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNClob(int, NClob)} with {@code int}, {@code NClob}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setNClob(int, NClob)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNClob(int, NClob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNClob(int, NClob)"})
  public void testSetNClobWithIntNClob_givenPreparedStatementSetNClobDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setNClob(anyInt(), Mockito.<NClob>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setNClob(1, mock(NClob.class));

    // Assert
    verify(preparedStatement).setNClob(eq(1), isA(NClob.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNClob(int, NClob)} with {@code int}, {@code NClob}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNClob(int, NClob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNClob(int, NClob)"})
  public void testSetNClobWithIntNClob_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setNClob(anyInt(), Mockito.<NClob>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcPreparedStatementImpl.setNClob(1, mock(NClob.class)));
    verify(preparedStatement).setNClob(eq(1), isA(NClob.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNClob(int, Reader, long)} with {@code int}, {@code
   * Reader}, {@code long}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setNClob(int, Reader, long)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNClob(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNClob(int, Reader, long)"})
  public void testSetNClobWithIntReaderLong_givenPreparedStatementSetNClobDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setNClob(anyInt(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setNClob(1, new StringReader("foo"), 3L);

    // Assert
    verify(preparedStatement).setNClob(eq(1), isA(Reader.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNClob(int, Reader, long)} with {@code int}, {@code
   * Reader}, {@code long}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNClob(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNClob(int, Reader, long)"})
  public void testSetNClobWithIntReaderLong_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException())
        .when(preparedStatement)
        .setNClob(anyInt(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcPreparedStatementImpl.setNClob(1, new StringReader("foo"), 3L));
    verify(preparedStatement).setNClob(eq(1), isA(Reader.class), eq(3L));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNClob(int, Reader)} with {@code int}, {@code Reader}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setNClob(int, Reader)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNClob(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNClob(int, Reader)"})
  public void testSetNClobWithIntReader_givenPreparedStatementSetNClobDoesNothing()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setNClob(anyInt(), Mockito.<Reader>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setNClob(1, new StringReader("foo"));

    // Assert
    verify(preparedStatement).setNClob(eq(1), isA(Reader.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setNClob(int, Reader)} with {@code int}, {@code Reader}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setNClob(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setNClob(int, Reader)"})
  public void testSetNClobWithIntReader_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setNClob(anyInt(), Mockito.<Reader>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcPreparedStatementImpl.setNClob(1, new StringReader("foo")));
    verify(preparedStatement).setNClob(eq(1), isA(Reader.class));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setSQLXML(int, SQLXML)}.
   *
   * <ul>
   *   <li>Given {@link PreparedStatement} {@link PreparedStatement#setSQLXML(int, SQLXML)} does
   *       nothing.
   *   <li>Then calls {@link PreparedStatement#setSQLXML(int, SQLXML)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setSQLXML(int, SQLXML)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setSQLXML(int, SQLXML)"})
  public void testSetSQLXML_givenPreparedStatementSetSQLXMLDoesNothing_thenCallsSetSQLXML()
      throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doNothing().when(preparedStatement).setSQLXML(anyInt(), Mockito.<SQLXML>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act
    jdbcPreparedStatementImpl.setSQLXML(1, null);

    // Assert
    verify(preparedStatement).setSQLXML(eq(1), isNull());
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCPreparedStatementImpl#setSQLXML(int, SQLXML)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCPreparedStatementImpl#setSQLXML(int, SQLXML)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCPreparedStatementImpl.setSQLXML(int, SQLXML)"})
  public void testSetSQLXML_thenThrowSQLException() throws SQLException {
    // Arrange
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    doThrow(new SQLException()).when(preparedStatement).setSQLXML(anyInt(), Mockito.<SQLXML>any());

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(preparedStatement);
    JDBCPreparedStatementImpl<PreparedStatement> jdbcPreparedStatementImpl =
        new JDBCPreparedStatementImpl<>(mock(JDBCSession.class), stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcPreparedStatementImpl.setSQLXML(1, null));
    verify(preparedStatement).setSQLXML(eq(1), isNull());
    verify(stmtSupplier).get();
  }
}
