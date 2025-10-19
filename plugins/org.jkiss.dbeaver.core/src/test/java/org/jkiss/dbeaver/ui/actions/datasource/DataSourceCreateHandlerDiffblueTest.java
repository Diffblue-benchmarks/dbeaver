package org.jkiss.dbeaver.ui.actions.datasource;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourceCreateHandlerDiffblueTest {
  /**
   * Test new {@link DataSourceCreateHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DataSourceCreateHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataSourceCreateHandler.<init>()"})
  public void testNewDataSourceCreateHandler() {
    // Arrange, Act and Assert
    assertTrue(new DataSourceCreateHandler().isEnabled());
  }
}
