package org.jkiss.dbeaver.ext.oracle.internal;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleActivatorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link OracleActivator}
   *   <li>{@link OracleActivator#getDefault()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OracleActivator.<init>()",
    "OracleActivator OracleActivator.getDefault()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new OracleActivator().getDefault());
  }
}
