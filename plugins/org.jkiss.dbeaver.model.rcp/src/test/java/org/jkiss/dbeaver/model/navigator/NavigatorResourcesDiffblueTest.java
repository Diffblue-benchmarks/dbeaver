package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.eclipse.core.internal.expressions.ExpressionStatus;
import org.eclipse.core.internal.filesystem.NullFileStore;
import org.eclipse.core.internal.resources.File;
import org.eclipse.core.internal.resources.VirtualFileStore;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.auth.SMSessionContext;
import org.jkiss.dbeaver.model.fs.DBFRemoteFileStore;
import org.jkiss.dbeaver.model.fs.DBFVirtualFileSystemRoot;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOFile;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOFileStore;
import org.jkiss.dbeaver.model.fs.nio.EFSNIOFileSystemRoot;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.jkiss.dbeaver.model.navigator.fs.DBNFileSystems;
import org.jkiss.dbeaver.model.rcp.DesktopProjectImpl;
import org.jkiss.dbeaver.model.rcp.DesktopWorkspaceImpl;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class NavigatorResourcesDiffblueTest {
  /**
   * Test {@link NavigatorResources#getProjectNode(DBNRoot, IProject)}.
   *
   * <ul>
   *   <li>Given {@link DBNProject} {@link DBNProject#getProject()} return {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#getProjectNode(DBNRoot, IProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNProject NavigatorResources.getProjectNode(DBNRoot, IProject)"})
  public void testGetProjectNode_givenDBNProjectGetProjectReturnNull_thenReturnNull() {
    // Arrange
    DBNProject dbnProject = mock(DBNProject.class);
    when(dbnProject.getProject()).thenReturn(null);

    DBNRoot root = mock(DBNRoot.class);
    when(root.getProjects()).thenReturn(new DBNProject[] {dbnProject});
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    DBNProject actualProjectNode = NavigatorResources.getProjectNode(root, null);

    // Assert
    verify(dbnProject).getProject();
    verify(root).getProjects();
    assertNull(actualProjectNode);
  }

  /**
   * Test {@link NavigatorResources#getProjectNode(DBNRoot, IProject)}.
   *
   * <ul>
   *   <li>Given {@link SessionContextImpl#SessionContextImpl(SMSessionContext)} with parentContext
   *       is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#getProjectNode(DBNRoot, IProject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNProject NavigatorResources.getProjectNode(DBNRoot, IProject)"})
  public void testGetProjectNode_givenSessionContextImplWithParentContextIsNull() {
    // Arrange
    DBNProject dbnProject = mock(DBNProject.class);
    DesktopProjectImpl desktopProjectImpl =
        new DesktopProjectImpl(null, null, new SessionContextImpl(null));
    when(dbnProject.getProject()).thenReturn(desktopProjectImpl);

    DBNRoot root = mock(DBNRoot.class);
    when(root.getProjects()).thenReturn(new DBNProject[] {dbnProject});
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NavigatorResources.getProjectNode(root, null);

    // Assert
    verify(dbnProject).getProject();
    verify(root).getProjects();
  }

  /**
   * Test {@link NavigatorResources#getNodeByResource(DBNModel, IResource)} with {@code model},
   * {@code resource}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#getNodeByResource(DBNModel, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNResource NavigatorResources.getNodeByResource(DBNModel, IResource)"})
  public void testGetNodeByResourceWithModelResource_thenReturnNull() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel model = new DBNModel(platform, new ArrayList<>());
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    DBNResource actualNodeByResource = NavigatorResources.getNodeByResource(model, resource);

    // Assert
    assertNull(actualNodeByResource);
  }

  /**
   * Test {@link NavigatorResources#getNodeByResource(DBNRoot, IResource)} with {@code root}, {@code
   * resource}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#getNodeByResource(DBNRoot, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNResource NavigatorResources.getNodeByResource(DBNRoot, IResource)"})
  public void testGetNodeByResourceWithRootResource_whenNull_thenReturnNull() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    DBNResource actualNodeByResource =
        NavigatorResources.getNodeByResource((DBNRoot) null, resource);

    // Assert
    assertNull(actualNodeByResource);
  }

  /**
   * Test {@link NavigatorResources#refreshNavigatorResource(DBPProject, IResource, Object)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPPlatform#getNavigatorModel()}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#refreshNavigatorResource(DBPProject, IResource,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NavigatorResources.refreshNavigatorResource(DBPProject, IResource, Object)"
  })
  public void testRefreshNavigatorResource_thenCallsGetNavigatorModel() {
    // Arrange
    DBPPlatform dbpPlatform = mock(DBPPlatform.class);
    when(dbpPlatform.getNavigatorModel()).thenReturn(null);

    DesktopWorkspaceImpl workspace = mock(DesktopWorkspaceImpl.class);
    when(workspace.getPlatform()).thenReturn(dbpPlatform);
    DesktopProjectImpl project =
        new DesktopProjectImpl(workspace, null, new SessionContextImpl(null));
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    NavigatorResources.refreshNavigatorResource(project, resource, DBNEvent.FORCE_REFRESH);

    // Assert
    verify(dbpPlatform).getNavigatorModel();
    verify(workspace).getPlatform();
  }

  /**
   * Test {@link NavigatorResources#findResource(DBRProgressMonitor, DBNNode, IResource)} with
   * {@code monitor}, {@code node}, {@code resource}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#findResource(DBRProgressMonitor, DBNNode,
   * IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBNResource NavigatorResources.findResource(DBRProgressMonitor, DBNNode, IResource)"
  })
  public void testFindResourceWithMonitorNodeResource_whenNull_thenReturnNull() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    DBNResource actualFindResourceResult = NavigatorResources.findResource(monitor, null, resource);

    // Assert
    assertNull(actualFindResourceResult);
  }

  /**
   * Test {@link NavigatorResources#findResource(DBNNode, IResource)} with {@code node}, {@code
   * resource}.
   *
   * <ul>
   *   <li>When {@link DBNFileSystems#DBNFileSystems(DBNProject)} with parentNode is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#findResource(DBNNode, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNResource NavigatorResources.findResource(DBNNode, IResource)"})
  public void testFindResourceWithNodeResource_whenDBNFileSystemsWithParentNodeIsNull() {
    // Arrange
    DBNFileSystems node = new DBNFileSystems(null);
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    DBNResource actualFindResourceResult = NavigatorResources.findResource(node, resource);

    // Assert
    assertNull(actualFindResourceResult);
  }

  /**
   * Test {@link NavigatorResources#findResource(DBNNode, IResource)} with {@code node}, {@code
   * resource}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#findResource(DBNNode, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNResource NavigatorResources.findResource(DBNNode, IResource)"})
  public void testFindResourceWithNodeResource_whenNull_thenReturnNull() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    DBNResource actualFindResourceResult = NavigatorResources.findResource(null, resource);

    // Assert
    assertNull(actualFindResourceResult);
  }

  /**
   * Test {@link NavigatorResources#isRootResource(DBPProject, IResource)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#isRootResource(DBPProject, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NavigatorResources.isRootResource(DBPProject, IResource)"})
  public void testIsRootResource_thenReturnFalse() {
    // Arrange
    SessionContextImpl sessionContext = new SessionContextImpl(mock(SMSessionContext.class));
    DesktopProjectImpl ownerProject = new DesktopProjectImpl(null, null, sessionContext);
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    boolean actualIsRootResourceResult = NavigatorResources.isRootResource(ownerProject, resource);

    // Assert
    assertFalse(actualIsRootResourceResult);
  }

  /**
   * Test {@link NavigatorResources#isRootResource(DBPProject, IResource)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#isRootResource(DBPProject, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean NavigatorResources.isRootResource(DBPProject, IResource)"})
  public void testIsRootResource_whenNull_thenReturnFalse() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    boolean actualIsRootResourceResult = NavigatorResources.isRootResource(null, resource);

    // Assert
    assertFalse(actualIsRootResourceResult);
  }

  /**
   * Test {@link NavigatorResources#makeNode(DBNNode, IResource)}.
   *
   * <p>Method under test: {@link NavigatorResources#makeNode(DBNNode, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode NavigatorResources.makeNode(DBNNode, IResource)"})
  public void testMakeNode() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBNDataSource parentNode = mock(DBNDataSource.class);
    DesktopProjectImpl desktopProjectImpl =
        new DesktopProjectImpl(null, null, new SessionContextImpl(null));
    when(parentNode.getOwnerProject()).thenReturn(desktopProjectImpl);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    DBNNode actualMakeNodeResult = NavigatorResources.makeNode(parentNode, resource);

    // Assert
    verify(parentNode).getOwnerProject();
    assertNull(actualMakeNodeResult);
  }

  /**
   * Test {@link NavigatorResources#makeNode(DBNNode, IResource)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link DBNDataSource} {@link DBNDataSource#getOwnerProject()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#makeNode(DBNNode, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode NavigatorResources.makeNode(DBNNode, IResource)"})
  public void testMakeNode_givenNull_whenDBNDataSourceGetOwnerProjectReturnNull() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBNDataSource parentNode = mock(DBNDataSource.class);
    when(parentNode.getOwnerProject()).thenReturn(null);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    DBNNode actualMakeNodeResult = NavigatorResources.makeNode(parentNode, resource);

    // Assert
    verify(parentNode).getOwnerProject();
    assertNull(actualMakeNodeResult);
  }

  /**
   * Test {@link NavigatorResources#makeNode(DBNNode, IResource)}.
   *
   * <ul>
   *   <li>Then calls {@link DBNProject#getProject()}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#makeNode(DBNNode, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode NavigatorResources.makeNode(DBNNode, IResource)"})
  public void testMakeNode_thenCallsGetProject() {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    DBNProject parentNode = mock(DBNProject.class);
    DesktopProjectImpl desktopProjectImpl =
        new DesktopProjectImpl(null, null, new SessionContextImpl(null));
    when(parentNode.getProject()).thenReturn(desktopProjectImpl);
    DBNFileSystems parentNode2 = new DBNFileSystems(parentNode);
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    DBNNode actualMakeNodeResult = NavigatorResources.makeNode(parentNode2, resource);

    // Assert
    verify(parentNode).getProject();
    assertNull(actualMakeNodeResult);
  }

  /**
   * Test {@link NavigatorResources#getChild(DBNNode, IResource)}.
   *
   * <ul>
   *   <li>When {@link DBNFileSystems#DBNFileSystems(DBNProject)} with parentNode is {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#getChild(DBNNode, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode NavigatorResources.getChild(DBNNode, IResource)"})
  public void testGetChild_whenDBNFileSystemsWithParentNodeIsNull_thenReturnNull() {
    // Arrange
    DBNFileSystems parentNode = new DBNFileSystems(null);
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    DBNNode actualChild = NavigatorResources.getChild(parentNode, resource);

    // Assert
    assertNull(actualChild);
  }

  /**
   * Test {@link NavigatorResources#getChild(DBNNode, IResource)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#getChild(DBNNode, IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode NavigatorResources.getChild(DBNNode, IResource)"})
  public void testGetChild_whenNull_thenReturnNull() {
    // Arrange
    EFSNIOFileSystemRoot root =
        new EFSNIOFileSystemRoot(null, mock(DBFVirtualFileSystemRoot.class), "Fs Prefix");
    Path backendFile = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    EFSNIOFile resource = new EFSNIOFile(root, backendFile);

    // Act
    DBNNode actualChild = NavigatorResources.getChild(null, resource);

    // Assert
    assertNull(actualChild);
  }

  /**
   * Test {@link NavigatorResources#refreshThisResource(DBRProgressMonitor, DBNNode)}.
   *
   * <p>Method under test: {@link NavigatorResources#refreshThisResource(DBRProgressMonitor,
   * DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshThisResource(DBRProgressMonitor, DBNNode)"})
  public void testRefreshThisResource() throws CoreException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    File file = mock(File.class);
    Path path = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileStore efsnioFileStore =
        new EFSNIOFileStore(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(), path);
    when(file.getStore()).thenReturn(efsnioFileStore);
    doThrow(new CoreException(new ExpressionStatus(-1, "Not all who wander are lost")))
        .when(file)
        .refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    DBNDataSource resNode = mock(DBNDataSource.class);
    when(resNode.getAdapter(IResource.class)).thenReturn(file);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act and Assert
    assertThrows(DBException.class, () -> NavigatorResources.refreshThisResource(monitor, resNode));
    verify(file).refreshLocal(eq(2), isA(IProgressMonitor.class));
    verify(file).getStore();
    verify(resNode).getAdapter(isA(Class.class));
  }

  /**
   * Test {@link NavigatorResources#refreshThisResource(DBRProgressMonitor, DBNNode)}.
   *
   * <p>Method under test: {@link NavigatorResources#refreshThisResource(DBRProgressMonitor,
   * DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshThisResource(DBRProgressMonitor, DBNNode)"})
  public void testRefreshThisResource2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBFRemoteFileStore dbfRemoteFileStore = mock(DBFRemoteFileStore.class);
    doThrow(new DBException("An error occurred"))
        .when(dbfRemoteFileStore)
        .refresh(Mockito.<DBRProgressMonitor>any());

    NullFileStore nullFileStore = mock(NullFileStore.class);
    when(nullFileStore.getAdapter(DBFRemoteFileStore.class)).thenReturn(dbfRemoteFileStore);

    File file = mock(File.class);
    when(file.getStore()).thenReturn(nullFileStore);

    DBNDataSource resNode = mock(DBNDataSource.class);
    when(resNode.getAdapter(IResource.class)).thenReturn(file);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act and Assert
    assertThrows(DBException.class, () -> NavigatorResources.refreshThisResource(monitor, resNode));
    verify(file).getStore();
    verify(nullFileStore).getAdapter(isA(Class.class));
    verify(dbfRemoteFileStore).refresh(isA(DBRProgressMonitor.class));
    verify(resNode).getAdapter(isA(Class.class));
  }

  /**
   * Test {@link NavigatorResources#refreshThisResource(DBRProgressMonitor, DBNNode)}.
   *
   * <ul>
   *   <li>Given {@link DBFRemoteFileStore} {@link DBFRemoteFileStore#refresh(DBRProgressMonitor)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#refreshThisResource(DBRProgressMonitor,
   * DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshThisResource(DBRProgressMonitor, DBNNode)"})
  public void testRefreshThisResource_givenDBFRemoteFileStoreRefreshDoesNothing()
      throws CoreException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBFRemoteFileStore dbfRemoteFileStore = mock(DBFRemoteFileStore.class);
    doNothing().when(dbfRemoteFileStore).refresh(Mockito.<DBRProgressMonitor>any());

    NullFileStore nullFileStore = mock(NullFileStore.class);
    when(nullFileStore.getAdapter(DBFRemoteFileStore.class)).thenReturn(dbfRemoteFileStore);

    IPath iPath = mock(IPath.class);
    when(iPath.toFile())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    File file = mock(File.class);
    when(file.getStore()).thenReturn(nullFileStore);
    when(file.getLocation()).thenReturn(iPath);
    doNothing().when(file).refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    DBNDataSource resNode = mock(DBNDataSource.class);
    when(resNode.getAdapter(IResource.class)).thenReturn(file);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NavigatorResources.refreshThisResource(monitor, resNode);

    // Assert
    verify(file).refreshLocal(eq(2), isA(IProgressMonitor.class));
    verify(file).getLocation();
    verify(file).getStore();
    verify(iPath).toFile();
    verify(nullFileStore).getAdapter(isA(Class.class));
    verify(dbfRemoteFileStore).refresh(isA(DBRProgressMonitor.class));
    verify(resNode).getAdapter(isA(Class.class));
  }

  /**
   * Test {@link NavigatorResources#refreshThisResource(DBRProgressMonitor, DBNNode)}.
   *
   * <ul>
   *   <li>Given {@link File} {@link File#getLocation()} return {@link IPath#EMPTY}.
   *   <li>Then calls {@link File#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#refreshThisResource(DBRProgressMonitor,
   * DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshThisResource(DBRProgressMonitor, DBNNode)"})
  public void testRefreshThisResource_givenFileGetLocationReturnEmpty_thenCallsGetName()
      throws CoreException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    File file = mock(File.class);
    when(file.getName()).thenReturn("Name");
    Path path = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileStore efsnioFileStore =
        new EFSNIOFileStore(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(), path);
    when(file.getStore()).thenReturn(efsnioFileStore);
    when(file.getLocation()).thenReturn(IPath.EMPTY);
    doNothing().when(file).refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    DBNDataSource resNode = mock(DBNDataSource.class);
    when(resNode.getAdapter(IResource.class)).thenReturn(file);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NavigatorResources.refreshThisResource(monitor, resNode);

    // Assert
    verify(file).refreshLocal(eq(2), isA(IProgressMonitor.class));
    verify(file).getLocation();
    verify(file).getName();
    verify(file).getStore();
    verify(resNode).getAdapter(isA(Class.class));
  }

  /**
   * Test {@link NavigatorResources#refreshThisResource(DBRProgressMonitor, DBNNode)}.
   *
   * <ul>
   *   <li>Given {@link File} {@link File#getLocation()} return {@code null}.
   *   <li>Then calls {@link File#getLocation()}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#refreshThisResource(DBRProgressMonitor,
   * DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshThisResource(DBRProgressMonitor, DBNNode)"})
  public void testRefreshThisResource_givenFileGetLocationReturnNull_thenCallsGetLocation()
      throws CoreException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    File file = mock(File.class);
    Path path = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileStore efsnioFileStore =
        new EFSNIOFileStore(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(), path);
    when(file.getStore()).thenReturn(efsnioFileStore);
    when(file.getLocation()).thenReturn(null);
    doNothing().when(file).refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    DBNDataSource resNode = mock(DBNDataSource.class);
    when(resNode.getAdapter(IResource.class)).thenReturn(file);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NavigatorResources.refreshThisResource(monitor, resNode);

    // Assert
    verify(file).refreshLocal(eq(2), isA(IProgressMonitor.class));
    verify(file).getLocation();
    verify(file).getStore();
    verify(resNode).getAdapter(isA(Class.class));
  }

  /**
   * Test {@link NavigatorResources#refreshThisResource(DBRProgressMonitor, DBNNode)}.
   *
   * <ul>
   *   <li>Given {@link File} {@link File#getLocation()} return {@link
   *       org.eclipse.core.runtime.Path#Path(String, String)} with {@code Device} and {@code Path}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#refreshThisResource(DBRProgressMonitor,
   * DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshThisResource(DBRProgressMonitor, DBNNode)"})
  public void testRefreshThisResource_givenFileGetLocationReturnPathWithDeviceAndPath()
      throws CoreException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    File file = mock(File.class);
    when(file.getName()).thenReturn("Name");
    Path path = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileStore efsnioFileStore =
        new EFSNIOFileStore(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(), path);
    when(file.getStore()).thenReturn(efsnioFileStore);
    when(file.getLocation()).thenReturn(new org.eclipse.core.runtime.Path("Device", "Path"));
    doNothing().when(file).refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    DBNDataSource resNode = mock(DBNDataSource.class);
    when(resNode.getAdapter(IResource.class)).thenReturn(file);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NavigatorResources.refreshThisResource(monitor, resNode);

    // Assert
    verify(file).refreshLocal(eq(2), isA(IProgressMonitor.class));
    verify(file).getLocation();
    verify(file).getName();
    verify(file).getStore();
    verify(resNode).getAdapter(isA(Class.class));
  }

  /**
   * Test {@link NavigatorResources#refreshThisResource(DBRProgressMonitor, DBNNode)}.
   *
   * <ul>
   *   <li>Given {@link File} {@link File#getLocation()} return {@link
   *       org.eclipse.core.runtime.Path#Path(String)} with {@code Full Path}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#refreshThisResource(DBRProgressMonitor,
   * DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshThisResource(DBRProgressMonitor, DBNNode)"})
  public void testRefreshThisResource_givenFileGetLocationReturnPathWithFullPath()
      throws CoreException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    File file = mock(File.class);
    when(file.getName()).thenReturn("Name");
    Path path = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileStore efsnioFileStore =
        new EFSNIOFileStore(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(), path);
    when(file.getStore()).thenReturn(efsnioFileStore);
    when(file.getLocation()).thenReturn(new org.eclipse.core.runtime.Path("Full Path"));
    doNothing().when(file).refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    DBNDataSource resNode = mock(DBNDataSource.class);
    when(resNode.getAdapter(IResource.class)).thenReturn(file);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NavigatorResources.refreshThisResource(monitor, resNode);

    // Assert
    verify(file).refreshLocal(eq(2), isA(IProgressMonitor.class));
    verify(file).getLocation();
    verify(file).getName();
    verify(file).getStore();
    verify(resNode).getAdapter(isA(Class.class));
  }

  /**
   * Test {@link NavigatorResources#refreshThisResource(DBRProgressMonitor, DBNNode)}.
   *
   * <ul>
   *   <li>Given {@link File} {@link File#getLocation()} return {@link IPath#ROOT}.
   *   <li>Then calls {@link File#getLocation()}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#refreshThisResource(DBRProgressMonitor,
   * DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshThisResource(DBRProgressMonitor, DBNNode)"})
  public void testRefreshThisResource_givenFileGetLocationReturnRoot_thenCallsGetLocation()
      throws CoreException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    File file = mock(File.class);
    Path path = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileStore efsnioFileStore =
        new EFSNIOFileStore(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(), path);
    when(file.getStore()).thenReturn(efsnioFileStore);
    when(file.getLocation()).thenReturn(IPath.ROOT);
    doNothing().when(file).refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    DBNDataSource resNode = mock(DBNDataSource.class);
    when(resNode.getAdapter(IResource.class)).thenReturn(file);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NavigatorResources.refreshThisResource(monitor, resNode);

    // Assert
    verify(file).refreshLocal(eq(2), isA(IProgressMonitor.class));
    verify(file).getLocation();
    verify(file).getStore();
    verify(resNode).getAdapter(isA(Class.class));
  }

  /**
   * Test {@link NavigatorResources#refreshThisResource(DBRProgressMonitor, DBNNode)}.
   *
   * <ul>
   *   <li>Given {@link File} {@link File#getStore()} return {@code null}.
   *   <li>Then calls {@link IPath#toFile()}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#refreshThisResource(DBRProgressMonitor,
   * DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshThisResource(DBRProgressMonitor, DBNNode)"})
  public void testRefreshThisResource_givenFileGetStoreReturnNull_thenCallsToFile()
      throws CoreException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    IPath iPath = mock(IPath.class);
    when(iPath.toFile())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    File file = mock(File.class);
    when(file.getStore()).thenReturn(null);
    when(file.getLocation()).thenReturn(iPath);
    doNothing().when(file).refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    DBNDataSource resNode = mock(DBNDataSource.class);
    when(resNode.getAdapter(IResource.class)).thenReturn(file);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NavigatorResources.refreshThisResource(monitor, resNode);

    // Assert
    verify(file).refreshLocal(eq(2), isA(IProgressMonitor.class));
    verify(file).getLocation();
    verify(file).getStore();
    verify(iPath).toFile();
    verify(resNode).getAdapter(isA(Class.class));
  }

  /**
   * Test {@link NavigatorResources#refreshThisResource(DBRProgressMonitor, DBNNode)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link DBNDataSource} {@link DBNDataSource#getAdapter(Class)} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#refreshThisResource(DBRProgressMonitor,
   * DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshThisResource(DBRProgressMonitor, DBNNode)"})
  public void testRefreshThisResource_givenNull_whenDBNDataSourceGetAdapterReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBNDataSource resNode = mock(DBNDataSource.class);
    when(resNode.getAdapter(IResource.class)).thenReturn(null);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NavigatorResources.refreshThisResource(monitor, resNode);

    // Assert
    verify(resNode).getAdapter(isA(Class.class));
  }

  /**
   * Test {@link NavigatorResources#refreshThisResource(DBRProgressMonitor, DBNNode)}.
   *
   * <ul>
   *   <li>Then calls {@link IPath#toFile()}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#refreshThisResource(DBRProgressMonitor,
   * DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshThisResource(DBRProgressMonitor, DBNNode)"})
  public void testRefreshThisResource_thenCallsToFile() throws CoreException, DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    IPath iPath = mock(IPath.class);
    when(iPath.toFile())
        .thenReturn(Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile());

    File file = mock(File.class);
    Path path = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileStore efsnioFileStore =
        new EFSNIOFileStore(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(), path);
    when(file.getStore()).thenReturn(efsnioFileStore);
    when(file.getLocation()).thenReturn(iPath);
    doNothing().when(file).refreshLocal(anyInt(), Mockito.<IProgressMonitor>any());

    DBNDataSource resNode = mock(DBNDataSource.class);
    when(resNode.getAdapter(IResource.class)).thenReturn(file);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NavigatorResources.refreshThisResource(monitor, resNode);

    // Assert
    verify(file).refreshLocal(eq(2), isA(IProgressMonitor.class));
    verify(file).getLocation();
    verify(file).getStore();
    verify(iPath).toFile();
    verify(resNode).getAdapter(isA(Class.class));
  }

  /**
   * Test {@link NavigatorResources#refreshThisResource(DBRProgressMonitor, DBNNode)}.
   *
   * <ul>
   *   <li>When {@link DBNFileSystems#DBNFileSystems(DBNProject)} with parentNode is {@code null}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#refreshThisResource(DBRProgressMonitor,
   * DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshThisResource(DBRProgressMonitor, DBNNode)"})
  public void testRefreshThisResource_whenDBNFileSystemsWithParentNodeIsNull_thenDoesNotThrow()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    NavigatorResources.refreshThisResource(monitor, new DBNFileSystems(null));
  }

  /**
   * Test {@link NavigatorResources#refreshFileStore(DBRProgressMonitor, IResource)}.
   *
   * <p>Method under test: {@link NavigatorResources#refreshFileStore(DBRProgressMonitor,
   * IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshFileStore(DBRProgressMonitor, IResource)"})
  public void testRefreshFileStore() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File resource = mock(File.class);
    Path path = Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");
    EFSNIOFileStore efsnioFileStore =
        new EFSNIOFileStore(
            Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri(), path);
    when(resource.getStore()).thenReturn(efsnioFileStore);

    // Act
    NavigatorResources.refreshFileStore(monitor, resource);

    // Assert
    verify(resource).getStore();
  }

  /**
   * Test {@link NavigatorResources#refreshFileStore(DBRProgressMonitor, IResource)}.
   *
   * <p>Method under test: {@link NavigatorResources#refreshFileStore(DBRProgressMonitor,
   * IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshFileStore(DBRProgressMonitor, IResource)"})
  public void testRefreshFileStore2() throws DBException {
    // Arrange
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File resource = mock(File.class);
    when(resource.getStore())
        .thenReturn(
            new VirtualFileStore(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri()));
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NavigatorResources.refreshFileStore(null, resource);

    // Assert
    verify(resource).getStore();
  }

  /**
   * Test {@link NavigatorResources#refreshFileStore(DBRProgressMonitor, IResource)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link File} {@link File#getStore()} return {@code null}.
   *   <li>Then calls {@link File#getStore()}.
   * </ul>
   *
   * <p>Method under test: {@link NavigatorResources#refreshFileStore(DBRProgressMonitor,
   * IResource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NavigatorResources.refreshFileStore(DBRProgressMonitor, IResource)"})
  public void testRefreshFileStore_givenNull_whenFileGetStoreReturnNull_thenCallsGetStore()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    File resource = mock(File.class);
    when(resource.getStore()).thenReturn(null);
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toUri();
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt");

    // Act
    NavigatorResources.refreshFileStore(monitor, resource);

    // Assert
    verify(resource).getStore();
  }
}
