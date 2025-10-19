package org.jkiss.dbeaver.registry.maven.versioning;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ComparableVersionDiffblueTest {
  /**
   * Test {@link ComparableVersion#ComparableVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2Version}.
   *   <li>Then return Canonical is {@code 1.0.2-version}.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#ComparableVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ComparableVersion.<init>(String)"})
  public void testNewComparableVersion_when102Version_thenReturnCanonicalIs102Version() {
    // Arrange and Act
    ComparableVersion actualComparableVersion = new ComparableVersion("1.0.2Version");

    // Assert
    assertEquals("1.0.2-version", actualComparableVersion.getCanonical());
    assertEquals("1.0.2Version", actualComparableVersion.toString());
  }

  /**
   * Test {@link ComparableVersion#ComparableVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2}.
   *   <li>Then return Canonical is {@code 1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#ComparableVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ComparableVersion.<init>(String)"})
  public void testNewComparableVersion_when102_thenReturnCanonicalIs102() {
    // Arrange and Act
    ComparableVersion actualComparableVersion = new ComparableVersion("1.0.2");

    // Assert
    assertEquals("1.0.2", actualComparableVersion.getCanonical());
    assertEquals("1.0.2", actualComparableVersion.toString());
  }

  /**
   * Test {@link ComparableVersion#ComparableVersion(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Canonical is empty string.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#ComparableVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ComparableVersion.<init>(String)"})
  public void testNewComparableVersion_whenEmptyString_thenReturnCanonicalIsEmptyString() {
    // Arrange and Act
    ComparableVersion actualComparableVersion = new ComparableVersion("");

    // Assert
    assertEquals("", actualComparableVersion.getCanonical());
    assertEquals("", actualComparableVersion.toString());
  }

  /**
   * Test {@link ComparableVersion#ComparableVersion(String)}.
   *
   * <ul>
   *   <li>When {@code Version1.0.2}.
   *   <li>Then return toString is {@code Version1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#ComparableVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ComparableVersion.<init>(String)"})
  public void testNewComparableVersion_whenVersion102_thenReturnToStringIsVersion102() {
    // Arrange and Act
    ComparableVersion actualComparableVersion = new ComparableVersion("Version1.0.2");

    // Assert
    assertEquals("Version1.0.2", actualComparableVersion.toString());
    assertEquals("version-1.0.2", actualComparableVersion.getCanonical());
  }

  /**
   * Test {@link ComparableVersion#ComparableVersion(String)}.
   *
   * <ul>
   *   <li>When {@code Version}.
   *   <li>Then return toString is {@code Version}.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#ComparableVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ComparableVersion.<init>(String)"})
  public void testNewComparableVersion_whenVersion_thenReturnToStringIsVersion() {
    // Arrange and Act
    ComparableVersion actualComparableVersion = new ComparableVersion("Version");

    // Assert
    assertEquals("Version", actualComparableVersion.toString());
    assertEquals("version", actualComparableVersion.getCanonical());
  }

  /**
   * Test {@link ComparableVersion#compareTo(ComparableVersion)} with {@code ComparableVersion}.
   *
   * <p>Method under test: {@link ComparableVersion#compareTo(ComparableVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ComparableVersion.compareTo(ComparableVersion)"})
  public void testCompareToWithComparableVersion() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("");

    // Act
    int actualCompareToResult = comparableVersion.compareTo(new ComparableVersion("1.0.2"));

    // Assert
    assertEquals(-1, actualCompareToResult);
  }

  /**
   * Test {@link ComparableVersion#compareTo(ComparableVersion)} with {@code ComparableVersion}.
   *
   * <ul>
   *   <li>Given {@link ComparableVersion#ComparableVersion(String)} with version is {@code
   *       1.0.2Version}.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#compareTo(ComparableVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ComparableVersion.compareTo(ComparableVersion)"})
  public void testCompareToWithComparableVersion_givenComparableVersionWithVersionIs102Version() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("1.0.2Version");

    // Act
    int actualCompareToResult = comparableVersion.compareTo(new ComparableVersion("1.0.2"));

    // Assert
    assertEquals(2, actualCompareToResult);
  }

  /**
   * Test {@link ComparableVersion#compareTo(ComparableVersion)} with {@code ComparableVersion}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#compareTo(ComparableVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ComparableVersion.compareTo(ComparableVersion)"})
  public void testCompareToWithComparableVersion_thenReturnMinusOne() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("Version");

    // Act
    int actualCompareToResult = comparableVersion.compareTo(new ComparableVersion("1.0.2"));

    // Assert
    assertEquals(-1, actualCompareToResult);
  }

  /**
   * Test {@link ComparableVersion#compareTo(ComparableVersion)} with {@code ComparableVersion}.
   *
   * <ul>
   *   <li>Then return minus two.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#compareTo(ComparableVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ComparableVersion.compareTo(ComparableVersion)"})
  public void testCompareToWithComparableVersion_thenReturnMinusTwo() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("");

    // Act
    int actualCompareToResult = comparableVersion.compareTo(new ComparableVersion("Version"));

    // Assert
    assertEquals(-2, actualCompareToResult);
  }

  /**
   * Test {@link ComparableVersion#compareTo(ComparableVersion)} with {@code ComparableVersion}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#compareTo(ComparableVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ComparableVersion.compareTo(ComparableVersion)"})
  public void testCompareToWithComparableVersion_thenReturnOne() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("1.0.2");

    // Act
    int actualCompareToResult = comparableVersion.compareTo(new ComparableVersion("Version"));

    // Assert
    assertEquals(1, actualCompareToResult);
  }

  /**
   * Test {@link ComparableVersion#compareTo(ComparableVersion)} with {@code ComparableVersion}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#compareTo(ComparableVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ComparableVersion.compareTo(ComparableVersion)"})
  public void testCompareToWithComparableVersion_thenReturnOne2() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("1.0.2");

    // Act
    int actualCompareToResult = comparableVersion.compareTo(new ComparableVersion(""));

    // Assert
    assertEquals(1, actualCompareToResult);
  }

  /**
   * Test {@link ComparableVersion#compareTo(ComparableVersion)} with {@code ComparableVersion}.
   *
   * <ul>
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#compareTo(ComparableVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ComparableVersion.compareTo(ComparableVersion)"})
  public void testCompareToWithComparableVersion_thenReturnTwo() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("Version");

    // Act
    int actualCompareToResult = comparableVersion.compareTo(new ComparableVersion(""));

    // Assert
    assertEquals(2, actualCompareToResult);
  }

  /**
   * Test {@link ComparableVersion#compareTo(ComparableVersion)} with {@code ComparableVersion}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#compareTo(ComparableVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ComparableVersion.compareTo(ComparableVersion)"})
  public void testCompareToWithComparableVersion_thenReturnZero() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("1.0.2");

    // Act
    int actualCompareToResult = comparableVersion.compareTo(new ComparableVersion("1.0.2"));

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test {@link ComparableVersion#compareTo(ComparableVersion)} with {@code ComparableVersion}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#compareTo(ComparableVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ComparableVersion.compareTo(ComparableVersion)"})
  public void testCompareToWithComparableVersion_thenReturnZero2() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("Version");

    // Act
    int actualCompareToResult = comparableVersion.compareTo(new ComparableVersion("Version"));

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test {@link ComparableVersion#compareTo(ComparableVersion)} with {@code ComparableVersion}.
   *
   * <ul>
   *   <li>When {@link ComparableVersion#ComparableVersion(String)} with version is {@code
   *       1.0.2Version}.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#compareTo(ComparableVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ComparableVersion.compareTo(ComparableVersion)"})
  public void testCompareToWithComparableVersion_whenComparableVersionWithVersionIs102Version() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("1.0.2");

    // Act
    int actualCompareToResult = comparableVersion.compareTo(new ComparableVersion("1.0.2Version"));

    // Assert
    assertEquals(-2, actualCompareToResult);
  }

  /**
   * Test {@link ComparableVersion#compareTo(ComparableVersion)} with {@code ComparableVersion}.
   *
   * <ul>
   *   <li>When {@link ComparableVersion#ComparableVersion(String)} with version is {@code
   *       Version1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#compareTo(ComparableVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int ComparableVersion.compareTo(ComparableVersion)"})
  public void testCompareToWithComparableVersion_whenComparableVersionWithVersionIsVersion102() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("Version");

    // Act
    int actualCompareToResult = comparableVersion.compareTo(new ComparableVersion("Version1.0.2"));

    // Assert
    assertEquals(-1, actualCompareToResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ComparableVersion#getCanonical()}
   *   <li>{@link ComparableVersion#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String ComparableVersion.getCanonical()",
    "String ComparableVersion.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("1.0.2");

    // Act
    String actualCanonical = comparableVersion.getCanonical();

    // Assert
    assertEquals("1.0.2", actualCanonical);
    assertEquals("1.0.2", comparableVersion.toString());
  }

  /**
   * Test {@link ComparableVersion#equals(Object)}, and {@link ComparableVersion#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ComparableVersion#equals(Object)}
   *   <li>{@link ComparableVersion#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ComparableVersion.equals(Object)",
    "int ComparableVersion.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("1.0.2");
    ComparableVersion comparableVersion2 = new ComparableVersion("1.0.2");

    // Act and Assert
    assertEquals(comparableVersion, comparableVersion2);
    assertEquals(comparableVersion.hashCode(), comparableVersion2.hashCode());
  }

  /**
   * Test {@link ComparableVersion#equals(Object)}, and {@link ComparableVersion#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ComparableVersion#equals(Object)}
   *   <li>{@link ComparableVersion#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ComparableVersion.equals(Object)",
    "int ComparableVersion.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("1.0.2");

    // Act and Assert
    assertEquals(comparableVersion, comparableVersion);
    int expectedHashCodeResult = comparableVersion.hashCode();
    assertEquals(expectedHashCodeResult, comparableVersion.hashCode());
  }

  /**
   * Test {@link ComparableVersion#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ComparableVersion.equals(Object)",
    "int ComparableVersion.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ComparableVersion("1.0.2"), 1);
  }

  /**
   * Test {@link ComparableVersion#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ComparableVersion.equals(Object)",
    "int ComparableVersion.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    ComparableVersion comparableVersion = new ComparableVersion("Version");

    // Act and Assert
    assertNotEquals(comparableVersion, new ComparableVersion("1.0.2"));
  }

  /**
   * Test {@link ComparableVersion#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ComparableVersion.equals(Object)",
    "int ComparableVersion.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ComparableVersion("1.0.2"), null);
  }

  /**
   * Test {@link ComparableVersion#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link ComparableVersion#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean ComparableVersion.equals(Object)",
    "int ComparableVersion.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new ComparableVersion("1.0.2"), "Different type to ComparableVersion");
  }
}
