package org.jkiss.dbeaver.model.sql.internal;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLModelActivatorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SQLModelActivator}
   *   <li>{@link SQLModelActivator#getInstance()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLModelActivator.<init>()",
    "SQLModelActivator SQLModelActivator.getInstance()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new SQLModelActivator().getInstance());
  }
}
