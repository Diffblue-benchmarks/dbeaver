package org.jkiss.dbeaver.model.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.task.DBTTaskEvent.Action;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBTTaskEventDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBTTaskEvent#DBTTaskEvent(DBTTask, Action)}
   *   <li>{@link DBTTaskEvent#toString()}
   *   <li>{@link DBTTaskEvent#getAction()}
   *   <li>{@link DBTTaskEvent#getTask()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTTaskEvent.<init>(DBTTask, Action)",
    "Action DBTTaskEvent.getAction()",
    "DBTTask DBTTaskEvent.getTask()",
    "java.lang.String DBTTaskEvent.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBTTask task = mock(DBTTask.class);

    // Act
    DBTTaskEvent actualDbtTaskEvent = new DBTTaskEvent(task, Action.TASK_ADD);
    actualDbtTaskEvent.toString();
    Action actualAction = actualDbtTaskEvent.getAction();

    // Assert
    assertEquals(Action.TASK_ADD, actualAction);
    assertSame(task, actualDbtTaskEvent.getTask());
  }
}
