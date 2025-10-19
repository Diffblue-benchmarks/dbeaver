package org.jkiss.dbeaver.model.impl.jdbc.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.impl.jdbc.struct.JDBCDataType;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LocalCacheProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JDBCObjectCacheDiffblueTest {
  @Mock private DBSObject dBSObject;

  @InjectMocks private JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jDBCBasicDataTypeCache;

  /**
   * Test {@link JDBCObjectCache#setMaximumCacheSize(int)}.
   *
   * <ul>
   *   <li>Then calls {@link DBDAttributeBindingCustom#getPrecision()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectCache#setMaximumCacheSize(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCObjectCache.setMaximumCacheSize(int)"})
  public void testSetMaximumCacheSize_thenCallsGetPrecision() {
    // Arrange
    DBDAttributeBindingCustom typed = mock(DBDAttributeBindingCustom.class);
    when(typed.getTypeID()).thenReturn(1);
    when(typed.getPrecision()).thenReturn(1);
    when(typed.getScale()).thenReturn(1);
    when(typed.getTypeName()).thenReturn("Type Name");
    JDBCDataType<DBSObject> jdbcDataType =
        new JDBCDataType<>(null, 42, "Name", "Remarks", true, true, 1, 1, 3);

    JDBCDataType<DBSObject> jdbcDataType2 = new JDBCDataType<>(jdbcDataType, typed);
    JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jdbcBasicDataTypeCache =
        new JDBCBasicDataTypeCache<>(jdbcDataType2);

    // Act
    jdbcBasicDataTypeCache.setMaximumCacheSize(3);

    // Assert
    verify(typed).getPrecision();
    verify(typed, atLeast(1)).getScale();
    verify(typed).getTypeID();
    verify(typed).getTypeName();
  }

  /**
   * Test {@link JDBCObjectCache#getAllObjects(DBRProgressMonitor, DBSObject)}.
   *
   * <ul>
   *   <li>When {@link DBRProgressMonitor} {@link DBRProgressMonitor#isCanceled()} return {@code
   *       true}.
   *   <li>Then calls {@link DBRProgressMonitor#isCanceled()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectCache#getAllObjects(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List JDBCObjectCache.getAllObjects(DBRProgressMonitor, DBSObject)"})
  public void testGetAllObjects_whenDBRProgressMonitorIsCanceledReturnTrue_thenCallsIsCanceled()
      throws DBException {
    // Arrange
    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(true);
    when(monitor.isForceCacheUsage()).thenReturn(false);

    // Act
    List<JDBCDataType> actualAllObjects = jDBCBasicDataTypeCache.getAllObjects(monitor, dBSObject);

    // Assert
    verify(monitor).isCanceled();
    verify(monitor, atLeast(1)).isForceCacheUsage();
    assertTrue(actualAllObjects.isEmpty());
  }

  /**
   * Test {@link JDBCObjectCache#getAllObjects(DBRProgressMonitor, DBSObject)}.
   *
   * <ul>
   *   <li>When {@link DBRProgressMonitor} {@link DBRProgressMonitor#isForceCacheUsage()} return
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectCache#getAllObjects(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List JDBCObjectCache.getAllObjects(DBRProgressMonitor, DBSObject)"})
  public void testGetAllObjects_whenDBRProgressMonitorIsForceCacheUsageReturnTrue()
      throws DBException {
    // Arrange
    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isForceCacheUsage()).thenReturn(true);

    // Act
    List<JDBCDataType> actualAllObjects = jDBCBasicDataTypeCache.getAllObjects(monitor, dBSObject);

    // Assert
    verify(monitor).isForceCacheUsage();
    assertTrue(actualAllObjects.isEmpty());
  }

  /**
   * Test {@link JDBCObjectCache#getObject(DBRProgressMonitor, DBSObject, String)} with {@code
   * monitor}, {@code owner}, {@code name}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectCache#getObject(DBRProgressMonitor, DBSObject, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject JDBCObjectCache.getObject(DBRProgressMonitor, DBSObject, String)"})
  public void testGetObjectWithMonitorOwnerName_thenReturnNull() throws DBException {
    // Arrange, Act and Assert
    assertNull(
        jDBCBasicDataTypeCache.getObject(
            new LocalCacheProgressMonitor(new LoggingProgressMonitor()),
            dBSObject,
            "org.jkiss.dbeaver.application"));
  }

  /**
   * Test {@link JDBCObjectCache#loadObjects(DBRProgressMonitor, DBSObject)}.
   *
   * <ul>
   *   <li>When {@link DBRProgressMonitor} {@link DBRProgressMonitor#isCanceled()} return {@code
   *       true}.
   *   <li>Then calls {@link DBRProgressMonitor#isCanceled()}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectCache#loadObjects(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCObjectCache.loadObjects(DBRProgressMonitor, DBSObject)"})
  public void testLoadObjects_whenDBRProgressMonitorIsCanceledReturnTrue_thenCallsIsCanceled()
      throws DBException {
    // Arrange
    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(true);
    when(monitor.isForceCacheUsage()).thenReturn(false);

    // Act
    jDBCBasicDataTypeCache.loadObjects(monitor, dBSObject);

    // Assert
    verify(monitor).isCanceled();
    verify(monitor).isForceCacheUsage();
  }

  /**
   * Test {@link JDBCObjectCache#loadObjects(DBRProgressMonitor, DBSObject)}.
   *
   * <ul>
   *   <li>When {@link DBRProgressMonitor} {@link DBRProgressMonitor#isForceCacheUsage()} return
   *       {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCObjectCache#loadObjects(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void JDBCObjectCache.loadObjects(DBRProgressMonitor, DBSObject)"})
  public void testLoadObjects_whenDBRProgressMonitorIsForceCacheUsageReturnTrue()
      throws DBException {
    // Arrange
    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isForceCacheUsage()).thenReturn(true);

    // Act
    jDBCBasicDataTypeCache.loadObjects(monitor, dBSObject);

    // Assert
    verify(monitor).isForceCacheUsage();
  }

  /**
   * Test {@link JDBCObjectCache#getCacheName()}.
   *
   * <p>Method under test: {@link JDBCObjectCache#getCacheName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String JDBCObjectCache.getCacheName()"})
  public void testGetCacheName() {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jdbcBasicDataTypeCache =
        new JDBCBasicDataTypeCache<>(dbsDocumentConstraint);

    // Act and Assert
    assertEquals("JDBCBasicDataTypeCache", jdbcBasicDataTypeCache.getCacheName());
  }

  /**
   * Test {@link JDBCObjectCache#handleCacheReadError(Exception)}.
   *
   * <p>Method under test: {@link JDBCObjectCache#handleCacheReadError(Exception)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean JDBCObjectCache.handleCacheReadError(Exception)"})
  public void testHandleCacheReadError() {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    JDBCBasicDataTypeCache<DBSObject, JDBCDataType> jdbcBasicDataTypeCache =
        new JDBCBasicDataTypeCache<>(dbsDocumentConstraint);

    // Act and Assert
    assertFalse(jdbcBasicDataTypeCache.handleCacheReadError(new Exception()));
  }
}
