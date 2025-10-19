package org.jkiss.dbeaver.model.websocket.event.datasource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSDataSourceConnectEventDiffblueTest {
  /**
   * Test {@link WSDataSourceConnectEvent#WSDataSourceConnectEvent(String, String, String, String)}.
   *
   * <p>Method under test: {@link WSDataSourceConnectEvent#WSDataSourceConnectEvent(String, String,
   * String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSDataSourceConnectEvent.<init>(String, String, String, String)"})
  public void testNewWSDataSourceConnectEvent() {
    // Arrange and Act
    WSDataSourceConnectEvent actualWsDataSourceConnectEvent =
        new WSDataSourceConnectEvent("myproject", "42", "42", "42");

    // Assert
    assertEquals("42", actualWsDataSourceConnectEvent.getSessionId());
    assertEquals("42", actualWsDataSourceConnectEvent.getUserId());
    assertEquals("42", actualWsDataSourceConnectEvent.getConnectionId());
    assertEquals("cb_datasource_connected", actualWsDataSourceConnectEvent.getId());
    assertEquals("cb_datasource_connection", actualWsDataSourceConnectEvent.getTopicId());
    assertEquals("myproject", actualWsDataSourceConnectEvent.getProjectId());
    assertFalse(actualWsDataSourceConnectEvent.isForceProcessed());
  }

  /**
   * Test {@link WSDataSourceConnectEvent#getConnectionId()}.
   *
   * <p>Method under test: {@link WSDataSourceConnectEvent#getConnectionId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WSDataSourceConnectEvent.getConnectionId()"})
  public void testGetConnectionId() {
    // Arrange
    WSDataSourceConnectEvent wsDataSourceConnectEvent =
        new WSDataSourceConnectEvent("myproject", "42", "42", "42");

    // Act and Assert
    assertEquals("42", wsDataSourceConnectEvent.getConnectionId());
  }
}
