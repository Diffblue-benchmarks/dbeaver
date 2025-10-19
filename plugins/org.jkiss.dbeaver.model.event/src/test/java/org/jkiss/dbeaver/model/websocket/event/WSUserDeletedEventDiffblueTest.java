package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSUserDeletedEventDiffblueTest {
  /**
   * Test {@link WSUserDeletedEvent#WSUserDeletedEvent(String)}.
   *
   * <p>Method under test: {@link WSUserDeletedEvent#WSUserDeletedEvent(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSUserDeletedEvent.<init>(String)"})
  public void testNewWSUserDeletedEvent() {
    // Arrange and Act
    WSUserDeletedEvent actualWsUserDeletedEvent = new WSUserDeletedEvent("42");

    // Assert
    assertEquals("42", actualWsUserDeletedEvent.getUserId());
    assertEquals("42", actualWsUserDeletedEvent.getDeletedUserId());
    assertEquals("cb_user", actualWsUserDeletedEvent.getTopicId());
    assertNull(actualWsUserDeletedEvent.getSessionId());
    assertFalse(actualWsUserDeletedEvent.isForceProcessed());
    assertEquals(WSUserDeletedEvent.ID, actualWsUserDeletedEvent.getId());
  }

  /**
   * Test {@link WSUserDeletedEvent#getDeletedUserId()}.
   *
   * <p>Method under test: {@link WSUserDeletedEvent#getDeletedUserId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WSUserDeletedEvent.getDeletedUserId()"})
  public void testGetDeletedUserId() {
    // Arrange, Act and Assert
    assertEquals("42", new WSUserDeletedEvent("42").getDeletedUserId());
  }
}
