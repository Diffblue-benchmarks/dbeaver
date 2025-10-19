package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSOutputLogInfoDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link WSOutputLogInfo#WSOutputLogInfo(String, String)}
   *   <li>{@link WSOutputLogInfo#getMessage()}
   *   <li>{@link WSOutputLogInfo#getSeverity()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WSOutputLogInfo.<init>(String, String)",
    "String WSOutputLogInfo.getMessage()",
    "String WSOutputLogInfo.getSeverity()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    WSOutputLogInfo actualWsOutputLogInfo =
        new WSOutputLogInfo("S1", "Not all who wander are lost");
    String actualMessage = actualWsOutputLogInfo.getMessage();

    // Assert
    assertEquals("Not all who wander are lost", actualMessage);
    assertEquals("S1", actualWsOutputLogInfo.getSeverity());
  }
}
