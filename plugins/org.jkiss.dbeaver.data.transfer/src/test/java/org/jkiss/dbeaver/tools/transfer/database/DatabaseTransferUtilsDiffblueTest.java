package org.jkiss.dbeaver.tools.transfer.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.edit.DBECommandContext;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.impl.edit.SQLDatabasePersistAction;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.navigator.DBNModel;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.VoidProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSDataManipulator;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSObjectContainer;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.jkiss.dbeaver.tools.transfer.database.DatabaseTransferUtils.TargetCommandContext;
import org.jkiss.dbeaver.tools.transfer.stream.StreamDataImporterColumnInfo;
import org.jkiss.dbeaver.tools.transfer.stream.StreamEntityMapping;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSource;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamDataSourceContainer;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamExecutionContext;
import org.jkiss.dbeaver.tools.transfer.stream.model.StreamTransferSession;
import org.jkiss.utils.Pair;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DatabaseTransferUtilsDiffblueTest {
  /**
   * Test {@link DatabaseTransferUtils#refreshDatabaseModel(DBRProgressMonitor,
   * DatabaseConsumerSettings, DatabaseMappingContainer)}.
   *
   * <p>Method under test: {@link DatabaseTransferUtils#refreshDatabaseModel(DBRProgressMonitor,
   * DatabaseConsumerSettings, DatabaseMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.refreshDatabaseModel(DBRProgressMonitor, DatabaseConsumerSettings, DatabaseMappingContainer)"
  })
  public void testRefreshDatabaseModel() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPProject dbpProject = mock(DBPProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(dbpProject.getNavigatorModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    StreamDataSourceContainer container = mock(StreamDataSourceContainer.class);
    when(container.getParentObject()).thenReturn(new StreamDataSource("Input Name"));
    when(container.getName()).thenReturn("Name");
    when(container.getRegistry()).thenReturn(dbpDataSourceRegistry);
    StreamDataSource container2 = new StreamDataSource(container);

    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(container2);
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile));

    // Act
    DatabaseTransferUtils.refreshDatabaseModel(monitor, consumerSettings, containerMapping);

    // Assert
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(container).getName();
    verify(container, atLeast(1)).getParentObject();
    verify(container).getRegistry();
  }

  /**
   * Test {@link DatabaseTransferUtils#refreshDatabaseModel(DBRProgressMonitor,
   * DatabaseConsumerSettings, DatabaseMappingContainer)}.
   *
   * <p>Method under test: {@link DatabaseTransferUtils#refreshDatabaseModel(DBRProgressMonitor,
   * DatabaseConsumerSettings, DatabaseMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.refreshDatabaseModel(DBRProgressMonitor, DatabaseConsumerSettings, DatabaseMappingContainer)"
  })
  public void testRefreshDatabaseModel2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPProject dbpProject = mock(DBPProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(dbpProject.getNavigatorModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    StreamDataSourceContainer container = mock(StreamDataSourceContainer.class);
    when(container.getParentObject()).thenThrow(new NumberFormatException());
    when(container.getName()).thenReturn("Name");
    when(container.getRegistry()).thenReturn(dbpDataSourceRegistry);
    StreamDataSource container2 = new StreamDataSource(container);

    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(container2);
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            DatabaseTransferUtils.refreshDatabaseModel(
                monitor, consumerSettings, containerMapping));
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(container).getName();
    verify(container).getParentObject();
    verify(container).getRegistry();
  }

  /**
   * Test {@link DatabaseTransferUtils#refreshDatabaseModel(DBRProgressMonitor,
   * DatabaseConsumerSettings, DatabaseMappingContainer)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getParentObject()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#refreshDatabaseModel(DBRProgressMonitor,
   * DatabaseConsumerSettings, DatabaseMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.refreshDatabaseModel(DBRProgressMonitor, DatabaseConsumerSettings, DatabaseMappingContainer)"
  })
  public void testRefreshDatabaseModel_thenCallsGetParentObject() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPProject dbpProject = mock(DBPProject.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    when(dbpProject.getNavigatorModel()).thenReturn(dbnModel);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getParentObject()).thenThrow(new NumberFormatException());
    DBVContainer container = new DBVContainer(parent, "Refresh database model");
    DBVEntity dbvEntity =
        new DBVEntity(container, "Refresh database model", "Refresh database model");

    StreamDataSourceContainer container2 = mock(StreamDataSourceContainer.class);
    when(container2.getParentObject()).thenReturn(dbvEntity);
    when(container2.getName()).thenReturn("Name");
    when(container2.getRegistry()).thenReturn(dbpDataSourceRegistry);
    StreamDataSource container3 = new StreamDataSource(container2);

    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(container3);
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            DatabaseTransferUtils.refreshDatabaseModel(
                monitor, consumerSettings, containerMapping));
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getParentObject();
    verify(container2).getName();
    verify(container2).getParentObject();
    verify(container2).getRegistry();
  }

  /**
   * Test {@link DatabaseTransferUtils#refreshDatabaseModel(DBRProgressMonitor,
   * DatabaseConsumerSettings, DatabaseMappingContainer)}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#refreshDatabaseModel(DBRProgressMonitor,
   * DatabaseConsumerSettings, DatabaseMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.refreshDatabaseModel(DBRProgressMonitor, DatabaseConsumerSettings, DatabaseMappingContainer)"
  })
  public void testRefreshDatabaseModel_thenDoesNotThrow() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(new StreamDataSource("Refresh database model"));
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile));

    // Act and Assert
    DatabaseTransferUtils.refreshDatabaseModel(monitor, consumerSettings, containerMapping);
  }

  /**
   * Test {@link DatabaseTransferUtils#refreshDatabaseModel(DBRProgressMonitor,
   * DatabaseConsumerSettings, DatabaseMappingContainer)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#refreshDatabaseModel(DBRProgressMonitor,
   * DatabaseConsumerSettings, DatabaseMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.refreshDatabaseModel(DBRProgressMonitor, DatabaseConsumerSettings, DatabaseMappingContainer)"
  })
  public void testRefreshDatabaseModel_whenJavaLangObject_thenDoesNotThrow() throws DBException {
    // Arrange
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor monitor = new LoggingProgressMonitor(Log.getLog(forClass));

    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(new StreamDataSource("Refresh database model"));
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile));

    // Act and Assert
    DatabaseTransferUtils.refreshDatabaseModel(monitor, consumerSettings, containerMapping);
  }

  /**
   * Test {@link DatabaseTransferUtils#refreshDatabaseModel(DBRProgressMonitor,
   * DatabaseConsumerSettings, DatabaseMappingContainer)}.
   *
   * <ul>
   *   <li>When {@link VoidProgressMonitor} (default constructor).
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#refreshDatabaseModel(DBRProgressMonitor,
   * DatabaseConsumerSettings, DatabaseMappingContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.refreshDatabaseModel(DBRProgressMonitor, DatabaseConsumerSettings, DatabaseMappingContainer)"
  })
  public void testRefreshDatabaseModel_whenVoidProgressMonitor_thenDoesNotThrow()
      throws DBException {
    // Arrange
    VoidProgressMonitor monitor = new VoidProgressMonitor();

    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    consumerSettings.setContainer(new StreamDataSource("Refresh database model"));
    DatabaseConsumerSettings consumerSettings2 = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings2, new StreamEntityMapping(inputFile));

    // Act and Assert
    DatabaseTransferUtils.refreshDatabaseModel(monitor, consumerSettings, containerMapping);
  }

  /**
   * Test {@link DatabaseTransferUtils#applyPropertyChanges(DBRProgressMonitor, Map,
   * DBECommandContext, DatabaseMappingContainer, DBSEntity)}.
   *
   * <p>Method under test: {@link DatabaseTransferUtils#applyPropertyChanges(DBRProgressMonitor,
   * Map, DBECommandContext, DatabaseMappingContainer, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.applyPropertyChanges(DBRProgressMonitor, Map, DBECommandContext, DatabaseMappingContainer, DBSEntity)"
  })
  public void testApplyPropertyChanges() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    HashMap<DBPPropertyDescriptor, Object> changedProperties = new HashMap<>();
    TargetCommandContext commandContext = new TargetCommandContext(null);
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    DatabaseTransferUtils.applyPropertyChanges(
        monitor,
        changedProperties,
        commandContext,
        containerMapping,
        new StreamEntityMapping(inputFile2));
  }

  /**
   * Test {@link DatabaseTransferUtils#applyPropertyChanges(DBRProgressMonitor, Map,
   * DBECommandContext, DatabaseMappingContainer, DBSEntity)}.
   *
   * <p>Method under test: {@link DatabaseTransferUtils#applyPropertyChanges(DBRProgressMonitor,
   * Map, DBECommandContext, DatabaseMappingContainer, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.applyPropertyChanges(DBRProgressMonitor, Map, DBECommandContext, DatabaseMappingContainer, DBSEntity)"
  })
  public void testApplyPropertyChanges2() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    HashMap<DBPPropertyDescriptor, Object> changedProperties = new HashMap<>();
    TargetCommandContext commandContext = new TargetCommandContext(null);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    DatabaseMappingContainer containerMapping = mock(DatabaseMappingContainer.class);
    when(containerMapping.getRawChangedPropertiesMap()).thenThrow(new NumberFormatException());
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            DatabaseTransferUtils.applyPropertyChanges(
                monitor,
                changedProperties,
                commandContext,
                containerMapping,
                new StreamEntityMapping(inputFile2)));
    verify(containerMapping).getRawChangedPropertiesMap();
  }

  /**
   * Test {@link DatabaseTransferUtils#applyPropertyChanges(DBRProgressMonitor, Map,
   * DBECommandContext, DatabaseMappingContainer, DBSEntity)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#applyPropertyChanges(DBRProgressMonitor,
   * Map, DBECommandContext, DatabaseMappingContainer, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.applyPropertyChanges(DBRProgressMonitor, Map, DBECommandContext, DatabaseMappingContainer, DBSEntity)"
  })
  public void testApplyPropertyChanges_givenNull() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    HashMap<DBPPropertyDescriptor, Object> changedProperties = new HashMap<>();
    TargetCommandContext commandContext = new TargetCommandContext(null);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    DatabaseMappingContainer containerMapping = mock(DatabaseMappingContainer.class);
    when(containerMapping.getRawChangedPropertiesMap()).thenReturn(null);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    DatabaseTransferUtils.applyPropertyChanges(
        monitor,
        changedProperties,
        commandContext,
        containerMapping,
        new StreamEntityMapping(inputFile2));

    // Assert
    verify(containerMapping).getRawChangedPropertiesMap();
  }

  /**
   * Test {@link DatabaseTransferUtils#applyPropertyChanges(DBRProgressMonitor, Map,
   * DBECommandContext, DatabaseMappingContainer, DBSEntity)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#applyPropertyChanges(DBRProgressMonitor,
   * Map, DBECommandContext, DatabaseMappingContainer, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.applyPropertyChanges(DBRProgressMonitor, Map, DBECommandContext, DatabaseMappingContainer, DBSEntity)"
  })
  public void testApplyPropertyChanges_thenCallsGetId() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    HashMap<DBPPropertyDescriptor, Object> changedProperties = new HashMap<>();
    TargetCommandContext commandContext = new TargetCommandContext(null);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("foo", "42");

    DatabaseMappingContainer containerMapping = mock(DatabaseMappingContainer.class);
    when(containerMapping.getRawChangedPropertiesMap()).thenReturn(stringObjectMap);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new NumberFormatException());
    DBVContainer container = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVEntity copy =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");

    DBVEntity table = new DBVEntity(container, copy, targetModel);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () ->
            DatabaseTransferUtils.applyPropertyChanges(
                monitor, changedProperties, commandContext, containerMapping, table));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(containerMapping).getRawChangedPropertiesMap();
  }

  /**
   * Test {@link DatabaseTransferUtils#applyPropertyChanges(DBRProgressMonitor, Map,
   * DBECommandContext, DatabaseMappingContainer, DBSEntity)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#applyPropertyChanges(DBRProgressMonitor,
   * Map, DBECommandContext, DatabaseMappingContainer, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.applyPropertyChanges(DBRProgressMonitor, Map, DBECommandContext, DatabaseMappingContainer, DBSEntity)"
  })
  public void testApplyPropertyChanges_whenNull() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    HashMap<DBPPropertyDescriptor, Object> changedProperties = new HashMap<>();
    TargetCommandContext commandContext = new TargetCommandContext(null);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    HashMap<String, Object> stringObjectMap = new HashMap<>();
    stringObjectMap.put("foo", "42");

    DatabaseMappingContainer containerMapping = mock(DatabaseMappingContainer.class);
    when(containerMapping.getRawChangedPropertiesMap()).thenReturn(stringObjectMap);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    DatabaseTransferUtils.applyPropertyChanges(
        monitor, changedProperties, commandContext, containerMapping, null);

    // Assert
    verify(containerMapping, atLeast(1)).getRawChangedPropertiesMap();
  }

  /**
   * Test {@link DatabaseTransferUtils#applyPropertyChanges(DBRProgressMonitor, Map,
   * DBECommandContext, DatabaseMappingContainer, DBSEntity)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#applyPropertyChanges(DBRProgressMonitor,
   * Map, DBECommandContext, DatabaseMappingContainer, DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.applyPropertyChanges(DBRProgressMonitor, Map, DBECommandContext, DatabaseMappingContainer, DBSEntity)"
  })
  public void testApplyPropertyChanges_whenNull_thenDoesNotThrow() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    TargetCommandContext commandContext = new TargetCommandContext(null);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    DatabaseTransferUtils.applyPropertyChanges(
        monitor, null, commandContext, null, new StreamEntityMapping(inputFile));
  }

  /**
   * Test {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}.
   *
   * <p>Method under test: {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction DatabaseTransferUtils.generateTargetAttributeDDL(DBPDataSource, DatabaseMappingAttribute)"
  })
  public void testGenerateTargetAttributeDDL() throws DBException {
    // Arrange
    StreamDataSource dataSource = mock(StreamDataSource.class);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    StreamDataSource streamDataSource = mock(StreamDataSource.class);
    when(streamDataSource.getSQLDialect()).thenThrow(new NumberFormatException());

    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);
    when(targetObject.getName()).thenReturn("Name");
    when(targetObject.getDataSource()).thenReturn(streamDataSource);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(
            monitor, consumerSettings, new StreamEntityMapping(inputFile2), targetObject);
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), null);
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            null, 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent2, source);

    DatabaseMappingAttribute attribute2 = new DatabaseMappingAttribute(attribute, parent);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () -> DatabaseTransferUtils.generateTargetAttributeDDL(dataSource, attribute2));
    verify(targetObject).getName();
    verify(targetObject, atLeast(1)).getDataSource();
    verify(streamDataSource).getSQLDialect();
  }

  /**
   * Test {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}.
   *
   * <p>Method under test: {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction DatabaseTransferUtils.generateTargetAttributeDDL(DBPDataSource, DatabaseMappingAttribute)"
  })
  public void testGenerateTargetAttributeDDL2() throws DBException {
    // Arrange
    StreamDataSource dataSource = mock(StreamDataSource.class);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile2);

    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);
    when(targetObject.getName()).thenReturn("Name");
    when(targetObject.getDataSource()).thenReturn(new StreamDataSource("Input Name"));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(
            monitor, consumerSettings, new StreamEntityMapping(inputFile3), targetObject);

    DatabaseMappingAttribute attribute = mock(DatabaseMappingAttribute.class);
    when(attribute.getTargetName()).thenThrow(new NumberFormatException());
    when(attribute.getParent()).thenReturn(databaseMappingContainer);
    Path inputFile4 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile4);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () -> DatabaseTransferUtils.generateTargetAttributeDDL(dataSource, attribute));
    verify(targetObject).getName();
    verify(targetObject, atLeast(1)).getDataSource();
    verify(attribute).getParent();
    verify(attribute).getTargetName();
  }

  /**
   * Test {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction DatabaseTransferUtils.generateTargetAttributeDDL(DBPDataSource, DatabaseMappingAttribute)"
  })
  public void testGenerateTargetAttributeDDL_givenNull() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new NumberFormatException());

    StreamDataSource dataSource = mock(StreamDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);
    when(targetObject.getName()).thenReturn("Name");
    when(targetObject.getDataSource()).thenReturn(null);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(
            monitor, consumerSettings, new StreamEntityMapping(inputFile2), targetObject);
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), null);
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            null, 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent2, source);

    DatabaseMappingAttribute attribute2 = new DatabaseMappingAttribute(attribute, parent);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () -> DatabaseTransferUtils.generateTargetAttributeDDL(dataSource, attribute2));
    verify(dbpDataSourceContainer).getPreferenceStore();
    verify(targetObject).getName();
    verify(dataSource).getContainer();
    verify(targetObject).getDataSource();
  }

  /**
   * Test {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}.
   *
   * <ul>
   *   <li>Given {@link StreamDataSource} {@link StreamDataSource#getSQLDialect()} return {@link
   *       BasicSQLDialect#INSTANCE}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction DatabaseTransferUtils.generateTargetAttributeDDL(DBPDataSource, DatabaseMappingAttribute)"
  })
  public void testGenerateTargetAttributeDDL_givenStreamDataSourceGetSQLDialectReturnInstance()
      throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new NumberFormatException());

    StreamDataSource dataSource = mock(StreamDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    StreamDataSource streamDataSource = mock(StreamDataSource.class);
    when(streamDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);
    when(targetObject.getName()).thenReturn("Name");
    when(targetObject.getDataSource()).thenReturn(streamDataSource);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(
            monitor, consumerSettings, new StreamEntityMapping(inputFile2), targetObject);
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), null);
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            null, 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent2, source);

    DatabaseMappingAttribute attribute2 = new DatabaseMappingAttribute(attribute, parent);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () -> DatabaseTransferUtils.generateTargetAttributeDDL(dataSource, attribute2));
    verify(dbpDataSourceContainer).getPreferenceStore();
    verify(targetObject).getName();
    verify(dataSource).getContainer();
    verify(targetObject, atLeast(1)).getDataSource();
    verify(streamDataSource).getSQLDialect();
  }

  /**
   * Test {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}.
   *
   * <ul>
   *   <li>Given {@code Target Name}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction DatabaseTransferUtils.generateTargetAttributeDDL(DBPDataSource, DatabaseMappingAttribute)"
  })
  public void testGenerateTargetAttributeDDL_givenTargetName() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new NumberFormatException());

    StreamDataSource dataSource = mock(StreamDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile2);

    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);
    when(targetObject.getName()).thenReturn("Name");
    when(targetObject.getDataSource()).thenReturn(new StreamDataSource("Input Name"));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile3 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DatabaseMappingContainer databaseMappingContainer =
        new DatabaseMappingContainer(
            monitor, consumerSettings, new StreamEntityMapping(inputFile3), targetObject);

    DatabaseMappingAttribute attribute = mock(DatabaseMappingAttribute.class);
    when(attribute.getTargetName()).thenReturn("Target Name");
    when(attribute.getParent()).thenReturn(databaseMappingContainer);
    Path inputFile4 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile4);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () -> DatabaseTransferUtils.generateTargetAttributeDDL(dataSource, attribute));
    verify(dbpDataSourceContainer).getPreferenceStore();
    verify(targetObject).getName();
    verify(dataSource).getContainer();
    verify(targetObject, atLeast(1)).getDataSource();
    verify(attribute).getParent();
    verify(attribute).getTargetName();
  }

  /**
   * Test {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLDialect#getQuotedIdentifier(String, boolean, boolean)}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction DatabaseTransferUtils.generateTargetAttributeDDL(DBPDataSource, DatabaseMappingAttribute)"
  })
  public void testGenerateTargetAttributeDDL_thenCallsGetQuotedIdentifier() throws DBException {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new NumberFormatException());

    StreamDataSource dataSource = mock(StreamDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    StreamDataSource streamDataSource = mock(StreamDataSource.class);
    when(streamDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBSDataManipulator targetObject = mock(DBSDataManipulator.class);
    when(targetObject.getName()).thenReturn("Name");
    when(targetObject.getDataSource()).thenReturn(streamDataSource);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DatabaseMappingContainer parent =
        new DatabaseMappingContainer(
            monitor, consumerSettings, new StreamEntityMapping(inputFile2), targetObject);
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), null);
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            null, 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent2, source);

    DatabaseMappingAttribute attribute2 = new DatabaseMappingAttribute(attribute, parent);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () -> DatabaseTransferUtils.generateTargetAttributeDDL(dataSource, attribute2));
    verify(dbpDataSourceContainer).getPreferenceStore();
    verify(targetObject).getName();
    verify(dataSource).getContainer();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(targetObject, atLeast(1)).getDataSource();
    verify(streamDataSource).getSQLDialect();
  }

  /**
   * Test {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}.
   *
   * <ul>
   *   <li>Then calls {@link DatabaseMappingContainer#getTarget()}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#generateTargetAttributeDDL(DBPDataSource,
   * DatabaseMappingAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction DatabaseTransferUtils.generateTargetAttributeDDL(DBPDataSource, DatabaseMappingAttribute)"
  })
  public void testGenerateTargetAttributeDDL_thenCallsGetTarget() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getPreferenceStore()).thenThrow(new NumberFormatException());

    StreamDataSource dataSource = mock(StreamDataSource.class);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile);
    Path inputFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    new StreamEntityMapping(inputFile2);

    DBSDataManipulator dbsDataManipulator = mock(DBSDataManipulator.class);
    when(dbsDataManipulator.getName()).thenReturn("Name");
    when(dbsDataManipulator.getDataSource()).thenReturn(new StreamDataSource("Input Name"));

    DatabaseMappingContainer parent = mock(DatabaseMappingContainer.class);
    when(parent.getTarget()).thenReturn(dbsDataManipulator);
    DatabaseMappingContainer parent2 =
        new DatabaseMappingContainer(new DatabaseConsumerSettings(), null);
    StreamDataImporterColumnInfo source =
        new StreamDataImporterColumnInfo(
            null, 1, "Column Name", "Type Name", 3, DBPDataKind.BOOLEAN);

    DatabaseMappingAttribute attribute = new DatabaseMappingAttribute(parent2, source);

    DatabaseMappingAttribute attribute2 = new DatabaseMappingAttribute(attribute, parent);

    // Act and Assert
    assertThrows(
        NumberFormatException.class,
        () -> DatabaseTransferUtils.generateTargetAttributeDDL(dataSource, attribute2));
    verify(dbpDataSourceContainer).getPreferenceStore();
    verify(dbsDataManipulator).getName();
    verify(dataSource).getContainer();
    verify(dbsDataManipulator, atLeast(1)).getDataSource();
    verify(parent).getTarget();
  }

  /**
   * Test {@link DatabaseTransferUtils#executeDDL(DBCSession, DBEPersistAction[])}.
   *
   * <ul>
   *   <li>Given {@link StreamDataSource#StreamDataSource(String)} with {@code Input Name}.
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#executeDDL(DBCSession, DBEPersistAction[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferUtils.executeDDL(DBCSession, DBEPersistAction[])"})
  public void testExecuteDDL_givenStreamDataSourceWithInputName_thenThrowDBCException()
      throws DBCException {
    // Arrange
    StreamTransferSession session = mock(StreamTransferSession.class);
    when(session.getDataSource()).thenReturn(new StreamDataSource("Input Name"));

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            DatabaseTransferUtils.executeDDL(
                session, new DBEPersistAction[] {new SQLDatabasePersistAction("Script")}));
    verify(session).getDataSource();
  }

  /**
   * Test {@link DatabaseTransferUtils#executeDDL(DBCSession, DBEPersistAction[])}.
   *
   * <ul>
   *   <li>When empty array of {@link DBEPersistAction}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#executeDDL(DBCSession, DBEPersistAction[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DatabaseTransferUtils.executeDDL(DBCSession, DBEPersistAction[])"})
  public void testExecuteDDL_whenEmptyArrayOfDBEPersistAction_thenDoesNotThrow()
      throws DBCException {
    // Arrange, Act and Assert
    DatabaseTransferUtils.executeDDL(null, new DBEPersistAction[] {});
  }

  /**
   * Test {@link DatabaseTransferUtils#createTargetDynamicTable(DBRProgressMonitor,
   * DBCExecutionContext, DBSObjectContainer, DatabaseMappingContainer, boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link DBCException}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#createTargetDynamicTable(DBRProgressMonitor,
   * DBCExecutionContext, DBSObjectContainer, DatabaseMappingContainer, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DatabaseTransferUtils.createTargetDynamicTable(DBRProgressMonitor, DBCExecutionContext, DBSObjectContainer, DatabaseMappingContainer, boolean)"
  })
  public void testCreateTargetDynamicTable_thenThrowDBCException() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    StreamExecutionContext executionContext = mock(StreamExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(new StreamDataSource("Input Name"));
    StreamDataSource schema = new StreamDataSource("Input Name");
    DatabaseConsumerSettings consumerSettings = new DatabaseConsumerSettings();
    Path inputFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    DatabaseMappingContainer containerMapping =
        new DatabaseMappingContainer(consumerSettings, new StreamEntityMapping(inputFile));

    // Act and Assert
    assertThrows(
        DBCException.class,
        () ->
            DatabaseTransferUtils.createTargetDynamicTable(
                monitor, executionContext, schema, containerMapping, true));
    verify(executionContext).getDataSource();
  }

  /**
   * Test {@link DatabaseTransferUtils#getDataType(String)}.
   *
   * <ul>
   *   <li>When {@code -1}.
   *   <li>Then return Second is {@code INTEGER}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#getDataType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Pair DatabaseTransferUtils.getDataType(String)"})
  public void testGetDataType_when1_thenReturnSecondIsInteger() {
    // Arrange and Act
    Pair<DBPDataKind, String> actualDataType = DatabaseTransferUtils.getDataType("-1");

    // Assert
    assertEquals("INTEGER", actualDataType.getSecond());
    assertEquals(DBPDataKind.NUMERIC, actualDataType.getFirst());
  }

  /**
   * Test {@link DatabaseTransferUtils#getDataType(String)}.
   *
   * <ul>
   *   <li>When {@code 42Value}.
   *   <li>Then return Second is {@code NVARCHAR}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#getDataType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Pair DatabaseTransferUtils.getDataType(String)"})
  public void testGetDataType_when42Value_thenReturnSecondIsNvarchar() {
    // Arrange and Act
    Pair<DBPDataKind, String> actualDataType = DatabaseTransferUtils.getDataType("42Value");

    // Assert
    assertEquals("NVARCHAR", actualDataType.getSecond());
    assertEquals(DBPDataKind.STRING, actualDataType.getFirst());
  }

  /**
   * Test {@link DatabaseTransferUtils#getDataType(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return Second is {@code INTEGER}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#getDataType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Pair DatabaseTransferUtils.getDataType(String)"})
  public void testGetDataType_when42_thenReturnSecondIsInteger() {
    // Arrange and Act
    Pair<DBPDataKind, String> actualDataType = DatabaseTransferUtils.getDataType("42");

    // Assert
    assertEquals("INTEGER", actualDataType.getSecond());
    assertEquals(DBPDataKind.NUMERIC, actualDataType.getFirst());
  }

  /**
   * Test {@link DatabaseTransferUtils#getDataType(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Second is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#getDataType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Pair DatabaseTransferUtils.getDataType(String)"})
  public void testGetDataType_whenEmptyString_thenReturnSecondIsNull() {
    // Arrange and Act
    Pair<DBPDataKind, String> actualDataType = DatabaseTransferUtils.getDataType("");

    // Assert
    assertNull(actualDataType.getSecond());
    assertEquals(DBPDataKind.UNKNOWN, actualDataType.getFirst());
  }

  /**
   * Test {@link DatabaseTransferUtils#getDataType(String)}.
   *
   * <ul>
   *   <li>When {@link Boolean#FALSE} toString.
   *   <li>Then return Second is {@code BOOLEAN}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#getDataType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Pair DatabaseTransferUtils.getDataType(String)"})
  public void testGetDataType_whenFalseToString_thenReturnSecondIsBoolean() {
    // Arrange and Act
    Pair<DBPDataKind, String> actualDataType =
        DatabaseTransferUtils.getDataType(Boolean.FALSE.toString());

    // Assert
    assertEquals("BOOLEAN", actualDataType.getSecond());
    assertEquals(DBPDataKind.BOOLEAN, actualDataType.getFirst());
  }

  /**
   * Test {@link DatabaseTransferUtils#getDataType(String)}.
   *
   * <ul>
   *   <li>When {@code (}.
   *   <li>Then return Second is {@code NVARCHAR}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#getDataType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Pair DatabaseTransferUtils.getDataType(String)"})
  public void testGetDataType_whenLeftParenthesis_thenReturnSecondIsNvarchar() {
    // Arrange and Act
    Pair<DBPDataKind, String> actualDataType = DatabaseTransferUtils.getDataType("(");

    // Assert
    assertEquals("NVARCHAR", actualDataType.getSecond());
    assertEquals(DBPDataKind.STRING, actualDataType.getFirst());
  }

  /**
   * Test {@link DatabaseTransferUtils#getDataType(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Second is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#getDataType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Pair DatabaseTransferUtils.getDataType(String)"})
  public void testGetDataType_whenNull_thenReturnSecondIsNull() {
    // Arrange and Act
    Pair<DBPDataKind, String> actualDataType = DatabaseTransferUtils.getDataType(null);

    // Assert
    assertNull(actualDataType.getSecond());
    assertEquals(DBPDataKind.UNKNOWN, actualDataType.getFirst());
  }

  /**
   * Test {@link DatabaseTransferUtils#getDataType(String)}.
   *
   * <ul>
   *   <li>When {@link Boolean#TRUE} toString.
   *   <li>Then return Second is {@code BOOLEAN}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#getDataType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Pair DatabaseTransferUtils.getDataType(String)"})
  public void testGetDataType_whenTrueToString_thenReturnSecondIsBoolean() {
    // Arrange and Act
    Pair<DBPDataKind, String> actualDataType =
        DatabaseTransferUtils.getDataType(Boolean.TRUE.toString());

    // Assert
    assertEquals("BOOLEAN", actualDataType.getSecond());
    assertEquals(DBPDataKind.BOOLEAN, actualDataType.getFirst());
  }

  /**
   * Test {@link DatabaseTransferUtils#getDataType(String)}.
   *
   * <ul>
   *   <li>When {@code Value}.
   *   <li>Then return Second is {@code VARCHAR}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseTransferUtils#getDataType(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Pair DatabaseTransferUtils.getDataType(String)"})
  public void testGetDataType_whenValue_thenReturnSecondIsVarchar() {
    // Arrange and Act
    Pair<DBPDataKind, String> actualDataType = DatabaseTransferUtils.getDataType("Value");

    // Assert
    assertEquals("VARCHAR", actualDataType.getSecond());
    assertEquals(DBPDataKind.STRING, actualDataType.getFirst());
  }

  /**
   * Test TargetCommandContext {@link
   * TargetCommandContext#TargetCommandContext(DBCExecutionContext)}.
   *
   * <p>Method under test: {@link TargetCommandContext#TargetCommandContext(DBCExecutionContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TargetCommandContext.<init>(DBCExecutionContext)"})
  public void testTargetCommandContextNewTargetCommandContext() {
    // Arrange and Act
    TargetCommandContext actualTargetCommandContext = new TargetCommandContext(null);

    // Assert
    assertNull(actualTargetCommandContext.getExecutionContext());
    assertTrue(actualTargetCommandContext.getUserParams().isEmpty());
  }
}
