package org.jkiss.dbeaver.model.data;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBDDisplayFormatDiffblueTest {
  /**
   * Test {@link DBDDisplayFormat#safeValueOf(String)}.
   *
   * <ul>
   *   <li>When {@code EDIT}.
   *   <li>Then return {@code EDIT}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDisplayFormat#safeValueOf(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDDisplayFormat DBDDisplayFormat.safeValueOf(String)"})
  public void testSafeValueOf_whenEdit_thenReturnEdit() {
    // Arrange, Act and Assert
    assertEquals(DBDDisplayFormat.EDIT, DBDDisplayFormat.safeValueOf("EDIT"));
  }

  /**
   * Test {@link DBDDisplayFormat#safeValueOf(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code UI}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDisplayFormat#safeValueOf(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDDisplayFormat DBDDisplayFormat.safeValueOf(String)"})
  public void testSafeValueOf_whenEmptyString_thenReturnUi() {
    // Arrange, Act and Assert
    assertEquals(DBDDisplayFormat.UI, DBDDisplayFormat.safeValueOf(""));
  }

  /**
   * Test {@link DBDDisplayFormat#safeValueOf(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code UI}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDisplayFormat#safeValueOf(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDDisplayFormat DBDDisplayFormat.safeValueOf(String)"})
  public void testSafeValueOf_whenNull_thenReturnUi() {
    // Arrange, Act and Assert
    assertEquals(DBDDisplayFormat.UI, DBDDisplayFormat.safeValueOf(null));
  }

  /**
   * Test {@link DBDDisplayFormat#safeValueOf(String)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return {@code UI}.
   * </ul>
   *
   * <p>Method under test: {@link DBDDisplayFormat#safeValueOf(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBDDisplayFormat DBDDisplayFormat.safeValueOf(String)"})
  public void testSafeValueOf_whenString_thenReturnUi() {
    // Arrange, Act and Assert
    assertEquals(DBDDisplayFormat.UI, DBDDisplayFormat.safeValueOf("String"));
  }
}
