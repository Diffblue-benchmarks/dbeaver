package org.jkiss.dbeaver.model.websocket.event.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSSessionExpiredEventDiffblueTest {
  /**
   * Test new {@link WSSessionExpiredEvent} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link WSSessionExpiredEvent}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSSessionExpiredEvent.<init>()"})
  public void testNewWSSessionExpiredEvent() {
    // Arrange and Act
    WSSessionExpiredEvent actualWsSessionExpiredEvent = new WSSessionExpiredEvent();

    // Assert
    assertEquals("cb_session", actualWsSessionExpiredEvent.getTopicId());
    assertNull(actualWsSessionExpiredEvent.getSessionId());
    assertNull(actualWsSessionExpiredEvent.getUserId());
    assertFalse(actualWsSessionExpiredEvent.isForceProcessed());
    assertEquals(WSSessionExpiredEvent.ID, actualWsSessionExpiredEvent.getId());
  }
}
