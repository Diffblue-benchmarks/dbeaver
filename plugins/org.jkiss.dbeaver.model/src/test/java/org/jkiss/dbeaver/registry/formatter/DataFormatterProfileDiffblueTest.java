package org.jkiss.dbeaver.registry.formatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.preferences.BundlePreferenceStore;
import org.jkiss.dbeaver.model.impl.preferences.SimplePreferenceStore;
import org.jkiss.dbeaver.model.preferences.DBPPreferenceListener;
import org.jkiss.dbeaver.model.preferences.DBPPreferenceListener.PreferenceChangeEvent;
import org.jkiss.dbeaver.model.preferences.DBPPreferenceStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DataFormatterProfileDiffblueTest {
  /**
   * Test {@link DataFormatterProfile#DataFormatterProfile(String, DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>Then return Locale Country is {@code STRING}.
   * </ul>
   *
   * <p>Method under test: {@link DataFormatterProfile#DataFormatterProfile(String,
   * DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataFormatterProfile.<init>(String, DBPPreferenceStore)"})
  public void testNewDataFormatterProfile_givenString_thenReturnLocaleCountryIsString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");

    // Act
    DataFormatterProfile actualDataFormatterProfile = new DataFormatterProfile("foo.txt", store);

    // Assert
    verify(store, atLeast(1)).getString(Mockito.<String>any());
    Locale locale = actualDataFormatterProfile.getLocale();
    assertEquals("STRING", locale.getCountry());
    assertEquals("STRING", locale.getDisplayCountry());
    assertEquals("String", locale.getDisplayVariant());
    assertEquals("String", locale.getVariant());
    assertEquals("string (STRING, String)", locale.getDisplayName());
    assertEquals("string", locale.getDisplayLanguage());
    assertEquals("string", locale.getLanguage());
  }

  /**
   * Test {@link DataFormatterProfile#DataFormatterProfile(String, DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Then return Locale DisplayVariant is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DataFormatterProfile#DataFormatterProfile(String,
   * DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataFormatterProfile.<init>(String, DBPPreferenceStore)"})
  public void testNewDataFormatterProfile_thenReturnLocaleDisplayVariantIsEmptyString()
      throws MissingResourceException {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("");

    // Act
    DataFormatterProfile actualDataFormatterProfile = new DataFormatterProfile("foo.txt", store);

    // Assert
    verify(store, atLeast(1)).getString(Mockito.<String>any());
    Locale locale = actualDataFormatterProfile.getLocale();
    assertEquals("", locale.getDisplayVariant());
    assertEquals("", locale.getVariant());
    assertEquals("English (United Kingdom)", locale.getDisplayName());
    assertEquals("English", locale.getDisplayLanguage());
    assertEquals("GB", locale.getCountry());
    assertEquals("GBR", locale.getISO3Country());
    assertEquals("United Kingdom", locale.getDisplayCountry());
    assertEquals("en", locale.getLanguage());
    assertEquals("eng", locale.getISO3Language());
  }

  /**
   * Test {@link DataFormatterProfile#isOverridesParent()}.
   *
   * <ul>
   *   <li>Given {@link SimplePreferenceStore} {@link SimplePreferenceStore#isSet(String)} return
   *       {@code true}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link DataFormatterProfile#isOverridesParent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean DataFormatterProfile.isOverridesParent()"})
  public void testIsOverridesParent_givenSimplePreferenceStoreIsSetReturnTrue_thenReturnTrue() {
    // Arrange
    HashMap<String, String> properties = new HashMap<>();
    properties.put("dataformat.profile.language", "Store");
    properties.put("dataformat.profile.country", "Store");
    properties.put("dataformat.profile.variant", "Store");

    SimplePreferenceStore store = mock(SimplePreferenceStore.class);
    when(store.isSet(Mockito.<String>any())).thenReturn(true);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    doNothing().when(store).setProperties(Mockito.<Map<String, String>>any());
    store.setProperties(properties);
    DataFormatterProfile dataFormatterProfile = new DataFormatterProfile("foo.txt", store);

    // Act
    boolean actualIsOverridesParentResult = dataFormatterProfile.isOverridesParent();

    // Assert
    verify(store, atLeast(1)).getString(Mockito.<String>any());
    verify(store).isSet("dataformat.profile.language");
    verify(store).setProperties(isA(Map.class));
    assertTrue(actualIsOverridesParentResult);
  }

  /**
   * Test {@link DataFormatterProfile#reset(DBPPreferenceStore)}.
   *
   * <p>Method under test: {@link DataFormatterProfile#reset(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataFormatterProfile.reset(DBPPreferenceStore)"})
  public void testReset() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");

    DataFormatterProfile dataFormatterProfile = new DataFormatterProfile("foo.txt", store);
    dataFormatterProfile.setProfileName("dataformat.profile.language");

    BundlePreferenceStore store2 = mock(BundlePreferenceStore.class);
    when(store2.getString(Mockito.<String>any())).thenReturn("String");

    // Act
    dataFormatterProfile.reset(store2);

    // Assert that nothing has changed
    verify(store, atLeast(1)).getString(Mockito.<String>any());
    verify(store2, atLeast(1)).getString(Mockito.<String>any());
    Locale locale = dataFormatterProfile.getLocale();
    assertEquals("STRING", locale.getCountry());
    assertEquals("STRING", locale.getDisplayCountry());
    assertEquals("String", locale.getDisplayVariant());
    assertEquals("String", locale.getVariant());
    assertEquals("string (STRING, String)", locale.getDisplayName());
    assertEquals("string", locale.getDisplayLanguage());
    assertEquals("string", locale.getLanguage());
  }

  /**
   * Test {@link DataFormatterProfile#reset(DBPPreferenceStore)}.
   *
   * <p>Method under test: {@link DataFormatterProfile#reset(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataFormatterProfile.reset(DBPPreferenceStore)"})
  public void testReset2() throws MissingResourceException {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");

    DataFormatterProfile dataFormatterProfile = new DataFormatterProfile("foo.txt", store);
    dataFormatterProfile.setProfileName("dataformat.profile.language");

    BundlePreferenceStore store2 = mock(BundlePreferenceStore.class);
    when(store2.getString(Mockito.<String>any())).thenReturn("");

    // Act
    dataFormatterProfile.reset(store2);

    // Assert
    verify(store, atLeast(1)).getString(Mockito.<String>any());
    verify(store2, atLeast(1)).getString(Mockito.<String>any());
    Locale locale = dataFormatterProfile.getLocale();
    assertEquals("", locale.getDisplayVariant());
    assertEquals("", locale.getVariant());
    assertEquals("English (United Kingdom)", locale.getDisplayName());
    assertEquals("English", locale.getDisplayLanguage());
    assertEquals("GB", locale.getCountry());
    assertEquals("GBR", locale.getISO3Country());
    assertEquals("United Kingdom", locale.getDisplayCountry());
    assertEquals("en", locale.getLanguage());
    assertEquals("eng", locale.getISO3Language());
  }

  /**
   * Test {@link DataFormatterProfile#preferenceChange(PreferenceChangeEvent)}.
   *
   * <p>Method under test: {@link DataFormatterProfile#preferenceChange(PreferenceChangeEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataFormatterProfile.preferenceChange(PreferenceChangeEvent)"})
  public void testPreferenceChange() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    DataFormatterProfile dataFormatterProfile = new DataFormatterProfile("foo.txt", store);
    PreferenceChangeEvent event =
        new PreferenceChangeEvent(DBPEvent.RENAME, "Property", DBPEvent.RENAME, DBPEvent.RENAME);

    // Act
    dataFormatterProfile.preferenceChange(event);

    // Assert
    verify(store, atLeast(1)).getString(Mockito.<String>any());
  }

  /**
   * Test {@link DataFormatterProfile#preferenceChange(PreferenceChangeEvent)}.
   *
   * <p>Method under test: {@link DataFormatterProfile#preferenceChange(PreferenceChangeEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataFormatterProfile.preferenceChange(PreferenceChangeEvent)"})
  public void testPreferenceChange2() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    DataFormatterProfile dataFormatterProfile = new DataFormatterProfile("foo.txt", store);
    PreferenceChangeEvent event =
        new PreferenceChangeEvent(DBPEvent.RENAME, null, DBPEvent.RENAME, DBPEvent.RENAME);

    // Act
    dataFormatterProfile.preferenceChange(event);

    // Assert
    verify(store, atLeast(1)).getString(Mockito.<String>any());
  }

  /**
   * Test {@link DataFormatterProfile#preferenceChange(PreferenceChangeEvent)}.
   *
   * <ul>
   *   <li>Given {@link BundlePreferenceStore} {@link BundlePreferenceStore#getString(String)}
   *       return empty string.
   * </ul>
   *
   * <p>Method under test: {@link DataFormatterProfile#preferenceChange(PreferenceChangeEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataFormatterProfile.preferenceChange(PreferenceChangeEvent)"})
  public void testPreferenceChange_givenBundlePreferenceStoreGetStringReturnEmptyString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("");
    DataFormatterProfile dataFormatterProfile = new DataFormatterProfile("foo.txt", store);
    PreferenceChangeEvent event =
        new PreferenceChangeEvent(
            DBPEvent.RENAME,
            DataFormatterProfile.DATAFORMAT_PREFIX,
            DBPEvent.RENAME,
            DBPEvent.RENAME);

    // Act
    dataFormatterProfile.preferenceChange(event);

    // Assert
    verify(store, atLeast(1)).getString(Mockito.<String>any());
  }

  /**
   * Test {@link DataFormatterProfile#preferenceChange(PreferenceChangeEvent)}.
   *
   * <ul>
   *   <li>Given {@link BundlePreferenceStore} {@link BundlePreferenceStore#getString(String)}
   *       return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DataFormatterProfile#preferenceChange(PreferenceChangeEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataFormatterProfile.preferenceChange(PreferenceChangeEvent)"})
  public void testPreferenceChange_givenBundlePreferenceStoreGetStringReturnNull() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn(null);
    DataFormatterProfile dataFormatterProfile = new DataFormatterProfile("foo.txt", store);
    PreferenceChangeEvent event =
        new PreferenceChangeEvent(
            DBPEvent.RENAME,
            DataFormatterProfile.DATAFORMAT_PREFIX,
            DBPEvent.RENAME,
            DBPEvent.RENAME);

    // Act
    dataFormatterProfile.preferenceChange(event);

    // Assert
    verify(store, atLeast(1)).getString(Mockito.<String>any());
  }

  /**
   * Test {@link DataFormatterProfile#preferenceChange(PreferenceChangeEvent)}.
   *
   * <ul>
   *   <li>Given {@link BundlePreferenceStore} {@link BundlePreferenceStore#getString(String)}
   *       return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link DataFormatterProfile#preferenceChange(PreferenceChangeEvent)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataFormatterProfile.preferenceChange(PreferenceChangeEvent)"})
  public void testPreferenceChange_givenBundlePreferenceStoreGetStringReturnString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    DataFormatterProfile dataFormatterProfile = new DataFormatterProfile("foo.txt", store);
    PreferenceChangeEvent event =
        new PreferenceChangeEvent(
            DBPEvent.RENAME,
            DataFormatterProfile.DATAFORMAT_PREFIX,
            DBPEvent.RENAME,
            DBPEvent.RENAME);

    // Act
    dataFormatterProfile.preferenceChange(event);

    // Assert
    verify(store, atLeast(1)).getString(Mockito.<String>any());
  }
}
