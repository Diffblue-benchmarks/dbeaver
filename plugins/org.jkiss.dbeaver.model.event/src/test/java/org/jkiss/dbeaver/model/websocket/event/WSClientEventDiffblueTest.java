package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.websocket.event.client.WSSubscribeOnTopicClientEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSClientEventDiffblueTest {
  /**
   * Test {@link WSClientEvent#getId()}.
   *
   * <p>Method under test: {@link WSClientEvent#getId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String WSClientEvent.getId()"})
  public void testGetId() {
    // Arrange, Act and Assert
    assertEquals("cb_client_topic_subscribe", new WSSubscribeOnTopicClientEvent("42").getId());
  }

  /**
   * Test {@link WSClientEvent#getTopicId()}.
   *
   * <p>Method under test: {@link WSClientEvent#getTopicId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String WSClientEvent.getTopicId()"})
  public void testGetTopicId() {
    // Arrange, Act and Assert
    assertEquals("42", new WSSubscribeOnTopicClientEvent("42").getTopicId());
  }
}
