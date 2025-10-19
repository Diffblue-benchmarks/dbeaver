package org.jkiss.dbeaver.model.fs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.CopyOption;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DBRRunnableContext;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class DBFUtilsDiffblueTest {
  /**
   * Test {@link DBFUtils#resolvePathFromString(DBRProgressMonitor, DBPProject, String)} with {@code
   * monitor}, {@code project}, {@code pathOrUri}.
   *
   * <p>Method under test: {@link DBFUtils#resolvePathFromString(DBRProgressMonitor, DBPProject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path DBFUtils.resolvePathFromString(DBRProgressMonitor, DBPProject, String)"})
  public void testResolvePathFromStringWithMonitorProjectPathOrUri() throws DBException {
    // Arrange and Act
    Path actualResolvePathFromStringResult =
        DBFUtils.resolvePathFromString(new LoggingProgressMonitor(), null, "Path Or Uri");

    // Assert
    File toFileResult = actualResolvePathFromStringResult.toFile();
    assertEquals("Path Or Uri", toFileResult.getName());
    assertFalse(toFileResult.isAbsolute());
  }

  /**
   * Test {@link DBFUtils#resolvePathFromString(DBRProgressMonitor, DBPProject, String)} with {@code
   * monitor}, {@code project}, {@code pathOrUri}.
   *
   * <p>Method under test: {@link DBFUtils#resolvePathFromString(DBRProgressMonitor, DBPProject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path DBFUtils.resolvePathFromString(DBRProgressMonitor, DBPProject, String)"})
  public void testResolvePathFromStringWithMonitorProjectPathOrUri2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPProject project = mock(DBPProject.class);
    when(project.getFileSystemManager())
        .thenReturn(new DBFFileSystemManager(mock(DBPProject.class)));

    // Act
    Path actualResolvePathFromStringResult =
        DBFUtils.resolvePathFromString(monitor, project, "Path Or Uri");

    // Assert
    verify(project).getFileSystemManager();
    File toFileResult = actualResolvePathFromStringResult.toFile();
    assertEquals("Path Or Uri", toFileResult.getName());
    assertFalse(toFileResult.isAbsolute());
  }

  /**
   * Test {@link DBFUtils#resolvePathFromString(DBRRunnableContext, DBPProject, String)} with {@code
   * runnableContext}, {@code project}, {@code pathOrUri}.
   *
   * <p>Method under test: {@link DBFUtils#resolvePathFromString(DBRRunnableContext, DBPProject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path DBFUtils.resolvePathFromString(DBRRunnableContext, DBPProject, String)"})
  public void testResolvePathFromStringWithRunnableContextProjectPathOrUri() throws DBException {
    // Arrange and Act
    Path actualResolvePathFromStringResult =
        DBFUtils.resolvePathFromString(
            mock(DBRRunnableContext.class), mock(DBPProject.class), "Path Or Uri");

    // Assert
    File toFileResult = actualResolvePathFromStringResult.toFile();
    assertEquals("Path Or Uri", toFileResult.getName());
    assertFalse(toFileResult.isAbsolute());
  }

  /**
   * Test {@link DBFUtils#resolvePathFromString(DBRRunnableContext, DBPProject, String)} with {@code
   * runnableContext}, {@code project}, {@code pathOrUri}.
   *
   * <p>Method under test: {@link DBFUtils#resolvePathFromString(DBRRunnableContext, DBPProject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path DBFUtils.resolvePathFromString(DBRRunnableContext, DBPProject, String)"})
  public void testResolvePathFromStringWithRunnableContextProjectPathOrUri2() throws DBException {
    // Arrange and Act
    Path actualResolvePathFromStringResult =
        DBFUtils.resolvePathFromString(mock(DBRRunnableContext.class), null, "file:");

    // Assert
    File toFileResult = actualResolvePathFromStringResult.toFile();
    assertEquals("file:", toFileResult.getName());
    assertFalse(toFileResult.isAbsolute());
  }

  /**
   * Test {@link DBFUtils#resolvePathFromString(DBRRunnableContext, DBPProject, String)} with {@code
   * runnableContext}, {@code project}, {@code pathOrUri}.
   *
   * <p>Method under test: {@link DBFUtils#resolvePathFromString(DBRRunnableContext, DBPProject,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path DBFUtils.resolvePathFromString(DBRRunnableContext, DBPProject, String)"})
  public void testResolvePathFromStringWithRunnableContextProjectPathOrUri3() throws DBException {
    // Arrange and Act
    Path actualResolvePathFromStringResult =
        DBFUtils.resolvePathFromString(mock(DBRRunnableContext.class), null, ":/");

    // Assert
    File toFileResult = actualResolvePathFromStringResult.toFile();
    assertEquals(":", toFileResult.getName());
    assertFalse(toFileResult.isAbsolute());
  }

  /**
   * Test {@link DBFUtils#resolvePathFromURI(DBRProgressMonitor, DBPProject, URI)}.
   *
   * <ul>
   *   <li>Then return toFile Name is {@code test.txt}.
   * </ul>
   *
   * <p>Method under test: {@link DBFUtils#resolvePathFromURI(DBRProgressMonitor, DBPProject, URI)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Path DBFUtils.resolvePathFromURI(DBRProgressMonitor, DBPProject, URI)"})
  public void testResolvePathFromURI_thenReturnToFileNameIsTestTxt() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBPProject project = mock(DBPProject.class);
    when(project.getFileSystemManager())
        .thenReturn(new DBFFileSystemManager(mock(DBPProject.class)));

    // Act
    Path actualResolvePathFromURIResult =
        DBFUtils.resolvePathFromURI(
            monitor, project, Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri());

    // Assert
    verify(project).getFileSystemManager();
    File toFileResult = actualResolvePathFromURIResult.toFile();
    assertEquals("test.txt", toFileResult.getName());
    assertTrue(toFileResult.isAbsolute());
  }

  /**
   * Test {@link DBFUtils#convertPathToString(Path)}.
   *
   * <p>Method under test: {@link DBFUtils#convertPathToString(Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBFUtils.convertPathToString(Path)"})
  public void testConvertPathToString() {
    // Arrange, Act and Assert
    assertEquals(
        Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toString(),
        DBFUtils.convertPathToString(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
  }

  /**
   * Test {@link DBFUtils#getQueryParameters(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBFUtils#getQueryParameters(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBFUtils.getQueryParameters(String)"})
  public void testGetQueryParameters_whenEmptyString_thenReturnEmpty() {
    // Arrange and Act
    Map<String, String> actualQueryParameters = DBFUtils.getQueryParameters("");

    // Assert
    assertTrue(actualQueryParameters.isEmpty());
  }

  /**
   * Test {@link DBFUtils#getQueryParameters(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBFUtils#getQueryParameters(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBFUtils.getQueryParameters(String)"})
  public void testGetQueryParameters_whenNull_thenReturnEmpty() {
    // Arrange and Act
    Map<String, String> actualQueryParameters = DBFUtils.getQueryParameters(null);

    // Assert
    assertTrue(actualQueryParameters.isEmpty());
  }

  /**
   * Test {@link DBFUtils#getQueryParameters(String)}.
   *
   * <ul>
   *   <li>When {@code Query=Query}.
   *   <li>Then return {@code Query}.
   * </ul>
   *
   * <p>Method under test: {@link DBFUtils#getQueryParameters(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBFUtils.getQueryParameters(String)"})
  public void testGetQueryParameters_whenQueryQuery_thenReturnQuery() {
    // Arrange and Act
    Map<String, String> actualQueryParameters = DBFUtils.getQueryParameters("Query=Query");

    // Assert
    assertEquals(1, actualQueryParameters.size());
    assertEquals("Query", actualQueryParameters.get("Query"));
  }

  /**
   * Test {@link DBFUtils#getQueryParameters(String)}.
   *
   * <ul>
   *   <li>When {@code Query}.
   *   <li>Then return {@code Query} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBFUtils#getQueryParameters(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBFUtils.getQueryParameters(String)"})
  public void testGetQueryParameters_whenQuery_thenReturnQueryIsNull() {
    // Arrange and Act
    Map<String, String> actualQueryParameters = DBFUtils.getQueryParameters("Query");

    // Assert
    assertEquals(1, actualQueryParameters.size());
    assertNull(actualQueryParameters.get("Query"));
  }

  /**
   * Test {@link DBFUtils#getQueryParameters(String)}.
   *
   * <ul>
   *   <li>When {@code Query=}.
   *   <li>Then return {@code Query} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBFUtils#getQueryParameters(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBFUtils.getQueryParameters(String)"})
  public void testGetQueryParameters_whenQuery_thenReturnQueryIsNull2() {
    // Arrange and Act
    Map<String, String> actualQueryParameters = DBFUtils.getQueryParameters("Query=");

    // Assert
    assertEquals(1, actualQueryParameters.size());
    assertNull(actualQueryParameters.get("Query"));
  }

  /**
   * Test {@link DBFUtils#getFileSystemId(FileSystem)}.
   *
   * <p>Method under test: {@link DBFUtils#getFileSystemId(FileSystem)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBFUtils.getFileSystemId(FileSystem)"})
  public void testGetFileSystemId() {
    // Arrange, Act and Assert
    assertNull(DBFUtils.getFileSystemId(FileSystems.getDefault()));
  }

  /**
   * Test {@link DBFUtils#move(Path, Path)}.
   *
   * <ul>
   *   <li>Given {@link Files} {@link Files#move(Path, Path, CopyOption[])} throw {@link
   *       IOException#IOException()}.
   *   <li>Then throw {@link IOException}.
   * </ul>
   *
   * <p>Method under test: {@link DBFUtils#move(Path, Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBFUtils.move(Path, Path)"})
  public void testMove_givenFilesMoveThrowIOException_thenThrowIOException() throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles
          .when(() -> Files.move(Mockito.<Path>any(), Mockito.<Path>any(), isA(CopyOption[].class)))
          .thenThrow(new IOException());

      // Act and Assert
      assertThrows(
          IOException.class,
          () ->
              DBFUtils.move(
                  Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"),
                  Paths.get(System.getProperty("java.io.tmpdir"), "test.txt")));
      mockFiles.verify(
          () -> Files.move(Mockito.<Path>any(), Mockito.<Path>any(), isA(CopyOption[].class)));
    }
  }

  /**
   * Test {@link DBFUtils#move(Path, Path)}.
   *
   * <ul>
   *   <li>Then calls {@link Files#move(Path, Path, CopyOption[])}.
   * </ul>
   *
   * <p>Method under test: {@link DBFUtils#move(Path, Path)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBFUtils.move(Path, Path)"})
  public void testMove_thenCallsMove() throws IOException {
    // Arrange
    try (MockedStatic<Files> mockFiles = mockStatic(Files.class)) {
      mockFiles
          .when(() -> Files.move(Mockito.<Path>any(), Mockito.<Path>any(), isA(CopyOption[].class)))
          .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Act
      DBFUtils.move(
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"),
          Paths.get(System.getProperty("java.io.tmpdir"), "test.txt"));

      // Assert
      mockFiles.verify(
          () -> Files.move(Mockito.<Path>any(), Mockito.<Path>any(), isA(CopyOption[].class)));
    }
  }
}
