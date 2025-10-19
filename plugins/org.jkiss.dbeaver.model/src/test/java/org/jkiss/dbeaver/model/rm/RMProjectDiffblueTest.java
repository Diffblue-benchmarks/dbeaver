package org.jkiss.dbeaver.model.rm;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.rm.RMProject.TimeRenderer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RMProjectDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Name is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMProject#RMProject()}
   *   <li>{@link RMProject#setCreateTime(Long)}
   *   <li>{@link RMProject#setCreator(String)}
   *   <li>{@link RMProject#setDescription(String)}
   *   <li>{@link RMProject#setId(String)}
   *   <li>{@link RMProject#setProjectPermissions(String[])}
   *   <li>{@link RMProject#setResourceTypes(RMResourceType[])}
   *   <li>{@link RMProject#setType(RMProjectType)}
   *   <li>{@link RMProject#getCreateTime()}
   *   <li>{@link RMProject#getCreator()}
   *   <li>{@link RMProject#getDescription()}
   *   <li>{@link RMProject#getId()}
   *   <li>{@link RMProject#getProjectPermissions()}
   *   <li>{@link RMProject#getResourceTypes()}
   *   <li>{@link RMProject#getType()}
   *   <li>{@link RMProject#isFolder()}
   *   <li>{@link RMProject#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RMProject.<init>()",
    "void RMProject.<init>(String)",
    "void RMProject.<init>(String, String, String, RMProjectType, Long, String, String[])",
    "Long RMProject.getCreateTime()",
    "String RMProject.getCreator()",
    "String RMProject.getDescription()",
    "String RMProject.getId()",
    "String[] RMProject.getProjectPermissions()",
    "RMResourceType[] RMProject.getResourceTypes()",
    "RMProjectType RMProject.getType()",
    "boolean RMProject.isFolder()",
    "void RMProject.setCreateTime(Long)",
    "void RMProject.setCreator(String)",
    "void RMProject.setDescription(String)",
    "void RMProject.setId(String)",
    "void RMProject.setProjectPermissions(String[])",
    "void RMProject.setResourceTypes(RMResourceType[])",
    "void RMProject.setType(RMProjectType)",
    "String RMProject.toString()"
  })
  public void testGettersAndSetters_thenReturnNameIsNull() {
    // Arrange and Act
    RMProject actualRmProject = new RMProject();
    actualRmProject.setCreateTime(1L);
    actualRmProject.setCreator("Creator");
    actualRmProject.setDescription("The characteristics of someone or something");
    actualRmProject.setId("42");
    String[] projectPermissions = new String[] {"Project Permissions"};
    actualRmProject.setProjectPermissions(projectPermissions);
    RMResourceType[] resourceTypes = new RMResourceType[] {new RMResourceType()};
    actualRmProject.setResourceTypes(resourceTypes);
    actualRmProject.setType(RMProjectType.GLOBAL);
    Long actualCreateTime = actualRmProject.getCreateTime();
    String actualCreator = actualRmProject.getCreator();
    String actualDescription = actualRmProject.getDescription();
    String actualId = actualRmProject.getId();
    String[] actualProjectPermissions = actualRmProject.getProjectPermissions();
    RMResourceType[] actualResourceTypes = actualRmProject.getResourceTypes();
    RMProjectType actualType = actualRmProject.getType();
    boolean actualIsFolderResult = actualRmProject.isFolder();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualRmProject.toString());
    assertEquals("Creator", actualCreator);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertNull(actualRmProject.getName());
    assertNull(actualRmProject.getChildren());
    assertEquals(1L, actualCreateTime.longValue());
    assertEquals(RMProjectType.GLOBAL, actualType);
    assertTrue(actualIsFolderResult);
    assertSame(projectPermissions, actualProjectPermissions);
    assertSame(resourceTypes, actualResourceTypes);
    assertArrayEquals(new String[] {"Project Permissions"}, actualProjectPermissions);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMProject#RMProject(String, String, String, RMProjectType, Long, String,
   *       String[])}
   *   <li>{@link RMProject#setCreateTime(Long)}
   *   <li>{@link RMProject#setCreator(String)}
   *   <li>{@link RMProject#setDescription(String)}
   *   <li>{@link RMProject#setId(String)}
   *   <li>{@link RMProject#setProjectPermissions(String[])}
   *   <li>{@link RMProject#setResourceTypes(RMResourceType[])}
   *   <li>{@link RMProject#setType(RMProjectType)}
   *   <li>{@link RMProject#getCreateTime()}
   *   <li>{@link RMProject#getCreator()}
   *   <li>{@link RMProject#getDescription()}
   *   <li>{@link RMProject#getId()}
   *   <li>{@link RMProject#getProjectPermissions()}
   *   <li>{@link RMProject#getResourceTypes()}
   *   <li>{@link RMProject#getType()}
   *   <li>{@link RMProject#isFolder()}
   *   <li>{@link RMProject#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RMProject.<init>()",
    "void RMProject.<init>(String)",
    "void RMProject.<init>(String, String, String, RMProjectType, Long, String, String[])",
    "Long RMProject.getCreateTime()",
    "String RMProject.getCreator()",
    "String RMProject.getDescription()",
    "String RMProject.getId()",
    "String[] RMProject.getProjectPermissions()",
    "RMResourceType[] RMProject.getResourceTypes()",
    "RMProjectType RMProject.getType()",
    "boolean RMProject.isFolder()",
    "void RMProject.setCreateTime(Long)",
    "void RMProject.setCreator(String)",
    "void RMProject.setDescription(String)",
    "void RMProject.setId(String)",
    "void RMProject.setProjectPermissions(String[])",
    "void RMProject.setResourceTypes(RMResourceType[])",
    "void RMProject.setType(RMProjectType)",
    "String RMProject.toString()"
  })
  public void testGettersAndSetters_when42_thenReturnName() {
    // Arrange
    String[] projectPermissions = new String[] {"Project Permissions"};

    // Act
    RMProject actualRmProject =
        new RMProject(
            "42",
            "Name",
            "The characteristics of someone or something",
            RMProjectType.GLOBAL,
            1L,
            "Creator",
            projectPermissions);
    actualRmProject.setCreateTime(1L);
    actualRmProject.setCreator("Creator");
    actualRmProject.setDescription("The characteristics of someone or something");
    actualRmProject.setId("42");
    String[] projectPermissions2 = new String[] {"Project Permissions"};
    actualRmProject.setProjectPermissions(projectPermissions2);
    RMResourceType[] resourceTypes = new RMResourceType[] {new RMResourceType()};
    actualRmProject.setResourceTypes(resourceTypes);
    actualRmProject.setType(RMProjectType.GLOBAL);
    Long actualCreateTime = actualRmProject.getCreateTime();
    String actualCreator = actualRmProject.getCreator();
    String actualDescription = actualRmProject.getDescription();
    String actualId = actualRmProject.getId();
    String[] actualProjectPermissions = actualRmProject.getProjectPermissions();
    RMResourceType[] actualResourceTypes = actualRmProject.getResourceTypes();
    RMProjectType actualType = actualRmProject.getType();
    boolean actualIsFolderResult = actualRmProject.isFolder();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualRmProject.toString());
    assertEquals("Creator", actualCreator);
    assertEquals("Name", actualRmProject.getName());
    assertEquals("The characteristics of someone or something", actualDescription);
    assertNull(actualRmProject.getChildren());
    assertEquals(1L, actualCreateTime.longValue());
    assertEquals(RMProjectType.GLOBAL, actualType);
    assertTrue(actualIsFolderResult);
    assertSame(projectPermissions2, actualProjectPermissions);
    assertSame(resourceTypes, actualResourceTypes);
    assertArrayEquals(new String[] {"Project Permissions"}, actualProjectPermissions);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMProject#RMProject(String)}
   *   <li>{@link RMProject#setCreateTime(Long)}
   *   <li>{@link RMProject#setCreator(String)}
   *   <li>{@link RMProject#setDescription(String)}
   *   <li>{@link RMProject#setId(String)}
   *   <li>{@link RMProject#setProjectPermissions(String[])}
   *   <li>{@link RMProject#setResourceTypes(RMResourceType[])}
   *   <li>{@link RMProject#setType(RMProjectType)}
   *   <li>{@link RMProject#getCreateTime()}
   *   <li>{@link RMProject#getCreator()}
   *   <li>{@link RMProject#getDescription()}
   *   <li>{@link RMProject#getId()}
   *   <li>{@link RMProject#getProjectPermissions()}
   *   <li>{@link RMProject#getResourceTypes()}
   *   <li>{@link RMProject#getType()}
   *   <li>{@link RMProject#isFolder()}
   *   <li>{@link RMProject#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void RMProject.<init>()",
    "void RMProject.<init>(String)",
    "void RMProject.<init>(String, String, String, RMProjectType, Long, String, String[])",
    "Long RMProject.getCreateTime()",
    "String RMProject.getCreator()",
    "String RMProject.getDescription()",
    "String RMProject.getId()",
    "String[] RMProject.getProjectPermissions()",
    "RMResourceType[] RMProject.getResourceTypes()",
    "RMProjectType RMProject.getType()",
    "boolean RMProject.isFolder()",
    "void RMProject.setCreateTime(Long)",
    "void RMProject.setCreator(String)",
    "void RMProject.setDescription(String)",
    "void RMProject.setId(String)",
    "void RMProject.setProjectPermissions(String[])",
    "void RMProject.setResourceTypes(RMResourceType[])",
    "void RMProject.setType(RMProjectType)",
    "String RMProject.toString()"
  })
  public void testGettersAndSetters_whenName_thenReturnName() {
    // Arrange and Act
    RMProject actualRmProject = new RMProject("Name");
    actualRmProject.setCreateTime(1L);
    actualRmProject.setCreator("Creator");
    actualRmProject.setDescription("The characteristics of someone or something");
    actualRmProject.setId("42");
    String[] projectPermissions = new String[] {"Project Permissions"};
    actualRmProject.setProjectPermissions(projectPermissions);
    RMResourceType[] resourceTypes = new RMResourceType[] {new RMResourceType()};
    actualRmProject.setResourceTypes(resourceTypes);
    actualRmProject.setType(RMProjectType.GLOBAL);
    Long actualCreateTime = actualRmProject.getCreateTime();
    String actualCreator = actualRmProject.getCreator();
    String actualDescription = actualRmProject.getDescription();
    String actualId = actualRmProject.getId();
    String[] actualProjectPermissions = actualRmProject.getProjectPermissions();
    RMResourceType[] actualResourceTypes = actualRmProject.getResourceTypes();
    RMProjectType actualType = actualRmProject.getType();
    boolean actualIsFolderResult = actualRmProject.isFolder();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualRmProject.toString());
    assertEquals("Creator", actualCreator);
    assertEquals("Name", actualRmProject.getName());
    assertEquals("The characteristics of someone or something", actualDescription);
    assertNull(actualRmProject.getChildren());
    assertEquals(1L, actualCreateTime.longValue());
    assertEquals(RMProjectType.GLOBAL, actualType);
    assertTrue(actualIsFolderResult);
    assertSame(projectPermissions, actualProjectPermissions);
    assertSame(resourceTypes, actualResourceTypes);
    assertArrayEquals(new String[] {"Project Permissions"}, actualProjectPermissions);
  }

  /**
   * Test {@link RMProject#getDisplayName()}.
   *
   * <ul>
   *   <li>Given createAnonymousProject.
   *   <li>Then return {@code Private}.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#getDisplayName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RMProject.getDisplayName()"})
  public void testGetDisplayName_givenCreateAnonymousProject_thenReturnPrivate() {
    // Arrange, Act and Assert
    assertEquals("Private", RMUtils.createAnonymousProject().getDisplayName());
  }

  /**
   * Test {@link RMProject#getDisplayName()}.
   *
   * <ul>
   *   <li>Given {@link RMProject#RMProject(String)} with {@code Name} Type is {@code GLOBAL}.
   *   <li>Then return {@code Shared}.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#getDisplayName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RMProject.getDisplayName()"})
  public void testGetDisplayName_givenRMProjectWithNameTypeIsGlobal_thenReturnShared() {
    // Arrange
    RMProject rmProject = new RMProject("Name");
    rmProject.setType(RMProjectType.GLOBAL);

    // Act and Assert
    assertEquals("Shared", rmProject.getDisplayName());
  }

  /**
   * Test {@link RMProject#getDisplayName()}.
   *
   * <ul>
   *   <li>Given {@link RMProject#RMProject(String)} with {@code Name} Type is {@code SHARED}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#getDisplayName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String RMProject.getDisplayName()"})
  public void testGetDisplayName_givenRMProjectWithNameTypeIsShared_thenReturnName() {
    // Arrange
    RMProject rmProject = new RMProject("Name");
    rmProject.setType(RMProjectType.SHARED);

    // Act and Assert
    assertEquals("Name", rmProject.getDisplayName());
  }

  /**
   * Test {@link RMProject#isShared()}.
   *
   * <ul>
   *   <li>Given createAnonymousProject.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#isShared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.isShared()"})
  public void testIsShared_givenCreateAnonymousProject_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(RMUtils.createAnonymousProject().isShared());
  }

  /**
   * Test {@link RMProject#isShared()}.
   *
   * <ul>
   *   <li>Given {@link RMProject#RMProject(String)} with {@code Name} Type is {@code GLOBAL}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#isShared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.isShared()"})
  public void testIsShared_givenRMProjectWithNameTypeIsGlobal_thenReturnTrue() {
    // Arrange
    RMProject rmProject = new RMProject("Name");
    rmProject.setType(RMProjectType.GLOBAL);

    // Act and Assert
    assertTrue(rmProject.isShared());
  }

  /**
   * Test {@link RMProject#isShared()}.
   *
   * <ul>
   *   <li>Given {@link RMProject#RMProject(String)} with {@code Name} Type is {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#isShared()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.isShared()"})
  public void testIsShared_givenRMProjectWithNameTypeIsNull_thenReturnFalse() {
    // Arrange
    RMProject rmProject = new RMProject("Name");
    rmProject.setType(null);

    // Act and Assert
    assertFalse(rmProject.isShared());
  }

  /**
   * Test {@link RMProject#isGlobal()}.
   *
   * <ul>
   *   <li>Given createAnonymousProject.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#isGlobal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.isGlobal()"})
  public void testIsGlobal_givenCreateAnonymousProject_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(RMUtils.createAnonymousProject().isGlobal());
  }

  /**
   * Test {@link RMProject#isGlobal()}.
   *
   * <ul>
   *   <li>Given {@link RMProject#RMProject(String)} with {@code Name} Type is {@link
   *       RMProjectType#GLOBAL}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#isGlobal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.isGlobal()"})
  public void testIsGlobal_givenRMProjectWithNameTypeIsGlobal_thenReturnTrue() {
    // Arrange
    RMProject rmProject = new RMProject("Name");
    rmProject.setType(RMProjectType.GLOBAL);

    // Act and Assert
    assertTrue(rmProject.isGlobal());
  }

  /**
   * Test {@link RMProject#equals(Object)}, and {@link RMProject#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMProject#equals(Object)}
   *   <li>{@link RMProject#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.equals(Object)", "int RMProject.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();
    RMProject createAnonymousProjectResult2 = RMUtils.createAnonymousProject();

    // Act and Assert
    assertEquals(createAnonymousProjectResult, createAnonymousProjectResult2);
    assertEquals(createAnonymousProjectResult.hashCode(), createAnonymousProjectResult2.hashCode());
  }

  /**
   * Test {@link RMProject#equals(Object)}, and {@link RMProject#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link RMProject#equals(Object)}
   *   <li>{@link RMProject#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.equals(Object)", "int RMProject.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();

    // Act and Assert
    assertEquals(createAnonymousProjectResult, createAnonymousProjectResult);
    int expectedHashCodeResult = createAnonymousProjectResult.hashCode();
    assertEquals(expectedHashCodeResult, createAnonymousProjectResult.hashCode());
  }

  /**
   * Test {@link RMProject#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.equals(Object)", "int RMProject.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    RMProject rmProject = new RMProject("Name");

    // Act and Assert
    assertNotEquals(rmProject, RMUtils.createAnonymousProject());
  }

  /**
   * Test {@link RMProject#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.equals(Object)", "int RMProject.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();
    createAnonymousProjectResult.setDescription("The characteristics of someone or something");

    // Act and Assert
    assertNotEquals(createAnonymousProjectResult, RMUtils.createAnonymousProject());
  }

  /**
   * Test {@link RMProject#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.equals(Object)", "int RMProject.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();
    createAnonymousProjectResult.setName("Name");

    // Act and Assert
    assertNotEquals(createAnonymousProjectResult, RMUtils.createAnonymousProject());
  }

  /**
   * Test {@link RMProject#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.equals(Object)", "int RMProject.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(RMUtils.createAnonymousProject(), null);
  }

  /**
   * Test {@link RMProject#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.equals(Object)", "int RMProject.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(RMUtils.createAnonymousProject(), "Different type to RMProject");
  }

  /**
   * Test {@link RMProject#hasProjectPermission(String)}.
   *
   * <p>Method under test: {@link RMProject#hasProjectPermission(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.hasProjectPermission(String)"})
  public void testHasProjectPermission() {
    // Arrange
    RMProject rmProject = new RMProject("Name");
    rmProject.setProjectPermissions(new String[] {"foo"});

    // Act and Assert
    assertFalse(rmProject.hasProjectPermission(null));
  }

  /**
   * Test {@link RMProject#hasProjectPermission(String)}.
   *
   * <p>Method under test: {@link RMProject#hasProjectPermission(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.hasProjectPermission(String)"})
  public void testHasProjectPermission2() {
    // Arrange
    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();
    createAnonymousProjectResult.setProjectPermissions(new String[] {});

    // Act and Assert
    assertFalse(createAnonymousProjectResult.hasProjectPermission("Permission"));
  }

  /**
   * Test {@link RMProject#hasProjectPermission(String)}.
   *
   * <p>Method under test: {@link RMProject#hasProjectPermission(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.hasProjectPermission(String)"})
  public void testHasProjectPermission3() {
    // Arrange
    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();
    createAnonymousProjectResult.setProjectPermissions(new String[] {null});

    // Act and Assert
    assertFalse(createAnonymousProjectResult.hasProjectPermission("Permission"));
  }

  /**
   * Test {@link RMProject#hasProjectPermission(String)}.
   *
   * <ul>
   *   <li>Given createAnonymousProject.
   *   <li>When {@code Permission}.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#hasProjectPermission(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.hasProjectPermission(String)"})
  public void testHasProjectPermission_givenCreateAnonymousProject_whenPermission() {
    // Arrange, Act and Assert
    assertFalse(RMUtils.createAnonymousProject().hasProjectPermission("Permission"));
  }

  /**
   * Test {@link RMProject#hasProjectPermission(String)}.
   *
   * <ul>
   *   <li>Given {@link RMProject#RMProject(String)} with {@code Name} ProjectPermissions is {@code
   *       null}.
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#hasProjectPermission(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.hasProjectPermission(String)"})
  public void testHasProjectPermission_givenRMProjectWithNameProjectPermissionsIsNull_whenNull() {
    // Arrange
    RMProject rmProject = new RMProject("Name");
    rmProject.setProjectPermissions(null);

    // Act and Assert
    assertFalse(rmProject.hasProjectPermission(null));
  }

  /**
   * Test {@link RMProject#hasProjectPermission(String)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link RMProject#hasProjectPermission(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean RMProject.hasProjectPermission(String)"})
  public void testHasProjectPermission_thenReturnTrue() {
    // Arrange
    RMProject createAnonymousProjectResult = RMUtils.createAnonymousProject();
    createAnonymousProjectResult.setProjectPermissions(new String[] {"Permission"});

    // Act and Assert
    assertTrue(createAnonymousProjectResult.hasProjectPermission("Permission"));
  }

  /**
   * Test TimeRenderer {@link TimeRenderer#transform(RMProject, Object)} with {@code RMProject},
   * {@code Object}.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then return {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link TimeRenderer#transform(RMProject, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TimeRenderer.transform(RMProject, Object)"})
  public void testTimeRendererTransformWithRMProjectObject_whenRename_thenReturnRename()
      throws IllegalArgumentException {
    // Arrange
    TimeRenderer timeRenderer = new TimeRenderer();
    Object object = DBPEvent.RENAME;

    // Act
    Object actualTransformResult = timeRenderer.transform(RMUtils.createAnonymousProject(), object);

    // Assert
    assertSame(object, actualTransformResult);
  }
}
