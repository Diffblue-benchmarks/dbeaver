package org.jkiss.dbeaver.model.navigator;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.eclipse.core.internal.events.ResourceChangeEvent;
import org.eclipse.core.internal.events.ResourceDelta;
import org.eclipse.core.internal.resources.mapping.ProposedResourceDelta;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceDelta;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.fs.DBFVirtualFileSystemRoot;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOFile;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOFileSystemRoot;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NavigatorResourceListenerDiffblueTest {
  /**
   * Test {@link NavigatorResourceListener#resourceChanged(IResourceChangeEvent)}.
   *
   * <ul>
   *   <li>Then calls {@link ResourceDelta#getAffectedChildren()}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResourceListener#resourceChanged(IResourceChangeEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResourceListener.resourceChanged(IResourceChangeEvent)"})
  public void testResourceChanged_thenCallsGetAffectedChildren() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DesktopNavigatorModel model = new DesktopNavigatorModel(platform, new ArrayList<>());
    NavigatorResourceListener navigatorResourceListener = new NavigatorResourceListener(model);
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new EFSNIOFile(root, backendFile);

    ResourceDelta delta = mock(ResourceDelta.class);
    EFSNIOFileSystemRoot root2 =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root2, backendFile2);
    when(delta.getAffectedChildren())
        .thenReturn(new IResourceDelta[] {new ProposedResourceDelta(resource)});

    // Act
    navigatorResourceListener.resourceChanged(
        new ResourceChangeEvent(DBNEvent.FORCE_REFRESH, 1, 1, delta));

    // Assert
    verify(delta).getAffectedChildren();
  }

  /**
   * Test {@link NavigatorResourceListener#handleResourceChange(DBNNode, IResourceDelta)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then calls {@link DBNDataSource#needsInitialization()}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResourceListener#handleResourceChange(DBNNode,
   * IResourceDelta)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NavigatorResourceListener.handleResourceChange(DBNNode, IResourceDelta)"
  })
  public void testHandleResourceChange_givenFalse_thenCallsNeedsInitialization() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DesktopNavigatorModel model = new DesktopNavigatorModel(platform, new ArrayList<>());
    NavigatorResourceListener navigatorResourceListener = new NavigatorResourceListener(model);

    DBNDataSource node = mock(DBNDataSource.class);
    when(node.needsInitialization()).thenReturn(false);
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    navigatorResourceListener.handleResourceChange(node, new ProposedResourceDelta(resource));

    // Assert
    verify(node).needsInitialization();
  }

  /**
   * Test {@link NavigatorResourceListener#handleResourceChange(DBNNode, IResourceDelta)}.
   *
   * <ul>
   *   <li>Given four.
   *   <li>When {@link IResourceDelta} {@link IResourceDelta#getKind()} return four.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResourceListener#handleResourceChange(DBNNode,
   * IResourceDelta)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NavigatorResourceListener.handleResourceChange(DBNNode, IResourceDelta)"
  })
  public void testHandleResourceChange_givenFour_whenIResourceDeltaGetKindReturnFour() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DesktopNavigatorModel model = new DesktopNavigatorModel(platform, new ArrayList<>());
    NavigatorResourceListener navigatorResourceListener = new NavigatorResourceListener(model);

    DBNDataSource node = mock(DBNDataSource.class);
    when(node.needsInitialization()).thenReturn(false);
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new EFSNIOFile(root, backendFile);

    IResourceDelta delta = mock(IResourceDelta.class);
    when(delta.getKind()).thenReturn(4);
    EFSNIOFileSystemRoot root2 =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root2, backendFile2);
    when(delta.getAffectedChildren(anyInt(), anyInt()))
        .thenReturn(new IResourceDelta[] {new ProposedResourceDelta(resource)});

    // Act
    navigatorResourceListener.handleResourceChange(node, delta);

    // Assert
    verify(delta).getAffectedChildren(31, 8);
    verify(delta).getKind();
    verify(node).needsInitialization();
  }

  /**
   * Test {@link NavigatorResourceListener#handleResourceChange(DBNNode, IResourceDelta)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link IResourceDelta} {@link IResourceDelta#getKind()} return one.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResourceListener#handleResourceChange(DBNNode,
   * IResourceDelta)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NavigatorResourceListener.handleResourceChange(DBNNode, IResourceDelta)"
  })
  public void testHandleResourceChange_givenOne_whenIResourceDeltaGetKindReturnOne() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DesktopNavigatorModel model = new DesktopNavigatorModel(platform, new ArrayList<>());
    NavigatorResourceListener navigatorResourceListener = new NavigatorResourceListener(model);

    DBNDataSource node = mock(DBNDataSource.class);
    when(node.needsInitialization()).thenReturn(false);
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    new EFSNIOFile(root, backendFile);

    IResourceDelta delta = mock(IResourceDelta.class);
    when(delta.getKind()).thenReturn(1);
    EFSNIOFileSystemRoot root2 =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile2 = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root2, backendFile2);
    when(delta.getAffectedChildren(anyInt(), anyInt()))
        .thenReturn(new IResourceDelta[] {new ProposedResourceDelta(resource)});

    // Act
    navigatorResourceListener.handleResourceChange(node, delta);

    // Assert
    verify(delta).getAffectedChildren(31, 8);
    verify(delta).getKind();
    verify(node).needsInitialization();
  }

  /**
   * Test {@link NavigatorResourceListener#handleChildResourceChange(DBNNode, IResourceDelta)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then calls {@link DBNProject#getProject()}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResourceListener#handleChildResourceChange(DBNNode,
   * IResourceDelta)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NavigatorResourceListener.handleChildResourceChange(DBNNode, IResourceDelta)"
  })
  public void testHandleChildResourceChange_givenNull_thenCallsGetProject() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DesktopNavigatorModel model = new DesktopNavigatorModel(platform, new ArrayList<>());
    NavigatorResourceListener navigatorResourceListener = new NavigatorResourceListener(model);

    DBNProject parentNode = mock(DBNProject.class);
    when(parentNode.getProject()).thenReturn(null);
    when(parentNode.needsInitialization()).thenReturn(true);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    navigatorResourceListener.handleChildResourceChange(
        parentNode, new ProposedResourceDelta(resource));

    // Assert
    verify(parentNode).getProject();
    verify(parentNode).needsInitialization();
  }
}
