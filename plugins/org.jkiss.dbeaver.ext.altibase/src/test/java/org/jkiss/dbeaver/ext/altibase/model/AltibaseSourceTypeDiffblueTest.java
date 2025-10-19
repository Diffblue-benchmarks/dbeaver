package org.jkiss.dbeaver.ext.altibase.model;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AltibaseSourceTypeDiffblueTest {
  /**
   * Test {@link AltibaseSourceType#isCustom()}.
   *
   * <p>Method under test: {@link AltibaseSourceType#isCustom()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AltibaseSourceType.isCustom()"})
  public void testIsCustom() {
    // Arrange, Act and Assert
    assertFalse(AltibaseSourceType.valueOf("TYPE").isCustom());
  }
}
