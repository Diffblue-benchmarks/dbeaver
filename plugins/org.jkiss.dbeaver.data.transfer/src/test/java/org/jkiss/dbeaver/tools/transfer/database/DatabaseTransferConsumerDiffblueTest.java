package org.jkiss.dbeaver.tools.transfer.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCResultSet;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatistics;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.SubTaskProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSAttributeBase;
import org.jkiss.dbeaver.model.struct.DBSDataContainer;
import org.jkiss.dbeaver.model.struct.DBSDataManipulator;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSObjectContainer;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.tools.transfer.IDataTransferConsumer;
import org.jkiss.dbeaver.tools.transfer.IDataTransferConsumer.TransferParameters;
import org.jkiss.dbeaver.tools.transfer.IDataTransferProcessor;
import org.jkiss.dbeaver.tools.transfer.database.DatabaseTransferConsumer.ColumnMapping;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.jkiss.dbeaver.tools.transfer.stream.StreamTransferResultSet;
import org.jkiss.dbeaver.tools.transfer.stream.exporter.DataExporterCSV;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSource;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSourceContainer;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamTransferSession;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseTransferConsumerDiffblueTest {
  @Mock private DBCSession dBCSession;

  @InjectMocks private DatabaseTransferConsumer databaseTransferConsumer;

  /**
   * Test {@link DatabaseTransferConsumer#DatabaseTransferConsumer()}.
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#DatabaseTransferConsumer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferConsumer.<init>()"})
  public void testNewDatabaseTransferConsumer() {
    // Arrange and Act
    DatabaseTransferConsumer actualDatabaseTransferConsumer = new DatabaseTransferConsumer();

    // Assert
    assertTrue(actualDatabaseTransferConsumer.getObjectIcon() instanceof DBIcon);
    assertEquals("?", actualDatabaseTransferConsumer.getObjectName());
    assertNull(actualDatabaseTransferConsumer.getPreviewRows());
    assertNull(actualDatabaseTransferConsumer.getTargetAttributes());
    assertNull(actualDatabaseTransferConsumer.getSourceObject());
    assertNull(actualDatabaseTransferConsumer.getTargetObject());
    assertNull(actualDatabaseTransferConsumer.getDatabaseObject());
    assertNull(actualDatabaseTransferConsumer.getContainer());
    assertNull(actualDatabaseTransferConsumer.getTargetObjectContainer());
    assertNull(actualDatabaseTransferConsumer.getSettings());
    assertNull(actualDatabaseTransferConsumer.getColumnMappings());
    assertFalse(actualDatabaseTransferConsumer.isConfigurationComplete());
    assertFalse(actualDatabaseTransferConsumer.isPreview());
  }

  /**
   * Test {@link DatabaseTransferConsumer#DatabaseTransferConsumer(DBSDataManipulator)}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#DatabaseTransferConsumer(DBSDataManipulator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferConsumer.<init>(DBSDataManipulator)"})
  public void testNewDatabaseTransferConsumer2() {
    // Arrange
    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);

    // Act
    DatabaseTransferConsumer actualDatabaseTransferConsumer =
        new DatabaseTransferConsumer(targetObject);

    // Assert
    assertTrue(actualDatabaseTransferConsumer.getObjectIcon() instanceof DBIcon);
    assertEquals("?", actualDatabaseTransferConsumer.getObjectName());
    assertNull(actualDatabaseTransferConsumer.getPreviewRows());
    assertNull(actualDatabaseTransferConsumer.getTargetAttributes());
    assertNull(actualDatabaseTransferConsumer.getSourceObject());
    assertNull(actualDatabaseTransferConsumer.getContainer());
    assertNull(actualDatabaseTransferConsumer.getTargetObjectContainer());
    assertNull(actualDatabaseTransferConsumer.getSettings());
    assertNull(actualDatabaseTransferConsumer.getColumnMappings());
    assertFalse(actualDatabaseTransferConsumer.isPreview());
    assertTrue(actualDatabaseTransferConsumer.isConfigurationComplete());
    assertSame(targetObject, actualDatabaseTransferConsumer.getDatabaseObject());
    assertSame(targetObject, actualDatabaseTransferConsumer.getTargetObject());
  }

  /**
   * Test {@link DatabaseTransferConsumer#DatabaseTransferConsumer(DBSObjectContainer)}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#DatabaseTransferConsumer(DBSObjectContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferConsumer.<init>(DBSObjectContainer)"})
  public void testNewDatabaseTransferConsumer3() {
    // Arrange
    StreamDataSource targetObjectContainer = new StreamDataSource("Input Name");

    // Act
    DatabaseTransferConsumer actualDatabaseTransferConsumer =
        new DatabaseTransferConsumer(targetObjectContainer);

    // Assert
    DBPImage objectContainerIcon = actualDatabaseTransferConsumer.getObjectContainerIcon();
    assertTrue(objectContainerIcon instanceof DBIcon);
    DBSObject databaseObject = actualDatabaseTransferConsumer.getDatabaseObject();
    assertTrue(databaseObject instanceof StreamDataSource);
    assertTrue(
        actualDatabaseTransferConsumer.getDataSourceContainer()
            instanceof StreamDataSourceContainer);
    assertEquals("Input Name", actualDatabaseTransferConsumer.getObjectName());
    assertEquals("\"Input Name\"", actualDatabaseTransferConsumer.getObjectContainerName());
    assertNull(actualDatabaseTransferConsumer.getPreviewRows());
    assertNull(actualDatabaseTransferConsumer.getTargetAttributes());
    assertNull(actualDatabaseTransferConsumer.getProject());
    assertNull(actualDatabaseTransferConsumer.getSourceObject());
    assertNull(actualDatabaseTransferConsumer.getTargetObject());
    assertNull(actualDatabaseTransferConsumer.getContainer());
    assertNull(actualDatabaseTransferConsumer.getSettings());
    assertNull(actualDatabaseTransferConsumer.getColumnMappings());
    assertFalse(actualDatabaseTransferConsumer.isConfigurationComplete());
    assertFalse(actualDatabaseTransferConsumer.isPreview());
    assertSame(targetObjectContainer, databaseObject);
    assertSame(targetObjectContainer, actualDatabaseTransferConsumer.getTargetObjectContainer());
    assertSame(objectContainerIcon, actualDatabaseTransferConsumer.getObjectIcon());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getTargetObjectContainer()}.
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getTargetObjectContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObjectContainer DatabaseTransferConsumer.getTargetObjectContainer()"})
  public void testGetTargetObjectContainer() {
    // Arrange, Act and Assert
    assertNull(new DatabaseTransferConsumer().getTargetObjectContainer());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DatabaseTransferConsumer#setContainer(DBSObjectContainer)}
   *   <li>{@link DatabaseTransferConsumer#setContainerMapping(DatabaseMappingContainer)}
   *   <li>{@link DatabaseTransferConsumer#setPreview(boolean)}
   *   <li>{@link DatabaseTransferConsumer#setSettings(DatabaseConsumerSettings)}
   *   <li>{@link DatabaseTransferConsumer#setTargetObject(DBSDataManipulator)}
   *   <li>{@link DatabaseTransferConsumer#getColumnMappings()}
   *   <li>{@link DatabaseTransferConsumer#getContainer()}
   *   <li>{@link DatabaseTransferConsumer#getPreviewRows()}
   *   <li>{@link DatabaseTransferConsumer#getSettings()}
   *   <li>{@link DatabaseTransferConsumer#getStatistics()}
   *   <li>{@link DatabaseTransferConsumer#getTargetAttributes()}
   *   <li>{@link DatabaseTransferConsumer#isPreview()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ColumnMapping[] DatabaseTransferConsumer.getColumnMappings()",
    "DBSObjectContainer DatabaseTransferConsumer.getContainer()",
    "List DatabaseTransferConsumer.getPreviewRows()",
    "DatabaseConsumerSettings DatabaseTransferConsumer.getSettings()",
    "DBCStatistics DatabaseTransferConsumer.getStatistics()",
    "List DatabaseTransferConsumer.getTargetAttributes()",
    "boolean DatabaseTransferConsumer.isPreview()",
    "void DatabaseTransferConsumer.setContainer(DBSObjectContainer)",
    "void DatabaseTransferConsumer.setContainerMapping(DatabaseMappingContainer)",
    "void DatabaseTransferConsumer.setPreview(boolean)",
    "void DatabaseTransferConsumer.setSettings(DatabaseConsumerSettings)",
    "void DatabaseTransferConsumer.setTargetObject(DBSDataManipulator)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    StreamDataSource container = new StreamDataSource("Input Name");

    // Act
    databaseTransferConsumer.setContainer(container);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    databaseTransferConsumer.setContainerMapping(containerMapping);
    databaseTransferConsumer.setPreview(true);
    DatabaseConsumerSettings settings = new DatabaseConsumerSettings();
    databaseTransferConsumer.setSettings(settings);
    databaseTransferConsumer.setTargetObject(mock(DBSDataManipulator.class));
    ColumnMapping[] actualColumnMappings = databaseTransferConsumer.getColumnMappings();
    DBSObjectContainer actualContainer = databaseTransferConsumer.getContainer();
    List<Object[]> actualPreviewRows = databaseTransferConsumer.getPreviewRows();
    DatabaseConsumerSettings actualSettings = databaseTransferConsumer.getSettings();
    DBCStatistics actualStatistics = databaseTransferConsumer.getStatistics();
    List<DBSAttributeBase> actualTargetAttributes = databaseTransferConsumer.getTargetAttributes();
    boolean actualIsPreviewResult = databaseTransferConsumer.isPreview();

    // Assert
    assertNull(actualStatistics.getQueryText());
    assertNull(actualStatistics.getError());
    assertNull(actualPreviewRows);
    assertNull(actualStatistics.getMessages());
    assertNull(actualStatistics.getWarnings());
    assertNull(actualTargetAttributes);
    assertNull(actualColumnMappings);
    assertEquals(-1L, actualStatistics.getRowsFetched());
    assertEquals(-1L, actualStatistics.getRowsUpdated());
    assertEquals(0, actualStatistics.getStatementsCount());
    assertEquals(0L, actualStatistics.getExecuteTime());
    assertEquals(0L, actualStatistics.getFetchTime());
    assertEquals(0L, actualStatistics.getTotalTime());
    assertTrue(actualStatistics.isEmpty());
    assertTrue(actualIsPreviewResult);
    assertSame(settings, actualSettings);
    assertSame(container, actualContainer);
  }

  /**
   * Test {@link DatabaseTransferConsumer#getDatabaseObject()}.
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getDatabaseObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DatabaseTransferConsumer.getDatabaseObject()"})
  public void testGetDatabaseObject() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    databaseTransferConsumer.setContainerMapping(containerMapping);

    // Act and Assert
    assertNull(databaseTransferConsumer.getDatabaseObject());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getDatabaseObject()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseTransferConsumer#DatabaseTransferConsumer()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getDatabaseObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DatabaseTransferConsumer.getDatabaseObject()"})
  public void testGetDatabaseObject_givenDatabaseTransferConsumer_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DatabaseTransferConsumer().getDatabaseObject());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getDatabaseObject()}.
   *
   * <ul>
   *   <li>Then return {@link StreamDataSource#StreamDataSource(String)} with {@code Input Name}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getDatabaseObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DatabaseTransferConsumer.getDatabaseObject()"})
  public void testGetDatabaseObject_thenReturnStreamDataSourceWithInputName() {
    // Arrange
    StreamDataSource targetObjectContainer = new StreamDataSource("Input Name");

    // Act and Assert
    assertSame(
        targetObjectContainer,
        new DatabaseTransferConsumer(targetObjectContainer).getDatabaseObject());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getProject()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseTransferConsumer#DatabaseTransferConsumer()} Settings is {@link
   *       DatabaseConsumerSettings} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getProject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPProject DatabaseTransferConsumer.getProject()"})
  public void testGetProject_givenDatabaseTransferConsumerSettingsIsDatabaseConsumerSettings() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(new DatabaseConsumerSettings());

    // Act and Assert
    assertNull(databaseTransferConsumer.getProject());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getProject()}.
   *
   * <ul>
   *   <li>Given {@link StreamDataSource#StreamDataSource(String)} with {@code Input Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getProject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPProject DatabaseTransferConsumer.getProject()"})
  public void testGetProject_givenStreamDataSourceWithInputName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DatabaseTransferConsumer(new StreamDataSource("Input Name")).getProject());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getProject()}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDataManipulator#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getProject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPProject DatabaseTransferConsumer.getProject()"})
  public void testGetProject_thenCallsGetDataSource() {
    // Arrange
    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);
    when(targetObject.getDataSource()).thenReturn(new StreamDataSource("Input Name"));

    // Act
    DBPProject actualProject = new DatabaseTransferConsumer(targetObject).getProject();

    // Assert
    verify(targetObject, atLeast(1)).getDataSource();
    assertNull(actualProject);
  }

  /**
   * Test {@link DatabaseTransferConsumer#fetchStart(DBCSession, DBCResultSet, long, long)}.
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#fetchStart(DBCSession, DBCResultSet,
   * long, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferConsumer.fetchStart(DBCSession, DBCResultSet, long, long)"
  })
  public void testFetchStart() throws DBCException {
    // Arrange
    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);
    when(targetObject.getDataSource()).thenReturn(new StreamDataSource("Input Name"));
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer(targetObject);

    StreamTransferSession session = mock(StreamTransferSession.class);
    when(session.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertThrows(
        DBCException.class, () -> databaseTransferConsumer.fetchStart(session, resultSet, 1L, 1L));
    verify(session).getProgressMonitor();
    verify(targetObject).getDataSource();
  }

  /**
   * Test {@link DatabaseTransferConsumer#fetchStart(DBCSession, DBCResultSet, long, long)}.
   *
   * <ul>
   *   <li>Given {@link DBSDataManipulator} {@link DBSDataManipulator#getDataSource()} throw {@link
   *       RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#fetchStart(DBCSession, DBCResultSet,
   * long, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferConsumer.fetchStart(DBCSession, DBCResultSet, long, long)"
  })
  public void testFetchStart_givenDBSDataManipulatorGetDataSourceThrowRuntimeException()
      throws DBCException {
    // Arrange
    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);
    when(targetObject.getDataSource()).thenThrow(new RuntimeException());
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer(targetObject);

    StreamTransferSession session = mock(StreamTransferSession.class);
    when(session.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> databaseTransferConsumer.fetchStart(session, resultSet, 1L, 1L));
    verify(session).getProgressMonitor();
    verify(targetObject).getDataSource();
  }

  /**
   * Test {@link DatabaseTransferConsumer#fetchStart(DBCSession, DBCResultSet, long, long)}.
   *
   * <ul>
   *   <li>Given {@link DatabaseTransferConsumer#DatabaseTransferConsumer()} Settings is {@link
   *       DatabaseConsumerSettings} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#fetchStart(DBCSession, DBCResultSet,
   * long, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferConsumer.fetchStart(DBCSession, DBCResultSet, long, long)"
  })
  public void testFetchStart_givenDatabaseTransferConsumerSettingsIsDatabaseConsumerSettings()
      throws DBCException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(new DatabaseConsumerSettings());

    StreamTransferSession session = mock(StreamTransferSession.class);
    when(session.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertThrows(
        DBCException.class, () -> databaseTransferConsumer.fetchStart(session, resultSet, 1L, 1L));
    verify(session).getProgressMonitor();
  }

  /**
   * Test {@link DatabaseTransferConsumer#fetchStart(DBCSession, DBCResultSet, long, long)}.
   *
   * <ul>
   *   <li>Then calls {@link DBCSession#getProgressMonitor()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#fetchStart(DBCSession, DBCResultSet,
   * long, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferConsumer.fetchStart(DBCSession, DBCResultSet, long, long)"
  })
  public void testFetchStart_thenCallsGetProgressMonitor() throws DBCException {
    // Arrange
    when(dBCSession.getProgressMonitor()).thenReturn(new LoggingProgressMonitor());

    DatabaseConsumerSettings settings = new DatabaseConsumerSettings();
    settings.setTruncateBeforeLoad(false);
    settings.setUseBulkLoad(false);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    databaseTransferConsumer.setContainerMapping(containerMapping);
    databaseTransferConsumer.setTargetObject(null);
    databaseTransferConsumer.setPreview(false);
    databaseTransferConsumer.setSettings(settings);
    LocalStatement statement = new LocalStatement(dBCSession, "Text");
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(dBCSession, statement, new StreamEntityMapping(inputFile2));

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> databaseTransferConsumer.fetchStart(dBCSession, resultSet, 0L, 1L));
    verify(dBCSession).getProgressMonitor();
  }

  /**
   * Test {@link DatabaseTransferConsumer#fetchEnd(DBCSession, DBCResultSet)}.
   *
   * <ul>
   *   <li>Then calls {@link DatabaseMappingContainer#getTarget()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#fetchEnd(DBCSession, DBCResultSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferConsumer.fetchEnd(DBCSession, DBCResultSet)"})
  public void testFetchEnd_thenCallsGetTarget() throws DBCException {
    // Arrange
    DatabaseMappingContainer containerMapping = mock(DatabaseMappingContainer.class);
    when(containerMapping.getTarget()).thenReturn(mock(DBSDataManipulator.class));

    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setContainerMapping(containerMapping);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);
    LocalStatement statement = new LocalStatement(null, "Text");
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamTransferResultSet resultSet =
        new StreamTransferResultSet(null, statement, new StreamEntityMapping(inputFile2));

    // Act
    databaseTransferConsumer.fetchEnd(null, resultSet);

    // Assert
    verify(containerMapping).getTarget();
  }

  /**
   * Test {@link DatabaseTransferConsumer#initTransfer(DBSObject, DatabaseConsumerSettings,
   * TransferParameters, IDataTransferProcessor, Map, DBPProject)} with {@code DBSObject}, {@code
   * DatabaseConsumerSettings}, {@code TransferParameters}, {@code IDataTransferProcessor}, {@code
   * Map}, {@code DBPProject}.
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#initTransfer(DBSObject,
   * DatabaseConsumerSettings, TransferParameters, IDataTransferProcessor, Map, DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferConsumer.initTransfer(DBSObject, DatabaseConsumerSettings, TransferParameters, IDataTransferProcessor, Map, DBPProject)"
  })
  public void
      testInitTransferWithDBSObjectDatabaseConsumerSettingsTransferParametersIDataTransferProcessorMapDBPProject() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    DatabaseConsumerSettings settings = new DatabaseConsumerSettings();
    TransferParameters parameters = new TransferParameters(true, true);
    DataExporterCSV processor = new DataExporterCSV();

    // Act
    databaseTransferConsumer.initTransfer(
        null, settings, parameters, processor, new HashMap<>(), mock(DBPProject.class));

    // Assert
    assertEquals("?", databaseTransferConsumer.getObjectContainerName());
    assertNull(databaseTransferConsumer.getDataSourceContainer());
    assertNull(databaseTransferConsumer.getObjectContainerIcon());
    assertNull(databaseTransferConsumer.getProject());
    assertSame(settings, databaseTransferConsumer.getSettings());
  }

  /**
   * Test {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferConsumer.startTransfer(DBRProgressMonitor)"})
  public void testStartTransfer() throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class));

    // Act and Assert
    databaseTransferConsumer.startTransfer(new LoggingProgressMonitor());
  }

  /**
   * Test {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferConsumer.startTransfer(DBRProgressMonitor)"})
  public void testStartTransfer2() throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class));
    Class<Object> forClass = Object.class;

    // Act and Assert
    databaseTransferConsumer.startTransfer(new LoggingProgressMonitor(Log.getLog(forClass)));
  }

  /**
   * Test {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferConsumer.startTransfer(DBRProgressMonitor)"})
  public void testStartTransfer3() throws DBException {
    // Arrange
    DatabaseConsumerSettings settings = new DatabaseConsumerSettings();
    settings.setContainer(new StreamDataSource("Create necessary database objects"));

    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(settings);

    // Act and Assert
    databaseTransferConsumer.startTransfer(new LoggingProgressMonitor());
  }

  /**
   * Test {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferConsumer.startTransfer(DBRProgressMonitor)"})
  public void testStartTransfer4() throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class));
    databaseTransferConsumer.setPreview(true);

    // Act and Assert
    databaseTransferConsumer.startTransfer(new LoggingProgressMonitor());
  }

  /**
   * Test {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferConsumer.startTransfer(DBRProgressMonitor)"})
  public void testStartTransfer_thenThrowDBCException() throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(new DatabaseConsumerSettings());

    // Act and Assert
    assertThrows(
        DBCException.class,
        () -> databaseTransferConsumer.startTransfer(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferConsumer.startTransfer(DBRProgressMonitor)"})
  public void testStartTransfer_thenThrowDBException() throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(new DatabaseConsumerSettings());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(
            monitor,
            consumerSettings,
            new StreamEntityMapping(inputFile),
            mock(DBSDataManipulator.class));
    databaseTransferConsumer.setContainerMapping(containerMapping);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile2);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> databaseTransferConsumer.startTransfer(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link SubTaskProgressMonitor#SubTaskProgressMonitor(DBRProgressMonitor)} with
   *       original is {@link LoggingProgressMonitor#LoggingProgressMonitor()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferConsumer.startTransfer(DBRProgressMonitor)"})
  public void testStartTransfer_whenSubTaskProgressMonitorWithOriginalIsLoggingProgressMonitor()
      throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class));

    // Act and Assert
    databaseTransferConsumer.startTransfer(
        new SubTaskProgressMonitor(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>When {@link SubTaskProgressMonitor#SubTaskProgressMonitor(DBRProgressMonitor)} with
   *       original is {@link LoggingProgressMonitor#LoggingProgressMonitor(Log)}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#startTransfer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferConsumer.startTransfer(DBRProgressMonitor)"})
  public void testStartTransfer_whenSubTaskProgressMonitorWithOriginalIsLoggingProgressMonitor2()
      throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class));
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor original = new LoggingProgressMonitor(Log.getLog(forClass));

    // Act and Assert
    databaseTransferConsumer.startTransfer(new SubTaskProgressMonitor(original));
  }

  /**
   * Test {@link DatabaseTransferConsumer#getSourceObject()}.
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getSourceObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSDataContainer DatabaseTransferConsumer.getSourceObject()"})
  public void testGetSourceObject() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class));
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    StreamEntityMapping source = new StreamEntityMapping(inputFile);

    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings, source);
    databaseTransferConsumer.setContainerMapping(containerMapping);

    // Act and Assert
    assertSame(source, databaseTransferConsumer.getSourceObject());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getSourceObject()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseTransferConsumer#DatabaseTransferConsumer()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getSourceObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSDataContainer DatabaseTransferConsumer.getSourceObject()"})
  public void testGetSourceObject_givenDatabaseTransferConsumer_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new DatabaseTransferConsumer().getSourceObject());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getTargetObject()}.
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getTargetObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSDataManipulator DatabaseTransferConsumer.getTargetObject()"})
  public void testGetTargetObject() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class));
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    databaseTransferConsumer.setContainerMapping(containerMapping);

    // Act and Assert
    assertNull(databaseTransferConsumer.getTargetObject());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getTargetObject()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseTransferConsumer#DatabaseTransferConsumer()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getTargetObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSDataManipulator DatabaseTransferConsumer.getTargetObject()"})
  public void testGetTargetObject_givenDatabaseTransferConsumer() {
    // Arrange, Act and Assert
    assertNull(new DatabaseTransferConsumer().getTargetObject());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectName()}.
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseTransferConsumer.getObjectName()"})
  public void testGetObjectName() {
    // Arrange
    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);
    when(targetObject.getName()).thenReturn("Name");
    when(targetObject.getDataSource()).thenReturn(new StreamDataSource("Input Name"));

    // Act
    String actualObjectName = new DatabaseTransferConsumer(targetObject).getObjectName();

    // Assert
    verify(targetObject).getName();
    verify(targetObject, atLeast(1)).getDataSource();
    assertEquals("Name [Existing]", actualObjectName);
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectName()}.
   *
   * <ul>
   *   <li>Given {@link DBSDataManipulator} {@link DBSDataManipulator#getDataSource()} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseTransferConsumer.getObjectName()"})
  public void testGetObjectName_givenDBSDataManipulatorGetDataSourceReturnNull() {
    // Arrange
    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);
    when(targetObject.getName()).thenReturn("Name");
    when(targetObject.getDataSource()).thenReturn(null);

    // Act
    String actualObjectName = new DatabaseTransferConsumer(targetObject).getObjectName();

    // Assert
    verify(targetObject).getName();
    verify(targetObject).getDataSource();
    assertEquals("Name [Existing]", actualObjectName);
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectName()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseTransferConsumer#DatabaseTransferConsumer()}.
   *   <li>Then return {@code ?}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseTransferConsumer.getObjectName()"})
  public void testGetObjectName_givenDatabaseTransferConsumer_thenReturnQuestionMark() {
    // Arrange, Act and Assert
    assertEquals("?", new DatabaseTransferConsumer().getObjectName());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectName()}.
   *
   * <ul>
   *   <li>Given {@link StreamDataSource#StreamDataSource(String)} with inputName is {@code ?}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseTransferConsumer.getObjectName()"})
  public void testGetObjectName_givenStreamDataSourceWithInputNameIsQuestionMark() {
    // Arrange, Act and Assert
    assertEquals("?", new DatabaseTransferConsumer(new StreamDataSource("?")).getObjectName());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectName()}.
   *
   * <ul>
   *   <li>Then return {@code null [Existing]}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseTransferConsumer.getObjectName()"})
  public void testGetObjectName_thenReturnNullExisting() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class));
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    databaseTransferConsumer.setContainerMapping(containerMapping);

    // Act and Assert
    assertEquals("null [Existing]", databaseTransferConsumer.getObjectName());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectIcon()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseTransferConsumer#DatabaseTransferConsumer()}.
   *   <li>Then return Token is {@code table}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseTransferConsumer.getObjectIcon()"})
  public void testGetObjectIcon_givenDatabaseTransferConsumer_thenReturnTokenIsTable() {
    // Arrange and Act
    DBPImage actualObjectIcon = new DatabaseTransferConsumer().getObjectIcon();
    String actualLocation = actualObjectIcon.getLocation();

    // Assert
    assertTrue(actualObjectIcon instanceof DBIcon);
    assertEquals("table", ((DBIcon) actualObjectIcon).getToken());
    assertEquals("tree/table.svg", actualObjectIcon.getLocation());
    assertEquals("tree/table.svg", actualLocation);
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectIcon()}.
   *
   * <ul>
   *   <li>Then {@link DatabaseTransferConsumer#DatabaseTransferConsumer()} SourceObject {@link
   *       StreamEntityMapping}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseTransferConsumer.getObjectIcon()"})
  public void testGetObjectIcon_thenDatabaseTransferConsumerSourceObjectStreamEntityMapping() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    databaseTransferConsumer.setContainerMapping(containerMapping);

    // Act
    DBPImage actualObjectIcon = databaseTransferConsumer.getObjectIcon();
    String actualLocation = actualObjectIcon.getLocation();

    // Assert
    assertTrue(actualObjectIcon instanceof DBIcon);
    DBSDataContainer sourceObject = databaseTransferConsumer.getSourceObject();
    assertTrue(sourceObject instanceof StreamEntityMapping);
    assertEquals("table", ((DBIcon) actualObjectIcon).getToken());
    assertEquals("tree/table.svg", actualObjectIcon.getLocation());
    assertEquals("tree/table.svg", actualLocation);
    assertSame(
        ((DBIcon) actualObjectIcon).TREE_TABLE,
        ((StreamEntityMapping) sourceObject).getEntityType().getIcon());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectIcon()}.
   *
   * <ul>
   *   <li>Then return Token is {@code folder_table}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseTransferConsumer.getObjectIcon()"})
  public void testGetObjectIcon_thenReturnTokenIsFolderTable() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(new StreamDataSource("Input Name"));

    // Act
    DBPImage actualObjectIcon = databaseTransferConsumer.getObjectIcon();
    String actualLocation = actualObjectIcon.getLocation();

    // Assert
    assertTrue(actualObjectIcon instanceof DBIcon);
    assertEquals("folder_table", ((DBIcon) actualObjectIcon).getToken());
    assertEquals("tree/folder_table.svg", actualObjectIcon.getLocation());
    assertEquals("tree/folder_table.svg", actualLocation);
    assertSame(
        ((DBIcon) actualObjectIcon).TREE_FOLDER_TABLE,
        databaseTransferConsumer.getObjectContainerIcon());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectContainerName()}.
   *
   * <ul>
   *   <li>Then return {@code Input Name}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectContainerName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseTransferConsumer.getObjectContainerName()"})
  public void testGetObjectContainerName_thenReturnInputName() {
    // Arrange
    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);
    when(targetObject.getDataSource()).thenReturn(new StreamDataSource("Input Name"));

    // Act
    String actualObjectContainerName =
        new DatabaseTransferConsumer(targetObject).getObjectContainerName();

    // Assert
    verify(targetObject).getDataSource();
    assertEquals("Input Name", actualObjectContainerName);
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectContainerName()}.
   *
   * <ul>
   *   <li>Then return {@code "Input Name"}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectContainerName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseTransferConsumer.getObjectContainerName()"})
  public void testGetObjectContainerName_thenReturnInputName2() {
    // Arrange, Act and Assert
    assertEquals(
        "\"Input Name\"",
        new DatabaseTransferConsumer(new StreamDataSource("Input Name")).getObjectContainerName());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectContainerName()}.
   *
   * <ul>
   *   <li>Then return {@code ?}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectContainerName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseTransferConsumer.getObjectContainerName()"})
  public void testGetObjectContainerName_thenReturnQuestionMark() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(new DatabaseConsumerSettings());

    // Act and Assert
    assertEquals("?", databaseTransferConsumer.getObjectContainerName());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectContainerName()}.
   *
   * <ul>
   *   <li>Then return {@code "}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectContainerName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DatabaseTransferConsumer.getObjectContainerName()"})
  public void testGetObjectContainerName_thenReturnQuotationMark() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(null);
    DBVContainer targetObjectContainer = new DBVContainer(parent, "\"");

    // Act
    String actualObjectContainerName =
        new DatabaseTransferConsumer(targetObjectContainer).getObjectContainerName();

    // Assert
    verify(parent).getDataSource();
    assertEquals("\"", actualObjectContainerName);
  }

  /**
   * Test {@link DatabaseTransferConsumer#getObjectContainerIcon()}.
   *
   * <ul>
   *   <li>Given {@link StreamDataSource#StreamDataSource(String)} with {@code Input Name}.
   *   <li>Then return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getObjectContainerIcon()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DatabaseTransferConsumer.getObjectContainerIcon()"})
  public void testGetObjectContainerIcon_givenStreamDataSourceWithInputName_thenReturnDBIcon() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(new StreamDataSource("Input Name"));

    // Act
    DBPImage actualObjectContainerIcon = databaseTransferConsumer.getObjectContainerIcon();
    String actualLocation = actualObjectContainerIcon.getLocation();

    // Assert
    assertTrue(actualObjectContainerIcon instanceof DBIcon);
    assertEquals("folder_table", ((DBIcon) actualObjectContainerIcon).getToken());
    assertEquals("tree/folder_table.svg", actualObjectContainerIcon.getLocation());
    assertEquals("tree/folder_table.svg", actualLocation);
    assertSame(
        ((DBIcon) actualObjectContainerIcon).TREE_FOLDER_TABLE,
        databaseTransferConsumer.getObjectIcon());
  }

  /**
   * Test {@link DatabaseTransferConsumer#isConfigurationComplete()}.
   *
   * <ul>
   *   <li>Given {@link DatabaseTransferConsumer#DatabaseTransferConsumer()}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#isConfigurationComplete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferConsumer.isConfigurationComplete()"})
  public void testIsConfigurationComplete_givenDatabaseTransferConsumer_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new DatabaseTransferConsumer().isConfigurationComplete());
  }

  /**
   * Test {@link DatabaseTransferConsumer#isConfigurationComplete()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#isConfigurationComplete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferConsumer.isConfigurationComplete()"})
  public void testIsConfigurationComplete_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class)).isConfigurationComplete());
  }

  /**
   * Test {@link DatabaseTransferConsumer#getDataSourceContainer()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#getDataSourceContainer()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.DBPDataSourceContainer DatabaseTransferConsumer.getDataSourceContainer()"
  })
  public void testGetDataSourceContainer_thenReturnNull() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(new DatabaseConsumerSettings());

    // Act and Assert
    assertNull(databaseTransferConsumer.getDataSourceContainer());
  }

  /**
   * Test {@link DatabaseTransferConsumer#equals(Object)}, and {@link
   * DatabaseTransferConsumer#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferConsumer.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    DatabaseTransferConsumer databaseTransferConsumer2 = new DatabaseTransferConsumer();

    // Act and Assert
    assertEquals(databaseTransferConsumer, databaseTransferConsumer2);
    assertNotEquals(databaseTransferConsumer.hashCode(), databaseTransferConsumer2.hashCode());
  }

  /**
   * Test {@link DatabaseTransferConsumer#equals(Object)}, and {@link
   * DatabaseTransferConsumer#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferConsumer.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    databaseTransferConsumer.setContainerMapping(containerMapping);
    DatabaseTransferConsumer databaseTransferConsumer2 = new DatabaseTransferConsumer();

    // Act and Assert
    assertEquals(databaseTransferConsumer, databaseTransferConsumer2);
    assertNotEquals(databaseTransferConsumer.hashCode(), databaseTransferConsumer2.hashCode());
  }

  /**
   * Test {@link DatabaseTransferConsumer#equals(Object)}, and {@link
   * DatabaseTransferConsumer#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferConsumer.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();

    // Act and Assert
    assertEquals(databaseTransferConsumer, databaseTransferConsumer);
    int expectedHashCodeResult = databaseTransferConsumer.hashCode();
    assertEquals(expectedHashCodeResult, databaseTransferConsumer.hashCode());
  }

  /**
   * Test {@link DatabaseTransferConsumer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferConsumer.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class));

    // Act and Assert
    assertNotEquals(databaseTransferConsumer, new DatabaseTransferConsumer());
  }

  /**
   * Test {@link DatabaseTransferConsumer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferConsumer.equals(Object)"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DatabaseTransferConsumer(), null);
  }

  /**
   * Test {@link DatabaseTransferConsumer#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferConsumer#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DatabaseTransferConsumer.equals(Object)"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DatabaseTransferConsumer(), "Different type to DatabaseTransferConsumer");
  }

  /**
   * Test {@link DatabaseTransferConsumer#supportsChangingReferentialIntegrity(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#supportsChangingReferentialIntegrity(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DatabaseTransferConsumer.supportsChangingReferentialIntegrity(DBRProgressMonitor)"
  })
  public void testSupportsChangingReferentialIntegrity() throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class));

    // Act and Assert
    assertFalse(
        databaseTransferConsumer.supportsChangingReferentialIntegrity(
            new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DatabaseTransferConsumer#supportsChangingReferentialIntegrity(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#supportsChangingReferentialIntegrity(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DatabaseTransferConsumer.supportsChangingReferentialIntegrity(DBRProgressMonitor)"
  })
  public void testSupportsChangingReferentialIntegrity2() throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(
            monitor,
            consumerSettings,
            new StreamEntityMapping(inputFile),
            mock(DBSDataManipulator.class));
    databaseTransferConsumer.setContainerMapping(containerMapping);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile2);

    // Act and Assert
    assertFalse(
        databaseTransferConsumer.supportsChangingReferentialIntegrity(
            new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DatabaseTransferConsumer#supportsChangingReferentialIntegrity(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#supportsChangingReferentialIntegrity(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DatabaseTransferConsumer.supportsChangingReferentialIntegrity(DBRProgressMonitor)"
  })
  public void testSupportsChangingReferentialIntegrity3() throws DBException {
    // Arrange
    DatabaseConsumerSettings settings = new DatabaseConsumerSettings();
    settings.setContainer(
        new StreamDataSource(
            "Can't initialize database consumer. No target object and no target container"));

    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(settings);

    // Act and Assert
    assertFalse(
        databaseTransferConsumer.supportsChangingReferentialIntegrity(
            new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DatabaseTransferConsumer#supportsChangingReferentialIntegrity(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#supportsChangingReferentialIntegrity(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DatabaseTransferConsumer.supportsChangingReferentialIntegrity(DBRProgressMonitor)"
  })
  public void testSupportsChangingReferentialIntegrity_thenThrowDBCException() throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(new DatabaseConsumerSettings());

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            databaseTransferConsumer.supportsChangingReferentialIntegrity(
                new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DatabaseTransferConsumer#enableReferentialIntegrity(DBRProgressMonitor, boolean)}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#enableReferentialIntegrity(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferConsumer.enableReferentialIntegrity(DBRProgressMonitor, boolean)"
  })
  public void testEnableReferentialIntegrity() throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class));

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            databaseTransferConsumer.enableReferentialIntegrity(
                new LoggingProgressMonitor(), true));
  }

  /**
   * Test {@link DatabaseTransferConsumer#enableReferentialIntegrity(DBRProgressMonitor, boolean)}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#enableReferentialIntegrity(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferConsumer.enableReferentialIntegrity(DBRProgressMonitor, boolean)"
  })
  public void testEnableReferentialIntegrity2() throws DBException {
    // Arrange
    DatabaseConsumerSettings settings = new DatabaseConsumerSettings();
    settings.setContainer(
        new StreamDataSource(
            "Can't initialize database consumer. No target object and no target container"));

    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(settings);

    // Act and Assert
    assertThrows(
        DBException.class,
        () ->
            databaseTransferConsumer.enableReferentialIntegrity(
                new LoggingProgressMonitor(), true));
  }

  /**
   * Test {@link DatabaseTransferConsumer#enableReferentialIntegrity(DBRProgressMonitor, boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#enableReferentialIntegrity(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferConsumer.enableReferentialIntegrity(DBRProgressMonitor, boolean)"
  })
  public void testEnableReferentialIntegrity_thenThrowDBCException() throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(new DatabaseConsumerSettings());

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            databaseTransferConsumer.enableReferentialIntegrity(
                new LoggingProgressMonitor(), true));
  }

  /**
   * Test {@link DatabaseTransferConsumer#getChangeReferentialIntegrityStatement(DBRProgressMonitor,
   * boolean)}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#getChangeReferentialIntegrityStatement(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseTransferConsumer.getChangeReferentialIntegrityStatement(DBRProgressMonitor, boolean)"
  })
  public void testGetChangeReferentialIntegrityStatement() throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer =
        new DatabaseTransferConsumer(mock(DBSDataManipulator.class));

    // Act and Assert
    assertNull(
        databaseTransferConsumer.getChangeReferentialIntegrityStatement(
            new LoggingProgressMonitor(), true));
  }

  /**
   * Test {@link DatabaseTransferConsumer#getChangeReferentialIntegrityStatement(DBRProgressMonitor,
   * boolean)}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#getChangeReferentialIntegrityStatement(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseTransferConsumer.getChangeReferentialIntegrityStatement(DBRProgressMonitor, boolean)"
  })
  public void testGetChangeReferentialIntegrityStatement2() throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(
            monitor,
            consumerSettings,
            new StreamEntityMapping(inputFile),
            mock(DBSDataManipulator.class));
    databaseTransferConsumer.setContainerMapping(containerMapping);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile2);

    // Act and Assert
    assertNull(
        databaseTransferConsumer.getChangeReferentialIntegrityStatement(
            new LoggingProgressMonitor(), true));
  }

  /**
   * Test {@link DatabaseTransferConsumer#getChangeReferentialIntegrityStatement(DBRProgressMonitor,
   * boolean)}.
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#getChangeReferentialIntegrityStatement(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseTransferConsumer.getChangeReferentialIntegrityStatement(DBRProgressMonitor, boolean)"
  })
  public void testGetChangeReferentialIntegrityStatement3() throws DBException {
    // Arrange
    DatabaseConsumerSettings settings = new DatabaseConsumerSettings();
    settings.setContainer(
        new StreamDataSource(
            "Can't initialize database consumer. No target object and no target container"));

    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(settings);

    // Act and Assert
    assertNull(
        databaseTransferConsumer.getChangeReferentialIntegrityStatement(
            new LoggingProgressMonitor(), true));
  }

  /**
   * Test {@link DatabaseTransferConsumer#getChangeReferentialIntegrityStatement(DBRProgressMonitor,
   * boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DatabaseTransferConsumer#getChangeReferentialIntegrityStatement(DBRProgressMonitor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DatabaseTransferConsumer.getChangeReferentialIntegrityStatement(DBRProgressMonitor, boolean)"
  })
  public void testGetChangeReferentialIntegrityStatement_thenThrowDBCException()
      throws DBException {
    // Arrange
    DatabaseTransferConsumer databaseTransferConsumer = new DatabaseTransferConsumer();
    databaseTransferConsumer.setSettings(new DatabaseConsumerSettings());

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            databaseTransferConsumer.getChangeReferentialIntegrityStatement(
                new LoggingProgressMonitor(), true));
  }
}
