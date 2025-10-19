package org.jkiss.dbeaver.model.ai;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AISqlJoinRuleDiffblueTest {
  /**
   * Test {@link AISqlJoinRule#getTitle()}.
   *
   * <p>Method under test: {@link AISqlJoinRule#getTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AISqlJoinRule.getTitle()"})
  public void testGetTitle() {
    // Arrange, Act and Assert
    assertEquals("Default", AISqlJoinRule.valueOf("DEFAULT").getTitle());
  }
}
