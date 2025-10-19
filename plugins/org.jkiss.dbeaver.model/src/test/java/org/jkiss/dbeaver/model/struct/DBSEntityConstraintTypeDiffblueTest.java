package org.jkiss.dbeaver.model.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSEntityConstraintTypeDiffblueTest {
  /**
   * Test {@link DBSEntityConstraintType#DBSEntityConstraintType(String, String, String, boolean,
   * boolean, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code Localized Name}.
   *   <li>Then return {@code Localized Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBSEntityConstraintType#DBSEntityConstraintType(String, String,
   * String, boolean, boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSEntityConstraintType.<init>(String, String, String, boolean, boolean, boolean, boolean)"
  })
  public void testNewDBSEntityConstraintType_whenLocalizedName_thenReturnLocalizedName() {
    // Arrange and Act
    DBSEntityConstraintType actualDbsEntityConstraintType =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    // Assert
    assertEquals("42", actualDbsEntityConstraintType.getId());
    assertEquals("Localized Name", actualDbsEntityConstraintType.getLocalizedName());
    assertEquals("Name", actualDbsEntityConstraintType.getName());
    assertTrue(actualDbsEntityConstraintType.isAssociation());
    assertTrue(actualDbsEntityConstraintType.isCustom());
    assertTrue(actualDbsEntityConstraintType.isLogical());
    assertTrue(actualDbsEntityConstraintType.isUnique());
  }

  /**
   * Test {@link DBSEntityConstraintType#DBSEntityConstraintType(String, String, String, boolean,
   * boolean, boolean, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return LocalizedName is {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBSEntityConstraintType#DBSEntityConstraintType(String, String,
   * String, boolean, boolean, boolean, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSEntityConstraintType.<init>(String, String, String, boolean, boolean, boolean, boolean)"
  })
  public void testNewDBSEntityConstraintType_whenNull_thenReturnLocalizedNameIsName() {
    // Arrange and Act
    DBSEntityConstraintType actualDbsEntityConstraintType =
        new DBSEntityConstraintType("42", "Name", null, true, true, true, true);

    // Assert
    assertEquals("42", actualDbsEntityConstraintType.getId());
    assertEquals("Name", actualDbsEntityConstraintType.getLocalizedName());
    assertEquals("Name", actualDbsEntityConstraintType.getName());
    assertTrue(actualDbsEntityConstraintType.isAssociation());
    assertTrue(actualDbsEntityConstraintType.isCustom());
    assertTrue(actualDbsEntityConstraintType.isLogical());
    assertTrue(actualDbsEntityConstraintType.isUnique());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSEntityConstraintType#toString()}
   *   <li>{@link DBSEntityConstraintType#getId()}
   *   <li>{@link DBSEntityConstraintType#getLocalizedName()}
   *   <li>{@link DBSEntityConstraintType#getName()}
   *   <li>{@link DBSEntityConstraintType#isAssociation()}
   *   <li>{@link DBSEntityConstraintType#isCustom()}
   *   <li>{@link DBSEntityConstraintType#isLogical()}
   *   <li>{@link DBSEntityConstraintType#isUnique()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBSEntityConstraintType.getId()",
    "String DBSEntityConstraintType.getLocalizedName()",
    "String DBSEntityConstraintType.getName()",
    "boolean DBSEntityConstraintType.isAssociation()",
    "boolean DBSEntityConstraintType.isCustom()",
    "boolean DBSEntityConstraintType.isLogical()",
    "boolean DBSEntityConstraintType.isUnique()",
    "String DBSEntityConstraintType.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBSEntityConstraintType dbsEntityConstraintType =
        new DBSEntityConstraintType("42", "Name", "Localized Name", true, true, true, true);

    // Act
    String actualToStringResult = dbsEntityConstraintType.toString();
    String actualId = dbsEntityConstraintType.getId();
    String actualLocalizedName = dbsEntityConstraintType.getLocalizedName();
    String actualName = dbsEntityConstraintType.getName();
    boolean actualIsAssociationResult = dbsEntityConstraintType.isAssociation();
    boolean actualIsCustomResult = dbsEntityConstraintType.isCustom();
    boolean actualIsLogicalResult = dbsEntityConstraintType.isLogical();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Localized Name", actualLocalizedName);
    assertEquals("Name", actualName);
    assertEquals("Name", actualToStringResult);
    assertTrue(actualIsAssociationResult);
    assertTrue(actualIsCustomResult);
    assertTrue(actualIsLogicalResult);
    assertTrue(dbsEntityConstraintType.isUnique());
  }
}
