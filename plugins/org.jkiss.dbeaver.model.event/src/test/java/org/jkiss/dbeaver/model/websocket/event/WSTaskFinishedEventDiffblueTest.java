package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSTaskFinishedEventDiffblueTest {
  /**
   * Test {@link WSTaskFinishedEvent#WSTaskFinishedEvent(String, String)}.
   *
   * <p>Method under test: {@link WSTaskFinishedEvent#WSTaskFinishedEvent(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSTaskFinishedEvent.<init>(String, String)"})
  public void testNewWSTaskFinishedEvent() {
    // Arrange and Act
    WSTaskFinishedEvent actualWsTaskFinishedEvent = new WSTaskFinishedEvent("42", "myproject");

    // Assert
    assertEquals("42", actualWsTaskFinishedEvent.getTaskId());
    assertEquals("cb_task", actualWsTaskFinishedEvent.getTopicId());
    assertEquals("cb_task_finished", actualWsTaskFinishedEvent.getId());
    assertEquals("myproject", actualWsTaskFinishedEvent.getProjectId());
    assertNull(actualWsTaskFinishedEvent.getSessionId());
    assertNull(actualWsTaskFinishedEvent.getUserId());
    assertFalse(actualWsTaskFinishedEvent.isForceProcessed());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSTaskFinishedEvent#getProjectId()}
   *   <li>{@link WSTaskFinishedEvent#getTaskId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String WSTaskFinishedEvent.getProjectId()",
    "String WSTaskFinishedEvent.getTaskId()"
  })
  public void testGettersAndSetters() {
    // Arrange
    WSTaskFinishedEvent wsTaskFinishedEvent = new WSTaskFinishedEvent("42", "myproject");

    // Act
    String actualProjectId = wsTaskFinishedEvent.getProjectId();

    // Assert
    assertEquals("42", wsTaskFinishedEvent.getTaskId());
    assertEquals("myproject", actualProjectId);
  }
}
