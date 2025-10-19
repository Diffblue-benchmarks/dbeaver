package org.jkiss.dbeaver.tools.configuration;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ConfigurationExportDataDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link ConfigurationExportData#ConfigurationExportData(String)}
   *   <li>{@link ConfigurationExportData#getFile()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ConfigurationExportData.<init>(String)",
    "String ConfigurationExportData.getFile()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("File", new ConfigurationExportData("File").getFile());
  }
}
