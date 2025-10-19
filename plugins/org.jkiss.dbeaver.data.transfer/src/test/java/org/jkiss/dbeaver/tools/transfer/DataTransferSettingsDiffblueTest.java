package org.jkiss.dbeaver.tools.transfer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DBRRunnableContext;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.model.task.DBTTaskInfoCollector;
import org.jkiss.dbeaver.model.task.DBTTaskInfoCollector.TaskInformation;
import org.jkiss.dbeaver.model.task.DBTTaskType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataTransferSettingsDiffblueTest {
  /**
   * Test {@link DataTransferSettings#DataTransferSettings(DBRProgressMonitor, DBTTask, Log, Map,
   * DataTransferState, boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferSettings#DataTransferSettings(DBRProgressMonitor,
   * DBTTask, Log, Map, DataTransferState, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferSettings.<init>(DBRProgressMonitor, DBTTask, Log, Map, DataTransferState, boolean)"
  })
  public void testNewDataTransferSettings_thenThrowIllegalArgumentException() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("producers", new ArrayList<>());

    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.getId()).thenThrow(new IllegalArgumentException());

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);
    when(task.getProject()).thenReturn(null);
    when(task.getType()).thenReturn(dbtTaskType);
    Class<Object> forClass = Object.class;
    Log taskLog = Log.getLog(forClass);
    HashMap<String, Object> configuration = new HashMap<>();

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            new DataTransferSettings(
                monitor, task, taskLog, configuration, new DataTransferState(), true));
    verify(task).getProject();
    verify(task, atLeast(1)).getProperties();
    verify(task).getType();
    verify(dbtTaskType).getId();
  }

  /**
   * Test {@link DataTransferSettings#isExportTask(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link DBTTaskType} {@link DBTTaskType#getId()} return {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferSettings#isExportTask(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTransferSettings.isExportTask(DBTTask)"})
  public void testIsExportTask_givenDBTTaskTypeGetIdReturn42_thenReturnFalse() {
    // Arrange
    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.getId()).thenReturn("42");

    DBTTask task = mock(DBTTask.class);
    when(task.getType()).thenReturn(dbtTaskType);

    // Act
    boolean actualIsExportTaskResult = DataTransferSettings.isExportTask(task);

    // Assert
    verify(task).getType();
    verify(dbtTaskType).getId();
    assertFalse(actualIsExportTaskResult);
  }

  /**
   * Test {@link DataTransferSettings#isExportTask(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link DBTTaskType} {@link DBTTaskType#getId()} return {@link
   *       DTConstants#TASK_EXPORT}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferSettings#isExportTask(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTransferSettings.isExportTask(DBTTask)"})
  public void testIsExportTask_givenDBTTaskTypeGetIdReturnTask_export_thenReturnTrue() {
    // Arrange
    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.getId()).thenReturn(DTConstants.TASK_EXPORT);

    DBTTask task = mock(DBTTask.class);
    when(task.getType()).thenReturn(dbtTaskType);

    // Act
    boolean actualIsExportTaskResult = DataTransferSettings.isExportTask(task);

    // Assert
    verify(task).getType();
    verify(dbtTaskType).getId();
    assertTrue(actualIsExportTaskResult);
  }

  /**
   * Test {@link DataTransferSettings#isExportTask(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link DBTTaskType} {@link DBTTaskType#getId()} throw {@link
   *       IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferSettings#isExportTask(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTransferSettings.isExportTask(DBTTask)"})
  public void testIsExportTask_givenDBTTaskTypeGetIdThrowIllegalArgumentException() {
    // Arrange
    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.getId()).thenThrow(new IllegalArgumentException());

    DBTTask task = mock(DBTTask.class);
    when(task.getType()).thenReturn(dbtTaskType);

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DataTransferSettings.isExportTask(task));
    verify(task).getType();
    verify(dbtTaskType).getId();
  }

  /**
   * Test {@link DataTransferSettings#isExportTask(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link IllegalArgumentException#IllegalArgumentException()}.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferSettings#isExportTask(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataTransferSettings.isExportTask(DBTTask)"})
  public void testIsExportTask_givenIllegalArgumentException() {
    // Arrange
    DBTTask task = mock(DBTTask.class);
    when(task.getType()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(IllegalArgumentException.class, () -> DataTransferSettings.isExportTask(task));
    verify(task).getType();
  }

  /**
   * Test {@link DataTransferSettings#saveNodesLocation(DBRRunnableContext, DBTTask, Map,
   * Collection, String)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then {@link HashMap#HashMap()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferSettings#saveNodesLocation(DBRRunnableContext,
   * DBTTask, Map, Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferSettings.saveNodesLocation(DBRRunnableContext, DBTTask, Map, Collection, String)"
  })
  public void testSaveNodesLocation_whenArrayList_thenHashMapSizeIsOne() throws DBException {
    // Arrange
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    DBTTask task = mock(DBTTask.class);
    HashMap<String, Object> state = new HashMap<>();

    // Act
    DataTransferSettings.saveNodesLocation(
        runnableContext, task, state, new ArrayList<>(), "Node Type");

    // Assert
    assertEquals(1, state.size());
    Object getResult = state.get("Node Type");
    assertTrue(getResult instanceof List);
    assertTrue(((List<Object>) getResult).isEmpty());
  }

  /**
   * Test {@link DataTransferSettings#saveNodesLocation(DBRRunnableContext, DBTTask, Map,
   * Collection, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link HashMap#HashMap()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferSettings#saveNodesLocation(DBRRunnableContext,
   * DBTTask, Map, Collection, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferSettings.saveNodesLocation(DBRRunnableContext, DBTTask, Map, Collection, String)"
  })
  public void testSaveNodesLocation_whenNull_thenHashMapEmpty() throws DBException {
    // Arrange
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    DBTTask task = mock(DBTTask.class);
    HashMap<String, Object> state = new HashMap<>();

    // Act
    DataTransferSettings.saveNodesLocation(runnableContext, task, state, null, "Node Type");

    // Assert that nothing has changed
    assertTrue(state.isEmpty());
  }

  /**
   * Test {@link DataTransferSettings#getNodesFromLocation(DBRProgressMonitor, DBTTask,
   * DataTransferState, Log, String, Class)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferSettings#getNodesFromLocation(DBRProgressMonitor,
   * DBTTask, DataTransferState, Log, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DataTransferSettings.getNodesFromLocation(DBRProgressMonitor, DBTTask, DataTransferState, Log, String, Class)"
  })
  public void testGetNodesFromLocation_givenHashMap_thenReturnEmpty() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(new HashMap<>());
    DataTransferState state = new DataTransferState();
    Class<Object> forClass = Object.class;
    Log taskLog = Log.getLog(forClass);
    Class<Object> nodeClass = Object.class;

    // Act
    List<Object> actualNodesFromLocation =
        DataTransferSettings.getNodesFromLocation(
            monitor, task, state, taskLog, "Node Type", nodeClass);

    // Assert
    verify(task).getProperties();
    assertTrue(actualNodesFromLocation.isEmpty());
  }

  /**
   * Test {@link DataTransferSettings#getNodesFromLocation(DBRProgressMonitor, DBTTask,
   * DataTransferState, Log, String, Class)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferSettings#getNodesFromLocation(DBRProgressMonitor,
   * DBTTask, DataTransferState, Log, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DataTransferSettings.getNodesFromLocation(DBRProgressMonitor, DBTTask, DataTransferState, Log, String, Class)"
  })
  public void testGetNodesFromLocation_thenThrowIllegalArgumentException() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenThrow(new IllegalArgumentException());
    DataTransferState state = new DataTransferState();
    Class<Object> forClass = Object.class;
    Log taskLog = Log.getLog(forClass);
    Class<Object> nodeClass = Object.class;

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            DataTransferSettings.getNodesFromLocation(
                monitor, task, state, taskLog, "Node Type", nodeClass));
    verify(task).getProperties();
  }

  /**
   * Test {@link DataTransferSettings#collectTaskInfo(DBTTask, TaskInformation)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()}.
   *   <li>When {@link DBTTask} {@link DBTTask#getProperties()} return {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferSettings#collectTaskInfo(DBTTask, TaskInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTransferSettings.collectTaskInfo(DBTTask, TaskInformation)"})
  public void testCollectTaskInfo_givenHashMap_whenDBTTaskGetPropertiesReturnHashMap() {
    // Arrange
    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(new HashMap<>());

    // Act
    DataTransferSettings.collectTaskInfo(task, new TaskInformation());

    // Assert
    verify(task).getProperties();
  }

  /**
   * Test {@link DataTransferSettings#collectTaskInfo(DBTTask, TaskInformation)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link DataTransferSettings#collectTaskInfo(DBTTask, TaskInformation)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTransferSettings.collectTaskInfo(DBTTask, TaskInformation)"})
  public void testCollectTaskInfo_thenThrowIllegalArgumentException() {
    // Arrange
    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenThrow(new IllegalArgumentException());

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> DataTransferSettings.collectTaskInfo(task, new TaskInformation()));
    verify(task).getProperties();
  }
}
