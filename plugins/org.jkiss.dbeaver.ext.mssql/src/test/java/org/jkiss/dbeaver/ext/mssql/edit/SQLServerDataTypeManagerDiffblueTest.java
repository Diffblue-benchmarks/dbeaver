package org.jkiss.dbeaver.ext.mssql.edit;

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
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.ext.mssql.model.SQLServerDataType;
import org.jkiss.dbeaver.ext.mssql.model.SQLServerSchema;
import org.jkiss.dbeaver.model.DBPDataKind;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.edit.SQLDatabasePersistAction;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor.ObjectCreateCommand;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerDataTypeManagerDiffblueTest {
  /**
   * Test {@link SQLServerDataTypeManager#canEditObject(SQLServerDataType)} with {@code
   * SQLServerDataType}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLServerDataTypeManager#canEditObject(SQLServerDataType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLServerDataTypeManager.canEditObject(SQLServerDataType)"})
  public void testCanEditObjectWithSQLServerDataType_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new SQLServerDataTypeManager().canEditObject(null));
  }

  /**
   * Test {@link SQLServerDataTypeManager#canCreateObject(Object)}.
   *
   * <p>Method under test: {@link SQLServerDataTypeManager#canCreateObject(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLServerDataTypeManager.canCreateObject(Object)"})
  public void testCanCreateObject() {
    // Arrange, Act and Assert
    assertFalse(new SQLServerDataTypeManager().canCreateObject("Container"));
  }

  /**
   * Test {@link SQLServerDataTypeManager#canDeleteObject(SQLServerDataType)} with {@code
   * SQLServerDataType}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLServerDataTypeManager#canDeleteObject(SQLServerDataType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLServerDataTypeManager.canDeleteObject(SQLServerDataType)"})
  public void testCanDeleteObjectWithSQLServerDataType_thenReturnFalse() {
    // Arrange
    SQLServerDataTypeManager sqlServerDataTypeManager = new SQLServerDataTypeManager();
    SQLServerDataType object = new SQLServerDataType(null, "Name", 1, DBPDataKind.BOOLEAN, 42);

    // Act
    boolean actualCanDeleteObjectResult = sqlServerDataTypeManager.canDeleteObject(object);

    // Assert
    assertFalse(actualCanDeleteObjectResult);
  }

  /**
   * Test {@link SQLServerDataTypeManager#addObjectCreateActions(DBRProgressMonitor,
   * DBCExecutionContext, List, ObjectCreateCommand, Map)}.
   *
   * <p>Method under test: {@link
   * SQLServerDataTypeManager#addObjectCreateActions(DBRProgressMonitor, DBCExecutionContext, List,
   * ObjectCreateCommand, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLServerDataTypeManager.addObjectCreateActions(DBRProgressMonitor, DBCExecutionContext, List, ObjectCreateCommand, Map)"
  })
  public void testAddObjectCreateActions() throws DBException {
    // Arrange
    SQLServerDataTypeManager sqlServerDataTypeManager = new SQLServerDataTypeManager();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    ArrayList<DBEPersistAction> actions = new ArrayList<>();
    actions.add(new SQLDatabasePersistAction("Not implemented"));
    ObjectCreateCommand command = mock(ObjectCreateCommand.class);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            sqlServerDataTypeManager.addObjectCreateActions(
                monitor, executionContext, actions, command, new HashMap<>()));
  }

  /**
   * Test {@link SQLServerDataTypeManager#addObjectCreateActions(DBRProgressMonitor,
   * DBCExecutionContext, List, ObjectCreateCommand, Map)}.
   *
   * <p>Method under test: {@link
   * SQLServerDataTypeManager#addObjectCreateActions(DBRProgressMonitor, DBCExecutionContext, List,
   * ObjectCreateCommand, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLServerDataTypeManager.addObjectCreateActions(DBRProgressMonitor, DBCExecutionContext, List, ObjectCreateCommand, Map)"
  })
  public void testAddObjectCreateActions2() throws DBException {
    // Arrange
    SQLServerDataTypeManager sqlServerDataTypeManager = new SQLServerDataTypeManager();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    ArrayList<DBEPersistAction> actions = new ArrayList<>();
    actions.add(new SQLDatabasePersistAction("Not implemented"));
    actions.add(new SQLDatabasePersistAction("Not implemented"));
    ObjectCreateCommand command = mock(ObjectCreateCommand.class);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            sqlServerDataTypeManager.addObjectCreateActions(
                monitor, executionContext, actions, command, new HashMap<>()));
  }

  /**
   * Test {@link SQLServerDataTypeManager#addObjectCreateActions(DBRProgressMonitor,
   * DBCExecutionContext, List, ObjectCreateCommand, Map)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLServerDataTypeManager#addObjectCreateActions(DBRProgressMonitor, DBCExecutionContext, List,
   * ObjectCreateCommand, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLServerDataTypeManager.addObjectCreateActions(DBRProgressMonitor, DBCExecutionContext, List, ObjectCreateCommand, Map)"
  })
  public void testAddObjectCreateActions_whenArrayList() throws DBException {
    // Arrange
    SQLServerDataTypeManager sqlServerDataTypeManager = new SQLServerDataTypeManager();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    ArrayList<DBEPersistAction> actions = new ArrayList<>();
    ObjectCreateCommand command = mock(ObjectCreateCommand.class);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            sqlServerDataTypeManager.addObjectCreateActions(
                monitor, executionContext, actions, command, new HashMap<>()));
  }

  /**
   * Test {@link SQLServerDataTypeManager#getMakerOptions(DBPDataSource)}.
   *
   * <p>Method under test: {@link SQLServerDataTypeManager#getMakerOptions(DBPDataSource)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"long SQLServerDataTypeManager.getMakerOptions(DBPDataSource)"})
  public void testGetMakerOptions() {
    // Arrange, Act and Assert
    assertEquals(4L, new SQLServerDataTypeManager().getMakerOptions(mock(DBPDataSource.class)));
  }
}
