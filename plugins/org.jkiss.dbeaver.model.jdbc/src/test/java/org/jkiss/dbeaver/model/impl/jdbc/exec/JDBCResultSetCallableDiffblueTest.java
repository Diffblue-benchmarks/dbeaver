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
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialRef;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCArrayImpl;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCExecutionContext;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCRemoteInstance;
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCContentBytes;
import org.jkiss.dbeaver.model.impl.jdbc.data.JDBCSQLXMLImpl;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCResultSetCallableDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCResultSetCallable#JDBCResultSetCallable(JDBCSession,
   *       JDBCCallableStatementImpl)}
   *   <li>{@link JDBCResultSetCallable#absolute(int)}
   *   <li>{@link JDBCResultSetCallable#relative(int)}
   *   <li>{@link JDBCResultSetCallable#clearWarnings()}
   *   <li>{@link JDBCResultSetCallable#setFetchDirection(int)}
   *   <li>{@link JDBCResultSetCallable#setFetchSize(int)}
   *   <li>{@link JDBCResultSetCallable#setMaxRows(long)}
   *   <li>{@link JDBCResultSetCallable#beforeFirst()}
   *   <li>{@link JDBCResultSetCallable#first()}
   *   <li>{@link JDBCResultSetCallable#previous()}
   *   <li>{@link JDBCResultSetCallable#getCursorName()}
   *   <li>{@link JDBCResultSetCallable#getFetchSize()}
   *   <li>{@link JDBCResultSetCallable#getHoldability()}
   *   <li>{@link JDBCResultSetCallable#getMetaData()}
   *   <li>{@link JDBCResultSetCallable#getOriginal()}
   *   <li>{@link JDBCResultSetCallable#getRow()}
   *   <li>{@link JDBCResultSetCallable#getWarnings()}
   *   <li>{@link JDBCResultSetCallable#isClosed()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCResultSetCallable.<init>(JDBCSession, JDBCCallableStatementImpl)",
    "boolean JDBCResultSetCallable.absolute(int)",
    "void JDBCResultSetCallable.beforeFirst()",
    "void JDBCResultSetCallable.clearWarnings()",
    "boolean JDBCResultSetCallable.first()",
    "String JDBCResultSetCallable.getCursorName()",
    "int JDBCResultSetCallable.getFetchSize()",
    "int JDBCResultSetCallable.getHoldability()",
    "ResultSetMetaData JDBCResultSetCallable.getMetaData()",
    "ResultSet JDBCResultSetCallable.getOriginal()",
    "int JDBCResultSetCallable.getRow()",
    "SQLWarning JDBCResultSetCallable.getWarnings()",
    "boolean JDBCResultSetCallable.isClosed()",
    "boolean JDBCResultSetCallable.previous()",
    "boolean JDBCResultSetCallable.relative(int)",
    "void JDBCResultSetCallable.setFetchDirection(int)",
    "void JDBCResultSetCallable.setFetchSize(int)",
    "void JDBCResultSetCallable.setMaxRows(long)"
  })
  public void testGettersAndSetters() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(
            mock(JDBCSession.class), mock(JDBCObjectSupplier.class), "Query", true);

    // Act
    JDBCResultSetCallable actualJdbcResultSetCallable =
        new JDBCResultSetCallable(session, statement);
    boolean actualAbsoluteResult = actualJdbcResultSetCallable.absolute(1);
    boolean actualRelativeResult = actualJdbcResultSetCallable.relative(1);
    actualJdbcResultSetCallable.clearWarnings();
    actualJdbcResultSetCallable.setFetchDirection(1);
    actualJdbcResultSetCallable.setFetchSize(1);
    actualJdbcResultSetCallable.setMaxRows(1L);
    actualJdbcResultSetCallable.beforeFirst();
    boolean actualFirstResult = actualJdbcResultSetCallable.first();
    boolean actualPreviousResult = actualJdbcResultSetCallable.previous();
    String actualCursorName = actualJdbcResultSetCallable.getCursorName();
    int actualFetchSize = actualJdbcResultSetCallable.getFetchSize();
    int actualHoldability = actualJdbcResultSetCallable.getHoldability();
    ResultSetMetaData actualMetaData = actualJdbcResultSetCallable.getMetaData();
    ResultSet actualOriginal = actualJdbcResultSetCallable.getOriginal();
    int actualRow = actualJdbcResultSetCallable.getRow();
    SQLWarning actualWarnings = actualJdbcResultSetCallable.getWarnings();

    // Assert
    assertNull(actualCursorName);
    assertNull(actualOriginal);
    assertNull(actualMetaData);
    assertNull(actualWarnings);
    assertEquals(-1, actualRow);
    assertEquals(1, actualFetchSize);
    assertEquals(1, actualHoldability);
    assertFalse(actualJdbcResultSetCallable.isClosed());
    assertTrue(actualAbsoluteResult);
    assertTrue(actualFirstResult);
    assertTrue(actualPreviousResult);
    assertTrue(actualRelativeResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#getSession()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getSession()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JDBCSession JDBCResultSetCallable.getSession()"})
  public void testGetSession() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    jdbcResultSetCallable.getSession();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#next()}.
   *
   * <ul>
   *   <li>Given {@link ParameterMetaData} {@link ParameterMetaData#getParameterMode(int)} return
   *       one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.next()"})
  public void testNext_givenParameterMetaDataGetParameterModeReturnOne_thenReturnTrue()
      throws SQLException {
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addRow("Values");

    // Act
    boolean actualNextResult = jdbcResultSetCallable.next();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualNextResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#next()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.next()"})
  public void testNext_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    boolean actualNextResult = jdbcResultSetCallable.next();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualNextResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#wasNull()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#wasNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.wasNull()"})
  public void testWasNull() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    boolean actualWasNullResult = jdbcResultSetCallable.wasNull();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualWasNullResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#getStatement()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Statement JDBCResultSetCallable.getStatement()"})
  public void testGetStatement() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    Statement actualStatement = jdbcResultSetCallable.getStatement();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(statement, actualStatement);
  }

  /**
   * Test {@link JDBCResultSetCallable#getString(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getString(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetCallable.getString(int)"})
  public void testGetStringWithParameterIndex_thenReturnString() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getString(anyInt())).thenReturn("String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    String actualString = jdbcResultSetCallable.getString(1);

    // Assert
    verify(callableStatement).getString(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals("String", actualString);
  }

  /**
   * Test {@link JDBCResultSetCallable#getString(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetCallable.getString(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    String actualString = jdbcResultSetCallable.getString("Parameter Name");

    // Assert
    verify(callableStatement).getString("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("String", actualString);
  }

  /**
   * Test {@link JDBCResultSetCallable#getBoolean(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBoolean(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.getBoolean(int)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    boolean actualBoolean = jdbcResultSetCallable.getBoolean(1);

    // Assert
    verify(callableStatement).getBoolean(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertFalse(actualBoolean);
  }

  /**
   * Test {@link JDBCResultSetCallable#getBoolean(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBoolean(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.getBoolean(int)"})
  public void testGetBooleanWithParameterIndex_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(anyInt())).thenReturn(true);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    boolean actualBoolean = jdbcResultSetCallable.getBoolean(1);

    // Assert
    verify(callableStatement).getBoolean(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualBoolean);
  }

  /**
   * Test {@link JDBCResultSetCallable#getBoolean(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBoolean(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.getBoolean(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    boolean actualBoolean = jdbcResultSetCallable.getBoolean("Parameter Name");

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
   * Test {@link JDBCResultSetCallable#getBoolean(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBoolean(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.getBoolean(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    boolean actualBoolean = jdbcResultSetCallable.getBoolean("Parameter Name");

    // Assert
    verify(callableStatement).getBoolean("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualBoolean);
  }

  /**
   * Test {@link JDBCResultSetCallable#getByte(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getByte(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JDBCResultSetCallable.getByte(int)"})
  public void testGetByteWithParameterIndex_thenReturnA() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getByte(anyInt())).thenReturn((byte) 'A');
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    byte actualByte = jdbcResultSetCallable.getByte(1);

    // Assert
    verify(callableStatement).getByte(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals('A', actualByte);
  }

  /**
   * Test {@link JDBCResultSetCallable#getByte(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getByte(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte JDBCResultSetCallable.getByte(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    byte actualByte = jdbcResultSetCallable.getByte("Parameter Name");

    // Assert
    verify(callableStatement).getByte("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals('A', actualByte);
  }

  /**
   * Test {@link JDBCResultSetCallable#getShort(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getShort(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JDBCResultSetCallable.getShort(int)"})
  public void testGetShortWithParameterIndex_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getShort(anyInt())).thenReturn((short) 1);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    short actualShort = jdbcResultSetCallable.getShort(1);

    // Assert
    verify(callableStatement).getShort(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals((short) 1, actualShort);
  }

  /**
   * Test {@link JDBCResultSetCallable#getShort(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getShort(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"short JDBCResultSetCallable.getShort(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    short actualShort = jdbcResultSetCallable.getShort("Parameter Name");

    // Assert
    verify(callableStatement).getShort("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals((short) 1, actualShort);
  }

  /**
   * Test {@link JDBCResultSetCallable#getInt(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getInt(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetCallable.getInt(int)"})
  public void testGetIntWithParameterIndex_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getInt(anyInt())).thenReturn(1);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    int actualInt = jdbcResultSetCallable.getInt(1);

    // Assert
    verify(callableStatement).getInt(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(1, actualInt);
  }

  /**
   * Test {@link JDBCResultSetCallable#getInt(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getInt(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetCallable.getInt(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    int actualInt = jdbcResultSetCallable.getInt("Parameter Name");

    // Assert
    verify(callableStatement).getInt("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1, actualInt);
  }

  /**
   * Test {@link JDBCResultSetCallable#getLong(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getLong(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCResultSetCallable.getLong(int)"})
  public void testGetLongWithParameterIndex_thenReturnOne() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getLong(anyInt())).thenReturn(1L);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    long actualLong = jdbcResultSetCallable.getLong(1);

    // Assert
    verify(callableStatement).getLong(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(1L, actualLong);
  }

  /**
   * Test {@link JDBCResultSetCallable#getLong(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getLong(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCResultSetCallable.getLong(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    long actualLong = jdbcResultSetCallable.getLong("Parameter Name");

    // Assert
    verify(callableStatement).getLong("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1L, actualLong);
  }

  /**
   * Test {@link JDBCResultSetCallable#getFloat(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getFloat(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JDBCResultSetCallable.getFloat(int)"})
  public void testGetFloatWithParameterIndex_thenReturnTen() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getFloat(anyInt())).thenReturn(10.0f);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    float actualFloat = jdbcResultSetCallable.getFloat(1);

    // Assert
    verify(callableStatement).getFloat(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(10.0f, actualFloat, 0.0f);
  }

  /**
   * Test {@link JDBCResultSetCallable#getFloat(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getFloat(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"float JDBCResultSetCallable.getFloat(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    float actualFloat = jdbcResultSetCallable.getFloat("Parameter Name");

    // Assert
    verify(callableStatement).getFloat("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(10.0f, actualFloat, 0.0f);
  }

  /**
   * Test {@link JDBCResultSetCallable#getDouble(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getDouble(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JDBCResultSetCallable.getDouble(int)"})
  public void testGetDoubleWithParameterIndex_thenReturnTen() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDouble(anyInt())).thenReturn(10.0d);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    double actualDouble = jdbcResultSetCallable.getDouble(1);

    // Assert
    verify(callableStatement).getDouble(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(10.0d, actualDouble, 0.0);
  }

  /**
   * Test {@link JDBCResultSetCallable#getDouble(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getDouble(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"double JDBCResultSetCallable.getDouble(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    double actualDouble = jdbcResultSetCallable.getDouble("Parameter Name");

    // Assert
    verify(callableStatement).getDouble("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(10.0d, actualDouble, 0.0);
  }

  /**
   * Test {@link JDBCResultSetCallable#getBigDecimal(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBigDecimal(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetCallable.getBigDecimal(int)"})
  public void testGetBigDecimalWithColumnIndex_thenReturnBigDecimalWith23() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBigDecimal(anyInt())).thenReturn(new BigDecimal("2.3"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetCallable.getBigDecimal(1);

    // Assert
    verify(callableStatement).getBigDecimal(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetCallable#getBigDecimal(String, int)} with {@code columnLabel}, {@code
   * scale}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBigDecimal(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetCallable.getBigDecimal(String, int)"})
  public void testGetBigDecimalWithColumnLabelScale_thenReturnBigDecimalWith23()
      throws SQLException {
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetCallable.getBigDecimal("2.3", 1);

    // Assert
    verify(callableStatement).getBigDecimal("2.3");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetCallable#getBigDecimal(int, int)} with {@code parameterIndex}, {@code
   * scale}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetCallable.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithParameterIndexScale_whenMinusOne_thenReturnBigDecimalWith23()
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetCallable.getBigDecimal(1, -1);

    // Assert
    verify(callableStatement).getBigDecimal(0, -1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetCallable#getBigDecimal(int, int)} with {@code parameterIndex}, {@code
   * scale}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetCallable.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithParameterIndexScale_whenOne_thenReturnBigDecimalWith23()
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetCallable.getBigDecimal(1, 3);

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
   * Test {@link JDBCResultSetCallable#getBigDecimal(int, int)} with {@code parameterIndex}, {@code
   * scale}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetCallable.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithParameterIndexScale_whenOne_thenReturnBigDecimalWith232()
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetCallable.getBigDecimal(1, 1);

    // Assert
    verify(callableStatement).getBigDecimal(0, 1);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetCallable#getBigDecimal(int, int)} with {@code parameterIndex}, {@code
   * scale}.
   *
   * <ul>
   *   <li>When zero.
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBigDecimal(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetCallable.getBigDecimal(int, int)"})
  public void testGetBigDecimalWithParameterIndexScale_whenZero_thenReturnBigDecimalWith23()
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetCallable.getBigDecimal(1, 0);

    // Assert
    verify(callableStatement).getBigDecimal(0, 0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetCallable#getBigDecimal(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@link BigDecimal#BigDecimal(String)} with {@code 2.3}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBigDecimal(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"BigDecimal JDBCResultSetCallable.getBigDecimal(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    BigDecimal actualBigDecimal = jdbcResultSetCallable.getBigDecimal("2.3");

    // Assert
    verify(callableStatement).getBigDecimal("2.3");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(new BigDecimal("2.3"), actualBigDecimal);
  }

  /**
   * Test {@link JDBCResultSetCallable#updateRowId(int, RowId)} with {@code columnIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateRowId(int, RowId)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateRowId(int, RowId)"})
  public void testUpdateRowIdWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.updateRowId(1, null));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateRowId(String, RowId)} with {@code columnLabel}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateRowId(String, RowId)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateRowId(String, RowId)"})
  public void testUpdateRowIdWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateRowId("Column Label", null));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNString(int, String)} with {@code columnIndex}, {@code
   * nString}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNString(int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNString(int, String)"})
  public void testUpdateNStringWithColumnIndexNString() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateNString(1, "N String"));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNString(String, String)} with {@code columnLabel},
   * {@code nString}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNString(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNString(String, String)"})
  public void testUpdateNStringWithColumnLabelNString() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateNString("Column Label", "N String"));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNClob(int, NClob)} with {@code columnIndex}, {@code
   * nClob}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNClob(int, NClob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNClob(int, NClob)"})
  public void testUpdateNClobWithColumnIndexNClob() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateNClob(1, mock(NClob.class)));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNClob(int, Reader)} with {@code columnIndex}, {@code
   * reader}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNClob(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNClob(int, Reader)"})
  public void testUpdateNClobWithColumnIndexReader() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateNClob(1, new StringReader("foo")));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNClob(int, Reader, long)} with {@code columnIndex},
   * {@code reader}, {@code length}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNClob(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNClob(int, Reader, long)"})
  public void testUpdateNClobWithColumnIndexReaderLength() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateNClob(1, new StringReader("foo"), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNClob(String, NClob)} with {@code columnLabel}, {@code
   * nClob}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNClob(String, NClob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNClob(String, NClob)"})
  public void testUpdateNClobWithColumnLabelNClob() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateNClob("Column Label", mock(NClob.class)));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNClob(String, Reader)} with {@code columnLabel}, {@code
   * reader}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNClob(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNClob(String, Reader)"})
  public void testUpdateNClobWithColumnLabelReader() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateNClob("Column Label", new StringReader("foo")));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNClob(String, Reader, long)} with {@code columnLabel},
   * {@code reader}, {@code length}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNClob(String, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNClob(String, Reader, long)"})
  public void testUpdateNClobWithColumnLabelReaderLength() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateNClob("Column Label", new StringReader("foo"), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateSQLXML(int, SQLXML)} with {@code columnIndex}, {@code
   * xmlObject}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateSQLXML(int, SQLXML)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateSQLXML(int, SQLXML)"})
  public void testUpdateSQLXMLWithColumnIndexXmlObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateSQLXML(1, new JDBCSQLXMLImpl(new JDBCContentBytes(null))));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateSQLXML(String, SQLXML)} with {@code columnLabel},
   * {@code xmlObject}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateSQLXML(String, SQLXML)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateSQLXML(String, SQLXML)"})
  public void testUpdateSQLXMLWithColumnLabelXmlObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateSQLXML(
                "Column Label", new JDBCSQLXMLImpl(new JDBCContentBytes(null))));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNCharacterStream(int, Reader)} with {@code
   * columnIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNCharacterStream(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNCharacterStream(int, Reader)"})
  public void testUpdateNCharacterStreamWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateNCharacterStream(1, new StringReader("foo")));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNCharacterStream(int, Reader, long)} with {@code
   * columnIndex}, {@code x}, {@code length}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNCharacterStream(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNCharacterStream(int, Reader, long)"})
  public void testUpdateNCharacterStreamWithColumnIndexXLength() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateNCharacterStream(1, new StringReader("foo"), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNCharacterStream(String, Reader)} with {@code
   * columnLabel}, {@code reader}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNCharacterStream(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNCharacterStream(String, Reader)"})
  public void testUpdateNCharacterStreamWithColumnLabelReader() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateNCharacterStream("Column Label", new StringReader("foo")));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNCharacterStream(String, Reader, long)} with {@code
   * columnLabel}, {@code reader}, {@code length}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNCharacterStream(String, Reader,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNCharacterStream(String, Reader, long)"})
  public void testUpdateNCharacterStreamWithColumnLabelReaderLength() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateNCharacterStream(
                "Column Label", new StringReader("foo"), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getBytes(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code AXAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBytes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] JDBCResultSetCallable.getBytes(int)"})
  public void testGetBytesWithParameterIndex_thenReturnAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBytes(anyInt())).thenReturn("AXAXAXAX".getBytes("UTF-8"));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    byte[] actualBytes = jdbcResultSetCallable.getBytes(1);

    // Assert
    verify(callableStatement).getBytes(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBytes);
  }

  /**
   * Test {@link JDBCResultSetCallable#getBytes(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code AXAXAXAX} Bytes is {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBytes(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"byte[] JDBCResultSetCallable.getBytes(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    byte[] actualBytes = jdbcResultSetCallable.getBytes("Parameter Name");

    // Assert
    verify(callableStatement).getBytes("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), actualBytes);
  }

  /**
   * Test {@link JDBCResultSetCallable#getDate(int, Calendar)} with {@code columnIndex}, {@code
   * cal}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getDate(int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getDate(int, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCResultSetCallable.getDate(int, Calendar)"})
  public void testGetDateWithColumnIndexCal_thenCallsGetDate() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(anyInt())).thenReturn(new Date(1L));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    jdbcResultSetCallable.getDate(1, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getDate(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getDate(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getDate(int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getDate(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCResultSetCallable.getDate(int)"})
  public void testGetDateWithParameterIndex_thenCallsGetDate() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getDate(anyInt())).thenReturn(new Date(1L));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    jdbcResultSetCallable.getDate(1);

    // Assert
    verify(callableStatement).getDate(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getDate(String, Calendar)} with {@code parameterName}, {@code
   * cal}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getDate(String, Calendar)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getDate(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCResultSetCallable.getDate(String, Calendar)"})
  public void testGetDateWithParameterNameCal_thenCallsGetDate() throws SQLException {
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    jdbcResultSetCallable.getDate("Parameter Name", new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getDate(eq("Parameter Name"), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getDate(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getDate(String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getDate(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCResultSetCallable.getDate(String)"})
  public void testGetDateWithParameterName_thenCallsGetDate() throws SQLException {
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    jdbcResultSetCallable.getDate("Parameter Name");

    // Assert
    verify(callableStatement).getDate("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getTime(int, Calendar)} with {@code columnIndex}, {@code
   * cal}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getTime(int, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCResultSetCallable.getTime(int, Calendar)"})
  public void testGetTimeWithColumnIndexCal_thenReturnNull() throws SQLException {
    // Arrange
    JDBCCallableStatementImpl statement = mock(JDBCCallableStatementImpl.class);
    when(statement.getTime(anyInt())).thenReturn(null);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Time actualTime = jdbcResultSetCallable.getTime(1, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(statement).getTime(0);
    assertNull(actualTime);
  }

  /**
   * Test {@link JDBCResultSetCallable#getTime(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getTime(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCResultSetCallable.getTime(int)"})
  public void testGetTimeWithParameterIndex_thenReturnNull() throws SQLException {
    // Arrange
    JDBCCallableStatementImpl statement = mock(JDBCCallableStatementImpl.class);
    when(statement.getTime(anyInt())).thenReturn(null);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Time actualTime = jdbcResultSetCallable.getTime(1);

    // Assert
    verify(statement).getTime(0);
    assertNull(actualTime);
  }

  /**
   * Test {@link JDBCResultSetCallable#getTime(String, Calendar)} with {@code parameterName}, {@code
   * cal}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getTime(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCResultSetCallable.getTime(String, Calendar)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    Time actualTime =
        jdbcResultSetCallable.getTime("Parameter Name", new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getTime(eq("Parameter Name"), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTime);
  }

  /**
   * Test {@link JDBCResultSetCallable#getTime(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getTime(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCResultSetCallable.getTime(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    Time actualTime = jdbcResultSetCallable.getTime("Parameter Name");

    // Assert
    verify(callableStatement).getTime("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTime);
  }

  /**
   * Test {@link JDBCResultSetCallable#getTimestamp(int, Calendar)} with {@code columnIndex}, {@code
   * cal}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getTimestamp(int, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCResultSetCallable.getTimestamp(int, Calendar)"})
  public void testGetTimestampWithColumnIndexCal_thenReturnNull() throws SQLException {
    // Arrange
    JDBCCallableStatementImpl statement = mock(JDBCCallableStatementImpl.class);
    when(statement.getTimestamp(anyInt())).thenReturn(null);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Timestamp actualTimestamp =
        jdbcResultSetCallable.getTimestamp(1, new GregorianCalendar(1, 1, 1));

    // Assert
    verify(statement).getTimestamp(0);
    assertNull(actualTimestamp);
  }

  /**
   * Test {@link JDBCResultSetCallable#getTimestamp(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getTimestamp(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCResultSetCallable.getTimestamp(int)"})
  public void testGetTimestampWithParameterIndex_thenReturnNull() throws SQLException {
    // Arrange
    JDBCCallableStatementImpl statement = mock(JDBCCallableStatementImpl.class);
    when(statement.getTimestamp(anyInt())).thenReturn(null);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Timestamp actualTimestamp = jdbcResultSetCallable.getTimestamp(1);

    // Assert
    verify(statement).getTimestamp(0);
    assertNull(actualTimestamp);
  }

  /**
   * Test {@link JDBCResultSetCallable#getTimestamp(String, Calendar)} with {@code parameterName},
   * {@code cal}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getTimestamp(String, Calendar)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCResultSetCallable.getTimestamp(String, Calendar)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    Timestamp actualTimestamp =
        jdbcResultSetCallable.getTimestamp("Parameter Name", new GregorianCalendar(1, 1, 1));

    // Assert
    verify(callableStatement).getTimestamp(eq("Parameter Name"), isA(Calendar.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTimestamp);
  }

  /**
   * Test {@link JDBCResultSetCallable#getTimestamp(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getTimestamp(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCResultSetCallable.getTimestamp(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    Timestamp actualTimestamp = jdbcResultSetCallable.getTimestamp("Parameter Name");

    // Assert
    verify(callableStatement).getTimestamp("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualTimestamp);
  }

  /**
   * Test {@link JDBCResultSetCallable#getBlob(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@link SerialBlob#SerialBlob(byte[])} with b is {@code AXAXAXAX} Bytes is
   *       {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBlob(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Blob JDBCResultSetCallable.getBlob(int)"})
  public void testGetBlobWithColumnIndex_thenReturnSerialBlobWithBIsAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    SerialBlob serialBlob = new SerialBlob("AXAXAXAX".getBytes("UTF-8"));
    when(callableStatement.getBlob(anyInt())).thenReturn(serialBlob);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Blob actualBlob = jdbcResultSetCallable.getBlob(1);

    // Assert
    verify(callableStatement).getBlob(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertSame(serialBlob, actualBlob);
  }

  /**
   * Test {@link JDBCResultSetCallable#getBlob(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@link SerialBlob#SerialBlob(byte[])} with b is {@code AXAXAXAX} Bytes is
   *       {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBlob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Blob JDBCResultSetCallable.getBlob(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    Blob actualBlob = jdbcResultSetCallable.getBlob("Parameter Name");

    // Assert
    verify(callableStatement).getBlob("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialBlob, actualBlob);
  }

  /**
   * Test {@link JDBCResultSetCallable#getClob(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@link SerialClob#SerialClob(char[])} with ch is {@code AZAZ} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getClob(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Clob JDBCResultSetCallable.getClob(int)"})
  public void testGetClobWithColumnIndex_thenReturnSerialClobWithChIsAzazToCharArray()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    SerialClob serialClob = new SerialClob("AZAZ".toCharArray());
    when(callableStatement.getClob(anyInt())).thenReturn(serialClob);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Clob actualClob = jdbcResultSetCallable.getClob(1);

    // Assert
    verify(callableStatement).getClob(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertSame(serialClob, actualClob);
  }

  /**
   * Test {@link JDBCResultSetCallable#getClob(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@link SerialClob#SerialClob(char[])} with ch is {@code AZAZ} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getClob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Clob JDBCResultSetCallable.getClob(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    Clob actualClob = jdbcResultSetCallable.getClob("Parameter Name");

    // Assert
    verify(callableStatement).getClob("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(serialClob, actualClob);
  }

  /**
   * Test {@link JDBCResultSetCallable#getAsciiStream(int)} with {@code columnIndex}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getAsciiStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream JDBCResultSetCallable.getAsciiStream(int)"})
  public void testGetAsciiStreamWithColumnIndex() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.getAsciiStream(1));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getAsciiStream(String)} with {@code columnLabel}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getAsciiStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream JDBCResultSetCallable.getAsciiStream(String)"})
  public void testGetAsciiStreamWithColumnLabel() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.getAsciiStream("Column Label"));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getUnicodeStream(int)} with {@code columnIndex}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getUnicodeStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream JDBCResultSetCallable.getUnicodeStream(int)"})
  public void testGetUnicodeStreamWithColumnIndex() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.getUnicodeStream(1));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getUnicodeStream(String)} with {@code columnLabel}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getUnicodeStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream JDBCResultSetCallable.getUnicodeStream(String)"})
  public void testGetUnicodeStreamWithColumnLabel() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.getUnicodeStream("Column Label"));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getBinaryStream(int)} with {@code columnIndex}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBinaryStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream JDBCResultSetCallable.getBinaryStream(int)"})
  public void testGetBinaryStreamWithColumnIndex() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.getBinaryStream(1));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getBinaryStream(String)} with {@code columnLabel}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getBinaryStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InputStream JDBCResultSetCallable.getBinaryStream(String)"})
  public void testGetBinaryStreamWithColumnLabel() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.getBinaryStream("Column Label"));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getURL(int)} with {@code parameterIndex}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getURL(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URL JDBCResultSetCallable.getURL(int)"})
  public void testGetURLWithParameterIndex() throws MalformedURLException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getURL(anyInt()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL());
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    URL actualURL = jdbcResultSetCallable.getURL(1);

    // Assert
    verify(callableStatement).getURL(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
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
   * Test {@link JDBCResultSetCallable#getURL(String)} with {@code parameterName}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URL JDBCResultSetCallable.getURL(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    URL actualURL = jdbcResultSetCallable.getURL("https://example.org/example");

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
   * Test {@link JDBCResultSetCallable#getObject(int, Map)} with {@code columnIndex}, {@code map}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getObject(int, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetCallable.getObject(int, Map)"})
  public void testGetObjectWithColumnIndexMap_thenReturnObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(anyInt())).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Object actualObject = jdbcResultSetCallable.getObject(1, new HashMap<>());

    // Assert
    verify(callableStatement).getObject(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCResultSetCallable#getObject(int, Class)} with {@code columnIndex}, {@code
   * type}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getObject(int, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetCallable.getObject(int, Class)"})
  public void testGetObjectWithColumnIndexType_thenReturnObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(anyInt(), eq(Object.class))).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);
    Class<Object> type = Object.class;

    // Act
    Object actualObject = jdbcResultSetCallable.getObject(1, type);

    // Assert
    verify(callableStatement).getObject(eq(0), isA(Class.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCResultSetCallable#getObject(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getObject(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetCallable.getObject(int)"})
  public void testGetObjectWithColumnIndex_thenReturnObject() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getObject(anyInt())).thenReturn("Object");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Object actualObject = jdbcResultSetCallable.getObject(1);

    // Assert
    verify(callableStatement).getObject(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCResultSetCallable#getObject(String, Class)} with {@code columnLabel}, {@code
   * type}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getObject(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetCallable.getObject(String, Class)"})
  public void testGetObjectWithColumnLabelType_thenReturnObject() throws SQLException {
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    Class<Object> type = Object.class;

    // Act
    Object actualObject = jdbcResultSetCallable.getObject("Column Label", type);

    // Assert
    verify(callableStatement).getObject(eq("Column Label"), isA(Class.class));
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCResultSetCallable#getObject(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code Object}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetCallable.getObject(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    Object actualObject = jdbcResultSetCallable.getObject("Parameter Name");

    // Assert
    verify(callableStatement).getObject("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("Object", actualObject);
  }

  /**
   * Test {@link JDBCResultSetCallable#unwrap(Class)}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCResultSetCallable.unwrap(Class)"})
  public void testUnwrap() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    Class<Object> iface = Object.class;

    // Act
    Object actualUnwrapResult = jdbcResultSetCallable.unwrap(iface);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertNull(actualUnwrapResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#isWrapperFor(Class)}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.isWrapperFor(Class)"})
  public void testIsWrapperFor() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    Class<Object> iface = Object.class;

    // Act
    boolean actualIsWrapperForResult = jdbcResultSetCallable.isWrapperFor(iface);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsWrapperForResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#isBeforeFirst()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#isBeforeFirst()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.isBeforeFirst()"})
  public void testIsBeforeFirst_thenReturnFalse() throws SQLException {
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.absolute(1);

    // Act
    boolean actualIsBeforeFirstResult = jdbcResultSetCallable.isBeforeFirst();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertFalse(actualIsBeforeFirstResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#isBeforeFirst()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#isBeforeFirst()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.isBeforeFirst()"})
  public void testIsBeforeFirst_thenReturnTrue() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    boolean actualIsBeforeFirstResult = jdbcResultSetCallable.isBeforeFirst();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualIsBeforeFirstResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#isAfterLast()}.
   *
   * <ul>
   *   <li>Given {@link ParameterMetaData} {@link ParameterMetaData#getParameterMode(int)} return
   *       one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#isAfterLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.isAfterLast()"})
  public void testIsAfterLast_givenParameterMetaDataGetParameterModeReturnOne_thenReturnTrue()
      throws SQLException {
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.absolute(1);

    // Act
    boolean actualIsAfterLastResult = jdbcResultSetCallable.isAfterLast();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualIsAfterLastResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#isAfterLast()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#isAfterLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.isAfterLast()"})
  public void testIsAfterLast_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    boolean actualIsAfterLastResult = jdbcResultSetCallable.isAfterLast();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsAfterLastResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#isFirst()}.
   *
   * <ul>
   *   <li>Given {@link ParameterMetaData} {@link ParameterMetaData#getParameterMode(int)} return
   *       one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#isFirst()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.isFirst()"})
  public void testIsFirst_givenParameterMetaDataGetParameterModeReturnOne_thenReturnTrue()
      throws SQLException {
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.relative(1);

    // Act
    boolean actualIsFirstResult = jdbcResultSetCallable.isFirst();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualIsFirstResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#isFirst()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#isFirst()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.isFirst()"})
  public void testIsFirst_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    boolean actualIsFirstResult = jdbcResultSetCallable.isFirst();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsFirstResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#isLast()}.
   *
   * <ul>
   *   <li>Given {@link ParameterMetaData} {@link ParameterMetaData#getParameterMode(int)} return
   *       one.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#isLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.isLast()"})
  public void testIsLast_givenParameterMetaDataGetParameterModeReturnOne_thenReturnTrue()
      throws SQLException {
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.relative(1);

    // Act
    boolean actualIsLastResult = jdbcResultSetCallable.isLast();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualIsLastResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#isLast()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#isLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.isLast()"})
  public void testIsLast_thenReturnFalse() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    boolean actualIsLastResult = jdbcResultSetCallable.isLast();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualIsLastResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#getRef(int)} with {@code columnIndex}.
   *
   * <ul>
   *   <li>Then return {@link SerialRef#SerialRef(Ref)} with ref is {@link SerialRef}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getRef(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Ref JDBCResultSetCallable.getRef(int)"})
  public void testGetRefWithColumnIndex_thenReturnSerialRefWithRefIsSerialRef()
      throws SQLException {
    // Arrange
    SerialRef ref = mock(SerialRef.class);
    when(ref.getBaseTypeName()).thenReturn("Base Type Name");
    SerialRef serialRef = new SerialRef(ref);

    JDBCCallableStatementImpl statement = mock(JDBCCallableStatementImpl.class);
    when(statement.getRef(anyInt())).thenReturn(serialRef);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Ref actualRef = jdbcResultSetCallable.getRef(1);

    // Assert
    verify(ref, atLeast(1)).getBaseTypeName();
    verify(statement).getRef(0);
    assertSame(serialRef, actualRef);
  }

  /**
   * Test {@link JDBCResultSetCallable#getRef(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@link SerialRef#SerialRef(Ref)} with ref is {@link SerialRef}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getRef(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Ref JDBCResultSetCallable.getRef(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    Ref actualRef = jdbcResultSetCallable.getRef("Parameter Name");

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
   * Test {@link JDBCResultSetCallable#findColumn(String)}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#findColumn(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetCallable.findColumn(String)"})
  public void testFindColumn() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    int actualFindColumnResult = jdbcResultSetCallable.findColumn("Column Label");

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(-1, actualFindColumnResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#getArray(int)} with {@code columnIndex}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getArray(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Array JDBCResultSetCallable.getArray(int)"})
  public void testGetArrayWithColumnIndex() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    Object[] items = new Object[] {"Items"};
    JDBCArrayImpl jdbcArrayImpl = new JDBCArrayImpl("Type Name", 1, items);
    when(callableStatement.getArray(anyInt())).thenReturn(jdbcArrayImpl);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Array actualArray = jdbcResultSetCallable.getArray(1);

    // Assert
    verify(callableStatement).getArray(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertSame(jdbcArrayImpl, actualArray);
  }

  /**
   * Test {@link JDBCResultSetCallable#getArray(String)} with {@code parameterName}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getArray(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Array JDBCResultSetCallable.getArray(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    Array actualArray = jdbcResultSetCallable.getArray("Parameter Name");

    // Assert
    verify(callableStatement).getArray("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(jdbcArrayImpl, actualArray);
  }

  /**
   * Test {@link JDBCResultSetCallable#afterLast()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#afterLast()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.afterLast()"})
  public void testAfterLast() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    jdbcResultSetCallable.afterLast();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#last()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#last()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.last()"})
  public void testLast() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    boolean actualLastResult = jdbcResultSetCallable.last();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertTrue(actualLastResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#getFetchDirection()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getFetchDirection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetCallable.getFetchDirection()"})
  public void testGetFetchDirection() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    int actualFetchDirection = jdbcResultSetCallable.getFetchDirection();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1000, actualFetchDirection);
  }

  /**
   * Test {@link JDBCResultSetCallable#getType()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetCallable.getType()"})
  public void testGetType() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    int actualType = jdbcResultSetCallable.getType();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1005, actualType);
  }

  /**
   * Test {@link JDBCResultSetCallable#getConcurrency()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getConcurrency()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCResultSetCallable.getConcurrency()"})
  public void testGetConcurrency() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    int actualConcurrency = jdbcResultSetCallable.getConcurrency();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1007, actualConcurrency);
  }

  /**
   * Test {@link JDBCResultSetCallable#rowUpdated()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#rowUpdated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.rowUpdated()"})
  public void testRowUpdated() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    boolean actualRowUpdatedResult = jdbcResultSetCallable.rowUpdated();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualRowUpdatedResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#rowInserted()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#rowInserted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.rowInserted()"})
  public void testRowInserted() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    boolean actualRowInsertedResult = jdbcResultSetCallable.rowInserted();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualRowInsertedResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#rowDeleted()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#rowDeleted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCResultSetCallable.rowDeleted()"})
  public void testRowDeleted() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    boolean actualRowDeletedResult = jdbcResultSetCallable.rowDeleted();

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertFalse(actualRowDeletedResult);
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNull(int)} with {@code columnIndex}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNull(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNull(int)"})
  public void testUpdateNullWithColumnIndex() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.updateNull(1));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateNull(String)} with {@code columnLabel}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateNull(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateNull(String)"})
  public void testUpdateNullWithColumnLabel() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateNull("Column Label"));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBoolean(int, boolean)} with {@code columnIndex}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBoolean(int, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBoolean(int, boolean)"})
  public void testUpdateBooleanWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.updateBoolean(1, true));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBoolean(String, boolean)} with {@code columnLabel},
   * {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBoolean(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBoolean(String, boolean)"})
  public void testUpdateBooleanWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateBoolean("Column Label", true));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateByte(int, byte)} with {@code columnIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateByte(int, byte)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateByte(int, byte)"})
  public void testUpdateByteWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateByte(1, (byte) 'A'));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateByte(String, byte)} with {@code columnLabel}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateByte(String, byte)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateByte(String, byte)"})
  public void testUpdateByteWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateByte("Column Label", (byte) 'A'));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateShort(int, short)} with {@code columnIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateShort(int, short)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateShort(int, short)"})
  public void testUpdateShortWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateShort(1, (short) 1));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateShort(String, short)} with {@code columnLabel}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateShort(String, short)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateShort(String, short)"})
  public void testUpdateShortWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateShort("Column Label", (short) 1));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateInt(int, int)} with {@code columnIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateInt(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateInt(int, int)"})
  public void testUpdateIntWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.updateInt(1, 2));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateInt(String, int)} with {@code columnLabel}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateInt(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateInt(String, int)"})
  public void testUpdateIntWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateInt("Column Label", 2));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateLong(int, long)} with {@code columnIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateLong(int, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateLong(int, long)"})
  public void testUpdateLongWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.updateLong(1, 1L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateLong(String, long)} with {@code columnLabel}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateLong(String, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateLong(String, long)"})
  public void testUpdateLongWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateLong("Column Label", 1L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateFloat(int, float)} with {@code columnIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateFloat(int, float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateFloat(int, float)"})
  public void testUpdateFloatWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.updateFloat(1, 10.0f));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateFloat(String, float)} with {@code columnLabel}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateFloat(String, float)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateFloat(String, float)"})
  public void testUpdateFloatWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateFloat("Column Label", 10.0f));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateDouble(int, double)} with {@code columnIndex}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateDouble(int, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateDouble(int, double)"})
  public void testUpdateDoubleWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.updateDouble(1, 2.0d));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateDouble(String, double)} with {@code columnLabel},
   * {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateDouble(String, double)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateDouble(String, double)"})
  public void testUpdateDoubleWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateDouble("Column Label", 2.0d));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBigDecimal(int, BigDecimal)} with {@code columnIndex},
   * {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBigDecimal(int, BigDecimal)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBigDecimal(int, BigDecimal)"})
  public void testUpdateBigDecimalWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateBigDecimal(1, new BigDecimal("2.3")));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBigDecimal(String, BigDecimal)} with {@code
   * columnLabel}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBigDecimal(String, BigDecimal)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBigDecimal(String, BigDecimal)"})
  public void testUpdateBigDecimalWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateBigDecimal("2.3", new BigDecimal("2.3")));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateString(int, String)} with {@code columnIndex}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateString(int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateString(int, String)"})
  public void testUpdateStringWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.updateString(1, "foo"));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateString(String, String)} with {@code columnLabel},
   * {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateString(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateString(String, String)"})
  public void testUpdateStringWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateString("Column Label", "foo"));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBytes(int, byte[])} with {@code columnIndex}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBytes(int, byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBytes(int, byte[])"})
  public void testUpdateBytesWithColumnIndexX() throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateBytes(1, "AXAXAXAX".getBytes("UTF-8")));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBytes(String, byte[])} with {@code columnLabel}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBytes(String, byte[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBytes(String, byte[])"})
  public void testUpdateBytesWithColumnLabelX() throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateBytes("Column Label", "AXAXAXAX".getBytes("UTF-8")));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateDate(int, Date)} with {@code columnIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateDate(int, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateDate(int, Date)"})
  public void testUpdateDateWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateDate(1, new Date(1L)));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateDate(String, Date)} with {@code columnLabel}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateDate(String, Date)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateDate(String, Date)"})
  public void testUpdateDateWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateDate("Column Label", new Date(1L)));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateTime(int, Time)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLFeatureNotSupportedException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateTime(int, Time)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateTime(int, Time)"})
  public void testUpdateTimeWithColumnIndexX_thenThrowSQLFeatureNotSupportedException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.updateTime(1, null));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateTime(String, Time)} with {@code columnLabel}, {@code
   * x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLFeatureNotSupportedException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateTime(String, Time)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateTime(String, Time)"})
  public void testUpdateTimeWithColumnLabelX_thenThrowSQLFeatureNotSupportedException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateTime("Column Label", null));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateTimestamp(int, Timestamp)} with {@code columnIndex},
   * {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLFeatureNotSupportedException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateTimestamp(int, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateTimestamp(int, Timestamp)"})
  public void testUpdateTimestampWithColumnIndexX_thenThrowSQLFeatureNotSupportedException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateTimestamp(1, null));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateTimestamp(String, Timestamp)} with {@code columnLabel},
   * {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLFeatureNotSupportedException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateTimestamp(String, Timestamp)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateTimestamp(String, Timestamp)"})
  public void testUpdateTimestampWithColumnLabelX_thenThrowSQLFeatureNotSupportedException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateTimestamp("Column Label", null));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateAsciiStream(int, InputStream)} with {@code int}, {@code
   * InputStream}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateAsciiStream(int, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateAsciiStream(int, InputStream)"})
  public void testUpdateAsciiStreamWithIntInputStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateAsciiStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateAsciiStream(int, InputStream, int)} with {@code int},
   * {@code InputStream}, {@code int}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateAsciiStream(int, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateAsciiStream(int, InputStream, int)"})
  public void testUpdateAsciiStreamWithIntInputStreamInt()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateAsciiStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateAsciiStream(int, InputStream, long)} with {@code int},
   * {@code InputStream}, {@code long}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateAsciiStream(int, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateAsciiStream(int, InputStream, long)"})
  public void testUpdateAsciiStreamWithIntInputStreamLong()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateAsciiStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateAsciiStream(String, InputStream)} with {@code String},
   * {@code InputStream}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateAsciiStream(String, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateAsciiStream(String, InputStream)"})
  public void testUpdateAsciiStreamWithStringInputStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateAsciiStream(
                "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateAsciiStream(String, InputStream, int)} with {@code
   * String}, {@code InputStream}, {@code int}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateAsciiStream(String, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateAsciiStream(String, InputStream, int)"})
  public void testUpdateAsciiStreamWithStringInputStreamInt()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateAsciiStream(
                "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateAsciiStream(String, InputStream, long)} with {@code
   * String}, {@code InputStream}, {@code long}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateAsciiStream(String, InputStream,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateAsciiStream(String, InputStream, long)"})
  public void testUpdateAsciiStreamWithStringInputStreamLong()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateAsciiStream(
                "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBinaryStream(int, InputStream)} with {@code int},
   * {@code InputStream}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBinaryStream(int, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBinaryStream(int, InputStream)"})
  public void testUpdateBinaryStreamWithIntInputStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateBinaryStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBinaryStream(int, InputStream, int)} with {@code int},
   * {@code InputStream}, {@code int}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBinaryStream(int, InputStream, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBinaryStream(int, InputStream, int)"})
  public void testUpdateBinaryStreamWithIntInputStreamInt()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateBinaryStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBinaryStream(int, InputStream, long)} with {@code int},
   * {@code InputStream}, {@code long}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBinaryStream(int, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBinaryStream(int, InputStream, long)"})
  public void testUpdateBinaryStreamWithIntInputStreamLong()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateBinaryStream(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBinaryStream(String, InputStream)} with {@code String},
   * {@code InputStream}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBinaryStream(String, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBinaryStream(String, InputStream)"})
  public void testUpdateBinaryStreamWithStringInputStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateBinaryStream(
                "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBinaryStream(String, InputStream, int)} with {@code
   * String}, {@code InputStream}, {@code int}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBinaryStream(String, InputStream,
   * int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBinaryStream(String, InputStream, int)"})
  public void testUpdateBinaryStreamWithStringInputStreamInt()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateBinaryStream(
                "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBinaryStream(String, InputStream, long)} with {@code
   * String}, {@code InputStream}, {@code long}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBinaryStream(String, InputStream,
   * long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBinaryStream(String, InputStream, long)"})
  public void testUpdateBinaryStreamWithStringInputStreamLong()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateBinaryStream(
                "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateCharacterStream(int, Reader)} with {@code int}, {@code
   * Reader}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateCharacterStream(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateCharacterStream(int, Reader)"})
  public void testUpdateCharacterStreamWithIntReader() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateCharacterStream(1, new StringReader("foo")));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateCharacterStream(int, Reader, int)} with {@code int},
   * {@code Reader}, {@code int}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateCharacterStream(int, Reader, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateCharacterStream(int, Reader, int)"})
  public void testUpdateCharacterStreamWithIntReaderInt() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateCharacterStream(1, new StringReader("foo"), 3));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateCharacterStream(int, Reader, long)} with {@code int},
   * {@code Reader}, {@code long}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateCharacterStream(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateCharacterStream(int, Reader, long)"})
  public void testUpdateCharacterStreamWithIntReaderLong() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateCharacterStream(1, new StringReader("foo"), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateCharacterStream(String, Reader)} with {@code String},
   * {@code Reader}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateCharacterStream(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateCharacterStream(String, Reader)"})
  public void testUpdateCharacterStreamWithStringReader() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateCharacterStream("Column Label", new StringReader("foo")));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateCharacterStream(String, Reader, int)} with {@code
   * String}, {@code Reader}, {@code int}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateCharacterStream(String, Reader, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateCharacterStream(String, Reader, int)"})
  public void testUpdateCharacterStreamWithStringReaderInt() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateCharacterStream(
                "Column Label", new StringReader("foo"), 3));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateCharacterStream(String, Reader, long)} with {@code
   * String}, {@code Reader}, {@code long}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateCharacterStream(String, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateCharacterStream(String, Reader, long)"})
  public void testUpdateCharacterStreamWithStringReaderLong() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateCharacterStream(
                "Column Label", new StringReader("foo"), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateObject(int, Object)} with {@code columnIndex}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateObject(int, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateObject(int, Object)"})
  public void testUpdateObjectWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.updateObject(1, "42"));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateObject(int, Object, int)} with {@code columnIndex},
   * {@code x}, {@code scaleOrLength}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateObject(int, Object, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateObject(int, Object, int)"})
  public void testUpdateObjectWithColumnIndexXScaleOrLength() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateObject(1, "42", 3));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateObject(String, Object)} with {@code columnLabel},
   * {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateObject(String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateObject(String, Object)"})
  public void testUpdateObjectWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateObject("Column Label", "42"));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateObject(String, Object, int)} with {@code columnLabel},
   * {@code x}, {@code scaleOrLength}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateObject(String, Object, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateObject(String, Object, int)"})
  public void testUpdateObjectWithColumnLabelXScaleOrLength() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateObject("Column Label", "42", 3));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#insertRow()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#insertRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.insertRow()"})
  public void testInsertRow() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.insertRow());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateRow()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateRow()"})
  public void testUpdateRow() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.updateRow());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#deleteRow()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#deleteRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.deleteRow()"})
  public void testDeleteRow() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.deleteRow());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#refreshRow()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#refreshRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.refreshRow()"})
  public void testRefreshRow() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.refreshRow());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#cancelRowUpdates()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#cancelRowUpdates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.cancelRowUpdates()"})
  public void testCancelRowUpdates() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.cancelRowUpdates());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#moveToInsertRow()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#moveToInsertRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.moveToInsertRow()"})
  public void testMoveToInsertRow() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.moveToInsertRow());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#moveToCurrentRow()}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#moveToCurrentRow()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.moveToCurrentRow()"})
  public void testMoveToCurrentRow() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class, () -> jdbcResultSetCallable.moveToCurrentRow());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateRef(int, Ref)} with {@code columnIndex}, {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLFeatureNotSupportedException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateRef(int, Ref)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateRef(int, Ref)"})
  public void testUpdateRefWithColumnIndexX_thenThrowSQLFeatureNotSupportedException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateRef(1, mock(SerialRef.class)));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateRef(String, Ref)} with {@code columnLabel}, {@code x}.
   *
   * <ul>
   *   <li>Then throw {@link SQLFeatureNotSupportedException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateRef(String, Ref)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateRef(String, Ref)"})
  public void testUpdateRefWithColumnLabelX_thenThrowSQLFeatureNotSupportedException()
      throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateRef("Column Label", mock(SerialRef.class)));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBlob(int, InputStream)} with {@code columnIndex},
   * {@code inputStream}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBlob(int, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBlob(int, InputStream)"})
  public void testUpdateBlobWithColumnIndexInputStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateBlob(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBlob(int, InputStream, long)} with {@code columnIndex},
   * {@code inputStream}, {@code length}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBlob(int, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBlob(int, InputStream, long)"})
  public void testUpdateBlobWithColumnIndexInputStreamLength()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateBlob(
                1, new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBlob(int, Blob)} with {@code columnIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBlob(int, Blob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBlob(int, Blob)"})
  public void testUpdateBlobWithColumnIndexX() throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateBlob(1, new SerialBlob("AXAXAXAX".getBytes("UTF-8"))));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBlob(String, InputStream)} with {@code columnLabel},
   * {@code inputStream}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBlob(String, InputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBlob(String, InputStream)"})
  public void testUpdateBlobWithColumnLabelInputStream()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateBlob(
                "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"))));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBlob(String, InputStream, long)} with {@code
   * columnLabel}, {@code inputStream}, {@code length}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBlob(String, InputStream, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBlob(String, InputStream, long)"})
  public void testUpdateBlobWithColumnLabelInputStreamLength()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateBlob(
                "Column Label", new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateBlob(String, Blob)} with {@code columnLabel}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateBlob(String, Blob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateBlob(String, Blob)"})
  public void testUpdateBlobWithColumnLabelX() throws UnsupportedEncodingException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateBlob(
                "Column Label", new SerialBlob("AXAXAXAX".getBytes("UTF-8"))));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateClob(int, Reader)} with {@code columnIndex}, {@code
   * reader}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateClob(int, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateClob(int, Reader)"})
  public void testUpdateClobWithColumnIndexReader() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateClob(1, new StringReader("foo")));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateClob(int, Reader, long)} with {@code columnIndex},
   * {@code reader}, {@code length}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateClob(int, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateClob(int, Reader, long)"})
  public void testUpdateClobWithColumnIndexReaderLength() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateClob(1, new StringReader("foo"), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateClob(int, Clob)} with {@code columnIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateClob(int, Clob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateClob(int, Clob)"})
  public void testUpdateClobWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateClob(1, new SerialClob("AZAZ".toCharArray())));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateClob(String, Reader)} with {@code columnLabel}, {@code
   * reader}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateClob(String, Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateClob(String, Reader)"})
  public void testUpdateClobWithColumnLabelReader() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateClob("Column Label", new StringReader("foo")));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateClob(String, Reader, long)} with {@code columnLabel},
   * {@code reader}, {@code length}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateClob(String, Reader, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateClob(String, Reader, long)"})
  public void testUpdateClobWithColumnLabelReaderLength() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateClob("Column Label", new StringReader("foo"), 3L));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateClob(String, Clob)} with {@code columnLabel}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateClob(String, Clob)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateClob(String, Clob)"})
  public void testUpdateClobWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateClob("Column Label", new SerialClob("AZAZ".toCharArray())));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateArray(int, Array)} with {@code columnIndex}, {@code x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateArray(int, Array)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateArray(int, Array)"})
  public void testUpdateArrayWithColumnIndexX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    Object[] items = new Object[] {"Items"};

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () -> jdbcResultSetCallable.updateArray(1, new JDBCArrayImpl("Type Name", 1, items)));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#updateArray(String, Array)} with {@code columnLabel}, {@code
   * x}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#updateArray(String, Array)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCResultSetCallable.updateArray(String, Array)"})
  public void testUpdateArrayWithColumnLabelX() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    Object[] items = new Object[] {"Items"};

    // Act and Assert
    assertThrows(
        SQLFeatureNotSupportedException.class,
        () ->
            jdbcResultSetCallable.updateArray(
                "Column Label", new JDBCArrayImpl("Type Name", 1, items)));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getNClob(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getNClob(int)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getNClob(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NClob JDBCResultSetCallable.getNClob(int)"})
  public void testGetNClobWithParameterIndex_thenCallsGetNClob() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNClob(anyInt())).thenReturn(mock(NClob.class));
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    jdbcResultSetCallable.getNClob(1);

    // Assert
    verify(callableStatement).getNClob(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getNClob(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then calls {@link CallableStatement#getNClob(String)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getNClob(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NClob JDBCResultSetCallable.getNClob(String)"})
  public void testGetNClobWithParameterName_thenCallsGetNClob() throws SQLException {
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    jdbcResultSetCallable.getNClob("Parameter Name");

    // Assert
    verify(callableStatement).getNClob("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#getSQLXML(int)} with {@code parameterIndex}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getSQLXML(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLXML JDBCResultSetCallable.getSQLXML(int)"})
  public void testGetSQLXMLWithParameterIndex() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.isThreadSafeDriver()).thenReturn(true);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getDriver()).thenReturn(dbpDriver);

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getName()).thenReturn("Name");
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCRemoteInstance instance = mock(JDBCRemoteInstance.class);
    when(instance.getDataSource()).thenReturn(jdbcDataSource);
    JDBCExecutionContext executionContext = new JDBCExecutionContext(instance, "Purpose");
    JDBCSQLXMLImpl jdbcsqlxmlImpl = new JDBCSQLXMLImpl(new JDBCContentBytes(executionContext));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getSQLXML(anyInt())).thenReturn(jdbcsqlxmlImpl);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    SQLXML actualSQLXML = jdbcResultSetCallable.getSQLXML(1);

    // Assert
    verify(callableStatement).getSQLXML(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(dbpDataSourceContainer).getDriver();
    verify(dbpDriver).isThreadSafeDriver();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    verify(jdbcDataSource).getContainer();
    verify(jdbcDataSource).getName();
    verify(instance, atLeast(1)).getDataSource();
    assertSame(jdbcsqlxmlImpl, actualSQLXML);
  }

  /**
   * Test {@link JDBCResultSetCallable#getSQLXML(String)} with {@code parameterName}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getSQLXML(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLXML JDBCResultSetCallable.getSQLXML(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    SQLXML actualSQLXML = jdbcResultSetCallable.getSQLXML("Parameter Name");

    // Assert
    verify(callableStatement).getSQLXML("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertSame(jdbcsqlxmlImpl, actualSQLXML);
  }

  /**
   * Test {@link JDBCResultSetCallable#getNString(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return {@code N String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getNString(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetCallable.getNString(int)"})
  public void testGetNStringWithParameterIndex_thenReturnNString() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getNString(anyInt())).thenReturn("N String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    String actualNString = jdbcResultSetCallable.getNString(1);

    // Assert
    verify(callableStatement).getNString(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals("N String", actualNString);
  }

  /**
   * Test {@link JDBCResultSetCallable#getNString(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return {@code N String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getNString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCResultSetCallable.getNString(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    String actualNString = jdbcResultSetCallable.getNString("Parameter Name");

    // Assert
    verify(callableStatement).getNString("Parameter Name");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("N String", actualNString);
  }

  /**
   * Test {@link JDBCResultSetCallable#getNCharacterStream(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getNCharacterStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCResultSetCallable.getNCharacterStream(int)"})
  public void testGetNCharacterStreamWithParameterIndex_thenReturnReady()
      throws IOException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    StringReader stringReader = new StringReader("foo");
    when(callableStatement.getNCharacterStream(anyInt())).thenReturn(stringReader);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Reader actualNCharacterStream = jdbcResultSetCallable.getNCharacterStream(1);

    // Assert
    verify(callableStatement).getNCharacterStream(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualNCharacterStream.ready());
    assertSame(stringReader, actualNCharacterStream);
  }

  /**
   * Test {@link JDBCResultSetCallable#getNCharacterStream(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getNCharacterStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCResultSetCallable.getNCharacterStream(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    Reader actualNCharacterStream = jdbcResultSetCallable.getNCharacterStream("Parameter Name");

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
   * Test {@link JDBCResultSetCallable#getCharacterStream(int)} with {@code parameterIndex}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getCharacterStream(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCResultSetCallable.getCharacterStream(int)"})
  public void testGetCharacterStreamWithParameterIndex_thenReturnReady()
      throws IOException, SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    StringReader stringReader = new StringReader("foo");
    when(callableStatement.getCharacterStream(anyInt())).thenReturn(stringReader);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Act
    Reader actualCharacterStream = jdbcResultSetCallable.getCharacterStream(1);

    // Assert
    verify(callableStatement).getCharacterStream(0);
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertTrue(actualCharacterStream.ready());
    assertSame(stringReader, actualCharacterStream);
  }

  /**
   * Test {@link JDBCResultSetCallable#getCharacterStream(String)} with {@code parameterName}.
   *
   * <ul>
   *   <li>Then return ready.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#getCharacterStream(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Reader JDBCResultSetCallable.getCharacterStream(String)"})
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    Reader actualCharacterStream = jdbcResultSetCallable.getCharacterStream("Parameter Name");

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
   * Test {@link JDBCResultSetCallable#addColumn(String, DBPDataKind)} with {@code label}, {@code
   * dataKind}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#addColumn(String, DBPDataKind)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.DBCAttributeMetaData JDBCResultSetCallable.addColumn(String, DBPDataKind)"
  })
  public void testAddColumnWithLabelDataKind() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#addColumn(String, DBPDataKind, int, int)} with {@code label},
   * {@code dataKind}, {@code localIndex}, {@code originalIndex}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#addColumn(String, DBPDataKind, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.DBCAttributeMetaData JDBCResultSetCallable.addColumn(String, DBPDataKind, int, int)"
  })
  public void testAddColumnWithLabelDataKindLocalIndexOriginalIndex() throws SQLException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    jdbcResultSetCallable.addColumn("Label", DBPDataKind.BOOLEAN, 1, 1);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#addColumn(String, DBSTypedObject, int, int)} with {@code
   * label}, {@code typedObject}, {@code localIndex}, {@code originalIndex}.
   *
   * <p>Method under test: {@link JDBCResultSetCallable#addColumn(String, DBSTypedObject, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.DBCAttributeMetaData JDBCResultSetCallable.addColumn(String, DBSTypedObject, int, int)"
  })
  public void testAddColumnWithLabelTypedObjectLocalIndexOriginalIndex() throws SQLException {
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    jdbcResultSetCallable.addColumn("Label", new SimpleTypedObject("Type Name"), 1, 1);

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
  }

  /**
   * Test {@link JDBCResultSetCallable#addColumn(String, DBSTypedObject)} with {@code label}, {@code
   * typedObject}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCResultSetCallable#addColumn(String, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.DBCAttributeMetaData JDBCResultSetCallable.addColumn(String, DBSTypedObject)"
  })
  public void testAddColumnWithLabelTypedObject_thenCallsGetParameterCount() throws SQLException {
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

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable jdbcResultSetCallable =
        new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    jdbcResultSetCallable.addColumn("Label", new SimpleTypedObject("Type Name"));

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
  }
}
