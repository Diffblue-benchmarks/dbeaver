package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.runtime.IVariableResolver;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DBRProcessDescriptorDiffblueTest {
  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand, IVariableResolver)}.
   *
   * <ul>
   *   <li>Given {@code Get}.
   *   <li>Then return ProcessBuilder command first is {@code Get}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand,
   * IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand, IVariableResolver)"})
  public void testNewDBRProcessDescriptor_givenGet_thenReturnProcessBuilderCommandFirstIsGet() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand("${U:U}");

    IVariableResolver variablesResolver = mock(IVariableResolver.class);
    when(variablesResolver.get(Mockito.<String>any())).thenReturn("Get");

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor =
        new DBRProcessDescriptor(command, variablesResolver);

    // Assert
    verify(variablesResolver).get("U");
    List<String> commandResult = actualDbrProcessDescriptor.getProcessBuilder().command();
    assertEquals(1, commandResult.size());
    assertEquals("Get", commandResult.get(0));
    assertEquals("Get", actualDbrProcessDescriptor.getName());
    assertSame(command, actualDbrProcessDescriptor.getCommand());
  }

  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then return ProcessBuilder directory is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand)"})
  public void testNewDBRProcessDescriptor_givenNull_thenReturnProcessBuilderDirectoryIsNull() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand((String) null);
    command.setCommandParams(null);
    command.setWorkingDirectory("");

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor = new DBRProcessDescriptor(command);

    // Assert
    assertEquals("?", actualDbrProcessDescriptor.getName());
    ProcessBuilder processBuilder = actualDbrProcessDescriptor.getProcessBuilder();
    assertNull(processBuilder.directory());
    assertTrue(processBuilder.command().isEmpty());
    assertSame(command, actualDbrProcessDescriptor.getCommand());
  }

  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand, IVariableResolver)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then return ProcessBuilder directory is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand,
   * IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand, IVariableResolver)"})
  public void testNewDBRProcessDescriptor_givenNull_thenReturnProcessBuilderDirectoryIsNull2() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand((String) null);
    command.setCommandParams(null);
    command.setWorkingDirectory("");

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor = new DBRProcessDescriptor(command, null);

    // Assert
    assertEquals("?", actualDbrProcessDescriptor.getName());
    ProcessBuilder processBuilder = actualDbrProcessDescriptor.getProcessBuilder();
    assertNull(processBuilder.directory());
    assertTrue(processBuilder.command().isEmpty());
    assertSame(command, actualDbrProcessDescriptor.getCommand());
  }

  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand, IVariableResolver)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then return ProcessBuilder directory is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand,
   * IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand, IVariableResolver)"})
  public void testNewDBRProcessDescriptor_givenNull_thenReturnProcessBuilderDirectoryIsNull3() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand((String) null);
    command.setCommandParams(null);
    command.setWorkingDirectory("");

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor =
        new DBRProcessDescriptor(command, mock(IVariableResolver.class));

    // Assert
    assertEquals("?", actualDbrProcessDescriptor.getName());
    ProcessBuilder processBuilder = actualDbrProcessDescriptor.getProcessBuilder();
    assertNull(processBuilder.directory());
    assertTrue(processBuilder.command().isEmpty());
    assertSame(command, actualDbrProcessDescriptor.getCommand());
  }

  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand)}.
   *
   * <ul>
   *   <li>Then return Command WorkingDirectory is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand)"})
  public void testNewDBRProcessDescriptor_thenReturnCommandWorkingDirectoryIsEmptyString() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand((String) null);
    ArrayList<String> commandParams = new ArrayList<>();
    command.setCommandParams(commandParams);
    command.setWorkingDirectory("");

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor = new DBRProcessDescriptor(command);

    // Assert
    DBRShellCommand command2 = actualDbrProcessDescriptor.getCommand();
    assertEquals("", command2.getWorkingDirectory());
    assertNull(command2.getCommand());
    assertEquals(0, command2.getPauseAfterExecute());
    assertFalse(command2.isEnabled());
    assertFalse(command2.isWaitProcessFinish());
    assertTrue(command2.getCommandParams().isEmpty());
    assertTrue(command2.isShowProcessPanel());
    assertTrue(command2.isTerminateAtDisconnect());
    assertEquals(DBRShellCommand.WAIT_PROCESS_TIMEOUT_FOREVER, command2.getWaitProcessTimeoutMs());
    assertSame(commandParams, actualDbrProcessDescriptor.getProcessBuilder().command());
  }

  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand, IVariableResolver)}.
   *
   * <ul>
   *   <li>Then return Command WorkingDirectory is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand,
   * IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand, IVariableResolver)"})
  public void testNewDBRProcessDescriptor_thenReturnCommandWorkingDirectoryIsEmptyString2() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand((String) null);
    ArrayList<String> commandParams = new ArrayList<>();
    command.setCommandParams(commandParams);
    command.setWorkingDirectory("");

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor = new DBRProcessDescriptor(command, null);

    // Assert
    DBRShellCommand command2 = actualDbrProcessDescriptor.getCommand();
    assertEquals("", command2.getWorkingDirectory());
    assertNull(command2.getCommand());
    assertEquals(0, command2.getPauseAfterExecute());
    assertFalse(command2.isEnabled());
    assertFalse(command2.isWaitProcessFinish());
    assertTrue(command2.getCommandParams().isEmpty());
    assertTrue(command2.isShowProcessPanel());
    assertTrue(command2.isTerminateAtDisconnect());
    assertEquals(DBRShellCommand.WAIT_PROCESS_TIMEOUT_FOREVER, command2.getWaitProcessTimeoutMs());
    assertSame(commandParams, actualDbrProcessDescriptor.getProcessBuilder().command());
  }

  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand, IVariableResolver)}.
   *
   * <ul>
   *   <li>Then return ProcessBuilder command first is {@code Command}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand,
   * IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand, IVariableResolver)"})
  public void testNewDBRProcessDescriptor_thenReturnProcessBuilderCommandFirstIsCommand() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand("Command");

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor =
        new DBRProcessDescriptor(command, mock(IVariableResolver.class));

    // Assert
    List<String> commandResult = actualDbrProcessDescriptor.getProcessBuilder().command();
    assertEquals(1, commandResult.size());
    assertEquals("Command", commandResult.get(0));
    assertEquals("Command", actualDbrProcessDescriptor.getName());
    assertSame(command, actualDbrProcessDescriptor.getCommand());
  }

  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand, IVariableResolver)}.
   *
   * <ul>
   *   <li>Then return ProcessBuilder command first is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand,
   * IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand, IVariableResolver)"})
  public void testNewDBRProcessDescriptor_thenReturnProcessBuilderCommandFirstIsEmptyString() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand("''");

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor =
        new DBRProcessDescriptor(command, mock(IVariableResolver.class));

    // Assert
    List<String> commandResult = actualDbrProcessDescriptor.getProcessBuilder().command();
    assertEquals(1, commandResult.size());
    assertEquals("", commandResult.get(0));
    assertEquals("", actualDbrProcessDescriptor.getName());
    assertSame(command, actualDbrProcessDescriptor.getCommand());
  }

  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand, IVariableResolver)}.
   *
   * <ul>
   *   <li>Then return ProcessBuilder command first is {@code ${U:U}}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand,
   * IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand, IVariableResolver)"})
  public void testNewDBRProcessDescriptor_thenReturnProcessBuilderCommandFirstIsUU() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand("${U:U}");

    IVariableResolver variablesResolver = mock(IVariableResolver.class);
    when(variablesResolver.get(Mockito.<String>any())).thenThrow(new IllegalThreadStateException());

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor =
        new DBRProcessDescriptor(command, variablesResolver);

    // Assert
    verify(variablesResolver).get("U");
    List<String> commandResult = actualDbrProcessDescriptor.getProcessBuilder().command();
    assertEquals(1, commandResult.size());
    assertEquals("${U:U}", commandResult.get(0));
    assertEquals("${U:U}", actualDbrProcessDescriptor.getName());
    assertSame(command, actualDbrProcessDescriptor.getCommand());
  }

  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand)}.
   *
   * <ul>
   *   <li>Then return ProcessBuilder command size is one.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand)"})
  public void testNewDBRProcessDescriptor_thenReturnProcessBuilderCommandSizeIsOne() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand("Command");

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor = new DBRProcessDescriptor(command);

    // Assert
    List<String> commandResult = actualDbrProcessDescriptor.getProcessBuilder().command();
    assertEquals(1, commandResult.size());
    assertEquals("Command", commandResult.get(0));
    assertEquals("Command", actualDbrProcessDescriptor.getName());
    assertSame(command, actualDbrProcessDescriptor.getCommand());
  }

  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand, IVariableResolver)}.
   *
   * <ul>
   *   <li>Then return ProcessBuilder command size is three.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand,
   * IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand, IVariableResolver)"})
  public void testNewDBRProcessDescriptor_thenReturnProcessBuilderCommandSizeIsThree() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand("Error matching regex");

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor =
        new DBRProcessDescriptor(command, mock(IVariableResolver.class));

    // Assert
    List<String> commandResult = actualDbrProcessDescriptor.getProcessBuilder().command();
    assertEquals(3, commandResult.size());
    assertEquals("Error", commandResult.get(0));
    assertEquals("Error", actualDbrProcessDescriptor.getName());
    assertEquals("matching", commandResult.get(1));
    assertEquals("regex", commandResult.get(2));
    assertSame(command, actualDbrProcessDescriptor.getCommand());
  }

  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand)}.
   *
   * <ul>
   *   <li>Then return ProcessBuilder directory Name is {@code Command}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand)"})
  public void testNewDBRProcessDescriptor_thenReturnProcessBuilderDirectoryNameIsCommand() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand((String) null);
    command.setCommandParams(null);
    command.setWorkingDirectory("Command");

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor = new DBRProcessDescriptor(command);

    // Assert
    assertEquals("?", actualDbrProcessDescriptor.getName());
    ProcessBuilder processBuilder = actualDbrProcessDescriptor.getProcessBuilder();
    File directoryResult = processBuilder.directory();
    assertEquals("Command", directoryResult.getName());
    assertFalse(directoryResult.isAbsolute());
    assertTrue(processBuilder.command().isEmpty());
    assertSame(command, actualDbrProcessDescriptor.getCommand());
  }

  /**
   * Test {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand, IVariableResolver)}.
   *
   * <ul>
   *   <li>Then return ProcessBuilder directory Name is {@code Command}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand,
   * IVariableResolver)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.<init>(DBRShellCommand, IVariableResolver)"})
  public void testNewDBRProcessDescriptor_thenReturnProcessBuilderDirectoryNameIsCommand2() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand((String) null);
    command.setCommandParams(null);
    command.setWorkingDirectory("Command");

    // Act
    DBRProcessDescriptor actualDbrProcessDescriptor = new DBRProcessDescriptor(command, null);

    // Assert
    assertEquals("?", actualDbrProcessDescriptor.getName());
    ProcessBuilder processBuilder = actualDbrProcessDescriptor.getProcessBuilder();
    File directoryResult = processBuilder.directory();
    assertEquals("Command", directoryResult.getName());
    assertFalse(directoryResult.isAbsolute());
    assertTrue(processBuilder.command().isEmpty());
    assertSame(command, actualDbrProcessDescriptor.getCommand());
  }

  /**
   * Test {@link DBRProcessDescriptor#getName()}.
   *
   * <ul>
   *   <li>Given {@link DBRShellCommand#DBRShellCommand(String)} with command is empty string.
   *   <li>Then return {@code ?}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBRProcessDescriptor.getName()"})
  public void testGetName_givenDBRShellCommandWithCommandIsEmptyString_thenReturnQuestionMark() {
    // Arrange, Act and Assert
    assertEquals("?", new DBRProcessDescriptor(new DBRShellCommand("")).getName());
  }

  /**
   * Test {@link DBRProcessDescriptor#getName()}.
   *
   * <ul>
   *   <li>Given {@link DBRShellCommand#DBRShellCommand(String)} with {@code Command}.
   *   <li>Then return {@code Command}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBRProcessDescriptor.getName()"})
  public void testGetName_givenDBRShellCommandWithCommand_thenReturnCommand() {
    // Arrange, Act and Assert
    assertEquals("Command", new DBRProcessDescriptor(new DBRShellCommand("Command")).getName());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBRProcessDescriptor#setProcessListener(DBRProcessListener)}
   *   <li>{@link DBRProcessDescriptor#getCommand()}
   *   <li>{@link DBRProcessDescriptor#getExitValue()}
   *   <li>{@link DBRProcessDescriptor#getProcess()}
   *   <li>{@link DBRProcessDescriptor#getProcessBuilder()}
   *   <li>{@link DBRProcessDescriptor#getProcessListener()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBRShellCommand DBRProcessDescriptor.getCommand()",
    "int DBRProcessDescriptor.getExitValue()",
    "Process DBRProcessDescriptor.getProcess()",
    "ProcessBuilder DBRProcessDescriptor.getProcessBuilder()",
    "DBRProcessListener DBRProcessDescriptor.getProcessListener()",
    "void DBRProcessDescriptor.setProcessListener(DBRProcessListener)"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBRShellCommand command = new DBRShellCommand("Command");
    DBRProcessDescriptor dbrProcessDescriptor = new DBRProcessDescriptor(command);
    DBRProcessListener processListener = mock(DBRProcessListener.class);

    // Act
    dbrProcessDescriptor.setProcessListener(processListener);
    DBRShellCommand actualCommand = dbrProcessDescriptor.getCommand();
    int actualExitValue = dbrProcessDescriptor.getExitValue();
    Process actualProcess = dbrProcessDescriptor.getProcess();
    ProcessBuilder actualProcessBuilder = dbrProcessDescriptor.getProcessBuilder();
    DBRProcessListener actualProcessListener = dbrProcessDescriptor.getProcessListener();

    // Assert
    List<String> commandResult = actualProcessBuilder.command();
    assertEquals(1, commandResult.size());
    assertEquals("Command", commandResult.get(0));
    assertNull(actualProcessBuilder.directory());
    assertNull(actualProcess);
    assertFalse(actualProcessBuilder.redirectErrorStream());
    assertEquals(DBRShellCommand.WAIT_PROCESS_TIMEOUT_FOREVER, actualExitValue);
    assertSame(command, actualCommand);
    assertSame(processListener, actualProcessListener);
  }

  /**
   * Test {@link DBRProcessDescriptor#isRunning()}.
   *
   * <p>Method under test: {@link DBRProcessDescriptor#isRunning()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRProcessDescriptor.isRunning()"})
  public void testIsRunning() {
    // Arrange, Act and Assert
    assertFalse(new DBRProcessDescriptor(new DBRShellCommand("Command")).isRunning());
  }

  /**
   * Test {@link DBRProcessDescriptor#execute()}.
   *
   * <ul>
   *   <li>Given {@link DBRShellCommand#DBRShellCommand(String)} with command is {@code null}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#execute()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRProcessDescriptor.execute()"})
  public void testExecute_givenDBRShellCommandWithCommandIsNull_thenThrowDBException()
      throws DBException {
    // Arrange, Act and Assert
    assertThrows(
        DBException.class,
        () -> new DBRProcessDescriptor(new DBRShellCommand((String) null)).execute());
  }

  /**
   * Test {@link DBRProcessDescriptor#waitFor(int)} with {@code int}.
   *
   * <ul>
   *   <li>Given {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand)} with command is
   *       {@link DBRShellCommand#DBRShellCommand(String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#waitFor(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBRProcessDescriptor.waitFor(int)"})
  public void testWaitForWithInt_givenDBRProcessDescriptorWithCommandIsDBRShellCommand() {
    // Arrange, Act and Assert
    assertEquals(
        DBRShellCommand.WAIT_PROCESS_TIMEOUT_FOREVER,
        new DBRProcessDescriptor(new DBRShellCommand("Command")).waitFor(10));
  }

  /**
   * Test {@link DBRProcessDescriptor#waitFor(int)} with {@code int}.
   *
   * <ul>
   *   <li>Given {@link DBRProcessListener} {@link DBRProcessListener#onProcessTerminated(int)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#waitFor(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBRProcessDescriptor.waitFor(int)"})
  public void testWaitForWithInt_givenDBRProcessListenerOnProcessTerminatedDoesNothing() {
    // Arrange
    DBRProcessListener processListener = mock(DBRProcessListener.class);
    doNothing().when(processListener).onProcessTerminated(anyInt());

    DBRProcessDescriptor dbrProcessDescriptor =
        new DBRProcessDescriptor(new DBRShellCommand("Command"));
    dbrProcessDescriptor.setProcessListener(processListener);

    // Act
    int actualWaitForResult = dbrProcessDescriptor.waitFor(10);

    // Assert
    verify(processListener).onProcessTerminated(-1);
    assertEquals(DBRShellCommand.WAIT_PROCESS_TIMEOUT_FOREVER, actualWaitForResult);
  }

  /**
   * Test {@link DBRProcessDescriptor#waitFor(int)} with {@code int}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalThreadStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#waitFor(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBRProcessDescriptor.waitFor(int)"})
  public void testWaitForWithInt_thenThrowIllegalThreadStateException() {
    // Arrange
    DBRProcessListener processListener = mock(DBRProcessListener.class);
    doThrow(new IllegalThreadStateException()).when(processListener).onProcessTerminated(anyInt());

    DBRProcessDescriptor dbrProcessDescriptor =
        new DBRProcessDescriptor(new DBRShellCommand("Command"));
    dbrProcessDescriptor.setProcessListener(processListener);

    // Act and Assert
    assertThrows(IllegalThreadStateException.class, () -> dbrProcessDescriptor.waitFor(10));
    verify(processListener).onProcessTerminated(-1);
  }

  /**
   * Test {@link DBRProcessDescriptor#waitFor()}.
   *
   * <ul>
   *   <li>Given {@link DBRProcessDescriptor#DBRProcessDescriptor(DBRShellCommand)} with command is
   *       {@link DBRShellCommand#DBRShellCommand(String)}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#waitFor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBRProcessDescriptor.waitFor()"})
  public void testWaitFor_givenDBRProcessDescriptorWithCommandIsDBRShellCommand() {
    // Arrange, Act and Assert
    assertEquals(
        DBRShellCommand.WAIT_PROCESS_TIMEOUT_FOREVER,
        new DBRProcessDescriptor(new DBRShellCommand("Command")).waitFor());
  }

  /**
   * Test {@link DBRProcessDescriptor#waitFor()}.
   *
   * <ul>
   *   <li>Given {@link DBRProcessListener} {@link DBRProcessListener#onProcessTerminated(int)} does
   *       nothing.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#waitFor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBRProcessDescriptor.waitFor()"})
  public void testWaitFor_givenDBRProcessListenerOnProcessTerminatedDoesNothing() {
    // Arrange
    DBRProcessListener processListener = mock(DBRProcessListener.class);
    doNothing().when(processListener).onProcessTerminated(anyInt());

    DBRProcessDescriptor dbrProcessDescriptor =
        new DBRProcessDescriptor(new DBRShellCommand("Command"));
    dbrProcessDescriptor.setProcessListener(processListener);

    // Act
    int actualWaitForResult = dbrProcessDescriptor.waitFor();

    // Assert
    verify(processListener).onProcessTerminated(-1);
    assertEquals(DBRShellCommand.WAIT_PROCESS_TIMEOUT_FOREVER, actualWaitForResult);
  }

  /**
   * Test {@link DBRProcessDescriptor#waitFor()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalThreadStateException}.
   * </ul>
   *
   * <p>Method under test: {@link DBRProcessDescriptor#waitFor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DBRProcessDescriptor.waitFor()"})
  public void testWaitFor_thenThrowIllegalThreadStateException() {
    // Arrange
    DBRProcessListener processListener = mock(DBRProcessListener.class);
    doThrow(new IllegalThreadStateException()).when(processListener).onProcessTerminated(anyInt());

    DBRProcessDescriptor dbrProcessDescriptor =
        new DBRProcessDescriptor(new DBRShellCommand("Command"));
    dbrProcessDescriptor.setProcessListener(processListener);

    // Act and Assert
    assertThrows(IllegalThreadStateException.class, () -> dbrProcessDescriptor.waitFor());
    verify(processListener).onProcessTerminated(-1);
  }

  /**
   * Test {@link DBRProcessDescriptor#dumpErrors()}.
   *
   * <p>Method under test: {@link DBRProcessDescriptor#dumpErrors()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBRProcessDescriptor.dumpErrors()"})
  public void testDumpErrors() {
    // Arrange, Act and Assert
    assertNull(new DBRProcessDescriptor(new DBRShellCommand("Command")).dumpErrors());
  }

  /**
   * Test {@link DBRProcessDescriptor#dumpOutput()}.
   *
   * <p>Method under test: {@link DBRProcessDescriptor#dumpOutput()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DBRProcessDescriptor.dumpOutput()"})
  public void testDumpOutput() {
    // Arrange, Act and Assert
    assertNull(new DBRProcessDescriptor(new DBRShellCommand("Command")).dumpOutput());
  }
}
