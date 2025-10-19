package org.jkiss.dbeaver.registry;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourceParseResultsDiffblueTest {
  /**
   * Test new {@link DataSourceParseResults} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DataSourceParseResults}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataSourceParseResults.<init>()"})
  public void testNewDataSourceParseResults() {
    // Arrange and Act
    DataSourceParseResults actualDataSourceParseResults = new DataSourceParseResults();

    // Assert
    assertTrue(actualDataSourceParseResults.addedDataSources.isEmpty());
    assertTrue(actualDataSourceParseResults.addedFolders.isEmpty());
    assertTrue(actualDataSourceParseResults.removedDataSources.isEmpty());
    assertTrue(actualDataSourceParseResults.removedFolders.isEmpty());
    assertTrue(actualDataSourceParseResults.updatedDataSources.isEmpty());
    assertTrue(actualDataSourceParseResults.updatedFolders.isEmpty());
  }
}
