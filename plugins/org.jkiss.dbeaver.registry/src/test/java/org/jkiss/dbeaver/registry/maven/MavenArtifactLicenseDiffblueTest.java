package org.jkiss.dbeaver.registry.maven;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MavenArtifactLicenseDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MavenArtifactLicense#MavenArtifactLicense(String, String)}
   *   <li>{@link MavenArtifactLicense#getName()}
   *   <li>{@link MavenArtifactLicense#getUrl()}
   *   <li>{@link MavenArtifactLicense#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MavenArtifactLicense.<init>(String, String)",
    "String MavenArtifactLicense.getName()",
    "String MavenArtifactLicense.getUrl()",
    "String MavenArtifactLicense.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    MavenArtifactLicense actualMavenArtifactLicense =
        new MavenArtifactLicense("Name", "https://example.org/example");
    String actualName = actualMavenArtifactLicense.getName();
    String actualUrl = actualMavenArtifactLicense.getUrl();

    // Assert
    assertEquals("Name", actualName);
    assertEquals("Name", actualMavenArtifactLicense.toString());
    assertEquals("https://example.org/example", actualUrl);
  }
}
