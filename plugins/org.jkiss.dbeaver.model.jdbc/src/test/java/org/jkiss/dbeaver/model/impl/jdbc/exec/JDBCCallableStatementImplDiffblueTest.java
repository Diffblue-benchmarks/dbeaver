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
import java.io.File;
import java.io.IOException;
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
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialRef;
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
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCContentBytes;
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCSQLXMLImpl;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCCallableStatementImplDiffblueTest {
  /**
   * Test {@link JDBCCallableStatementImpl#JDBCCallableStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}.
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#JDBCCallableStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCCallableStatementImpl.<init>(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testNewJDBCCallableStatementImpl() throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    // Act
    JDBCCallableStatementImpl actualJdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualJdbcCallableStatementImpl.getResultSet() instanceof JDBCResultSetCallable);
    assertEquals("Query", actualJdbcCallableStatementImpl.getFormattedQuery());
    assertEquals("Query", actualJdbcCallableStatementImpl.getQueryString());
    assertNull(actualJdbcCallableStatementImpl.getBlockThread());
    assertNull(actualJdbcCallableStatementImpl.getStatementWarnings());
    assertNull(actualJdbcCallableStatementImpl.getGeneratedKeys());
    assertNull(actualJdbcCallableStatementImpl.getMetaData());
    assertNull(actualJdbcCallableStatementImpl.getWarnings());
    assertNull(actualJdbcCallableStatementImpl.getStatementSource());
    assertEquals(0, actualJdbcCallableStatementImpl.getFetchDirection());
    assertEquals(0, actualJdbcCallableStatementImpl.getFetchSize());
    assertEquals(0, actualJdbcCallableStatementImpl.getMaxFieldSize());
    assertEquals(0, actualJdbcCallableStatementImpl.getMaxRows());
    assertEquals(0, actualJdbcCallableStatementImpl.getQueryTimeout());
    assertEquals(0, actualJdbcCallableStatementImpl.getResultSetConcurrency());
    assertEquals(0, actualJdbcCallableStatementImpl.getResultSetHoldability());
    assertEquals(0, actualJdbcCallableStatementImpl.getResultSetType());
    assertEquals(0, actualJdbcCallableStatementImpl.getUpdateCount());
    assertEquals(0L, actualJdbcCallableStatementImpl.getLargeMaxRows());
    assertEquals(0L, actualJdbcCallableStatementImpl.getLargeUpdateCount());
    assertEquals(0L, actualJdbcCallableStatementImpl.getUpdateRowCount());
    assertFalse(actualJdbcCallableStatementImpl.getMoreResults());
    assertFalse(actualJdbcCallableStatementImpl.isCloseOnCompletion());
    assertFalse(actualJdbcCallableStatementImpl.isClosed());
    assertFalse(actualJdbcCallableStatementImpl.isPoolable());
    assertFalse(actualJdbcCallableStatementImpl.isQMLoggingEnabled());
    assertFalse(actualJdbcCallableStatementImpl.isStatementClosed());
    assertTrue(actualJdbcCallableStatementImpl.disableLogging);
    assertSame(connection, actualJdbcCallableStatementImpl.getSession());
    assertSame(connection, actualJdbcCallableStatementImpl.getConnection());
  }

  /**
   * Test {@link JDBCCallableStatementImpl#JDBCCallableStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}.
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#JDBCCallableStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCCallableStatementImpl.<init>(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testNewJDBCCallableStatementImpl2() throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    // Act
    JDBCCallableStatementImpl actualJdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualJdbcCallableStatementImpl.getResultSet() instanceof JDBCResultSetCallable);
    assertEquals("Query", actualJdbcCallableStatementImpl.getFormattedQuery());
    assertEquals("Query", actualJdbcCallableStatementImpl.getQueryString());
    assertNull(actualJdbcCallableStatementImpl.getBlockThread());
    assertNull(actualJdbcCallableStatementImpl.getStatementWarnings());
    assertNull(actualJdbcCallableStatementImpl.getGeneratedKeys());
    assertNull(actualJdbcCallableStatementImpl.getMetaData());
    assertNull(actualJdbcCallableStatementImpl.getWarnings());
    assertNull(actualJdbcCallableStatementImpl.getStatementSource());
    assertEquals(0, actualJdbcCallableStatementImpl.getFetchDirection());
    assertEquals(0, actualJdbcCallableStatementImpl.getFetchSize());
    assertEquals(0, actualJdbcCallableStatementImpl.getMaxFieldSize());
    assertEquals(0, actualJdbcCallableStatementImpl.getMaxRows());
    assertEquals(0, actualJdbcCallableStatementImpl.getQueryTimeout());
    assertEquals(0, actualJdbcCallableStatementImpl.getResultSetConcurrency());
    assertEquals(0, actualJdbcCallableStatementImpl.getResultSetHoldability());
    assertEquals(0, actualJdbcCallableStatementImpl.getResultSetType());
    assertEquals(0, actualJdbcCallableStatementImpl.getUpdateCount());
    assertEquals(0L, actualJdbcCallableStatementImpl.getLargeMaxRows());
    assertEquals(0L, actualJdbcCallableStatementImpl.getLargeUpdateCount());
    assertEquals(0L, actualJdbcCallableStatementImpl.getUpdateRowCount());
    assertFalse(actualJdbcCallableStatementImpl.getMoreResults());
    assertFalse(actualJdbcCallableStatementImpl.isCloseOnCompletion());
    assertFalse(actualJdbcCallableStatementImpl.isClosed());
    assertFalse(actualJdbcCallableStatementImpl.isPoolable());
    assertFalse(actualJdbcCallableStatementImpl.isQMLoggingEnabled());
    assertFalse(actualJdbcCallableStatementImpl.isStatementClosed());
    assertTrue(actualJdbcCallableStatementImpl.disableLogging);
    assertSame(connection, actualJdbcCallableStatementImpl.getSession());
    assertSame(connection, actualJdbcCallableStatementImpl.getConnection());
  }

  /**
   * Test {@link JDBCCallableStatementImpl#JDBCCallableStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}.
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#JDBCCallableStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCCallableStatementImpl.<init>(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testNewJDBCCallableStatementImpl3() throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenThrow(new SQLException());
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    // Act
    JDBCCallableStatementImpl actualJdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualJdbcCallableStatementImpl.getResultSet() instanceof JDBCResultSetCallable);
    assertEquals("Query", actualJdbcCallableStatementImpl.getFormattedQuery());
    assertEquals("Query", actualJdbcCallableStatementImpl.getQueryString());
    assertNull(actualJdbcCallableStatementImpl.getBlockThread());
    assertNull(actualJdbcCallableStatementImpl.getStatementWarnings());
    assertNull(actualJdbcCallableStatementImpl.getGeneratedKeys());
    assertNull(actualJdbcCallableStatementImpl.getMetaData());
    assertNull(actualJdbcCallableStatementImpl.getWarnings());
    assertNull(actualJdbcCallableStatementImpl.getStatementSource());
    assertEquals(0, actualJdbcCallableStatementImpl.getFetchDirection());
    assertEquals(0, actualJdbcCallableStatementImpl.getFetchSize());
    assertEquals(0, actualJdbcCallableStatementImpl.getMaxFieldSize());
    assertEquals(0, actualJdbcCallableStatementImpl.getMaxRows());
    assertEquals(0, actualJdbcCallableStatementImpl.getQueryTimeout());
    assertEquals(0, actualJdbcCallableStatementImpl.getResultSetConcurrency());
    assertEquals(0, actualJdbcCallableStatementImpl.getResultSetHoldability());
    assertEquals(0, actualJdbcCallableStatementImpl.getResultSetType());
    assertEquals(0, actualJdbcCallableStatementImpl.getUpdateCount());
    assertEquals(0L, actualJdbcCallableStatementImpl.getLargeMaxRows());
    assertEquals(0L, actualJdbcCallableStatementImpl.getLargeUpdateCount());
    assertEquals(0L, actualJdbcCallableStatementImpl.getUpdateRowCount());
    assertFalse(actualJdbcCallableStatementImpl.getMoreResults());
    assertFalse(actualJdbcCallableStatementImpl.isCloseOnCompletion());
    assertFalse(actualJdbcCallableStatementImpl.isClosed());
    assertFalse(actualJdbcCallableStatementImpl.isPoolable());
    assertFalse(actualJdbcCallableStatementImpl.isQMLoggingEnabled());
    assertFalse(actualJdbcCallableStatementImpl.isStatementClosed());
    assertTrue(actualJdbcCallableStatementImpl.disableLogging);
    assertSame(connection, actualJdbcCallableStatementImpl.getSession());
    assertSame(connection, actualJdbcCallableStatementImpl.getConnection());
  }

  /**
   * Test {@link JDBCCallableStatementImpl#JDBCCallableStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link ParameterMetaData} {@link ParameterMetaData#getParameterMode(int)} return
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#JDBCCallableStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCCallableStatementImpl.<init>(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testNewJDBCCallableStatementImpl_givenParameterMetaDataGetParameterModeReturnOne()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    // Act
    JDBCCallableStatementImpl actualJdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualJdbcCallableStatementImpl.getResultSet() instanceof JDBCResultSetCallable);
    assertEquals("Query", actualJdbcCallableStatementImpl.getFormattedQuery());
    assertEquals("Query", actualJdbcCallableStatementImpl.getQueryString());
    assertNull(actualJdbcCallableStatementImpl.getBlockThread());
    assertNull(actualJdbcCallableStatementImpl.getStatementWarnings());
    assertNull(actualJdbcCallableStatementImpl.getGeneratedKeys());
    assertNull(actualJdbcCallableStatementImpl.getMetaData());
    assertNull(actualJdbcCallableStatementImpl.getWarnings());
    assertNull(actualJdbcCallableStatementImpl.getStatementSource());
    assertEquals(0, actualJdbcCallableStatementImpl.getFetchDirection());
    assertEquals(0, actualJdbcCallableStatementImpl.getFetchSize());
    assertEquals(0, actualJdbcCallableStatementImpl.getMaxFieldSize());
    assertEquals(0, actualJdbcCallableStatementImpl.getMaxRows());
    assertEquals(0, actualJdbcCallableStatementImpl.getQueryTimeout());
    assertEquals(0, actualJdbcCallableStatementImpl.getResultSetConcurrency());
    assertEquals(0, actualJdbcCallableStatementImpl.getResultSetHoldability());
    assertEquals(0, actualJdbcCallableStatementImpl.getResultSetType());
    assertEquals(0, actualJdbcCallableStatementImpl.getUpdateCount());
    assertEquals(0L, actualJdbcCallableStatementImpl.getLargeMaxRows());
    assertEquals(0L, actualJdbcCallableStatementImpl.getLargeUpdateCount());
    assertEquals(0L, actualJdbcCallableStatementImpl.getUpdateRowCount());
    assertFalse(actualJdbcCallableStatementImpl.getMoreResults());
    assertFalse(actualJdbcCallableStatementImpl.isCloseOnCompletion());
    assertFalse(actualJdbcCallableStatementImpl.isClosed());
    assertFalse(actualJdbcCallableStatementImpl.isPoolable());
    assertFalse(actualJdbcCallableStatementImpl.isQMLoggingEnabled());
    assertFalse(actualJdbcCallableStatementImpl.isStatementClosed());
    assertTrue(actualJdbcCallableStatementImpl.disableLogging);
    assertSame(connection, actualJdbcCallableStatementImpl.getSession());
    assertSame(connection, actualJdbcCallableStatementImpl.getConnection());
  }

  /**
   * Test {@link JDBCCallableStatementImpl#JDBCCallableStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#JDBCCallableStatementImpl(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCCallableStatementImpl.<init>(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testNewJDBCCallableStatementImpl_givenSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true));
    verify(stmtSupplier).get();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#close()}.
   *
   * <ul>
   *   <li>Given {@link CallableStatement} {@link CallableStatement#getParameterMetaData()} throw
   *       {@link SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.close()"})
  public void testClose_givenCallableStatementGetParameterMetaDataThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException()).when(callableStatement).close();

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);
    try (JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true)) {}

    // Act and Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(callableStatement).close();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#close()}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.close()"})
  public void testClose_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).close();

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);
    try (JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true)) {}

    // Act and Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(callableStatement).close();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#executeStatement()}.
   *
   * <ul>
   *   <li>Given {@link CallableStatement} {@link CallableStatement#execute()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#executeStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCCallableStatementImpl.executeStatement()"})
  public void testExecuteStatement_givenCallableStatementExecuteReturnTrue_thenReturnTrue()
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
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.execute()).thenReturn(true);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    boolean actualExecuteStatementResult = jdbcCallableStatementImpl.executeStatement();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement).execute();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertTrue(actualExecuteStatementResult);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#executeStatement()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#executeStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCCallableStatementImpl.executeStatement()"})
  public void testExecuteStatement_thenReturnFalse() throws SQLException, DBCException {
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
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));
    when(connection.getExecutionContext()).thenReturn(jdbcExecutionContext);

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.execute()).thenReturn(false);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    boolean actualExecuteStatementResult = jdbcCallableStatementImpl.executeStatement();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement).execute();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(connection, atLeast(1)).getProgressMonitor();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    verify(connection, atLeast(1)).getExecutionContext();
    verify(connection, atLeast(1)).setBlockThread(Mockito.<Thread>any());
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertFalse(actualExecuteStatementResult);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getResultSet()}.
   *
   * <ul>
   *   <li>Then return {@link JDBCResultSetCallable}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCCallableStatementImpl.getResultSet()"})
  public void testGetResultSet_thenReturnJDBCResultSetCallable() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getResultSet()).thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    JDBCResultSet actualResultSet = jdbcCallableStatementImpl.getResultSet();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(callableStatement).getResultSet();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualResultSet instanceof JDBCResultSetCallable);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getResultSet()}.
   *
   * <ul>
   *   <li>Then return {@link JDBCResultSetImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCCallableStatementImpl.getResultSet()"})
  public void testGetResultSet_thenReturnJDBCResultSetImpl() throws SQLException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getJdbcFactory()).thenReturn(new JDBCFactoryDefault());

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(jdbcDataSource);

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

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

    CallableStatement callableStatement2 = mock(CallableStatement.class);
    when(callableStatement2.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement2.getResultSet()).thenReturn(jdbcResultSetCallable);

    JDBCObjectSupplier<CallableStatement> stmtSupplier2 = mock(JDBCObjectSupplier.class);
    when(stmtSupplier2.get()).thenReturn(callableStatement2);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier2, "Query", true);

    // Act
    JDBCResultSet actualResultSet = jdbcCallableStatementImpl.getResultSet();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement2, atLeast(1)).getParameterMetaData();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(callableStatement2).getResultSet();
    verify(stmtSupplier2).get();
    verify(stmtSupplier).get();
    verify(connection2).getDataSource();
    verify(connection, atLeast(1)).getDataSource();
    verify(jdbcDataSource).getJdbcFactory();
    assertTrue(actualResultSet instanceof JDBCResultSetImpl);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getResultSet()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCResultSet JDBCCallableStatementImpl.getResultSet()"})
  public void testGetResultSet_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getResultSet()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getResultSet());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(callableStatement).getResultSet();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#registerOutParameter(int, int, int)} with {@code int},
   * {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#registerOutParameter(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.registerOutParameter(int, int, int)"})
  public void testRegisterOutParameterWithIntIntInt_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).registerOutParameter(anyInt(), anyInt(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.registerOutParameter(1, 1, 1);

    // Assert
    verify(callableStatement).registerOutParameter(1, 1, 1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#registerOutParameter(int, int, int)} with {@code int},
   * {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#registerOutParameter(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.registerOutParameter(int, int, int)"})
  public void testRegisterOutParameterWithIntIntInt_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .registerOutParameter(anyInt(), anyInt(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.registerOutParameter(1, 1, 1));
    verify(callableStatement).registerOutParameter(1, 1, 1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#registerOutParameter(int, int, String)} with {@code int},
   * {@code int}, {@code String}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#registerOutParameter(int, int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.registerOutParameter(int, int, String)"})
  public void testRegisterOutParameterWithIntIntString_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .registerOutParameter(anyInt(), anyInt(), Mockito.<String>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.registerOutParameter(1, 1, "Type Name");

    // Assert
    verify(callableStatement).registerOutParameter(1, 1, "Type Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#registerOutParameter(int, int, String)} with {@code int},
   * {@code int}, {@code String}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#registerOutParameter(int, int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.registerOutParameter(int, int, String)"})
  public void testRegisterOutParameterWithIntIntString_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .registerOutParameter(anyInt(), anyInt(), Mockito.<String>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.registerOutParameter(1, 1, "Type Name"));
    verify(callableStatement).registerOutParameter(1, 1, "Type Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#registerOutParameter(int, int)} with {@code int}, {@code
   * int}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#registerOutParameter(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.registerOutParameter(int, int)"})
  public void testRegisterOutParameterWithIntInt_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).registerOutParameter(anyInt(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.registerOutParameter(1, 1);

    // Assert
    verify(callableStatement).registerOutParameter(1, 1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#registerOutParameter(int, int)} with {@code int}, {@code
   * int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#registerOutParameter(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.registerOutParameter(int, int)"})
  public void testRegisterOutParameterWithIntInt_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException()).when(callableStatement).registerOutParameter(anyInt(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.registerOutParameter(1, 1));
    verify(callableStatement).registerOutParameter(1, 1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#registerOutParameter(String, int, int)} with {@code
   * String}, {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#registerOutParameter(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.registerOutParameter(String, int, int)"})
  public void testRegisterOutParameterWithStringIntInt_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .registerOutParameter(Mockito.<String>any(), anyInt(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.registerOutParameter("Parameter Name", 1, 1);

    // Assert
    verify(callableStatement).registerOutParameter("Parameter Name", 1, 1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#registerOutParameter(String, int, int)} with {@code
   * String}, {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#registerOutParameter(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.registerOutParameter(String, int, int)"})
  public void testRegisterOutParameterWithStringIntInt_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .registerOutParameter(Mockito.<String>any(), anyInt(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.registerOutParameter("Parameter Name", 1, 1));
    verify(callableStatement).registerOutParameter("Parameter Name", 1, 1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#registerOutParameter(String, int, String)} with {@code
   * String}, {@code int}, {@code String}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#registerOutParameter(String, int,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.registerOutParameter(String, int, String)"})
  public void testRegisterOutParameterWithStringIntString_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .registerOutParameter(Mockito.<String>any(), anyInt(), Mockito.<String>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.registerOutParameter("Parameter Name", 1, "Type Name");

    // Assert
    verify(callableStatement).registerOutParameter("Parameter Name", 1, "Type Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#registerOutParameter(String, int, String)} with {@code
   * String}, {@code int}, {@code String}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#registerOutParameter(String, int,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.registerOutParameter(String, int, String)"})
  public void testRegisterOutParameterWithStringIntString_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .registerOutParameter(Mockito.<String>any(), anyInt(), Mockito.<String>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.registerOutParameter("Parameter Name", 1, "Type Name"));
    verify(callableStatement).registerOutParameter("Parameter Name", 1, "Type Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#registerOutParameter(String, int)} with {@code String},
   * {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#registerOutParameter(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.registerOutParameter(String, int)"})
  public void testRegisterOutParameterWithStringInt_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).registerOutParameter(Mockito.<String>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.registerOutParameter("Parameter Name", 1);

    // Assert
    verify(callableStatement).registerOutParameter("Parameter Name", 1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#registerOutParameter(String, int)} with {@code String},
   * {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#registerOutParameter(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.registerOutParameter(String, int)"})
  public void testRegisterOutParameterWithStringInt_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .registerOutParameter(Mockito.<String>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.registerOutParameter("Parameter Name", 1));
    verify(callableStatement).registerOutParameter("Parameter Name", 1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#wasNull()}.
   *
   * <ul>
   *   <li>Given {@link CallableStatement} {@link CallableStatement#wasNull()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#wasNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCCallableStatementImpl.wasNull()"})
  public void testWasNull_givenCallableStatementWasNullThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.wasNull()).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.wasNull());
    verify(callableStatement).wasNull();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#wasNull()}.
   *
   * <ul>
   *   <li>Given {@link ParameterMetaData} {@link ParameterMetaData#getParameterMode(int)} return
   *       one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#wasNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCCallableStatementImpl.wasNull()"})
  public void testWasNull_givenParameterMetaDataGetParameterModeReturnOne_thenReturnFalse()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.wasNull()).thenReturn(false);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    boolean actualWasNullResult = jdbcCallableStatementImpl.wasNull();

    // Assert
    verify(callableStatement).wasNull();
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertFalse(actualWasNullResult);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#wasNull()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#wasNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCCallableStatementImpl.wasNull()"})
  public void testWasNull_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.wasNull()).thenReturn(true);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    boolean actualWasNullResult = jdbcCallableStatementImpl.wasNull();

    // Assert
    verify(callableStatement).wasNull();
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualWasNullResult);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getString(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getString(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCallableStatementImpl.getString(int)"})
  public void testGetStringWithParameterIndex_thenReturnString() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getString(anyInt())).thenReturn("String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    String actualString = jdbcCallableStatementImpl.getString(1);

    // Assert
    verify(callableStatement).getString(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("String", actualString);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getString(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getString(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCallableStatementImpl.getString(int)"})
  public void testGetStringWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getString(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getString(1));
    verify(callableStatement).getString(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getString(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCallableStatementImpl.getString(String)"})
  public void testGetStringWithParameterName_thenReturnString() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getString(Mockito.<String>any())).thenReturn("String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    String actualString = jdbcCallableStatementImpl.getString("Parameter Name");

    // Assert
    verify(callableStatement).getString("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("String", actualString);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getString(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCallableStatementImpl.getString(String)"})
  public void testGetStringWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getString(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getString("Parameter Name"));
    verify(callableStatement).getString("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBoolean(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBoolean(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCCallableStatementImpl.getBoolean(int)"})
  public void testGetBooleanWithParameterIndex_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(anyInt())).thenReturn(false);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    boolean actualBoolean = jdbcCallableStatementImpl.getBoolean(1);

    // Assert
    verify(callableStatement).getBoolean(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertFalse(actualBoolean);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBoolean(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBoolean(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCCallableStatementImpl.getBoolean(int)"})
  public void testGetBooleanWithParameterIndex_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(anyInt())).thenReturn(true);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    boolean actualBoolean = jdbcCallableStatementImpl.getBoolean(1);

    // Assert
    verify(callableStatement).getBoolean(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualBoolean);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBoolean(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBoolean(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCCallableStatementImpl.getBoolean(int)"})
  public void testGetBooleanWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getBoolean(1));
    verify(callableStatement).getBoolean(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBoolean(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBoolean(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCCallableStatementImpl.getBoolean(String)"})
  public void testGetBooleanWithParameterName_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(false);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    boolean actualBoolean = jdbcCallableStatementImpl.getBoolean("Parameter Name");

    // Assert
    verify(callableStatement).getBoolean("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertFalse(actualBoolean);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBoolean(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBoolean(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCCallableStatementImpl.getBoolean(String)"})
  public void testGetBooleanWithParameterName_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(true);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    boolean actualBoolean = jdbcCallableStatementImpl.getBoolean("Parameter Name");

    // Assert
    verify(callableStatement).getBoolean("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualBoolean);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBoolean(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBoolean(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCCallableStatementImpl.getBoolean(String)"})
  public void testGetBooleanWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getBoolean("Parameter Name"));
    verify(callableStatement).getBoolean("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getByte(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getByte(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JDBCCallableStatementImpl.getByte(int)"})
  public void testGetByteWithParameterIndex_thenReturnA() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getByte(anyInt())).thenReturn((byte) 'A');
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    byte actualByte = jdbcCallableStatementImpl.getByte(1);

    // Assert
    verify(callableStatement).getByte(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals('A', actualByte);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getByte(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getByte(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JDBCCallableStatementImpl.getByte(int)"})
  public void testGetByteWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getByte(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getByte(1));
    verify(callableStatement).getByte(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getByte(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getByte(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JDBCCallableStatementImpl.getByte(String)"})
  public void testGetByteWithParameterName_thenReturnA() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getByte(Mockito.<String>any())).thenReturn((byte) 'A');
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    byte actualByte = jdbcCallableStatementImpl.getByte("Parameter Name");

    // Assert
    verify(callableStatement).getByte("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals('A', actualByte);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getByte(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getByte(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JDBCCallableStatementImpl.getByte(String)"})
  public void testGetByteWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getByte(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getByte("Parameter Name"));
    verify(callableStatement).getByte("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getShort(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getShort(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JDBCCallableStatementImpl.getShort(int)"})
  public void testGetShortWithParameterIndex_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getShort(anyInt())).thenReturn((short) 1);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    short actualShort = jdbcCallableStatementImpl.getShort(1);

    // Assert
    verify(callableStatement).getShort(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals((short) 1, actualShort);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getShort(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getShort(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JDBCCallableStatementImpl.getShort(int)"})
  public void testGetShortWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getShort(anyInt())).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getShort(1));
    verify(callableStatement).getShort(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getShort(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getShort(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JDBCCallableStatementImpl.getShort(String)"})
  public void testGetShortWithParameterName_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getShort(Mockito.<String>any())).thenReturn((short) 1);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    short actualShort = jdbcCallableStatementImpl.getShort("Parameter Name");

    // Assert
    verify(callableStatement).getShort("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals((short) 1, actualShort);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getShort(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getShort(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JDBCCallableStatementImpl.getShort(String)"})
  public void testGetShortWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getShort(Mockito.<String>any())).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getShort("Parameter Name"));
    verify(callableStatement).getShort("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getInt(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getInt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCCallableStatementImpl.getInt(int)"})
  public void testGetIntWithParameterIndex_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getInt(anyInt())).thenReturn(1);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    int actualInt = jdbcCallableStatementImpl.getInt(1);

    // Assert
    verify(callableStatement).getInt(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1, actualInt);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getInt(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getInt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCCallableStatementImpl.getInt(int)"})
  public void testGetIntWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getInt(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getInt(1));
    verify(callableStatement).getInt(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getInt(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getInt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCCallableStatementImpl.getInt(String)"})
  public void testGetIntWithParameterName_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getInt(Mockito.<String>any())).thenReturn(1);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    int actualInt = jdbcCallableStatementImpl.getInt("Parameter Name");

    // Assert
    verify(callableStatement).getInt("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1, actualInt);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getInt(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getInt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCCallableStatementImpl.getInt(String)"})
  public void testGetIntWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getInt(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getInt("Parameter Name"));
    verify(callableStatement).getInt("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getLong(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getLong(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCCallableStatementImpl.getLong(int)"})
  public void testGetLongWithParameterIndex_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getLong(anyInt())).thenReturn(1L);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    long actualLong = jdbcCallableStatementImpl.getLong(1);

    // Assert
    verify(callableStatement).getLong(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1L, actualLong);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getLong(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getLong(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCCallableStatementImpl.getLong(int)"})
  public void testGetLongWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getLong(anyInt())).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getLong(1));
    verify(callableStatement).getLong(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getLong(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getLong(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCCallableStatementImpl.getLong(String)"})
  public void testGetLongWithParameterName_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getLong(Mockito.<String>any())).thenReturn(1L);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    long actualLong = jdbcCallableStatementImpl.getLong("Parameter Name");

    // Assert
    verify(callableStatement).getLong("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1L, actualLong);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getLong(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getLong(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCCallableStatementImpl.getLong(String)"})
  public void testGetLongWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getLong(Mockito.<String>any())).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getLong("Parameter Name"));
    verify(callableStatement).getLong("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getFloat(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getFloat(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JDBCCallableStatementImpl.getFloat(int)"})
  public void testGetFloatWithParameterIndex_thenReturnTen() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getFloat(anyInt())).thenReturn(10.0f);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    float actualFloat = jdbcCallableStatementImpl.getFloat(1);

    // Assert
    verify(callableStatement).getFloat(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(10.0f, actualFloat, 0.0f);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getFloat(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getFloat(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JDBCCallableStatementImpl.getFloat(int)"})
  public void testGetFloatWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getFloat(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getFloat(1));
    verify(callableStatement).getFloat(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getFloat(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getFloat(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JDBCCallableStatementImpl.getFloat(String)"})
  public void testGetFloatWithParameterName_thenReturnTen() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getFloat(Mockito.<String>any())).thenReturn(10.0f);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    float actualFloat = jdbcCallableStatementImpl.getFloat("Parameter Name");

    // Assert
    verify(callableStatement).getFloat("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(10.0f, actualFloat, 0.0f);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getFloat(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getFloat(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JDBCCallableStatementImpl.getFloat(String)"})
  public void testGetFloatWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getFloat(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getFloat("Parameter Name"));
    verify(callableStatement).getFloat("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getDouble(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getDouble(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JDBCCallableStatementImpl.getDouble(int)"})
  public void testGetDoubleWithParameterIndex_thenReturnTen() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDouble(anyInt())).thenReturn(10.0d);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    double actualDouble = jdbcCallableStatementImpl.getDouble(1);

    // Assert
    verify(callableStatement).getDouble(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(10.0d, actualDouble, 0.0);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getDouble(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getDouble(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JDBCCallableStatementImpl.getDouble(int)"})
  public void testGetDoubleWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDouble(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getDouble(1));
    verify(callableStatement).getDouble(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getDouble(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getDouble(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JDBCCallableStatementImpl.getDouble(String)"})
  public void testGetDoubleWithParameterName_thenReturnTen() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDouble(Mockito.<String>any())).thenReturn(10.0d);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    double actualDouble = jdbcCallableStatementImpl.getDouble("Parameter Name");

    // Assert
    verify(callableStatement).getDouble("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(10.0d, actualDouble, 0.0);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getDouble(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getDouble(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JDBCCallableStatementImpl.getDouble(String)"})
  public void testGetDoubleWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDouble(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getDouble("Parameter Name"));
    verify(callableStatement).getDouble("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBigDecimal(int, int)} with {@code parameterIndex},
   * {@code scale}.
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCCallableStatementImpl.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithParameterIndexScale() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt(), anyInt())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    BigDecimal actualBigDecimal = jdbcCallableStatementImpl.getBigDecimal(1, 3);

    // Assert
    verify(callableStatement).getBigDecimal(1, 3);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBigDecimal(int, int)} with {@code parameterIndex},
   * {@code scale}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterMode(int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCCallableStatementImpl.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithParameterIndexScale_thenCallsGetParameterMode()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(3);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt(), anyInt())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    BigDecimal actualBigDecimal = jdbcCallableStatementImpl.getBigDecimal(3, 3);

    // Assert
    verify(callableStatement).getBigDecimal(3, 3);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBigDecimal(int, int)} with {@code parameterIndex},
   * {@code scale}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCCallableStatementImpl.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithParameterIndexScale_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt(), anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getBigDecimal(1, 3));
    verify(callableStatement).getBigDecimal(1, 3);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBigDecimal(int, int)} with {@code parameterIndex},
   * {@code scale}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then calls {@link ParameterMetaData#getParameterMode(int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCCallableStatementImpl.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithParameterIndexScale_whenMinusOne_thenCallsGetParameterMode()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(3);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt(), anyInt())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    BigDecimal actualBigDecimal = jdbcCallableStatementImpl.getBigDecimal(-1, 3);

    // Assert
    verify(callableStatement).getBigDecimal(-1, 3);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBigDecimal(int, int)} with {@code parameterIndex},
   * {@code scale}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then calls {@link ParameterMetaData#getParameterMode(int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCCallableStatementImpl.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithParameterIndexScale_whenZero_thenCallsGetParameterMode()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(3);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt(), anyInt())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    BigDecimal actualBigDecimal = jdbcCallableStatementImpl.getBigDecimal(0, 3);

    // Assert
    verify(callableStatement).getBigDecimal(0, 3);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBigDecimal(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBigDecimal(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCCallableStatementImpl.getBigDecimal(int)"})
  public void testGetBigDecimalWithParameterIndex_thenReturnBigDecimalWith23() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    BigDecimal actualBigDecimal = jdbcCallableStatementImpl.getBigDecimal(1);

    // Assert
    verify(callableStatement).getBigDecimal(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBigDecimal(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBigDecimal(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCCallableStatementImpl.getBigDecimal(int)"})
  public void testGetBigDecimalWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getBigDecimal(1));
    verify(callableStatement).getBigDecimal(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBigDecimal(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBigDecimal(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCCallableStatementImpl.getBigDecimal(String)"})
  public void testGetBigDecimalWithParameterName_thenReturnBigDecimalWith23() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(Mockito.<String>any())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    BigDecimal actualBigDecimal = jdbcCallableStatementImpl.getBigDecimal("2.3");

    // Assert
    verify(callableStatement).getBigDecimal("2.3");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBigDecimal(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBigDecimal(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCCallableStatementImpl.getBigDecimal(String)"})
  public void testGetBigDecimalWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getBigDecimal("2.3"));
    verify(callableStatement).getBigDecimal("2.3");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBytes(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code AXAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBytes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] JDBCCallableStatementImpl.getBytes(int)"})
  public void testGetBytesWithParameterIndex_thenReturnAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBytes(anyInt())).thenReturn("AXAXAXAX".getBytes("UTF-8"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    byte[] actualBytes = jdbcCallableStatementImpl.getBytes(1);

    // Assert
    verify(callableStatement).getBytes(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBytes);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBytes(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBytes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] JDBCCallableStatementImpl.getBytes(int)"})
  public void testGetBytesWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBytes(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getBytes(1));
    verify(callableStatement).getBytes(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBytes(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code AXAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] JDBCCallableStatementImpl.getBytes(String)"})
  public void testGetBytesWithParameterName_thenReturnAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBytes(Mockito.<String>any()))
        .thenReturn("AXAXAXAX".getBytes("UTF-8"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    byte[] actualBytes = jdbcCallableStatementImpl.getBytes("Parameter Name");

    // Assert
    verify(callableStatement).getBytes("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBytes);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBytes(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] JDBCCallableStatementImpl.getBytes(String)"})
  public void testGetBytesWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBytes(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getBytes("Parameter Name"));
    verify(callableStatement).getBytes("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getDate(int, Calendar)} with {@code parameterIndex},
   * {@code cal}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getDate(int, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCCallableStatementImpl.getDate(int, Calendar)"})
  public void testGetDateWithParameterIndexCal_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(anyInt(), Mockito.<Calendar>any())).thenReturn(new Date(1L));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.getDate(1, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getDate(eq(1), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getDate(int, Calendar)} with {@code parameterIndex},
   * {@code cal}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getDate(int, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCCallableStatementImpl.getDate(int, Calendar)"})
  public void testGetDateWithParameterIndexCal_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(anyInt(), Mockito.<Calendar>any()))
        .thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.getDate(1, new GregorianCalendar(1, 1, 1)));
    verify(callableStatement).getDate(eq(1), isA(Calendar.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getDate(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getDate(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCCallableStatementImpl.getDate(int)"})
  public void testGetDateWithParameterIndex_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(anyInt())).thenReturn(new Date(1L));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.getDate(1);

    // Assert
    verify(callableStatement).getDate(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getDate(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getDate(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCCallableStatementImpl.getDate(int)"})
  public void testGetDateWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getDate(1));
    verify(callableStatement).getDate(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getDate(String, Calendar)} with {@code parameterName},
   * {@code cal}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getDate(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCCallableStatementImpl.getDate(String, Calendar)"})
  public void testGetDateWithParameterNameCal_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(Mockito.<String>any(), Mockito.<Calendar>any()))
        .thenReturn(new Date(1L));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.getDate("Parameter Name", new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getDate(eq("Parameter Name"), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getDate(String, Calendar)} with {@code parameterName},
   * {@code cal}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getDate(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCCallableStatementImpl.getDate(String, Calendar)"})
  public void testGetDateWithParameterNameCal_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(Mockito.<String>any(), Mockito.<Calendar>any()))
        .thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.getDate("Parameter Name", new GregorianCalendar(1, 1, 1)));
    verify(callableStatement).getDate(eq("Parameter Name"), isA(Calendar.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getDate(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getDate(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCCallableStatementImpl.getDate(String)"})
  public void testGetDateWithParameterName_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(Mockito.<String>any())).thenReturn(new Date(1L));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.getDate("Parameter Name");

    // Assert
    verify(callableStatement).getDate("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getDate(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getDate(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCCallableStatementImpl.getDate(String)"})
  public void testGetDateWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getDate("Parameter Name"));
    verify(callableStatement).getDate("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTime(int, Calendar)} with {@code parameterIndex},
   * {@code cal}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTime(int, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCCallableStatementImpl.getTime(int, Calendar)"})
  public void testGetTimeWithParameterIndexCal_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getTime(anyInt(), Mockito.<Calendar>any())).thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Time actualTime = jdbcCallableStatementImpl.getTime(1, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getTime(eq(1), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTime);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTime(int, Calendar)} with {@code parameterIndex},
   * {@code cal}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTime(int, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCCallableStatementImpl.getTime(int, Calendar)"})
  public void testGetTimeWithParameterIndexCal_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getTime(anyInt(), Mockito.<Calendar>any()))
        .thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.getTime(1, new GregorianCalendar(1, 1, 1)));
    verify(callableStatement).getTime(eq(1), isA(Calendar.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTime(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCCallableStatementImpl.getTime(int)"})
  public void testGetTimeWithParameterIndex_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getTime(anyInt())).thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Time actualTime = jdbcCallableStatementImpl.getTime(1);

    // Assert
    verify(callableStatement).getTime(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTime);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTime(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCCallableStatementImpl.getTime(int)"})
  public void testGetTimeWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getTime(anyInt())).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getTime(1));
    verify(callableStatement).getTime(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTime(String, Calendar)} with {@code parameterName},
   * {@code cal}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTime(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCCallableStatementImpl.getTime(String, Calendar)"})
  public void testGetTimeWithParameterNameCal_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getTime(Mockito.<String>any(), Mockito.<Calendar>any()))
        .thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Time actualTime =
        jdbcCallableStatementImpl.getTime("Parameter Name", new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getTime(eq("Parameter Name"), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTime);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTime(String, Calendar)} with {@code parameterName},
   * {@code cal}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTime(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCCallableStatementImpl.getTime(String, Calendar)"})
  public void testGetTimeWithParameterNameCal_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getTime(Mockito.<String>any(), Mockito.<Calendar>any()))
        .thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.getTime("Parameter Name", new GregorianCalendar(1, 1, 1)));
    verify(callableStatement).getTime(eq("Parameter Name"), isA(Calendar.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTime(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTime(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCCallableStatementImpl.getTime(String)"})
  public void testGetTimeWithParameterName_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getTime(Mockito.<String>any())).thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Time actualTime = jdbcCallableStatementImpl.getTime("Parameter Name");

    // Assert
    verify(callableStatement).getTime("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTime);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTime(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTime(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCCallableStatementImpl.getTime(String)"})
  public void testGetTimeWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getTime(Mockito.<String>any())).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getTime("Parameter Name"));
    verify(callableStatement).getTime("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTimestamp(int, Calendar)} with {@code parameterIndex},
   * {@code cal}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTimestamp(int, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCCallableStatementImpl.getTimestamp(int, Calendar)"})
  public void testGetTimestampWithParameterIndexCal_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getTimestamp(anyInt(), Mockito.<Calendar>any())).thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Timestamp actualTimestamp =
        jdbcCallableStatementImpl.getTimestamp(1, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getTimestamp(eq(1), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTimestamp);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTimestamp(int, Calendar)} with {@code parameterIndex},
   * {@code cal}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTimestamp(int, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCCallableStatementImpl.getTimestamp(int, Calendar)"})
  public void testGetTimestampWithParameterIndexCal_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getTimestamp(anyInt(), Mockito.<Calendar>any()))
        .thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.getTimestamp(1, new GregorianCalendar(1, 1, 1)));
    verify(callableStatement).getTimestamp(eq(1), isA(Calendar.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTimestamp(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTimestamp(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCCallableStatementImpl.getTimestamp(int)"})
  public void testGetTimestampWithParameterIndex_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getTimestamp(anyInt())).thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Timestamp actualTimestamp = jdbcCallableStatementImpl.getTimestamp(1);

    // Assert
    verify(callableStatement).getTimestamp(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTimestamp);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTimestamp(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTimestamp(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCCallableStatementImpl.getTimestamp(int)"})
  public void testGetTimestampWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getTimestamp(anyInt())).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getTimestamp(1));
    verify(callableStatement).getTimestamp(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTimestamp(String, Calendar)} with {@code
   * parameterName}, {@code cal}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTimestamp(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCCallableStatementImpl.getTimestamp(String, Calendar)"})
  public void testGetTimestampWithParameterNameCal_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getTimestamp(Mockito.<String>any(), Mockito.<Calendar>any()))
        .thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Timestamp actualTimestamp =
        jdbcCallableStatementImpl.getTimestamp("Parameter Name", new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getTimestamp(eq("Parameter Name"), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTimestamp);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTimestamp(String, Calendar)} with {@code
   * parameterName}, {@code cal}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTimestamp(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCCallableStatementImpl.getTimestamp(String, Calendar)"})
  public void testGetTimestampWithParameterNameCal_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getTimestamp(Mockito.<String>any(), Mockito.<Calendar>any()))
        .thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.getTimestamp(
                "Parameter Name", new GregorianCalendar(1, 1, 1)));
    verify(callableStatement).getTimestamp(eq("Parameter Name"), isA(Calendar.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTimestamp(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTimestamp(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCCallableStatementImpl.getTimestamp(String)"})
  public void testGetTimestampWithParameterName_thenReturnNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getTimestamp(Mockito.<String>any())).thenReturn(null);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Timestamp actualTimestamp = jdbcCallableStatementImpl.getTimestamp("Parameter Name");

    // Assert
    verify(callableStatement).getTimestamp("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTimestamp);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getTimestamp(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getTimestamp(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCCallableStatementImpl.getTimestamp(String)"})
  public void testGetTimestampWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getTimestamp(Mockito.<String>any())).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.getTimestamp("Parameter Name"));
    verify(callableStatement).getTimestamp("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getURL(int)} with {@code parameterIndex}.
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getURL(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URL JDBCCallableStatementImpl.getURL(int)"})
  public void testGetURLWithParameterIndex() throws MalformedURLException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getURL(anyInt()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    URL actualURL = jdbcCallableStatementImpl.getURL(1);

    // Assert
    verify(callableStatement).getURL(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    String expectedToStringResult =
        String.join(
            "",
            "file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
                .toString()
                .concat(File.separator));
    assertEquals(expectedToStringResult, actualURL.toString());
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getURL(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getURL(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URL JDBCCallableStatementImpl.getURL(int)"})
  public void testGetURLWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getURL(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getURL(1));
    verify(callableStatement).getURL(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getURL(String)} with {@code parameterName}.
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URL JDBCCallableStatementImpl.getURL(String)"})
  public void testGetURLWithParameterName() throws MalformedURLException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getURL(Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    URL actualURL = jdbcCallableStatementImpl.getURL("https://example.org/example");

    // Assert
    verify(callableStatement).getURL("https://example.org/example");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    String expectedToStringResult =
        String.join(
            "",
            "file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
                .toString()
                .concat(File.separator));
    assertEquals(expectedToStringResult, actualURL.toString());
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getURL(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URL JDBCCallableStatementImpl.getURL(String)"})
  public void testGetURLWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getURL(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.getURL("https://example.org/example"));
    verify(callableStatement).getURL("https://example.org/example");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setURL(String, URL)} with {@code parameterName}, {@code
   * val}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setURL(String, URL)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setURL(String, URL)"})
  public void testSetURLWithParameterNameVal_thenCallsGetParameterCount()
      throws MalformedURLException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setURL(Mockito.<String>any(), Mockito.<URL>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setURL(
        "https://example.org/example",
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());

    // Assert
    verify(callableStatement).setURL(eq("https://example.org/example"), isA(URL.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setURL(String, URL)} with {@code parameterName}, {@code
   * val}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setURL(String, URL)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setURL(String, URL)"})
  public void testSetURLWithParameterNameVal_thenThrowSQLException()
      throws MalformedURLException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setURL(Mockito.<String>any(), Mockito.<URL>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setURL(
                "https://example.org/example",
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL()));
    verify(callableStatement).setURL(eq("https://example.org/example"), isA(URL.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setRowId(String, RowId)} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setRowId(String, RowId)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setRowId(String, RowId)"})
  public void testSetRowIdWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setRowId(Mockito.<String>any(), Mockito.<RowId>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setRowId("Parameter Name", null);

    // Assert
    verify(callableStatement).setRowId(eq("Parameter Name"), (RowId) isNull());
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setRowId(String, RowId)} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setRowId(String, RowId)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setRowId(String, RowId)"})
  public void testSetRowIdWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setRowId(Mockito.<String>any(), Mockito.<RowId>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.setRowId("Parameter Name", null));
    verify(callableStatement).setRowId(eq("Parameter Name"), (RowId) isNull());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNull(String, int, String)} with {@code parameterName},
   * {@code sqlType}, {@code typeName}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNull(String, int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNull(String, int, String)"})
  public void testSetNullWithParameterNameSqlTypeTypeName_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setNull(Mockito.<String>any(), anyInt(), Mockito.<String>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setNull("Parameter Name", 1, "Type Name");

    // Assert
    verify(callableStatement).setNull("Parameter Name", 1, "Type Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNull(String, int, String)} with {@code parameterName},
   * {@code sqlType}, {@code typeName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNull(String, int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNull(String, int, String)"})
  public void testSetNullWithParameterNameSqlTypeTypeName_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setNull(Mockito.<String>any(), anyInt(), Mockito.<String>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.setNull("Parameter Name", 1, "Type Name"));
    verify(callableStatement).setNull("Parameter Name", 1, "Type Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNull(String, int)} with {@code parameterName}, {@code
   * sqlType}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNull(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNull(String, int)"})
  public void testSetNullWithParameterNameSqlType_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setNull(Mockito.<String>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setNull("Parameter Name", 1);

    // Assert
    verify(callableStatement).setNull("Parameter Name", 1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNull(String, int)} with {@code parameterName}, {@code
   * sqlType}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNull(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNull(String, int)"})
  public void testSetNullWithParameterNameSqlType_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException()).when(callableStatement).setNull(Mockito.<String>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.setNull("Parameter Name", 1));
    verify(callableStatement).setNull("Parameter Name", 1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNString(String, String)} with {@code parameterName},
   * {@code value}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNString(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNString(String, String)"})
  public void testSetNStringWithParameterNameValue_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setNString(Mockito.<String>any(), Mockito.<String>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setNString("Parameter Name", "42");

    // Assert
    verify(callableStatement).setNString("Parameter Name", "42");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNString(String, String)} with {@code parameterName},
   * {@code value}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNString(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNString(String, String)"})
  public void testSetNStringWithParameterNameValue_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setNString(Mockito.<String>any(), Mockito.<String>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.setNString("Parameter Name", "42"));
    verify(callableStatement).setNString("Parameter Name", "42");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNCharacterStream(String, Reader, long)} with {@code
   * parameterName}, {@code value}, {@code length}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNCharacterStream(String, Reader,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNCharacterStream(String, Reader, long)"})
  public void testSetNCharacterStreamWithParameterNameValueLength_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setNCharacterStream(Mockito.<String>any(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setNCharacterStream("Parameter Name", new StringReader("foo"), 3L);

    // Assert
    verify(callableStatement).setNCharacterStream(eq("Parameter Name"), isA(Reader.class), eq(3L));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNCharacterStream(String, Reader, long)} with {@code
   * parameterName}, {@code value}, {@code length}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNCharacterStream(String, Reader,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNCharacterStream(String, Reader, long)"})
  public void testSetNCharacterStreamWithParameterNameValueLength_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setNCharacterStream(Mockito.<String>any(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setNCharacterStream(
                "Parameter Name", new StringReader("foo"), 3L));
    verify(callableStatement).setNCharacterStream(eq("Parameter Name"), isA(Reader.class), eq(3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNCharacterStream(String, Reader)} with {@code
   * parameterName}, {@code value}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNCharacterStream(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNCharacterStream(String, Reader)"})
  public void testSetNCharacterStreamWithParameterNameValue_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setNCharacterStream(Mockito.<String>any(), Mockito.<Reader>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setNCharacterStream("Parameter Name", new StringReader("foo"));

    // Assert
    verify(callableStatement).setNCharacterStream(eq("Parameter Name"), isA(Reader.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNCharacterStream(String, Reader)} with {@code
   * parameterName}, {@code value}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNCharacterStream(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNCharacterStream(String, Reader)"})
  public void testSetNCharacterStreamWithParameterNameValue_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setNCharacterStream(Mockito.<String>any(), Mockito.<Reader>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setNCharacterStream(
                "Parameter Name", new StringReader("foo")));
    verify(callableStatement).setNCharacterStream(eq("Parameter Name"), isA(Reader.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNClob(String, NClob)} with {@code String}, {@code
   * NClob}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNClob(String, NClob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNClob(String, NClob)"})
  public void testSetNClobWithStringNClob_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setNClob(Mockito.<String>any(), Mockito.<NClob>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setNClob("Parameter Name", mock(NClob.class));

    // Assert
    verify(callableStatement).setNClob(eq("Parameter Name"), isA(NClob.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNClob(String, NClob)} with {@code String}, {@code
   * NClob}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNClob(String, NClob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNClob(String, NClob)"})
  public void testSetNClobWithStringNClob_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setNClob(Mockito.<String>any(), Mockito.<NClob>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.setNClob("Parameter Name", mock(NClob.class)));
    verify(callableStatement).setNClob(eq("Parameter Name"), isA(NClob.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNClob(String, Reader, long)} with {@code String},
   * {@code Reader}, {@code long}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNClob(String, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNClob(String, Reader, long)"})
  public void testSetNClobWithStringReaderLong_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setNClob(Mockito.<String>any(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setNClob("Parameter Name", new StringReader("foo"), 3L);

    // Assert
    verify(callableStatement).setNClob(eq("Parameter Name"), isA(Reader.class), eq(3L));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNClob(String, Reader, long)} with {@code String},
   * {@code Reader}, {@code long}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNClob(String, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNClob(String, Reader, long)"})
  public void testSetNClobWithStringReaderLong_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setNClob(Mockito.<String>any(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.setNClob("Parameter Name", new StringReader("foo"), 3L));
    verify(callableStatement).setNClob(eq("Parameter Name"), isA(Reader.class), eq(3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNClob(String, Reader)} with {@code String}, {@code
   * Reader}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNClob(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNClob(String, Reader)"})
  public void testSetNClobWithStringReader_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setNClob(Mockito.<String>any(), Mockito.<Reader>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setNClob("Parameter Name", new StringReader("foo"));

    // Assert
    verify(callableStatement).setNClob(eq("Parameter Name"), isA(Reader.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setNClob(String, Reader)} with {@code String}, {@code
   * Reader}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setNClob(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setNClob(String, Reader)"})
  public void testSetNClobWithStringReader_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setNClob(Mockito.<String>any(), Mockito.<Reader>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.setNClob("Parameter Name", new StringReader("foo")));
    verify(callableStatement).setNClob(eq("Parameter Name"), isA(Reader.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setClob(String, Clob)} with {@code String}, {@code Clob}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setClob(String, Clob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setClob(String, Clob)"})
  public void testSetClobWithStringClob_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setClob(Mockito.<String>any(), Mockito.<Clob>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setClob("Parameter Name", new SerialClob("AZAZ".toCharArray()));

    // Assert
    verify(callableStatement).setClob(eq("Parameter Name"), isA(Clob.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setClob(String, Clob)} with {@code String}, {@code Clob}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setClob(String, Clob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setClob(String, Clob)"})
  public void testSetClobWithStringClob_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setClob(Mockito.<String>any(), Mockito.<Clob>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setClob(
                "Parameter Name", new SerialClob("AZAZ".toCharArray())));
    verify(callableStatement).setClob(eq("Parameter Name"), isA(Clob.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setClob(String, Reader, long)} with {@code String},
   * {@code Reader}, {@code long}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setClob(String, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setClob(String, Reader, long)"})
  public void testSetClobWithStringReaderLong_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setClob(Mockito.<String>any(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setClob("Parameter Name", new StringReader("foo"), 3L);

    // Assert
    verify(callableStatement).setClob(eq("Parameter Name"), isA(Reader.class), eq(3L));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setClob(String, Reader, long)} with {@code String},
   * {@code Reader}, {@code long}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setClob(String, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setClob(String, Reader, long)"})
  public void testSetClobWithStringReaderLong_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setClob(Mockito.<String>any(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.setClob("Parameter Name", new StringReader("foo"), 3L));
    verify(callableStatement).setClob(eq("Parameter Name"), isA(Reader.class), eq(3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setClob(String, Reader)} with {@code String}, {@code
   * Reader}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setClob(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setClob(String, Reader)"})
  public void testSetClobWithStringReader_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setClob(Mockito.<String>any(), Mockito.<Reader>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setClob("Parameter Name", new StringReader("foo"));

    // Assert
    verify(callableStatement).setClob(eq("Parameter Name"), isA(Reader.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setClob(String, Reader)} with {@code String}, {@code
   * Reader}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setClob(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setClob(String, Reader)"})
  public void testSetClobWithStringReader_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setClob(Mockito.<String>any(), Mockito.<Reader>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.setClob("Parameter Name", new StringReader("foo")));
    verify(callableStatement).setClob(eq("Parameter Name"), isA(Reader.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBoolean(String, boolean)} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBoolean(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBoolean(String, boolean)"})
  public void testSetBooleanWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setBoolean(Mockito.<String>any(), anyBoolean());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setBoolean("Parameter Name", true);

    // Assert
    verify(callableStatement).setBoolean("Parameter Name", true);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBoolean(String, boolean)} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBoolean(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBoolean(String, boolean)"})
  public void testSetBooleanWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setBoolean(Mockito.<String>any(), anyBoolean());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.setBoolean("Parameter Name", true));
    verify(callableStatement).setBoolean("Parameter Name", true);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setByte(String, byte)} with {@code parameterName}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setByte(String, byte)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setByte(String, byte)"})
  public void testSetByteWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setByte(Mockito.<String>any(), anyByte());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setByte("Parameter Name", (byte) 'A');

    // Assert
    verify(callableStatement).setByte("Parameter Name", (byte) 65);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setByte(String, byte)} with {@code parameterName}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setByte(String, byte)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setByte(String, byte)"})
  public void testSetByteWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException()).when(callableStatement).setByte(Mockito.<String>any(), anyByte());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.setByte("Parameter Name", (byte) 'A'));
    verify(callableStatement).setByte("Parameter Name", (byte) 65);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setShort(String, short)} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setShort(String, short)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setShort(String, short)"})
  public void testSetShortWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setShort(Mockito.<String>any(), anyShort());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setShort("Parameter Name", (short) 1);

    // Assert
    verify(callableStatement).setShort("Parameter Name", (short) 1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setShort(String, short)} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setShort(String, short)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setShort(String, short)"})
  public void testSetShortWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException()).when(callableStatement).setShort(Mockito.<String>any(), anyShort());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.setShort("Parameter Name", (short) 1));
    verify(callableStatement).setShort("Parameter Name", (short) 1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setInt(String, int)} with {@code parameterName}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setInt(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setInt(String, int)"})
  public void testSetIntWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setInt(Mockito.<String>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setInt("Parameter Name", 2);

    // Assert
    verify(callableStatement).setInt("Parameter Name", 2);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setInt(String, int)} with {@code parameterName}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setInt(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setInt(String, int)"})
  public void testSetIntWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException()).when(callableStatement).setInt(Mockito.<String>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.setInt("Parameter Name", 2));
    verify(callableStatement).setInt("Parameter Name", 2);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setLong(String, long)} with {@code parameterName}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setLong(String, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setLong(String, long)"})
  public void testSetLongWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setLong(Mockito.<String>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setLong("Parameter Name", 1L);

    // Assert
    verify(callableStatement).setLong("Parameter Name", 1L);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setLong(String, long)} with {@code parameterName}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setLong(String, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setLong(String, long)"})
  public void testSetLongWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException()).when(callableStatement).setLong(Mockito.<String>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.setLong("Parameter Name", 1L));
    verify(callableStatement).setLong("Parameter Name", 1L);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setFloat(String, float)} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setFloat(String, float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setFloat(String, float)"})
  public void testSetFloatWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setFloat(Mockito.<String>any(), anyFloat());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setFloat("Parameter Name", 10.0f);

    // Assert
    verify(callableStatement).setFloat("Parameter Name", 10.0f);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setFloat(String, float)} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setFloat(String, float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setFloat(String, float)"})
  public void testSetFloatWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException()).when(callableStatement).setFloat(Mockito.<String>any(), anyFloat());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.setFloat("Parameter Name", 10.0f));
    verify(callableStatement).setFloat("Parameter Name", 10.0f);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setDouble(String, double)} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setDouble(String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setDouble(String, double)"})
  public void testSetDoubleWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setDouble(Mockito.<String>any(), anyDouble());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setDouble("Parameter Name", 2.0d);

    // Assert
    verify(callableStatement).setDouble("Parameter Name", 2.0d);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setDouble(String, double)} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setDouble(String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setDouble(String, double)"})
  public void testSetDoubleWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setDouble(Mockito.<String>any(), anyDouble());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.setDouble("Parameter Name", 2.0d));
    verify(callableStatement).setDouble("Parameter Name", 2.0d);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBlob(String, Blob)} with {@code String}, {@code Blob}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBlob(String, Blob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBlob(String, Blob)"})
  public void testSetBlobWithStringBlob_thenCallsGetParameterCount()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setBlob(Mockito.<String>any(), Mockito.<Blob>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setBlob(
        "Parameter Name", new SerialBlob("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(callableStatement).setBlob(eq("Parameter Name"), isA(Blob.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBlob(String, Blob)} with {@code String}, {@code Blob}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBlob(String, Blob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBlob(String, Blob)"})
  public void testSetBlobWithStringBlob_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setBlob(Mockito.<String>any(), Mockito.<Blob>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setBlob(
                "Parameter Name", new SerialBlob("AXAXAXAX".getBytes("UTF-8"))));
    verify(callableStatement).setBlob(eq("Parameter Name"), isA(Blob.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBlob(String, InputStream, long)} with {@code String},
   * {@code InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBlob(String, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBlob(String, InputStream, long)"})
  public void testSetBlobWithStringInputStreamLong_thenCallsGetParameterCount()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setBlob(Mockito.<String>any(), Mockito.<InputStream>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setBlob(
        "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Assert
    verify(callableStatement).setBlob(eq("Parameter Name"), isA(InputStream.class), eq(3L));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBlob(String, InputStream, long)} with {@code String},
   * {@code InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBlob(String, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBlob(String, InputStream, long)"})
  public void testSetBlobWithStringInputStreamLong_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setBlob(Mockito.<String>any(), Mockito.<InputStream>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setBlob(
                "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L));
    verify(callableStatement).setBlob(eq("Parameter Name"), isA(InputStream.class), eq(3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBlob(String, InputStream)} with {@code String}, {@code
   * InputStream}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBlob(String, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBlob(String, InputStream)"})
  public void testSetBlobWithStringInputStream_thenCallsGetParameterCount()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setBlob(Mockito.<String>any(), Mockito.<InputStream>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setBlob(
        "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(callableStatement).setBlob(eq("Parameter Name"), isA(InputStream.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBlob(String, InputStream)} with {@code String}, {@code
   * InputStream}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBlob(String, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBlob(String, InputStream)"})
  public void testSetBlobWithStringInputStream_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setBlob(Mockito.<String>any(), Mockito.<InputStream>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setBlob(
                "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(callableStatement).setBlob(eq("Parameter Name"), isA(InputStream.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBigDecimal(String, BigDecimal)} with {@code
   * parameterName}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBigDecimal(String, BigDecimal)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBigDecimal(String, BigDecimal)"})
  public void testSetBigDecimalWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setBigDecimal(Mockito.<String>any(), Mockito.<BigDecimal>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setBigDecimal("2.3", new BigDecimal("2.3"));

    // Assert
    verify(callableStatement).setBigDecimal(eq("2.3"), isA(BigDecimal.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBigDecimal(String, BigDecimal)} with {@code
   * parameterName}, {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBigDecimal(String, BigDecimal)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBigDecimal(String, BigDecimal)"})
  public void testSetBigDecimalWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setBigDecimal(Mockito.<String>any(), Mockito.<BigDecimal>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.setBigDecimal("2.3", new BigDecimal("2.3")));
    verify(callableStatement).setBigDecimal(eq("2.3"), isA(BigDecimal.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setString(String, String)} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setString(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setString(String, String)"})
  public void testSetStringWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setString(Mockito.<String>any(), Mockito.<String>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setString("Parameter Name", "foo");

    // Assert
    verify(callableStatement).setString("Parameter Name", "foo");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setString(String, String)} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setString(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setString(String, String)"})
  public void testSetStringWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setString(Mockito.<String>any(), Mockito.<String>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.setString("Parameter Name", "foo"));
    verify(callableStatement).setString("Parameter Name", "foo");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBytes(String, byte[])} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBytes(String, byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBytes(String, byte[])"})
  public void testSetBytesWithParameterNameX_thenCallsGetParameterCount()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setBytes(Mockito.<String>any(), Mockito.<byte[]>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setBytes("Parameter Name", "AXAXAXAX".getBytes("UTF-8"));

    // Assert
    verify(callableStatement).setBytes(eq("Parameter Name"), isA(byte[].class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBytes(String, byte[])} with {@code parameterName},
   * {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBytes(String, byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBytes(String, byte[])"})
  public void testSetBytesWithParameterNameX_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setBytes(Mockito.<String>any(), Mockito.<byte[]>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.setBytes("Parameter Name", "AXAXAXAX".getBytes("UTF-8")));
    verify(callableStatement).setBytes(eq("Parameter Name"), isA(byte[].class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setDate(String, Date, Calendar)} with {@code
   * parameterName}, {@code x}, {@code cal}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setDate(String, Date, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setDate(String, Date, Calendar)"})
  public void testSetDateWithParameterNameXCal_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setDate(Mockito.<String>any(), Mockito.<Date>any(), Mockito.<Calendar>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    Date x = new Date(1L);

    // Act
    jdbcCallableStatementImpl.setDate("Parameter Name", x, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).setDate(eq("Parameter Name"), isA(Date.class), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setDate(String, Date, Calendar)} with {@code
   * parameterName}, {@code x}, {@code cal}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setDate(String, Date, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setDate(String, Date, Calendar)"})
  public void testSetDateWithParameterNameXCal_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setDate(Mockito.<String>any(), Mockito.<Date>any(), Mockito.<Calendar>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    Date x = new Date(1L);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setDate("Parameter Name", x, new GregorianCalendar(1, 1, 1)));
    verify(callableStatement).setDate(eq("Parameter Name"), isA(Date.class), isA(Calendar.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setDate(String, Date)} with {@code parameterName}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setDate(String, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setDate(String, Date)"})
  public void testSetDateWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setDate(Mockito.<String>any(), Mockito.<Date>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setDate("Parameter Name", new Date(1L));

    // Assert
    verify(callableStatement).setDate(eq("Parameter Name"), isA(Date.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setDate(String, Date)} with {@code parameterName}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setDate(String, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setDate(String, Date)"})
  public void testSetDateWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setDate(Mockito.<String>any(), Mockito.<Date>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.setDate("Parameter Name", new Date(1L)));
    verify(callableStatement).setDate(eq("Parameter Name"), isA(Date.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setTime(String, Time, Calendar)} with {@code
   * parameterName}, {@code x}, {@code cal}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setTime(String, Time, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setTime(String, Time, Calendar)"})
  public void testSetTimeWithParameterNameXCal_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setTime(Mockito.<String>any(), Mockito.<Time>any(), Mockito.<Calendar>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setTime("Parameter Name", null, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).setTime(eq("Parameter Name"), (Time) isNull(), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setTime(String, Time, Calendar)} with {@code
   * parameterName}, {@code x}, {@code cal}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setTime(String, Time, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setTime(String, Time, Calendar)"})
  public void testSetTimeWithParameterNameXCal_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setTime(Mockito.<String>any(), Mockito.<Time>any(), Mockito.<Calendar>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setTime(
                "Parameter Name", null, new GregorianCalendar(1, 1, 1)));
    verify(callableStatement).setTime(eq("Parameter Name"), (Time) isNull(), isA(Calendar.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setTime(String, Time)} with {@code parameterName}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setTime(String, Time)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setTime(String, Time)"})
  public void testSetTimeWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setTime(Mockito.<String>any(), Mockito.<Time>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setTime("Parameter Name", null);

    // Assert
    verify(callableStatement).setTime(eq("Parameter Name"), (Time) isNull());
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setTime(String, Time)} with {@code parameterName}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setTime(String, Time)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setTime(String, Time)"})
  public void testSetTimeWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setTime(Mockito.<String>any(), Mockito.<Time>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.setTime("Parameter Name", null));
    verify(callableStatement).setTime(eq("Parameter Name"), (Time) isNull());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setTimestamp(String, Timestamp, Calendar)} with {@code
   * parameterName}, {@code x}, {@code cal}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setTimestamp(String, Timestamp,
   * Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setTimestamp(String, Timestamp, Calendar)"})
  public void testSetTimestampWithParameterNameXCal_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setTimestamp(Mockito.<String>any(), Mockito.<Timestamp>any(), Mockito.<Calendar>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setTimestamp("Parameter Name", null, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement)
        .setTimestamp(eq("Parameter Name"), (Timestamp) isNull(), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setTimestamp(String, Timestamp, Calendar)} with {@code
   * parameterName}, {@code x}, {@code cal}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setTimestamp(String, Timestamp,
   * Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setTimestamp(String, Timestamp, Calendar)"})
  public void testSetTimestampWithParameterNameXCal_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setTimestamp(Mockito.<String>any(), Mockito.<Timestamp>any(), Mockito.<Calendar>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setTimestamp(
                "Parameter Name", null, new GregorianCalendar(1, 1, 1)));
    verify(callableStatement)
        .setTimestamp(eq("Parameter Name"), (Timestamp) isNull(), isA(Calendar.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setTimestamp(String, Timestamp)} with {@code
   * parameterName}, {@code x}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setTimestamp(String, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setTimestamp(String, Timestamp)"})
  public void testSetTimestampWithParameterNameX_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setTimestamp(Mockito.<String>any(), Mockito.<Timestamp>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setTimestamp("Parameter Name", null);

    // Assert
    verify(callableStatement).setTimestamp(eq("Parameter Name"), (Timestamp) isNull());
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setTimestamp(String, Timestamp)} with {@code
   * parameterName}, {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setTimestamp(String, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setTimestamp(String, Timestamp)"})
  public void testSetTimestampWithParameterNameX_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setTimestamp(Mockito.<String>any(), Mockito.<Timestamp>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.setTimestamp("Parameter Name", null));
    verify(callableStatement).setTimestamp(eq("Parameter Name"), (Timestamp) isNull());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setAsciiStream(String, InputStream, int)} with {@code
   * String}, {@code InputStream}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setAsciiStream(String, InputStream,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setAsciiStream(String, InputStream, int)"})
  public void testSetAsciiStreamWithStringInputStreamInt_thenCallsGetParameterCount()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setAsciiStream(Mockito.<String>any(), Mockito.<InputStream>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setAsciiStream(
        "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3);

    // Assert
    verify(callableStatement).setAsciiStream(eq("Parameter Name"), isA(InputStream.class), eq(3));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setAsciiStream(String, InputStream, int)} with {@code
   * String}, {@code InputStream}, {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setAsciiStream(String, InputStream,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setAsciiStream(String, InputStream, int)"})
  public void testSetAsciiStreamWithStringInputStreamInt_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setAsciiStream(Mockito.<String>any(), Mockito.<InputStream>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setAsciiStream(
                "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3));
    verify(callableStatement).setAsciiStream(eq("Parameter Name"), isA(InputStream.class), eq(3));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setAsciiStream(String, InputStream, long)} with {@code
   * String}, {@code InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setAsciiStream(String, InputStream,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setAsciiStream(String, InputStream, long)"})
  public void testSetAsciiStreamWithStringInputStreamLong_thenCallsGetParameterCount()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setAsciiStream(Mockito.<String>any(), Mockito.<InputStream>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setAsciiStream(
        "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Assert
    verify(callableStatement).setAsciiStream(eq("Parameter Name"), isA(InputStream.class), eq(3L));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setAsciiStream(String, InputStream, long)} with {@code
   * String}, {@code InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setAsciiStream(String, InputStream,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setAsciiStream(String, InputStream, long)"})
  public void testSetAsciiStreamWithStringInputStreamLong_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setAsciiStream(Mockito.<String>any(), Mockito.<InputStream>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setAsciiStream(
                "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L));
    verify(callableStatement).setAsciiStream(eq("Parameter Name"), isA(InputStream.class), eq(3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setAsciiStream(String, InputStream)} with {@code String},
   * {@code InputStream}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setAsciiStream(String, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setAsciiStream(String, InputStream)"})
  public void testSetAsciiStreamWithStringInputStream_thenCallsGetParameterCount()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setAsciiStream(Mockito.<String>any(), Mockito.<InputStream>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setAsciiStream(
        "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(callableStatement).setAsciiStream(eq("Parameter Name"), isA(InputStream.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setAsciiStream(String, InputStream)} with {@code String},
   * {@code InputStream}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setAsciiStream(String, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setAsciiStream(String, InputStream)"})
  public void testSetAsciiStreamWithStringInputStream_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setAsciiStream(Mockito.<String>any(), Mockito.<InputStream>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setAsciiStream(
                "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(callableStatement).setAsciiStream(eq("Parameter Name"), isA(InputStream.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBinaryStream(String, InputStream, int)} with {@code
   * String}, {@code InputStream}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBinaryStream(String, InputStream,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBinaryStream(String, InputStream, int)"})
  public void testSetBinaryStreamWithStringInputStreamInt_thenCallsGetParameterCount()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setBinaryStream(Mockito.<String>any(), Mockito.<InputStream>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setBinaryStream(
        "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3);

    // Assert
    verify(callableStatement).setBinaryStream(eq("Parameter Name"), isA(InputStream.class), eq(3));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBinaryStream(String, InputStream, int)} with {@code
   * String}, {@code InputStream}, {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBinaryStream(String, InputStream,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBinaryStream(String, InputStream, int)"})
  public void testSetBinaryStreamWithStringInputStreamInt_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setBinaryStream(Mockito.<String>any(), Mockito.<InputStream>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setBinaryStream(
                "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3));
    verify(callableStatement).setBinaryStream(eq("Parameter Name"), isA(InputStream.class), eq(3));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBinaryStream(String, InputStream, long)} with {@code
   * String}, {@code InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBinaryStream(String, InputStream,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBinaryStream(String, InputStream, long)"})
  public void testSetBinaryStreamWithStringInputStreamLong_thenCallsGetParameterCount()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setBinaryStream(Mockito.<String>any(), Mockito.<InputStream>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setBinaryStream(
        "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L);

    // Assert
    verify(callableStatement).setBinaryStream(eq("Parameter Name"), isA(InputStream.class), eq(3L));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBinaryStream(String, InputStream, long)} with {@code
   * String}, {@code InputStream}, {@code long}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBinaryStream(String, InputStream,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBinaryStream(String, InputStream, long)"})
  public void testSetBinaryStreamWithStringInputStreamLong_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setBinaryStream(Mockito.<String>any(), Mockito.<InputStream>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setBinaryStream(
                "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L));
    verify(callableStatement).setBinaryStream(eq("Parameter Name"), isA(InputStream.class), eq(3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBinaryStream(String, InputStream)} with {@code
   * String}, {@code InputStream}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBinaryStream(String, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBinaryStream(String, InputStream)"})
  public void testSetBinaryStreamWithStringInputStream_thenCallsGetParameterCount()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setBinaryStream(Mockito.<String>any(), Mockito.<InputStream>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setBinaryStream(
        "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    // Assert
    verify(callableStatement).setBinaryStream(eq("Parameter Name"), isA(InputStream.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setBinaryStream(String, InputStream)} with {@code
   * String}, {@code InputStream}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setBinaryStream(String, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setBinaryStream(String, InputStream)"})
  public void testSetBinaryStreamWithStringInputStream_thenThrowSQLException()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setBinaryStream(Mockito.<String>any(), Mockito.<InputStream>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setBinaryStream(
                "Parameter Name", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(callableStatement).setBinaryStream(eq("Parameter Name"), isA(InputStream.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setObject(String, Object, int, int)} with {@code String},
   * {@code Object}, {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setObject(String, Object, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setObject(String, Object, int, int)"})
  public void testSetObjectWithStringObjectIntInt_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setObject(Mockito.<String>any(), Mockito.<Object>any(), anyInt(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setObject("Parameter Name", "42", 1, 1);

    // Assert
    verify(callableStatement).setObject(eq("Parameter Name"), isA(Object.class), eq(1), eq(1));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setObject(String, Object, int, int)} with {@code String},
   * {@code Object}, {@code int}, {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setObject(String, Object, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setObject(String, Object, int, int)"})
  public void testSetObjectWithStringObjectIntInt_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setObject(Mockito.<String>any(), Mockito.<Object>any(), anyInt(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jdbcCallableStatementImpl.setObject("Parameter Name", "42", 1, 1));
    verify(callableStatement).setObject(eq("Parameter Name"), isA(Object.class), eq(1), eq(1));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setObject(String, Object, int)} with {@code String},
   * {@code Object}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setObject(String, Object, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setObject(String, Object, int)"})
  public void testSetObjectWithStringObjectInt_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setObject(Mockito.<String>any(), Mockito.<Object>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setObject("Parameter Name", "42", 1);

    // Assert
    verify(callableStatement).setObject(eq("Parameter Name"), isA(Object.class), eq(1));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setObject(String, Object, int)} with {@code String},
   * {@code Object}, {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setObject(String, Object, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setObject(String, Object, int)"})
  public void testSetObjectWithStringObjectInt_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setObject(Mockito.<String>any(), Mockito.<Object>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.setObject("Parameter Name", "42", 1));
    verify(callableStatement).setObject(eq("Parameter Name"), isA(Object.class), eq(1));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setObject(String, Object)} with {@code String}, {@code
   * Object}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setObject(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setObject(String, Object)"})
  public void testSetObjectWithStringObject_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setObject(Mockito.<String>any(), Mockito.<Object>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setObject("Parameter Name", "42");

    // Assert
    verify(callableStatement).setObject(eq("Parameter Name"), isA(Object.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setObject(String, Object)} with {@code String}, {@code
   * Object}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setObject(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setObject(String, Object)"})
  public void testSetObjectWithStringObject_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setObject(Mockito.<String>any(), Mockito.<Object>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.setObject("Parameter Name", "42"));
    verify(callableStatement).setObject(eq("Parameter Name"), isA(Object.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setCharacterStream(String, Reader, int)} with {@code
   * String}, {@code Reader}, {@code int}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setCharacterStream(String, Reader, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setCharacterStream(String, Reader, int)"})
  public void testSetCharacterStreamWithStringReaderInt_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setCharacterStream(Mockito.<String>any(), Mockito.<Reader>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setCharacterStream("Parameter Name", new StringReader("foo"), 3);

    // Assert
    verify(callableStatement).setCharacterStream(eq("Parameter Name"), isA(Reader.class), eq(3));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setCharacterStream(String, Reader, int)} with {@code
   * String}, {@code Reader}, {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setCharacterStream(String, Reader, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setCharacterStream(String, Reader, int)"})
  public void testSetCharacterStreamWithStringReaderInt_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setCharacterStream(Mockito.<String>any(), Mockito.<Reader>any(), anyInt());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setCharacterStream(
                "Parameter Name", new StringReader("foo"), 3));
    verify(callableStatement).setCharacterStream(eq("Parameter Name"), isA(Reader.class), eq(3));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setCharacterStream(String, Reader, long)} with {@code
   * String}, {@code Reader}, {@code long}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setCharacterStream(String, Reader,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setCharacterStream(String, Reader, long)"})
  public void testSetCharacterStreamWithStringReaderLong_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setCharacterStream(Mockito.<String>any(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setCharacterStream("Parameter Name", new StringReader("foo"), 3L);

    // Assert
    verify(callableStatement).setCharacterStream(eq("Parameter Name"), isA(Reader.class), eq(3L));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setCharacterStream(String, Reader, long)} with {@code
   * String}, {@code Reader}, {@code long}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setCharacterStream(String, Reader,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setCharacterStream(String, Reader, long)"})
  public void testSetCharacterStreamWithStringReaderLong_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setCharacterStream(Mockito.<String>any(), Mockito.<Reader>any(), anyLong());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setCharacterStream(
                "Parameter Name", new StringReader("foo"), 3L));
    verify(callableStatement).setCharacterStream(eq("Parameter Name"), isA(Reader.class), eq(3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setCharacterStream(String, Reader)} with {@code String},
   * {@code Reader}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setCharacterStream(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setCharacterStream(String, Reader)"})
  public void testSetCharacterStreamWithStringReader_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing()
        .when(callableStatement)
        .setCharacterStream(Mockito.<String>any(), Mockito.<Reader>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setCharacterStream("Parameter Name", new StringReader("foo"));

    // Assert
    verify(callableStatement).setCharacterStream(eq("Parameter Name"), isA(Reader.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setCharacterStream(String, Reader)} with {@code String},
   * {@code Reader}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setCharacterStream(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setCharacterStream(String, Reader)"})
  public void testSetCharacterStreamWithStringReader_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setCharacterStream(Mockito.<String>any(), Mockito.<Reader>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setCharacterStream(
                "Parameter Name", new StringReader("foo")));
    verify(callableStatement).setCharacterStream(eq("Parameter Name"), isA(Reader.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getObject(int, Class)} with {@code parameterIndex},
   * {@code type}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getObject(int, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCCallableStatementImpl.getObject(int, Class)"})
  public void testGetObjectWithParameterIndexType_thenReturnObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(anyInt(), eq(Object.class))).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    Class<Object> type = Object.class;

    // Act
    Object actualObject = jdbcCallableStatementImpl.getObject(1, type);

    // Assert
    verify(callableStatement).getObject(eq(1), isA(Class.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getObject(int, Class)} with {@code parameterIndex},
   * {@code type}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getObject(int, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCCallableStatementImpl.getObject(int, Class)"})
  public void testGetObjectWithParameterIndexType_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(anyInt(), eq(Object.class))).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    Class<Object> type = Object.class;

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getObject(1, type));
    verify(callableStatement).getObject(eq(1), isA(Class.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getObject(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getObject(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCCallableStatementImpl.getObject(int)"})
  public void testGetObjectWithParameterIndex_thenReturnObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(anyInt())).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Object actualObject = jdbcCallableStatementImpl.getObject(1);

    // Assert
    verify(callableStatement).getObject(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getObject(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getObject(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCCallableStatementImpl.getObject(int)"})
  public void testGetObjectWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getObject(1));
    verify(callableStatement).getObject(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getObject(String, Class)} with {@code parameterName},
   * {@code type}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getObject(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCCallableStatementImpl.getObject(String, Class)"})
  public void testGetObjectWithParameterNameType_thenReturnObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(Mockito.<String>any(), eq(Object.class))).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    Class<Object> type = Object.class;

    // Act
    Object actualObject = jdbcCallableStatementImpl.getObject("Parameter Name", type);

    // Assert
    verify(callableStatement).getObject(eq("Parameter Name"), isA(Class.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getObject(String, Class)} with {@code parameterName},
   * {@code type}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getObject(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCCallableStatementImpl.getObject(String, Class)"})
  public void testGetObjectWithParameterNameType_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(Mockito.<String>any(), eq(Object.class)))
        .thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    Class<Object> type = Object.class;

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.getObject("Parameter Name", type));
    verify(callableStatement).getObject(eq("Parameter Name"), isA(Class.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getObject(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCCallableStatementImpl.getObject(String)"})
  public void testGetObjectWithParameterName_thenReturnObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(Mockito.<String>any())).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Object actualObject = jdbcCallableStatementImpl.getObject("Parameter Name");

    // Assert
    verify(callableStatement).getObject("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getObject(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCCallableStatementImpl.getObject(String)"})
  public void testGetObjectWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getObject("Parameter Name"));
    verify(callableStatement).getObject("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getRef(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@link SerialRef#SerialRef(Ref)} with ref is {@link SerialRef}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getRef(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Ref JDBCCallableStatementImpl.getRef(int)"})
  public void testGetRefWithParameterIndex_thenReturnSerialRefWithRefIsSerialRef()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    SerialRef ref = mock(SerialRef.class);
    when(ref.getBaseTypeName()).thenReturn("Base Type Name");
    SerialRef serialRef = new SerialRef(ref);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getRef(anyInt())).thenReturn(serialRef);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Ref actualRef = jdbcCallableStatementImpl.getRef(1);

    // Assert
    verify(callableStatement).getRef(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(ref, atLeast(1)).getBaseTypeName();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialRef, actualRef);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getRef(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getRef(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Ref JDBCCallableStatementImpl.getRef(int)"})
  public void testGetRefWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getRef(anyInt())).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getRef(1));
    verify(callableStatement).getRef(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getRef(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@link SerialRef#SerialRef(Ref)} with ref is {@link SerialRef}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getRef(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Ref JDBCCallableStatementImpl.getRef(String)"})
  public void testGetRefWithParameterName_thenReturnSerialRefWithRefIsSerialRef()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    SerialRef ref = mock(SerialRef.class);
    when(ref.getBaseTypeName()).thenReturn("Base Type Name");
    SerialRef serialRef = new SerialRef(ref);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    when(callableStatement.getRef(Mockito.<String>any())).thenReturn(serialRef);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Ref actualRef = jdbcCallableStatementImpl.getRef("Parameter Name");

    // Assert
    verify(callableStatement).getRef("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(ref, atLeast(1)).getBaseTypeName();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialRef, actualRef);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getRef(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getRef(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Ref JDBCCallableStatementImpl.getRef(String)"})
  public void testGetRefWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getRef(Mockito.<String>any())).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getRef("Parameter Name"));
    verify(callableStatement).getRef("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getNClob(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getNClob(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NClob JDBCCallableStatementImpl.getNClob(int)"})
  public void testGetNClobWithParameterIndex_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNClob(anyInt())).thenReturn(mock(NClob.class));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.getNClob(1);

    // Assert
    verify(callableStatement).getNClob(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getNClob(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getNClob(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NClob JDBCCallableStatementImpl.getNClob(int)"})
  public void testGetNClobWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNClob(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getNClob(1));
    verify(callableStatement).getNClob(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getNClob(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getNClob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NClob JDBCCallableStatementImpl.getNClob(String)"})
  public void testGetNClobWithParameterName_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNClob(Mockito.<String>any())).thenReturn(mock(NClob.class));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.getNClob("Parameter Name");

    // Assert
    verify(callableStatement).getNClob("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getNClob(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getNClob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NClob JDBCCallableStatementImpl.getNClob(String)"})
  public void testGetNClobWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNClob(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getNClob("Parameter Name"));
    verify(callableStatement).getNClob("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setSQLXML(String, SQLXML)} with {@code parameterName},
   * {@code xmlObject}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setSQLXML(String, SQLXML)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setSQLXML(String, SQLXML)"})
  public void testSetSQLXMLWithParameterNameXmlObject_thenCallsGetParameterCount()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    doNothing().when(callableStatement).setSQLXML(Mockito.<String>any(), Mockito.<SQLXML>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    jdbcCallableStatementImpl.setSQLXML(
        "Parameter Name", new JDBCSQLXMLImpl(new JDBCContentBytes(null)));

    // Assert
    verify(callableStatement).setSQLXML(eq("Parameter Name"), isA(SQLXML.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#setSQLXML(String, SQLXML)} with {@code parameterName},
   * {@code xmlObject}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#setSQLXML(String, SQLXML)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCallableStatementImpl.setSQLXML(String, SQLXML)"})
  public void testSetSQLXMLWithParameterNameXmlObject_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    doThrow(new SQLException())
        .when(callableStatement)
        .setSQLXML(Mockito.<String>any(), Mockito.<SQLXML>any());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcCallableStatementImpl.setSQLXML(
                "Parameter Name", new JDBCSQLXMLImpl(new JDBCContentBytes(null))));
    verify(callableStatement).setSQLXML(eq("Parameter Name"), isA(SQLXML.class));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getSQLXML(int)} with {@code parameterIndex}.
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getSQLXML(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLXML JDBCCallableStatementImpl.getSQLXML(int)"})
  public void testGetSQLXMLWithParameterIndex() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    JDBCSQLXMLImpl jdbcsqlxmlImpl = new JDBCSQLXMLImpl(new JDBCContentBytes(null));
    when(callableStatement.getSQLXML(anyInt())).thenReturn(jdbcsqlxmlImpl);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    SQLXML actualSQLXML = jdbcCallableStatementImpl.getSQLXML(1);

    // Assert
    verify(callableStatement).getSQLXML(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(jdbcsqlxmlImpl, actualSQLXML);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getSQLXML(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getSQLXML(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLXML JDBCCallableStatementImpl.getSQLXML(int)"})
  public void testGetSQLXMLWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getSQLXML(anyInt())).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getSQLXML(1));
    verify(callableStatement).getSQLXML(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getSQLXML(String)} with {@code parameterName}.
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getSQLXML(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLXML JDBCCallableStatementImpl.getSQLXML(String)"})
  public void testGetSQLXMLWithParameterName() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);
    JDBCSQLXMLImpl jdbcsqlxmlImpl = new JDBCSQLXMLImpl(new JDBCContentBytes(null));
    when(callableStatement.getSQLXML(Mockito.<String>any())).thenReturn(jdbcsqlxmlImpl);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    SQLXML actualSQLXML = jdbcCallableStatementImpl.getSQLXML("Parameter Name");

    // Assert
    verify(callableStatement).getSQLXML("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(jdbcsqlxmlImpl, actualSQLXML);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getSQLXML(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getSQLXML(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLXML JDBCCallableStatementImpl.getSQLXML(String)"})
  public void testGetSQLXMLWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());
    when(callableStatement.getSQLXML(Mockito.<String>any())).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getSQLXML("Parameter Name"));
    verify(callableStatement).getSQLXML("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getNString(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code N String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getNString(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCallableStatementImpl.getNString(int)"})
  public void testGetNStringWithParameterIndex_thenReturnNString() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNString(anyInt())).thenReturn("N String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    String actualNString = jdbcCallableStatementImpl.getNString(1);

    // Assert
    verify(callableStatement).getNString(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("N String", actualNString);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getNString(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getNString(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCallableStatementImpl.getNString(int)"})
  public void testGetNStringWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNString(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getNString(1));
    verify(callableStatement).getNString(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getNString(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code N String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getNString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCallableStatementImpl.getNString(String)"})
  public void testGetNStringWithParameterName_thenReturnNString() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNString(Mockito.<String>any())).thenReturn("N String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    String actualNString = jdbcCallableStatementImpl.getNString("Parameter Name");

    // Assert
    verify(callableStatement).getNString("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("N String", actualNString);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getNString(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getNString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCallableStatementImpl.getNString(String)"})
  public void testGetNStringWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNString(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getNString("Parameter Name"));
    verify(callableStatement).getNString("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getNCharacterStream(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getNCharacterStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCCallableStatementImpl.getNCharacterStream(int)"})
  public void testGetNCharacterStreamWithParameterIndex_thenReturnReady()
      throws IOException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    StringReader stringReader = new StringReader("foo");
    when(callableStatement.getNCharacterStream(anyInt())).thenReturn(stringReader);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Reader actualNCharacterStream = jdbcCallableStatementImpl.getNCharacterStream(1);

    // Assert
    verify(callableStatement).getNCharacterStream(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualNCharacterStream.ready());
    assertSame(stringReader, actualNCharacterStream);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getNCharacterStream(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getNCharacterStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCCallableStatementImpl.getNCharacterStream(int)"})
  public void testGetNCharacterStreamWithParameterIndex_thenThrowSQLException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNCharacterStream(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getNCharacterStream(1));
    verify(callableStatement).getNCharacterStream(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getNCharacterStream(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getNCharacterStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCCallableStatementImpl.getNCharacterStream(String)"})
  public void testGetNCharacterStreamWithParameterName_thenReturnReady()
      throws IOException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    StringReader stringReader = new StringReader("foo");
    when(callableStatement.getNCharacterStream(Mockito.<String>any())).thenReturn(stringReader);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Reader actualNCharacterStream = jdbcCallableStatementImpl.getNCharacterStream("Parameter Name");

    // Assert
    verify(callableStatement).getNCharacterStream("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualNCharacterStream.ready());
    assertSame(stringReader, actualNCharacterStream);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getNCharacterStream(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getNCharacterStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCCallableStatementImpl.getNCharacterStream(String)"})
  public void testGetNCharacterStreamWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNCharacterStream(Mockito.<String>any()))
        .thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.getNCharacterStream("Parameter Name"));
    verify(callableStatement).getNCharacterStream("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBlob(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@link SerialBlob#SerialBlob(byte[])} with b is {@code AXAXAXAX} Bytes is
   *       {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBlob(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Blob JDBCCallableStatementImpl.getBlob(int)"})
  public void testGetBlobWithParameterIndex_thenReturnSerialBlobWithBIsAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    SerialBlob serialBlob = new SerialBlob("AXAXAXAX".getBytes("UTF-8"));
    when(callableStatement.getBlob(anyInt())).thenReturn(serialBlob);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Blob actualBlob = jdbcCallableStatementImpl.getBlob(1);

    // Assert
    verify(callableStatement).getBlob(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialBlob, actualBlob);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBlob(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBlob(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Blob JDBCCallableStatementImpl.getBlob(int)"})
  public void testGetBlobWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBlob(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getBlob(1));
    verify(callableStatement).getBlob(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBlob(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@link SerialBlob#SerialBlob(byte[])} with b is {@code AXAXAXAX} Bytes is
   *       {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBlob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Blob JDBCCallableStatementImpl.getBlob(String)"})
  public void testGetBlobWithParameterName_thenReturnSerialBlobWithBIsAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    SerialBlob serialBlob = new SerialBlob("AXAXAXAX".getBytes("UTF-8"));
    when(callableStatement.getBlob(Mockito.<String>any())).thenReturn(serialBlob);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Blob actualBlob = jdbcCallableStatementImpl.getBlob("Parameter Name");

    // Assert
    verify(callableStatement).getBlob("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialBlob, actualBlob);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getBlob(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getBlob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Blob JDBCCallableStatementImpl.getBlob(String)"})
  public void testGetBlobWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBlob(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getBlob("Parameter Name"));
    verify(callableStatement).getBlob("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getClob(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@link SerialClob#SerialClob(char[])} with ch is {@code AZAZ} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getClob(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Clob JDBCCallableStatementImpl.getClob(int)"})
  public void testGetClobWithParameterIndex_thenReturnSerialClobWithChIsAzazToCharArray()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    SerialClob serialClob = new SerialClob("AZAZ".toCharArray());
    when(callableStatement.getClob(anyInt())).thenReturn(serialClob);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Clob actualClob = jdbcCallableStatementImpl.getClob(1);

    // Assert
    verify(callableStatement).getClob(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialClob, actualClob);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getClob(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getClob(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Clob JDBCCallableStatementImpl.getClob(int)"})
  public void testGetClobWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getClob(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getClob(1));
    verify(callableStatement).getClob(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getClob(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@link SerialClob#SerialClob(char[])} with ch is {@code AZAZ} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getClob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Clob JDBCCallableStatementImpl.getClob(String)"})
  public void testGetClobWithParameterName_thenReturnSerialClobWithChIsAzazToCharArray()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    SerialClob serialClob = new SerialClob("AZAZ".toCharArray());
    when(callableStatement.getClob(Mockito.<String>any())).thenReturn(serialClob);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Clob actualClob = jdbcCallableStatementImpl.getClob("Parameter Name");

    // Assert
    verify(callableStatement).getClob("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialClob, actualClob);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getClob(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getClob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Clob JDBCCallableStatementImpl.getClob(String)"})
  public void testGetClobWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getClob(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getClob("Parameter Name"));
    verify(callableStatement).getClob("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getArray(int)} with {@code parameterIndex}.
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getArray(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Array JDBCCallableStatementImpl.getArray(int)"})
  public void testGetArrayWithParameterIndex() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    Object[] items = new Object[] {"Items"};
    JDBCArrayImpl jdbcArrayImpl = new JDBCArrayImpl("Type Name", 1, items);
    when(callableStatement.getArray(anyInt())).thenReturn(jdbcArrayImpl);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Array actualArray = jdbcCallableStatementImpl.getArray(1);

    // Assert
    verify(callableStatement).getArray(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(jdbcArrayImpl, actualArray);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getArray(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getArray(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Array JDBCCallableStatementImpl.getArray(int)"})
  public void testGetArrayWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getArray(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getArray(1));
    verify(callableStatement).getArray(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getArray(String)} with {@code parameterName}.
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getArray(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Array JDBCCallableStatementImpl.getArray(String)"})
  public void testGetArrayWithParameterName() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    Object[] items = new Object[] {"Items"};
    JDBCArrayImpl jdbcArrayImpl = new JDBCArrayImpl("Type Name", 1, items);
    when(callableStatement.getArray(Mockito.<String>any())).thenReturn(jdbcArrayImpl);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Array actualArray = jdbcCallableStatementImpl.getArray("Parameter Name");

    // Assert
    verify(callableStatement).getArray("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(jdbcArrayImpl, actualArray);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getArray(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getArray(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Array JDBCCallableStatementImpl.getArray(String)"})
  public void testGetArrayWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getArray(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getArray("Parameter Name"));
    verify(callableStatement).getArray("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getCharacterStream(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getCharacterStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCCallableStatementImpl.getCharacterStream(int)"})
  public void testGetCharacterStreamWithParameterIndex_thenReturnReady()
      throws IOException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    StringReader stringReader = new StringReader("foo");
    when(callableStatement.getCharacterStream(anyInt())).thenReturn(stringReader);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Reader actualCharacterStream = jdbcCallableStatementImpl.getCharacterStream(1);

    // Assert
    verify(callableStatement).getCharacterStream(1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualCharacterStream.ready());
    assertSame(stringReader, actualCharacterStream);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getCharacterStream(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getCharacterStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCCallableStatementImpl.getCharacterStream(int)"})
  public void testGetCharacterStreamWithParameterIndex_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getCharacterStream(anyInt())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcCallableStatementImpl.getCharacterStream(1));
    verify(callableStatement).getCharacterStream(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getCharacterStream(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getCharacterStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCCallableStatementImpl.getCharacterStream(String)"})
  public void testGetCharacterStreamWithParameterName_thenReturnReady()
      throws IOException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    StringReader stringReader = new StringReader("foo");
    when(callableStatement.getCharacterStream(Mockito.<String>any())).thenReturn(stringReader);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act
    Reader actualCharacterStream = jdbcCallableStatementImpl.getCharacterStream("Parameter Name");

    // Assert
    verify(callableStatement).getCharacterStream("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualCharacterStream.ready());
    assertSame(stringReader, actualCharacterStream);
  }

  /**
   * Test {@link JDBCCallableStatementImpl#getCharacterStream(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCallableStatementImpl#getCharacterStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCCallableStatementImpl.getCharacterStream(String)"})
  public void testGetCharacterStreamWithParameterName_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getCharacterStream(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl jdbcCallableStatementImpl =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> jdbcCallableStatementImpl.getCharacterStream("Parameter Name"));
    verify(callableStatement).getCharacterStream("Parameter Name");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }
}
