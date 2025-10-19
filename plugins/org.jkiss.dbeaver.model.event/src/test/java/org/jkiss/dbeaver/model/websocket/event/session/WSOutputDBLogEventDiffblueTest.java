package org.jkiss.dbeaver.model.websocket.event.session;

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
import org.jkiss.dbeaver.model.websocket.event.WSOutputLogInfo;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSOutputDBLogEventDiffblueTest {
  /**
   * Test {@link WSOutputDBLogEvent#WSOutputDBLogEvent(String, List, long)}.
   *
   * <ul>
   *   <li>Then return Messages is {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link WSOutputDBLogEvent#WSOutputDBLogEvent(String, List, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSOutputDBLogEvent.<init>(String, List, long)"})
  public void testNewWSOutputDBLogEvent_thenReturnMessagesIsArrayList() {
    // Arrange
    ArrayList<WSOutputLogInfo> messages = new ArrayList<>();
    messages.add(new WSOutputLogInfo("S1", "Not all who wander are lost"));

    // Act
    WSOutputDBLogEvent actualWsOutputDBLogEvent = new WSOutputDBLogEvent("42", messages, 1L);

    // Assert
    assertSame(messages, actualWsOutputDBLogEvent.getMessages());
  }

  /**
   * Test {@link WSOutputDBLogEvent#WSOutputDBLogEvent(String, List, long)}.
   *
   * <ul>
   *   <li>Then return Messages size is two.
   * </ul>
   *
   * <p>Method under test: {@link WSOutputDBLogEvent#WSOutputDBLogEvent(String, List, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSOutputDBLogEvent.<init>(String, List, long)"})
  public void testNewWSOutputDBLogEvent_thenReturnMessagesSizeIsTwo() {
    // Arrange
    ArrayList<WSOutputLogInfo> messages = new ArrayList<>();
    messages.add(new WSOutputLogInfo("S1", "Not all who wander are lost"));
    WSOutputLogInfo wsOutputLogInfo = new WSOutputLogInfo("S1", "Not all who wander are lost");
    messages.add(wsOutputLogInfo);

    // Act
    WSOutputDBLogEvent actualWsOutputDBLogEvent = new WSOutputDBLogEvent("42", messages, 1L);

    // Assert
    List<WSOutputLogInfo> messages2 = actualWsOutputDBLogEvent.getMessages();
    assertEquals(2, messages2.size());
    assertSame(wsOutputLogInfo, messages2.get(1));
  }

  /**
   * Test {@link WSOutputDBLogEvent#WSOutputDBLogEvent(String, List, long)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return ContextId is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link WSOutputDBLogEvent#WSOutputDBLogEvent(String, List, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSOutputDBLogEvent.<init>(String, List, long)"})
  public void testNewWSOutputDBLogEvent_whenArrayList_thenReturnContextIdIs42() {
    // Arrange and Act
    WSOutputDBLogEvent actualWsOutputDBLogEvent =
        new WSOutputDBLogEvent("42", new ArrayList<>(), 1L);

    // Assert
    assertEquals("42", actualWsOutputDBLogEvent.getContextId());
    assertEquals("cb_database_output_log", actualWsOutputDBLogEvent.getTopicId());
    assertEquals("cb_database_output_log_updated", actualWsOutputDBLogEvent.getId());
    assertNull(actualWsOutputDBLogEvent.getSessionId());
    assertNull(actualWsOutputDBLogEvent.getUserId());
    assertEquals(1L, actualWsOutputDBLogEvent.getEventTimestamp());
    assertFalse(actualWsOutputDBLogEvent.isForceProcessed());
    assertTrue(actualWsOutputDBLogEvent.getMessages().isEmpty());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSOutputDBLogEvent#getContextId()}
   *   <li>{@link WSOutputDBLogEvent#getEventTimestamp()}
   *   <li>{@link WSOutputDBLogEvent#getMessages()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String WSOutputDBLogEvent.getContextId()",
    "long WSOutputDBLogEvent.getEventTimestamp()",
    "List WSOutputDBLogEvent.getMessages()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<WSOutputLogInfo> messages = new ArrayList<>();
    WSOutputDBLogEvent wsOutputDBLogEvent = new WSOutputDBLogEvent("42", messages, 1L);

    // Act
    String actualContextId = wsOutputDBLogEvent.getContextId();
    long actualEventTimestamp = wsOutputDBLogEvent.getEventTimestamp();
    List<WSOutputLogInfo> actualMessages = wsOutputDBLogEvent.getMessages();

    // Assert
    assertEquals("42", actualContextId);
    assertEquals(1L, actualEventTimestamp);
    assertTrue(actualMessages.isEmpty());
    assertSame(messages, actualMessages);
  }
}
