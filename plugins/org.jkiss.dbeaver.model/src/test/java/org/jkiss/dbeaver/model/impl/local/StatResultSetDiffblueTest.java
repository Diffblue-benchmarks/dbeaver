package org.jkiss.dbeaver.model.impl.local;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class StatResultSetDiffblueTest {
  /**
   * Test {@link StatResultSet#StatResultSet(DBCSession, DBCStatement)}.
   *
   * <p>Method under test: {@link StatResultSet#StatResultSet(DBCSession, DBCStatement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StatResultSet.<init>(DBCSession, DBCStatement)"})
  public void testNewStatResultSet() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement statement = new LocalStatement(mock(DBCSession.class), "Text");

    // Act
    StatResultSet actualStatResultSet = new StatResultSet(session, statement);

    // Assert
    assertTrue(actualStatResultSet.rows.isEmpty());
    assertSame(statement, actualStatResultSet.getSourceStatement());
    assertSame(session, actualStatResultSet.getSession());
  }
}
