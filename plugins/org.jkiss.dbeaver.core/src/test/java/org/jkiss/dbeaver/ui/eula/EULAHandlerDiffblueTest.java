package org.jkiss.dbeaver.ui.eula;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EULAHandlerDiffblueTest {
  /**
   * Test new {@link EULAHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link EULAHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EULAHandler.<init>()"})
  public void testNewEULAHandler() {
    // Arrange, Act and Assert
    assertTrue(new EULAHandler().isEnabled());
  }
}
