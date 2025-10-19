package org.jkiss.dbeaver.registry.maven.versioning;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class VersionRangeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link VersionRange#getRecommendedVersion()}
   *   <li>{@link VersionRange#getRestrictions()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "ArtifactVersion VersionRange.getRecommendedVersion()",
    "List VersionRange.getRestrictions()"
  })
  public void testGettersAndSetters() {
    // Arrange
    VersionRange createFromVersionResult = VersionRange.createFromVersion("1.0.2");

    // Act
    ArtifactVersion actualRecommendedVersion = createFromVersionResult.getRecommendedVersion();

    // Assert
    assertTrue(actualRecommendedVersion instanceof DefaultArtifactVersion);
    assertTrue(createFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#cloneOf()}.
   *
   * <p>Method under test: {@link VersionRange#cloneOf()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.cloneOf()"})
  public void testCloneOf() {
    // Arrange
    VersionRange createFromVersionResult = VersionRange.createFromVersion("1.0.2");

    // Act
    VersionRange actualCloneOfResult = createFromVersionResult.cloneOf();

    // Assert
    assertEquals(createFromVersionResult, actualCloneOfResult);
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec() throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult =
        VersionRange.createFromVersionSpec(
            "org.jkiss.dbeaver.registry.maven.versioning.ArtifactVersion");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals(
        "org.jkiss.dbeaver.registry.maven.versioning.ArtifactVersion",
        recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec2() throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("-[");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("-[", recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec3() throws InvalidVersionSpecificationException {
    // Arrange, Act and Assert
    assertThrows(
        InvalidVersionSpecificationException.class,
        () -> VersionRange.createFromVersionSpec("[]["));
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>Then return RecommendedVersion MajorVersion is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_thenReturnRecommendedVersionMajorVersionIsFortyTwo()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("42");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertNull(recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertEquals(42, recommendedVersion.getMajorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>Then return RecommendedVersion Qualifier is empty string.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_thenReturnRecommendedVersionQualifierIsEmptyString()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("0-");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("", recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code -0}.
   *   <li>Then return RecommendedVersion Qualifier is {@code -0}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_when0_thenReturnRecommendedVersionQualifierIs0()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("-0");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("-0", recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code .0}.
   *   <li>Then return RecommendedVersion Qualifier is {@code .0}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_when0_thenReturnRecommendedVersionQualifierIs02()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec(".0");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals(".0", recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code 0[}.
   *   <li>Then return RecommendedVersion Qualifier is {@code 0[}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_when0_thenReturnRecommendedVersionQualifierIs03()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("0[");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("0[", recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code 0.}.
   *   <li>Then return RecommendedVersion Qualifier is {@code 0.}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_when0_thenReturnRecommendedVersionQualifierIs04()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("0.");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("0.", recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code 0}.
   *   <li>Then return RecommendedVersion Qualifier is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_when0_thenReturnRecommendedVersionQualifierIsNull()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("0");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertNull(recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code -}.
   *   <li>Then return RecommendedVersion Qualifier is {@code -}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenDash_thenReturnRecommendedVersionQualifierIsDash()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("-");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("-", recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code .}.
   *   <li>Then return RecommendedVersion Qualifier is {@code .}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenDot_thenReturnRecommendedVersionQualifierIsDot()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec(".");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals(".", recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return RecommendedVersion is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenEmptyString_thenReturnRecommendedVersionIsNull()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("");

    // Assert
    assertNull(actualCreateFromVersionSpecResult.getRecommendedVersion());
    assertFalse(actualCreateFromVersionSpecResult.hasRestrictions());
    assertTrue(actualCreateFromVersionSpecResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code (}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenLeftParenthesis()
      throws InvalidVersionSpecificationException {
    // Arrange, Act and Assert
    assertThrows(
        InvalidVersionSpecificationException.class, () -> VersionRange.createFromVersionSpec("("));
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code (]}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenLeftParenthesisRightSquareBracket()
      throws InvalidVersionSpecificationException {
    // Arrange, Act and Assert
    assertThrows(
        InvalidVersionSpecificationException.class, () -> VersionRange.createFromVersionSpec("(]"));
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code [}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenLeftSquareBracket()
      throws InvalidVersionSpecificationException {
    // Arrange, Act and Assert
    assertThrows(
        InvalidVersionSpecificationException.class, () -> VersionRange.createFromVersionSpec("["));
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code [,]}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenLeftSquareBracketCommaRightSquareBracket()
      throws InvalidVersionSpecificationException {
    // Arrange, Act and Assert
    assertThrows(
        InvalidVersionSpecificationException.class,
        () -> VersionRange.createFromVersionSpec("[,]"));
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenLeftSquareBracketRightSquareBracket()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("[]");

    // Assert
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    ArtifactVersion lowerBound = getResult.getLowerBound();
    assertTrue(lowerBound instanceof DefaultArtifactVersion);
    assertEquals("", lowerBound.getQualifier());
    assertEquals(0, lowerBound.getBuildNumber());
    assertEquals(0, lowerBound.getIncrementalVersion());
    assertEquals(0, lowerBound.getMajorVersion());
    assertEquals(0, lowerBound.getMinorVersion());
    assertTrue(getResult.isLowerBoundInclusive());
    assertTrue(getResult.isUpperBoundInclusive());
    assertTrue(actualCreateFromVersionSpecResult.hasRestrictions());
    assertSame(lowerBound, getResult.getUpperBound());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code [],}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenLeftSquareBracketRightSquareBracketComma()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("[],");

    // Assert
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    ArtifactVersion lowerBound = getResult.getLowerBound();
    assertTrue(lowerBound instanceof DefaultArtifactVersion);
    assertEquals("", lowerBound.getQualifier());
    assertEquals(0, lowerBound.getBuildNumber());
    assertEquals(0, lowerBound.getIncrementalVersion());
    assertEquals(0, lowerBound.getMajorVersion());
    assertEquals(0, lowerBound.getMinorVersion());
    assertTrue(getResult.isLowerBoundInclusive());
    assertTrue(getResult.isUpperBoundInclusive());
    assertTrue(actualCreateFromVersionSpecResult.hasRestrictions());
    assertSame(lowerBound, getResult.getUpperBound());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code []-}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenLeftSquareBracketRightSquareBracketDash()
      throws InvalidVersionSpecificationException {
    // Arrange, Act and Assert
    assertThrows(
        InvalidVersionSpecificationException.class,
        () -> VersionRange.createFromVersionSpec("[]-"));
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenNull_thenReturnNull()
      throws InvalidVersionSpecificationException {
    // Arrange, Act and Assert
    assertNull(VersionRange.createFromVersionSpec(null));
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code Spec0}.
   *   <li>Then return RecommendedVersion Qualifier is {@code Spec0}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenSpec0_thenReturnRecommendedVersionQualifierIsSpec0()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("Spec0");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("Spec0", recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersionSpec(String)}.
   *
   * <ul>
   *   <li>When {@code Spec}.
   *   <li>Then return RecommendedVersion Qualifier is {@code Spec}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersionSpec(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersionSpec(String)"})
  public void testCreateFromVersionSpec_whenSpec_thenReturnRecommendedVersionQualifierIsSpec()
      throws InvalidVersionSpecificationException {
    // Arrange and Act
    VersionRange actualCreateFromVersionSpecResult = VersionRange.createFromVersionSpec("Spec");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionSpecResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("Spec", recommendedVersion.getQualifier());
    List<Restriction> restrictions = actualCreateFromVersionSpecResult.getRestrictions();
    assertEquals(1, restrictions.size());
    Restriction getResult = restrictions.get(0);
    assertNull(getResult.getLowerBound());
    assertNull(getResult.getUpperBound());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(getResult.isLowerBoundInclusive());
    assertFalse(getResult.isUpperBoundInclusive());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult =
        VersionRange.createFromVersion(
            "org.jkiss.dbeaver.registry.maven.versioning.ArtifactVersion");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals(
        "org.jkiss.dbeaver.registry.maven.versioning.ArtifactVersion",
        recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>Then return RecommendedVersion Qualifier is {@code 1.0.2Number is invalid}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_thenReturnRecommendedVersionQualifierIs102NumberIsInvalid() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult =
        VersionRange.createFromVersion("1.0.2Number is invalid");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("1.0.2Number is invalid", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>Then return RecommendedVersion Qualifier is {@code --}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_thenReturnRecommendedVersionQualifierIsDashDash() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("--");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("--", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>Then return RecommendedVersion Qualifier is {@code Version1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_thenReturnRecommendedVersionQualifierIsVersion102() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("Version1.0.2");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("Version1.0.2", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code -0}.
   *   <li>Then return RecommendedVersion Qualifier is {@code -0}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_when0_thenReturnRecommendedVersionQualifierIs0() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("-0");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("-0", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 0}.
   *   <li>Then return RecommendedVersion Qualifier is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_when0_thenReturnRecommendedVersionQualifierIsNull() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("0");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertNull(recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return RecommendedVersion MajorVersion is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_when42_thenReturnRecommendedVersionMajorVersionIsFortyTwo() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("42");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertNull(recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertEquals(42, recommendedVersion.getMajorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2}.
   *   <li>Then return RecommendedVersion MajorVersion is one.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_when102_thenReturnRecommendedVersionMajorVersionIsOne() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("1.0.2");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertNull(recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertEquals(1, recommendedVersion.getMajorVersion());
    assertEquals(2, recommendedVersion.getIncrementalVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2.}.
   *   <li>Then return RecommendedVersion Qualifier is {@code 1.0.2.}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_when102_thenReturnRecommendedVersionQualifierIs102() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("1.0.2.");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("1.0.2.", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2..}.
   *   <li>Then return RecommendedVersion Qualifier is {@code 1.0.2..}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_when102_thenReturnRecommendedVersionQualifierIs1022() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("1.0.2..");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("1.0.2..", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code .1.0.2}.
   *   <li>Then return RecommendedVersion Qualifier is {@code .1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_when102_thenReturnRecommendedVersionQualifierIs1023() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion(".1.0.2");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals(".1.0.2", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2-}.
   *   <li>Then return RecommendedVersion Qualifier is empty string.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_when102_thenReturnRecommendedVersionQualifierIsEmptyString() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("1.0.2-");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertEquals(1, recommendedVersion.getMajorVersion());
    assertEquals(2, recommendedVersion.getIncrementalVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 01.0.2}.
   *   <li>Then return RecommendedVersion Qualifier is {@code 01.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_when0102_thenReturnRecommendedVersionQualifierIs0102() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("01.0.2");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("01.0.2", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.21.0.2}.
   *   <li>Then return RecommendedVersion Qualifier is {@code 1.0.21.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_when102102_thenReturnRecommendedVersionQualifierIs102102() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("1.0.21.0.2");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("1.0.21.0.2", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code -}.
   *   <li>Then return RecommendedVersion Qualifier is {@code -}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_whenDash_thenReturnRecommendedVersionQualifierIsDash() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("-");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("-", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code .}.
   *   <li>Then return RecommendedVersion Qualifier is {@code .}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_whenDot_thenReturnRecommendedVersionQualifierIsDot() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion(".");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals(".", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#createFromVersion(String)}.
   *
   * <ul>
   *   <li>When {@code Version}.
   *   <li>Then return RecommendedVersion Qualifier is {@code Version}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#createFromVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.createFromVersion(String)"})
  public void testCreateFromVersion_whenVersion_thenReturnRecommendedVersionQualifierIsVersion() {
    // Arrange and Act
    VersionRange actualCreateFromVersionResult = VersionRange.createFromVersion("Version");

    // Assert
    ArtifactVersion recommendedVersion = actualCreateFromVersionResult.getRecommendedVersion();
    assertTrue(recommendedVersion instanceof DefaultArtifactVersion);
    assertEquals("Version", recommendedVersion.getQualifier());
    assertEquals(0, recommendedVersion.getBuildNumber());
    assertEquals(0, recommendedVersion.getIncrementalVersion());
    assertEquals(0, recommendedVersion.getMajorVersion());
    assertEquals(0, recommendedVersion.getMinorVersion());
    assertFalse(actualCreateFromVersionResult.hasRestrictions());
    assertTrue(actualCreateFromVersionResult.getRestrictions().isEmpty());
  }

  /**
   * Test {@link VersionRange#restrict(VersionRange)}.
   *
   * <ul>
   *   <li>When createFromVersion {@code 1.0.2}.
   *   <li>Then return createFromVersion {@code 1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#restrict(VersionRange)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"VersionRange VersionRange.restrict(VersionRange)"})
  public void testRestrict_whenCreateFromVersion102_thenReturnCreateFromVersion102() {
    // Arrange
    VersionRange createFromVersionResult = VersionRange.createFromVersion("1.0.2");

    // Act
    VersionRange actualRestrictResult =
        createFromVersionResult.restrict(VersionRange.createFromVersion("1.0.2"));

    // Assert
    assertEquals(createFromVersionResult, actualRestrictResult);
  }

  /**
   * Test {@link VersionRange#toString()}.
   *
   * <p>Method under test: {@link VersionRange#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String VersionRange.toString()"})
  public void testToString() {
    // Arrange, Act and Assert
    assertEquals("1.0.2", VersionRange.createFromVersion("1.0.2").toString());
  }

  /**
   * Test {@link VersionRange#matchVersion(List)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#matchVersion(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ArtifactVersion VersionRange.matchVersion(List)"})
  public void testMatchVersion_givenNull_whenArrayListAddNull() {
    // Arrange
    VersionRange createFromVersionResult = VersionRange.createFromVersion("1.0.2");

    ArrayList<ArtifactVersion> versions = new ArrayList<>();
    versions.add(null);

    // Act and Assert
    assertNull(createFromVersionResult.matchVersion(versions));
  }

  /**
   * Test {@link VersionRange#matchVersion(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#matchVersion(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ArtifactVersion VersionRange.matchVersion(List)"})
  public void testMatchVersion_whenArrayList() {
    // Arrange
    VersionRange createFromVersionResult = VersionRange.createFromVersion("1.0.2");

    // Act and Assert
    assertNull(createFromVersionResult.matchVersion(new ArrayList<>()));
  }

  /**
   * Test {@link VersionRange#containsVersion(ArtifactVersion)}.
   *
   * <p>Method under test: {@link VersionRange#containsVersion(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionRange.containsVersion(ArtifactVersion)"})
  public void testContainsVersion() {
    // Arrange
    VersionRange createFromVersionResult = VersionRange.createFromVersion("1.0.2");

    // Act
    boolean actualContainsVersionResult =
        createFromVersionResult.containsVersion(new DefaultArtifactVersion("1.0.2"));

    // Assert
    assertFalse(actualContainsVersionResult);
  }

  /**
   * Test {@link VersionRange#hasRestrictions()}.
   *
   * <p>Method under test: {@link VersionRange#hasRestrictions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionRange.hasRestrictions()"})
  public void testHasRestrictions() {
    // Arrange, Act and Assert
    assertFalse(VersionRange.createFromVersion("1.0.2").hasRestrictions());
  }

  /**
   * Test {@link VersionRange#equals(Object)}, and {@link VersionRange#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link VersionRange#equals(Object)}
   *   <li>{@link VersionRange#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionRange.equals(Object)", "int VersionRange.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    VersionRange createFromVersionResult = VersionRange.createFromVersion("1.0.2");
    VersionRange createFromVersionResult2 = VersionRange.createFromVersion("1.0.2");

    // Act and Assert
    assertEquals(createFromVersionResult, createFromVersionResult2);
    assertEquals(createFromVersionResult.hashCode(), createFromVersionResult2.hashCode());
  }

  /**
   * Test {@link VersionRange#equals(Object)}, and {@link VersionRange#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link VersionRange#equals(Object)}
   *   <li>{@link VersionRange#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionRange.equals(Object)", "int VersionRange.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    VersionRange createFromVersionResult = VersionRange.createFromVersion("1.0.2");

    // Act and Assert
    assertEquals(createFromVersionResult, createFromVersionResult);
    int expectedHashCodeResult = createFromVersionResult.hashCode();
    assertEquals(expectedHashCodeResult, createFromVersionResult.hashCode());
  }

  /**
   * Test {@link VersionRange#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionRange.equals(Object)", "int VersionRange.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(VersionRange.createFromVersion("1.0.2"), 1);
  }

  /**
   * Test {@link VersionRange#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionRange.equals(Object)", "int VersionRange.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    VersionRange createFromVersionResult = VersionRange.createFromVersion("Version");

    // Act and Assert
    assertNotEquals(createFromVersionResult, VersionRange.createFromVersion("1.0.2"));
  }

  /**
   * Test {@link VersionRange#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionRange.equals(Object)", "int VersionRange.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(VersionRange.createFromVersion("1.0.2"), null);
  }

  /**
   * Test {@link VersionRange#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link VersionRange#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionRange.equals(Object)", "int VersionRange.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(VersionRange.createFromVersion("1.0.2"), "Different type to VersionRange");
  }
}
