package org.jkiss.dbeaver.registry.maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.runtime.ProxyProgressMonitor;
import org.jkiss.dbeaver.model.task.DBTTask;
import org.jkiss.dbeaver.registry.task.TaskLoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MavenRegistryDiffblueTest {
  @InjectMocks private MavenRegistry mavenRegistry;

  @Mock private MavenRepository mavenRepository;

  /**
   * Test {@link MavenRegistry#setCustomRepositories(List)}.
   *
   * <ul>
   *   <li>Given {@link MavenRegistry}.
   *   <li>Then {@link MavenRegistry} Repositories Empty.
   * </ul>
   *
   * <p>Method under test: {@link MavenRegistry#setCustomRepositories(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRegistry.setCustomRepositories(List)"})
  public void testSetCustomRepositories_givenMavenRegistry_thenMavenRegistryRepositoriesEmpty() {
    // Arrange and Act
    mavenRegistry.setCustomRepositories(new ArrayList<>());

    // Assert that nothing has changed
    assertTrue(mavenRegistry.getRepositories().isEmpty());
  }

  /**
   * Test {@link MavenRegistry#findArtifact(DBRProgressMonitor, MavenArtifactVersion,
   * MavenArtifactReference)}.
   *
   * <p>Method under test: {@link MavenRegistry#findArtifact(DBRProgressMonitor,
   * MavenArtifactVersion, MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenRegistry.findArtifact(DBRProgressMonitor, MavenArtifactVersion, MavenArtifactReference)"
  })
  public void testFindArtifact() {
    // Arrange
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));
    ProxyProgressMonitor monitor = new ProxyProgressMonitor(original);
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");
    MavenArtifactVersion createInvalidVersionResult =
        MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2");
    when(mavenRepository.findArtifact(
            Mockito.<DBRProgressMonitor>any(), Mockito.<MavenArtifactReference>any()))
        .thenReturn(createInvalidVersionResult);
    MavenArtifact artifact2 = new MavenArtifact(mavenRepository, "42", "42", "Classifier", "1.0.2");
    MavenArtifactVersion owner = MavenArtifactVersion.createInvalidVersion(artifact2, "1.0.2");

    // Act
    MavenArtifactVersion actualFindArtifactResult =
        mavenRegistry.findArtifact(monitor, owner, new MavenArtifactReference("Ref"));

    // Assert
    verify(mavenRepository)
        .findArtifact(isA(DBRProgressMonitor.class), isA(MavenArtifactReference.class));
    assertSame(createInvalidVersionResult, actualFindArtifactResult);
  }

  /**
   * Test {@link MavenRegistry#findArtifact(DBRProgressMonitor, MavenArtifactVersion,
   * MavenArtifactReference)}.
   *
   * <p>Method under test: {@link MavenRegistry#findArtifact(DBRProgressMonitor,
   * MavenArtifactVersion, MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenRegistry.findArtifact(DBRProgressMonitor, MavenArtifactVersion, MavenArtifactReference)"
  })
  public void testFindArtifact2() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");
    when(mavenRepository.findArtifact(
            Mockito.<DBRProgressMonitor>any(), Mockito.<MavenArtifactReference>any()))
        .thenReturn(MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2"));
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));
    ProxyProgressMonitor monitor = new ProxyProgressMonitor(original);
    MavenArtifact artifact2 = new MavenArtifact(mavenRepository, "42", "42", "Classifier", "1.0.2");
    MavenArtifactVersion createInvalidVersionResult =
        MavenArtifactVersion.createInvalidVersion(artifact2, "1.0.2");
    when(mavenRepository.findArtifact(
            Mockito.<DBRProgressMonitor>any(), Mockito.<MavenArtifactReference>any()))
        .thenReturn(createInvalidVersionResult);

    // Act
    MavenArtifactVersion actualFindArtifactResult =
        mavenRegistry.findArtifact(monitor, null, new MavenArtifactReference("Ref"));

    // Assert
    verify(mavenRepository, atLeast(1))
        .findArtifact(isA(DBRProgressMonitor.class), isA(MavenArtifactReference.class));
    assertSame(createInvalidVersionResult, actualFindArtifactResult);
  }

  /**
   * Test {@link MavenRegistry#findArtifact(DBRProgressMonitor, MavenArtifactVersion,
   * MavenArtifactReference)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link MavenRepository} {@link MavenRepository#findArtifact(DBRProgressMonitor,
   *       MavenArtifactReference)} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRegistry#findArtifact(DBRProgressMonitor,
   * MavenArtifactVersion, MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenRegistry.findArtifact(DBRProgressMonitor, MavenArtifactVersion, MavenArtifactReference)"
  })
  public void testFindArtifact_givenNull_whenMavenRepositoryFindArtifactReturnNull() {
    // Arrange
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));
    ProxyProgressMonitor monitor = new ProxyProgressMonitor(original);
    when(mavenRepository.findArtifact(
            Mockito.<DBRProgressMonitor>any(), Mockito.<MavenArtifactReference>any()))
        .thenReturn(null);
    MavenArtifact artifact = new MavenArtifact(mavenRepository, "42", "42", "Classifier", "1.0.2");
    MavenArtifactVersion owner = MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2");

    // Act
    MavenArtifactVersion actualFindArtifactResult =
        mavenRegistry.findArtifact(monitor, owner, new MavenArtifactReference("Ref"));

    // Assert
    verify(mavenRepository)
        .findArtifact(isA(DBRProgressMonitor.class), isA(MavenArtifactReference.class));
    MavenArtifact artifact2 = actualFindArtifactResult.getArtifact();
    assertEquals("Ref", artifact2.getArtifactId());
    assertEquals("Ref", artifact2.getGroupId());
    assertEquals("Ref", actualFindArtifactResult.getArtifactId());
    assertEquals("Ref", actualFindArtifactResult.getGroupId());
    assertEquals("Ref:Ref", artifact2.getId());
    assertEquals("Ref:Ref", actualFindArtifactResult.getId());
    assertEquals("Ref:Ref:RELEASE", actualFindArtifactResult.getPath());
    assertEquals(
        "https://repo1.maven.org/maven2/Ref/Ref/RELEASE/Ref-RELEASE.jar",
        actualFindArtifactResult.getExternalURL());
    assertNull(artifact2.getClassifier());
    assertNull(artifact2.getFallbackVersion());
    assertNull(actualFindArtifactResult.getClassifier());
    assertNull(actualFindArtifactResult.getFallbackVersion());
    assertEquals(
        MavenArtifactReference.VERSION_PATTERN_RELEASE, actualFindArtifactResult.getVersion());
  }

  /**
   * Test {@link MavenRegistry#findArtifact(DBRProgressMonitor, MavenArtifactVersion,
   * MavenArtifactReference)}.
   *
   * <ul>
   *   <li>Then return Artifact ArtifactId is {@code Ref}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRegistry#findArtifact(DBRProgressMonitor,
   * MavenArtifactVersion, MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "MavenArtifactVersion MavenRegistry.findArtifact(DBRProgressMonitor, MavenArtifactVersion, MavenArtifactReference)"
  })
  public void testFindArtifact_thenReturnArtifactArtifactIdIsRef() {
    // Arrange
    MavenArtifact artifact =
        new MavenArtifact(MavenRepository.UnknownRepository, "42", "42", "Classifier", "1.0.2");
    when(mavenRepository.findArtifact(
            Mockito.<DBRProgressMonitor>any(), Mockito.<MavenArtifactReference>any()))
        .thenReturn(MavenArtifactVersion.createInvalidVersion(artifact, "1.0.2"));
    TaskLoggingProgressMonitor original =
        new TaskLoggingProgressMonitor(new LoggingProgressMonitor(), mock(DBTTask.class));
    ProxyProgressMonitor monitor = new ProxyProgressMonitor(original);
    when(mavenRepository.findArtifact(
            Mockito.<DBRProgressMonitor>any(), Mockito.<MavenArtifactReference>any()))
        .thenReturn(null);

    // Act
    MavenArtifactVersion actualFindArtifactResult =
        mavenRegistry.findArtifact(monitor, null, new MavenArtifactReference("Ref"));

    // Assert
    verify(mavenRepository, atLeast(1))
        .findArtifact(isA(DBRProgressMonitor.class), isA(MavenArtifactReference.class));
    MavenArtifact artifact2 = actualFindArtifactResult.getArtifact();
    assertEquals("Ref", artifact2.getArtifactId());
    assertEquals("Ref", artifact2.getGroupId());
    assertEquals("Ref", actualFindArtifactResult.getArtifactId());
    assertEquals("Ref", actualFindArtifactResult.getGroupId());
    assertEquals("Ref:Ref", artifact2.getId());
    assertEquals("Ref:Ref", actualFindArtifactResult.getId());
    assertEquals("Ref:Ref:RELEASE", actualFindArtifactResult.getPath());
    assertEquals(
        "https://repo1.maven.org/maven2/Ref/Ref/RELEASE/Ref-RELEASE.jar",
        actualFindArtifactResult.getExternalURL());
    assertNull(artifact2.getClassifier());
    assertNull(artifact2.getFallbackVersion());
    assertNull(actualFindArtifactResult.getClassifier());
    assertNull(actualFindArtifactResult.getFallbackVersion());
    assertEquals(
        MavenArtifactReference.VERSION_PATTERN_RELEASE, actualFindArtifactResult.getVersion());
  }

  /**
   * Test {@link MavenRegistry#resetArtifactInfo(MavenArtifactReference)}.
   *
   * <ul>
   *   <li>Then calls {@link MavenRepository#resetArtifactCache(MavenArtifactReference)}.
   * </ul>
   *
   * <p>Method under test: {@link MavenRegistry#resetArtifactInfo(MavenArtifactReference)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MavenRegistry.resetArtifactInfo(MavenArtifactReference)"})
  public void testResetArtifactInfo_thenCallsResetArtifactCache() {
    // Arrange
    doNothing().when(mavenRepository).resetArtifactCache(Mockito.<MavenArtifactReference>any());

    // Act
    mavenRegistry.resetArtifactInfo(new MavenArtifactReference("Ref"));

    // Assert
    verify(mavenRepository).resetArtifactCache(isA(MavenArtifactReference.class));
  }
}
