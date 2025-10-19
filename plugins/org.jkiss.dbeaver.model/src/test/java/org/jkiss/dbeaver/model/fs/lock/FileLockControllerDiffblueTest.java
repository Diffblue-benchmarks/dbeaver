package org.jkiss.dbeaver.model.fs.lock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mockStatic;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.jkiss.dbeaver.DBException;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class FileLockControllerDiffblueTest {
  /**
   * Test {@link FileLockController#FileLockController(String, long, Path)}.
   *
   * <ul>
   *   <li>Then return not FileLocked is {@code foo.txt}.
   * </ul>
   *
   * <p>Method under test: {@link FileLockController#FileLockController(String, long, Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileLockController.<init>(String, long, Path)"})
  public void testNewFileLockController_thenReturnNotFileLockedIsFooTxt() throws DBException {
    // Arrange
    Path metadataFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertFalse(new FileLockController("42", 1L, metadataFolder).isFileLocked("foo.txt"));
  }

  /**
   * Test {@link FileLockController#FileLockController(String, Path)}.
   *
   * <ul>
   *   <li>Then return not FileLocked is {@code foo.txt}.
   * </ul>
   *
   * <p>Method under test: {@link FileLockController#FileLockController(String, Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileLockController.<init>(String, Path)"})
  public void testNewFileLockController_thenReturnNotFileLockedIsFooTxt2() throws DBException {
    // Arrange
    Path metadataFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    FileLockController actualFileLockController = new FileLockController("42", metadataFolder);

    // Assert
    assertFalse(actualFileLockController.isFileLocked("foo.txt"));
  }

  /**
   * Test {@link FileLockController#isLocked(Path)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link FileLockController#isLocked(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FileLockController.isLocked(Path)"})
  public void testIsLocked_thenReturnTrue() throws DBException {
    // Arrange
    Path metadataFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    FileLockController fileLockController = new FileLockController("42", metadataFolder);

    // Act and Assert
    assertTrue(
        fileLockController.isLocked(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
  }

  /**
   * Test {@link FileLockController#isFileLocked(String)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link FileLockController#isFileLocked(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean FileLockController.isFileLocked(String)"})
  public void testIsFileLocked_thenReturnFalse() throws DBException {
    // Arrange
    Path metadataFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    FileLockController fileLockController = new FileLockController("42", metadataFolder);

    // Act and Assert
    assertFalse(fileLockController.isFileLocked("foo.txt"));
  }

  /**
   * Test {@link FileLockController#forceUnlock(Path)}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#deleteIfExists(Path)} return {@code true}.
   *   <li>Then calls {@link Files#deleteIfExists(Path)}.
   * </ul>
   *
   * <p>Method under test: {@link FileLockController#forceUnlock(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileLockController.forceUnlock(Path)"})
  public void testForceUnlock_givenFilesDeleteIfExistsReturnTrue_thenCallsDeleteIfExists()
      throws IOException, DBException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenReturn(true);
      Path metadataFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      FileLockController fileLockController = new FileLockController("42", metadataFolder);

      // Act
      fileLockController.forceUnlock(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
    }
  }

  /**
   * Test {@link FileLockController#forceUnlock(Path)}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#deleteIfExists(Path)} throw {@link
   *       IOException#IOException()}.
   *   <li>Then calls {@link Files#deleteIfExists(Path)}.
   * </ul>
   *
   * <p>Method under test: {@link FileLockController#forceUnlock(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileLockController.forceUnlock(Path)"})
  public void testForceUnlock_givenFilesDeleteIfExistsThrowIOException_thenCallsDeleteIfExists()
      throws IOException, DBException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenThrow(new IOException());
      Path metadataFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      FileLockController fileLockController = new FileLockController("42", metadataFolder);

      // Act
      fileLockController.forceUnlock(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
    }
  }
}
