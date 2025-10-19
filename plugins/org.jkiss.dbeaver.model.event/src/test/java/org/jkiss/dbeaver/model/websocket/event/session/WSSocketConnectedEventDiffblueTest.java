package org.jkiss.dbeaver.model.websocket.event.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSSocketConnectedEventDiffblueTest {
  /**
   * Test {@link WSSocketConnectedEvent#WSSocketConnectedEvent(String)}.
   *
   * <p>Method under test: {@link WSSocketConnectedEvent#WSSocketConnectedEvent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSSocketConnectedEvent.<init>(String)"})
  public void testNewWSSocketConnectedEvent() {
    // Arrange and Act
    WSSocketConnectedEvent actualWsSocketConnectedEvent = new WSSocketConnectedEvent("42");

    // Assert
    assertEquals("42", actualWsSocketConnectedEvent.getApplicationRunId());
    assertEquals("cb_session", actualWsSocketConnectedEvent.getTopicId());
    assertEquals("cb_session_websocket_connected", actualWsSocketConnectedEvent.getId());
    assertNull(actualWsSocketConnectedEvent.getSessionId());
    assertNull(actualWsSocketConnectedEvent.getUserId());
    assertFalse(actualWsSocketConnectedEvent.isForceProcessed());
  }

  /**
   * Test {@link WSSocketConnectedEvent#getApplicationRunId()}.
   *
   * <p>Method under test: {@link WSSocketConnectedEvent#getApplicationRunId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WSSocketConnectedEvent.getApplicationRunId()"})
  public void testGetApplicationRunId() {
    // Arrange, Act and Assert
    assertEquals("42", new WSSocketConnectedEvent("42").getApplicationRunId());
  }
}
