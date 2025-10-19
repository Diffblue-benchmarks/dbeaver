package org.jkiss.dbeaver.model.struct.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.cache.AbstractObjectCache.CacheIterator;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractObjectCacheDiffblueTest {
  /**
   * Test CacheIterator {@link CacheIterator#hasNext()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link CacheIterator#hasNext()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean CacheIterator.hasNext()"})
  public void testCacheIteratorHasNext_thenReturnTrue() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    // Act
    boolean actualHasNextResult = simpleObjectCache.new CacheIterator().hasNext();

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    assertTrue(actualHasNextResult);
  }

  /**
   * Test CacheIterator {@link CacheIterator#CacheIterator(AbstractObjectCache)}.
   *
   * <ul>
   *   <li>Then return not hasNext.
   * </ul>
   *
   * <p>Method under test: {@link CacheIterator#CacheIterator(AbstractObjectCache)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CacheIterator.<init>(AbstractObjectCache)"})
  public void testCacheIteratorNewCacheIterator_thenReturnNotHasNext() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    // Act
    CacheIterator actualCacheIterator = simpleObjectCache.new CacheIterator();

    // Assert
    DBSObject actualNextResult = actualCacheIterator.next();
    boolean actualHasNextResult = actualCacheIterator.hasNext();
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    assertFalse(actualHasNextResult);
    assertSame(dbsDocumentConstraint, actualNextResult);
  }

  /**
   * Test CacheIterator {@link CacheIterator#next()}.
   *
   * <ul>
   *   <li>Then not {@link CacheIterator#CacheIterator(AbstractObjectCache)} with {@link
   *       SimpleObjectCache} (default constructor) hasNext.
   * </ul>
   *
   * <p>Method under test: {@link CacheIterator#next()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject CacheIterator.next()"})
  public void testCacheIteratorNext_thenNotCacheIteratorWithSimpleObjectCacheHasNext() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);
    CacheIterator cacheIterator = simpleObjectCache.new CacheIterator();

    // Act
    DBSObject actualNextResult = cacheIterator.next();

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    assertFalse(cacheIterator.hasNext());
    assertSame(dbsDocumentConstraint, actualNextResult);
  }

  /**
   * Test {@link AbstractObjectCache#getListOrderComparator()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getListOrderComparator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Comparator AbstractObjectCache.getListOrderComparator()"})
  public void testGetListOrderComparator_thenReturnNull() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    // Act and Assert
    assertNull(simpleObjectCache.getListOrderComparator());
  }

  /**
   * Test {@link AbstractObjectCache#setListOrderComparator(Comparator)}.
   *
   * <p>Method under test: {@link AbstractObjectCache#setListOrderComparator(Comparator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.setListOrderComparator(Comparator)"})
  public void testSetListOrderComparator() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    Comparator<DBSObject> listOrderComparator = mock(Comparator.class);

    // Act
    simpleObjectCache.setListOrderComparator(listOrderComparator);

    // Assert
    assertSame(listOrderComparator, simpleObjectCache.getListOrderComparator());
  }

  /**
   * Test {@link AbstractObjectCache#getCachedObjects()}.
   *
   * <ul>
   *   <li>Given {@link SimpleObjectCache} (default constructor).
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getCachedObjects()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List AbstractObjectCache.getCachedObjects()"})
  public void testGetCachedObjects_givenSimpleObjectCache_thenReturnEmpty() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    // Act and Assert
    assertTrue(simpleObjectCache.getCachedObjects().isEmpty());
  }

  /**
   * Test {@link AbstractObjectCache#getCachedObjects()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getCachedObjects()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List AbstractObjectCache.getCachedObjects()"})
  public void testGetCachedObjects_thenReturnSizeIsOne() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    // Act
    List<DBSObject> actualCachedObjects = simpleObjectCache.getCachedObjects();

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    assertEquals(1, actualCachedObjects.size());
    assertSame(dbsDocumentConstraint, actualCachedObjects.get(0));
  }

  /**
   * Test {@link AbstractObjectCache#getTypedObjects(DBRProgressMonitor, DBSObject, Class)}.
   *
   * <ul>
   *   <li>Given {@link SimpleObjectCache} (default constructor).
   *   <li>When {@code Object}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getTypedObjects(DBRProgressMonitor, DBSObject,
   * Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List AbstractObjectCache.getTypedObjects(DBRProgressMonitor, DBSObject, Class)"
  })
  public void testGetTypedObjects_givenSimpleObjectCache_whenJavaLangObject_thenReturnEmpty()
      throws DBException {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    Class<Object> type = Object.class;

    // Act and Assert
    assertTrue(simpleObjectCache.getTypedObjects(monitor, dbsDocumentConstraint, type).isEmpty());
  }

  /**
   * Test {@link AbstractObjectCache#getTypedObjects(DBRProgressMonitor, DBSObject, Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getTypedObjects(DBRProgressMonitor, DBSObject,
   * Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List AbstractObjectCache.getTypedObjects(DBRProgressMonitor, DBSObject, Class)"
  })
  public void testGetTypedObjects_whenJavaLangObject_thenReturnSizeIsOne() throws DBException {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBSDocumentConstraint dbsDocumentConstraint2 =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    Class<Object> type = Object.class;

    // Act
    List<Object> actualTypedObjects =
        simpleObjectCache.getTypedObjects(monitor, dbsDocumentConstraint2, type);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    assertEquals(1, actualTypedObjects.size());
    assertSame(dbsDocumentConstraint, actualTypedObjects.get(0));
  }

  /**
   * Test {@link AbstractObjectCache#getCachedObject(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getId()} return {@code
   *       42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getCachedObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject AbstractObjectCache.getCachedObject(String)"})
  public void testGetCachedObject_givenDBPDataSourceContainerGetIdReturn42_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.setCaseSensitive(false);
    simpleObjectCache.cacheObject(dbvModel);

    // Act
    DBSObject actualCachedObject = simpleObjectCache.getCachedObject("Name");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualCachedObject);
  }

  /**
   * Test {@link AbstractObjectCache#getCachedObject(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getId()} return {@code
   *       42}.
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getCachedObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject AbstractObjectCache.getCachedObject(String)"})
  public void testGetCachedObject_givenDBPDataSourceContainerGetIdReturn42_whenNull() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.setCaseSensitive(false);
    simpleObjectCache.cacheObject(dbvModel);

    // Act
    DBSObject actualCachedObject = simpleObjectCache.getCachedObject(null);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualCachedObject);
  }

  /**
   * Test {@link AbstractObjectCache#getCachedObject(String)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer} {@link DBPDataSourceContainer#getId()} return {@code
   *       null}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getCachedObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject AbstractObjectCache.getCachedObject(String)"})
  public void testGetCachedObject_givenDBPDataSourceContainerGetIdReturnNull_thenCallsGetId() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn(null);
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.setCaseSensitive(false);
    simpleObjectCache.cacheObject(dbvModel);

    // Act
    DBSObject actualCachedObject = simpleObjectCache.getCachedObject("Name");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualCachedObject);
  }

  /**
   * Test {@link AbstractObjectCache#getCachedObject(String)}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(DBPDataSourceContainer)} with dataSourceContainer is
   *       {@link DBPDataSourceContainer}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getCachedObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject AbstractObjectCache.getCachedObject(String)"})
  public void testGetCachedObject_givenDBVModelWithDataSourceContainerIsDBPDataSourceContainer() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "DocumentKey");
    DBVEntity copy = new DBVEntity(container2, "DocumentKey", "DocumentKey");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbvEntityForeignKey);

    // Act
    DBSObject actualCachedObject = simpleObjectCache.getCachedObject("Name");

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualCachedObject);
  }

  /**
   * Test {@link AbstractObjectCache#getCachedObject(String)}.
   *
   * <ul>
   *   <li>Given {@link SimpleObjectCache} (default constructor).
   *   <li>When {@code Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getCachedObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject AbstractObjectCache.getCachedObject(String)"})
  public void testGetCachedObject_givenSimpleObjectCache_whenName_thenReturnNull() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    // Act and Assert
    assertNull(simpleObjectCache.getCachedObject("Name"));
  }

  /**
   * Test {@link AbstractObjectCache#getCachedObject(String)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getCachedObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject AbstractObjectCache.getCachedObject(String)"})
  public void testGetCachedObject_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    // Act
    DBSObject actualCachedObject = simpleObjectCache.getCachedObject("Name");

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(entity, atLeast(1)).getDataSource();
    assertNull(actualCachedObject);
  }

  /**
   * Test {@link AbstractObjectCache#getCachedObject(String)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getCachedObject(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject AbstractObjectCache.getCachedObject(String)"})
  public void testGetCachedObject_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "DocumentKey");
    DBVEntity entity = new DBVEntity(container, "DocumentKey", "DocumentKey");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbvEntityForeignKey);

    // Act
    DBSObject actualCachedObject = simpleObjectCache.getCachedObject("Name");

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(parent, atLeast(1)).getDataSource();
    assertNull(actualCachedObject);
  }

  /**
   * Test {@link AbstractObjectCache#getCacheSize()}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getSQLDialect()} return {@link
   *       BasicSQLDialect#INSTANCE}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getCacheSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AbstractObjectCache.getCacheSize()"})
  public void testGetCacheSize_givenDBPDataSourceGetSQLDialectReturnInstance_thenReturnOne() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    // Act
    int actualCacheSize = simpleObjectCache.getCacheSize();

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    assertEquals(1, actualCacheSize);
  }

  /**
   * Test {@link AbstractObjectCache#getCacheSize()}.
   *
   * <ul>
   *   <li>Given {@link SimpleObjectCache} (default constructor).
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getCacheSize()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AbstractObjectCache.getCacheSize()"})
  public void testGetCacheSize_givenSimpleObjectCache_thenReturnZero() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    // Act and Assert
    assertEquals(0, simpleObjectCache.getCacheSize());
  }

  /**
   * Test {@link AbstractObjectCache#cacheObject(DBSObject)}.
   *
   * <p>Method under test: {@link AbstractObjectCache#cacheObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.cacheObject(DBSObject)"})
  public void testCacheObject() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    // Act
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    List<DBSObject> cachedObjects = simpleObjectCache.getCachedObjects();
    assertEquals(1, cachedObjects.size());
    assertEquals(1, simpleObjectCache.getCacheSize());
    assertFalse(simpleObjectCache.isEmpty());
    assertSame(dbsDocumentConstraint, cachedObjects.get(0));
  }

  /**
   * Test {@link AbstractObjectCache#cacheObject(DBSObject)}.
   *
   * <p>Method under test: {@link AbstractObjectCache#cacheObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.cacheObject(DBSObject)"})
  public void testCacheObject2() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    // Act
    simpleObjectCache.cacheObject(dbvEntityForeignKey);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    List<DBSObject> cachedObjects = simpleObjectCache.getCachedObjects();
    assertEquals(1, cachedObjects.size());
    assertEquals(1, simpleObjectCache.getCacheSize());
    assertFalse(simpleObjectCache.isEmpty());
    assertSame(dbvEntityForeignKey, cachedObjects.get(0));
  }

  /**
   * Test {@link AbstractObjectCache#cacheObject(DBSObject)}.
   *
   * <p>Method under test: {@link AbstractObjectCache#cacheObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.cacheObject(DBSObject)"})
  public void testCacheObject3() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    // Act
    simpleObjectCache.cacheObject(dbvEntityForeignKey);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    List<DBSObject> cachedObjects = simpleObjectCache.getCachedObjects();
    assertEquals(1, cachedObjects.size());
    assertEquals(1, simpleObjectCache.getCacheSize());
    assertFalse(simpleObjectCache.isEmpty());
    assertSame(dbvEntityForeignKey, cachedObjects.get(0));
  }

  /**
   * Test {@link AbstractObjectCache#removeObject(DBSObject, boolean)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#removeObject(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.removeObject(DBSObject, boolean)"})
  public void testRemoveObject_given42_whenDBVModelWithIdIs42AndMapIsHashMap_thenCallsGetId() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity2 = new DBVEntity(container, copy, targetModel);

    // Act
    simpleObjectCache.removeObject(new DBVEntityForeignKey(entity2), true);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link AbstractObjectCache#removeObject(DBSObject, boolean)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer} {@link DBVContainer#getDataSource()} return {@link
   *       DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#removeObject(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.removeObject(DBSObject, boolean)"})
  public void testRemoveObject_givenDBVContainerGetDataSourceReturnDBPDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbvEntityForeignKey);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container2 = new DBVContainer(parent2, "Name");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container3, "Name", "Description Column Names");

    DBVEntity entity2 = new DBVEntity(container2, copy, targetModel);

    // Act
    simpleObjectCache.removeObject(new DBVEntityForeignKey(entity2), false);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSource2).getSQLDialect();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent2).getDataSource();
  }

  /**
   * Test {@link AbstractObjectCache#removeObject(DBSObject, boolean)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#removeObject(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.removeObject(DBSObject, boolean)"})
  public void testRemoveObject_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource2);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    simpleObjectCache.removeObject(new DBVEntityForeignKey(entity2), true);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSource2).getSQLDialect();
    verify(entity).getDataSource();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link AbstractObjectCache#removeObject(DBSObject, boolean)}.
   *
   * <ul>
   *   <li>When {@link DBSDocumentContainer} {@link DBSDocumentContainer#getDataSource()} return
   *       {@link DBPDataSource}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#removeObject(DBSObject, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.removeObject(DBSObject, boolean)"})
  public void testRemoveObject_whenDBSDocumentContainerGetDataSourceReturnDBPDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity2 = mock(DBSDocumentContainer.class);
    when(entity2.getDataSource()).thenReturn(dbpDataSource2);

    // Act
    simpleObjectCache.removeObject(new DBSDocumentConstraint(entity2), true);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSource2).getSQLDialect();
    verify(entity).getDataSource();
    verify(entity2).getDataSource();
  }

  /**
   * Test {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject, String, Class)} with
   * {@code DBRProgressMonitor}, {@code DBSObject}, {@code String}, {@code Class}.
   *
   * <p>Method under test: {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject,
   * String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AbstractObjectCache.getObject(DBRProgressMonitor, DBSObject, String, Class)"
  })
  public void testGetObjectWithDBRProgressMonitorDBSObjectStringClass() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "DocumentKey");
    DBVEntity copy = new DBVEntity(container2, "DocumentKey", "DocumentKey");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbvEntityForeignKey);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    Class<Object> type = Object.class;

    // Act
    Object actualObject = simpleObjectCache.getObject(monitor, dbsDocumentConstraint, "Name", type);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualObject);
  }

  /**
   * Test {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject, String, Class)} with
   * {@code DBRProgressMonitor}, {@code DBSObject}, {@code String}, {@code Class}.
   *
   * <p>Method under test: {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject,
   * String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AbstractObjectCache.getObject(DBRProgressMonitor, DBSObject, String, Class)"
  })
  public void testGetObjectWithDBRProgressMonitorDBSObjectStringClass2() throws DBException {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbvModel);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    Class<Object> type = Object.class;

    // Act
    Object actualObject = simpleObjectCache.getObject(monitor, dbsDocumentConstraint, "42", type);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertSame(dbvModel, actualObject);
  }

  /**
   * Test {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject, String, Class)} with
   * {@code DBRProgressMonitor}, {@code DBSObject}, {@code String}, {@code Class}.
   *
   * <p>Method under test: {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject,
   * String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AbstractObjectCache.getObject(DBRProgressMonitor, DBSObject, String, Class)"
  })
  public void testGetObjectWithDBRProgressMonitorDBSObjectStringClass3() throws DBException {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn(null);
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbvModel);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    Class<Object> type = Object.class;

    // Act
    Object actualObject = simpleObjectCache.getObject(monitor, dbsDocumentConstraint, "42", type);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualObject);
  }

  /**
   * Test {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject, String, Class)} with
   * {@code DBRProgressMonitor}, {@code DBSObject}, {@code String}, {@code Class}.
   *
   * <p>Method under test: {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject,
   * String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AbstractObjectCache.getObject(DBRProgressMonitor, DBSObject, String, Class)"
  })
  public void testGetObjectWithDBRProgressMonitorDBSObjectStringClass4() throws DBException {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn(null);
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbvModel);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    Class<Object> type = Object.class;

    // Act
    Object actualObject = simpleObjectCache.getObject(monitor, dbsDocumentConstraint, null, type);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertNull(actualObject);
  }

  /**
   * Test {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject, String, Class)} with
   * {@code DBRProgressMonitor}, {@code DBSObject}, {@code String}, {@code Class}.
   *
   * <p>Method under test: {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject,
   * String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AbstractObjectCache.getObject(DBRProgressMonitor, DBSObject, String, Class)"
  })
  public void testGetObjectWithDBRProgressMonitorDBSObjectStringClass5() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.setCaseSensitive(false);
    simpleObjectCache.cacheObject(dbvModel);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    Class<Object> type = Object.class;

    // Act
    Object actualObject = simpleObjectCache.getObject(monitor, dbsDocumentConstraint, "42", type);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertSame(dbvModel, actualObject);
  }

  /**
   * Test {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject, String, Class)} with
   * {@code DBRProgressMonitor}, {@code DBSObject}, {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Given {@link SimpleObjectCache} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject,
   * String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AbstractObjectCache.getObject(DBRProgressMonitor, DBSObject, String, Class)"
  })
  public void testGetObjectWithDBRProgressMonitorDBSObjectStringClass_givenSimpleObjectCache()
      throws DBException {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    Class<Object> type = Object.class;

    // Act and Assert
    assertNull(simpleObjectCache.getObject(monitor, dbsDocumentConstraint, "Name", type));
  }

  /**
   * Test {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject, String, Class)} with
   * {@code DBRProgressMonitor}, {@code DBSObject}, {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject,
   * String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AbstractObjectCache.getObject(DBRProgressMonitor, DBSObject, String, Class)"
  })
  public void testGetObjectWithDBRProgressMonitorDBSObjectStringClass_thenCallsGetDataSource()
      throws DBException {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBSDocumentConstraint dbsDocumentConstraint2 =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    Class<Object> type = Object.class;

    // Act
    Object actualObject =
        simpleObjectCache.getObject(monitor, dbsDocumentConstraint2, "Name", type);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(entity, atLeast(1)).getDataSource();
    assertNull(actualObject);
  }

  /**
   * Test {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject, String, Class)} with
   * {@code DBRProgressMonitor}, {@code DBSObject}, {@code String}, {@code Class}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getObject(DBRProgressMonitor, DBSObject,
   * String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AbstractObjectCache.getObject(DBRProgressMonitor, DBSObject, String, Class)"
  })
  public void testGetObjectWithDBRProgressMonitorDBSObjectStringClass_thenCallsGetDataSource2()
      throws DBException {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "DocumentKey");
    DBVEntity entity = new DBVEntity(container, "DocumentKey", "DocumentKey");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbvEntityForeignKey);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    Class<Object> type = Object.class;

    // Act
    Object actualObject = simpleObjectCache.getObject(monitor, dbsDocumentConstraint, "Name", type);

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(parent, atLeast(1)).getDataSource();
    assertNull(actualObject);
  }

  /**
   * Test {@link AbstractObjectCache#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getSQLDialect()} return {@link
   *       BasicSQLDialect#INSTANCE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractObjectCache.isEmpty()"})
  public void testIsEmpty_givenDBPDataSourceGetSQLDialectReturnInstance_thenReturnFalse() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    // Act
    boolean actualIsEmptyResult = simpleObjectCache.isEmpty();

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    assertFalse(actualIsEmptyResult);
  }

  /**
   * Test {@link AbstractObjectCache#isEmpty()}.
   *
   * <ul>
   *   <li>Given {@link SimpleObjectCache} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractObjectCache.isEmpty()"})
  public void testIsEmpty_givenSimpleObjectCache_thenReturnTrue() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    // Act and Assert
    assertTrue(simpleObjectCache.isEmpty());
  }

  /**
   * Test {@link AbstractObjectCache#isFullyCached()}.
   *
   * <ul>
   *   <li>Given {@link SimpleObjectCache} (default constructor) FullCache is {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#isFullyCached()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractObjectCache.isFullyCached()"})
  public void testIsFullyCached_givenSimpleObjectCacheFullCacheIsTrue_thenReturnTrue() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.setFullCache(true);

    // Act and Assert
    assertTrue(simpleObjectCache.isFullyCached());
  }

  /**
   * Test {@link AbstractObjectCache#isFullyCached()}.
   *
   * <ul>
   *   <li>Given {@link SimpleObjectCache} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#isFullyCached()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractObjectCache.isFullyCached()"})
  public void testIsFullyCached_givenSimpleObjectCache_thenReturnFalse() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    // Act and Assert
    assertFalse(simpleObjectCache.isFullyCached());
  }

  /**
   * Test {@link AbstractObjectCache#setFullCache(boolean)}.
   *
   * <p>Method under test: {@link AbstractObjectCache#setFullCache(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.setFullCache(boolean)"})
  public void testSetFullCache() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    // Act
    simpleObjectCache.setFullCache(true);

    // Assert
    assertTrue(simpleObjectCache.isFullyCached());
  }

  /**
   * Test {@link AbstractObjectCache#setCache(List)}.
   *
   * <ul>
   *   <li>Then {@link SimpleObjectCache} (default constructor) CacheSize is one.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#setCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.setCache(List)"})
  public void testSetCache_thenSimpleObjectCacheCacheSizeIsOne() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    ArrayList<DBSObject> objects = new ArrayList<>();
    objects.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Act
    simpleObjectCache.setCache(objects);

    // Assert
    assertEquals(1, simpleObjectCache.getCacheSize());
    assertFalse(simpleObjectCache.isEmpty());
    assertTrue(simpleObjectCache.isFullyCached());
    assertSame(objects, simpleObjectCache.getCachedObjects());
  }

  /**
   * Test {@link AbstractObjectCache#setCache(List)}.
   *
   * <ul>
   *   <li>Then {@link SimpleObjectCache} (default constructor) CacheSize is two.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#setCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.setCache(List)"})
  public void testSetCache_thenSimpleObjectCacheCacheSizeIsTwo() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    ArrayList<DBSObject> objects = new ArrayList<>();
    objects.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));
    objects.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Act
    simpleObjectCache.setCache(objects);

    // Assert
    assertEquals(2, simpleObjectCache.getCacheSize());
    assertFalse(simpleObjectCache.isEmpty());
    assertTrue(simpleObjectCache.isFullyCached());
    assertSame(objects, simpleObjectCache.getCachedObjects());
  }

  /**
   * Test {@link AbstractObjectCache#setCache(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then {@link SimpleObjectCache} (default constructor) CacheSize is zero.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#setCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.setCache(List)"})
  public void testSetCache_whenArrayList_thenSimpleObjectCacheCacheSizeIsZero() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    ArrayList<DBSObject> objects = new ArrayList<>();

    // Act
    simpleObjectCache.setCache(objects);

    // Assert
    assertEquals(0, simpleObjectCache.getCacheSize());
    assertTrue(simpleObjectCache.isEmpty());
    assertTrue(simpleObjectCache.isFullyCached());
    assertSame(objects, simpleObjectCache.getCachedObjects());
  }

  /**
   * Test {@link AbstractObjectCache#mergeCache(List)}.
   *
   * <p>Method under test: {@link AbstractObjectCache#mergeCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.mergeCache(List)"})
  public void testMergeCache() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbvEntityForeignKey);

    ArrayList<DBSObject> objects = new ArrayList<>();
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    objects.add(dbsDocumentConstraint);

    // Act
    simpleObjectCache.mergeCache(objects);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    List<DBSObject> cachedObjects = simpleObjectCache.getCachedObjects();
    assertEquals(1, cachedObjects.size());
    assertEquals(1, simpleObjectCache.getCacheSize());
    assertFalse(simpleObjectCache.isEmpty());
    assertSame(dbsDocumentConstraint, cachedObjects.get(0));
  }

  /**
   * Test {@link AbstractObjectCache#mergeCache(List)}.
   *
   * <ul>
   *   <li>Given {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)} RefEntityId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#mergeCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.mergeCache(List)"})
  public void testMergeCache_givenDBVEntityForeignKeyWithEntityIsDBVEntityRefEntityIdIs42() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("42");

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbvEntityForeignKey);

    ArrayList<DBSObject> objects = new ArrayList<>();
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    objects.add(dbsDocumentConstraint);

    // Act
    simpleObjectCache.mergeCache(objects);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    List<DBSObject> cachedObjects = simpleObjectCache.getCachedObjects();
    assertEquals(1, cachedObjects.size());
    assertEquals(1, simpleObjectCache.getCacheSize());
    assertFalse(simpleObjectCache.isEmpty());
    assertSame(dbsDocumentConstraint, cachedObjects.get(0));
  }

  /**
   * Test {@link AbstractObjectCache#mergeCache(List)}.
   *
   * <ul>
   *   <li>Given {@link SimpleObjectCache} (default constructor).
   *   <li>Then {@link SimpleObjectCache} (default constructor) CacheSize is two.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#mergeCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.mergeCache(List)"})
  public void testMergeCache_givenSimpleObjectCache_thenSimpleObjectCacheCacheSizeIsTwo() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    ArrayList<DBSObject> objects = new ArrayList<>();
    objects.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));
    objects.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Act
    simpleObjectCache.mergeCache(objects);

    // Assert
    assertEquals(2, simpleObjectCache.getCacheSize());
    assertFalse(simpleObjectCache.isEmpty());
    assertTrue(simpleObjectCache.isFullyCached());
    assertSame(objects, simpleObjectCache.getCachedObjects());
  }

  /**
   * Test {@link AbstractObjectCache#mergeCache(List)}.
   *
   * <ul>
   *   <li>Then {@link SimpleObjectCache} (default constructor) CachedObjects Empty.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#mergeCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.mergeCache(List)"})
  public void testMergeCache_thenSimpleObjectCacheCachedObjectsEmpty() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    // Act
    simpleObjectCache.mergeCache(new ArrayList<>());

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    assertEquals(0, simpleObjectCache.getCacheSize());
    assertTrue(simpleObjectCache.getCachedObjects().isEmpty());
    assertTrue(simpleObjectCache.isEmpty());
    assertTrue(simpleObjectCache.isFullyCached());
  }

  /**
   * Test {@link AbstractObjectCache#mergeCache(List)}.
   *
   * <ul>
   *   <li>Then {@link SimpleObjectCache} (default constructor) CachedObjects first {@link
   *       DBSDocumentConstraint}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#mergeCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.mergeCache(List)"})
  public void testMergeCache_thenSimpleObjectCacheCachedObjectsFirstDBSDocumentConstraint() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    ArrayList<DBSObject> objects = new ArrayList<>();
    objects.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Act
    simpleObjectCache.mergeCache(objects);

    // Assert that nothing has changed
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    List<DBSObject> cachedObjects = simpleObjectCache.getCachedObjects();
    assertEquals(1, cachedObjects.size());
    DBSObject getResult = cachedObjects.get(0);
    assertTrue(getResult instanceof DBSDocumentConstraint);
    assertEquals(1, simpleObjectCache.getCacheSize());
    assertFalse(simpleObjectCache.isEmpty());
    assertSame(dbsDocumentConstraint, getResult);
  }

  /**
   * Test {@link AbstractObjectCache#mergeCache(List)}.
   *
   * <ul>
   *   <li>Then {@link SimpleObjectCache} (default constructor) CachedObjects first {@link
   *       DBVEntityForeignKey}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#mergeCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.mergeCache(List)"})
  public void testMergeCache_thenSimpleObjectCacheCachedObjectsFirstDBVEntityForeignKey() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbvEntityForeignKey);

    ArrayList<DBSObject> objects = new ArrayList<>();
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    objects.add(new DBVEntityForeignKey(entity2));

    // Act
    simpleObjectCache.mergeCache(objects);

    // Assert that nothing has changed
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
    List<DBSObject> cachedObjects = simpleObjectCache.getCachedObjects();
    assertEquals(1, cachedObjects.size());
    DBSObject getResult = cachedObjects.get(0);
    assertTrue(getResult instanceof DBVEntityForeignKey);
    assertEquals(1, simpleObjectCache.getCacheSize());
    assertFalse(simpleObjectCache.isEmpty());
    assertSame(dbvEntityForeignKey, getResult);
  }

  /**
   * Test {@link AbstractObjectCache#mergeCache(List)}.
   *
   * <ul>
   *   <li>Then {@link SimpleObjectCache} (default constructor) CachedObjects is {@link
   *       ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#mergeCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.mergeCache(List)"})
  public void testMergeCache_thenSimpleObjectCacheCachedObjectsIsArrayList() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    ArrayList<DBSObject> objects = new ArrayList<>();
    objects.add(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Act
    simpleObjectCache.mergeCache(objects);

    // Assert
    assertEquals(1, simpleObjectCache.getCacheSize());
    assertFalse(simpleObjectCache.isEmpty());
    assertTrue(simpleObjectCache.isFullyCached());
    assertSame(objects, simpleObjectCache.getCachedObjects());
  }

  /**
   * Test {@link AbstractObjectCache#mergeCache(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then {@link SimpleObjectCache} (default constructor) CacheSize is zero.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#mergeCache(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.mergeCache(List)"})
  public void testMergeCache_whenArrayList_thenSimpleObjectCacheCacheSizeIsZero() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    ArrayList<DBSObject> objects = new ArrayList<>();

    // Act
    simpleObjectCache.mergeCache(objects);

    // Assert
    assertEquals(0, simpleObjectCache.getCacheSize());
    assertTrue(simpleObjectCache.isEmpty());
    assertTrue(simpleObjectCache.isFullyCached());
    assertSame(objects, simpleObjectCache.getCachedObjects());
  }

  /**
   * Test {@link AbstractObjectCache#isValidDuplicateObject(DBSObject)}.
   *
   * <p>Method under test: {@link AbstractObjectCache#isValidDuplicateObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractObjectCache.isValidDuplicateObject(DBSObject)"})
  public void testIsValidDuplicateObject() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    // Act
    boolean actualIsValidDuplicateObjectResult =
        simpleObjectCache.isValidDuplicateObject(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    assertFalse(actualIsValidDuplicateObjectResult);
  }

  /**
   * Test {@link AbstractObjectCache#detectCaseSensitivity(DBSObject)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#detectCaseSensitivity(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.detectCaseSensitivity(DBSObject)"})
  public void testDetectCaseSensitivity_given42_thenCallsGetId() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    simpleObjectCache.detectCaseSensitivity(new DBVEntityForeignKey(entity));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link AbstractObjectCache#detectCaseSensitivity(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#detectCaseSensitivity(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.detectCaseSensitivity(DBSObject)"})
  public void testDetectCaseSensitivity_thenCallsGetDataSource() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);

    // Act
    simpleObjectCache.detectCaseSensitivity(new DBSDocumentConstraint(entity));

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link AbstractObjectCache#detectCaseSensitivity(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#detectCaseSensitivity(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.detectCaseSensitivity(DBSObject)"})
  public void testDetectCaseSensitivity_thenCallsGetDataSource2() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    simpleObjectCache.detectCaseSensitivity(new DBVEntityForeignKey(entity));

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link AbstractObjectCache#isValidObject(DBRProgressMonitor, DBSObject, DBSObject)}.
   *
   * <p>Method under test: {@link AbstractObjectCache#isValidObject(DBRProgressMonitor, DBSObject,
   * DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean AbstractObjectCache.isValidObject(DBRProgressMonitor, DBSObject, DBSObject)"
  })
  public void testIsValidObject() throws DBException {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act and Assert
    assertTrue(
        simpleObjectCache.isValidObject(
            monitor,
            dbsDocumentConstraint,
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class))));
  }

  /**
   * Test {@link AbstractObjectCache#clearChildrenOf(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBSDocumentContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#clearChildrenOf(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.clearChildrenOf(DBSObject)"})
  public void testClearChildrenOf_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    // Act
    simpleObjectCache.clearChildrenOf(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
  }

  /**
   * Test {@link AbstractObjectCache#clearChildrenOf(DBSObject)}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#clearChildrenOf(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractObjectCache.clearChildrenOf(DBSObject)"})
  public void testClearChildrenOf_thenCallsGetDataSource2() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity dbvEntity = new DBVEntity(container, "Name", "Description Column Names");

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbvEntity);

    // Act
    simpleObjectCache.clearChildrenOf(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link AbstractObjectCache#getObjectName(DBSObject)}.
   *
   * <ul>
   *   <li>Then return {@code DocumentKey}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getObjectName(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectCache.getObjectName(DBSObject)"})
  public void testGetObjectName_thenReturnDocumentKey() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();

    // Act
    String actualObjectName =
        simpleObjectCache.getObjectName(
            new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    assertEquals("DocumentKey", actualObjectName);
  }

  /**
   * Test {@link AbstractObjectCache#getObjectName(DBSObject)}.
   *
   * <ul>
   *   <li>Then return {@code vfk_DocumentKey_?}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractObjectCache#getObjectName(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractObjectCache.getObjectName(DBSObject)"})
  public void testGetObjectName_thenReturnVfkDocumentKey() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "DocumentKey");
    DBVEntity entity = new DBVEntity(container, "DocumentKey", "DocumentKey");

    // Act
    String actualObjectName = simpleObjectCache.getObjectName(new DBVEntityForeignKey(entity));

    // Assert
    assertEquals("vfk_DocumentKey_?", actualObjectName);
  }
}
