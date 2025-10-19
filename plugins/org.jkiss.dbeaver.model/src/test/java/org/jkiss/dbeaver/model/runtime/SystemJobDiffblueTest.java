package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SystemJobDiffblueTest {
  /**
   * Test {@link SystemJob#SystemJob(String, DBRRunnableWithProgress)}.
   *
   * <p>Method under test: {@link SystemJob#SystemJob(String, DBRRunnableWithProgress)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SystemJob.<init>(String, DBRRunnableWithProgress)"})
  public void testNewSystemJob() {
    // Arrange and Act
    SystemJob actualSystemJob = new SystemJob("Name", mock(DBRRunnableWithProgress.class));

    // Assert
    assertEquals("Name", actualSystemJob.getName());
    assertNull(actualSystemJob.getThread());
    assertNull(actualSystemJob.getResult());
    assertNull(actualSystemJob.getRule());
    assertNull(actualSystemJob.getJobGroup());
    assertEquals(-1L, actualSystemJob.getCancelTimestamp());
    assertEquals(0, actualSystemJob.getState());
    assertEquals(30, actualSystemJob.getPriority());
    assertFalse(actualSystemJob.isBlocking());
    assertFalse(actualSystemJob.isUser());
    assertFalse(actualSystemJob.isCanceled());
    assertFalse(actualSystemJob.isFinished());
    assertFalse(actualSystemJob.isRunDirectly());
    assertTrue(actualSystemJob.isSystem());
    assertTrue(actualSystemJob.isForceCancel());
  }

  /**
   * Test {@link SystemJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <p>Method under test: {@link SystemJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus SystemJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    Throwable throwable = new Throwable("org.jkiss.dbeaver.model");
    Throwable throwable2 = new Throwable("org.jkiss.dbeaver.model", throwable);
    doThrow(new InvocationTargetException(throwable2, "foo"))
        .when(runnable)
        .run(Mockito.<DBRProgressMonitor>any());
    SystemJob systemJob = new SystemJob("Name", runnable);

    // Act
    IStatus actualRunResult = systemJob.run(new LoggingProgressMonitor());

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
    assertTrue(actualRunResult instanceof Status);
    assertEquals("java.lang.Throwable: org.jkiss.dbeaver.model", actualRunResult.getMessage());
    assertSame(throwable, actualRunResult.getException());
  }

  /**
   * Test {@link SystemJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Given {@link Throwable#Throwable()} initCause {@link Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link SystemJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus SystemJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_givenThrowableInitCauseThrowable()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    Throwable throwable = new Throwable();
    Throwable throwable2 = new Throwable();
    throwable.initCause(throwable2);

    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    doThrow(new InvocationTargetException(throwable, "foo"))
        .when(runnable)
        .run(Mockito.<DBRProgressMonitor>any());
    SystemJob systemJob = new SystemJob("Name", runnable);

    // Act
    IStatus actualRunResult = systemJob.run(new LoggingProgressMonitor());

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
    assertTrue(actualRunResult instanceof MultiStatus);
    IStatus[] children = actualRunResult.getChildren();
    IStatus iStatus = children[0];
    assertTrue(iStatus instanceof Status);
    assertEquals("java.lang.Throwable", actualRunResult.getMessage());
    assertEquals("java.lang.Throwable", iStatus.getMessage());
    assertEquals(0, iStatus.getChildren().length);
    assertEquals(1, children.length);
    assertFalse(iStatus.isMultiStatus());
    assertSame(throwable2, iStatus.getException());
  }

  /**
   * Test {@link SystemJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Given {@link Throwable#Throwable(String, Throwable)} with {@code org.jkiss.dbeaver.model}
   *       and {@link Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link SystemJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus SystemJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_givenThrowableWithOrgJkissDbeaverModelAndThrowable()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    Throwable throwable = new Throwable();
    Throwable throwable2 = new Throwable("org.jkiss.dbeaver.model", throwable);
    doThrow(new InvocationTargetException(throwable2, "foo"))
        .when(runnable)
        .run(Mockito.<DBRProgressMonitor>any());
    SystemJob systemJob = new SystemJob("Name", runnable);

    // Act
    IStatus actualRunResult = systemJob.run(new LoggingProgressMonitor());

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
    assertTrue(actualRunResult instanceof MultiStatus);
    IStatus[] children = actualRunResult.getChildren();
    IStatus iStatus = children[0];
    assertTrue(iStatus instanceof Status);
    assertEquals("java.lang.Throwable", iStatus.getMessage());
    assertEquals("java.lang.Throwable: org.jkiss.dbeaver.model", actualRunResult.getMessage());
    assertEquals(0, iStatus.getChildren().length);
    assertEquals(1, children.length);
    assertFalse(iStatus.isMultiStatus());
    assertSame(throwable, iStatus.getException());
  }

  /**
   * Test {@link SystemJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then first element return {@link MultiStatus}.
   * </ul>
   *
   * <p>Method under test: {@link SystemJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus SystemJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenFirstElementReturnMultiStatus()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    Throwable throwable = new Throwable();
    Throwable throwable2 = new Throwable("org.jkiss.dbeaver.model", new Throwable());
    throwable.initCause(throwable2);

    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    doThrow(new InvocationTargetException(throwable, "foo"))
        .when(runnable)
        .run(Mockito.<DBRProgressMonitor>any());
    SystemJob systemJob = new SystemJob("Name", runnable);

    // Act
    IStatus actualRunResult = systemJob.run(new LoggingProgressMonitor());

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
    assertTrue(actualRunResult instanceof MultiStatus);
    IStatus[] children = actualRunResult.getChildren();
    IStatus iStatus = children[0];
    assertTrue(iStatus instanceof MultiStatus);
    IStatus[] children2 = iStatus.getChildren();
    assertTrue(children2[0] instanceof Status);
    assertEquals("java.lang.Throwable: org.jkiss.dbeaver.model", iStatus.getMessage());
    assertEquals(1, children.length);
    assertEquals(1, children2.length);
    assertTrue(iStatus.isMultiStatus());
    assertSame(throwable2, iStatus.getException());
  }

  /**
   * Test {@link SystemJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then return {@link Status#CANCEL_STATUS}.
   * </ul>
   *
   * <p>Method under test: {@link SystemJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus SystemJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenReturnCancel_status()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    doThrow(new InterruptedException()).when(runnable).run(Mockito.<DBRProgressMonitor>any());
    SystemJob systemJob = new SystemJob("Name", runnable);

    // Act
    IStatus actualRunResult = systemJob.run(new LoggingProgressMonitor());

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
    assertSame(((Status) actualRunResult).CANCEL_STATUS, actualRunResult);
  }

  /**
   * Test {@link SystemJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then return Exception is {@link Throwable#Throwable()}.
   * </ul>
   *
   * <p>Method under test: {@link SystemJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus SystemJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenReturnExceptionIsThrowable()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    Throwable throwable = new Throwable();
    doThrow(new InvocationTargetException(throwable, "foo"))
        .when(runnable)
        .run(Mockito.<DBRProgressMonitor>any());
    SystemJob systemJob = new SystemJob("Name", runnable);

    // Act
    IStatus actualRunResult = systemJob.run(new LoggingProgressMonitor());

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
    assertTrue(actualRunResult instanceof Status);
    assertEquals("java.lang.Throwable", actualRunResult.getMessage());
    assertSame(throwable, actualRunResult.getException());
  }

  /**
   * Test {@link SystemJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then return Message is empty string.
   * </ul>
   *
   * <p>Method under test: {@link SystemJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus SystemJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenReturnMessageIsEmptyString()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    IOException ioException = new IOException();
    Throwable throwable = new Throwable();
    ioException.initCause(throwable);

    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    doThrow(new InvocationTargetException(ioException, "foo"))
        .when(runnable)
        .run(Mockito.<DBRProgressMonitor>any());

    SystemJob systemJob = new SystemJob("Name", runnable);
    systemJob.addJobChangeListener(new JobChangeAdapter());

    // Act
    IStatus actualRunResult = systemJob.run(new LoggingProgressMonitor());

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
    assertTrue(actualRunResult instanceof MultiStatus);
    IStatus[] children = actualRunResult.getChildren();
    IStatus iStatus = children[0];
    assertTrue(iStatus instanceof Status);
    assertEquals("", actualRunResult.getMessage());
    assertEquals("java.lang.Throwable", iStatus.getMessage());
    assertEquals(0, iStatus.getChildren().length);
    assertEquals(1, children.length);
    assertFalse(iStatus.isMultiStatus());
    assertSame(throwable, iStatus.getException());
  }

  /**
   * Test {@link SystemJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then return Message is {@code IOException}.
   * </ul>
   *
   * <p>Method under test: {@link SystemJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus SystemJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenReturnMessageIsIOException()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    IOException ioException = new IOException();
    doThrow(new InvocationTargetException(ioException, "foo"))
        .when(runnable)
        .run(Mockito.<DBRProgressMonitor>any());

    SystemJob systemJob = new SystemJob("Name", runnable);
    systemJob.addJobChangeListener(new JobChangeAdapter());

    // Act
    IStatus actualRunResult = systemJob.run(new LoggingProgressMonitor());

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
    assertTrue(actualRunResult instanceof Status);
    assertEquals("IOException", actualRunResult.getMessage());
    assertSame(ioException, actualRunResult.getException());
  }

  /**
   * Test {@link SystemJob#run(DBRProgressMonitor)} with {@code DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then return {@link Status#OK_STATUS}.
   * </ul>
   *
   * <p>Method under test: {@link SystemJob#run(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IStatus SystemJob.run(DBRProgressMonitor)"})
  public void testRunWithDBRProgressMonitor_thenReturnOk_status()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    doNothing().when(runnable).run(Mockito.<DBRProgressMonitor>any());
    SystemJob systemJob = new SystemJob("Name", runnable);

    // Act
    IStatus actualRunResult = systemJob.run(new LoggingProgressMonitor());

    // Assert
    verify(runnable).run(isA(DBRProgressMonitor.class));
    assertSame(((Status) actualRunResult).OK_STATUS, actualRunResult);
  }
}
