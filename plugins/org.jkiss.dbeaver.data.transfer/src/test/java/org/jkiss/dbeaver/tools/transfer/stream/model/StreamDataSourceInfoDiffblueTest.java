package org.jkiss.dbeaver.tools.transfer.stream.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.osgi.framework.Version;

public class StreamDataSourceInfoDiffblueTest {
  /**
   * Test {@link StreamDataSourceInfo#getDatabaseVersion()}.
   *
   * <p>Method under test: {@link StreamDataSourceInfo#getDatabaseVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Version StreamDataSourceInfo.getDatabaseVersion()"})
  public void testGetDatabaseVersion() {
    // Arrange and Act
    Version actualDatabaseVersion = new StreamDataSourceInfo().getDatabaseVersion();

    // Assert
    assertEquals("", actualDatabaseVersion.getQualifier());
    assertEquals(0, actualDatabaseVersion.getMicro());
    assertEquals(0, actualDatabaseVersion.getMinor());
    assertEquals(1, actualDatabaseVersion.getMajor());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link StreamDataSourceInfo}
   *   <li>{@link StreamDataSourceInfo#getCatalogTerm()}
   *   <li>{@link StreamDataSourceInfo#getDatabaseProductName()}
   *   <li>{@link StreamDataSourceInfo#getDatabaseProductVersion()}
   *   <li>{@link StreamDataSourceInfo#getDriverName()}
   *   <li>{@link StreamDataSourceInfo#getDriverVersion()}
   *   <li>{@link StreamDataSourceInfo#getProcedureTerm()}
   *   <li>{@link StreamDataSourceInfo#getSchemaTerm()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void StreamDataSourceInfo.<init>()",
    "String StreamDataSourceInfo.getCatalogTerm()",
    "String StreamDataSourceInfo.getDatabaseProductName()",
    "String StreamDataSourceInfo.getDatabaseProductVersion()",
    "String StreamDataSourceInfo.getDriverName()",
    "String StreamDataSourceInfo.getDriverVersion()",
    "String StreamDataSourceInfo.getProcedureTerm()",
    "String StreamDataSourceInfo.getSchemaTerm()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    StreamDataSourceInfo actualStreamDataSourceInfo = new StreamDataSourceInfo();
    String actualCatalogTerm = actualStreamDataSourceInfo.getCatalogTerm();
    String actualDatabaseProductName = actualStreamDataSourceInfo.getDatabaseProductName();
    String actualDatabaseProductVersion = actualStreamDataSourceInfo.getDatabaseProductVersion();
    String actualDriverName = actualStreamDataSourceInfo.getDriverName();
    String actualDriverVersion = actualStreamDataSourceInfo.getDriverVersion();
    String actualProcedureTerm = actualStreamDataSourceInfo.getProcedureTerm();

    // Assert
    assertEquals("1.0", actualDatabaseProductVersion);
    assertEquals("1.0", actualDriverVersion);
    assertEquals("stream", actualDatabaseProductName);
    assertEquals("stream", actualDriverName);
    assertNull(actualCatalogTerm);
    assertNull(actualProcedureTerm);
    assertNull(actualStreamDataSourceInfo.getSchemaTerm());
  }
}
