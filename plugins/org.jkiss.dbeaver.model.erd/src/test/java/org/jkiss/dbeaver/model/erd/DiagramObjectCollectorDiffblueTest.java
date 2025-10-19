package org.jkiss.dbeaver.model.erd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPNamedObject;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DiagramObjectCollectorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DiagramObjectCollector#DiagramObjectCollector(ERDDiagram)}
   *   <li>{@link DiagramObjectCollector#setShowViews(boolean)}
   *   <li>{@link DiagramObjectCollector#getDiagramEntities()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DiagramObjectCollector.<init>(ERDDiagram)",
    "List DiagramObjectCollector.getDiagramEntities()",
    "void DiagramObjectCollector.setShowViews(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    // Act
    DiagramObjectCollector actualDiagramObjectCollector = new DiagramObjectCollector(diagram);
    actualDiagramObjectCollector.setShowViews(true);

    // Assert
    assertTrue(actualDiagramObjectCollector.getDiagramEntities().isEmpty());
  }

  /**
   * Test {@link DiagramObjectCollector#collectTables(DBRProgressMonitor, Collection,
   * DiagramCollectSettings, boolean)} with {@code monitor}, {@code roots}, {@code settings}, {@code
   * forceShowViews}.
   *
   * <ul>
   *   <li>Then return {@link Set}.
   * </ul>
   *
   * <p>Method under test: {@link DiagramObjectCollector#collectTables(DBRProgressMonitor,
   * Collection, DiagramCollectSettings, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection DiagramObjectCollector.collectTables(DBRProgressMonitor, Collection, DiagramCollectSettings, boolean)"
  })
  public void testCollectTablesWithMonitorRootsSettingsForceShowViews_thenReturnSet()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ArrayList<DBSObject> roots = new ArrayList<>();

    DiagramCollectSettings settings = mock(DiagramCollectSettings.class);
    when(settings.isShowPartitions()).thenReturn(true);
    when(settings.isShowViews()).thenReturn(true);

    // Act
    Collection<DBSEntity> actualCollectTablesResult =
        DiagramObjectCollector.collectTables(monitor, roots, settings, true);

    // Assert
    verify(settings).isShowPartitions();
    verify(settings).isShowViews();
    assertTrue(actualCollectTablesResult instanceof Set);
    assertTrue(actualCollectTablesResult.isEmpty());
  }

  /**
   * Test {@link DiagramObjectCollector#generateDiagramObjects(DBRProgressMonitor, Collection,
   * DiagramCollectSettings)}.
   *
   * <ul>
   *   <li>Then calls {@link DiagramCollectSettings#isShowPartitions()}.
   * </ul>
   *
   * <p>Method under test: {@link DiagramObjectCollector#generateDiagramObjects(DBRProgressMonitor,
   * Collection, DiagramCollectSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DiagramObjectCollector.generateDiagramObjects(DBRProgressMonitor, Collection, DiagramCollectSettings)"
  })
  public void testGenerateDiagramObjects_thenCallsIsShowPartitions() throws DBException {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    DiagramObjectCollector diagramObjectCollector = new DiagramObjectCollector(diagram);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ArrayList<DBSObject> roots = new ArrayList<>();

    DiagramCollectSettings settings = mock(DiagramCollectSettings.class);
    when(settings.isShowPartitions()).thenReturn(false);
    when(settings.isShowViews()).thenReturn(false);

    // Act
    diagramObjectCollector.generateDiagramObjects(monitor, roots, settings);

    // Assert
    verify(settings).isShowPartitions();
    verify(settings).isShowViews();
  }

  /**
   * Test {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor, ERDDiagram,
   * DBPProject, Collection, DiagramCollectSettings, boolean)}.
   *
   * <p>Method under test: {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor,
   * ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DiagramObjectCollector.generateEntityList(DBRProgressMonitor, ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)"
  })
  public void testGenerateEntityList() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    DBPProject diagramProject = mock(DBPProject.class);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.isConnected()).thenThrow(new RuntimeException());

    LinkedHashSet<DBPNamedObject> objects = new LinkedHashSet<>();
    objects.add(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            DiagramObjectCollector.generateEntityList(
                monitor,
                diagram,
                diagramProject,
                objects,
                mock(DiagramCollectSettings.class),
                true));
    verify(dbpDataSourceContainer).isConnected();
  }

  /**
   * Test {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor, ERDDiagram,
   * DBPProject, Collection, DiagramCollectSettings, boolean)}.
   *
   * <p>Method under test: {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor,
   * ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DiagramObjectCollector.generateEntityList(DBRProgressMonitor, ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)"
  })
  public void testGenerateEntityList2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    DBPProject diagramProject = mock(DBPProject.class);
    when(diagramProject.getName()).thenReturn("Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(mock(DBPProject.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.isConnected()).thenReturn(false);
    when(dbpDataSourceContainer2.connect(
            Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(true);
    when(dbpDataSourceContainer2.getName()).thenReturn("Name");
    when(dbpDataSourceContainer2.getDataSource()).thenReturn(dbpDataSource);

    LinkedHashSet<DBPNamedObject> objects = new LinkedHashSet<>();
    objects.add(dbpDataSourceContainer2);

    // Act
    List<ERDEntity> actualGenerateEntityListResult =
        DiagramObjectCollector.generateEntityList(
            monitor, diagram, diagramProject, objects, mock(DiagramCollectSettings.class), true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceContainer2).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(dbpDataSourceContainer2, atLeast(1)).getDataSource();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpDataSourceContainer2).isConnected();
    verify(dbpDataSourceContainer2, atLeast(1)).getName();
    verify(diagramProject).getName();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    assertEquals(1, diagram.getErrorMessages().size());
    assertTrue(actualGenerateEntityListResult.isEmpty());
  }

  /**
   * Test {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor, ERDDiagram,
   * DBPProject, Collection, DiagramCollectSettings, boolean)}.
   *
   * <p>Method under test: {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor,
   * ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DiagramObjectCollector.generateEntityList(DBRProgressMonitor, ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)"
  })
  public void testGenerateEntityList3() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    DBPProject diagramProject = mock(DBPProject.class);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenThrow(new RuntimeException());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.isConnected()).thenReturn(false);
    when(dbpDataSourceContainer2.connect(
            Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(true);
    when(dbpDataSourceContainer2.getName()).thenReturn("Name");
    when(dbpDataSourceContainer2.getDataSource()).thenReturn(dbpDataSource);

    LinkedHashSet<DBPNamedObject> objects = new LinkedHashSet<>();
    objects.add(dbpDataSourceContainer2);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            DiagramObjectCollector.generateEntityList(
                monitor,
                diagram,
                diagramProject,
                objects,
                mock(DiagramCollectSettings.class),
                true));
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer2).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(dbpDataSourceContainer2).getDataSource();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpDataSourceContainer2).isConnected();
    verify(dbpDataSourceContainer2).getName();
  }

  /**
   * Test {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor, ERDDiagram,
   * DBPProject, Collection, DiagramCollectSettings, boolean)}.
   *
   * <p>Method under test: {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor,
   * ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DiagramObjectCollector.generateEntityList(DBRProgressMonitor, ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)"
  })
  public void testGenerateEntityList4() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    DBPProject diagramProject = mock(DBPProject.class);

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getContainer()).thenThrow(new RuntimeException());
    ERDEntity entity2 = new ERDEntity(dataSource);
    ERDEntity entity3 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(
            entity3,
            "Progress sub task without start",
            "The characteristics of someone or something");

    ERDLogicalAssociation erdLogicalAssociation =
        new ERDLogicalAssociation(
            entity2, "Name", "The characteristics of someone or something", pk);

    LinkedHashSet<DBPNamedObject> objects = new LinkedHashSet<>();
    objects.add(erdLogicalAssociation);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            DiagramObjectCollector.generateEntityList(
                monitor,
                diagram,
                diagramProject,
                objects,
                mock(DiagramCollectSettings.class),
                true));
    verify(dataSource).getContainer();
  }

  /**
   * Test {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor, ERDDiagram,
   * DBPProject, Collection, DiagramCollectSettings, boolean)}.
   *
   * <p>Method under test: {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor,
   * ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DiagramObjectCollector.generateEntityList(DBRProgressMonitor, ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)"
  })
  public void testGenerateEntityList5() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    DBPProject diagramProject = mock(DBPProject.class);
    when(diagramProject.getName()).thenReturn("Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(mock(DBPProject.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dataSource = mock(DBPDataSource.class);
    when(dataSource.getSQLDialect()).thenReturn(sqlDialect);
    when(dataSource.getContainer()).thenReturn(dbpDataSourceContainer);
    ERDEntity entity2 = new ERDEntity(dataSource);
    ERDEntity entity3 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(
            entity3,
            "Progress sub task without start",
            "The characteristics of someone or something");

    ERDLogicalAssociation erdLogicalAssociation =
        new ERDLogicalAssociation(
            entity2, "Name", "The characteristics of someone or something", pk);

    LinkedHashSet<DBPNamedObject> objects = new LinkedHashSet<>();
    objects.add(erdLogicalAssociation);

    // Act
    List<ERDEntity> actualGenerateEntityListResult =
        DiagramObjectCollector.generateEntityList(
            monitor, diagram, diagramProject, objects, mock(DiagramCollectSettings.class), true);

    // Assert
    verify(dataSource).getContainer();
    verify(dataSource).getSQLDialect();
    verify(dbpDataSourceContainer).getProject();
    verify(diagramProject).getName();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    assertEquals(1, diagram.getErrorMessages().size());
    assertTrue(actualGenerateEntityListResult.isEmpty());
  }

  /**
   * Test {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor, ERDDiagram,
   * DBPProject, Collection, DiagramCollectSettings, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#isConnected()} return
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor,
   * ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DiagramObjectCollector.generateEntityList(DBRProgressMonitor, ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)"
  })
  public void testGenerateEntityList_givenDBPDataSourceContainerIsConnectedReturnTrue() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    DBPProject diagramProject = mock(DBPProject.class);
    when(diagramProject.getName()).thenReturn("Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(mock(DBPProject.class));

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer2.isConnected()).thenReturn(true);
    when(dbpDataSourceContainer2.getName()).thenReturn("Name");
    when(dbpDataSourceContainer2.getDataSource()).thenReturn(dbpDataSource);

    LinkedHashSet<DBPNamedObject> objects = new LinkedHashSet<>();
    objects.add(dbpDataSourceContainer2);

    // Act
    List<ERDEntity> actualGenerateEntityListResult =
        DiagramObjectCollector.generateEntityList(
            monitor, diagram, diagramProject, objects, mock(DiagramCollectSettings.class), true);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceContainer2, atLeast(1)).getDataSource();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpDataSourceContainer2).isConnected();
    verify(dbpDataSourceContainer2).getName();
    verify(diagramProject).getName();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    assertEquals(1, diagram.getErrorMessages().size());
    assertTrue(actualGenerateEntityListResult.isEmpty());
  }

  /**
   * Test {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor, ERDDiagram,
   * DBPProject, Collection, DiagramCollectSettings, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getContainer()} throw {@link
   *       RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor,
   * ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DiagramObjectCollector.generateEntityList(DBRProgressMonitor, ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)"
  })
  public void testGenerateEntityList_givenDBPDataSourceGetContainerThrowRuntimeException()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    DBPProject diagramProject = mock(DBPProject.class);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenThrow(new RuntimeException());

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.isConnected()).thenReturn(false);
    when(dbpDataSourceContainer.connect(
            Mockito.<DBRProgressMonitor>any(), anyBoolean(), anyBoolean()))
        .thenReturn(true);
    when(dbpDataSourceContainer.getName()).thenReturn("Name");
    when(dbpDataSourceContainer.getDataSource()).thenReturn(dbpDataSource);

    LinkedHashSet<DBPNamedObject> objects = new LinkedHashSet<>();
    objects.add(dbpDataSourceContainer);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            DiagramObjectCollector.generateEntityList(
                monitor,
                diagram,
                diagramProject,
                objects,
                mock(DiagramCollectSettings.class),
                true));
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).connect(isA(DBRProgressMonitor.class), eq(true), eq(true));
    verify(dbpDataSourceContainer).getDataSource();
    verify(dbpDataSourceContainer).isConnected();
    verify(dbpDataSourceContainer).getName();
  }

  /**
   * Test {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor, ERDDiagram,
   * DBPProject, Collection, DiagramCollectSettings, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBPNamedObject}.
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@link DBPNamedObject}.
   * </ul>
   *
   * <p>Method under test: {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor,
   * ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DiagramObjectCollector.generateEntityList(DBRProgressMonitor, ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)"
  })
  public void testGenerateEntityList_givenDBPNamedObject_whenLinkedHashSetAddDBPNamedObject() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    DBPProject diagramProject = mock(DBPProject.class);

    LinkedHashSet<DBPNamedObject> objects = new LinkedHashSet<>();
    objects.add(mock(DBPNamedObject.class));

    // Act
    List<ERDEntity> actualGenerateEntityListResult =
        DiagramObjectCollector.generateEntityList(
            monitor, diagram, diagramProject, objects, mock(DiagramCollectSettings.class), true);

    // Assert
    assertTrue(diagram.getErrorMessages().isEmpty());
    assertTrue(actualGenerateEntityListResult.isEmpty());
  }

  /**
   * Test {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor, ERDDiagram,
   * DBPProject, Collection, DiagramCollectSettings, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer} {@link DBVContainer#getDataSource()} throw {@link
   *       RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor,
   * ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DiagramObjectCollector.generateEntityList(DBRProgressMonitor, ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)"
  })
  public void testGenerateEntityList_givenDBVContainerGetDataSourceThrowRuntimeException() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    DBPProject diagramProject = mock(DBPProject.class);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenThrow(new RuntimeException());
    DBVContainer container2 = new DBVContainer(parent, "Progress sub task without start");
    DBVEntity entity2 =
        new DBVEntity(
            container2, "Progress sub task without start", "Progress sub task without start");
    ERDEntity entity3 = new ERDEntity(entity2);
    ERDEntity entity4 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(
            entity4,
            "Progress sub task without start",
            "The characteristics of someone or something");

    ERDLogicalAssociation erdLogicalAssociation =
        new ERDLogicalAssociation(
            entity3, "Name", "The characteristics of someone or something", pk);

    LinkedHashSet<DBPNamedObject> objects = new LinkedHashSet<>();
    objects.add(erdLogicalAssociation);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            DiagramObjectCollector.generateEntityList(
                monitor,
                diagram,
                diagramProject,
                objects,
                mock(DiagramCollectSettings.class),
                true));
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor, ERDDiagram,
   * DBPProject, Collection, DiagramCollectSettings, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor,
   * ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DiagramObjectCollector.generateEntityList(DBRProgressMonitor, ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)"
  })
  public void testGenerateEntityList_thenCallsGetDataSource() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    DBPProject diagramProject = mock(DBPProject.class);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenThrow(new RuntimeException());

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container2 = new DBVContainer(parent, "Progress sub task without start");
    DBVEntity entity2 =
        new DBVEntity(
            container2, "Progress sub task without start", "Progress sub task without start");
    ERDEntity entity3 = new ERDEntity(entity2);
    ERDEntity entity4 = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey pk =
        new ERDLogicalPrimaryKey(
            entity4,
            "Progress sub task without start",
            "The characteristics of someone or something");

    ERDLogicalAssociation erdLogicalAssociation =
        new ERDLogicalAssociation(
            entity3, "Name", "The characteristics of someone or something", pk);

    LinkedHashSet<DBPNamedObject> objects = new LinkedHashSet<>();
    objects.add(erdLogicalAssociation);

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            DiagramObjectCollector.generateEntityList(
                monitor,
                diagram,
                diagramProject,
                objects,
                mock(DiagramCollectSettings.class),
                true));
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor, ERDDiagram,
   * DBPProject, Collection, DiagramCollectSettings, boolean)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link DiagramObjectCollector#generateEntityList(DBRProgressMonitor,
   * ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List DiagramObjectCollector.generateEntityList(DBRProgressMonitor, ERDDiagram, DBPProject, Collection, DiagramCollectSettings, boolean)"
  })
  public void testGenerateEntityList_whenArrayList() {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram diagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    DBPProject diagramProject = mock(DBPProject.class);

    // Act
    List<ERDEntity> actualGenerateEntityListResult =
        DiagramObjectCollector.generateEntityList(
            monitor,
            diagram,
            diagramProject,
            new ArrayList<>(),
            mock(DiagramCollectSettings.class),
            true);

    // Assert
    assertTrue(diagram.getErrorMessages().isEmpty());
    assertTrue(actualGenerateEntityListResult.isEmpty());
  }
}
