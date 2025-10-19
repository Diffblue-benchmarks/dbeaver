package org.jkiss.dbeaver.model.text;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Locale;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TextUtilsDiffblueTest {
  /**
   * Test {@link TextUtils#isEmptyLine(IDocument, int)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#isEmptyLine(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TextUtils.isEmptyLine(IDocument, int)"})
  public void testIsEmptyLine_thenReturnFalse() throws BadLocationException {
    // Arrange, Act and Assert
    assertFalse(TextUtils.isEmptyLine(new Document("Not all who wander are lost"), 0));
  }

  /**
   * Test {@link TextUtils#isEmptyLine(IDocument, int)}.
   *
   * <ul>
   *   <li>When {@link Document#Document()}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#isEmptyLine(IDocument, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TextUtils.isEmptyLine(IDocument, int)"})
  public void testIsEmptyLine_whenDocument_thenReturnTrue() throws BadLocationException {
    // Arrange, Act and Assert
    assertTrue(TextUtils.isEmptyLine(new Document(), 0));
  }

  /**
   * Test {@link TextUtils#getOffsetOf(IDocument, int, String)}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#getOffsetOf(IDocument, int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TextUtils.getOffsetOf(IDocument, int, String)"})
  public void testGetOffsetOf_thenReturnMinusOne() throws BadLocationException {
    // Arrange, Act and Assert
    assertEquals(
        -1, TextUtils.getOffsetOf(new Document("Not all who wander are lost"), 0, "Pattern"));
  }

  /**
   * Test {@link TextUtils#getOffsetOf(IDocument, int, String)}.
   *
   * <ul>
   *   <li>When {@link Document#Document()}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#getOffsetOf(IDocument, int, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TextUtils.getOffsetOf(IDocument, int, String)"})
  public void testGetOffsetOf_whenDocument_thenReturnMinusOne() throws BadLocationException {
    // Arrange, Act and Assert
    assertEquals(-1, TextUtils.getOffsetOf(new Document(), 0, "Pattern"));
  }

  /**
   * Test {@link TextUtils#fuzzyScore(CharSequence, CharSequence, Locale)} with {@code term}, {@code
   * query}, {@code locale}.
   *
   * <ul>
   *   <li>Then return five hundred twenty.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#fuzzyScore(CharSequence, CharSequence, Locale)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TextUtils.fuzzyScore(CharSequence, CharSequence, Locale)"})
  public void testFuzzyScoreWithTermQueryLocale_thenReturnFiveHundredTwenty() {
    // Arrange and Act
    int actualFuzzyScoreResult =
        TextUtils.fuzzyScore(
            Plugin.PLUGIN_PREFERENCE_SCOPE, Plugin.PLUGIN_PREFERENCE_SCOPE, Locale.getDefault());

    // Assert
    assertEquals(520, actualFuzzyScoreResult);
  }

  /**
   * Test {@link TextUtils#fuzzyScore(CharSequence, CharSequence, Locale)} with {@code term}, {@code
   * query}, {@code locale}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#fuzzyScore(CharSequence, CharSequence, Locale)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TextUtils.fuzzyScore(CharSequence, CharSequence, Locale)"})
  public void testFuzzyScoreWithTermQueryLocale_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> TextUtils.fuzzyScore(null, null, Locale.getDefault()));
  }

  /**
   * Test {@link TextUtils#fuzzyScore(CharSequence, CharSequence, Locale)} with {@code term}, {@code
   * query}, {@code locale}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#fuzzyScore(CharSequence, CharSequence, Locale)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TextUtils.fuzzyScore(CharSequence, CharSequence, Locale)"})
  public void testFuzzyScoreWithTermQueryLocale_whenNull_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> TextUtils.fuzzyScore(Plugin.PLUGIN_PREFERENCE_SCOPE, null, Locale.getDefault()));
  }

  /**
   * Test {@link TextUtils#fuzzyScore(CharSequence, CharSequence, Locale)} with {@code term}, {@code
   * query}, {@code locale}.
   *
   * <ul>
   *   <li>When {@link Plugin#PREFERENCES_DEFAULT_OVERRIDE_BASE_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#fuzzyScore(CharSequence, CharSequence, Locale)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TextUtils.fuzzyScore(CharSequence, CharSequence, Locale)"})
  public void testFuzzyScoreWithTermQueryLocale_whenPreferences_default_override_base_name() {
    // Arrange and Act
    int actualFuzzyScoreResult =
        TextUtils.fuzzyScore(
            Plugin.PREFERENCES_DEFAULT_OVERRIDE_BASE_NAME,
            Plugin.PLUGIN_PREFERENCE_SCOPE,
            Locale.getDefault());

    // Assert
    assertEquals(0, actualFuzzyScoreResult);
  }

  /**
   * Test {@link TextUtils#fuzzyScore(CharSequence, CharSequence, Locale)} with {@code term}, {@code
   * query}, {@code locale}.
   *
   * <ul>
   *   <li>When {@link Plugin#PREFERENCES_DEFAULT_OVERRIDE_FILE_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#fuzzyScore(CharSequence, CharSequence, Locale)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TextUtils.fuzzyScore(CharSequence, CharSequence, Locale)"})
  public void testFuzzyScoreWithTermQueryLocale_whenPreferences_default_override_file_name() {
    // Arrange and Act
    int actualFuzzyScoreResult =
        TextUtils.fuzzyScore(
            Plugin.PREFERENCES_DEFAULT_OVERRIDE_FILE_NAME,
            Plugin.PLUGIN_PREFERENCE_SCOPE,
            Locale.getDefault());

    // Assert
    assertEquals(0, actualFuzzyScoreResult);
  }

  /**
   * Test {@link TextUtils#fuzzyScore(CharSequence, CharSequence)} with {@code term}, {@code query}.
   *
   * <ul>
   *   <li>Then return five hundred twenty.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#fuzzyScore(CharSequence, CharSequence)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TextUtils.fuzzyScore(CharSequence, CharSequence)"})
  public void testFuzzyScoreWithTermQuery_thenReturnFiveHundredTwenty() {
    // Arrange, Act and Assert
    assertEquals(
        520, TextUtils.fuzzyScore(Plugin.PLUGIN_PREFERENCE_SCOPE, Plugin.PLUGIN_PREFERENCE_SCOPE));
  }

  /**
   * Test {@link TextUtils#fuzzyScore(CharSequence, CharSequence)} with {@code term}, {@code query}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#fuzzyScore(CharSequence, CharSequence)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TextUtils.fuzzyScore(CharSequence, CharSequence)"})
  public void testFuzzyScoreWithTermQuery_whenNull_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> TextUtils.fuzzyScore(null, null));
  }

  /**
   * Test {@link TextUtils#fuzzyScore(CharSequence, CharSequence)} with {@code term}, {@code query}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#fuzzyScore(CharSequence, CharSequence)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TextUtils.fuzzyScore(CharSequence, CharSequence)"})
  public void testFuzzyScoreWithTermQuery_whenNull_thenThrowIllegalArgumentException2() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> TextUtils.fuzzyScore(Plugin.PLUGIN_PREFERENCE_SCOPE, null));
  }

  /**
   * Test {@link TextUtils#fuzzyScore(CharSequence, CharSequence)} with {@code term}, {@code query}.
   *
   * <ul>
   *   <li>When {@link Plugin#PREFERENCES_DEFAULT_OVERRIDE_BASE_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#fuzzyScore(CharSequence, CharSequence)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TextUtils.fuzzyScore(CharSequence, CharSequence)"})
  public void testFuzzyScoreWithTermQuery_whenPreferences_default_override_base_name() {
    // Arrange, Act and Assert
    assertEquals(
        0,
        TextUtils.fuzzyScore(
            Plugin.PREFERENCES_DEFAULT_OVERRIDE_BASE_NAME, Plugin.PLUGIN_PREFERENCE_SCOPE));
  }

  /**
   * Test {@link TextUtils#fuzzyScore(CharSequence, CharSequence)} with {@code term}, {@code query}.
   *
   * <ul>
   *   <li>When {@link Plugin#PREFERENCES_DEFAULT_OVERRIDE_FILE_NAME}.
   * </ul>
   *
   * <p>Method under test: {@link TextUtils#fuzzyScore(CharSequence, CharSequence)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int TextUtils.fuzzyScore(CharSequence, CharSequence)"})
  public void testFuzzyScoreWithTermQuery_whenPreferences_default_override_file_name() {
    // Arrange, Act and Assert
    assertEquals(
        0,
        TextUtils.fuzzyScore(
            Plugin.PREFERENCES_DEFAULT_OVERRIDE_FILE_NAME, Plugin.PLUGIN_PREFERENCE_SCOPE));
  }
}
