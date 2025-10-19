package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor;
import org.jkiss.dbeaver.model.navigator.meta.DBXTreeFolder;
import org.jkiss.dbeaver.model.navigator.meta.DBXTreeNode;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DBNDatabaseFolderDiffblueTest {
  @InjectMocks private DBNDatabaseFolder dBNDatabaseFolder;

  @Mock private DBNDatabaseNode dBNDatabaseNode;

  @Mock private DBXTreeFolder dBXTreeFolder;

  /**
   * Test {@link DBNDatabaseFolder#reloadObject(DBRProgressMonitor, DBSObject)}.
   *
   * <p>Method under test: {@link DBNDatabaseFolder#reloadObject(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNDatabaseFolder.reloadObject(DBRProgressMonitor, DBSObject)"})
  public void testReloadObject() {
    // Arrange
    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getNavigatorRoot()).thenReturn(null);

    DBPDataSourceContainer dataSource = mock(DBPDataSourceContainer.class);
    when(dataSource.getDriver()).thenReturn(dbpDriver);
    DBNDataSource parent = new DBNDataSource(new DBNEmptyNode(), dataSource);
    DBNDatabaseFolder dbnDatabaseFolder = new DBNDatabaseFolder(parent, null);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    boolean actualReloadObjectResult =
        dbnDatabaseFolder.reloadObject(
            monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    verify(dataSource).getDriver();
    verify(dbpDriver).getNavigatorRoot();
    assertFalse(actualReloadObjectResult);
  }

  /**
   * Test {@link DBNDatabaseFolder#getObject()}.
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DBNDatabaseFolder.getObject()"})
  public void testGetObject() {
    // Arrange and Act
    DBSObject actualObject = dBNDatabaseFolder.getObject();

    // Assert
    assertSame(dBNDatabaseFolder, actualObject);
  }

  /**
   * Test {@link DBNDatabaseFolder#getValueObject()}.
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getValueObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Object DBNDatabaseFolder.getValueObject()"})
  public void testGetValueObject() {
    // Arrange
    when(dBNDatabaseNode.getValueObject()).thenReturn(DBNEvent.FORCE_REFRESH);

    // Act
    dBNDatabaseFolder.getValueObject();

    // Assert
    verify(dBNDatabaseNode).getValueObject();
  }

  /**
   * Test {@link DBNDatabaseFolder#getChildrenType()}.
   *
   * <ul>
   *   <li>Then return {@code Children Type Label}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getChildrenType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseFolder.getChildrenType()"})
  public void testGetChildrenType_thenReturnChildrenTypeLabel() {
    // Arrange
    when(dBNDatabaseNode.getDataSource()).thenReturn(mock(DBPDataSource.class));

    ArrayList<DBXTreeNode> dbxTreeNodeList = new ArrayList<>();
    dbxTreeNodeList.add(dBXTreeFolder);
    when(dBXTreeFolder.getChildrenTypeLabel(Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Children Type Label");
    when(dBXTreeFolder.getChildren(Mockito.<DBNNode>any())).thenReturn(dbxTreeNodeList);

    // Act
    String actualChildrenType = dBNDatabaseFolder.getChildrenType();

    // Assert
    verify(dBNDatabaseNode).getDataSource();
    verify(dBXTreeFolder).getChildren(isA(DBNNode.class));
    verify(dBXTreeFolder).getChildrenTypeLabel(isA(DBPDataSource.class), (String) isNull());
    assertEquals("Children Type Label", actualChildrenType);
  }

  /**
   * Test {@link DBNDatabaseFolder#getChildrenType()}.
   *
   * <ul>
   *   <li>Then return {@code ?}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getChildrenType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseFolder.getChildrenType()"})
  public void testGetChildrenType_thenReturnQuestionMark() {
    // Arrange
    when(dBXTreeFolder.getChildren(Mockito.<DBNNode>any())).thenReturn(new ArrayList<>());

    // Act
    String actualChildrenType = dBNDatabaseFolder.getChildrenType();

    // Assert
    verify(dBXTreeFolder).getChildren(isA(DBNNode.class));
    assertEquals("?", actualChildrenType);
  }

  /**
   * Test {@link DBNDatabaseFolder#getNodeId()}.
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getNodeId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseFolder.getNodeId()"})
  public void testGetNodeId() {
    // Arrange
    when(dBXTreeFolder.getHumanReadableId()).thenReturn("42");

    // Act
    String actualNodeId = dBNDatabaseFolder.getNodeId();

    // Assert
    verify(dBXTreeFolder).getHumanReadableId();
    assertEquals("42", actualNodeId);
  }

  /**
   * Test {@link DBNDatabaseFolder#getName()}.
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseFolder.getName()"})
  public void testGetName() {
    // Arrange
    when(dBNDatabaseNode.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(dBXTreeFolder.getChildrenTypeLabel(Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Children Type Label");

    // Act
    String actualName = dBNDatabaseFolder.getName();

    // Assert
    verify(dBNDatabaseNode).getDataSource();
    verify(dBXTreeFolder).getChildrenTypeLabel(isA(DBPDataSource.class), (String) isNull());
    assertEquals("Children Type Label", actualName);
  }

  /**
   * Test {@link DBNDatabaseFolder#getLocalizedName(String)}.
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getLocalizedName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseFolder.getLocalizedName(String)"})
  public void testGetLocalizedName() {
    // Arrange
    when(dBNDatabaseNode.getDataSource()).thenReturn(mock(DBPDataSource.class));
    when(dBXTreeFolder.getChildrenTypeLabel(Mockito.<DBPDataSource>any(), Mockito.<String>any()))
        .thenReturn("Children Type Label");

    // Act
    String actualLocalizedName = dBNDatabaseFolder.getLocalizedName("en");

    // Assert
    verify(dBNDatabaseNode).getDataSource();
    verify(dBXTreeFolder).getChildrenTypeLabel(isA(DBPDataSource.class), eq("en"));
    assertEquals("Children Type Label", actualLocalizedName);
  }

  /**
   * Test {@link DBNDatabaseFolder#getDescription()}.
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseFolder.getDescription()"})
  public void testGetDescription() {
    // Arrange
    when(dBXTreeFolder.getDescription()).thenReturn("The characteristics of someone or something");

    // Act
    String actualDescription = dBNDatabaseFolder.getDescription();

    // Assert
    verify(dBXTreeFolder).getDescription();
    assertEquals("The characteristics of someone or something", actualDescription);
  }

  /**
   * Test {@link DBNDatabaseFolder#getParentObject()}.
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getParentObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSObject DBNDatabaseFolder.getParentObject()"})
  public void testGetParentObject() {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    when(dBNDatabaseNode.getObject()).thenReturn(dbsDocumentConstraint);

    // Act
    DBSObject actualParentObject = dBNDatabaseFolder.getParentObject();

    // Assert
    verify(dBNDatabaseNode).getObject();
    assertSame(dbsDocumentConstraint, actualParentObject);
  }

  /**
   * Test {@link DBNDatabaseFolder#getDataSource()}.
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBNDatabaseFolder.getDataSource()"})
  public void testGetDataSource() {
    // Arrange
    when(dBNDatabaseNode.getDataSource()).thenReturn(mock(DBPDataSource.class));

    // Act
    dBNDatabaseFolder.getDataSource();

    // Assert
    verify(dBNDatabaseNode).getDataSource();
  }

  /**
   * Test {@link DBNDatabaseFolder#isPersisted()}.
   *
   * <ul>
   *   <li>Given {@link DBNDatabaseNode} {@link DBNDatabaseNode#isPersisted()} return {@code false}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseFolder#isPersisted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNDatabaseFolder.isPersisted()"})
  public void testIsPersisted_givenDBNDatabaseNodeIsPersistedReturnFalse_thenReturnFalse() {
    // Arrange
    when(dBNDatabaseNode.isPersisted()).thenReturn(false);

    // Act
    boolean actualIsPersistedResult = dBNDatabaseFolder.isPersisted();

    // Assert
    verify(dBNDatabaseNode).isPersisted();
    assertFalse(actualIsPersistedResult);
  }

  /**
   * Test {@link DBNDatabaseFolder#isPersisted()}.
   *
   * <ul>
   *   <li>Given {@link DBNDatabaseNode} {@link DBNDatabaseNode#isPersisted()} return {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseFolder#isPersisted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNDatabaseFolder.isPersisted()"})
  public void testIsPersisted_givenDBNDatabaseNodeIsPersistedReturnTrue_thenReturnTrue() {
    // Arrange
    when(dBNDatabaseNode.isPersisted()).thenReturn(true);

    // Act
    boolean actualIsPersistedResult = dBNDatabaseFolder.isPersisted();

    // Assert
    verify(dBNDatabaseNode).isPersisted();
    assertTrue(actualIsPersistedResult);
  }

  /**
   * Test {@link DBNDatabaseFolder#getChildrenClass()}.
   *
   * <ul>
   *   <li>Given {@link AbstractDescriptor} {@link AbstractDescriptor#getObjectClass(String, Class)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getChildrenClass()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class DBNDatabaseFolder.getChildrenClass()"})
  public void testGetChildrenClass_givenAbstractDescriptorGetObjectClassReturnNull() {
    // Arrange
    AbstractDescriptor abstractDescriptor = mock(AbstractDescriptor.class);
    when(abstractDescriptor.getObjectClass(Mockito.<String>any(), Mockito.<Class<DBSObject>>any()))
        .thenReturn(null);
    when(dBXTreeFolder.getSource()).thenReturn(abstractDescriptor);
    when(dBXTreeFolder.getType()).thenReturn("Type");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    Class<? extends DBSObject> actualChildrenClass = dBNDatabaseFolder.getChildrenClass();

    // Assert
    verify(abstractDescriptor).getObjectClass(eq("Type"), isA(Class.class));
    verify(dBXTreeFolder).getType();
    verify(dBXTreeFolder).getSource();
    assertNull(actualChildrenClass);
  }

  /**
   * Test {@link DBNDatabaseFolder#getChildrenClass()}.
   *
   * <ul>
   *   <li>Then return {@link DBSObject}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getChildrenClass()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class DBNDatabaseFolder.getChildrenClass()"})
  public void testGetChildrenClass_thenReturnDBSObject() {
    // Arrange
    AbstractDescriptor abstractDescriptor = mock(AbstractDescriptor.class);
    Class<DBSObject> forNameResult = DBSObject.class;
    when(abstractDescriptor.getObjectClass(Mockito.<String>any(), Mockito.<Class<DBSObject>>any()))
        .thenReturn(forNameResult);
    when(dBXTreeFolder.getSource()).thenReturn(abstractDescriptor);
    when(dBXTreeFolder.getType()).thenReturn("Type");
    Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile();

    // Act
    Class<? extends DBSObject> actualChildrenClass = dBNDatabaseFolder.getChildrenClass();

    // Assert
    verify(abstractDescriptor).getObjectClass(eq("Type"), isA(Class.class));
    verify(dBXTreeFolder).getType();
    verify(dBXTreeFolder).getSource();
    Class<DBSObject> expectedChildrenClass = DBSObject.class;
    assertEquals(expectedChildrenClass, actualChildrenClass);
  }

  /**
   * Test {@link DBNDatabaseFolder#getChildrenObjects(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBXTreeFolder} {@link DBXTreeFolder#hasChildren(DBNNode)} return {@code
   *       false}.
   *   <li>Then return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseFolder#getChildrenObjects(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection DBNDatabaseFolder.getChildrenObjects(DBRProgressMonitor)"})
  public void testGetChildrenObjects_givenDBXTreeFolderHasChildrenReturnFalse_thenReturnList()
      throws DBException {
    // Arrange
    when(dBXTreeFolder.hasChildren(Mockito.<DBNNode>any())).thenReturn(false);

    // Act
    Collection<DBSObject> actualChildrenObjects =
        dBNDatabaseFolder.getChildrenObjects(new LoggingProgressMonitor());

    // Assert
    verify(dBXTreeFolder).hasChildren(isA(DBNNode.class));
    assertTrue(actualChildrenObjects instanceof List);
    assertTrue(actualChildrenObjects.isEmpty());
  }
}
