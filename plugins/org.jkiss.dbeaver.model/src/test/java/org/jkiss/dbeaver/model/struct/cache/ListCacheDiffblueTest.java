package org.jkiss.dbeaver.model.struct.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ListCacheDiffblueTest {
  /**
   * Test {@link ListCache#ListCache(List)}.
   *
   * <ul>
   *   <li>Then return CachedObjects is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link ListCache#ListCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ListCache.<init>(List)"})
  public void testNewListCache_thenReturnCachedObjectsIsArrayList() {
    // Arrange
    ArrayList<DBSObject> objectList = new ArrayList<>();
    objectList.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Act
    ListCache<DBSObject, DBSObject> actualListCache = new ListCache<>(objectList);

    // Assert
    assertSame(objectList, actualListCache.getCachedObjects());
  }

  /**
   * Test {@link ListCache#ListCache(List)}.
   *
   * <ul>
   *   <li>Then return CachedObjects size is two.
   * </ul>
   *
   * <p>Method under test: {@link ListCache#ListCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ListCache.<init>(List)"})
  public void testNewListCache_thenReturnCachedObjectsSizeIsTwo() {
    // Arrange
    ArrayList<DBSObject> objectList = new ArrayList<>();
    objectList.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    objectList.add(dbsDocumentConstraint);

    // Act
    ListCache<DBSObject, DBSObject> actualListCache = new ListCache<>(objectList);

    // Assert
    List<DBSObject> cachedObjects = actualListCache.getCachedObjects();
    assertEquals(2, cachedObjects.size());
    assertSame(dbsDocumentConstraint, cachedObjects.get(1));
  }

  /**
   * Test {@link ListCache#ListCache(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return CachedObjects Empty.
   * </ul>
   *
   * <p>Method under test: {@link ListCache#ListCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ListCache.<init>(List)"})
  public void testNewListCache_whenArrayList_thenReturnCachedObjectsEmpty() {
    // Arrange and Act
    ListCache<DBSObject, DBSObject> actualListCache = new ListCache<>(new ArrayList<>());

    // Assert
    assertTrue(actualListCache.getCachedObjects().isEmpty());
    assertTrue(actualListCache.isFullyCached());
  }

  /**
   * Test {@link ListCache#ListCache(List)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return CachedObjects Empty.
   * </ul>
   *
   * <p>Method under test: {@link ListCache#ListCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ListCache.<init>(List)"})
  public void testNewListCache_whenNull_thenReturnCachedObjectsEmpty() {
    // Arrange and Act
    ListCache<DBSObject, DBSObject> actualListCache = new ListCache<>(null);

    // Assert
    assertTrue(actualListCache.getCachedObjects().isEmpty());
    assertTrue(actualListCache.isFullyCached());
  }

  /**
   * Test {@link ListCache#getAllObjects(DBRProgressMonitor, DBSObject)}.
   *
   * <p>Method under test: {@link ListCache#getAllObjects(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List ListCache.getAllObjects(DBRProgressMonitor, DBSObject)"})
  public void testGetAllObjects() throws DBException {
    // Arrange
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(new ArrayList<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertTrue(
        listCache
            .getAllObjects(monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)))
            .isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ListCache#renameObject(DBSObject, String, String)}
   *   <li>{@link ListCache#getCachedObjects()}
   *   <li>{@link ListCache#isFullyCached()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List ListCache.getCachedObjects()",
    "boolean ListCache.isFullyCached()",
    "void ListCache.renameObject(DBSObject, String, String)"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<DBSObject> objectList = new ArrayList<>();
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(objectList);

    // Act
    listCache.renameObject(
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), "Old Name", "New Name");
    List<DBSObject> actualCachedObjects = listCache.getCachedObjects();
    boolean actualIsFullyCachedResult = listCache.isFullyCached();

    // Assert
    assertTrue(actualCachedObjects.isEmpty());
    assertTrue(actualIsFullyCachedResult);
    assertSame(objectList, actualCachedObjects);
  }

  /**
   * Test {@link ListCache#getObject(DBRProgressMonitor, DBSObject, String)}.
   *
   * <p>Method under test: {@link ListCache#getObject(DBRProgressMonitor, DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject ListCache.getObject(DBRProgressMonitor, DBSObject, String)"})
  public void testGetObject() throws DBException {
    // Arrange
    ArrayList<DBSObject> objectList = new ArrayList<>();
    objectList.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(objectList);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertNull(
        listCache.getObject(
            monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), "Name"));
  }

  /**
   * Test {@link ListCache#getObject(DBRProgressMonitor, DBSObject, String)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ListCache#getObject(DBRProgressMonitor, DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject ListCache.getObject(DBRProgressMonitor, DBSObject, String)"})
  public void testGetObject_givenDBVContainerWithParentIsDBVContainerAndName_thenReturnNull()
      throws DBException {
    // Arrange
    ArrayList<DBSObject> objectList = new ArrayList<>();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    objectList.add(new DBVEntityForeignKey(entity));
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(objectList);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertNull(
        listCache.getObject(
            monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), "Name"));
  }

  /**
   * Test {@link ListCache#getObject(DBRProgressMonitor, DBSObject, String)}.
   *
   * <ul>
   *   <li>Then return {@link DBVModel#DBVModel(DBPDataSourceContainer)} with dataSourceContainer is
   *       {@link DBPDataSourceContainer}.
   * </ul>
   *
   * <p>Method under test: {@link ListCache#getObject(DBRProgressMonitor, DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject ListCache.getObject(DBRProgressMonitor, DBSObject, String)"})
  public void testGetObject_thenReturnDBVModelWithDataSourceContainerIsDBPDataSourceContainer()
      throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    ArrayList<DBSObject> objectList = new ArrayList<>();
    objectList.add(dbvModel);
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(objectList);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    DBSObject actualObject =
        listCache.getObject(
            monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), "42");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertSame(dbvModel, actualObject);
  }

  /**
   * Test {@link ListCache#getObject(DBRProgressMonitor, DBSObject, String)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ListCache#getObject(DBRProgressMonitor, DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject ListCache.getObject(DBRProgressMonitor, DBSObject, String)"})
  public void testGetObject_whenName_thenReturnNull() throws DBException {
    // Arrange
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(new ArrayList<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertNull(
        listCache.getObject(
            monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), "Name"));
  }

  /**
   * Test {@link ListCache#getCachedObject(String)}.
   *
   * <p>Method under test: {@link ListCache#getCachedObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject ListCache.getCachedObject(String)"})
  public void testGetCachedObject() {
    // Arrange
    ArrayList<DBSObject> objectList = new ArrayList<>();
    objectList.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(objectList);

    // Act and Assert
    assertNull(listCache.getCachedObject("Name"));
  }

  /**
   * Test {@link ListCache#getCachedObject(String)}.
   *
   * <p>Method under test: {@link ListCache#getCachedObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject ListCache.getCachedObject(String)"})
  public void testGetCachedObject2() {
    // Arrange
    ArrayList<DBSObject> objectList = new ArrayList<>();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "DocumentKey");
    DBVEntity entity = new DBVEntity(container, "DocumentKey", "DocumentKey");
    objectList.add(new DBVEntityForeignKey(entity));
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(objectList);

    // Act and Assert
    assertNull(listCache.getCachedObject("Name"));
  }

  /**
   * Test {@link ListCache#getCachedObject(String)}.
   *
   * <p>Method under test: {@link ListCache#getCachedObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject ListCache.getCachedObject(String)"})
  public void testGetCachedObject3() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    ArrayList<DBSObject> objectList = new ArrayList<>();
    objectList.add(dbvModel);
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(objectList);

    // Act
    DBSObject actualCachedObject = listCache.getCachedObject("42");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertSame(dbvModel, actualCachedObject);
  }

  /**
   * Test {@link ListCache#getCachedObject(String)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ListCache#getCachedObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject ListCache.getCachedObject(String)"})
  public void testGetCachedObject_whenName_thenReturnNull() {
    // Arrange
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(new ArrayList<>());

    // Act and Assert
    assertNull(listCache.getCachedObject("Name"));
  }

  /**
   * Test {@link ListCache#cacheObject(DBSObject)}.
   *
   * <p>Method under test: {@link ListCache#cacheObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ListCache.cacheObject(DBSObject)"})
  public void testCacheObject() {
    // Arrange
    ArrayList<DBSObject> objectList = new ArrayList<>();
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(objectList);
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    listCache.cacheObject(dbsDocumentConstraint);

    // Assert
    List<DBSObject> cachedObjects = listCache.getCachedObjects();
    assertEquals(1, cachedObjects.size());
    assertSame(objectList, cachedObjects);
    assertSame(dbsDocumentConstraint, cachedObjects.get(0));
  }

  /**
   * Test {@link ListCache#setCache(List)}.
   *
   * <ul>
   *   <li>Then {@link ListCache#ListCache(List)} with objectList is {@link ArrayList#ArrayList()}
   *       CachedObjects Empty.
   * </ul>
   *
   * <p>Method under test: {@link ListCache#setCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ListCache.setCache(List)"})
  public void testSetCache_thenListCacheWithObjectListIsArrayListCachedObjectsEmpty() {
    // Arrange
    ArrayList<DBSObject> objectList = new ArrayList<>();
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(objectList);
    ArrayList<DBSObject> objects = new ArrayList<>();

    // Act
    listCache.setCache(objects);

    // Assert that nothing has changed
    assertTrue(listCache.getCachedObjects().isEmpty());
    assertEquals(objectList, objects);
  }

  /**
   * Test {@link ListCache#setCache(List)}.
   *
   * <ul>
   *   <li>Then {@link ListCache#ListCache(List)} with objectList is {@link ArrayList#ArrayList()}
   *       CachedObjects is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link ListCache#setCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ListCache.setCache(List)"})
  public void testSetCache_thenListCacheWithObjectListIsArrayListCachedObjectsIsArrayList() {
    // Arrange
    ArrayList<DBSObject> objectList = new ArrayList<>();
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(objectList);

    ArrayList<DBSObject> objects = new ArrayList<>();
    objects.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));
    objects.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Act
    listCache.setCache(objects);

    // Assert
    assertEquals(objectList, objects);
    assertSame(objectList, listCache.getCachedObjects());
  }

  /**
   * Test {@link ListCache#setCache(List)}.
   *
   * <ul>
   *   <li>Then {@link ListCache#ListCache(List)} with objectList is {@link ArrayList#ArrayList()}
   *       CachedObjects size is one.
   * </ul>
   *
   * <p>Method under test: {@link ListCache#setCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ListCache.setCache(List)"})
  public void testSetCache_thenListCacheWithObjectListIsArrayListCachedObjectsSizeIsOne() {
    // Arrange
    ArrayList<DBSObject> objectList = new ArrayList<>();
    ListCache<DBSObject, DBSObject> listCache = new ListCache<>(objectList);

    ArrayList<DBSObject> objects = new ArrayList<>();
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    objects.add(dbsDocumentConstraint);

    // Act
    listCache.setCache(objects);

    // Assert
    List<DBSObject> cachedObjects = listCache.getCachedObjects();
    assertEquals(1, cachedObjects.size());
    assertEquals(objectList, objects);
    assertSame(objectList, cachedObjects);
    assertSame(dbsDocumentConstraint, cachedObjects.get(0));
  }
}
