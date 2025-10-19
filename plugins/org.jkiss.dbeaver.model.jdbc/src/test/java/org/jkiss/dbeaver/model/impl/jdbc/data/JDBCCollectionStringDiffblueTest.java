package org.jkiss.dbeaver.model.impl.jdbc.data;

import static org.junit.Assert.assertEquals;
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
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.util.List;
import org.jkiss.dbeaver.model.data.DBDValueHandler;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.jdbc.data.handlers.JDBCArrayValueHandler;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCCallableStatementImpl;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCColumnMetaData;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCResultSetCallable;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCResultSetMetaDataImpl;
import org.jkiss.dbeaver.model.impl.jdbc.struct.JDBCDataType;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDataType;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCCollectionStringDiffblueTest {
  /**
   * Test {@link JDBCCollectionString#JDBCCollectionString(DBRProgressMonitor, DBSDataType,
   * DBDValueHandler, String)}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCollectionString#JDBCCollectionString(DBRProgressMonitor,
   * DBSDataType, DBDValueHandler, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCCollectionString.<init>(DBRProgressMonitor, DBSDataType, DBDValueHandler, String)"
  })
  public void testNewJDBCCollectionString_thenReturnEmpty() throws SQLException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(null);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(null);

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable = new JDBCResultSetCallable(session, statement);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.getTableMetaData(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");
    when(resultSetMeta.getResultSet()).thenReturn(jdbcResultSetCallable);
    JDBCColumnMetaData typed = new JDBCColumnMetaData(resultSetMeta, 1);
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(null, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> type = new JDBCDataType<>(jdbcDataType, typed);

    // Act
    JDBCCollectionString actualJdbcCollectionString =
        new JDBCCollectionString(monitor, type, JDBCArrayValueHandler.INSTANCE, null);

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection, atLeast(1)).getDataSource();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta, atLeast(1)).getResultSet();
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableMetaData("Catalog Name", "Schema Name", "Table Name");
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    assertTrue(actualJdbcCollectionString.isEmpty());
  }

  /**
   * Test {@link JDBCCollectionString#JDBCCollectionString(DBRProgressMonitor, DBSDataType,
   * DBDValueHandler, String, Object[])}.
   *
   * <ul>
   *   <li>Then return size is eighteen.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCollectionString#JDBCCollectionString(DBRProgressMonitor,
   * DBSDataType, DBDValueHandler, String, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCCollectionString.<init>(DBRProgressMonitor, DBSDataType, DBDValueHandler, String, Object[])"
  })
  public void testNewJDBCCollectionString_thenReturnSizeIsEighteen() throws SQLException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(null);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(null);

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable = new JDBCResultSetCallable(session, statement);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.getTableMetaData(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");
    when(resultSetMeta.getResultSet()).thenReturn(jdbcResultSetCallable);
    JDBCColumnMetaData typed = new JDBCColumnMetaData(resultSetMeta, 1);
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(null, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> type = new JDBCDataType<>(jdbcDataType, typed);
    JDBCCollection jdbcCollection = new JDBCCollection();
    JDBCCollection jdbcCollection2 = new JDBCCollection();
    JDBCCollection jdbcCollection3 = new JDBCCollection();
    JDBCCollection jdbcCollection4 = new JDBCCollection();
    JDBCCollection jdbcCollection5 = new JDBCCollection();
    JDBCCollection jdbcCollection6 = new JDBCCollection();
    JDBCCollection jdbcCollection7 = new JDBCCollection();
    JDBCCollection jdbcCollection8 = new JDBCCollection();
    JDBCCollection jdbcCollection9 = new JDBCCollection();
    JDBCCollection jdbcCollection10 = new JDBCCollection();
    JDBCCollection jdbcCollection11 = new JDBCCollection();
    JDBCCollection jdbcCollection12 = new JDBCCollection();
    JDBCCollection jdbcCollection13 = new JDBCCollection();
    JDBCCollection jdbcCollection14 = new JDBCCollection();
    JDBCCollection jdbcCollection15 = new JDBCCollection();
    JDBCCollection jdbcCollection16 = new JDBCCollection();
    JDBCCollection jdbcCollection17 = new JDBCCollection();

    // Act
    JDBCCollectionString actualJdbcCollectionString =
        new JDBCCollectionString(
            monitor,
            type,
            JDBCArrayValueHandler.INSTANCE,
            "42",
            new Object[] {
              jdbcCollection,
              jdbcCollection2,
              jdbcCollection3,
              jdbcCollection4,
              jdbcCollection5,
              jdbcCollection6,
              jdbcCollection7,
              jdbcCollection8,
              jdbcCollection9,
              jdbcCollection10,
              jdbcCollection11,
              jdbcCollection12,
              jdbcCollection13,
              jdbcCollection14,
              jdbcCollection15,
              jdbcCollection16,
              jdbcCollection17,
              new JDBCCollection()
            });

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection, atLeast(1)).getDataSource();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta, atLeast(1)).getResultSet();
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableMetaData("Catalog Name", "Schema Name", "Table Name");
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    assertEquals(18, actualJdbcCollectionString.size());
    assertTrue(((List<Object>) actualJdbcCollectionString.get(0)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollectionString.get(1)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollectionString.get(12)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollectionString.get(13)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollectionString.get(14)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollectionString.get(15)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollectionString.get(17)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollectionString.get(2)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollectionString.get(3)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollectionString.get(4)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollectionString.get(5)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollectionString.get(Short.SIZE)).isEmpty());
  }

  /**
   * Test {@link JDBCCollectionString#JDBCCollectionString(DBRProgressMonitor, DBSDataType,
   * DBDValueHandler, String)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCollectionString#JDBCCollectionString(DBRProgressMonitor,
   * DBSDataType, DBDValueHandler, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCCollectionString.<init>(DBRProgressMonitor, DBSDataType, DBDValueHandler, String)"
  })
  public void testNewJDBCCollectionString_thenReturnSizeIsOne() throws SQLException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(null);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(null);

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable = new JDBCResultSetCallable(session, statement);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.getTableMetaData(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");
    when(resultSetMeta.getResultSet()).thenReturn(jdbcResultSetCallable);
    JDBCColumnMetaData typed = new JDBCColumnMetaData(resultSetMeta, 1);
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(null, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> type = new JDBCDataType<>(jdbcDataType, typed);

    // Act
    JDBCCollectionString actualJdbcCollectionString =
        new JDBCCollectionString(monitor, type, JDBCArrayValueHandler.INSTANCE, "42");

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection, atLeast(1)).getDataSource();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta, atLeast(1)).getResultSet();
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableMetaData("Catalog Name", "Schema Name", "Table Name");
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    assertEquals(1, actualJdbcCollectionString.size());
    assertEquals("42", actualJdbcCollectionString.get(0));
  }

  /**
   * Test {@link JDBCCollectionString#JDBCCollectionString(DBRProgressMonitor, DBSDataType,
   * DBDValueHandler, String, Object[])}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCollectionString#JDBCCollectionString(DBRProgressMonitor,
   * DBSDataType, DBDValueHandler, String, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCCollectionString.<init>(DBRProgressMonitor, DBSDataType, DBDValueHandler, String, Object[])"
  })
  public void testNewJDBCCollectionString_thenReturnSizeIsOne2() throws SQLException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(null);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(null);

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable jdbcResultSetCallable = new JDBCResultSetCallable(session, statement);

    JDBCResultSetMetaDataImpl resultSetMeta = mock(JDBCResultSetMetaDataImpl.class);
    when(resultSetMeta.getTableMetaData(
            Mockito.<String>any(), Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(null);
    when(resultSetMeta.isAutoIncrement(anyInt())).thenReturn(true);
    when(resultSetMeta.isWritable(anyInt())).thenReturn(true);
    when(resultSetMeta.getColumnDisplaySize(anyInt())).thenReturn(3);
    when(resultSetMeta.getColumnType(anyInt())).thenReturn(1);
    when(resultSetMeta.getPrecision(anyInt())).thenReturn(1);
    when(resultSetMeta.getScale(anyInt())).thenReturn(1);
    when(resultSetMeta.isNullable(anyInt())).thenReturn(1);
    when(resultSetMeta.getCatalogName(anyInt())).thenReturn("Catalog Name");
    when(resultSetMeta.getColumnLabel(anyInt())).thenReturn("Column Label");
    when(resultSetMeta.getColumnName(anyInt())).thenReturn("Column Name");
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("Column Type Name");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");
    when(resultSetMeta.getResultSet()).thenReturn(jdbcResultSetCallable);
    JDBCColumnMetaData typed = new JDBCColumnMetaData(resultSetMeta, 1);
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(null, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> type = new JDBCDataType<>(jdbcDataType, typed);
    Object[] contents = new Object[] {"Contents"};

    // Act
    JDBCCollectionString actualJdbcCollectionString =
        new JDBCCollectionString(monitor, type, JDBCArrayValueHandler.INSTANCE, "42", contents);

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection, atLeast(1)).getDataSource();
    verify(resultSetMeta).getCatalogName(2);
    verify(resultSetMeta).getColumnDisplaySize(2);
    verify(resultSetMeta).getColumnLabel(2);
    verify(resultSetMeta).getColumnName(2);
    verify(resultSetMeta).getColumnType(2);
    verify(resultSetMeta).getColumnTypeName(2);
    verify(resultSetMeta).getPrecision(2);
    verify(resultSetMeta, atLeast(1)).getResultSet();
    verify(resultSetMeta).getScale(2);
    verify(resultSetMeta).getSchemaName(2);
    verify(resultSetMeta).getTableMetaData("Catalog Name", "Schema Name", "Table Name");
    verify(resultSetMeta).getTableName(2);
    verify(resultSetMeta).isAutoIncrement(2);
    verify(resultSetMeta).isNullable(2);
    verify(resultSetMeta).isWritable(2);
    assertEquals(1, actualJdbcCollectionString.size());
    assertEquals("Contents", actualJdbcCollectionString.get(0));
  }
}
