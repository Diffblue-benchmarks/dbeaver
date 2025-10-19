package org.jkiss.dbeaver.model.rm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RMProjectPermissionDiffblueTest {
  /**
   * Test {@link RMProjectPermission#fromPermission(String)}.
   *
   * <ul>
   *   <li>Then return {@code DATA_SOURCES_VIEW}.
   * </ul>
   *
   * <p>Method under test: {@link RMProjectPermission#fromPermission(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RMProjectPermission RMProjectPermission.fromPermission(String)"})
  public void testFromPermission_thenReturnDataSourcesView() {
    // Arrange, Act and Assert
    assertEquals(
        RMProjectPermission.DATA_SOURCES_VIEW,
        RMProjectPermission.fromPermission(RMConstants.PERMISSION_PROJECT_DATASOURCES_VIEW));
  }

  /**
   * Test {@link RMProjectPermission#fromPermission(String)}.
   *
   * <ul>
   *   <li>When {@code Permission}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RMProjectPermission#fromPermission(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RMProjectPermission RMProjectPermission.fromPermission(String)"})
  public void testFromPermission_whenPermission_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(RMProjectPermission.fromPermission("Permission"));
  }

  /**
   * Test {@link RMProjectPermission#getPermissionId()}.
   *
   * <p>Method under test: {@link RMProjectPermission#getPermissionId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RMProjectPermission.getPermissionId()"})
  public void testGetPermissionId() {
    // Arrange, Act and Assert
    assertEquals(
        RMConstants.PERMISSION_PROJECT_DATASOURCES_VIEW,
        RMProjectPermission.valueOf("DATA_SOURCES_VIEW").getPermissionId());
  }

  /**
   * Test {@link RMProjectPermission#getAllPermissions()}.
   *
   * <ul>
   *   <li>Given {@code DATA_SOURCES_EDIT}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link RMProjectPermission#getAllPermissions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set RMProjectPermission.getAllPermissions()"})
  public void testGetAllPermissions_givenDataSourcesEdit_thenReturnSizeIsTwo() {
    // Arrange and Act
    Set<String> actualAllPermissions = RMProjectPermission.DATA_SOURCES_EDIT.getAllPermissions();

    // Assert
    assertEquals(2, actualAllPermissions.size());
    assertTrue(actualAllPermissions.contains(RMConstants.PERMISSION_PROJECT_DATASOURCES_EDIT));
    assertTrue(actualAllPermissions.contains(RMConstants.PERMISSION_PROJECT_DATASOURCES_VIEW));
  }

  /**
   * Test {@link RMProjectPermission#getAllPermissions()}.
   *
   * <ul>
   *   <li>Given {@code DATA_SOURCES_VIEW}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link RMProjectPermission#getAllPermissions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set RMProjectPermission.getAllPermissions()"})
  public void testGetAllPermissions_givenDataSourcesView_thenReturnSizeIsOne() {
    // Arrange and Act
    Set<String> actualAllPermissions = RMProjectPermission.DATA_SOURCES_VIEW.getAllPermissions();

    // Assert
    assertEquals(1, actualAllPermissions.size());
    assertTrue(actualAllPermissions.contains(RMConstants.PERMISSION_PROJECT_DATASOURCES_VIEW));
  }
}
