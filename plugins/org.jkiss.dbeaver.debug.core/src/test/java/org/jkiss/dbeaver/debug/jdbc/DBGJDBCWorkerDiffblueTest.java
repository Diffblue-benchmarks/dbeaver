package org.jkiss.dbeaver.debug.jdbc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.debug.DBGEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBGJDBCWorkerDiffblueTest {
  /**
   * Test {@link DBGJDBCWorker#DBGJDBCWorker(DBGJDBCSession, String, String, DBGEvent, DBGEvent)}.
   *
   * <p>Method under test: {@link DBGJDBCWorker#DBGJDBCWorker(DBGJDBCSession, String, String,
   * DBGEvent, DBGEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBGJDBCWorker.<init>(DBGJDBCSession, String, String, DBGEvent, DBGEvent)"
  })
  public void testNewDBGJDBCWorker() {
    // Arrange
    DBGEvent begin = new DBGEvent("Source", 1);

    // Act
    DBGJDBCWorker actualDbgjdbcWorker =
        new DBGJDBCWorker(null, "Name", "Sql Command", begin, new DBGEvent("Source", 1));

    // Assert
    assertEquals(-1L, actualDbgjdbcWorker.getCancelTimestamp());
    assertFalse(actualDbgjdbcWorker.isFinished());
    assertFalse(actualDbgjdbcWorker.isRunDirectly());
  }
}
