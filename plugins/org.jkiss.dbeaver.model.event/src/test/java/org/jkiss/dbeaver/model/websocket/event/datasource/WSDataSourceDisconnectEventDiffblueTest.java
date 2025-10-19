package org.jkiss.dbeaver.model.websocket.event.datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSDataSourceDisconnectEventDiffblueTest {
  /**
   * Test {@link WSDataSourceDisconnectEvent#WSDataSourceDisconnectEvent(String, String, String,
   * String)}.
   *
   * <p>Method under test: {@link WSDataSourceDisconnectEvent#WSDataSourceDisconnectEvent(String,
   * String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSDataSourceDisconnectEvent.<init>(String, String, String, String)"})
  public void testNewWSDataSourceDisconnectEvent() {
    // Arrange and Act
    WSDataSourceDisconnectEvent actualWsDataSourceDisconnectEvent =
        new WSDataSourceDisconnectEvent("myproject", "42", "42", "42");

    // Assert
    assertEquals("42", actualWsDataSourceDisconnectEvent.getSessionId());
    assertEquals("42", actualWsDataSourceDisconnectEvent.getUserId());
    assertEquals("42", actualWsDataSourceDisconnectEvent.getConnectionId());
    assertEquals("cb_datasource_connection", actualWsDataSourceDisconnectEvent.getTopicId());
    assertEquals("cb_datasource_disconnected", actualWsDataSourceDisconnectEvent.getId());
    assertEquals("myproject", actualWsDataSourceDisconnectEvent.getProjectId());
    assertFalse(actualWsDataSourceDisconnectEvent.isForceProcessed());
  }

  /**
   * Test {@link WSDataSourceDisconnectEvent#getConnectionId()}.
   *
   * <p>Method under test: {@link WSDataSourceDisconnectEvent#getConnectionId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WSDataSourceDisconnectEvent.getConnectionId()"})
  public void testGetConnectionId() {
    // Arrange
    WSDataSourceDisconnectEvent wsDataSourceDisconnectEvent =
        new WSDataSourceDisconnectEvent("myproject", "42", "42", "42");

    // Act and Assert
    assertEquals("42", wsDataSourceDisconnectEvent.getConnectionId());
  }
}
