package org.jkiss.dbeaver.registry.maven.versioning;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class RestrictionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Restriction#Restriction(ArtifactVersion, boolean, ArtifactVersion, boolean)}
   *   <li>{@link Restriction#getLowerBound()}
   *   <li>{@link Restriction#getUpperBound()}
   *   <li>{@link Restriction#isLowerBoundInclusive()}
   *   <li>{@link Restriction#isUpperBoundInclusive()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void Restriction.<init>(ArtifactVersion, boolean, ArtifactVersion, boolean)",
    "ArtifactVersion Restriction.getLowerBound()",
    "ArtifactVersion Restriction.getUpperBound()",
    "boolean Restriction.isLowerBoundInclusive()",
    "boolean Restriction.isUpperBoundInclusive()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("1.0.2");
    DefaultArtifactVersion upperBound = new DefaultArtifactVersion("1.0.2");

    // Act
    Restriction actualRestriction = new Restriction(lowerBound, true, upperBound, true);
    ArtifactVersion actualLowerBound = actualRestriction.getLowerBound();
    ArtifactVersion actualUpperBound = actualRestriction.getUpperBound();
    boolean actualIsLowerBoundInclusiveResult = actualRestriction.isLowerBoundInclusive();

    // Assert
    assertTrue(actualIsLowerBoundInclusiveResult);
    assertTrue(actualRestriction.isUpperBoundInclusive());
    assertSame(lowerBound, actualLowerBound);
    assertSame(upperBound, actualUpperBound);
  }

  /**
   * Test {@link Restriction#containsVersion(ArtifactVersion)}.
   *
   * <p>Method under test: {@link Restriction#containsVersion(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.containsVersion(ArtifactVersion)"})
  public void testContainsVersion() {
    // Arrange
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("1.0.2");
    Restriction restriction =
        new Restriction(lowerBound, false, new DefaultArtifactVersion("1.0.2"), true);

    // Act
    boolean actualContainsVersionResult =
        restriction.containsVersion(new DefaultArtifactVersion("1.0.2"));

    // Assert
    assertFalse(actualContainsVersionResult);
  }

  /**
   * Test {@link Restriction#containsVersion(ArtifactVersion)}.
   *
   * <p>Method under test: {@link Restriction#containsVersion(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.containsVersion(ArtifactVersion)"})
  public void testContainsVersion2() {
    // Arrange
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("1.0.2");
    Restriction restriction =
        new Restriction(lowerBound, true, new DefaultArtifactVersion("1.0.2"), false);

    // Act
    boolean actualContainsVersionResult =
        restriction.containsVersion(new DefaultArtifactVersion("1.0.2"));

    // Assert
    assertFalse(actualContainsVersionResult);
  }

  /**
   * Test {@link Restriction#containsVersion(ArtifactVersion)}.
   *
   * <ul>
   *   <li>Given {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with version is
   *       {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#containsVersion(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.containsVersion(ArtifactVersion)"})
  public void testContainsVersion_givenDefaultArtifactVersionWithVersionIs42_thenReturnFalse() {
    // Arrange
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("42");
    Restriction restriction =
        new Restriction(lowerBound, true, new DefaultArtifactVersion("1.0.2"), true);

    // Act
    boolean actualContainsVersionResult =
        restriction.containsVersion(new DefaultArtifactVersion("1.0.2"));

    // Assert
    assertFalse(actualContainsVersionResult);
  }

  /**
   * Test {@link Restriction#containsVersion(ArtifactVersion)}.
   *
   * <ul>
   *   <li>Given {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with {@code Version}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#containsVersion(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.containsVersion(ArtifactVersion)"})
  public void testContainsVersion_givenDefaultArtifactVersionWithVersion_thenReturnFalse() {
    // Arrange
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("1.0.2");
    Restriction restriction =
        new Restriction(lowerBound, true, new DefaultArtifactVersion("Version"), true);

    // Act
    boolean actualContainsVersionResult =
        restriction.containsVersion(new DefaultArtifactVersion("1.0.2"));

    // Assert
    assertFalse(actualContainsVersionResult);
  }

  /**
   * Test {@link Restriction#containsVersion(ArtifactVersion)}.
   *
   * <ul>
   *   <li>Given {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with {@code Version}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#containsVersion(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.containsVersion(ArtifactVersion)"})
  public void testContainsVersion_givenDefaultArtifactVersionWithVersion_thenReturnTrue() {
    // Arrange
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("Version");
    Restriction restriction =
        new Restriction(lowerBound, true, new DefaultArtifactVersion("1.0.2"), true);

    // Act
    boolean actualContainsVersionResult =
        restriction.containsVersion(new DefaultArtifactVersion("1.0.2"));

    // Assert
    assertTrue(actualContainsVersionResult);
  }

  /**
   * Test {@link Restriction#containsVersion(ArtifactVersion)}.
   *
   * <ul>
   *   <li>Given {@link Restriction#EVERYTHING}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#containsVersion(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.containsVersion(ArtifactVersion)"})
  public void testContainsVersion_givenEverything_thenReturnTrue() {
    // Arrange and Act
    boolean actualContainsVersionResult =
        Restriction.EVERYTHING.containsVersion(new DefaultArtifactVersion("1.0.2"));

    // Assert
    assertTrue(actualContainsVersionResult);
  }

  /**
   * Test {@link Restriction#containsVersion(ArtifactVersion)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#containsVersion(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.containsVersion(ArtifactVersion)"})
  public void testContainsVersion_thenReturnTrue() {
    // Arrange
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("1.0.2");
    Restriction restriction =
        new Restriction(lowerBound, true, new DefaultArtifactVersion("1.0.2"), true);

    // Act
    boolean actualContainsVersionResult =
        restriction.containsVersion(new DefaultArtifactVersion("1.0.2"));

    // Assert
    assertTrue(actualContainsVersionResult);
  }

  /**
   * Test {@link Restriction#equals(Object)}, and {@link Restriction#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Restriction#equals(Object)}
   *   <li>{@link Restriction#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.equals(Object)", "int Restriction.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    Restriction restriction = Restriction.EVERYTHING;
    Restriction restriction2 = Restriction.EVERYTHING;

    // Act and Assert
    assertEquals(restriction, restriction2);
    assertEquals(restriction.hashCode(), restriction2.hashCode());
  }

  /**
   * Test {@link Restriction#equals(Object)}, and {@link Restriction#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Restriction#equals(Object)}
   *   <li>{@link Restriction#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.equals(Object)", "int Restriction.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
    // Arrange
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("1.0.2");
    Restriction restriction =
        new Restriction(lowerBound, true, new DefaultArtifactVersion("1.0.2"), true);
    DefaultArtifactVersion lowerBound2 = new DefaultArtifactVersion("1.0.2");
    Restriction restriction2 =
        new Restriction(lowerBound2, true, new DefaultArtifactVersion("1.0.2"), true);

    // Act and Assert
    assertEquals(restriction, restriction2);
    assertEquals(restriction.hashCode(), restriction2.hashCode());
  }

  /**
   * Test {@link Restriction#equals(Object)}, and {@link Restriction#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link Restriction#equals(Object)}
   *   <li>{@link Restriction#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.equals(Object)", "int Restriction.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    Restriction restriction = Restriction.EVERYTHING;

    // Act and Assert
    assertEquals(restriction, restriction);
    int expectedHashCodeResult = restriction.hashCode();
    assertEquals(expectedHashCodeResult, restriction.hashCode());
  }

  /**
   * Test {@link Restriction#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.equals(Object)", "int Restriction.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("1.0.2");

    // Act and Assert
    assertNotEquals(
        new Restriction(lowerBound, true, new DefaultArtifactVersion("1.0.2"), true),
        Restriction.EVERYTHING);
  }

  /**
   * Test {@link Restriction#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.equals(Object)", "int Restriction.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange, Act and Assert
    assertNotEquals(
        new Restriction(null, true, new DefaultArtifactVersion("1.0.2"), true),
        Restriction.EVERYTHING);
  }

  /**
   * Test {@link Restriction#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.equals(Object)", "int Restriction.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange, Act and Assert
    assertNotEquals(
        new Restriction(null, false, new DefaultArtifactVersion("1.0.2"), true),
        Restriction.EVERYTHING);
  }

  /**
   * Test {@link Restriction#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.equals(Object)", "int Restriction.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    Restriction restriction =
        new Restriction(null, true, new DefaultArtifactVersion("1.0.2"), true);
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("1.0.2");

    // Act and Assert
    assertNotEquals(
        restriction, new Restriction(lowerBound, true, new DefaultArtifactVersion("1.0.2"), true));
  }

  /**
   * Test {@link Restriction#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.equals(Object)", "int Restriction.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    Restriction restriction =
        new Restriction(new DefaultArtifactVersion("1.0.2"), true, null, true);
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("1.0.2");

    // Act and Assert
    assertNotEquals(
        restriction, new Restriction(lowerBound, true, new DefaultArtifactVersion("1.0.2"), true));
  }

  /**
   * Test {@link Restriction#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.equals(Object)", "int Restriction.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("1.0.2");
    Restriction restriction =
        new Restriction(lowerBound, true, new DefaultArtifactVersion("1.0.2"), false);
    DefaultArtifactVersion lowerBound2 = new DefaultArtifactVersion("1.0.2");

    // Act and Assert
    assertNotEquals(
        restriction, new Restriction(lowerBound2, true, new DefaultArtifactVersion("1.0.2"), true));
  }

  /**
   * Test {@link Restriction#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.equals(Object)", "int Restriction.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange, Act and Assert
    assertNotEquals(new Restriction(null, false, null, true), Restriction.EVERYTHING);
  }

  /**
   * Test {@link Restriction#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.equals(Object)", "int Restriction.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(Restriction.EVERYTHING, null);
  }

  /**
   * Test {@link Restriction#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean Restriction.equals(Object)", "int Restriction.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(Restriction.EVERYTHING, "Different type to Restriction");
  }

  /**
   * Test {@link Restriction#toString()}.
   *
   * <ul>
   *   <li>Given {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with version is
   *       {@code 1.0.2}.
   *   <li>Then return {@code [1.0.2,1.0.2]}.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String Restriction.toString()"})
  public void testToString_givenDefaultArtifactVersionWithVersionIs102_thenReturn102102() {
    // Arrange
    DefaultArtifactVersion lowerBound = new DefaultArtifactVersion("1.0.2");
    Restriction restriction =
        new Restriction(lowerBound, true, new DefaultArtifactVersion("1.0.2"), true);

    // Act and Assert
    assertEquals("[1.0.2,1.0.2]", restriction.toString());
  }

  /**
   * Test {@link Restriction#toString()}.
   *
   * <ul>
   *   <li>Given {@link Restriction#EVERYTHING}.
   *   <li>Then return {@code (,)}.
   * </ul>
   *
   * <p>Method under test: {@link Restriction#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String Restriction.toString()"})
  public void testToString_givenEverything_thenReturnLeftParenthesisCommaRightParenthesis() {
    // Arrange, Act and Assert
    assertEquals("(,)", Restriction.EVERYTHING.toString());
  }
}
