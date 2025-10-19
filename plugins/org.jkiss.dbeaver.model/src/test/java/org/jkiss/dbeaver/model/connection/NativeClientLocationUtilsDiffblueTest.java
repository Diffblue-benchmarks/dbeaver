package org.jkiss.dbeaver.model.connection;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class NativeClientLocationUtilsDiffblueTest {
  /**
   * Test {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection, Iterable, Function)}.
   *
   * <p>Method under test: {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection,
   * Iterable, Function)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map NativeClientLocationUtils.findLocalClientsOnUnix(Collection, Iterable, Function)"
  })
  public void testFindLocalClientsOnUnix() throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles
          .when(() -> Files.notExists(Mockito.<Path>any(), isA(LinkOption[].class)))
          .thenReturn(false);
      mockFiles
          .when(() -> Files.walkFileTree(Mockito.<Path>any(), Mockito.<FileVisitor<Path>>any()))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      ArrayList<String> extraFoldersToExamine = new ArrayList<>();
      extraFoldersToExamine.add("foo");
      extraFoldersToExamine.add("");

      ArrayList<String> fileEndings = new ArrayList<>();
      fileEndings.add("foo");

      // Act
      Map<String, DBPNativeClientLocation> actualFindLocalClientsOnUnixResult =
          NativeClientLocationUtils.findLocalClientsOnUnix(
              extraFoldersToExamine, fileEndings, mock(Function.class));

      // Assert
      mockFiles.verify(
          () -> Files.notExists(Mockito.<Path>any(), isA(LinkOption[].class)), atLeast(1));
      mockFiles.verify(
          () -> Files.walkFileTree(Mockito.<Path>any(), Mockito.<FileVisitor<Path>>any()),
          atLeast(1));
      assertTrue(actualFindLocalClientsOnUnixResult.isEmpty());
    }
  }

  /**
   * Test {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection, Iterable, Function)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection,
   * Iterable, Function)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map NativeClientLocationUtils.findLocalClientsOnUnix(Collection, Iterable, Function)"
  })
  public void testFindLocalClientsOnUnix_given42_whenArrayListAdd42_thenReturnEmpty() {
    // Arrange
    ArrayList<String> extraFoldersToExamine = new ArrayList<>();
    extraFoldersToExamine.add("42");

    // Act
    Map<String, DBPNativeClientLocation> actualFindLocalClientsOnUnixResult =
        NativeClientLocationUtils.findLocalClientsOnUnix(
            extraFoldersToExamine, new ArrayList<>(), mock(Function.class));

    // Assert
    assertTrue(actualFindLocalClientsOnUnixResult.isEmpty());
  }

  /**
   * Test {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection, Iterable, Function)}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#walkFileTree(Path, FileVisitor)} throw {@link
   *       IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection,
   * Iterable, Function)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map NativeClientLocationUtils.findLocalClientsOnUnix(Collection, Iterable, Function)"
  })
  public void testFindLocalClientsOnUnix_givenFilesWalkFileTreeThrowIOException()
      throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles
          .when(() -> Files.notExists(Mockito.<Path>any(), isA(LinkOption[].class)))
          .thenReturn(false);
      mockFiles
          .when(() -> Files.walkFileTree(Mockito.<Path>any(), Mockito.<FileVisitor<Path>>any()))
          .thenThrow(new IOException());

      ArrayList<String> extraFoldersToExamine = new ArrayList<>();
      extraFoldersToExamine.add("foo");
      extraFoldersToExamine.add("");

      ArrayList<String> fileEndings = new ArrayList<>();
      fileEndings.add("foo");

      // Act
      Map<String, DBPNativeClientLocation> actualFindLocalClientsOnUnixResult =
          NativeClientLocationUtils.findLocalClientsOnUnix(
              extraFoldersToExamine, fileEndings, mock(Function.class));

      // Assert
      mockFiles.verify(
          () -> Files.notExists(Mockito.<Path>any(), isA(LinkOption[].class)), atLeast(1));
      mockFiles.verify(
          () -> Files.walkFileTree(Mockito.<Path>any(), Mockito.<FileVisitor<Path>>any()),
          atLeast(1));
      assertTrue(actualFindLocalClientsOnUnixResult.isEmpty());
    }
  }

  /**
   * Test {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection, Iterable, Function)}.
   *
   * <ul>
   *   <li>Given {@code /usr/bin}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code /usr/bin}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection,
   * Iterable, Function)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map NativeClientLocationUtils.findLocalClientsOnUnix(Collection, Iterable, Function)"
  })
  public void testFindLocalClientsOnUnix_givenUsrBin_whenArrayListAddUsrBin_thenReturnEmpty() {
    // Arrange
    ArrayList<String> extraFoldersToExamine = new ArrayList<>();
    extraFoldersToExamine.add("/usr/bin");

    // Act
    Map<String, DBPNativeClientLocation> actualFindLocalClientsOnUnixResult =
        NativeClientLocationUtils.findLocalClientsOnUnix(
            extraFoldersToExamine, new ArrayList<>(), mock(Function.class));

    // Assert
    assertTrue(actualFindLocalClientsOnUnixResult.isEmpty());
  }

  /**
   * Test {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection, Iterable, Function)}.
   *
   * <ul>
   *   <li>Given {@code /usr/bin}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code /usr/bin}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection,
   * Iterable, Function)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map NativeClientLocationUtils.findLocalClientsOnUnix(Collection, Iterable, Function)"
  })
  public void testFindLocalClientsOnUnix_givenUsrBin_whenArrayListAddUsrBin_thenReturnEmpty2() {
    // Arrange
    ArrayList<String> extraFoldersToExamine = new ArrayList<>();

    ArrayList<String> fileEndings = new ArrayList<>();
    fileEndings.add("/usr/bin");

    // Act
    Map<String, DBPNativeClientLocation> actualFindLocalClientsOnUnixResult =
        NativeClientLocationUtils.findLocalClientsOnUnix(
            extraFoldersToExamine, fileEndings, mock(Function.class));

    // Assert
    assertTrue(actualFindLocalClientsOnUnixResult.isEmpty());
  }

  /**
   * Test {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection, Iterable, Function)}.
   *
   * <ul>
   *   <li>Given {@code /usr/local/bin}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code /usr/local/bin}.
   * </ul>
   *
   * <p>Method under test: {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection,
   * Iterable, Function)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map NativeClientLocationUtils.findLocalClientsOnUnix(Collection, Iterable, Function)"
  })
  public void testFindLocalClientsOnUnix_givenUsrLocalBin_whenArrayListAddUsrLocalBin() {
    // Arrange
    ArrayList<String> extraFoldersToExamine = new ArrayList<>();
    extraFoldersToExamine.add("/usr/local/bin");
    extraFoldersToExamine.add("/usr/bin");

    // Act
    Map<String, DBPNativeClientLocation> actualFindLocalClientsOnUnixResult =
        NativeClientLocationUtils.findLocalClientsOnUnix(
            extraFoldersToExamine, new ArrayList<>(), mock(Function.class));

    // Assert
    assertTrue(actualFindLocalClientsOnUnixResult.isEmpty());
  }

  /**
   * Test {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection, Iterable, Function)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link NativeClientLocationUtils#findLocalClientsOnUnix(Collection,
   * Iterable, Function)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map NativeClientLocationUtils.findLocalClientsOnUnix(Collection, Iterable, Function)"
  })
  public void testFindLocalClientsOnUnix_whenArrayList_thenReturnEmpty() {
    // Arrange
    ArrayList<String> extraFoldersToExamine = new ArrayList<>();

    // Act
    Map<String, DBPNativeClientLocation> actualFindLocalClientsOnUnixResult =
        NativeClientLocationUtils.findLocalClientsOnUnix(
            extraFoldersToExamine, new ArrayList<>(), mock(Function.class));

    // Assert
    assertTrue(actualFindLocalClientsOnUnixResult.isEmpty());
  }
}
