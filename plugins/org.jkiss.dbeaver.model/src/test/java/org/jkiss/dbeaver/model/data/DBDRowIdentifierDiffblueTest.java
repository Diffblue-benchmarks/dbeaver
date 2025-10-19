package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraint;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVEntityConstraint;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBDRowIdentifierDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBDRowIdentifier#DBDRowIdentifier(DBSEntity, DBSEntityConstraint)}
   *   <li>{@link DBDRowIdentifier#toString()}
   *   <li>{@link DBDRowIdentifier#getAttributes()}
   *   <li>{@link DBDRowIdentifier#getEntity()}
   *   <li>{@link DBDRowIdentifier#getUniqueKey()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDRowIdentifier.<init>(DBSEntity, DBSEntityConstraint)",
    "List DBDRowIdentifier.getAttributes()",
    "DBSEntity DBDRowIdentifier.getEntity()",
    "DBSEntityConstraint DBDRowIdentifier.getUniqueKey()",
    "String DBDRowIdentifier.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBSDocumentConstraint entityIdentifier =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    DBDRowIdentifier actualDbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);
    String actualToStringResult = actualDbdRowIdentifier.toString();
    List<DBDAttributeBinding> actualAttributes = actualDbdRowIdentifier.getAttributes();
    DBSEntity actualEntity = actualDbdRowIdentifier.getEntity();
    DBSEntityConstraint actualUniqueKey = actualDbdRowIdentifier.getUniqueKey();

    // Assert
    assertEquals("Name.DocumentKey()", actualToStringResult);
    assertTrue(actualAttributes.isEmpty());
    assertSame(entityIdentifier, actualUniqueKey);
    assertSame(entity, actualEntity);
  }

  /**
   * Test {@link DBDRowIdentifier#getKeyType()}.
   *
   * <ul>
   *   <li>Then return {@code PRIMARY KEY}.
   * </ul>
   *
   * <p>Method under test: {@link DBDRowIdentifier#getKeyType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBDRowIdentifier.getKeyType()"})
  public void testGetKeyType_thenReturnPrimaryKey() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBSDocumentConstraint entityIdentifier =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);

    // Act and Assert
    assertEquals("PRIMARY KEY", dbdRowIdentifier.getKeyType());
  }

  /**
   * Test {@link DBDRowIdentifier#isIncomplete()}.
   *
   * <p>Method under test: {@link DBDRowIdentifier#isIncomplete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDRowIdentifier.isIncomplete()"})
  public void testIsIncomplete() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBSDocumentConstraint entityIdentifier =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);

    // Act and Assert
    assertTrue(dbdRowIdentifier.isIncomplete());
  }

  /**
   * Test {@link DBDRowIdentifier#isValidIdentifier()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDRowIdentifier#isValidIdentifier()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDRowIdentifier.isValidIdentifier()"})
  public void testIsValidIdentifier_thenReturnFalse() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBSDocumentConstraint entityIdentifier =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);

    // Act and Assert
    assertFalse(dbdRowIdentifier.isValidIdentifier());
  }

  /**
   * Test {@link DBDRowIdentifier#isValidIdentifier()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DBDRowIdentifier#isValidIdentifier()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDRowIdentifier.isValidIdentifier()"})
  public void testIsValidIdentifier_thenReturnTrue() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, null);

    // Act and Assert
    assertTrue(dbdRowIdentifier.isValidIdentifier());
  }

  /**
   * Test {@link DBDRowIdentifier#hasAttribute(DBDAttributeBinding)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link DBDRowIdentifier#hasAttribute(DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBDRowIdentifier.hasAttribute(DBDAttributeBinding)"})
  public void testHasAttribute_whenNull_thenReturnFalse() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBSDocumentConstraint entityIdentifier =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);

    // Act and Assert
    assertFalse(dbdRowIdentifier.hasAttribute(null));
  }

  /**
   * Test {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])}.
   *
   * <p>Method under test: {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor,
   * DBDAttributeBinding[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDRowIdentifier.reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])"
  })
  public void testReloadAttributes() throws DBException {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint entityIdentifier = new DBVEntityConstraint(entity2, copy);

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, entityIdentifier);

    // Act
    dbdRowIdentifier.reloadAttributes(
        new LoggingProgressMonitor(), new DBDAttributeBinding[] {null});

    // Assert that nothing has changed
    assertFalse(dbdRowIdentifier.isValidIdentifier());
    assertTrue(dbdRowIdentifier.getAttributes().isEmpty());
    assertTrue(dbdRowIdentifier.isIncomplete());
  }

  /**
   * Test {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])}.
   *
   * <p>Method under test: {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor,
   * DBDAttributeBinding[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDRowIdentifier.reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])"
  })
  public void testReloadAttributes2() throws DBException {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity, null);

    // Act
    dbdRowIdentifier.reloadAttributes(
        new LoggingProgressMonitor(), new DBDAttributeBinding[] {null});

    // Assert that nothing has changed
    assertTrue(dbdRowIdentifier.getAttributes().isEmpty());
    assertTrue(dbdRowIdentifier.isIncomplete());
    assertTrue(dbdRowIdentifier.isValidIdentifier());
  }

  /**
   * Test {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])}.
   *
   * <p>Method under test: {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor,
   * DBDAttributeBinding[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDRowIdentifier.reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])"
  })
  public void testReloadAttributes3() throws DBException {
    // Arrange
    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbvEntityAttribute);
    DBSDocumentConstraint entityIdentifier = new DBSDocumentConstraint(entity);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container2, "Name", "Description Column Names");

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity3, entityIdentifier);

    // Act
    dbdRowIdentifier.reloadAttributes(new LoggingProgressMonitor(), new DBDAttributeBinding[] {});

    // Assert that nothing has changed
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
    assertFalse(dbdRowIdentifier.isValidIdentifier());
    assertTrue(dbdRowIdentifier.getAttributes().isEmpty());
    assertTrue(dbdRowIdentifier.isIncomplete());
  }

  /**
   * Test {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])}.
   *
   * <p>Method under test: {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor,
   * DBDAttributeBinding[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDRowIdentifier.reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])"
  })
  public void testReloadAttributes4() throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint entityIdentifier = new DBVEntityConstraint(entity, copy);
    entityIdentifier.addAttribute("Name");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity3, entityIdentifier);

    // Act
    dbdRowIdentifier.reloadAttributes(
        new LoggingProgressMonitor(), new DBDAttributeBinding[] {null});

    // Assert that nothing has changed
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertFalse(dbdRowIdentifier.isValidIdentifier());
    assertTrue(dbdRowIdentifier.getAttributes().isEmpty());
    assertTrue(dbdRowIdentifier.isIncomplete());
  }

  /**
   * Test {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])}.
   *
   * <p>Method under test: {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor,
   * DBDAttributeBinding[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDRowIdentifier.reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])"
  })
  public void testReloadAttributes5() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint entityIdentifier = new DBVEntityConstraint(entity, copy);
    entityIdentifier.setUseAllColumns(true);
    entityIdentifier.addAttribute("Name");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity4, entityIdentifier);

    // Act
    dbdRowIdentifier.reloadAttributes(
        new LoggingProgressMonitor(), new DBDAttributeBinding[] {null});

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    List<DBDAttributeBinding> attributes = dbdRowIdentifier.getAttributes();
    assertEquals(1, attributes.size());
    assertNull(attributes.get(0));
    assertFalse(dbdRowIdentifier.isIncomplete());
    assertTrue(dbdRowIdentifier.isValidIdentifier());
  }

  /**
   * Test {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])}.
   *
   * <p>Method under test: {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor,
   * DBDAttributeBinding[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDRowIdentifier.reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])"
  })
  public void testReloadAttributes6() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity2.addVirtualAttribute(attribute);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint entityIdentifier = new DBVEntityConstraint(entity2, copy);
    entityIdentifier.addAttribute("org.jkiss.dbeaver.model");
    entityIdentifier.addAttribute("Name");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity4, entityIdentifier);

    // Act
    dbdRowIdentifier.reloadAttributes(
        new LoggingProgressMonitor(), new DBDAttributeBinding[] {null});

    // Assert that nothing has changed
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertFalse(dbdRowIdentifier.isValidIdentifier());
    assertTrue(dbdRowIdentifier.getAttributes().isEmpty());
    assertTrue(dbdRowIdentifier.isIncomplete());
  }

  /**
   * Test {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])}.
   *
   * <ul>
   *   <li>Given {@link DBSDocumentContainer} {@link
   *       DBSDocumentContainer#getDocumentAttribute(DBRProgressMonitor)} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor,
   * DBDAttributeBinding[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDRowIdentifier.reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])"
  })
  public void testReloadAttributes_givenDBSDocumentContainerGetDocumentAttributeReturnNull()
      throws DBException {
    // Arrange
    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDocumentAttribute(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBSDocumentConstraint entityIdentifier = new DBSDocumentConstraint(entity);
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity2, entityIdentifier);

    // Act
    dbdRowIdentifier.reloadAttributes(
        new LoggingProgressMonitor(), new DBDAttributeBinding[] {null});

    // Assert that nothing has changed
    verify(entity).getDocumentAttribute(isA(DBRProgressMonitor.class));
    assertFalse(dbdRowIdentifier.isValidIdentifier());
    assertTrue(dbdRowIdentifier.getAttributes().isEmpty());
    assertTrue(dbdRowIdentifier.isIncomplete());
  }

  /**
   * Test {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer} {@link DBVContainer#getRealContainer(DBRProgressMonitor)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor,
   * DBDAttributeBinding[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDRowIdentifier.reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])"
  })
  public void testReloadAttributes_givenDBVContainerGetRealContainerReturnNull()
      throws DBException {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(null);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity2, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint entityIdentifier = new DBVEntityConstraint(entity, copy);
    entityIdentifier.addAttribute("Name");
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity3, entityIdentifier);

    // Act
    dbdRowIdentifier.reloadAttributes(
        new LoggingProgressMonitor(), new DBDAttributeBinding[] {null});

    // Assert that nothing has changed
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertFalse(dbdRowIdentifier.isValidIdentifier());
    assertTrue(dbdRowIdentifier.getAttributes().isEmpty());
    assertTrue(dbdRowIdentifier.isIncomplete());
  }

  /**
   * Test {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor,
   * DBDAttributeBinding[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDRowIdentifier.reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])"
  })
  public void testReloadAttributes_thenCallsGetContainer() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer container = new DBVContainer(parent, "Name");

    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint entityIdentifier = new DBVEntityConstraint(entity, copy);
    entityIdentifier.addAttribute("Name");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity4, entityIdentifier);

    // Act
    dbdRowIdentifier.reloadAttributes(
        new LoggingProgressMonitor(), new DBDAttributeBinding[] {null});

    // Assert that nothing has changed
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertFalse(dbdRowIdentifier.isValidIdentifier());
    assertTrue(dbdRowIdentifier.getAttributes().isEmpty());
    assertTrue(dbdRowIdentifier.isIncomplete());
  }

  /**
   * Test {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])}.
   *
   * <ul>
   *   <li>When empty array of {@link DBDAttributeBinding}.
   * </ul>
   *
   * <p>Method under test: {@link DBDRowIdentifier#reloadAttributes(DBRProgressMonitor,
   * DBDAttributeBinding[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBDRowIdentifier.reloadAttributes(DBRProgressMonitor, DBDAttributeBinding[])"
  })
  public void testReloadAttributes_whenEmptyArrayOfDBDAttributeBinding() throws DBException {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel dbvModel = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    when(parent.getRealContainer(Mockito.<DBRProgressMonitor>any())).thenReturn(dbvModel);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");
    attribute.setCustom(true);

    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    entity2.addVirtualAttribute(attribute);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityConstraint copy =
        new DBVEntityConstraint(entity3, DBSEntityConstraintType.ASSOCIATION, "Name");

    DBVEntityConstraint entityIdentifier = new DBVEntityConstraint(entity2, copy);
    entityIdentifier.addAttribute("Name");
    DBVContainer container4 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity4 = new DBVEntity(container4, "Name", "Description Column Names");

    DBDRowIdentifier dbdRowIdentifier = new DBDRowIdentifier(entity4, entityIdentifier);

    // Act
    dbdRowIdentifier.reloadAttributes(new LoggingProgressMonitor(), new DBDAttributeBinding[] {});

    // Assert that nothing has changed
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(parent).getDataSource();
    verify(parent).getRealContainer(isA(DBRProgressMonitor.class));
    assertFalse(dbdRowIdentifier.isValidIdentifier());
    assertTrue(dbdRowIdentifier.getAttributes().isEmpty());
    assertTrue(dbdRowIdentifier.isIncomplete());
  }
}
