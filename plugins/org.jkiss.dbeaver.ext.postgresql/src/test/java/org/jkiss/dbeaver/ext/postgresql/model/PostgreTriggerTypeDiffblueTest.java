package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PostgreTriggerTypeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PostgreTriggerType#PostgreTriggerType(String)}
   *   <li>{@link PostgreTriggerType#toString()}
   *   <li>{@link PostgreTriggerType#getName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PostgreTriggerType.<init>(String)",
    "String PostgreTriggerType.getName()",
    "String PostgreTriggerType.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    PostgreTriggerType actualPostgreTriggerType = new PostgreTriggerType("Name");
    String actualToStringResult = actualPostgreTriggerType.toString();

    // Assert
    assertEquals("Name", actualPostgreTriggerType.getName());
    assertEquals("Name", actualToStringResult);
  }
}
