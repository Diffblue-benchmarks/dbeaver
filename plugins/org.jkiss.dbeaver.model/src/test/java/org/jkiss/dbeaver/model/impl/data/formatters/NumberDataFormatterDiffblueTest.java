package org.jkiss.dbeaver.model.impl.data.formatters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NumberDataFormatterDiffblueTest {
  @InjectMocks private NumberDataFormatter numberDataFormatter;

  @Mock private StringBuffer stringBuffer;

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link NumberDataFormatter}
   *   <li>{@link NumberDataFormatter#getPattern()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void NumberDataFormatter.<init>()",
    "String NumberDataFormatter.getPattern()"
  })
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertNull(new NumberDataFormatter().getPattern());
  }

  /**
   * Test {@link NumberDataFormatter#formatValue(Object)}.
   *
   * <ul>
   *   <li>Given {@link NumberDataFormatter} (default constructor).
   *   <li>When forty-two.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link NumberDataFormatter#formatValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NumberDataFormatter.formatValue(Object)"})
  public void testFormatValue_givenNumberDataFormatter_whenFortyTwo_thenReturn42() {
    // Arrange, Act and Assert
    assertEquals("42", new NumberDataFormatter().formatValue(42));
  }

  /**
   * Test {@link NumberDataFormatter#formatValue(Object)}.
   *
   * <ul>
   *   <li>Given {@link NumberDataFormatter} (default constructor).
   *   <li>When {@link Float#NaN}.
   *   <li>Then return {@code NaN}.
   * </ul>
   *
   * <p>Method under test: {@link NumberDataFormatter#formatValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NumberDataFormatter.formatValue(Object)"})
  public void testFormatValue_givenNumberDataFormatter_whenNaN_thenReturnNaN() {
    // Arrange, Act and Assert
    assertEquals("NaN", new NumberDataFormatter().formatValue(Float.NaN));
  }

  /**
   * Test {@link NumberDataFormatter#formatValue(Object)}.
   *
   * <ul>
   *   <li>Given {@link NumberDataFormatter} (default constructor).
   *   <li>When {@link Double#NaN}.
   *   <li>Then return {@code NaN}.
   * </ul>
   *
   * <p>Method under test: {@link NumberDataFormatter#formatValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NumberDataFormatter.formatValue(Object)"})
  public void testFormatValue_givenNumberDataFormatter_whenNaN_thenReturnNaN2() {
    // Arrange, Act and Assert
    assertEquals("NaN", new NumberDataFormatter().formatValue(Double.NaN));
  }

  /**
   * Test {@link NumberDataFormatter#formatValue(Object)}.
   *
   * <ul>
   *   <li>Given {@link NumberDataFormatter} (default constructor).
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link NumberDataFormatter#formatValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NumberDataFormatter.formatValue(Object)"})
  public void testFormatValue_givenNumberDataFormatter_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new NumberDataFormatter().formatValue(null));
  }

  /**
   * Test {@link NumberDataFormatter#formatValue(Object)}.
   *
   * <ul>
   *   <li>Given {@link NumberDataFormatter} (default constructor).
   *   <li>When ten.
   *   <li>Then return {@code 10.0}.
   * </ul>
   *
   * <p>Method under test: {@link NumberDataFormatter#formatValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NumberDataFormatter.formatValue(Object)"})
  public void testFormatValue_givenNumberDataFormatter_whenTen_thenReturn100() {
    // Arrange, Act and Assert
    assertEquals("10.0", new NumberDataFormatter().formatValue(10.0f));
  }

  /**
   * Test {@link NumberDataFormatter#formatValue(Object)}.
   *
   * <ul>
   *   <li>Given {@link NumberDataFormatter} (default constructor).
   *   <li>When ten.
   *   <li>Then return {@code 10.0}.
   * </ul>
   *
   * <p>Method under test: {@link NumberDataFormatter#formatValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NumberDataFormatter.formatValue(Object)"})
  public void testFormatValue_givenNumberDataFormatter_whenTen_thenReturn1002() {
    // Arrange, Act and Assert
    assertEquals("10.0", new NumberDataFormatter().formatValue(10.0d));
  }

  /**
   * Test {@link NumberDataFormatter#formatValue(Object)}.
   *
   * <ul>
   *   <li>Given {@link StringBuffer} {@link StringBuffer#setLength(int)} does nothing.
   *   <li>Then calls {@link StringBuffer#setLength(int)}.
   * </ul>
   *
   * <p>Method under test: {@link NumberDataFormatter#formatValue(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String NumberDataFormatter.formatValue(Object)"})
  public void testFormatValue_givenStringBufferSetLengthDoesNothing_thenCallsSetLength() {
    // Arrange
    doNothing().when(stringBuffer).setLength(anyInt());

    // Act
    String actualFormatValueResult = numberDataFormatter.formatValue(42);

    // Assert
    verify(stringBuffer).setLength(0);
    assertEquals("42", actualFormatValueResult);
  }
}
