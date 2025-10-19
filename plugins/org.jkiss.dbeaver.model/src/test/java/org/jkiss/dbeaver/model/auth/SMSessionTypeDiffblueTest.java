package org.jkiss.dbeaver.model.auth;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMSessionTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMSessionType#SMSessionType(String)}
   *   <li>{@link SMSessionType#getSessionType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMSessionType.<init>(String)", "String SMSessionType.getSessionType()"})
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("Session Type", new SMSessionType("Session Type").getSessionType());
  }
}
