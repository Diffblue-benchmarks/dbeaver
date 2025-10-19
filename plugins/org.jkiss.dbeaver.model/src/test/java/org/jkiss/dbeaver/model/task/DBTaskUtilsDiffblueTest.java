package org.jkiss.dbeaver.model.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.VoidExecutionContextDefaults;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.task.DBTaskUtils.TaskConfirmationsCollector;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBTaskUtilsDiffblueTest {
  /**
   * Test {@link DBTaskUtils#getVariables(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DBTaskUtils#TASK_VARIABLES} is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#getVariables(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBTaskUtils.getVariables(DBTTask)"})
  public void testGetVariables_givenHashMapTask_variablesIsHashMap() {
    // Arrange
    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_VARIABLES, new HashMap<>());

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    // Act
    Map<String, Object> actualVariables = DBTaskUtils.getVariables(task);

    // Assert
    verify(task).getProperties();
    assertTrue(actualVariables.isEmpty());
  }

  /**
   * Test {@link DBTaskUtils#getVariables(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DBTaskUtils#TASK_VARIABLES} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#getVariables(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBTaskUtils.getVariables(DBTTask)"})
  public void testGetVariables_givenHashMapTask_variablesIsNull() {
    // Arrange
    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_VARIABLES, null);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    // Act
    Map<String, Object> actualVariables = DBTaskUtils.getVariables(task);

    // Assert
    verify(task).getProperties();
    assertTrue(actualVariables.isEmpty());
  }

  /**
   * Test {@link DBTaskUtils#setVariables(Map, Map)} with {@code taskState}, {@code variables}.
   *
   * <ul>
   *   <li>Given {@link DBTaskUtils#TASK_VARIABLES}.
   *   <li>Then {@link HashMap#HashMap()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#setVariables(Map, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.setVariables(Map, Map)"})
  public void testSetVariablesWithTaskStateVariables_givenTask_variables_thenHashMapSizeIsOne() {
    // Arrange
    HashMap<String, Object> taskState = new HashMap<>();

    HashMap<String, Object> variables = new HashMap<>();
    variables.put(DBTaskUtils.TASK_VARIABLES, DBPEvent.RENAME);

    // Act
    DBTaskUtils.setVariables(taskState, variables);

    // Assert
    assertEquals(1, taskState.size());
    Object getResult = taskState.get(DBTaskUtils.TASK_VARIABLES);
    assertTrue(getResult instanceof Map);
    assertEquals(1, ((Map<String, Object>) getResult).size());
    assertTrue(((Map<String, Object>) getResult).containsKey(DBTaskUtils.TASK_VARIABLES));
    assertSame(variables, getResult);
  }

  /**
   * Test {@link DBTaskUtils#setVariables(Map, Map)} with {@code taskState}, {@code variables}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then {@link HashMap#HashMap()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#setVariables(Map, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.setVariables(Map, Map)"})
  public void testSetVariablesWithTaskStateVariables_whenHashMap_thenHashMapEmpty() {
    // Arrange
    HashMap<String, Object> taskState = new HashMap<>();

    // Act
    DBTaskUtils.setVariables(taskState, new HashMap<>());

    // Assert that nothing has changed
    assertTrue(taskState.isEmpty());
  }

  /**
   * Test {@link DBTaskUtils#setVariables(Map, Map)} with {@code taskState}, {@code variables}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link HashMap#HashMap()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#setVariables(Map, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.setVariables(Map, Map)"})
  public void testSetVariablesWithTaskStateVariables_whenNull_thenHashMapEmpty() {
    // Arrange
    HashMap<String, Object> taskState = new HashMap<>();
    taskState.put(DBTaskUtils.TASK_VARIABLES, DBPEvent.RENAME);

    // Act
    DBTaskUtils.setVariables(taskState, null);

    // Assert
    assertTrue(taskState.isEmpty());
  }

  /**
   * Test {@link DBTaskUtils#setVariables(DBTTask, Map)} with {@code task}, {@code variables}.
   *
   * <ul>
   *   <li>Given {@link DBTaskUtils#TASK_VARIABLES}.
   *   <li>Then {@link HashMap#HashMap()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#setVariables(DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.setVariables(DBTTask, Map)"})
  public void testSetVariablesWithTaskVariables_givenTask_variables_thenHashMapSizeIsOne() {
    // Arrange
    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_VARIABLES, DBPEvent.RENAME);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    HashMap<String, Object> variables = new HashMap<>();
    variables.put(DBTaskUtils.TASK_VARIABLES, DBPEvent.RENAME);

    // Act
    DBTaskUtils.setVariables(task, variables);

    // Assert that nothing has changed
    verify(task).getProperties();
    assertEquals(1, variables.size());
    assertTrue(variables.containsKey(DBTaskUtils.TASK_VARIABLES));
  }

  /**
   * Test {@link DBTaskUtils#setVariables(DBTTask, Map)} with {@code task}, {@code variables}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#setVariables(DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.setVariables(DBTTask, Map)"})
  public void testSetVariablesWithTaskVariables_whenHashMap_thenHashMap() {
    // Arrange
    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_VARIABLES, DBPEvent.RENAME);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);
    HashMap<String, Object> variables = new HashMap<>();

    // Act
    DBTaskUtils.setVariables(task, variables);

    // Assert
    verify(task).getProperties();
    assertEquals(stringObjectMap, variables);
  }

  /**
   * Test {@link DBTaskUtils#setVariables(DBTTask, Map)} with {@code task}, {@code variables}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#setVariables(DBTTask, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.setVariables(DBTTask, Map)"})
  public void testSetVariablesWithTaskVariables_whenNull() {
    // Arrange
    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_VARIABLES, DBPEvent.RENAME);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    // Act
    DBTaskUtils.setVariables(task, null);

    // Assert that nothing has changed
    verify(task).getProperties();
  }

  /**
   * Test {@link DBTaskUtils#extractContext(DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>When {@link DBCExecutionContext} {@link DBCExecutionContext#isConnected()} return {@code
   *       false}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#extractContext(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskContext DBTaskUtils.extractContext(DBCExecutionContext)"})
  public void testExtractContext_givenFalse_whenDBCExecutionContextIsConnectedReturnFalse() {
    // Arrange
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.isConnected()).thenReturn(false);
    when(executionContext.getContextDefaults()).thenReturn(null);

    // Act
    DBTTaskContext actualExtractContextResult = DBTaskUtils.extractContext(executionContext);

    // Assert
    verify(executionContext).getContextDefaults();
    verify(executionContext).isConnected();
    assertNull(actualExtractContextResult.getDefaultCatalog());
    assertNull(actualExtractContextResult.getDefaultSchema());
    assertEquals(0, actualExtractContextResult.getTransactionIsolation());
    assertFalse(actualExtractContextResult.isAutoCommit());
  }

  /**
   * Test {@link DBTaskUtils#extractContext(DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link DBCExecutionContext} {@link DBCExecutionContext#isConnected()} return {@code
   *       true}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#extractContext(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskContext DBTaskUtils.extractContext(DBCExecutionContext)"})
  public void testExtractContext_givenTrue_whenDBCExecutionContextIsConnectedReturnTrue() {
    // Arrange
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.isConnected()).thenReturn(true);
    when(executionContext.getContextDefaults()).thenReturn(new VoidExecutionContextDefaults());

    // Act
    DBTTaskContext actualExtractContextResult = DBTaskUtils.extractContext(executionContext);

    // Assert
    verify(executionContext).getContextDefaults();
    verify(executionContext).isConnected();
    assertNull(actualExtractContextResult.getDefaultCatalog());
    assertNull(actualExtractContextResult.getDefaultSchema());
    assertEquals(0, actualExtractContextResult.getTransactionIsolation());
    assertFalse(actualExtractContextResult.isAutoCommit());
  }

  /**
   * Test {@link DBTaskUtils#loadTaskContext(Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code autoCommit} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#loadTaskContext(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskContext DBTaskUtils.loadTaskContext(Map)"})
  public void testLoadTaskContext_givenHashMapAutoCommitIsRename() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("defaultCatalog", null);
    objectObjectMap.put("defaultSchema", null);
    objectObjectMap.put("autoCommit", DBPEvent.RENAME);
    objectObjectMap.put("transactionIsolation", null);

    HashMap<String, Object> taskState = new HashMap<>();
    taskState.put(DBTaskUtils.TASK_CONTEXT, objectObjectMap);

    // Act
    DBTTaskContext actualLoadTaskContextResult = DBTaskUtils.loadTaskContext(taskState);

    // Assert
    assertNull(actualLoadTaskContextResult.getDefaultCatalog());
    assertNull(actualLoadTaskContextResult.getDefaultSchema());
    assertEquals(-1, actualLoadTaskContextResult.getTransactionIsolation());
    assertFalse(actualLoadTaskContextResult.isAutoCommit());
  }

  /**
   * Test {@link DBTaskUtils#loadTaskContext(Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code defaultSchema} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#loadTaskContext(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskContext DBTaskUtils.loadTaskContext(Map)"})
  public void testLoadTaskContext_givenHashMapDefaultSchemaIsRename() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("defaultCatalog", null);
    objectObjectMap.put("defaultSchema", DBPEvent.RENAME);
    objectObjectMap.put("autoCommit", null);
    objectObjectMap.put("transactionIsolation", null);

    HashMap<String, Object> taskState = new HashMap<>();
    taskState.put(DBTaskUtils.TASK_CONTEXT, objectObjectMap);

    // Act
    DBTTaskContext actualLoadTaskContextResult = DBTaskUtils.loadTaskContext(taskState);

    // Assert
    assertNull(actualLoadTaskContextResult.getDefaultCatalog());
    assertEquals(-1, actualLoadTaskContextResult.getTransactionIsolation());
    assertFalse(actualLoadTaskContextResult.isAutoCommit());
  }

  /**
   * Test {@link DBTaskUtils#loadTaskContext(Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code transactionIsolation} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#loadTaskContext(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskContext DBTaskUtils.loadTaskContext(Map)"})
  public void testLoadTaskContext_givenHashMapTransactionIsolationIsNull() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("defaultCatalog", null);
    objectObjectMap.put("defaultSchema", null);
    objectObjectMap.put("autoCommit", null);
    objectObjectMap.put("transactionIsolation", null);

    HashMap<String, Object> taskState = new HashMap<>();
    taskState.put(DBTaskUtils.TASK_CONTEXT, objectObjectMap);

    // Act
    DBTTaskContext actualLoadTaskContextResult = DBTaskUtils.loadTaskContext(taskState);

    // Assert
    assertNull(actualLoadTaskContextResult.getDefaultCatalog());
    assertNull(actualLoadTaskContextResult.getDefaultSchema());
    assertEquals(-1, actualLoadTaskContextResult.getTransactionIsolation());
    assertFalse(actualLoadTaskContextResult.isAutoCommit());
  }

  /**
   * Test {@link DBTaskUtils#loadTaskContext(Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code transactionIsolation} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#loadTaskContext(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskContext DBTaskUtils.loadTaskContext(Map)"})
  public void testLoadTaskContext_givenHashMapTransactionIsolationIsRename() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("defaultCatalog", null);
    objectObjectMap.put("defaultSchema", null);
    objectObjectMap.put("autoCommit", null);
    objectObjectMap.put("transactionIsolation", DBPEvent.RENAME);

    HashMap<String, Object> taskState = new HashMap<>();
    taskState.put(DBTaskUtils.TASK_CONTEXT, objectObjectMap);

    // Act
    DBTTaskContext actualLoadTaskContextResult = DBTaskUtils.loadTaskContext(taskState);

    // Assert
    assertNull(actualLoadTaskContextResult.getDefaultCatalog());
    assertNull(actualLoadTaskContextResult.getDefaultSchema());
    assertEquals(-1, actualLoadTaskContextResult.getTransactionIsolation());
    assertFalse(actualLoadTaskContextResult.isAutoCommit());
  }

  /**
   * Test {@link DBTaskUtils#loadTaskContext(Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code transactionIsolation} is space.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#loadTaskContext(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskContext DBTaskUtils.loadTaskContext(Map)"})
  public void testLoadTaskContext_givenHashMapTransactionIsolationIsSpace() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("defaultCatalog", null);
    objectObjectMap.put("defaultSchema", null);
    objectObjectMap.put("autoCommit", null);
    objectObjectMap.put("transactionIsolation", " ");

    HashMap<String, Object> taskState = new HashMap<>();
    taskState.put(DBTaskUtils.TASK_CONTEXT, objectObjectMap);

    // Act
    DBTTaskContext actualLoadTaskContextResult = DBTaskUtils.loadTaskContext(taskState);

    // Assert
    assertNull(actualLoadTaskContextResult.getDefaultCatalog());
    assertNull(actualLoadTaskContextResult.getDefaultSchema());
    assertEquals(-1, actualLoadTaskContextResult.getTransactionIsolation());
    assertFalse(actualLoadTaskContextResult.isAutoCommit());
  }

  /**
   * Test {@link DBTaskUtils#loadTaskContext(Map)}.
   *
   * <ul>
   *   <li>Then return DefaultCatalog is {@link DBTaskUtils#TASK_CONTEXT}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#loadTaskContext(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskContext DBTaskUtils.loadTaskContext(Map)"})
  public void testLoadTaskContext_thenReturnDefaultCatalogIsTask_context() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("defaultCatalog", DBTaskUtils.TASK_CONTEXT);
    objectObjectMap.put("defaultSchema", null);
    objectObjectMap.put("autoCommit", null);
    objectObjectMap.put("transactionIsolation", null);

    HashMap<String, Object> taskState = new HashMap<>();
    taskState.put(DBTaskUtils.TASK_CONTEXT, objectObjectMap);

    // Act
    DBTTaskContext actualLoadTaskContextResult = DBTaskUtils.loadTaskContext(taskState);

    // Assert
    assertNull(actualLoadTaskContextResult.getDefaultSchema());
    assertEquals(-1, actualLoadTaskContextResult.getTransactionIsolation());
    assertFalse(actualLoadTaskContextResult.isAutoCommit());
    assertEquals(DBTaskUtils.TASK_CONTEXT, actualLoadTaskContextResult.getDefaultCatalog());
  }

  /**
   * Test {@link DBTaskUtils#loadTaskContext(Map)}.
   *
   * <ul>
   *   <li>Then return TransactionIsolation is one.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#loadTaskContext(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskContext DBTaskUtils.loadTaskContext(Map)"})
  public void testLoadTaskContext_thenReturnTransactionIsolationIsOne() {
    // Arrange
    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("defaultCatalog", null);
    objectObjectMap.put("defaultSchema", null);
    objectObjectMap.put("autoCommit", null);
    objectObjectMap.put("transactionIsolation", "1");

    HashMap<String, Object> taskState = new HashMap<>();
    taskState.put(DBTaskUtils.TASK_CONTEXT, objectObjectMap);

    // Act
    DBTTaskContext actualLoadTaskContextResult = DBTaskUtils.loadTaskContext(taskState);

    // Assert
    assertNull(actualLoadTaskContextResult.getDefaultCatalog());
    assertNull(actualLoadTaskContextResult.getDefaultSchema());
    assertEquals(1, actualLoadTaskContextResult.getTransactionIsolation());
    assertFalse(actualLoadTaskContextResult.isAutoCommit());
  }

  /**
   * Test {@link DBTaskUtils#loadTaskContext(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#loadTaskContext(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBTTaskContext DBTaskUtils.loadTaskContext(Map)"})
  public void testLoadTaskContext_whenHashMap_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBTaskUtils.loadTaskContext(new HashMap<>()));
  }

  /**
   * Test {@link DBTaskUtils#saveTaskContext(Map, DBTTaskContext)}.
   *
   * <ul>
   *   <li>Given {@code Default Catalog}.
   *   <li>Then {@link HashMap#HashMap()} {@link DBTaskUtils#TASK_CONTEXT} size is four.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#saveTaskContext(Map, DBTTaskContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.saveTaskContext(Map, DBTTaskContext)"})
  public void testSaveTaskContext_givenDefaultCatalog_thenHashMapTask_contextSizeIsFour() {
    // Arrange
    HashMap<String, Object> taskState = new HashMap<>();

    DBTTaskContext context = new DBTTaskContext();
    context.setDefaultCatalog("Default Catalog");
    context.setDefaultSchema("Default Schema");
    context.setTransactionIsolation(1);

    // Act
    DBTaskUtils.saveTaskContext(taskState, context);

    // Assert
    assertEquals(1, taskState.size());
    Object getResult = taskState.get(DBTaskUtils.TASK_CONTEXT);
    assertTrue(getResult instanceof Map);
    assertEquals(4, ((Map<String, Object>) getResult).size());
    assertEquals("Default Catalog", ((Map<String, Object>) getResult).get("defaultCatalog"));
    assertEquals("Default Schema", ((Map<String, Object>) getResult).get("defaultSchema"));
    assertEquals(
        1, ((Integer) ((Map<String, Object>) getResult).get("transactionIsolation")).intValue());
    assertTrue(((Map<String, Object>) getResult).containsKey("autoCommit"));
  }

  /**
   * Test {@link DBTaskUtils#saveTaskContext(Map, DBTTaskContext)}.
   *
   * <ul>
   *   <li>Given minus one.
   *   <li>Then {@link HashMap#HashMap()} {@link DBTaskUtils#TASK_CONTEXT} size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#saveTaskContext(Map, DBTTaskContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.saveTaskContext(Map, DBTTaskContext)"})
  public void testSaveTaskContext_givenMinusOne_thenHashMapTask_contextSizeIsOne() {
    // Arrange
    HashMap<String, Object> taskState = new HashMap<>();
    taskState.put(DBTaskUtils.TASK_CONTEXT, DBPEvent.RENAME);

    DBTTaskContext context = new DBTTaskContext();
    context.setDefaultCatalog(null);
    context.setDefaultSchema(null);
    context.setTransactionIsolation(-1);

    // Act
    DBTaskUtils.saveTaskContext(taskState, context);

    // Assert
    assertEquals(1, taskState.size());
    Object getResult = taskState.get(DBTaskUtils.TASK_CONTEXT);
    assertTrue(getResult instanceof Map);
    assertEquals(1, ((Map<String, Boolean>) getResult).size());
    assertTrue(((Map<String, Boolean>) getResult).containsKey("autoCommit"));
  }

  /**
   * Test {@link DBTaskUtils#saveTaskContext(Map, DBTTaskContext)}.
   *
   * <ul>
   *   <li>Given {@link DBTaskUtils#TASK_CONTEXT}.
   *   <li>When {@code null}.
   *   <li>Then {@link HashMap#HashMap()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#saveTaskContext(Map, DBTTaskContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.saveTaskContext(Map, DBTTaskContext)"})
  public void testSaveTaskContext_givenTask_context_whenNull_thenHashMapEmpty() {
    // Arrange
    HashMap<String, Object> taskState = new HashMap<>();
    taskState.put(DBTaskUtils.TASK_CONTEXT, DBPEvent.RENAME);

    // Act
    DBTaskUtils.saveTaskContext(taskState, null);

    // Assert
    assertTrue(taskState.isEmpty());
  }

  /**
   * Test {@link DBTaskUtils#saveTaskContext(Map, DBTTaskContext)}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>Then {@link HashMap#HashMap()} {@link DBTaskUtils#TASK_CONTEXT} size is two.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#saveTaskContext(Map, DBTTaskContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.saveTaskContext(Map, DBTTaskContext)"})
  public void testSaveTaskContext_givenZero_thenHashMapTask_contextSizeIsTwo() {
    // Arrange
    HashMap<String, Object> taskState = new HashMap<>();
    taskState.put(DBTaskUtils.TASK_CONTEXT, DBPEvent.RENAME);

    DBTTaskContext context = new DBTTaskContext();
    context.setDefaultCatalog(null);
    context.setDefaultSchema(null);
    context.setTransactionIsolation(0);

    // Act
    DBTaskUtils.saveTaskContext(taskState, context);

    // Assert
    assertEquals(1, taskState.size());
    Object getResult = taskState.get(DBTaskUtils.TASK_CONTEXT);
    assertTrue(getResult instanceof Map);
    assertEquals(2, ((Map<String, Object>) getResult).size());
    assertEquals(
        0, ((Integer) ((Map<String, Object>) getResult).get("transactionIsolation")).intValue());
  }

  /**
   * Test {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTaskUtils.initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)"
  })
  public void testInitFromContext_givenDBPDataSource() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_CONTEXT, new HashMap<>());

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    DBTaskUtils.initFromContext(monitor, task, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    verify(task).getProperties();
  }

  /**
   * Test {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code autoCommit} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTaskUtils.initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)"
  })
  public void testInitFromContext_givenHashMapAutoCommitIsRename() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("autoCommit", DBPEvent.RENAME);

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_CONTEXT, objectObjectMap);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(null);

    // Act
    DBTaskUtils.initFromContext(monitor, task, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    verify(task).getProperties();
  }

  /**
   * Test {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code defaultCatalog} is {@code Properties}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTaskUtils.initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)"
  })
  public void testInitFromContext_givenHashMapDefaultCatalogIsProperties() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("defaultCatalog", "Properties");

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_CONTEXT, objectObjectMap);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(null);

    // Act
    DBTaskUtils.initFromContext(monitor, task, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    verify(task).getProperties();
  }

  /**
   * Test {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code defaultCatalog} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTaskUtils.initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)"
  })
  public void testInitFromContext_givenHashMapDefaultCatalogIsRename() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("defaultCatalog", DBPEvent.RENAME);

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_CONTEXT, objectObjectMap);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(null);

    // Act
    DBTaskUtils.initFromContext(monitor, task, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    verify(task).getProperties();
  }

  /**
   * Test {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DBTaskUtils#TASK_CONTEXT} is {@code null}.
   *   <li>When {@link DBCExecutionContext}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTaskUtils.initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)"
  })
  public void testInitFromContext_givenHashMapTask_contextIsNull_whenDBCExecutionContext()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_CONTEXT, null);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    // Act
    DBTaskUtils.initFromContext(monitor, task, mock(DBCExecutionContext.class));

    // Assert
    verify(task).getProperties();
  }

  /**
   * Test {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code transactionIsolation} is {@code Properties}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTaskUtils.initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)"
  })
  public void testInitFromContext_givenHashMapTransactionIsolationIsProperties()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("transactionIsolation", "Properties");

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_CONTEXT, objectObjectMap);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(null);

    // Act
    DBTaskUtils.initFromContext(monitor, task, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    verify(task).getProperties();
  }

  /**
   * Test {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code transactionIsolation} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTaskUtils.initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)"
  })
  public void testInitFromContext_givenHashMapTransactionIsolationIsRename() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put("transactionIsolation", DBPEvent.RENAME);

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_CONTEXT, objectObjectMap);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(null);

    // Act
    DBTaskUtils.initFromContext(monitor, task, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    verify(task).getProperties();
  }

  /**
   * Test {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link DBCExecutionContext} {@link DBCExecutionContext#getDataSource()} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#initFromContext(DBRProgressMonitor, DBTTask,
   * DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTaskUtils.initFromContext(DBRProgressMonitor, DBTTask, DBCExecutionContext)"
  })
  public void testInitFromContext_givenNull_whenDBCExecutionContextGetDataSourceReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_CONTEXT, new HashMap<>());

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(null);

    // Act
    DBTaskUtils.initFromContext(monitor, task, executionContext);

    // Assert
    verify(executionContext).getDataSource();
    verify(task).getProperties();
  }

  /**
   * Test {@link DBTaskUtils#isTaskExists(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link DBTTaskManager} {@link DBTTaskManager#getTaskById(String)} return {@link
   *       DBTTask}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#isTaskExists(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBTaskUtils.isTaskExists(DBTTask)"})
  public void testIsTaskExists_givenDBTTaskManagerGetTaskByIdReturnDBTTask_thenReturnTrue() {
    // Arrange
    DBTTaskManager dbtTaskManager = mock(DBTTaskManager.class);
    when(dbtTaskManager.getTaskById(Mockito.<String>any())).thenReturn(mock(DBTTask.class));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getTaskManager()).thenReturn(dbtTaskManager);

    DBTTask task = mock(DBTTask.class);
    when(task.getId()).thenReturn("42");
    when(task.getProject()).thenReturn(dbpProject);

    // Act
    boolean actualIsTaskExistsResult = DBTaskUtils.isTaskExists(task);

    // Assert
    verify(dbpProject).getTaskManager();
    verify(task).getId();
    verify(task).getProject();
    verify(dbtTaskManager).getTaskById("42");
    assertTrue(actualIsTaskExistsResult);
  }

  /**
   * Test {@link DBTaskUtils#isTaskExists(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link DBTTaskManager} {@link DBTTaskManager#getTaskById(String)} return {@code
   *       null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#isTaskExists(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBTaskUtils.isTaskExists(DBTTask)"})
  public void testIsTaskExists_givenDBTTaskManagerGetTaskByIdReturnNull_thenReturnFalse() {
    // Arrange
    DBTTaskManager dbtTaskManager = mock(DBTTaskManager.class);
    when(dbtTaskManager.getTaskById(Mockito.<String>any())).thenReturn(null);

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getTaskManager()).thenReturn(dbtTaskManager);

    DBTTask task = mock(DBTTask.class);
    when(task.getId()).thenReturn("42");
    when(task.getProject()).thenReturn(dbpProject);

    // Act
    boolean actualIsTaskExistsResult = DBTaskUtils.isTaskExists(task);

    // Assert
    verify(dbpProject).getTaskManager();
    verify(task).getId();
    verify(task).getProject();
    verify(dbtTaskManager).getTaskById("42");
    assertFalse(actualIsTaskExistsResult);
  }

  /**
   * Test {@link DBTaskUtils#isTaskExists(DBTTask)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#isTaskExists(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBTaskUtils.isTaskExists(DBTTask)"})
  public void testIsTaskExists_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(DBTaskUtils.isTaskExists(null));
  }

  /**
   * Test {@link DBTaskUtils#collectTaskVariables(DBTTask, Predicate, Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DBPEvent#RENAME} is {@link DBPEvent#RENAME}.
   *   <li>Then {@link HashMap#HashMap()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#collectTaskVariables(DBTTask, Predicate, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.collectTaskVariables(DBTTask, Predicate, Map)"})
  public void testCollectTaskVariables_givenHashMapRenameIsRename_thenHashMapSizeIsOne()
      throws DBException {
    // Arrange
    DBTTaskManager dbtTaskManager = mock(DBTTaskManager.class);
    when(dbtTaskManager.getTaskById(Mockito.<String>any())).thenReturn(mock(DBTTask.class));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getTaskManager()).thenReturn(dbtTaskManager);

    DBTTaskVariableCollector dbtTaskVariableCollector = mock(DBTTaskVariableCollector.class);
    doNothing()
        .when(dbtTaskVariableCollector)
        .collectTaskVariables(
            Mockito.<DBTTask>any(),
            Mockito.<Predicate<DBTTask>>any(),
            Mockito.<Map<DBTTask, Map<String, Object>>>any());

    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.createHandler()).thenReturn(dbtTaskVariableCollector);

    HashMap<Object, Object> objectObjectMap = new HashMap<>();
    objectObjectMap.put(DBPEvent.RENAME, DBPEvent.RENAME);

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_VARIABLES, objectObjectMap);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);
    when(task.getType()).thenReturn(dbtTaskType);
    when(task.getId()).thenReturn("42");
    when(task.getProject()).thenReturn(dbpProject);
    Predicate<DBTTask> predicate = DBTaskUtils::isTaskExists;
    HashMap<DBTTask, Map<String, Object>> variables = new HashMap<>();

    // Act
    DBTaskUtils.collectTaskVariables(task, predicate, variables);

    // Assert
    verify(dbpProject).getTaskManager();
    verify(task).getId();
    verify(task).getProject();
    verify(task).getProperties();
    verify(task).getType();
    verify(dbtTaskManager).getTaskById("42");
    verify(dbtTaskType).createHandler();
    verify(dbtTaskVariableCollector)
        .collectTaskVariables(isA(DBTTask.class), isA(Predicate.class), isA(Map.class));
    assertEquals(1, variables.size());
  }

  /**
   * Test {@link DBTaskUtils#collectTaskVariables(DBTTask, Predicate, Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DBTaskUtils#TASK_VARIABLES} is {@link
   *       HashMap#HashMap()}.
   *   <li>Then {@link HashMap#HashMap()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#collectTaskVariables(DBTTask, Predicate, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.collectTaskVariables(DBTTask, Predicate, Map)"})
  public void testCollectTaskVariables_givenHashMapTask_variablesIsHashMap_thenHashMapEmpty()
      throws DBException {
    // Arrange
    DBTTaskManager dbtTaskManager = mock(DBTTaskManager.class);
    when(dbtTaskManager.getTaskById(Mockito.<String>any())).thenReturn(mock(DBTTask.class));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getTaskManager()).thenReturn(dbtTaskManager);

    DBTTaskVariableCollector dbtTaskVariableCollector = mock(DBTTaskVariableCollector.class);
    doNothing()
        .when(dbtTaskVariableCollector)
        .collectTaskVariables(
            Mockito.<DBTTask>any(),
            Mockito.<Predicate<DBTTask>>any(),
            Mockito.<Map<DBTTask, Map<String, Object>>>any());

    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.createHandler()).thenReturn(dbtTaskVariableCollector);

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_VARIABLES, new HashMap<>());

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);
    when(task.getType()).thenReturn(dbtTaskType);
    when(task.getId()).thenReturn("42");
    when(task.getProject()).thenReturn(dbpProject);
    Predicate<DBTTask> predicate = DBTaskUtils::isTaskExists;
    HashMap<DBTTask, Map<String, Object>> variables = new HashMap<>();

    // Act
    DBTaskUtils.collectTaskVariables(task, predicate, variables);

    // Assert that nothing has changed
    verify(dbpProject).getTaskManager();
    verify(task).getId();
    verify(task).getProject();
    verify(task).getProperties();
    verify(task).getType();
    verify(dbtTaskManager).getTaskById("42");
    verify(dbtTaskType).createHandler();
    verify(dbtTaskVariableCollector)
        .collectTaskVariables(isA(DBTTask.class), isA(Predicate.class), isA(Map.class));
    assertTrue(variables.isEmpty());
  }

  /**
   * Test {@link DBTaskUtils#collectTaskVariables(DBTTask, Predicate, Map)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@link DBTaskUtils#TASK_VARIABLES} is {@code null}.
   *   <li>Then {@link HashMap#HashMap()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#collectTaskVariables(DBTTask, Predicate, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTaskUtils.collectTaskVariables(DBTTask, Predicate, Map)"})
  public void testCollectTaskVariables_givenHashMapTask_variablesIsNull_thenHashMapEmpty()
      throws DBException {
    // Arrange
    DBTTaskManager dbtTaskManager = mock(DBTTaskManager.class);
    when(dbtTaskManager.getTaskById(Mockito.<String>any())).thenReturn(mock(DBTTask.class));

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getTaskManager()).thenReturn(dbtTaskManager);

    DBTTaskVariableCollector dbtTaskVariableCollector = mock(DBTTaskVariableCollector.class);
    doNothing()
        .when(dbtTaskVariableCollector)
        .collectTaskVariables(
            Mockito.<DBTTask>any(),
            Mockito.<Predicate<DBTTask>>any(),
            Mockito.<Map<DBTTask, Map<String, Object>>>any());

    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.createHandler()).thenReturn(dbtTaskVariableCollector);

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put(DBTaskUtils.TASK_VARIABLES, null);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);
    when(task.getType()).thenReturn(dbtTaskType);
    when(task.getId()).thenReturn("42");
    when(task.getProject()).thenReturn(dbpProject);
    Predicate<DBTTask> predicate = DBTaskUtils::isTaskExists;
    HashMap<DBTTask, Map<String, Object>> variables = new HashMap<>();

    // Act
    DBTaskUtils.collectTaskVariables(task, predicate, variables);

    // Assert that nothing has changed
    verify(dbpProject).getTaskManager();
    verify(task).getId();
    verify(task).getProject();
    verify(task).getProperties();
    verify(task).getType();
    verify(dbtTaskManager).getTaskById("42");
    verify(dbtTaskType).createHandler();
    verify(dbtTaskVariableCollector)
        .collectTaskVariables(isA(DBTTask.class), isA(Predicate.class), isA(Map.class));
    assertTrue(variables.isEmpty());
  }

  /**
   * Test {@link DBTaskUtils#collectConfirmationMessages(StringBuilder, DBTTask,
   * TaskConfirmationsCollector)}.
   *
   * <p>Method under test: {@link DBTaskUtils#collectConfirmationMessages(StringBuilder, DBTTask,
   * TaskConfirmationsCollector)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBTaskUtils.collectConfirmationMessages(StringBuilder, DBTTask, TaskConfirmationsCollector)"
  })
  public void testCollectConfirmationMessages() {
    // Arrange
    StringBuilder messageBuilder = new StringBuilder("foo");

    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.confirmationMessageIfNeeded()).thenReturn("Confirmation Message If Needed");

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(new HashMap<>());
    when(task.getType()).thenReturn(dbtTaskType);

    TaskConfirmationsCollector extraConfirmationsCollector = mock(TaskConfirmationsCollector.class);
    when(extraConfirmationsCollector.collect(Mockito.<StringBuilder>any(), Mockito.<DBTTask>any()))
        .thenReturn(true);

    // Act
    boolean actualCollectConfirmationMessagesResult =
        DBTaskUtils.collectConfirmationMessages(messageBuilder, task, extraConfirmationsCollector);

    // Assert
    verify(task, atLeast(1)).getProperties();
    verify(task).getType();
    verify(dbtTaskType).confirmationMessageIfNeeded();
    verify(extraConfirmationsCollector).collect(isA(StringBuilder.class), isA(DBTTask.class));
    assertEquals("fooConfirmation Message If Needed\n", messageBuilder.toString());
    assertTrue(actualCollectConfirmationMessagesResult);
  }

  /**
   * Test {@link DBTaskUtils#collectConfirmationMessages(StringBuilder, DBTTask,
   * TaskConfirmationsCollector)}.
   *
   * <p>Method under test: {@link DBTaskUtils#collectConfirmationMessages(StringBuilder, DBTTask,
   * TaskConfirmationsCollector)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBTaskUtils.collectConfirmationMessages(StringBuilder, DBTTask, TaskConfirmationsCollector)"
  })
  public void testCollectConfirmationMessages2() {
    // Arrange
    StringBuilder messageBuilder = new StringBuilder("foo");

    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.confirmationMessageIfNeeded()).thenReturn("\n");

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(new HashMap<>());
    when(task.getType()).thenReturn(dbtTaskType);

    TaskConfirmationsCollector extraConfirmationsCollector = mock(TaskConfirmationsCollector.class);
    when(extraConfirmationsCollector.collect(Mockito.<StringBuilder>any(), Mockito.<DBTTask>any()))
        .thenReturn(true);

    // Act
    boolean actualCollectConfirmationMessagesResult =
        DBTaskUtils.collectConfirmationMessages(messageBuilder, task, extraConfirmationsCollector);

    // Assert
    verify(task, atLeast(1)).getProperties();
    verify(task).getType();
    verify(dbtTaskType).confirmationMessageIfNeeded();
    verify(extraConfirmationsCollector).collect(isA(StringBuilder.class), isA(DBTTask.class));
    assertEquals("foo\n\n", messageBuilder.toString());
    assertTrue(actualCollectConfirmationMessagesResult);
  }

  /**
   * Test {@link DBTaskUtils#collectConfirmationMessages(StringBuilder, DBTTask,
   * TaskConfirmationsCollector)}.
   *
   * <p>Method under test: {@link DBTaskUtils#collectConfirmationMessages(StringBuilder, DBTTask,
   * TaskConfirmationsCollector)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBTaskUtils.collectConfirmationMessages(StringBuilder, DBTTask, TaskConfirmationsCollector)"
  })
  public void testCollectConfirmationMessages3() {
    // Arrange
    StringBuilder messageBuilder = new StringBuilder("foo");

    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.confirmationMessageIfNeeded()).thenReturn(null);

    DBTTask task = mock(DBTTask.class);
    when(task.getType()).thenReturn(dbtTaskType);

    TaskConfirmationsCollector extraConfirmationsCollector = mock(TaskConfirmationsCollector.class);
    when(extraConfirmationsCollector.collect(Mockito.<StringBuilder>any(), Mockito.<DBTTask>any()))
        .thenReturn(true);

    // Act
    boolean actualCollectConfirmationMessagesResult =
        DBTaskUtils.collectConfirmationMessages(messageBuilder, task, extraConfirmationsCollector);

    // Assert
    verify(task).getType();
    verify(dbtTaskType).confirmationMessageIfNeeded();
    verify(extraConfirmationsCollector).collect(isA(StringBuilder.class), isA(DBTTask.class));
    assertEquals("foo", messageBuilder.toString());
    assertTrue(actualCollectConfirmationMessagesResult);
  }

  /**
   * Test {@link DBTaskUtils#collectConfirmationMessages(StringBuilder, DBTTask,
   * TaskConfirmationsCollector)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#collectConfirmationMessages(StringBuilder, DBTTask,
   * TaskConfirmationsCollector)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBTaskUtils.collectConfirmationMessages(StringBuilder, DBTTask, TaskConfirmationsCollector)"
  })
  public void testCollectConfirmationMessages_givenFalse_thenReturnFalse() {
    // Arrange
    StringBuilder messageBuilder = new StringBuilder("foo");

    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.confirmationMessageIfNeeded()).thenReturn(null);

    DBTTask task = mock(DBTTask.class);
    when(task.getType()).thenReturn(dbtTaskType);

    TaskConfirmationsCollector extraConfirmationsCollector = mock(TaskConfirmationsCollector.class);
    when(extraConfirmationsCollector.collect(Mockito.<StringBuilder>any(), Mockito.<DBTTask>any()))
        .thenReturn(false);

    // Act
    boolean actualCollectConfirmationMessagesResult =
        DBTaskUtils.collectConfirmationMessages(messageBuilder, task, extraConfirmationsCollector);

    // Assert
    verify(task).getType();
    verify(dbtTaskType).confirmationMessageIfNeeded();
    verify(extraConfirmationsCollector).collect(isA(StringBuilder.class), isA(DBTTask.class));
    assertEquals("foo", messageBuilder.toString());
    assertFalse(actualCollectConfirmationMessagesResult);
  }

  /**
   * Test {@link DBTaskUtils#collectConfirmationMessages(StringBuilder, DBTTask,
   * TaskConfirmationsCollector)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code 42} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#collectConfirmationMessages(StringBuilder, DBTTask,
   * TaskConfirmationsCollector)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBTaskUtils.collectConfirmationMessages(StringBuilder, DBTTask, TaskConfirmationsCollector)"
  })
  public void testCollectConfirmationMessages_givenHashMap42IsRename() {
    // Arrange
    StringBuilder messageBuilder = new StringBuilder("foo");

    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.confirmationMessageIfNeeded()).thenReturn("Confirmation Message If Needed");

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("42", DBPEvent.RENAME);
    stringObjectMap.putIfAbsent("file", DBPEvent.RENAME);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);
    when(task.getType()).thenReturn(dbtTaskType);

    TaskConfirmationsCollector extraConfirmationsCollector = mock(TaskConfirmationsCollector.class);
    when(extraConfirmationsCollector.collect(Mockito.<StringBuilder>any(), Mockito.<DBTTask>any()))
        .thenReturn(true);

    // Act
    boolean actualCollectConfirmationMessagesResult =
        DBTaskUtils.collectConfirmationMessages(messageBuilder, task, extraConfirmationsCollector);

    // Assert
    verify(task, atLeast(1)).getProperties();
    verify(task).getType();
    verify(dbtTaskType).confirmationMessageIfNeeded();
    verify(extraConfirmationsCollector).collect(isA(StringBuilder.class), isA(DBTTask.class));
    assertEquals("fooConfirmation Message If Needed\n", messageBuilder.toString());
    assertTrue(actualCollectConfirmationMessagesResult);
  }

  /**
   * Test {@link DBTaskUtils#collectConfirmationMessages(StringBuilder, DBTTask,
   * TaskConfirmationsCollector)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code file} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBTaskUtils#collectConfirmationMessages(StringBuilder, DBTTask,
   * TaskConfirmationsCollector)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DBTaskUtils.collectConfirmationMessages(StringBuilder, DBTTask, TaskConfirmationsCollector)"
  })
  public void testCollectConfirmationMessages_givenHashMapFileIsRename() {
    // Arrange
    StringBuilder messageBuilder = new StringBuilder("foo");

    DBTTaskType dbtTaskType = mock(DBTTaskType.class);
    when(dbtTaskType.confirmationMessageIfNeeded()).thenReturn("Confirmation Message If Needed");

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("file", DBPEvent.RENAME);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);
    when(task.getType()).thenReturn(dbtTaskType);

    TaskConfirmationsCollector extraConfirmationsCollector = mock(TaskConfirmationsCollector.class);
    when(extraConfirmationsCollector.collect(Mockito.<StringBuilder>any(), Mockito.<DBTTask>any()))
        .thenReturn(true);

    // Act
    boolean actualCollectConfirmationMessagesResult =
        DBTaskUtils.collectConfirmationMessages(messageBuilder, task, extraConfirmationsCollector);

    // Assert
    verify(task, atLeast(1)).getProperties();
    verify(task).getType();
    verify(dbtTaskType).confirmationMessageIfNeeded();
    verify(extraConfirmationsCollector).collect(isA(StringBuilder.class), isA(DBTTask.class));
    assertEquals("fooConfirmation Message If Needed\n", messageBuilder.toString());
    assertTrue(actualCollectConfirmationMessagesResult);
  }
}
