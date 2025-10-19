package org.jkiss.dbeaver.tools.scripts;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eclipse.core.resources.IResource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ScriptsExportDataDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ScriptsExportData#ScriptsExportData(Collection, boolean, File)}
   *   <li>{@link ScriptsExportData#getOutputFolder()}
   *   <li>{@link ScriptsExportData#getScripts()}
   *   <li>{@link ScriptsExportData#isOverwriteFiles()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ScriptsExportData.<init>(Collection, boolean, File)",
    "File ScriptsExportData.getOutputFolder()",
    "Collection ScriptsExportData.getScripts()",
    "boolean ScriptsExportData.isOverwriteFiles()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<IResource> scripts = new ArrayList<>();
    File outputFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    ScriptsExportData actualScriptsExportData = new ScriptsExportData(scripts, true, outputFolder);
    File actualOutputFolder = actualScriptsExportData.getOutputFolder();
    Collection<IResource> actualScripts = actualScriptsExportData.getScripts();

    // Assert
    assertTrue(actualScripts instanceof List);
    assertTrue(actualScriptsExportData.isOverwriteFiles());
    assertSame(scripts, actualScripts);
    assertSame(outputFolder, actualOutputFolder);
  }
}
