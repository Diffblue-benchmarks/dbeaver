package org.jkiss.dbeaver.model.websocket.event.client;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSSessionPingClientEventDiffblueTest {
  /**
   * Test {@link WSSessionPingClientEvent#WSSessionPingClientEvent(String)}.
   *
   * <p>Method under test: {@link WSSessionPingClientEvent#WSSessionPingClientEvent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSSessionPingClientEvent.<init>(String)"})
  public void testNewWSSessionPingClientEvent() {
    // Arrange and Act
    WSSessionPingClientEvent actualWsSessionPingClientEvent = new WSSessionPingClientEvent("42");

    // Assert
    assertEquals("42", actualWsSessionPingClientEvent.getTopicId());
    assertEquals(WSSessionPingClientEvent.ID, actualWsSessionPingClientEvent.getId());
  }
}
