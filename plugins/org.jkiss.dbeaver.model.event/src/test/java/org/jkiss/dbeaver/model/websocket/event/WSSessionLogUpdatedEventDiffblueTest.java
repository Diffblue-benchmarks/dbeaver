package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSSessionLogUpdatedEventDiffblueTest {
  /**
   * Test {@link WSSessionLogUpdatedEvent#WSSessionLogUpdatedEvent(String, String, MessageType,
   * String)}.
   *
   * <p>Method under test: {@link WSSessionLogUpdatedEvent#WSSessionLogUpdatedEvent(String, String,
   * MessageType, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSSessionLogUpdatedEvent.<init>(String, String, MessageType, String)"})
  public void testNewWSSessionLogUpdatedEvent() {
    // Arrange and Act
    WSSessionLogUpdatedEvent actualWsSessionLogUpdatedEvent =
        new WSSessionLogUpdatedEvent("42", "42", MessageType.DEBUG, "Not all who wander are lost");

    // Assert
    assertEquals("42", actualWsSessionLogUpdatedEvent.getSessionId());
    assertEquals("42", actualWsSessionLogUpdatedEvent.getUserId());
    assertEquals("Not all who wander are lost", actualWsSessionLogUpdatedEvent.getMessage());
    assertEquals("cb_session_log", actualWsSessionLogUpdatedEvent.getTopicId());
    assertEquals("cb_session_log_updated", actualWsSessionLogUpdatedEvent.getId());
    assertEquals(MessageType.DEBUG, actualWsSessionLogUpdatedEvent.getMessageType());
    assertFalse(actualWsSessionLogUpdatedEvent.isForceProcessed());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSSessionLogUpdatedEvent#getMessage()}
   *   <li>{@link WSSessionLogUpdatedEvent#getMessageType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String WSSessionLogUpdatedEvent.getMessage()",
    "MessageType WSSessionLogUpdatedEvent.getMessageType()"
  })
  public void testGettersAndSetters() {
    // Arrange
    WSSessionLogUpdatedEvent wsSessionLogUpdatedEvent =
        new WSSessionLogUpdatedEvent("42", "42", MessageType.DEBUG, "Not all who wander are lost");

    // Act
    String actualMessage = wsSessionLogUpdatedEvent.getMessage();

    // Assert
    assertEquals("Not all who wander are lost", actualMessage);
    assertEquals(MessageType.DEBUG, wsSessionLogUpdatedEvent.getMessageType());
  }
}
