package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import org.eclipse.core.internal.expressions.ExpressionStatus;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.internal.resources.Folder;
import org.eclipse.core.internal.resources.ResourceStatus;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.fs.DBFVirtualFileSystemRoot;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOFile;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOFileSystemRoot;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOFolder;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOPath;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.DefaultProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ResourceUtilsDiffblueTest {
  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)} with {@code folder},
   * {@code monitor}.
   *
   * <ul>
   *   <li>Given {@link IPath#EMPTY}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder, DBRProgressMonitor)"})
  public void testCheckFolderExistsWithFolderMonitor_givenEmpty()
      throws CoreException, DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Folder folder = mock(Folder.class);
    when(folder.getFullPath()).thenReturn(IPath.EMPTY);
    when(folder.exists()).thenReturn(false);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(folder)
        .create(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> ResourceUtils.checkFolderExists(folder, new LoggingProgressMonitor()));
    verify(folder).create(eq(true), eq(true), isA(IProgressMonitor.class));
    verify(folder).exists();
    verify(folder).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)} with {@code folder},
   * {@code monitor}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link Folder} {@link Folder#getFullPath()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder, DBRProgressMonitor)"})
  public void testCheckFolderExistsWithFolderMonitor_givenNull_whenFolderGetFullPathReturnNull()
      throws CoreException, DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Folder folder = mock(Folder.class);
    when(folder.getFullPath()).thenReturn(null);
    when(folder.exists()).thenReturn(false);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(folder)
        .create(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> ResourceUtils.checkFolderExists(folder, new LoggingProgressMonitor()));
    verify(folder).create(eq(true), eq(true), isA(IProgressMonitor.class));
    verify(folder).exists();
    verify(folder).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)} with {@code folder},
   * {@code monitor}.
   *
   * <ul>
   *   <li>Given {@link Path#Path(String, String)} with {@code Device} and {@code Path}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder, DBRProgressMonitor)"})
  public void testCheckFolderExistsWithFolderMonitor_givenPathWithDeviceAndPath()
      throws CoreException, DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Folder folder = mock(Folder.class);
    when(folder.getFullPath()).thenReturn(new Path("Device", "Path"));
    when(folder.exists()).thenReturn(false);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(folder)
        .create(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> ResourceUtils.checkFolderExists(folder, new LoggingProgressMonitor()));
    verify(folder).create(eq(true), eq(true), isA(IProgressMonitor.class));
    verify(folder).exists();
    verify(folder).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)} with {@code folder},
   * {@code monitor}.
   *
   * <ul>
   *   <li>Given {@link Path#Path(String)} with {@code Full Path}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder, DBRProgressMonitor)"})
  public void testCheckFolderExistsWithFolderMonitor_givenPathWithFullPath()
      throws CoreException, DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Folder folder = mock(Folder.class);
    when(folder.getFullPath()).thenReturn(new Path("Full Path"));
    when(folder.exists()).thenReturn(false);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(folder)
        .create(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> ResourceUtils.checkFolderExists(folder, new LoggingProgressMonitor()));
    verify(folder).create(eq(true), eq(true), isA(IProgressMonitor.class));
    verify(folder).exists();
    verify(folder).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)} with {@code folder},
   * {@code monitor}.
   *
   * <ul>
   *   <li>Given {@link IPath#ROOT}.
   *   <li>When {@link Folder} {@link Folder#getFullPath()} return {@link IPath#ROOT}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder, DBRProgressMonitor)"})
  public void testCheckFolderExistsWithFolderMonitor_givenRoot_whenFolderGetFullPathReturnRoot()
      throws CoreException, DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Folder folder = mock(Folder.class);
    when(folder.getFullPath()).thenReturn(IPath.ROOT);
    when(folder.exists()).thenReturn(false);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(folder)
        .create(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> ResourceUtils.checkFolderExists(folder, new LoggingProgressMonitor()));
    verify(folder).create(eq(true), eq(true), isA(IProgressMonitor.class));
    verify(folder).exists();
    verify(folder).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)} with {@code folder},
   * {@code monitor}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder, DBRProgressMonitor)"})
  public void testCheckFolderExistsWithFolderMonitor_thenDoesNotThrow() throws DBException {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    java.nio.file.Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder folder = new EFSNIOFolder(root, backendFolder);

    // Act and Assert
    ResourceUtils.checkFolderExists(folder, new LoggingProgressMonitor());
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)} with {@code folder},
   * {@code monitor}.
   *
   * <ul>
   *   <li>When {@link Folder} {@link Folder#create(boolean, boolean, IProgressMonitor)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder, DBRProgressMonitor)"})
  public void testCheckFolderExistsWithFolderMonitor_whenFolderCreateDoesNothing()
      throws CoreException, DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Folder folder = mock(Folder.class);
    when(folder.exists()).thenReturn(false);
    doNothing().when(folder).create(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.checkFolderExists(folder, new LoggingProgressMonitor());

    // Assert
    verify(folder).create(eq(true), eq(true), isA(IProgressMonitor.class));
    verify(folder).exists();
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder)} with {@code folder}.
   *
   * <ul>
   *   <li>Given {@link IPath#EMPTY}.
   *   <li>When {@link Folder} {@link Folder#getFullPath()} return {@link IPath#EMPTY}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder)"})
  public void testCheckFolderExistsWithFolder_givenEmpty_whenFolderGetFullPathReturnEmpty()
      throws CoreException, DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Folder folder = mock(Folder.class);
    when(folder.getFullPath()).thenReturn(IPath.EMPTY);
    when(folder.exists()).thenReturn(false);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(folder)
        .create(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act and Assert
    assertThrows(DBException.class, () -> ResourceUtils.checkFolderExists(folder));
    verify(folder).create(eq(true), eq(true), isA(IProgressMonitor.class));
    verify(folder).exists();
    verify(folder).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder)} with {@code folder}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link Folder} {@link Folder#getFullPath()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder)"})
  public void testCheckFolderExistsWithFolder_givenNull_whenFolderGetFullPathReturnNull()
      throws CoreException, DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Folder folder = mock(Folder.class);
    when(folder.getFullPath()).thenReturn(null);
    when(folder.exists()).thenReturn(false);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(folder)
        .create(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act and Assert
    assertThrows(DBException.class, () -> ResourceUtils.checkFolderExists(folder));
    verify(folder).create(eq(true), eq(true), isA(IProgressMonitor.class));
    verify(folder).exists();
    verify(folder).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder)} with {@code folder}.
   *
   * <ul>
   *   <li>Given {@link Path#Path(String, String)} with {@code Device} and {@code Path}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder)"})
  public void testCheckFolderExistsWithFolder_givenPathWithDeviceAndPath()
      throws CoreException, DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Folder folder = mock(Folder.class);
    when(folder.getFullPath()).thenReturn(new Path("Device", "Path"));
    when(folder.exists()).thenReturn(false);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(folder)
        .create(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act and Assert
    assertThrows(DBException.class, () -> ResourceUtils.checkFolderExists(folder));
    verify(folder).create(eq(true), eq(true), isA(IProgressMonitor.class));
    verify(folder).exists();
    verify(folder).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder)} with {@code folder}.
   *
   * <ul>
   *   <li>Given {@link Path#Path(String)} with {@code Full Path}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder)"})
  public void testCheckFolderExistsWithFolder_givenPathWithFullPath()
      throws CoreException, DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Folder folder = mock(Folder.class);
    when(folder.getFullPath()).thenReturn(new Path("Full Path"));
    when(folder.exists()).thenReturn(false);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(folder)
        .create(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act and Assert
    assertThrows(DBException.class, () -> ResourceUtils.checkFolderExists(folder));
    verify(folder).create(eq(true), eq(true), isA(IProgressMonitor.class));
    verify(folder).exists();
    verify(folder).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder)} with {@code folder}.
   *
   * <ul>
   *   <li>Given {@link IPath#ROOT}.
   *   <li>When {@link Folder} {@link Folder#getFullPath()} return {@link IPath#ROOT}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder)"})
  public void testCheckFolderExistsWithFolder_givenRoot_whenFolderGetFullPathReturnRoot()
      throws CoreException, DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Folder folder = mock(Folder.class);
    when(folder.getFullPath()).thenReturn(IPath.ROOT);
    when(folder.exists()).thenReturn(false);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(folder)
        .create(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act and Assert
    assertThrows(DBException.class, () -> ResourceUtils.checkFolderExists(folder));
    verify(folder).create(eq(true), eq(true), isA(IProgressMonitor.class));
    verify(folder).exists();
    verify(folder).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder)} with {@code folder}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder)"})
  public void testCheckFolderExistsWithFolder_thenDoesNotThrow() throws DBException {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    java.nio.file.Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder folder = new EFSNIOFolder(root, backendFolder);

    // Act and Assert
    ResourceUtils.checkFolderExists(folder);
  }

  /**
   * Test {@link ResourceUtils#checkFolderExists(IFolder)} with {@code folder}.
   *
   * <ul>
   *   <li>When {@link Folder} {@link Folder#create(boolean, boolean, IProgressMonitor)} does
   *       nothing.
   *   <li>Then calls {@link Folder#create(boolean, boolean, IProgressMonitor)}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#checkFolderExists(IFolder)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.checkFolderExists(IFolder)"})
  public void testCheckFolderExistsWithFolder_whenFolderCreateDoesNothing_thenCallsCreate()
      throws CoreException, DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    Folder folder = mock(Folder.class);
    when(folder.exists()).thenReturn(false);
    doNothing().when(folder).create(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.checkFolderExists(folder);

    // Assert
    verify(folder).create(eq(true), eq(true), isA(IProgressMonitor.class));
    verify(folder).exists();
  }

  /**
   * Test {@link ResourceUtils#syncFile(DBRProgressMonitor, IResource)}.
   *
   * <p>Method under test: {@link ResourceUtils#syncFile(DBRProgressMonitor, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.syncFile(DBRProgressMonitor, IResource)"})
  public void testSyncFile() throws CoreException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File localFile = mock(File.class);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(localFile)
        .refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.syncFile(monitor, localFile);

    // Assert
    verify(localFile).refreshLocal(eq(0), isA(IProgressMonitor.class));
  }

  /**
   * Test {@link ResourceUtils#syncFile(DBRProgressMonitor, IResource)}.
   *
   * <p>Method under test: {@link ResourceUtils#syncFile(DBRProgressMonitor, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.syncFile(DBRProgressMonitor, IResource)"})
  public void testSyncFile2() throws CoreException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File localFile = mock(File.class);
    ExpressionStatus status =
        new ExpressionStatus(-1, "Not all who wander are lost", new Throwable());
    doThrow(new CoreException(status))
        .when(localFile)
        .refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.syncFile(monitor, localFile);

    // Assert
    verify(localFile).refreshLocal(eq(0), isA(IProgressMonitor.class));
  }

  /**
   * Test {@link ResourceUtils#syncFile(DBRProgressMonitor, IResource)}.
   *
   * <p>Method under test: {@link ResourceUtils#syncFile(DBRProgressMonitor, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.syncFile(DBRProgressMonitor, IResource)"})
  public void testSyncFile3() throws CoreException {
    // Arrange
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor monitor = new LoggingProgressMonitor(Log.getLog(forClass));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File localFile = mock(File.class);
    Class<Object> caller = Object.class;
    doThrow(new CoreException(new MultiStatus(caller, 1, "Not all who wander are lost")))
        .when(localFile)
        .refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.syncFile(monitor, localFile);

    // Assert
    verify(localFile).refreshLocal(eq(0), isA(IProgressMonitor.class));
  }

  /**
   * Test {@link ResourceUtils#syncFile(DBRProgressMonitor, IResource)}.
   *
   * <p>Method under test: {@link ResourceUtils#syncFile(DBRProgressMonitor, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.syncFile(DBRProgressMonitor, IResource)"})
  public void testSyncFile4() throws CoreException {
    // Arrange
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor monitor = new LoggingProgressMonitor(Log.getLog(forClass));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Class<Object> caller = Object.class;

    MultiStatus status = new MultiStatus(caller, 1, "Not all who wander are lost");
    status.add(new ExpressionStatus(-1, "Not all who wander are lost"));

    File localFile = mock(File.class);
    doThrow(new CoreException(status))
        .when(localFile)
        .refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.syncFile(monitor, localFile);

    // Assert
    verify(localFile).refreshLocal(eq(0), isA(IProgressMonitor.class));
  }

  /**
   * Test {@link ResourceUtils#syncFile(DBRProgressMonitor, IResource)}.
   *
   * <p>Method under test: {@link ResourceUtils#syncFile(DBRProgressMonitor, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.syncFile(DBRProgressMonitor, IResource)"})
  public void testSyncFile5() throws CoreException {
    // Arrange
    Class<Object> forClass = Object.class;
    LoggingProgressMonitor monitor = new LoggingProgressMonitor(Log.getLog(forClass));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Class<Object> caller = Object.class;

    MultiStatus status = new MultiStatus(caller, 1, "Not all who wander are lost");
    status.add(new ExpressionStatus(-1, "Not all who wander are lost"));
    status.add(new ExpressionStatus(-1, "Not all who wander are lost"));

    File localFile = mock(File.class);
    doThrow(new CoreException(status))
        .when(localFile)
        .refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.syncFile(monitor, localFile);

    // Assert
    verify(localFile).refreshLocal(eq(0), isA(IProgressMonitor.class));
  }

  /**
   * Test {@link ResourceUtils#syncFile(DBRProgressMonitor, IResource)}.
   *
   * <ul>
   *   <li>Given {@link ResourceStatus#ResourceStatus(int, String)} with code is two and message is
   *       {@code Not all who wander are lost}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#syncFile(DBRProgressMonitor, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.syncFile(DBRProgressMonitor, IResource)"})
  public void testSyncFile_givenResourceStatusWithCodeIsTwoAndMessageIsNotAllWhoWanderAreLost()
      throws CoreException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File localFile = mock(File.class);
    doThrow(new CoreException(new ResourceStatus(2, "Not all who wander are lost")))
        .when(localFile)
        .refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.syncFile(monitor, localFile);

    // Assert
    verify(localFile).refreshLocal(eq(0), isA(IProgressMonitor.class));
  }

  /**
   * Test {@link ResourceUtils#getUniqueFile(IFolder, String, String)}.
   *
   * <ul>
   *   <li>Then FullPath return {@link Path}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#getUniqueFile(IFolder, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"IFile ResourceUtils.getUniqueFile(IFolder, String, String)"})
  public void testGetUniqueFile_thenFullPathReturnPath() throws CoreException {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    java.nio.file.Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder folder = new EFSNIOFolder(root, backendFolder);

    // Act
    IFile actualUniqueFile = ResourceUtils.getUniqueFile(folder, "foo.txt", "File Ext");

    // Assert
    assertTrue(actualUniqueFile.getFullPath() instanceof Path);
    assertTrue(actualUniqueFile instanceof EFSNIOFile);
    IContainer parent = actualUniqueFile.getParent();
    assertTrue(parent instanceof EFSNIOFolder);
    assertEquals("File Ext", actualUniqueFile.getFileExtension());
    assertEquals("foo.txt.File Ext", actualUniqueFile.getName());
    assertNull(actualUniqueFile.getProject());
    assertNull(actualUniqueFile.getResourceAttributes());
    assertNull(actualUniqueFile.getContentDescription());
    assertEquals(0L, actualUniqueFile.getLocalTimeStamp());
    assertEquals(0L, actualUniqueFile.getModificationStamp());
    assertEquals(1, actualUniqueFile.getSessionProperties().size());
    assertEquals(1, actualUniqueFile.getType());
    assertFalse(actualUniqueFile.isDerived());
    assertFalse(actualUniqueFile.isHidden());
    assertFalse(actualUniqueFile.isLinked());
    assertFalse(actualUniqueFile.isPhantom());
    assertFalse(actualUniqueFile.isTeamPrivateMember());
    assertFalse(actualUniqueFile.isVirtual());
    assertTrue(actualUniqueFile.getPersistentProperties().isEmpty());
    assertTrue(actualUniqueFile.isReadOnly());
    assertTrue(actualUniqueFile.isAccessible());
    assertEquals(folder, parent);
    assertEquals(GeneralUtils.DEFAULT_ENCODING, actualUniqueFile.getCharset());
    assertSame(root, ((EFSNIOFile) actualUniqueFile).getRoot());
  }

  /**
   * Test {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}.
   *
   * <p>Method under test: {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.deleteTempFile(DBRProgressMonitor, IFile)"})
  public void testDeleteTempFile() throws CoreException {
    // Arrange
    DefaultProgressMonitor monitor = new DefaultProgressMonitor(new NullProgressMonitor());
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File file = mock(File.class);
    java.nio.file.Path nioPath = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    when(file.getFullPath()).thenReturn(new EFSNIOPath(nioPath));
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(file)
        .delete(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.deleteTempFile(monitor, file);

    // Assert
    verify(file).delete(eq(true), eq(false), isA(IProgressMonitor.class));
    verify(file).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}.
   *
   * <p>Method under test: {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.deleteTempFile(DBRProgressMonitor, IFile)"})
  public void testDeleteTempFile2() throws CoreException {
    // Arrange
    DefaultProgressMonitor monitor = new DefaultProgressMonitor(new NullProgressMonitor());
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File file = mock(File.class);
    when(file.getFullPath())
        .thenReturn(new Path("Can't delete temporary file ''", "Can't delete temporary file ''"));
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(file)
        .delete(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.deleteTempFile(monitor, file);

    // Assert
    verify(file).delete(eq(true), eq(false), isA(IProgressMonitor.class));
    verify(file).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}.
   *
   * <p>Method under test: {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.deleteTempFile(DBRProgressMonitor, IFile)"})
  public void testDeleteTempFile3() throws CoreException {
    // Arrange
    DefaultProgressMonitor monitor = new DefaultProgressMonitor(new NullProgressMonitor());
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File file = mock(File.class);
    when(file.getFullPath()).thenReturn(IPath.EMPTY);
    doThrow(new CoreException(new ResourceStatus(1, "Not all who wander are lost")))
        .when(file)
        .delete(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.deleteTempFile(monitor, file);

    // Assert
    verify(file).delete(eq(true), eq(false), isA(IProgressMonitor.class));
    verify(file).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}.
   *
   * <p>Method under test: {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.deleteTempFile(DBRProgressMonitor, IFile)"})
  public void testDeleteTempFile4() throws CoreException {
    // Arrange
    DefaultProgressMonitor monitor = new DefaultProgressMonitor(new NullProgressMonitor());
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File file = mock(File.class);
    when(file.getFullPath()).thenReturn(IPath.EMPTY);
    doThrow(new CoreException(new ExpressionStatus(-1, "Can't delete temporary file ''")))
        .when(file)
        .delete(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.deleteTempFile(monitor, file);

    // Assert
    verify(file).delete(eq(true), eq(false), isA(IProgressMonitor.class));
    verify(file).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}.
   *
   * <ul>
   *   <li>Given {@link IPath#EMPTY}.
   *   <li>When {@link File} {@link File#getFullPath()} return {@link IPath#EMPTY}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.deleteTempFile(DBRProgressMonitor, IFile)"})
  public void testDeleteTempFile_givenEmpty_whenFileGetFullPathReturnEmpty() throws CoreException {
    // Arrange
    DefaultProgressMonitor monitor = new DefaultProgressMonitor(new NullProgressMonitor());
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File file = mock(File.class);
    when(file.getFullPath()).thenReturn(IPath.EMPTY);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(file)
        .delete(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.deleteTempFile(monitor, file);

    // Assert
    verify(file).delete(eq(true), eq(false), isA(IProgressMonitor.class));
    verify(file).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}.
   *
   * <ul>
   *   <li>Given {@link Path#Path(String)} with fullPath is {@code Can't delete temporary file ''}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.deleteTempFile(DBRProgressMonitor, IFile)"})
  public void testDeleteTempFile_givenPathWithFullPathIsCanTDeleteTemporaryFile()
      throws CoreException {
    // Arrange
    DefaultProgressMonitor monitor = new DefaultProgressMonitor(new NullProgressMonitor());
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File file = mock(File.class);
    when(file.getFullPath()).thenReturn(new Path("Can't delete temporary file ''"));
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(file)
        .delete(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.deleteTempFile(monitor, file);

    // Assert
    verify(file).delete(eq(true), eq(false), isA(IProgressMonitor.class));
    verify(file).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}.
   *
   * <ul>
   *   <li>Given {@link IPath#ROOT}.
   *   <li>When {@link File} {@link File#getFullPath()} return {@link IPath#ROOT}.
   *   <li>Then calls {@link File#getFullPath()}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.deleteTempFile(DBRProgressMonitor, IFile)"})
  public void testDeleteTempFile_givenRoot_whenFileGetFullPathReturnRoot_thenCallsGetFullPath()
      throws CoreException {
    // Arrange
    DefaultProgressMonitor monitor = new DefaultProgressMonitor(new NullProgressMonitor());
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File file = mock(File.class);
    when(file.getFullPath()).thenReturn(IPath.ROOT);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(file)
        .delete(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.deleteTempFile(monitor, file);

    // Assert
    verify(file).delete(eq(true), eq(false), isA(IProgressMonitor.class));
    verify(file).getFullPath();
  }

  /**
   * Test {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}.
   *
   * <ul>
   *   <li>When {@link File} {@link File#delete(boolean, boolean, IProgressMonitor)} does nothing.
   *   <li>Then calls {@link File#delete(boolean, boolean, IProgressMonitor)}.
   * </ul>
   *
   * <p>Method under test: {@link ResourceUtils#deleteTempFile(DBRProgressMonitor, IFile)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ResourceUtils.deleteTempFile(DBRProgressMonitor, IFile)"})
  public void testDeleteTempFile_whenFileDeleteDoesNothing_thenCallsDelete() throws CoreException {
    // Arrange
    DefaultProgressMonitor monitor = new DefaultProgressMonitor(new NullProgressMonitor());
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File file = mock(File.class);
    doNothing().when(file).delete(anyBoolean(), anyBoolean(), Mockito.<IProgressMonitor>any());

    // Act
    ResourceUtils.deleteTempFile(monitor, file);

    // Assert
    verify(file).delete(eq(true), eq(false), isA(IProgressMonitor.class));
  }
}
