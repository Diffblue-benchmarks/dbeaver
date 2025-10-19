package org.jkiss.dbeaver.model.task;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBTSchedulerExternalSettingsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBTSchedulerExternalSettings}
   *   <li>{@link DBTSchedulerExternalSettings#setPlaintextDetails(String)}
   *   <li>{@link DBTSchedulerExternalSettings#getPlaintextDetails()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBTSchedulerExternalSettings.<init>()",
    "String DBTSchedulerExternalSettings.getPlaintextDetails()",
    "void DBTSchedulerExternalSettings.setPlaintextDetails(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBTSchedulerExternalSettings actualDbtSchedulerExternalSettings =
        new DBTSchedulerExternalSettings();
    actualDbtSchedulerExternalSettings.setPlaintextDetails("Plaintext Details");

    // Assert
    assertEquals("Plaintext Details", actualDbtSchedulerExternalSettings.getPlaintextDetails());
  }
}
