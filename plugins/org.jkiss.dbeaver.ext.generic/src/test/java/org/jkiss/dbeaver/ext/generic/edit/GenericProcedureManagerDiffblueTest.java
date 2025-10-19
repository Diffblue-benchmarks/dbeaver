package org.jkiss.dbeaver.ext.generic.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.ext.generic.model.GenericProcedure;
import org.jkiss.dbeaver.ext.generic.model.GenericStructContainer;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCFeatureNotSupportedException;
import org.jkiss.dbeaver.model.impl.edit.SQLDatabasePersistAction;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor.ObjectCreateCommand;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GenericProcedureManagerDiffblueTest {
  /**
   * Test {@link GenericProcedureManager#getMakerOptions(DBPDataSource)}.
   *
   * <p>Method under test: {@link GenericProcedureManager#getMakerOptions(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long GenericProcedureManager.getMakerOptions(DBPDataSource)"})
  public void testGetMakerOptions() {
    // Arrange, Act and Assert
    assertEquals(4L, new GenericProcedureManager().getMakerOptions(mock(DBPDataSource.class)));
  }

  /**
   * Test {@link GenericProcedureManager#addObjectCreateActions(DBRProgressMonitor,
   * DBCExecutionContext, List, ObjectCreateCommand, Map)}.
   *
   * <ul>
   *   <li>Given {@link SQLDatabasePersistAction#SQLDatabasePersistAction(String)} with script is
   *       {@code Not supported}.
   * </ul>
   *
   * <p>Method under test: {@link GenericProcedureManager#addObjectCreateActions(DBRProgressMonitor,
   * DBCExecutionContext, List, ObjectCreateCommand, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void GenericProcedureManager.addObjectCreateActions(DBRProgressMonitor, DBCExecutionContext, List, ObjectCreateCommand, Map)"
  })
  public void testAddObjectCreateActions_givenSQLDatabasePersistActionWithScriptIsNotSupported()
      throws DBCException {
    // Arrange
    GenericProcedureManager genericProcedureManager = new GenericProcedureManager();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    ArrayList<DBEPersistAction> actions = new ArrayList<>();
    actions.add(new SQLDatabasePersistAction("Not supported"));
    ObjectCreateCommand command = mock(ObjectCreateCommand.class);

    // Act and Assert
    assertThrows(
        DBCFeatureNotSupportedException.class,
        () ->
            genericProcedureManager.addObjectCreateActions(
                monitor, executionContext, actions, command, new HashMap<>()));
  }

  /**
   * Test {@link GenericProcedureManager#addObjectCreateActions(DBRProgressMonitor,
   * DBCExecutionContext, List, ObjectCreateCommand, Map)}.
   *
   * <ul>
   *   <li>Given {@link SQLDatabasePersistAction#SQLDatabasePersistAction(String)} with script is
   *       {@code Not supported}.
   * </ul>
   *
   * <p>Method under test: {@link GenericProcedureManager#addObjectCreateActions(DBRProgressMonitor,
   * DBCExecutionContext, List, ObjectCreateCommand, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void GenericProcedureManager.addObjectCreateActions(DBRProgressMonitor, DBCExecutionContext, List, ObjectCreateCommand, Map)"
  })
  public void testAddObjectCreateActions_givenSQLDatabasePersistActionWithScriptIsNotSupported2()
      throws DBCException {
    // Arrange
    GenericProcedureManager genericProcedureManager = new GenericProcedureManager();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    ArrayList<DBEPersistAction> actions = new ArrayList<>();
    actions.add(new SQLDatabasePersistAction("Not supported"));
    actions.add(new SQLDatabasePersistAction("Not supported"));
    ObjectCreateCommand command = mock(ObjectCreateCommand.class);

    // Act and Assert
    assertThrows(
        DBCFeatureNotSupportedException.class,
        () ->
            genericProcedureManager.addObjectCreateActions(
                monitor, executionContext, actions, command, new HashMap<>()));
  }

  /**
   * Test {@link GenericProcedureManager#addObjectCreateActions(DBRProgressMonitor,
   * DBCExecutionContext, List, ObjectCreateCommand, Map)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link GenericProcedureManager#addObjectCreateActions(DBRProgressMonitor,
   * DBCExecutionContext, List, ObjectCreateCommand, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void GenericProcedureManager.addObjectCreateActions(DBRProgressMonitor, DBCExecutionContext, List, ObjectCreateCommand, Map)"
  })
  public void testAddObjectCreateActions_whenArrayList() throws DBCException {
    // Arrange
    GenericProcedureManager genericProcedureManager = new GenericProcedureManager();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    ArrayList<DBEPersistAction> actions = new ArrayList<>();
    ObjectCreateCommand command = mock(ObjectCreateCommand.class);

    // Act and Assert
    assertThrows(
        DBCFeatureNotSupportedException.class,
        () ->
            genericProcedureManager.addObjectCreateActions(
                monitor, executionContext, actions, command, new HashMap<>()));
  }

  /**
   * Test {@link GenericProcedureManager#canCreateObject(Object)}.
   *
   * <p>Method under test: {@link GenericProcedureManager#canCreateObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean GenericProcedureManager.canCreateObject(Object)"})
  public void testCanCreateObject() {
    // Arrange, Act and Assert
    assertFalse(new GenericProcedureManager().canCreateObject("Container"));
  }
}
