package org.jkiss.dbeaver.model.fs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.net.URI;
import java.nio.file.Paths;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBFFileSystemManagerDiffblueTest {
  /**
   * Test {@link DBFFileSystemManager#DBFFileSystemManager(DBPProject)}.
   *
   * <p>Method under test: {@link DBFFileSystemManager#DBFFileSystemManager(DBPProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBFFileSystemManager.<init>(DBPProject)"})
  public void testNewDBFFileSystemManager() throws DBException {
    // Arrange, Act and Assert
    File toFileResult =
        new DBFFileSystemManager(mock(DBPProject.class))
            .getPathFromString(null, "Path Or Uri")
            .toFile();
    assertEquals("Path Or Uri", toFileResult.getName());
    assertFalse(toFileResult.isAbsolute());
  }

  /**
   * Test {@link DBFFileSystemManager#getPathFromString(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>When {@code Path Or Uri}.
   *   <li>Then return toFile Name is {@code Path Or Uri}.
   * </ul>
   *
   * <p>Method under test: {@link DBFFileSystemManager#getPathFromString(DBRProgressMonitor,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.nio.file.Path DBFFileSystemManager.getPathFromString(DBRProgressMonitor, String)"
  })
  public void testGetPathFromString_whenPathOrUri_thenReturnToFileNameIsPathOrUri()
      throws DBException {
    // Arrange
    DBFFileSystemManager dbfFileSystemManager = new DBFFileSystemManager(mock(DBPProject.class));

    // Act and Assert
    File toFileResult =
        dbfFileSystemManager
            .getPathFromString(new LoggingProgressMonitor(), "Path Or Uri")
            .toFile();
    assertEquals("Path Or Uri", toFileResult.getName());
    assertFalse(toFileResult.isAbsolute());
  }

  /**
   * Test {@link DBFFileSystemManager#getPathFromURI(DBRProgressMonitor, URI)}.
   *
   * <p>Method under test: {@link DBFFileSystemManager#getPathFromURI(DBRProgressMonitor, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "java.nio.file.Path DBFFileSystemManager.getPathFromURI(DBRProgressMonitor, URI)"
  })
  public void testGetPathFromURI() throws DBException {
    // Arrange
    DBFFileSystemManager dbfFileSystemManager = new DBFFileSystemManager(mock(DBPProject.class));

    // Act and Assert
    File toFileResult =
        dbfFileSystemManager
            .getPathFromURI(
                new LoggingProgressMonitor(),
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri())
            .toFile();
    assertEquals("test.txt", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }
}
