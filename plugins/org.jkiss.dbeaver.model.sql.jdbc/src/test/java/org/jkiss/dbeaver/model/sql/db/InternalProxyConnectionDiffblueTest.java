package org.jkiss.dbeaver.model.sql.db;

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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import javax.sql.rowset.serial.SerialArray;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;
import javax.sql.rowset.serial.SerialStruct;
import org.jkiss.dbeaver.model.connection.InternalDatabaseConfig;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class InternalProxyConnectionDiffblueTest {
  /**
   * Test {@link InternalProxyConnection#InternalProxyConnection(Connection,
   * InternalDatabaseConfig)}.
   *
   * <p>Method under test: {@link InternalProxyConnection#InternalProxyConnection(Connection,
   * InternalDatabaseConfig)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.<init>(Connection, InternalDatabaseConfig)"})
  public void testNewInternalProxyConnection() throws SQLException {
    // Arrange and Act
    InternalProxyConnection actualInternalProxyConnection =
        new InternalProxyConnection(mock(Connection.class), mock(InternalDatabaseConfig.class));

    // Assert
    assertNull(actualInternalProxyConnection.getCatalog());
    assertNull(actualInternalProxyConnection.getSchema());
    assertNull(actualInternalProxyConnection.getMetaData());
    assertNull(actualInternalProxyConnection.getWarnings());
    assertNull(actualInternalProxyConnection.getClientInfo());
    assertEquals(0, actualInternalProxyConnection.getHoldability());
    assertEquals(0, actualInternalProxyConnection.getNetworkTimeout());
    assertEquals(0, actualInternalProxyConnection.getTransactionIsolation());
    assertFalse(actualInternalProxyConnection.getAutoCommit());
    assertFalse(actualInternalProxyConnection.isClosed());
    assertFalse(actualInternalProxyConnection.isReadOnly());
    assertTrue(actualInternalProxyConnection.getTypeMap().isEmpty());
  }

  /**
   * Test {@link InternalProxyConnection#createStatement(int, int)} with {@code resultSetType},
   * {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#createStatement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Statement InternalProxyConnection.createStatement(int, int)"})
  public void testCreateStatementWithResultSetTypeResultSetConcurrency() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createStatement(anyInt(), anyInt())).thenReturn(mock(Statement.class));
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    Statement actualCreateStatementResult = internalProxyConnection.createStatement(1, 1);

    // Assert
    verify(connection).createStatement(1, 1);
    assertEquals(0L, actualCreateStatementResult.getLargeMaxRows());
  }

  /**
   * Test {@link InternalProxyConnection#createStatement(int, int, int)} with {@code resultSetType},
   * {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#createStatement(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Statement InternalProxyConnection.createStatement(int, int, int)"})
  public void testCreateStatementWithResultSetTypeResultSetConcurrencyResultSetHoldability()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createStatement(anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(Statement.class));
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    Statement actualCreateStatementResult = internalProxyConnection.createStatement(1, 1, 1);

    // Assert
    verify(connection).createStatement(1, 1, 1);
    assertEquals(0L, actualCreateStatementResult.getLargeMaxRows());
  }

  /**
   * Test {@link InternalProxyConnection#createStatement(int, int, int)} with {@code resultSetType},
   * {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#createStatement(int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Statement InternalProxyConnection.createStatement(int, int, int)"})
  public void testCreateStatementWithResultSetTypeResultSetConcurrencyResultSetHoldability2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createStatement(anyInt(), anyInt(), anyInt())).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.createStatement(1, 1, 1));
    verify(connection).createStatement(1, 1, 1);
  }

  /**
   * Test {@link InternalProxyConnection#createStatement(int, int)} with {@code resultSetType},
   * {@code resultSetConcurrency}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createStatement(int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Statement InternalProxyConnection.createStatement(int, int)"})
  public void testCreateStatementWithResultSetTypeResultSetConcurrency_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createStatement(anyInt(), anyInt())).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.createStatement(1, 1));
    verify(connection).createStatement(1, 1);
  }

  /**
   * Test {@link InternalProxyConnection#createStatement()}.
   *
   * <ul>
   *   <li>Then return LargeMaxRows is zero.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Statement InternalProxyConnection.createStatement()"})
  public void testCreateStatement_thenReturnLargeMaxRowsIsZero() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createStatement()).thenReturn(mock(Statement.class));
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    Statement actualCreateStatementResult = internalProxyConnection.createStatement();

    // Assert
    verify(connection).createStatement();
    assertEquals(0L, actualCreateStatementResult.getLargeMaxRows());
  }

  /**
   * Test {@link InternalProxyConnection#createStatement()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Statement InternalProxyConnection.createStatement()"})
  public void testCreateStatement_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createStatement()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.createStatement());
    verify(connection).createStatement();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}");

    // Assert
    verify(connection).prepareStatement("");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql2() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}");

    // Assert
    verify(connection).prepareStatement("\\");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql3() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}");

    // Assert
    verify(connection).prepareStatement("{table_prefix}.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql4() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}");

    // Assert
    verify(connection).prepareStatement("{table_prefix}.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql5() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}");

    // Assert
    verify(connection).prepareStatement("\\{table_prefix}.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql6() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}");

    // Assert
    verify(connection).prepareStatement("\\{table_prefix}.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql7() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).prepareStatement("{table_prefix}.{table_prefix}.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql8() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).prepareStatement("{table_prefix}.{table_prefix}.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql9() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).prepareStatement("");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("Sql", 1);

    // Assert
    verify(connection).prepareStatement("Sql", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys2() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1);

    // Assert
    verify(connection).prepareStatement("Schema.", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys3() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1);

    // Assert
    verify(connection).prepareStatement("\\Schema.", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys4() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1);

    // Assert
    verify(connection).prepareStatement("", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys5() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1);

    // Assert
    verify(connection).prepareStatement("\\", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys6() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1);

    // Assert
    verify(connection).prepareStatement("{table_prefix}.", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys7() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1);

    // Assert
    verify(connection).prepareStatement("{table_prefix}.", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys8() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1);

    // Assert
    verify(connection).prepareStatement("foo.", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys9() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1);

    // Assert
    verify(connection).prepareStatement("42.", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys10() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1);

    // Assert
    verify(connection).prepareStatement("\\{table_prefix}.", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys11() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1);

    // Assert
    verify(connection).prepareStatement("\\{table_prefix}.", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys12() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1);

    // Assert
    verify(connection).prepareStatement("\\foo.", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys13() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1);

    // Assert
    verify(connection).prepareStatement("\\42.", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenThrow(new SQLException());

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.prepareStatement("Sql", 1));
    verify(connection).prepareStatement("Sql", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int)} with {@code sql}, {@code
   * autoGeneratedKeys}.
   *
   * <ul>
   *   <li>When {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int)"})
  public void testPrepareStatementWithSqlAutoGeneratedKeys_whenSql() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("Sql", 1);

    // Assert
    verify(connection).prepareStatement("Sql", 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("Sql", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("Sql"), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes2() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("Schema."), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes3() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("\\Schema."), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes4() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq(""), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes5() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("\\"), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes6() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("{table_prefix}."), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes7() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("{table_prefix}."), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes8() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("foo."), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes9() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("42."), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes10() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("\\{table_prefix}."), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes11() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("\\{table_prefix}."), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes12() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("\\foo."), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes13() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("\\42."), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenThrow(new SQLException());

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> internalProxyConnection.prepareStatement("Sql", new int[] {1, -1, 1, -1}));
    verify(connection).prepareStatement(eq("Sql"), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int[])} with {@code sql}, {@code
   * columnIndexes}.
   *
   * <ul>
   *   <li>When {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String, int[])"})
  public void testPrepareStatementWithSqlColumnIndexes_whenSql() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<int[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("Sql", new int[] {1, -1, 1, -1});

    // Assert
    verify(connection).prepareStatement(eq("Sql"), isA(int[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("Sql", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("Sql"), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames2() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("Schema."), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames3() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("\\Schema."), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames4() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq(""), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames5() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("\\"), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames6() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("{table_prefix}."), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames7() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("{table_prefix}."), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames8() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("foo."), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames9() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("42."), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames10() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("\\{table_prefix}."), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames11() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("\\{table_prefix}."), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames12() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("\\foo."), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames13() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("\\42."), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenThrow(new SQLException());

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> internalProxyConnection.prepareStatement("Sql", new String[] {"Column Names"}));
    verify(connection).prepareStatement(eq("Sql"), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, String[])} with {@code sql},
   * {@code columnNames}.
   *
   * <ul>
   *   <li>When {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, String[])"
  })
  public void testPrepareStatementWithSqlColumnNames_whenSql() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), Mockito.<String[]>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("Sql", new String[] {"Column Names"});

    // Assert
    verify(connection).prepareStatement(eq("Sql"), isA(String[].class));
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenThrow(new SQLException());

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.prepareStatement("Sql", 1, 1));
    verify(connection).prepareStatement("Sql", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency2() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("Sql", 1, 1);

    // Assert
    verify(connection).prepareStatement("Sql", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency3() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareStatement("Schema.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency4() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareStatement("\\Schema.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency5() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareStatement("", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency6() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareStatement("\\", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency7() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareStatement("{table_prefix}.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency8() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareStatement("{table_prefix}.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency9() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareStatement("foo.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency10() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareStatement("42.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency11() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareStatement("\\{table_prefix}.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency12() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareStatement("\\{table_prefix}.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency13() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareStatement("\\foo.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency14() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareStatement("\\42.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("Sql", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("Sql", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenThrow(new SQLException());

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act and Assert
    assertThrows(
        SQLException.class, () -> internalProxyConnection.prepareStatement("Sql", 1, 1, 1));
    verify(connection).prepareStatement("Sql", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability3()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("Sql", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("Sql", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability4()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("Schema.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability5()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("\\Schema.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability6()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability7()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("\\", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability8()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("{table_prefix}.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability9()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("{table_prefix}.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability10()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("foo.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability11()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("42.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability12()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("\\{table_prefix}.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability13()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("\\{table_prefix}.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability14()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("\\foo.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability15()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareStatement("\\42.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}.
   *
   * <ul>
   *   <li>When {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "PreparedStatement InternalProxyConnection.prepareStatement(String, int, int)"
  })
  public void testPrepareStatementWithSqlResultSetTypeResultSetConcurrency_whenSql()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("Sql", 1, 1);

    // Assert
    verify(connection).prepareStatement("Sql", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_givenInternalDatabaseConfigGetSchemaReturn42()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}");

    // Assert
    verify(connection).prepareStatement("42.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_givenInternalDatabaseConfigGetSchemaReturn422()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}");

    // Assert
    verify(connection).prepareStatement("\\42.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_givenInternalDatabaseConfigGetSchemaReturn423()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).prepareStatement("42.42.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_givenInternalDatabaseConfigGetSchemaReturnFoo()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}");

    // Assert
    verify(connection).prepareStatement("foo.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_givenInternalDatabaseConfigGetSchemaReturnFoo2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}");

    // Assert
    verify(connection).prepareStatement("\\foo.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_givenInternalDatabaseConfigGetSchemaReturnFoo3()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).prepareStatement("foo.foo.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code Schema}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_givenInternalDatabaseConfigGetSchemaReturnSchema()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}");

    // Assert
    verify(connection).prepareStatement("Schema.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code Schema}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_givenInternalDatabaseConfigGetSchemaReturnSchema2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("\\{table_prefix}");

    // Assert
    verify(connection).prepareStatement("\\Schema.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code Schema}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_givenInternalDatabaseConfigGetSchemaReturnSchema3()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).prepareStatement("Schema.Schema.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any())).thenThrow(new SQLException());

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.prepareStatement("Sql"));
    verify(connection).prepareStatement("Sql");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>When {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_whenSql() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("Sql");

    // Assert
    verify(connection).prepareStatement("Sql");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>When {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_whenSql2() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("Sql");

    // Assert
    verify(connection).prepareStatement("Sql");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareStatement(String)} with {@code sql}.
   *
   * <ul>
   *   <li>When {@code {table_prefix}\{table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareStatement(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PreparedStatement InternalProxyConnection.prepareStatement(String)"})
  public void testPrepareStatementWithSql_whenTablePrefixTablePrefix() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(PreparedStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareStatement("{table_prefix}\\{table_prefix}");

    // Assert
    verify(connection).prepareStatement("Schema.\\Schema.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("Sql", 1, 1);

    // Assert
    verify(connection).prepareCall("Sql", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency2() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareCall("Schema.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency3() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareCall("\\Schema.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency4() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareCall("", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency5() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareCall("\\", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency6() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareCall("{table_prefix}.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency7() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareCall("{table_prefix}.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency8() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareCall("foo.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency9() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareCall("42.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency10() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareCall("\\{table_prefix}.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency11() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareCall("\\{table_prefix}.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency12() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareCall("\\foo.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency13() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}", 1, 1);

    // Assert
    verify(connection).prepareCall("\\42.", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("Sql", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("Sql", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenThrow(new SQLException());

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.prepareCall("Sql", 1, 1, 1));
    verify(connection).prepareCall("Sql", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability3()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("Sql", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("Sql", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability4()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("Schema.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability5()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("\\Schema.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability6()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability7()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("\\", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability8()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("{table_prefix}.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability9()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("{table_prefix}.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability10()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("foo.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability11()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("42.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability12()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("\\{table_prefix}.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability13()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("\\{table_prefix}.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability14()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("\\foo.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int, int)} with {@code sql},
   * {@code resultSetType}, {@code resultSetConcurrency}, {@code resultSetHoldability}.
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CallableStatement InternalProxyConnection.prepareCall(String, int, int, int)"
  })
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrencyResultSetHoldability15()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}", 1, 1, 1);

    // Assert
    verify(connection).prepareCall("\\42.", 1, 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenThrow(new SQLException());

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.prepareCall("Sql", 1, 1));
    verify(connection).prepareCall("Sql", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String, int, int)} with {@code sql}, {@code
   * resultSetType}, {@code resultSetConcurrency}.
   *
   * <ul>
   *   <li>When {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String, int, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String, int, int)"})
  public void testPrepareCallWithSqlResultSetTypeResultSetConcurrency_whenSql()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any(), anyInt(), anyInt()))
        .thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("Sql", 1, 1);

    // Assert
    verify(connection).prepareCall("Sql", 1, 1);
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturn42()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}");

    // Assert
    verify(connection).prepareCall("42.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturn422()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}");

    // Assert
    verify(connection).prepareCall("\\42.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturn423()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).prepareCall("42.42.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       empty string.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnEmptyString()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}");

    // Assert
    verify(connection).prepareCall("");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       empty string.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnEmptyString2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}");

    // Assert
    verify(connection).prepareCall("\\");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       empty string.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnEmptyString3()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).prepareCall("");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnFoo()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}");

    // Assert
    verify(connection).prepareCall("foo.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnFoo2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}");

    // Assert
    verify(connection).prepareCall("\\foo.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnFoo3()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).prepareCall("foo.foo.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code Schema}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnSchema()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}");

    // Assert
    verify(connection).prepareCall("Schema.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code Schema}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnSchema2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}");

    // Assert
    verify(connection).prepareCall("\\Schema.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code Schema}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnSchema3()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).prepareCall("Schema.Schema.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code Schema}.
   *   <li>When {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnSchema_whenSql()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("Sql");

    // Assert
    verify(connection).prepareCall("Sql");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code {table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnTablePrefix()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}");

    // Assert
    verify(connection).prepareCall("{table_prefix}.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code \{table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnTablePrefix2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}");

    // Assert
    verify(connection).prepareCall("{table_prefix}.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code {table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnTablePrefix3()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}");

    // Assert
    verify(connection).prepareCall("\\{table_prefix}.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code \{table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnTablePrefix4()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("\\{table_prefix}");

    // Assert
    verify(connection).prepareCall("\\{table_prefix}.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code {table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnTablePrefix5()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).prepareCall("{table_prefix}.{table_prefix}.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code \{table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_givenInternalDatabaseConfigGetSchemaReturnTablePrefix6()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).prepareCall("{table_prefix}.{table_prefix}.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenThrow(new SQLException());

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.prepareCall("Sql"));
    verify(connection).prepareCall("Sql");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>When {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_whenSql() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("Sql");

    // Assert
    verify(connection).prepareCall("Sql");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#prepareCall(String)} with {@code sql}.
   *
   * <ul>
   *   <li>When {@code {table_prefix}\{table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#prepareCall(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"CallableStatement InternalProxyConnection.prepareCall(String)"})
  public void testPrepareCallWithSql_whenTablePrefixTablePrefix() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.prepareCall(Mockito.<String>any())).thenReturn(mock(CallableStatement.class));

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    internalProxyConnection.prepareCall("{table_prefix}\\{table_prefix}");

    // Assert
    verify(connection).prepareCall("Schema.\\Schema.");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#nativeSQL(String)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenConnectionNativeSQLThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenThrow(new SQLException());

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.nativeSQL("Sql"));
    verify(connection).nativeSQL("Sql");
    verify(config).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturn42() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult =
        internalProxyConnection.nativeSQL("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).nativeSQL("42.42.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code 42}.
   *   <li>When {@code {table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturn42_whenTablePrefix()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("{table_prefix}");

    // Assert
    verify(connection).nativeSQL("42.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code 42}.
   *   <li>When {@code \{table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturn42_whenTablePrefix2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("42");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("\\{table_prefix}");

    // Assert
    verify(connection).nativeSQL("\\42.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       empty string.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnEmptyString()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("{table_prefix}");

    // Assert
    verify(connection).nativeSQL("");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       empty string.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnEmptyString2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("\\{table_prefix}");

    // Assert
    verify(connection).nativeSQL("\\");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       empty string.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnEmptyString3()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult =
        internalProxyConnection.nativeSQL("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).nativeSQL("");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       empty string.
   *   <li>When {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnEmptyString_whenSql()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("Sql");

    // Assert
    verify(connection).nativeSQL("Sql");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnFoo() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult =
        internalProxyConnection.nativeSQL("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).nativeSQL("foo.foo.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code foo}.
   *   <li>When {@code {table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnFoo_whenTablePrefix()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("{table_prefix}");

    // Assert
    verify(connection).nativeSQL("foo.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code foo}.
   *   <li>When {@code \{table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnFoo_whenTablePrefix2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("foo");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("\\{table_prefix}");

    // Assert
    verify(connection).nativeSQL("\\foo.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code Schema}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnSchema() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult =
        internalProxyConnection.nativeSQL("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).nativeSQL("Schema.Schema.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code Schema}.
   *   <li>When {@code Sql}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnSchema_whenSql()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("Sql");

    // Assert
    verify(connection).nativeSQL("Sql");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code Schema}.
   *   <li>When {@code {table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnSchema_whenTablePrefix()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("{table_prefix}");

    // Assert
    verify(connection).nativeSQL("Schema.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code Schema}.
   *   <li>When {@code \{table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnSchema_whenTablePrefix2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("\\{table_prefix}");

    // Assert
    verify(connection).nativeSQL("\\Schema.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code {table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnTablePrefix()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("{table_prefix}");

    // Assert
    verify(connection).nativeSQL("{table_prefix}.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code \{table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnTablePrefix2()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("{table_prefix}");

    // Assert
    verify(connection).nativeSQL("{table_prefix}.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code {table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnTablePrefix3()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("\\{table_prefix}");

    // Assert
    verify(connection).nativeSQL("\\{table_prefix}.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code \{table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnTablePrefix4()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult = internalProxyConnection.nativeSQL("\\{table_prefix}");

    // Assert
    verify(connection).nativeSQL("\\{table_prefix}.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code {table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnTablePrefix5()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult =
        internalProxyConnection.nativeSQL("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).nativeSQL("{table_prefix}.{table_prefix}.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>Given {@link InternalDatabaseConfig} {@link InternalDatabaseConfig#getSchema()} return
   *       {@code \{table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_givenInternalDatabaseConfigGetSchemaReturnTablePrefix6()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("\\{table_prefix}");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult =
        internalProxyConnection.nativeSQL("{table_prefix}{table_prefix}");

    // Assert
    verify(connection).nativeSQL("{table_prefix}.{table_prefix}.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#nativeSQL(String)}.
   *
   * <ul>
   *   <li>When {@code {table_prefix}\{table_prefix}}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#nativeSQL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.nativeSQL(String)"})
  public void testNativeSQL_whenTablePrefixTablePrefix() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.nativeSQL(Mockito.<String>any())).thenReturn("Native SQL");

    InternalDatabaseConfig config = mock(InternalDatabaseConfig.class);
    when(config.getSchema()).thenReturn("Schema");

    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, config);

    // Act
    String actualNativeSQLResult =
        internalProxyConnection.nativeSQL("{table_prefix}\\{table_prefix}");

    // Assert
    verify(connection).nativeSQL("Schema.\\Schema.");
    verify(config).getSchema();
    assertEquals("Native SQL", actualNativeSQLResult);
  }

  /**
   * Test {@link InternalProxyConnection#getTypeMap()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getTypeMap()} return {@link
   *       HashMap#HashMap()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getTypeMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map InternalProxyConnection.getTypeMap()"})
  public void testGetTypeMap_givenConnectionGetTypeMapReturnHashMap_thenReturnEmpty()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    Mockito.<Map<String, Class<?>>>when(connection.getTypeMap()).thenReturn(new HashMap<>());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    Map<String, Class<?>> actualTypeMap = internalProxyConnection.getTypeMap();

    // Assert
    verify(connection).getTypeMap();
    assertTrue(actualTypeMap.isEmpty());
  }

  /**
   * Test {@link InternalProxyConnection#getTypeMap()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getTypeMap()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getTypeMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map InternalProxyConnection.getTypeMap()"})
  public void testGetTypeMap_givenConnectionGetTypeMapThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    Mockito.<Map<String, Class<?>>>when(connection.getTypeMap()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.getTypeMap());
    verify(connection).getTypeMap();
  }

  /**
   * Test {@link InternalProxyConnection#getHoldability()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getHoldability()} return one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getHoldability()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int InternalProxyConnection.getHoldability()"})
  public void testGetHoldability_givenConnectionGetHoldabilityReturnOne_thenReturnOne()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getHoldability()).thenReturn(1);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    int actualHoldability = internalProxyConnection.getHoldability();

    // Assert
    verify(connection).getHoldability();
    assertEquals(1, actualHoldability);
  }

  /**
   * Test {@link InternalProxyConnection#getHoldability()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getHoldability()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int InternalProxyConnection.getHoldability()"})
  public void testGetHoldability_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getHoldability()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.getHoldability());
    verify(connection).getHoldability();
  }

  /**
   * Test {@link InternalProxyConnection#setHoldability(int)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setHoldability(int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setHoldability(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setHoldability(int)"})
  public void testSetHoldability_givenConnectionSetHoldabilityDoesNothing() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).setHoldability(anyInt());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.setHoldability(1);

    // Assert
    verify(connection).setHoldability(1);
  }

  /**
   * Test {@link InternalProxyConnection#setHoldability(int)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setHoldability(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setHoldability(int)"})
  public void testSetHoldability_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException()).when(connection).setHoldability(anyInt());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.setHoldability(1));
    verify(connection).setHoldability(1);
  }

  /**
   * Test {@link InternalProxyConnection#setSavepoint(String)} with {@code String}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setSavepoint(String)} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setSavepoint(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Savepoint InternalProxyConnection.setSavepoint(String)"})
  public void testSetSavepointWithString_givenConnectionSetSavepointReturnNull_thenReturnNull()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.setSavepoint(Mockito.<String>any())).thenReturn(null);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    Savepoint actualSetSavepointResult = internalProxyConnection.setSavepoint("Name");

    // Assert
    verify(connection).setSavepoint("Name");
    assertNull(actualSetSavepointResult);
  }

  /**
   * Test {@link InternalProxyConnection#setSavepoint(String)} with {@code String}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setSavepoint(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Savepoint InternalProxyConnection.setSavepoint(String)"})
  public void testSetSavepointWithString_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.setSavepoint(Mockito.<String>any())).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.setSavepoint("Name"));
    verify(connection).setSavepoint("Name");
  }

  /**
   * Test {@link InternalProxyConnection#setSavepoint()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setSavepoint()} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setSavepoint()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Savepoint InternalProxyConnection.setSavepoint()"})
  public void testSetSavepoint_givenConnectionSetSavepointReturnNull_thenReturnNull()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.setSavepoint()).thenReturn(null);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    Savepoint actualSetSavepointResult = internalProxyConnection.setSavepoint();

    // Assert
    verify(connection).setSavepoint();
    assertNull(actualSetSavepointResult);
  }

  /**
   * Test {@link InternalProxyConnection#setSavepoint()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setSavepoint()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Savepoint InternalProxyConnection.setSavepoint()"})
  public void testSetSavepoint_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.setSavepoint()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.setSavepoint());
    verify(connection).setSavepoint();
  }

  /**
   * Test {@link InternalProxyConnection#releaseSavepoint(Savepoint)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#releaseSavepoint(Savepoint)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#releaseSavepoint(Savepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.releaseSavepoint(Savepoint)"})
  public void testReleaseSavepoint_givenConnectionReleaseSavepointDoesNothing()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).releaseSavepoint(Mockito.<Savepoint>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.releaseSavepoint(null);

    // Assert
    verify(connection).releaseSavepoint(isNull());
  }

  /**
   * Test {@link InternalProxyConnection#releaseSavepoint(Savepoint)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#releaseSavepoint(Savepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.releaseSavepoint(Savepoint)"})
  public void testReleaseSavepoint_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException()).when(connection).releaseSavepoint(Mockito.<Savepoint>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.releaseSavepoint(null));
    verify(connection).releaseSavepoint(isNull());
  }

  /**
   * Test {@link InternalProxyConnection#getAutoCommit()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getAutoCommit()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getAutoCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.getAutoCommit()"})
  public void testGetAutoCommit_givenConnectionGetAutoCommitReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getAutoCommit()).thenReturn(false);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    boolean actualAutoCommit = internalProxyConnection.getAutoCommit();

    // Assert
    verify(connection).getAutoCommit();
    assertFalse(actualAutoCommit);
  }

  /**
   * Test {@link InternalProxyConnection#getAutoCommit()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getAutoCommit()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getAutoCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.getAutoCommit()"})
  public void testGetAutoCommit_givenConnectionGetAutoCommitReturnTrue_thenReturnTrue()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getAutoCommit()).thenReturn(true);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    boolean actualAutoCommit = internalProxyConnection.getAutoCommit();

    // Assert
    verify(connection).getAutoCommit();
    assertTrue(actualAutoCommit);
  }

  /**
   * Test {@link InternalProxyConnection#getAutoCommit()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getAutoCommit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.getAutoCommit()"})
  public void testGetAutoCommit_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getAutoCommit()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.getAutoCommit());
    verify(connection).getAutoCommit();
  }

  /**
   * Test {@link InternalProxyConnection#setAutoCommit(boolean)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setAutoCommit(boolean)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setAutoCommit(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setAutoCommit(boolean)"})
  public void testSetAutoCommit_givenConnectionSetAutoCommitDoesNothing() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).setAutoCommit(anyBoolean());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.setAutoCommit(true);

    // Assert
    verify(connection).setAutoCommit(true);
  }

  /**
   * Test {@link InternalProxyConnection#setAutoCommit(boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setAutoCommit(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setAutoCommit(boolean)"})
  public void testSetAutoCommit_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException()).when(connection).setAutoCommit(anyBoolean());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.setAutoCommit(true));
    verify(connection).setAutoCommit(true);
  }

  /**
   * Test {@link InternalProxyConnection#commit()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#commit()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#commit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.commit()"})
  public void testCommit_givenConnectionCommitDoesNothing() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).commit();
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.commit();

    // Assert
    verify(connection).commit();
  }

  /**
   * Test {@link InternalProxyConnection#commit()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#commit()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#commit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.commit()"})
  public void testCommit_givenConnectionCommitThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException()).when(connection).commit();
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.commit());
    verify(connection).commit();
  }

  /**
   * Test {@link InternalProxyConnection#rollback(Savepoint)} with {@code Savepoint}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#rollback(Savepoint)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#rollback(Savepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.rollback(Savepoint)"})
  public void testRollbackWithSavepoint_givenConnectionRollbackDoesNothing() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).rollback(Mockito.<Savepoint>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.rollback(null);

    // Assert
    verify(connection).rollback(isNull());
  }

  /**
   * Test {@link InternalProxyConnection#rollback(Savepoint)} with {@code Savepoint}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#rollback(Savepoint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.rollback(Savepoint)"})
  public void testRollbackWithSavepoint_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException()).when(connection).rollback(Mockito.<Savepoint>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.rollback(null));
    verify(connection).rollback(isNull());
  }

  /**
   * Test {@link InternalProxyConnection#rollback()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#rollback()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#rollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.rollback()"})
  public void testRollback_givenConnectionRollbackDoesNothing() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).rollback();
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.rollback();

    // Assert
    verify(connection).rollback();
  }

  /**
   * Test {@link InternalProxyConnection#rollback()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#rollback()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#rollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.rollback()"})
  public void testRollback_givenConnectionRollbackThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException()).when(connection).rollback();
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.rollback());
    verify(connection).rollback();
  }

  /**
   * Test {@link InternalProxyConnection#close()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#close()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.close()"})
  public void testClose_givenConnectionCloseDoesNothing() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).close();
    try (InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class))) {}

    // Act and Assert
    verify(connection).close();
  }

  /**
   * Test {@link InternalProxyConnection#isClosed()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#isClosed()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#isClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.isClosed()"})
  public void testIsClosed_givenConnectionIsClosedReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.isClosed()).thenReturn(false);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    boolean actualIsClosedResult = internalProxyConnection.isClosed();

    // Assert
    verify(connection).isClosed();
    assertFalse(actualIsClosedResult);
  }

  /**
   * Test {@link InternalProxyConnection#isClosed()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#isClosed()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#isClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.isClosed()"})
  public void testIsClosed_givenConnectionIsClosedReturnTrue_thenReturnTrue() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.isClosed()).thenReturn(true);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    boolean actualIsClosedResult = internalProxyConnection.isClosed();

    // Assert
    verify(connection).isClosed();
    assertTrue(actualIsClosedResult);
  }

  /**
   * Test {@link InternalProxyConnection#isClosed()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#isClosed()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#isClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.isClosed()"})
  public void testIsClosed_givenConnectionIsClosedThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.isClosed()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.isClosed());
    verify(connection).isClosed();
  }

  /**
   * Test {@link InternalProxyConnection#getMetaData()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getMetaData()} return {@link
   *       DatabaseMetaData}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getMetaData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DatabaseMetaData InternalProxyConnection.getMetaData()"})
  public void testGetMetaData_givenConnectionGetMetaDataReturnDatabaseMetaData()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getMetaData()).thenReturn(mock(DatabaseMetaData.class));
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.getMetaData();

    // Assert
    verify(connection).getMetaData();
  }

  /**
   * Test {@link InternalProxyConnection#getMetaData()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getMetaData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DatabaseMetaData InternalProxyConnection.getMetaData()"})
  public void testGetMetaData_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getMetaData()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.getMetaData());
    verify(connection).getMetaData();
  }

  /**
   * Test {@link InternalProxyConnection#isReadOnly()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#isReadOnly()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#isReadOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.isReadOnly()"})
  public void testIsReadOnly_givenConnectionIsReadOnlyReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.isReadOnly()).thenReturn(false);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    boolean actualIsReadOnlyResult = internalProxyConnection.isReadOnly();

    // Assert
    verify(connection).isReadOnly();
    assertFalse(actualIsReadOnlyResult);
  }

  /**
   * Test {@link InternalProxyConnection#isReadOnly()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#isReadOnly()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#isReadOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.isReadOnly()"})
  public void testIsReadOnly_givenConnectionIsReadOnlyReturnTrue_thenReturnTrue()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.isReadOnly()).thenReturn(true);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    boolean actualIsReadOnlyResult = internalProxyConnection.isReadOnly();

    // Assert
    verify(connection).isReadOnly();
    assertTrue(actualIsReadOnlyResult);
  }

  /**
   * Test {@link InternalProxyConnection#isReadOnly()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#isReadOnly()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#isReadOnly()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.isReadOnly()"})
  public void testIsReadOnly_givenConnectionIsReadOnlyThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.isReadOnly()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.isReadOnly());
    verify(connection).isReadOnly();
  }

  /**
   * Test {@link InternalProxyConnection#setReadOnly(boolean)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setReadOnly(boolean)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setReadOnly(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setReadOnly(boolean)"})
  public void testSetReadOnly_givenConnectionSetReadOnlyDoesNothing() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).setReadOnly(anyBoolean());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.setReadOnly(true);

    // Assert
    verify(connection).setReadOnly(true);
  }

  /**
   * Test {@link InternalProxyConnection#setReadOnly(boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setReadOnly(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setReadOnly(boolean)"})
  public void testSetReadOnly_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException()).when(connection).setReadOnly(anyBoolean());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.setReadOnly(true));
    verify(connection).setReadOnly(true);
  }

  /**
   * Test {@link InternalProxyConnection#getCatalog()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getCatalog()} return {@code Catalog}.
   *   <li>Then return {@code Catalog}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getCatalog()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.getCatalog()"})
  public void testGetCatalog_givenConnectionGetCatalogReturnCatalog_thenReturnCatalog()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getCatalog()).thenReturn("Catalog");
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    String actualCatalog = internalProxyConnection.getCatalog();

    // Assert
    verify(connection).getCatalog();
    assertEquals("Catalog", actualCatalog);
  }

  /**
   * Test {@link InternalProxyConnection#getCatalog()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getCatalog()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getCatalog()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.getCatalog()"})
  public void testGetCatalog_givenConnectionGetCatalogThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getCatalog()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.getCatalog());
    verify(connection).getCatalog();
  }

  /**
   * Test {@link InternalProxyConnection#setCatalog(String)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setCatalog(String)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setCatalog(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setCatalog(String)"})
  public void testSetCatalog_givenConnectionSetCatalogDoesNothing() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).setCatalog(Mockito.<String>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.setCatalog("Catalog");

    // Assert
    verify(connection).setCatalog("Catalog");
  }

  /**
   * Test {@link InternalProxyConnection#setCatalog(String)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setCatalog(String)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setCatalog(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setCatalog(String)"})
  public void testSetCatalog_givenConnectionSetCatalogThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException()).when(connection).setCatalog(Mockito.<String>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.setCatalog("Catalog"));
    verify(connection).setCatalog("Catalog");
  }

  /**
   * Test {@link InternalProxyConnection#getTransactionIsolation()}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getTransactionIsolation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int InternalProxyConnection.getTransactionIsolation()"})
  public void testGetTransactionIsolation_thenReturnOne() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getTransactionIsolation()).thenReturn(1);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    int actualTransactionIsolation = internalProxyConnection.getTransactionIsolation();

    // Assert
    verify(connection).getTransactionIsolation();
    assertEquals(1, actualTransactionIsolation);
  }

  /**
   * Test {@link InternalProxyConnection#getTransactionIsolation()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getTransactionIsolation()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int InternalProxyConnection.getTransactionIsolation()"})
  public void testGetTransactionIsolation_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getTransactionIsolation()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.getTransactionIsolation());
    verify(connection).getTransactionIsolation();
  }

  /**
   * Test {@link InternalProxyConnection#setTransactionIsolation(int)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setTransactionIsolation(int)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setTransactionIsolation(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setTransactionIsolation(int)"})
  public void testSetTransactionIsolation_givenConnectionSetTransactionIsolationDoesNothing()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).setTransactionIsolation(anyInt());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.setTransactionIsolation(1);

    // Assert
    verify(connection).setTransactionIsolation(1);
  }

  /**
   * Test {@link InternalProxyConnection#setTransactionIsolation(int)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setTransactionIsolation(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setTransactionIsolation(int)"})
  public void testSetTransactionIsolation_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException()).when(connection).setTransactionIsolation(anyInt());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.setTransactionIsolation(1));
    verify(connection).setTransactionIsolation(1);
  }

  /**
   * Test {@link InternalProxyConnection#getWarnings()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getWarnings()} return {@link
   *       SQLWarning#SQLWarning()}.
   *   <li>Then return {@link SQLWarning#SQLWarning()}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLWarning InternalProxyConnection.getWarnings()"})
  public void testGetWarnings_givenConnectionGetWarningsReturnSQLWarning_thenReturnSQLWarning()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    SQLWarning sqlWarning = new SQLWarning();
    when(connection.getWarnings()).thenReturn(sqlWarning);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    SQLWarning actualWarnings = internalProxyConnection.getWarnings();

    // Assert
    verify(connection).getWarnings();
    assertSame(sqlWarning, actualWarnings);
  }

  /**
   * Test {@link InternalProxyConnection#getWarnings()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLWarning InternalProxyConnection.getWarnings()"})
  public void testGetWarnings_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getWarnings()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.getWarnings());
    verify(connection).getWarnings();
  }

  /**
   * Test {@link InternalProxyConnection#clearWarnings()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#clearWarnings()} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#clearWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.clearWarnings()"})
  public void testClearWarnings_givenConnectionClearWarningsDoesNothing() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).clearWarnings();
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.clearWarnings();

    // Assert
    verify(connection).clearWarnings();
  }

  /**
   * Test {@link InternalProxyConnection#clearWarnings()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#clearWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.clearWarnings()"})
  public void testClearWarnings_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException()).when(connection).clearWarnings();
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.clearWarnings());
    verify(connection).clearWarnings();
  }

  /**
   * Test {@link InternalProxyConnection#createClob()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#createClob()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createClob()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Clob InternalProxyConnection.createClob()"})
  public void testCreateClob_givenConnectionCreateClobThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createClob()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.createClob());
    verify(connection).createClob();
  }

  /**
   * Test {@link InternalProxyConnection#createClob()}.
   *
   * <ul>
   *   <li>Then return {@link SerialClob#SerialClob(char[])} with ch is {@code AZAZ} toCharArray.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createClob()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Clob InternalProxyConnection.createClob()"})
  public void testCreateClob_thenReturnSerialClobWithChIsAzazToCharArray() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    SerialClob serialClob = new SerialClob("AZAZ".toCharArray());
    when(connection.createClob()).thenReturn(serialClob);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    Clob actualCreateClobResult = internalProxyConnection.createClob();

    // Assert
    verify(connection).createClob();
    assertSame(serialClob, actualCreateClobResult);
  }

  /**
   * Test {@link InternalProxyConnection#createBlob()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#createBlob()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createBlob()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Blob InternalProxyConnection.createBlob()"})
  public void testCreateBlob_givenConnectionCreateBlobThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createBlob()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.createBlob());
    verify(connection).createBlob();
  }

  /**
   * Test {@link InternalProxyConnection#createBlob()}.
   *
   * <ul>
   *   <li>Then return {@link SerialBlob#SerialBlob(byte[])} with b is {@code AXAXAXAX} Bytes is
   *       {@code UTF-8}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createBlob()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Blob InternalProxyConnection.createBlob()"})
  public void testCreateBlob_thenReturnSerialBlobWithBIsAxaxaxaxBytesIsUtf8()
      throws UnsupportedEncodingException, SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    SerialBlob serialBlob = new SerialBlob("AXAXAXAX".getBytes("UTF-8"));
    when(connection.createBlob()).thenReturn(serialBlob);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    Blob actualCreateBlobResult = internalProxyConnection.createBlob();

    // Assert
    verify(connection).createBlob();
    assertSame(serialBlob, actualCreateBlobResult);
  }

  /**
   * Test {@link InternalProxyConnection#createNClob()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#createNClob()} return {@link NClob}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createNClob()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NClob InternalProxyConnection.createNClob()"})
  public void testCreateNClob_givenConnectionCreateNClobReturnNClob() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createNClob()).thenReturn(mock(NClob.class));
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.createNClob();

    // Assert
    verify(connection).createNClob();
  }

  /**
   * Test {@link InternalProxyConnection#createNClob()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createNClob()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NClob InternalProxyConnection.createNClob()"})
  public void testCreateNClob_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createNClob()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.createNClob());
    verify(connection).createNClob();
  }

  /**
   * Test {@link InternalProxyConnection#createSQLXML()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#createSQLXML()} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createSQLXML()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLXML InternalProxyConnection.createSQLXML()"})
  public void testCreateSQLXML_givenConnectionCreateSQLXMLReturnNull_thenReturnNull()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createSQLXML()).thenReturn(null);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    SQLXML actualCreateSQLXMLResult = internalProxyConnection.createSQLXML();

    // Assert
    verify(connection).createSQLXML();
    assertNull(actualCreateSQLXMLResult);
  }

  /**
   * Test {@link InternalProxyConnection#createSQLXML()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createSQLXML()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SQLXML InternalProxyConnection.createSQLXML()"})
  public void testCreateSQLXML_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createSQLXML()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.createSQLXML());
    verify(connection).createSQLXML();
  }

  /**
   * Test {@link InternalProxyConnection#isValid(int)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#isValid(int)} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#isValid(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.isValid(int)"})
  public void testIsValid_givenConnectionIsValidReturnFalse_thenReturnFalse() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.isValid(anyInt())).thenReturn(false);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    boolean actualIsValidResult = internalProxyConnection.isValid(10);

    // Assert
    verify(connection).isValid(10);
    assertFalse(actualIsValidResult);
  }

  /**
   * Test {@link InternalProxyConnection#isValid(int)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#isValid(int)} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#isValid(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.isValid(int)"})
  public void testIsValid_givenConnectionIsValidReturnTrue_thenReturnTrue() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.isValid(anyInt())).thenReturn(true);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    boolean actualIsValidResult = internalProxyConnection.isValid(10);

    // Assert
    verify(connection).isValid(10);
    assertTrue(actualIsValidResult);
  }

  /**
   * Test {@link InternalProxyConnection#isValid(int)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#isValid(int)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#isValid(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.isValid(int)"})
  public void testIsValid_givenConnectionIsValidThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.isValid(anyInt())).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.isValid(10));
    verify(connection).isValid(10);
  }

  /**
   * Test {@link InternalProxyConnection#setClientInfo(String, String)} with {@code name}, {@code
   * value}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setClientInfo(String, String)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setClientInfo(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setClientInfo(String, String)"})
  public void testSetClientInfoWithNameValue_givenConnectionSetClientInfoDoesNothing()
      throws SQLClientInfoException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).setClientInfo(Mockito.<String>any(), Mockito.<String>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.setClientInfo("Name", "42");

    // Assert
    verify(connection).setClientInfo("Name", "42");
  }

  /**
   * Test {@link InternalProxyConnection#setClientInfo(String, String)} with {@code name}, {@code
   * value}.
   *
   * <ul>
   *   <li>Then throw {@link SQLClientInfoException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setClientInfo(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setClientInfo(String, String)"})
  public void testSetClientInfoWithNameValue_thenThrowSQLClientInfoException()
      throws SQLClientInfoException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLClientInfoException())
        .when(connection)
        .setClientInfo(Mockito.<String>any(), Mockito.<String>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(
        SQLClientInfoException.class, () -> internalProxyConnection.setClientInfo("Name", "42"));
    verify(connection).setClientInfo("Name", "42");
  }

  /**
   * Test {@link InternalProxyConnection#setClientInfo(Properties)} with {@code properties}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setClientInfo(Properties)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setClientInfo(Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setClientInfo(Properties)"})
  public void testSetClientInfoWithProperties_givenConnectionSetClientInfoDoesNothing()
      throws SQLClientInfoException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).setClientInfo(Mockito.<Properties>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.setClientInfo(new Properties());

    // Assert
    verify(connection).setClientInfo(isA(Properties.class));
  }

  /**
   * Test {@link InternalProxyConnection#setClientInfo(Properties)} with {@code properties}.
   *
   * <ul>
   *   <li>Then throw {@link SQLClientInfoException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setClientInfo(Properties)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setClientInfo(Properties)"})
  public void testSetClientInfoWithProperties_thenThrowSQLClientInfoException()
      throws SQLClientInfoException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLClientInfoException()).when(connection).setClientInfo(Mockito.<Properties>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(
        SQLClientInfoException.class,
        () -> internalProxyConnection.setClientInfo(new Properties()));
    verify(connection).setClientInfo(isA(Properties.class));
  }

  /**
   * Test {@link InternalProxyConnection#getClientInfo(String)} with {@code String}.
   *
   * <ul>
   *   <li>Then return {@code Client Info}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getClientInfo(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.getClientInfo(String)"})
  public void testGetClientInfoWithString_thenReturnClientInfo() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getClientInfo(Mockito.<String>any())).thenReturn("Client Info");
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    String actualClientInfo = internalProxyConnection.getClientInfo("Name");

    // Assert
    verify(connection).getClientInfo("Name");
    assertEquals("Client Info", actualClientInfo);
  }

  /**
   * Test {@link InternalProxyConnection#getClientInfo(String)} with {@code String}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getClientInfo(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.getClientInfo(String)"})
  public void testGetClientInfoWithString_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getClientInfo(Mockito.<String>any())).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.getClientInfo("Name"));
    verify(connection).getClientInfo("Name");
  }

  /**
   * Test {@link InternalProxyConnection#getClientInfo()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getClientInfo()} return {@link
   *       Properties#Properties()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getClientInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Properties InternalProxyConnection.getClientInfo()"})
  public void testGetClientInfo_givenConnectionGetClientInfoReturnProperties_thenReturnEmpty()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    Properties properties = new Properties();
    when(connection.getClientInfo()).thenReturn(properties);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    Properties actualClientInfo = internalProxyConnection.getClientInfo();

    // Assert
    verify(connection).getClientInfo();
    assertTrue(actualClientInfo.isEmpty());
    assertSame(properties, actualClientInfo);
  }

  /**
   * Test {@link InternalProxyConnection#getClientInfo()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getClientInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Properties InternalProxyConnection.getClientInfo()"})
  public void testGetClientInfo_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getClientInfo()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.getClientInfo());
    verify(connection).getClientInfo();
  }

  /**
   * Test {@link InternalProxyConnection#createArrayOf(String, Object[])}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#createArrayOf(String, Object[])} return {@link
   *       SerialArray}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createArrayOf(String, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.sql.Array InternalProxyConnection.createArrayOf(String, Object[])"})
  public void testCreateArrayOf_givenConnectionCreateArrayOfReturnSerialArray()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createArrayOf(Mockito.<String>any(), Mockito.<Object[]>any()))
        .thenReturn(mock(SerialArray.class));
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.createArrayOf("Type Name", new Object[] {"Elements"});

    // Assert
    verify(connection).createArrayOf(eq("Type Name"), isA(Object[].class));
  }

  /**
   * Test {@link InternalProxyConnection#createArrayOf(String, Object[])}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createArrayOf(String, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.sql.Array InternalProxyConnection.createArrayOf(String, Object[])"})
  public void testCreateArrayOf_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createArrayOf(Mockito.<String>any(), Mockito.<Object[]>any()))
        .thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> internalProxyConnection.createArrayOf("Type Name", new Object[] {"Elements"}));
    verify(connection).createArrayOf(eq("Type Name"), isA(Object[].class));
  }

  /**
   * Test {@link InternalProxyConnection#createStruct(String, Object[])}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#createStruct(String, Object[])} return {@link
   *       SerialStruct}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createStruct(String, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.sql.Struct InternalProxyConnection.createStruct(String, Object[])"})
  public void testCreateStruct_givenConnectionCreateStructReturnSerialStruct() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createStruct(Mockito.<String>any(), Mockito.<Object[]>any()))
        .thenReturn(mock(SerialStruct.class));
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.createStruct("Type Name", new Object[] {"Attributes"});

    // Assert
    verify(connection).createStruct(eq("Type Name"), isA(Object[].class));
  }

  /**
   * Test {@link InternalProxyConnection#createStruct(String, Object[])}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#createStruct(String, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.sql.Struct InternalProxyConnection.createStruct(String, Object[])"})
  public void testCreateStruct_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.createStruct(Mockito.<String>any(), Mockito.<Object[]>any()))
        .thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> internalProxyConnection.createStruct("Type Name", new Object[] {"Attributes"}));
    verify(connection).createStruct(eq("Type Name"), isA(Object[].class));
  }

  /**
   * Test {@link InternalProxyConnection#getSchema()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getSchema()} return {@code Schema}.
   *   <li>Then return {@code Schema}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getSchema()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.getSchema()"})
  public void testGetSchema_givenConnectionGetSchemaReturnSchema_thenReturnSchema()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getSchema()).thenReturn("Schema");
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    String actualSchema = internalProxyConnection.getSchema();

    // Assert
    verify(connection).getSchema();
    assertEquals("Schema", actualSchema);
  }

  /**
   * Test {@link InternalProxyConnection#getSchema()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getSchema()} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getSchema()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InternalProxyConnection.getSchema()"})
  public void testGetSchema_givenConnectionGetSchemaThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getSchema()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.getSchema());
    verify(connection).getSchema();
  }

  /**
   * Test {@link InternalProxyConnection#setSchema(String)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setSchema(String)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setSchema(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setSchema(String)"})
  public void testSetSchema_givenConnectionSetSchemaDoesNothing() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).setSchema(Mockito.<String>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.setSchema("Schema");

    // Assert
    verify(connection).setSchema("Schema");
  }

  /**
   * Test {@link InternalProxyConnection#setSchema(String)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setSchema(String)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setSchema(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setSchema(String)"})
  public void testSetSchema_givenConnectionSetSchemaThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException()).when(connection).setSchema(Mockito.<String>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.setSchema("Schema"));
    verify(connection).setSchema("Schema");
  }

  /**
   * Test {@link InternalProxyConnection#abort(Executor)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#abort(Executor)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#abort(Executor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.abort(Executor)"})
  public void testAbort_givenConnectionAbortDoesNothing() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).abort(Mockito.<Executor>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.abort(mock(Executor.class));

    // Assert
    verify(connection).abort(isA(Executor.class));
  }

  /**
   * Test {@link InternalProxyConnection#abort(Executor)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#abort(Executor)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#abort(Executor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.abort(Executor)"})
  public void testAbort_givenConnectionAbortThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException()).when(connection).abort(Mockito.<Executor>any());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.abort(mock(Executor.class)));
    verify(connection).abort(isA(Executor.class));
  }

  /**
   * Test {@link InternalProxyConnection#setNetworkTimeout(Executor, int)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setNetworkTimeout(Executor, int)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setNetworkTimeout(Executor, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setNetworkTimeout(Executor, int)"})
  public void testSetNetworkTimeout_givenConnectionSetNetworkTimeoutDoesNothing()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doNothing().when(connection).setNetworkTimeout(Mockito.<Executor>any(), anyInt());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    internalProxyConnection.setNetworkTimeout(mock(Executor.class), 1000);

    // Assert
    verify(connection).setNetworkTimeout(isA(Executor.class), eq(1000));
  }

  /**
   * Test {@link InternalProxyConnection#setNetworkTimeout(Executor, int)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#setNetworkTimeout(Executor, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InternalProxyConnection.setNetworkTimeout(Executor, int)"})
  public void testSetNetworkTimeout_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    doThrow(new SQLException())
        .when(connection)
        .setNetworkTimeout(Mockito.<Executor>any(), anyInt());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> internalProxyConnection.setNetworkTimeout(mock(Executor.class), 1000));
    verify(connection).setNetworkTimeout(isA(Executor.class), eq(1000));
  }

  /**
   * Test {@link InternalProxyConnection#getNetworkTimeout()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getNetworkTimeout()} return ten.
   *   <li>Then return ten.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getNetworkTimeout()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int InternalProxyConnection.getNetworkTimeout()"})
  public void testGetNetworkTimeout_givenConnectionGetNetworkTimeoutReturnTen_thenReturnTen()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getNetworkTimeout()).thenReturn(10);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act
    int actualNetworkTimeout = internalProxyConnection.getNetworkTimeout();

    // Assert
    verify(connection).getNetworkTimeout();
    assertEquals(10, actualNetworkTimeout);
  }

  /**
   * Test {@link InternalProxyConnection#getNetworkTimeout()}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#getNetworkTimeout()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int InternalProxyConnection.getNetworkTimeout()"})
  public void testGetNetworkTimeout_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.getNetworkTimeout()).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.getNetworkTimeout());
    verify(connection).getNetworkTimeout();
  }

  /**
   * Test {@link InternalProxyConnection#unwrap(Class)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#unwrap(Class)} return {@code Unwrap}.
   *   <li>Then return {@code Unwrap}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object InternalProxyConnection.unwrap(Class)"})
  public void testUnwrap_givenConnectionUnwrapReturnUnwrap_thenReturnUnwrap() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.unwrap(Object.class)).thenReturn("Unwrap");
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));
    Class<Object> iface = Object.class;

    // Act
    Object actualUnwrapResult = internalProxyConnection.unwrap(iface);

    // Assert
    verify(connection).unwrap(isA(Class.class));
    assertEquals("Unwrap", actualUnwrapResult);
  }

  /**
   * Test {@link InternalProxyConnection#unwrap(Class)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#unwrap(Class)} throw {@link
   *       SQLException#SQLException()}.
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#unwrap(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object InternalProxyConnection.unwrap(Class)"})
  public void testUnwrap_givenConnectionUnwrapThrowSQLException_thenThrowSQLException()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.unwrap(Object.class)).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));
    Class<Object> iface = Object.class;

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.unwrap(iface));
    verify(connection).unwrap(isA(Class.class));
  }

  /**
   * Test {@link InternalProxyConnection#isWrapperFor(Class)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#isWrapperFor(Class)} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.isWrapperFor(Class)"})
  public void testIsWrapperFor_givenConnectionIsWrapperForReturnFalse_thenReturnFalse()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.isWrapperFor(Mockito.<Class<?>>any())).thenReturn(false);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));
    Class<Object> iface = Object.class;

    // Act
    boolean actualIsWrapperForResult = internalProxyConnection.isWrapperFor(iface);

    // Assert
    verify(connection).isWrapperFor(isA(Class.class));
    assertFalse(actualIsWrapperForResult);
  }

  /**
   * Test {@link InternalProxyConnection#isWrapperFor(Class)}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#isWrapperFor(Class)} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.isWrapperFor(Class)"})
  public void testIsWrapperFor_givenConnectionIsWrapperForReturnTrue_thenReturnTrue()
      throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.isWrapperFor(Mockito.<Class<?>>any())).thenReturn(true);
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));
    Class<Object> iface = Object.class;

    // Act
    boolean actualIsWrapperForResult = internalProxyConnection.isWrapperFor(iface);

    // Assert
    verify(connection).isWrapperFor(isA(Class.class));
    assertTrue(actualIsWrapperForResult);
  }

  /**
   * Test {@link InternalProxyConnection#isWrapperFor(Class)}.
   *
   * <ul>
   *   <li>Then throw {@link SQLException}.
   * </ul>
   *
   * <p>Method under test: {@link InternalProxyConnection#isWrapperFor(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean InternalProxyConnection.isWrapperFor(Class)"})
  public void testIsWrapperFor_thenThrowSQLException() throws SQLException {
    // Arrange
    Connection connection = mock(Connection.class);
    when(connection.isWrapperFor(Mockito.<Class<?>>any())).thenThrow(new SQLException());
    InternalProxyConnection internalProxyConnection =
        new InternalProxyConnection(connection, mock(InternalDatabaseConfig.class));
    Class<Object> iface = Object.class;

    // Act and Assert
    assertThrows(SQLException.class, () -> internalProxyConnection.isWrapperFor(iface));
    verify(connection).isWrapperFor(isA(Class.class));
  }
}
