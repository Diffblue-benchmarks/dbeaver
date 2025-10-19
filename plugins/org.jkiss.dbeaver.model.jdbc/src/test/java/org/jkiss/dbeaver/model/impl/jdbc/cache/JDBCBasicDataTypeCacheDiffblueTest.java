package org.jkiss.dbeaver.model.impl.jdbc.cache;

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
import java.sql.ParameterMetaData;
import java.sql.SQLException;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCDatabaseMetaData;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCResultSet;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCStatement;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCCallableStatementImpl;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCResultSetCallable;
import org.jkiss.dbeaver.model.impl.jdbc.struct.JDBCDataType;
import org.jkiss.dbeaver.model.struct.DBSDataType;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JDBCBasicDataTypeCacheDiffblueTest {
  @Mock private DBSObject dBSObject;

  @InjectMocks private JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jDBCBasicDataTypeCache;

  /**
   * Test {@link JDBCBasicDataTypeCache#JDBCBasicDataTypeCache(DBSObject)}.
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#JDBCBasicDataTypeCache(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCBasicDataTypeCache.<init>(DBSObject)"})
  public void testNewJDBCBasicDataTypeCache() {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    JDBCBasicDataTypeCache<DBSObject, JDBCDataType> actualJdbcBasicDataTypeCache =
        new JDBCBasicDataTypeCache<>(dbsDocumentConstraint);

    // Assert
    DBSObject dbsObject = actualJdbcBasicDataTypeCache.owner;
    assertTrue(dbsObject instanceof DBSDocumentConstraint);
    assertEquals("DocumentKey", dbsObject.getName());
    assertEquals("JDBCBasicDataTypeCache", actualJdbcBasicDataTypeCache.getCacheName());
    assertNull(dbsObject.getDescription());
    assertNull(actualJdbcBasicDataTypeCache.getListOrderComparator());
    assertNull(dbsObject.getDataSource());
    assertEquals(0, actualJdbcBasicDataTypeCache.getCacheSize());
    assertFalse(dbsObject.isPersisted());
    assertFalse(actualJdbcBasicDataTypeCache.isFullyCached());
    assertTrue(actualJdbcBasicDataTypeCache.getCachedObjects().isEmpty());
    assertTrue(actualJdbcBasicDataTypeCache.ignoredTypes.isEmpty());
    assertTrue(actualJdbcBasicDataTypeCache.isEmpty());
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#prepareObjectsStatement(JDBCSession, DBSObject)}.
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#prepareObjectsStatement(JDBCSession,
   * DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCStatement JDBCBasicDataTypeCache.prepareObjectsStatement(JDBCSession, DBSObject)"
  })
  public void testPrepareObjectsStatement() throws SQLException {
    // Arrange
    JDBCDatabaseMetaData jdbcDatabaseMetaData = mock(JDBCDatabaseMetaData.class);
    when(jdbcDatabaseMetaData.getTypeInfo()).thenThrow(new SQLException());

    JDBCSession session = mock(JDBCSession.class);
    when(session.getMetaData()).thenReturn(jdbcDatabaseMetaData);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jDBCBasicDataTypeCache.prepareObjectsStatement(session, dBSObject));
    verify(jdbcDatabaseMetaData).getTypeInfo();
    verify(session).getMetaData();
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#prepareObjectsStatement(JDBCSession, DBSObject)}.
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#prepareObjectsStatement(JDBCSession,
   * DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCStatement JDBCBasicDataTypeCache.prepareObjectsStatement(JDBCSession, DBSObject)"
  })
  public void testPrepareObjectsStatement2() throws SQLException {
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

    JDBCDatabaseMetaData jdbcDatabaseMetaData = mock(JDBCDatabaseMetaData.class);
    when(jdbcDatabaseMetaData.getTypeInfo()).thenReturn(jdbcResultSetCallable);

    JDBCSession session = mock(JDBCSession.class);
    when(session.getMetaData()).thenReturn(jdbcDatabaseMetaData);

    // Act
    JDBCStatement actualPrepareObjectsStatementResult =
        jDBCBasicDataTypeCache.prepareObjectsStatement(session, dBSObject);

    // Assert
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(jdbcDatabaseMetaData).getTypeInfo();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    verify(session).getMetaData();
    assertSame(statement, actualPrepareObjectsStatementResult);
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#prepareObjectsStatement(JDBCSession, DBSObject)}.
   *
   * <ul>
   *   <li>Given {@link SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#prepareObjectsStatement(JDBCSession,
   * DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCStatement JDBCBasicDataTypeCache.prepareObjectsStatement(JDBCSession, DBSObject)"
  })
  public void testPrepareObjectsStatement_givenSQLException() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    when(session.getMetaData()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        SQLException.class,
        () -> jDBCBasicDataTypeCache.prepareObjectsStatement(session, dBSObject));
    verify(session).getMetaData();
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject, JDBCResultSet)}.
   *
   * <ul>
   *   <li>Given {@link CallableStatement} {@link CallableStatement#getInt(String)} return {@code
   *       2000}.
   *   <li>Then return TypeID is {@code 2000}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject,
   * JDBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.fetchObject(JDBCSession, DBSObject, JDBCResultSet)"
  })
  public void testFetchObject_givenCallableStatementGetIntReturn2000_thenReturnTypeIDIs2000()
      throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(false);
    when(callableStatement.getInt(Mockito.<String>any())).thenReturn(2000);
    when(callableStatement.getString(Mockito.<String>any())).thenReturn("String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCDataType actualFetchObjectResult =
        jDBCBasicDataTypeCache.fetchObject(session, dBSObject, dbResult);

    // Assert
    verify(callableStatement).getBoolean("UNSIGNED_ATTRIBUTE");
    verify(callableStatement, atLeast(1)).getInt(Mockito.<String>any());
    verify(callableStatement, atLeast(1)).getString(Mockito.<String>any());
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(2000, actualFetchObjectResult.getPrecision().intValue());
    assertEquals(2000, actualFetchObjectResult.getScale().intValue());
    assertEquals(2000, actualFetchObjectResult.getMaxScale());
    assertEquals(2000, actualFetchObjectResult.getMinScale());
    assertEquals(2000, actualFetchObjectResult.getTypeID());
    assertEquals(2000L, actualFetchObjectResult.getMaxLength());
    assertEquals(DBPDataKind.UNKNOWN, actualFetchObjectResult.getDataKind());
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject, JDBCResultSet)}.
   *
   * <ul>
   *   <li>Given {@link CallableStatement} {@link CallableStatement#getString(String)} return empty
   *       string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject,
   * JDBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.fetchObject(JDBCSession, DBSObject, JDBCResultSet)"
  })
  public void testFetchObject_givenCallableStatementGetStringReturnEmptyString_thenReturnNull()
      throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getString(Mockito.<String>any())).thenReturn("");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCDataType actualFetchObjectResult =
        jDBCBasicDataTypeCache.fetchObject(session, dBSObject, dbResult);

    // Assert
    verify(callableStatement).getString("TYPE_NAME");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertNull(actualFetchObjectResult);
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject, JDBCResultSet)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getId()} return {@code
   *       42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject,
   * JDBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.fetchObject(JDBCSession, DBSObject, JDBCResultSet)"
  })
  public void testFetchObject_givenDBPDataSourceContainerGetIdReturn42_thenCallsGetId()
      throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getId()).thenReturn("42");

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCSession session2 = mock(JDBCSession.class);
    when(session2.getDataSource()).thenReturn(jdbcDataSource);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getString(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(session2, statement);

    // Act
    JDBCDataType actualFetchObjectResult =
        jDBCBasicDataTypeCache.fetchObject(session, dBSObject, dbResult);

    // Assert
    verify(callableStatement).getString("TYPE_NAME");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(dbpDataSourceContainer).getId();
    verify(stmtSupplier).get();
    verify(session2).getDataSource();
    verify(connection).getDataSource();
    verify(jdbcDataSource).getContainer();
    assertNull(actualFetchObjectResult);
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject, JDBCResultSet)}.
   *
   * <ul>
   *   <li>Then return Description is {@code varchar}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject,
   * JDBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.fetchObject(JDBCSession, DBSObject, JDBCResultSet)"
  })
  public void testFetchObject_thenReturnDescriptionIsVarchar() throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(false);
    when(callableStatement.getInt(Mockito.<String>any())).thenReturn(-2);
    when(callableStatement.getString(Mockito.<String>any())).thenReturn("varchar");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCDataType actualFetchObjectResult =
        jDBCBasicDataTypeCache.fetchObject(session, dBSObject, dbResult);

    // Assert
    verify(callableStatement).getBoolean("UNSIGNED_ATTRIBUTE");
    verify(callableStatement, atLeast(1)).getInt(Mockito.<String>any());
    verify(callableStatement, atLeast(1)).getString(Mockito.<String>any());
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals("varchar", actualFetchObjectResult.getDescription());
    assertEquals("varchar", actualFetchObjectResult.getFullTypeName());
    assertEquals("varchar", actualFetchObjectResult.getName());
    assertEquals("varchar", actualFetchObjectResult.getTypeName());
    assertEquals(-2, actualFetchObjectResult.getPrecision().intValue());
    assertEquals(-2, actualFetchObjectResult.getScale().intValue());
    assertEquals(-2, actualFetchObjectResult.getMaxScale());
    assertEquals(-2, actualFetchObjectResult.getMinScale());
    assertEquals(-2L, actualFetchObjectResult.getMaxLength());
    assertEquals(12, actualFetchObjectResult.getTypeID());
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject, JDBCResultSet)}.
   *
   * <ul>
   *   <li>Then return Description is {@code varchar}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject,
   * JDBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.fetchObject(JDBCSession, DBSObject, JDBCResultSet)"
  })
  public void testFetchObject_thenReturnDescriptionIsVarchar2() throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(false);
    when(callableStatement.getInt(Mockito.<String>any())).thenReturn(2000);
    when(callableStatement.getString(Mockito.<String>any())).thenReturn("varchar");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCDataType actualFetchObjectResult =
        jDBCBasicDataTypeCache.fetchObject(session, dBSObject, dbResult);

    // Assert
    verify(callableStatement).getBoolean("UNSIGNED_ATTRIBUTE");
    verify(callableStatement, atLeast(1)).getInt(Mockito.<String>any());
    verify(callableStatement, atLeast(1)).getString(Mockito.<String>any());
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals("varchar", actualFetchObjectResult.getDescription());
    assertEquals("varchar", actualFetchObjectResult.getFullTypeName());
    assertEquals("varchar", actualFetchObjectResult.getName());
    assertEquals("varchar", actualFetchObjectResult.getTypeName());
    assertEquals(12, actualFetchObjectResult.getTypeID());
    assertEquals(2000, actualFetchObjectResult.getPrecision().intValue());
    assertEquals(2000, actualFetchObjectResult.getScale().intValue());
    assertEquals(2000, actualFetchObjectResult.getMaxScale());
    assertEquals(2000, actualFetchObjectResult.getMinScale());
    assertEquals(2000L, actualFetchObjectResult.getMaxLength());
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject, JDBCResultSet)}.
   *
   * <ul>
   *   <li>Then return Precision intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject,
   * JDBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.fetchObject(JDBCSession, DBSObject, JDBCResultSet)"
  })
  public void testFetchObject_thenReturnPrecisionIntValueIsOne() throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(false);
    when(callableStatement.getInt(Mockito.<String>any())).thenReturn(1);
    when(callableStatement.getString(Mockito.<String>any())).thenReturn("String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCDataType actualFetchObjectResult =
        jDBCBasicDataTypeCache.fetchObject(session, dBSObject, dbResult);

    // Assert
    verify(callableStatement).getBoolean("UNSIGNED_ATTRIBUTE");
    verify(callableStatement, atLeast(1)).getInt(Mockito.<String>any());
    verify(callableStatement, atLeast(1)).getString(Mockito.<String>any());
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(1, actualFetchObjectResult.getPrecision().intValue());
    assertEquals(1, actualFetchObjectResult.getScale().intValue());
    assertEquals(1, actualFetchObjectResult.getMaxScale());
    assertEquals(1, actualFetchObjectResult.getMinScale());
    assertEquals(1, actualFetchObjectResult.getTypeID());
    assertEquals(1L, actualFetchObjectResult.getMaxLength());
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject, JDBCResultSet)}.
   *
   * <ul>
   *   <li>Then return Precision intValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject,
   * JDBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.fetchObject(JDBCSession, DBSObject, JDBCResultSet)"
  })
  public void testFetchObject_thenReturnPrecisionIntValueIsZero() throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(true);
    when(callableStatement.getInt(Mockito.<String>any())).thenReturn(0);
    when(callableStatement.getString(Mockito.<String>any())).thenReturn("String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCDataType actualFetchObjectResult =
        jDBCBasicDataTypeCache.fetchObject(session, dBSObject, dbResult);

    // Assert
    verify(callableStatement).getBoolean("UNSIGNED_ATTRIBUTE");
    verify(callableStatement, atLeast(1)).getInt(Mockito.<String>any());
    verify(callableStatement, atLeast(1)).getString(Mockito.<String>any());
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(0, actualFetchObjectResult.getPrecision().intValue());
    assertEquals(0, actualFetchObjectResult.getScale().intValue());
    assertEquals(0, actualFetchObjectResult.getMaxScale());
    assertEquals(0, actualFetchObjectResult.getMinScale());
    assertEquals(0, actualFetchObjectResult.getTypeID());
    assertEquals(0L, actualFetchObjectResult.getMaxLength());
    assertFalse(actualFetchObjectResult.isSearchable());
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject, JDBCResultSet)}.
   *
   * <ul>
   *   <li>Then return TypeID is minus two.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject,
   * JDBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.fetchObject(JDBCSession, DBSObject, JDBCResultSet)"
  })
  public void testFetchObject_thenReturnTypeIDIsMinusTwo() throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterMode(anyInt())).thenReturn(1);
    when(parameterMetaData.getParameterCount()).thenReturn(3);

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(false);
    when(callableStatement.getInt(Mockito.<String>any())).thenReturn(-2);
    when(callableStatement.getString(Mockito.<String>any())).thenReturn("String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCDataType actualFetchObjectResult =
        jDBCBasicDataTypeCache.fetchObject(session, dBSObject, dbResult);

    // Assert
    verify(callableStatement).getBoolean("UNSIGNED_ATTRIBUTE");
    verify(callableStatement, atLeast(1)).getInt(Mockito.<String>any());
    verify(callableStatement, atLeast(1)).getString(Mockito.<String>any());
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(parameterMetaData, atLeast(1)).getParameterMode(anyInt());
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection, atLeast(1)).getDataSource();
    assertEquals(-2, actualFetchObjectResult.getPrecision().intValue());
    assertEquals(-2, actualFetchObjectResult.getScale().intValue());
    assertEquals(-2, actualFetchObjectResult.getMaxScale());
    assertEquals(-2, actualFetchObjectResult.getMinScale());
    assertEquals(-2, actualFetchObjectResult.getTypeID());
    assertEquals(-2L, actualFetchObjectResult.getMaxLength());
    assertEquals(DBPDataKind.BINARY, actualFetchObjectResult.getDataKind());
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject, JDBCResultSet)}.
   *
   * <ul>
   *   <li>Then return Unsigned.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject,
   * JDBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.fetchObject(JDBCSession, DBSObject, JDBCResultSet)"
  })
  public void testFetchObject_thenReturnUnsigned() throws SQLException, DBException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(true);
    when(callableStatement.getInt(Mockito.<String>any())).thenReturn(1);
    when(callableStatement.getString(Mockito.<String>any())).thenReturn("String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCDataType actualFetchObjectResult =
        jDBCBasicDataTypeCache.fetchObject(session, dBSObject, dbResult);

    // Assert
    verify(callableStatement).getBoolean("UNSIGNED_ATTRIBUTE");
    verify(callableStatement, atLeast(1)).getInt(Mockito.<String>any());
    verify(callableStatement, atLeast(1)).getString(Mockito.<String>any());
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals(1, actualFetchObjectResult.getPrecision().intValue());
    assertEquals(1, actualFetchObjectResult.getScale().intValue());
    assertEquals(1, actualFetchObjectResult.getMaxScale());
    assertEquals(1, actualFetchObjectResult.getMinScale());
    assertEquals(1, actualFetchObjectResult.getTypeID());
    assertEquals(1L, actualFetchObjectResult.getMaxLength());
    assertTrue(actualFetchObjectResult.isUnsigned());
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject, JDBCResultSet)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#fetchObject(JDBCSession, DBSObject,
   * JDBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.fetchObject(JDBCSession, DBSObject, JDBCResultSet)"
  })
  public void testFetchObject_whenNull_thenReturnNull() throws SQLException, DBException {
    // Arrange, Act and Assert
    assertNull(jDBCBasicDataTypeCache.fetchObject(mock(JDBCSession.class), dBSObject, null));
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#makeDataType(JDBCResultSet, String, int)} with {@code
   * dbResult}, {@code name}, {@code valueType}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#makeDataType(JDBCResultSet, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.makeDataType(JDBCResultSet, String, int)"
  })
  public void testMakeDataTypeWithDbResultNameValueType_thenCallsGetId() throws SQLException {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jdbcBasicDataTypeCache =
        new JDBCBasicDataTypeCache<>(dbsDocumentConstraint);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getId()).thenReturn("42");

    JDBCDataSource jdbcDataSource = mock(JDBCDataSource.class);
    when(jdbcDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    JDBCSession session = mock(JDBCSession.class);
    when(session.getDataSource()).thenReturn(jdbcDataSource);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getInt(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getString(Mockito.<String>any())).thenThrow(new SQLException());
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);

    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(session, statement);

    // Act
    JDBCDataType actualMakeDataTypeResult =
        jdbcBasicDataTypeCache.makeDataType(dbResult, "Name", 42);

    // Assert
    verify(callableStatement).getBoolean("UNSIGNED_ATTRIBUTE");
    verify(callableStatement, atLeast(1)).getInt(Mockito.<String>any());
    verify(callableStatement).getString("LOCAL_TYPE_NAME");
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(dbpDataSourceContainer, atLeast(1)).getId();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    verify(session, atLeast(1)).getDataSource();
    verify(jdbcDataSource, atLeast(1)).getContainer();
    assertNull(actualMakeDataTypeResult.getDescription());
    assertEquals(0, actualMakeDataTypeResult.getPrecision().intValue());
    assertEquals(0, actualMakeDataTypeResult.getScale().intValue());
    assertEquals(0, actualMakeDataTypeResult.getMaxScale());
    assertEquals(0, actualMakeDataTypeResult.getMinScale());
    assertEquals(0L, actualMakeDataTypeResult.getMaxLength());
    assertFalse(actualMakeDataTypeResult.isSearchable());
    assertFalse(actualMakeDataTypeResult.isUnsigned());
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#makeDataType(JDBCResultSet, String, int)} with {@code
   * dbResult}, {@code name}, {@code valueType}.
   *
   * <ul>
   *   <li>Then return Description is {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#makeDataType(JDBCResultSet, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.makeDataType(JDBCResultSet, String, int)"
  })
  public void testMakeDataTypeWithDbResultNameValueType_thenReturnDescriptionIsString()
      throws SQLException {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jdbcBasicDataTypeCache =
        new JDBCBasicDataTypeCache<>(dbsDocumentConstraint);

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    ParameterMetaData parameterMetaData = mock(ParameterMetaData.class);
    when(parameterMetaData.getParameterCount()).thenThrow(new SQLException());

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getBoolean(Mockito.<String>any())).thenReturn(true);
    when(callableStatement.getInt(Mockito.<String>any())).thenReturn(1);
    when(callableStatement.getString(Mockito.<String>any())).thenReturn("String");
    when(callableStatement.getParameterMetaData()).thenReturn(parameterMetaData);

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable dbResult = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act
    JDBCDataType actualMakeDataTypeResult =
        jdbcBasicDataTypeCache.makeDataType(dbResult, "Name", 42);

    // Assert
    verify(callableStatement).getBoolean("UNSIGNED_ATTRIBUTE");
    verify(callableStatement, atLeast(1)).getInt(Mockito.<String>any());
    verify(callableStatement).getString("LOCAL_TYPE_NAME");
    verify(parameterMetaData, atLeast(1)).getParameterCount();
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
    assertEquals("String", actualMakeDataTypeResult.getDescription());
    assertEquals(1, actualMakeDataTypeResult.getPrecision().intValue());
    assertEquals(1, actualMakeDataTypeResult.getScale().intValue());
    assertEquals(1, actualMakeDataTypeResult.getMaxScale());
    assertEquals(1, actualMakeDataTypeResult.getMinScale());
    assertEquals(1L, actualMakeDataTypeResult.getMaxLength());
    assertTrue(actualMakeDataTypeResult.isSearchable());
    assertTrue(actualMakeDataTypeResult.isUnsigned());
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#makeDataType(JDBCResultSet, String, int)} with {@code
   * dbResult}, {@code name}, {@code valueType}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Description is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#makeDataType(JDBCResultSet, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "JDBCDataType JDBCBasicDataTypeCache.makeDataType(JDBCResultSet, String, int)"
  })
  public void testMakeDataTypeWithDbResultNameValueType_whenNull_thenReturnDescriptionIsNull() {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jdbcBasicDataTypeCache =
        new JDBCBasicDataTypeCache<>(dbsDocumentConstraint);

    // Act
    JDBCDataType actualMakeDataTypeResult = jdbcBasicDataTypeCache.makeDataType(null, "Name", 42);

    // Assert
    assertNull(actualMakeDataTypeResult.getDescription());
    assertEquals(0, actualMakeDataTypeResult.getPrecision().intValue());
    assertEquals(0, actualMakeDataTypeResult.getScale().intValue());
    assertEquals(0, actualMakeDataTypeResult.getMaxScale());
    assertEquals(0, actualMakeDataTypeResult.getMinScale());
    assertEquals(0L, actualMakeDataTypeResult.getMaxLength());
    assertFalse(actualMakeDataTypeResult.isSearchable());
    assertFalse(actualMakeDataTypeResult.isUnsigned());
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#fillStandardTypes(DBSObject)}.
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#fillStandardTypes(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCBasicDataTypeCache.fillStandardTypes(DBSObject)"})
  public void testFillStandardTypes() {
    // Arrange and Act
    jDBCBasicDataTypeCache.fillStandardTypes(dBSObject);

    // Assert
    assertEquals(18, jDBCBasicDataTypeCache.getCachedObjects().size());
    assertEquals(18, jDBCBasicDataTypeCache.getCacheSize());
    assertFalse(jDBCBasicDataTypeCache.isEmpty());
    assertTrue(jDBCBasicDataTypeCache.isFullyCached());
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#getCachedObject(int)} with {@code typeID}.
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#getCachedObject(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSDataType JDBCBasicDataTypeCache.getCachedObject(int)"})
  public void testGetCachedObjectWithTypeID() {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jdbcBasicDataTypeCache =
        new JDBCBasicDataTypeCache<>(dbsDocumentConstraint);

    // Act and Assert
    assertNull(jdbcBasicDataTypeCache.getCachedObject(1));
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#getCachedObject(int)} with {@code typeID}.
   *
   * <ul>
   *   <li>Given {@link JDBCDataType} {@link JDBCDataType#getTypeID()} return one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#getCachedObject(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSDataType JDBCBasicDataTypeCache.getCachedObject(int)"})
  public void testGetCachedObjectWithTypeID_givenJDBCDataTypeGetTypeIDReturnOne() {
    // Arrange
    JDBCDataType jdbcDataType = mock(JDBCDataType.class);
    when(jdbcDataType.getTypeID()).thenReturn(1);
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jdbcBasicDataTypeCache =
        new JDBCBasicDataTypeCache<>(dbsDocumentConstraint);
    jdbcBasicDataTypeCache.cacheObject(jdbcDataType);

    // Act
    jdbcBasicDataTypeCache.getCachedObject(1);

    // Assert
    verify(jdbcDataType).getTypeID();
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#getCachedObject(int)} with {@code typeID}.
   *
   * <ul>
   *   <li>Given {@link JDBCDataType} {@link JDBCDataType#getTypeID()} return zero.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#getCachedObject(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSDataType JDBCBasicDataTypeCache.getCachedObject(int)"})
  public void testGetCachedObjectWithTypeID_givenJDBCDataTypeGetTypeIDReturnZero() {
    // Arrange
    JDBCDataType jdbcDataType = mock(JDBCDataType.class);
    when(jdbcDataType.getTypeID()).thenReturn(0);
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jdbcBasicDataTypeCache =
        new JDBCBasicDataTypeCache<>(dbsDocumentConstraint);
    jdbcBasicDataTypeCache.cacheObject(jdbcDataType);

    // Act
    DBSDataType actualCachedObject = jdbcBasicDataTypeCache.getCachedObject(1);

    // Assert
    verify(jdbcDataType).getTypeID();
    assertNull(actualCachedObject);
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#isValidDuplicateObject(JDBCDataType)} with {@code
   * JDBCDataType}.
   *
   * <ul>
   *   <li>Given {@code BOOLEAN}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#isValidDuplicateObject(JDBCDataType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCBasicDataTypeCache.isValidDuplicateObject(JDBCDataType)"})
  public void testIsValidDuplicateObjectWithJDBCDataType_givenBoolean_thenReturnTrue() {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jdbcBasicDataTypeCache =
        new JDBCBasicDataTypeCache<>(dbsDocumentConstraint);

    JDBCDataType jdbcDataType = mock(JDBCDataType.class);
    when(jdbcDataType.getDataKind()).thenReturn(DBPDataKind.BOOLEAN);

    // Act
    boolean actualIsValidDuplicateObjectResult =
        jdbcBasicDataTypeCache.isValidDuplicateObject(jdbcDataType);

    // Assert
    verify(jdbcDataType).getDataKind();
    assertTrue(actualIsValidDuplicateObjectResult);
  }

  /**
   * Test {@link JDBCBasicDataTypeCache#isValidDuplicateObject(JDBCDataType)} with {@code
   * JDBCDataType}.
   *
   * <ul>
   *   <li>Given {@link DBPDataKind#UNKNOWN}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCBasicDataTypeCache#isValidDuplicateObject(JDBCDataType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCBasicDataTypeCache.isValidDuplicateObject(JDBCDataType)"})
  public void testIsValidDuplicateObjectWithJDBCDataType_givenUnknown_thenReturnFalse() {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jdbcBasicDataTypeCache =
        new JDBCBasicDataTypeCache<>(dbsDocumentConstraint);

    JDBCDataType jdbcDataType = mock(JDBCDataType.class);
    when(jdbcDataType.getDataKind()).thenReturn(DBPDataKind.UNKNOWN);

    // Act
    boolean actualIsValidDuplicateObjectResult =
        jdbcBasicDataTypeCache.isValidDuplicateObject(jdbcDataType);

    // Assert
    verify(jdbcDataType).getDataKind();
    assertFalse(actualIsValidDuplicateObjectResult);
  }
}
