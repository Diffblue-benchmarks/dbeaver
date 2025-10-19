package org.jkiss.dbeaver.model.websocket.event.client;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSUnsubscribeFromTopicClientEventDiffblueTest {
  /**
   * Test {@link WSUnsubscribeFromTopicClientEvent#WSUnsubscribeFromTopicClientEvent(String)}.
   *
   * <p>Method under test: {@link
   * WSUnsubscribeFromTopicClientEvent#WSUnsubscribeFromTopicClientEvent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSUnsubscribeFromTopicClientEvent.<init>(String)"})
  public void testNewWSUnsubscribeFromTopicClientEvent() {
    // Arrange and Act
    WSUnsubscribeFromTopicClientEvent actualWsUnsubscribeFromTopicClientEvent =
        new WSUnsubscribeFromTopicClientEvent("42");

    // Assert
    assertEquals("42", actualWsUnsubscribeFromTopicClientEvent.getTopicId());
    assertEquals(
        WSUnsubscribeFromTopicClientEvent.ID, actualWsUnsubscribeFromTopicClientEvent.getId());
  }
}
