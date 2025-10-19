package org.jkiss.dbeaver.model.websocket.event.resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSResourceUpdatedEventDiffblueTest {
  /**
   * Test {@link WSResourceUpdatedEvent#create(String, String, String, String, WSResourceProperty,
   * String)}.
   *
   * <p>Method under test: {@link WSResourceUpdatedEvent#create(String, String, String, String,
   * WSResourceProperty, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSResourceUpdatedEvent WSResourceUpdatedEvent.create(String, String, String, String, WSResourceProperty, String)"
  })
  public void testCreate() {
    // Arrange and Act
    WSResourceUpdatedEvent actualCreateResult =
        WSResourceUpdatedEvent.create(
            "42", "42", "myproject", "Resource Path", WSResourceProperty.CONTENT, "Details");

    // Assert
    assertEquals("42", actualCreateResult.getSessionId());
    assertEquals("42", actualCreateResult.getUserId());
    assertEquals("Details", actualCreateResult.getDetails());
    assertEquals("Resource Path", actualCreateResult.getResourcePath());
    assertEquals("cb_scripts", actualCreateResult.getTopicId());
    assertEquals("myproject", actualCreateResult.getProjectId());
    assertEquals(WSResourceProperty.CONTENT, actualCreateResult.getProperty());
    assertFalse(actualCreateResult.isForceProcessed());
    assertEquals(WSResourceUpdatedEvent.CREATED, actualCreateResult.getId());
  }

  /**
   * Test {@link WSResourceUpdatedEvent#update(String, String, String, String, WSResourceProperty,
   * String)}.
   *
   * <p>Method under test: {@link WSResourceUpdatedEvent#update(String, String, String, String,
   * WSResourceProperty, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSResourceUpdatedEvent WSResourceUpdatedEvent.update(String, String, String, String, WSResourceProperty, String)"
  })
  public void testUpdate() {
    // Arrange and Act
    WSResourceUpdatedEvent actualUpdateResult =
        WSResourceUpdatedEvent.update(
            "42", "42", "myproject", "Resource Path", WSResourceProperty.CONTENT, "Details");

    // Assert
    assertEquals("42", actualUpdateResult.getSessionId());
    assertEquals("42", actualUpdateResult.getUserId());
    assertEquals("Details", actualUpdateResult.getDetails());
    assertEquals("Resource Path", actualUpdateResult.getResourcePath());
    assertEquals("cb_scripts", actualUpdateResult.getTopicId());
    assertEquals("myproject", actualUpdateResult.getProjectId());
    assertEquals(WSResourceProperty.CONTENT, actualUpdateResult.getProperty());
    assertFalse(actualUpdateResult.isForceProcessed());
    assertEquals(WSResourceUpdatedEvent.UPDATED, actualUpdateResult.getId());
  }

  /**
   * Test {@link WSResourceUpdatedEvent#delete(String, String, String, String, WSResourceProperty,
   * String)}.
   *
   * <p>Method under test: {@link WSResourceUpdatedEvent#delete(String, String, String, String,
   * WSResourceProperty, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSResourceUpdatedEvent WSResourceUpdatedEvent.delete(String, String, String, String, WSResourceProperty, String)"
  })
  public void testDelete() {
    // Arrange and Act
    WSResourceUpdatedEvent actualDeleteResult =
        WSResourceUpdatedEvent.delete(
            "42", "42", "myproject", "Resource Path", WSResourceProperty.CONTENT, "Details");

    // Assert
    assertEquals("42", actualDeleteResult.getSessionId());
    assertEquals("42", actualDeleteResult.getUserId());
    assertEquals("Details", actualDeleteResult.getDetails());
    assertEquals("Resource Path", actualDeleteResult.getResourcePath());
    assertEquals("cb_scripts", actualDeleteResult.getTopicId());
    assertEquals("myproject", actualDeleteResult.getProjectId());
    assertEquals(WSResourceProperty.CONTENT, actualDeleteResult.getProperty());
    assertFalse(actualDeleteResult.isForceProcessed());
    assertEquals(WSResourceUpdatedEvent.DELETED, actualDeleteResult.getId());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSResourceUpdatedEvent#getDetails()}
   *   <li>{@link WSResourceUpdatedEvent#getProjectId()}
   *   <li>{@link WSResourceUpdatedEvent#getProperty()}
   *   <li>{@link WSResourceUpdatedEvent#getResourcePath()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String WSResourceUpdatedEvent.getDetails()",
    "String WSResourceUpdatedEvent.getProjectId()",
    "WSResourceProperty WSResourceUpdatedEvent.getProperty()",
    "String WSResourceUpdatedEvent.getResourcePath()"
  })
  public void testGettersAndSetters() {
    // Arrange
    WSResourceUpdatedEvent createResult =
        WSResourceUpdatedEvent.create(
            "42", "42", "myproject", "Resource Path", WSResourceProperty.CONTENT, "Details");

    // Act
    String actualDetails = createResult.getDetails();
    String actualProjectId = createResult.getProjectId();
    WSResourceProperty actualProperty = createResult.getProperty();

    // Assert
    assertEquals("Details", actualDetails);
    assertEquals("Resource Path", createResult.getResourcePath());
    assertEquals("myproject", actualProjectId);
    assertEquals(WSResourceProperty.CONTENT, actualProperty);
  }
}
