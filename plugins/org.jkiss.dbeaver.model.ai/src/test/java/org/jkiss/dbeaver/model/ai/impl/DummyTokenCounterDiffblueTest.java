package org.jkiss.dbeaver.model.ai.impl;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DummyTokenCounterDiffblueTest {
  /**
   * Test {@link DummyTokenCounter#count(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DummyTokenCounter#count(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DummyTokenCounter.count(String)"})
  public void testCount_whenEmptyString_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, new DummyTokenCounter().count(""));
  }

  /**
   * Test {@link DummyTokenCounter#count(String)}.
   *
   * <ul>
   *   <li>When {@code Not all who wander are lost}.
   *   <li>Then return thirteen.
   * </ul>
   *
   * <p>Method under test: {@link DummyTokenCounter#count(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DummyTokenCounter.count(String)"})
  public void testCount_whenNotAllWhoWanderAreLost_thenReturnThirteen() {
    // Arrange, Act and Assert
    assertEquals(13, new DummyTokenCounter().count("Not all who wander are lost"));
  }
}
