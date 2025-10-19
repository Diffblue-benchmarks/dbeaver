package org.jkiss.dbeaver.model.runtime.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBRFeatureDiffblueTest {
  /**
   * Test {@link DBRFeature#createCategory(String, String)} with {@code name}, {@code description}.
   *
   * <p>Method under test: {@link DBRFeature#createCategory(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBRFeature DBRFeature.createCategory(String, String)"})
  public void testCreateCategoryWithNameDescription() {
    // Arrange and Act
    DBRFeature actualCreateCategoryResult =
        DBRFeature.createCategory("Name", "The characteristics of someone or something");

    // Assert
    assertEquals("Name", actualCreateCategoryResult.getId());
    assertEquals("Name", actualCreateCategoryResult.getName());
    assertEquals(
        "The characteristics of someone or something", actualCreateCategoryResult.getDescription());
    assertNull(actualCreateCategoryResult.getCommandId());
    assertNull(actualCreateCategoryResult.getHelpURL());
    assertTrue(actualCreateCategoryResult.isAbstract());
    assertSame(DBRFeature.ROOT, actualCreateCategoryResult.getParentFeature());
  }

  /**
   * Test {@link DBRFeature#createCategory(DBRFeature, String, String)} with {@code parentFeature},
   * {@code name}, {@code description}.
   *
   * <p>Method under test: {@link DBRFeature#createCategory(DBRFeature, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBRFeature DBRFeature.createCategory(DBRFeature, String, String)"})
  public void testCreateCategoryWithParentFeatureNameDescription() {
    // Arrange and Act
    DBRFeature actualCreateCategoryResult =
        DBRFeature.createCategory(
            DBRFeature.ROOT, "Name", "The characteristics of someone or something");

    // Assert
    assertEquals("Name", actualCreateCategoryResult.getId());
    assertEquals("Name", actualCreateCategoryResult.getName());
    assertEquals(
        "The characteristics of someone or something", actualCreateCategoryResult.getDescription());
    assertNull(actualCreateCategoryResult.getCommandId());
    assertNull(actualCreateCategoryResult.getHelpURL());
    assertTrue(actualCreateCategoryResult.isAbstract());
    assertSame(DBRFeature.ROOT, actualCreateCategoryResult.getParentFeature());
  }

  /**
   * Test {@link DBRFeature#createFeature(DBRFeature, String)}.
   *
   * <p>Method under test: {@link DBRFeature#createFeature(DBRFeature, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBRFeature DBRFeature.createFeature(DBRFeature, String)"})
  public void testCreateFeature() {
    // Arrange and Act
    DBRFeature actualCreateFeatureResult = DBRFeature.createFeature(DBRFeature.ROOT, "Name");

    // Assert
    assertEquals("Name", actualCreateFeatureResult.getId());
    assertEquals("Name", actualCreateFeatureResult.getName());
    assertNull(actualCreateFeatureResult.getCommandId());
    assertNull(actualCreateFeatureResult.getDescription());
    assertNull(actualCreateFeatureResult.getHelpURL());
    assertFalse(actualCreateFeatureResult.isAbstract());
    assertSame(DBRFeature.ROOT, actualCreateFeatureResult.getParentFeature());
  }

  /**
   * Test {@link DBRFeature#createCommandFeature(DBRFeature, String)}.
   *
   * <p>Method under test: {@link DBRFeature#createCommandFeature(DBRFeature, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBRFeature DBRFeature.createCommandFeature(DBRFeature, String)"})
  public void testCreateCommandFeature() {
    // Arrange and Act
    DBRFeature actualCreateCommandFeatureResult =
        DBRFeature.createCommandFeature(DBRFeature.ROOT, "42");

    // Assert
    assertEquals("42", actualCreateCommandFeatureResult.getCommandId());
    assertEquals("42", actualCreateCommandFeatureResult.getId());
    assertEquals("42", actualCreateCommandFeatureResult.getName());
    assertNull(actualCreateCommandFeatureResult.getDescription());
    assertNull(actualCreateCommandFeatureResult.getHelpURL());
    assertFalse(actualCreateCommandFeatureResult.isAbstract());
    assertSame(DBRFeature.ROOT, actualCreateCommandFeatureResult.getParentFeature());
  }

  /**
   * Test {@link DBRFeature#getId()}.
   *
   * <ul>
   *   <li>Given {@link DBRFeature#ROOT} Id is {@code null}.
   *   <li>Then return {@code Root Feature}.
   * </ul>
   *
   * <p>Method under test: {@link DBRFeature#getId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBRFeature.getId()"})
  public void testGetId_givenRootIdIsNull_thenReturnRootFeature() {
    // Arrange
    DBRFeature dbrFeature = DBRFeature.ROOT;
    dbrFeature.setId(null);

    // Act and Assert
    assertEquals("Root Feature", dbrFeature.getId());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBRFeature#setId(String)}
   *   <li>{@link DBRFeature#toString()}
   *   <li>{@link DBRFeature#getCommandId()}
   *   <li>{@link DBRFeature#getDescription()}
   *   <li>{@link DBRFeature#getHelpURL()}
   *   <li>{@link DBRFeature#getName()}
   *   <li>{@link DBRFeature#getParentFeature()}
   *   <li>{@link DBRFeature#isAbstract()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBRFeature.getCommandId()",
    "String DBRFeature.getDescription()",
    "String DBRFeature.getHelpURL()",
    "String DBRFeature.getName()",
    "DBRFeature DBRFeature.getParentFeature()",
    "boolean DBRFeature.isAbstract()",
    "void DBRFeature.setId(String)",
    "String DBRFeature.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBRFeature createCommandFeatureResult = DBRFeature.createCommandFeature(DBRFeature.ROOT, "42");

    // Act
    createCommandFeatureResult.setId("42");
    String actualToStringResult = createCommandFeatureResult.toString();
    String actualCommandId = createCommandFeatureResult.getCommandId();
    String actualDescription = createCommandFeatureResult.getDescription();
    String actualHelpURL = createCommandFeatureResult.getHelpURL();
    String actualName = createCommandFeatureResult.getName();
    DBRFeature actualParentFeature = createCommandFeatureResult.getParentFeature();

    // Assert
    assertEquals("42 (42)", actualToStringResult);
    assertEquals("42", actualCommandId);
    assertEquals("42", actualName);
    assertEquals("Root", actualParentFeature.getId());
    assertNull(actualDescription);
    assertNull(actualHelpURL);
    assertFalse(createCommandFeatureResult.isAbstract());
    assertSame(DBRFeature.ROOT, actualParentFeature);
  }
}
