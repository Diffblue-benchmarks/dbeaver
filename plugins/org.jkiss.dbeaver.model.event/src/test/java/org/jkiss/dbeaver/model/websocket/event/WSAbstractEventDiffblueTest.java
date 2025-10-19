package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSAbstractEventDiffblueTest {
  /**
   * Test {@link WSAbstractEvent#getId()}.
   *
   * <p>Method under test: {@link WSAbstractEvent#getId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String WSAbstractEvent.getId()"})
  public void testGetId() {
    // Arrange, Act and Assert
    assertEquals("cb_config_changed", new WSServerConfigurationChangedEvent().getId());
  }

  /**
   * Test {@link WSAbstractEvent#getSessionId()}.
   *
   * <p>Method under test: {@link WSAbstractEvent#getSessionId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String WSAbstractEvent.getSessionId()"})
  public void testGetSessionId() {
    // Arrange, Act and Assert
    assertNull(new WSServerConfigurationChangedEvent().getSessionId());
  }

  /**
   * Test {@link WSAbstractEvent#getTopicId()}.
   *
   * <p>Method under test: {@link WSAbstractEvent#getTopicId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String WSAbstractEvent.getTopicId()"})
  public void testGetTopicId() {
    // Arrange, Act and Assert
    assertEquals("cb_config", new WSServerConfigurationChangedEvent().getTopicId());
  }

  /**
   * Test {@link WSAbstractEvent#getUserId()}.
   *
   * <p>Method under test: {@link WSAbstractEvent#getUserId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String WSAbstractEvent.getUserId()"})
  public void testGetUserId() {
    // Arrange, Act and Assert
    assertNull(new WSServerConfigurationChangedEvent().getUserId());
  }
}
