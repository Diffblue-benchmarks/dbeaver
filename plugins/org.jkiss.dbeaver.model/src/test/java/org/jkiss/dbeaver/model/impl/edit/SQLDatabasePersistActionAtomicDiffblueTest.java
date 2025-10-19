package org.jkiss.dbeaver.model.impl.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.edit.DBEPersistAction.ActionType;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLDatabasePersistActionAtomicDiffblueTest {
  /**
   * Test {@link SQLDatabasePersistActionAtomic#SQLDatabasePersistActionAtomic(String, String)}.
   *
   * <p>Method under test: {@link
   * SQLDatabasePersistActionAtomic#SQLDatabasePersistActionAtomic(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDatabasePersistActionAtomic.<init>(String, String)"})
  public void testNewSQLDatabasePersistActionAtomic() {
    // Arrange and Act
    SQLDatabasePersistActionAtomic actualSqlDatabasePersistActionAtomic =
        new SQLDatabasePersistActionAtomic("Dr", "Script");

    // Assert
    assertEquals("Dr", actualSqlDatabasePersistActionAtomic.getTitle());
    assertEquals("Script", actualSqlDatabasePersistActionAtomic.getScript());
    assertEquals(ActionType.NORMAL, actualSqlDatabasePersistActionAtomic.getType());
    assertFalse(actualSqlDatabasePersistActionAtomic.isComplex());
  }

  /**
   * Test {@link SQLDatabasePersistActionAtomic#SQLDatabasePersistActionAtomic(String, String,
   * boolean)}.
   *
   * <p>Method under test: {@link
   * SQLDatabasePersistActionAtomic#SQLDatabasePersistActionAtomic(String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDatabasePersistActionAtomic.<init>(String, String, boolean)"})
  public void testNewSQLDatabasePersistActionAtomic2() {
    // Arrange and Act
    SQLDatabasePersistActionAtomic actualSqlDatabasePersistActionAtomic =
        new SQLDatabasePersistActionAtomic("Dr", "Script", true);

    // Assert
    assertEquals("Dr", actualSqlDatabasePersistActionAtomic.getTitle());
    assertEquals("Script", actualSqlDatabasePersistActionAtomic.getScript());
    assertEquals(ActionType.NORMAL, actualSqlDatabasePersistActionAtomic.getType());
    assertFalse(actualSqlDatabasePersistActionAtomic.isComplex());
  }

  /**
   * Test {@link SQLDatabasePersistActionAtomic#beforeExecute(DBCSession)}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionContext} {@link DBCExecutionContext#isConnected()} return {@code
   *       true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDatabasePersistActionAtomic#beforeExecute(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDatabasePersistActionAtomic.beforeExecute(DBCSession)"})
  public void testBeforeExecute_givenDBCExecutionContextIsConnectedReturnTrue()
      throws DBCException {
    // Arrange
    SQLDatabasePersistActionAtomic sqlDatabasePersistActionAtomic =
        new SQLDatabasePersistActionAtomic("Dr", "Script");

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.isConnected()).thenReturn(true);

    DBCSession session = mock(DBCSession.class);
    when(session.getExecutionContext()).thenReturn(dbcExecutionContext);

    // Act
    sqlDatabasePersistActionAtomic.beforeExecute(session);

    // Assert
    verify(dbcExecutionContext).isConnected();
    verify(session).getExecutionContext();
  }

  /**
   * Test {@link SQLDatabasePersistActionAtomic#beforeExecute(DBCSession)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link DBCSession} {@link DBCSession#getExecutionContext()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDatabasePersistActionAtomic#beforeExecute(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDatabasePersistActionAtomic.beforeExecute(DBCSession)"})
  public void testBeforeExecute_givenNull_whenDBCSessionGetExecutionContextReturnNull()
      throws DBCException {
    // Arrange
    SQLDatabasePersistActionAtomic sqlDatabasePersistActionAtomic =
        new SQLDatabasePersistActionAtomic("Dr", "Script");

    DBCSession session = mock(DBCSession.class);
    when(session.getExecutionContext()).thenReturn(null);

    // Act
    sqlDatabasePersistActionAtomic.beforeExecute(session);

    // Assert
    verify(session).getExecutionContext();
  }

  /**
   * Test {@link SQLDatabasePersistActionAtomic#beforeExecute(DBCSession)}.
   *
   * <ul>
   *   <li>Then calls {@link DBCExecutionContext#isConnected()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLDatabasePersistActionAtomic#beforeExecute(DBCSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDatabasePersistActionAtomic.beforeExecute(DBCSession)"})
  public void testBeforeExecute_thenCallsIsConnected() throws DBCException {
    // Arrange
    SQLDatabasePersistActionAtomic sqlDatabasePersistActionAtomic =
        new SQLDatabasePersistActionAtomic("Dr", "Script");

    DBCExecutionContext dbcExecutionContext = mock(DBCExecutionContext.class);
    when(dbcExecutionContext.isConnected()).thenReturn(false);

    DBCSession session = mock(DBCSession.class);
    when(session.getExecutionContext()).thenReturn(dbcExecutionContext);

    // Act
    sqlDatabasePersistActionAtomic.beforeExecute(session);

    // Assert
    verify(dbcExecutionContext).isConnected();
    verify(session).getExecutionContext();
  }
}
