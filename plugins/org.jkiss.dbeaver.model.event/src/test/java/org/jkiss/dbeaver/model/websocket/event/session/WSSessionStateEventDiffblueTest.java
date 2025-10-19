package org.jkiss.dbeaver.model.websocket.event.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSSessionStateEventDiffblueTest {
  /**
   * Test {@link WSSessionStateEvent#WSSessionStateEvent(long, long, boolean, boolean, String,
   * Map)}.
   *
   * <p>Method under test: {@link WSSessionStateEvent#WSSessionStateEvent(long, long, boolean,
   * boolean, String, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSSessionStateEvent.<init>(long, long, boolean, boolean, String, Map)"})
  public void testNewWSSessionStateEvent() {
    // Arrange and Act
    WSSessionStateEvent actualWsSessionStateEvent =
        new WSSessionStateEvent(1L, 1L, true, true, "en", new HashMap<>());

    // Assert
    assertEquals("cb_session", actualWsSessionStateEvent.getTopicId());
    assertEquals("cb_session_state", actualWsSessionStateEvent.getId());
    assertEquals("en", actualWsSessionStateEvent.getLocale());
    assertNull(actualWsSessionStateEvent.getSessionId());
    assertNull(actualWsSessionStateEvent.getUserId());
    assertEquals(1L, actualWsSessionStateEvent.getLastAccessTime());
    assertEquals(1L, actualWsSessionStateEvent.getRemainingTime());
    assertFalse(actualWsSessionStateEvent.isForceProcessed());
    assertTrue(actualWsSessionStateEvent.getActionParameters().isEmpty());
    assertTrue(actualWsSessionStateEvent.isCacheExpired());
    assertTrue(actualWsSessionStateEvent.isValid());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSSessionStateEvent#getActionParameters()}
   *   <li>{@link WSSessionStateEvent#getLastAccessTime()}
   *   <li>{@link WSSessionStateEvent#getLocale()}
   *   <li>{@link WSSessionStateEvent#getRemainingTime()}
   *   <li>{@link WSSessionStateEvent#isCacheExpired()}
   *   <li>{@link WSSessionStateEvent#isValid()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Map WSSessionStateEvent.getActionParameters()",
    "long WSSessionStateEvent.getLastAccessTime()",
    "String WSSessionStateEvent.getLocale()",
    "long WSSessionStateEvent.getRemainingTime()",
    "boolean WSSessionStateEvent.isCacheExpired()",
    "boolean WSSessionStateEvent.isValid()"
  })
  public void testGettersAndSetters() {
    // Arrange
    HashMap<String, Object> actionParameters = new HashMap<>();
    WSSessionStateEvent wsSessionStateEvent =
        new WSSessionStateEvent(1L, 1L, true, true, "en", actionParameters);

    // Act
    Map<String, Object> actualActionParameters = wsSessionStateEvent.getActionParameters();
    long actualLastAccessTime = wsSessionStateEvent.getLastAccessTime();
    String actualLocale = wsSessionStateEvent.getLocale();
    long actualRemainingTime = wsSessionStateEvent.getRemainingTime();
    boolean actualIsCacheExpiredResult = wsSessionStateEvent.isCacheExpired();
    boolean actualIsValidResult = wsSessionStateEvent.isValid();

    // Assert
    assertEquals("en", actualLocale);
    assertEquals(1L, actualLastAccessTime);
    assertEquals(1L, actualRemainingTime);
    assertTrue(actualActionParameters.isEmpty());
    assertTrue(actualIsCacheExpiredResult);
    assertTrue(actualIsValidResult);
    assertSame(actionParameters, actualActionParameters);
  }
}
