package org.jkiss.dbeaver.model.impl.jdbc.data.handlers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class JDBCDateTimeValueHandlerDiffblueTest {
  /**
   * Test {@link JDBCDateTimeValueHandler#getTimeValue(Object)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDateTimeValueHandler#getTimeValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCDateTimeValueHandler.getTimeValue(Object)"})
  public void testGetTimeValue_whenLocalDateWith1970AndOneAndOne() {
    // Arrange and Act
    Time actualTimeValue = JDBCDateTimeValueHandler.getTimeValue(LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals("1970-01-01", new SimpleDateFormat("yyyy-MM-dd").format(actualTimeValue));
  }

  /**
   * Test {@link JDBCDateTimeValueHandler#getTimeValue(Object)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one atStartOfDay.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDateTimeValueHandler#getTimeValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCDateTimeValueHandler.getTimeValue(Object)"})
  public void testGetTimeValue_whenLocalDateWith1970AndOneAndOneAtStartOfDay() {
    // Arrange and Act
    Time actualTimeValue =
        JDBCDateTimeValueHandler.getTimeValue(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    assertEquals("1970-01-01", new SimpleDateFormat("yyyy-MM-dd").format(actualTimeValue));
  }

  /**
   * Test {@link JDBCDateTimeValueHandler#getTimeValue(Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDateTimeValueHandler#getTimeValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Time JDBCDateTimeValueHandler.getTimeValue(Object)"})
  public void testGetTimeValue_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(JDBCDateTimeValueHandler.getTimeValue(null));
  }

  /**
   * Test {@link JDBCDateTimeValueHandler#getDateValue(Object)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDateTimeValueHandler#getDateValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCDateTimeValueHandler.getDateValue(Object)"})
  public void testGetDateValue_whenLocalDateWith1970AndOneAndOne() {
    // Arrange and Act
    Date actualDateValue = JDBCDateTimeValueHandler.getDateValue(LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals("1970-01-01", new SimpleDateFormat("yyyy-MM-dd").format(actualDateValue));
  }

  /**
   * Test {@link JDBCDateTimeValueHandler#getDateValue(Object)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one atStartOfDay.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDateTimeValueHandler#getDateValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCDateTimeValueHandler.getDateValue(Object)"})
  public void testGetDateValue_whenLocalDateWith1970AndOneAndOneAtStartOfDay() {
    // Arrange and Act
    Date actualDateValue =
        JDBCDateTimeValueHandler.getDateValue(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    assertEquals("1970-01-01", new SimpleDateFormat("yyyy-MM-dd").format(actualDateValue));
  }

  /**
   * Test {@link JDBCDateTimeValueHandler#getDateValue(Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDateTimeValueHandler#getDateValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Date JDBCDateTimeValueHandler.getDateValue(Object)"})
  public void testGetDateValue_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(JDBCDateTimeValueHandler.getDateValue(null));
  }

  /**
   * Test {@link JDBCDateTimeValueHandler#getTimestampValue(Object)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDateTimeValueHandler#getTimestampValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCDateTimeValueHandler.getTimestampValue(Object)"})
  public void testGetTimestampValue_whenLocalDateWith1970AndOneAndOne() {
    // Arrange and Act
    Timestamp actualTimestampValue =
        JDBCDateTimeValueHandler.getTimestampValue(LocalDate.of(1970, 1, 1));

    // Assert
    assertEquals("1970-01-01", new SimpleDateFormat("yyyy-MM-dd").format(actualTimestampValue));
  }

  /**
   * Test {@link JDBCDateTimeValueHandler#getTimestampValue(Object)}.
   *
   * <ul>
   *   <li>When {@link LocalDate} with {@code 1970} and one and one atStartOfDay.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDateTimeValueHandler#getTimestampValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCDateTimeValueHandler.getTimestampValue(Object)"})
  public void testGetTimestampValue_whenLocalDateWith1970AndOneAndOneAtStartOfDay() {
    // Arrange and Act
    Timestamp actualTimestampValue =
        JDBCDateTimeValueHandler.getTimestampValue(LocalDate.of(1970, 1, 1).atStartOfDay());

    // Assert
    assertEquals("1970-01-01", new SimpleDateFormat("yyyy-MM-dd").format(actualTimestampValue));
  }

  /**
   * Test {@link JDBCDateTimeValueHandler#getTimestampValue(Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDateTimeValueHandler#getTimestampValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCDateTimeValueHandler.getTimestampValue(Object)"})
  public void testGetTimestampValue_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(JDBCDateTimeValueHandler.getTimestampValue(null));
  }

  /**
   * Test {@link JDBCDateTimeValueHandler#getTimestampValue(Object)}.
   *
   * <ul>
   *   <li>When {@link OffsetDateTime} with {@link LocalDate} and {@link LocalTime#MIDNIGHT} and
   *       {@link ZoneOffset#UTC}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDateTimeValueHandler#getTimestampValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Timestamp JDBCDateTimeValueHandler.getTimestampValue(Object)"})
  public void testGetTimestampValue_whenOffsetDateTimeWithLocalDateAndMidnightAndUtc() {
    // Arrange
    OffsetDateTime ofResult =
        OffsetDateTime.of(LocalDate.of(1970, 1, 1), LocalTime.MIDNIGHT, ZoneOffset.UTC);

    // Act
    Timestamp actualTimestampValue = JDBCDateTimeValueHandler.getTimestampValue(ofResult);

    // Assert
    assertEquals("1970-01-01", new SimpleDateFormat("yyyy-MM-dd").format(actualTimestampValue));
  }

  /**
   * Test {@link JDBCDateTimeValueHandler#getTwoDigitValue(int)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDateTimeValueHandler#getTwoDigitValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JDBCDateTimeValueHandler.getTwoDigitValue(int)"})
  public void testGetTwoDigitValue_whenFortyTwo_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals("42", JDBCDateTimeValueHandler.getTwoDigitValue(42));
  }

  /**
   * Test {@link JDBCDateTimeValueHandler#getTwoDigitValue(int)}.
   *
   * <ul>
   *   <li>When nine.
   *   <li>Then return {@code 09}.
   * </ul>
   *
   * <p>Method under test: {@link JDBCDateTimeValueHandler#getTwoDigitValue(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String JDBCDateTimeValueHandler.getTwoDigitValue(int)"})
  public void testGetTwoDigitValue_whenNine_thenReturn09() {
    // Arrange, Act and Assert
    assertEquals("09", JDBCDateTimeValueHandler.getTwoDigitValue(9));
  }
}
