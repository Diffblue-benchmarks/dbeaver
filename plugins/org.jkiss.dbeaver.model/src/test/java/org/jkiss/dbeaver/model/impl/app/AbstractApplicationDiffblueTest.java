package org.jkiss.dbeaver.model.impl.app;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AbstractApplicationDiffblueTest {
  /**
   * Test {@link AbstractApplication#getInstance()}.
   *
   * <p>Method under test: {@link AbstractApplication#getInstance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.app.DBPApplication AbstractApplication.getInstance()"
  })
  public void testGetInstance() {
    // Arrange, Act and Assert
    assertThrows(IllegalStateException.class, () -> AbstractApplication.getInstance());
  }
}
