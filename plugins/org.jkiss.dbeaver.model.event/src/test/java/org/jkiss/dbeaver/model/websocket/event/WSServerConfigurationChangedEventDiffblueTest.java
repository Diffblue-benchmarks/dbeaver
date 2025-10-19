package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSServerConfigurationChangedEventDiffblueTest {
  /**
   * Test {@link WSServerConfigurationChangedEvent#WSServerConfigurationChangedEvent()}.
   *
   * <p>Method under test: {@link
   * WSServerConfigurationChangedEvent#WSServerConfigurationChangedEvent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSServerConfigurationChangedEvent.<init>()"})
  public void testNewWSServerConfigurationChangedEvent() {
    // Arrange and Act
    WSServerConfigurationChangedEvent actualWsServerConfigurationChangedEvent =
        new WSServerConfigurationChangedEvent();

    // Assert
    assertEquals("cb_config", actualWsServerConfigurationChangedEvent.getTopicId());
    assertEquals("cb_config_changed", actualWsServerConfigurationChangedEvent.getId());
    assertNull(actualWsServerConfigurationChangedEvent.getSessionId());
    assertNull(actualWsServerConfigurationChangedEvent.getUserId());
    assertFalse(actualWsServerConfigurationChangedEvent.isForceProcessed());
  }

  /**
   * Test {@link WSServerConfigurationChangedEvent#WSServerConfigurationChangedEvent(String,
   * String)}.
   *
   * <p>Method under test: {@link
   * WSServerConfigurationChangedEvent#WSServerConfigurationChangedEvent(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSServerConfigurationChangedEvent.<init>(String, String)"})
  public void testNewWSServerConfigurationChangedEvent2() {
    // Arrange and Act
    WSServerConfigurationChangedEvent actualWsServerConfigurationChangedEvent =
        new WSServerConfigurationChangedEvent("42", "42");

    // Assert
    assertEquals("42", actualWsServerConfigurationChangedEvent.getSessionId());
    assertEquals("42", actualWsServerConfigurationChangedEvent.getUserId());
    assertEquals("cb_config", actualWsServerConfigurationChangedEvent.getTopicId());
    assertEquals("cb_config_changed", actualWsServerConfigurationChangedEvent.getId());
    assertFalse(actualWsServerConfigurationChangedEvent.isForceProcessed());
  }
}
