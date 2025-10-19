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
import java.util.HashMap;
import java.util.List;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SimpleObjectCacheDiffblueTest {
  /**
   * Test {@link SimpleObjectCache#getAllObjects(DBRProgressMonitor, DBSObject)}.
   *
   * <ul>
   *   <li>Given {@link SimpleObjectCache} (default constructor).
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SimpleObjectCache#getAllObjects(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SimpleObjectCache.getAllObjects(DBRProgressMonitor, DBSObject)"})
  public void testGetAllObjects_givenSimpleObjectCache_thenReturnEmpty() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertTrue(
        simpleObjectCache
            .getAllObjects(monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)))
            .isEmpty());
  }

  /**
   * Test {@link SimpleObjectCache#getAllObjects(DBRProgressMonitor, DBSObject)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link SimpleObjectCache#getAllObjects(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List SimpleObjectCache.getAllObjects(DBRProgressMonitor, DBSObject)"})
  public void testGetAllObjects_thenReturnSizeIsOne() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    List<DBSObject> actualAllObjects =
        simpleObjectCache.getAllObjects(
            monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(entity).getDataSource();
    assertEquals(1, actualAllObjects.size());
    assertSame(dbsDocumentConstraint, actualAllObjects.get(0));
  }

  /**
   * Test {@link SimpleObjectCache#getObject(DBRProgressMonitor, DBSObject, String)} with {@code
   * monitor}, {@code owner}, {@code name}.
   *
   * <ul>
   *   <li>Given {@link DBVModel#DBVModel(String, Map)} with id is {@code 42} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleObjectCache#getObject(DBRProgressMonitor, DBSObject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject SimpleObjectCache.getObject(DBRProgressMonitor, DBSObject, String)"
  })
  public void testGetObjectWithMonitorOwnerName_givenDBVModelWithIdIs42AndMapIsHashMap() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "DocumentKey", "DocumentKey");
    simpleObjectCache.cacheObject(new DBVEntityForeignKey(entity));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertNull(
        simpleObjectCache.getObject(
            monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), "Name"));
  }

  /**
   * Test {@link SimpleObjectCache#getObject(DBRProgressMonitor, DBSObject, String)} with {@code
   * monitor}, {@code owner}, {@code name}.
   *
   * <ul>
   *   <li>Given {@link SimpleObjectCache} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link SimpleObjectCache#getObject(DBRProgressMonitor, DBSObject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject SimpleObjectCache.getObject(DBRProgressMonitor, DBSObject, String)"
  })
  public void testGetObjectWithMonitorOwnerName_givenSimpleObjectCache() {
    // Arrange
    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertNull(
        simpleObjectCache.getObject(
            monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), "Name"));
  }

  /**
   * Test {@link SimpleObjectCache#getObject(DBRProgressMonitor, DBSObject, String)} with {@code
   * monitor}, {@code owner}, {@code name}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getSQLDialect()}.
   * </ul>
   *
   * <p>Method under test: {@link SimpleObjectCache#getObject(DBRProgressMonitor, DBSObject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSObject SimpleObjectCache.getObject(DBRProgressMonitor, DBSObject, String)"
  })
  public void testGetObjectWithMonitorOwnerName_thenCallsGetSQLDialect() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    DBSObject actualObject =
        simpleObjectCache.getObject(
            monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)), "Name");

    // Assert
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(entity, atLeast(1)).getDataSource();
    assertNull(actualObject);
  }

  /**
   * Test new {@link SimpleObjectCache} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SimpleObjectCache}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SimpleObjectCache.<init>()"})
  public void testNewSimpleObjectCache() {
    // Arrange and Act
    SimpleObjectCache<DBSObject, DBSObject> actualSimpleObjectCache = new SimpleObjectCache<>();

    // Assert
    assertNull(actualSimpleObjectCache.getListOrderComparator());
    assertEquals(0, actualSimpleObjectCache.getCacheSize());
    assertFalse(actualSimpleObjectCache.isFullyCached());
    assertTrue(actualSimpleObjectCache.getCachedObjects().isEmpty());
    assertTrue(actualSimpleObjectCache.isEmpty());
    assertTrue(actualSimpleObjectCache.caseSensitive);
  }
}
