package org.jkiss.dbeaver.tools.transfer.stream;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvaluationContext;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.data.DBDDataFilter;
import org.jkiss.dbeaver.model.data.DBDDataReceiver;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionSource;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.fs.DBFFileSystemManager;
import org.jkiss.dbeaver.model.impl.AbstractExecutionSource;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntityType;
import org.jkiss.dbeaver.model.struct.DBSInstance;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.tools.sql.task.SQLScriptDataReceiver;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSource;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSourceContainer;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamExecutionContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class StreamEntityMappingDiffblueTest {
  /**
   * Test {@link StreamEntityMapping#StreamEntityMapping(Path, String, boolean)}.
   *
   * <p>Method under test: {@link StreamEntityMapping#StreamEntityMapping(Path, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamEntityMapping.<init>(Path, String, boolean)"})
  public void testNewStreamEntityMapping() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    StreamEntityMapping actualStreamEntityMapping =
        new StreamEntityMapping(inputFile, "Entity Name", true);

    // Assert
    DBPDataSource dataSource = actualStreamEntityMapping.getDataSource();
    assertTrue(dataSource instanceof StreamDataSource);
    assertEquals("Entity Name", actualStreamEntityMapping.getEntityName());
    assertEquals("Entity Name", actualStreamEntityMapping.getName());
    assertNull(actualStreamEntityMapping.getDescription());
    assertEquals(1, actualStreamEntityMapping.getSupportedFeatures().length);
    assertTrue(actualStreamEntityMapping.getStreamColumns().isEmpty());
    assertTrue(actualStreamEntityMapping.isChild());
    assertTrue(actualStreamEntityMapping.isPersisted());
    assertSame(inputFile, actualStreamEntityMapping.getInputFile());
    assertSame(dataSource, actualStreamEntityMapping.getParentObject());
  }

  /**
   * Test {@link StreamEntityMapping#StreamEntityMapping(DBRProgressMonitor, DBPProject, Map)}.
   *
   * <ul>
   *   <li>Given {@code Config}.
   *   <li>Then return InputFile toFile Name is {@code Config}.
   * </ul>
   *
   * <p>Method under test: {@link StreamEntityMapping#StreamEntityMapping(DBRProgressMonitor,
   * DBPProject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamEntityMapping.<init>(DBRProgressMonitor, DBPProject, Map)"})
  public void testNewStreamEntityMapping_givenConfig_thenReturnInputFileToFileNameIsConfig()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<String, Object> config = new HashMap<>();
    config.put("inputFile", "Config");
    config.put("entityId", null);

    // Act
    StreamEntityMapping actualStreamEntityMapping = new StreamEntityMapping(monitor, null, config);

    // Assert
    DBPDataSource dataSource = actualStreamEntityMapping.getDataSource();
    Collection<? extends DBSInstance> availableInstances = dataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(dataSource instanceof StreamDataSource);
    DBPDataSourceContainer container = dataSource.getContainer();
    assertTrue(container instanceof StreamDataSourceContainer);
    assertEquals("Config", actualStreamEntityMapping.getInputFile().toFile().getName());
    assertSame(dataSource, ((List<? extends DBSInstance>) availableInstances).get(0));
    StreamExecutionContext defaultContext = ((StreamDataSource) dataSource).getDefaultContext();
    assertSame(dataSource, defaultContext.getDataSource());
    assertSame(dataSource, actualStreamEntityMapping.getParentObject());
    assertSame(dataSource, defaultContext.getOwnerInstance());
    assertSame(container, dataSource.getParentObject());
    assertSame(container, container.getVirtualModel().getDataSourceContainer());
  }

  /**
   * Test {@link StreamEntityMapping#StreamEntityMapping(DBRProgressMonitor, DBPProject, Map)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@link HashMap#HashMap()} {@code inputFile} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link StreamEntityMapping#StreamEntityMapping(DBRProgressMonitor,
   * DBPProject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamEntityMapping.<init>(DBRProgressMonitor, DBPProject, Map)"})
  public void testNewStreamEntityMapping_givenEmptyString_whenHashMapInputFileIsEmptyString()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    HashMap<String, Object> config = new HashMap<>();
    config.put("inputFile", "");
    config.put("entityId", null);

    // Act
    StreamEntityMapping actualStreamEntityMapping = new StreamEntityMapping(monitor, null, config);

    // Assert
    DBPDataSource dataSource = actualStreamEntityMapping.getDataSource();
    Collection<? extends DBSInstance> availableInstances = dataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(dataSource instanceof StreamDataSource);
    DBPDataSourceContainer container = dataSource.getContainer();
    assertTrue(container instanceof StreamDataSourceContainer);
    assertEquals("", actualStreamEntityMapping.getInputFile().toFile().getName());
    assertSame(dataSource, ((List<? extends DBSInstance>) availableInstances).get(0));
    StreamExecutionContext defaultContext = ((StreamDataSource) dataSource).getDefaultContext();
    assertSame(dataSource, defaultContext.getDataSource());
    assertSame(dataSource, actualStreamEntityMapping.getParentObject());
    assertSame(dataSource, defaultContext.getOwnerInstance());
    assertSame(container, dataSource.getParentObject());
    assertSame(container, container.getVirtualModel().getDataSourceContainer());
  }

  /**
   * Test {@link StreamEntityMapping#StreamEntityMapping(DBRProgressMonitor, DBPProject, Map)}.
   *
   * <ul>
   *   <li>Given forty-two.
   *   <li>Then return InputFile toFile Name is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link StreamEntityMapping#StreamEntityMapping(DBRProgressMonitor,
   * DBPProject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamEntityMapping.<init>(DBRProgressMonitor, DBPProject, Map)"})
  public void testNewStreamEntityMapping_givenFortyTwo_thenReturnInputFileToFileNameIs42()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPProject project = mock(DBPProject.class);
    when(project.getFileSystemManager())
        .thenReturn(new DBFFileSystemManager(mock(DBPProject.class)));

    HashMap<String, Object> config = new HashMap<>();
    config.put("inputFile", 42);
    config.put("entityId", null);

    // Act
    StreamEntityMapping actualStreamEntityMapping =
        new StreamEntityMapping(monitor, project, config);

    // Assert
    verify(project).getFileSystemManager();
    DBPDataSource dataSource = actualStreamEntityMapping.getDataSource();
    Collection<? extends DBSInstance> availableInstances = dataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(dataSource instanceof StreamDataSource);
    DBPDataSourceContainer container = dataSource.getContainer();
    assertTrue(container instanceof StreamDataSourceContainer);
    assertEquals("42", actualStreamEntityMapping.getInputFile().toFile().getName());
    assertSame(dataSource, ((List<? extends DBSInstance>) availableInstances).get(0));
    StreamExecutionContext defaultContext = ((StreamDataSource) dataSource).getDefaultContext();
    assertSame(dataSource, defaultContext.getDataSource());
    assertSame(dataSource, actualStreamEntityMapping.getParentObject());
    assertSame(dataSource, defaultContext.getOwnerInstance());
    assertSame(container, dataSource.getParentObject());
    assertSame(container, container.getVirtualModel().getDataSourceContainer());
  }

  /**
   * Test {@link StreamEntityMapping#StreamEntityMapping(Path)}.
   *
   * <ul>
   *   <li>Then DataSource return {@link StreamDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link StreamEntityMapping#StreamEntityMapping(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamEntityMapping.<init>(Path)"})
  public void testNewStreamEntityMapping_thenDataSourceReturnStreamDataSource() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    StreamEntityMapping actualStreamEntityMapping = new StreamEntityMapping(inputFile);

    // Assert
    DBPDataSource dataSource = actualStreamEntityMapping.getDataSource();
    assertTrue(dataSource instanceof StreamDataSource);
    assertEquals("test.txt", actualStreamEntityMapping.getEntityName());
    assertEquals("test.txt", actualStreamEntityMapping.getName());
    assertNull(actualStreamEntityMapping.getDescription());
    assertEquals(1, actualStreamEntityMapping.getSupportedFeatures().length);
    assertFalse(actualStreamEntityMapping.isChild());
    assertTrue(actualStreamEntityMapping.getStreamColumns().isEmpty());
    assertTrue(actualStreamEntityMapping.isPersisted());
    assertSame(inputFile, actualStreamEntityMapping.getInputFile());
    assertSame(dataSource, actualStreamEntityMapping.getParentObject());
  }

  /**
   * Test {@link StreamEntityMapping#StreamEntityMapping(DBRProgressMonitor, DBPProject, Map)}.
   *
   * <ul>
   *   <li>Then return InputFile toFile Name is empty string.
   * </ul>
   *
   * <p>Method under test: {@link StreamEntityMapping#StreamEntityMapping(DBRProgressMonitor,
   * DBPProject, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamEntityMapping.<init>(DBRProgressMonitor, DBPProject, Map)"})
  public void testNewStreamEntityMapping_thenReturnInputFileToFileNameIsEmptyString()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPProject project = mock(DBPProject.class);
    when(project.getFileSystemManager())
        .thenReturn(new DBFFileSystemManager(mock(DBPProject.class)));

    // Act
    StreamEntityMapping actualStreamEntityMapping =
        new StreamEntityMapping(monitor, project, new HashMap<>());

    // Assert
    verify(project).getFileSystemManager();
    DBPDataSource dataSource = actualStreamEntityMapping.getDataSource();
    Collection<? extends DBSInstance> availableInstances = dataSource.getAvailableInstances();
    assertEquals(1, availableInstances.size());
    assertTrue(availableInstances instanceof List);
    assertTrue(dataSource instanceof StreamDataSource);
    DBPDataSourceContainer container = dataSource.getContainer();
    assertTrue(container instanceof StreamDataSourceContainer);
    assertEquals("", actualStreamEntityMapping.getInputFile().toFile().getName());
    assertSame(dataSource, ((List<? extends DBSInstance>) availableInstances).get(0));
    StreamExecutionContext defaultContext = ((StreamDataSource) dataSource).getDefaultContext();
    assertSame(dataSource, defaultContext.getDataSource());
    assertSame(dataSource, actualStreamEntityMapping.getParentObject());
    assertSame(dataSource, defaultContext.getOwnerInstance());
    assertSame(container, dataSource.getParentObject());
    assertSame(container, container.getVirtualModel().getDataSourceContainer());
  }

  /**
   * Test {@link StreamEntityMapping#getAttributes(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StreamEntityMapping#getAttributes(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List StreamEntityMapping.getAttributes(DBRProgressMonitor)"})
  public void testGetAttributes() throws DBException {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);

    // Act and Assert
    assertTrue(streamEntityMapping.getAttributes(new LoggingProgressMonitor()).isEmpty());
  }

  /**
   * Test {@link StreamEntityMapping#getAttribute(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link StreamEntityMapping#getAttribute(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.struct.DBSEntityAttribute StreamEntityMapping.getAttribute(DBRProgressMonitor, String)"
  })
  public void testGetAttribute() throws DBException {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);

    // Act and Assert
    assertNull(streamEntityMapping.getAttribute(new LoggingProgressMonitor(), "Attribute Name"));
  }

  /**
   * Test {@link StreamEntityMapping#getConstraints(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StreamEntityMapping#getConstraints(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection StreamEntityMapping.getConstraints(DBRProgressMonitor)"})
  public void testGetConstraints() throws DBException {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);

    // Act and Assert
    assertNull(streamEntityMapping.getConstraints(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link StreamEntityMapping#getAssociations(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StreamEntityMapping#getAssociations(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection StreamEntityMapping.getAssociations(DBRProgressMonitor)"})
  public void testGetAssociations() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);

    // Act and Assert
    assertNull(streamEntityMapping.getAssociations(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link StreamEntityMapping#getReferences(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link StreamEntityMapping#getReferences(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection StreamEntityMapping.getReferences(DBRProgressMonitor)"})
  public void testGetReferences() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);

    // Act and Assert
    assertNull(streamEntityMapping.getReferences(new LoggingProgressMonitor()));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamEntityMapping#getDataSource()}
   *   <li>{@link StreamEntityMapping#getDescription()}
   *   <li>{@link StreamEntityMapping#getEntityName()}
   *   <li>{@link StreamEntityMapping#getEntityType()}
   *   <li>{@link StreamEntityMapping#getInputFile()}
   *   <li>{@link StreamEntityMapping#getName()}
   *   <li>{@link StreamEntityMapping#getParentObject()}
   *   <li>{@link StreamEntityMapping#getStreamColumns()}
   *   <li>{@link StreamEntityMapping#isChild()}
   *   <li>{@link StreamEntityMapping#isPersisted()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPDataSource StreamEntityMapping.getDataSource()",
    "String StreamEntityMapping.getDescription()",
    "String StreamEntityMapping.getEntityName()",
    "DBSEntityType StreamEntityMapping.getEntityType()",
    "Path StreamEntityMapping.getInputFile()",
    "String StreamEntityMapping.getName()",
    "DBSObject StreamEntityMapping.getParentObject()",
    "List StreamEntityMapping.getStreamColumns()",
    "boolean StreamEntityMapping.isChild()",
    "boolean StreamEntityMapping.isPersisted()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);

    // Act
    DBPDataSource actualDataSource = streamEntityMapping.getDataSource();
    String actualDescription = streamEntityMapping.getDescription();
    String actualEntityName = streamEntityMapping.getEntityName();
    DBSEntityType actualEntityType = streamEntityMapping.getEntityType();
    Path actualInputFile = streamEntityMapping.getInputFile();
    String actualName = streamEntityMapping.getName();
    DBSObject actualParentObject = streamEntityMapping.getParentObject();
    List<StreamDataImporterColumnInfo> actualStreamColumns = streamEntityMapping.getStreamColumns();
    boolean actualIsChildResult = streamEntityMapping.isChild();
    boolean actualIsPersistedResult = streamEntityMapping.isPersisted();

    // Assert
    assertTrue(actualDataSource instanceof StreamDataSource);
    assertEquals("test.txt", actualEntityName);
    assertEquals("test.txt", actualName);
    assertNull(actualDescription);
    assertFalse(actualIsChildResult);
    assertTrue(actualStreamColumns.isEmpty());
    assertTrue(actualIsPersistedResult);
    assertSame(actualDataSource, actualParentObject);
    assertSame(inputFile, actualInputFile);
    assertSame(DBSEntityType.TABLE, actualEntityType);
  }

  /**
   * Test {@link StreamEntityMapping#getSupportedFeatures()}.
   *
   * <p>Method under test: {@link StreamEntityMapping#getSupportedFeatures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] StreamEntityMapping.getSupportedFeatures()"})
  public void testGetSupportedFeatures() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertArrayEquals(
        new String[] {"data.select"}, new StreamEntityMapping(inputFile).getSupportedFeatures());
  }

  /**
   * Test {@link StreamEntityMapping#readData(DBCExecutionSource, DBCSession, DBDDataReceiver,
   * DBDDataFilter, long, long, long, int)}.
   *
   * <p>Method under test: {@link StreamEntityMapping#readData(DBCExecutionSource, DBCSession,
   * DBDDataReceiver, DBDDataFilter, long, long, long, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.exec.DBCStatistics StreamEntityMapping.readData(DBCExecutionSource, DBCSession, DBDDataReceiver, DBDDataFilter, long, long, long, int)"
  })
  public void testReadData() throws DBCException {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    AbstractExecutionSource source =
        new AbstractExecutionSource(new StreamEntityMapping(inputFile2), null, "Controller");
    SQLScriptDataReceiver dataReceiver = new SQLScriptDataReceiver();

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            streamEntityMapping.readData(
                source, null, dataReceiver, new DBDDataFilter(), 1L, 1L, 1L, 3));
  }

  /**
   * Test {@link StreamEntityMapping#countData(DBCExecutionSource, DBCSession, DBDDataFilter,
   * long)}.
   *
   * <p>Method under test: {@link StreamEntityMapping#countData(DBCExecutionSource, DBCSession,
   * DBDDataFilter, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "long StreamEntityMapping.countData(DBCExecutionSource, DBCSession, DBDDataFilter, long)"
  })
  public void testCountData() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    AbstractExecutionSource source =
        new AbstractExecutionSource(new StreamEntityMapping(inputFile2), null, "Controller");

    // Act and Assert
    assertEquals(-1L, streamEntityMapping.countData(source, null, new DBDDataFilter(), 3L));
  }

  /**
   * Test {@link StreamEntityMapping#getFullyQualifiedName(DBPEvaluationContext)}.
   *
   * <p>Method under test: {@link StreamEntityMapping#getFullyQualifiedName(DBPEvaluationContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String StreamEntityMapping.getFullyQualifiedName(DBPEvaluationContext)"})
  public void testGetFullyQualifiedName() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertEquals(
        "test.txt",
        new StreamEntityMapping(inputFile).getFullyQualifiedName(DBPEvaluationContext.UI));
  }

  /**
   * Test {@link StreamEntityMapping#setStreamColumns(List)}.
   *
   * <p>Method under test: {@link StreamEntityMapping#setStreamColumns(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamEntityMapping.setStreamColumns(List)"})
  public void testSetStreamColumns() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);

    // Act
    streamEntityMapping.setStreamColumns(new ArrayList<>());

    // Assert that nothing has changed
    assertTrue(streamEntityMapping.getStreamColumns().isEmpty());
  }

  /**
   * Test {@link StreamEntityMapping#setStreamColumns(List)}.
   *
   * <ul>
   *   <li>Then {@link ArrayList#ArrayList()} size is two.
   * </ul>
   *
   * <p>Method under test: {@link StreamEntityMapping#setStreamColumns(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StreamEntityMapping.setStreamColumns(List)"})
  public void testSetStreamColumns_thenArrayListSizeIsTwo() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);

    ArrayList<StreamDataImporterColumnInfo> streamColumns = new ArrayList<>();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo streamDataImporterColumnInfo =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile2),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);
    streamColumns.add(streamDataImporterColumnInfo);
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamDataImporterColumnInfo streamDataImporterColumnInfo2 =
        new StreamDataImporterColumnInfo(
            new StreamEntityMapping(inputFile3),
            1,
            "Column Name",
            "Type Name",
            3,
            DBPDataKind.BOOLEAN);
    streamColumns.add(streamDataImporterColumnInfo2);

    // Act
    streamEntityMapping.setStreamColumns(streamColumns);

    // Assert
    assertEquals(2, streamColumns.size());
    assertEquals("Column Name_1", streamColumns.get(1).getName());
    assertEquals(streamColumns, streamEntityMapping.getStreamColumns());
  }

  /**
   * Test {@link StreamEntityMapping#getStreamColumn(String)}.
   *
   * <p>Method under test: {@link StreamEntityMapping#getStreamColumn(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"StreamDataImporterColumnInfo StreamEntityMapping.getStreamColumn(String)"})
  public void testGetStreamColumn() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertNull(new StreamEntityMapping(inputFile).getStreamColumn("Name"));
  }

  /**
   * Test {@link StreamEntityMapping#saveSettings()}.
   *
   * <p>Method under test: {@link StreamEntityMapping#saveSettings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map StreamEntityMapping.saveSettings()"})
  public void testSaveSettings() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    Map<String, Object> actualSaveSettingsResult =
        new StreamEntityMapping(inputFile).saveSettings();

    // Assert
    assertEquals(1, actualSaveSettingsResult.size());
    assertEquals("test.txt", actualSaveSettingsResult.get("entityId"));
  }

  /**
   * Test {@link StreamEntityMapping#isSameColumns(StreamEntityMapping)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link StreamEntityMapping#isSameColumns(StreamEntityMapping)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StreamEntityMapping.isSameColumns(StreamEntityMapping)"})
  public void testIsSameColumns_thenReturnTrue() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    boolean actualIsSameColumnsResult =
        streamEntityMapping.isSameColumns(new StreamEntityMapping(inputFile2));

    // Assert
    assertTrue(actualIsSameColumnsResult);
  }

  /**
   * Test {@link StreamEntityMapping#equals(Object)}, and {@link StreamEntityMapping#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamEntityMapping#equals(Object)}
   *   <li>{@link StreamEntityMapping#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean StreamEntityMapping.equals(Object)",
    "int StreamEntityMapping.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping2 = new StreamEntityMapping(inputFile2);

    // Act and Assert
    assertEquals(streamEntityMapping, streamEntityMapping2);
    assertEquals(streamEntityMapping.hashCode(), streamEntityMapping2.hashCode());
  }

  /**
   * Test {@link StreamEntityMapping#equals(Object)}, and {@link StreamEntityMapping#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link StreamEntityMapping#equals(Object)}
   *   <li>{@link StreamEntityMapping#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean StreamEntityMapping.equals(Object)",
    "int StreamEntityMapping.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping streamEntityMapping = new StreamEntityMapping(inputFile);

    // Act and Assert
    assertEquals(streamEntityMapping, streamEntityMapping);
    int expectedHashCodeResult = streamEntityMapping.hashCode();
    assertEquals(expectedHashCodeResult, streamEntityMapping.hashCode());
  }

  /**
   * Test {@link StreamEntityMapping#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link StreamEntityMapping#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean StreamEntityMapping.equals(Object)",
    "int StreamEntityMapping.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() throws DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBPProject project = mock(DBPProject.class);
    when(project.getFileSystemManager())
        .thenReturn(new DBFFileSystemManager(mock(DBPProject.class)));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    StreamEntityMapping streamEntityMapping =
        new StreamEntityMapping(monitor, project, new HashMap<>());
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertNotEquals(streamEntityMapping, new StreamEntityMapping(inputFile));
  }

  /**
   * Test {@link StreamEntityMapping#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link StreamEntityMapping#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean StreamEntityMapping.equals(Object)",
    "int StreamEntityMapping.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() throws DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBFFileSystemManager dbfFileSystemManager = mock(DBFFileSystemManager.class);
    when(dbfFileSystemManager.getPathFromString(
            Mockito.<DBRProgressMonitor>any(), Mockito.<String>any()))
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

    DBPProject project = mock(DBPProject.class);
    when(project.getFileSystemManager()).thenReturn(dbfFileSystemManager);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    StreamEntityMapping streamEntityMapping =
        new StreamEntityMapping(monitor, project, new HashMap<>());
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertNotEquals(streamEntityMapping, new StreamEntityMapping(inputFile));
  }

  /**
   * Test {@link StreamEntityMapping#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link StreamEntityMapping#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean StreamEntityMapping.equals(Object)",
    "int StreamEntityMapping.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertNotEquals(new StreamEntityMapping(inputFile), null);
  }

  /**
   * Test {@link StreamEntityMapping#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link StreamEntityMapping#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean StreamEntityMapping.equals(Object)",
    "int StreamEntityMapping.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertNotEquals(new StreamEntityMapping(inputFile), "Different type to StreamEntityMapping");
  }
}
