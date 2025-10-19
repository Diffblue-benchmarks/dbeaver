package org.jkiss.dbeaver.model.task;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.task.DBTTaskFolderEvent.Action;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBTTaskFolderEventDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBTTaskFolderEvent#DBTTaskFolderEvent(DBTTaskFolder, Action)}
   *   <li>{@link DBTTaskFolderEvent#toString()}
   *   <li>{@link DBTTaskFolderEvent#getAction()}
   *   <li>{@link DBTTaskFolderEvent#getTaskFolder()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTTaskFolderEvent.<init>(DBTTaskFolder, Action)",
    "Action DBTTaskFolderEvent.getAction()",
    "DBTTaskFolder DBTTaskFolderEvent.getTaskFolder()",
    "java.lang.String DBTTaskFolderEvent.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DBTTaskFolder taskFolder = mock(DBTTaskFolder.class);

    // Act
    DBTTaskFolderEvent actualDbtTaskFolderEvent =
        new DBTTaskFolderEvent(taskFolder, Action.TASK_FOLDER_ADD);
    actualDbtTaskFolderEvent.toString();
    Action actualAction = actualDbtTaskFolderEvent.getAction();

    // Assert
    assertEquals(Action.TASK_FOLDER_ADD, actualAction);
    assertSame(taskFolder, actualDbtTaskFolderEvent.getTaskFolder());
  }
}
