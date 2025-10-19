package org.jkiss.dbeaver.registry.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
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
import java.util.List;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.app.DBPWorkspace;
import org.jkiss.dbeaver.model.auth.SMSessionContext;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.model.task.DBTTaskFolder;
import org.jkiss.dbeaver.registry.project.LocalProjectImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TaskFolderImplDiffblueTest {
  @Mock private DBPProject dBPProject;

  @Mock private DBTTaskFolder dBTTaskFolder;

  @Mock private List<DBTTask> list;

  @InjectMocks private TaskFolderImpl taskFolderImpl;

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TaskFolderImpl#TaskFolderImpl(String, DBTTaskFolder, DBPProject, List)}
   *   <li>{@link TaskFolderImpl#setName(String)}
   *   <li>{@link TaskFolderImpl#getName()}
   *   <li>{@link TaskFolderImpl#getNestedTaskFolders()}
   *   <li>{@link TaskFolderImpl#getParentFolder()}
   *   <li>{@link TaskFolderImpl#getProject()}
   *   <li>{@link TaskFolderImpl#getTasks()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TaskFolderImpl.<init>(String, DBTTaskFolder, DBPProject, List)",
    "String TaskFolderImpl.getName()",
    "List TaskFolderImpl.getNestedTaskFolders()",
    "DBTTaskFolder TaskFolderImpl.getParentFolder()",
    "DBPProject TaskFolderImpl.getProject()",
    "List TaskFolderImpl.getTasks()",
    "void TaskFolderImpl.setName(String)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl folderProject =
        new LocalProjectImpl(workspace, new SessionContextImpl(null), projectPath);
    ArrayList<DBTTask> folderTasks = new ArrayList<>();

    // Act
    TaskFolderImpl actualTaskFolderImpl =
        new TaskFolderImpl("Folder Name", null, folderProject, folderTasks);
    actualTaskFolderImpl.setName("New Name");
    String actualName = actualTaskFolderImpl.getName();
    List<DBTTaskFolder> actualNestedTaskFolders = actualTaskFolderImpl.getNestedTaskFolders();
    DBTTaskFolder actualParentFolder = actualTaskFolderImpl.getParentFolder();
    DBPProject actualProject = actualTaskFolderImpl.getProject();
    List<DBTTask> actualTasks = actualTaskFolderImpl.getTasks();

    // Assert
    assertEquals("New Name", actualName);
    assertNull(actualParentFolder);
    assertTrue(actualNestedTaskFolders.isEmpty());
    assertTrue(actualTasks.isEmpty());
    assertSame(folderTasks, actualTasks);
    assertSame(folderProject, actualProject);
  }

  /**
   * Test {@link TaskFolderImpl#addTaskToFolder(DBTTask)}.
   *
   * <p>Method under test: {@link TaskFolderImpl#addTaskToFolder(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskFolderImpl.addTaskToFolder(DBTTask)"})
  public void testAddTaskToFolder() {
    // Arrange
    DBTTaskFolder parentFolder = mock(DBTTaskFolder.class);
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl folderProject = new LocalProjectImpl(workspace, sessionContext, projectPath);
    ArrayList<DBTTask> folderTasks = new ArrayList<>();

    TaskFolderImpl taskFolderImpl =
        new TaskFolderImpl("Folder Name", parentFolder, folderProject, folderTasks);
    DBPWorkspace workspace2 = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext2 = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl project = new LocalProjectImpl(workspace2, sessionContext2, projectPath2);
    Date createTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date updateTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    DBTTaskFolder parentFolder2 = mock(DBTTaskFolder.class);
    DBPWorkspace workspace3 = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext3 = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl folderProject2 =
        new LocalProjectImpl(workspace3, sessionContext3, projectPath3);

    TaskFolderImpl folder =
        new TaskFolderImpl("Folder Name", parentFolder2, folderProject2, new ArrayList<>());

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

    // Act
    taskFolderImpl.addTaskToFolder(task);

    // Assert
    List<DBTTask> tasks = taskFolderImpl.getTasks();
    assertEquals(1, tasks.size());
    assertSame(folderTasks, tasks);
    assertSame(task, tasks.get(0));
  }

  /**
   * Test {@link TaskFolderImpl#removeTaskFromFolder(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link List} {@link List#remove(Object)} return {@code true}.
   *   <li>Then calls {@link List#remove(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link TaskFolderImpl#removeTaskFromFolder(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskFolderImpl.removeTaskFromFolder(DBTTask)"})
  public void testRemoveTaskFromFolder_givenListRemoveReturnTrue_thenCallsRemove() {
    // Arrange
    when(list.remove(Mockito.<Object>any())).thenReturn(true);
    Date createTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Date updateTime =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());

    TaskImpl task =
        new TaskImpl(
            dBPProject,
            null,
            "42",
            "Label",
            "The characteristics of someone or something",
            createTime,
            updateTime,
            dBTTaskFolder);

    // Act
    taskFolderImpl.removeTaskFromFolder(task);

    // Assert
    verify(list).remove(isA(Object.class));
  }

  /**
   * Test {@link TaskFolderImpl#addFolderToFoldersList(DBTTaskFolder)}.
   *
   * <p>Method under test: {@link TaskFolderImpl#addFolderToFoldersList(DBTTaskFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TaskFolderImpl.addFolderToFoldersList(DBTTaskFolder)"})
  public void testAddFolderToFoldersList() {
    // Arrange and Act
    taskFolderImpl.addFolderToFoldersList(dBTTaskFolder);

    // Assert
    List<DBTTaskFolder> nestedTaskFolders = taskFolderImpl.getNestedTaskFolders();
    assertEquals(1, nestedTaskFolders.size());
    assertSame(dBTTaskFolder, nestedTaskFolders.get(0));
  }

  /**
   * Test {@link TaskFolderImpl#removeFolderFromFoldersList(DBTTaskFolder)}.
   *
   * <p>Method under test: {@link TaskFolderImpl#removeFolderFromFoldersList(DBTTaskFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TaskFolderImpl.removeFolderFromFoldersList(DBTTaskFolder)"})
  public void testRemoveFolderFromFoldersList() {
    // Arrange
    DBTTaskFolder parentFolder = mock(DBTTaskFolder.class);
    DBPWorkspace workspace = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl folderProject = new LocalProjectImpl(workspace, sessionContext, projectPath);

    TaskFolderImpl taskFolderImpl =
        new TaskFolderImpl("Folder Name", parentFolder, folderProject, new ArrayList<>());
    DBTTaskFolder parentFolder2 = mock(DBTTaskFolder.class);
    DBPWorkspace workspace2 = mock(DBPWorkspace.class);
    SessionContextImpl sessionContext2 = new SessionContextImpl(mock(SMSessionContext.class));
    Path projectPath2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    LocalProjectImpl folderProject2 =
        new LocalProjectImpl(workspace2, sessionContext2, projectPath2);

    TaskFolderImpl taskFolder =
        new TaskFolderImpl("Folder Name", parentFolder2, folderProject2, new ArrayList<>());

    // Act
    boolean actualRemoveFolderFromFoldersListResult =
        taskFolderImpl.removeFolderFromFoldersList(taskFolder);

    // Assert
    assertFalse(actualRemoveFolderFromFoldersListResult);
  }
}
