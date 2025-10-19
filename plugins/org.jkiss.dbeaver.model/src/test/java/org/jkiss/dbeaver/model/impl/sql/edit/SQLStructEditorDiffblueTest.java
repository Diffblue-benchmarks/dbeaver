package org.jkiss.dbeaver.model.impl.sql.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.edit.DBECommand;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLStructEditor.StructCreateCommand;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLStructEditorDiffblueTest {
  /**
   * Test StructCreateCommand {@link StructCreateCommand#aggregateCommand(DBECommand)}.
   *
   * <p>Method under test: {@link StructCreateCommand#aggregateCommand(DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean StructCreateCommand.aggregateCommand(DBECommand)"})
  public void testStructCreateCommandAggregateCommand() {
    // Arrange
    SQLStructEditor sqlStructEditor = mock(SQLStructEditor.class);
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    StructCreateCommand structCreateCommand =
        sqlStructEditor.new StructCreateCommand(dbsDocumentConstraint, "foo", new HashMap<>());

    // Act and Assert
    assertFalse(structCreateCommand.aggregateCommand(mock(DBECommand.class)));
  }

  /**
   * Test StructCreateCommand {@link StructCreateCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link StructCreateCommand#getPersistActions(DBRProgressMonitor,
   * DBCExecutionContext, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBEPersistAction[] StructCreateCommand.getPersistActions(DBRProgressMonitor, DBCExecutionContext, Map)"
  })
  public void testStructCreateCommandGetPersistActions_thenThrowDBException() throws DBException {
    // Arrange
    SQLStructEditor sqlStructEditor = mock(SQLStructEditor.class);
    doThrow(new DBException("An error occurred"))
        .when(sqlStructEditor)
        .addStructObjectCreateActions(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBCExecutionContext>any(),
            Mockito.<List<DBEPersistAction>>any(),
            Mockito.<StructCreateCommand>any(),
            Mockito.<Map<String, Object>>any());
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));
    StructCreateCommand structCreateCommand =
        sqlStructEditor.new StructCreateCommand(dbsDocumentConstraint, "foo", new HashMap<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);

    // Act and Assert
    assertThrows(
        DBException.class,
        () -> structCreateCommand.getPersistActions(monitor, executionContext, new HashMap<>()));
    verify(sqlStructEditor)
        .addStructObjectCreateActions(
            isA(DBRProgressMonitor.class),
            isA(DBCExecutionContext.class),
            isA(List.class),
            isA(StructCreateCommand.class),
            isA(Map.class));
  }

  /**
   * Test StructCreateCommand {@link StructCreateCommand#StructCreateCommand(SQLStructEditor,
   * DBSObject, String, Map)}.
   *
   * <p>Method under test: {@link StructCreateCommand#StructCreateCommand(SQLStructEditor,
   * DBSObject, String, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void StructCreateCommand.<init>(SQLStructEditor, DBSObject, String, Map)"})
  public void testStructCreateCommandNewStructCreateCommand() {
    // Arrange
    SQLStructEditor sqlStructEditor = mock(SQLStructEditor.class);
    DBSDocumentConstraint dbsDocumentConstraint =
        new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    StructCreateCommand actualStructCreateCommand =
        sqlStructEditor.new StructCreateCommand(dbsDocumentConstraint, "foo", new HashMap<>());

    // Assert
    assertEquals("foo", actualStructCreateCommand.getTitle());
    assertEquals(1, actualStructCreateCommand.getObjectCommands().size());
    assertFalse(actualStructCreateCommand.ignoreNestedCommands());
    assertFalse(actualStructCreateCommand.isDisableSessionLogging());
    assertTrue(actualStructCreateCommand.getProperties().isEmpty());
    assertTrue(actualStructCreateCommand.getOptions().isEmpty());
    assertTrue(actualStructCreateCommand.isUndoable());
    assertSame(dbsDocumentConstraint, actualStructCreateCommand.getObject());
  }
}
