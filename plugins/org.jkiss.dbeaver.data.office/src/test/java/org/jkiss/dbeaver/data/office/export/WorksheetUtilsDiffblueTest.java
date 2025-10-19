package org.jkiss.dbeaver.data.office.export;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class WorksheetUtilsDiffblueTest {
  /**
   * Test {@link WorksheetUtils#makeUniqueSheetName(Workbook, String)}.
   *
   * <p>Method under test: {@link WorksheetUtils#makeUniqueSheetName(Workbook, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WorksheetUtils.makeUniqueSheetName(Workbook, String)"})
  public void testMakeUniqueSheetName() {
    // Arrange, Act and Assert
    assertEquals(
        "____________", WorksheetUtils.makeUniqueSheetName(new HSSFWorkbook(), "[\\\\/*\\[\\]:?]"));
  }

  /**
   * Test {@link WorksheetUtils#makeUniqueSheetName(Workbook, String)}.
   *
   * <ul>
   *   <li>When {@code '}.
   *   <li>Then return {@link WorksheetUtils#DEFAULT_SHEET_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link WorksheetUtils#makeUniqueSheetName(Workbook, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WorksheetUtils.makeUniqueSheetName(Workbook, String)"})
  public void testMakeUniqueSheetName_whenApostrophe_thenReturnDefault_sheet_name() {
    // Arrange, Act and Assert
    assertEquals(
        WorksheetUtils.DEFAULT_SHEET_NAME,
        WorksheetUtils.makeUniqueSheetName(new HSSFWorkbook(), "'"));
  }

  /**
   * Test {@link WorksheetUtils#makeUniqueSheetName(Workbook, String)}.
   *
   * <ul>
   *   <li>When {@code *}.
   *   <li>Then return {@code _}.
   * </ul>
   *
   * <p>Method under test: {@link WorksheetUtils#makeUniqueSheetName(Workbook, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WorksheetUtils.makeUniqueSheetName(Workbook, String)"})
  public void testMakeUniqueSheetName_whenAsterisk_thenReturnUnderscore() {
    // Arrange, Act and Assert
    assertEquals("_", WorksheetUtils.makeUniqueSheetName(new HSSFWorkbook(), "*"));
  }

  /**
   * Test {@link WorksheetUtils#makeUniqueSheetName(Workbook, String)}.
   *
   * <ul>
   *   <li>When {@code History}.
   *   <li>Then return {@link WorksheetUtils#DEFAULT_SHEET_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link WorksheetUtils#makeUniqueSheetName(Workbook, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WorksheetUtils.makeUniqueSheetName(Workbook, String)"})
  public void testMakeUniqueSheetName_whenHistory_thenReturnDefault_sheet_name() {
    // Arrange, Act and Assert
    assertEquals(
        WorksheetUtils.DEFAULT_SHEET_NAME,
        WorksheetUtils.makeUniqueSheetName(new HSSFWorkbook(), "History"));
  }

  /**
   * Test {@link WorksheetUtils#makeUniqueSheetName(Workbook, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link WorksheetUtils#DEFAULT_SHEET_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link WorksheetUtils#makeUniqueSheetName(Workbook, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WorksheetUtils.makeUniqueSheetName(Workbook, String)"})
  public void testMakeUniqueSheetName_whenNull_thenReturnDefault_sheet_name() {
    // Arrange, Act and Assert
    assertEquals(
        WorksheetUtils.DEFAULT_SHEET_NAME,
        WorksheetUtils.makeUniqueSheetName(new HSSFWorkbook(), null));
  }

  /**
   * Test {@link WorksheetUtils#makeUniqueSheetName(Workbook, String)}.
   *
   * <ul>
   *   <li>When {@code Sheet Name}.
   *   <li>Then return {@code Sheet Name}.
   * </ul>
   *
   * <p>Method under test: {@link WorksheetUtils#makeUniqueSheetName(Workbook, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WorksheetUtils.makeUniqueSheetName(Workbook, String)"})
  public void testMakeUniqueSheetName_whenSheetName_thenReturnSheetName() {
    // Arrange, Act and Assert
    assertEquals(
        "Sheet Name", WorksheetUtils.makeUniqueSheetName(new HSSFWorkbook(), "Sheet Name"));
  }
}
