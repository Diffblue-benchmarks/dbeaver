package org.jkiss.dbeaver.tools.sql;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPTransactionIsolation;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLScriptExecuteSettingsDiffblueTest {
  /**
   * Test {@link SQLScriptExecuteSettings#loadConfiguration(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code dataSources} is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptExecuteSettings#loadConfiguration(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptExecuteSettings.loadConfiguration(DBTTask)"})
  public void testLoadConfiguration_givenHashMapDataSourcesIsArrayList() throws DBException {
    // Arrange
    SQLScriptExecuteSettings sqlScriptExecuteSettings = new SQLScriptExecuteSettings();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("dataSources", new ArrayList<>());

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    // Act
    sqlScriptExecuteSettings.loadConfiguration(task);

    // Assert that nothing has changed
    verify(task).getProperties();
    assertTrue(sqlScriptExecuteSettings.getScriptFiles().isEmpty());
  }

  /**
   * Test {@link SQLScriptExecuteSettings#loadConfiguration(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code ignoreErrors} is {@code Properties}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptExecuteSettings#loadConfiguration(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptExecuteSettings.loadConfiguration(DBTTask)"})
  public void testLoadConfiguration_givenHashMapIgnoreErrorsIsProperties() throws DBException {
    // Arrange
    SQLScriptExecuteSettings sqlScriptExecuteSettings = new SQLScriptExecuteSettings();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("ignoreErrors", "Properties");

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    // Act
    sqlScriptExecuteSettings.loadConfiguration(task);

    // Assert that nothing has changed
    verify(task).getProperties();
    assertTrue(sqlScriptExecuteSettings.getScriptFiles().isEmpty());
  }

  /**
   * Test {@link SQLScriptExecuteSettings#loadConfiguration(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code scriptFiles} is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptExecuteSettings#loadConfiguration(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptExecuteSettings.loadConfiguration(DBTTask)"})
  public void testLoadConfiguration_givenHashMapScriptFilesIsArrayList() throws DBException {
    // Arrange
    SQLScriptExecuteSettings sqlScriptExecuteSettings = new SQLScriptExecuteSettings();

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("scriptFiles", new ArrayList<>());

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    // Act
    sqlScriptExecuteSettings.loadConfiguration(task);

    // Assert that nothing has changed
    verify(task).getProperties();
    assertTrue(sqlScriptExecuteSettings.getScriptFiles().isEmpty());
  }

  /**
   * Test {@link SQLScriptExecuteSettings#loadConfiguration(DBTTask)}.
   *
   * <ul>
   *   <li>Given {@link LinkedHashSet#LinkedHashSet()} add {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptExecuteSettings#loadConfiguration(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptExecuteSettings.loadConfiguration(DBTTask)"})
  public void testLoadConfiguration_givenLinkedHashSetAddHashMap() throws DBException {
    // Arrange
    SQLScriptExecuteSettings sqlScriptExecuteSettings = new SQLScriptExecuteSettings();

    LinkedHashSet<Object> objectSet = new LinkedHashSet<>();
    objectSet.add(new HashMap<>());

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("dataSources", objectSet);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    // Act
    sqlScriptExecuteSettings.loadConfiguration(task);

    // Assert that nothing has changed
    verify(task).getProperties();
    assertTrue(sqlScriptExecuteSettings.getScriptFiles().isEmpty());
  }

  /**
   * Test {@link SQLScriptExecuteSettings#loadConfiguration(DBTTask)}.
   *
   * <ul>
   *   <li>Then {@link SQLScriptExecuteSettings} (default constructor) ScriptFiles size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptExecuteSettings#loadConfiguration(DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptExecuteSettings.loadConfiguration(DBTTask)"})
  public void testLoadConfiguration_thenSQLScriptExecuteSettingsScriptFilesSizeIsOne()
      throws DBException {
    // Arrange
    SQLScriptExecuteSettings sqlScriptExecuteSettings = new SQLScriptExecuteSettings();

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("scriptFiles", objectList);

    DBTTask task = mock(DBTTask.class);
    when(task.getProperties()).thenReturn(stringObjectMap);

    // Act
    sqlScriptExecuteSettings.loadConfiguration(task);

    // Assert
    verify(task).getProperties();
    List<String> scriptFiles = sqlScriptExecuteSettings.getScriptFiles();
    assertEquals(1, scriptFiles.size());
    assertEquals("42", scriptFiles.get(0));
  }

  /**
   * Test {@link SQLScriptExecuteSettings#saveConfiguration(Map)}.
   *
   * <ul>
   *   <li>Given {@link SQLScriptExecuteSettings} (default constructor).
   *   <li>Then {@link HashMap#HashMap()} {@code scriptFiles} {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptExecuteSettings#saveConfiguration(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptExecuteSettings.saveConfiguration(Map)"})
  public void testSaveConfiguration_givenSQLScriptExecuteSettings_thenHashMapScriptFilesList() {
    // Arrange
    SQLScriptExecuteSettings sqlScriptExecuteSettings = new SQLScriptExecuteSettings();
    HashMap<String, Object> config = new HashMap<>();

    // Act
    sqlScriptExecuteSettings.saveConfiguration(config);

    // Assert
    assertEquals(5, config.size());
    Object getResult = config.get("scriptFiles");
    assertTrue(getResult instanceof List);
    assertFalse((Boolean) config.get("autoCommit"));
    assertFalse((Boolean) config.get("dumpQueryResultsToLog"));
    assertFalse((Boolean) config.get("ignoreErrors"));
    assertTrue(((List<Object>) getResult).isEmpty());
    assertEquals(getResult, config.get("dataSources"));
    assertSame(getResult, sqlScriptExecuteSettings.getScriptFiles());
  }

  /**
   * Test {@link SQLScriptExecuteSettings#saveConfiguration(Map)}.
   *
   * <ul>
   *   <li>Then {@link HashMap#HashMap()} {@code dataSources} {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptExecuteSettings#saveConfiguration(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLScriptExecuteSettings.saveConfiguration(Map)"})
  public void testSaveConfiguration_thenHashMapDataSourcesList() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getName()).thenReturn("Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getId()).thenReturn("42");
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    ArrayList<DBPDataSourceContainer> dataSources = new ArrayList<>();
    dataSources.add(dbpDataSourceContainer);

    SQLScriptExecuteSettings sqlScriptExecuteSettings = new SQLScriptExecuteSettings();
    sqlScriptExecuteSettings.setDataSources(dataSources);
    HashMap<String, Object> config = new HashMap<>();

    // Act
    sqlScriptExecuteSettings.saveConfiguration(config);

    // Assert
    verify(dbpDataSourceContainer).getId();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject).getName();
    assertEquals(5, config.size());
    Object getResult = config.get("dataSources");
    assertTrue(getResult instanceof List);
    assertEquals(1, ((List<LinkedHashMap>) getResult).size());
    LinkedHashMap getResult2 = ((List<LinkedHashMap>) getResult).get(0);
    assertEquals(2, getResult2.size());
    assertEquals("42", getResult2.get("dataSource"));
    assertEquals("Name", getResult2.get("project"));
    assertTrue(config.containsKey("autoCommit"));
    assertTrue(config.containsKey("dumpQueryResultsToLog"));
    assertTrue(config.containsKey("ignoreErrors"));
    assertTrue(config.containsKey("scriptFiles"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SQLScriptExecuteSettings}
   *   <li>{@link SQLScriptExecuteSettings#setDataSources(List)}
   *   <li>{@link SQLScriptExecuteSettings#setDumpQueryResultsToLog(boolean)}
   *   <li>{@link SQLScriptExecuteSettings#setIgnoreErrors(boolean)}
   *   <li>{@link SQLScriptExecuteSettings#setScriptFiles(List)}
   *   <li>{@link SQLScriptExecuteSettings#setTransactionIsolation(DBPTransactionIsolation)}
   *   <li>{@link SQLScriptExecuteSettings#setAutoCommit(boolean)}
   *   <li>{@link SQLScriptExecuteSettings#getDataSources()}
   *   <li>{@link SQLScriptExecuteSettings#getScriptFiles()}
   *   <li>{@link SQLScriptExecuteSettings#getTransactionIsolation()}
   *   <li>{@link SQLScriptExecuteSettings#isAutoCommit()}
   *   <li>{@link SQLScriptExecuteSettings#isDumpQueryResultsToLog()}
   *   <li>{@link SQLScriptExecuteSettings#isIgnoreErrors()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLScriptExecuteSettings.<init>()",
    "List SQLScriptExecuteSettings.getDataSources()",
    "List SQLScriptExecuteSettings.getScriptFiles()",
    "DBPTransactionIsolation SQLScriptExecuteSettings.getTransactionIsolation()",
    "boolean SQLScriptExecuteSettings.isAutoCommit()",
    "boolean SQLScriptExecuteSettings.isDumpQueryResultsToLog()",
    "boolean SQLScriptExecuteSettings.isIgnoreErrors()",
    "void SQLScriptExecuteSettings.setAutoCommit(boolean)",
    "void SQLScriptExecuteSettings.setDataSources(List)",
    "void SQLScriptExecuteSettings.setDumpQueryResultsToLog(boolean)",
    "void SQLScriptExecuteSettings.setIgnoreErrors(boolean)",
    "void SQLScriptExecuteSettings.setScriptFiles(List)",
    "void SQLScriptExecuteSettings.setTransactionIsolation(DBPTransactionIsolation)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SQLScriptExecuteSettings actualSqlScriptExecuteSettings = new SQLScriptExecuteSettings();
    ArrayList<DBPDataSourceContainer> dataSources = new ArrayList<>();
    actualSqlScriptExecuteSettings.setDataSources(dataSources);
    actualSqlScriptExecuteSettings.setDumpQueryResultsToLog(true);
    actualSqlScriptExecuteSettings.setIgnoreErrors(true);
    ArrayList<String> scriptFiles = new ArrayList<>();
    actualSqlScriptExecuteSettings.setScriptFiles(scriptFiles);
    DBPTransactionIsolation transactionIsolation = mock(DBPTransactionIsolation.class);
    actualSqlScriptExecuteSettings.setTransactionIsolation(transactionIsolation);
    actualSqlScriptExecuteSettings.setAutoCommit(true);
    List<DBPDataSourceContainer> actualDataSources =
        actualSqlScriptExecuteSettings.getDataSources();
    List<String> actualScriptFiles = actualSqlScriptExecuteSettings.getScriptFiles();
    DBPTransactionIsolation actualTransactionIsolation =
        actualSqlScriptExecuteSettings.getTransactionIsolation();
    boolean actualIsAutoCommitResult = actualSqlScriptExecuteSettings.isAutoCommit();
    boolean actualIsDumpQueryResultsToLogResult =
        actualSqlScriptExecuteSettings.isDumpQueryResultsToLog();
    boolean actualIsIgnoreErrorsResult = actualSqlScriptExecuteSettings.isIgnoreErrors();

    // Assert
    assertTrue(actualDataSources.isEmpty());
    assertTrue(actualScriptFiles.isEmpty());
    assertTrue(actualIsAutoCommitResult);
    assertTrue(actualIsDumpQueryResultsToLogResult);
    assertTrue(actualIsIgnoreErrorsResult);
    assertSame(dataSources, actualDataSources);
    assertSame(scriptFiles, actualScriptFiles);
    assertSame(transactionIsolation, actualTransactionIsolation);
  }
}
