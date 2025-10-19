package org.jkiss.dbeaver.tools.transfer.task;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Locale;
import org.eclipse.core.runtime.OperationCanceledException;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.runtime.DBRRunnableContext;
import org.jkiss.dbeaver.model.runtime.DBRRunnableWithProgress;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.model.task.DBTTaskExecutionListener;
import org.jkiss.dbeaver.model.task.DBTTaskInfoCollector;
import org.jkiss.dbeaver.model.task.DBTTaskInfoCollector.TaskInformation;
import org.jkiss.dbeaver.model.task.DBTTaskRunStatus;
import org.jkiss.dbeaver.tools.transfer.DataTransferSettings;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DTTaskHandlerTransferDiffblueTest {
  /**
   * Test {@link DTTaskHandlerTransfer#executeTask(DBRRunnableContext, DBTTask, Locale, Log,
   * PrintStream, DBTTaskExecutionListener)}.
   *
   * <ul>
   *   <li>Given {@link InterruptedException#InterruptedException()}.
   *   <li>Then return ResultMessage is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DTTaskHandlerTransfer#executeTask(DBRRunnableContext, DBTTask,
   * Locale, Log, PrintStream, DBTTaskExecutionListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBTTaskRunStatus DTTaskHandlerTransfer.executeTask(DBRRunnableContext, DBTTask, Locale, Log, PrintStream, DBTTaskExecutionListener)"
  })
  public void testExecuteTask_givenInterruptedException_thenReturnResultMessageIsNull()
      throws InterruptedException, InvocationTargetException, DBException {
    // Arrange
    DTTaskHandlerTransfer dtTaskHandlerTransfer = new DTTaskHandlerTransfer();

    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    doThrow(new InterruptedException())
        .when(runnableContext)
        .run(anyBoolean(), anyBoolean(), Mockito.<DBRRunnableWithProgress>any());
    DBTTask task = mock(DBTTask.class);
    Locale locale = Locale.getDefault();
    Class<Object> forClass = Object.class;
    Log log = Log.getLog(forClass);

    // Act
    DBTTaskRunStatus actualExecuteTaskResult =
        dtTaskHandlerTransfer.executeTask(
            runnableContext,
            task,
            locale,
            log,
            new PrintStream(new ByteArrayOutputStream()),
            mock(DBTTaskExecutionListener.class));

    // Assert
    verify(runnableContext).run(eq(true), eq(true), isA(DBRRunnableWithProgress.class));
    assertNull(actualExecuteTaskResult.getResultMessage());
  }

  /**
   * Test {@link DTTaskHandlerTransfer#executeTask(DBRRunnableContext, DBTTask, Locale, Log,
   * PrintStream, DBTTaskExecutionListener)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DTTaskHandlerTransfer#executeTask(DBRRunnableContext, DBTTask,
   * Locale, Log, PrintStream, DBTTaskExecutionListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBTTaskRunStatus DTTaskHandlerTransfer.executeTask(DBRRunnableContext, DBTTask, Locale, Log, PrintStream, DBTTaskExecutionListener)"
  })
  public void testExecuteTask_thenThrowDBException()
      throws InterruptedException, InvocationTargetException, DBException {
    // Arrange
    DTTaskHandlerTransfer dtTaskHandlerTransfer = new DTTaskHandlerTransfer();

    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    doThrow(new InvocationTargetException(new Throwable(), "foo"))
        .when(runnableContext)
        .run(anyBoolean(), anyBoolean(), Mockito.<DBRRunnableWithProgress>any());
    DBTTask task = mock(DBTTask.class);
    Locale locale = Locale.getDefault();
    Class<Object> forClass = Object.class;
    Log log = Log.getLog(forClass);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            dtTaskHandlerTransfer.executeTask(
                runnableContext,
                task,
                locale,
                log,
                new PrintStream(new ByteArrayOutputStream()),
                mock(DBTTaskExecutionListener.class)));
    verify(runnableContext).run(eq(true), eq(true), isA(DBRRunnableWithProgress.class));
  }

  /**
   * Test {@link DTTaskHandlerTransfer#executeTask(DBRRunnableContext, DBTTask, Locale, Log,
   * PrintStream, DBTTaskExecutionListener)}.
   *
   * <ul>
   *   <li>Then throw {@link OperationCanceledException}.
   * </ul>
   *
   * <p>Method under test: {@link DTTaskHandlerTransfer#executeTask(DBRRunnableContext, DBTTask,
   * Locale, Log, PrintStream, DBTTaskExecutionListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBTTaskRunStatus DTTaskHandlerTransfer.executeTask(DBRRunnableContext, DBTTask, Locale, Log, PrintStream, DBTTaskExecutionListener)"
  })
  public void testExecuteTask_thenThrowOperationCanceledException()
      throws InterruptedException, InvocationTargetException, DBException {
    // Arrange
    DTTaskHandlerTransfer dtTaskHandlerTransfer = new DTTaskHandlerTransfer();

    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    doNothing()
        .when(runnableContext)
        .run(anyBoolean(), anyBoolean(), Mockito.<DBRRunnableWithProgress>any());
    DBTTask task = mock(DBTTask.class);
    Locale locale = Locale.getDefault();
    Class<Object> forClass = Object.class;
    Log log = Log.getLog(forClass);
    PrintStream logStream = new PrintStream(new ByteArrayOutputStream());

    DBTTaskExecutionListener listener = mock(DBTTaskExecutionListener.class);
    doThrow(new OperationCanceledException("An error occurred"))
        .when(listener)
        .taskStarted(Mockito.<DBTTask>any());

    // Act and Assert
    assertThrows(
        OperationCanceledException.class,
        () ->
            dtTaskHandlerTransfer.executeTask(
                runnableContext, task, locale, log, logStream, listener));
    verify(runnableContext).run(eq(true), eq(true), isA(DBRRunnableWithProgress.class));
    verify(listener).taskStarted(isA(DBTTask.class));
  }

  /**
   * Test {@link DTTaskHandlerTransfer#executeWithSettings(DBRRunnableContext, DBTTask, Locale, Log,
   * PrintStream, DBTTaskExecutionListener, DataTransferSettings)}.
   *
   * <ul>
   *   <li>Then throw {@link OperationCanceledException}.
   * </ul>
   *
   * <p>Method under test: {@link DTTaskHandlerTransfer#executeWithSettings(DBRRunnableContext,
   * DBTTask, Locale, Log, PrintStream, DBTTaskExecutionListener, DataTransferSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DTTaskHandlerTransfer.executeWithSettings(DBRRunnableContext, DBTTask, Locale, Log, PrintStream, DBTTaskExecutionListener, DataTransferSettings)"
  })
  public void testExecuteWithSettings_thenThrowOperationCanceledException() throws DBException {
    // Arrange
    DTTaskHandlerTransfer dtTaskHandlerTransfer = new DTTaskHandlerTransfer();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    DBTTask task = mock(DBTTask.class);
    Locale locale = Locale.getDefault();
    Class<Object> forClass = Object.class;
    Log log = Log.getLog(forClass);
    PrintStream logStream = new PrintStream(new ByteArrayOutputStream());

    DBTTaskExecutionListener listener = mock(DBTTaskExecutionListener.class);
    doThrow(new OperationCanceledException("An error occurred"))
        .when(listener)
        .taskStarted(Mockito.<DBTTask>any());

    // Act and Assert
    assertThrows(
        OperationCanceledException.class,
        () ->
            dtTaskHandlerTransfer.executeWithSettings(
                runnableContext, task, locale, log, logStream, listener, null));
    verify(listener).taskStarted(isA(DBTTask.class));
  }

  /**
   * Test {@link DTTaskHandlerTransfer#collectTaskInfo(DBTTask, TaskInformation)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   *   <li>Then calls {@link DBTTask#getProperties()}.
   * </ul>
   *
   * <p>Method under test: {@link DTTaskHandlerTransfer#collectTaskInfo(DBTTask, TaskInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DTTaskHandlerTransfer.collectTaskInfo(DBTTask, TaskInformation)"})
  public void testCollectTaskInfo_givenHashMap_thenCallsGetProperties() {
    // Arrange
    DTTaskHandlerTransfer dtTaskHandlerTransfer = new DTTaskHandlerTransfer();

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(new HashMap<>());

    // Act
    dtTaskHandlerTransfer.collectTaskInfo(task, new TaskInformation());

    // Assert
    verify(task).getProperties();
  }
}
