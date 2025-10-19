package org.jkiss.dbeaver.model.edit.prop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.DBPObject;
import org.jkiss.dbeaver.model.data.DBDNull;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.edit.SQLDatabasePersistAction;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBECommandPropertyDiffblueTest {
  /**
   * Test {@link DBECommandProperty#DBECommandProperty(DBPObject, DBEPropertyHandler)}.
   *
   * <p>Method under test: {@link DBECommandProperty#DBECommandProperty(DBPObject,
   * DBEPropertyHandler)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBECommandProperty.<init>(DBPObject, DBEPropertyHandler)"})
  public void testNewDBECommandProperty() {
    // Arrange
    DBPObject dbpObject = mock(DBPObject.class);
    DBEPropertyHandler<DBPObject> handler = mock(DBEPropertyHandler.class);

    // Act
    DBECommandProperty<DBPObject> actualDbeCommandProperty =
        new DBECommandProperty<>(dbpObject, handler);

    // Assert
    assertNull(actualDbeCommandProperty.getNewValue());
    assertNull(actualDbeCommandProperty.getOldValue());
    assertFalse(actualDbeCommandProperty.ignoreNestedCommands());
    assertFalse(actualDbeCommandProperty.isDisableSessionLogging());
    assertTrue(actualDbeCommandProperty.isUndoable());
    assertSame(dbpObject, actualDbeCommandProperty.getObject());
    assertSame(handler, actualDbeCommandProperty.getHandler());
  }

  /**
   * Test {@link DBECommandProperty#DBECommandProperty(DBPObject, DBEPropertyHandler, Object,
   * Object)}.
   *
   * <p>Method under test: {@link DBECommandProperty#DBECommandProperty(DBPObject,
   * DBEPropertyHandler, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBECommandProperty.<init>(DBPObject, DBEPropertyHandler, Object, Object)"
  })
  public void testNewDBECommandProperty2() {
    // Arrange
    DBPObject dbpObject = mock(DBPObject.class);
    DBEPropertyHandler<DBPObject> handler = mock(DBEPropertyHandler.class);
    Object object = DBPEvent.RENAME;

    // Act
    DBECommandProperty<DBPObject> actualDbeCommandProperty =
        new DBECommandProperty<>(dbpObject, handler, DBPEvent.RENAME, object);

    // Assert
    assertFalse(actualDbeCommandProperty.ignoreNestedCommands());
    assertFalse(actualDbeCommandProperty.isDisableSessionLogging());
    assertTrue(actualDbeCommandProperty.isUndoable());
    assertSame(object, actualDbeCommandProperty.getNewValue());
    assertSame(object, actualDbeCommandProperty.getOldValue());
    assertSame(dbpObject, actualDbeCommandProperty.getObject());
    assertSame(handler, actualDbeCommandProperty.getHandler());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBECommandProperty#resetValue()}
   *   <li>{@link DBECommandProperty#getHandler()}
   *   <li>{@link DBECommandProperty#getNewValue()}
   *   <li>{@link DBECommandProperty#getOldValue()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPropertyHandler DBECommandProperty.getHandler()",
    "Object DBECommandProperty.getNewValue()",
    "Object DBECommandProperty.getOldValue()",
    "void DBECommandProperty.resetValue()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBECommandProperty<DBPObject> dbeCommandProperty =
        new DBECommandProperty<>(DBDNull.INSTANCE, mock(DBEPropertyHandler.class));

    // Act
    dbeCommandProperty.resetValue();
    dbeCommandProperty.getHandler();
    Object actualNewValue = dbeCommandProperty.getNewValue();

    // Assert
    assertNull(actualNewValue);
    assertNull(dbeCommandProperty.getOldValue());
  }

  /**
   * Test {@link DBECommandProperty#setNewValue(Object)}.
   *
   * <p>Method under test: {@link DBECommandProperty#setNewValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBECommandProperty.setNewValue(Object)"})
  public void testSetNewValue() {
    // Arrange
    DBECommandProperty<DBPObject> dbeCommandProperty =
        new DBECommandProperty<>(mock(DBPObject.class), mock(DBEPropertyHandler.class));
    Object object = DBPEvent.RENAME;

    // Act
    dbeCommandProperty.setNewValue(object);

    // Assert
    assertSame(object, dbeCommandProperty.getNewValue());
  }

  /**
   * Test {@link DBECommandProperty#validateCommand(DBRProgressMonitor, Map)}.
   *
   * <ul>
   *   <li>Given {@link DBEPropertyValidator} {@link DBEPropertyValidator#validate(DBPObject,
   *       Object)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link DBECommandProperty#validateCommand(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBECommandProperty.validateCommand(DBRProgressMonitor, Map)"})
  public void testValidateCommand_givenDBEPropertyValidatorValidateDoesNothing()
      throws DBException {
    // Arrange
    DBEPropertyValidator<DBPObject> handler = mock(DBEPropertyValidator.class);
    doNothing().when(handler).validate(Mockito.<DBPObject>any(), Mockito.<Object>any());
    DBECommandProperty<DBPObject> dbeCommandProperty =
        new DBECommandProperty<>(DBDNull.INSTANCE, handler);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    dbeCommandProperty.validateCommand(monitor, new HashMap<>());

    // Assert
    verify(handler).validate(isA(DBPObject.class), isNull());
  }

  /**
   * Test {@link DBECommandProperty#validateCommand(DBRProgressMonitor, Map)}.
   *
   * <ul>
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link DBECommandProperty#validateCommand(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBECommandProperty.validateCommand(DBRProgressMonitor, Map)"})
  public void testValidateCommand_thenDoesNotThrow() throws DBException {
    // Arrange
    DBECommandProperty<DBPObject> dbeCommandProperty =
        new DBECommandProperty<>(DBDNull.INSTANCE, mock(DBEPropertyHandler.class));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    dbeCommandProperty.validateCommand(monitor, new HashMap<>());
  }

  /**
   * Test {@link DBECommandProperty#validateCommand(DBRProgressMonitor, Map)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBECommandProperty#validateCommand(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBECommandProperty.validateCommand(DBRProgressMonitor, Map)"})
  public void testValidateCommand_thenThrowDBException() throws DBException {
    // Arrange
    DBEPropertyValidator<DBPObject> handler = mock(DBEPropertyValidator.class);
    doThrow(new DBException("An error occurred"))
        .when(handler)
        .validate(Mockito.<DBPObject>any(), Mockito.<Object>any());
    DBECommandProperty<DBPObject> dbeCommandProperty =
        new DBECommandProperty<>(DBDNull.INSTANCE, handler);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        DBException.class, () -> dbeCommandProperty.validateCommand(monitor, new HashMap<>()));
    verify(handler).validate(isA(DBPObject.class), isNull());
  }

  /**
   * Test {@link DBECommandProperty#updateModel()}.
   *
   * <ul>
   *   <li>Then calls {@link DBEPropertyUpdater#updateModel(DBPObject, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link DBECommandProperty#updateModel()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBECommandProperty.updateModel()"})
  public void testUpdateModel_thenCallsUpdateModel() {
    // Arrange
    DBEPropertyUpdater<DBPObject> handler = mock(DBEPropertyUpdater.class);
    doNothing().when(handler).updateModel(Mockito.<DBPObject>any(), Mockito.<Object>any());
    DBECommandProperty<DBPObject> dbeCommandProperty =
        new DBECommandProperty<>(DBDNull.INSTANCE, handler);

    // Act
    dbeCommandProperty.updateModel();

    // Assert
    verify(handler).updateModel(isA(DBPObject.class), isNull());
  }

  /**
   * Test {@link DBECommandProperty#getPersistActions(DBRProgressMonitor, DBCExecutionContext,
   * Map)}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link DBECommandProperty#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction[] DBECommandProperty.getPersistActions(DBRProgressMonitor, DBCExecutionContext, Map)"
  })
  public void testGetPersistActions_thenReturnArrayLengthIsOne() {
    // Arrange
    DBEPropertyPersister<DBPObject> handler = mock(DBEPropertyPersister.class);
    SQLDatabasePersistAction sqlDatabasePersistAction = new SQLDatabasePersistAction("Script");
    when(handler.getPersistActions(Mockito.<DBPObject>any(), Mockito.<Object>any()))
        .thenReturn(new DBEPersistAction[] {sqlDatabasePersistAction});
    DBECommandProperty<DBPObject> dbeCommandProperty =
        new DBECommandProperty<>(DBDNull.INSTANCE, handler);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act
    DBEPersistAction[] actualPersistActions =
        dbeCommandProperty.getPersistActions(monitor, executionContext, new HashMap<>());

    // Assert
    verify(handler).getPersistActions(isA(DBPObject.class), isNull());
    assertEquals(1, actualPersistActions.length);
    assertSame(sqlDatabasePersistAction, actualPersistActions[0]);
  }

  /**
   * Test {@link DBECommandProperty#getPersistActions(DBRProgressMonitor, DBCExecutionContext,
   * Map)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBECommandProperty#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction[] DBECommandProperty.getPersistActions(DBRProgressMonitor, DBCExecutionContext, Map)"
  })
  public void testGetPersistActions_thenReturnNull() {
    // Arrange
    DBECommandProperty<DBPObject> dbeCommandProperty =
        new DBECommandProperty<>(mock(DBPObject.class), mock(DBEPropertyHandler.class));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act and Assert
    assertNull(dbeCommandProperty.getPersistActions(monitor, executionContext, new HashMap<>()));
  }
}
