package org.jkiss.dbeaver.registry.driver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.app.DBPPlatform;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MissingDataSourceProviderDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link MissingDataSourceProvider}
   *   <li>{@link MissingDataSourceProvider#init(DBPPlatform)}
   *   <li>{@link MissingDataSourceProvider#getFeatures()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MissingDataSourceProvider.<init>()",
    "long MissingDataSourceProvider.getFeatures()",
    "void MissingDataSourceProvider.init(DBPPlatform)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    MissingDataSourceProvider actualMissingDataSourceProvider = new MissingDataSourceProvider();
    actualMissingDataSourceProvider.init(mock(DBPPlatform.class));

    // Assert
    assertEquals(0L, actualMissingDataSourceProvider.getFeatures());
  }
}
