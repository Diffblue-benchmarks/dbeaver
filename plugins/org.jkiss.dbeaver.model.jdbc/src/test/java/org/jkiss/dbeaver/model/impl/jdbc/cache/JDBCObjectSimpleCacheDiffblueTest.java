package org.jkiss.dbeaver.model.impl.jdbc.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
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
import java.sql.SQLException;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCObjectSupplier;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCPreparedStatement;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCResultSet;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.jkiss.dbeaver.model.impl.jdbc.JDBCDataSource;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCCallableStatementImpl;
import org.jkiss.dbeaver.model.impl.jdbc.exec.JDBCResultSetCallable;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JDBCObjectSimpleCacheDiffblueTest {
  @InjectMocks private JDBCObjectSimpleCache<DBSObject, DBSObject> jDBCObjectSimpleCache;

  /**
   * Test {@link JDBCObjectSimpleCache#JDBCObjectSimpleCache(Class, String, Object[])}.
   *
   * <p>Method under test: {@link JDBCObjectSimpleCache#JDBCObjectSimpleCache(Class, String,
   * Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCObjectSimpleCache.<init>(Class, String, Object[])"})
  public void testNewJDBCObjectSimpleCache() {
    // Arrange
    Class<DBSObject> objectType = DBSObject.class;

    // Act
    JDBCObjectSimpleCache<DBSObject, DBSObject> actualJdbcObjectSimpleCache =
        new JDBCObjectSimpleCache<>(objectType, "Query", "Args");

    // Assert
    assertEquals("JDBCObjectSimpleCache", actualJdbcObjectSimpleCache.getCacheName());
    assertNull(actualJdbcObjectSimpleCache.getListOrderComparator());
    assertEquals(0, actualJdbcObjectSimpleCache.getCacheSize());
    assertFalse(actualJdbcObjectSimpleCache.isFullyCached());
    assertTrue(actualJdbcObjectSimpleCache.getCachedObjects().isEmpty());
    assertTrue(actualJdbcObjectSimpleCache.isEmpty());
  }

  /**
   * Test {@link JDBCObjectSimpleCache#prepareObjectsStatement(JDBCSession, DBSObject)}.
   *
   * <p>Method under test: {@link JDBCObjectSimpleCache#prepareObjectsStatement(JDBCSession,
   * DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.jdbc.JDBCStatement JDBCObjectSimpleCache.prepareObjectsStatement(JDBCSession, DBSObject)"
  })
  public void testPrepareObjectsStatement() throws SQLException {
    // Arrange
    Class<DBSObject> objectType = DBSObject.class;
    JDBCObjectSimpleCache<DBSObject, DBSObject> jdbcObjectSimpleCache =
        new JDBCObjectSimpleCache<>(objectType, "Query", null);

    JDBCSession session = mock(JDBCSession.class);
    when(session.prepareStatement(Mockito.<String>any()))
        .thenReturn(mock(JDBCPreparedStatement.class));

    // Act
    jdbcObjectSimpleCache.prepareObjectsStatement(
        session, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    verify(session).prepareStatement("Query");
  }

  /**
   * Test {@link JDBCObjectSimpleCache#prepareObjectsStatement(JDBCSession, DBSObject)}.
   *
   * <ul>
   *   <li>Given {@link JDBCObjectSimpleCache}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectSimpleCache#prepareObjectsStatement(JDBCSession,
   * DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.jdbc.JDBCStatement JDBCObjectSimpleCache.prepareObjectsStatement(JDBCSession, DBSObject)"
  })
  public void testPrepareObjectsStatement_givenJDBCObjectSimpleCache() throws SQLException {
    // Arrange
    JDBCSession session = mock(JDBCSession.class);
    when(session.prepareStatement(Mockito.<String>any())).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jDBCObjectSimpleCache.prepareObjectsStatement(
                session, new DBSDocumentConstraint(mock(DBSDocumentContainer.class))));
    verify(session).prepareStatement(null);
  }

  /**
   * Test {@link JDBCObjectSimpleCache#prepareObjectsStatement(JDBCSession, DBSObject)}.
   *
   * <ul>
   *   <li>Given {@link JDBCPreparedStatement} {@link JDBCPreparedStatement#setObject(int, Object)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectSimpleCache#prepareObjectsStatement(JDBCSession,
   * DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.jdbc.JDBCStatement JDBCObjectSimpleCache.prepareObjectsStatement(JDBCSession, DBSObject)"
  })
  public void testPrepareObjectsStatement_givenJDBCPreparedStatementSetObjectDoesNothing()
      throws SQLException {
    // Arrange
    Class<DBSObject> objectType = DBSObject.class;
    JDBCObjectSimpleCache<DBSObject, DBSObject> jdbcObjectSimpleCache =
        new JDBCObjectSimpleCache<>(objectType, "Query", "Args");

    JDBCPreparedStatement jdbcPreparedStatement = mock(JDBCPreparedStatement.class);
    doNothing().when(jdbcPreparedStatement).setObject(anyInt(), Mockito.<Object>any());

    JDBCSession session = mock(JDBCSession.class);
    when(session.prepareStatement(Mockito.<String>any())).thenReturn(jdbcPreparedStatement);

    // Act
    jdbcObjectSimpleCache.prepareObjectsStatement(
        session, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    verify(jdbcPreparedStatement).setObject(eq(1), isA(Object.class));
    verify(session).prepareStatement("Query");
  }

  /**
   * Test {@link JDBCObjectSimpleCache#prepareObjectsStatement(JDBCSession, DBSObject)}.
   *
   * <ul>
   *   <li>Given {@link JDBCPreparedStatement} {@link JDBCPreparedStatement#setObject(int, Object)}
   *       throw {@link SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectSimpleCache#prepareObjectsStatement(JDBCSession,
   * DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.jdbc.JDBCStatement JDBCObjectSimpleCache.prepareObjectsStatement(JDBCSession, DBSObject)"
  })
  public void testPrepareObjectsStatement_givenJDBCPreparedStatementSetObjectThrowSQLException()
      throws SQLException {
    // Arrange
    Class<DBSObject> objectType = DBSObject.class;
    JDBCObjectSimpleCache<DBSObject, DBSObject> jdbcObjectSimpleCache =
        new JDBCObjectSimpleCache<>(objectType, "Query", "Args");

    JDBCPreparedStatement jdbcPreparedStatement = mock(JDBCPreparedStatement.class);
    doThrow(new SQLException())
        .when(jdbcPreparedStatement)
        .setObject(anyInt(), Mockito.<Object>any());

    JDBCSession session = mock(JDBCSession.class);
    when(session.prepareStatement(Mockito.<String>any())).thenReturn(jdbcPreparedStatement);

    // Act and Assert
    assertThrows(
        SQLException.class,
        () ->
            jdbcObjectSimpleCache.prepareObjectsStatement(
                session, new DBSDocumentConstraint(mock(DBSDocumentContainer.class))));
    verify(jdbcPreparedStatement).setObject(eq(1), isA(Object.class));
    verify(session).prepareStatement("Query");
  }

  /**
   * Test {@link JDBCObjectSimpleCache#fetchObject(JDBCSession, DBSObject, JDBCResultSet)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectSimpleCache#fetchObject(JDBCSession, DBSObject,
   * JDBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject JDBCObjectSimpleCache.fetchObject(JDBCSession, DBSObject, JDBCResultSet)"
  })
  public void testFetchObject_thenThrowDBException() throws SQLException, DBException {
    // Arrange
    Class<DBSObject> objectType = DBSObject.class;
    JDBCObjectSimpleCache<DBSObject, DBSObject> jdbcObjectSimpleCache =
        new JDBCObjectSimpleCache<>(objectType, "Query", "Args");
    JDBCSession session = mock(JDBCSession.class);
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    JDBCSession connection = mock(JDBCSession.class);
    when(connection.getDataSource()).thenReturn(mock(JDBCDataSource.class));

    CallableStatement callableStatement = mock(CallableStatement.class);
    when(callableStatement.getParameterMetaData()).thenThrow(new SQLException());

    JDBCObjectSupplier<CallableStatement> stmtSupplier = mock(JDBCObjectSupplier.class);
    when(stmtSupplier.get()).thenReturn(callableStatement);

    JDBCCallableStatementImpl statement =
        new JDBCCallableStatementImpl(connection, stmtSupplier, "Query", true);
    JDBCResultSetCallable resultSet = new JDBCResultSetCallable(mock(JDBCSession.class), statement);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> jdbcObjectSimpleCache.fetchObject(session, dbsDocumentConstraint, resultSet));
    verify(callableStatement, atLeast(1)).getParameterMetaData();
    verify(stmtSupplier).get();
    verify(connection).getDataSource();
  }
}
