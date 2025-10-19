package org.jkiss.dbeaver.tools.compare.simple;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import org.jkiss.dbeaver.model.navigator.DBNDatabaseNode;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CompareReportRendererDiffblueTest {
  @Mock private CompareObjectsSettings compareObjectsSettings;

  @Mock private CompareReport compareReport;

  @InjectMocks private CompareReportRenderer compareReportRenderer;

  /**
   * Test {@link CompareReportRenderer#renderReport(DBRProgressMonitor, CompareReport,
   * CompareObjectsSettings, OutputStream)}.
   *
   * <ul>
   *   <li>Given {@link CompareReportRenderer} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link CompareReportRenderer#renderReport(DBRProgressMonitor,
   * CompareReport, CompareObjectsSettings, OutputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CompareReportRenderer.renderReport(DBRProgressMonitor, CompareReport, CompareObjectsSettings, OutputStream)"
  })
  public void testRenderReport_givenCompareReportRenderer() throws IOException {
    // Arrange
    CompareReportRenderer compareReportRenderer = new CompareReportRenderer();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ArrayList<DBNDatabaseNode> nodes = new ArrayList<>();
    CompareReport report = new CompareReport(nodes, new ArrayList<>());
    CompareObjectsSettings settings = new CompareObjectsSettings(new ArrayList<>());
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    // Act
    compareReportRenderer.renderReport(monitor, report, settings, outputStream);

    // Assert
    assertEquals(1071, outputStream.toByteArray().length);
  }

  /**
   * Test {@link CompareReportRenderer#renderReport(DBRProgressMonitor, CompareReport,
   * CompareObjectsSettings, OutputStream)}.
   *
   * <ul>
   *   <li>Then calls {@link CompareObjectsSettings#isShowOnlyDifferences()}.
   * </ul>
   *
   * <p>Method under test: {@link CompareReportRenderer#renderReport(DBRProgressMonitor,
   * CompareReport, CompareObjectsSettings, OutputStream)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CompareReportRenderer.renderReport(DBRProgressMonitor, CompareReport, CompareObjectsSettings, OutputStream)"
  })
  public void testRenderReport_thenCallsIsShowOnlyDifferences() throws IOException {
    // Arrange
    when(compareReport.getNodes()).thenReturn(new ArrayList<>());
    when(compareReport.getReportLines()).thenReturn(new ArrayList<>());
    when(compareObjectsSettings.isShowOnlyDifferences()).thenReturn(true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    // Act
    compareReportRenderer.renderReport(
        monitor, compareReport, compareObjectsSettings, outputStream);

    // Assert
    verify(compareObjectsSettings, atLeast(1)).isShowOnlyDifferences();
    verify(compareReport, atLeast(1)).getNodes();
    verify(compareReport, atLeast(1)).getReportLines();
    assertEquals(1071, outputStream.toByteArray().length);
  }
}
