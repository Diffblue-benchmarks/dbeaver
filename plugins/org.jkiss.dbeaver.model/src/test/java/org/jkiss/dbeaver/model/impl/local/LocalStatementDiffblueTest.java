package org.jkiss.dbeaver.model.impl.local;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCResultSet;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LocalStatementDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link LocalStatement#LocalStatement(DBCSession, String)}
   *   <li>{@link LocalStatement#addToBatch()}
   *   <li>{@link LocalStatement#cancelBlock(DBRProgressMonitor, Thread)}
   *   <li>{@link LocalStatement#close()}
   *   <li>{@link LocalStatement#setLimit(long, long)}
   *   <li>{@link LocalStatement#setResultsFetchSize(int)}
   *   <li>{@link LocalStatement#setStatementTimeout(int)}
   *   <li>{@link LocalStatement#getQueryString()}
   *   <li>{@link LocalStatement#getUpdateRowCount()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LocalStatement.<init>(DBCSession, String)",
    "void LocalStatement.addToBatch()",
    "void LocalStatement.cancelBlock(DBRProgressMonitor, Thread)",
    "void LocalStatement.close()",
    "String LocalStatement.getQueryString()",
    "long LocalStatement.getUpdateRowCount()",
    "void LocalStatement.setLimit(long, long)",
    "void LocalStatement.setResultsFetchSize(int)",
    "void LocalStatement.setStatementTimeout(int)"
  })
  public void testGettersAndSetters() throws DBException {
    // Arrange
    DBCSession session = mock(DBCSession.class);

    // Act
    LocalStatement actualLocalStatement = new LocalStatement(session, "Text");
    actualLocalStatement.addToBatch();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    actualLocalStatement.cancelBlock(monitor, new Thread());
    actualLocalStatement.close();
    actualLocalStatement.setLimit(1L, 1L);
    actualLocalStatement.setResultsFetchSize(3);
    actualLocalStatement.setStatementTimeout(10);
    String actualQueryString = actualLocalStatement.getQueryString();
    long actualUpdateRowCount = actualLocalStatement.getUpdateRowCount();

    // Assert
    assertEquals("Text", actualQueryString);
    assertNull(actualLocalStatement.getStatementSource());
    assertEquals(0L, actualUpdateRowCount);
    assertSame(session, actualLocalStatement.getSession());
  }

  /**
   * Test {@link LocalStatement#executeStatement()}.
   *
   * <p>Method under test: {@link LocalStatement#executeStatement()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LocalStatement.executeStatement()"})
  public void testExecuteStatement() throws DBCException {
    // Arrange, Act and Assert
    assertFalse(new LocalStatement(mock(DBCSession.class), "Text").executeStatement());
  }

  /**
   * Test {@link LocalStatement#executeStatementBatch()}.
   *
   * <p>Method under test: {@link LocalStatement#executeStatementBatch()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long[] LocalStatement.executeStatementBatch()"})
  public void testExecuteStatementBatch() throws DBCException {
    // Arrange, Act and Assert
    assertArrayEquals(
        new long[] {}, new LocalStatement(mock(DBCSession.class), "Text").executeStatementBatch());
  }

  /**
   * Test {@link LocalStatement#openResultSet()}.
   *
   * <p>Method under test: {@link LocalStatement#openResultSet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCResultSet LocalStatement.openResultSet()"})
  public void testOpenResultSet() throws DBCException {
    // Arrange
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    // Act
    DBCResultSet actualOpenResultSetResult = localStatement.openResultSet();

    // Assert
    assertTrue(actualOpenResultSetResult instanceof LocalResultSet);
    assertTrue(actualOpenResultSetResult.getMeta() instanceof LocalResultSetMeta);
    DBCStatement sourceStatement = actualOpenResultSetResult.getSourceStatement();
    assertTrue(sourceStatement instanceof LocalStatement);
    assertNull(actualOpenResultSetResult.getResultSetName());
    assertNull(actualOpenResultSetResult.getRowMeta());
    assertEquals(-1, ((LocalResultSet<DBCStatement>) actualOpenResultSetResult).curPosition);
    assertEquals(0, ((LocalResultSet<DBCStatement>) actualOpenResultSetResult).getColumnCount());
    assertTrue(((LocalResultSet<DBCStatement>) actualOpenResultSetResult).rows.isEmpty());
    assertSame(localStatement, sourceStatement);
  }

  /**
   * Test {@link LocalStatement#nextResults()}.
   *
   * <p>Method under test: {@link LocalStatement#nextResults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LocalStatement.nextResults()"})
  public void testNextResults() throws DBCException {
    // Arrange, Act and Assert
    assertFalse(new LocalStatement(mock(DBCSession.class), "Text").nextResults());
  }

  /**
   * Test {@link LocalStatement#getStatementWarnings()}.
   *
   * <p>Method under test: {@link LocalStatement#getStatementWarnings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Throwable[] LocalStatement.getStatementWarnings()"})
  public void testGetStatementWarnings() throws DBCException {
    // Arrange, Act and Assert
    assertEquals(
        0, new LocalStatement(mock(DBCSession.class), "Text").getStatementWarnings().length);
  }
}
