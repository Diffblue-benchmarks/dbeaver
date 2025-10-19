package org.jkiss.dbeaver.ext.firebird.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.regex.Matcher;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FireBirdPlanTokenDiffblueTest {
  /**
   * Test {@link FireBirdPlanToken#newMatcher(String)}.
   *
   * <p>Method under test: {@link FireBirdPlanToken#newMatcher(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Matcher FireBirdPlanToken.newMatcher(String)"})
  public void testNewMatcher() {
    // Arrange and Act
    Matcher actualNewMatcherResult = FireBirdPlanToken.PLAN.newMatcher("Text");

    // Assert
    assertEquals("\\GPLAN\\b", actualNewMatcherResult.pattern().pattern());
    assertFalse(actualNewMatcherResult.hasTransparentBounds());
    assertFalse(actualNewMatcherResult.hitEnd());
    assertFalse(actualNewMatcherResult.requireEnd());
    assertTrue(actualNewMatcherResult.hasAnchoringBounds());
  }
}
