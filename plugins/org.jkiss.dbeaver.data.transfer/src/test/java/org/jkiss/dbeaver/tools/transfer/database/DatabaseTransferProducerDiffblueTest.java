package org.jkiss.dbeaver.tools.transfer.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.data.DBDDataFilter;
import org.jkiss.dbeaver.model.exec.DBCStatistics;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DBRRunnableContext;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.tools.transfer.IDataTransferConsumer;
import org.jkiss.dbeaver.tools.transfer.IDataTransferProcessor;
import org.jkiss.dbeaver.tools.transfer.database.DatabaseTransferProducer.ObjectSerializer;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.jkiss.dbeaver.tools.transfer.stream.exporter.DataExporterCSV;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSourceContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DatabaseTransferProducerDiffblueTest {
  /**
   * Test {@link DatabaseTransferProducer#DatabaseTransferProducer()}.
   *
   * <p>Method under test: {@link DatabaseTransferProducer#DatabaseTransferProducer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferProducer.<init>()"})
  public void testNewDatabaseTransferProducer() {
    // Arrange and Act
    DatabaseTransferProducer actualDatabaseTransferProducer = new DatabaseTransferProducer();

    // Assert
    assertTrue(actualDatabaseTransferProducer.getObjectIcon() instanceof DBIcon);
    assertEquals("?", actualDatabaseTransferProducer.getObjectContainerName());
    assertNull(actualDatabaseTransferProducer.getDefaultCatalog());
    assertNull(actualDatabaseTransferProducer.getDefaultSchema());
    assertNull(actualDatabaseTransferProducer.getDataSourceContainer());
    assertNull(actualDatabaseTransferProducer.getObjectContainerIcon());
    assertNull(actualDatabaseTransferProducer.getProject());
    assertNull(actualDatabaseTransferProducer.getDatabaseObject());
    assertFalse(actualDatabaseTransferProducer.isConfigurationComplete());
  }

  /**
   * Test {@link DatabaseTransferProducer#DatabaseTransferProducer(DBSDataContainer)}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferProducer#DatabaseTransferProducer(DBSDataContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferProducer.<init>(DBSDataContainer)"})
  public void testNewDatabaseTransferProducer2() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);

    // Act
    DatabaseTransferProducer actualDatabaseTransferProducer =
        new DatabaseTransferProducer(dataContainer);

    // Assert
    assertTrue(actualDatabaseTransferProducer.getObjectIcon() instanceof DBIcon);
    DBSDataContainer databaseObject = actualDatabaseTransferProducer.getDatabaseObject();
    assertTrue(databaseObject instanceof StreamEntityMapping);
    assertTrue(
        actualDatabaseTransferProducer.getDataSourceContainer()
            instanceof StreamDataSourceContainer);
    assertEquals("test.txt", actualDatabaseTransferProducer.getObjectContainerName());
    assertNull(actualDatabaseTransferProducer.getDefaultCatalog());
    assertNull(actualDatabaseTransferProducer.getDefaultSchema());
    assertNull(actualDatabaseTransferProducer.getProject());
    assertTrue(actualDatabaseTransferProducer.isConfigurationComplete());
    assertSame(dataContainer, databaseObject);
  }

  /**
   * Test {@link DatabaseTransferProducer#DatabaseTransferProducer(DBSDataContainer,
   * DBDDataFilter)}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferProducer#DatabaseTransferProducer(DBSDataContainer, DBDDataFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferProducer.<init>(DBSDataContainer, DBDDataFilter)"})
  public void testNewDatabaseTransferProducer3() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile);

    // Act
    DatabaseTransferProducer actualDatabaseTransferProducer =
        new DatabaseTransferProducer(dataContainer, new DBDDataFilter());

    // Assert
    assertTrue(actualDatabaseTransferProducer.getObjectIcon() instanceof DBIcon);
    DBSDataContainer databaseObject = actualDatabaseTransferProducer.getDatabaseObject();
    assertTrue(databaseObject instanceof StreamEntityMapping);
    assertTrue(
        actualDatabaseTransferProducer.getDataSourceContainer()
            instanceof StreamDataSourceContainer);
    assertEquals("test.txt", actualDatabaseTransferProducer.getObjectContainerName());
    assertNull(actualDatabaseTransferProducer.getDefaultCatalog());
    assertNull(actualDatabaseTransferProducer.getDefaultSchema());
    assertNull(actualDatabaseTransferProducer.getProject());
    assertTrue(actualDatabaseTransferProducer.isConfigurationComplete());
    assertSame(dataContainer, databaseObject);
  }

  /**
   * Test {@link DatabaseTransferProducer#getDatabaseObject()}.
   *
   * <p>Method under test: {@link DatabaseTransferProducer#getDatabaseObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSDataContainer DatabaseTransferProducer.getDatabaseObject()"})
  public void testGetDatabaseObject() {
    // Arrange, Act and Assert
    assertNull(new DatabaseTransferProducer().getDatabaseObject());
  }

  /**
   * Test {@link DatabaseTransferProducer#getProject()}.
   *
   * <p>Method under test: {@link DatabaseTransferProducer#getProject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.app.DBPProject DatabaseTransferProducer.getProject()"
  })
  public void testGetProject() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertNull(new DatabaseTransferProducer(new StreamEntityMapping(inputFile)).getProject());
  }

  /**
   * Test {@link DatabaseTransferProducer#getProject()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseTransferProducer#DatabaseTransferProducer()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferProducer#getProject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.app.DBPProject DatabaseTransferProducer.getProject()"
  })
  public void testGetProject_givenDatabaseTransferProducer() {
    // Arrange, Act and Assert
    assertNull(new DatabaseTransferProducer().getProject());
  }

  /**
   * Test {@link DatabaseTransferProducer#getObjectIcon()}.
   *
   * <p>Method under test: {@link DatabaseTransferProducer#getObjectIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseTransferProducer.getObjectIcon()"})
  public void testGetObjectIcon() {
    // Arrange and Act
    DBPImage actualObjectIcon = new DatabaseTransferProducer().getObjectIcon();
    String actualLocation = actualObjectIcon.getLocation();

    // Assert
    assertTrue(actualObjectIcon instanceof DBIcon);
    assertEquals("table", ((DBIcon) actualObjectIcon).getToken());
    assertEquals("tree/table.svg", actualObjectIcon.getLocation());
    assertEquals("tree/table.svg", actualLocation);
  }

  /**
   * Test {@link DatabaseTransferProducer#getObjectContainerName()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseTransferProducer#DatabaseTransferProducer()}.
   *   <li>Then return {@code ?}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferProducer#getObjectContainerName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseTransferProducer.getObjectContainerName()"})
  public void testGetObjectContainerName_givenDatabaseTransferProducer_thenReturnQuestionMark() {
    // Arrange, Act and Assert
    assertEquals("?", new DatabaseTransferProducer().getObjectContainerName());
  }

  /**
   * Test {@link DatabaseTransferProducer#getObjectContainerName()}.
   *
   * <ul>
   *   <li>Then return {@code test.txt}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferProducer#getObjectContainerName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseTransferProducer.getObjectContainerName()"})
  public void testGetObjectContainerName_thenReturnTestTxt() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertEquals(
        "test.txt",
        new DatabaseTransferProducer(new StreamEntityMapping(inputFile)).getObjectContainerName());
  }

  /**
   * Test {@link DatabaseTransferProducer#getObjectContainerIcon()}.
   *
   * <p>Method under test: {@link DatabaseTransferProducer#getObjectContainerIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseTransferProducer.getObjectContainerIcon()"})
  public void testGetObjectContainerIcon() {
    // Arrange, Act and Assert
    assertNull(new DatabaseTransferProducer().getObjectContainerIcon());
  }

  /**
   * Test {@link DatabaseTransferProducer#isConfigurationComplete()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseTransferProducer#DatabaseTransferProducer()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferProducer#isConfigurationComplete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferProducer.isConfigurationComplete()"})
  public void testIsConfigurationComplete_givenDatabaseTransferProducer_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DatabaseTransferProducer().isConfigurationComplete());
  }

  /**
   * Test {@link DatabaseTransferProducer#isConfigurationComplete()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferProducer#isConfigurationComplete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferProducer.isConfigurationComplete()"})
  public void testIsConfigurationComplete_thenReturnTrue() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertTrue(
        new DatabaseTransferProducer(new StreamEntityMapping(inputFile)).isConfigurationComplete());
  }

  /**
   * Test {@link DatabaseTransferProducer#getDataSourceContainer()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseTransferProducer#DatabaseTransferProducer()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferProducer#getDataSourceContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.DBPDataSourceContainer DatabaseTransferProducer.getDataSourceContainer()"
  })
  public void testGetDataSourceContainer_givenDatabaseTransferProducer_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DatabaseTransferProducer().getDataSourceContainer());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DatabaseTransferProducer#setDataContainer(DBSDataContainer)}
   *   <li>{@link DatabaseTransferProducer#setDefaultCatalog(String)}
   *   <li>{@link DatabaseTransferProducer#setDefaultSchema(String)}
   *   <li>{@link DatabaseTransferProducer#getDefaultCatalog()}
   *   <li>{@link DatabaseTransferProducer#getDefaultSchema()}
   *   <li>{@link DatabaseTransferProducer#getStatistics()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseTransferProducer.getDefaultCatalog()",
    "String DatabaseTransferProducer.getDefaultSchema()",
    "DBCStatistics DatabaseTransferProducer.getStatistics()",
    "void DatabaseTransferProducer.setDataContainer(DBSDataContainer)",
    "void DatabaseTransferProducer.setDefaultCatalog(String)",
    "void DatabaseTransferProducer.setDefaultSchema(String)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DatabaseTransferProducer databaseTransferProducer = new DatabaseTransferProducer();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    databaseTransferProducer.setDataContainer(new StreamEntityMapping(inputFile));
    databaseTransferProducer.setDefaultCatalog("Default Catalog");
    databaseTransferProducer.setDefaultSchema("Default Schema");
    String actualDefaultCatalog = databaseTransferProducer.getDefaultCatalog();
    String actualDefaultSchema = databaseTransferProducer.getDefaultSchema();
    DBCStatistics actualStatistics = databaseTransferProducer.getStatistics();

    // Assert
    assertEquals("Default Catalog", actualDefaultCatalog);
    assertEquals("Default Schema", actualDefaultSchema);
    assertNull(actualStatistics.getQueryText());
    assertNull(actualStatistics.getError());
    assertNull(actualStatistics.getMessages());
    assertNull(actualStatistics.getWarnings());
    assertEquals(-1L, actualStatistics.getRowsFetched());
    assertEquals(-1L, actualStatistics.getRowsUpdated());
    assertEquals(0, actualStatistics.getStatementsCount());
    assertEquals(0L, actualStatistics.getExecuteTime());
    assertEquals(0L, actualStatistics.getFetchTime());
    assertEquals(0L, actualStatistics.getTotalTime());
    assertTrue(actualStatistics.getInfo().isEmpty());
    assertTrue(actualStatistics.isEmpty());
  }

  /**
   * Test ObjectSerializer {@link ObjectSerializer#serializeObject(DBRRunnableContext, DBTTask,
   * DatabaseTransferProducer, Map)} with {@code DBRRunnableContext}, {@code DBTTask}, {@code
   * DatabaseTransferProducer}, {@code Map}.
   *
   * <p>Method under test: {@link ObjectSerializer#serializeObject(DBRRunnableContext, DBTTask,
   * DatabaseTransferProducer, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ObjectSerializer.serializeObject(DBRRunnableContext, DBTTask, DatabaseTransferProducer, Map)"
  })
  public void
      testObjectSerializerSerializeObjectWithDBRRunnableContextDBTTaskDatabaseTransferProducerMap() {
    // Arrange
    ObjectSerializer objectSerializer = new ObjectSerializer();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    DBTTask context = mock(DBTTask.class);
    DatabaseTransferProducer object = new DatabaseTransferProducer();
    HashMap<String, Object> state = new HashMap<>();

    // Act
    objectSerializer.serializeObject(runnableContext, context, object, state);

    // Assert
    assertEquals(1, state.size());
    assertEquals("unknown", state.get("type"));
  }

  /**
   * Test ObjectSerializer {@link ObjectSerializer#serializeObject(DBRRunnableContext, DBTTask,
   * DatabaseTransferProducer, Map)} with {@code DBRRunnableContext}, {@code DBTTask}, {@code
   * DatabaseTransferProducer}, {@code Map}.
   *
   * <p>Method under test: {@link ObjectSerializer#serializeObject(DBRRunnableContext, DBTTask,
   * DatabaseTransferProducer, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ObjectSerializer.serializeObject(DBRRunnableContext, DBTTask, DatabaseTransferProducer, Map)"
  })
  public void
      testObjectSerializerSerializeObjectWithDBRRunnableContextDBTTaskDatabaseTransferProducerMap2() {
    // Arrange
    ObjectSerializer objectSerializer = new ObjectSerializer();
    DBRRunnableContext runnableContext = mock(DBRRunnableContext.class);
    DBTTask context = mock(DBTTask.class);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseTransferProducer object = new DatabaseTransferProducer(null, new DBDDataFilter());
    HashMap<String, Object> state = new HashMap<>();

    // Act
    objectSerializer.serializeObject(runnableContext, context, object, state);

    // Assert
    assertEquals(2, state.size());
    Object getResult = state.get("dataFilter");
    assertTrue(getResult instanceof Map);
    assertTrue(state.containsKey("type"));
    assertTrue(((Map<Object, Object>) getResult).isEmpty());
  }

  /**
   * Test {@link DatabaseTransferProducer#transferData(DBRProgressMonitor, IDataTransferConsumer,
   * IDataTransferProcessor, DatabaseProducerSettings, DBTTask)} with {@code DBRProgressMonitor},
   * {@code IDataTransferConsumer}, {@code IDataTransferProcessor}, {@code
   * DatabaseProducerSettings}, {@code DBTTask}.
   *
   * <p>Method under test: {@link DatabaseTransferProducer#transferData(DBRProgressMonitor,
   * IDataTransferConsumer, IDataTransferProcessor, DatabaseProducerSettings, DBTTask)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferProducer.transferData(DBRProgressMonitor, IDataTransferConsumer, IDataTransferProcessor, DatabaseProducerSettings, DBTTask)"
  })
  public void
      testTransferDataWithDBRProgressMonitorIDataTransferConsumerIDataTransferProcessorDatabaseProducerSettingsDBTTask()
          throws DBException {
    // Arrange
    DatabaseTransferProducer databaseTransferProducer = new DatabaseTransferProducer();
    LoggingProgressMonitor monitor1 = new LoggingProgressMonitor();
    DatabaseTransferConsumer consumer = new DatabaseTransferConsumer();
    DataExporterCSV processor = new DataExporterCSV();

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            databaseTransferProducer.transferData(
                monitor1,
                consumer,
                processor,
                new DatabaseProducerSettings(),
                mock(DBTTask.class)));
  }

  /**
   * Test {@link DatabaseTransferProducer#equals(Object)}, and {@link
   * DatabaseTransferProducer#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferProducer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferProducer.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DatabaseTransferProducer databaseTransferProducer = new DatabaseTransferProducer();
    DatabaseTransferProducer databaseTransferProducer2 = new DatabaseTransferProducer();

    // Act and Assert
    assertEquals(databaseTransferProducer, databaseTransferProducer2);
    assertNotEquals(databaseTransferProducer.hashCode(), databaseTransferProducer2.hashCode());
  }

  /**
   * Test {@link DatabaseTransferProducer#equals(Object)}, and {@link
   * DatabaseTransferProducer#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferProducer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferProducer.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DatabaseTransferProducer databaseTransferProducer = new DatabaseTransferProducer();

    // Act and Assert
    assertEquals(databaseTransferProducer, databaseTransferProducer);
    int expectedHashCodeResult = databaseTransferProducer.hashCode();
    assertEquals(expectedHashCodeResult, databaseTransferProducer.hashCode());
  }

  /**
   * Test {@link DatabaseTransferProducer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferProducer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferProducer.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseTransferProducer databaseTransferProducer =
        new DatabaseTransferProducer(new StreamEntityMapping(inputFile));

    // Act and Assert
    assertNotEquals(databaseTransferProducer, new DatabaseTransferProducer());
  }

  /**
   * Test {@link DatabaseTransferProducer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferProducer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferProducer.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseTransferProducer databaseTransferProducer =
        new DatabaseTransferProducer(new StreamEntityMapping(inputFile));
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping dataContainer = new StreamEntityMapping(inputFile2);
    DatabaseTransferProducer databaseTransferProducer2 =
        new DatabaseTransferProducer(dataContainer, new DBDDataFilter());

    // Act and Assert
    assertNotEquals(databaseTransferProducer, databaseTransferProducer2);
  }

  /**
   * Test {@link DatabaseTransferProducer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferProducer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferProducer.equals(Object)"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DatabaseTransferProducer(), null);
  }

  /**
   * Test {@link DatabaseTransferProducer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferProducer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferProducer.equals(Object)"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DatabaseTransferProducer(), "Different type to DatabaseTransferProducer");
  }
}
