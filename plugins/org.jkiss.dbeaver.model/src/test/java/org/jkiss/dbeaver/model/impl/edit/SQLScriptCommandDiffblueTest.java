package org.jkiss.dbeaver.model.impl.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.edit.DBEPersistAction.ActionType;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLScriptCommandDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLScriptCommand#SQLScriptCommand(DBSObject, String, String)}
   *   <li>{@link SQLScriptCommand#updateModel()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLScriptCommand.<init>(DBSObject, String, String)",
    "void SQLScriptCommand.updateModel()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    SQLScriptCommand<DBSObject> actualSqlScriptCommand =
        new SQLScriptCommand<>(dbsDocumentConstraint, "Dr", "Script");
    actualSqlScriptCommand.updateModel();

    // Assert
    assertEquals("Dr", actualSqlScriptCommand.getTitle());
    assertFalse(actualSqlScriptCommand.isDisableSessionLogging());
    assertSame(dbsDocumentConstraint, actualSqlScriptCommand.getObject());
  }

  /**
   * Test {@link SQLScriptCommand#getPersistActions(DBRProgressMonitor, DBCExecutionContext, Map)}.
   *
   * <p>Method under test: {@link SQLScriptCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction[] SQLScriptCommand.getPersistActions(DBRProgressMonitor, DBCExecutionContext, Map)"
  })
  public void testGetPersistActions() {
    // Arrange
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    SQLScriptCommand<DBSObject> sqlScriptCommand =
        new SQLScriptCommand<>(dbsDocumentConstraint, "Dr", "Script");
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act
    DBEPersistAction[] actualPersistActions =
        sqlScriptCommand.getPersistActions(monitor, executionContext, new HashMap<>());

    // Assert
    DBEPersistAction dbePersistAction = actualPersistActions[0];
    assertTrue(dbePersistAction instanceof SQLDatabasePersistAction);
    assertEquals("Dr", dbePersistAction.getTitle());
    assertEquals("Script", dbePersistAction.getScript());
    assertEquals(1, actualPersistActions.length);
    assertEquals(ActionType.NORMAL, dbePersistAction.getType());
    assertFalse(dbePersistAction.isComplex());
  }
}
