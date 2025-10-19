package org.jkiss.dbeaver.tools.project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import java.util.Map;
import org.eclipse.jface.wizard.WizardPage;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.w3c.dom.Document;

@RunWith(MockitoJUnitRunner.class)
public class ProjectImportDataDiffblueTest {
  @Mock private File file;

  @Mock private Map<String, String> map;

  @InjectMocks private ProjectImportData projectImportData;

  /**
   * Test {@link ProjectImportData#setImportFile(File)}.
   *
   * <p>Method under test: {@link ProjectImportData#setImportFile(File)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProjectImportData.setImportFile(File)"})
  public void testSetImportFile() {
    // Arrange
    ProjectImportData projectImportData = new ProjectImportData();
    File importFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    projectImportData.setImportFile(importFile);

    // Assert
    assertSame(importFile, projectImportData.getImportFile());
  }

  /**
   * Test {@link ProjectImportData#isFileSpecified(WizardPage)}.
   *
   * <p>Method under test: {@link ProjectImportData#isFileSpecified(WizardPage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProjectImportData.isFileSpecified(WizardPage)"})
  public void testIsFileSpecified() {
    // Arrange
    ProjectImportData projectImportData = new ProjectImportData();
    ProjectExportWizardPage page = new ProjectExportWizardPage("Page Name");

    // Act
    boolean actualIsFileSpecifiedResult = projectImportData.isFileSpecified(page);

    // Assert
    assertEquals("Import file not specified", page.getMessage());
    assertEquals(3, page.getMessageType());
    assertFalse(actualIsFileSpecifiedResult);
  }

  /**
   * Test {@link ProjectImportData#isFileSpecified(WizardPage)}.
   *
   * <p>Method under test: {@link ProjectImportData#isFileSpecified(WizardPage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProjectImportData.isFileSpecified(WizardPage)"})
  public void testIsFileSpecified2() {
    // Arrange
    when(file.isFile()).thenReturn(true);
    when(file.exists()).thenReturn(true);
    ProjectExportWizardPage page = new ProjectExportWizardPage("Page Name");

    // Act
    boolean actualIsFileSpecifiedResult = projectImportData.isFileSpecified(page);

    // Assert
    verify(file).exists();
    verify(file).isFile();
    assertEquals("Configure project import settings", page.getMessage());
    assertEquals(0, page.getMessageType());
    assertTrue(actualIsFileSpecifiedResult);
  }

  /**
   * Test {@link ProjectImportData#isFileSpecified(WizardPage)}.
   *
   * <p>Method under test: {@link ProjectImportData#isFileSpecified(WizardPage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProjectImportData.isFileSpecified(WizardPage)"})
  public void testIsFileSpecified3() {
    // Arrange
    when(file.isFile()).thenReturn(false);
    when(file.exists()).thenReturn(true);
    when(file.getAbsolutePath()).thenReturn("Absolute Path");
    ProjectExportWizardPage page = new ProjectExportWizardPage("Page Name");

    // Act
    boolean actualIsFileSpecifiedResult = projectImportData.isFileSpecified(page);

    // Assert
    verify(file).exists();
    verify(file).getAbsolutePath();
    verify(file).isFile();
    assertEquals("File 'Absolute Path' is a directory", page.getMessage());
    assertEquals(3, page.getMessageType());
    assertFalse(actualIsFileSpecifiedResult);
  }

  /**
   * Test {@link ProjectImportData#isFileSpecified(WizardPage)}.
   *
   * <p>Method under test: {@link ProjectImportData#isFileSpecified(WizardPage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProjectImportData.isFileSpecified(WizardPage)"})
  public void testIsFileSpecified4() {
    // Arrange
    when(file.exists()).thenReturn(false);
    when(file.getAbsolutePath()).thenReturn("Absolute Path");
    ProjectExportWizardPage page = new ProjectExportWizardPage("Page Name");

    // Act
    boolean actualIsFileSpecifiedResult = projectImportData.isFileSpecified(page);

    // Assert
    verify(file).exists();
    verify(file).getAbsolutePath();
    assertEquals("File 'Absolute Path' doesn't exist", page.getMessage());
    assertEquals(3, page.getMessageType());
    assertFalse(actualIsFileSpecifiedResult);
  }

  /**
   * Test {@link ProjectImportData#isProjectsSelected(WizardPage)}.
   *
   * <ul>
   *   <li>Given {@link ProjectImportData} (default constructor).
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ProjectImportData#isProjectsSelected(WizardPage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProjectImportData.isProjectsSelected(WizardPage)"})
  public void testIsProjectsSelected_givenProjectImportData_thenReturnFalse() {
    // Arrange
    ProjectImportData projectImportData = new ProjectImportData();

    // Act
    boolean actualIsProjectsSelectedResult =
        projectImportData.isProjectsSelected(new ProjectExportWizardPage("Page Name"));

    // Assert
    assertFalse(actualIsProjectsSelectedResult);
  }

  /**
   * Test {@link ProjectImportData#isProjectsSelected(WizardPage)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ProjectImportData#isProjectsSelected(WizardPage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProjectImportData.isProjectsSelected(WizardPage)"})
  public void testIsProjectsSelected_thenReturnTrue() {
    // Arrange
    ProjectImportData projectImportData = new ProjectImportData();
    projectImportData.addProjectName("Source Name", "Target Name");

    // Act
    boolean actualIsProjectsSelectedResult =
        projectImportData.isProjectsSelected(new ProjectExportWizardPage("Page Name"));

    // Assert
    assertTrue(actualIsProjectsSelectedResult);
  }

  /**
   * Test {@link ProjectImportData#loadArchiveMeta(WizardPage)}.
   *
   * <p>Method under test: {@link ProjectImportData#loadArchiveMeta(WizardPage)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProjectImportData.loadArchiveMeta(WizardPage)"})
  public void testLoadArchiveMeta() {
    // Arrange
    when(file.getAbsolutePath()).thenReturn("Absolute Path");
    when(file.getPath()).thenReturn("Path");
    ProjectExportWizardPage page = new ProjectExportWizardPage("Page Name");

    // Act
    boolean actualLoadArchiveMetaResult = projectImportData.loadArchiveMeta(page);

    // Assert
    verify(file).getAbsolutePath();
    verify(file).getPath();
    assertEquals("Cannot open archive 'Absolute Path': Path", page.getMessage());
    assertEquals(3, page.getMessageType());
    assertFalse(actualLoadArchiveMetaResult);
  }

  /**
   * Test {@link ProjectImportData#getTargetProjectName(String)}.
   *
   * <p>Method under test: {@link ProjectImportData#getTargetProjectName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ProjectImportData.getTargetProjectName(String)"})
  public void testGetTargetProjectName() {
    // Arrange, Act and Assert
    assertNull(new ProjectImportData().getTargetProjectName("Project Name"));
  }

  /**
   * Test {@link ProjectImportData#clearProjectNameMap()}.
   *
   * <p>Method under test: {@link ProjectImportData#clearProjectNameMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProjectImportData.clearProjectNameMap()"})
  public void testClearProjectNameMap() {
    // Arrange
    doNothing().when(map).clear();

    // Act
    projectImportData.clearProjectNameMap();

    // Assert
    verify(map).clear();
  }

  /**
   * Test {@link ProjectImportData#addProjectName(String, String)}.
   *
   * <p>Method under test: {@link ProjectImportData#addProjectName(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProjectImportData.addProjectName(String, String)"})
  public void testAddProjectName() {
    // Arrange
    when(map.put(Mockito.<String>any(), Mockito.<String>any())).thenReturn("Put");

    // Act
    projectImportData.addProjectName("Source Name", "Target Name");

    // Assert
    verify(map).put("Source Name", "Target Name");
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link ProjectImportData}
   *   <li>{@link ProjectImportData#setImportDriverLibraries(boolean)}
   *   <li>{@link ProjectImportData#getImportFile()}
   *   <li>{@link ProjectImportData#getMetaTree()}
   *   <li>{@link ProjectImportData#isImportDriverLibraries()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ProjectImportData.<init>()",
    "File ProjectImportData.getImportFile()",
    "Document ProjectImportData.getMetaTree()",
    "boolean ProjectImportData.isImportDriverLibraries()",
    "void ProjectImportData.setImportDriverLibraries(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    ProjectImportData actualProjectImportData = new ProjectImportData();
    actualProjectImportData.setImportDriverLibraries(true);
    File actualImportFile = actualProjectImportData.getImportFile();
    Document actualMetaTree = actualProjectImportData.getMetaTree();

    // Assert
    assertNull(actualImportFile);
    assertNull(actualMetaTree);
    assertTrue(actualProjectImportData.isImportDriverLibraries());
  }
}
