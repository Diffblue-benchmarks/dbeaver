package org.jkiss.dbeaver.model.fs.lock;

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mockStatic;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class FileLockDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link FileLock#FileLock(Path)}
   *   <li>{@link FileLock#getLockFilePath()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileLock.<init>(Path)", "Path FileLock.getLockFilePath()"})
  public void testGettersAndSetters() {
    // Arrange
    Path lockFilePath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertSame(lockFilePath, new FileLock(lockFilePath).getLockFilePath());
  }

  /**
   * Test {@link FileLock#unlock()}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#deleteIfExists(Path)} return {@code true}.
   *   <li>Then calls {@link Files#deleteIfExists(Path)}.
   * </ul>
   *
   * <p>Method under test: {@link FileLock#unlock()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileLock.unlock()"})
  public void testUnlock_givenFilesDeleteIfExistsReturnTrue_thenCallsDeleteIfExists()
      throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenReturn(true);
      Path lockFilePath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

      // Act
      new FileLock(lockFilePath).unlock();

      // Assert
      mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
    }
  }

  /**
   * Test {@link FileLock#close()}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#deleteIfExists(Path)} return {@code true}.
   *   <li>Then calls {@link Files#deleteIfExists(Path)}.
   * </ul>
   *
   * <p>Method under test: {@link FileLock#close()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FileLock.close()"})
  public void testClose_givenFilesDeleteIfExistsReturnTrue_thenCallsDeleteIfExists()
      throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.deleteIfExists(Mockito.<Path>any())).thenReturn(true);
      Path lockFilePath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
      try (FileLock fileLock = new FileLock(lockFilePath)) {}

      // Act and Assert
      mockFiles.verify(() -> Files.deleteIfExists(Mockito.<Path>any()));
    }
  }
}
