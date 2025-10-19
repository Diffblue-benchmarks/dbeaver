package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSUserCloseSessionsEventDiffblueTest {
  /**
   * Test {@link WSUserCloseSessionsEvent#WSUserCloseSessionsEvent(List)}.
   *
   * <ul>
   *   <li>Given {@code cb_user}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code cb_user}.
   * </ul>
   *
   * <p>Method under test: {@link WSUserCloseSessionsEvent#WSUserCloseSessionsEvent(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSUserCloseSessionsEvent.<init>(List)"})
  public void testNewWSUserCloseSessionsEvent_givenCbUser_whenArrayListAddCbUser() {
    // Arrange
    ArrayList<String> sessionIds = new ArrayList<>();
    sessionIds.add("cb_user");
    sessionIds.add(WSUserCloseSessionsEvent.ID);

    // Act
    WSUserCloseSessionsEvent actualWsUserCloseSessionsEvent =
        new WSUserCloseSessionsEvent(sessionIds);

    // Assert
    assertEquals("cb_user", actualWsUserCloseSessionsEvent.getTopicId());
    assertNull(actualWsUserCloseSessionsEvent.getSessionId());
    assertNull(actualWsUserCloseSessionsEvent.getUserId());
    assertFalse(actualWsUserCloseSessionsEvent.isForceProcessed());
    assertEquals(WSUserCloseSessionsEvent.ID, actualWsUserCloseSessionsEvent.getId());
    assertSame(sessionIds, actualWsUserCloseSessionsEvent.getSessionIds());
  }

  /**
   * Test {@link WSUserCloseSessionsEvent#WSUserCloseSessionsEvent(List, String, String)}.
   *
   * <ul>
   *   <li>Given {@code cb_user}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code cb_user}.
   * </ul>
   *
   * <p>Method under test: {@link WSUserCloseSessionsEvent#WSUserCloseSessionsEvent(List, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSUserCloseSessionsEvent.<init>(List, String, String)"})
  public void testNewWSUserCloseSessionsEvent_givenCbUser_whenArrayListAddCbUser2() {
    // Arrange
    ArrayList<String> sessionIds = new ArrayList<>();
    sessionIds.add("cb_user");
    sessionIds.add(WSUserCloseSessionsEvent.ID);

    // Act
    WSUserCloseSessionsEvent actualWsUserCloseSessionsEvent =
        new WSUserCloseSessionsEvent(sessionIds, "42", "42");

    // Assert
    assertEquals("42", actualWsUserCloseSessionsEvent.getSessionId());
    assertEquals("42", actualWsUserCloseSessionsEvent.getUserId());
    assertEquals("cb_user", actualWsUserCloseSessionsEvent.getTopicId());
    assertFalse(actualWsUserCloseSessionsEvent.isForceProcessed());
    assertEquals(WSUserCloseSessionsEvent.ID, actualWsUserCloseSessionsEvent.getId());
    assertSame(sessionIds, actualWsUserCloseSessionsEvent.getSessionIds());
  }

  /**
   * Test {@link WSUserCloseSessionsEvent#WSUserCloseSessionsEvent(List)}.
   *
   * <ul>
   *   <li>Given {@link WSUserCloseSessionsEvent#ID}.
   *   <li>Then return SessionIds is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link WSUserCloseSessionsEvent#WSUserCloseSessionsEvent(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSUserCloseSessionsEvent.<init>(List)"})
  public void testNewWSUserCloseSessionsEvent_givenId_thenReturnSessionIdsIsArrayList() {
    // Arrange
    ArrayList<String> sessionIds = new ArrayList<>();
    sessionIds.add(WSUserCloseSessionsEvent.ID);

    // Act
    WSUserCloseSessionsEvent actualWsUserCloseSessionsEvent =
        new WSUserCloseSessionsEvent(sessionIds);

    // Assert
    assertEquals("cb_user", actualWsUserCloseSessionsEvent.getTopicId());
    assertNull(actualWsUserCloseSessionsEvent.getSessionId());
    assertNull(actualWsUserCloseSessionsEvent.getUserId());
    assertFalse(actualWsUserCloseSessionsEvent.isForceProcessed());
    assertEquals(WSUserCloseSessionsEvent.ID, actualWsUserCloseSessionsEvent.getId());
    assertSame(sessionIds, actualWsUserCloseSessionsEvent.getSessionIds());
  }

  /**
   * Test {@link WSUserCloseSessionsEvent#WSUserCloseSessionsEvent(List, String, String)}.
   *
   * <ul>
   *   <li>Given {@link WSUserCloseSessionsEvent#ID}.
   *   <li>Then return SessionIds is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link WSUserCloseSessionsEvent#WSUserCloseSessionsEvent(List, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSUserCloseSessionsEvent.<init>(List, String, String)"})
  public void testNewWSUserCloseSessionsEvent_givenId_thenReturnSessionIdsIsArrayList2() {
    // Arrange
    ArrayList<String> sessionIds = new ArrayList<>();
    sessionIds.add(WSUserCloseSessionsEvent.ID);

    // Act
    WSUserCloseSessionsEvent actualWsUserCloseSessionsEvent =
        new WSUserCloseSessionsEvent(sessionIds, "42", "42");

    // Assert
    assertEquals("42", actualWsUserCloseSessionsEvent.getSessionId());
    assertEquals("42", actualWsUserCloseSessionsEvent.getUserId());
    assertEquals("cb_user", actualWsUserCloseSessionsEvent.getTopicId());
    assertFalse(actualWsUserCloseSessionsEvent.isForceProcessed());
    assertEquals(WSUserCloseSessionsEvent.ID, actualWsUserCloseSessionsEvent.getId());
    assertSame(sessionIds, actualWsUserCloseSessionsEvent.getSessionIds());
  }

  /**
   * Test {@link WSUserCloseSessionsEvent#WSUserCloseSessionsEvent(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return SessionIds Empty.
   * </ul>
   *
   * <p>Method under test: {@link WSUserCloseSessionsEvent#WSUserCloseSessionsEvent(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSUserCloseSessionsEvent.<init>(List)"})
  public void testNewWSUserCloseSessionsEvent_whenArrayList_thenReturnSessionIdsEmpty() {
    // Arrange and Act
    WSUserCloseSessionsEvent actualWsUserCloseSessionsEvent =
        new WSUserCloseSessionsEvent(new ArrayList<>());

    // Assert
    assertEquals("cb_user", actualWsUserCloseSessionsEvent.getTopicId());
    assertNull(actualWsUserCloseSessionsEvent.getSessionId());
    assertNull(actualWsUserCloseSessionsEvent.getUserId());
    assertFalse(actualWsUserCloseSessionsEvent.isForceProcessed());
    assertTrue(actualWsUserCloseSessionsEvent.getSessionIds().isEmpty());
    assertEquals(WSUserCloseSessionsEvent.ID, actualWsUserCloseSessionsEvent.getId());
  }

  /**
   * Test {@link WSUserCloseSessionsEvent#WSUserCloseSessionsEvent(List, String, String)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return SessionIds Empty.
   * </ul>
   *
   * <p>Method under test: {@link WSUserCloseSessionsEvent#WSUserCloseSessionsEvent(List, String,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSUserCloseSessionsEvent.<init>(List, String, String)"})
  public void testNewWSUserCloseSessionsEvent_whenArrayList_thenReturnSessionIdsEmpty2() {
    // Arrange and Act
    WSUserCloseSessionsEvent actualWsUserCloseSessionsEvent =
        new WSUserCloseSessionsEvent(new ArrayList<>(), "42", "42");

    // Assert
    assertEquals("42", actualWsUserCloseSessionsEvent.getSessionId());
    assertEquals("42", actualWsUserCloseSessionsEvent.getUserId());
    assertEquals("cb_user", actualWsUserCloseSessionsEvent.getTopicId());
    assertFalse(actualWsUserCloseSessionsEvent.isForceProcessed());
    assertTrue(actualWsUserCloseSessionsEvent.getSessionIds().isEmpty());
    assertEquals(WSUserCloseSessionsEvent.ID, actualWsUserCloseSessionsEvent.getId());
  }

  /**
   * Test {@link WSUserCloseSessionsEvent#getSessionIds()}.
   *
   * <p>Method under test: {@link WSUserCloseSessionsEvent#getSessionIds()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List WSUserCloseSessionsEvent.getSessionIds()"})
  public void testGetSessionIds() {
    // Arrange
    ArrayList<String> sessionIds = new ArrayList<>();

    // Act
    List<String> actualSessionIds = new WSUserCloseSessionsEvent(sessionIds).getSessionIds();

    // Assert
    assertTrue(actualSessionIds.isEmpty());
    assertSame(sessionIds, actualSessionIds);
  }
}
