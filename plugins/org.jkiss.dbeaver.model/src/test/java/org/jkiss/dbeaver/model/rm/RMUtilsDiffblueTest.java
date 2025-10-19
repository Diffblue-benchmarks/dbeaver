package org.jkiss.dbeaver.model.rm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import org.jkiss.dbeaver.DBException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RMUtilsDiffblueTest {
  /**
   * Test {@link RMUtils#getProjectName(String)}.
   *
   * <ul>
   *   <li>When {@code myproject_}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link RMUtils#getProjectName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RMUtils.getProjectName(String)"})
  public void testGetProjectName_whenMyproject_thenReturnEmptyString() throws DBException {
    // Arrange, Act and Assert
    assertEquals("", RMUtils.getProjectName("myproject_"));
  }

  /**
   * Test {@link RMUtils#getProjectName(String)}.
   *
   * <ul>
   *   <li>When {@code myproject}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link RMUtils#getProjectName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RMUtils.getProjectName(String)"})
  public void testGetProjectName_whenMyproject_thenThrowDBException() throws DBException {
    // Arrange, Act and Assert
    assertThrows(DBException.class, () -> RMUtils.getProjectName("myproject"));
  }

  /**
   * Test {@link RMUtils#getProjectPathById(String)}.
   *
   * <ul>
   *   <li>When {@code myproject}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link RMUtils#getProjectPathById(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path RMUtils.getProjectPathById(String)"})
  public void testGetProjectPathById_whenMyproject_thenThrowDBException() throws DBException {
    // Arrange, Act and Assert
    assertThrows(DBException.class, () -> RMUtils.getProjectPathById("myproject"));
  }

  /**
   * Test {@link RMUtils#parseProjectPermissions(Set)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashSet#HashSet()} add {@code 42}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link RMUtils#parseProjectPermissions(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set RMUtils.parseProjectPermissions(Set)"})
  public void testParseProjectPermissions_given42_whenHashSetAdd42_thenReturnEmpty() {
    // Arrange
    HashSet<String> permissions = new HashSet<>();
    permissions.add("42");
    permissions.add("foo");

    // Act
    Set<String> actualParseProjectPermissionsResult = RMUtils.parseProjectPermissions(permissions);

    // Assert
    assertTrue(actualParseProjectPermissionsResult.isEmpty());
  }

  /**
   * Test {@link RMUtils#parseProjectPermissions(Set)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link HashSet#HashSet()} add {@code foo}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link RMUtils#parseProjectPermissions(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set RMUtils.parseProjectPermissions(Set)"})
  public void testParseProjectPermissions_givenFoo_whenHashSetAddFoo_thenReturnEmpty() {
    // Arrange
    HashSet<String> permissions = new HashSet<>();
    permissions.add("foo");

    // Act
    Set<String> actualParseProjectPermissionsResult = RMUtils.parseProjectPermissions(permissions);

    // Assert
    assertTrue(actualParseProjectPermissionsResult.isEmpty());
  }

  /**
   * Test {@link RMUtils#parseProjectPermissions(Set)}.
   *
   * <ul>
   *   <li>Then return {@link HashSet#HashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link RMUtils#parseProjectPermissions(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set RMUtils.parseProjectPermissions(Set)"})
  public void testParseProjectPermissions_thenReturnHashSet() {
    // Arrange
    HashSet<String> permissions = new HashSet<>();
    permissions.add(RMConstants.PERMISSION_PROJECT_DATASOURCES_VIEW);

    // Act
    Set<String> actualParseProjectPermissionsResult = RMUtils.parseProjectPermissions(permissions);

    // Assert
    assertEquals(permissions, actualParseProjectPermissionsResult);
  }

  /**
   * Test {@link RMUtils#parseProjectPermissions(Set)}.
   *
   * <ul>
   *   <li>When {@link HashSet#HashSet()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link RMUtils#parseProjectPermissions(Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set RMUtils.parseProjectPermissions(Set)"})
  public void testParseProjectPermissions_whenHashSet_thenReturnEmpty() {
    // Arrange and Act
    Set<String> actualParseProjectPermissionsResult =
        RMUtils.parseProjectPermissions(new HashSet<>());

    // Assert
    assertTrue(actualParseProjectPermissionsResult.isEmpty());
  }

  /**
   * Test {@link RMUtils#createAnonymousProject()}.
   *
   * <p>Method under test: {@link RMUtils#createAnonymousProject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RMProject RMUtils.createAnonymousProject()"})
  public void testCreateAnonymousProject() {
    // Arrange and Act
    RMProject actualCreateAnonymousProjectResult = RMUtils.createAnonymousProject();

    // Assert
    assertEquals("Private", actualCreateAnonymousProjectResult.getDisplayName());
    assertEquals("anonymous", actualCreateAnonymousProjectResult.getName());
    assertEquals("anonymous", actualCreateAnonymousProjectResult.getId());
    assertEquals("anonymous", actualCreateAnonymousProjectResult.toString());
    assertNull(actualCreateAnonymousProjectResult.getCreateTime());
    assertNull(actualCreateAnonymousProjectResult.getCreator());
    assertNull(actualCreateAnonymousProjectResult.getDescription());
    assertNull(actualCreateAnonymousProjectResult.getResourceTypes());
    assertNull(actualCreateAnonymousProjectResult.getChildren());
    assertEquals(2, actualCreateAnonymousProjectResult.getProjectPermissions().length);
    assertEquals(RMProjectType.USER, actualCreateAnonymousProjectResult.getType());
    assertFalse(actualCreateAnonymousProjectResult.isGlobal());
    assertTrue(actualCreateAnonymousProjectResult.isFolder());
  }

  /**
   * Test {@link RMUtils#makeProjectIdFromPath(Path, RMProjectType)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code test.txt}.
   *   <li>Then return {@code g_test.txt}.
   * </ul>
   *
   * <p>Method under test: {@link RMUtils#makeProjectIdFromPath(Path, RMProjectType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RMUtils.makeProjectIdFromPath(Path, RMProjectType)"})
  public void testMakeProjectIdFromPath_whenPropertyIsJavaIoTmpdirIsTestTxt_thenReturnGTestTxt() {
    // Arrange, Act and Assert
    assertEquals(
        "g_test.txt",
        RMUtils.makeProjectIdFromPath(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"), RMProjectType.GLOBAL));
  }
}
