package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSUserSecretEventDiffblueTest {
  /**
   * Test {@link WSUserSecretEvent#WSUserSecretEvent(String, String, String, String)}.
   *
   * <p>Method under test: {@link WSUserSecretEvent#WSUserSecretEvent(String, String, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSUserSecretEvent.<init>(String, String, String, String)"})
  public void testNewWSUserSecretEvent() {
    // Arrange and Act
    WSUserSecretEvent actualWsUserSecretEvent =
        new WSUserSecretEvent("myproject", "42", "42", "42");

    // Assert
    assertEquals("42", actualWsUserSecretEvent.getSessionId());
    assertEquals("42", actualWsUserSecretEvent.getUserId());
    assertEquals("42", actualWsUserSecretEvent.getDataSourceId());
    assertEquals("cb_user_secret", actualWsUserSecretEvent.getTopicId());
    assertEquals("cb_user_secret_updated", actualWsUserSecretEvent.getId());
    assertEquals("myproject", actualWsUserSecretEvent.getProjectId());
    assertFalse(actualWsUserSecretEvent.isForceProcessed());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSUserSecretEvent#getDataSourceId()}
   *   <li>{@link WSUserSecretEvent#getProjectId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String WSUserSecretEvent.getDataSourceId()",
    "String WSUserSecretEvent.getProjectId()"
  })
  public void testGettersAndSetters() {
    // Arrange
    WSUserSecretEvent wsUserSecretEvent = new WSUserSecretEvent("myproject", "42", "42", "42");

    // Act
    String actualDataSourceId = wsUserSecretEvent.getDataSourceId();

    // Assert
    assertEquals("42", actualDataSourceId);
    assertEquals("myproject", wsUserSecretEvent.getProjectId());
  }
}
