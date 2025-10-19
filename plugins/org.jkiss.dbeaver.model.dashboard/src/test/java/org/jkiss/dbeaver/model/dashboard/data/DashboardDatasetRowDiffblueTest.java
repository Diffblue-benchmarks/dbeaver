package org.jkiss.dbeaver.model.dashboard.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Date;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DashboardDatasetRowDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DashboardDatasetRow#DashboardDatasetRow(Date, Object[])}
   *   <li>{@link DashboardDatasetRow#getTimestamp()}
   *   <li>{@link DashboardDatasetRow#getValues()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DashboardDatasetRow.<init>(Date, Object[])",
    "Date DashboardDatasetRow.getTimestamp()",
    "Object[] DashboardDatasetRow.getValues()"
  })
  public void testGettersAndSetters() {
    // Arrange
    Date timestamp =
        Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
    Object[] values = new Object[] {"Values"};

    // Act
    DashboardDatasetRow actualDashboardDatasetRow = new DashboardDatasetRow(timestamp, values);
    Date actualTimestamp = actualDashboardDatasetRow.getTimestamp();
    Object[] actualValues = actualDashboardDatasetRow.getValues();

    // Assert
    assertEquals("Values", actualValues[0]);
    assertEquals(1, actualValues.length);
    assertSame(timestamp, actualTimestamp);
    assertSame(values, actualValues);
  }
}
