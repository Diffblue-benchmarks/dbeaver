package org.jkiss.dbeaver.registry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.navigator.DBNBrowseSettings;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourceNavigatorSettingsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DataSourceNavigatorSettings#DataSourceNavigatorSettings()}
   *   <li>{@link DataSourceNavigatorSettings#setHideFolders(boolean)}
   *   <li>{@link DataSourceNavigatorSettings#setHideSchemas(boolean)}
   *   <li>{@link DataSourceNavigatorSettings#setHideVirtualModel(boolean)}
   *   <li>{@link DataSourceNavigatorSettings#setMergeEntities(boolean)}
   *   <li>{@link DataSourceNavigatorSettings#setShowOnlyEntities(boolean)}
   *   <li>{@link DataSourceNavigatorSettings#setShowSystemObjects(boolean)}
   *   <li>{@link DataSourceNavigatorSettings#setShowUtilityObjects(boolean)}
   *   <li>{@link DataSourceNavigatorSettings#isHideFolders()}
   *   <li>{@link DataSourceNavigatorSettings#isHideSchemas()}
   *   <li>{@link DataSourceNavigatorSettings#isHideVirtualModel()}
   *   <li>{@link DataSourceNavigatorSettings#isMergeEntities()}
   *   <li>{@link DataSourceNavigatorSettings#isShowOnlyEntities()}
   *   <li>{@link DataSourceNavigatorSettings#isShowSystemObjects()}
   *   <li>{@link DataSourceNavigatorSettings#isShowUtilityObjects()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataSourceNavigatorSettings.<init>()",
    "boolean DataSourceNavigatorSettings.isHideFolders()",
    "boolean DataSourceNavigatorSettings.isHideSchemas()",
    "boolean DataSourceNavigatorSettings.isHideVirtualModel()",
    "boolean DataSourceNavigatorSettings.isMergeEntities()",
    "boolean DataSourceNavigatorSettings.isShowOnlyEntities()",
    "boolean DataSourceNavigatorSettings.isShowSystemObjects()",
    "boolean DataSourceNavigatorSettings.isShowUtilityObjects()",
    "void DataSourceNavigatorSettings.setHideFolders(boolean)",
    "void DataSourceNavigatorSettings.setHideSchemas(boolean)",
    "void DataSourceNavigatorSettings.setHideVirtualModel(boolean)",
    "void DataSourceNavigatorSettings.setMergeEntities(boolean)",
    "void DataSourceNavigatorSettings.setShowOnlyEntities(boolean)",
    "void DataSourceNavigatorSettings.setShowSystemObjects(boolean)",
    "void DataSourceNavigatorSettings.setShowUtilityObjects(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DataSourceNavigatorSettings actualDataSourceNavigatorSettings =
        new DataSourceNavigatorSettings();
    actualDataSourceNavigatorSettings.setHideFolders(true);
    actualDataSourceNavigatorSettings.setHideSchemas(true);
    actualDataSourceNavigatorSettings.setHideVirtualModel(true);
    actualDataSourceNavigatorSettings.setMergeEntities(true);
    actualDataSourceNavigatorSettings.setShowOnlyEntities(true);
    actualDataSourceNavigatorSettings.setShowSystemObjects(true);
    actualDataSourceNavigatorSettings.setShowUtilityObjects(true);
    boolean actualIsHideFoldersResult = actualDataSourceNavigatorSettings.isHideFolders();
    boolean actualIsHideSchemasResult = actualDataSourceNavigatorSettings.isHideSchemas();
    boolean actualIsHideVirtualModelResult = actualDataSourceNavigatorSettings.isHideVirtualModel();
    boolean actualIsMergeEntitiesResult = actualDataSourceNavigatorSettings.isMergeEntities();
    boolean actualIsShowOnlyEntitiesResult = actualDataSourceNavigatorSettings.isShowOnlyEntities();
    boolean actualIsShowSystemObjectsResult =
        actualDataSourceNavigatorSettings.isShowSystemObjects();

    // Assert
    assertTrue(actualIsHideFoldersResult);
    assertTrue(actualIsHideSchemasResult);
    assertTrue(actualIsHideVirtualModelResult);
    assertTrue(actualIsMergeEntitiesResult);
    assertTrue(actualIsShowOnlyEntitiesResult);
    assertTrue(actualIsShowSystemObjectsResult);
    assertTrue(actualDataSourceNavigatorSettings.isShowUtilityObjects());
  }

  /**
   * Test {@link DataSourceNavigatorSettings#DataSourceNavigatorSettings(DBNBrowseSettings)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>Then return ShowSystemObjects.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataSourceNavigatorSettings#DataSourceNavigatorSettings(DBNBrowseSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataSourceNavigatorSettings.<init>(DBNBrowseSettings)"})
  public void testNewDataSourceNavigatorSettings_givenTrue_thenReturnShowSystemObjects() {
    // Arrange
    DataSourceNavigatorSettings copyFrom = new DataSourceNavigatorSettings();
    copyFrom.setShowSystemObjects(true);

    // Act
    DataSourceNavigatorSettings actualDataSourceNavigatorSettings =
        new DataSourceNavigatorSettings(copyFrom);

    // Assert
    assertFalse(actualDataSourceNavigatorSettings.isHideFolders());
    assertFalse(actualDataSourceNavigatorSettings.isHideSchemas());
    assertFalse(actualDataSourceNavigatorSettings.isHideVirtualModel());
    assertFalse(actualDataSourceNavigatorSettings.isMergeEntities());
    assertFalse(actualDataSourceNavigatorSettings.isShowOnlyEntities());
    assertFalse(actualDataSourceNavigatorSettings.isShowUtilityObjects());
    assertTrue(actualDataSourceNavigatorSettings.isShowSystemObjects());
  }

  /**
   * Test {@link DataSourceNavigatorSettings#DataSourceNavigatorSettings(DBNBrowseSettings)}.
   *
   * <ul>
   *   <li>Then return not ShowSystemObjects.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataSourceNavigatorSettings#DataSourceNavigatorSettings(DBNBrowseSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataSourceNavigatorSettings.<init>(DBNBrowseSettings)"})
  public void testNewDataSourceNavigatorSettings_thenReturnNotShowSystemObjects() {
    // Arrange and Act
    DataSourceNavigatorSettings actualDataSourceNavigatorSettings =
        new DataSourceNavigatorSettings(new DataSourceNavigatorSettings());

    // Assert
    assertFalse(actualDataSourceNavigatorSettings.isHideFolders());
    assertFalse(actualDataSourceNavigatorSettings.isHideSchemas());
    assertFalse(actualDataSourceNavigatorSettings.isHideVirtualModel());
    assertFalse(actualDataSourceNavigatorSettings.isMergeEntities());
    assertFalse(actualDataSourceNavigatorSettings.isShowOnlyEntities());
    assertFalse(actualDataSourceNavigatorSettings.isShowSystemObjects());
    assertFalse(actualDataSourceNavigatorSettings.isShowUtilityObjects());
  }
}
