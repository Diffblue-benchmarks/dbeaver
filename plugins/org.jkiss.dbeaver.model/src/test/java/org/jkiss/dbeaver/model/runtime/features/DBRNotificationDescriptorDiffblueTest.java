package org.jkiss.dbeaver.model.runtime.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBRNotificationDescriptorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBRNotificationDescriptor#DBRNotificationDescriptor(DBRNotificationAction, String,
   *       String)}
   *   <li>{@link DBRNotificationDescriptor#setAction(DBRNotificationAction)}
   *   <li>{@link DBRNotificationDescriptor#setShellCommand(String)}
   *   <li>{@link DBRNotificationDescriptor#setSoundFile(String)}
   *   <li>{@link DBRNotificationDescriptor#getAction()}
   *   <li>{@link DBRNotificationDescriptor#getShellCommand()}
   *   <li>{@link DBRNotificationDescriptor#getSoundFile()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBRNotificationDescriptor.<init>(DBRNotificationAction, String, String)",
    "DBRNotificationAction DBRNotificationDescriptor.getAction()",
    "String DBRNotificationDescriptor.getShellCommand()",
    "String DBRNotificationDescriptor.getSoundFile()",
    "void DBRNotificationDescriptor.setAction(DBRNotificationAction)",
    "void DBRNotificationDescriptor.setShellCommand(String)",
    "void DBRNotificationDescriptor.setSoundFile(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBRNotificationDescriptor actualDbrNotificationDescriptor =
        new DBRNotificationDescriptor(DBRNotificationAction.NONE, "Sound File", "Shell Command");
    actualDbrNotificationDescriptor.setAction(DBRNotificationAction.NONE);
    actualDbrNotificationDescriptor.setShellCommand("Shell Command");
    actualDbrNotificationDescriptor.setSoundFile("Sound File");
    DBRNotificationAction actualAction = actualDbrNotificationDescriptor.getAction();
    String actualShellCommand = actualDbrNotificationDescriptor.getShellCommand();

    // Assert
    assertEquals("Shell Command", actualShellCommand);
    assertEquals("Sound File", actualDbrNotificationDescriptor.getSoundFile());
    assertEquals(DBRNotificationAction.NONE, actualAction);
  }

  /**
   * Test {@link DBRNotificationDescriptor#DBRNotificationDescriptor()}.
   *
   * <p>Method under test: {@link DBRNotificationDescriptor#DBRNotificationDescriptor()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBRNotificationDescriptor.<init>()"})
  public void testNewDBRNotificationDescriptor() {
    // Arrange and Act
    DBRNotificationDescriptor actualDbrNotificationDescriptor = new DBRNotificationDescriptor();

    // Assert
    assertNull(actualDbrNotificationDescriptor.getShellCommand());
    assertNull(actualDbrNotificationDescriptor.getSoundFile());
    assertEquals(DBRNotificationAction.NONE, actualDbrNotificationDescriptor.getAction());
  }
}
