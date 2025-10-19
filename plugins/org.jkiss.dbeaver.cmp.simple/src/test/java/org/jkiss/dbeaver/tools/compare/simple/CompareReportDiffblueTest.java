package org.jkiss.dbeaver.tools.compare.simple;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.navigator.DBNDatabaseNode;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CompareReportDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CompareReport#CompareReport(List, List)}
   *   <li>{@link CompareReport#getNodes()}
   *   <li>{@link CompareReport#getReportLines()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CompareReport.<init>(List, List)",
    "List CompareReport.getNodes()",
    "List CompareReport.getReportLines()"
  })
  public void testGettersAndSetters() {
    // Arrange
    ArrayList<DBNDatabaseNode> nodes = new ArrayList<>();
    ArrayList<CompareReportLine> reportLines = new ArrayList<>();

    // Act
    CompareReport actualCompareReport = new CompareReport(nodes, reportLines);
    List<DBNDatabaseNode> actualNodes = actualCompareReport.getNodes();
    List<CompareReportLine> actualReportLines = actualCompareReport.getReportLines();

    // Assert
    assertTrue(actualNodes.isEmpty());
    assertTrue(actualReportLines.isEmpty());
    assertSame(nodes, actualNodes);
    assertSame(reportLines, actualReportLines);
  }
}
