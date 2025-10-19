package org.jkiss.dbeaver.registry.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MavenProfileDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MavenProfile#MavenProfile(String)}
   *   <li>{@link MavenProfile#getDependencies()}
   *   <li>{@link MavenProfile#getDependencyManagement()}
   *   <li>{@link MavenProfile#getId()}
   *   <li>{@link MavenProfile#getProperties()}
   *   <li>{@link MavenProfile#getRepositories()}
   *   <li>{@link MavenProfile#isActive()}
   *   <li>{@link MavenProfile#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MavenProfile.<init>(String)",
    "List MavenProfile.getDependencies()",
    "List MavenProfile.getDependencyManagement()",
    "String MavenProfile.getId()",
    "Map MavenProfile.getProperties()",
    "List MavenProfile.getRepositories()",
    "boolean MavenProfile.isActive()",
    "String MavenProfile.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    MavenProfile actualMavenProfile = new MavenProfile("42");
    List<MavenArtifactDependency> actualDependencies = actualMavenProfile.getDependencies();
    List<MavenArtifactDependency> actualDependencyManagement =
        actualMavenProfile.getDependencyManagement();
    String actualId = actualMavenProfile.getId();
    Map<String, String> actualProperties = actualMavenProfile.getProperties();
    List<MavenRepository> actualRepositories = actualMavenProfile.getRepositories();
    boolean actualIsActiveResult = actualMavenProfile.isActive();

    // Assert
    assertEquals("42", actualId);
    assertEquals("42", actualMavenProfile.toString());
    assertNull(actualDependencies);
    assertNull(actualDependencyManagement);
    assertNull(actualRepositories);
    assertFalse(actualIsActiveResult);
    assertTrue(actualProperties.isEmpty());
  }

  /**
   * Test {@link MavenProfile#addRepository(MavenRepository)}.
   *
   * <ul>
   *   <li>Then {@link MavenProfile#MavenProfile(String)} with id is {@code 42} Repositories size is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link MavenProfile#addRepository(MavenRepository)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenProfile.addRepository(MavenRepository)"})
  public void testAddRepository_thenMavenProfileWithIdIs42RepositoriesSizeIsOne() {
    // Arrange
    MavenProfile mavenProfile = new MavenProfile("42");

    // Act
    mavenProfile.addRepository(MavenRepository.UnknownRepository);

    // Assert
    List<MavenRepository> repositories = mavenProfile.getRepositories();
    assertEquals(1, repositories.size());
    assertSame(MavenRepository.UnknownRepository, repositories.get(0));
  }

  /**
   * Test {@link MavenProfile#addRepository(MavenRepository)}.
   *
   * <ul>
   *   <li>Then {@link MavenProfile#MavenProfile(String)} with id is {@code 42} Repositories size is
   *       two.
   * </ul>
   *
   * <p>Method under test: {@link MavenProfile#addRepository(MavenRepository)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenProfile.addRepository(MavenRepository)"})
  public void testAddRepository_thenMavenProfileWithIdIs42RepositoriesSizeIsTwo() {
    // Arrange
    MavenProfile mavenProfile = new MavenProfile("42");
    mavenProfile.addRepository(MavenRepository.UnknownRepository);

    // Act
    mavenProfile.addRepository(MavenRepository.UnknownRepository);

    // Assert
    List<MavenRepository> repositories = mavenProfile.getRepositories();
    assertEquals(2, repositories.size());
    MavenRepository mavenRepository = MavenRepository.UnknownRepository;
    assertSame(mavenRepository, repositories.get(0));
    assertSame(mavenRepository, repositories.get(1));
  }
}
