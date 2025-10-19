package org.jkiss.dbeaver.model.impl.jdbc.exec;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.SQLException;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.jdbc.JDBCSession;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCFakeStatementImplDiffblueTest {
  /**
   * Test {@link JDBCFakeStatementImpl#JDBCFakeStatementImpl(JDBCSession, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then Original return {@link JDBCVoidStatementImpl}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCFakeStatementImpl#JDBCFakeStatementImpl(JDBCSession, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCFakeStatementImpl.<init>(JDBCSession, String, boolean)"})
  public void testNewJDBCFakeStatementImpl_whenTrue_thenOriginalReturnJDBCVoidStatementImpl()
      throws SQLException, DBCException {
    // Arrange
    JDBCSession connection = mock(JDBCSession.class);

    // Act
    JDBCFakeStatementImpl actualJdbcFakeStatementImpl =
        new JDBCFakeStatementImpl(connection, "Query Text", true);

    // Assert
    assertTrue(actualJdbcFakeStatementImpl.getOriginal() instanceof JDBCVoidStatementImpl);
    assertEquals("Query Text", actualJdbcFakeStatementImpl.getFormattedQuery());
    assertEquals("Query Text", actualJdbcFakeStatementImpl.getQueryString());
    assertNull(actualJdbcFakeStatementImpl.getBlockThread());
    assertNull(actualJdbcFakeStatementImpl.getStatementWarnings());
    assertNull(actualJdbcFakeStatementImpl.getParameterMetaData());
    assertNull(actualJdbcFakeStatementImpl.getGeneratedKeys());
    assertNull(actualJdbcFakeStatementImpl.getMetaData());
    assertNull(actualJdbcFakeStatementImpl.getWarnings());
    assertNull(actualJdbcFakeStatementImpl.getStatementSource());
    assertNull(actualJdbcFakeStatementImpl.getResultSet());
    assertEquals(0, actualJdbcFakeStatementImpl.getFetchDirection());
    assertEquals(0, actualJdbcFakeStatementImpl.getFetchSize());
    assertEquals(0, actualJdbcFakeStatementImpl.getMaxFieldSize());
    assertEquals(0, actualJdbcFakeStatementImpl.getMaxRows());
    assertEquals(0, actualJdbcFakeStatementImpl.getQueryTimeout());
    assertEquals(0, actualJdbcFakeStatementImpl.getResultSetConcurrency());
    assertEquals(0, actualJdbcFakeStatementImpl.getResultSetHoldability());
    assertEquals(0, actualJdbcFakeStatementImpl.getResultSetType());
    assertEquals(0, actualJdbcFakeStatementImpl.getUpdateCount());
    assertEquals(0L, actualJdbcFakeStatementImpl.getLargeMaxRows());
    assertEquals(0L, actualJdbcFakeStatementImpl.getUpdateRowCount());
    assertFalse(actualJdbcFakeStatementImpl.getMoreResults());
    assertFalse(actualJdbcFakeStatementImpl.isCloseOnCompletion());
    assertFalse(actualJdbcFakeStatementImpl.isClosed());
    assertFalse(actualJdbcFakeStatementImpl.isPoolable());
    assertFalse(actualJdbcFakeStatementImpl.isQMLoggingEnabled());
    assertFalse(actualJdbcFakeStatementImpl.isStatementClosed());
    assertTrue(actualJdbcFakeStatementImpl.disableLogging);
    assertSame(connection, actualJdbcFakeStatementImpl.getSession());
    assertSame(connection, actualJdbcFakeStatementImpl.getConnection());
  }

  /**
   * Test {@link JDBCFakeStatementImpl#execute()}.
   *
   * <p>Method under test: {@link JDBCFakeStatementImpl#execute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCFakeStatementImpl.execute()"})
  public void testExecute() throws SQLException {
    // Arrange
    JDBCFakeStatementImpl jdbcFakeStatementImpl =
        new JDBCFakeStatementImpl(mock(JDBCSession.class), "Query Text", true);

    // Act and Assert
    assertFalse(jdbcFakeStatementImpl.execute());
  }

  /**
   * Test {@link JDBCFakeStatementImpl#executeStatement()}.
   *
   * <p>Method under test: {@link JDBCFakeStatementImpl#executeStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCFakeStatementImpl.executeStatement()"})
  public void testExecuteStatement() throws SQLException, DBCException {
    // Arrange
    JDBCFakeStatementImpl jdbcFakeStatementImpl =
        new JDBCFakeStatementImpl(mock(JDBCSession.class), "Query Text", true);

    // Act and Assert
    assertFalse(jdbcFakeStatementImpl.executeStatement());
  }

  /**
   * Test {@link JDBCFakeStatementImpl#executeUpdate()}.
   *
   * <p>Method under test: {@link JDBCFakeStatementImpl#executeUpdate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int JDBCFakeStatementImpl.executeUpdate()"})
  public void testExecuteUpdate() throws SQLException {
    // Arrange
    JDBCFakeStatementImpl jdbcFakeStatementImpl =
        new JDBCFakeStatementImpl(mock(JDBCSession.class), "Query Text", true);

    // Act and Assert
    assertEquals(0, jdbcFakeStatementImpl.executeUpdate());
  }

  /**
   * Test {@link JDBCFakeStatementImpl#executeQuery()}.
   *
   * <p>Method under test: {@link JDBCFakeStatementImpl#executeQuery()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.jdbc.JDBCResultSet JDBCFakeStatementImpl.executeQuery()"
  })
  public void testExecuteQuery() throws SQLException {
    // Arrange
    JDBCFakeStatementImpl jdbcFakeStatementImpl =
        new JDBCFakeStatementImpl(mock(JDBCSession.class), "Query Text", true);

    // Act and Assert
    assertNull(jdbcFakeStatementImpl.executeQuery());
  }

  /**
   * Test {@link JDBCFakeStatementImpl#getResultSet()}.
   *
   * <p>Method under test: {@link JDBCFakeStatementImpl#getResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.jdbc.JDBCResultSet JDBCFakeStatementImpl.getResultSet()"
  })
  public void testGetResultSet() throws SQLException {
    // Arrange
    JDBCFakeStatementImpl jdbcFakeStatementImpl =
        new JDBCFakeStatementImpl(mock(JDBCSession.class), "Query Text", true);

    // Act and Assert
    assertNull(jdbcFakeStatementImpl.getResultSet());
  }
}
