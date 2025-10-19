package org.jkiss.dbeaver.model.impl.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceInfo;
import org.jkiss.dbeaver.model.DBPObject;
import org.jkiss.dbeaver.model.edit.DBECommand;
import org.jkiss.dbeaver.model.edit.DBECommandListener;
import org.jkiss.dbeaver.model.edit.DBECommandReflector;
import org.jkiss.dbeaver.model.exec.DBCExecutionContext;
import org.jkiss.dbeaver.model.exec.DBCExecutionPurpose;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.jkiss.dbeaver.model.impl.edit.AbstractCommandContext.CommandInfo;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor;
import org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor.RefreshObjectReflector;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AbstractCommandContextDiffblueTest {
  /**
   * Test CommandInfo getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CommandInfo#CommandInfo(DBECommand, DBECommandReflector)}
   *   <li>{@link CommandInfo#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CommandInfo.<init>(DBECommand, DBECommandReflector)",
    "String CommandInfo.toString()"
  })
  public void testCommandInfoGettersAndSetters() {
    // Arrange and Act
    CommandInfo actualCommandInfo = new CommandInfo(mock(DBECommand.class), null);
    actualCommandInfo.toString();

    // Assert
    assertNull(actualCommandInfo.persistActions);
    assertNull(actualCommandInfo.reflector);
    assertNull(actualCommandInfo.mergedBy);
    assertNull(actualCommandInfo.prevInBatch);
    assertFalse(actualCommandInfo.executed);
  }

  /**
   * Test {@link AbstractCommandContext#isDirty()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCommandContext#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractCommandContext.isDirty()"})
  public void testIsDirty_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new TestCommandContext(mock(DBCExecutionContext.class), true).isDirty());
  }

  /**
   * Test {@link AbstractCommandContext#isDirty()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCommandContext#isDirty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractCommandContext.isDirty()"})
  public void testIsDirty_thenThrowIllegalStateException() {
    // Arrange
    DBECommand command = mock(DBECommand.class);
    when(command.getObject()).thenThrow(new IllegalStateException());

    TestCommandContext testCommandContext =
        new TestCommandContext(mock(DBCExecutionContext.class), true);
    testCommandContext.addCommand(command, new RefreshObjectReflector<>());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> testCommandContext.isDirty());
    verify(command).getObject();
  }

  /**
   * Test {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}.
   *
   * <p>Method under test: {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractCommandContext.saveChanges(DBRProgressMonitor, Map)"})
  public void testSaveChanges() throws DBException {
    // Arrange
    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsTransactionsForDDL()).thenThrow(new IllegalStateException());

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.isConnected()).thenReturn(true);
    TestCommandContext testCommandContext = new TestCommandContext(executionContext, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> testCommandContext.saveChanges(monitor, new HashMap<>()));
    verify(dbpDataSource).getInfo();
    verify(dbpDataSourceInfo).supportsTransactionsForDDL();
    verify(executionContext).getDataSource();
    verify(executionContext, atLeast(1)).isConnected();
  }

  /**
   * Test {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}.
   *
   * <p>Method under test: {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractCommandContext.saveChanges(DBRProgressMonitor, Map)"})
  public void testSaveChanges2() throws DBException {
    // Arrange
    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsTransactionsForDDL()).thenReturn(true);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.isConnected()).thenReturn(true);
    TestCommandContext testCommandContext = new TestCommandContext(executionContext, false);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    testCommandContext.saveChanges(monitor, new HashMap<>());

    // Assert
    verify(dbpDataSource).getInfo();
    verify(dbpDataSourceInfo).supportsTransactionsForDDL();
    verify(executionContext).getDataSource();
    verify(executionContext, atLeast(1)).isConnected();
  }

  /**
   * Test {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionContext} {@link DBCExecutionContext#getDataSource()} throw
   *       {@link IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractCommandContext.saveChanges(DBRProgressMonitor, Map)"})
  public void testSaveChanges_givenDBCExecutionContextGetDataSourceThrowIllegalStateException()
      throws DBException {
    // Arrange
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenThrow(new IllegalStateException());
    when(executionContext.isConnected()).thenReturn(true);
    TestCommandContext testCommandContext = new TestCommandContext(executionContext, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> testCommandContext.saveChanges(monitor, new HashMap<>()));
    verify(executionContext).getDataSource();
    verify(executionContext, atLeast(1)).isConnected();
  }

  /**
   * Test {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}.
   *
   * <ul>
   *   <li>Given {@link DBCExecutionContext} {@link DBCExecutionContext#isConnected()} throw {@link
   *       IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractCommandContext.saveChanges(DBRProgressMonitor, Map)"})
  public void testSaveChanges_givenDBCExecutionContextIsConnectedThrowIllegalStateException()
      throws DBException {
    // Arrange
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.isConnected()).thenThrow(new IllegalStateException());
    TestCommandContext testCommandContext = new TestCommandContext(executionContext, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> testCommandContext.saveChanges(monitor, new HashMap<>()));
    verify(executionContext).isConnected();
  }

  /**
   * Test {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSource} {@link DBPDataSource#getInfo()} throw {@link
   *       IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractCommandContext.saveChanges(DBRProgressMonitor, Map)"})
  public void testSaveChanges_givenDBPDataSourceGetInfoThrowIllegalStateException()
      throws DBException {
    // Arrange
    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenThrow(new IllegalStateException());

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.isConnected()).thenReturn(true);
    TestCommandContext testCommandContext = new TestCommandContext(executionContext, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> testCommandContext.saveChanges(monitor, new HashMap<>()));
    verify(dbpDataSource).getInfo();
    verify(executionContext).getDataSource();
    verify(executionContext, atLeast(1)).isConnected();
  }

  /**
   * Test {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}.
   *
   * <ul>
   *   <li>Given {@link DBPDataSourceInfo} {@link DBPDataSourceInfo#supportsTransactionsForDDL()}
   *       return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractCommandContext.saveChanges(DBRProgressMonitor, Map)"})
  public void testSaveChanges_givenDBPDataSourceInfoSupportsTransactionsForDDLReturnTrue()
      throws DBException {
    // Arrange
    DBPDataSourceInfo dbpDataSourceInfo = mock(DBPDataSourceInfo.class);
    when(dbpDataSourceInfo.supportsTransactionsForDDL()).thenReturn(true);

    DBPDataSource dbpDataSource = mock(DBPDataSource.class);
    when(dbpDataSource.getInfo()).thenReturn(dbpDataSourceInfo);

    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.getDataSource()).thenReturn(dbpDataSource);
    when(executionContext.isConnected()).thenReturn(true);
    TestCommandContext testCommandContext = new TestCommandContext(executionContext, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act
    testCommandContext.saveChanges(monitor, new HashMap<>());

    // Assert
    verify(dbpDataSource).getInfo();
    verify(dbpDataSourceInfo).supportsTransactionsForDDL();
    verify(executionContext).getDataSource();
    verify(executionContext, atLeast(1)).isConnected();
  }

  /**
   * Test {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}.
   *
   * <ul>
   *   <li>Then calls {@link DBECommand#getObject()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCommandContext#saveChanges(DBRProgressMonitor, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractCommandContext.saveChanges(DBRProgressMonitor, Map)"})
  public void testSaveChanges_thenCallsGetObject() throws DBException {
    // Arrange
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.isConnected()).thenReturn(true);

    DBECommand command = mock(DBECommand.class);
    when(command.getObject()).thenThrow(new IllegalStateException());

    TestCommandContext testCommandContext = new TestCommandContext(executionContext, true);
    testCommandContext.addCommand(command, new RefreshObjectReflector<>());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> testCommandContext.saveChanges(monitor, new HashMap<>()));
    verify(command).getObject();
    verify(executionContext, atLeast(1)).isConnected();
  }

  /**
   * Test {@link AbstractCommandContext#getFinalCommands()}.
   *
   * <ul>
   *   <li>Then return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCommandContext#getFinalCommands()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection AbstractCommandContext.getFinalCommands()"})
  public void testGetFinalCommands_thenReturnList() {
    // Arrange and Act
    Collection<? extends DBECommand<?>> actualFinalCommands =
        new TestCommandContext(mock(DBCExecutionContext.class), true).getFinalCommands();

    // Assert
    assertTrue(actualFinalCommands instanceof List);
    assertTrue(actualFinalCommands.isEmpty());
  }

  /**
   * Test {@link AbstractCommandContext#getFinalCommands()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCommandContext#getFinalCommands()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection AbstractCommandContext.getFinalCommands()"})
  public void testGetFinalCommands_thenThrowIllegalStateException() {
    // Arrange
    DBECommand command = mock(DBECommand.class);
    when(command.getObject()).thenThrow(new IllegalStateException());

    TestCommandContext testCommandContext =
        new TestCommandContext(mock(DBCExecutionContext.class), true);
    testCommandContext.addCommand(command, new RefreshObjectReflector<>());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> testCommandContext.getFinalCommands());
    verify(command).getObject();
  }

  /**
   * Test {@link AbstractCommandContext#getEditedObjects()}.
   *
   * <ul>
   *   <li>Then return {@link List}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCommandContext#getEditedObjects()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection AbstractCommandContext.getEditedObjects()"})
  public void testGetEditedObjects_thenReturnList() {
    // Arrange and Act
    Collection<DBPObject> actualEditedObjects =
        new TestCommandContext(mock(DBCExecutionContext.class), true).getEditedObjects();

    // Assert
    assertTrue(actualEditedObjects instanceof List);
    assertTrue(actualEditedObjects.isEmpty());
  }

  /**
   * Test {@link AbstractCommandContext#getEditedObjects()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractCommandContext#getEditedObjects()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection AbstractCommandContext.getEditedObjects()"})
  public void testGetEditedObjects_thenThrowIllegalStateException() {
    // Arrange
    DBECommand command = mock(DBECommand.class);
    when(command.getObject()).thenThrow(new IllegalStateException());

    TestCommandContext testCommandContext =
        new TestCommandContext(mock(DBCExecutionContext.class), true);
    testCommandContext.addCommand(command, new RefreshObjectReflector<>());

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> testCommandContext.getEditedObjects());
    verify(command).getObject();
  }

  /**
   * Test {@link AbstractCommandContext#addCommandListener(DBECommandListener)}.
   *
   * <p>Method under test: {@link AbstractCommandContext#addCommandListener(DBECommandListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractCommandContext.addCommandListener(DBECommandListener)"})
  public void testAddCommandListener() {
    // Arrange
    TestCommandContext testCommandContext =
        new TestCommandContext(mock(DBCExecutionContext.class), true);
    DBECommandListener listener = mock(DBECommandListener.class);

    // Act
    testCommandContext.addCommandListener(listener);

    // Assert
    DBECommandListener[] listeners = testCommandContext.getListeners();
    assertEquals(1, listeners.length);
    assertSame(listener, listeners[0]);
  }

  /**
   * Test {@link AbstractCommandContext#getUserParams()}.
   *
   * <p>Method under test: {@link AbstractCommandContext#getUserParams()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map AbstractCommandContext.getUserParams()"})
  public void testGetUserParams() {
    // Arrange, Act and Assert
    assertTrue(
        new TestCommandContext(mock(DBCExecutionContext.class), true).getUserParams().isEmpty());
  }

  /**
   * Test {@link AbstractCommandContext#getListeners()}.
   *
   * <p>Method under test: {@link AbstractCommandContext#getListeners()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBECommandListener[] AbstractCommandContext.getListeners()"})
  public void testGetListeners() {
    // Arrange, Act and Assert
    assertEquals(
        0, new TestCommandContext(mock(DBCExecutionContext.class), true).getListeners().length);
  }

  /**
   * Test {@link AbstractCommandContext#getRedoCommand()}.
   *
   * <p>Method under test: {@link AbstractCommandContext#getRedoCommand()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBECommand AbstractCommandContext.getRedoCommand()"})
  public void testGetRedoCommand() {
    // Arrange, Act and Assert
    assertNull(new TestCommandContext(mock(DBCExecutionContext.class), true).getRedoCommand());
  }

  /**
   * Test {@link AbstractCommandContext#redoCommand()}.
   *
   * <p>Method under test: {@link AbstractCommandContext#redoCommand()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractCommandContext.redoCommand()"})
  public void testRedoCommand() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new TestCommandContext(mock(DBCExecutionContext.class), true).redoCommand());
  }

  /**
   * Test {@link AbstractCommandContext#openCommandPersistContext(DBRProgressMonitor, DBECommand)}.
   *
   * <p>Method under test: {@link
   * AbstractCommandContext#openCommandPersistContext(DBRProgressMonitor, DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBCSession AbstractCommandContext.openCommandPersistContext(DBRProgressMonitor, DBECommand)"
  })
  public void testOpenCommandPersistContext() throws DBException {
    // Arrange
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.openSession(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBCExecutionPurpose>any(),
            Mockito.<String>any()))
        .thenReturn(mock(DBCSession.class));
    TestCommandContext testCommandContext = new TestCommandContext(executionContext, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBECommand<?> command = mock(DBECommand.class);
    when(command.getTitle()).thenReturn("Dr");

    // Act
    testCommandContext.openCommandPersistContext(monitor, command);

    // Assert
    verify(command).getTitle();
    verify(executionContext)
        .openSession(
            isA(DBRProgressMonitor.class), eq(DBCExecutionPurpose.META_DDL), eq("ExecuteDr"));
  }

  /**
   * Test {@link AbstractCommandContext#openCommandPersistContext(DBRProgressMonitor, DBECommand)}.
   *
   * <p>Method under test: {@link
   * AbstractCommandContext#openCommandPersistContext(DBRProgressMonitor, DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBCSession AbstractCommandContext.openCommandPersistContext(DBRProgressMonitor, DBECommand)"
  })
  public void testOpenCommandPersistContext2() throws DBException {
    // Arrange
    DBCExecutionContext executionContext = mock(DBCExecutionContext.class);
    when(executionContext.openSession(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<DBCExecutionPurpose>any(),
            Mockito.<String>any()))
        .thenThrow(new IllegalStateException());
    TestCommandContext testCommandContext = new TestCommandContext(executionContext, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBECommand<?> command = mock(DBECommand.class);
    when(command.getTitle()).thenReturn("Dr");

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> testCommandContext.openCommandPersistContext(monitor, command));
    verify(command).getTitle();
    verify(executionContext)
        .openSession(
            isA(DBRProgressMonitor.class), eq(DBCExecutionPurpose.META_DDL), eq("ExecuteDr"));
  }

  /**
   * Test {@link AbstractCommandContext#openCommandPersistContext(DBRProgressMonitor, DBECommand)}.
   *
   * <ul>
   *   <li>Given {@link IllegalStateException#IllegalStateException()}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractCommandContext#openCommandPersistContext(DBRProgressMonitor, DBECommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBCSession AbstractCommandContext.openCommandPersistContext(DBRProgressMonitor, DBECommand)"
  })
  public void testOpenCommandPersistContext_givenIllegalStateException() throws DBException {
    // Arrange
    TestCommandContext testCommandContext =
        new TestCommandContext(mock(DBCExecutionContext.class), true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBECommand<?> command = mock(DBECommand.class);
    when(command.getTitle()).thenThrow(new IllegalStateException());

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> testCommandContext.openCommandPersistContext(monitor, command));
    verify(command).getTitle();
  }
}
