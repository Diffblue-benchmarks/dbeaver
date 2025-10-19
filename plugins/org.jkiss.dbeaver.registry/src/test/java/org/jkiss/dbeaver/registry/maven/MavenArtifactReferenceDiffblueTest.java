package org.jkiss.dbeaver.registry.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.registry.maven.MavenArtifactDependency.Scope;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MavenArtifactReferenceDiffblueTest {
  /**
   * Test {@link MavenArtifactReference#MavenArtifactReference(String, String, String, String,
   * String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return FallbackVersion is {@code 1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactReference#MavenArtifactReference(String, String,
   * String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenArtifactReference.<init>(String, String, String, String, String)"})
  public void testNewMavenArtifactReference_when42_thenReturnFallbackVersionIs102() {
    // Arrange and Act
    MavenArtifactReference actualMavenArtifactReference =
        new MavenArtifactReference("42", "42", "Classifier", "1.0.2", "1.0.2");

    // Assert
    assertEquals("1.0.2", actualMavenArtifactReference.getFallbackVersion());
    assertEquals("1.0.2", actualMavenArtifactReference.getVersion());
    assertEquals("42", actualMavenArtifactReference.getArtifactId());
    assertEquals("42", actualMavenArtifactReference.getGroupId());
    assertEquals("42:42:Classifier:1.0.2", actualMavenArtifactReference.getId());
    assertEquals("42:42:Classifier:1.0.2", actualMavenArtifactReference.getPath());
    assertEquals("Classifier", actualMavenArtifactReference.getClassifier());
  }

  /**
   * Test {@link MavenArtifactReference#MavenArtifactReference(String)}.
   *
   * <ul>
   *   <li>When {@code foo:bar:baz}.
   *   <li>Then return FallbackVersion is {@code baz}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactReference#MavenArtifactReference(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenArtifactReference.<init>(String)"})
  public void testNewMavenArtifactReference_whenFooBarBaz_thenReturnFallbackVersionIsBaz() {
    // Arrange and Act
    MavenArtifactReference actualMavenArtifactReference = new MavenArtifactReference("foo:bar:baz");

    // Assert
    assertEquals("bar", actualMavenArtifactReference.getArtifactId());
    assertEquals("baz", actualMavenArtifactReference.getFallbackVersion());
    assertEquals("foo", actualMavenArtifactReference.getGroupId());
    assertEquals("foo:bar:baz", actualMavenArtifactReference.getId());
    assertEquals("foo:bar:baz", actualMavenArtifactReference.getPath());
  }

  /**
   * Test {@link MavenArtifactReference#MavenArtifactReference(String)}.
   *
   * <ul>
   *   <li>When {@code :foo:bar:baz}.
   *   <li>Then return GroupId is empty string.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactReference#MavenArtifactReference(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenArtifactReference.<init>(String)"})
  public void testNewMavenArtifactReference_whenFooBarBaz_thenReturnGroupIdIsEmptyString() {
    // Arrange and Act
    MavenArtifactReference actualMavenArtifactReference =
        new MavenArtifactReference(":foo:bar:baz");

    // Assert
    assertEquals("", actualMavenArtifactReference.getGroupId());
    assertEquals(":foo:bar:baz", actualMavenArtifactReference.getId());
    assertEquals(":foo:bar:baz", actualMavenArtifactReference.getPath());
    assertEquals("bar", actualMavenArtifactReference.getClassifier());
    assertEquals("foo", actualMavenArtifactReference.getArtifactId());
  }

  /**
   * Test {@link MavenArtifactReference#MavenArtifactReference(String)}.
   *
   * <ul>
   *   <li>When {@code foo:bar}.
   *   <li>Then return Id is {@code foo:bar}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactReference#MavenArtifactReference(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenArtifactReference.<init>(String)"})
  public void testNewMavenArtifactReference_whenFooBar_thenReturnIdIsFooBar() {
    // Arrange and Act
    MavenArtifactReference actualMavenArtifactReference = new MavenArtifactReference("foo:bar");

    // Assert
    assertEquals("bar", actualMavenArtifactReference.getArtifactId());
    assertEquals("foo", actualMavenArtifactReference.getGroupId());
    assertEquals("foo:bar", actualMavenArtifactReference.getId());
    assertEquals("foo:bar", actualMavenArtifactReference.getPath());
    assertNull(actualMavenArtifactReference.getFallbackVersion());
  }

  /**
   * Test {@link MavenArtifactReference#MavenArtifactReference(String, String, String, String,
   * String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Id is {@code null:null}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactReference#MavenArtifactReference(String, String,
   * String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenArtifactReference.<init>(String, String, String, String, String)"})
  public void testNewMavenArtifactReference_whenNull_thenReturnIdIsNullNull() {
    // Arrange and Act
    MavenArtifactReference actualMavenArtifactReference =
        new MavenArtifactReference(null, null, null, null, null);

    // Assert
    assertEquals("null:null", actualMavenArtifactReference.getId());
    assertEquals("null:null", actualMavenArtifactReference.getPath());
    assertNull(actualMavenArtifactReference.getArtifactId());
    assertNull(actualMavenArtifactReference.getClassifier());
    assertNull(actualMavenArtifactReference.getFallbackVersion());
    assertNull(actualMavenArtifactReference.getGroupId());
    assertNull(actualMavenArtifactReference.getVersion());
  }

  /**
   * Test {@link MavenArtifactReference#MavenArtifactReference(String)}.
   *
   * <ul>
   *   <li>When {@code Ref}.
   *   <li>Then return ArtifactId is {@code Ref}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactReference#MavenArtifactReference(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenArtifactReference.<init>(String)"})
  public void testNewMavenArtifactReference_whenRef_thenReturnArtifactIdIsRef() {
    // Arrange and Act
    MavenArtifactReference actualMavenArtifactReference = new MavenArtifactReference("Ref");

    // Assert
    assertEquals("Ref", actualMavenArtifactReference.getArtifactId());
    assertEquals("Ref", actualMavenArtifactReference.getGroupId());
    assertEquals("Ref:Ref", actualMavenArtifactReference.getId());
    assertEquals("Ref:Ref", actualMavenArtifactReference.getPath());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MavenArtifactReference#setResolveOptionalDependencies(boolean)}
   *   <li>{@link MavenArtifactReference#toString()}
   *   <li>{@link MavenArtifactReference#getArtifactId()}
   *   <li>{@link MavenArtifactReference#getClassifier()}
   *   <li>{@link MavenArtifactReference#getFallbackVersion()}
   *   <li>{@link MavenArtifactReference#getGroupId()}
   *   <li>{@link MavenArtifactReference#getId()}
   *   <li>{@link MavenArtifactReference#getPath()}
   *   <li>{@link MavenArtifactReference#getVersion()}
   *   <li>{@link MavenArtifactReference#isResolveOptionalDependencies()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String MavenArtifactReference.getArtifactId()",
    "String MavenArtifactReference.getClassifier()",
    "String MavenArtifactReference.getFallbackVersion()",
    "String MavenArtifactReference.getGroupId()",
    "String MavenArtifactReference.getId()",
    "String MavenArtifactReference.getPath()",
    "String MavenArtifactReference.getVersion()",
    "boolean MavenArtifactReference.isResolveOptionalDependencies()",
    "void MavenArtifactReference.setResolveOptionalDependencies(boolean)",
    "String MavenArtifactReference.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    MavenArtifactReference mavenArtifactReference = new MavenArtifactReference("Ref");

    // Act
    mavenArtifactReference.setResolveOptionalDependencies(true);
    String actualToStringResult = mavenArtifactReference.toString();
    String actualArtifactId = mavenArtifactReference.getArtifactId();
    String actualClassifier = mavenArtifactReference.getClassifier();
    String actualFallbackVersion = mavenArtifactReference.getFallbackVersion();
    String actualGroupId = mavenArtifactReference.getGroupId();
    String actualId = mavenArtifactReference.getId();
    String actualPath = mavenArtifactReference.getPath();
    String actualVersion = mavenArtifactReference.getVersion();

    // Assert
    assertEquals("Ref", actualArtifactId);
    assertEquals("Ref", actualGroupId);
    assertEquals("Ref:Ref", actualId);
    assertEquals("Ref:Ref", actualPath);
    assertEquals("Ref:Ref", actualToStringResult);
    assertNull(actualClassifier);
    assertNull(actualFallbackVersion);
    assertTrue(mavenArtifactReference.isResolveOptionalDependencies());
    assertEquals(MavenArtifactReference.VERSION_PATTERN_RELEASE, actualVersion);
  }

  /**
   * Test {@link MavenArtifactReference#equals(Object)}, and {@link
   * MavenArtifactReference#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MavenArtifactReference#equals(Object)}
   *   <li>{@link MavenArtifactReference#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MavenArtifactReference.hashCode()"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    MavenArtifactReference mavenArtifactReference = new MavenArtifactReference("Ref");

    // Act and Assert
    assertEquals(mavenArtifactReference, mavenArtifactReference);
    int expectedHashCodeResult = mavenArtifactReference.hashCode();
    assertEquals(expectedHashCodeResult, mavenArtifactReference.hashCode());
  }

  /**
   * Test {@link MavenArtifactReference#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactReference#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MavenArtifactReference.hashCode()"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    MavenArtifactReference mavenArtifactReference = new MavenArtifactReference("Ref");

    // Act and Assert
    assertNotEquals(mavenArtifactReference, new MavenArtifactReference("Ref"));
  }

  /**
   * Test {@link MavenArtifactReference#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactReference#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MavenArtifactReference.hashCode()"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new MavenArtifactReference("Ref"), null);
  }

  /**
   * Test {@link MavenArtifactReference#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactReference#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int MavenArtifactReference.hashCode()"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new MavenArtifactReference("Ref"), "Different type to MavenArtifactReference");
  }

  /**
   * Test {@link MavenArtifactReference#makeId(IMavenIdentifier)}.
   *
   * <p>Method under test: {@link MavenArtifactReference#makeId(IMavenIdentifier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactReference.makeId(IMavenIdentifier)"})
  public void testMakeId() {
    // Arrange
    MavenArtifact identifier =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", ":", "1.0.2");

    // Act and Assert
    assertEquals("42:42:::1.0.2", MavenArtifactReference.makeId(identifier));
  }

  /**
   * Test {@link MavenArtifactReference#makeId(IMavenIdentifier)}.
   *
   * <p>Method under test: {@link MavenArtifactReference#makeId(IMavenIdentifier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactReference.makeId(IMavenIdentifier)"})
  public void testMakeId2() {
    // Arrange
    MavenArtifactReference identifier =
        new MavenArtifactReference("42", "42", ":", "1.0.2", "1.0.2");

    // Act and Assert
    assertEquals("42:42:::1.0.2", MavenArtifactReference.makeId(identifier));
  }

  /**
   * Test {@link MavenArtifactReference#makeId(IMavenIdentifier)}.
   *
   * <ul>
   *   <li>Then return {@code 42:42::}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactReference#makeId(IMavenIdentifier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactReference.makeId(IMavenIdentifier)"})
  public void testMakeId_thenReturn4242() {
    // Arrange
    MavenArtifactDependency identifier =
        new MavenArtifactDependency("42", "42", ":", "1.0.2", Scope.COMPILE, true);

    // Act and Assert
    assertEquals("42:42::", MavenArtifactReference.makeId(identifier));
  }

  /**
   * Test {@link MavenArtifactReference#makeId(IMavenIdentifier)}.
   *
   * <ul>
   *   <li>When {@link MavenArtifactReference#MavenArtifactReference(String)} with {@code Ref}.
   *   <li>Then return {@code Ref:Ref}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactReference#makeId(IMavenIdentifier)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactReference.makeId(IMavenIdentifier)"})
  public void testMakeId_whenMavenArtifactReferenceWithRef_thenReturnRefRef() {
    // Arrange, Act and Assert
    assertEquals("Ref:Ref", MavenArtifactReference.makeId(new MavenArtifactReference("Ref")));
  }
}
