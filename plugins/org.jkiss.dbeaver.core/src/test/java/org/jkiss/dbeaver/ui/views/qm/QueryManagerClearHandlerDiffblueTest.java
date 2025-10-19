package org.jkiss.dbeaver.ui.views.qm;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QueryManagerClearHandlerDiffblueTest {
  /**
   * Test new {@link QueryManagerClearHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link QueryManagerClearHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QueryManagerClearHandler.<init>()"})
  public void testNewQueryManagerClearHandler() {
    // Arrange, Act and Assert
    assertTrue(new QueryManagerClearHandler().isEnabled());
  }
}
