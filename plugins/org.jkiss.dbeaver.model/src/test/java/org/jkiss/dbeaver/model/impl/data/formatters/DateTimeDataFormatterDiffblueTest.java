package org.jkiss.dbeaver.model.impl.data.formatters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.jkiss.dbeaver.model.impl.SimpleTypedObject;
import org.jkiss.dbeaver.model.impl.sql.AbstractSQLDialect;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.jkiss.utils.time.ExtendedDateFormat;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DateTimeDataFormatterDiffblueTest {
  /**
   * Test {@link DateTimeDataFormatter#init(DBSTypedObject, Locale, Map)}.
   *
   * <ul>
   *   <li>Given {@code f+}.
   *   <li>Then {@link DateTimeDataFormatter} (default constructor) DateFormat toPattern is {@code
   *       +}.
   * </ul>
   *
   * <p>Method under test: {@link DateTimeDataFormatter#init(DBSTypedObject, Locale, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DateTimeDataFormatter.init(DBSTypedObject, Locale, Map)"})
  public void testInit_givenF_thenDateTimeDataFormatterDateFormatToPatternIsPlusSign() {
    // Arrange
    DateTimeDataFormatter dateTimeDataFormatter = new DateTimeDataFormatter();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put(DateTimeDataFormatter.PROP_PATTERN, "f+");
    properties.put(DateTimeDataFormatter.PROP_TIMEZONE, "");

    // Act
    dateTimeDataFormatter.init(
        SimpleTypedObject.DEFAULT_TYPE, AbstractSQLDialect.DEF_LOCALE, properties);

    // Assert
    DateFormat dateFormat = dateTimeDataFormatter.getDateFormat();
    assertTrue(dateFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(dateFormat.getCalendar() instanceof GregorianCalendar);
    assertTrue(dateFormat instanceof ExtendedDateFormat);
    assertEquals("+", ((ExtendedDateFormat) dateFormat).toPattern());
    assertEquals("f+", dateTimeDataFormatter.getPattern());
    assertFalse(dateFormat.isLenient());
  }

  /**
   * Test {@link DateTimeDataFormatter#init(DBSTypedObject, Locale, Map)}.
   *
   * <ul>
   *   <li>Given {@code f}.
   *   <li>When {@link HashMap#HashMap()} {@link DateTimeDataFormatter#PROP_PATTERN} is {@code f}.
   *   <li>Then {@link DateTimeDataFormatter} (default constructor) Pattern is {@code f}.
   * </ul>
   *
   * <p>Method under test: {@link DateTimeDataFormatter#init(DBSTypedObject, Locale, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DateTimeDataFormatter.init(DBSTypedObject, Locale, Map)"})
  public void testInit_givenF_whenHashMapProp_patternIsF_thenDateTimeDataFormatterPatternIsF() {
    // Arrange
    DateTimeDataFormatter dateTimeDataFormatter = new DateTimeDataFormatter();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put(DateTimeDataFormatter.PROP_PATTERN, "f");
    properties.put(DateTimeDataFormatter.PROP_TIMEZONE, "");

    // Act
    dateTimeDataFormatter.init(
        SimpleTypedObject.DEFAULT_TYPE, AbstractSQLDialect.DEF_LOCALE, properties);

    // Assert
    DateFormat dateFormat = dateTimeDataFormatter.getDateFormat();
    assertTrue(dateFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(dateFormat.getCalendar() instanceof GregorianCalendar);
    assertTrue(dateFormat instanceof ExtendedDateFormat);
    assertEquals("", ((ExtendedDateFormat) dateFormat).toPattern());
    assertEquals("f", dateTimeDataFormatter.getPattern());
    assertFalse(dateFormat.isLenient());
  }

  /**
   * Test {@link DateTimeDataFormatter#init(DBSTypedObject, Locale, Map)}.
   *
   * <ul>
   *   <li>Given minus one.
   *   <li>Then {@link DateTimeDataFormatter} (default constructor) Zone toString is {@code -01:00}.
   * </ul>
   *
   * <p>Method under test: {@link DateTimeDataFormatter#init(DBSTypedObject, Locale, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DateTimeDataFormatter.init(DBSTypedObject, Locale, Map)"})
  public void testInit_givenMinusOne_thenDateTimeDataFormatterZoneToStringIs0100() {
    // Arrange
    DateTimeDataFormatter dateTimeDataFormatter = new DateTimeDataFormatter();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put(DateTimeDataFormatter.PROP_PATTERN, null);
    properties.put(DateTimeDataFormatter.PROP_TIMEZONE, -1);

    // Act
    dateTimeDataFormatter.init(
        SimpleTypedObject.DEFAULT_TYPE, AbstractSQLDialect.DEF_LOCALE, properties);

    // Assert
    DateFormat dateFormat = dateTimeDataFormatter.getDateFormat();
    assertTrue(dateFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(dateFormat.getCalendar() instanceof GregorianCalendar);
    assertTrue(dateFormat instanceof ExtendedDateFormat);
    assertEquals("", ((ExtendedDateFormat) dateFormat).toPattern());
    assertEquals("", dateTimeDataFormatter.getPattern());
    assertEquals("-01:00", dateTimeDataFormatter.getZone().toString());
    assertFalse(dateFormat.isLenient());
  }

  /**
   * Test {@link DateTimeDataFormatter#init(DBSTypedObject, Locale, Map)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>Then {@link DateTimeDataFormatter} (default constructor) DateFormat toPattern is {@code
   *       1}.
   * </ul>
   *
   * <p>Method under test: {@link DateTimeDataFormatter#init(DBSTypedObject, Locale, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DateTimeDataFormatter.init(DBSTypedObject, Locale, Map)"})
  public void testInit_givenOne_thenDateTimeDataFormatterDateFormatToPatternIs1() {
    // Arrange
    DateTimeDataFormatter dateTimeDataFormatter = new DateTimeDataFormatter();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put(DateTimeDataFormatter.PROP_PATTERN, 1);
    properties.put(DateTimeDataFormatter.PROP_TIMEZONE, "");

    // Act
    dateTimeDataFormatter.init(
        SimpleTypedObject.DEFAULT_TYPE, AbstractSQLDialect.DEF_LOCALE, properties);

    // Assert
    DateFormat dateFormat = dateTimeDataFormatter.getDateFormat();
    assertTrue(dateFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(dateFormat.getCalendar() instanceof GregorianCalendar);
    assertTrue(dateFormat instanceof ExtendedDateFormat);
    assertEquals("1", ((ExtendedDateFormat) dateFormat).toPattern());
    assertEquals("1", dateTimeDataFormatter.getPattern());
    assertFalse(dateFormat.isLenient());
  }

  /**
   * Test {@link DateTimeDataFormatter#init(DBSTypedObject, Locale, Map)}.
   *
   * <ul>
   *   <li>Then {@link DateTimeDataFormatter} (default constructor) Pattern is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DateTimeDataFormatter#init(DBSTypedObject, Locale, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DateTimeDataFormatter.init(DBSTypedObject, Locale, Map)"})
  public void testInit_thenDateTimeDataFormatterPatternIsEmptyString() {
    // Arrange
    DateTimeDataFormatter dateTimeDataFormatter = new DateTimeDataFormatter();

    HashMap<String, Object> properties = new HashMap<>();
    properties.put(DateTimeDataFormatter.PROP_PATTERN, null);
    properties.put(DateTimeDataFormatter.PROP_TIMEZONE, "");

    // Act
    dateTimeDataFormatter.init(
        SimpleTypedObject.DEFAULT_TYPE, AbstractSQLDialect.DEF_LOCALE, properties);

    // Assert
    DateFormat dateFormat = dateTimeDataFormatter.getDateFormat();
    assertTrue(dateFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(dateFormat.getCalendar() instanceof GregorianCalendar);
    assertTrue(dateFormat instanceof ExtendedDateFormat);
    assertEquals("", ((ExtendedDateFormat) dateFormat).toPattern());
    assertEquals("", dateTimeDataFormatter.getPattern());
    assertFalse(dateFormat.isLenient());
  }

  /**
   * Test {@link DateTimeDataFormatter#init(DBSTypedObject, Locale, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then {@link DateTimeDataFormatter} (default constructor) Pattern is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DateTimeDataFormatter#init(DBSTypedObject, Locale, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DateTimeDataFormatter.init(DBSTypedObject, Locale, Map)"})
  public void testInit_whenHashMap_thenDateTimeDataFormatterPatternIsEmptyString() {
    // Arrange
    DateTimeDataFormatter dateTimeDataFormatter = new DateTimeDataFormatter();

    // Act
    dateTimeDataFormatter.init(
        SimpleTypedObject.DEFAULT_TYPE, AbstractSQLDialect.DEF_LOCALE, new HashMap<>());

    // Assert
    DateFormat dateFormat = dateTimeDataFormatter.getDateFormat();
    assertTrue(dateFormat.getNumberFormat() instanceof DecimalFormat);
    assertTrue(dateFormat.getCalendar() instanceof GregorianCalendar);
    assertTrue(dateFormat instanceof ExtendedDateFormat);
    assertEquals("", ((ExtendedDateFormat) dateFormat).toPattern());
    assertEquals("", dateTimeDataFormatter.getPattern());
    assertFalse(dateFormat.isLenient());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DateTimeDataFormatter}
   *   <li>{@link DateTimeDataFormatter#getDateFormat()}
   *   <li>{@link DateTimeDataFormatter#getPattern()}
   *   <li>{@link DateTimeDataFormatter#getZone()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DateTimeDataFormatter.<init>()",
    "DateFormat DateTimeDataFormatter.getDateFormat()",
    "String DateTimeDataFormatter.getPattern()",
    "java.time.ZoneId DateTimeDataFormatter.getZone()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DateTimeDataFormatter actualDateTimeDataFormatter = new DateTimeDataFormatter();
    DateFormat actualDateFormat = actualDateTimeDataFormatter.getDateFormat();
    String actualPattern = actualDateTimeDataFormatter.getPattern();

    // Assert
    assertNull(actualPattern);
    assertNull(actualDateFormat);
    assertNull(actualDateTimeDataFormatter.getZone());
  }
}
