package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AIDatabaseScopeDiffblueTest {
  /**
   * Test {@link AIDatabaseScope#getTitle()}.
   *
   * <p>Method under test: {@link AIDatabaseScope#getTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AIDatabaseScope.getTitle()"})
  public void testGetTitle() {
    // Arrange, Act and Assert
    assertEquals("Current Schema", AIDatabaseScope.valueOf("CURRENT_SCHEMA").getTitle());
  }
}
