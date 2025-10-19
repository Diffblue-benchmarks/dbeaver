package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSServerStateEventDiffblueTest {
  /**
   * Test {@link WSServerStateEvent#WSServerStateEvent(String, String, String)}.
   *
   * <p>Method under test: {@link WSServerStateEvent#WSServerStateEvent(String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSServerStateEvent.<init>(String, String, String)"})
  public void testNewWSServerStateEvent() {
    // Arrange and Act
    WSServerStateEvent actualWsServerStateEvent = new WSServerStateEvent("42", "42", "Action");

    // Assert
    assertEquals("42", actualWsServerStateEvent.getSessionId());
    assertEquals("42", actualWsServerStateEvent.getUserId());
    assertEquals("Action", actualWsServerStateEvent.getAction());
    assertEquals("cb_server_state", actualWsServerStateEvent.getTopicId());
    assertEquals("cb_server_state_updated", actualWsServerStateEvent.getId());
    assertTrue(actualWsServerStateEvent.isForceProcessed());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSServerStateEvent#getAction()}
   *   <li>{@link WSServerStateEvent#isForceProcessed()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String WSServerStateEvent.getAction()",
    "boolean WSServerStateEvent.isForceProcessed()"
  })
  public void testGettersAndSetters() {
    // Arrange
    WSServerStateEvent wsServerStateEvent = new WSServerStateEvent("42", "42", "Action");

    // Act
    String actualAction = wsServerStateEvent.getAction();

    // Assert
    assertEquals("Action", actualAction);
    assertTrue(wsServerStateEvent.isForceProcessed());
  }
}
