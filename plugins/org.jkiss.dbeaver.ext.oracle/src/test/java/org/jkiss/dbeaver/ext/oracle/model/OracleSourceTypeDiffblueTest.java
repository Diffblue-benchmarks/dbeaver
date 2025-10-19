package org.jkiss.dbeaver.ext.oracle.model;

import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleSourceTypeDiffblueTest {
  /**
   * Test {@link OracleSourceType#isCustom()}.
   *
   * <p>Method under test: {@link OracleSourceType#isCustom()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OracleSourceType.isCustom()"})
  public void testIsCustom() {
    // Arrange, Act and Assert
    assertFalse(OracleSourceType.valueOf("TYPE").isCustom());
  }
}
