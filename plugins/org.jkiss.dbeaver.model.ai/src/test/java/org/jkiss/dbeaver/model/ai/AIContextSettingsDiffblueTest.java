package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.ai.AIContextSettings.PersistentSettings;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIContextSettingsDiffblueTest {
  /**
   * Test PersistentSettings new {@link PersistentSettings} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link PersistentSettings}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PersistentSettings.<init>()"})
  public void testPersistentSettingsNewPersistentSettings() {
    // Arrange and Act
    PersistentSettings actualPersistentSettings = new PersistentSettings();

    // Assert
    assertNull(actualPersistentSettings.objects);
    assertNull(actualPersistentSettings.scope);
    assertFalse(actualPersistentSettings.confirmed);
  }
}
