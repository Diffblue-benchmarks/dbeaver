package org.jkiss.dbeaver.ui.actions.datasource;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourceMigrateHandlerDiffblueTest {
  /**
   * Test new {@link DataSourceMigrateHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DataSourceMigrateHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataSourceMigrateHandler.<init>()"})
  public void testNewDataSourceMigrateHandler() {
    // Arrange, Act and Assert
    assertTrue(new DataSourceMigrateHandler().isEnabled());
  }
}
