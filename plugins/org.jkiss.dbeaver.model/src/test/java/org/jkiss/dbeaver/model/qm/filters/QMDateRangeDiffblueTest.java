package org.jkiss.dbeaver.model.qm.filters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMDateRangeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMDateRange#QMDateRange(LocalDateTime, LocalDateTime)}
   *   <li>{@link QMDateRange#getFrom()}
   *   <li>{@link QMDateRange#getTo()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMDateRange.<init>(LocalDateTime, LocalDateTime)",
    "LocalDateTime QMDateRange.getFrom()",
    "LocalDateTime QMDateRange.getTo()"
  })
  public void testGettersAndSetters() {
    // Arrange
    LocalDateTime from = LocalDate.of(1970, 1, 1).atStartOfDay();
    LocalDateTime resultTo = LocalDate.of(1970, 1, 1).atStartOfDay();

    // Act
    QMDateRange actualQmDateRange = new QMDateRange(from, resultTo);
    LocalDateTime actualFrom = actualQmDateRange.getFrom();

    // Assert
    assertSame(from, actualFrom);
    assertSame(resultTo, actualQmDateRange.getTo());
  }

  /**
   * Test {@link QMDateRange#QMDateRange(ZonedDateTime, ZonedDateTime)}.
   *
   * <ul>
   *   <li>Then return From toLocalTime toString is {@code 00:00}.
   * </ul>
   *
   * <p>Method under test: {@link QMDateRange#QMDateRange(ZonedDateTime, ZonedDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMDateRange.<init>(ZonedDateTime, ZonedDateTime)"})
  public void testNewQMDateRange_thenReturnFromToLocalTimeToStringIs0000() {
    // Arrange
    LocalDate ofResult = LocalDate.of(1970, 1, 1);
    ZonedDateTime from = ofResult.atStartOfDay().atZone(ZoneOffset.UTC);

    LocalDate ofResult2 = LocalDate.of(1970, 1, 1);
    ZonedDateTime resultTo = ofResult2.atStartOfDay().atZone(ZoneOffset.UTC);

    // Act
    QMDateRange actualQmDateRange = new QMDateRange(from, resultTo);

    // Assert
    LocalDateTime from2 = actualQmDateRange.getFrom();
    LocalTime toLocalTimeResult = from2.toLocalTime();
    assertEquals("00:00", toLocalTimeResult.toString());
    LocalDate toLocalDateResult = from2.toLocalDate();
    assertEquals("1970-01-01", toLocalDateResult.toString());
    LocalDateTime resultTo2 = actualQmDateRange.getTo();
    LocalDate toLocalDateResult2 = resultTo2.toLocalDate();
    assertEquals("1970-01-01", toLocalDateResult2.toString());
    assertSame(toLocalTimeResult, resultTo2.toLocalTime());
    assertSame(ofResult, toLocalDateResult);
    assertSame(ofResult2, toLocalDateResult2);
  }

  /**
   * Test {@link QMDateRange#QMDateRange(ZonedDateTime, ZonedDateTime)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return From is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link QMDateRange#QMDateRange(ZonedDateTime, ZonedDateTime)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QMDateRange.<init>(ZonedDateTime, ZonedDateTime)"})
  public void testNewQMDateRange_whenNull_thenReturnFromIsNull() {
    // Arrange and Act
    QMDateRange actualQmDateRange = new QMDateRange((ZonedDateTime) null, null);

    // Assert
    assertNull(actualQmDateRange.getFrom());
    assertNull(actualQmDateRange.getTo());
  }
}
