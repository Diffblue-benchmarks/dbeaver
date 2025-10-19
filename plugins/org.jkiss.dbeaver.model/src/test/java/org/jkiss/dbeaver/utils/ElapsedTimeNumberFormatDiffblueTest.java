package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.text.FieldPosition;
import java.text.ParsePosition;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ElapsedTimeNumberFormatDiffblueTest {
  /**
   * Test new {@link ElapsedTimeNumberFormat} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ElapsedTimeNumberFormat}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ElapsedTimeNumberFormat.<init>()"})
  public void testNewElapsedTimeNumberFormat() {
    // Arrange and Act
    ElapsedTimeNumberFormat actualElapsedTimeNumberFormat = new ElapsedTimeNumberFormat();

    // Assert
    assertEquals(0, actualElapsedTimeNumberFormat.getMinimumFractionDigits());
    assertEquals(1, actualElapsedTimeNumberFormat.getMinimumIntegerDigits());
    assertEquals(3, actualElapsedTimeNumberFormat.getMaximumFractionDigits());
    assertEquals(40, actualElapsedTimeNumberFormat.getMaximumIntegerDigits());
    assertFalse(actualElapsedTimeNumberFormat.isParseIntegerOnly());
    assertTrue(actualElapsedTimeNumberFormat.isGroupingUsed());
  }

  /**
   * Test {@link ElapsedTimeNumberFormat#format(double, StringBuffer, FieldPosition)} with {@code
   * double}, {@code StringBuffer}, {@code FieldPosition}.
   *
   * <p>Method under test: {@link ElapsedTimeNumberFormat#format(double, StringBuffer,
   * FieldPosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StringBuffer ElapsedTimeNumberFormat.format(double, StringBuffer, FieldPosition)"
  })
  public void testFormatWithDoubleStringBufferFieldPosition() {
    // Arrange
    ElapsedTimeNumberFormat elapsedTimeNumberFormat = new ElapsedTimeNumberFormat();
    StringBuffer toAppendTo = new StringBuffer("foo");

    // Act
    StringBuffer actualFormatResult =
        elapsedTimeNumberFormat.format(10.0d, toAppendTo, new FieldPosition(1));

    // Assert
    assertEquals("foo0.0s", toAppendTo.toString());
    assertSame(toAppendTo, actualFormatResult);
  }

  /**
   * Test {@link ElapsedTimeNumberFormat#format(long, StringBuffer, FieldPosition)} with {@code
   * long}, {@code StringBuffer}, {@code FieldPosition}.
   *
   * <p>Method under test: {@link ElapsedTimeNumberFormat#format(long, StringBuffer, FieldPosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StringBuffer ElapsedTimeNumberFormat.format(long, StringBuffer, FieldPosition)"
  })
  public void testFormatWithLongStringBufferFieldPosition() {
    // Arrange
    ElapsedTimeNumberFormat elapsedTimeNumberFormat = new ElapsedTimeNumberFormat();
    StringBuffer toAppendTo = new StringBuffer("foo");

    // Act
    StringBuffer actualFormatResult =
        elapsedTimeNumberFormat.format(1L, toAppendTo, new FieldPosition(1));

    // Assert
    assertEquals("foo0.0s", toAppendTo.toString());
    assertSame(toAppendTo, actualFormatResult);
  }

  /**
   * Test {@link ElapsedTimeNumberFormat#format(long, StringBuffer, FieldPosition)} with {@code
   * long}, {@code StringBuffer}, {@code FieldPosition}.
   *
   * <p>Method under test: {@link ElapsedTimeNumberFormat#format(long, StringBuffer, FieldPosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "StringBuffer ElapsedTimeNumberFormat.format(long, StringBuffer, FieldPosition)"
  })
  public void testFormatWithLongStringBufferFieldPosition2() {
    // Arrange
    ElapsedTimeNumberFormat elapsedTimeNumberFormat = new ElapsedTimeNumberFormat();
    StringBuffer toAppendTo = new StringBuffer("foo");

    // Act
    StringBuffer actualFormatResult =
        elapsedTimeNumberFormat.format(Long.MAX_VALUE, toAppendTo, new FieldPosition(1));

    // Assert
    assertEquals("foo2562047788015h 12m 55s", toAppendTo.toString());
    assertSame(toAppendTo, actualFormatResult);
  }

  /**
   * Test {@link ElapsedTimeNumberFormat#parse(String, ParsePosition)} with {@code String}, {@code
   * ParsePosition}.
   *
   * <p>Method under test: {@link ElapsedTimeNumberFormat#parse(String, ParsePosition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Number ElapsedTimeNumberFormat.parse(String, ParsePosition)"})
  public void testParseWithStringParsePosition() {
    // Arrange
    ElapsedTimeNumberFormat elapsedTimeNumberFormat = new ElapsedTimeNumberFormat();

    // Act and Assert
    assertNull(elapsedTimeNumberFormat.parse("Source", new ParsePosition(1)));
  }
}
