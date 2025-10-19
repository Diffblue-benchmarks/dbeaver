package org.jkiss.dbeaver.registry.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.util.List;
import org.jkiss.dbeaver.registry.maven.MavenRepository.RepositoryType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MavenArtifactVersionDiffblueTest {
  /**
   * Test {@link MavenArtifactVersion#createInvalidVersion(MavenArtifact, String)}.
   *
   * <p>Method under test: {@link MavenArtifactVersion#createInvalidVersion(MavenArtifact, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifactVersion.createInvalidVersion(MavenArtifact, String)"
  })
  public void testCreateInvalidVersion() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act
    MavenArtifactVersion actualCreateInvalidVersionResult =
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2");

    // Assert
    assertEquals("1.0.2", actualCreateInvalidVersionResult.getFallbackVersion());
    assertEquals("1.0.2", actualCreateInvalidVersionResult.getVersion());
    assertEquals("42", actualCreateInvalidVersionResult.getArtifactId());
    assertEquals("42", actualCreateInvalidVersionResult.getGroupId());
    assertEquals("42:42:Classifier:1.0.2", actualCreateInvalidVersionResult.getId());
    assertEquals("42:42:Classifier:1.0.2:1.0.2", actualCreateInvalidVersionResult.getPath());
    assertEquals("Classifier", actualCreateInvalidVersionResult.getClassifier());
    assertEquals(
        "https://repo1.maven.org/maven2/42/42/1.0.2/42-1.0.2-Classifier.jar",
        actualCreateInvalidVersionResult.getExternalURL());
    assertNull(actualCreateInvalidVersionResult.getDescription());
    assertNull(actualCreateInvalidVersionResult.getName());
    assertNull(actualCreateInvalidVersionResult.getPackaging());
    assertNull(actualCreateInvalidVersionResult.getUrl());
    assertNull(actualCreateInvalidVersionResult.getParent());
    assertTrue(actualCreateInvalidVersionResult.getActiveRepositories().isEmpty());
    assertTrue(actualCreateInvalidVersionResult.getDependencies().isEmpty());
    assertTrue(actualCreateInvalidVersionResult.getLicenses().isEmpty());
    assertTrue(actualCreateInvalidVersionResult.getProfiles().isEmpty());
    assertTrue(actualCreateInvalidVersionResult.isInvalidVersion());
    assertSame(artifact, actualCreateInvalidVersionResult.getArtifact());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MavenArtifactVersion#toString()}
   *   <li>{@link MavenArtifactVersion#getArtifact()}
   *   <li>{@link MavenArtifactVersion#getDescription()}
   *   <li>{@link MavenArtifactVersion#getLicenses()}
   *   <li>{@link MavenArtifactVersion#getName()}
   *   <li>{@link MavenArtifactVersion#getPackaging()}
   *   <li>{@link MavenArtifactVersion#getParent()}
   *   <li>{@link MavenArtifactVersion#getProfiles()}
   *   <li>{@link MavenArtifactVersion#getUrl()}
   *   <li>{@link MavenArtifactVersion#getVersion()}
   *   <li>{@link MavenArtifactVersion#isInvalidVersion()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifact MavenArtifactVersion.getArtifact()",
    "String MavenArtifactVersion.getDescription()",
    "List MavenArtifactVersion.getLicenses()",
    "String MavenArtifactVersion.getName()",
    "String MavenArtifactVersion.getPackaging()",
    "MavenArtifactVersion MavenArtifactVersion.getParent()",
    "List MavenArtifactVersion.getProfiles()",
    "String MavenArtifactVersion.getUrl()",
    "String MavenArtifactVersion.getVersion()",
    "boolean MavenArtifactVersion.isInvalidVersion()",
    "String MavenArtifactVersion.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");
    MavenArtifactVersion createInvalidVersionResult =
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2");

    // Act
    String actualToStringResult = createInvalidVersionResult.toString();
    MavenArtifact actualArtifact = createInvalidVersionResult.getArtifact();
    String actualDescription = createInvalidVersionResult.getDescription();
    List<MavenArtifactLicense> actualLicenses = createInvalidVersionResult.getLicenses();
    String actualName = createInvalidVersionResult.getName();
    String actualPackaging = createInvalidVersionResult.getPackaging();
    MavenArtifactVersion actualParent = createInvalidVersionResult.getParent();
    List<MavenProfile> actualProfiles = createInvalidVersionResult.getProfiles();
    String actualUrl = createInvalidVersionResult.getUrl();
    String actualVersion = createInvalidVersionResult.getVersion();
    boolean actualIsInvalidVersionResult = createInvalidVersionResult.isInvalidVersion();

    // Assert
    assertEquals("1.0.2", actualVersion);
    assertEquals("42:42:Classifier:1.0.2:1.0.2", actualToStringResult);
    assertNull(actualDescription);
    assertNull(actualName);
    assertNull(actualPackaging);
    assertNull(actualUrl);
    assertNull(actualParent);
    assertTrue(actualLicenses.isEmpty());
    assertTrue(actualProfiles.isEmpty());
    assertTrue(actualIsInvalidVersionResult);
    assertSame(artifact, actualArtifact);
  }

  /**
   * Test {@link MavenArtifactVersion#getGroupId()}.
   *
   * <ul>
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactVersion#getGroupId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactVersion.getGroupId()"})
  public void testGetGroupId_thenReturn42() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals("42", MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2").getGroupId());
  }

  /**
   * Test {@link MavenArtifactVersion#getArtifactId()}.
   *
   * <ul>
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactVersion#getArtifactId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactVersion.getArtifactId()"})
  public void testGetArtifactId_thenReturn42() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals(
        "42", MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2").getArtifactId());
  }

  /**
   * Test {@link MavenArtifactVersion#getClassifier()}.
   *
   * <ul>
   *   <li>Then return {@code Classifier}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactVersion#getClassifier()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactVersion.getClassifier()"})
  public void testGetClassifier_thenReturnClassifier() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals(
        "Classifier", MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2").getClassifier());
  }

  /**
   * Test {@link MavenArtifactVersion#getFallbackVersion()}.
   *
   * <ul>
   *   <li>Then return {@code 1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactVersion#getFallbackVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactVersion.getFallbackVersion()"})
  public void testGetFallbackVersion_thenReturn102() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals(
        "1.0.2", MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2").getFallbackVersion());
  }

  /**
   * Test {@link MavenArtifactVersion#getId()}.
   *
   * <ul>
   *   <li>Then return {@code 42:42:Classifier:1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactVersion#getId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactVersion.getId()"})
  public void testGetId_thenReturn4242Classifier102() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals(
        "42:42:Classifier:1.0.2",
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2").getId());
  }

  /**
   * Test {@link MavenArtifactVersion#getDependencies()}.
   *
   * <p>Method under test: {@link MavenArtifactVersion#getDependencies()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List MavenArtifactVersion.getDependencies()"})
  public void testGetDependencies() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertTrue(
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2").getDependencies().isEmpty());
  }

  /**
   * Test {@link MavenArtifactVersion#getCacheFile()}.
   *
   * <p>Method under test: {@link MavenArtifactVersion#getCacheFile()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.nio.file.Path MavenArtifactVersion.getCacheFile()"})
  public void testGetCacheFile() {
    // Arrange
    MavenRepository repository =
        new MavenRepository(
            "42", MavenArtifact.FILE_JAR, MavenArtifact.FILE_JAR, RepositoryType.LOCAL);
    MavenArtifact artifact = new MavenArtifact(repository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    File toFileResult =
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2").getCacheFile().toFile();
    assertEquals("42-1.0.2-Classifier.jar", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link MavenArtifactVersion#getCacheFile()}.
   *
   * <ul>
   *   <li>Then return toFile Name is {@code 42-1.0.2-Classifier.jar}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactVersion#getCacheFile()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.nio.file.Path MavenArtifactVersion.getCacheFile()"})
  public void testGetCacheFile_thenReturnToFileNameIs42102ClassifierJar() {
    // Arrange
    MavenRepository repository =
        new MavenRepository(
            "42", MavenArtifact.FILE_JAR, "https://example.org/example", RepositoryType.LOCAL);
    MavenArtifact artifact = new MavenArtifact(repository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    File toFileResult =
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2").getCacheFile().toFile();
    assertEquals("42-1.0.2-Classifier.jar", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link MavenArtifactVersion#getCacheFile()}.
   *
   * <ul>
   *   <li>Then return toFile Name is {@code 42-1.0.2.jar}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactVersion#getCacheFile()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.nio.file.Path MavenArtifactVersion.getCacheFile()"})
  public void testGetCacheFile_thenReturnToFileNameIs42102Jar() {
    // Arrange
    MavenRepository repository =
        new MavenRepository(
            "42", MavenArtifact.FILE_JAR, "https://example.org/example", RepositoryType.LOCAL);
    MavenArtifact artifact = new MavenArtifact(repository, "42", "42", "", "1.0.2");

    // Act and Assert
    File toFileResult =
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2").getCacheFile().toFile();
    assertEquals("42-1.0.2.jar", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link MavenArtifactVersion#getExternalURL(String)} with {@code String}.
   *
   * <p>Method under test: {@link MavenArtifactVersion#getExternalURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactVersion.getExternalURL(String)"})
  public void testGetExternalURLWithString() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals(
        "https://repo1.maven.org/maven2/42/42/1.0.2/42-1.0.2.https://example.org/example",
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2")
            .getExternalURL("https://example.org/example"));
  }

  /**
   * Test {@link MavenArtifactVersion#getExternalURL(String)} with {@code String}.
   *
   * <p>Method under test: {@link MavenArtifactVersion#getExternalURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactVersion.getExternalURL(String)"})
  public void testGetExternalURLWithString2() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals(
        "https://repo1.maven.org/maven2/42/42/1.0.2/42-1.0.2-Classifier.jar",
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2")
            .getExternalURL(MavenArtifact.FILE_JAR));
  }

  /**
   * Test {@link MavenArtifactVersion#getExternalURL(String)} with {@code String}.
   *
   * <ul>
   *   <li>Then return {@code https://repo1.maven.org/maven2/42/42/1.0.2/42-1.0.2.jar}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactVersion#getExternalURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactVersion.getExternalURL(String)"})
  public void testGetExternalURLWithString_thenReturnHttpsRepo1MavenOrgMaven2424210242102Jar() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "", "1.0.2");

    // Act and Assert
    assertEquals(
        "https://repo1.maven.org/maven2/42/42/1.0.2/42-1.0.2.jar",
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2")
            .getExternalURL(MavenArtifact.FILE_JAR));
  }

  /**
   * Test {@link MavenArtifactVersion#getExternalURL()}.
   *
   * <ul>
   *   <li>Then return {@code https://repo1.maven.org/maven2/42/42/1.0.2/42-1.0.2-Classifier.jar}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactVersion#getExternalURL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactVersion.getExternalURL()"})
  public void testGetExternalURL_thenReturnHttpsRepo1MavenOrgMaven2424210242102ClassifierJar() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals(
        "https://repo1.maven.org/maven2/42/42/1.0.2/42-1.0.2-Classifier.jar",
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2").getExternalURL());
  }

  /**
   * Test {@link MavenArtifactVersion#getExternalURL()}.
   *
   * <ul>
   *   <li>Then return {@code https://repo1.maven.org/maven2/42/42/1.0.2/42-1.0.2.jar}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactVersion#getExternalURL()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactVersion.getExternalURL()"})
  public void testGetExternalURL_thenReturnHttpsRepo1MavenOrgMaven2424210242102Jar() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "", "1.0.2");

    // Act and Assert
    assertEquals(
        "https://repo1.maven.org/maven2/42/42/1.0.2/42-1.0.2.jar",
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2").getExternalURL());
  }

  /**
   * Test {@link MavenArtifactVersion#getPath()}.
   *
   * <ul>
   *   <li>Then return {@code 42:42:Classifier:1.0.2:1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifactVersion#getPath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifactVersion.getPath()"})
  public void testGetPath_thenReturn4242Classifier102102() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals(
        "42:42:Classifier:1.0.2:1.0.2",
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2").getPath());
  }

  /**
   * Test {@link MavenArtifactVersion#getActiveRepositories()}.
   *
   * <p>Method under test: {@link MavenArtifactVersion#getActiveRepositories()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Collection MavenArtifactVersion.getActiveRepositories()"})
  public void testGetActiveRepositories() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertTrue(
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2")
            .getActiveRepositories()
            .isEmpty());
  }
}
