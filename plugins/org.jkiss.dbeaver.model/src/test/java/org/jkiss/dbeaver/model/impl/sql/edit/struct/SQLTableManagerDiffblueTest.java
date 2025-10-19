package org.jkiss.dbeaver.model.impl.sql.edit.struct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.edit.DBEObjectManager;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLTableManagerDiffblueTest {
  /**
   * Test {@link SQLTableManager#isCompact(Map)}.
   *
   * <p>Method under test: {@link SQLTableManager#isCompact(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLTableManager.isCompact(Map)"})
  public void testIsCompact() {
    // Arrange, Act and Assert
    assertFalse(SQLTableManager.isCompact(new HashMap<>()));
  }

  /**
   * Test {@link SQLTableManager#getDelimiter(Map)}.
   *
   * <p>Method under test: {@link SQLTableManager#getDelimiter(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLTableManager.getDelimiter(Map)"})
  public void testGetDelimiter() {
    // Arrange, Act and Assert
    assertEquals("\n", SQLTableManager.getDelimiter(new HashMap<>()));
  }

  /**
   * Test {@link SQLTableManager#findCommentPos(CharSequence, String)} with {@code cs}, {@code
   * slComment}.
   *
   * <p>Method under test: {@link SQLTableManager#findCommentPos(CharSequence, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLTableManager.findCommentPos(CharSequence, String)"})
  public void testFindCommentPosWithCsSlComment() {
    // Arrange, Act and Assert
    assertEquals(
        -1,
        SQLTableManager.findCommentPos(
            "org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor$NestedObjectCommand",
            "Sl Comment"));
  }

  /**
   * Test {@link SQLTableManager#findCommentPos(CharSequence, String, int, boolean)} with {@code
   * cs}, {@code slComment}, {@code start}, {@code findFirst}.
   *
   * <p>Method under test: {@link SQLTableManager#findCommentPos(CharSequence, String, int,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLTableManager.findCommentPos(CharSequence, String, int, boolean)"})
  public void testFindCommentPosWithCsSlCommentStartFindFirst() {
    // Arrange and Act
    int actualFindCommentPosResult =
        SQLTableManager.findCommentPos(
            "org.jkiss.dbeaver.model.impl.sql.edit.SQLObjectEditor$NestedObjectCommand",
            "Sl Comment",
            1,
            true);

    // Assert
    assertEquals(-1, actualFindCommentPosResult);
  }

  /**
   * Test {@link SQLTableManager#findCommentPos(CharSequence, String, int, boolean)} with {@code
   * cs}, {@code slComment}, {@code start}, {@code findFirst}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableManager#findCommentPos(CharSequence, String, int,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLTableManager.findCommentPos(CharSequence, String, int, boolean)"})
  public void testFindCommentPosWithCsSlCommentStartFindFirst_thenReturnMinusOne() {
    // Arrange and Act
    int actualFindCommentPosResult =
        SQLTableManager.findCommentPos(
            SQLTableManager.BASE_MATERIALIZED_VIEW_NAME, "Sl Comment", 1, true);

    // Assert
    assertEquals(-1, actualFindCommentPosResult);
  }

  /**
   * Test {@link SQLTableManager#findCommentPos(CharSequence, String, int, boolean)} with {@code
   * cs}, {@code slComment}, {@code start}, {@code findFirst}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableManager#findCommentPos(CharSequence, String, int,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLTableManager.findCommentPos(CharSequence, String, int, boolean)"})
  public void testFindCommentPosWithCsSlCommentStartFindFirst_whenEmptyString_thenReturnOne() {
    // Arrange and Act
    int actualFindCommentPosResult =
        SQLTableManager.findCommentPos(SQLTableManager.BASE_MATERIALIZED_VIEW_NAME, "", 1, true);

    // Assert
    assertEquals(1, actualFindCommentPosResult);
  }

  /**
   * Test {@link SQLTableManager#findCommentPos(CharSequence, String, int, boolean)} with {@code
   * cs}, {@code slComment}, {@code start}, {@code findFirst}.
   *
   * <ul>
   *   <li>When {@link DBEObjectManager#OPTION_ACTIVE_EDITOR}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableManager#findCommentPos(CharSequence, String, int,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLTableManager.findCommentPos(CharSequence, String, int, boolean)"})
  public void testFindCommentPosWithCsSlCommentStartFindFirst_whenOption_active_editor() {
    // Arrange and Act
    int actualFindCommentPosResult =
        SQLTableManager.findCommentPos(
            DBEObjectManager.OPTION_ACTIVE_EDITOR, "Sl Comment", 1, true);

    // Assert
    assertEquals(-1, actualFindCommentPosResult);
  }

  /**
   * Test {@link SQLTableManager#findCommentPos(CharSequence, String)} with {@code cs}, {@code
   * slComment}.
   *
   * <ul>
   *   <li>When {@link SQLTableManager#BASE_MATERIALIZED_VIEW_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableManager#findCommentPos(CharSequence, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLTableManager.findCommentPos(CharSequence, String)"})
  public void testFindCommentPosWithCsSlComment_whenBase_materialized_view_name() {
    // Arrange, Act and Assert
    assertEquals(
        -1,
        SQLTableManager.findCommentPos(SQLTableManager.BASE_MATERIALIZED_VIEW_NAME, "Sl Comment"));
  }

  /**
   * Test {@link SQLTableManager#findCommentPos(CharSequence, String)} with {@code cs}, {@code
   * slComment}.
   *
   * <ul>
   *   <li>When {@link SQLTableManager#BASE_MATERIALIZED_VIEW_NAME}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableManager#findCommentPos(CharSequence, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLTableManager.findCommentPos(CharSequence, String)"})
  public void testFindCommentPosWithCsSlComment_whenBase_materialized_view_name_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(
        0, SQLTableManager.findCommentPos(SQLTableManager.BASE_MATERIALIZED_VIEW_NAME, ""));
  }

  /**
   * Test {@link SQLTableManager#findCommentPos(CharSequence, String)} with {@code cs}, {@code
   * slComment}.
   *
   * <ul>
   *   <li>When {@link DBEObjectManager#OPTION_ACTIVE_EDITOR}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableManager#findCommentPos(CharSequence, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int SQLTableManager.findCommentPos(CharSequence, String)"})
  public void testFindCommentPosWithCsSlComment_whenOption_active_editor_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(
        -1, SQLTableManager.findCommentPos(DBEObjectManager.OPTION_ACTIVE_EDITOR, "Sl Comment"));
  }

  /**
   * Test {@link SQLTableManager#appendCommaBeforeLastComment(StringBuilder, String, int)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code ,42} toString is {@code
   *       ,42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableManager#appendCommaBeforeLastComment(StringBuilder,
   * String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int SQLTableManager.appendCommaBeforeLastComment(StringBuilder, String, int)"
  })
  public void testAppendCommaBeforeLastComment_thenStringBuilderWith42ToStringIs42() {
    // Arrange
    StringBuilder query = new StringBuilder(",42");

    // Act
    int actualAppendCommaBeforeLastCommentResult =
        SQLTableManager.appendCommaBeforeLastComment(query, "42", 1);

    // Assert
    assertEquals(",42", query.toString());
    assertEquals(4, actualAppendCommaBeforeLastCommentResult);
  }

  /**
   * Test {@link SQLTableManager#appendCommaBeforeLastComment(StringBuilder, String, int)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code 42} toString is {@code ,42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableManager#appendCommaBeforeLastComment(StringBuilder,
   * String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int SQLTableManager.appendCommaBeforeLastComment(StringBuilder, String, int)"
  })
  public void testAppendCommaBeforeLastComment_thenStringBuilderWith42ToStringIs422() {
    // Arrange
    StringBuilder query = new StringBuilder("42");

    // Act
    int actualAppendCommaBeforeLastCommentResult =
        SQLTableManager.appendCommaBeforeLastComment(query, "42", 0);

    // Assert
    assertEquals(",42", query.toString());
    assertEquals(3, actualAppendCommaBeforeLastCommentResult);
  }

  /**
   * Test {@link SQLTableManager#appendCommaBeforeLastComment(StringBuilder, String, int)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo42} toString is {@code
   *       foo,42}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableManager#appendCommaBeforeLastComment(StringBuilder,
   * String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int SQLTableManager.appendCommaBeforeLastComment(StringBuilder, String, int)"
  })
  public void testAppendCommaBeforeLastComment_thenStringBuilderWithFoo42ToStringIsFoo42() {
    // Arrange
    StringBuilder query = new StringBuilder("foo42");

    // Act
    int actualAppendCommaBeforeLastCommentResult =
        SQLTableManager.appendCommaBeforeLastComment(query, "42", 1);

    // Assert
    assertEquals("foo,42", query.toString());
    assertEquals(6, actualAppendCommaBeforeLastCommentResult);
  }

  /**
   * Test {@link SQLTableManager#appendCommaBeforeLastComment(StringBuilder, String, int)}.
   *
   * <ul>
   *   <li>Then {@link StringBuilder#StringBuilder(String)} with {@code foo} toString is {@code
   *       foo,}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableManager#appendCommaBeforeLastComment(StringBuilder,
   * String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int SQLTableManager.appendCommaBeforeLastComment(StringBuilder, String, int)"
  })
  public void testAppendCommaBeforeLastComment_thenStringBuilderWithFooToStringIsFoo() {
    // Arrange
    StringBuilder query = new StringBuilder("foo");

    // Act
    int actualAppendCommaBeforeLastCommentResult =
        SQLTableManager.appendCommaBeforeLastComment(query, "Sl Comment", 1);

    // Assert
    assertEquals("foo,", query.toString());
    assertEquals(4, actualAppendCommaBeforeLastCommentResult);
  }
}
