package org.jkiss.dbeaver.model.sql;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.impl.preferences.BundlePreferenceStore;
import org.jkiss.dbeaver.model.preferences.DBPPreferenceStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SQLTableAliasInsertModeDiffblueTest {
  /**
   * Test {@link SQLTableAliasInsertMode#getText()}.
   *
   * <p>Method under test: {@link SQLTableAliasInsertMode#getText()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SQLTableAliasInsertMode.getText()"})
  public void testGetText() {
    // Arrange, Act and Assert
    assertEquals("N/A", SQLTableAliasInsertMode.valueOf("NONE").getText());
  }

  /**
   * Test {@link SQLTableAliasInsertMode#fromPreferences(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableAliasInsertMode#fromPreferences(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLTableAliasInsertMode SQLTableAliasInsertMode.fromPreferences(DBPPreferenceStore)"
  })
  public void testFromPreferences_givenEmptyString() {
    // Arrange
    BundlePreferenceStore preferenceStore = mock(BundlePreferenceStore.class);
    when(preferenceStore.getString(Mockito.<String>any())).thenReturn("");

    // Act
    SQLTableAliasInsertMode actualFromPreferencesResult =
        SQLTableAliasInsertMode.fromPreferences(preferenceStore);

    // Assert
    verify(preferenceStore).getString("sql.proposals.insert.table.alias");
    assertEquals(SQLTableAliasInsertMode.PLAIN, actualFromPreferencesResult);
  }

  /**
   * Test {@link SQLTableAliasInsertMode#fromPreferences(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@link Boolean#FALSE} toString.
   *   <li>Then return {@code NONE}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableAliasInsertMode#fromPreferences(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLTableAliasInsertMode SQLTableAliasInsertMode.fromPreferences(DBPPreferenceStore)"
  })
  public void testFromPreferences_givenFalseToString_thenReturnNone() {
    // Arrange
    BundlePreferenceStore preferenceStore = mock(BundlePreferenceStore.class);
    when(preferenceStore.getString(Mockito.<String>any())).thenReturn(Boolean.FALSE.toString());

    // Act
    SQLTableAliasInsertMode actualFromPreferencesResult =
        SQLTableAliasInsertMode.fromPreferences(preferenceStore);

    // Assert
    verify(preferenceStore).getString("sql.proposals.insert.table.alias");
    assertEquals(SQLTableAliasInsertMode.NONE, actualFromPreferencesResult);
  }

  /**
   * Test {@link SQLTableAliasInsertMode#fromPreferences(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then calls {@link DBPPreferenceStore#getString(String)}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableAliasInsertMode#fromPreferences(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLTableAliasInsertMode SQLTableAliasInsertMode.fromPreferences(DBPPreferenceStore)"
  })
  public void testFromPreferences_givenNull_thenCallsGetString() {
    // Arrange
    DBPPreferenceStore preferenceStore = mock(DBPPreferenceStore.class);
    when(preferenceStore.getString(Mockito.<String>any())).thenReturn(null);

    // Act
    SQLTableAliasInsertMode actualFromPreferencesResult =
        SQLTableAliasInsertMode.fromPreferences(preferenceStore);

    // Assert
    verify(preferenceStore).getString("sql.proposals.insert.table.alias");
    assertEquals(SQLTableAliasInsertMode.PLAIN, actualFromPreferencesResult);
  }

  /**
   * Test {@link SQLTableAliasInsertMode#fromPreferences(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#getString(String)} return
   *       {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableAliasInsertMode#fromPreferences(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLTableAliasInsertMode SQLTableAliasInsertMode.fromPreferences(DBPPreferenceStore)"
  })
  public void testFromPreferences_givenString_whenBundlePreferenceStoreGetStringReturnString() {
    // Arrange
    BundlePreferenceStore preferenceStore = mock(BundlePreferenceStore.class);
    when(preferenceStore.getString(Mockito.<String>any())).thenReturn("String");

    // Act
    SQLTableAliasInsertMode actualFromPreferencesResult =
        SQLTableAliasInsertMode.fromPreferences(preferenceStore);

    // Assert
    verify(preferenceStore).getString("sql.proposals.insert.table.alias");
    assertEquals(SQLTableAliasInsertMode.PLAIN, actualFromPreferencesResult);
  }

  /**
   * Test {@link SQLTableAliasInsertMode#fromPreferences(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@link Boolean#TRUE} toString.
   * </ul>
   *
   * <p>Method under test: {@link SQLTableAliasInsertMode#fromPreferences(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SQLTableAliasInsertMode SQLTableAliasInsertMode.fromPreferences(DBPPreferenceStore)"
  })
  public void testFromPreferences_givenTrueToString() {
    // Arrange
    BundlePreferenceStore preferenceStore = mock(BundlePreferenceStore.class);
    when(preferenceStore.getString(Mockito.<String>any())).thenReturn(Boolean.TRUE.toString());

    // Act
    SQLTableAliasInsertMode actualFromPreferencesResult =
        SQLTableAliasInsertMode.fromPreferences(preferenceStore);

    // Assert
    verify(preferenceStore).getString("sql.proposals.insert.table.alias");
    assertEquals(SQLTableAliasInsertMode.PLAIN, actualFromPreferencesResult);
  }
}
