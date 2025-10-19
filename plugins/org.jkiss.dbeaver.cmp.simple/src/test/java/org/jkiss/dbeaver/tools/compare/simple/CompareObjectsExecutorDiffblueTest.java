package org.jkiss.dbeaver.tools.compare.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.navigator.DBNDatabaseFolder;
import org.jkiss.dbeaver.model.navigator.DBNDatabaseNode;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CompareObjectsExecutorDiffblueTest {
  /**
   * Test {@link CompareObjectsExecutor#CompareObjectsExecutor(CompareObjectsSettings)}.
   *
   * <p>Method under test: {@link
   * CompareObjectsExecutor#CompareObjectsExecutor(CompareObjectsSettings)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CompareObjectsExecutor.<init>(CompareObjectsSettings)"})
  public void testNewCompareObjectsExecutor() {
    // Arrange, Act and Assert
    assertNull(
        new CompareObjectsExecutor(new CompareObjectsSettings(new ArrayList<>()))
            .getInitializeError());
  }

  /**
   * Test {@link CompareObjectsExecutor#getInitializeError()}.
   *
   * <p>Method under test: {@link CompareObjectsExecutor#getInitializeError()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.eclipse.core.runtime.IStatus CompareObjectsExecutor.getInitializeError()"
  })
  public void testGetInitializeError() {
    // Arrange, Act and Assert
    assertNull(
        new CompareObjectsExecutor(new CompareObjectsSettings(new ArrayList<>()))
            .getInitializeError());
  }

  /**
   * Test {@link CompareObjectsExecutor#compareObjects(DBRProgressMonitor, List)}.
   *
   * <ul>
   *   <li>Then return ReportLines size is one.
   * </ul>
   *
   * <p>Method under test: {@link CompareObjectsExecutor#compareObjects(DBRProgressMonitor, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "CompareReport CompareObjectsExecutor.compareObjects(DBRProgressMonitor, List)"
  })
  public void testCompareObjects_thenReturnReportLinesSizeIsOne()
      throws InterruptedException, DBException {
    // Arrange
    CompareObjectsExecutor compareObjectsExecutor =
        new CompareObjectsExecutor(new CompareObjectsSettings(new ArrayList<>()));
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ArrayList<DBNDatabaseNode> nodes = new ArrayList<>();
    nodes.add(mock(DBNDatabaseFolder.class));

    // Act
    CompareReport actualCompareObjectsResult =
        compareObjectsExecutor.compareObjects(monitor, nodes);

    // Assert
    List<CompareReportLine> reportLines = actualCompareObjectsResult.getReportLines();
    assertEquals(1, reportLines.size());
    CompareReportLine getResult = reportLines.get(0);
    assertNull(getResult.properties);
    assertEquals(0, getResult.nodes.length);
    assertEquals(1, getResult.depth);
    assertFalse(getResult.hasDifference);
    assertTrue(actualCompareObjectsResult.getNodes().isEmpty());
  }
}
