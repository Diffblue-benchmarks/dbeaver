package org.jkiss.dbeaver.ui.views.qm;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QueryManagerFilterHandlerDiffblueTest {
  /**
   * Test new {@link QueryManagerFilterHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link QueryManagerFilterHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QueryManagerFilterHandler.<init>()"})
  public void testNewQueryManagerFilterHandler() {
    // Arrange, Act and Assert
    assertTrue(new QueryManagerFilterHandler().isEnabled());
  }
}
