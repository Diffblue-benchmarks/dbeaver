package org.jkiss.dbeaver.model.navigator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.navigator.meta.DBXTreeDescriptor;
import org.jkiss.dbeaver.model.navigator.meta.DBXTreeNode;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBNDatabaseItemDiffblueTest {
  /**
   * Test {@link DBNDatabaseItem#DBNDatabaseItem(DBNNode, DBXTreeNode, DBSObject, boolean)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then ParentNode return {@link DBNEmptyNode}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#DBNDatabaseItem(DBNNode, DBXTreeNode, DBSObject,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNDatabaseItem.<init>(DBNNode, DBXTreeNode, DBSObject, boolean)"})
  public void testNewDBNDatabaseItem_whenNull_thenParentNodeReturnDBNEmptyNode() {
    // Arrange
    DBNEmptyNode parent = new DBNEmptyNode();
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    DBNDatabaseItem actualDbnDatabaseItem = new DBNDatabaseItem(parent, null, object, true);

    // Assert
    DBNNode parentNode = actualDbnDatabaseItem.getParentNode();
    assertTrue(parentNode instanceof DBNEmptyNode);
    DBSObject object2 = actualDbnDatabaseItem.getObject();
    assertTrue(object2 instanceof DBSDocumentConstraint);
    assertNull(actualDbnDatabaseItem.getNodeBriefInfo());
    assertNull(actualDbnDatabaseItem.getNodeDescription());
    assertNull(actualDbnDatabaseItem.getDescription());
    assertNull(actualDbnDatabaseItem.getLastLoadError());
    assertNull(actualDbnDatabaseItem.getDataSource());
    assertNull(actualDbnDatabaseItem.getOwnerProjectOrNull());
    assertNull(actualDbnDatabaseItem.getChildNodes());
    assertNull(actualDbnDatabaseItem.getModel());
    assertNull(actualDbnDatabaseItem.getMeta());
    assertEquals(0, actualDbnDatabaseItem.getDynamicStructChildren().length);
    assertFalse(actualDbnDatabaseItem.isDisposed());
    assertFalse(actualDbnDatabaseItem.isDynamicStructObject());
    assertFalse(actualDbnDatabaseItem.isPersisted());
    assertFalse(actualDbnDatabaseItem.hasDynamicStructChildren());
    assertFalse(actualDbnDatabaseItem.isFiltered());
    assertFalse(actualDbnDatabaseItem.isLocked());
    assertTrue(actualDbnDatabaseItem.isManageable());
    assertSame(parent, parentNode);
    assertSame(object, object2);
    assertSame(object, actualDbnDatabaseItem.getValueObject());
  }

  /**
   * Test {@link DBNDatabaseItem#isDisposed()}.
   *
   * <ul>
   *   <li>Given {@link DBXTreeNode} {@link DBXTreeNode#addChild(DBXTreeNode)} does nothing.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#isDisposed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNDatabaseItem.isDisposed()"})
  public void testIsDisposed_givenDBXTreeNodeAddChildDoesNothing_thenReturnFalse()
      throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);
    DBNEmptyNode parent2 = new DBNEmptyNode();
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(parent2, meta, object, true);

    // Act
    boolean actualIsDisposedResult = dbnDatabaseItem.isDisposed();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(parent).addChild(isA(DBXTreeNode.class));
    assertFalse(actualIsDisposedResult);
  }

  /**
   * Test {@link DBNDatabaseItem#isDisposed()}.
   *
   * <ul>
   *   <li>Given {@link DBXTreeNode} {@link DBXTreeNode#addChild(DBXTreeNode)} does nothing.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#isDisposed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNDatabaseItem.isDisposed()"})
  public void testIsDisposed_givenDBXTreeNodeAddChildDoesNothing_thenReturnTrue()
      throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);
    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(new DBNEmptyNode(), meta, null, true);

    // Act
    boolean actualIsDisposedResult = dbnDatabaseItem.isDisposed();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(parent).addChild(isA(DBXTreeNode.class));
    assertTrue(actualIsDisposedResult);
  }

  /**
   * Test {@link DBNDatabaseItem#dispose(boolean)}.
   *
   * <p>Method under test: {@link DBNDatabaseItem#dispose(boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBNDatabaseItem.dispose(boolean)"})
  public void testDispose() throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);
    DBNEmptyNode parent2 = new DBNEmptyNode();
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(parent2, meta, object, true);

    // Act
    dbnDatabaseItem.dispose(true);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(parent).addChild(isA(DBXTreeNode.class));
    assertEquals("", dbnDatabaseItem.getNodeType());
    assertEquals("", dbnDatabaseItem.getNodeTypeLabel());
    assertEquals("#empty.[NULL]", dbnDatabaseItem.getNodeFullName());
    assertEquals("[NULL]", dbnDatabaseItem.getNodeDisplayName());
    assertEquals("[NULL]", dbnDatabaseItem.getNodeId());
    assertEquals("[NULL]", dbnDatabaseItem.getNodeItemPath());
    assertEquals("[NULL]", dbnDatabaseItem.getName());
    assertEquals("[NULL]", dbnDatabaseItem.getNodeTargetName());
    assertEquals("node://#empty/[NULL]", dbnDatabaseItem.getNodeUri());
    assertNull(dbnDatabaseItem.getValueObject());
    assertNull(dbnDatabaseItem.getObject());
    assertTrue(dbnDatabaseItem.isDisposed());
  }

  /**
   * Test {@link DBNDatabaseItem#isDynamicStructObject()}.
   *
   * <ul>
   *   <li>Given {@link DBXTreeNode} {@link DBXTreeNode#addChild(DBXTreeNode)} does nothing.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#isDynamicStructObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNDatabaseItem.isDynamicStructObject()"})
  public void testIsDynamicStructObject_givenDBXTreeNodeAddChildDoesNothing_thenReturnFalse()
      throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);
    DBNEmptyNode parent2 = new DBNEmptyNode();
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(parent2, meta, object, true);

    // Act
    boolean actualIsDynamicStructObjectResult = dbnDatabaseItem.isDynamicStructObject();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(parent).addChild(isA(DBXTreeNode.class));
    assertFalse(actualIsDynamicStructObjectResult);
  }

  /**
   * Test {@link DBNDatabaseItem#reloadObject(DBRProgressMonitor, DBSObject)}.
   *
   * <ul>
   *   <li>Given {@link DBXTreeNode} {@link DBXTreeNode#addChild(DBXTreeNode)} does nothing.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#reloadObject(DBRProgressMonitor, DBSObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNDatabaseItem.reloadObject(DBRProgressMonitor, DBSObject)"})
  public void testReloadObject_givenDBXTreeNodeAddChildDoesNothing_thenReturnTrue()
      throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);
    DBNEmptyNode parent2 = new DBNEmptyNode();
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(parent2, meta, object, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    boolean actualReloadObjectResult =
        dbnDatabaseItem.reloadObject(
            monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(parent).addChild(isA(DBXTreeNode.class));
    assertTrue(actualReloadObjectResult);
  }

  /**
   * Test {@link DBNDatabaseItem#isPersisted()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#isPersisted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNDatabaseItem.isPersisted()"})
  public void testIsPersisted_givenDBVContainerWithParentIsDBVContainerAndName_thenReturnTrue()
      throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);
    DBNEmptyNode parent2 = new DBNEmptyNode();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBNDatabaseItem dbnDatabaseItem =
        new DBNDatabaseItem(parent2, meta, new DBVEntityForeignKey(entity), true);

    // Act
    boolean actualIsPersistedResult = dbnDatabaseItem.isPersisted();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(parent).addChild(isA(DBXTreeNode.class));
    assertTrue(actualIsPersistedResult);
  }

  /**
   * Test {@link DBNDatabaseItem#isPersisted()}.
   *
   * <ul>
   *   <li>Given {@link DBXTreeNode} {@link DBXTreeNode#addChild(DBXTreeNode)} does nothing.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#isPersisted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNDatabaseItem.isPersisted()"})
  public void testIsPersisted_givenDBXTreeNodeAddChildDoesNothing_thenReturnFalse()
      throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);
    DBNEmptyNode parent2 = new DBNEmptyNode();
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(parent2, meta, object, true);

    // Act
    boolean actualIsPersistedResult = dbnDatabaseItem.isPersisted();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(parent).addChild(isA(DBXTreeNode.class));
    assertFalse(actualIsPersistedResult);
  }

  /**
   * Test {@link DBNDatabaseItem#isPersisted()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#isPersisted()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBNDatabaseItem.isPersisted()"})
  public void testIsPersisted_thenReturnFalse() throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);
    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(new DBNEmptyNode(), meta, null, true);

    // Act
    boolean actualIsPersistedResult = dbnDatabaseItem.isPersisted();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(parent).addChild(isA(DBXTreeNode.class));
    assertFalse(actualIsPersistedResult);
  }

  /**
   * Test {@link DBNDatabaseItem#toString()}.
   *
   * <ul>
   *   <li>Given {@link DBSDocumentConstraint#DBSDocumentConstraint(DBSDocumentContainer)} with
   *       entity is {@link DBSDocumentContainer}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseItem.toString()"})
  public void testToString_givenDBSDocumentConstraintWithEntityIsDBSDocumentContainer()
      throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);
    DBNEmptyNode parent2 = new DBNEmptyNode();
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(parent2, meta, object, true);

    // Act
    dbnDatabaseItem.toString();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(parent).addChild(isA(DBXTreeNode.class));
  }

  /**
   * Test {@link DBNDatabaseItem#toString()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and name is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseItem.toString()"})
  public void testToString_givenDBVContainerWithParentIsDBVContainerAndNameIsEmptyString()
      throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent2, "");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey object = new DBVEntityForeignKey(entity);

    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(new DBNEmptyNode(), meta, object, true);

    // Act
    String actualToStringResult = dbnDatabaseItem.toString();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(parent).addChild(isA(DBXTreeNode.class));
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(parent2).getDataSource();
    assertEquals("VFK: 42->null.null ([])", actualToStringResult);
  }

  /**
   * Test {@link DBNDatabaseItem#toString()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and name is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseItem.toString()"})
  public void testToString_givenDBVContainerWithParentIsDBVContainerAndNameIsNull()
      throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent2, null);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey object = new DBVEntityForeignKey(entity);

    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(new DBNEmptyNode(), meta, object, true);

    // Act
    String actualToStringResult = dbnDatabaseItem.toString();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(parent).addChild(isA(DBXTreeNode.class));
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(parent2).getDataSource();
    assertEquals("VFK: 42->null.null ([])", actualToStringResult);
  }

  /**
   * Test {@link DBNDatabaseItem#toString()}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect} {@link SQLDialect#getStructSeparator()} return {@code A}.
   *   <li>Then return {@code VFK: 42A42->null.null ([])}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseItem.toString()"})
  public void testToString_givenSQLDialectGetStructSeparatorReturnA_thenReturnVfk42a42NullNull()
      throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);

    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getStructSeparator()).thenReturn('A');
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey object = new DBVEntityForeignKey(entity);

    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(new DBNEmptyNode(), meta, object, true);

    // Act
    String actualToStringResult = dbnDatabaseItem.toString();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(parent).addChild(isA(DBXTreeNode.class));
    verify(sqlDialect, atLeast(1)).getQuotedIdentifier("Name", true, false);
    verify(sqlDialect).getStructSeparator();
    verify(parent2).getDataSource();
    assertEquals("VFK: 42A42->null.null ([])", actualToStringResult);
  }

  /**
   * Test {@link DBNDatabaseItem#toString()}.
   *
   * <ul>
   *   <li>Then return {@code node://#empty/[NULL]}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseItem.toString()"})
  public void testToString_thenReturnNodeEmptyNull() throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);
    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(new DBNEmptyNode(), meta, null, true);

    // Act
    String actualToStringResult = dbnDatabaseItem.toString();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(parent).addChild(isA(DBXTreeNode.class));
    assertEquals("node://#empty/[NULL]", actualToStringResult);
  }

  /**
   * Test {@link DBNDatabaseItem#toString()}.
   *
   * <ul>
   *   <li>Then return {@code VFK: "Name"."Name"->null.null ([])}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseItem.toString()"})
  public void testToString_thenReturnVfkNameNameNullNull() throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent2 = mock(DBVContainer.class);
    when(parent2.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent2, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey object = new DBVEntityForeignKey(entity);

    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(new DBNEmptyNode(), meta, object, true);

    // Act
    String actualToStringResult = dbnDatabaseItem.toString();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(parent).addChild(isA(DBXTreeNode.class));
    verify(parent2).getDataSource();
    assertEquals("VFK: \"Name\".\"Name\"->null.null ([])", actualToStringResult);
  }

  /**
   * Test {@link DBNDatabaseItem#toString()}.
   *
   * <ul>
   *   <li>Then return {@code VFK: "Name"->null.null ([])}.
   * </ul>
   *
   * <p>Method under test: {@link DBNDatabaseItem#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBNDatabaseItem.toString()"})
  public void testToString_thenReturnVfkNameNullNull() throws InvalidRegistryObjectException {
    // Arrange
    DBXTreeNode parent = mock(DBXTreeNode.class);
    doNothing().when(parent).addChild(Mockito.<DBXTreeNode>any());

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    DBXTreeDescriptor item =
        new DBXTreeDescriptor(
            mock(AbstractDescriptor.class),
            parent,
            config,
            "Path",
            "Property Name",
            true,
            true,
            true,
            true,
            true,
            "Visible If",
            "Recursive Link");
    DBXTreeDescriptor meta = new DBXTreeDescriptor(null, item);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey object = new DBVEntityForeignKey(entity);

    DBNDatabaseItem dbnDatabaseItem = new DBNDatabaseItem(new DBNEmptyNode(), meta, object, true);

    // Act
    String actualToStringResult = dbnDatabaseItem.toString();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(dbpDataSource, atLeast(1)).getSQLDialect();
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).addChild(isA(DBXTreeNode.class));
    assertEquals("VFK: \"Name\"->null.null ([])", actualToStringResult);
  }
}
