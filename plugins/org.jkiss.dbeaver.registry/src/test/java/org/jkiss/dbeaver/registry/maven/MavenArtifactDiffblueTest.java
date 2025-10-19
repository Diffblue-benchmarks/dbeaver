package org.jkiss.dbeaver.registry.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.util.Date;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.ProxyProgressMonitor;
import org.jkiss.dbeaver.model.runtime.VoidProgressMonitor;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.registry.maven.MavenRepository.RepositoryType;
import org.jkiss.dbeaver.registry.task.TaskLoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MavenArtifactDiffblueTest {
  @InjectMocks private MavenArtifact mavenArtifact;

  @Mock private MavenRepository mavenRepository;

  /**
   * Test {@link MavenArtifact#MavenArtifact(MavenRepository, String, String, String, String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return FallbackVersion is {@code 1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#MavenArtifact(MavenRepository, String, String,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenArtifact.<init>(MavenRepository, String, String, String, String)"})
  public void testNewMavenArtifact_when42_thenReturnFallbackVersionIs102() {
    // Arrange and Act
    MavenArtifact actualMavenArtifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Assert
    assertEquals("1.0.2", actualMavenArtifact.getFallbackVersion());
    assertEquals("42", actualMavenArtifact.getArtifactId());
    assertEquals("42", actualMavenArtifact.getGroupId());
    assertEquals("42:42:Classifier:1.0.2", actualMavenArtifact.getId());
    assertEquals("Classifier", actualMavenArtifact.getClassifier());
  }

  /**
   * Test {@link MavenArtifact#MavenArtifact(MavenRepository, String, String, String, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Id is {@code null:null}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#MavenArtifact(MavenRepository, String, String,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenArtifact.<init>(MavenRepository, String, String, String, String)"})
  public void testNewMavenArtifact_whenNull_thenReturnIdIsNullNull() {
    // Arrange and Act
    MavenArtifact actualMavenArtifact =
        new MavenArtifact(MavenRepository.UnknownRepository, null, null, null, null);

    // Assert
    assertEquals("null:null", actualMavenArtifact.getId());
    assertNull(actualMavenArtifact.getArtifactId());
    assertNull(actualMavenArtifact.getClassifier());
    assertNull(actualMavenArtifact.getFallbackVersion());
    assertNull(actualMavenArtifact.getGroupId());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MavenArtifact#toString()}
   *   <li>{@link MavenArtifact#getArtifactId()}
   *   <li>{@link MavenArtifact#getClassifier()}
   *   <li>{@link MavenArtifact#getFallbackVersion()}
   *   <li>{@link MavenArtifact#getGroupId()}
   *   <li>{@link MavenArtifact#getLastUpdate()}
   *   <li>{@link MavenArtifact#getRepository()}
   *   <li>{@link MavenArtifact#getVersion()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String MavenArtifact.getArtifactId()",
    "String MavenArtifact.getClassifier()",
    "String MavenArtifact.getFallbackVersion()",
    "String MavenArtifact.getGroupId()",
    "Date MavenArtifact.getLastUpdate()",
    "MavenRepository MavenArtifact.getRepository()",
    "String MavenArtifact.getVersion()",
    "String MavenArtifact.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    MavenArtifact mavenArtifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act
    String actualToStringResult = mavenArtifact.toString();
    String actualArtifactId = mavenArtifact.getArtifactId();
    String actualClassifier = mavenArtifact.getClassifier();
    String actualFallbackVersion = mavenArtifact.getFallbackVersion();
    String actualGroupId = mavenArtifact.getGroupId();
    Date actualLastUpdate = mavenArtifact.getLastUpdate();
    MavenRepository actualRepository = mavenArtifact.getRepository();

    // Assert
    assertEquals("", mavenArtifact.getVersion());
    assertEquals("1.0.2", actualFallbackVersion);
    assertEquals("42", actualArtifactId);
    assertEquals("42", actualGroupId);
    assertEquals("42:42:Classifier:1.0.2", actualToStringResult);
    assertEquals("Classifier", actualClassifier);
    assertNull(actualLastUpdate);
    assertSame(MavenRepository.UnknownRepository, actualRepository);
  }

  /**
   * Test {@link MavenArtifact#getVersion(String)} with {@code String}.
   *
   * <p>Method under test: {@link MavenArtifact#getVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MavenArtifactVersion MavenArtifact.getVersion(String)"})
  public void testGetVersionWithString() {
    // Arrange
    MavenArtifact mavenArtifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertNull(mavenArtifact.getVersion("1.0.2"));
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion() throws IOException {
    // Arrange
    MavenRepository repository =
        new MavenRepository(
            "42",
            MavenArtifactReference.VERSION_PATTERN_RELEASE,
            "https://example.org/example",
            RepositoryType.LOCAL);
    MavenArtifact mavenArtifact = new MavenArtifact(repository, "42", "42", "Classifier", "1.0.2");
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new VoidProgressMonitor(), mock(DBTTask.class));

    // Act and Assert
    assertNull(mavenArtifact.resolveVersion(new ProxyProgressMonitor(original), "1.0.2", true));
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion2() throws IOException {
    // Arrange
    MavenRepository repository =
        new MavenRepository(
            "42",
            MavenArtifactReference.VERSION_PATTERN_RELEASE,
            "https://example.org/example",
            RepositoryType.LOCAL);
    repository.setIsSnapshot(true);
    MavenArtifact mavenArtifact = new MavenArtifact(repository, "42", "42", "Classifier", "1.0.2");
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));

    // Act and Assert
    assertNull(mavenArtifact.resolveVersion(new ProxyProgressMonitor(original), "1.0.2", true));
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion3() throws IOException {
    // Arrange
    MavenRepository repository =
        new MavenRepository(
            "42",
            MavenArtifactReference.VERSION_PATTERN_RELEASE,
            MavenArtifactReference.VERSION_PATTERN_RELEASE,
            RepositoryType.LOCAL);
    MavenArtifact mavenArtifact = new MavenArtifact(repository, "42", "42", "Classifier", "1.0.2");
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));

    // Act and Assert
    assertNull(mavenArtifact.resolveVersion(new ProxyProgressMonitor(original), "1.0.2", true));
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion4() throws IOException {
    // Arrange
    MavenRepository repository =
        new MavenRepository(
            "42",
            MavenArtifactReference.VERSION_PATTERN_RELEASE,
            "https://example.org/example",
            RepositoryType.LOCAL);
    MavenArtifact mavenArtifact = new MavenArtifact(repository, "42", "42", null, "1.0.2");
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));

    // Act and Assert
    assertNull(mavenArtifact.resolveVersion(new ProxyProgressMonitor(original), "1.0.2", true));
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion5() throws IOException {
    // Arrange
    MavenRepository repository =
        new MavenRepository(
            "42",
            MavenArtifactReference.VERSION_PATTERN_RELEASE,
            "https://example.org/example",
            RepositoryType.LOCAL);
    MavenArtifact mavenArtifact = new MavenArtifact(repository, "42", "42", "Classifier", null);
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));

    // Act and Assert
    assertNull(mavenArtifact.resolveVersion(new ProxyProgressMonitor(original), "1.0.2", true));
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion_thenReturnNull() throws IOException {
    // Arrange
    MavenRepository repository =
        new MavenRepository(
            "42",
            MavenArtifactReference.VERSION_PATTERN_RELEASE,
            "https://example.org/example",
            RepositoryType.LOCAL);
    MavenArtifact mavenArtifact = new MavenArtifact(repository, "42", "42", "Classifier", "1.0.2");
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));

    // Act and Assert
    assertNull(mavenArtifact.resolveVersion(new ProxyProgressMonitor(original), "1.0.2", true));
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion_thenThrowIllegalArgumentException() throws IOException {
    // Arrange
    when(mavenRepository.isSnapshot()).thenThrow(new IllegalArgumentException());
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> mavenArtifact.resolveVersion(new ProxyProgressMonitor(original), "1.0.2", true));
    verify(mavenRepository).isSnapshot();
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion_whenEmptyString_thenThrowIOException() throws IOException {
    // Arrange
    MavenRepository repository =
        new MavenRepository(
            "42",
            MavenArtifactReference.VERSION_PATTERN_RELEASE,
            "https://example.org/example",
            RepositoryType.LOCAL);
    MavenArtifact mavenArtifact = new MavenArtifact(repository, "42", "42", "Classifier", "1.0.2");
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));

    // Act and Assert
    assertThrows(
        IOException.class,
        () -> mavenArtifact.resolveVersion(new ProxyProgressMonitor(original), "", true));
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion_whenNull_thenThrowIOException() throws IOException {
    // Arrange
    MavenRepository repository =
        new MavenRepository(
            "42",
            MavenArtifactReference.VERSION_PATTERN_RELEASE,
            "https://example.org/example",
            RepositoryType.LOCAL);
    MavenArtifact mavenArtifact = new MavenArtifact(repository, "42", "42", "Classifier", "1.0.2");
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));

    // Act and Assert
    assertThrows(
        IOException.class,
        () -> mavenArtifact.resolveVersion(new ProxyProgressMonitor(original), null, true));
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <ul>
   *   <li>When {@link ProxyProgressMonitor#ProxyProgressMonitor(DBRProgressMonitor)} with original
   *       is {@link VoidProgressMonitor} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion_whenProxyProgressMonitorWithOriginalIsVoidProgressMonitor()
      throws IOException {
    // Arrange
    MavenRepository repository =
        new MavenRepository(
            "42",
            MavenArtifactReference.VERSION_PATTERN_RELEASE,
            "https://example.org/example",
            RepositoryType.LOCAL);
    MavenArtifact mavenArtifact = new MavenArtifact(repository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertNull(
        mavenArtifact.resolveVersion(
            new ProxyProgressMonitor(new VoidProgressMonitor()), "1.0.2", true));
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <ul>
   *   <li>When {@link MavenArtifactReference#VERSION_PATTERN_LATEST}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion_whenVersion_pattern_latest_thenThrowIllegalArgumentException()
      throws IOException {
    // Arrange
    when(mavenRepository.isSnapshot()).thenThrow(new IllegalArgumentException());
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            mavenArtifact.resolveVersion(
                new ProxyProgressMonitor(original),
                MavenArtifactReference.VERSION_PATTERN_LATEST,
                true));
    verify(mavenRepository).isSnapshot();
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <ul>
   *   <li>When {@link MavenArtifactReference#VERSION_PATTERN_RELEASE}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion_whenVersion_pattern_release_thenThrowIllegalArgumentException()
      throws IOException {
    // Arrange
    when(mavenRepository.isSnapshot()).thenThrow(new IllegalArgumentException());
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            mavenArtifact.resolveVersion(
                new ProxyProgressMonitor(original),
                MavenArtifactReference.VERSION_PATTERN_RELEASE,
                true));
    verify(mavenRepository).isSnapshot();
  }

  /**
   * Test {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}.
   *
   * <ul>
   *   <li>When {@link MavenArtifactReference#VERSION_PATTERN_SNAPSHOT}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#resolveVersion(DBRProgressMonitor, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenArtifact.resolveVersion(DBRProgressMonitor, String, boolean)"
  })
  public void testResolveVersion_whenVersion_pattern_snapshot() throws IOException {
    // Arrange
    when(mavenRepository.isSnapshot()).thenThrow(new IllegalArgumentException());
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () ->
            mavenArtifact.resolveVersion(
                new ProxyProgressMonitor(original),
                MavenArtifactReference.VERSION_PATTERN_SNAPSHOT,
                true));
    verify(mavenRepository).isSnapshot();
  }

  /**
   * Test {@link MavenArtifact#versionMatches(String, String)}.
   *
   * <ul>
   *   <li>When {@code 0}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#versionMatches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenArtifact.versionMatches(String, String)"})
  public void testVersionMatches_when0_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(MavenArtifact.versionMatches("{", "0"));
  }

  /**
   * Test {@link MavenArtifact#versionMatches(String, String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#versionMatches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenArtifact.versionMatches(String, String)"})
  public void testVersionMatches_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(MavenArtifact.versionMatches("42", "1.0.2"));
  }

  /**
   * Test {@link MavenArtifact#versionMatches(String, String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#versionMatches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenArtifact.versionMatches(String, String)"})
  public void testVersionMatches_when102_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(MavenArtifact.versionMatches("1.0.2", "1.0.2"));
  }

  /**
   * Test {@link MavenArtifact#versionMatches(String, String)}.
   *
   * <ul>
   *   <li>When {@code -}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#versionMatches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenArtifact.versionMatches(String, String)"})
  public void testVersionMatches_whenDash_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(MavenArtifact.versionMatches("-", "1.0.2"));
  }

  /**
   * Test {@link MavenArtifact#versionMatches(String, String)}.
   *
   * <ul>
   *   <li>When {@code .}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#versionMatches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenArtifact.versionMatches(String, String)"})
  public void testVersionMatches_whenDot_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(MavenArtifact.versionMatches(".", "1.0.2"));
  }

  /**
   * Test {@link MavenArtifact#versionMatches(String, String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#versionMatches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenArtifact.versionMatches(String, String)"})
  public void testVersionMatches_whenEmptyString_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MavenArtifact.versionMatches("1.0.2", ""));
  }

  /**
   * Test {@link MavenArtifact#versionMatches(String, String)}.
   * <ul>
   *   <li>When {@code {}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenArtifact#versionMatches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenArtifact.versionMatches(String, String)"})
  public void testVersionMatches_whenLeftCurlyBracket_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(MavenArtifact.versionMatches("{", "1.0.2"));
  }

  /**
   * Test {@link MavenArtifact#versionMatches(String, String)}.
   * <ul>
   *   <li>When {@code {}.</li>
   *   <li>Then return {@code true}.</li>
   * </ul>
   * <p>
   * Method under test: {@link MavenArtifact#versionMatches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenArtifact.versionMatches(String, String)"})
  public void testVersionMatches_whenLeftCurlyBracket_thenReturnTrue2() {
    // Arrange, Act and Assert
    assertTrue(MavenArtifact.versionMatches("1.0.2", "{"));
  }

  /**
   * Test {@link MavenArtifact#versionMatches(String, String)}.
   *
   * <ul>
   *   <li>When {@code (}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#versionMatches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenArtifact.versionMatches(String, String)"})
  public void testVersionMatches_whenLeftParenthesis_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MavenArtifact.versionMatches("1.0.2", "("));
  }

  /**
   * Test {@link MavenArtifact#versionMatches(String, String)}.
   *
   * <ul>
   *   <li>When {@code [}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#versionMatches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenArtifact.versionMatches(String, String)"})
  public void testVersionMatches_whenLeftSquareBracket_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(MavenArtifact.versionMatches("1.0.2", "["));
  }

  /**
   * Test {@link MavenArtifact#versionMatches(String, String)}.
   *
   * <ul>
   *   <li>When {@code MavenArtifact}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#versionMatches(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MavenArtifact.versionMatches(String, String)"})
  public void testVersionMatches_whenOrgJkissDbeaverRegistryMavenMavenArtifact_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        MavenArtifact.versionMatches("org.jkiss.dbeaver.registry.maven.MavenArtifact", "1.0.2"));
  }

  /**
   * Test {@link MavenArtifact#getId()}.
   *
   * <ul>
   *   <li>Given {@link MavenArtifact}.
   *   <li>Then return {@code null:null}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#getId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifact.getId()"})
  public void testGetId_givenMavenArtifact_thenReturnNullNull() {
    // Arrange, Act and Assert
    assertEquals("null:null", mavenArtifact.getId());
  }

  /**
   * Test {@link MavenArtifact#getId()}.
   *
   * <ul>
   *   <li>Then return {@code 42:42:Classifier:1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#getId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifact.getId()"})
  public void testGetId_thenReturn4242Classifier102() {
    // Arrange
    MavenArtifact mavenArtifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals("42:42:Classifier:1.0.2", mavenArtifact.getId());
  }

  /**
   * Test {@link MavenArtifact#getFileURL(String, String, boolean)}.
   *
   * <p>Method under test: {@link MavenArtifact#getFileURL(String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifact.getFileURL(String, String, boolean)"})
  public void testGetFileURL() {
    // Arrange
    MavenArtifact mavenArtifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "", "1.0.2");

    // Act and Assert
    assertEquals(
        "https://repo1.maven.org/maven2/42/42/https://example.org/example/42-https://example.org/example.jar",
        mavenArtifact.getFileURL("https://example.org/example", MavenArtifact.FILE_JAR, false));
  }

  /**
   * Test {@link MavenArtifact#getFileURL(String, String, boolean)}.
   *
   * <ul>
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#getFileURL(String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifact.getFileURL(String, String, boolean)"})
  public void testGetFileURL_thenReturnAString() {
    // Arrange
    MavenArtifact mavenArtifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals(
        "https://repo1.maven.org/maven2/42/42/https://example.org/example/42-https://example.org/example"
            + "-Classifier.jar",
        mavenArtifact.getFileURL("https://example.org/example", MavenArtifact.FILE_JAR, false));
  }

  /**
   * Test {@link MavenArtifact#getFileURL(String, String, boolean)}.
   *
   * <ul>
   *   <li>Then return a string.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#getFileURL(String, String, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifact.getFileURL(String, String, boolean)"})
  public void testGetFileURL_thenReturnAString2() {
    // Arrange
    MavenArtifact mavenArtifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals(
        "https://repo1.maven.org/maven2/42/42/https://example.org/example/42-https://example.org/example.https"
            + "://example.org/example",
        mavenArtifact.getFileURL(
            "https://example.org/example", "https://example.org/example", false));
  }

  /**
   * Test {@link MavenArtifact#getVersionFileName(String, String)}.
   *
   * <ul>
   *   <li>Given {@link MavenArtifact}.
   *   <li>When {@link MavenArtifact#FILE_JAR}.
   *   <li>Then return {@code null-1.0.2.jar}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#getVersionFileName(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifact.getVersionFileName(String, String)"})
  public void testGetVersionFileName_givenMavenArtifact_whenFile_jar_thenReturnNull102Jar() {
    // Arrange, Act and Assert
    assertEquals(
        "null-1.0.2.jar", mavenArtifact.getVersionFileName("1.0.2", MavenArtifact.FILE_JAR));
  }

  /**
   * Test {@link MavenArtifact#getVersionFileName(String, String)}.
   *
   * <ul>
   *   <li>Then return {@code 42-1.0.2-Classifier.jar}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#getVersionFileName(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifact.getVersionFileName(String, String)"})
  public void testGetVersionFileName_thenReturn42102ClassifierJar() {
    // Arrange
    MavenArtifact mavenArtifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals(
        "42-1.0.2-Classifier.jar",
        mavenArtifact.getVersionFileName("1.0.2", MavenArtifact.FILE_JAR));
  }

  /**
   * Test {@link MavenArtifact#getVersionFileName(String, String)}.
   *
   * <ul>
   *   <li>Then return {@code 42-1.0.2.jar}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#getVersionFileName(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifact.getVersionFileName(String, String)"})
  public void testGetVersionFileName_thenReturn42102Jar() {
    // Arrange
    MavenArtifact mavenArtifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "", "1.0.2");

    // Act and Assert
    assertEquals("42-1.0.2.jar", mavenArtifact.getVersionFileName("1.0.2", MavenArtifact.FILE_JAR));
  }

  /**
   * Test {@link MavenArtifact#getVersionFileName(String, String)}.
   *
   * <ul>
   *   <li>When {@code File Type}.
   *   <li>Then return {@code 42-1.0.2.File Type}.
   * </ul>
   *
   * <p>Method under test: {@link MavenArtifact#getVersionFileName(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MavenArtifact.getVersionFileName(String, String)"})
  public void testGetVersionFileName_whenFileType_thenReturn42102FileType() {
    // Arrange
    MavenArtifact mavenArtifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");

    // Act and Assert
    assertEquals("42-1.0.2.File Type", mavenArtifact.getVersionFileName("1.0.2", "File Type"));
  }
}
