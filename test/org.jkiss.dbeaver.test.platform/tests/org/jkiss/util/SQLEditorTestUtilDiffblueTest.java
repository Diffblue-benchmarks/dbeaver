package org.jkiss.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLEditorTestUtilDiffblueTest {
  /**
   * Test {@link SQLEditorTestUtil#getCursorPositions(String[])}.
   *
   * <ul>
   *   <li>When {@link SQLEditorTestUtil#CURSOR} and {@code Queries}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link SQLEditorTestUtil#getCursorPositions(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SQLEditorTestUtil.getCursorPositions(String[])"})
  public void testGetCursorPositions_whenCursorAndQueries_thenReturnSizeIsTwo() {
    // Arrange and Act
    Map<String, int[]> actualCursorPositions =
        SQLEditorTestUtil.getCursorPositions(SQLEditorTestUtil.CURSOR, "Queries");

    // Assert
    assertEquals(2, actualCursorPositions.size());
    assertArrayEquals(new int[] {}, actualCursorPositions.get("Queries"));
    assertArrayEquals(new int[] {0}, actualCursorPositions.get(""));
  }

  /**
   * Test {@link SQLEditorTestUtil#getCursorPositions(String[])}.
   *
   * <ul>
   *   <li>When {@code Queries}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link SQLEditorTestUtil#getCursorPositions(String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SQLEditorTestUtil.getCursorPositions(String[])"})
  public void testGetCursorPositions_whenQueries_thenReturnSizeIsOne() {
    // Arrange and Act
    Map<String, int[]> actualCursorPositions = SQLEditorTestUtil.getCursorPositions("Queries");

    // Assert
    assertEquals(1, actualCursorPositions.size());
    assertArrayEquals(new int[] {}, actualCursorPositions.get("Queries"));
  }
}
