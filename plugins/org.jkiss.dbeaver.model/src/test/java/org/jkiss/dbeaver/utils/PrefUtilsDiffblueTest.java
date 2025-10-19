package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.preferences.BundlePreferenceStore;
import org.jkiss.dbeaver.model.preferences.DBPPreferenceStore;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PrefUtilsDiffblueTest {
  /**
   * Test {@link PrefUtils#savePreferenceStore(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>Given {@link IOException#IOException()}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#savePreferenceStore(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.savePreferenceStore(DBPPreferenceStore)"})
  public void testSavePreferenceStore_givenIOException() throws IOException {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doThrow(new IOException()).when(store).save();

    // Act
    PrefUtils.savePreferenceStore(store);

    // Assert
    verify(store).save();
  }

  /**
   * Test {@link PrefUtils#savePreferenceStore(DBPPreferenceStore)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#save()} does nothing.
   *   <li>Then calls {@link BundlePreferenceStore#save()}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#savePreferenceStore(DBPPreferenceStore)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.savePreferenceStore(DBPPreferenceStore)"})
  public void testSavePreferenceStore_whenBundlePreferenceStoreSaveDoesNothing_thenCallsSave()
      throws IOException {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).save();

    // Act
    PrefUtils.savePreferenceStore(store);

    // Assert
    verify(store).save();
  }

  /**
   * Test {@link PrefUtils#setDefaultPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>Given {@code Default String}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setDefaultPreferenceValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setDefaultPreferenceValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetDefaultPreferenceValue_givenDefaultString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getDefaultString(Mockito.<String>any())).thenReturn("Default String");

    // Act
    PrefUtils.setDefaultPreferenceValue(store, "Name", DBPEvent.RENAME);

    // Assert
    verify(store).getDefaultString("Name");
  }

  /**
   * Test {@link PrefUtils#setDefaultPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setDefaultPreferenceValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setDefaultPreferenceValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetDefaultPreferenceValue_givenEmptyString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getDefaultString(Mockito.<String>any())).thenReturn("");
    doNothing().when(store).setDefault(Mockito.<String>any(), Mockito.<String>any());

    // Act
    PrefUtils.setDefaultPreferenceValue(store, "Name", DBPEvent.RENAME);

    // Assert
    verify(store).getDefaultString("Name");
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@code Byte}.
   *   <li>Then return intValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_given42_whenJavaLangByte_thenReturnIntValueIsFortyTwo() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("42");
    Class<Byte> valueType = Byte.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertEquals(42, ((Integer) actualPreferenceValue).intValue());
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@code Long}.
   *   <li>Then return longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_given42_whenJavaLangLong_thenReturnLongValueIsFortyTwo() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("42");
    Class<Long> valueType = Long.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertEquals(42L, ((Long) actualPreferenceValue).longValue());
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@code BigDecimal}.
   *   <li>Then calls {@link BundlePreferenceStore#getString(String)}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_given42_whenJavaMathBigDecimal_thenCallsGetString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("42");
    Class<BigDecimal> valueType = BigDecimal.class;

    // Act
    PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@code BigInteger}.
   *   <li>Then return {@link BigInteger}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_given42_whenJavaMathBigInteger_thenReturnBigInteger() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("42");
    Class<BigInteger> valueType = BigInteger.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertTrue(actualPreferenceValue instanceof BigInteger);
    assertEquals("42", actualPreferenceValue.toString());
    assertEquals(1, ((BigInteger) actualPreferenceValue).getLowestSetBit());
    assertEquals(1, ((BigInteger) actualPreferenceValue).signum());
    assertArrayEquals(new byte[] {'*'}, ((BigInteger) actualPreferenceValue).toByteArray());
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@code CharSequence}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenEmptyString_whenJavaLangCharSequence_thenReturnNull() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("");
    Class<CharSequence> valueType = CharSequence.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertNull(actualPreferenceValue);
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>When {@code BigDecimal}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenEmptyString_whenJavaMathBigDecimal_thenReturnNull() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("");
    Class<BigDecimal> valueType = BigDecimal.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store, atLeast(1)).getString("Prop Name");
    assertNull(actualPreferenceValue);
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#getString(String)} return
   *       {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenNull_whenBundlePreferenceStoreGetStringReturnNull() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn(null);
    Class<Object> valueType = Object.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertNull(actualPreferenceValue);
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@link RuntimeException#RuntimeException()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenRuntimeException_thenThrowRuntimeException() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenThrow(new RuntimeException());
    Class<Object> valueType = Object.class;

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> PrefUtils.getPreferenceValue(store, "Prop Name", valueType));
    verify(store, atLeast(1)).getString("Prop Name");
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@code Boolean}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenString_whenJavaLangBoolean_thenReturnFalse() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    Class<Boolean> valueType = Boolean.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertFalse((Boolean) actualPreferenceValue);
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@code Byte}.
   *   <li>Then return intValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenString_whenJavaLangByte_thenReturnIntValueIsZero() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    Class<Byte> valueType = Byte.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertEquals(0, ((Integer) actualPreferenceValue).intValue());
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@code CharSequence}.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenString_whenJavaLangCharSequence_thenReturnString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    Class<CharSequence> valueType = CharSequence.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertEquals("String", actualPreferenceValue);
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@code Double}.
   *   <li>Then return doubleValue is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenString_whenJavaLangDouble_thenReturnDoubleValueIsNaN() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    Class<Double> valueType = Double.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertEquals(Double.NaN, ((Double) actualPreferenceValue).doubleValue(), 0.0);
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@code Float}.
   *   <li>Then return floatValue is {@link Float#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenString_whenJavaLangFloat_thenReturnFloatValueIsNaN() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    Class<Float> valueType = Float.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertEquals(Float.NaN, ((Float) actualPreferenceValue).floatValue(), 0.0f);
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@code Integer}.
   *   <li>Then return intValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenString_whenJavaLangInteger_thenReturnIntValueIsZero() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    Class<Integer> valueType = Integer.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertEquals(0, ((Integer) actualPreferenceValue).intValue());
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@code Long}.
   *   <li>Then return longValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenString_whenJavaLangLong_thenReturnLongValueIsZero() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    Class<Long> valueType = Long.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertEquals(0L, ((Long) actualPreferenceValue).longValue());
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@code Object}.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenString_whenJavaLangObject_thenReturnString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    Class<Object> valueType = Object.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store, atLeast(1)).getString("Prop Name");
    assertEquals("String", actualPreferenceValue);
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@code Short}.
   *   <li>Then return intValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenString_whenJavaLangShort_thenReturnIntValueIsZero() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    Class<Short> valueType = Short.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertEquals(0, ((Integer) actualPreferenceValue).intValue());
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@code BigDecimal}.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenString_whenJavaMathBigDecimal_thenReturnString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    Class<BigDecimal> valueType = BigDecimal.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store, atLeast(1)).getString("Prop Name");
    assertEquals("String", actualPreferenceValue);
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Given {@code String}.
   *   <li>When {@code BigInteger}.
   *   <li>Then return {@code String}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_givenString_whenJavaMathBigInteger_thenReturnString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("String");
    Class<BigInteger> valueType = BigInteger.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store, atLeast(1)).getString("Prop Name");
    assertEquals("String", actualPreferenceValue);
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>Then calls {@link DBPPreferenceStore#getString(String)}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_thenCallsGetString() {
    // Arrange
    DBPPreferenceStore store = mock(DBPPreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("");

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", null);

    // Assert
    verify(store).getString("Prop Name");
    assertNull(actualPreferenceValue);
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#getString(String)} return
   *       empty string.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_whenBundlePreferenceStoreGetStringReturnEmptyString() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("");
    Class<Object> valueType = Object.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store, atLeast(1)).getString("Prop Name");
    assertNull(actualPreferenceValue);
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>When {@code Byte}.
   *   <li>Then return intValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_whenJavaLangByte_thenReturnIntValueIsZero() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("");
    Class<Byte> valueType = Byte.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertEquals(0, ((Integer) actualPreferenceValue).intValue());
  }

  /**
   * Test {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}.
   *
   * <ul>
   *   <li>When {@code Double}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#getPreferenceValue(DBPPreferenceStore, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PrefUtils.getPreferenceValue(DBPPreferenceStore, String, Class)"})
  public void testGetPreferenceValue_whenJavaLangDouble_thenReturnDoubleValueIsFortyTwo() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    when(store.getString(Mockito.<String>any())).thenReturn("42");
    Class<Double> valueType = Double.class;

    // Act
    Object actualPreferenceValue = PrefUtils.getPreferenceValue(store, "Prop Name", valueType);

    // Assert
    verify(store).getString("Prop Name");
    assertEquals(42.0d, ((Double) actualPreferenceValue).doubleValue(), 0.0);
  }

  /**
   * Test {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then calls {@link BundlePreferenceStore#setValue(String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.setPreferenceValue(DBPPreferenceStore, String, Object)"})
  public void testSetPreferenceValue_when42_thenCallsSetValue() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setValue(Mockito.<String>any(), Mockito.<String>any());

    // Act
    PrefUtils.setPreferenceValue(store, "Prop Name", "42");

    // Assert
    verify(store).setValue("Prop Name", "42");
  }

  /**
   * Test {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then calls {@link BundlePreferenceStore#setValue(String, int)}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.setPreferenceValue(DBPPreferenceStore, String, Object)"})
  public void testSetPreferenceValue_whenA_thenCallsSetValue() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setValue(Mockito.<String>any(), anyInt());

    // Act
    PrefUtils.setPreferenceValue(store, "Prop Name", (byte) 'A');

    // Assert
    verify(store).setValue("Prop Name", 65);
  }

  /**
   * Test {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#setValue(String,
   *       boolean)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.setPreferenceValue(DBPPreferenceStore, String, Object)"})
  public void testSetPreferenceValue_whenBundlePreferenceStoreSetValueDoesNothing() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setValue(Mockito.<String>any(), anyBoolean());

    // Act
    PrefUtils.setPreferenceValue(store, "Prop Name", true);

    // Assert
    verify(store).setValue("Prop Name", true);
  }

  /**
   * Test {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#setValue(String, int)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.setPreferenceValue(DBPPreferenceStore, String, Object)"})
  public void testSetPreferenceValue_whenBundlePreferenceStoreSetValueDoesNothing2() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setValue(Mockito.<String>any(), anyInt());

    // Act
    PrefUtils.setPreferenceValue(store, "Prop Name", 42);

    // Assert
    verify(store).setValue("Prop Name", 42);
  }

  /**
   * Test {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#setValue(String, long)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.setPreferenceValue(DBPPreferenceStore, String, Object)"})
  public void testSetPreferenceValue_whenBundlePreferenceStoreSetValueDoesNothing3() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setValue(Mockito.<String>any(), anyLong());

    // Act
    PrefUtils.setPreferenceValue(store, "Prop Name", 42L);

    // Assert
    verify(store).setValue("Prop Name", 42L);
  }

  /**
   * Test {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#setValue(String, double)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.setPreferenceValue(DBPPreferenceStore, String, Object)"})
  public void testSetPreferenceValue_whenBundlePreferenceStoreSetValueDoesNothing4() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setValue(Mockito.<String>any(), anyDouble());

    // Act
    PrefUtils.setPreferenceValue(store, "Prop Name", 10.0d);

    // Assert
    verify(store).setValue("Prop Name", 10.0d);
  }

  /**
   * Test {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#setValue(String,
   *       boolean)} throw {@link RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.setPreferenceValue(DBPPreferenceStore, String, Object)"})
  public void testSetPreferenceValue_whenBundlePreferenceStoreSetValueThrowRuntimeException() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doThrow(new RuntimeException()).when(store).setValue(Mockito.<String>any(), anyBoolean());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> PrefUtils.setPreferenceValue(store, "Prop Name", true));
    verify(store).setValue("Prop Name", true);
  }

  /**
   * Test {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#setValue(String, int)}
   *       throw {@link RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.setPreferenceValue(DBPPreferenceStore, String, Object)"})
  public void testSetPreferenceValue_whenBundlePreferenceStoreSetValueThrowRuntimeException2() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doThrow(new RuntimeException()).when(store).setValue(Mockito.<String>any(), anyInt());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> PrefUtils.setPreferenceValue(store, "Prop Name", 42));
    verify(store).setValue("Prop Name", 42);
  }

  /**
   * Test {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#setValue(String, long)}
   *       throw {@link RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.setPreferenceValue(DBPPreferenceStore, String, Object)"})
  public void testSetPreferenceValue_whenBundlePreferenceStoreSetValueThrowRuntimeException3() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doThrow(new RuntimeException()).when(store).setValue(Mockito.<String>any(), anyLong());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> PrefUtils.setPreferenceValue(store, "Prop Name", 42L));
    verify(store).setValue("Prop Name", 42L);
  }

  /**
   * Test {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#setValue(String, double)}
   *       throw {@link RuntimeException#RuntimeException()}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.setPreferenceValue(DBPPreferenceStore, String, Object)"})
  public void testSetPreferenceValue_whenBundlePreferenceStoreSetValueThrowRuntimeException4() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doThrow(new RuntimeException()).when(store).setValue(Mockito.<String>any(), anyDouble());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> PrefUtils.setPreferenceValue(store, "Prop Name", 10.0d));
    verify(store).setValue("Prop Name", 10.0d);
  }

  /**
   * Test {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.setPreferenceValue(DBPPreferenceStore, String, Object)"})
  public void testSetPreferenceValue_whenBundlePreferenceStore_thenDoesNotThrow() {
    // Arrange, Act and Assert
    PrefUtils.setPreferenceValue(mock(BundlePreferenceStore.class), "Prop Name", null);
  }

  /**
   * Test {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then calls {@link BundlePreferenceStore#setValue(String, int)}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceValue(DBPPreferenceStore, String, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PrefUtils.setPreferenceValue(DBPPreferenceStore, String, Object)"})
  public void testSetPreferenceValue_whenOne_thenCallsSetValue() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setValue(Mockito.<String>any(), anyInt());

    // Act
    PrefUtils.setPreferenceValue(store, "Prop Name", (short) 1);

    // Assert
    verify(store).setValue("Prop Name", 1);
  }

  /**
   * Test {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String, Object)}.
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setPreferenceDefaultValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetPreferenceDefaultValue() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doThrow(new RuntimeException()).when(store).setDefault(Mockito.<String>any(), anyBoolean());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> PrefUtils.setPreferenceDefaultValue(store, "Prop Name", true));
    verify(store).setDefault("Prop Name", true);
  }

  /**
   * Test {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String, Object)}.
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setPreferenceDefaultValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetPreferenceDefaultValue2() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doThrow(new RuntimeException()).when(store).setDefault(Mockito.<String>any(), anyInt());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> PrefUtils.setPreferenceDefaultValue(store, "Prop Name", 42));
    verify(store).setDefault("Prop Name", 42);
  }

  /**
   * Test {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String, Object)}.
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setPreferenceDefaultValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetPreferenceDefaultValue3() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doThrow(new RuntimeException()).when(store).setDefault(Mockito.<String>any(), anyLong());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> PrefUtils.setPreferenceDefaultValue(store, "Prop Name", 42L));
    verify(store).setDefault("Prop Name", 42L);
  }

  /**
   * Test {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String, Object)}.
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setPreferenceDefaultValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetPreferenceDefaultValue4() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doThrow(new RuntimeException()).when(store).setDefault(Mockito.<String>any(), anyDouble());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> PrefUtils.setPreferenceDefaultValue(store, "Prop Name", 10.0d));
    verify(store).setDefault("Prop Name", 10.0d);
  }

  /**
   * Test {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then calls {@link BundlePreferenceStore#setDefault(String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setPreferenceDefaultValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetPreferenceDefaultValue_when42_thenCallsSetDefault() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setDefault(Mockito.<String>any(), Mockito.<String>any());

    // Act
    PrefUtils.setPreferenceDefaultValue(store, "Prop Name", "42");

    // Assert
    verify(store).setDefault("Prop Name", "42");
  }

  /**
   * Test {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then calls {@link BundlePreferenceStore#setDefault(String, int)}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setPreferenceDefaultValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetPreferenceDefaultValue_whenA_thenCallsSetDefault() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setDefault(Mockito.<String>any(), anyInt());

    // Act
    PrefUtils.setPreferenceDefaultValue(store, "Prop Name", (byte) 'A');

    // Assert
    verify(store).setDefault("Prop Name", 65);
  }

  /**
   * Test {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#setDefault(String,
   *       boolean)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setPreferenceDefaultValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetPreferenceDefaultValue_whenBundlePreferenceStoreSetDefaultDoesNothing() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setDefault(Mockito.<String>any(), anyBoolean());

    // Act
    PrefUtils.setPreferenceDefaultValue(store, "Prop Name", true);

    // Assert
    verify(store).setDefault("Prop Name", true);
  }

  /**
   * Test {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#setDefault(String, int)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setPreferenceDefaultValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetPreferenceDefaultValue_whenBundlePreferenceStoreSetDefaultDoesNothing2() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setDefault(Mockito.<String>any(), anyInt());

    // Act
    PrefUtils.setPreferenceDefaultValue(store, "Prop Name", 42);

    // Assert
    verify(store).setDefault("Prop Name", 42);
  }

  /**
   * Test {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#setDefault(String, long)}
   *       does nothing.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setPreferenceDefaultValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetPreferenceDefaultValue_whenBundlePreferenceStoreSetDefaultDoesNothing3() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setDefault(Mockito.<String>any(), anyLong());

    // Act
    PrefUtils.setPreferenceDefaultValue(store, "Prop Name", 42L);

    // Assert
    verify(store).setDefault("Prop Name", 42L);
  }

  /**
   * Test {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore} {@link BundlePreferenceStore#setDefault(String,
   *       double)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setPreferenceDefaultValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetPreferenceDefaultValue_whenBundlePreferenceStoreSetDefaultDoesNothing4() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setDefault(Mockito.<String>any(), anyDouble());

    // Act
    PrefUtils.setPreferenceDefaultValue(store, "Prop Name", 10.0d);

    // Assert
    verify(store).setDefault("Prop Name", 10.0d);
  }

  /**
   * Test {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When {@link BundlePreferenceStore}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setPreferenceDefaultValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetPreferenceDefaultValue_whenBundlePreferenceStore_thenDoesNotThrow() {
    // Arrange, Act and Assert
    PrefUtils.setPreferenceDefaultValue(mock(BundlePreferenceStore.class), "Prop Name", null);
  }

  /**
   * Test {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String, Object)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then calls {@link BundlePreferenceStore#setDefault(String, int)}.
   * </ul>
   *
   * <p>Method under test: {@link PrefUtils#setPreferenceDefaultValue(DBPPreferenceStore, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PrefUtils.setPreferenceDefaultValue(DBPPreferenceStore, String, Object)"
  })
  public void testSetPreferenceDefaultValue_whenOne_thenCallsSetDefault() {
    // Arrange
    BundlePreferenceStore store = mock(BundlePreferenceStore.class);
    doNothing().when(store).setDefault(Mockito.<String>any(), anyInt());

    // Act
    PrefUtils.setPreferenceDefaultValue(store, "Prop Name", (short) 1);

    // Assert
    verify(store).setDefault("Prop Name", 1);
  }
}
