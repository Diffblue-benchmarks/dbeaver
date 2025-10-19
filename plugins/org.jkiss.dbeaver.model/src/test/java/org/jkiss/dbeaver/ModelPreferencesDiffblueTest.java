package org.jkiss.dbeaver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.ModelPreferences.SQLScriptStatementDelimiterMode;
import org.jkiss.dbeaver.ModelPreferences.SeparateConnectionBehavior;
import org.jkiss.dbeaver.model.impl.preferences.BundlePreferenceStore;
import org.jkiss.dbeaver.model.preferences.DBPPreferenceStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ModelPreferencesDiffblueTest {
  /**
   * Test SQLScriptStatementDelimiterMode {@link
   * SQLScriptStatementDelimiterMode#fromPreferences(DBPPreferenceStore)}.
   *
   * <p>Method under test: {@link
   * SQLScriptStatementDelimiterMode#fromPreferences(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptStatementDelimiterMode SQLScriptStatementDelimiterMode.fromPreferences(DBPPreferenceStore)"
  })
  public void testSQLScriptStatementDelimiterModeFromPreferences() {
    // Arrange
    DBPPreferenceStore preferenceStore = mock(DBPPreferenceStore.class);
    when(preferenceStore.getString(Mockito.<String>any())).thenReturn(Boolean.TRUE.toString());

    // Act
    SQLScriptStatementDelimiterMode actualFromPreferencesResult =
        SQLScriptStatementDelimiterMode.fromPreferences(preferenceStore);

    // Assert
    verify(preferenceStore).getString("script.sql.delimiter.blank");
    assertEquals(
        SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR, actualFromPreferencesResult);
  }

  /**
   * Test SQLScriptStatementDelimiterMode {@link
   * SQLScriptStatementDelimiterMode#fromPreferences(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLScriptStatementDelimiterMode#fromPreferences(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptStatementDelimiterMode SQLScriptStatementDelimiterMode.fromPreferences(DBPPreferenceStore)"
  })
  public void testSQLScriptStatementDelimiterModeFromPreferences_givenNull() {
    // Arrange
    BundlePreferenceStore preferenceStore = mock(BundlePreferenceStore.class);
    when(preferenceStore.getString(Mockito.<String>any())).thenReturn(null);

    // Act
    SQLScriptStatementDelimiterMode actualFromPreferencesResult =
        SQLScriptStatementDelimiterMode.fromPreferences(preferenceStore);

    // Assert
    verify(preferenceStore).getString("script.sql.delimiter.blank");
    assertEquals(SQLScriptStatementDelimiterMode.SMART, actualFromPreferencesResult);
  }

  /**
   * Test SQLScriptStatementDelimiterMode {@link
   * SQLScriptStatementDelimiterMode#fromPreferences(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLScriptStatementDelimiterMode#fromPreferences(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptStatementDelimiterMode SQLScriptStatementDelimiterMode.fromPreferences(DBPPreferenceStore)"
  })
  public void testSQLScriptStatementDelimiterModeFromPreferences_givenString() {
    // Arrange
    BundlePreferenceStore preferenceStore = mock(BundlePreferenceStore.class);
    when(preferenceStore.getString(Mockito.<String>any())).thenReturn("String");

    // Act
    SQLScriptStatementDelimiterMode actualFromPreferencesResult =
        SQLScriptStatementDelimiterMode.fromPreferences(preferenceStore);

    // Assert
    verify(preferenceStore).getString("script.sql.delimiter.blank");
    assertEquals(SQLScriptStatementDelimiterMode.SMART, actualFromPreferencesResult);
  }

  /**
   * Test SQLScriptStatementDelimiterMode {@link
   * SQLScriptStatementDelimiterMode#fromPreferences(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Then return {@code ONLY_SEPARATOR}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SQLScriptStatementDelimiterMode#fromPreferences(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptStatementDelimiterMode SQLScriptStatementDelimiterMode.fromPreferences(DBPPreferenceStore)"
  })
  public void testSQLScriptStatementDelimiterModeFromPreferences_thenReturnOnlySeparator() {
    // Arrange
    DBPPreferenceStore preferenceStore = mock(DBPPreferenceStore.class);
    when(preferenceStore.getString(Mockito.<String>any())).thenReturn(Boolean.FALSE.toString());

    // Act
    SQLScriptStatementDelimiterMode actualFromPreferencesResult =
        SQLScriptStatementDelimiterMode.fromPreferences(preferenceStore);

    // Assert
    verify(preferenceStore).getString("script.sql.delimiter.blank");
    assertEquals(SQLScriptStatementDelimiterMode.ONLY_SEPARATOR, actualFromPreferencesResult);
  }

  /**
   * Test SQLScriptStatementDelimiterMode {@link SQLScriptStatementDelimiterMode#getName()}.
   *
   * <p>Method under test: {@link SQLScriptStatementDelimiterMode#getName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLScriptStatementDelimiterMode.getName()"})
  public void testSQLScriptStatementDelimiterModeGetName() {
    // Arrange, Act and Assert
    assertEquals(
        "BLANK_LINE_AND_SEPARATOR",
        SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR.getName());
  }

  /**
   * Test SQLScriptStatementDelimiterMode {@link SQLScriptStatementDelimiterMode#getTitle()}.
   *
   * <p>Method under test: {@link SQLScriptStatementDelimiterMode#getTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLScriptStatementDelimiterMode.getTitle()"})
  public void testSQLScriptStatementDelimiterModeGetTitle() {
    // Arrange, Act and Assert
    assertEquals(
        "Always", SQLScriptStatementDelimiterMode.valueOf("BLANK_LINE_AND_SEPARATOR").getTitle());
  }

  /**
   * Test SQLScriptStatementDelimiterMode {@link
   * SQLScriptStatementDelimiterMode#valueByName(String)}.
   *
   * <ul>
   *   <li>Then return {@code ONLY_SEPARATOR}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptStatementDelimiterMode#valueByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptStatementDelimiterMode SQLScriptStatementDelimiterMode.valueByName(String)"
  })
  public void testSQLScriptStatementDelimiterModeValueByName_thenReturnOnlySeparator() {
    // Arrange, Act and Assert
    assertEquals(
        SQLScriptStatementDelimiterMode.ONLY_SEPARATOR,
        SQLScriptStatementDelimiterMode.valueByName(Boolean.FALSE.toString()));
  }

  /**
   * Test SQLScriptStatementDelimiterMode {@link
   * SQLScriptStatementDelimiterMode#valueByName(String)}.
   *
   * <ul>
   *   <li>When {@code BLANK_LINE_AND_SEPARATOR}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptStatementDelimiterMode#valueByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptStatementDelimiterMode SQLScriptStatementDelimiterMode.valueByName(String)"
  })
  public void testSQLScriptStatementDelimiterModeValueByName_whenBlankLineAndSeparator() {
    // Arrange, Act and Assert
    assertEquals(
        SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR,
        SQLScriptStatementDelimiterMode.valueByName("BLANK_LINE_AND_SEPARATOR"));
  }

  /**
   * Test SQLScriptStatementDelimiterMode {@link
   * SQLScriptStatementDelimiterMode#valueByName(String)}.
   *
   * <ul>
   *   <li>When {@code Name}.
   *   <li>Then return {@code SMART}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptStatementDelimiterMode#valueByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptStatementDelimiterMode SQLScriptStatementDelimiterMode.valueByName(String)"
  })
  public void testSQLScriptStatementDelimiterModeValueByName_whenName_thenReturnSmart() {
    // Arrange, Act and Assert
    assertEquals(
        SQLScriptStatementDelimiterMode.SMART, SQLScriptStatementDelimiterMode.valueByName("Name"));
  }

  /**
   * Test SQLScriptStatementDelimiterMode {@link
   * SQLScriptStatementDelimiterMode#valueByName(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code SMART}.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptStatementDelimiterMode#valueByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptStatementDelimiterMode SQLScriptStatementDelimiterMode.valueByName(String)"
  })
  public void testSQLScriptStatementDelimiterModeValueByName_whenNull_thenReturnSmart() {
    // Arrange, Act and Assert
    assertEquals(
        SQLScriptStatementDelimiterMode.SMART, SQLScriptStatementDelimiterMode.valueByName(null));
  }

  /**
   * Test SQLScriptStatementDelimiterMode {@link
   * SQLScriptStatementDelimiterMode#valueByName(String)}.
   *
   * <ul>
   *   <li>When {@link Boolean#TRUE} toString.
   * </ul>
   *
   * <p>Method under test: {@link SQLScriptStatementDelimiterMode#valueByName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLScriptStatementDelimiterMode SQLScriptStatementDelimiterMode.valueByName(String)"
  })
  public void testSQLScriptStatementDelimiterModeValueByName_whenTrueToString() {
    // Arrange, Act and Assert
    assertEquals(
        SQLScriptStatementDelimiterMode.BLANK_LINE_AND_SEPARATOR,
        SQLScriptStatementDelimiterMode.valueByName(Boolean.TRUE.toString()));
  }

  /**
   * Test SeparateConnectionBehavior {@link SeparateConnectionBehavior#getTitle()}.
   *
   * <p>Method under test: {@link SeparateConnectionBehavior#getTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SeparateConnectionBehavior.getTitle()"})
  public void testSeparateConnectionBehaviorGetTitle() {
    // Arrange, Act and Assert
    assertEquals("Default", SeparateConnectionBehavior.valueOf("DEFAULT").getTitle());
  }

  /**
   * Test SeparateConnectionBehavior {@link SeparateConnectionBehavior#parse(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code DEFAULT}.
   * </ul>
   *
   * <p>Method under test: {@link SeparateConnectionBehavior#parse(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SeparateConnectionBehavior SeparateConnectionBehavior.parse(String)"})
  public void testSeparateConnectionBehaviorParse_when42_thenReturnDefault() {
    // Arrange, Act and Assert
    assertEquals(SeparateConnectionBehavior.DEFAULT, SeparateConnectionBehavior.parse("42"));
  }

  /**
   * Test SeparateConnectionBehavior {@link SeparateConnectionBehavior#parse(String)}.
   *
   * <ul>
   *   <li>When {@code ALWAYS}.
   *   <li>Then return {@code ALWAYS}.
   * </ul>
   *
   * <p>Method under test: {@link SeparateConnectionBehavior#parse(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SeparateConnectionBehavior SeparateConnectionBehavior.parse(String)"})
  public void testSeparateConnectionBehaviorParse_whenAlways_thenReturnAlways() {
    // Arrange, Act and Assert
    assertEquals(SeparateConnectionBehavior.ALWAYS, SeparateConnectionBehavior.parse("ALWAYS"));
  }

  /**
   * Test SeparateConnectionBehavior {@link SeparateConnectionBehavior#parse(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code DEFAULT}.
   * </ul>
   *
   * <p>Method under test: {@link SeparateConnectionBehavior#parse(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SeparateConnectionBehavior SeparateConnectionBehavior.parse(String)"})
  public void testSeparateConnectionBehaviorParse_whenEmptyString_thenReturnDefault() {
    // Arrange, Act and Assert
    assertEquals(SeparateConnectionBehavior.DEFAULT, SeparateConnectionBehavior.parse(""));
  }

  /**
   * Test SeparateConnectionBehavior {@link SeparateConnectionBehavior#parse(String)}.
   *
   * <ul>
   *   <li>When {@link Boolean#FALSE} toString.
   *   <li>Then return {@code NEVER}.
   * </ul>
   *
   * <p>Method under test: {@link SeparateConnectionBehavior#parse(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SeparateConnectionBehavior SeparateConnectionBehavior.parse(String)"})
  public void testSeparateConnectionBehaviorParse_whenFalseToString_thenReturnNever() {
    // Arrange, Act and Assert
    assertEquals(
        SeparateConnectionBehavior.NEVER,
        SeparateConnectionBehavior.parse(Boolean.FALSE.toString()));
  }

  /**
   * Test SeparateConnectionBehavior {@link SeparateConnectionBehavior#parse(String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code DEFAULT}.
   * </ul>
   *
   * <p>Method under test: {@link SeparateConnectionBehavior#parse(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SeparateConnectionBehavior SeparateConnectionBehavior.parse(String)"})
  public void testSeparateConnectionBehaviorParse_whenNull_thenReturnDefault() {
    // Arrange, Act and Assert
    assertEquals(SeparateConnectionBehavior.DEFAULT, SeparateConnectionBehavior.parse(null));
  }

  /**
   * Test SeparateConnectionBehavior {@link SeparateConnectionBehavior#parse(String)}.
   *
   * <ul>
   *   <li>When {@link Boolean#TRUE} toString.
   *   <li>Then return {@code DEFAULT}.
   * </ul>
   *
   * <p>Method under test: {@link SeparateConnectionBehavior#parse(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SeparateConnectionBehavior SeparateConnectionBehavior.parse(String)"})
  public void testSeparateConnectionBehaviorParse_whenTrueToString_thenReturnDefault() {
    // Arrange, Act and Assert
    assertEquals(
        SeparateConnectionBehavior.DEFAULT,
        SeparateConnectionBehavior.parse(Boolean.TRUE.toString()));
  }
}
