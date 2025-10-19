package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBIconComposite;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.auth.SMSessionContext;
import org.jkiss.dbeaver.model.impl.auth.SessionContextImpl;
import org.jkiss.dbeaver.model.navigator.DBNEvent.Action;
import org.jkiss.dbeaver.model.navigator.DBNEvent.NodeChange;
import org.jkiss.dbeaver.model.navigator.DBNModel.NodePath;
import org.jkiss.dbeaver.model.navigator.DBNNode.NodePathType;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSObjectState;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DBNModelDiffblueTest {
  @InjectMocks private DBNModel dBNModel;

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBNModel#DBNModel(DBPPlatform, List)}
   *   <li>{@link DBNModel#setModelAuthContext(SMSessionContext)}
   *   <li>{@link DBNModel#getModelAuthContext()}
   *   <li>{@link DBNModel#getModelProjects()}
   *   <li>{@link DBNModel#getPlatform()}
   *   <li>{@link DBNModel#getRoot()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBNModel.<init>(DBPPlatform, List)",
    "SMSessionContext DBNModel.getModelAuthContext()",
    "List DBNModel.getModelProjects()",
    "DBPPlatform DBNModel.getPlatform()",
    "org.jkiss.dbeaver.model.navigator.DBNRoot DBNModel.getRoot()",
    "void DBNModel.setModelAuthContext(SMSessionContext)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    ArrayList<DBPProject> modelProjects = new ArrayList<>();

    // Act
    DBNModel actualDbnModel = new DBNModel(platform, modelProjects);
    SessionContextImpl modelAuthContext = new SessionContextImpl(null);
    actualDbnModel.setModelAuthContext(modelAuthContext);
    SMSessionContext actualModelAuthContext = actualDbnModel.getModelAuthContext();
    List<? extends DBPProject> actualModelProjects = actualDbnModel.getModelProjects();
    DBPPlatform actualPlatform = actualDbnModel.getPlatform();

    // Assert
    assertTrue(actualModelAuthContext instanceof SessionContextImpl);
    assertNull(actualDbnModel.getRoot());
    assertTrue(actualModelProjects.isEmpty());
    assertSame(modelProjects, actualModelProjects);
    assertSame(modelAuthContext, actualModelAuthContext);
    assertSame(platform, actualPlatform);
  }

  /**
   * Test {@link DBNModel#isGlobal()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#isGlobal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNModel.isGlobal()"})
  public void testIsGlobal_thenReturnFalse() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());

    // Act and Assert
    assertFalse(dbnModel.isGlobal());
  }

  /**
   * Test {@link DBNModel#isGlobal()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#isGlobal()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNModel.isGlobal()"})
  public void testIsGlobal_thenReturnTrue() {
    // Arrange
    DBNModel dbnModel = new DBNModel(mock(DBPPlatform.class), null);

    // Act and Assert
    assertTrue(dbnModel.isGlobal());
  }

  /**
   * Test {@link DBNModel#findNode(DBSObject)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer}.
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#findNode(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDatabaseNode DBNModel.findNode(DBSObject)"})
  public void testFindNode_givenDBPDataSourceContainer_thenCallsGetContainer() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());

    DBPDataSource object = mock(DBPDataSource.class);
    when(object.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    // Act
    DBNDatabaseNode actualFindNodeResult = dbnModel.findNode(object);

    // Assert
    verify(object).getContainer();
    assertNull(actualFindNodeResult);
  }

  /**
   * Test {@link DBNModel#findNode(DBSObject)}.
   *
   * <ul>
   *   <li>When {@link DBSDocumentConstraint#DBSDocumentConstraint(DBSDocumentContainer)} with
   *       entity is {@link DBSDocumentContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#findNode(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDatabaseNode DBNModel.findNode(DBSObject)"})
  public void testFindNode_whenDBSDocumentConstraintWithEntityIsDBSDocumentContainer() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());

    // Act
    DBNDatabaseNode actualFindNodeResult =
        dbnModel.findNode(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    assertNull(actualFindNodeResult);
  }

  /**
   * Test {@link DBNModel#getNodeByObject(DBSObject)} with {@code object}.
   *
   * <p>Method under test: {@link DBNModel#getNodeByObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDatabaseNode DBNModel.getNodeByObject(DBSObject)"})
  public void testGetNodeByObjectWithObject() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());

    // Act
    DBNDatabaseNode actualNodeByObject =
        dbnModel.getNodeByObject(new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    assertNull(actualNodeByObject);
  }

  /**
   * Test {@link DBNModel#getNodeByObject(DBSObject)} with {@code object}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceContainer}.
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#getNodeByObject(DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNDatabaseNode DBNModel.getNodeByObject(DBSObject)"})
  public void testGetNodeByObjectWithObject_givenDBPDataSourceContainer_thenCallsGetContainer() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());

    DBPDataSource object = mock(DBPDataSource.class);
    when(object.getContainer()).thenReturn(mock(DBPDataSourceContainer.class));

    // Act
    DBNDatabaseNode actualNodeByObject = dbnModel.getNodeByObject(object);

    // Assert
    verify(object).getContainer();
    assertNull(actualNodeByObject);
  }

  /**
   * Test {@link DBNModel#getNodeByPath(DBRProgressMonitor, String)} with {@code monitor}, {@code
   * path}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#getNodeByPath(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode DBNModel.getNodeByPath(DBRProgressMonitor, String)"})
  public void testGetNodeByPathWithMonitorPath_whenEmptyString_thenReturnNull() throws DBException {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());

    // Act and Assert
    assertNull(dbnModel.getNodeByPath(new LoggingProgressMonitor(), ""));
  }

  /**
   * Test {@link DBNModel#getNodeByPath(DBRProgressMonitor, String)} with {@code monitor}, {@code
   * path}.
   *
   * <ul>
   *   <li>When {@code /}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#getNodeByPath(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBNNode DBNModel.getNodeByPath(DBRProgressMonitor, String)"})
  public void testGetNodeByPathWithMonitorPath_whenSlash_thenReturnNull() throws DBException {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());

    // Act and Assert
    assertNull(dbnModel.getNodeByPath(new LoggingProgressMonitor(), "/"));
  }

  /**
   * Test {@link DBNModel#fireNodeUpdate(Object, DBNNode, NodeChange)}.
   *
   * <ul>
   *   <li>Given {@link DBPPlatform} {@link DBPPlatform#isShuttingDown()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#fireNodeUpdate(Object, DBNNode, NodeChange)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNModel.fireNodeUpdate(Object, DBNNode, NodeChange)"})
  public void testFireNodeUpdate_givenDBPPlatformIsShuttingDownReturnFalse() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    when(platform.isShuttingDown()).thenReturn(false);
    DBNModel dbnModel = new DBNModel(platform, null);

    // Act
    dbnModel.fireNodeUpdate(DBNEvent.FORCE_REFRESH, new DBNEmptyNode(), NodeChange.BEFORE_LOAD);

    // Assert
    verify(platform).isShuttingDown();
  }

  /**
   * Test {@link DBNModel#fireNodeUpdate(Object, DBNNode, NodeChange)}.
   *
   * <ul>
   *   <li>Given {@link DBPPlatform} {@link DBPPlatform#isShuttingDown()} return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#fireNodeUpdate(Object, DBNNode, NodeChange)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNModel.fireNodeUpdate(Object, DBNNode, NodeChange)"})
  public void testFireNodeUpdate_givenDBPPlatformIsShuttingDownReturnTrue() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    when(platform.isShuttingDown()).thenReturn(true);
    DBNModel dbnModel = new DBNModel(platform, null);

    // Act
    dbnModel.fireNodeUpdate(DBNEvent.FORCE_REFRESH, new DBNEmptyNode(), NodeChange.BEFORE_LOAD);

    // Assert
    verify(platform).isShuttingDown();
  }

  /**
   * Test {@link DBNModel#fireNodeUpdate(Object, DBNNode, NodeChange)}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#fireNodeUpdate(Object, DBNNode, NodeChange)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNModel.fireNodeUpdate(Object, DBNNode, NodeChange)"})
  public void testFireNodeUpdate_thenDoesNotThrow() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());

    // Act and Assert
    dbnModel.fireNodeUpdate(DBNEvent.FORCE_REFRESH, new DBNEmptyNode(), NodeChange.BEFORE_LOAD);
  }

  /**
   * Test {@link DBNModel#fireNodeUpdate(Object, DBNNode, NodeChange)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#fireNodeUpdate(Object, DBNNode, NodeChange)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNModel.fireNodeUpdate(Object, DBNNode, NodeChange)"})
  public void testFireNodeUpdate_thenThrowIllegalStateException() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    when(platform.isShuttingDown()).thenThrow(new IllegalStateException());
    DBNModel dbnModel = new DBNModel(platform, null);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            dbnModel.fireNodeUpdate(
                DBNEvent.FORCE_REFRESH, new DBNEmptyNode(), NodeChange.BEFORE_LOAD));
    verify(platform).isShuttingDown();
  }

  /**
   * Test {@link DBNModel#fireNodeEvent(DBNEvent)}.
   *
   * <ul>
   *   <li>Given {@link DBPPlatform} {@link DBPPlatform#isShuttingDown()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#fireNodeEvent(DBNEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNModel.fireNodeEvent(DBNEvent)"})
  public void testFireNodeEvent_givenDBPPlatformIsShuttingDownReturnFalse() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    when(platform.isShuttingDown()).thenReturn(false);
    DBNModel dbnModel = new DBNModel(platform, null);
    DBNEvent event = new DBNEvent(DBNEvent.FORCE_REFRESH, Action.ADD, new DBNEmptyNode());

    // Act
    dbnModel.fireNodeEvent(event);

    // Assert
    verify(platform).isShuttingDown();
  }

  /**
   * Test {@link DBNModel#fireNodeEvent(DBNEvent)}.
   *
   * <ul>
   *   <li>Given {@link DBPPlatform} {@link DBPPlatform#isShuttingDown()} return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#fireNodeEvent(DBNEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNModel.fireNodeEvent(DBNEvent)"})
  public void testFireNodeEvent_givenDBPPlatformIsShuttingDownReturnTrue() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    when(platform.isShuttingDown()).thenReturn(true);
    DBNModel dbnModel = new DBNModel(platform, null);
    DBNEvent event = new DBNEvent(DBNEvent.FORCE_REFRESH, Action.ADD, new DBNEmptyNode());

    // Act
    dbnModel.fireNodeEvent(event);

    // Assert
    verify(platform).isShuttingDown();
  }

  /**
   * Test {@link DBNModel#fireNodeEvent(DBNEvent)}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#fireNodeEvent(DBNEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNModel.fireNodeEvent(DBNEvent)"})
  public void testFireNodeEvent_thenDoesNotThrow() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    DBNEvent event = new DBNEvent(DBNEvent.FORCE_REFRESH, Action.ADD, new DBNEmptyNode());

    // Act and Assert
    dbnModel.fireNodeEvent(event);
  }

  /**
   * Test {@link DBNModel#fireNodeEvent(DBNEvent)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#fireNodeEvent(DBNEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNModel.fireNodeEvent(DBNEvent)"})
  public void testFireNodeEvent_thenThrowIllegalStateException() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    when(platform.isShuttingDown()).thenThrow(new IllegalStateException());
    DBNModel dbnModel = new DBNModel(platform, null);
    DBNEvent event = new DBNEvent(DBNEvent.FORCE_REFRESH, Action.ADD, new DBNEmptyNode());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dbnModel.fireNodeEvent(event));
    verify(platform).isShuttingDown();
  }

  /**
   * Test {@link DBNModel#getStateOverlayImage(DBPImage, DBSObjectState)}.
   *
   * <p>Method under test: {@link DBNModel#getStateOverlayImage(DBPImage, DBSObjectState)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBNModel.getStateOverlayImage(DBPImage, DBSObjectState)"})
  public void testGetStateOverlayImage() {
    // Arrange
    DBPImage main = mock(DBPImage.class);
    when(main.getLocation()).thenReturn("Location");
    DBIconComposite image =
        new DBIconComposite(
            main,
            true,
            mock(DBPImage.class),
            mock(DBPImage.class),
            mock(DBPImage.class),
            mock(DBPImage.class));
    DBSObjectState state = DBSObjectState.NORMAL;

    // Act
    DBPImage actualStateOverlayImage = DBNModel.getStateOverlayImage(image, state);
    String actualLocation = actualStateOverlayImage.getLocation();

    // Assert
    verify(main).getLocation();
    assertTrue(actualStateOverlayImage instanceof DBIconComposite);
    assertEquals("Location", image.getLocation());
    assertEquals("Location", actualStateOverlayImage.getLocation());
    assertEquals("Location", actualLocation);
    assertEquals("Normal", state.getTitle());
    assertNull(state.getOverlayImage());
    assertTrue(image.hasOverlays());
    assertTrue(((DBIconComposite) actualStateOverlayImage).hasOverlays());
    assertTrue(image.isDisabled());
    assertTrue(((DBIconComposite) actualStateOverlayImage).isDisabled());
  }

  /**
   * Test {@link DBNModel#getStateOverlayImage(DBPImage, DBSObjectState)}.
   *
   * <ul>
   *   <li>When {@link DBSObjectState#ACTIVE}.
   *   <li>Then BottomRight return {@link DBIcon}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#getStateOverlayImage(DBPImage, DBSObjectState)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBNModel.getStateOverlayImage(DBPImage, DBSObjectState)"})
  public void testGetStateOverlayImage_whenActive_thenBottomRightReturnDBIcon() {
    // Arrange
    DBIconComposite image =
        new DBIconComposite(
            mock(DBPImage.class),
            true,
            mock(DBPImage.class),
            mock(DBPImage.class),
            mock(DBPImage.class),
            mock(DBPImage.class));
    DBSObjectState state = DBSObjectState.ACTIVE;

    // Act
    DBPImage actualStateOverlayImage = DBNModel.getStateOverlayImage(image, state);

    // Assert
    DBPImage bottomRight = ((DBIconComposite) actualStateOverlayImage).getBottomRight();
    assertTrue(bottomRight instanceof DBIcon);
    assertTrue(actualStateOverlayImage instanceof DBIconComposite);
    assertEquals("Active", state.getTitle());
    assertEquals("over/success_ovr.png", bottomRight.getLocation());
    assertEquals("over_success", ((DBIcon) bottomRight).getToken());
    assertNull(image.getLocation());
    assertNull(actualStateOverlayImage.getLocation());
    assertTrue(image.hasOverlays());
    assertTrue(((DBIconComposite) actualStateOverlayImage).hasOverlays());
    assertTrue(image.isDisabled());
    assertTrue(((DBIconComposite) actualStateOverlayImage).isDisabled());
  }

  /**
   * Test {@link DBNModel#getStateOverlayImage(DBPImage, DBSObjectState)}.
   *
   * <ul>
   *   <li>When {@link DBPImage}.
   *   <li>Then return BottomLeft is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#getStateOverlayImage(DBPImage, DBSObjectState)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBNModel.getStateOverlayImage(DBPImage, DBSObjectState)"})
  public void testGetStateOverlayImage_whenDBPImage_thenReturnBottomLeftIsNull() {
    // Arrange
    DBPImage image = mock(DBPImage.class);
    DBSObjectState state = DBSObjectState.ACTIVE;

    // Act
    DBPImage actualStateOverlayImage = DBNModel.getStateOverlayImage(image, state);

    // Assert
    DBPImage bottomRight = ((DBIconComposite) actualStateOverlayImage).getBottomRight();
    assertTrue(bottomRight instanceof DBIcon);
    assertTrue(actualStateOverlayImage instanceof DBIconComposite);
    assertEquals("Active", state.getTitle());
    assertEquals("over/success_ovr.png", bottomRight.getLocation());
    assertEquals("over_success", ((DBIcon) bottomRight).getToken());
    assertNull(actualStateOverlayImage.getLocation());
    assertNull(((DBIconComposite) actualStateOverlayImage).getBottomLeft());
    assertNull(((DBIconComposite) actualStateOverlayImage).getTopLeft());
    assertNull(((DBIconComposite) actualStateOverlayImage).getTopRight());
    assertFalse(((DBIconComposite) actualStateOverlayImage).isDisabled());
    assertTrue(((DBIconComposite) actualStateOverlayImage).hasOverlays());
    assertSame(image, ((DBIconComposite) actualStateOverlayImage).getMain());
  }

  /**
   * Test {@link DBNModel#getStateOverlayImage(DBPImage, DBSObjectState)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#getStateOverlayImage(DBPImage, DBSObjectState)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPImage DBNModel.getStateOverlayImage(DBPImage, DBSObjectState)"})
  public void testGetStateOverlayImage_whenNull_thenNull() {
    // Arrange
    DBIconComposite image =
        new DBIconComposite(
            mock(DBPImage.class),
            true,
            mock(DBPImage.class),
            mock(DBPImage.class),
            mock(DBPImage.class),
            mock(DBPImage.class));

    // Act
    DBPImage actualStateOverlayImage = DBNModel.getStateOverlayImage(image, null);

    // Assert
    assertTrue(actualStateOverlayImage instanceof DBIconComposite);
    assertNull(image.getLocation());
    assertNull(actualStateOverlayImage.getLocation());
    assertNull(null);
    assertTrue(image.hasOverlays());
    assertTrue(((DBIconComposite) actualStateOverlayImage).hasOverlays());
    assertTrue(image.isDisabled());
    assertTrue(((DBIconComposite) actualStateOverlayImage).isDisabled());
  }

  /**
   * Test {@link DBNModel#isNodeVisible(DBNNode)}.
   *
   * <p>Method under test: {@link DBNModel#isNodeVisible(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNModel.isNodeVisible(DBNNode)"})
  public void testIsNodeVisible() {
    // Arrange
    DBPPlatform platform = mock(DBPPlatform.class);
    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());

    // Act and Assert
    assertTrue(dbnModel.isNodeVisible(new DBNEmptyNode()));
  }

  /**
   * Test {@link DBNModel#isNodeVisible(DBNNode)}.
   *
   * <ul>
   *   <li>Given {@link Function} {@link Function#apply(Object)} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#isNodeVisible(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNModel.isNodeVisible(DBNNode)"})
  public void testIsNodeVisible_givenFunctionApplyReturnFalse_thenReturnFalse() {
    // Arrange
    Function<DBNNode, Boolean> filter = mock(Function.class);
    when(filter.apply(Mockito.<DBNNode>any())).thenReturn(false);
    dBNModel.addFilter(filter);

    // Act
    boolean actualIsNodeVisibleResult = dBNModel.isNodeVisible(new DBNEmptyNode());

    // Assert
    verify(filter).apply(isA(DBNNode.class));
    assertFalse(actualIsNodeVisibleResult);
  }

  /**
   * Test {@link DBNModel#isNodeVisible(DBNNode)}.
   *
   * <ul>
   *   <li>Given {@link Function} {@link Function#apply(Object)} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#isNodeVisible(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNModel.isNodeVisible(DBNNode)"})
  public void testIsNodeVisible_givenFunctionApplyReturnTrue_thenReturnTrue() {
    // Arrange
    Function<DBNNode, Boolean> filter = mock(Function.class);
    when(filter.apply(Mockito.<DBNNode>any())).thenReturn(true);
    DBPPlatform platform = mock(DBPPlatform.class);

    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    dbnModel.addFilter(filter);

    // Act
    boolean actualIsNodeVisibleResult = dbnModel.isNodeVisible(new DBNEmptyNode());

    // Assert
    verify(filter).apply(isA(DBNNode.class));
    assertTrue(actualIsNodeVisibleResult);
  }

  /**
   * Test {@link DBNModel#isNodeVisible(DBNNode)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBNModel#isNodeVisible(DBNNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNModel.isNodeVisible(DBNNode)"})
  public void testIsNodeVisible_thenThrowIllegalStateException() {
    // Arrange
    Function<DBNNode, Boolean> filter = mock(Function.class);
    when(filter.apply(Mockito.<DBNNode>any())).thenThrow(new IllegalStateException());
    DBPPlatform platform = mock(DBPPlatform.class);

    DBNModel dbnModel = new DBNModel(platform, new ArrayList<>());
    dbnModel.addFilter(filter);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> dbnModel.isNodeVisible(new DBNEmptyNode()));
    verify(filter).apply(isA(DBNNode.class));
  }

  /**
   * Test NodePath {@link NodePath#first()}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code Path Items}.
   *   <li>Then return {@code Path Items}.
   * </ul>
   *
   * <p>Method under test: {@link NodePath#first()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NodePath.first()"})
  public void testNodePathFirst_givenArrayListAddPathItems_thenReturnPathItems() {
    // Arrange
    ArrayList<String> pathItems = new ArrayList<>();
    pathItems.add("Path Items");

    // Act and Assert
    assertEquals("Path Items", new NodePath(NodePathType.resource, pathItems).first());
  }

  /**
   * Test NodePath {@link NodePath#first()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NodePath#first()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NodePath.first()"})
  public void testNodePathFirst_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new NodePath(NodePathType.resource, new ArrayList<>()).first());
  }

  /**
   * Test NodePath {@link NodePath#NodePath(NodePathType, List)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@link NodePath#pathItems} size is two.
   * </ul>
   *
   * <p>Method under test: {@link NodePath#NodePath(NodePathType, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NodePath.<init>(NodePathType, List)"})
  public void testNodePathNewNodePath_given42_whenArrayListAdd42_thenReturnPathItemsSizeIsTwo() {
    // Arrange
    ArrayList<String> pathItems = new ArrayList<>();
    pathItems.add("42");
    pathItems.add("foo");

    // Act
    NodePath actualNodePath = new NodePath(NodePathType.resource, pathItems);

    // Assert
    List<String> stringList = actualNodePath.pathItems;
    assertEquals(2, stringList.size());
    assertEquals("42", stringList.get(0));
    assertEquals("42", actualNodePath.first());
    assertEquals("foo", stringList.get(1));
  }

  /**
   * Test NodePath {@link NodePath#NodePath(NodePathType, List)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>Then return {@link NodePath#pathItems} size is one.
   * </ul>
   *
   * <p>Method under test: {@link NodePath#NodePath(NodePathType, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NodePath.<init>(NodePathType, List)"})
  public void testNodePathNewNodePath_givenFoo_thenReturnPathItemsSizeIsOne() {
    // Arrange
    ArrayList<String> pathItems = new ArrayList<>();
    pathItems.add("foo");

    // Act
    NodePath actualNodePath = new NodePath(NodePathType.resource, pathItems);

    // Assert
    List<String> stringList = actualNodePath.pathItems;
    assertEquals(1, stringList.size());
    assertEquals("foo", stringList.get(0));
    assertEquals("foo", actualNodePath.first());
    assertEquals(NodePathType.resource, actualNodePath.type);
    assertTrue(actualNodePath.legacyFormat);
  }

  /**
   * Test NodePath {@link NodePath#NodePath(NodePathType, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return first is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NodePath#NodePath(NodePathType, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NodePath.<init>(NodePathType, List)"})
  public void testNodePathNewNodePath_whenArrayList_thenReturnFirstIsNull() {
    // Arrange and Act
    NodePath actualNodePath = new NodePath(NodePathType.resource, new ArrayList<>());

    // Assert
    assertNull(actualNodePath.first());
    assertEquals(NodePathType.resource, actualNodePath.type);
    assertTrue(actualNodePath.pathItems.isEmpty());
    assertTrue(actualNodePath.legacyFormat);
  }

  /**
   * Test NodePath {@link NodePath#NodePath(NodePathType, List)}.
   *
   * <ul>
   *   <li>When {@link NodePathType#node}.
   *   <li>Then return {@link NodePath#type} is {@code node}.
   * </ul>
   *
   * <p>Method under test: {@link NodePath#NodePath(NodePathType, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NodePath.<init>(NodePathType, List)"})
  public void testNodePathNewNodePath_whenNode_thenReturnTypeIsNode() {
    // Arrange and Act
    NodePath actualNodePath = new NodePath(NodePathType.node, new ArrayList<>());

    // Assert
    assertNull(actualNodePath.first());
    assertEquals(NodePathType.node, actualNodePath.type);
    assertFalse(actualNodePath.legacyFormat);
    assertTrue(actualNodePath.pathItems.isEmpty());
  }

  /**
   * Test NodePath {@link NodePath#toString()}.
   *
   * <p>Method under test: {@link NodePath#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NodePath.toString()"})
  public void testNodePathToString() {
    // Arrange, Act and Assert
    assertEquals(
        "resource://[]", new NodePath(NodePathType.resource, new ArrayList<>()).toString());
  }
}
