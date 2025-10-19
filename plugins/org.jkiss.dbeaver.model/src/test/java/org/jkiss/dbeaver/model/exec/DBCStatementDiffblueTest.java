package org.jkiss.dbeaver.model.exec;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBCStatementDiffblueTest {
  /**
   * Test {@link DBCStatement#executeStatementBatch()}.
   *
   * <p>Method under test: {@link DBCStatement#executeStatementBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long[] DBCStatement.executeStatementBatch()"})
  public void testExecuteStatementBatch() throws DBCException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new long[] {}, new LocalStatement(mock(DBCSession.class), "Text").executeStatementBatch());
  }

  /**
   * Test {@link DBCStatement#openGeneratedKeysResultSet()}.
   *
   * <p>Method under test: {@link DBCStatement#openGeneratedKeysResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.DBCResultSet DBCStatement.openGeneratedKeysResultSet()"
  })
  public void testOpenGeneratedKeysResultSet() throws DBCException {
    // Arrange, Act and Assert
    assertThrows(
        DBCFeatureNotSupportedException.class,
        () -> new LocalStatement(mock(DBCSession.class), "Text").openGeneratedKeysResultSet());
  }

  /**
   * Test {@link DBCStatement#isStatementClosed()}.
   *
   * <p>Method under test: {@link DBCStatement#isStatementClosed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBCStatement.isStatementClosed()"})
  public void testIsStatementClosed() throws DBCException {
    // Arrange, Act and Assert
    assertFalse(new LocalStatement(mock(DBCSession.class), "Text").isStatementClosed());
  }
}
