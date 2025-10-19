package org.jkiss.dbeaver.model.websocket.event.client;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSSubscribeOnTopicClientEventDiffblueTest {
  /**
   * Test {@link WSSubscribeOnTopicClientEvent#WSSubscribeOnTopicClientEvent(String)}.
   *
   * <p>Method under test: {@link
   * WSSubscribeOnTopicClientEvent#WSSubscribeOnTopicClientEvent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSSubscribeOnTopicClientEvent.<init>(String)"})
  public void testNewWSSubscribeOnTopicClientEvent() {
    // Arrange and Act
    WSSubscribeOnTopicClientEvent actualWsSubscribeOnTopicClientEvent =
        new WSSubscribeOnTopicClientEvent("42");

    // Assert
    assertEquals("42", actualWsSubscribeOnTopicClientEvent.getTopicId());
    assertEquals(WSSubscribeOnTopicClientEvent.ID, actualWsSubscribeOnTopicClientEvent.getId());
  }
}
