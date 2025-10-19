package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSUserDisabledEventDiffblueTest {
  /**
   * Test {@link WSUserDisabledEvent#WSUserDisabledEvent(String)}.
   *
   * <p>Method under test: {@link WSUserDisabledEvent#WSUserDisabledEvent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSUserDisabledEvent.<init>(String)"})
  public void testNewWSUserDisabledEvent() {
    // Arrange and Act
    WSUserDisabledEvent actualWsUserDisabledEvent = new WSUserDisabledEvent("42");

    // Assert
    assertEquals("42", actualWsUserDisabledEvent.getUserId());
    assertEquals("cb_user", actualWsUserDisabledEvent.getTopicId());
    assertNull(actualWsUserDisabledEvent.getSessionId());
    assertFalse(actualWsUserDisabledEvent.isForceProcessed());
    assertEquals(WSUserDisabledEvent.ID, actualWsUserDisabledEvent.getId());
  }
}
