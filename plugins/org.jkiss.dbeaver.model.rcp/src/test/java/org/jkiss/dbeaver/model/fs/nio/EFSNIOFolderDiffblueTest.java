package org.jkiss.dbeaver.model.fs.nio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.jkiss.dbeaver.model.fs.DBFVirtualFileSystemRoot;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOResource.FeatureNotSupportedException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EFSNIOFolderDiffblueTest {
  /**
   * Test {@link EFSNIOFolder#getType()}.
   *
   * <p>Method under test: {@link EFSNIOFolder#getType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int EFSNIOFolder.getType()"})
  public void testGetType() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder efsnioFolder = new EFSNIOFolder(root, backendFolder);

    // Act and Assert
    assertEquals(2, efsnioFolder.getType());
  }

  /**
   * Test {@link EFSNIOFolder#createLink(IPath, int, IProgressMonitor)} with {@code localLocation},
   * {@code updateFlags}, {@code monitor}.
   *
   * <p>Method under test: {@link EFSNIOFolder#createLink(IPath, int, IProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EFSNIOFolder.createLink(IPath, int, IProgressMonitor)"})
  public void testCreateLinkWithLocalLocationUpdateFlagsMonitor() throws CoreException {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder efsnioFolder = new EFSNIOFolder(root, backendFolder);

    // Act and Assert
    assertThrows(
        FeatureNotSupportedException.class,
        () -> efsnioFolder.createLink(IPath.EMPTY, 1, new NullProgressMonitor()));
  }

  /**
   * Test {@link EFSNIOFolder#createLink(URI, int, IProgressMonitor)} with {@code location}, {@code
   * updateFlags}, {@code monitor}.
   *
   * <p>Method under test: {@link EFSNIOFolder#createLink(URI, int, IProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EFSNIOFolder.createLink(URI, int, IProgressMonitor)"})
  public void testCreateLinkWithLocationUpdateFlagsMonitor() throws CoreException {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder efsnioFolder = new EFSNIOFolder(root, backendFolder);
    URI location = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();

    // Act and Assert
    assertThrows(
        FeatureNotSupportedException.class,
        () -> efsnioFolder.createLink(location, 1, new NullProgressMonitor()));
  }

  /**
   * Test {@link EFSNIOFolder#getFile(String)} with {@code name}.
   *
   * <p>Method under test: {@link EFSNIOFolder#getFile(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IFile EFSNIOFolder.getFile(String)"})
  public void testGetFileWithName() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder efsnioFolder = new EFSNIOFolder(root, backendFolder);

    // Act
    IFile actualFile = efsnioFolder.getFile("");

    // Assert
    assertTrue(actualFile instanceof EFSNIOFile);
    assertEquals(efsnioFolder, actualFile);
  }

  /**
   * Test {@link EFSNIOFolder#getFile(String)} with {@code name}.
   *
   * <ul>
   *   <li>Then return NioPath toFile Name is {@code .dbeaver-placeholder}.
   * </ul>
   *
   * <p>Method under test: {@link EFSNIOFolder#getFile(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IFile EFSNIOFolder.getFile(String)"})
  public void testGetFileWithName_thenReturnNioPathToFileNameIsDbeaverPlaceholder() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder efsnioFolder = new EFSNIOFolder(root, backendFolder);

    // Act
    IFile actualFile = efsnioFolder.getFile(".dbeaver-placeholder");

    // Assert
    IPath fullPath = actualFile.getFullPath();
    assertTrue(fullPath instanceof org.eclipse.core.runtime.Path);
    assertTrue(actualFile instanceof EFSNIOFile);
    IContainer parent = actualFile.getParent();
    assertTrue(parent.getParent() instanceof EFSNIOFolder);
    assertTrue(parent instanceof EFSNIOFolder);
    assertEquals(".dbeaver-placeholder", ((EFSNIOFile) actualFile).getNioPath().toFile().getName());
    assertEquals(".dbeaver-placeholder", fullPath.toFile().getName());
    assertEquals(".dbeaver-placeholder", actualFile.getName());
    assertEquals("dbeaver-placeholder", actualFile.getFileExtension());
    assertEquals("dbeaver-placeholder", fullPath.getFileExtension());
  }

  /**
   * Test {@link EFSNIOFolder#getFile(String)} with {@code name}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return NioPath toFile Name is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link EFSNIOFolder#getFile(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IFile EFSNIOFolder.getFile(String)"})
  public void testGetFileWithName_when42_thenReturnNioPathToFileNameIs42() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder efsnioFolder = new EFSNIOFolder(root, backendFolder);

    // Act
    IFile actualFile = efsnioFolder.getFile("42");

    // Assert
    IPath fullPath = actualFile.getFullPath();
    assertTrue(fullPath instanceof org.eclipse.core.runtime.Path);
    assertTrue(actualFile instanceof EFSNIOFile);
    IContainer parent = actualFile.getParent();
    assertTrue(parent.getParent() instanceof EFSNIOFolder);
    assertTrue(parent instanceof EFSNIOFolder);
    assertEquals("42", ((EFSNIOFile) actualFile).getNioPath().toFile().getName());
    assertEquals("42", fullPath.toFile().getName());
    assertEquals("42", actualFile.getName());
  }

  /**
   * Test {@link EFSNIOFolder#getFile(String)} with {@code name}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return NioPath toFile Name is {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link EFSNIOFolder#getFile(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IFile EFSNIOFolder.getFile(String)"})
  public void testGetFileWithName_whenName_thenReturnNioPathToFileNameIsName() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder efsnioFolder = new EFSNIOFolder(root, backendFolder);

    // Act
    IFile actualFile = efsnioFolder.getFile("Name");

    // Assert
    IPath fullPath = actualFile.getFullPath();
    assertTrue(fullPath instanceof org.eclipse.core.runtime.Path);
    assertTrue(actualFile instanceof EFSNIOFile);
    IContainer parent = actualFile.getParent();
    assertTrue(parent.getParent() instanceof EFSNIOFolder);
    assertTrue(parent instanceof EFSNIOFolder);
    assertEquals("Name", ((EFSNIOFile) actualFile).getNioPath().toFile().getName());
    assertEquals("Name", fullPath.toFile().getName());
    assertEquals("Name", actualFile.getName());
  }

  /**
   * Test {@link EFSNIOFolder#getFolder(String)} with {@code name}.
   *
   * <p>Method under test: {@link EFSNIOFolder#getFolder(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IFolder EFSNIOFolder.getFolder(String)"})
  public void testGetFolderWithName() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder efsnioFolder = new EFSNIOFolder(root, backendFolder);

    // Act
    IFolder actualFolder = efsnioFolder.getFolder("");

    // Assert
    assertTrue(actualFolder instanceof EFSNIOFolder);
    assertEquals(efsnioFolder, actualFolder);
  }

  /**
   * Test {@link EFSNIOFolder#getFolder(String)} with {@code name}.
   *
   * <ul>
   *   <li>Then return NioPath toFile Name is {@code .dbeaver-placeholder}.
   * </ul>
   *
   * <p>Method under test: {@link EFSNIOFolder#getFolder(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IFolder EFSNIOFolder.getFolder(String)"})
  public void testGetFolderWithName_thenReturnNioPathToFileNameIsDbeaverPlaceholder() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder efsnioFolder = new EFSNIOFolder(root, backendFolder);

    // Act
    IFolder actualFolder = efsnioFolder.getFolder(".dbeaver-placeholder");

    // Assert
    IPath fullPath = actualFolder.getFullPath();
    assertTrue(fullPath instanceof org.eclipse.core.runtime.Path);
    IContainer parent = actualFolder.getParent();
    assertTrue(parent.getParent() instanceof EFSNIOFolder);
    assertTrue(parent instanceof EFSNIOFolder);
    assertTrue(actualFolder instanceof EFSNIOFolder);
    assertEquals(
        ".dbeaver-placeholder", ((EFSNIOFolder) actualFolder).getNioPath().toFile().getName());
    assertEquals(".dbeaver-placeholder", fullPath.toFile().getName());
    assertEquals(".dbeaver-placeholder", actualFolder.getName());
    assertEquals("dbeaver-placeholder", actualFolder.getFileExtension());
    assertEquals("dbeaver-placeholder", fullPath.getFileExtension());
  }

  /**
   * Test {@link EFSNIOFolder#getFolder(String)} with {@code name}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return NioPath toFile Name is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link EFSNIOFolder#getFolder(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IFolder EFSNIOFolder.getFolder(String)"})
  public void testGetFolderWithName_when42_thenReturnNioPathToFileNameIs42() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder efsnioFolder = new EFSNIOFolder(root, backendFolder);

    // Act
    IFolder actualFolder = efsnioFolder.getFolder("42");

    // Assert
    IPath fullPath = actualFolder.getFullPath();
    assertTrue(fullPath instanceof org.eclipse.core.runtime.Path);
    IContainer parent = actualFolder.getParent();
    assertTrue(parent.getParent() instanceof EFSNIOFolder);
    assertTrue(parent instanceof EFSNIOFolder);
    assertTrue(actualFolder instanceof EFSNIOFolder);
    assertEquals("42", ((EFSNIOFolder) actualFolder).getNioPath().toFile().getName());
    assertEquals("42", fullPath.toFile().getName());
    assertEquals("42", actualFolder.getName());
  }

  /**
   * Test {@link EFSNIOFolder#getFolder(String)} with {@code name}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return NioPath toFile Name is {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link EFSNIOFolder#getFolder(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IFolder EFSNIOFolder.getFolder(String)"})
  public void testGetFolderWithName_whenName_thenReturnNioPathToFileNameIsName() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder efsnioFolder = new EFSNIOFolder(root, backendFolder);

    // Act
    IFolder actualFolder = efsnioFolder.getFolder("Name");

    // Assert
    IPath fullPath = actualFolder.getFullPath();
    assertTrue(fullPath instanceof org.eclipse.core.runtime.Path);
    IContainer parent = actualFolder.getParent();
    assertTrue(parent.getParent() instanceof EFSNIOFolder);
    assertTrue(parent instanceof EFSNIOFolder);
    assertTrue(actualFolder instanceof EFSNIOFolder);
    assertEquals("Name", ((EFSNIOFolder) actualFolder).getNioPath().toFile().getName());
    assertEquals("Name", fullPath.toFile().getName());
    assertEquals("Name", actualFolder.getName());
  }

  /**
   * Test {@link EFSNIOFolder#move(IPath, boolean, boolean, IProgressMonitor)} with {@code
   * destination}, {@code force}, {@code keepHistory}, {@code monitor}.
   *
   * <p>Method under test: {@link EFSNIOFolder#move(IPath, boolean, boolean, IProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EFSNIOFolder.move(IPath, boolean, boolean, IProgressMonitor)"})
  public void testMoveWithDestinationForceKeepHistoryMonitor() throws CoreException {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder efsnioFolder = new EFSNIOFolder(root, backendFolder);

    // Act and Assert
    assertThrows(
        FeatureNotSupportedException.class,
        () -> efsnioFolder.move(IPath.EMPTY, true, true, new NullProgressMonitor()));
  }
}
