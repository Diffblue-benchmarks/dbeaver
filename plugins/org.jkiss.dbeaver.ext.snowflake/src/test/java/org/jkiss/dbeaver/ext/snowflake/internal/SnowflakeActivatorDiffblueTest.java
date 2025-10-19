package org.jkiss.dbeaver.ext.snowflake.internal;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SnowflakeActivatorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SnowflakeActivator}
   *   <li>{@link SnowflakeActivator#getDefault()}
   *   <li>{@link SnowflakeActivator#getPreferences()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SnowflakeActivator.<init>()",
    "SnowflakeActivator SnowflakeActivator.getDefault()",
    "org.jkiss.dbeaver.model.preferences.DBPPreferenceStore SnowflakeActivator.getPreferences()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SnowflakeActivator actualSnowflakeActivator = new SnowflakeActivator();
    SnowflakeActivator actualDefault = actualSnowflakeActivator.getDefault();

    // Assert
    assertNull(actualDefault);
    assertNull(actualSnowflakeActivator.getPreferences());
  }
}
