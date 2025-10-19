package org.jkiss.dbeaver.tools.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ProjectImportWizardPageFileDiffblueTest {
  /**
   * Test {@link ProjectImportWizardPageFile#ProjectImportWizardPageFile(ProjectImportData)}.
   *
   * <p>Method under test: {@link
   * ProjectImportWizardPageFile#ProjectImportWizardPageFile(ProjectImportData)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProjectImportWizardPageFile.<init>(ProjectImportData)"})
  public void testNewProjectImportWizardPageFile() {
    // Arrange and Act
    ProjectImportWizardPageFile actualProjectImportWizardPageFile =
        new ProjectImportWizardPageFile(new ProjectImportData());

    // Assert
    assertEquals(
        "Configure project import settings.", actualProjectImportWizardPageFile.getDescription());
    assertEquals("Import project(s)", actualProjectImportWizardPageFile.getTitle());
    assertEquals("Import project(s)", actualProjectImportWizardPageFile.getName());
    assertEquals("Import project(s)", actualProjectImportWizardPageFile.toString());
    assertNull(actualProjectImportWizardPageFile.getErrorMessage());
    assertNull(actualProjectImportWizardPageFile.getMessage());
    assertNull(actualProjectImportWizardPageFile.getWizard());
    assertNull(actualProjectImportWizardPageFile.getNextPage());
    assertNull(actualProjectImportWizardPageFile.getPreviousPage());
    assertNull(actualProjectImportWizardPageFile.getImage());
    assertNull(actualProjectImportWizardPageFile.getMinimumPageSize());
    assertNull(actualProjectImportWizardPageFile.getControl());
    assertNull(actualProjectImportWizardPageFile.getShell());
    assertEquals(0, actualProjectImportWizardPageFile.getMessageType());
    assertFalse(actualProjectImportWizardPageFile.isPageComplete());
  }

  /**
   * Test {@link ProjectImportWizardPageFile#isPageComplete()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ProjectImportWizardPageFile#isPageComplete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProjectImportWizardPageFile.isPageComplete()"})
  public void testIsPageComplete_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new ProjectImportWizardPageFile(new ProjectImportData()).isPageComplete());
  }

  /**
   * Test {@link ProjectImportWizardPageFile#isPageComplete()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ProjectImportWizardPageFile#isPageComplete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProjectImportWizardPageFile.isPageComplete()"})
  public void testIsPageComplete_thenReturnTrue() {
    // Arrange
    ProjectImportData importData = new ProjectImportData();
    importData.addProjectName("Source Name", "Target Name");

    // Act and Assert
    assertTrue(new ProjectImportWizardPageFile(importData).isPageComplete());
  }
}
