package org.jkiss.dbeaver.model.websocket.event.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSAccessTokenExpiredEventDiffblueTest {
  /**
   * Test new {@link WSAccessTokenExpiredEvent} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link WSAccessTokenExpiredEvent}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSAccessTokenExpiredEvent.<init>()"})
  public void testNewWSAccessTokenExpiredEvent() {
    // Arrange and Act
    WSAccessTokenExpiredEvent actualWsAccessTokenExpiredEvent = new WSAccessTokenExpiredEvent();

    // Assert
    assertEquals("cb_session", actualWsAccessTokenExpiredEvent.getTopicId());
    assertNull(actualWsAccessTokenExpiredEvent.getSessionId());
    assertNull(actualWsAccessTokenExpiredEvent.getUserId());
    assertFalse(actualWsAccessTokenExpiredEvent.isForceProcessed());
    assertEquals(WSAccessTokenExpiredEvent.ID, actualWsAccessTokenExpiredEvent.getId());
  }
}
