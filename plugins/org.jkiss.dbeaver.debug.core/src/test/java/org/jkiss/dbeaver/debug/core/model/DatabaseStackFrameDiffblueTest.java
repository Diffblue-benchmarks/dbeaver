package org.jkiss.dbeaver.debug.core.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IThread;
import org.eclipse.debug.core.model.IVariable;
import org.jkiss.dbeaver.debug.DBGException;
import org.jkiss.dbeaver.debug.DBGSession;
import org.jkiss.dbeaver.debug.DBGStackFrame;
import org.jkiss.dbeaver.debug.DBGVariable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DatabaseStackFrameDiffblueTest {
  /**
   * Test {@link DatabaseStackFrame#DatabaseStackFrame(DatabaseThread, DBGStackFrame)}.
   *
   * <ul>
   *   <li>Then Thread return {@link DatabaseThread}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#DatabaseStackFrame(DatabaseThread,
   * DBGStackFrame)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseStackFrame.<init>(DatabaseThread, DBGStackFrame)"})
  public void testNewDatabaseStackFrame_thenThreadReturnDatabaseThread() throws DebugException {
    // Arrange
    DatabaseThread thread = new DatabaseThread(null);

    // Act
    DatabaseStackFrame actualDatabaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Assert
    IThread thread2 = actualDatabaseStackFrame.getThread();
    assertTrue(thread2 instanceof DatabaseThread);
    assertEquals("null line: 0", actualDatabaseStackFrame.getName());
    assertNull(actualDatabaseStackFrame.getSourceIdentifier());
    assertNull(actualDatabaseStackFrame.getDebugTarget());
    assertNull(actualDatabaseStackFrame.getDatabaseDebugTarget());
    assertEquals(-1, actualDatabaseStackFrame.getCharEnd());
    assertEquals(-1, actualDatabaseStackFrame.getCharStart());
    assertEquals(0, actualDatabaseStackFrame.getLineNumber());
    assertEquals(0, actualDatabaseStackFrame.getRegisterGroups().length);
    assertFalse(actualDatabaseStackFrame.hasRegisterGroups());
    assertFalse(actualDatabaseStackFrame.isStepping());
    assertSame(thread, thread2);
  }

  /**
   * Test {@link DatabaseStackFrame#canStepInto()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseDebugTarget} {@link DatabaseDebugTarget#canStepInto()} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#canStepInto()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.canStepInto()"})
  public void testCanStepInto_givenDatabaseDebugTargetCanStepIntoReturnTrue_thenReturnTrue() {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.canStepInto()).thenReturn(true);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualCanStepIntoResult = databaseStackFrame.canStepInto();

    // Assert
    verify(target).canStepInto();
    assertTrue(actualCanStepIntoResult);
  }

  /**
   * Test {@link DatabaseStackFrame#canStepInto()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseThread} {@link DatabaseThread#canStepInto()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#canStepInto()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.canStepInto()"})
  public void testCanStepInto_givenDatabaseThreadCanStepIntoReturnFalse_thenReturnFalse() {
    // Arrange
    DatabaseThread thread = mock(DatabaseThread.class);
    when(thread.canStepInto()).thenReturn(false);
    when(thread.getDatabaseDebugTarget()).thenReturn(null);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualCanStepIntoResult = databaseStackFrame.canStepInto();

    // Assert
    verify(thread).getDatabaseDebugTarget();
    verify(thread).canStepInto();
    assertFalse(actualCanStepIntoResult);
  }

  /**
   * Test {@link DatabaseStackFrame#canStepOver()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseDebugTarget} {@link DatabaseDebugTarget#canStepOver()} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#canStepOver()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.canStepOver()"})
  public void testCanStepOver_givenDatabaseDebugTargetCanStepOverReturnTrue_thenReturnTrue() {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.canStepOver()).thenReturn(true);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualCanStepOverResult = databaseStackFrame.canStepOver();

    // Assert
    verify(target).canStepOver();
    assertTrue(actualCanStepOverResult);
  }

  /**
   * Test {@link DatabaseStackFrame#canStepOver()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseThread} {@link DatabaseThread#canStepOver()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#canStepOver()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.canStepOver()"})
  public void testCanStepOver_givenDatabaseThreadCanStepOverReturnFalse_thenReturnFalse() {
    // Arrange
    DatabaseThread thread = mock(DatabaseThread.class);
    when(thread.canStepOver()).thenReturn(false);
    when(thread.getDatabaseDebugTarget()).thenReturn(null);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualCanStepOverResult = databaseStackFrame.canStepOver();

    // Assert
    verify(thread).getDatabaseDebugTarget();
    verify(thread).canStepOver();
    assertFalse(actualCanStepOverResult);
  }

  /**
   * Test {@link DatabaseStackFrame#canStepReturn()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseDebugTarget} {@link DatabaseDebugTarget#canStepReturn()} return
   *       {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#canStepReturn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.canStepReturn()"})
  public void testCanStepReturn_givenDatabaseDebugTargetCanStepReturnReturnTrue_thenReturnTrue() {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.canStepReturn()).thenReturn(true);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualCanStepReturnResult = databaseStackFrame.canStepReturn();

    // Assert
    verify(target).canStepReturn();
    assertTrue(actualCanStepReturnResult);
  }

  /**
   * Test {@link DatabaseStackFrame#canStepReturn()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseThread} {@link DatabaseThread#canStepReturn()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#canStepReturn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.canStepReturn()"})
  public void testCanStepReturn_givenDatabaseThreadCanStepReturnReturnFalse_thenReturnFalse() {
    // Arrange
    DatabaseThread thread = mock(DatabaseThread.class);
    when(thread.canStepReturn()).thenReturn(false);
    when(thread.getDatabaseDebugTarget()).thenReturn(null);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualCanStepReturnResult = databaseStackFrame.canStepReturn();

    // Assert
    verify(thread).getDatabaseDebugTarget();
    verify(thread).canStepReturn();
    assertFalse(actualCanStepReturnResult);
  }

  /**
   * Test {@link DatabaseStackFrame#isStepping()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseThread#DatabaseThread(IDatabaseDebugTarget)} with target is {@code
   *       null} Stepping is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#isStepping()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.isStepping()"})
  public void testIsStepping_givenDatabaseThreadWithTargetIsNullSteppingIsTrue_thenReturnTrue() {
    // Arrange
    DatabaseThread thread = new DatabaseThread(null);
    thread.setStepping(true);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act and Assert
    assertTrue(databaseStackFrame.isStepping());
  }

  /**
   * Test {@link DatabaseStackFrame#isStepping()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseThread#DatabaseThread(IDatabaseDebugTarget)} with target is {@code
   *       null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#isStepping()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.isStepping()"})
  public void testIsStepping_givenDatabaseThreadWithTargetIsNull_thenReturnFalse() {
    // Arrange
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(new DatabaseThread(null), mock(DBGStackFrame.class));

    // Act and Assert
    assertFalse(databaseStackFrame.isStepping());
  }

  /**
   * Test {@link DatabaseStackFrame#stepInto()}.
   *
   * <ul>
   *   <li>Then calls {@link DatabaseThread#getDatabaseDebugTarget()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#stepInto()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseStackFrame.stepInto()"})
  public void testStepInto_thenCallsGetDatabaseDebugTarget() throws DebugException {
    // Arrange
    DatabaseThread thread = mock(DatabaseThread.class);
    when(thread.getDatabaseDebugTarget()).thenReturn(null);
    doNothing().when(thread).stepInto();
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    databaseStackFrame.stepInto();

    // Assert
    verify(thread).getDatabaseDebugTarget();
    verify(thread).stepInto();
  }

  /**
   * Test {@link DatabaseStackFrame#stepOver()}.
   *
   * <ul>
   *   <li>Then calls {@link DatabaseThread#getDatabaseDebugTarget()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#stepOver()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseStackFrame.stepOver()"})
  public void testStepOver_thenCallsGetDatabaseDebugTarget() throws DebugException {
    // Arrange
    DatabaseThread thread = mock(DatabaseThread.class);
    when(thread.getDatabaseDebugTarget()).thenReturn(null);
    doNothing().when(thread).stepOver();
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    databaseStackFrame.stepOver();

    // Assert
    verify(thread).getDatabaseDebugTarget();
    verify(thread).stepOver();
  }

  /**
   * Test {@link DatabaseStackFrame#stepReturn()}.
   *
   * <ul>
   *   <li>Then calls {@link DatabaseDebugTarget#canStepReturn()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#stepReturn()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseStackFrame.stepReturn()"})
  public void testStepReturn_thenCallsCanStepReturn() throws DebugException {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.canStepReturn()).thenReturn(true);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    databaseStackFrame.stepReturn();

    // Assert
    verify(target).canStepReturn();
  }

  /**
   * Test {@link DatabaseStackFrame#canResume()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseDebugTarget} {@link DatabaseDebugTarget#canResume()} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#canResume()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.canResume()"})
  public void testCanResume_givenDatabaseDebugTargetCanResumeReturnTrue_thenReturnTrue() {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.canResume()).thenReturn(true);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualCanResumeResult = databaseStackFrame.canResume();

    // Assert
    verify(target).canResume();
    assertTrue(actualCanResumeResult);
  }

  /**
   * Test {@link DatabaseStackFrame#canResume()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseThread} {@link DatabaseThread#canResume()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#canResume()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.canResume()"})
  public void testCanResume_givenDatabaseThreadCanResumeReturnFalse_thenReturnFalse() {
    // Arrange
    DatabaseThread thread = mock(DatabaseThread.class);
    when(thread.canResume()).thenReturn(false);
    when(thread.getDatabaseDebugTarget()).thenReturn(null);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualCanResumeResult = databaseStackFrame.canResume();

    // Assert
    verify(thread).getDatabaseDebugTarget();
    verify(thread).canResume();
    assertFalse(actualCanResumeResult);
  }

  /**
   * Test {@link DatabaseStackFrame#canSuspend()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseDebugTarget} {@link DatabaseDebugTarget#canSuspend()} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#canSuspend()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.canSuspend()"})
  public void testCanSuspend_givenDatabaseDebugTargetCanSuspendReturnTrue_thenReturnTrue() {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.canSuspend()).thenReturn(true);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualCanSuspendResult = databaseStackFrame.canSuspend();

    // Assert
    verify(target).canSuspend();
    assertTrue(actualCanSuspendResult);
  }

  /**
   * Test {@link DatabaseStackFrame#canSuspend()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseThread} {@link DatabaseThread#canSuspend()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#canSuspend()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.canSuspend()"})
  public void testCanSuspend_givenDatabaseThreadCanSuspendReturnFalse_thenReturnFalse() {
    // Arrange
    DatabaseThread thread = mock(DatabaseThread.class);
    when(thread.canSuspend()).thenReturn(false);
    when(thread.getDatabaseDebugTarget()).thenReturn(null);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualCanSuspendResult = databaseStackFrame.canSuspend();

    // Assert
    verify(thread).getDatabaseDebugTarget();
    verify(thread).canSuspend();
    assertFalse(actualCanSuspendResult);
  }

  /**
   * Test {@link DatabaseStackFrame#isSuspended()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseDebugTarget} {@link DatabaseDebugTarget#isSuspended()} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#isSuspended()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.isSuspended()"})
  public void testIsSuspended_givenDatabaseDebugTargetIsSuspendedReturnTrue_thenReturnTrue() {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.isSuspended()).thenReturn(true);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualIsSuspendedResult = databaseStackFrame.isSuspended();

    // Assert
    verify(target).isSuspended();
    assertTrue(actualIsSuspendedResult);
  }

  /**
   * Test {@link DatabaseStackFrame#isSuspended()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseThread} {@link DatabaseThread#isSuspended()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#isSuspended()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.isSuspended()"})
  public void testIsSuspended_givenDatabaseThreadIsSuspendedReturnFalse_thenReturnFalse() {
    // Arrange
    DatabaseThread thread = mock(DatabaseThread.class);
    when(thread.isSuspended()).thenReturn(false);
    when(thread.getDatabaseDebugTarget()).thenReturn(null);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualIsSuspendedResult = databaseStackFrame.isSuspended();

    // Assert
    verify(thread).getDatabaseDebugTarget();
    verify(thread).isSuspended();
    assertFalse(actualIsSuspendedResult);
  }

  /**
   * Test {@link DatabaseStackFrame#resume()}.
   *
   * <ul>
   *   <li>Then calls {@link DatabaseThread#getDatabaseDebugTarget()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#resume()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseStackFrame.resume()"})
  public void testResume_thenCallsGetDatabaseDebugTarget() throws DebugException {
    // Arrange
    DatabaseThread thread = mock(DatabaseThread.class);
    when(thread.getDatabaseDebugTarget()).thenReturn(null);
    doNothing().when(thread).resume();
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    databaseStackFrame.resume();

    // Assert
    verify(thread).getDatabaseDebugTarget();
    verify(thread).resume();
  }

  /**
   * Test {@link DatabaseStackFrame#suspend()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseDebugTarget} {@link DatabaseDebugTarget#suspend()} does nothing.
   *   <li>Then calls {@link DatabaseDebugTarget#suspend()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#suspend()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseStackFrame.suspend()"})
  public void testSuspend_givenDatabaseDebugTargetSuspendDoesNothing_thenCallsSuspend()
      throws DebugException {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    doNothing().when(target).suspend();
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    databaseStackFrame.suspend();

    // Assert
    verify(target).suspend();
  }

  /**
   * Test {@link DatabaseStackFrame#canTerminate()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseDebugTarget} {@link DatabaseDebugTarget#canTerminate()} return
   *       {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#canTerminate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.canTerminate()"})
  public void testCanTerminate_givenDatabaseDebugTargetCanTerminateReturnTrue_thenReturnTrue() {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.canTerminate()).thenReturn(true);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualCanTerminateResult = databaseStackFrame.canTerminate();

    // Assert
    verify(target).canTerminate();
    assertTrue(actualCanTerminateResult);
  }

  /**
   * Test {@link DatabaseStackFrame#canTerminate()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseThread} {@link DatabaseThread#canTerminate()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#canTerminate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.canTerminate()"})
  public void testCanTerminate_givenDatabaseThreadCanTerminateReturnFalse_thenReturnFalse() {
    // Arrange
    DatabaseThread thread = mock(DatabaseThread.class);
    when(thread.canTerminate()).thenReturn(false);
    when(thread.getDatabaseDebugTarget()).thenReturn(null);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualCanTerminateResult = databaseStackFrame.canTerminate();

    // Assert
    verify(thread).getDatabaseDebugTarget();
    verify(thread).canTerminate();
    assertFalse(actualCanTerminateResult);
  }

  /**
   * Test {@link DatabaseStackFrame#isTerminated()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseDebugTarget} {@link DatabaseDebugTarget#isTerminated()} return
   *       {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#isTerminated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.isTerminated()"})
  public void testIsTerminated_givenDatabaseDebugTargetIsTerminatedReturnTrue_thenReturnTrue() {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.isTerminated()).thenReturn(true);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualIsTerminatedResult = databaseStackFrame.isTerminated();

    // Assert
    verify(target).isTerminated();
    assertTrue(actualIsTerminatedResult);
  }

  /**
   * Test {@link DatabaseStackFrame#isTerminated()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseThread} {@link DatabaseThread#isTerminated()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#isTerminated()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.isTerminated()"})
  public void testIsTerminated_givenDatabaseThreadIsTerminatedReturnFalse_thenReturnFalse() {
    // Arrange
    DatabaseThread thread = mock(DatabaseThread.class);
    when(thread.isTerminated()).thenReturn(false);
    when(thread.getDatabaseDebugTarget()).thenReturn(null);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualIsTerminatedResult = databaseStackFrame.isTerminated();

    // Assert
    verify(thread).getDatabaseDebugTarget();
    verify(thread).isTerminated();
    assertFalse(actualIsTerminatedResult);
  }

  /**
   * Test {@link DatabaseStackFrame#terminate()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseDebugTarget} {@link DatabaseDebugTarget#terminate()} does nothing.
   *   <li>Then calls {@link DatabaseDebugTarget#terminate()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#terminate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseStackFrame.terminate()"})
  public void testTerminate_givenDatabaseDebugTargetTerminateDoesNothing_thenCallsTerminate()
      throws DebugException {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    doNothing().when(target).terminate();
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    databaseStackFrame.terminate();

    // Assert
    verify(target).terminate();
  }

  /**
   * Test {@link DatabaseStackFrame#getVariables()}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#getVariables()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IVariable[] DatabaseStackFrame.getVariables()"})
  public void testGetVariables_thenReturnArrayLengthIsZero() throws DebugException, DBGException {
    // Arrange
    DBGSession dbgSession = mock(DBGSession.class);
    Mockito.<List<? extends DBGVariable<?>>>when(
            dbgSession.getVariables(Mockito.<DBGStackFrame>any()))
        .thenThrow(new DBGException("An error occurred"));

    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.getSession()).thenReturn(dbgSession);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    IVariable[] actualVariables = databaseStackFrame.getVariables();

    // Assert
    verify(dbgSession).getVariables(isA(DBGStackFrame.class));
    verify(target).getSession();
    assertEquals(0, actualVariables.length);
  }

  /**
   * Test {@link DatabaseStackFrame#rebuildVariables(List)}.
   *
   * <ul>
   *   <li>Given {@link DBGVariable} {@link DBGVariable#getLineNumber()} return {@link
   *       Integer#MIN_VALUE}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#rebuildVariables(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseStackFrame.rebuildVariables(List)"})
  public void testRebuildVariables_givenDBGVariableGetLineNumberReturnMin_value() {
    // Arrange
    DBGStackFrame dbgStackFrame = mock(DBGStackFrame.class);
    when(dbgStackFrame.getLineNumber()).thenReturn(2);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(new DatabaseThread(null), dbgStackFrame);

    DBGVariable<Object> dbgVariable = mock(DBGVariable.class);
    when(dbgVariable.getLineNumber()).thenReturn(2);
    when(dbgVariable.getName()).thenReturn("Name");

    DBGVariable<Object> dbgVariable2 = mock(DBGVariable.class);
    when(dbgVariable2.getLineNumber()).thenReturn(Integer.MIN_VALUE);
    when(dbgVariable2.getName()).thenReturn("Name");

    ArrayList<DBGVariable<?>> dbgVariables = new ArrayList<>();
    dbgVariables.add(dbgVariable2);
    dbgVariables.add(dbgVariable);

    // Act
    databaseStackFrame.rebuildVariables(dbgVariables);

    // Assert
    verify(dbgStackFrame).getLineNumber();
    verify(dbgVariable2).getLineNumber();
    verify(dbgVariable).getLineNumber();
    verify(dbgVariable2).getName();
    verify(dbgVariable).getName();
  }

  /**
   * Test {@link DatabaseStackFrame#rebuildVariables(List)}.
   *
   * <ul>
   *   <li>Given {@link DBGVariable} {@link DBGVariable#getName()} return {@code Name}.
   *   <li>Then calls {@link DBGVariable#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#rebuildVariables(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseStackFrame.rebuildVariables(List)"})
  public void testRebuildVariables_givenDBGVariableGetNameReturnName_thenCallsGetName() {
    // Arrange
    DBGStackFrame dbgStackFrame = mock(DBGStackFrame.class);
    when(dbgStackFrame.getLineNumber()).thenReturn(2);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(new DatabaseThread(null), dbgStackFrame);

    DBGVariable<Object> dbgVariable = mock(DBGVariable.class);
    when(dbgVariable.getName()).thenReturn("Name");

    ArrayList<DBGVariable<?>> dbgVariables = new ArrayList<>();
    dbgVariables.add(dbgVariable);

    // Act
    databaseStackFrame.rebuildVariables(dbgVariables);

    // Assert
    verify(dbgStackFrame).getLineNumber();
    verify(dbgVariable).getName();
  }

  /**
   * Test {@link DatabaseStackFrame#rebuildVariables(List)}.
   *
   * <ul>
   *   <li>Then calls {@link DBGVariable#getLineNumber()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#rebuildVariables(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseStackFrame.rebuildVariables(List)"})
  public void testRebuildVariables_thenCallsGetLineNumber() {
    // Arrange
    DBGStackFrame dbgStackFrame = mock(DBGStackFrame.class);
    when(dbgStackFrame.getLineNumber()).thenReturn(2);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(new DatabaseThread(null), dbgStackFrame);

    DBGVariable<Object> dbgVariable = mock(DBGVariable.class);
    when(dbgVariable.getLineNumber()).thenReturn(2);
    when(dbgVariable.getName()).thenReturn("Name");

    DBGVariable<Object> dbgVariable2 = mock(DBGVariable.class);
    when(dbgVariable2.getLineNumber()).thenReturn(2);
    when(dbgVariable2.getName()).thenReturn("Name");

    ArrayList<DBGVariable<?>> dbgVariables = new ArrayList<>();
    dbgVariables.add(dbgVariable2);
    dbgVariables.add(dbgVariable);

    // Act
    databaseStackFrame.rebuildVariables(dbgVariables);

    // Assert
    verify(dbgStackFrame).getLineNumber();
    verify(dbgVariable2).getLineNumber();
    verify(dbgVariable).getLineNumber();
    verify(dbgVariable2).getName();
    verify(dbgVariable).getName();
  }

  /**
   * Test {@link DatabaseStackFrame#rebuildVariables(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#rebuildVariables(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseStackFrame.rebuildVariables(List)"})
  public void testRebuildVariables_whenArrayList() {
    // Arrange
    DBGStackFrame dbgStackFrame = mock(DBGStackFrame.class);
    when(dbgStackFrame.getLineNumber()).thenReturn(2);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(new DatabaseThread(null), dbgStackFrame);

    // Act
    databaseStackFrame.rebuildVariables(new ArrayList<>());

    // Assert
    verify(dbgStackFrame).getLineNumber();
  }

  /**
   * Test {@link DatabaseStackFrame#hasVariables()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseDebugTarget} {@link DatabaseDebugTarget#isSuspended()} return {@code
   *       true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#hasVariables()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.hasVariables()"})
  public void testHasVariables_givenDatabaseDebugTargetIsSuspendedReturnTrue_thenReturnTrue()
      throws DebugException {
    // Arrange
    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.isSuspended()).thenReturn(true);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualHasVariablesResult = databaseStackFrame.hasVariables();

    // Assert
    verify(target).isSuspended();
    assertTrue(actualHasVariablesResult);
  }

  /**
   * Test {@link DatabaseStackFrame#hasVariables()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseThread} {@link DatabaseThread#isSuspended()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#hasVariables()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseStackFrame.hasVariables()"})
  public void testHasVariables_givenDatabaseThreadIsSuspendedReturnFalse_thenReturnFalse()
      throws DebugException {
    // Arrange
    DatabaseThread thread = mock(DatabaseThread.class);
    when(thread.isSuspended()).thenReturn(false);
    when(thread.getDatabaseDebugTarget()).thenReturn(null);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    boolean actualHasVariablesResult = databaseStackFrame.hasVariables();

    // Assert
    verify(thread).getDatabaseDebugTarget();
    verify(thread).isSuspended();
    assertFalse(actualHasVariablesResult);
  }

  /**
   * Test {@link DatabaseStackFrame#getLineNumber()}.
   *
   * <ul>
   *   <li>Given {@link DBGStackFrame} {@link DBGStackFrame#getLineNumber()} return two.
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#getLineNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DatabaseStackFrame.getLineNumber()"})
  public void testGetLineNumber_givenDBGStackFrameGetLineNumberReturnTwo_thenReturnTwo()
      throws DebugException {
    // Arrange
    DBGStackFrame dbgStackFrame = mock(DBGStackFrame.class);
    when(dbgStackFrame.getLineNumber()).thenReturn(2);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(new DatabaseThread(null), dbgStackFrame);

    // Act
    int actualLineNumber = databaseStackFrame.getLineNumber();

    // Assert
    verify(dbgStackFrame).getLineNumber();
    assertEquals(2, actualLineNumber);
  }

  /**
   * Test {@link DatabaseStackFrame#getName()}.
   *
   * <ul>
   *   <li>Given {@link DBGStackFrame} {@link DBGStackFrame#getLineNumber()} return two.
   *   <li>Then return {@code Name line: 2}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseStackFrame.getName()"})
  public void testGetName_givenDBGStackFrameGetLineNumberReturnTwo_thenReturnNameLine2()
      throws DebugException {
    // Arrange
    DBGStackFrame dbgStackFrame = mock(DBGStackFrame.class);
    when(dbgStackFrame.getLineNumber()).thenReturn(2);
    when(dbgStackFrame.getName()).thenReturn("Name");
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(new DatabaseThread(null), dbgStackFrame);

    // Act
    String actualName = databaseStackFrame.getName();

    // Assert
    verify(dbgStackFrame).getLineNumber();
    verify(dbgStackFrame).getName();
    assertEquals("Name line: 2", actualName);
  }

  /**
   * Test {@link DatabaseStackFrame#getSource()}.
   *
   * <ul>
   *   <li>Given {@link DBGSession} {@link DBGSession#getSource(DBGStackFrame)} return {@code
   *       Source}.
   *   <li>Then return {@code Source}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#getSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseStackFrame.getSource()"})
  public void testGetSource_givenDBGSessionGetSourceReturnSource_thenReturnSource()
      throws DebugException, DBGException {
    // Arrange
    DBGSession dbgSession = mock(DBGSession.class);
    when(dbgSession.getSource(Mockito.<DBGStackFrame>any())).thenReturn("Source");

    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.getSession()).thenReturn(dbgSession);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act
    String actualSource = databaseStackFrame.getSource();

    // Assert
    verify(dbgSession).getSource(isA(DBGStackFrame.class));
    verify(target).getSession();
    assertEquals("Source", actualSource);
  }

  /**
   * Test {@link DatabaseStackFrame#getSource()}.
   *
   * <ul>
   *   <li>Then throw {@link DebugException}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#getSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseStackFrame.getSource()"})
  public void testGetSource_thenThrowDebugException() throws DebugException, DBGException {
    // Arrange
    DBGSession dbgSession = mock(DBGSession.class);
    when(dbgSession.getSource(Mockito.<DBGStackFrame>any()))
        .thenThrow(new DBGException("An error occurred"));

    DatabaseDebugTarget target = mock(DatabaseDebugTarget.class);
    when(target.getSession()).thenReturn(dbgSession);
    DatabaseThread thread = new DatabaseThread(target);
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(thread, mock(DBGStackFrame.class));

    // Act and Assert
    assertThrows(DebugException.class, () -> databaseStackFrame.getSource());
    verify(dbgSession).getSource(isA(DBGStackFrame.class));
    verify(target).getSession();
  }

  /**
   * Test {@link DatabaseStackFrame#getSourceIdentifier()}.
   *
   * <ul>
   *   <li>Then return {@code Source Identifier}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseStackFrame#getSourceIdentifier()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object DatabaseStackFrame.getSourceIdentifier()"})
  public void testGetSourceIdentifier_thenReturnSourceIdentifier() {
    // Arrange
    DBGStackFrame dbgStackFrame = mock(DBGStackFrame.class);
    when(dbgStackFrame.getSourceIdentifier()).thenReturn("Source Identifier");
    DatabaseStackFrame databaseStackFrame =
        new DatabaseStackFrame(new DatabaseThread(null), dbgStackFrame);

    // Act
    Object actualSourceIdentifier = databaseStackFrame.getSourceIdentifier();

    // Assert
    verify(dbgStackFrame).getSourceIdentifier();
    assertEquals("Source Identifier", actualSourceIdentifier);
  }
}
