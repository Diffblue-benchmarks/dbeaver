package org.jkiss.dbeaver.registry.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DBRRunnableWithProgress;
import org.jkiss.dbeaver.model.task.DBTTaskExecutionListener;
import org.jkiss.dbeaver.registry.project.LocalProjectImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TaskRunJobDiffblueTest {
  /**
   * Test {@link TaskRunJob#TaskRunJob(TaskImpl, Locale, DBTTaskExecutionListener)}.
   *
   * <ul>
   *   <li>Then return Name is {@code Task [Name] runner - Label}.
   * </ul>
   *
   * <p>Method under test: {@link TaskRunJob#TaskRunJob(TaskImpl, Locale, DBTTaskExecutionListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskRunJob.<init>(TaskImpl, Locale, DBTTaskExecutionListener)"})
  public void testNewTaskRunJob_thenReturnNameIsTaskNameRunnerLabel() {
    // Arrange
    TaskTypeDescriptor type = mock(TaskTypeDescriptor.class);
    when(type.getName()).thenReturn("Name");
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl project =
        new LocalProjectImpl(workspace, new SessionContextImpl(null), projectPath);
    Date createTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date updateTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DBPWorkspace workspace2 = mock(DBPWorkspace.class);
    Path projectPath2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl folderProject =
        new LocalProjectImpl(workspace2, new SessionContextImpl(null), projectPath2);
    TaskFolderImpl folder =
        new TaskFolderImpl("Folder Name", null, folderProject, new ArrayList<>());

    TaskImpl task =
        new TaskImpl(
            project,
            type,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);

    // Act
    TaskRunJob actualTaskRunJob =
        new TaskRunJob(task, Locale.getDefault(), mock(DBTTaskExecutionListener.class));

    // Assert
    verify(type).getName();
    assertEquals("Task [Name] runner - Label", actualTaskRunJob.getName());
    assertNull(actualTaskRunJob.getThread());
    assertNull(actualTaskRunJob.getTaskError());
    assertNull(actualTaskRunJob.getResult());
    assertNull(actualTaskRunJob.getRule());
    assertNull(actualTaskRunJob.getJobGroup());
    assertEquals(-1L, actualTaskRunJob.getCancelTimestamp());
    assertEquals(0, actualTaskRunJob.getState());
    assertEquals(30, actualTaskRunJob.getPriority());
    assertFalse(actualTaskRunJob.isBlocking());
    assertFalse(actualTaskRunJob.isSystem());
    assertFalse(actualTaskRunJob.isCanceled());
    assertFalse(actualTaskRunJob.isFinished());
    assertFalse(actualTaskRunJob.isRunDirectly());
    assertTrue(actualTaskRunJob.isUser());
    assertTrue(actualTaskRunJob.isForceCancel());
  }

  /**
   * Test {@link TaskRunJob#TaskRunJob(TaskImpl, Locale, DBTTaskExecutionListener)}.
   *
   * <ul>
   *   <li>Then return Name is {@code Task [Name] runner - Name}.
   * </ul>
   *
   * <p>Method under test: {@link TaskRunJob#TaskRunJob(TaskImpl, Locale, DBTTaskExecutionListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskRunJob.<init>(TaskImpl, Locale, DBTTaskExecutionListener)"})
  public void testNewTaskRunJob_thenReturnNameIsTaskNameRunnerName() {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    when(workspace.getAuthContext()).thenReturn(new SessionContextImpl(null));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new LocalProjectImpl(workspace, null, projectPath);

    DBPWorkspace workspace2 = mock(DBPWorkspace.class);
    when(workspace2.getAuthContext()).thenReturn(new SessionContextImpl(null));
    Path projectPath2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl folderProject = new LocalProjectImpl(workspace2, null, projectPath2);
    new TaskFolderImpl("Folder Name", null, folderProject, new ArrayList<>());

    TaskTypeDescriptor taskTypeDescriptor = mock(TaskTypeDescriptor.class);
    when(taskTypeDescriptor.getName()).thenReturn("Name");

    TaskImpl task = mock(TaskImpl.class);
    when(task.getName()).thenReturn("Name");
    when(task.getType()).thenReturn(taskTypeDescriptor);
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry), 1);

    // Act
    TaskRunJob actualTaskRunJob =
        new TaskRunJob(task, Locale.getDefault(), mock(DBTTaskExecutionListener.class));

    // Assert
    verify(workspace).getAuthContext();
    verify(workspace2).getAuthContext();
    verify(task).getName();
    verify(task).getType();
    verify(taskTypeDescriptor).getName();
    assertEquals("Task [Name] runner - Name", actualTaskRunJob.getName());
    assertNull(actualTaskRunJob.getThread());
    assertNull(actualTaskRunJob.getTaskError());
    assertNull(actualTaskRunJob.getResult());
    assertNull(actualTaskRunJob.getRule());
    assertNull(actualTaskRunJob.getJobGroup());
    assertEquals(-1L, actualTaskRunJob.getCancelTimestamp());
    assertEquals(0, actualTaskRunJob.getState());
    assertEquals(30, actualTaskRunJob.getPriority());
    assertFalse(actualTaskRunJob.isBlocking());
    assertFalse(actualTaskRunJob.isSystem());
    assertFalse(actualTaskRunJob.isCanceled());
    assertFalse(actualTaskRunJob.isFinished());
    assertFalse(actualTaskRunJob.isRunDirectly());
    assertTrue(actualTaskRunJob.isUser());
    assertTrue(actualTaskRunJob.isForceCancel());
  }

  /**
   * Test {@link TaskRunJob#run(boolean, boolean, DBRRunnableWithProgress)} with {@code boolean},
   * {@code boolean}, {@code DBRRunnableWithProgress}.
   *
   * <ul>
   *   <li>Then calls {@link DBPWorkspace#getAuthContext()}.
   * </ul>
   *
   * <p>Method under test: {@link TaskRunJob#run(boolean, boolean, DBRRunnableWithProgress)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskRunJob.run(boolean, boolean, DBRRunnableWithProgress)"})
  public void testRunWithBooleanBooleanDBRRunnableWithProgress_thenCallsGetAuthContext()
      throws InterruptedException, InvocationTargetException {
    // Arrange
    TaskTypeDescriptor type = mock(TaskTypeDescriptor.class);
    when(type.getName()).thenReturn("Name");

    DBPWorkspace workspace = mock(DBPWorkspace.class);
    when(workspace.getAuthContext()).thenReturn(new SessionContextImpl(null));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl folderProject = new LocalProjectImpl(workspace, null, projectPath);
    TaskFolderImpl folder =
        new TaskFolderImpl("Folder Name", null, folderProject, new ArrayList<>());
    DBPWorkspace workspace2 = mock(DBPWorkspace.class);
    Path projectPath2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl project =
        new LocalProjectImpl(workspace2, new SessionContextImpl(null), projectPath2);
    Date createTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date updateTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    TaskImpl task =
        new TaskImpl(
            project,
            type,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    TaskRunJob taskRunJob =
        new TaskRunJob(task, Locale.getDefault(), mock(DBTTaskExecutionListener.class));

    DBRRunnableWithProgress runnable = mock(DBRRunnableWithProgress.class);
    doNothing().when(runnable).run(Mockito.<DBRProgressMonitor>any());

    // Act
    taskRunJob.run(true, true, runnable);

    // Assert
    verify(workspace).getAuthContext();
    verify(runnable).run(isNull());
    verify(type).getName();
  }

  /**
   * Test {@link TaskRunJob#cancelByTimeReached()}.
   *
   * <p>Method under test: {@link TaskRunJob#cancelByTimeReached()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskRunJob.cancelByTimeReached()"})
  public void testCancelByTimeReached() {
    // Arrange
    TaskTypeDescriptor type = mock(TaskTypeDescriptor.class);
    when(type.getName()).thenReturn("Name");

    DBPWorkspace workspace = mock(DBPWorkspace.class);
    when(workspace.getAuthContext()).thenReturn(new SessionContextImpl(null));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl folderProject = new LocalProjectImpl(workspace, null, projectPath);
    TaskFolderImpl folder =
        new TaskFolderImpl("Folder Name", null, folderProject, new ArrayList<>());
    DBPWorkspace workspace2 = mock(DBPWorkspace.class);
    Path projectPath2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl project =
        new LocalProjectImpl(workspace2, new SessionContextImpl(null), projectPath2);
    Date createTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date updateTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    TaskImpl task =
        new TaskImpl(
            project,
            type,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    TaskRunJob taskRunJob =
        new TaskRunJob(task, Locale.getDefault(), mock(DBTTaskExecutionListener.class));

    // Act
    taskRunJob.cancelByTimeReached();

    // Assert
    verify(workspace).getAuthContext();
    verify(type).getName();
  }

  /**
   * Test {@link TaskRunJob#cancelByTimeReached()}.
   *
   * <p>Method under test: {@link TaskRunJob#cancelByTimeReached()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskRunJob.cancelByTimeReached()"})
  public void testCancelByTimeReached2() {
    // Arrange
    TaskTypeDescriptor type = mock(TaskTypeDescriptor.class);
    when(type.getName()).thenReturn("Name");

    DBPWorkspace workspace = mock(DBPWorkspace.class);
    when(workspace.getAuthContext()).thenReturn(new SessionContextImpl(null));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl folderProject = new LocalProjectImpl(workspace, null, projectPath);
    TaskFolderImpl folder =
        new TaskFolderImpl("Folder Name", null, folderProject, new ArrayList<>());
    DBPWorkspace workspace2 = mock(DBPWorkspace.class);
    Path projectPath2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl project =
        new LocalProjectImpl(workspace2, new SessionContextImpl(null), projectPath2);
    Date createTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date updateTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    TaskImpl task =
        new TaskImpl(
            project,
            type,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    task.setMaxExecutionTime(3);
    TaskRunJob taskRunJob =
        new TaskRunJob(task, Locale.getDefault(), mock(DBTTaskExecutionListener.class));

    // Act
    taskRunJob.cancelByTimeReached();

    // Assert
    verify(workspace).getAuthContext();
    verify(type).getName();
  }
}
