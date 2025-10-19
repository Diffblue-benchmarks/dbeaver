package org.jkiss.dbeaver.registry.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.registry.maven.MavenArtifactDependency.Scope;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MavenArtifactDependencyDiffblueTest {
  /**
   * Test {@link MavenArtifactDependency#MavenArtifactDependency(String, String, String, String,
   * Scope, boolean)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return Version is {@code 1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactDependency#MavenArtifactDependency(String, String,
   * String, String, Scope, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MavenArtifactDependency.<init>(String, String, String, String, Scope, boolean)"
  })
  public void testNewMavenArtifactDependency_when42_thenReturnVersionIs102() {
    // Arrange and Act
    MavenArtifactDependency actualMavenArtifactDependency =
        new MavenArtifactDependency("42", "42", "Classifier", "1.0.2", Scope.COMPILE, true);

    // Assert
    assertEquals("1.0.2", actualMavenArtifactDependency.getVersion());
    assertEquals("42", actualMavenArtifactDependency.getArtifactId());
    assertEquals("42", actualMavenArtifactDependency.getGroupId());
    assertEquals("42:42:Classifier", actualMavenArtifactDependency.getId());
    assertEquals("42:42:Classifier", actualMavenArtifactDependency.getPath());
    assertEquals("Classifier", actualMavenArtifactDependency.getClassifier());
  }

  /**
   * Test {@link MavenArtifactDependency#MavenArtifactDependency(String, String, String, String,
   * Scope, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Id is {@code null:null}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactDependency#MavenArtifactDependency(String, String,
   * String, String, Scope, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MavenArtifactDependency.<init>(String, String, String, String, Scope, boolean)"
  })
  public void testNewMavenArtifactDependency_whenNull_thenReturnIdIsNullNull() {
    // Arrange and Act
    MavenArtifactDependency actualMavenArtifactDependency =
        new MavenArtifactDependency(null, null, null, null, Scope.COMPILE, true);

    // Assert
    assertEquals("null:null", actualMavenArtifactDependency.getId());
    assertEquals("null:null", actualMavenArtifactDependency.getPath());
    assertNull(actualMavenArtifactDependency.getArtifactId());
    assertNull(actualMavenArtifactDependency.getClassifier());
    assertNull(actualMavenArtifactDependency.getGroupId());
    assertNull(actualMavenArtifactDependency.getVersion());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MavenArtifactDependency#setBroken(boolean)}
   *   <li>{@link MavenArtifactDependency#getExclusions()}
   *   <li>{@link MavenArtifactDependency#getScope()}
   *   <li>{@link MavenArtifactDependency#isBroken()}
   *   <li>{@link MavenArtifactDependency#isOptional()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List MavenArtifactDependency.getExclusions()",
    "Scope MavenArtifactDependency.getScope()",
    "boolean MavenArtifactDependency.isBroken()",
    "boolean MavenArtifactDependency.isOptional()",
    "void MavenArtifactDependency.setBroken(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange
    MavenArtifactDependency mavenArtifactDependency =
        new MavenArtifactDependency("42", "42", "Classifier", "1.0.2", Scope.COMPILE, true);

    // Act
    mavenArtifactDependency.setBroken(true);
    List<MavenArtifactReference> actualExclusions = mavenArtifactDependency.getExclusions();
    Scope actualScope = mavenArtifactDependency.getScope();
    boolean actualIsBrokenResult = mavenArtifactDependency.isBroken();

    // Assert
    assertNull(actualExclusions);
    assertEquals(Scope.COMPILE, actualScope);
    assertTrue(actualIsBrokenResult);
    assertTrue(mavenArtifactDependency.isOptional());
  }

  /**
   * Test {@link MavenArtifactDependency#addExclusion(MavenArtifactReference)}.
   *
   * <p>Method under test: {@link MavenArtifactDependency#addExclusion(MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenArtifactDependency.addExclusion(MavenArtifactReference)"})
  public void testAddExclusion() {
    // Arrange
    MavenArtifactDependency mavenArtifactDependency =
        new MavenArtifactDependency("42", "42", "Classifier", "1.0.2", Scope.COMPILE, true);
    MavenArtifactReference ref = new MavenArtifactReference("Ref");

    // Act
    mavenArtifactDependency.addExclusion(ref);

    // Assert
    List<MavenArtifactReference> exclusions = mavenArtifactDependency.getExclusions();
    assertEquals(1, exclusions.size());
    assertSame(ref, exclusions.get(0));
  }

  /**
   * Test {@link MavenArtifactDependency#addExclusion(MavenArtifactReference)}.
   *
   * <p>Method under test: {@link MavenArtifactDependency#addExclusion(MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenArtifactDependency.addExclusion(MavenArtifactReference)"})
  public void testAddExclusion2() {
    // Arrange
    MavenArtifactDependency mavenArtifactDependency =
        new MavenArtifactDependency("42", "42", "Classifier", "1.0.2", Scope.COMPILE, true);
    MavenArtifactReference ref = new MavenArtifactReference("Ref");
    mavenArtifactDependency.addExclusion(ref);
    MavenArtifactReference ref2 = new MavenArtifactReference("Ref");

    // Act
    mavenArtifactDependency.addExclusion(ref2);

    // Assert
    List<MavenArtifactReference> exclusions = mavenArtifactDependency.getExclusions();
    assertEquals(2, exclusions.size());
    assertSame(ref, exclusions.get(0));
    assertSame(ref2, exclusions.get(1));
  }
}
