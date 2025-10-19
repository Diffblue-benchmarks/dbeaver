package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPConnectionInformationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBPConnectionInformation#DBPConnectionInformation(String, String, String, String)}
   *   <li>{@link DBPConnectionInformation#getDriverName()}
   *   <li>{@link DBPConnectionInformation#getProductName()}
   *   <li>{@link DBPConnectionInformation#getProductVersion()}
   *   <li>{@link DBPConnectionInformation#getUrl()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPConnectionInformation.<init>(String, String, String, String)",
    "String DBPConnectionInformation.getDriverName()",
    "String DBPConnectionInformation.getProductName()",
    "String DBPConnectionInformation.getProductVersion()",
    "String DBPConnectionInformation.getUrl()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBPConnectionInformation actualDbpConnectionInformation =
        new DBPConnectionInformation(
            "https://example.org/example", "Driver Name", "Product Name", "1.0.2");
    String actualDriverName = actualDbpConnectionInformation.getDriverName();
    String actualProductName = actualDbpConnectionInformation.getProductName();
    String actualProductVersion = actualDbpConnectionInformation.getProductVersion();

    // Assert
    assertEquals("1.0.2", actualProductVersion);
    assertEquals("Driver Name", actualDriverName);
    assertEquals("Product Name", actualProductName);
    assertEquals("https://example.org/example", actualDbpConnectionInformation.getUrl());
  }
}
