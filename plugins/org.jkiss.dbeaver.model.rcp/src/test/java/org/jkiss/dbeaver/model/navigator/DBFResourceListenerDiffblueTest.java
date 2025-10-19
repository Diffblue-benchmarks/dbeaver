package org.jkiss.dbeaver.model.navigator;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.eclipse.core.internal.resources.Project;
import org.jkiss.dbeaver.model.fs.DBFVirtualFileSystem;
import org.jkiss.dbeaver.model.fs.DBFVirtualFileSystemRoot;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOFile;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOFileSystemRoot;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOFolder;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOListener;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOListener.Action;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOResource;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.jkiss.dbeaver.model.navigator.fs.DBNFileSystem;
import org.jkiss.dbeaver.model.navigator.fs.DBNFileSystems;
import org.jkiss.dbeaver.model.rcp.DesktopProjectImpl;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBFResourceListenerDiffblueTest {
  /**
   * Test {@link DBFResourceListener#resourceChanged(EFSNIOResource, Action)}.
   *
   * <p>Method under test: {@link DBFResourceListener#resourceChanged(EFSNIOResource, Action)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBFResourceListener.resourceChanged(EFSNIOResource, Action)"})
  public void testResourceChanged() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBNFileSystems fileSystems = mock(DBNFileSystems.class);
    Project project = mock(Project.class);
    DesktopProjectImpl desktopProjectImpl =
        new DesktopProjectImpl(null, project, new SessionContextImpl(null));
    when(fileSystems.getOwnerProject()).thenReturn(desktopProjectImpl);
    DBFResourceListener dbfResourceListener = new DBFResourceListener(fileSystems);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    dbfResourceListener.resourceChanged(resource, Action.CREATE);

    // Assert
    verify(fileSystems).getOwnerProject();
  }

  /**
   * Test {@link DBFResourceListener#resourceChanged(EFSNIOResource, Action)}.
   *
   * <p>Method under test: {@link DBFResourceListener#resourceChanged(EFSNIOResource, Action)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBFResourceListener.resourceChanged(EFSNIOResource, Action)"})
  public void testResourceChanged2() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBNFileSystems fileSystems = mock(DBNFileSystems.class);
    DesktopProjectImpl desktopProjectImpl =
        new DesktopProjectImpl(null, null, new SessionContextImpl(null));
    when(fileSystems.getOwnerProject()).thenReturn(desktopProjectImpl);
    DBFResourceListener dbfResourceListener = new DBFResourceListener(fileSystems);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(
            mock(Project.class), mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    dbfResourceListener.resourceChanged(resource, Action.CREATE);

    // Assert
    verify(fileSystems).getOwnerProject();
  }

  /**
   * Test {@link DBFResourceListener#resourceChanged(EFSNIOResource, Action)}.
   *
   * <ul>
   *   <li>Given {@link DBNFileSystems} {@link DBNFileSystems#getOwnerProject()} return {@code
   *       null}.
   * </ul>
   *
   * <p>Method under test: {@link DBFResourceListener#resourceChanged(EFSNIOResource, Action)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBFResourceListener.resourceChanged(EFSNIOResource, Action)"})
  public void testResourceChanged_givenDBNFileSystemsGetOwnerProjectReturnNull() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBNFileSystems fileSystems = mock(DBNFileSystems.class);
    when(fileSystems.getOwnerProject()).thenReturn(null);
    DBFResourceListener dbfResourceListener = new DBFResourceListener(fileSystems);
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    dbfResourceListener.resourceChanged(resource, Action.CREATE);

    // Assert
    verify(fileSystems).getOwnerProject();
  }

  /**
   * Test {@link DBFResourceListener#resourceChanged(EFSNIOResource, Action)}.
   *
   * <ul>
   *   <li>Then calls {@link DBFVirtualFileSystemRoot#getFileSystem()}.
   * </ul>
   *
   * <p>Method under test: {@link DBFResourceListener#resourceChanged(EFSNIOResource, Action)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBFResourceListener.resourceChanged(EFSNIOResource, Action)"})
  public void testResourceChanged_thenCallsGetFileSystem() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBNFileSystems fileSystems = mock(DBNFileSystems.class);
    DBNFileSystem dbnFileSystem =
        new DBNFileSystem(new DBNFileSystems(null), mock(DBFVirtualFileSystem.class));
    when(fileSystems.getCachedChildren()).thenReturn(new DBNFileSystem[] {dbnFileSystem});
    DesktopProjectImpl desktopProjectImpl =
        new DesktopProjectImpl(null, null, new SessionContextImpl(null));
    when(fileSystems.getOwnerProject()).thenReturn(desktopProjectImpl);
    DBFResourceListener dbfResourceListener = new DBFResourceListener(fileSystems);

    DBFVirtualFileSystemRoot fsRoot = mock(DBFVirtualFileSystemRoot.class);
    when(fsRoot.getFileSystem()).thenReturn(mock(DBFVirtualFileSystem.class));
    EFSNIOFileSystemRoot root = new EFSNIOFileSystemRoot(null, fsRoot, "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    dbfResourceListener.resourceChanged(resource, Action.CREATE);

    // Assert
    verify(fsRoot).getFileSystem();
    verify(fileSystems).getOwnerProject();
    verify(fileSystems, atLeast(1)).getCachedChildren();
  }

  /**
   * Test {@link DBFResourceListener#resourceChanged(EFSNIOResource, Action)}.
   *
   * <ul>
   *   <li>Then calls {@link DBNFileSystem#getFileSystem()}.
   * </ul>
   *
   * <p>Method under test: {@link DBFResourceListener#resourceChanged(EFSNIOResource, Action)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBFResourceListener.resourceChanged(EFSNIOResource, Action)"})
  public void testResourceChanged_thenCallsGetFileSystem2() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBNFileSystem dbnFileSystem = mock(DBNFileSystem.class);
    when(dbnFileSystem.getFileSystem()).thenReturn(mock(DBFVirtualFileSystem.class));

    DBNFileSystems fileSystems = mock(DBNFileSystems.class);
    when(fileSystems.getCachedChildren()).thenReturn(new DBNFileSystem[] {dbnFileSystem});
    DesktopProjectImpl desktopProjectImpl =
        new DesktopProjectImpl(null, null, new SessionContextImpl(null));
    when(fileSystems.getOwnerProject()).thenReturn(desktopProjectImpl);
    DBFResourceListener dbfResourceListener = new DBFResourceListener(fileSystems);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBFVirtualFileSystemRoot fsRoot = mock(DBFVirtualFileSystemRoot.class);
    when(fsRoot.getFileSystem()).thenReturn(mock(DBFVirtualFileSystem.class));
    EFSNIOFileSystemRoot root = new EFSNIOFileSystemRoot(null, fsRoot, "Fs Prefix");
    Path backendFolder = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFolder resource = new EFSNIOFolder(root, backendFolder);

    // Act
    dbfResourceListener.resourceChanged(resource, Action.CREATE);

    // Assert
    verify(fsRoot).getFileSystem();
    verify(fileSystems).getOwnerProject();
    verify(dbnFileSystem).getFileSystem();
    verify(fileSystems, atLeast(1)).getCachedChildren();
  }

  /**
   * Test {@link DBFResourceListener#resourceChanged(EFSNIOResource, Action)}.
   *
   * <ul>
   *   <li>Then calls {@link DBNProject#getProject()}.
   * </ul>
   *
   * <p>Method under test: {@link DBFResourceListener#resourceChanged(EFSNIOResource, Action)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBFResourceListener.resourceChanged(EFSNIOResource, Action)"})
  public void testResourceChanged_thenCallsGetProject() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBNProject parentNode = mock(DBNProject.class);
    DesktopProjectImpl desktopProjectImpl =
        new DesktopProjectImpl(null, null, new SessionContextImpl(null));
    when(parentNode.getProject()).thenReturn(desktopProjectImpl);
    DBNFileSystems fileSystems = new DBNFileSystems(parentNode);
    DBFResourceListener dbfResourceListener = new DBFResourceListener(fileSystems);
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    dbfResourceListener.resourceChanged(resource, Action.CREATE);

    // Assert
    verify(parentNode).getProject();
  }
}
