package org.jkiss.dbeaver.model.navigator.meta;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.navigator.meta.DBXTreeNodeHandler.Action;
import org.jkiss.dbeaver.model.navigator.meta.DBXTreeNodeHandler.Perform;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBXTreeNodeHandlerDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBXTreeNodeHandler#DBXTreeNodeHandler(Action, Perform, String)}
   *   <li>{@link DBXTreeNodeHandler#getAction()}
   *   <li>{@link DBXTreeNodeHandler#getCommand()}
   *   <li>{@link DBXTreeNodeHandler#getPerform()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBXTreeNodeHandler.<init>(Action, Perform, String)",
    "Action DBXTreeNodeHandler.getAction()",
    "String DBXTreeNodeHandler.getCommand()",
    "Perform DBXTreeNodeHandler.getPerform()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBXTreeNodeHandler actualDbxTreeNodeHandler =
        new DBXTreeNodeHandler(Action.open, Perform.open, "Command");
    Action actualAction = actualDbxTreeNodeHandler.getAction();
    String actualCommand = actualDbxTreeNodeHandler.getCommand();

    // Assert
    assertEquals("Command", actualCommand);
    assertEquals(Action.open, actualAction);
    assertEquals(Perform.open, actualDbxTreeNodeHandler.getPerform());
  }
}
