package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSTransactionalCountEventDiffblueTest {
  /**
   * Test {@link WSTransactionalCountEvent#WSTransactionalCountEvent(String, String, String, String,
   * String, int)}.
   *
   * <p>Method under test: {@link WSTransactionalCountEvent#WSTransactionalCountEvent(String,
   * String, String, String, String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WSTransactionalCountEvent.<init>(String, String, String, String, String, int)"
  })
  public void testNewWSTransactionalCountEvent() {
    // Arrange and Act
    WSTransactionalCountEvent actualWsTransactionalCountEvent =
        new WSTransactionalCountEvent("42", "42", "myproject", "42", "42", 3);

    // Assert
    assertEquals("42", actualWsTransactionalCountEvent.getSessionId());
    assertEquals("42", actualWsTransactionalCountEvent.getUserId());
    assertEquals("cb_transaction", actualWsTransactionalCountEvent.getTopicId());
    assertEquals("cb_transaction_count", actualWsTransactionalCountEvent.getId());
    assertEquals("myproject", actualWsTransactionalCountEvent.getProjectId());
    assertFalse(actualWsTransactionalCountEvent.isForceProcessed());
  }
}
