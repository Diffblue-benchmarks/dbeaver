package org.jkiss.dbeaver.launcher;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mockStatic;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class LauncherUtilsDiffblueTest {
  /**
   * Test {@link LauncherUtils#toFileURL(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example}.
   *   <li>Then return Name is {@code example}.
   * </ul>
   *
   * <p>Method under test: {@link LauncherUtils#toFileURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"File LauncherUtils.toFileURL(String)"})
  public void testToFileURL_whenHttpsExampleOrgExample_thenReturnNameIsExample() {
    // Arrange and Act
    File actualToFileURLResult = LauncherUtils.toFileURL("https://example.org/example");

    // Assert
    assertEquals("example", actualToFileURLResult.getName());
    assertFalse(actualToFileURLResult.isAbsolute());
  }

  /**
   * Test {@link LauncherUtils#toFileURL(String)}.
   *
   * <ul>
   *   <li>When {@code https://example.org/example\/}.
   *   <li>Then return Name is {@code example\}.
   * </ul>
   *
   * <p>Method under test: {@link LauncherUtils#toFileURL(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"File LauncherUtils.toFileURL(String)"})
  public void testToFileURL_whenHttpsExampleOrgExample_thenReturnNameIsExample2() {
    // Arrange and Act
    File actualToFileURLResult = LauncherUtils.toFileURL("https://example.org/example\\/");

    // Assert
    assertEquals("example\\", actualToFileURLResult.getName());
    assertFalse(actualToFileURLResult.isAbsolute());
  }

  /**
   * Test {@link LauncherUtils#adjustTrailingSlash(URL, boolean)}.
   *
   * <p>Method under test: {@link LauncherUtils#adjustTrailingSlash(URL, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URL LauncherUtils.adjustTrailingSlash(URL, boolean)"})
  public void testAdjustTrailingSlash() throws MalformedURLException {
    // Arrange and Act
    URL actualAdjustTrailingSlashResult =
        LauncherUtils.adjustTrailingSlash(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(), true);

    // Assert
    String expectedToStringResult =
        String.join(
            "",
            "file:",
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")
                .toString()
                .concat(File.separator));
    assertEquals(expectedToStringResult, actualAdjustTrailingSlashResult.toString());
  }

  /**
   * Test {@link LauncherUtils#adjustTrailingSlash(URL, boolean)}.
   *
   * <p>Method under test: {@link LauncherUtils#adjustTrailingSlash(URL, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"URL LauncherUtils.adjustTrailingSlash(URL, boolean)"})
  public void testAdjustTrailingSlash2() throws MalformedURLException {
    // Arrange and Act
    URL actualAdjustTrailingSlashResult =
        LauncherUtils.adjustTrailingSlash(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri().toURL(), false);

    // Assert
    String expectedToStringResult =
        String.join(
            "", "file:", Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString());
    assertEquals(expectedToStringResult, actualAdjustTrailingSlashResult.toString());
  }

  /**
   * Test {@link LauncherUtils#canWrite(File)}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#isWritable(Path)} return {@code false}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LauncherUtils#canWrite(File)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LauncherUtils.canWrite(File)"})
  public void testCanWrite_givenFilesIsWritableReturnFalse_thenReturnTrue() {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.isWritable(Mockito.<Path>any())).thenReturn(false);

      // Act
      boolean actualCanWriteResult =
          LauncherUtils.canWrite(
              Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

      // Assert
      mockFiles.verify(() -> Files.isWritable(Mockito.<Path>any()));
      assertTrue(actualCanWriteResult);
    }
  }

  /**
   * Test {@link LauncherUtils#canWrite(File)}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#isWritable(Path)} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LauncherUtils#canWrite(File)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LauncherUtils.canWrite(File)"})
  public void testCanWrite_givenFilesIsWritableReturnTrue_thenReturnTrue() {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.isWritable(Mockito.<Path>any())).thenReturn(true);

      // Act
      boolean actualCanWriteResult =
          LauncherUtils.canWrite(
              Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

      // Assert
      mockFiles.verify(() -> Files.isWritable(Mockito.<Path>any()));
      assertTrue(actualCanWriteResult);
    }
  }

  /**
   * Test {@link LauncherUtils#canWrite(File)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link LauncherUtils#canWrite(File)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LauncherUtils.canWrite(File)"})
  public void testCanWrite_thenThrowIllegalArgumentException() {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles
          .when(() -> Files.isWritable(Mockito.<Path>any()))
          .thenThrow(new IllegalArgumentException());

      // Act and Assert
      assertThrows(
          IllegalArgumentException.class,
          () ->
              LauncherUtils.canWrite(
                  Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
      mockFiles.verify(() -> Files.isWritable(Mockito.<Path>any()));
    }
  }

  /**
   * Test {@link LauncherUtils#canWrite(File)}.
   *
   * <ul>
   *   <li>When Property is {@code java.io.tmpdir} is {@code writableArea} toFile.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LauncherUtils#canWrite(File)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LauncherUtils.canWrite(File)"})
  public void testCanWrite_whenPropertyIsJavaIoTmpdirIsWritableAreaToFile_thenReturnFalse() {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles.when(() -> Files.isWritable(Mockito.<Path>any())).thenReturn(false);

      // Act and Assert
      assertFalse(
          LauncherUtils.canWrite(
              Paths.get(System.getProperty("java.io.tmpdir"), "writableArea").toFile()));
    }
  }

  /**
   * Test {@link LauncherUtils#escape(String)}.
   *
   * <ul>
   *   <li>When {@code Original}.
   *   <li>Then return {@code Original}.
   * </ul>
   *
   * <p>Method under test: {@link LauncherUtils#escape(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String LauncherUtils.escape(String)"})
  public void testEscape_whenOriginal_thenReturnOriginal() {
    // Arrange, Act and Assert
    assertEquals("Original", LauncherUtils.escape("Original"));
  }

  /**
   * Test {@link LauncherUtils#escape(String)}.
   *
   * <ul>
   *   <li>When {@code /}.
   *   <li>Then return {@code \/}.
   * </ul>
   *
   * <p>Method under test: {@link LauncherUtils#escape(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String LauncherUtils.escape(String)"})
  public void testEscape_whenSlash_thenReturnBackslashSlash() {
    // Arrange, Act and Assert
    assertEquals("\\/", LauncherUtils.escape("/"));
  }
}
