package org.jkiss.dbeaver.model.impl.jdbc.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.data.DBDValueCloneable;
import org.jkiss.dbeaver.model.data.DBDValueHandler;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCArrayImpl;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
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
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class JDBCCollectionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link JDBCCollection#JDBCCollection()}
   *   <li>{@link JDBCCollection#release()}
   *   <li>{@link JDBCCollection#getComponentType()}
   *   <li>{@link JDBCCollection#getComponentValueHandler()}
   *   <li>{@link JDBCCollection#getRawValue()}
   *   <li>{@link JDBCCollection#isModified()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCCollection.<init>()",
    "DBSDataType JDBCCollection.getComponentType()",
    "DBDValueHandler JDBCCollection.getComponentValueHandler()",
    "Object JDBCCollection.getRawValue()",
    "boolean JDBCCollection.isModified()",
    "void JDBCCollection.release()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    JDBCCollection actualJdbcCollection = new JDBCCollection();
    actualJdbcCollection.release();
    DBSDataType actualComponentType = actualJdbcCollection.getComponentType();
    DBDValueHandler actualComponentValueHandler = actualJdbcCollection.getComponentValueHandler();
    Object actualRawValue = actualJdbcCollection.getRawValue();

    // Assert
    assertNull(actualRawValue);
    assertNull(actualComponentValueHandler);
    assertNull(actualComponentType);
    assertFalse(actualJdbcCollection.isModified());
  }

  /**
   * Test {@link JDBCCollection#JDBCCollection(DBRProgressMonitor, DBSDataType, DBDValueHandler,
   * Object[])}.
   *
   * <ul>
   *   <li>Then return size is eighteen.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCollection#JDBCCollection(DBRProgressMonitor, DBSDataType,
   * DBDValueHandler, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCCollection.<init>(DBRProgressMonitor, DBSDataType, DBDValueHandler, Object[])"
  })
  public void testNewJDBCCollection_thenReturnSizeIsEighteen() throws SQLException {
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
    JDBCCollection actualJdbcCollection =
        new JDBCCollection(
            monitor,
            type,
            JDBCArrayValueHandler.INSTANCE,
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
    assertEquals(18, actualJdbcCollection.size());
    assertTrue(((List<Object>) actualJdbcCollection.get(0)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollection.get(1)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollection.get(12)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollection.get(13)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollection.get(14)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollection.get(15)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollection.get(17)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollection.get(2)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollection.get(3)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollection.get(4)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollection.get(5)).isEmpty());
    assertTrue(((List<Object>) actualJdbcCollection.get(Short.SIZE)).isEmpty());
  }

  /**
   * Test {@link JDBCCollection#JDBCCollection(DBRProgressMonitor, DBSDataType, DBDValueHandler,
   * Object[])}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCollection#JDBCCollection(DBRProgressMonitor, DBSDataType,
   * DBDValueHandler, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JDBCCollection.<init>(DBRProgressMonitor, DBSDataType, DBDValueHandler, Object[])"
  })
  public void testNewJDBCCollection_thenReturnSizeIsOne() throws SQLException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(null);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(null);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

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
    JDBCCollection actualJdbcCollection =
        new JDBCCollection(monitor, type, JDBCArrayValueHandler.INSTANCE, contents);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(session).getDataSource();
    verify(connection).getDataSource();
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
    assertEquals(1, actualJdbcCollection.size());
    assertEquals("Contents", actualJdbcCollection.get(0));
  }

  /**
   * Test {@link JDBCCollection#cloneValue(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link JDBCCollection#cloneValue(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDValueCloneable JDBCCollection.cloneValue(DBRProgressMonitor)"})
  public void testCloneValue() {
    // Arrange
    JDBCCollection jdbcCollection = new JDBCCollection();

    // Act
    DBDValueCloneable actualCloneValueResult =
        jdbcCollection.cloneValue(new LoggingProgressMonitor());

    // Assert
    assertEquals(jdbcCollection, actualCloneValueResult);
  }

  /**
   * Test {@link JDBCCollection#isNull()}.
   *
   * <p>Method under test: {@link JDBCCollection#isNull()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCCollection.isNull()"})
  public void testIsNull() {
    // Arrange, Act and Assert
    assertTrue(new JDBCCollection().isNull());
  }

  /**
   * Test {@link JDBCCollection#toString()}.
   *
   * <p>Method under test: {@link JDBCCollection#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCollection.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("[NULL]", new JDBCCollection().toString());
  }

  /**
   * Test {@link JDBCCollection#makeArrayString(char[])}.
   *
   * <p>Method under test: {@link JDBCCollection#makeArrayString(char[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCCollection.makeArrayString(char[])"})
  public void testMakeArrayString() {
    // Arrange
    JDBCCollection jdbcCollection = new JDBCCollection();

    // Act
    String actualMakeArrayStringResult = jdbcCollection.makeArrayString("A A ".toCharArray());

    // Assert
    assertEquals("NULL", actualMakeArrayStringResult);
  }

  /**
   * Test {@link JDBCCollection#getItemCount()}.
   *
   * <p>Method under test: {@link JDBCCollection#getItemCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCCollection.getItemCount()"})
  public void testGetItemCount() {
    // Arrange, Act and Assert
    assertEquals(0, new JDBCCollection().getItemCount());
  }

  /**
   * Test {@link JDBCCollection#setContents(Object[])}.
   *
   * <p>Method under test: {@link JDBCCollection#setContents(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCCollection.setContents(Object[])"})
  public void testSetContents() {
    // Arrange
    JDBCCollection jdbcCollection = new JDBCCollection();

    // Act
    jdbcCollection.setContents(new Object[] {"Contents"});

    // Assert
    assertEquals(1, jdbcCollection.size());
    assertEquals("Contents", jdbcCollection.get(0));
  }

  /**
   * Test {@link JDBCCollection#getArrayValue()}.
   *
   * <p>Method under test: {@link JDBCCollection#getArrayValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Array JDBCCollection.getArrayValue()"})
  public void testGetArrayValue() throws DBCException {
    // Arrange, Act and Assert
    assertNull(new JDBCCollection().getArrayValue());
  }

  /**
   * Test {@link JDBCCollection#makeCollectionFromArray(JDBCSession, DBSTypedObject, Array)} with
   * {@code session}, {@code column}, {@code array}.
   *
   * <ul>
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCollection#makeCollectionFromArray(JDBCSession,
   * DBSTypedObject, Array)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCCollection JDBCCollection.makeCollectionFromArray(JDBCSession, DBSTypedObject, Array)"
  })
  public void testMakeCollectionFromArrayWithSessionColumnArray_thenThrowRuntimeException()
      throws DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    when(session.getProgressMonitor()).thenThrow(new RuntimeException());
    Object[] items = new Object[] {"Items"};

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            JDBCCollection.makeCollectionFromArray(
                session, null, new JDBCArrayImpl("Type Name", 1, items)));
    verify(session).getProgressMonitor();
  }

  /**
   * Test {@link JDBCCollection#makeCollectionFromJavaArray(JDBCSession, Object)} with {@code
   * session}, {@code array}.
   *
   * <p>Method under test: {@link JDBCCollection#makeCollectionFromJavaArray(JDBCSession, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCCollection JDBCCollection.makeCollectionFromJavaArray(JDBCSession, Object)"
  })
  public void testMakeCollectionFromJavaArrayWithSessionArray() throws DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getLocalDataType(anyInt())).thenThrow(new RuntimeException());

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> JDBCCollection.makeCollectionFromJavaArray(session, "Array"));
    verify(session).getDataSource();
    verify(jdbcDataSource).getLocalDataType(2002);
  }

  /**
   * Test {@link JDBCCollection#makeCollectionFromJavaArray(JDBCSession, Object)} with {@code
   * session}, {@code array}.
   *
   * <p>Method under test: {@link JDBCCollection#makeCollectionFromJavaArray(JDBCSession, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCCollection JDBCCollection.makeCollectionFromJavaArray(JDBCSession, Object)"
  })
  public void testMakeCollectionFromJavaArrayWithSessionArray2() throws DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getLocalDataType(Mockito.<String>any())).thenReturn(null);
    when(jdbcDataSource.getDefaultDataTypeName(Mockito.<DBPDataKind>any()))
        .thenReturn("Default Data Type Name");
    when(jdbcDataSource.getLocalDataType(anyInt())).thenReturn(null);

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);

    // Act and Assert
    assertThrows(
        DBCException.class, () -> JDBCCollection.makeCollectionFromJavaArray(session, "Array"));
    verify(jdbcDataSource).getLocalDataType("Default Data Type Name");
    verify(session).getDataSource();
    verify(jdbcDataSource).getDefaultDataTypeName(DBPDataKind.OBJECT);
    verify(jdbcDataSource).getLocalDataType(2002);
  }

  /**
   * Test {@link JDBCCollection#makeCollectionFromJavaArray(JDBCSession, Object)} with {@code
   * session}, {@code array}.
   *
   * <p>Method under test: {@link JDBCCollection#makeCollectionFromJavaArray(JDBCSession, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCCollection JDBCCollection.makeCollectionFromJavaArray(JDBCSession, Object)"
  })
  public void testMakeCollectionFromJavaArrayWithSessionArray3() throws DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getLocalDataType(Mockito.<String>any())).thenThrow(new RuntimeException());
    when(jdbcDataSource.getDefaultDataTypeName(Mockito.<DBPDataKind>any()))
        .thenReturn("Default Data Type Name");
    when(jdbcDataSource.getLocalDataType(anyInt())).thenReturn(null);

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);

    // Act and Assert
    assertThrows(
        DBCException.class, () -> JDBCCollection.makeCollectionFromJavaArray(session, "Array"));
    verify(jdbcDataSource).getLocalDataType("Default Data Type Name");
    verify(session).getDataSource();
    verify(jdbcDataSource).getDefaultDataTypeName(DBPDataKind.OBJECT);
    verify(jdbcDataSource).getLocalDataType(2002);
  }

  /**
   * Test {@link JDBCCollection#makeCollectionFromJavaArray(JDBCSession, Object)} with {@code
   * session}, {@code array}.
   *
   * <p>Method under test: {@link JDBCCollection#makeCollectionFromJavaArray(JDBCSession, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCCollection JDBCCollection.makeCollectionFromJavaArray(JDBCSession, Object)"
  })
  public void testMakeCollectionFromJavaArrayWithSessionArray4() throws DBCException {
    // Arrange
    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getDefaultDataTypeName(Mockito.<DBPDataKind>any())).thenReturn(null);
    when(jdbcDataSource.getLocalDataType(anyInt())).thenReturn(null);

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);

    // Act and Assert
    assertThrows(
        DBCException.class, () -> JDBCCollection.makeCollectionFromJavaArray(session, "Array"));
    verify(session).getDataSource();
    verify(jdbcDataSource).getDefaultDataTypeName(DBPDataKind.OBJECT);
    verify(jdbcDataSource).getLocalDataType(2002);
  }

  /**
   * Test {@link JDBCCollection#makeCollectionFromJavaArray(JDBCSession, Object)} with {@code
   * session}, {@code array}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCCollection#makeCollectionFromJavaArray(JDBCSession, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCCollection JDBCCollection.makeCollectionFromJavaArray(JDBCSession, Object)"
  })
  public void testMakeCollectionFromJavaArrayWithSessionArray_givenRuntimeException()
      throws DBCException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenThrow(new RuntimeException());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> JDBCCollection.makeCollectionFromJavaArray(session, "Array"));
    verify(session).getDataSource();
  }

  /**
   * Test {@link JDBCCollection#size()}.
   *
   * <p>Method under test: {@link JDBCCollection#size()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCCollection.size()"})
  public void testSize() {
    // Arrange, Act and Assert
    assertEquals(0, new JDBCCollection().size());
  }
}
