package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSWorkspaceConfigurationChangedEventDiffblueTest {
  /**
   * Test {@link WSWorkspaceConfigurationChangedEvent#WSWorkspaceConfigurationChangedEvent(String,
   * String, String)}.
   *
   * <p>Method under test: {@link
   * WSWorkspaceConfigurationChangedEvent#WSWorkspaceConfigurationChangedEvent(String, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSWorkspaceConfigurationChangedEvent.<init>(String, String, String)"})
  public void testNewWSWorkspaceConfigurationChangedEvent() {
    // Arrange and Act
    WSWorkspaceConfigurationChangedEvent actualWsWorkspaceConfigurationChangedEvent =
        new WSWorkspaceConfigurationChangedEvent("/directory/foo.txt", "42", "42");

    // Assert
    assertEquals(
        "/directory/foo.txt", actualWsWorkspaceConfigurationChangedEvent.getConfigFilePath());
    assertEquals("42", actualWsWorkspaceConfigurationChangedEvent.getSessionId());
    assertEquals("42", actualWsWorkspaceConfigurationChangedEvent.getUserId());
    assertEquals("cb_workspace_config_changed", actualWsWorkspaceConfigurationChangedEvent.getId());
    assertEquals(
        "cb_workspace_configuration", actualWsWorkspaceConfigurationChangedEvent.getTopicId());
    assertFalse(actualWsWorkspaceConfigurationChangedEvent.isForceProcessed());
  }

  /**
   * Test {@link WSWorkspaceConfigurationChangedEvent#getConfigFilePath()}.
   *
   * <p>Method under test: {@link WSWorkspaceConfigurationChangedEvent#getConfigFilePath()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WSWorkspaceConfigurationChangedEvent.getConfigFilePath()"})
  public void testGetConfigFilePath() {
    // Arrange
    WSWorkspaceConfigurationChangedEvent wsWorkspaceConfigurationChangedEvent =
        new WSWorkspaceConfigurationChangedEvent("/directory/foo.txt", "42", "42");

    // Act and Assert
    assertEquals("/directory/foo.txt", wsWorkspaceConfigurationChangedEvent.getConfigFilePath());
  }
}
