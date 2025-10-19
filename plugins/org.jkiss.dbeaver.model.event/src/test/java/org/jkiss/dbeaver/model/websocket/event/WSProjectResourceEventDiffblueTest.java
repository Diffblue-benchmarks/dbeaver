package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSProjectResourceEventDiffblueTest {
  /**
   * Test {@link WSProjectResourceEvent#WSProjectResourceEvent(String, String, String, String,
   * String)}.
   *
   * <p>Method under test: {@link WSProjectResourceEvent#WSProjectResourceEvent(String, String,
   * String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSProjectResourceEvent.<init>(String, String, String, String, String)"})
  public void testNewWSProjectResourceEvent() {
    // Arrange and Act
    WSProjectResourceEvent actualWsProjectResourceEvent =
        new WSProjectResourceEvent("42", "42", "42", "42", "myproject");

    // Assert
    assertEquals("42", actualWsProjectResourceEvent.getId());
    assertEquals("42", actualWsProjectResourceEvent.getSessionId());
    assertEquals("42", actualWsProjectResourceEvent.getTopicId());
    assertEquals("42", actualWsProjectResourceEvent.getUserId());
    assertEquals("myproject", actualWsProjectResourceEvent.getProjectId());
    assertFalse(actualWsProjectResourceEvent.isForceProcessed());
  }

  /**
   * Test {@link WSProjectResourceEvent#getProjectId()}.
   *
   * <p>Method under test: {@link WSProjectResourceEvent#getProjectId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WSProjectResourceEvent.getProjectId()"})
  public void testGetProjectId() {
    // Arrange
    WSProjectResourceEvent wsProjectResourceEvent =
        new WSProjectResourceEvent("42", "42", "42", "42", "myproject");

    // Act and Assert
    assertEquals("myproject", wsProjectResourceEvent.getProjectId());
  }
}
