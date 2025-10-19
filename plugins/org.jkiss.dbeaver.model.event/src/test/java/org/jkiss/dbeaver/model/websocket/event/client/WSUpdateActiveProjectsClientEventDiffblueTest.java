package org.jkiss.dbeaver.model.websocket.event.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSUpdateActiveProjectsClientEventDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSUpdateActiveProjectsClientEvent#WSUpdateActiveProjectsClientEvent(Set)}
   *   <li>{@link WSUpdateActiveProjectsClientEvent#getProjectIds()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WSUpdateActiveProjectsClientEvent.<init>(Set)",
    "Set WSUpdateActiveProjectsClientEvent.getProjectIds()"
  })
  public void testGettersAndSetters() {
    // Arrange
    HashSet<String> projectIds = new HashSet<>();

    // Act
    WSUpdateActiveProjectsClientEvent actualWsUpdateActiveProjectsClientEvent =
        new WSUpdateActiveProjectsClientEvent(projectIds);
    Set<String> actualProjectIds = actualWsUpdateActiveProjectsClientEvent.getProjectIds();

    // Assert
    assertEquals("cb_projects", actualWsUpdateActiveProjectsClientEvent.getTopicId());
    assertTrue(actualProjectIds.isEmpty());
    assertEquals(
        WSUpdateActiveProjectsClientEvent.ID, actualWsUpdateActiveProjectsClientEvent.getId());
    assertSame(projectIds, actualProjectIds);
  }
}
