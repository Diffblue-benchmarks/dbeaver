package org.jkiss.dbeaver.tools.compare.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CompareReportLineDiffblueTest {
  /**
   * Test new {@link CompareReportLine} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link CompareReportLine}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompareReportLine.<init>()"})
  public void testNewCompareReportLine() {
    // Arrange and Act
    CompareReportLine actualCompareReportLine = new CompareReportLine();

    // Assert
    assertNull(actualCompareReportLine.properties);
    assertNull(actualCompareReportLine.structure);
    assertNull(actualCompareReportLine.nodes);
    assertEquals(0, actualCompareReportLine.depth);
    assertFalse(actualCompareReportLine.hasDifference);
  }
}
