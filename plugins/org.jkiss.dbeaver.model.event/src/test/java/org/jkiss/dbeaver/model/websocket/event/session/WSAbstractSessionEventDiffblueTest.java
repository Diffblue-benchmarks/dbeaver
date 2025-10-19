package org.jkiss.dbeaver.model.websocket.event.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSAbstractSessionEventDiffblueTest {
  /**
   * Test {@link WSAbstractSessionEvent#WSAbstractSessionEvent(String, String)}.
   *
   * <p>Method under test: {@link WSAbstractSessionEvent#WSAbstractSessionEvent(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSAbstractSessionEvent.<init>(String, String)"})
  public void testNewWSAbstractSessionEvent() {
    // Arrange and Act
    WSAbstractSessionEvent actualWsAbstractSessionEvent = new WSAbstractSessionEvent("42", "42");

    // Assert
    assertEquals("42", actualWsAbstractSessionEvent.getId());
    assertEquals("42", actualWsAbstractSessionEvent.getTopicId());
    assertNull(actualWsAbstractSessionEvent.getSessionId());
    assertNull(actualWsAbstractSessionEvent.getUserId());
    assertFalse(actualWsAbstractSessionEvent.isForceProcessed());
  }
}
