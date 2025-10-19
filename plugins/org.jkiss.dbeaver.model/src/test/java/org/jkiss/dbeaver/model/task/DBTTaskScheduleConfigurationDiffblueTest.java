package org.jkiss.dbeaver.model.task;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBTTaskScheduleConfigurationDiffblueTest {
  /**
   * Test new {@link DBTTaskScheduleConfiguration} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * DBTTaskScheduleConfiguration}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBTTaskScheduleConfiguration.<init>()"})
  public void testNewDBTTaskScheduleConfiguration() {
    // Arrange, Act and Assert
    assertTrue(new DBTTaskScheduleConfiguration().properties.isEmpty());
  }
}
