package org.jkiss.dbeaver.ext.cubrid.internal;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CubridActivatorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link CubridActivator}
   *   <li>{@link CubridActivator#getDefault()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CubridActivator.<init>()",
    "CubridActivator CubridActivator.getDefault()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new CubridActivator().getDefault());
  }
}
