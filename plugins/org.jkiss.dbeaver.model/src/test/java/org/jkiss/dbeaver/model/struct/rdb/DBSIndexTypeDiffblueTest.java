package org.jkiss.dbeaver.model.struct.rdb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBSIndexTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSIndexType#DBSIndexType(String, String)}
   *   <li>{@link DBSIndexType#getId()}
   *   <li>{@link DBSIndexType#getName()}
   *   <li>{@link DBSIndexType#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBSIndexType.<init>(String, String)",
    "String DBSIndexType.getId()",
    "String DBSIndexType.getName()",
    "String DBSIndexType.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBSIndexType actualDbsIndexType = new DBSIndexType("42", "Name");
    String actualId = actualDbsIndexType.getId();
    String actualName = actualDbsIndexType.getName();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Name", actualName);
    assertEquals("Name", actualDbsIndexType.toString());
  }

  /**
   * Test {@link DBSIndexType#equals(Object)}, and {@link DBSIndexType#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSIndexType#equals(Object)}
   *   <li>{@link DBSIndexType#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSIndexType.equals(Object)", "int DBSIndexType.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DBSIndexType dbsIndexType = DBSIndexType.CLUSTERED;
    DBSIndexType dbsIndexType2 = DBSIndexType.CLUSTERED;

    // Act and Assert
    assertEquals(dbsIndexType, dbsIndexType2);
    assertEquals(dbsIndexType.hashCode(), dbsIndexType2.hashCode());
  }

  /**
   * Test {@link DBSIndexType#equals(Object)}, and {@link DBSIndexType#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBSIndexType#equals(Object)}
   *   <li>{@link DBSIndexType#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSIndexType.equals(Object)", "int DBSIndexType.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBSIndexType dbsIndexType = DBSIndexType.CLUSTERED;

    // Act and Assert
    assertEquals(dbsIndexType, dbsIndexType);
    int expectedHashCodeResult = dbsIndexType.hashCode();
    assertEquals(expectedHashCodeResult, dbsIndexType.hashCode());
  }

  /**
   * Test {@link DBSIndexType#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSIndexType#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSIndexType.equals(Object)", "int DBSIndexType.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(DBSIndexType.HASHED, DBSIndexType.CLUSTERED);
  }

  /**
   * Test {@link DBSIndexType#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSIndexType#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSIndexType.equals(Object)", "int DBSIndexType.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(DBSIndexType.CLUSTERED, null);
  }

  /**
   * Test {@link DBSIndexType#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBSIndexType#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBSIndexType.equals(Object)", "int DBSIndexType.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(DBSIndexType.CLUSTERED, "Different type to DBSIndexType");
  }
}
