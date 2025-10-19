package org.jkiss.dbeaver.model.impl.jdbc.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.CallableStatement;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCCallableStatement;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCDatabaseMetaData;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCPreparedStatement;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCResultSet;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCStatement;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCFactoryDefaultDiffblueTest {
  /**
   * Test {@link JDBCFactoryDefault#createMetaData(JDBCSession, JDBCObjectSupplier)}.
   *
   * <ul>
   *   <li>Given {@link DatabaseMetaData}.
   *   <li>Then return {@link JDBCDatabaseMetaDataImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCFactoryDefault#createMetaData(JDBCSession,
   * JDBCObjectSupplier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDatabaseMetaData JDBCFactoryDefault.createMetaData(JDBCSession, JDBCObjectSupplier)"
  })
  public void testCreateMetaData_givenDatabaseMetaData_thenReturnJDBCDatabaseMetaDataImpl()
      throws SQLException {
    // Arrange
    JDBCFactoryDefault jdbcFactoryDefault = new JDBCFactoryDefault();
    JDBCSession session = mock(JDBCSession.class);

    JDBCObjectSupplier<DatabaseMetaData> metadata = mock(JDBCObjectSupplier.class);
    when(metadata.get()).thenReturn(mock(DatabaseMetaData.class));

    // Act
    JDBCDatabaseMetaData actualCreateMetaDataResult =
        jdbcFactoryDefault.createMetaData(session, metadata);

    // Assert
    verify(metadata).get();
    assertTrue(actualCreateMetaDataResult instanceof JDBCDatabaseMetaDataImpl);
    assertNull(actualCreateMetaDataResult.getCatalogSeparator());
    assertNull(actualCreateMetaDataResult.getCatalogTerm());
    assertNull(actualCreateMetaDataResult.getDatabaseProductName());
    assertNull(actualCreateMetaDataResult.getDatabaseProductVersion());
    assertNull(actualCreateMetaDataResult.getDriverName());
    assertNull(actualCreateMetaDataResult.getDriverVersion());
    assertNull(actualCreateMetaDataResult.getExtraNameCharacters());
    assertNull(actualCreateMetaDataResult.getIdentifierQuoteString());
    assertNull(actualCreateMetaDataResult.getNumericFunctions());
    assertNull(actualCreateMetaDataResult.getProcedureTerm());
    assertNull(actualCreateMetaDataResult.getSQLKeywords());
    assertNull(actualCreateMetaDataResult.getSchemaTerm());
    assertNull(actualCreateMetaDataResult.getSearchStringEscape());
    assertNull(actualCreateMetaDataResult.getStringFunctions());
    assertNull(actualCreateMetaDataResult.getSystemFunctions());
    assertNull(actualCreateMetaDataResult.getTimeDateFunctions());
    assertNull(actualCreateMetaDataResult.getURL());
    assertNull(actualCreateMetaDataResult.getUserName());
    assertNull(actualCreateMetaDataResult.getRowIdLifetime());
    assertNull(actualCreateMetaDataResult.getDataSource());
    assertEquals(0, actualCreateMetaDataResult.getDatabaseMajorVersion());
    assertEquals(0, actualCreateMetaDataResult.getDatabaseMinorVersion());
    assertEquals(0, actualCreateMetaDataResult.getDefaultTransactionIsolation());
    assertEquals(0, actualCreateMetaDataResult.getDriverMajorVersion());
    assertEquals(0, actualCreateMetaDataResult.getDriverMinorVersion());
    assertEquals(0, actualCreateMetaDataResult.getJDBCMajorVersion());
    assertEquals(0, actualCreateMetaDataResult.getJDBCMinorVersion());
    assertEquals(0, actualCreateMetaDataResult.getMaxBinaryLiteralLength());
    assertEquals(0, actualCreateMetaDataResult.getMaxCatalogNameLength());
    assertEquals(0, actualCreateMetaDataResult.getMaxCharLiteralLength());
    assertEquals(0, actualCreateMetaDataResult.getMaxColumnNameLength());
    assertEquals(0, actualCreateMetaDataResult.getMaxColumnsInGroupBy());
    assertEquals(0, actualCreateMetaDataResult.getMaxColumnsInIndex());
    assertEquals(0, actualCreateMetaDataResult.getMaxColumnsInOrderBy());
    assertEquals(0, actualCreateMetaDataResult.getMaxColumnsInSelect());
    assertEquals(0, actualCreateMetaDataResult.getMaxColumnsInTable());
    assertEquals(0, actualCreateMetaDataResult.getMaxConnections());
    assertEquals(0, actualCreateMetaDataResult.getMaxCursorNameLength());
    assertEquals(0, actualCreateMetaDataResult.getMaxIndexLength());
    assertEquals(0, actualCreateMetaDataResult.getMaxProcedureNameLength());
    assertEquals(0, actualCreateMetaDataResult.getMaxRowSize());
    assertEquals(0, actualCreateMetaDataResult.getMaxSchemaNameLength());
    assertEquals(0, actualCreateMetaDataResult.getMaxStatementLength());
    assertEquals(0, actualCreateMetaDataResult.getMaxStatements());
    assertEquals(0, actualCreateMetaDataResult.getMaxTableNameLength());
    assertEquals(0, actualCreateMetaDataResult.getMaxTablesInSelect());
    assertEquals(0, actualCreateMetaDataResult.getMaxUserNameLength());
    assertEquals(0, actualCreateMetaDataResult.getResultSetHoldability());
    assertEquals(0, actualCreateMetaDataResult.getSQLStateType());
    assertEquals(0L, actualCreateMetaDataResult.getMaxLogicalLobSize());
    assertFalse(actualCreateMetaDataResult.isCatalogAtStart());
    assertFalse(actualCreateMetaDataResult.isReadOnly());
    assertSame(session, actualCreateMetaDataResult.getConnection());
  }

  /**
   * Test {@link JDBCFactoryDefault#createMetaData(JDBCSession, JDBCObjectSupplier)}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCFactoryDefault#createMetaData(JDBCSession,
   * JDBCObjectSupplier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDatabaseMetaData JDBCFactoryDefault.createMetaData(JDBCSession, JDBCObjectSupplier)"
  })
  public void testCreateMetaData_givenSQLException_thenThrowSQLException() throws SQLException {
    // Arrange
    JDBCFactoryDefault jdbcFactoryDefault = new JDBCFactoryDefault();
    JDBCSession session = mock(JDBCSession.class);

    JDBCObjectSupplier<DatabaseMetaData> metadata = mock(JDBCObjectSupplier.class);
    when(metadata.get()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(SQLException.class, () -> jdbcFactoryDefault.createMetaData(session, metadata));
    verify(metadata).get();
  }

  /**
   * Test {@link JDBCFactoryDefault#createStatement(JDBCSession, JDBCObjectSupplier, boolean)}.
   *
   * <ul>
   *   <li>Given {@link Statement}.
   *   <li>Then return {@link JDBCStatementImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCFactoryDefault#createStatement(JDBCSession,
   * JDBCObjectSupplier, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCStatement JDBCFactoryDefault.createStatement(JDBCSession, JDBCObjectSupplier, boolean)"
  })
  public void testCreateStatement_givenStatement_thenReturnJDBCStatementImpl()
      throws SQLException, DBCException {
    // Arrange
    JDBCFactoryDefault jdbcFactoryDefault = new JDBCFactoryDefault();
    JDBCSession session = mock(JDBCSession.class);

    JDBCObjectSupplier<Statement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(mock(Statement.class));

    // Act
    JDBCStatement actualCreateStatementResult =
        jdbcFactoryDefault.createStatement(session, stmtSupplier, true);

    // Assert
    verify(stmtSupplier).get();
    assertTrue(actualCreateStatementResult instanceof JDBCStatementImpl);
    assertNull(actualCreateStatementResult.getQueryString());
    assertNull(actualCreateStatementResult.getBlockThread());
    assertNull(actualCreateStatementResult.getStatementWarnings());
    assertNull(actualCreateStatementResult.getGeneratedKeys());
    assertNull(actualCreateStatementResult.getWarnings());
    assertNull(actualCreateStatementResult.getStatementSource());
    assertNull(actualCreateStatementResult.getResultSet());
    assertEquals(0, actualCreateStatementResult.getFetchDirection());
    assertEquals(0, actualCreateStatementResult.getFetchSize());
    assertEquals(0, actualCreateStatementResult.getMaxFieldSize());
    assertEquals(0, actualCreateStatementResult.getMaxRows());
    assertEquals(0, actualCreateStatementResult.getQueryTimeout());
    assertEquals(0, actualCreateStatementResult.getResultSetConcurrency());
    assertEquals(0, actualCreateStatementResult.getResultSetHoldability());
    assertEquals(0, actualCreateStatementResult.getResultSetType());
    assertEquals(0, actualCreateStatementResult.getUpdateCount());
    assertEquals(0L, actualCreateStatementResult.getLargeMaxRows());
    assertEquals(0L, actualCreateStatementResult.getLargeUpdateCount());
    assertEquals(0L, actualCreateStatementResult.getUpdateRowCount());
    assertFalse(actualCreateStatementResult.getMoreResults());
    assertFalse(actualCreateStatementResult.isCloseOnCompletion());
    assertFalse(actualCreateStatementResult.isClosed());
    assertFalse(actualCreateStatementResult.isPoolable());
    assertFalse(actualCreateStatementResult.isStatementClosed());
    assertFalse(((JDBCStatementImpl<Statement>) actualCreateStatementResult).isQMLoggingEnabled());
    assertTrue(((JDBCStatementImpl<Statement>) actualCreateStatementResult).disableLogging);
    assertSame(session, actualCreateStatementResult.getSession());
    assertSame(session, actualCreateStatementResult.getConnection());
  }

  /**
   * Test {@link JDBCFactoryDefault#createPreparedStatement(JDBCSession, JDBCObjectSupplier, String,
   * boolean)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCPreparedStatementImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCFactoryDefault#createPreparedStatement(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCPreparedStatement JDBCFactoryDefault.createPreparedStatement(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testCreatePreparedStatement_thenReturnJDBCPreparedStatementImpl()
      throws SQLException, DBCException {
    // Arrange
    JDBCFactoryDefault jdbcFactoryDefault = new JDBCFactoryDefault();
    JDBCSession session = mock(JDBCSession.class);

    JDBCObjectSupplier<PreparedStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(mock(PreparedStatement.class));

    // Act
    JDBCPreparedStatement actualCreatePreparedStatementResult =
        jdbcFactoryDefault.createPreparedStatement(session, stmtSupplier, "Sql", true);

    // Assert
    verify(stmtSupplier).get();
    assertTrue(actualCreatePreparedStatementResult instanceof JDBCPreparedStatementImpl);
    assertEquals("Sql", actualCreatePreparedStatementResult.getFormattedQuery());
    assertEquals("Sql", actualCreatePreparedStatementResult.getQueryString());
    assertNull(actualCreatePreparedStatementResult.getBlockThread());
    assertNull(actualCreatePreparedStatementResult.getStatementWarnings());
    assertNull(actualCreatePreparedStatementResult.getParameterMetaData());
    assertNull(actualCreatePreparedStatementResult.getGeneratedKeys());
    assertNull(actualCreatePreparedStatementResult.getMetaData());
    assertNull(actualCreatePreparedStatementResult.getWarnings());
    assertNull(actualCreatePreparedStatementResult.getStatementSource());
    assertNull(actualCreatePreparedStatementResult.getResultSet());
    assertEquals(0, actualCreatePreparedStatementResult.getFetchDirection());
    assertEquals(0, actualCreatePreparedStatementResult.getFetchSize());
    assertEquals(0, actualCreatePreparedStatementResult.getMaxFieldSize());
    assertEquals(0, actualCreatePreparedStatementResult.getMaxRows());
    assertEquals(0, actualCreatePreparedStatementResult.getQueryTimeout());
    assertEquals(0, actualCreatePreparedStatementResult.getResultSetConcurrency());
    assertEquals(0, actualCreatePreparedStatementResult.getResultSetHoldability());
    assertEquals(0, actualCreatePreparedStatementResult.getResultSetType());
    assertEquals(0, actualCreatePreparedStatementResult.getUpdateCount());
    assertEquals(0L, actualCreatePreparedStatementResult.getLargeMaxRows());
    assertEquals(0L, actualCreatePreparedStatementResult.getLargeUpdateCount());
    assertEquals(0L, actualCreatePreparedStatementResult.getUpdateRowCount());
    assertFalse(actualCreatePreparedStatementResult.getMoreResults());
    assertFalse(actualCreatePreparedStatementResult.isCloseOnCompletion());
    assertFalse(actualCreatePreparedStatementResult.isClosed());
    assertFalse(actualCreatePreparedStatementResult.isPoolable());
    assertFalse(actualCreatePreparedStatementResult.isStatementClosed());
    assertFalse(
        ((JDBCPreparedStatementImpl<PreparedStatement>) actualCreatePreparedStatementResult)
            .isQMLoggingEnabled());
    assertTrue(
        ((JDBCPreparedStatementImpl<PreparedStatement>) actualCreatePreparedStatementResult)
            .disableLogging);
    assertSame(session, actualCreatePreparedStatementResult.getConnection());
    assertSame(session, actualCreatePreparedStatementResult.getSession());
  }

  /**
   * Test {@link JDBCFactoryDefault#createCallableStatement(JDBCSession, JDBCObjectSupplier, String,
   * boolean)}.
   *
   * <p>Method under test: {@link JDBCFactoryDefault#createCallableStatement(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCCallableStatement JDBCFactoryDefault.createCallableStatement(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testCreateCallableStatement() throws SQLException, DBCException {
    // Arrange
    JDBCFactoryDefault jdbcFactoryDefault = new JDBCFactoryDefault();

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    // Act
    JDBCCallableStatement actualCreateCallableStatementResult =
        jdbcFactoryDefault.createCallableStatement(session, stmtSupplier, "Sql", true);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    assertTrue(actualCreateCallableStatementResult instanceof JDBCCallableStatementImpl);
    assertTrue(actualCreateCallableStatementResult.getResultSet() instanceof JDBCResultSetCallable);
    assertEquals("Sql", actualCreateCallableStatementResult.getFormattedQuery());
    assertEquals("Sql", actualCreateCallableStatementResult.getQueryString());
    assertNull(actualCreateCallableStatementResult.getBlockThread());
    assertNull(actualCreateCallableStatementResult.getStatementWarnings());
    assertNull(actualCreateCallableStatementResult.getGeneratedKeys());
    assertNull(actualCreateCallableStatementResult.getMetaData());
    assertNull(actualCreateCallableStatementResult.getWarnings());
    assertNull(actualCreateCallableStatementResult.getStatementSource());
    assertEquals(0, actualCreateCallableStatementResult.getFetchDirection());
    assertEquals(0, actualCreateCallableStatementResult.getFetchSize());
    assertEquals(0, actualCreateCallableStatementResult.getMaxFieldSize());
    assertEquals(0, actualCreateCallableStatementResult.getMaxRows());
    assertEquals(0, actualCreateCallableStatementResult.getQueryTimeout());
    assertEquals(0, actualCreateCallableStatementResult.getResultSetConcurrency());
    assertEquals(0, actualCreateCallableStatementResult.getResultSetHoldability());
    assertEquals(0, actualCreateCallableStatementResult.getResultSetType());
    assertEquals(0, actualCreateCallableStatementResult.getUpdateCount());
    assertEquals(0L, actualCreateCallableStatementResult.getLargeMaxRows());
    assertEquals(0L, actualCreateCallableStatementResult.getLargeUpdateCount());
    assertEquals(0L, actualCreateCallableStatementResult.getUpdateRowCount());
    assertFalse(actualCreateCallableStatementResult.getMoreResults());
    assertFalse(actualCreateCallableStatementResult.isCloseOnCompletion());
    assertFalse(actualCreateCallableStatementResult.isClosed());
    assertFalse(actualCreateCallableStatementResult.isPoolable());
    assertFalse(actualCreateCallableStatementResult.isStatementClosed());
    assertFalse(
        ((JDBCCallableStatementImpl) actualCreateCallableStatementResult).isQMLoggingEnabled());
    assertTrue(((JDBCCallableStatementImpl) actualCreateCallableStatementResult).disableLogging);
    assertSame(session, actualCreateCallableStatementResult.getConnection());
    assertSame(session, actualCreateCallableStatementResult.getSession());
  }

  /**
   * Test {@link JDBCFactoryDefault#createCallableStatement(JDBCSession, JDBCObjectSupplier, String,
   * boolean)}.
   *
   * <p>Method under test: {@link JDBCFactoryDefault#createCallableStatement(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCCallableStatement JDBCFactoryDefault.createCallableStatement(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testCreateCallableStatement2() throws SQLException, DBCException {
    // Arrange
    JDBCFactoryDefault jdbcFactoryDefault = new JDBCFactoryDefault();

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    // Act
    JDBCCallableStatement actualCreateCallableStatementResult =
        jdbcFactoryDefault.createCallableStatement(session, stmtSupplier, "Sql", true);

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    assertTrue(actualCreateCallableStatementResult instanceof JDBCCallableStatementImpl);
    assertTrue(actualCreateCallableStatementResult.getResultSet() instanceof JDBCResultSetCallable);
    assertEquals("Sql", actualCreateCallableStatementResult.getFormattedQuery());
    assertEquals("Sql", actualCreateCallableStatementResult.getQueryString());
    assertNull(actualCreateCallableStatementResult.getBlockThread());
    assertNull(actualCreateCallableStatementResult.getStatementWarnings());
    assertNull(actualCreateCallableStatementResult.getGeneratedKeys());
    assertNull(actualCreateCallableStatementResult.getMetaData());
    assertNull(actualCreateCallableStatementResult.getWarnings());
    assertNull(actualCreateCallableStatementResult.getStatementSource());
    assertEquals(0, actualCreateCallableStatementResult.getFetchDirection());
    assertEquals(0, actualCreateCallableStatementResult.getFetchSize());
    assertEquals(0, actualCreateCallableStatementResult.getMaxFieldSize());
    assertEquals(0, actualCreateCallableStatementResult.getMaxRows());
    assertEquals(0, actualCreateCallableStatementResult.getQueryTimeout());
    assertEquals(0, actualCreateCallableStatementResult.getResultSetConcurrency());
    assertEquals(0, actualCreateCallableStatementResult.getResultSetHoldability());
    assertEquals(0, actualCreateCallableStatementResult.getResultSetType());
    assertEquals(0, actualCreateCallableStatementResult.getUpdateCount());
    assertEquals(0L, actualCreateCallableStatementResult.getLargeMaxRows());
    assertEquals(0L, actualCreateCallableStatementResult.getLargeUpdateCount());
    assertEquals(0L, actualCreateCallableStatementResult.getUpdateRowCount());
    assertFalse(actualCreateCallableStatementResult.getMoreResults());
    assertFalse(actualCreateCallableStatementResult.isCloseOnCompletion());
    assertFalse(actualCreateCallableStatementResult.isClosed());
    assertFalse(actualCreateCallableStatementResult.isPoolable());
    assertFalse(actualCreateCallableStatementResult.isStatementClosed());
    assertFalse(
        ((JDBCCallableStatementImpl) actualCreateCallableStatementResult).isQMLoggingEnabled());
    assertTrue(((JDBCCallableStatementImpl) actualCreateCallableStatementResult).disableLogging);
    assertSame(session, actualCreateCallableStatementResult.getConnection());
    assertSame(session, actualCreateCallableStatementResult.getSession());
  }

  /**
   * Test {@link JDBCFactoryDefault#createCallableStatement(JDBCSession, JDBCObjectSupplier, String,
   * boolean)}.
   *
   * <p>Method under test: {@link JDBCFactoryDefault#createCallableStatement(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCCallableStatement JDBCFactoryDefault.createCallableStatement(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testCreateCallableStatement3() throws SQLException, DBCException {
    // Arrange
    JDBCFactoryDefault jdbcFactoryDefault = new JDBCFactoryDefault();

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenThrow(new SQLException());
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    // Act
    JDBCCallableStatement actualCreateCallableStatementResult =
        jdbcFactoryDefault.createCallableStatement(session, stmtSupplier, "Sql", true);

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(1);
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    assertTrue(actualCreateCallableStatementResult instanceof JDBCCallableStatementImpl);
    assertTrue(actualCreateCallableStatementResult.getResultSet() instanceof JDBCResultSetCallable);
    assertEquals("Sql", actualCreateCallableStatementResult.getFormattedQuery());
    assertEquals("Sql", actualCreateCallableStatementResult.getQueryString());
    assertNull(actualCreateCallableStatementResult.getBlockThread());
    assertNull(actualCreateCallableStatementResult.getStatementWarnings());
    assertNull(actualCreateCallableStatementResult.getGeneratedKeys());
    assertNull(actualCreateCallableStatementResult.getMetaData());
    assertNull(actualCreateCallableStatementResult.getWarnings());
    assertNull(actualCreateCallableStatementResult.getStatementSource());
    assertEquals(0, actualCreateCallableStatementResult.getFetchDirection());
    assertEquals(0, actualCreateCallableStatementResult.getFetchSize());
    assertEquals(0, actualCreateCallableStatementResult.getMaxFieldSize());
    assertEquals(0, actualCreateCallableStatementResult.getMaxRows());
    assertEquals(0, actualCreateCallableStatementResult.getQueryTimeout());
    assertEquals(0, actualCreateCallableStatementResult.getResultSetConcurrency());
    assertEquals(0, actualCreateCallableStatementResult.getResultSetHoldability());
    assertEquals(0, actualCreateCallableStatementResult.getResultSetType());
    assertEquals(0, actualCreateCallableStatementResult.getUpdateCount());
    assertEquals(0L, actualCreateCallableStatementResult.getLargeMaxRows());
    assertEquals(0L, actualCreateCallableStatementResult.getLargeUpdateCount());
    assertEquals(0L, actualCreateCallableStatementResult.getUpdateRowCount());
    assertFalse(actualCreateCallableStatementResult.getMoreResults());
    assertFalse(actualCreateCallableStatementResult.isCloseOnCompletion());
    assertFalse(actualCreateCallableStatementResult.isClosed());
    assertFalse(actualCreateCallableStatementResult.isPoolable());
    assertFalse(actualCreateCallableStatementResult.isStatementClosed());
    assertFalse(
        ((JDBCCallableStatementImpl) actualCreateCallableStatementResult).isQMLoggingEnabled());
    assertTrue(((JDBCCallableStatementImpl) actualCreateCallableStatementResult).disableLogging);
    assertSame(session, actualCreateCallableStatementResult.getConnection());
    assertSame(session, actualCreateCallableStatementResult.getSession());
  }

  /**
   * Test {@link JDBCFactoryDefault#createCallableStatement(JDBCSession, JDBCObjectSupplier, String,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link ParameterMetaData} {@link ParameterMetaData#getParameterMode(int)} return
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCFactoryDefault#createCallableStatement(JDBCSession,
   * JDBCObjectSupplier, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCCallableStatement JDBCFactoryDefault.createCallableStatement(JDBCSession, JDBCObjectSupplier, String, boolean)"
  })
  public void testCreateCallableStatement_givenParameterMetaDataGetParameterModeReturnOne()
      throws SQLException, DBCException {
    // Arrange
    JDBCFactoryDefault jdbcFactoryDefault = new JDBCFactoryDefault();

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    // Act
    JDBCCallableStatement actualCreateCallableStatementResult =
        jdbcFactoryDefault.createCallableStatement(session, stmtSupplier, "Sql", true);

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session, atLeast(1)).getDataSource();
    assertTrue(actualCreateCallableStatementResult instanceof JDBCCallableStatementImpl);
    assertTrue(actualCreateCallableStatementResult.getResultSet() instanceof JDBCResultSetCallable);
    assertEquals("Sql", actualCreateCallableStatementResult.getFormattedQuery());
    assertEquals("Sql", actualCreateCallableStatementResult.getQueryString());
    assertNull(actualCreateCallableStatementResult.getBlockThread());
    assertNull(actualCreateCallableStatementResult.getStatementWarnings());
    assertNull(actualCreateCallableStatementResult.getGeneratedKeys());
    assertNull(actualCreateCallableStatementResult.getMetaData());
    assertNull(actualCreateCallableStatementResult.getWarnings());
    assertNull(actualCreateCallableStatementResult.getStatementSource());
    assertEquals(0, actualCreateCallableStatementResult.getFetchDirection());
    assertEquals(0, actualCreateCallableStatementResult.getFetchSize());
    assertEquals(0, actualCreateCallableStatementResult.getMaxFieldSize());
    assertEquals(0, actualCreateCallableStatementResult.getMaxRows());
    assertEquals(0, actualCreateCallableStatementResult.getQueryTimeout());
    assertEquals(0, actualCreateCallableStatementResult.getResultSetConcurrency());
    assertEquals(0, actualCreateCallableStatementResult.getResultSetHoldability());
    assertEquals(0, actualCreateCallableStatementResult.getResultSetType());
    assertEquals(0, actualCreateCallableStatementResult.getUpdateCount());
    assertEquals(0L, actualCreateCallableStatementResult.getLargeMaxRows());
    assertEquals(0L, actualCreateCallableStatementResult.getLargeUpdateCount());
    assertEquals(0L, actualCreateCallableStatementResult.getUpdateRowCount());
    assertFalse(actualCreateCallableStatementResult.getMoreResults());
    assertFalse(actualCreateCallableStatementResult.isCloseOnCompletion());
    assertFalse(actualCreateCallableStatementResult.isClosed());
    assertFalse(actualCreateCallableStatementResult.isPoolable());
    assertFalse(actualCreateCallableStatementResult.isStatementClosed());
    assertFalse(
        ((JDBCCallableStatementImpl) actualCreateCallableStatementResult).isQMLoggingEnabled());
    assertTrue(((JDBCCallableStatementImpl) actualCreateCallableStatementResult).disableLogging);
    assertSame(session, actualCreateCallableStatementResult.getConnection());
    assertSame(session, actualCreateCallableStatementResult.getSession());
  }

  /**
   * Test {@link JDBCFactoryDefault#createResultSet(JDBCSession, JDBCStatement, ResultSet,
   * boolean)}.
   *
   * <ul>
   *   <li>Then return {@link JDBCResultSetImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCFactoryDefault#createResultSet(JDBCSession, JDBCStatement,
   * ResultSet, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCResultSet JDBCFactoryDefault.createResultSet(JDBCSession, JDBCStatement, ResultSet, boolean)"
  })
  public void testCreateResultSet_thenReturnJDBCResultSetImpl() throws SQLException {
    // Arrange
    JDBCFactoryDefault jdbcFactoryDefault = new JDBCFactoryDefault();
    JDBCSession session = mock(JDBCSession.class);
    JDBCStatement statement = mock(JDBCStatement.class);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement2 =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable original = new JDBCResultSetCallable(mock(JDBCSession.class), statement2);

    // Act
    JDBCResultSet actualCreateResultSetResult =
        jdbcFactoryDefault.createResultSet(session, statement, original, true);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualCreateResultSetResult instanceof JDBCResultSetImpl);
  }
}
