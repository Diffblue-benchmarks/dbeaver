package org.jkiss.dbeaver.ui.actions.datasource;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourceCreateFromUrlHandlerDiffblueTest {
  /**
   * Test new {@link DataSourceCreateFromUrlHandler} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * DataSourceCreateFromUrlHandler}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataSourceCreateFromUrlHandler.<init>()"})
  public void testNewDataSourceCreateFromUrlHandler() {
    // Arrange, Act and Assert
    assertTrue(new DataSourceCreateFromUrlHandler().isEnabled());
  }
}
