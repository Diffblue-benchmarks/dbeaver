package org.jkiss.dbeaver.model.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPAttributeReferencePurpose;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.data.DBDAttributeBinding;
import org.jkiss.dbeaver.model.data.DBDAttributeBindingCustom;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.exec.DBCStatement;
import org.jkiss.dbeaver.model.impl.local.LocalResultSet;
import org.jkiss.dbeaver.model.impl.local.LocalResultSetColumn;
import org.jkiss.dbeaver.model.impl.local.LocalStatement;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityAttribute;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBStructUtilsDiffblueTest {
  /**
   * Test {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)}
   * with {@code monitor}, {@code attribute}.
   *
   * <p>Method under test: {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor,
   * DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityReferrer DBStructUtils.getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)"
  })
  public void testGetEnumerableConstraintWithMonitorAttribute() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, null, "Name");
    when(attribute.getEntityAttribute()).thenReturn(dbvEntityAttribute);

    // Act
    DBSEntityReferrer actualEnumerableConstraint =
        DBStructUtils.getEnumerableConstraint(monitor, attribute);

    // Assert
    verify(attribute).getEntityAttribute();
    assertNull(actualEnumerableConstraint);
  }

  /**
   * Test {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)}
   * with {@code monitor}, {@code attribute}.
   *
   * <p>Method under test: {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor,
   * DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityReferrer DBStructUtils.getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)"
  })
  public void testGetEnumerableConstraintWithMonitorAttribute2() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVEntity entity = mock(DBVEntity.class);
    when(entity.getAssociations(Mockito.<DBRProgressMonitor>any())).thenReturn(new ArrayList<>());
    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, null, "Name");

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getEntityAttribute()).thenReturn(dbvEntityAttribute);

    // Act
    DBSEntityReferrer actualEnumerableConstraint =
        DBStructUtils.getEnumerableConstraint(monitor, attribute);

    // Assert
    verify(attribute).getEntityAttribute();
    verify(entity).getAssociations(isA(DBRProgressMonitor.class));
    assertNull(actualEnumerableConstraint);
  }

  /**
   * Test {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)}
   * with {@code monitor}, {@code attribute}.
   *
   * <p>Method under test: {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor,
   * DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityReferrer DBStructUtils.getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)"
  })
  public void testGetEnumerableConstraintWithMonitorAttribute3() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVEntity entity = mock(DBVEntity.class);
    when(entity.getAssociations(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));
    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, null, "Name");

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getEntityAttribute()).thenReturn(dbvEntityAttribute);

    // Act and Assert
    assertThrows(
        DBException.class, () -> DBStructUtils.getEnumerableConstraint(monitor, attribute));
    verify(attribute).getEntityAttribute();
    verify(entity).getAssociations(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)}
   * with {@code monitor}, {@code attribute}.
   *
   * <p>Method under test: {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor,
   * DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityReferrer DBStructUtils.getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)"
  })
  public void testGetEnumerableConstraintWithMonitorAttribute4() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<DBVEntityForeignKey> dbvEntityForeignKeyList = new ArrayList<>();
    DBVEntity entity =
        new DBVEntity(new DBVContainer(null, "Name"), "Name", "Description Column Names");
    dbvEntityForeignKeyList.add(new DBVEntityForeignKey(entity));

    DBVEntity entity2 = mock(DBVEntity.class);
    when(entity2.getAssociations(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbvEntityForeignKeyList);
    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity2, null, "Name");

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getEntityAttribute()).thenReturn(dbvEntityAttribute);

    // Act
    DBSEntityReferrer actualEnumerableConstraint =
        DBStructUtils.getEnumerableConstraint(monitor, attribute);

    // Assert
    verify(attribute).getEntityAttribute();
    verify(entity2).getAssociations(isA(DBRProgressMonitor.class));
    assertNull(actualEnumerableConstraint);
  }

  /**
   * Test {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)}
   * with {@code monitor}, {@code attribute}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor,
   * DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityReferrer DBStructUtils.getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)"
  })
  public void testGetEnumerableConstraintWithMonitorAttribute_givenArrayListAddNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<DBVEntityForeignKey> dbvEntityForeignKeyList = new ArrayList<>();
    dbvEntityForeignKeyList.add(null);

    DBVEntity entity = mock(DBVEntity.class);
    when(entity.getAssociations(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbvEntityForeignKeyList);
    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, null, "Name");

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getEntityAttribute()).thenReturn(dbvEntityAttribute);

    // Act
    DBSEntityReferrer actualEnumerableConstraint =
        DBStructUtils.getEnumerableConstraint(monitor, attribute);

    // Assert
    verify(attribute).getEntityAttribute();
    verify(entity).getAssociations(isA(DBRProgressMonitor.class));
    assertNull(actualEnumerableConstraint);
  }

  /**
   * Test {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)}
   * with {@code monitor}, {@code attribute}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor,
   * DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityReferrer DBStructUtils.getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)"
  })
  public void testGetEnumerableConstraintWithMonitorAttribute_givenNull() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getEntityAttribute()).thenReturn(null);

    // Act
    DBSEntityReferrer actualEnumerableConstraint =
        DBStructUtils.getEnumerableConstraint(monitor, attribute);

    // Assert
    verify(attribute).getEntityAttribute();
    assertNull(actualEnumerableConstraint);
  }

  /**
   * Test {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)}
   * with {@code monitor}, {@code attribute}.
   *
   * <ul>
   *   <li>Then calls {@link DBVEntityForeignKey#getAttributeReferences(DBRProgressMonitor)}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor,
   * DBDAttributeBinding)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityReferrer DBStructUtils.getEnumerableConstraint(DBRProgressMonitor, DBDAttributeBinding)"
  })
  public void testGetEnumerableConstraintWithMonitorAttribute_thenCallsGetAttributeReferences()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBVEntityForeignKey dbvEntityForeignKey = mock(DBVEntityForeignKey.class);
    when(dbvEntityForeignKey.getAttributeReferences(Mockito.<DBRProgressMonitor>any()))
        .thenThrow(new DBException("An error occurred"));

    ArrayList<DBVEntityForeignKey> dbvEntityForeignKeyList = new ArrayList<>();
    dbvEntityForeignKeyList.add(dbvEntityForeignKey);

    DBVEntity entity = mock(DBVEntity.class);
    when(entity.getAssociations(Mockito.<DBRProgressMonitor>any()))
        .thenReturn(dbvEntityForeignKeyList);
    DBVEntityAttribute dbvEntityAttribute = new DBVEntityAttribute(entity, null, "Name");

    DBDAttributeBindingCustom attribute = mock(DBDAttributeBindingCustom.class);
    when(attribute.getEntityAttribute()).thenReturn(dbvEntityAttribute);

    // Act and Assert
    assertThrows(
        DBException.class, () -> DBStructUtils.getEnumerableConstraint(monitor, attribute));
    verify(attribute).getEntityAttribute();
    verify(entity).getAssociations(isA(DBRProgressMonitor.class));
    verify(dbvEntityForeignKey).getAttributeReferences(isA(DBRProgressMonitor.class));
  }

  /**
   * Test {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor, DBSEntityAttribute)} with
   * {@code monitor}, {@code entityAttribute}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getEnumerableConstraint(DBRProgressMonitor,
   * DBSEntityAttribute)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityReferrer DBStructUtils.getEnumerableConstraint(DBRProgressMonitor, DBSEntityAttribute)"
  })
  public void testGetEnumerableConstraintWithMonitorEntityAttribute_thenReturnNull()
      throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute entityAttribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    DBSEntityReferrer actualEnumerableConstraint =
        DBStructUtils.getEnumerableConstraint(monitor, entityAttribute);

    // Assert
    assertNull(actualEnumerableConstraint);
  }

  /**
   * Test {@link DBStructUtils#getAssociatedEntity(DBRProgressMonitor, DBSEntityConstraint)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getAssociatedEntity(DBRProgressMonitor,
   * DBSEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntity DBStructUtils.getAssociatedEntity(DBRProgressMonitor, DBSEntityConstraint)"
  })
  public void testGetAssociatedEntity_thenReturnNull() throws DBException {
    // Arrange
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    DBSEntity actualAssociatedEntity =
        DBStructUtils.getAssociatedEntity(
            monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    assertNull(actualAssociatedEntity);
  }

  /**
   * Test {@link DBStructUtils#generateTableListDDL(DBRProgressMonitor, StringBuilder, Collection,
   * Map, boolean)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then calls {@link DBRProgressMonitor#beginTask(String, int)}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#generateTableListDDL(DBRProgressMonitor,
   * StringBuilder, Collection, Map, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBStructUtils.generateTableListDDL(DBRProgressMonitor, StringBuilder, Collection, Map, boolean)"
  })
  public void testGenerateTableListDDL_givenFalse_thenCallsBeginTask() throws DBException {
    // Arrange
    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(false);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();
    StringBuilder sql = new StringBuilder("foo");
    ArrayList<DBSEntity> tablesOrViews = new ArrayList<>();

    // Act
    DBStructUtils.generateTableListDDL(monitor, sql, tablesOrViews, new HashMap<>(), true);

    // Assert
    verify(monitor).beginTask("Sorting table list", 0);
    verify(monitor, atLeast(1)).done();
    verify(monitor).isCanceled();
  }

  /**
   * Test {@link DBStructUtils#generateTableListDDL(DBRProgressMonitor, StringBuilder, Collection,
   * Map, boolean)}.
   *
   * <ul>
   *   <li>Given {@code true}.
   *   <li>When {@link DBRProgressMonitor} {@link DBRProgressMonitor#isCanceled()} return {@code
   *       true}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#generateTableListDDL(DBRProgressMonitor,
   * StringBuilder, Collection, Map, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBStructUtils.generateTableListDDL(DBRProgressMonitor, StringBuilder, Collection, Map, boolean)"
  })
  public void testGenerateTableListDDL_givenTrue_whenDBRProgressMonitorIsCanceledReturnTrue()
      throws DBException {
    // Arrange
    DBRProgressMonitor monitor = mock(DBRProgressMonitor.class);
    when(monitor.isCanceled()).thenReturn(true);
    doNothing().when(monitor).beginTask(Mockito.<String>any(), anyInt());
    doNothing().when(monitor).done();
    StringBuilder sql = new StringBuilder("foo");
    ArrayList<DBSEntity> tablesOrViews = new ArrayList<>();

    // Act
    DBStructUtils.generateTableListDDL(monitor, sql, tablesOrViews, new HashMap<>(), true);

    // Assert
    verify(monitor).beginTask("Sorting table list", 0);
    verify(monitor, atLeast(1)).done();
    verify(monitor).isCanceled();
  }

  /**
   * Test {@link DBStructUtils#getAttributeName(DBSAttributeBase, DBPAttributeReferencePurpose)}
   * with {@code attribute}, {@code purpose}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getAttributeName(DBSAttributeBase,
   * DBPAttributeReferencePurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBStructUtils.getAttributeName(DBSAttributeBase, DBPAttributeReferencePurpose)"
  })
  public void testGetAttributeNameWithAttributePurpose_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    String actualAttributeName =
        DBStructUtils.getAttributeName(attribute, DBPAttributeReferencePurpose.UPDATE_TARGET);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals("\"Name\"", actualAttributeName);
  }

  /**
   * Test {@link DBStructUtils#getAttributeName(DBSAttributeBase, DBPAttributeReferencePurpose)}
   * with {@code attribute}, {@code purpose}.
   *
   * <ul>
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getAttributeName(DBSAttributeBase,
   * DBPAttributeReferencePurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBStructUtils.getAttributeName(DBSAttributeBase, DBPAttributeReferencePurpose)"
  })
  public void testGetAttributeNameWithAttributePurpose_thenReturn42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    String actualAttributeName =
        DBStructUtils.getAttributeName(attribute, DBPAttributeReferencePurpose.UPDATE_TARGET);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("42", actualAttributeName);
  }

  /**
   * Test {@link DBStructUtils#getAttributeName(DBSAttributeBase, DBPAttributeReferencePurpose)}
   * with {@code attribute}, {@code purpose}.
   *
   * <ul>
   *   <li>Then return {@code Label}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getAttributeName(DBSAttributeBase,
   * DBPAttributeReferencePurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBStructUtils.getAttributeName(DBSAttributeBase, DBPAttributeReferencePurpose)"
  })
  public void testGetAttributeNameWithAttributePurpose_thenReturnLabel() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertEquals(
        "Label",
        DBStructUtils.getAttributeName(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN),
            DBPAttributeReferencePurpose.UPDATE_TARGET));
  }

  /**
   * Test {@link DBStructUtils#getAttributeName(DBSAttributeBase, DBPAttributeReferencePurpose)}
   * with {@code attribute}, {@code purpose}.
   *
   * <ul>
   *   <li>Then return {@code "Name"}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getAttributeName(DBSAttributeBase,
   * DBPAttributeReferencePurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBStructUtils.getAttributeName(DBSAttributeBase, DBPAttributeReferencePurpose)"
  })
  public void testGetAttributeNameWithAttributePurpose_thenReturnName() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    String actualAttributeName =
        DBStructUtils.getAttributeName(attribute, DBPAttributeReferencePurpose.UPDATE_TARGET);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("\"Name\"", actualAttributeName);
  }

  /**
   * Test {@link DBStructUtils#getAttributeName(DBSAttributeBase, DBPAttributeReferencePurpose)}
   * with {@code attribute}, {@code purpose}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getAttributeName(DBSAttributeBase,
   * DBPAttributeReferencePurpose)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBStructUtils.getAttributeName(DBSAttributeBase, DBPAttributeReferencePurpose)"
  })
  public void testGetAttributeNameWithAttributePurpose_thenReturnName2() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    String actualAttributeName =
        DBStructUtils.getAttributeName(attribute2, DBPAttributeReferencePurpose.UPDATE_TARGET);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("Name", actualAttributeName);
  }

  /**
   * Test {@link DBStructUtils#getAttributeName(DBSAttributeBase)} with {@code attribute}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getAttributeName(DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBStructUtils.getAttributeName(DBSAttributeBase)"})
  public void testGetAttributeNameWithAttribute_thenCallsGetDataSource() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(dbpDataSource);
    DBVModel container = new DBVModel(dataSourceContainer);

    DBPDataSourceContainer dataSourceContainer2 = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer2.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer2);
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    String actualAttributeName = DBStructUtils.getAttributeName(attribute);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(dataSourceContainer, atLeast(1)).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer2, atLeast(1)).getId();
    assertEquals("\"Name\"", actualAttributeName);
  }

  /**
   * Test {@link DBStructUtils#getAttributeName(DBSAttributeBase)} with {@code attribute}.
   *
   * <ul>
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getAttributeName(DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBStructUtils.getAttributeName(DBSAttributeBase)"})
  public void testGetAttributeNameWithAttribute_thenReturn42() {
    // Arrange
    SQLDialect sqlDialect = mock(SQLDialect.class);
    when(sqlDialect.getQuotedIdentifier(Mockito.<String>any(), anyBoolean(), anyBoolean()))
        .thenReturn("42");

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(sqlDialect);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    String actualAttributeName = DBStructUtils.getAttributeName(attribute);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(sqlDialect).getQuotedIdentifier("Name", true, false);
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("42", actualAttributeName);
  }

  /**
   * Test {@link DBStructUtils#getAttributeName(DBSAttributeBase)} with {@code attribute}.
   *
   * <ul>
   *   <li>Then return {@code Label}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getAttributeName(DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBStructUtils.getAttributeName(DBSAttributeBase)"})
  public void testGetAttributeNameWithAttribute_thenReturnLabel() {
    // Arrange
    DBCSession session = mock(DBCSession.class);
    LocalStatement localStatement = new LocalStatement(mock(DBCSession.class), "Text");

    LocalResultSet<DBCStatement> resultSet = new LocalResultSet<>(session, localStatement);

    // Act and Assert
    assertEquals(
        "Label",
        DBStructUtils.getAttributeName(
            new LocalResultSetColumn(resultSet, 1, "Label", DBPDataKind.BOOLEAN)));
  }

  /**
   * Test {@link DBStructUtils#getAttributeName(DBSAttributeBase)} with {@code attribute}.
   *
   * <ul>
   *   <li>Then return {@code "Name"}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getAttributeName(DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBStructUtils.getAttributeName(DBSAttributeBase)"})
  public void testGetAttributeNameWithAttribute_thenReturnName() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    String actualAttributeName = DBStructUtils.getAttributeName(attribute);

    // Assert
    verify(dbpDataSource).getSQLDialect();
    verify(parent, atLeast(1)).getDataSource();
    assertEquals("\"Name\"", actualAttributeName);
  }

  /**
   * Test {@link DBStructUtils#getAttributeName(DBSAttributeBase)} with {@code attribute}.
   *
   * <ul>
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBStructUtils#getAttributeName(DBSAttributeBase)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBStructUtils.getAttributeName(DBSAttributeBase)"})
  public void testGetAttributeNameWithAttribute_thenReturnName2() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityAttribute attribute =
        new DBVEntityAttribute(entity2, mock(DBVEntityAttribute.class), "Name");
    entity.addVirtualAttribute(attribute);
    DBVEntityAttribute attribute2 =
        new DBVEntityAttribute(entity, mock(DBVEntityAttribute.class), "Name");

    // Act
    String actualAttributeName = DBStructUtils.getAttributeName(attribute2);

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("Name", actualAttributeName);
  }
}
