package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.rm.RMProjectInfo;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSProjectUpdateEventDiffblueTest {
  /**
   * Test {@link WSProjectUpdateEvent#WSProjectUpdateEvent(String, String, String, String,
   * RMProjectInfo)}.
   *
   * <p>Method under test: {@link WSProjectUpdateEvent#WSProjectUpdateEvent(String, String, String,
   * String, RMProjectInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WSProjectUpdateEvent.<init>(String, String, String, String, RMProjectInfo)"
  })
  public void testNewWSProjectUpdateEvent() {
    // Arrange
    RMProjectInfo projectInfo =
        new RMProjectInfo("Name", "The characteristics of someone or something");

    // Act
    WSProjectUpdateEvent actualWsProjectUpdateEvent =
        new WSProjectUpdateEvent("42", "42", "42", "myproject", projectInfo);

    // Assert
    assertEquals("42", actualWsProjectUpdateEvent.getId());
    assertEquals("42", actualWsProjectUpdateEvent.getSessionId());
    assertEquals("42", actualWsProjectUpdateEvent.getUserId());
    assertEquals("cb_projects", actualWsProjectUpdateEvent.getTopicId());
    assertEquals("myproject", actualWsProjectUpdateEvent.getProjectId());
    assertFalse(actualWsProjectUpdateEvent.isForceProcessed());
    assertSame(projectInfo, actualWsProjectUpdateEvent.getProjectInfo());
  }

  /**
   * Test {@link WSProjectUpdateEvent#create(String, String, String)}.
   *
   * <p>Method under test: {@link WSProjectUpdateEvent#create(String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"WSProjectUpdateEvent WSProjectUpdateEvent.create(String, String, String)"})
  public void testCreate() {
    // Arrange and Act
    WSProjectUpdateEvent actualCreateResult = WSProjectUpdateEvent.create("42", "42", "myproject");

    // Assert
    assertEquals("42", actualCreateResult.getSessionId());
    assertEquals("42", actualCreateResult.getUserId());
    assertEquals("cb_projects", actualCreateResult.getTopicId());
    assertEquals("myproject", actualCreateResult.getProjectId());
    assertNull(actualCreateResult.getProjectInfo());
    assertFalse(actualCreateResult.isForceProcessed());
    assertEquals(WSProjectUpdateEvent.ADDED, actualCreateResult.getId());
  }

  /**
   * Test {@link WSProjectUpdateEvent#update(String, String, String, RMProjectInfo)}.
   *
   * <p>Method under test: {@link WSProjectUpdateEvent#update(String, String, String,
   * RMProjectInfo)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSProjectUpdateEvent WSProjectUpdateEvent.update(String, String, String, RMProjectInfo)"
  })
  public void testUpdate() {
    // Arrange
    RMProjectInfo projectInfo =
        new RMProjectInfo("Name", "The characteristics of someone or something");

    // Act
    WSProjectUpdateEvent actualUpdateResult =
        WSProjectUpdateEvent.update("42", "42", "myproject", projectInfo);

    // Assert
    assertEquals("42", actualUpdateResult.getSessionId());
    assertEquals("42", actualUpdateResult.getUserId());
    assertEquals("cb_projects", actualUpdateResult.getTopicId());
    assertEquals("myproject", actualUpdateResult.getProjectId());
    assertFalse(actualUpdateResult.isForceProcessed());
    assertEquals(WSProjectUpdateEvent.UPDATED, actualUpdateResult.getId());
    assertSame(projectInfo, actualUpdateResult.getProjectInfo());
  }

  /**
   * Test {@link WSProjectUpdateEvent#delete(String, String, String)}.
   *
   * <p>Method under test: {@link WSProjectUpdateEvent#delete(String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"WSProjectUpdateEvent WSProjectUpdateEvent.delete(String, String, String)"})
  public void testDelete() {
    // Arrange and Act
    WSProjectUpdateEvent actualDeleteResult = WSProjectUpdateEvent.delete("42", "42", "myproject");

    // Assert
    assertEquals("42", actualDeleteResult.getSessionId());
    assertEquals("42", actualDeleteResult.getUserId());
    assertEquals("cb_projects", actualDeleteResult.getTopicId());
    assertEquals("myproject", actualDeleteResult.getProjectId());
    assertNull(actualDeleteResult.getProjectInfo());
    assertFalse(actualDeleteResult.isForceProcessed());
    assertEquals(WSProjectUpdateEvent.REMOVED, actualDeleteResult.getId());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSProjectUpdateEvent#getProjectId()}
   *   <li>{@link WSProjectUpdateEvent#getProjectInfo()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String WSProjectUpdateEvent.getProjectId()",
    "RMProjectInfo WSProjectUpdateEvent.getProjectInfo()"
  })
  public void testGettersAndSetters() {
    // Arrange
    WSProjectUpdateEvent createResult = WSProjectUpdateEvent.create("42", "42", "myproject");

    // Act
    String actualProjectId = createResult.getProjectId();

    // Assert
    assertEquals("myproject", actualProjectId);
    assertNull(createResult.getProjectInfo());
  }
}
