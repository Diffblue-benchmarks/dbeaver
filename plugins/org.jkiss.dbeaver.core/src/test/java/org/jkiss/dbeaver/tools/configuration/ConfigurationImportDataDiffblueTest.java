package org.jkiss.dbeaver.tools.configuration;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ConfigurationImportDataDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ConfigurationImportData#ConfigurationImportData(String)}
   *   <li>{@link ConfigurationImportData#getFilePath()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConfigurationImportData.<init>(String)",
    "String ConfigurationImportData.getFilePath()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals(
        "/directory/foo.txt", new ConfigurationImportData("/directory/foo.txt").getFilePath());
  }
}
