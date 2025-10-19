package org.jkiss.dbeaver.registry.task;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.jkiss.dbeaver.model.runtime.DefaultProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LocalCacheProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.ProxyProgressMonitor;
import org.jkiss.dbeaver.model.runtime.SubTaskProgressMonitor;
import org.jkiss.dbeaver.model.runtime.VoidProgressMonitor;
import org.jkiss.dbeaver.registry.project.LocalProjectImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TaskLoggingProgressMonitorDiffblueTest {
  /**
   * Test {@link TaskLoggingProgressMonitor#beginTask(String, int)}.
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#beginTask(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.beginTask(String, int)"})
  public void testBeginTask() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Date createTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date updateTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    TaskImpl task2 =
        new TaskImpl(
            null,
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime2,
            updateTime2,
            null);

    TaskLoggingProgressMonitor original = new TaskLoggingProgressMonitor(monitor, task2);
    DefaultProgressMonitor monitor2 =
        new DefaultProgressMonitor(new ProxyProgressMonitor(original));

    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(monitor2, task);

    // Act
    taskLoggingProgressMonitor.beginTask("Name", 1);

    // Assert
    verify(workspace).getAuthContext();
  }

  /**
   * Test {@link TaskLoggingProgressMonitor#beginTask(String, int)}.
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#beginTask(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.beginTask(String, int)"})
  public void testBeginTask2() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor monitor = new LoggingProgressMonitor(Log.getLog(forClass));

    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(monitor, task);

    // Act
    taskLoggingProgressMonitor.beginTask("Name", 1);

    // Assert
    verify(workspace).getAuthContext();
  }

  /**
   * Test {@link TaskLoggingProgressMonitor#beginTask(String, int)}.
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#beginTask(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.beginTask(String, int)"})
  public void testBeginTask3() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Date createTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date updateTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    TaskImpl task2 =
        new TaskImpl(
            null,
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime2,
            updateTime2,
            null);

    TaskLoggingProgressMonitor original = new TaskLoggingProgressMonitor(monitor, task2);
    SubTaskProgressMonitor monitor2 =
        new SubTaskProgressMonitor(new ProxyProgressMonitor(original));

    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(monitor2, task);

    // Act
    taskLoggingProgressMonitor.beginTask("Name", 1);

    // Assert
    verify(workspace).getAuthContext();
  }

  /**
   * Test {@link TaskLoggingProgressMonitor#beginTask(String, int)}.
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#beginTask(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.beginTask(String, int)"})
  public void testBeginTask4() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    VoidProgressMonitor monitor = new VoidProgressMonitor();
    Date createTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date updateTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    TaskImpl task2 =
        new TaskImpl(
            null,
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime2,
            updateTime2,
            null);

    TaskLoggingProgressMonitor original = new TaskLoggingProgressMonitor(monitor, task2);
    SubTaskProgressMonitor monitor2 =
        new SubTaskProgressMonitor(new ProxyProgressMonitor(original));

    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(monitor2, task);

    // Act
    taskLoggingProgressMonitor.beginTask("Name", 1);

    // Assert
    verify(workspace).getAuthContext();
  }

  /**
   * Test {@link TaskLoggingProgressMonitor#beginTask(String, int)}.
   *
   * <ul>
   *   <li>Given {@link LocalCacheProgressMonitor#LocalCacheProgressMonitor(DBRProgressMonitor)}
   *       with original is {@link ProxyProgressMonitor#ProxyProgressMonitor(DBRProgressMonitor)}.
   * </ul>
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#beginTask(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.beginTask(String, int)"})
  public void testBeginTask_givenLocalCacheProgressMonitorWithOriginalIsProxyProgressMonitor() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Date createTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date updateTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    TaskImpl task2 =
        new TaskImpl(
            null,
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime2,
            updateTime2,
            null);

    TaskLoggingProgressMonitor original = new TaskLoggingProgressMonitor(monitor, task2);
    LocalCacheProgressMonitor original2 =
        new LocalCacheProgressMonitor(new ProxyProgressMonitor(original));
    ProxyProgressMonitor original3 = new ProxyProgressMonitor(original2);
    SubTaskProgressMonitor monitor2 = new SubTaskProgressMonitor(original3);

    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(monitor2, task);

    // Act
    taskLoggingProgressMonitor.beginTask("Name", 1);

    // Assert
    verify(workspace).getAuthContext();
  }

  /**
   * Test {@link TaskLoggingProgressMonitor#beginTask(String, int)}.
   *
   * <ul>
   *   <li>Given {@link ProxyProgressMonitor#ProxyProgressMonitor(DBRProgressMonitor)} with original
   *       is {@link DefaultProgressMonitor#DefaultProgressMonitor(IProgressMonitor)}.
   * </ul>
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#beginTask(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.beginTask(String, int)"})
  public void testBeginTask_givenProxyProgressMonitorWithOriginalIsDefaultProgressMonitor() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Date createTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date updateTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    TaskImpl task2 =
        new TaskImpl(
            null,
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime2,
            updateTime2,
            null);

    TaskLoggingProgressMonitor original = new TaskLoggingProgressMonitor(monitor, task2);
    DefaultProgressMonitor original2 =
        new DefaultProgressMonitor(new ProxyProgressMonitor(original));
    ProxyProgressMonitor original3 = new ProxyProgressMonitor(original2);
    SubTaskProgressMonitor monitor2 = new SubTaskProgressMonitor(original3);

    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(monitor2, task);

    // Act
    taskLoggingProgressMonitor.beginTask("Name", 1);

    // Assert
    verify(workspace).getAuthContext();
  }

  /**
   * Test {@link TaskLoggingProgressMonitor#beginTask(String, int)}.
   *
   * <ul>
   *   <li>Given {@link ProxyProgressMonitor#ProxyProgressMonitor(DBRProgressMonitor)} with original
   *       is {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   * </ul>
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#beginTask(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.beginTask(String, int)"})
  public void testBeginTask_givenProxyProgressMonitorWithOriginalIsLoggingProgressMonitor() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(
            new ProxyProgressMonitor(new LoggingProgressMonitor()), task);

    // Act
    taskLoggingProgressMonitor.beginTask("Name", 1);

    // Assert
    verify(workspace).getAuthContext();
  }

  /**
   * Test {@link TaskLoggingProgressMonitor#beginTask(String, int)}.
   *
   * <ul>
   *   <li>Given {@link ProxyProgressMonitor#ProxyProgressMonitor(DBRProgressMonitor)} with original
   *       is {@link LoggingProgressMonitor#LoggingProgressMonitor(Log)}.
   * </ul>
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#beginTask(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.beginTask(String, int)"})
  public void testBeginTask_givenProxyProgressMonitorWithOriginalIsLoggingProgressMonitor2() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor original = new LoggingProgressMonitor(Log.getLog(forClass));
    ProxyProgressMonitor original2 = new ProxyProgressMonitor(original);
    SubTaskProgressMonitor monitor = new SubTaskProgressMonitor(original2);

    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(monitor, task);

    // Act
    taskLoggingProgressMonitor.beginTask("Name", 1);

    // Assert
    verify(workspace).getAuthContext();
  }

  /**
   * Test {@link TaskLoggingProgressMonitor#beginTask(String, int)}.
   *
   * <ul>
   *   <li>Given {@link SubTaskProgressMonitor#SubTaskProgressMonitor(DBRProgressMonitor)} with
   *       original is {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   * </ul>
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#beginTask(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.beginTask(String, int)"})
  public void testBeginTask_givenSubTaskProgressMonitorWithOriginalIsLoggingProgressMonitor() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(
            new SubTaskProgressMonitor(new LoggingProgressMonitor()), task);

    // Act
    taskLoggingProgressMonitor.beginTask("Name", 1);

    // Assert
    verify(workspace).getAuthContext();
  }

  /**
   * Test {@link TaskLoggingProgressMonitor#subTask(String)}.
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#subTask(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.subTask(String)"})
  public void testSubTask() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Date createTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date updateTime2 =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    TaskImpl task2 =
        new TaskImpl(
            null,
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime2,
            updateTime2,
            null);

    TaskLoggingProgressMonitor original = new TaskLoggingProgressMonitor(monitor, task2);
    DefaultProgressMonitor monitor2 =
        new DefaultProgressMonitor(new ProxyProgressMonitor(original));

    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(monitor2, task);

    // Act
    taskLoggingProgressMonitor.subTask("Name");

    // Assert
    verify(workspace).getAuthContext();
  }

  /**
   * Test {@link TaskLoggingProgressMonitor#subTask(String)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then calls {@link DBPWorkspace#getAuthContext()}.
   * </ul>
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#subTask(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.subTask(String)"})
  public void testSubTask_givenJavaLangObject_thenCallsGetAuthContext() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor monitor = new LoggingProgressMonitor(Log.getLog(forClass));

    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(monitor, task);

    // Act
    taskLoggingProgressMonitor.subTask("Name");

    // Assert
    verify(workspace).getAuthContext();
  }

  /**
   * Test {@link TaskLoggingProgressMonitor#subTask(String)}.
   *
   * <ul>
   *   <li>Given {@link ProxyProgressMonitor#ProxyProgressMonitor(DBRProgressMonitor)} with original
   *       is {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   * </ul>
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#subTask(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.subTask(String)"})
  public void testSubTask_givenProxyProgressMonitorWithOriginalIsLoggingProgressMonitor() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(
            new ProxyProgressMonitor(new LoggingProgressMonitor()), task);

    // Act
    taskLoggingProgressMonitor.subTask("Name");

    // Assert
    verify(workspace).getAuthContext();
  }

  /**
   * Test {@link TaskLoggingProgressMonitor#subTask(String)}.
   *
   * <ul>
   *   <li>Given {@link ProxyProgressMonitor#ProxyProgressMonitor(DBRProgressMonitor)} with original
   *       is {@link VoidProgressMonitor} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link TaskLoggingProgressMonitor#subTask(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskLoggingProgressMonitor.subTask(String)"})
  public void testSubTask_givenProxyProgressMonitorWithOriginalIsVoidProgressMonitor() {
    // Arrange
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
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            folder);
    TaskLoggingProgressMonitor taskLoggingProgressMonitor =
        new TaskLoggingProgressMonitor(new ProxyProgressMonitor(new VoidProgressMonitor()), task);

    // Act
    taskLoggingProgressMonitor.subTask("Name");

    // Assert
    verify(workspace).getAuthContext();
  }
}
