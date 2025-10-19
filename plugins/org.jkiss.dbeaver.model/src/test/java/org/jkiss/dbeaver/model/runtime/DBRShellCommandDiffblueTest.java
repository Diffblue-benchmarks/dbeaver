package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBRShellCommandDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBRShellCommand#DBRShellCommand(List)}
   *   <li>{@link DBRShellCommand#setCommand(String)}
   *   <li>{@link DBRShellCommand#setCommandParams(List)}
   *   <li>{@link DBRShellCommand#setEnabled(boolean)}
   *   <li>{@link DBRShellCommand#setPauseAfterExecute(int)}
   *   <li>{@link DBRShellCommand#setShowProcessPanel(boolean)}
   *   <li>{@link DBRShellCommand#setTerminateAtDisconnect(boolean)}
   *   <li>{@link DBRShellCommand#setWaitProcessFinish(boolean)}
   *   <li>{@link DBRShellCommand#setWaitProcessTimeoutMs(int)}
   *   <li>{@link DBRShellCommand#setWorkingDirectory(String)}
   *   <li>{@link DBRShellCommand#getCommand()}
   *   <li>{@link DBRShellCommand#getCommandParams()}
   *   <li>{@link DBRShellCommand#getPauseAfterExecute()}
   *   <li>{@link DBRShellCommand#getWaitProcessTimeoutMs()}
   *   <li>{@link DBRShellCommand#getWorkingDirectory()}
   *   <li>{@link DBRShellCommand#isEnabled()}
   *   <li>{@link DBRShellCommand#isShowProcessPanel()}
   *   <li>{@link DBRShellCommand#isTerminateAtDisconnect()}
   *   <li>{@link DBRShellCommand#isWaitProcessFinish()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBRShellCommand.<init>(String)",
    "void DBRShellCommand.<init>(List)",
    "String DBRShellCommand.getCommand()",
    "List DBRShellCommand.getCommandParams()",
    "int DBRShellCommand.getPauseAfterExecute()",
    "int DBRShellCommand.getWaitProcessTimeoutMs()",
    "String DBRShellCommand.getWorkingDirectory()",
    "boolean DBRShellCommand.isEnabled()",
    "boolean DBRShellCommand.isShowProcessPanel()",
    "boolean DBRShellCommand.isTerminateAtDisconnect()",
    "boolean DBRShellCommand.isWaitProcessFinish()",
    "void DBRShellCommand.setCommand(String)",
    "void DBRShellCommand.setCommandParams(List)",
    "void DBRShellCommand.setEnabled(boolean)",
    "void DBRShellCommand.setPauseAfterExecute(int)",
    "void DBRShellCommand.setShowProcessPanel(boolean)",
    "void DBRShellCommand.setTerminateAtDisconnect(boolean)",
    "void DBRShellCommand.setWaitProcessFinish(boolean)",
    "void DBRShellCommand.setWaitProcessTimeoutMs(int)",
    "void DBRShellCommand.setWorkingDirectory(String)"
  })
  public void testGettersAndSetters_whenArrayList() {
    // Arrange and Act
    DBRShellCommand actualDbrShellCommand = new DBRShellCommand(new ArrayList<>());
    actualDbrShellCommand.setCommand("Command");
    ArrayList<String> commandParams = new ArrayList<>();
    actualDbrShellCommand.setCommandParams(commandParams);
    actualDbrShellCommand.setEnabled(true);
    actualDbrShellCommand.setPauseAfterExecute(1);
    actualDbrShellCommand.setShowProcessPanel(true);
    actualDbrShellCommand.setTerminateAtDisconnect(true);
    actualDbrShellCommand.setWaitProcessFinish(true);
    actualDbrShellCommand.setWaitProcessTimeoutMs(10);
    actualDbrShellCommand.setWorkingDirectory("/directory");
    String actualCommand = actualDbrShellCommand.getCommand();
    List<String> actualCommandParams = actualDbrShellCommand.getCommandParams();
    int actualPauseAfterExecute = actualDbrShellCommand.getPauseAfterExecute();
    int actualWaitProcessTimeoutMs = actualDbrShellCommand.getWaitProcessTimeoutMs();
    String actualWorkingDirectory = actualDbrShellCommand.getWorkingDirectory();
    boolean actualIsEnabledResult = actualDbrShellCommand.isEnabled();
    boolean actualIsShowProcessPanelResult = actualDbrShellCommand.isShowProcessPanel();
    boolean actualIsTerminateAtDisconnectResult = actualDbrShellCommand.isTerminateAtDisconnect();
    boolean actualIsWaitProcessFinishResult = actualDbrShellCommand.isWaitProcessFinish();

    // Assert
    assertEquals("/directory", actualWorkingDirectory);
    assertEquals("Command", actualCommand);
    assertEquals(1, actualPauseAfterExecute);
    assertEquals(10, actualWaitProcessTimeoutMs);
    assertTrue(actualCommandParams.isEmpty());
    assertTrue(actualIsEnabledResult);
    assertTrue(actualIsShowProcessPanelResult);
    assertTrue(actualIsTerminateAtDisconnectResult);
    assertTrue(actualIsWaitProcessFinishResult);
    assertSame(commandParams, actualCommandParams);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code Command}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBRShellCommand#DBRShellCommand(String)}
   *   <li>{@link DBRShellCommand#setCommand(String)}
   *   <li>{@link DBRShellCommand#setCommandParams(List)}
   *   <li>{@link DBRShellCommand#setEnabled(boolean)}
   *   <li>{@link DBRShellCommand#setPauseAfterExecute(int)}
   *   <li>{@link DBRShellCommand#setShowProcessPanel(boolean)}
   *   <li>{@link DBRShellCommand#setTerminateAtDisconnect(boolean)}
   *   <li>{@link DBRShellCommand#setWaitProcessFinish(boolean)}
   *   <li>{@link DBRShellCommand#setWaitProcessTimeoutMs(int)}
   *   <li>{@link DBRShellCommand#setWorkingDirectory(String)}
   *   <li>{@link DBRShellCommand#getCommand()}
   *   <li>{@link DBRShellCommand#getCommandParams()}
   *   <li>{@link DBRShellCommand#getPauseAfterExecute()}
   *   <li>{@link DBRShellCommand#getWaitProcessTimeoutMs()}
   *   <li>{@link DBRShellCommand#getWorkingDirectory()}
   *   <li>{@link DBRShellCommand#isEnabled()}
   *   <li>{@link DBRShellCommand#isShowProcessPanel()}
   *   <li>{@link DBRShellCommand#isTerminateAtDisconnect()}
   *   <li>{@link DBRShellCommand#isWaitProcessFinish()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBRShellCommand.<init>(String)",
    "void DBRShellCommand.<init>(List)",
    "String DBRShellCommand.getCommand()",
    "List DBRShellCommand.getCommandParams()",
    "int DBRShellCommand.getPauseAfterExecute()",
    "int DBRShellCommand.getWaitProcessTimeoutMs()",
    "String DBRShellCommand.getWorkingDirectory()",
    "boolean DBRShellCommand.isEnabled()",
    "boolean DBRShellCommand.isShowProcessPanel()",
    "boolean DBRShellCommand.isTerminateAtDisconnect()",
    "boolean DBRShellCommand.isWaitProcessFinish()",
    "void DBRShellCommand.setCommand(String)",
    "void DBRShellCommand.setCommandParams(List)",
    "void DBRShellCommand.setEnabled(boolean)",
    "void DBRShellCommand.setPauseAfterExecute(int)",
    "void DBRShellCommand.setShowProcessPanel(boolean)",
    "void DBRShellCommand.setTerminateAtDisconnect(boolean)",
    "void DBRShellCommand.setWaitProcessFinish(boolean)",
    "void DBRShellCommand.setWaitProcessTimeoutMs(int)",
    "void DBRShellCommand.setWorkingDirectory(String)"
  })
  public void testGettersAndSetters_whenCommand() {
    // Arrange and Act
    DBRShellCommand actualDbrShellCommand = new DBRShellCommand("Command");
    actualDbrShellCommand.setCommand("Command");
    ArrayList<String> commandParams = new ArrayList<>();
    actualDbrShellCommand.setCommandParams(commandParams);
    actualDbrShellCommand.setEnabled(true);
    actualDbrShellCommand.setPauseAfterExecute(1);
    actualDbrShellCommand.setShowProcessPanel(true);
    actualDbrShellCommand.setTerminateAtDisconnect(true);
    actualDbrShellCommand.setWaitProcessFinish(true);
    actualDbrShellCommand.setWaitProcessTimeoutMs(10);
    actualDbrShellCommand.setWorkingDirectory("/directory");
    String actualCommand = actualDbrShellCommand.getCommand();
    List<String> actualCommandParams = actualDbrShellCommand.getCommandParams();
    int actualPauseAfterExecute = actualDbrShellCommand.getPauseAfterExecute();
    int actualWaitProcessTimeoutMs = actualDbrShellCommand.getWaitProcessTimeoutMs();
    String actualWorkingDirectory = actualDbrShellCommand.getWorkingDirectory();
    boolean actualIsEnabledResult = actualDbrShellCommand.isEnabled();
    boolean actualIsShowProcessPanelResult = actualDbrShellCommand.isShowProcessPanel();
    boolean actualIsTerminateAtDisconnectResult = actualDbrShellCommand.isTerminateAtDisconnect();
    boolean actualIsWaitProcessFinishResult = actualDbrShellCommand.isWaitProcessFinish();

    // Assert
    assertEquals("/directory", actualWorkingDirectory);
    assertEquals("Command", actualCommand);
    assertEquals(1, actualPauseAfterExecute);
    assertEquals(10, actualWaitProcessTimeoutMs);
    assertTrue(actualCommandParams.isEmpty());
    assertTrue(actualIsEnabledResult);
    assertTrue(actualIsShowProcessPanelResult);
    assertTrue(actualIsTerminateAtDisconnectResult);
    assertTrue(actualIsWaitProcessFinishResult);
    assertSame(commandParams, actualCommandParams);
  }

  /**
   * Test {@link DBRShellCommand#DBRShellCommand(DBRShellCommand)}.
   *
   * <ul>
   *   <li>When {@link DBRShellCommand#DBRShellCommand(String)} with {@code Command}.
   *   <li>Then return {@code Command}.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#DBRShellCommand(DBRShellCommand)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRShellCommand.<init>(DBRShellCommand)"})
  public void testNewDBRShellCommand_whenDBRShellCommandWithCommand_thenReturnCommand() {
    // Arrange and Act
    DBRShellCommand actualDbrShellCommand = new DBRShellCommand(new DBRShellCommand("Command"));

    // Assert
    assertEquals("Command", actualDbrShellCommand.getCommand());
    assertNull(actualDbrShellCommand.getWorkingDirectory());
    assertNull(actualDbrShellCommand.getCommandParams());
    assertEquals(0, actualDbrShellCommand.getPauseAfterExecute());
    assertFalse(actualDbrShellCommand.isEnabled());
    assertFalse(actualDbrShellCommand.isWaitProcessFinish());
    assertTrue(actualDbrShellCommand.isShowProcessPanel());
    assertTrue(actualDbrShellCommand.isTerminateAtDisconnect());
    assertEquals(
        DBRShellCommand.WAIT_PROCESS_TIMEOUT_FOREVER,
        actualDbrShellCommand.getWaitProcessTimeoutMs());
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}, and {@link DBRShellCommand#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DBRShellCommand dbrShellCommand = new DBRShellCommand("Command");
    DBRShellCommand dbrShellCommand2 = new DBRShellCommand("Command");

    // Act and Assert
    assertEquals(dbrShellCommand, dbrShellCommand2);
    assertNotEquals(dbrShellCommand.hashCode(), dbrShellCommand2.hashCode());
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}, and {@link DBRShellCommand#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DBRShellCommand dbrShellCommand = new DBRShellCommand("Command");

    // Act and Assert
    assertEquals(dbrShellCommand, dbrShellCommand);
    int expectedHashCodeResult = dbrShellCommand.hashCode();
    assertEquals(expectedHashCodeResult, dbrShellCommand.hashCode());
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange
    DBRShellCommand dbrShellCommand = new DBRShellCommand("42");

    // Act and Assert
    assertNotEquals(dbrShellCommand, new DBRShellCommand("Command"));
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DBRShellCommand dbrShellCommand = new DBRShellCommand("Command");
    dbrShellCommand.setCommandParams(new ArrayList<>());

    // Act and Assert
    assertNotEquals(dbrShellCommand, new DBRShellCommand("Command"));
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual3() {
    // Arrange
    DBRShellCommand dbrShellCommand = new DBRShellCommand("Command");
    dbrShellCommand.setEnabled(true);

    // Act and Assert
    assertNotEquals(dbrShellCommand, new DBRShellCommand("Command"));
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual4() {
    // Arrange
    DBRShellCommand dbrShellCommand = new DBRShellCommand("Command");
    dbrShellCommand.setWaitProcessFinish(true);

    // Act and Assert
    assertNotEquals(dbrShellCommand, new DBRShellCommand("Command"));
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual5() {
    // Arrange
    DBRShellCommand dbrShellCommand = new DBRShellCommand("Command");
    dbrShellCommand.setWaitProcessTimeoutMs(10);

    // Act and Assert
    assertNotEquals(dbrShellCommand, new DBRShellCommand("Command"));
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual6() {
    // Arrange
    DBRShellCommand dbrShellCommand = new DBRShellCommand("Command");
    dbrShellCommand.setPauseAfterExecute(1);

    // Act and Assert
    assertNotEquals(dbrShellCommand, new DBRShellCommand("Command"));
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual7() {
    // Arrange
    DBRShellCommand dbrShellCommand = new DBRShellCommand("Command");
    dbrShellCommand.setWorkingDirectory("/directory");

    // Act and Assert
    assertNotEquals(dbrShellCommand, new DBRShellCommand("Command"));
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual8() {
    // Arrange
    DBRShellCommand dbrShellCommand = new DBRShellCommand("Command");
    dbrShellCommand.setShowProcessPanel(false);

    // Act and Assert
    assertNotEquals(dbrShellCommand, new DBRShellCommand("Command"));
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual9() {
    // Arrange
    DBRShellCommand dbrShellCommand = new DBRShellCommand("Command");
    dbrShellCommand.setTerminateAtDisconnect(false);

    // Act and Assert
    assertNotEquals(dbrShellCommand, new DBRShellCommand("Command"));
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBRShellCommand("Command"), null);
  }

  /**
   * Test {@link DBRShellCommand#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DBRShellCommand#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DBRShellCommand.equals(Object)"})
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DBRShellCommand("Command"), "Different type to DBRShellCommand");
  }
}
