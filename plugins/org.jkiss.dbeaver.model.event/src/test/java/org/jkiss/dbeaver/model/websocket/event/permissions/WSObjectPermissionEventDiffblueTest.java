package org.jkiss.dbeaver.model.websocket.event.permissions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.security.SMObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSObjectPermissionEventDiffblueTest {
  /**
   * Test {@link WSObjectPermissionEvent#WSObjectPermissionEvent(String, SMObjectType, String,
   * String, String)}.
   *
   * <p>Method under test: {@link WSObjectPermissionEvent#WSObjectPermissionEvent(String,
   * SMObjectType, String, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WSObjectPermissionEvent.<init>(String, SMObjectType, String, String, String)"
  })
  public void testNewWSObjectPermissionEvent() {
    // Arrange and Act
    WSObjectPermissionEvent actualWsObjectPermissionEvent =
        new WSObjectPermissionEvent("42", SMObjectType.datasource, "42", "42", "42");

    // Assert
    assertEquals("42", actualWsObjectPermissionEvent.getId());
    assertEquals("42", actualWsObjectPermissionEvent.getSessionId());
    assertEquals("42", actualWsObjectPermissionEvent.getUserId());
    assertEquals("42", actualWsObjectPermissionEvent.getObjectId());
    assertEquals("cb_object_permissions", actualWsObjectPermissionEvent.getTopicId());
    assertEquals(SMObjectType.datasource, actualWsObjectPermissionEvent.getSmObjectType());
    assertFalse(actualWsObjectPermissionEvent.isForceProcessed());
  }

  /**
   * Test {@link WSObjectPermissionEvent#update(String, String, SMObjectType, String)}.
   *
   * <p>Method under test: {@link WSObjectPermissionEvent#update(String, String, SMObjectType,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSObjectPermissionEvent WSObjectPermissionEvent.update(String, String, SMObjectType, String)"
  })
  public void testUpdate() {
    // Arrange and Act
    WSObjectPermissionEvent actualUpdateResult =
        WSObjectPermissionEvent.update("42", "42", SMObjectType.datasource, "42");

    // Assert
    assertEquals("42", actualUpdateResult.getSessionId());
    assertEquals("42", actualUpdateResult.getUserId());
    assertEquals("42", actualUpdateResult.getObjectId());
    assertEquals("cb_object_permissions", actualUpdateResult.getTopicId());
    assertEquals(SMObjectType.datasource, actualUpdateResult.getSmObjectType());
    assertFalse(actualUpdateResult.isForceProcessed());
    assertEquals(WSObjectPermissionEvent.UPDATED, actualUpdateResult.getId());
  }

  /**
   * Test {@link WSObjectPermissionEvent#delete(String, String, SMObjectType, String)}.
   *
   * <p>Method under test: {@link WSObjectPermissionEvent#delete(String, String, SMObjectType,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "WSObjectPermissionEvent WSObjectPermissionEvent.delete(String, String, SMObjectType, String)"
  })
  public void testDelete() {
    // Arrange and Act
    WSObjectPermissionEvent actualDeleteResult =
        WSObjectPermissionEvent.delete("42", "42", SMObjectType.datasource, "42");

    // Assert
    assertEquals("42", actualDeleteResult.getSessionId());
    assertEquals("42", actualDeleteResult.getUserId());
    assertEquals("42", actualDeleteResult.getObjectId());
    assertEquals("cb_object_permissions", actualDeleteResult.getTopicId());
    assertEquals(SMObjectType.datasource, actualDeleteResult.getSmObjectType());
    assertFalse(actualDeleteResult.isForceProcessed());
    assertEquals(WSObjectPermissionEvent.DELETED, actualDeleteResult.getId());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSObjectPermissionEvent#getObjectId()}
   *   <li>{@link WSObjectPermissionEvent#getSmObjectType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String WSObjectPermissionEvent.getObjectId()",
    "SMObjectType WSObjectPermissionEvent.getSmObjectType()"
  })
  public void testGettersAndSetters() {
    // Arrange
    WSObjectPermissionEvent deleteResult =
        WSObjectPermissionEvent.delete("42", "42", SMObjectType.datasource, "42");

    // Act
    String actualObjectId = deleteResult.getObjectId();

    // Assert
    assertEquals("42", actualObjectId);
    assertEquals(SMObjectType.datasource, deleteResult.getSmObjectType());
  }
}
