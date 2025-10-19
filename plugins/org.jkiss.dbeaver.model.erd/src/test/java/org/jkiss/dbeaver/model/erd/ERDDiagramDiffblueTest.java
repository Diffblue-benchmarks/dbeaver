package org.jkiss.dbeaver.model.erd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.navigator.DBNModel;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSObjectContainer;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ERDDiagramDiffblueTest {
  /**
   * Test {@link ERDDiagram#getMonitor()}.
   *
   * <ul>
   *   <li>Then return not ForceCacheUsage.
   * </ul>
   *
   * <p>Method under test: {@link ERDDiagram#getMonitor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBRProgressMonitor ERDDiagram.getMonitor()"})
  public void testGetMonitor_thenReturnNotForceCacheUsage() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    // Act and Assert
    assertFalse(erdDiagram.getMonitor().isForceCacheUsage());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ERDDiagram#setDiagramMonitor(DBRProgressMonitor)}
   *   <li>{@link ERDDiagram#setLayoutManualAllowed(boolean)}
   *   <li>{@link ERDDiagram#setLayoutManualDesired(boolean)}
   *   <li>{@link ERDDiagram#setName(String)}
   *   <li>{@link ERDDiagram#setRootObjectContainer(DBSObjectContainer)}
   *   <li>{@link ERDDiagram#disableDiagramMonitor()}
   *   <li>{@link ERDDiagram#setNeedsAutoLayout(boolean)}
   *   <li>{@link ERDDiagram#getContentProvider()}
   *   <li>{@link ERDDiagram#getEntities()}
   *   <li>{@link ERDDiagram#getEntityMap()}
   *   <li>{@link ERDDiagram#getErrorMessages()}
   *   <li>{@link ERDDiagram#getName()}
   *   <li>{@link ERDDiagram#getNotes()}
   *   <li>{@link ERDDiagram#getRootObjectContainer()}
   *   <li>{@link ERDDiagram#isEditEnabled()}
   *   <li>{@link ERDDiagram#isLayoutManualAllowed()}
   *   <li>{@link ERDDiagram#isLayoutManualDesired()}
   *   <li>{@link ERDDiagram#isNeedsAutoLayout()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ERDDiagram.disableDiagramMonitor()",
    "ERDContentProvider ERDDiagram.getContentProvider()",
    "List ERDDiagram.getEntities()",
    "Map ERDDiagram.getEntityMap()",
    "List ERDDiagram.getErrorMessages()",
    "String ERDDiagram.getName()",
    "List ERDDiagram.getNotes()",
    "DBSObjectContainer ERDDiagram.getRootObjectContainer()",
    "boolean ERDDiagram.isEditEnabled()",
    "boolean ERDDiagram.isLayoutManualAllowed()",
    "boolean ERDDiagram.isLayoutManualDesired()",
    "boolean ERDDiagram.isNeedsAutoLayout()",
    "void ERDDiagram.setDiagramMonitor(DBRProgressMonitor)",
    "void ERDDiagram.setLayoutManualAllowed(boolean)",
    "void ERDDiagram.setLayoutManualDesired(boolean)",
    "void ERDDiagram.setName(String)",
    "void ERDDiagram.setNeedsAutoLayout(boolean)",
    "void ERDDiagram.setRootObjectContainer(DBSObjectContainer)"
  })
  public void testGettersAndSetters() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDContentProviderDefault contentProvider = new ERDContentProviderDefault();

    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", contentProvider);

    // Act
    erdDiagram.setDiagramMonitor(new LoggingProgressMonitor());
    erdDiagram.setLayoutManualAllowed(true);
    erdDiagram.setLayoutManualDesired(true);
    erdDiagram.setName("Name");
    DBVModel rootObjectContainer = new DBVModel(mock(DBPDataSourceContainer.class));
    erdDiagram.setRootObjectContainer(rootObjectContainer);
    erdDiagram.disableDiagramMonitor();
    erdDiagram.setNeedsAutoLayout(true);
    ERDContentProvider actualContentProvider = erdDiagram.getContentProvider();
    List<ERDEntity> actualEntities = erdDiagram.getEntities();
    Map<DBSEntity, ERDEntity> actualEntityMap = erdDiagram.getEntityMap();
    List<String> actualErrorMessages = erdDiagram.getErrorMessages();
    String actualName = erdDiagram.getName();
    List<ERDNote> actualNotes = erdDiagram.getNotes();
    DBSObjectContainer actualRootObjectContainer = erdDiagram.getRootObjectContainer();
    boolean actualIsEditEnabledResult = erdDiagram.isEditEnabled();
    boolean actualIsLayoutManualAllowedResult = erdDiagram.isLayoutManualAllowed();
    boolean actualIsLayoutManualDesiredResult = erdDiagram.isLayoutManualDesired();
    boolean actualIsNeedsAutoLayoutResult = erdDiagram.isNeedsAutoLayout();

    // Assert
    assertTrue(actualContentProvider instanceof ERDContentProviderDefault);
    assertEquals("Name", actualName);
    assertFalse(actualIsEditEnabledResult);
    assertTrue(actualEntities.isEmpty());
    assertTrue(actualErrorMessages.isEmpty());
    assertTrue(actualNotes.isEmpty());
    assertTrue(actualEntityMap.isEmpty());
    assertTrue(actualIsLayoutManualAllowedResult);
    assertTrue(actualIsLayoutManualDesiredResult);
    assertTrue(actualIsNeedsAutoLayoutResult);
    assertSame(contentProvider, actualContentProvider);
    assertSame(rootObjectContainer, actualRootObjectContainer);
  }

  /**
   * Test {@link ERDDiagram#ERDDiagram(DBSObject, String, ERDContentProvider)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then DataSources return {@link Set}.
   * </ul>
   *
   * <p>Method under test: {@link ERDDiagram#ERDDiagram(DBSObject, String, ERDContentProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDDiagram.<init>(DBSObject, String, ERDContentProvider)"})
  public void testNewERDDiagram_whenName_thenDataSourcesReturnSet() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDContentProviderDefault contentProvider = new ERDContentProviderDefault();

    // Act
    ERDDiagram actualErdDiagram = new ERDDiagram(container, "Name", contentProvider);

    // Assert
    Collection<DBPDataSourceContainer> dataSources = actualErdDiagram.getDataSources();
    assertTrue(dataSources instanceof Set);
    ERDContentProvider contentProvider2 = actualErdDiagram.getContentProvider();
    assertTrue(contentProvider2 instanceof ERDContentProviderDefault);
    DBSObject object = actualErdDiagram.getObject();
    assertTrue(object instanceof ERDLogicalPrimaryKey);
    assertEquals("Name", actualErdDiagram.getName());
    assertNull(actualErdDiagram.getUserData());
    assertNull(actualErdDiagram.getRootObjectContainer());
    assertEquals(0, actualErdDiagram.getEntityCount());
    assertFalse(actualErdDiagram.isEditEnabled());
    assertFalse(actualErdDiagram.isLayoutManualAllowed());
    assertFalse(actualErdDiagram.isNeedsAutoLayout());
    assertTrue(dataSources.isEmpty());
    assertTrue(actualErdDiagram.getContents().isEmpty());
    assertTrue(actualErdDiagram.getEntities().isEmpty());
    assertTrue(actualErdDiagram.getErrorMessages().isEmpty());
    assertTrue(actualErdDiagram.getNotes().isEmpty());
    assertTrue(actualErdDiagram.getEntityMap().isEmpty());
    assertTrue(actualErdDiagram.isLayoutManualDesired());
    assertSame(contentProvider, contentProvider2);
    assertSame(container, object);
  }

  /**
   * Test {@link ERDDiagram#ERDDiagram(DBSObject, String, ERDContentProvider)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link ERDDiagram#ERDDiagram(DBSObject, String, ERDContentProvider)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDDiagram.<init>(DBSObject, String, ERDContentProvider)"})
  public void testNewERDDiagram_whenNull_thenThrowIllegalArgumentException() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new ERDDiagram(container, null, new ERDContentProviderDefault()));
  }

  /**
   * Test {@link ERDDiagram#getEntityOrder(ERDEntity)}.
   *
   * <p>Method under test: {@link ERDDiagram#getEntityOrder(ERDEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ERDDiagram.getEntityOrder(ERDEntity)"})
  public void testGetEntityOrder() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    // Act
    int actualEntityOrder = erdDiagram.getEntityOrder(new ERDEntity(mock(DBPDataSource.class)));

    // Assert
    assertEquals(-1, actualEntityOrder);
  }

  /**
   * Test {@link ERDDiagram#getEntities(DBPDataSourceContainer)} with {@code dataSourceContainer}.
   *
   * <p>Method under test: {@link ERDDiagram#getEntities(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDDiagram.getEntities(DBPDataSourceContainer)"})
  public void testGetEntitiesWithDataSourceContainer() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    // Act
    List<ERDEntity> actualEntities = erdDiagram.getEntities(mock(DBPDataSourceContainer.class));

    // Assert
    assertTrue(actualEntities.isEmpty());
  }

  /**
   * Test {@link ERDDiagram#getEntities(DBSEntity)} with {@code table}.
   *
   * <p>Method under test: {@link ERDDiagram#getEntities(DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDDiagram.getEntities(DBSEntity)"})
  public void testGetEntitiesWithTable() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    List<ERDEntity> actualEntities = erdDiagram.getEntities(table);

    // Assert
    assertTrue(actualEntities.isEmpty());
  }

  /**
   * Test {@link ERDDiagram#addNote(ERDNote, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ERDDiagram#addNote(ERDNote, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDDiagram.addNote(ERDNote, boolean)"})
  public void testAddNote_whenFalse() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ERDNote note = new ERDNote("Text");

    // Act
    erdDiagram.addNote(note, false);

    // Assert
    List<ERDObject<?>> contents = erdDiagram.getContents();
    assertEquals(1, contents.size());
    assertEquals(contents, erdDiagram.getNotes());
    assertSame(note, contents.get(0));
  }

  /**
   * Test {@link ERDDiagram#addNote(ERDNote, boolean)}.
   *
   * <ul>
   *   <li>When {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ERDDiagram#addNote(ERDNote, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDDiagram.addNote(ERDNote, boolean)"})
  public void testAddNote_whenTrue() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    ERDNote note = new ERDNote("Text");

    // Act
    erdDiagram.addNote(note, true);

    // Assert
    List<ERDObject<?>> contents = erdDiagram.getContents();
    assertEquals(1, contents.size());
    assertEquals(contents, erdDiagram.getNotes());
    assertSame(note, contents.get(0));
  }

  /**
   * Test {@link ERDDiagram#getEntityCount()}.
   *
   * <p>Method under test: {@link ERDDiagram#getEntityCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ERDDiagram.getEntityCount()"})
  public void testGetEntityCount() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    // Act and Assert
    assertEquals(0, erdDiagram.getEntityCount());
  }

  /**
   * Test {@link ERDDiagram#copy()}.
   *
   * <p>Method under test: {@link ERDDiagram#copy()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ERDDiagram ERDDiagram.copy()"})
  public void testCopy() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDContentProviderDefault contentProvider = new ERDContentProviderDefault();

    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", contentProvider);

    // Act
    ERDDiagram actualCopyResult = erdDiagram.copy();

    // Assert
    Collection<DBPDataSourceContainer> dataSources = actualCopyResult.getDataSources();
    assertTrue(dataSources instanceof Set);
    ERDContentProvider contentProvider2 = actualCopyResult.getContentProvider();
    assertTrue(contentProvider2 instanceof ERDContentProviderDefault);
    DBSObject object = actualCopyResult.getObject();
    assertTrue(object instanceof ERDLogicalPrimaryKey);
    assertEquals("Name", actualCopyResult.getName());
    assertNull(actualCopyResult.getUserData());
    assertNull(actualCopyResult.getRootObjectContainer());
    assertEquals(0, actualCopyResult.getEntityCount());
    assertFalse(actualCopyResult.isEditEnabled());
    assertFalse(actualCopyResult.isLayoutManualAllowed());
    assertFalse(actualCopyResult.isNeedsAutoLayout());
    assertTrue(dataSources.isEmpty());
    assertTrue(actualCopyResult.getContents().isEmpty());
    assertTrue(actualCopyResult.getEntities().isEmpty());
    assertTrue(actualCopyResult.getErrorMessages().isEmpty());
    assertTrue(actualCopyResult.getNotes().isEmpty());
    assertTrue(actualCopyResult.getEntityMap().isEmpty());
    assertTrue(actualCopyResult.isLayoutManualDesired());
    assertSame(contentProvider, contentProvider2);
    assertSame(container, object);
  }

  /**
   * Test {@link ERDDiagram#containsTable(DBSEntity)}.
   *
   * <p>Method under test: {@link ERDDiagram#containsTable(DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ERDDiagram.containsTable(DBSEntity)"})
  public void testContainsTable() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    boolean actualContainsTableResult = erdDiagram.containsTable(table);

    // Assert
    assertFalse(actualContainsTableResult);
  }

  /**
   * Test {@link ERDDiagram#getEntity(DBSEntity)}.
   *
   * <p>Method under test: {@link ERDDiagram#getEntity(DBSEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ERDEntity ERDDiagram.getEntity(DBSEntity)"})
  public void testGetEntity() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity table = new DBVEntity(container2, "Name", "Description Column Names");

    // Act
    ERDEntity actualEntity = erdDiagram.getEntity(table);

    // Assert
    assertNull(actualEntity);
  }

  /**
   * Test {@link ERDDiagram#getDataSources()}.
   *
   * <p>Method under test: {@link ERDDiagram#getDataSources()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection ERDDiagram.getDataSources()"})
  public void testGetDataSources() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    // Act
    Collection<DBPDataSourceContainer> actualDataSources = erdDiagram.getDataSources();

    // Assert
    assertTrue(actualDataSources instanceof Set);
    assertTrue(actualDataSources.isEmpty());
  }

  /**
   * Test {@link ERDDiagram#getObjectContainers(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link ERDDiagram#getObjectContainers(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection ERDDiagram.getObjectContainers(DBPDataSourceContainer)"})
  public void testGetObjectContainers() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    // Act and Assert
    assertNull(erdDiagram.getObjectContainers(mock(DBPDataSourceContainer.class)));
  }

  /**
   * Test {@link ERDDiagram#getDataSourceIndex(DBPDataSourceContainer)}.
   *
   * <p>Method under test: {@link ERDDiagram#getDataSourceIndex(DBPDataSourceContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ERDDiagram.getDataSourceIndex(DBPDataSourceContainer)"})
  public void testGetDataSourceIndex() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    // Act and Assert
    assertEquals(0, erdDiagram.getDataSourceIndex(mock(DBPDataSourceContainer.class)));
  }

  /**
   * Test {@link ERDDiagram#getContents()}.
   *
   * <p>Method under test: {@link ERDDiagram#getContents()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ERDDiagram.getContents()"})
  public void testGetContents() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    // Act and Assert
    assertTrue(erdDiagram.getContents().isEmpty());
  }

  /**
   * Test {@link ERDDiagram#addErrorMessage(String)}.
   *
   * <p>Method under test: {@link ERDDiagram#addErrorMessage(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ERDDiagram.addErrorMessage(String)"})
  public void testAddErrorMessage() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());

    // Act
    erdDiagram.addErrorMessage("Not all who wander are lost");

    // Assert
    List<String> errorMessages = erdDiagram.getErrorMessages();
    assertEquals(1, errorMessages.size());
    assertEquals("Not all who wander are lost", errorMessages.get(0));
  }

  /**
   * Test {@link ERDDiagram#toMap(ERDContext, boolean)}.
   *
   * <p>Method under test: {@link ERDDiagram#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDDiagram.toMap(ERDContext, boolean)"})
  public void testToMap() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");
    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdDiagram.toMap(context, true);

    // Assert
    assertEquals(3, actualToMapResult.size());
    Object getResult = actualToMapResult.get("associations");
    assertTrue(getResult instanceof List);
    Object getResult2 = actualToMapResult.get(ERDPersistedState.TAG_ENTITIES);
    assertTrue(getResult2 instanceof List);
    Object getResult3 = actualToMapResult.get("data");
    assertTrue(getResult3 instanceof Map);
    assertEquals(1, ((Map<String, ArrayList>) getResult3).size());
    assertTrue(((Map<String, ArrayList>) getResult3).get("icons").isEmpty());
    assertTrue(((List<Object>) getResult).isEmpty());
    assertTrue(((List<Object>) getResult2).isEmpty());
  }

  /**
   * Test {@link ERDDiagram#toMap(ERDContext, boolean)}.
   *
   * <p>Method under test: {@link ERDDiagram#toMap(ERDContext, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map ERDDiagram.toMap(ERDContext, boolean)"})
  public void testToMap2() {
    // Arrange
    ERDEntity entity = new ERDEntity(mock(DBPDataSource.class));
    ERDLogicalPrimaryKey container =
        new ERDLogicalPrimaryKey(entity, "Name", "The characteristics of someone or something");

    ERDDiagram erdDiagram = new ERDDiagram(container, "Name", new ERDContentProviderDefault());
    erdDiagram.addNote(new ERDNote(ERDPersistedState.TAG_ENTITIES), true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel navigatorModel = new DBNModel(platform, new ArrayList<>());

    ERDContext context = new ERDContext(monitor, dataSourceContainer, navigatorModel);

    // Act
    Map<String, Object> actualToMapResult = erdDiagram.toMap(context, true);

    // Assert
    assertEquals(3, actualToMapResult.size());
    Object getResult = actualToMapResult.get("associations");
    assertTrue(getResult instanceof List);
    Object getResult2 = actualToMapResult.get(ERDPersistedState.TAG_ENTITIES);
    assertTrue(getResult2 instanceof List);
    Object getResult3 = actualToMapResult.get("data");
    assertTrue(getResult3 instanceof Map);
    assertEquals(1, ((Map<String, ArrayList>) getResult3).size());
    assertTrue(((Map<String, ArrayList>) getResult3).get("icons").isEmpty());
    assertTrue(((List<Object>) getResult).isEmpty());
    assertTrue(((List<Object>) getResult2).isEmpty());
  }
}
