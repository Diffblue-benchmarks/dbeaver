package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBIconDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code ABC123}.
   *   <li>Then return Token is {@code ABC123}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBIcon#DBIcon(String, String)}
   *   <li>{@link DBIcon#toString()}
   *   <li>{@link DBIcon#getLocation()}
   *   <li>{@link DBIcon#getToken()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBIcon.<init>(String)",
    "void DBIcon.<init>(String, String)",
    "String DBIcon.getLocation()",
    "String DBIcon.getToken()",
    "String DBIcon.toString()"
  })
  public void testGettersAndSetters_whenAbc123_thenReturnTokenIsAbc123() {
    // Arrange and Act
    DBIcon actualDbIcon = new DBIcon("ABC123", "Path");
    String actualToStringResult = actualDbIcon.toString();
    String actualLocation = actualDbIcon.getLocation();

    // Assert
    assertEquals("ABC123", actualDbIcon.getToken());
    assertEquals("ABC123:Path", actualToStringResult);
    assertEquals("Path", actualLocation);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Path}.
   *   <li>Then return toString is {@code null:Path}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBIcon#DBIcon(String)}
   *   <li>{@link DBIcon#toString()}
   *   <li>{@link DBIcon#getLocation()}
   *   <li>{@link DBIcon#getToken()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBIcon.<init>(String)",
    "void DBIcon.<init>(String, String)",
    "String DBIcon.getLocation()",
    "String DBIcon.getToken()",
    "String DBIcon.toString()"
  })
  public void testGettersAndSetters_whenPath_thenReturnToStringIsNullPath() {
    // Arrange and Act
    DBIcon actualDbIcon = new DBIcon("Path");
    String actualToStringResult = actualDbIcon.toString();
    String actualLocation = actualDbIcon.getLocation();

    // Assert
    assertEquals("Path", actualLocation);
    assertEquals("null:Path", actualToStringResult);
    assertNull(actualDbIcon.getToken());
  }

  /**
   * Test {@link DBIcon#getImageById(String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBIcon#getImageById(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"org.jkiss.dbeaver.model.DBPImage DBIcon.getImageById(String)"})
  public void testGetImageById_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(DBIcon.getImageById("ABC123"));
  }

  /**
   * Test {@link DBIcon#equals(Object)}, and {@link DBIcon#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBIcon#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBIcon.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DBIcon dbIcon = DBIcon.APACHE;
    DBIcon dbIcon2 = DBIcon.APACHE;

    // Act and Assert
    assertEquals(dbIcon, dbIcon2);
    assertEquals(dbIcon.hashCode(), dbIcon2.hashCode());
  }

  /**
   * Test {@link DBIcon#equals(Object)}, and {@link DBIcon#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBIcon#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBIcon.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBIcon dbIcon = DBIcon.APACHE;

    // Act and Assert
    assertEquals(dbIcon, dbIcon);
    int expectedHashCodeResult = dbIcon.hashCode();
    assertEquals(expectedHashCodeResult, dbIcon.hashCode());
  }

  /**
   * Test {@link DBIcon#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBIcon#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBIcon.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(DBIcon.DATABASE_BIG_DEFAULT, DBIcon.APACHE);
  }

  /**
   * Test {@link DBIcon#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBIcon#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBIcon.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange, Act and Assert
    assertNotEquals(new DBIcon("apache", "Path"), DBIcon.APACHE);
  }

  /**
   * Test {@link DBIcon#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBIcon#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBIcon.equals(Object)"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(DBIcon.APACHE, null);
  }

  /**
   * Test {@link DBIcon#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBIcon#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBIcon.equals(Object)"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(DBIcon.APACHE, "Different type to DBIcon");
  }
}
