package org.jkiss.dbeaver.model.dashboard.data;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DashboardDatasetDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DashboardDataset#DashboardDataset(String[])}
   *   <li>{@link DashboardDataset#getColumnNames()}
   *   <li>{@link DashboardDataset#getRows()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DashboardDataset.<init>(String[])",
    "String[] DashboardDataset.getColumnNames()",
    "List DashboardDataset.getRows()"
  })
  public void testGettersAndSetters() {
    // Arrange
    String[] columnNames = new String[] {"Column Names"};

    // Act
    DashboardDataset actualDashboardDataset = new DashboardDataset(columnNames);
    String[] actualColumnNames = actualDashboardDataset.getColumnNames();

    // Assert
    assertTrue(actualDashboardDataset.getRows().isEmpty());
    assertSame(columnNames, actualColumnNames);
    assertArrayEquals(new String[] {"Column Names"}, actualColumnNames);
  }

  /**
   * Test {@link DashboardDataset#addRow(DashboardDatasetRow)}.
   *
   * <p>Method under test: {@link DashboardDataset#addRow(DashboardDatasetRow)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardDataset.addRow(DashboardDatasetRow)"})
  public void testAddRow() {
    // Arrange
    String[] columnNames = new String[] {"Column Names"};
    DashboardDataset dashboardDataset = new DashboardDataset(columnNames);
    Date timestamp =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Object[] values = new Object[] {"Values"};

    DashboardDatasetRow row = new DashboardDatasetRow(timestamp, values);

    // Act
    dashboardDataset.addRow(row);

    // Assert
    List<DashboardDatasetRow> rows = dashboardDataset.getRows();
    assertEquals(1, rows.size());
    assertSame(row, rows.get(0));
  }
}
