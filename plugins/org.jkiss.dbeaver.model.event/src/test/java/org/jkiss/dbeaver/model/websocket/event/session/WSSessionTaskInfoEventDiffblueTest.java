package org.jkiss.dbeaver.model.websocket.event.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSSessionTaskInfoEventDiffblueTest {
  /**
   * Test {@link WSSessionTaskInfoEvent#WSSessionTaskInfoEvent(String, String, boolean)}.
   *
   * <p>Method under test: {@link WSSessionTaskInfoEvent#WSSessionTaskInfoEvent(String, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSSessionTaskInfoEvent.<init>(String, String, boolean)"})
  public void testNewWSSessionTaskInfoEvent() {
    // Arrange and Act
    WSSessionTaskInfoEvent actualWsSessionTaskInfoEvent =
        new WSSessionTaskInfoEvent("42", "Status Name", true);

    // Assert
    assertEquals("42", actualWsSessionTaskInfoEvent.getTaskId());
    assertEquals("Status Name", actualWsSessionTaskInfoEvent.getStatusName());
    assertEquals("cb_session_task", actualWsSessionTaskInfoEvent.getTopicId());
    assertEquals("cb_session_task_info_updated", actualWsSessionTaskInfoEvent.getId());
    assertNull(actualWsSessionTaskInfoEvent.getSessionId());
    assertNull(actualWsSessionTaskInfoEvent.getUserId());
    assertFalse(actualWsSessionTaskInfoEvent.isForceProcessed());
    assertTrue(actualWsSessionTaskInfoEvent.isRunning());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSSessionTaskInfoEvent#getStatusName()}
   *   <li>{@link WSSessionTaskInfoEvent#getTaskId()}
   *   <li>{@link WSSessionTaskInfoEvent#isRunning()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String WSSessionTaskInfoEvent.getStatusName()",
    "String WSSessionTaskInfoEvent.getTaskId()",
    "boolean WSSessionTaskInfoEvent.isRunning()"
  })
  public void testGettersAndSetters() {
    // Arrange
    WSSessionTaskInfoEvent wsSessionTaskInfoEvent =
        new WSSessionTaskInfoEvent("42", "Status Name", true);

    // Act
    String actualStatusName = wsSessionTaskInfoEvent.getStatusName();
    String actualTaskId = wsSessionTaskInfoEvent.getTaskId();

    // Assert
    assertEquals("42", actualTaskId);
    assertEquals("Status Name", actualStatusName);
    assertTrue(wsSessionTaskInfoEvent.isRunning());
  }
}
