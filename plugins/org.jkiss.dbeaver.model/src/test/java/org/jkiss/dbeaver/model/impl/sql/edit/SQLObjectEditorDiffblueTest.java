package org.jkiss.dbeaver.model.impl.sql.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.DBPDataSourceInfo;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.DBPObject;
import org.jkiss.dbeaver.model.data.DBDNull;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.edit.DBECommandAbstract;
import org.jkiss.dbeaver.model.impl.sql.BasicSQLDialect;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor.EmptyCommand;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor.ObjectChangeCommand;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor.ObjectCreateCommand;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor.ObjectDeleteCommand;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor.ObjectRenameCommand;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor.RefreshObjectReflector;
import org.jkiss.dbeaver.model.impl.struct.RelationalObjectType;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.jkiss.dbeaver.model.struct.DBSObjectType;
import org.jkiss.dbeaver.model.struct.cache.SimpleObjectCache;
import org.jkiss.dbeaver.model.virtual.DBVContainer;
import org.jkiss.dbeaver.model.virtual.DBVEntity;
import org.jkiss.dbeaver.model.virtual.DBVEntityForeignKey;
import org.jkiss.dbeaver.model.virtual.DBVModel;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SQLObjectEditorDiffblueTest {
  @Mock private DBSObject dBSObject;

  /**
   * Test EmptyCommand {@link EmptyCommand#EmptyCommand(DBPObject)}.
   *
   * <p>Method under test: {@link EmptyCommand#EmptyCommand(DBPObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EmptyCommand.<init>(DBPObject)"})
  public void testEmptyCommandNewEmptyCommand() {
    // Arrange and Act
    EmptyCommand actualEmptyCommand = new EmptyCommand(DBDNull.INSTANCE);

    // Assert
    assertEquals("Empty", actualEmptyCommand.getTitle());
    assertFalse(actualEmptyCommand.isDisableSessionLogging());
    assertSame(DBDNull.INSTANCE, actualEmptyCommand.getObject());
  }

  /**
   * Test ObjectChangeCommand {@link ObjectChangeCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectChangeCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction[] ObjectChangeCommand.getPersistActions(DBRProgressMonitor, DBCExecutionContext, Map)"
  })
  public void testObjectChangeCommandGetPersistActions_thenThrowDBException() throws DBException {
    // Arrange
    SQLObjectEditor sqlObjectEditor = mock(SQLObjectEditor.class);
    doThrow(new DBException("An error occurred"))
        .when(sqlObjectEditor)
        .addObjectModifyActions(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBCExecutionContext>any(),
            Mockito.<List<DBEPersistAction>>any(),
            Mockito.<ObjectChangeCommand>any(),
            Mockito.<Map<String, Object>>any());
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    ObjectChangeCommand objectChangeCommand =
        sqlObjectEditor.new ObjectChangeCommand(dbsDocumentConstraint);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> objectChangeCommand.getPersistActions(monitor, executionContext, new HashMap<>()));
    verify(sqlObjectEditor)
        .addObjectModifyActions(
            isA(DBRProgressMonitor.class),
            isA(DBCExecutionContext.class),
            isA(List.class),
            isA(ObjectChangeCommand.class),
            isA(Map.class));
  }

  /**
   * Test ObjectChangeCommand {@link ObjectChangeCommand#validateCommand(DBRProgressMonitor, Map)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLObjectEditor#validateObjectProperties(DBRProgressMonitor,
   *       ObjectChangeCommand, Map)}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectChangeCommand#validateCommand(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectChangeCommand.validateCommand(DBRProgressMonitor, Map)"})
  public void testObjectChangeCommandValidateCommand_thenCallsValidateObjectProperties()
      throws DBException {
    // Arrange
    SQLObjectEditor sqlObjectEditor = mock(SQLObjectEditor.class);
    doNothing()
        .when(sqlObjectEditor)
        .validateObjectProperties(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<ObjectChangeCommand>any(),
            Mockito.<Map<String, Object>>any());
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    ObjectChangeCommand objectChangeCommand =
        sqlObjectEditor.new ObjectChangeCommand(dbsDocumentConstraint);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    objectChangeCommand.validateCommand(monitor, new HashMap<>());

    // Assert
    verify(sqlObjectEditor)
        .validateObjectProperties(
            isA(DBRProgressMonitor.class), isA(ObjectChangeCommand.class), isA(Map.class));
  }

  /**
   * Test ObjectCreateCommand {@link ObjectCreateCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectCreateCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction[] ObjectCreateCommand.getPersistActions(DBRProgressMonitor, DBCExecutionContext, Map)"
  })
  public void testObjectCreateCommandGetPersistActions_thenThrowDBException() throws DBException {
    // Arrange
    SQLObjectEditor sqlObjectEditor = mock(SQLObjectEditor.class);
    doThrow(new DBException("An error occurred"))
        .when(sqlObjectEditor)
        .addObjectCreateActions(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBCExecutionContext>any(),
            Mockito.<List<DBEPersistAction>>any(),
            Mockito.<ObjectCreateCommand>any(),
            Mockito.<Map<String, Object>>any());
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    ObjectCreateCommand objectCreateCommand =
        sqlObjectEditor.new ObjectCreateCommand(dbsDocumentConstraint, "foo", new HashMap<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> objectCreateCommand.getPersistActions(monitor, executionContext, new HashMap<>()));
    verify(sqlObjectEditor)
        .addObjectCreateActions(
            isA(DBRProgressMonitor.class),
            isA(DBCExecutionContext.class),
            isA(List.class),
            isA(ObjectCreateCommand.class),
            isA(Map.class));
  }

  /**
   * Test ObjectCreateCommand {@link ObjectCreateCommand#updateModel()}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getContainer()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectCreateCommand#updateModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectCreateCommand.updateModel()"})
  public void testObjectCreateCommandUpdateModel_givenDBPDataSourceGetContainerReturnNull() {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);
    SQLObjectEditor sqlObjectEditor = mock(SQLObjectEditor.class);
    ObjectCreateCommand objectCreateCommand =
        sqlObjectEditor.new ObjectCreateCommand(dbsDocumentConstraint, "foo", new HashMap<>());

    // Act
    objectCreateCommand.updateModel();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
  }

  /**
   * Test ObjectCreateCommand {@link ObjectCreateCommand#updateModel()}.
   *
   * <ul>
   *   <li>Given {@link DBSObject} {@link DBSObject#isPersisted()} return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectCreateCommand#updateModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectCreateCommand.updateModel()"})
  public void testObjectCreateCommandUpdateModel_givenDBSObjectIsPersistedReturnFalse() {
    // Arrange
    when(dBSObject.getDataSource()).thenReturn(null);
    when(dBSObject.isPersisted()).thenReturn(false);
    when(dBSObject.getDataSource()).thenReturn(null);
    SQLObjectEditor sqlObjectEditor = mock(SQLObjectEditor.class);
    ObjectCreateCommand objectCreateCommand =
        sqlObjectEditor.new ObjectCreateCommand(dBSObject, "foo", new HashMap<>());

    // Act
    objectCreateCommand.updateModel();

    // Assert
    verify(dBSObject).isPersisted();
    verify(dBSObject, atLeast(1)).getDataSource();
  }

  /**
   * Test ObjectCreateCommand {@link ObjectCreateCommand#updateModel()}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectCreateCommand#updateModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectCreateCommand.updateModel()"})
  public void testObjectCreateCommandUpdateModel_thenCallsFireEvent() {
    // Arrange
    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);
    SQLObjectEditor sqlObjectEditor = mock(SQLObjectEditor.class);
    ObjectCreateCommand objectCreateCommand =
        sqlObjectEditor.new ObjectCreateCommand(dbsDocumentConstraint, "foo", new HashMap<>());

    // Act
    objectCreateCommand.updateModel();

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
  }

  /**
   * Test ObjectCreateCommand {@link ObjectCreateCommand#validateCommand(DBRProgressMonitor, Map)}.
   *
   * <p>Method under test: {@link ObjectCreateCommand#validateCommand(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectCreateCommand.validateCommand(DBRProgressMonitor, Map)"})
  public void testObjectCreateCommandValidateCommand() throws DBException {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getSQLDialect()).thenReturn(BasicSQLDialect.INSTANCE);

    DBSDocumentConstraint dbsDocumentConstraint = mock(DBSDocumentConstraint.class);
    when(dbsDocumentConstraint.getDataSource()).thenReturn(dbpDataSource);

    SimpleObjectCache<DBSObject, DBSObject> simpleObjectCache = new SimpleObjectCache<>();
    simpleObjectCache.cacheObject(dbsDocumentConstraint);

    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.getSupportedObjectTypes())
        .thenReturn(new DBSObjectType[] {RelationalObjectType.TYPE_CATALOG});

    DBPDataSource dbpDataSource2 = mock(DBPDataSource.class);
    when(dbpDataSource2.getInfo()).thenReturn(dbpDataSourceInfo);

    DBSDocumentConstraint dbsDocumentConstraint2 = mock(DBSDocumentConstraint.class);
    when(dbsDocumentConstraint2.isPersisted()).thenReturn(false);
    when(dbsDocumentConstraint2.getName()).thenReturn("");
    when(dbsDocumentConstraint2.getDataSource()).thenReturn(dbpDataSource2);
    SQLObjectEditor sqlObjectEditor = mock(SQLObjectEditor.class);
    ObjectCreateCommand objectCreateCommand =
        sqlObjectEditor.new ObjectCreateCommand(dbsDocumentConstraint2, "foo", new HashMap<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        DBException.class, () -> objectCreateCommand.validateCommand(monitor, new HashMap<>()));
    verify(dbpDataSource2).getInfo();
    verify(dbpDataSource).getSQLDialect();
    verify(dbpDataSourceInfo).getSupportedObjectTypes();
    verify(dbsDocumentConstraint).getDataSource();
    verify(dbsDocumentConstraint2).getDataSource();
    verify(dbsDocumentConstraint2).getName();
    verify(dbsDocumentConstraint2).isPersisted();
  }

  /**
   * Test ObjectDeleteCommand {@link ObjectDeleteCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link ObjectDeleteCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction[] ObjectDeleteCommand.getPersistActions(DBRProgressMonitor, DBCExecutionContext, Map)"
  })
  public void testObjectDeleteCommandGetPersistActions_thenReturnArrayLengthIsZero()
      throws DBException {
    // Arrange
    SQLObjectEditor sqlObjectEditor = mock(SQLObjectEditor.class);
    doNothing()
        .when(sqlObjectEditor)
        .addObjectDeleteActions(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBCExecutionContext>any(),
            Mockito.<List<DBEPersistAction>>any(),
            Mockito.<ObjectDeleteCommand>any(),
            Mockito.<Map<String, Object>>any());
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    ObjectDeleteCommand objectDeleteCommand =
        sqlObjectEditor.new ObjectDeleteCommand(dbsDocumentConstraint, "foo");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act
    DBEPersistAction[] actualPersistActions =
        objectDeleteCommand.getPersistActions(monitor, executionContext, new HashMap<>());

    // Assert
    verify(sqlObjectEditor)
        .addObjectDeleteActions(
            isA(DBRProgressMonitor.class),
            isA(DBCExecutionContext.class),
            isA(List.class),
            isA(ObjectDeleteCommand.class),
            isA(Map.class));
    assertEquals(0, actualPersistActions.length);
  }

  /**
   * Test ObjectDeleteCommand {@link ObjectDeleteCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectDeleteCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction[] ObjectDeleteCommand.getPersistActions(DBRProgressMonitor, DBCExecutionContext, Map)"
  })
  public void testObjectDeleteCommandGetPersistActions_thenThrowDBException() throws DBException {
    // Arrange
    SQLObjectEditor sqlObjectEditor = mock(SQLObjectEditor.class);
    doThrow(new DBException("An error occurred"))
        .when(sqlObjectEditor)
        .addObjectDeleteActions(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBCExecutionContext>any(),
            Mockito.<List<DBEPersistAction>>any(),
            Mockito.<ObjectDeleteCommand>any(),
            Mockito.<Map<String, Object>>any());
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    ObjectDeleteCommand objectDeleteCommand =
        sqlObjectEditor.new ObjectDeleteCommand(dbsDocumentConstraint, "foo");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> objectDeleteCommand.getPersistActions(monitor, executionContext, new HashMap<>()));
    verify(sqlObjectEditor)
        .addObjectDeleteActions(
            isA(DBRProgressMonitor.class),
            isA(DBCExecutionContext.class),
            isA(List.class),
            isA(ObjectDeleteCommand.class),
            isA(Map.class));
  }

  /**
   * Test ObjectRenameCommand {@link ObjectRenameCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}.
   *
   * <p>Method under test: {@link ObjectRenameCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction[] ObjectRenameCommand.getPersistActions(DBRProgressMonitor, DBCExecutionContext, Map)"
  })
  public void testObjectRenameCommandGetPersistActions() {
    // Arrange
    SQLObjectEditor sqlObjectEditor = mock(SQLObjectEditor.class);
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    ObjectRenameCommand objectRenameCommand =
        sqlObjectEditor
        .new ObjectRenameCommand(dbsDocumentConstraint, "foo", new HashMap<>(), "DocumentKey");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act and Assert
    assertEquals(
        0,
        objectRenameCommand.getPersistActions(monitor, executionContext, new HashMap<>()).length);
  }

  /**
   * Test ObjectRenameCommand {@link ObjectRenameCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}.
   *
   * <ul>
   *   <li>Then calls {@link SQLObjectEditor#addObjectRenameActions(DBRProgressMonitor,
   *       DBCExecutionContext, List, ObjectRenameCommand, Map)}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectRenameCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction[] ObjectRenameCommand.getPersistActions(DBRProgressMonitor, DBCExecutionContext, Map)"
  })
  public void testObjectRenameCommandGetPersistActions_thenCallsAddObjectRenameActions() {
    // Arrange
    SQLObjectEditor sqlObjectEditor = mock(SQLObjectEditor.class);
    doNothing()
        .when(sqlObjectEditor)
        .addObjectRenameActions(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBCExecutionContext>any(),
            Mockito.<List<DBEPersistAction>>any(),
            Mockito.<ObjectRenameCommand>any(),
            Mockito.<Map<String, Object>>any());
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    ObjectRenameCommand objectRenameCommand =
        sqlObjectEditor
        .new ObjectRenameCommand(dbsDocumentConstraint, "foo", new HashMap<>(), "foo");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act
    DBEPersistAction[] actualPersistActions =
        objectRenameCommand.getPersistActions(monitor, executionContext, new HashMap<>());

    // Assert
    verify(sqlObjectEditor)
        .addObjectRenameActions(
            isA(DBRProgressMonitor.class),
            isA(DBCExecutionContext.class),
            isA(List.class),
            isA(ObjectRenameCommand.class),
            isA(Map.class));
    assertEquals(0, actualPersistActions.length);
  }

  /**
   * Test ObjectRenameCommand {@link ObjectRenameCommand#ObjectRenameCommand(SQLObjectEditor,
   * DBSObject, String, Map, String)}.
   *
   * <p>Method under test: {@link ObjectRenameCommand#ObjectRenameCommand(SQLObjectEditor,
   * DBSObject, String, Map, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ObjectRenameCommand.<init>(SQLObjectEditor, DBSObject, String, Map, String)"
  })
  public void testObjectRenameCommandNewObjectRenameCommand() {
    // Arrange
    SQLObjectEditor sqlObjectEditor = mock(SQLObjectEditor.class);
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    ObjectRenameCommand actualObjectRenameCommand =
        sqlObjectEditor
        .new ObjectRenameCommand(dbsDocumentConstraint, "foo", new HashMap<>(), "foo");

    // Assert
    assertEquals("DocumentKey", actualObjectRenameCommand.getOldName());
    assertEquals("foo", actualObjectRenameCommand.getTitle());
    assertEquals("foo", actualObjectRenameCommand.getNewName());
    assertFalse(actualObjectRenameCommand.ignoreNestedCommands());
    assertFalse(actualObjectRenameCommand.isDisableSessionLogging());
    assertTrue(actualObjectRenameCommand.getOptions().isEmpty());
    assertTrue(actualObjectRenameCommand.isUndoable());
    assertSame(dbsDocumentConstraint, actualObjectRenameCommand.getObject());
  }

  /**
   * Test RefreshObjectReflector {@link RefreshObjectReflector#redoCommand(DBECommandAbstract)} with
   * {@code DBECommandAbstract}.
   *
   * <p>Method under test: {@link RefreshObjectReflector#redoCommand(DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefreshObjectReflector.redoCommand(DBECommandAbstract)"})
  public void testRefreshObjectReflectorRedoCommandWithDBECommandAbstract() {
    // Arrange
    RefreshObjectReflector<DBSObject> refreshObjectReflector = new RefreshObjectReflector<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    // Act
    refreshObjectReflector.redoCommand(new DBECommandAbstract<>(dbsDocumentConstraint, "Dr"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
  }

  /**
   * Test RefreshObjectReflector {@link RefreshObjectReflector#redoCommand(DBECommandAbstract)} with
   * {@code DBECommandAbstract}.
   *
   * <p>Method under test: {@link RefreshObjectReflector#redoCommand(DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefreshObjectReflector.redoCommand(DBECommandAbstract)"})
  public void testRefreshObjectReflectorRedoCommandWithDBECommandAbstract2() {
    // Arrange
    RefreshObjectReflector<DBSObject> refreshObjectReflector = new RefreshObjectReflector<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    refreshObjectReflector.redoCommand(
        new DBECommandAbstract<>(new DBVEntityForeignKey(entity), "Dr"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test RefreshObjectReflector {@link RefreshObjectReflector#redoCommand(DBECommandAbstract)} with
   * {@code DBECommandAbstract}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link RefreshObjectReflector#redoCommand(DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefreshObjectReflector.redoCommand(DBECommandAbstract)"})
  public void testRefreshObjectReflectorRedoCommandWithDBECommandAbstract_thenCallsFireEvent() {
    // Arrange
    RefreshObjectReflector<DBSObject> refreshObjectReflector = new RefreshObjectReflector<>();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    // Act
    refreshObjectReflector.redoCommand(new DBECommandAbstract<>(dbsDocumentConstraint, "Dr"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
  }

  /**
   * Test RefreshObjectReflector {@link RefreshObjectReflector#redoCommand(DBECommandAbstract)} with
   * {@code DBECommandAbstract}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link RefreshObjectReflector#redoCommand(DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefreshObjectReflector.redoCommand(DBECommandAbstract)"})
  public void testRefreshObjectReflectorRedoCommandWithDBECommandAbstract_thenCallsGetId() {
    // Arrange
    RefreshObjectReflector<DBSObject> refreshObjectReflector = new RefreshObjectReflector<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    refreshObjectReflector.redoCommand(
        new DBECommandAbstract<>(new DBVEntityForeignKey(entity), "Dr"));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }

  /**
   * Test RefreshObjectReflector {@link RefreshObjectReflector#undoCommand(DBECommandAbstract)} with
   * {@code DBECommandAbstract}.
   *
   * <p>Method under test: {@link RefreshObjectReflector#undoCommand(DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefreshObjectReflector.undoCommand(DBECommandAbstract)"})
  public void testRefreshObjectReflectorUndoCommandWithDBECommandAbstract() {
    // Arrange
    RefreshObjectReflector<DBSObject> refreshObjectReflector = new RefreshObjectReflector<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    // Act
    refreshObjectReflector.undoCommand(new DBECommandAbstract<>(dbsDocumentConstraint, "Dr"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(entity).getDataSource();
  }

  /**
   * Test RefreshObjectReflector {@link RefreshObjectReflector#undoCommand(DBECommandAbstract)} with
   * {@code DBECommandAbstract}.
   *
   * <p>Method under test: {@link RefreshObjectReflector#undoCommand(DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefreshObjectReflector.undoCommand(DBECommandAbstract)"})
  public void testRefreshObjectReflectorUndoCommandWithDBECommandAbstract2() {
    // Arrange
    RefreshObjectReflector<DBSObject> refreshObjectReflector = new RefreshObjectReflector<>();

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(null);

    DBVContainer parent = mock(DBVContainer.class);
    when(parent.getDataSource()).thenReturn(dbpDataSource);
    DBVContainer container = new DBVContainer(parent, "Name");
    DBVEntity entity = new DBVEntity(container, "Name", "Description Column Names");

    // Act
    refreshObjectReflector.undoCommand(
        new DBECommandAbstract<>(new DBVEntityForeignKey(entity), "Dr"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(parent).getDataSource();
  }

  /**
   * Test RefreshObjectReflector {@link RefreshObjectReflector#undoCommand(DBECommandAbstract)} with
   * {@code DBECommandAbstract}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#fireEvent(DBPEvent)}.
   * </ul>
   *
   * <p>Method under test: {@link RefreshObjectReflector#undoCommand(DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefreshObjectReflector.undoCommand(DBECommandAbstract)"})
  public void testRefreshObjectReflectorUndoCommandWithDBECommandAbstract_thenCallsFireEvent() {
    // Arrange
    RefreshObjectReflector<DBSObject> refreshObjectReflector = new RefreshObjectReflector<>();

    DBPDataSourceContainer dbpDataSourceContainer = mock(DBPDataSourceContainer.class);
    doNothing().when(dbpDataSourceContainer).fireEvent(Mockito.<DBPEvent>any());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getContainer()).thenReturn(dbpDataSourceContainer);

    DBSDocumentContainer entity = mock(DBSDocumentContainer.class);
    when(entity.getDataSource()).thenReturn(dbpDataSource);
    DBSDocumentConstraint dbsDocumentConstraint = new DBSDocumentConstraint(entity);

    // Act
    refreshObjectReflector.undoCommand(new DBECommandAbstract<>(dbsDocumentConstraint, "Dr"));

    // Assert
    verify(dbpDataSource).getContainer();
    verify(dbpDataSourceContainer).fireEvent(isA(DBPEvent.class));
    verify(entity).getDataSource();
  }

  /**
   * Test RefreshObjectReflector {@link RefreshObjectReflector#undoCommand(DBECommandAbstract)} with
   * {@code DBECommandAbstract}.
   *
   * <ul>
   *   <li>Then calls {@link DBPDataSourceContainer#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link RefreshObjectReflector#undoCommand(DBECommandAbstract)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RefreshObjectReflector.undoCommand(DBECommandAbstract)"})
  public void testRefreshObjectReflectorUndoCommandWithDBECommandAbstract_thenCallsGetId() {
    // Arrange
    RefreshObjectReflector<DBSObject> refreshObjectReflector = new RefreshObjectReflector<>();

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getId()).thenReturn("42");
    DBVModel targetModel = new DBVModel(dataSourceContainer);
    DBVModel container = new DBVModel("42", new HashMap<>());
    DBVContainer container2 = new DBVContainer(mock(DBVContainer.class), "Name");
    DBVEntity copy = new DBVEntity(container2, "Name", "Description Column Names");

    DBVEntity entity = new DBVEntity(container, copy, targetModel);

    // Act
    refreshObjectReflector.undoCommand(
        new DBECommandAbstract<>(new DBVEntityForeignKey(entity), "Dr"));

    // Assert
    verify(dataSourceContainer, atLeast(1)).getId();
  }
}
