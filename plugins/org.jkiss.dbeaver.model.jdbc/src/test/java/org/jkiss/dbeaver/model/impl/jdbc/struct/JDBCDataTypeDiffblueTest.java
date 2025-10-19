package org.jkiss.dbeaver.model.impl.jdbc.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
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
import java.sql.CallableStatement;
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCSQLDialect;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCCallableStatementImpl;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCColumnMetaData;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCResultSetCallable;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCResultSetMetaDataImpl;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDataType;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCDataTypeDiffblueTest {
  /**
   * Test {@link JDBCDataType#JDBCDataType(DBSObject, DBSTypedObject)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>Then ParentObject return {@link JDBCDataType}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#JDBCDataType(DBSObject, DBSTypedObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCDataType.<init>(DBSObject, DBSTypedObject)"})
  public void testNewJDBCDataType_givenOne_thenParentObjectReturnJDBCDataType() {
    // Arrange
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(null, 42, "Name", "Remarks", true, true, 1, 1, 3);

    DBDAttributeBindingCustom typed = mock(DBDAttributeBindingCustom.class);
    when(typed.getTypeID()).thenReturn(1);
    when(typed.getPrecision()).thenReturn(1);
    when(typed.getScale()).thenReturn(1);
    when(typed.getTypeName()).thenReturn("Type Name");

    // Act
    JDBCDataType<DBSObject> actualJdbcDataType = new JDBCDataType<>(jdbcDataType, typed);

    // Assert
    verify(typed).getPrecision();
    verify(typed, atLeast(1)).getScale();
    verify(typed).getTypeID();
    verify(typed).getTypeName();
    DBSObject parentObject = actualJdbcDataType.getParentObject();
    assertTrue(parentObject instanceof JDBCDataType);
    assertEquals("Type Name", actualJdbcDataType.getName());
    assertEquals("Type Name", actualJdbcDataType.getTypeName());
    assertNull(actualJdbcDataType.getDescription());
    assertEquals(0L, actualJdbcDataType.getTypeModifiers());
    assertEquals(1, actualJdbcDataType.getPrecision().intValue());
    assertEquals(1, actualJdbcDataType.getScale().intValue());
    assertEquals(1, actualJdbcDataType.getMaxScale());
    assertEquals(1, actualJdbcDataType.getMinScale());
    assertEquals(1, actualJdbcDataType.getTypeID());
    assertEquals(1L, actualJdbcDataType.getMaxLength());
    assertFalse(actualJdbcDataType.isSearchable());
    assertFalse(actualJdbcDataType.isUnsigned());
    assertTrue(actualJdbcDataType.isPersisted());
    assertSame(jdbcDataType, parentObject);
  }

  /**
   * Test {@link JDBCDataType#getFullTypeName()}.
   *
   * <ul>
   *   <li>Then return {@code Column Type Name}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#getFullTypeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDataType.getFullTypeName()"})
  public void testGetFullTypeName_thenReturnColumnTypeName() throws SQLException {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

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

    JDBCDataType<DBSObject> jdbcDataType = new JDBCDataType<>(dbsDocumentConstraint, typed);

    // Act
    String actualFullTypeName = jdbcDataType.getFullTypeName();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(dbpDataSource).getSQLDialect();
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
    verify(entity, atLeast(1)).getDataSource();
    assertEquals("Column Type Name", actualFullTypeName);
  }

  /**
   * Test {@link JDBCDataType#getFullTypeName()}.
   *
   * <ul>
   *   <li>Then return {@code Column Type NameColumn Type Modifiers}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#getFullTypeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDataType.getFullTypeName()"})
  public void testGetFullTypeName_thenReturnColumnTypeNameColumnTypeModifiers()
      throws SQLException {
    // Arrange
    JDBCSQLDialect jdbcsqlDialect = mock(JDBCSQLDialect.class);
    when(jdbcsqlDialect.getColumnTypeModifiers(
            Mockito.<DBPDataSource>any(),
            Mockito.<DBSTypedObject>any(),
            Mockito.<String>any(),
            Mockito.<DBPDataKind>any()))
        .thenReturn("Column Type Modifiers");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(jdbcsqlDialect);

    DBSDocumentConstraint dbsDocumentConstraint = mock(DBSDocumentConstraint.class);
    when(dbsDocumentConstraint.getDataSource()).thenReturn(dbpDataSource);

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

    JDBCDataType<DBSObject> jdbcDataType = new JDBCDataType<>(dbsDocumentConstraint, typed);

    // Act
    String actualFullTypeName = jdbcDataType.getFullTypeName();

    // Assert
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(dbpDataSource).getSQLDialect();
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
    verify(jdbcsqlDialect)
        .getColumnTypeModifiers(
            isA(DBPDataSource.class),
            isA(DBSTypedObject.class),
            eq("Column Type Name"),
            eq(DBPDataKind.UNKNOWN));
    verify(dbsDocumentConstraint, atLeast(1)).getDataSource();
    assertEquals("Column Type NameColumn Type Modifiers", actualFullTypeName);
  }

  /**
   * Test {@link JDBCDataType#getFullTypeName()}.
   *
   * <ul>
   *   <li>Then return {@code <unknown type>}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#getFullTypeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDataType.getFullTypeName()"})
  public void testGetFullTypeName_thenReturnUnknownType() throws SQLException {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint = mock(DBSDocumentConstraint.class);
    when(dbsDocumentConstraint.getDataSource()).thenReturn(mock(DBPDataSource.class));

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
    when(resultSetMeta.getColumnTypeName(anyInt())).thenReturn("");
    when(resultSetMeta.getSchemaName(anyInt())).thenReturn("Schema Name");
    when(resultSetMeta.getTableName(anyInt())).thenReturn("Table Name");
    when(resultSetMeta.getResultSet()).thenReturn(jdbcResultSetCallable);
    JDBCColumnMetaData typed = new JDBCColumnMetaData(resultSetMeta, 1);

    JDBCDataType<DBSObject> jdbcDataType = new JDBCDataType<>(dbsDocumentConstraint, typed);

    // Act
    String actualFullTypeName = jdbcDataType.getFullTypeName();

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
    verify(dbsDocumentConstraint).getDataSource();
    assertEquals("<unknown type>", actualFullTypeName);
  }

  /**
   * Test {@link JDBCDataType#getDataSource()}.
   *
   * <ul>
   *   <li>Then calls {@link ParameterMetaData#getParameterCount()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource JDBCDataType.getDataSource()"})
  public void testGetDataSource_thenCallsGetParameterCount() throws SQLException {
    // Arrange
    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

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

    JDBCDataType<DBSObject> jdbcDataType = new JDBCDataType<>(dbsDocumentConstraint, typed);

    // Act
    jdbcDataType.getDataSource();

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
    verify(entity).getDataSource();
  }

  /**
   * Test {@link JDBCDataType#getScale()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@code null}.
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#getScale()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer JDBCDataType.getScale()"})
  public void testGetScale_givenJDBCSessionGetDataSourceReturnNull_thenReturnIntValueIsOne()
      throws SQLException {
    // Arrange
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

    JDBCDataType<DBSObject> jdbcDataType2 = new JDBCDataType<>(jdbcDataType, typed);

    // Act
    Integer actualScale = jdbcDataType2.getScale();

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
    assertEquals(1, actualScale.intValue());
  }

  /**
   * Test {@link JDBCDataType#getPrecision()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@code null}.
   *   <li>Then return intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#getPrecision()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Integer JDBCDataType.getPrecision()"})
  public void testGetPrecision_givenJDBCSessionGetDataSourceReturnNull_thenReturnIntValueIsOne()
      throws SQLException {
    // Arrange
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

    JDBCDataType<DBSObject> jdbcDataType2 = new JDBCDataType<>(jdbcDataType, typed);

    // Act
    Integer actualPrecision = jdbcDataType2.getPrecision();

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
    assertEquals(1, actualPrecision.intValue());
  }

  /**
   * Test {@link JDBCDataType#getMaxLength()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@code null}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#getMaxLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long JDBCDataType.getMaxLength()"})
  public void testGetMaxLength_givenJDBCSessionGetDataSourceReturnNull_thenReturnOne()
      throws SQLException {
    // Arrange
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

    JDBCDataType<DBSObject> jdbcDataType2 = new JDBCDataType<>(jdbcDataType, typed);

    // Act
    long actualMaxLength = jdbcDataType2.getMaxLength();

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
    assertEquals(1L, actualMaxLength);
  }

  /**
   * Test {@link JDBCDataType#geTypeExtension()}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#geTypeExtension()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object JDBCDataType.geTypeExtension()"})
  public void testGeTypeExtension_givenJDBCSessionGetDataSourceReturnNull_thenReturnNull()
      throws SQLException {
    // Arrange
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

    JDBCDataType<DBSObject> jdbcDataType2 = new JDBCDataType<>(jdbcDataType, typed);

    // Act
    Object actualGeTypeExtensionResult = jdbcDataType2.geTypeExtension();

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
    assertNull(actualGeTypeExtensionResult);
  }

  /**
   * Test {@link JDBCDataType#getComponentType(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link JDBCSession} {@link JDBCSession#getDataSource()} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#getComponentType(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSDataType JDBCDataType.getComponentType(DBRProgressMonitor)"})
  public void testGetComponentType_givenJDBCSessionGetDataSourceReturnNull_thenReturnNull()
      throws SQLException, DBException {
    // Arrange
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

    JDBCDataType<DBSObject> jdbcDataType2 = new JDBCDataType<>(jdbcDataType, typed);

    // Act
    DBSDataType actualComponentType = jdbcDataType2.getComponentType(new LoggingProgressMonitor());

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
    assertNull(actualComponentType);
  }

  /**
   * Test {@link JDBCDataType#toString()}.
   *
   * <ul>
   *   <li>Then return {@code DocumentKey.Name}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDataType.toString()"})
  public void testToString_thenReturnDocumentKeyName() {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(dbsDocumentConstraint, 42, "Name", "Remarks", true, true, 1, 1, 3);

    // Act and Assert
    assertEquals("DocumentKey.Name", jdbcDataType.toString());
  }

  /**
   * Test {@link JDBCDataType#toString()}.
   *
   * <ul>
   *   <li>Then return {@code Type Name}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDataType.toString()"})
  public void testToString_thenReturnTypeName() {
    // Arrange
    DBDAttributeBindingCustom typed = mock(DBDAttributeBindingCustom.class);
    when(typed.getTypeID()).thenReturn(1);
    when(typed.getPrecision()).thenReturn(1);
    when(typed.getScale()).thenReturn(1);
    when(typed.getTypeName()).thenReturn("Type Name");
    JDBCDataType<DBSObject> jdbcDataType = new JDBCDataType<>(mock(DBPDataSource.class), typed);

    // Act
    String actualToStringResult = jdbcDataType.toString();

    // Assert
    verify(typed).getPrecision();
    verify(typed, atLeast(1)).getScale();
    verify(typed).getTypeID();
    verify(typed).getTypeName();
    assertEquals("Type Name", actualToStringResult);
  }

  /**
   * Test {@link JDBCDataType#toString()}.
   *
   * <ul>
   *   <li>Then return {@code Type Name.Name}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDataType#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCDataType.toString()"})
  public void testToString_thenReturnTypeNameName() {
    // Arrange
    DBDAttributeBindingCustom typed = mock(DBDAttributeBindingCustom.class);
    when(typed.getTypeID()).thenReturn(1);
    when(typed.getPrecision()).thenReturn(1);
    when(typed.getScale()).thenReturn(1);
    when(typed.getTypeName()).thenReturn("Type Name");
    JDBCDataType<DBSObject> jdbcDataType = new JDBCDataType<>(null, typed);
    JDBCDataType<DBSObject> jdbcDataType2 =
        new JDBCDataType<>(jdbcDataType, 42, "Name", "Remarks", true, true, 1, 1, 3);

    // Act
    String actualToStringResult = jdbcDataType2.toString();

    // Assert
    verify(typed).getPrecision();
    verify(typed, atLeast(1)).getScale();
    verify(typed).getTypeID();
    verify(typed).getTypeName();
    assertEquals("Type Name.Name", actualToStringResult);
  }
}
