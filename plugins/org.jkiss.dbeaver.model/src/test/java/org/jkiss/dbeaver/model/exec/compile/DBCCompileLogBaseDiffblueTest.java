package org.jkiss.dbeaver.model.exec.compile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DBCCompileLogBaseDiffblueTest {
  @InjectMocks private DBCCompileLogBase dBCCompileLogBase;

  @Mock private List<DBCCompileError> list;

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBCCompileLogBase}
   *   <li>{@link DBCCompileLogBase#getError()}
   *   <li>{@link DBCCompileLogBase#getErrorStack()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBCCompileLogBase.<init>()",
    "Throwable DBCCompileLogBase.getError()",
    "Collection DBCCompileLogBase.getErrorStack()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBCCompileLogBase actualDbcCompileLogBase = new DBCCompileLogBase();
    Throwable actualError = actualDbcCompileLogBase.getError();

    // Assert
    assertTrue(actualDbcCompileLogBase.getErrorStack() instanceof List);
    assertNull(actualError);
  }

  /**
   * Test {@link DBCCompileLogBase#log(int, Object, Throwable)}.
   *
   * <ul>
   *   <li>Then {@link DBCCompileLogBase} (default constructor) ErrorStack size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBCCompileLogBase#log(int, Object, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCCompileLogBase.log(int, Object, Throwable)"})
  public void testLog_thenDBCCompileLogBaseErrorStackSizeIsOne() {
    // Arrange
    DBCCompileLogBase dbcCompileLogBase = new DBCCompileLogBase();
    DBCCompileError dbcCompileError =
        new DBCCompileError(true, "Not all who wander are lost", 2, 1);

    // Act
    dbcCompileLogBase.log(1, dbcCompileError, null);

    // Assert
    Collection<DBCCompileError> errorStack = dbcCompileLogBase.getErrorStack();
    assertEquals(1, errorStack.size());
    assertTrue(errorStack instanceof List);
    assertNull(dbcCompileLogBase.getError());
    assertSame(dbcCompileError, ((List<DBCCompileError>) errorStack).get(0));
  }

  /**
   * Test {@link DBCCompileLogBase#log(int, Object, Throwable)}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then {@link DBCCompileLogBase} (default constructor) ErrorStack Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBCCompileLogBase#log(int, Object, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCCompileLogBase.log(int, Object, Throwable)"})
  public void testLog_whenRename_thenDBCCompileLogBaseErrorStackEmpty() {
    // Arrange
    DBCCompileLogBase dbcCompileLogBase = new DBCCompileLogBase();

    // Act
    dbcCompileLogBase.log(1, DBPEvent.RENAME, null);

    // Assert that nothing has changed
    Collection<DBCCompileError> errorStack = dbcCompileLogBase.getErrorStack();
    assertTrue(errorStack instanceof List);
    assertTrue(errorStack.isEmpty());
  }

  /**
   * Test {@link DBCCompileLogBase#log(int, Object, Throwable)}.
   *
   * <ul>
   *   <li>When {@link Throwable#Throwable()}.
   *   <li>Then {@link DBCCompileLogBase} (default constructor) Error is {@link
   *       Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link DBCCompileLogBase#log(int, Object, Throwable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCCompileLogBase.log(int, Object, Throwable)"})
  public void testLog_whenThrowable_thenDBCCompileLogBaseErrorIsThrowable() {
    // Arrange
    DBCCompileLogBase dbcCompileLogBase = new DBCCompileLogBase();
    Throwable t = new Throwable();

    // Act
    dbcCompileLogBase.log(1, DBPEvent.RENAME, t);

    // Assert
    Collection<DBCCompileError> errorStack = dbcCompileLogBase.getErrorStack();
    assertTrue(errorStack instanceof List);
    assertTrue(errorStack.isEmpty());
    assertSame(t, dbcCompileLogBase.getError());
  }

  /**
   * Test {@link DBCCompileLogBase#warn(DBCCompileError)}.
   *
   * <ul>
   *   <li>Then {@link DBCCompileLogBase} (default constructor) ErrorStack size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBCCompileLogBase#warn(DBCCompileError)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCCompileLogBase.warn(DBCCompileError)"})
  public void testWarn_thenDBCCompileLogBaseErrorStackSizeIsOne() {
    // Arrange
    DBCCompileLogBase dbcCompileLogBase = new DBCCompileLogBase();
    DBCCompileError error = new DBCCompileError(true, "Not all who wander are lost", 2, 1);

    // Act
    dbcCompileLogBase.warn(error);

    // Assert
    Collection<DBCCompileError> errorStack = dbcCompileLogBase.getErrorStack();
    assertEquals(1, errorStack.size());
    assertTrue(errorStack instanceof List);
    assertSame(error, ((List<DBCCompileError>) errorStack).get(0));
  }

  /**
   * Test {@link DBCCompileLogBase#warn(DBCCompileError)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link DBCCompileLogBase} (default constructor) ErrorStack Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBCCompileLogBase#warn(DBCCompileError)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCCompileLogBase.warn(DBCCompileError)"})
  public void testWarn_whenNull_thenDBCCompileLogBaseErrorStackEmpty() {
    // Arrange
    DBCCompileLogBase dbcCompileLogBase = new DBCCompileLogBase();

    // Act
    dbcCompileLogBase.warn(null);

    // Assert that nothing has changed
    Collection<DBCCompileError> errorStack = dbcCompileLogBase.getErrorStack();
    assertTrue(errorStack instanceof List);
    assertTrue(errorStack.isEmpty());
  }

  /**
   * Test {@link DBCCompileLogBase#error(DBCCompileError)}.
   *
   * <ul>
   *   <li>Then {@link DBCCompileLogBase} (default constructor) ErrorStack size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBCCompileLogBase#error(DBCCompileError)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCCompileLogBase.error(DBCCompileError)"})
  public void testError_thenDBCCompileLogBaseErrorStackSizeIsOne() {
    // Arrange
    DBCCompileLogBase dbcCompileLogBase = new DBCCompileLogBase();
    DBCCompileError error = new DBCCompileError(true, "Not all who wander are lost", 2, 1);

    // Act
    dbcCompileLogBase.error(error);

    // Assert
    Collection<DBCCompileError> errorStack = dbcCompileLogBase.getErrorStack();
    assertEquals(1, errorStack.size());
    assertTrue(errorStack instanceof List);
    assertSame(error, ((List<DBCCompileError>) errorStack).get(0));
  }

  /**
   * Test {@link DBCCompileLogBase#error(DBCCompileError)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link DBCCompileLogBase} (default constructor) ErrorStack Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBCCompileLogBase#error(DBCCompileError)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCCompileLogBase.error(DBCCompileError)"})
  public void testError_whenNull_thenDBCCompileLogBaseErrorStackEmpty() {
    // Arrange
    DBCCompileLogBase dbcCompileLogBase = new DBCCompileLogBase();

    // Act
    dbcCompileLogBase.error(null);

    // Assert that nothing has changed
    Collection<DBCCompileError> errorStack = dbcCompileLogBase.getErrorStack();
    assertTrue(errorStack instanceof List);
    assertTrue(errorStack.isEmpty());
  }

  /**
   * Test {@link DBCCompileLogBase#clearLog()}.
   *
   * <p>Method under test: {@link DBCCompileLogBase#clearLog()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBCCompileLogBase.clearLog()"})
  public void testClearLog() {
    // Arrange
    doNothing().when(list).clear();

    // Act
    dBCCompileLogBase.clearLog();

    // Assert
    verify(list).clear();
  }
}
