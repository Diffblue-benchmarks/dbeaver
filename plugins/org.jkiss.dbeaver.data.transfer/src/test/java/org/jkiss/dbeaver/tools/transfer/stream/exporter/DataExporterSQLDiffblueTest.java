package org.jkiss.dbeaver.tools.transfer.stream.exporter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.tools.transfer.stream.exporter.DataExporterSQL.InsertKeyword;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataExporterSQLDiffblueTest {
  /**
   * Test InsertKeyword {@link InsertKeyword#fromValue(String)}.
   *
   * <ul>
   *   <li>When {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link InsertKeyword#fromValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InsertKeyword InsertKeyword.fromValue(String)"})
  public void testInsertKeywordFromValue_whenFoo() {
    // Arrange, Act and Assert
    assertEquals(InsertKeyword.INSERT, InsertKeyword.fromValue("foo"));
  }

  /**
   * Test InsertKeyword {@link InsertKeyword#fromValue(String)}.
   *
   * <ul>
   *   <li>When {@code INSERT}.
   * </ul>
   *
   * <p>Method under test: {@link InsertKeyword#fromValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"InsertKeyword InsertKeyword.fromValue(String)"})
  public void testInsertKeywordFromValue_whenInsert() {
    // Arrange, Act and Assert
    assertEquals(InsertKeyword.INSERT, InsertKeyword.fromValue("INSERT"));
  }

  /**
   * Test InsertKeyword {@link InsertKeyword#value()}.
   *
   * <p>Method under test: {@link InsertKeyword#value()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String InsertKeyword.value()"})
  public void testInsertKeywordValue() {
    // Arrange, Act and Assert
    assertEquals("INSERT", InsertKeyword.valueOf("INSERT").value());
  }

  /**
   * Test {@link DataExporterSQL#shouldTruncateOutputFileBeforeExport()}.
   *
   * <p>Method under test: {@link DataExporterSQL#shouldTruncateOutputFileBeforeExport()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataExporterSQL.shouldTruncateOutputFileBeforeExport()"})
  public void testShouldTruncateOutputFileBeforeExport() {
    // Arrange, Act and Assert
    assertFalse(new DataExporterSQL().shouldTruncateOutputFileBeforeExport());
  }

  /**
   * Test new {@link DataExporterSQL} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DataExporterSQL}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataExporterSQL.<init>()"})
  public void testNewDataExporterSQL() {
    // Arrange, Act and Assert
    assertNull(new DataExporterSQL().getSite());
  }
}
