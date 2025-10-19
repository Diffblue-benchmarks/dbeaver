package org.jkiss.dbeaver.model.virtual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.app.DBPDataSourceRegistry;
import org.jkiss.dbeaver.model.app.DBPProject;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSEntity;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraint;
import org.jkiss.dbeaver.model.struct.DBSEntityConstraintType;
import org.jkiss.dbeaver.model.struct.rdb.DBSForeignKeyModifyRule;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBVEntityForeignKeyDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)}
   *   <li>{@link DBVEntityForeignKey#setRefEntityId(String)}
   *   <li>{@link DBVEntityForeignKey#getAttributes()}
   *   <li>{@link DBVEntityForeignKey#getConstraintType()}
   *   <li>{@link DBVEntityForeignKey#getDeleteRule()}
   *   <li>{@link DBVEntityForeignKey#getDescription()}
   *   <li>{@link DBVEntityForeignKey#getEntity()}
   *   <li>{@link DBVEntityForeignKey#getRefConstraintId()}
   *   <li>{@link DBVEntityForeignKey#getRefEntityId()}
   *   <li>{@link DBVEntityForeignKey#getUpdateRule()}
   *   <li>{@link DBVEntityForeignKey#isPersisted()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVEntityForeignKey.<init>(DBVEntity)",
    "List DBVEntityForeignKey.getAttributes()",
    "DBSEntityConstraintType DBVEntityForeignKey.getConstraintType()",
    "DBSForeignKeyModifyRule DBVEntityForeignKey.getDeleteRule()",
    "String DBVEntityForeignKey.getDescription()",
    "DBVEntity DBVEntityForeignKey.getEntity()",
    "String DBVEntityForeignKey.getRefConstraintId()",
    "String DBVEntityForeignKey.getRefEntityId()",
    "DBSForeignKeyModifyRule DBVEntityForeignKey.getUpdateRule()",
    "boolean DBVEntityForeignKey.isPersisted()",
    "void DBVEntityForeignKey.setRefEntityId(String)",
    "String DBVEntityForeignKey.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBVContainer parent = new DBVContainer(null, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent, "Name"), "Name", "Description Column Names");

    // Act
    DBVEntityForeignKey actualDbvEntityForeignKey = new DBVEntityForeignKey(entity);
    actualDbvEntityForeignKey.setRefEntityId("42");
    List<DBVEntityForeignKeyColumn> actualAttributes = actualDbvEntityForeignKey.getAttributes();
    DBSEntityConstraintType actualConstraintType = actualDbvEntityForeignKey.getConstraintType();
    DBSForeignKeyModifyRule actualDeleteRule = actualDbvEntityForeignKey.getDeleteRule();
    String actualDescription = actualDbvEntityForeignKey.getDescription();
    DBVEntity actualEntity = actualDbvEntityForeignKey.getEntity();
    String actualRefConstraintId = actualDbvEntityForeignKey.getRefConstraintId();
    String actualRefEntityId = actualDbvEntityForeignKey.getRefEntityId();
    DBSForeignKeyModifyRule actualUpdateRule = actualDbvEntityForeignKey.getUpdateRule();
    boolean actualIsPersistedResult = actualDbvEntityForeignKey.isPersisted();

    // Assert
    assertEquals("42", actualRefEntityId);
    assertNull(actualDescription);
    assertNull(actualRefConstraintId);
    assertTrue(actualAttributes.isEmpty());
    assertTrue(actualIsPersistedResult);
    assertSame(entity, actualEntity);
    assertSame(entity, actualDbvEntityForeignKey.getParentObject());
    assertSame(DBSEntityConstraintType.VIRTUAL_FOREIGN_KEY, actualConstraintType);
    DBSForeignKeyModifyRule dbsForeignKeyModifyRule = DBSForeignKeyModifyRule.NO_ACTION;
    assertSame(dbsForeignKeyModifyRule, actualDeleteRule);
    assertSame(dbsForeignKeyModifyRule, actualUpdateRule);
  }

  /**
   * Test {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity, DBVEntityForeignKey, DBVModel)}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity,
   * DBVEntityForeignKey, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.<init>(DBVEntity, DBVEntityForeignKey, DBVModel)"})
  public void testNewDBVEntityForeignKey() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVEntity entity2 = new DBVEntity(null, "Name", "Description Column Names");

    DBVEntityForeignKey copy = new DBVEntityForeignKey(entity2);
    copy.setRefEntityId("42");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);

    // Act
    DBVEntityForeignKey actualDbvEntityForeignKey =
        new DBVEntityForeignKey(entity, copy, targetModel);

    // Assert
    DBVEntity entity3 = actualDbvEntityForeignKey.getEntity();
    DBVEntityConstraint bestIdentifier = entity3.getBestIdentifier();
    DBVEntity actualEntity = bestIdentifier.getEntity();
    verify(dataSourceContainer, atLeast(1)).getId();
    List<DBVEntityConstraint> constraints = entity3.getConstraints();
    assertEquals(1, constraints.size());
    assertSame(bestIdentifier, constraints.get(0));
    DBVContainer expectedParentObject = entity3.getContainer();
    assertSame(expectedParentObject, entity3.getParentObject());
    assertSame(entity3, actualEntity);
    assertSame(entity3, bestIdentifier.getParentObject());
    assertSame(entity3, actualDbvEntityForeignKey.getParentObject());
  }

  /**
   * Test {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity, DBVEntityForeignKey, DBVModel)}.
   *
   * <ul>
   *   <li>Given {@code Ref Entity Id}.
   *   <li>Then return {@code Ref Entity Id}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity,
   * DBVEntityForeignKey, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.<init>(DBVEntity, DBVEntityForeignKey, DBVModel)"})
  public void testNewDBVEntityForeignKey_givenRefEntityId_thenReturnRefEntityId() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBVContainer parent3 = mock(DBVContainer.class);

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);
    when(parent3.getDataSourceContainer()).thenReturn(dbpDataSourceContainer);
    DBVContainer container = new DBVContainer(parent3, "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityForeignKey copy = new DBVEntityForeignKey(entity2);
    copy.setRefEntityId("Ref Entity Id");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);

    // Act
    DBVEntityForeignKey actualDbvEntityForeignKey =
        new DBVEntityForeignKey(entity, copy, targetModel);

    // Assert
    DBVEntity entity3 = actualDbvEntityForeignKey.getEntity();
    DBVEntityConstraint bestIdentifier = entity3.getBestIdentifier();
    DBVEntity actualEntity = bestIdentifier.getEntity();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent3).getDataSourceContainer();
    assertEquals("Ref Entity Id", actualDbvEntityForeignKey.getRefEntityId());
    assertEquals("vfk_Name_Ref Entity Id", actualDbvEntityForeignKey.getName());
    List<DBVEntityConstraint> constraints = entity3.getConstraints();
    assertEquals(1, constraints.size());
    assertSame(bestIdentifier, constraints.get(0));
    DBVContainer expectedParentObject = entity3.getContainer();
    assertSame(expectedParentObject, entity3.getParentObject());
    assertSame(entity3, actualEntity);
    assertSame(entity3, bestIdentifier.getParentObject());
    assertSame(entity3, actualDbvEntityForeignKey.getParentObject());
  }

  /**
   * Test {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity, DBVEntityForeignKey, DBVModel)}.
   *
   * <ul>
   *   <li>Then return Name is {@code vfk_Name_?}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity,
   * DBVEntityForeignKey, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.<init>(DBVEntity, DBVEntityForeignKey, DBVModel)"})
  public void testNewDBVEntityForeignKey_thenReturnNameIsVfkName() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey copy = new DBVEntityForeignKey(entity2);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);

    // Act
    DBVEntityForeignKey actualDbvEntityForeignKey =
        new DBVEntityForeignKey(entity, copy, targetModel);

    // Assert
    DBVEntity entity3 = actualDbvEntityForeignKey.getEntity();
    DBVEntityConstraint bestIdentifier = entity3.getBestIdentifier();
    DBVEntity actualEntity = bestIdentifier.getEntity();
    verify(dataSourceContainer, atLeast(1)).getId();
    assertEquals("vfk_Name_?", actualDbvEntityForeignKey.getName());
    assertNull(actualDbvEntityForeignKey.getRefEntityId());
    List<DBVEntityConstraint> constraints = entity3.getConstraints();
    assertEquals(1, constraints.size());
    assertSame(bestIdentifier, constraints.get(0));
    DBVContainer expectedParentObject = entity3.getContainer();
    assertSame(expectedParentObject, entity3.getParentObject());
    assertSame(entity3, actualEntity);
    assertSame(entity3, bestIdentifier.getParentObject());
    assertSame(entity3, actualDbvEntityForeignKey.getParentObject());
  }

  /**
   * Test {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity, DBVEntityForeignKey, DBVModel)}.
   *
   * <ul>
   *   <li>Then return RefEntityId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity,
   * DBVEntityForeignKey, DBVModel)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.<init>(DBVEntity, DBVEntityForeignKey, DBVModel)"})
  public void testNewDBVEntityForeignKey_thenReturnRefEntityIdIs42() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBVContainer parent3 = mock(DBVContainer.class);

    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);
    when(parent3.getDataSourceContainer()).thenReturn(dbpDataSourceContainer);
    DBVContainer container = new DBVContainer(parent3, "Name");
    DBVEntity entity2 = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityForeignKey copy = new DBVEntityForeignKey(entity2);
    copy.setRefEntityId("42");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);

    // Act
    DBVEntityForeignKey actualDbvEntityForeignKey =
        new DBVEntityForeignKey(entity, copy, targetModel);

    // Assert
    DBVEntity entity3 = actualDbvEntityForeignKey.getEntity();
    DBVEntityConstraint bestIdentifier = entity3.getBestIdentifier();
    DBVEntity actualEntity = bestIdentifier.getEntity();
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent3).getDataSourceContainer();
    assertEquals("42", actualDbvEntityForeignKey.getRefEntityId());
    assertEquals("vfk_Name_42", actualDbvEntityForeignKey.getName());
    List<DBVEntityConstraint> constraints = entity3.getConstraints();
    assertEquals(1, constraints.size());
    assertSame(bestIdentifier, constraints.get(0));
    DBVContainer expectedParentObject = entity3.getContainer();
    assertSame(expectedParentObject, entity3.getParentObject());
    assertSame(entity3, actualEntity);
    assertSame(entity3, bestIdentifier.getParentObject());
    assertSame(entity3, actualDbvEntityForeignKey.getParentObject());
  }

  /**
   * Test {@link DBVEntityForeignKey#dispose()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String, Map)} with parent is {@link
   *       DBVContainer} and {@code Name} and map is {@link HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.dispose()"})
  public void testDispose_givenDBVContainerWithParentIsDBVContainerAndNameAndMapIsHashMap() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("foo");

    // Act
    dbvEntityForeignKey.dispose();

    // Assert
    assertEquals("vfk_Name_?", dbvEntityForeignKey.getName());
    assertNull(dbvEntityForeignKey.getRefEntityId());
  }

  /**
   * Test {@link DBVEntityForeignKey#dispose()}.
   *
   * <ul>
   *   <li>Given {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.dispose()"})
  public void testDispose_givenDBVEntityForeignKeyWithEntityIsDBVEntity() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    // Act
    dbvEntityForeignKey.dispose();

    // Assert that nothing has changed
    assertEquals("vfk_Name_?", dbvEntityForeignKey.getName());
  }

  /**
   * Test {@link DBVEntityForeignKey#dispose()}.
   *
   * <ul>
   *   <li>Given {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)} RefEntityId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#dispose()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.dispose()"})
  public void testDispose_givenDBVEntityForeignKeyWithEntityIsDBVEntityRefEntityIdIs42() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("42");

    // Act
    dbvEntityForeignKey.dispose();

    // Assert
    assertEquals("vfk_Name_?", dbvEntityForeignKey.getName());
    assertNull(dbvEntityForeignKey.getRefEntityId());
  }

  /**
   * Test {@link DBVEntityForeignKey#getReferencedConstraint(DBRProgressMonitor)} with {@code
   * DBRProgressMonitor}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getReferencedConstraint(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraint DBVEntityForeignKey.getReferencedConstraint(DBRProgressMonitor)"
  })
  public void testGetReferencedConstraintWithDBRProgressMonitor() throws DBException {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> dbvEntityForeignKey.getReferencedConstraint(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DBVEntityForeignKey#getReferencedConstraint(DBRProgressMonitor)} with {@code
   * DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getReferencedConstraint(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraint DBVEntityForeignKey.getReferencedConstraint(DBRProgressMonitor)"
  })
  public void testGetReferencedConstraintWithDBRProgressMonitor_thenCallsGetContainer()
      throws DBException {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("foo");

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> dbvEntityForeignKey.getReferencedConstraint(new LoggingProgressMonitor()));
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntityForeignKey#getReferencedConstraint()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getReferencedConstraint()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityConstraint DBVEntityForeignKey.getReferencedConstraint()"})
  public void testGetReferencedConstraint_givenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertNull(new DBVEntityForeignKey(entity).getReferencedConstraint());
  }

  /**
   * Test {@link DBVEntityForeignKey#getReferencedConstraint()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getReferencedConstraint()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntityConstraint DBVEntityForeignKey.getReferencedConstraint()"})
  public void testGetReferencedConstraint_thenCallsGetContainer() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("foo");

    // Act
    DBSEntityConstraint actualReferencedConstraint = dbvEntityForeignKey.getReferencedConstraint();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSource();
    assertNull(actualReferencedConstraint);
  }

  /**
   * Test {@link DBVEntityForeignKey#setReferencedConstraint(DBRProgressMonitor,
   * DBSEntityConstraint)} with {@code monitor}, {@code constraint}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#setReferencedConstraint(DBRProgressMonitor,
   * DBSEntityConstraint)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBVEntityForeignKey.setReferencedConstraint(DBRProgressMonitor, DBSEntityConstraint)"
  })
  public void testSetReferencedConstraintWithMonitorConstraint() throws DBException {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getId()).thenReturn("42");
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSourceContainer()).thenReturn(dbpDataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    dbvEntityForeignKey.setReferencedConstraint(
        monitor, new DBSDocumentConstraint(mock(DBSDocumentContainer.class)));

    // Assert
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject).getId();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSourceContainer();
  }

  /**
   * Test {@link DBVEntityForeignKey#setReferencedConstraint(String, String)} with {@code
   * refEntityId}, {@code refConsId}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#setReferencedConstraint(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.setReferencedConstraint(String, String)"})
  public void testSetReferencedConstraintWithRefEntityIdRefConsId() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    // Act
    dbvEntityForeignKey.setReferencedConstraint("42", "42");

    // Assert
    assertEquals("42", dbvEntityForeignKey.getRefConstraintId());
    assertEquals("42", dbvEntityForeignKey.getRefEntityId());
    assertEquals("vfk_Name_42", dbvEntityForeignKey.getName());
  }

  /**
   * Test {@link DBVEntityForeignKey#setReferencedConstraint(String, String)} with {@code
   * refEntityId}, {@code refConsId}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#setReferencedConstraint(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.setReferencedConstraint(String, String)"})
  public void testSetReferencedConstraintWithRefEntityIdRefConsId2() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId(null);

    // Act
    dbvEntityForeignKey.setReferencedConstraint(null, "42");

    // Assert
    assertEquals("42", dbvEntityForeignKey.getRefConstraintId());
    assertEquals("vfk_Name_?", dbvEntityForeignKey.getName());
    assertNull(dbvEntityForeignKey.getRefEntityId());
  }

  /**
   * Test {@link DBVEntityForeignKey#setReferencedConstraint(String, String)} with {@code
   * refEntityId}, {@code refConsId}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#setReferencedConstraint(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.setReferencedConstraint(String, String)"})
  public void testSetReferencedConstraintWithRefEntityIdRefConsId3() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("foo");

    // Act
    dbvEntityForeignKey.setReferencedConstraint(null, "42");

    // Assert
    assertEquals("42", dbvEntityForeignKey.getRefConstraintId());
    assertEquals("vfk_Name_?", dbvEntityForeignKey.getName());
    assertNull(dbvEntityForeignKey.getRefEntityId());
  }

  /**
   * Test {@link DBVEntityForeignKey#setReferencedConstraint(String, String)} with {@code
   * refEntityId}, {@code refConsId}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#setReferencedConstraint(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.setReferencedConstraint(String, String)"})
  public void testSetReferencedConstraintWithRefEntityIdRefConsId4() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("42");

    // Act
    dbvEntityForeignKey.setReferencedConstraint("42", "42");

    // Assert
    assertEquals("42", dbvEntityForeignKey.getRefConstraintId());
    assertEquals("42", dbvEntityForeignKey.getRefEntityId());
    assertEquals("vfk_Name_42", dbvEntityForeignKey.getName());
  }

  /**
   * Test {@link DBVEntityForeignKey#getRealReferenceConstraint(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBVEntityForeignKey#getRealReferenceConstraint(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraint DBVEntityForeignKey.getRealReferenceConstraint(DBRProgressMonitor)"
  })
  public void testGetRealReferenceConstraint_givenDBVContainerWithParentIsDBVContainerAndName()
      throws DBException {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> dbvEntityForeignKey.getRealReferenceConstraint(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DBVEntityForeignKey#getRealReferenceConstraint(DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DBVEntityForeignKey#getRealReferenceConstraint(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBSEntityConstraint DBVEntityForeignKey.getRealReferenceConstraint(DBRProgressMonitor)"
  })
  public void testGetRealReferenceConstraint_thenCallsGetContainer() throws DBException {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("foo");

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> dbvEntityForeignKey.getRealReferenceConstraint(new LoggingProgressMonitor()));
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntityForeignKey#getAssociatedEntity(DBRProgressMonitor)} with {@code
   * DBRProgressMonitor}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getAssociatedEntity(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntity DBVEntityForeignKey.getAssociatedEntity(DBRProgressMonitor)"})
  public void testGetAssociatedEntityWithDBRProgressMonitor() throws DBException {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> dbvEntityForeignKey.getAssociatedEntity(new LoggingProgressMonitor()));
  }

  /**
   * Test {@link DBVEntityForeignKey#getAssociatedEntity(DBRProgressMonitor)} with {@code
   * DBRProgressMonitor}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getAssociatedEntity(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntity DBVEntityForeignKey.getAssociatedEntity(DBRProgressMonitor)"})
  public void testGetAssociatedEntityWithDBRProgressMonitor_thenCallsGetContainer()
      throws DBException {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("foo");

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> dbvEntityForeignKey.getAssociatedEntity(new LoggingProgressMonitor()));
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntityForeignKey#getAssociatedEntity()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getAssociatedEntity()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntity DBVEntityForeignKey.getAssociatedEntity()"})
  public void testGetAssociatedEntity_givenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertNull(new DBVEntityForeignKey(entity).getAssociatedEntity());
  }

  /**
   * Test {@link DBVEntityForeignKey#getAssociatedEntity()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSource#getContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getAssociatedEntity()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBSEntity DBVEntityForeignKey.getAssociatedEntity()"})
  public void testGetAssociatedEntity_thenCallsGetContainer() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceRegistry dbpDataSourceRegistry = mock(DBPDataSourceRegistry.class);
    when(dbpDataSourceRegistry.getProject()).thenReturn(dbpProject);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getRegistry()).thenReturn(dbpDataSourceRegistry);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("foo");

    // Act
    DBSEntity actualAssociatedEntity = dbvEntityForeignKey.getAssociatedEntity();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).getRegistry();
    verify(dbpDataSourceRegistry).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSource();
    assertNull(actualAssociatedEntity);
  }

  /**
   * Test {@link DBVEntityForeignKey#getAttributeReferences(DBRProgressMonitor)}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getAttributeReferences(DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List DBVEntityForeignKey.getAttributeReferences(DBRProgressMonitor)"})
  public void testGetAttributeReferences() throws DBException {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    // Act and Assert
    assertTrue(dbvEntityForeignKey.getAttributeReferences(new LoggingProgressMonitor()).isEmpty());
  }

  /**
   * Test {@link DBVEntityForeignKey#setAttributes(List)}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#setAttributes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.setAttributes(List)"})
  public void testSetAttributes() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    ArrayList<DBVEntityForeignKeyColumn> attrs = new ArrayList<>();
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity2);
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey2, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);
    attrs.add(dbvEntityForeignKeyColumn);

    // Act
    dbvEntityForeignKey.setAttributes(attrs);

    // Assert
    List<DBVEntityForeignKeyColumn> attributes = dbvEntityForeignKey.getAttributes();
    assertEquals(1, attributes.size());
    assertSame(dbvEntityForeignKeyColumn, attributes.get(0));
  }

  /**
   * Test {@link DBVEntityForeignKey#setAttributes(List)}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#setAttributes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.setAttributes(List)"})
  public void testSetAttributes2() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    ArrayList<DBVEntityForeignKeyColumn> attrs = new ArrayList<>();
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity2 = new DBVEntity(container2, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey = new DBVEntityForeignKey(entity2);
    DBVEntityForeignKey foreignKey2 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy =
        new DBVEntityForeignKeyColumn(foreignKey2, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn =
        new DBVEntityForeignKeyColumn(foreignKey, copy);
    attrs.add(dbvEntityForeignKeyColumn);
    DBVContainer container3 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity3 = new DBVEntity(container3, "Name", "Description Column Names");
    DBVEntityForeignKey foreignKey3 = new DBVEntityForeignKey(entity3);
    DBVEntityForeignKey foreignKey4 = new DBVEntityForeignKey(mock(DBVEntity.class));
    DBVEntityForeignKeyColumn copy2 =
        new DBVEntityForeignKeyColumn(foreignKey4, "Attribute Name", "Ref Attribute Name");

    DBVEntityForeignKeyColumn dbvEntityForeignKeyColumn2 =
        new DBVEntityForeignKeyColumn(foreignKey3, copy2);
    attrs.add(dbvEntityForeignKeyColumn2);

    // Act
    dbvEntityForeignKey.setAttributes(attrs);

    // Assert
    assertEquals(attrs, dbvEntityForeignKey.getAttributes());
  }

  /**
   * Test {@link DBVEntityForeignKey#setAttributes(List)}.
   *
   * <ul>
   *   <li>Then {@link DBVEntityForeignKey#DBVEntityForeignKey(DBVEntity)} with entity is {@link
   *       DBVEntity#DBVEntity(DBVContainer, String, String)} Attributes Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#setAttributes(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBVEntityForeignKey.setAttributes(List)"})
  public void testSetAttributes_thenDBVEntityForeignKeyWithEntityIsDBVEntityAttributesEmpty() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");
    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);

    // Act
    dbvEntityForeignKey.setAttributes(new ArrayList<>());

    // Assert that nothing has changed
    assertTrue(dbvEntityForeignKey.getAttributes().isEmpty());
  }

  /**
   * Test {@link DBVEntityForeignKey#getParentObject()}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getParentObject()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBVEntity DBVEntityForeignKey.getParentObject()"})
  public void testGetParentObject() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertSame(entity, new DBVEntityForeignKey(entity).getParentObject());
  }

  /**
   * Test {@link DBVEntityForeignKey#getDataSource()}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVEntityForeignKey.getDataSource()"})
  public void testGetDataSource() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    new DBVEntityForeignKey(entity).getDataSource();

    // Assert
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntityForeignKey#getDataSource()}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVEntityForeignKey.getDataSource()"})
  public void testGetDataSource2() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVContainer parent2 = new DBVContainer(parent, "Name");
    DBVContainer container = new DBVContainer(parent2, "Name", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    new DBVEntityForeignKey(entity).getDataSource();

    // Assert
    verify(parent).getDataSource();
  }

  /**
   * Test {@link DBVEntityForeignKey#getDataSource()}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVEntityForeignKey.getDataSource()"})
  public void testGetDataSource3() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    new DBVEntityForeignKey(entity).getDataSource();

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVEntityForeignKey#getDataSource()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String, Map)} with parent is {@link
   *       DBVModel#DBVModel(DBPDataSourceContainer)} and {@code Name} and map is {@link
   *       HashMap#HashMap()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVEntityForeignKey.getDataSource()"})
  public void testGetDataSource_givenDBVContainerWithParentIsDBVModelAndNameAndMapIsHashMap() {
    // Arrange
    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getDataSource()).thenReturn(mock(DBPDataSource.class));
    DBVModel parent = new DBVModel(dataSourceContainer);
    DBVContainer container = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    new DBVEntityForeignKey(entity).getDataSource();

    // Assert
    verify(dataSourceContainer).getDataSource();
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test {@link DBVEntityForeignKey#getDataSource()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSource DBVEntityForeignKey.getDataSource()"})
  public void testGetDataSource_thenReturnNull() {
    // Arrange
    DBVModel parent = new DBVModel("42", new HashMap<>());
    DBVContainer parent2 = mock(DBVContainer.class);
    DBVContainer parent3 = new DBVContainer(parent2, "Name", new HashMap<>());
    parent.addContainer(new DBVContainer(parent3, "Name"));
    DBVContainer container = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertNull(new DBVEntityForeignKey(entity).getDataSource());
  }

  /**
   * Test {@link DBVEntityForeignKey#getName()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   *   <li>Then return {@code vfk_Name_?}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBVEntityForeignKey.getName()"})
  public void testGetName_givenDBVContainerWithParentIsDBVContainerAndName_thenReturnVfkName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertEquals("vfk_Name_?", new DBVEntityForeignKey(entity).getName());
  }

  /**
   * Test {@link DBVEntityForeignKey#getName()}.
   *
   * <ul>
   *   <li>Then return {@code vfk_Name_foo}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBVEntityForeignKey.getName()"})
  public void testGetName_thenReturnVfkNameFoo() {
    // Arrange
    DBVContainer parent = mock(DBVContainer.class);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("foo");

    // Act and Assert
    assertEquals("vfk_Name_foo", dbvEntityForeignKey.getName());
  }

  /**
   * Test {@link DBVEntityForeignKey#getAssociatedDataSource()}.
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getAssociatedDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer DBVEntityForeignKey.getAssociatedDataSource()"})
  public void testGetAssociatedDataSource() {
    // Arrange
    DBVEntity entity = new DBVEntity(null, "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("42");

    // Act and Assert
    assertNull(dbvEntityForeignKey.getAssociatedDataSource());
  }

  /**
   * Test {@link DBVEntityForeignKey#getAssociatedDataSource()}.
   *
   * <ul>
   *   <li>Given {@link DBVContainer#DBVContainer(DBVContainer, String)} with parent is {@link
   *       DBVContainer} and {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getAssociatedDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer DBVEntityForeignKey.getAssociatedDataSource()"})
  public void testGetAssociatedDataSource_givenDBVContainerWithParentIsDBVContainerAndName() {
    // Arrange
    DBVContainer container = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act and Assert
    assertNull(new DBVEntityForeignKey(entity).getAssociatedDataSource());
  }

  /**
   * Test {@link DBVEntityForeignKey#getAssociatedDataSource()}.
   *
   * <ul>
   *   <li>Then calls {@link DBVContainer#getDataSourceContainer()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getAssociatedDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer DBVEntityForeignKey.getAssociatedDataSource()"})
  public void testGetAssociatedDataSource_thenCallsGetDataSourceContainer() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dbpDataSourceContainer.getProject()).thenReturn(dbpProject);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSourceContainer()).thenReturn(dbpDataSourceContainer);
    DBVContainer parent2 = new DBVContainer(parent, "Name", new HashMap<>());
    DBVEntity entity =
        new DBVEntity(new DBVContainer(parent2, "Name"), "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("foo");

    // Act
    DBPDataSourceContainer actualAssociatedDataSource =
        dbvEntityForeignKey.getAssociatedDataSource();

    // Assert
    verify(dbpDataSourceContainer).getProject();
    verify(dbpProject).getNavigatorModel();
    verify(parent).getDataSourceContainer();
    assertNull(actualAssociatedDataSource);
  }

  /**
   * Test {@link DBVEntityForeignKey#getAssociatedDataSource()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link DBVEntityForeignKey#getAssociatedDataSource()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer DBVEntityForeignKey.getAssociatedDataSource()"})
  public void testGetAssociatedDataSource_thenCallsGetId() {
    // Arrange
    DBPProject dbpProject = mock(DBPProject.class);
    when(dbpProject.getNavigatorModel()).thenReturn(null);

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    when(dataSourceContainer.getProject()).thenReturn(dbpProject);
    DBVModel container = new DBVModel(dataSourceContainer);
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    DBVEntityForeignKey dbvEntityForeignKey = new DBVEntityForeignKey(entity);
    dbvEntityForeignKey.setRefEntityId("42");

    // Act
    DBPDataSourceContainer actualAssociatedDataSource =
        dbvEntityForeignKey.getAssociatedDataSource();

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
    verify(dataSourceContainer).getProject();
    verify(dbpProject).getNavigatorModel();
    assertNull(actualAssociatedDataSource);
  }
}
