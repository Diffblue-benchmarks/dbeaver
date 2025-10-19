package org.jkiss.dbeaver.model.impl.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPObject;
import org.jkiss.dbeaver.model.data.DBDNull;
import org.jkiss.dbeaver.model.edit.DBECommand;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBECommandAbstractDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBECommandAbstract#DBECommandAbstract(DBPObject, String)}
   *   <li>{@link DBECommandAbstract#setDisableSessionLogging(boolean)}
   *   <li>{@link DBECommandAbstract#setIgnoreNestedCommands(boolean)}
   *   <li>{@link DBECommandAbstract#updateModel()}
   *   <li>{@link DBECommandAbstract#validateCommand(DBRProgressMonitor, Map)}
   *   <li>{@link DBECommandAbstract#getObject()}
   *   <li>{@link DBECommandAbstract#getTitle()}
   *   <li>{@link DBECommandAbstract#ignoreNestedCommands()}
   *   <li>{@link DBECommandAbstract#isDisableSessionLogging()}
   *   <li>{@link DBECommandAbstract#isUndoable()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBECommandAbstract.<init>(DBPObject, String)",
    "DBPObject DBECommandAbstract.getObject()",
    "String DBECommandAbstract.getTitle()",
    "boolean DBECommandAbstract.ignoreNestedCommands()",
    "boolean DBECommandAbstract.isDisableSessionLogging()",
    "boolean DBECommandAbstract.isUndoable()",
    "void DBECommandAbstract.setDisableSessionLogging(boolean)",
    "void DBECommandAbstract.setIgnoreNestedCommands(boolean)",
    "void DBECommandAbstract.updateModel()",
    "void DBECommandAbstract.validateCommand(DBRProgressMonitor, Map)"
  })
  public void testGettersAndSetters() throws DBException {
    // Arrange and Act
    DBECommandAbstract<DBPObject> actualDbeCommandAbstract =
        new DBECommandAbstract<>(DBDNull.INSTANCE, "Dr");
    actualDbeCommandAbstract.setDisableSessionLogging(true);
    actualDbeCommandAbstract.setIgnoreNestedCommands(true);
    actualDbeCommandAbstract.updateModel();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    actualDbeCommandAbstract.validateCommand(monitor, new HashMap<>());
    DBPObject actualObject = actualDbeCommandAbstract.getObject();
    String actualTitle = actualDbeCommandAbstract.getTitle();
    boolean actualIgnoreNestedCommandsResult = actualDbeCommandAbstract.ignoreNestedCommands();
    boolean actualIsDisableSessionLoggingResult =
        actualDbeCommandAbstract.isDisableSessionLogging();

    // Assert
    assertEquals("Dr", actualTitle);
    assertTrue(actualIgnoreNestedCommandsResult);
    assertTrue(actualIsDisableSessionLoggingResult);
    assertTrue(actualDbeCommandAbstract.isUndoable());
    assertSame(((DBDNull) actualObject).INSTANCE, actualObject);
  }

  /**
   * Test {@link DBECommandAbstract#merge(DBECommand, Map)}.
   *
   * <ul>
   *   <li>Then {@link HashMap#HashMap()} Empty.
   * </ul>
   *
   * <p>Method under test: {@link DBECommandAbstract#merge(DBECommand, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBECommand DBECommandAbstract.merge(DBECommand, Map)"})
  public void testMerge_thenHashMapEmpty() {
    // Arrange
    DBECommandAbstract<DBPObject> dbeCommandAbstract =
        new DBECommandAbstract<>(DBDNull.INSTANCE, "Dr");
    DBECommand<?> prevCommand = mock(DBECommand.class);
    HashMap<Object, Object> userParams = new HashMap<>();

    // Act
    DBECommand<?> actualMergeResult = dbeCommandAbstract.merge(prevCommand, userParams);

    // Assert
    assertTrue(userParams.isEmpty());
    assertSame(dbeCommandAbstract, actualMergeResult);
  }

  /**
   * Test {@link DBECommandAbstract#getPersistActions(DBRProgressMonitor, DBCExecutionContext,
   * Map)}.
   *
   * <p>Method under test: {@link DBECommandAbstract#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.edit.DBEPersistAction[] DBECommandAbstract.getPersistActions(DBRProgressMonitor, DBCExecutionContext, Map)"
  })
  public void testGetPersistActions() throws DBException {
    // Arrange
    DBECommandAbstract<DBPObject> dbeCommandAbstract =
        new DBECommandAbstract<>(DBDNull.INSTANCE, "Dr");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act and Assert
    assertNull(dbeCommandAbstract.getPersistActions(monitor, executionContext, new HashMap<>()));
  }
}
