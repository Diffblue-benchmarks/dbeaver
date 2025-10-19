package org.jkiss.dbeaver.model.impl.data.formatters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Locale;
import java.util.Map;
import org.jkiss.dbeaver.model.impl.sql.AbstractSQLDialect;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class NumberFormatSampleDiffblueTest {
  /**
   * Test {@link NumberFormatSample#getDefaultProperties(Locale)}.
   *
   * <ul>
   *   <li>When {@link AbstractSQLDialect#DEF_LOCALE}.
   *   <li>Then return size is eight.
   * </ul>
   *
   * <p>Method under test: {@link NumberFormatSample#getDefaultProperties(Locale)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map NumberFormatSample.getDefaultProperties(Locale)"})
  public void testGetDefaultProperties_whenDef_locale_thenReturnSizeIsEight() {
    // Arrange and Act
    Map<String, Object> actualDefaultProperties =
        new NumberFormatSample().getDefaultProperties(AbstractSQLDialect.DEF_LOCALE);

    // Assert
    assertEquals(8, actualDefaultProperties.size());
    assertEquals(
        0,
        ((Integer) actualDefaultProperties.get(NumberFormatSample.PROP_MIN_FRACT_DIGITS))
            .intValue());
    assertEquals(
        1,
        ((Integer) actualDefaultProperties.get(NumberFormatSample.PROP_MIN_INT_DIGITS)).intValue());
    assertEquals(
        10,
        ((Integer) actualDefaultProperties.get(NumberFormatSample.PROP_MAX_FRACT_DIGITS))
            .intValue());
    assertEquals(
        3,
        ((Integer) actualDefaultProperties.get(NumberFormatSample.PROP_GROUPING_SIZE)).intValue());
    assertFalse(
        (Boolean) actualDefaultProperties.get(NumberFormatSample.PROP_NATIVE_SPECIAL_VALUES));
    assertFalse((Boolean) actualDefaultProperties.get(NumberFormatSample.PROP_USE_TYPE_SCALE));
    assertTrue((Boolean) actualDefaultProperties.get(NumberFormatSample.PROP_USE_GROUPING));
    assertEquals(
        Integer.MAX_VALUE,
        ((Integer) actualDefaultProperties.get(NumberFormatSample.PROP_MAX_INT_DIGITS)).intValue());
  }

  /**
   * Test {@link NumberFormatSample#getSampleValue()}.
   *
   * <p>Method under test: {@link NumberFormatSample#getSampleValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object NumberFormatSample.getSampleValue()"})
  public void testGetSampleValue() {
    // Arrange, Act and Assert
    assertEquals(
        1.234567890012345E9d,
        ((Double) new NumberFormatSample().getSampleValue()).doubleValue(),
        0.0);
  }

  /**
   * Test new {@link NumberFormatSample} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link NumberFormatSample}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NumberFormatSample.<init>()"})
  public void testNewNumberFormatSample() {
    // Arrange, Act and Assert
    assertEquals(
        1.234567890012345E9d,
        ((Double) new NumberFormatSample().getSampleValue()).doubleValue(),
        0.0);
  }
}
