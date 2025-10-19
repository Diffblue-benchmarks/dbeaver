package org.jkiss.dbeaver.model.websocket.event;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WSEventDiffblueTest {
  /**
   * Test {@link WSEvent#isForceProcessed()}.
   *
   * <p>Method under test: {@link WSEvent#isForceProcessed()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean WSEvent.isForceProcessed()"})
  public void testIsForceProcessed() {
    // Arrange, Act and Assert
    assertFalse(new WSServerConfigurationChangedEvent().isForceProcessed());
  }
}
