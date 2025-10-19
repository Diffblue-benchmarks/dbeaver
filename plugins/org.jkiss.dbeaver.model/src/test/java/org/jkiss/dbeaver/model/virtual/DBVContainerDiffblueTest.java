package org.jkiss.dbeaver.model.virtual;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.exec.DBCLogicalOperator;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSObjectContainer;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBVContainerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBVContainer#DBVContainer(DBVContainer, String)}
   *   <li>{@link DBVContainer#setDescription(String)}
   *   <li>{@link DBVContainer#cacheStructure(DBRProgressMonitor, int)}
   *   <li>{@link DBVContainer#getDescription()}
   *   <li>{@link DBVContainer#getName()}
   *   <li>{@link DBVContainer#getType()}
   *   <li>{@link DBVContainer#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVContainer.<init>(DBVContainer, String)",
    "void DBVContainer.cacheStructure(DBRProgressMonitor, int)",
    "String DBVContainer.getDescription()",
    "String DBVContainer.getName()",
    "String DBVContainer.getType()",
    "void DBVContainer.setDescription(String)",
    "String DBVContainer.toString()"
  })
  public void testGettersAndSetters() throws DBException {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    // Act
    DBVContainer actualDbvContainer = new DBVContainer(parent, "Name");
    actualDbvContainer.setDescription("The characteristics of someone or something");
    actualDbvContainer.cacheStructure(new LoggingProgressMonitor(), 1);
    String actualDescription = actualDbvContainer.getDescription();
    String actualName = actualDbvContainer.getName();
    String actualType = actualDbvContainer.getType();

    // Assert
    assertEquals("Name", actualName);
    assertEquals("Name", actualDbvContainer.toString());
    assertEquals("The characteristics of someone or something", actualDescription);
    assertEquals("container", actualType);
    assertNull(actualDbvContainer.getTransformSettings());
    assertSame(parent, actualDbvContainer.getParentObject());
  }

  /**
   * Test {@link DBVContainer#DBVContainer(DBVContainer, String, Map)}.
   *
   * <ul>
   *   <li>Given {@code @}.
   *   <li>When {@link HashMap#HashMap()} {@code @} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#DBVContainer(DBVContainer, String, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.<init>(DBVContainer, String, Map)"})
  public void testNewDBVContainer_givenCommercialAt_whenHashMapCommercialAtIsRename() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name");

    HashMap<String, Object> map = new HashMap<>();
    map.put("@", DBPEvent.RENAME);
    map.put("foo", DBPEvent.RENAME);

    // Act
    DBVContainer actualDbvContainer = new DBVContainer(parent, "Name", map);

    // Assert
    assertEquals("Name", actualDbvContainer.getName());
    assertEquals("Name", actualDbvContainer.toString());
    assertEquals("container", actualDbvContainer.getType());
    assertNull(actualDbvContainer.getDescription());
    assertNull(actualDbvContainer.getDataSourceContainer());
    assertNull(actualDbvContainer.getProject());
    assertNull(actualDbvContainer.getTransformSettings());
    assertFalse(actualDbvContainer.hasValuableData());
    assertTrue(actualDbvContainer.getContainers().isEmpty());
    assertTrue(actualDbvContainer.getEntities().isEmpty());
    assertTrue(actualDbvContainer.getProperties().isEmpty());
    assertTrue(actualDbvContainer.isPersisted());
    assertSame(parent, actualDbvContainer.getParentObject());
  }

  /**
   * Test {@link DBVContainer#DBVContainer(DBVContainer, String, Map)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#DBVContainer(DBVContainer, String, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.<init>(DBVContainer, String, Map)"})
  public void testNewDBVContainer_givenFoo_whenHashMapFooIsRename() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name");

    HashMap<String, Object> map = new HashMap<>();
    map.put("foo", DBPEvent.RENAME);

    // Act
    DBVContainer actualDbvContainer = new DBVContainer(parent, "Name", map);

    // Assert
    assertEquals("Name", actualDbvContainer.getName());
    assertEquals("Name", actualDbvContainer.toString());
    assertEquals("container", actualDbvContainer.getType());
    assertNull(actualDbvContainer.getDescription());
    assertNull(actualDbvContainer.getDataSourceContainer());
    assertNull(actualDbvContainer.getProject());
    assertNull(actualDbvContainer.getTransformSettings());
    assertFalse(actualDbvContainer.hasValuableData());
    assertTrue(actualDbvContainer.getContainers().isEmpty());
    assertTrue(actualDbvContainer.getEntities().isEmpty());
    assertTrue(actualDbvContainer.getProperties().isEmpty());
    assertTrue(actualDbvContainer.isPersisted());
    assertSame(parent, actualDbvContainer.getParentObject());
  }

  /**
   * Test {@link DBVContainer#DBVContainer(DBVContainer, String, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#DBVContainer(DBVContainer, String, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.<init>(DBVContainer, String, Map)"})
  public void testNewDBVContainer_whenHashMap() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name");

    // Act
    DBVContainer actualDbvContainer = new DBVContainer(parent, "Name", new HashMap<>());

    // Assert
    assertEquals("Name", actualDbvContainer.getName());
    assertEquals("Name", actualDbvContainer.toString());
    assertEquals("container", actualDbvContainer.getType());
    assertNull(actualDbvContainer.getDescription());
    assertNull(actualDbvContainer.getDataSourceContainer());
    assertNull(actualDbvContainer.getProject());
    assertNull(actualDbvContainer.getTransformSettings());
    assertFalse(actualDbvContainer.hasValuableData());
    assertTrue(actualDbvContainer.getContainers().isEmpty());
    assertTrue(actualDbvContainer.getEntities().isEmpty());
    assertTrue(actualDbvContainer.getProperties().isEmpty());
    assertTrue(actualDbvContainer.isPersisted());
    assertSame(parent, actualDbvContainer.getParentObject());
  }

  /**
   * Test {@link DBVContainer#dispose()}.
   *
   * <p>Method under test: {@link DBVContainer#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.dispose()"})
  public void testDispose() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent2, "Name"));

    // Act
    dbvContainer.dispose();

    // Assert
    assertFalse(dbvContainer.hasValuableData());
    assertTrue(dbvContainer.getContainers().isEmpty());
    assertTrue(dbvContainer.getEntities().isEmpty());
  }

  /**
   * Test {@link DBVContainer#dispose()}.
   *
   * <p>Method under test: {@link DBVContainer#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.dispose()"})
  public void testDispose2() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    dbvContainer.addEntity(entity);
    DBVContainer parent3 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent3, "Name"));

    // Act
    dbvContainer.dispose();

    // Assert
    assertFalse(dbvContainer.hasValuableData());
    assertTrue(dbvContainer.getContainers().isEmpty());
    assertTrue(dbvContainer.getEntities().isEmpty());
  }

  /**
   * Test {@link DBVContainer#dispose()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer#DBVContainer(DBVContainer, String, Map)} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.dispose()"})
  public void testDispose_givenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    // Act
    dbvContainer.dispose();

    // Assert that nothing has changed
    assertFalse(dbvContainer.hasValuableData());
    assertTrue(dbvContainer.getContainers().isEmpty());
    assertTrue(dbvContainer.getEntities().isEmpty());
  }

  /**
   * Test {@link DBVContainer#getRealContainer(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String, Map)} with parent is {@link
   *       DBVModel#DBVModel(DBPDataSourceContainer)} and {@code Name} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObjectContainer DBVContainer.getRealContainer(DBRProgressMonitor)"})
  public void testGetRealContainer_givenDBVContainerWithParentIsDBVModelAndNameAndMapIsHashMap()
      throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent2, "Name");

    // Act
    DBSObjectContainer actualRealContainer =
        dbvContainer.getRealContainer(new LoggingProgressMonitor());

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualRealContainer);
  }

  /**
   * Test {@link DBVContainer#getRealContainer(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String, Map)} with parent is {@code
   *       null} and {@code Name} and map is {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObjectContainer DBVContainer.getRealContainer(DBRProgressMonitor)"})
  public void testGetRealContainer_givenDBVContainerWithParentIsNullAndNameAndMapIsHashMap()
      throws DBException {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    // Act and Assert
    assertNull(dbvContainer.getRealContainer(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DBVContainer#getRealContainer(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObjectContainer DBVContainer.getRealContainer(DBRProgressMonitor)"})
  public void testGetRealContainer_givenDBVModelWithIdIs42AndMapIsHashMap() throws DBException {
    // Arrange
    DBVModel parent = new DBVModel("42", new HashMap<>());
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent2, "Name");

    // Act and Assert
    assertNull(dbvContainer.getRealContainer(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DBVContainer#getRealContainer(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObjectContainer DBVContainer.getRealContainer(DBRProgressMonitor)"})
  public void testGetRealContainer_thenCallsGetDataSource() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    // Act
    DBSObjectContainer actualRealContainer =
        dbvModel.getRealContainer(new LoggingProgressMonitor());

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualRealContainer);
  }

  /**
   * Test {@link DBVContainer#getParentObject()}.
   *
   * <p>Method under test: {@link DBVContainer#getParentObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVContainer DBVContainer.getParentObject()"})
  public void testGetParentObject() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    // Act and Assert
    assertSame(parent, new DBVContainer(parent, "Name").getParentObject());
  }

  /**
   * Test {@link DBVContainer#getDataSource()}.
   *
   * <p>Method under test: {@link DBVContainer#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVContainer.getDataSource()"})
  public void testGetDataSource() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());

    // Act
    new DBVContainer(parent2, "Name").getDataSource();

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVContainer#getContainers()}.
   *
   * <p>Method under test: {@link DBVContainer#getContainers()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Collection DBVContainer.getContainers()"})
  public void testGetContainers() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    // Act and Assert
    assertTrue(new DBVContainer(parent, "Name").getContainers().isEmpty());
  }

  /**
   * Test {@link DBVContainer#getContainer(String, boolean)}.
   *
   * <p>Method under test: {@link DBVContainer#getContainer(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVContainer DBVContainer.getContainer(String, boolean)"})
  public void testGetContainer() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    // Act and Assert
    assertSame(dbvContainer, dbvContainer.getContainer("Name", true).getParentObject());
  }

  /**
   * Test {@link DBVContainer#getContainer(String, boolean)}.
   *
   * <ul>
   *   <li>Then return {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer#DBVContainer(DBVContainer, String, Map)} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#getContainer(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVContainer DBVContainer.getContainer(String, boolean)"})
  public void testGetContainer_thenReturnDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    dbvContainer.addContainer(container);

    // Act and Assert
    assertSame(container, dbvContainer.getContainer("Name", true));
  }

  /**
   * Test {@link DBVContainer#getContainer(String, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#getContainer(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVContainer DBVContainer.getContainer(String, boolean)"})
  public void testGetContainer_whenFalse_thenReturnNull() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    // Act and Assert
    assertNull(new DBVContainer(parent, "Name").getContainer("Name", false));
  }

  /**
   * Test {@link DBVContainer#addContainer(DBVContainer)}.
   *
   * <p>Method under test: {@link DBVContainer#addContainer(DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.addContainer(DBVContainer)"})
  public void testAddContainer() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer parent2 = new DBVContainer(parent, "Name");
    DBVContainer parent3 = new DBVContainer(parent2, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent3, "Name");

    DBVModel container = mock(DBVModel.class);
    when(container.getName()).thenReturn("Name");

    // Act
    dbvContainer.addContainer(container);

    // Assert
    verify(container).getName();
    assertEquals(1, dbvContainer.getContainers().size());
  }

  /**
   * Test {@link DBVContainer#addContainer(DBVContainer)}.
   *
   * <ul>
   *   <li>Then {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer#DBVContainer(DBVContainer, String, Map)} and {@code Name} Containers Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#addContainer(DBVContainer)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.addContainer(DBVContainer)"})
  public void testAddContainer_thenDBVContainerWithParentIsDBVContainerAndNameContainersEmpty() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");

    // Act
    dbvContainer.addContainer(container);

    // Assert that nothing has changed
    assertTrue(container.getContainers().isEmpty());
  }

  /**
   * Test {@link DBVContainer#getEntities()}.
   *
   * <p>Method under test: {@link DBVContainer#getEntities()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Collection DBVContainer.getEntities()"})
  public void testGetEntities() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    // Act and Assert
    assertTrue(new DBVContainer(parent, "Name").getEntities().isEmpty());
  }

  /**
   * Test {@link DBVContainer#getEntity(String, boolean)}.
   *
   * <p>Method under test: {@link DBVContainer#getEntity(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntity DBVContainer.getEntity(String, boolean)"})
  public void testGetEntity() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    dbvContainer.addEntity(entity);

    // Act and Assert
    assertSame(entity, dbvContainer.getEntity("Name", true));
  }

  /**
   * Test {@link DBVContainer#getEntity(String, boolean)}.
   *
   * <ul>
   *   <li>When {@code false}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#getEntity(String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntity DBVContainer.getEntity(String, boolean)"})
  public void testGetEntity_whenFalse_thenReturnNull() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    // Act and Assert
    assertNull(new DBVContainer(parent, "Name").getEntity("Name", false));
  }

  /**
   * Test {@link DBVContainer#addEntity(DBVEntity)}.
   *
   * <ul>
   *   <li>Then {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer#DBVContainer(DBVContainer, String, Map)} and {@code Name} Entities size is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#addEntity(DBVEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.addEntity(DBVEntity)"})
  public void testAddEntity_thenDBVContainerWithParentIsDBVContainerAndNameEntitiesSizeIsOne() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    // Act
    dbvContainer.addEntity(entity);

    // Assert
    assertEquals(1, dbvContainer.getEntities().size());
    assertTrue(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#removeEntity(DBVEntity)}.
   *
   * <ul>
   *   <li>Given {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)} RefEntityId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#removeEntity(DBVEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.removeEntity(DBVEntity)"})
  public void testRemoveEntity_givenDBVEntityForeignKeyWithEntityIsDBVEntityRefEntityIdIs42() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel parent2 = new DBVModel(dataSourceContainer);
    DBVContainer parent3 = new DBVContainer(parent2, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent3, "Name");
    DBVContainer parent4 = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId("42");

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity2.addForeignKey(foreignKey);

    // Act
    dbvContainer.removeEntity(entity2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(entity2.getForeignKeys().isEmpty());
  }

  /**
   * Test {@link DBVContainer#removeEntity(DBVEntity)}.
   *
   * <ul>
   *   <li>Given {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)} RefEntityId is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#removeEntity(DBVEntity)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.removeEntity(DBVEntity)"})
  public void testRemoveEntity_givenDBVEntityForeignKeyWithEntityIsDBVEntityRefEntityIdIsNull() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel parent2 = new DBVModel(dataSourceContainer);
    DBVContainer parent3 = new DBVContainer(parent2, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent3, "Name");
    DBVContainer parent4 = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent4, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity);
    foreignKey.setRefEntityId(null);

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity2.addForeignKey(foreignKey);

    // Act
    dbvContainer.removeEntity(entity2);

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertTrue(entity2.getForeignKeys().isEmpty());
  }

  /**
   * Test {@link DBVContainer#hasValuableData()}.
   *
   * <p>Method under test: {@link DBVContainer#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVContainer.hasValuableData()"})
  public void testHasValuableData() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    dbvContainer.addEntity(entity);
    DBVContainer parent3 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent3, "Name"));

    // Act and Assert
    assertTrue(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#hasValuableData()}.
   *
   * <p>Method under test: {@link DBVContainer#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVContainer.hasValuableData()"})
  public void testHasValuableData2() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    dbvContainer.setProperty("Name", DBPEvent.RENAME);
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent2, "Name"));

    // Act and Assert
    assertTrue(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#hasValuableData()}.
   *
   * <p>Method under test: {@link DBVContainer#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVContainer.hasValuableData()"})
  public void testHasValuableData3() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity = new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "");
    dbvContainer.addEntity(entity);
    DBVContainer parent3 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent3, "Name"));

    // Act and Assert
    assertFalse(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#hasValuableData()}.
   *
   * <p>Method under test: {@link DBVContainer#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVContainer.hasValuableData()"})
  public void testHasValuableData4() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", new HashMap<>());
    dbvContainer.addEntity(entity);
    DBVContainer parent3 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent3, "Name"));

    // Act and Assert
    assertFalse(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#hasValuableData()}.
   *
   * <p>Method under test: {@link DBVContainer#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVContainer.hasValuableData()"})
  public void testHasValuableData5() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent, "Name"), "Name", "");
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride color =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");
    entity.addColorOverride(color);
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent2, "Name");
    dbvContainer.addEntity(entity);
    DBVContainer parent3 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent3, "Name"));

    // Act and Assert
    assertTrue(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#hasValuableData()}.
   *
   * <p>Method under test: {@link DBVContainer#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVContainer.hasValuableData()"})
  public void testHasValuableData6() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent, "Name"), "Name", "");
    entity.setColorOverrides(new ArrayList<>());
    DBVEntity entity2 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(null, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint, false);
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent2, "Name");
    dbvContainer.addEntity(entity);
    DBVContainer parent3 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent3, "Name"));

    // Act and Assert
    assertFalse(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#hasValuableData()}.
   *
   * <p>Method under test: {@link DBVContainer#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVContainer.hasValuableData()"})
  public void testHasValuableData7() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent, "Name"), "Name", "");
    entity.setProperty("Name", DBPEvent.RENAME);
    DBVEntity entity2 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(null, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint, false);
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent2, "Name");
    dbvContainer.addEntity(entity);
    DBVContainer parent3 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent3, "Name"));

    // Act and Assert
    assertTrue(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#hasValuableData()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@code null}
   *       and {@code Name}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVContainer.hasValuableData()"})
  public void testHasValuableData_givenDBVContainerWithParentIsNullAndName_thenReturnFalse() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVEntity entity = new DBVEntity(new DBVContainer(parent, "Name"), "Name", "");
    DBVEntity entity2 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(null, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint, false);
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent2, "Name");
    dbvContainer.addEntity(entity);
    DBVContainer parent3 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent3, "Name"));

    // Act and Assert
    assertFalse(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#hasValuableData()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVContainer.hasValuableData()"})
  public void testHasValuableData_thenReturnFalse() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    // Act and Assert
    assertFalse(new DBVContainer(parent, "Name").hasValuableData());
  }

  /**
   * Test {@link DBVContainer#hasValuableData()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#hasValuableData()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBVContainer.hasValuableData()"})
  public void testHasValuableData_thenReturnFalse2() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent2, "Name"));

    // Act and Assert
    assertFalse(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#copyFrom(DBVContainer, DBVModel)} with {@code container}, {@code
   * targetModel}.
   *
   * <p>Method under test: {@link DBVContainer#copyFrom(DBVContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.copyFrom(DBVContainer, DBVModel)"})
  public void testCopyFromWithContainerTargetModel() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer container = new DBVContainer(parent2, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    // Act
    dbvContainer.copyFrom(container, new DBVModel(dataSourceContainer));

    // Assert that nothing has changed
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("Name", container.getName());
    assertEquals("Name", container.toString());
    assertFalse(container.hasValuableData());
    assertTrue(container.getEntities().isEmpty());
  }

  /**
   * Test {@link DBVContainer#copyFrom(DBVContainer, DBVModel)} with {@code container}, {@code
   * targetModel}.
   *
   * <p>Method under test: {@link DBVContainer#copyFrom(DBVContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.copyFrom(DBVContainer, DBVModel)"})
  public void testCopyFromWithContainerTargetModel2() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    // Act
    dbvContainer.copyFrom(container, new DBVModel(dataSourceContainer2));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals("42", dbvContainer.getName());
    assertEquals("42", dbvContainer.toString());
    assertFalse(dbvContainer.hasValuableData());
    assertTrue(dbvContainer.getContainers().isEmpty());
    assertTrue(dbvContainer.getEntities().isEmpty());
  }

  /**
   * Test {@link DBVContainer#copyFrom(DBVContainer, DBVModel)} with {@code container}, {@code
   * targetModel}.
   *
   * <p>Method under test: {@link DBVContainer#copyFrom(DBVContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.copyFrom(DBVContainer, DBVModel)"})
  public void testCopyFromWithContainerTargetModel3() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel container = new DBVModel(dataSourceContainer);
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    container.addContainer(new DBVContainer(parent2, "Name"));

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    // Act
    dbvContainer.copyFrom(container, new DBVModel(dataSourceContainer2));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals("42", dbvContainer.getName());
    assertEquals("42", dbvContainer.toString());
    assertEquals(1, dbvContainer.getContainers().size());
    assertFalse(dbvContainer.hasValuableData());
    assertTrue(dbvContainer.getEntities().isEmpty());
  }

  /**
   * Test {@link DBVContainer#copyFrom(DBVContainer, DBVModel)} with {@code container}, {@code
   * targetModel}.
   *
   * <p>Method under test: {@link DBVContainer#copyFrom(DBVContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.copyFrom(DBVContainer, DBVModel)"})
  public void testCopyFromWithContainerTargetModel4() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel container = new DBVModel(dataSourceContainer);
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    container.addEntity(entity);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    // Act
    dbvContainer.copyFrom(container, new DBVModel(dataSourceContainer2));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals("42", dbvContainer.getName());
    assertEquals("42", dbvContainer.toString());
    assertEquals(1, dbvContainer.getEntities().size());
    assertTrue(dbvContainer.getContainers().isEmpty());
    assertTrue(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#copyFrom(DBVContainer, DBVModel)} with {@code container}, {@code
   * targetModel}.
   *
   * <p>Method under test: {@link DBVContainer#copyFrom(DBVContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.copyFrom(DBVContainer, DBVModel)"})
  public void testCopyFromWithContainerTargetModel5() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");

    DBVModel container = new DBVModel(dataSourceContainer);
    container.setProperty("Name", DBPEvent.RENAME);
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    container.addContainer(new DBVContainer(parent2, "Name"));

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    // Act
    dbvContainer.copyFrom(container, new DBVModel(dataSourceContainer2));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals(1, dbvContainer.getContainers().size());
    Map<String, Object> properties = dbvContainer.getProperties();
    assertEquals(1, properties.size());
    assertTrue(properties.containsKey("Name"));
    assertTrue(dbvContainer.getParentObject().getProperties().isEmpty());
  }

  /**
   * Test {@link DBVContainer#copyFrom(DBVContainer, DBVModel)} with {@code container}, {@code
   * targetModel}.
   *
   * <p>Method under test: {@link DBVContainer#copyFrom(DBVContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.copyFrom(DBVContainer, DBVModel)"})
  public void testCopyFromWithContainerTargetModel6() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride color =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");
    entity.addColorOverride(color);

    DBVModel container = new DBVModel(dataSourceContainer);
    container.addEntity(entity);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    // Act
    dbvContainer.copyFrom(container, new DBVModel(dataSourceContainer2));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals("42", dbvContainer.getName());
    assertEquals("42", dbvContainer.toString());
    assertEquals(1, dbvContainer.getEntities().size());
    assertTrue(dbvContainer.getContainers().isEmpty());
    assertTrue(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#copyFrom(DBVContainer, DBVModel)} with {@code container}, {@code
   * targetModel}.
   *
   * <p>Method under test: {@link DBVContainer#copyFrom(DBVContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.copyFrom(DBVContainer, DBVModel)"})
  public void testCopyFromWithContainerTargetModel7() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    entity.setColorOverrides(new ArrayList<>());

    DBVModel container = new DBVModel(dataSourceContainer);
    container.addEntity(entity);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    // Act
    dbvContainer.copyFrom(container, new DBVModel(dataSourceContainer2));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals("42", dbvContainer.getName());
    assertEquals("42", dbvContainer.toString());
    assertEquals(1, dbvContainer.getEntities().size());
    assertTrue(dbvContainer.getContainers().isEmpty());
    assertTrue(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#copyFrom(DBVContainer, DBVModel)} with {@code container}, {@code
   * targetModel}.
   *
   * <p>Method under test: {@link DBVContainer#copyFrom(DBVContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.copyFrom(DBVContainer, DBVModel)"})
  public void testCopyFromWithContainerTargetModel8() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(null, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity, copy);
    constraint.addAttribute("Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());

    DBVEntity entity3 =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    entity3.addConstraint(constraint, false);
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride color =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");
    entity3.addColorOverride(color);

    DBVModel container = new DBVModel(dataSourceContainer);
    container.addEntity(entity3);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    // Act
    dbvContainer.copyFrom(container, new DBVModel(dataSourceContainer2));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals("42", dbvContainer.getName());
    assertEquals("42", dbvContainer.toString());
    assertEquals(1, dbvContainer.getEntities().size());
    assertTrue(dbvContainer.getContainers().isEmpty());
    assertTrue(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#copyFrom(DBVContainer, DBVModel)} with {@code container}, {@code
   * targetModel}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@code null}
   *       and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#copyFrom(DBVContainer, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVContainer.copyFrom(DBVContainer, DBVModel)"})
  public void testCopyFromWithContainerTargetModel_givenDBVContainerWithParentIsNullAndName() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());

    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVEntity entity2 =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntity entity3 = new DBVEntity(null, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint constraint = new DBVEntityConstraint(entity2, copy);
    entity.addConstraint(constraint, false);
    Object[] attributeValues = new Object[] {DBPEvent.RENAME};
    DBVColorOverride color =
        new DBVColorOverride(
            "Attribute Name",
            DBCLogicalOperator.EQUALS,
            attributeValues,
            "Color Foreground",
            "Color Background");
    entity.addColorOverride(color);

    DBVModel container = new DBVModel(dataSourceContainer);
    container.addEntity(entity);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");

    // Act
    dbvContainer.copyFrom(container, new DBVModel(dataSourceContainer2));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals("42", dbvContainer.getName());
    assertEquals("42", dbvContainer.toString());
    assertEquals(1, dbvContainer.getEntities().size());
    assertTrue(dbvContainer.getContainers().isEmpty());
    assertTrue(dbvContainer.hasValuableData());
  }

  /**
   * Test {@link DBVContainer#getChildren(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer#DBVContainer(DBVContainer, String, Map)} and {@code Name}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Collection DBVContainer.getChildren(DBRProgressMonitor)"})
  public void testGetChildren_givenDBVContainerWithParentIsDBVContainerAndName_thenReturnEmpty()
      throws DBException {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    // Act and Assert
    assertTrue(dbvContainer.getChildren(new LoggingProgressMonitor()).isEmpty());
  }

  /**
   * Test {@link DBVContainer#getChildren(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#getChildren(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Collection DBVContainer.getChildren(DBRProgressMonitor)"})
  public void testGetChildren_thenReturnSizeIsOne() throws DBException {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent2, "Name"));

    // Act and Assert
    assertEquals(1, dbvContainer.getChildren(new LoggingProgressMonitor()).size());
  }

  /**
   * Test {@link DBVContainer#getChild(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link DBVContainer#getChild(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DBVContainer.getChild(DBRProgressMonitor, String)"})
  public void testGetChild() throws DBException {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent2, "Name"));

    // Act and Assert
    assertNull(dbvContainer.getChild(new LoggingProgressMonitor(), "Child Name"));
  }

  /**
   * Test {@link DBVContainer#getChild(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer#DBVContainer(DBVContainer, String, Map)} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#getChild(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DBVContainer.getChild(DBRProgressMonitor, String)"})
  public void testGetChild_givenDBVContainerWithParentIsDBVContainerAndName() throws DBException {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    // Act and Assert
    assertNull(dbvContainer.getChild(new LoggingProgressMonitor(), "Child Name"));
  }

  /**
   * Test {@link DBVContainer#getPrimaryChildType(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@link DBVContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#getPrimaryChildType(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class DBVContainer.getPrimaryChildType(DBRProgressMonitor)"})
  public void testGetPrimaryChildType_thenReturnDBVContainer() throws DBException {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());

    DBVContainer dbvContainer = new DBVContainer(parent, "Name");
    DBVContainer parent2 = new DBVContainer(null, "Name", new HashMap<>());
    dbvContainer.addContainer(new DBVContainer(parent2, "Name"));

    // Act
    Class<? extends DBSObject> actualPrimaryChildType =
        dbvContainer.getPrimaryChildType(new LoggingProgressMonitor());

    // Assert
    Class<DBVContainer> expectedPrimaryChildType = DBVContainer.class;
    assertEquals(expectedPrimaryChildType, actualPrimaryChildType);
  }

  /**
   * Test {@link DBVContainer#getPrimaryChildType(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then return {@link DBVEntity}.
   * </ul>
   *
   * <p>Method under test: {@link DBVContainer#getPrimaryChildType(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class DBVContainer.getPrimaryChildType(DBRProgressMonitor)"})
  public void testGetPrimaryChildType_thenReturnDBVEntity() throws DBException {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVContainer dbvContainer = new DBVContainer(parent, "Name");

    // Act
    Class<? extends DBSObject> actualPrimaryChildType =
        dbvContainer.getPrimaryChildType(new LoggingProgressMonitor());

    // Assert
    Class<DBVEntity> expectedPrimaryChildType = DBVEntity.class;
    assertEquals(expectedPrimaryChildType, actualPrimaryChildType);
  }
}
