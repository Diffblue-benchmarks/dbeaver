package org.jkiss.dbeaver.model.impl.jdbc.exec;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCTransactionDiffblueTest {
  /**
   * Test {@link JDBCTransaction#JDBCTransaction(Connection)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>When {@link Connection} {@link Connection#getAutoCommit()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCTransaction#JDBCTransaction(Connection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCTransaction.<init>(Connection)"})
  public void testNewJDBCTransaction_givenFalse_whenConnectionGetAutoCommitReturnFalse()
      throws SQLException {
    // Arrange
    Connection dbCon = mock(Connection.class);
    when(dbCon.getAutoCommit()).thenReturn(false);

    // Act
    new JDBCTransaction(dbCon);

    // Assert
    verify(dbCon).getAutoCommit();
  }

  /**
   * Test {@link JDBCTransaction#JDBCTransaction(Connection)}.
   *
   * <ul>
   *   <li>When {@link Connection} {@link Connection#getAutoCommit()} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCTransaction#JDBCTransaction(Connection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCTransaction.<init>(Connection)"})
  public void testNewJDBCTransaction_whenConnectionGetAutoCommitThrowSQLException()
      throws SQLException {
    // Arrange
    Connection dbCon = mock(Connection.class);
    when(dbCon.getAutoCommit()).thenThrow(new SQLException());

    // Act and Assert
    assertThrows(SQLException.class, () -> new JDBCTransaction(dbCon));
    verify(dbCon).getAutoCommit();
  }

  /**
   * Test {@link JDBCTransaction#JDBCTransaction(Connection)}.
   *
   * <ul>
   *   <li>When {@link Connection} {@link Connection#setAutoCommit(boolean)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JDBCTransaction#JDBCTransaction(Connection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCTransaction.<init>(Connection)"})
  public void testNewJDBCTransaction_whenConnectionSetAutoCommitDoesNothing() throws SQLException {
    // Arrange
    Connection dbCon = mock(Connection.class);
    doNothing().when(dbCon).setAutoCommit(anyBoolean());
    when(dbCon.getAutoCommit()).thenReturn(true);

    // Act
    new JDBCTransaction(dbCon);

    // Assert
    verify(dbCon).getAutoCommit();
    verify(dbCon).setAutoCommit(false);
  }

  /**
   * Test {@link JDBCTransaction#JDBCTransaction(Connection)}.
   *
   * <ul>
   *   <li>When {@link Connection} {@link Connection#setAutoCommit(boolean)} throw {@link
   *       SQLException#SQLException()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCTransaction#JDBCTransaction(Connection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCTransaction.<init>(Connection)"})
  public void testNewJDBCTransaction_whenConnectionSetAutoCommitThrowSQLException()
      throws SQLException {
    // Arrange
    Connection dbCon = mock(Connection.class);
    doThrow(new SQLException()).when(dbCon).setAutoCommit(anyBoolean());
    when(dbCon.getAutoCommit()).thenReturn(true);

    // Act and Assert
    assertThrows(SQLException.class, () -> new JDBCTransaction(dbCon));
    verify(dbCon).getAutoCommit();
    verify(dbCon).setAutoCommit(false);
  }

  /**
   * Test {@link JDBCTransaction#commit()}.
   *
   * <p>Method under test: {@link JDBCTransaction#commit()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCTransaction.commit()"})
  public void testCommit() throws SQLException {
    // Arrange
    Connection dbCon = mock(Connection.class);
    doNothing().when(dbCon).setAutoCommit(anyBoolean());
    when(dbCon.getAutoCommit()).thenReturn(true);
    doNothing().when(dbCon).commit();

    // Act
    new JDBCTransaction(dbCon).commit();

    // Assert
    verify(dbCon).commit();
    verify(dbCon).getAutoCommit();
    verify(dbCon).setAutoCommit(false);
  }

  /**
   * Test {@link JDBCTransaction#rollback()}.
   *
   * <p>Method under test: {@link JDBCTransaction#rollback()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCTransaction.rollback()"})
  public void testRollback() throws SQLException {
    // Arrange
    Connection dbCon = mock(Connection.class);
    doNothing().when(dbCon).setAutoCommit(anyBoolean());
    when(dbCon.getAutoCommit()).thenReturn(true);
    doNothing().when(dbCon).rollback();

    // Act
    new JDBCTransaction(dbCon).rollback();

    // Assert
    verify(dbCon).getAutoCommit();
    verify(dbCon).rollback();
    verify(dbCon).setAutoCommit(false);
  }

  /**
   * Test {@link JDBCTransaction#close()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#getAutoCommit()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCTransaction#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCTransaction.close()"})
  public void testClose_givenConnectionGetAutoCommitReturnFalse() throws SQLException {
    // Arrange
    Connection dbCon = mock(Connection.class);
    when(dbCon.getAutoCommit()).thenReturn(false);
    try (JDBCTransaction jdbcTransaction = new JDBCTransaction(dbCon)) {}

    // Act and Assert
    verify(dbCon).getAutoCommit();
  }

  /**
   * Test {@link JDBCTransaction#close()}.
   *
   * <ul>
   *   <li>Given {@link Connection} {@link Connection#setAutoCommit(boolean)} does nothing.
   *   <li>Then calls {@link Connection#setAutoCommit(boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCTransaction#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCTransaction.close()"})
  public void testClose_givenConnectionSetAutoCommitDoesNothing_thenCallsSetAutoCommit()
      throws SQLException {
    // Arrange
    Connection dbCon = mock(Connection.class);
    doNothing().when(dbCon).setAutoCommit(anyBoolean());
    when(dbCon.getAutoCommit()).thenReturn(true);
    try (JDBCTransaction jdbcTransaction = new JDBCTransaction(dbCon)) {}

    // Act and Assert
    verify(dbCon).getAutoCommit();
    verify(dbCon, atLeast(1)).setAutoCommit(anyBoolean());
  }
}
