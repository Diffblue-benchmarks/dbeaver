package org.jkiss.dbeaver.model.data.aggregate;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.nio.charset.Charset;
import java.time.Duration;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AggregateUtilsDiffblueTest {
  /**
   * Test {@link AggregateUtils#compareValues(Comparable, Comparable)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link AggregateUtils#compareValues(Comparable, Comparable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AggregateUtils.compareValues(Comparable, Comparable)"})
  public void testCompareValues_whenA_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, AggregateUtils.compareValues(42, (byte) 'A'));
  }

  /**
   * Test {@link AggregateUtils#compareValues(Comparable, Comparable)}.
   *
   * <ul>
   *   <li>When forName {@code UTF-8}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AggregateUtils#compareValues(Comparable, Comparable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AggregateUtils.compareValues(Comparable, Comparable)"})
  public void testCompareValues_whenForNameUtf8_thenReturnZero() {
    // Arrange
    Charset val1 = Charset.forName("UTF-8");

    // Act and Assert
    assertEquals(0, AggregateUtils.compareValues(val1, Charset.forName("UTF-8")));
  }

  /**
   * Test {@link AggregateUtils#compareValues(Comparable, Comparable)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AggregateUtils#compareValues(Comparable, Comparable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AggregateUtils.compareValues(Comparable, Comparable)"})
  public void testCompareValues_whenFortyTwo_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, AggregateUtils.compareValues(42, Charset.forName("UTF-8")));
  }

  /**
   * Test {@link AggregateUtils#compareValues(Comparable, Comparable)}.
   *
   * <ul>
   *   <li>When forty-two.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AggregateUtils#compareValues(Comparable, Comparable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AggregateUtils.compareValues(Comparable, Comparable)"})
  public void testCompareValues_whenFortyTwo_thenReturnZero2() {
    // Arrange, Act and Assert
    assertEquals(0, AggregateUtils.compareValues(42, 42));
  }

  /**
   * Test {@link AggregateUtils#compareValues(Comparable, Comparable)}.
   *
   * <ul>
   *   <li>When ofSeconds one.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link AggregateUtils#compareValues(Comparable, Comparable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AggregateUtils.compareValues(Comparable, Comparable)"})
  public void testCompareValues_whenOfSecondsOne_thenReturnZero() {
    // Arrange
    Duration val1 = Duration.ofSeconds(1L);

    // Act and Assert
    assertEquals(0, AggregateUtils.compareValues(val1, Charset.forName("UTF-8")));
  }

  /**
   * Test {@link AggregateUtils#compareValues(Comparable, Comparable)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link AggregateUtils#compareValues(Comparable, Comparable)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int AggregateUtils.compareValues(Comparable, Comparable)"})
  public void testCompareValues_whenOne_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, AggregateUtils.compareValues(42, 1));
  }
}
