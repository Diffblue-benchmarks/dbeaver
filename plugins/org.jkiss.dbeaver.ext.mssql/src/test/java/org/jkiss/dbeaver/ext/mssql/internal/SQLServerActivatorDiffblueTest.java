package org.jkiss.dbeaver.ext.mssql.internal;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerActivatorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SQLServerActivator}
   *   <li>{@link SQLServerActivator#getDefault()}
   *   <li>{@link SQLServerActivator#getPreferences()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLServerActivator.<init>()",
    "SQLServerActivator SQLServerActivator.getDefault()",
    "org.jkiss.dbeaver.model.preferences.DBPPreferenceStore SQLServerActivator.getPreferences()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SQLServerActivator actualSqlServerActivator = new SQLServerActivator();
    SQLServerActivator actualDefault = actualSqlServerActivator.getDefault();

    // Assert
    assertNull(actualDefault);
    assertNull(actualSqlServerActivator.getPreferences());
  }
}
