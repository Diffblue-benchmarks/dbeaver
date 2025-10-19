package org.jkiss.dbeaver.tools.transfer.stream.exporter;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QuoteStrategyDiffblueTest {
  /**
   * Test {@link QuoteStrategy#value()}.
   *
   * <p>Method under test: {@link QuoteStrategy#value()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String QuoteStrategy.value()"})
  public void testValue() {
    // Arrange, Act and Assert
    assertEquals("disabled", QuoteStrategy.valueOf("DISABLED").value());
  }

  /**
   * Test {@link QuoteStrategy#fromValue(String)}.
   *
   * <ul>
   *   <li>When {@code disabled}.
   *   <li>Then return {@code DISABLED}.
   * </ul>
   *
   * <p>Method under test: {@link QuoteStrategy#fromValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QuoteStrategy QuoteStrategy.fromValue(String)"})
  public void testFromValue_whenDisabled_thenReturnDisabled() {
    // Arrange, Act and Assert
    assertEquals(QuoteStrategy.DISABLED, QuoteStrategy.fromValue("disabled"));
  }

  /**
   * Test {@link QuoteStrategy#fromValue(String)}.
   *
   * <ul>
   *   <li>When {@link Boolean#FALSE} toString.
   *   <li>Then return {@code DISABLED}.
   * </ul>
   *
   * <p>Method under test: {@link QuoteStrategy#fromValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QuoteStrategy QuoteStrategy.fromValue(String)"})
  public void testFromValue_whenFalseToString_thenReturnDisabled() {
    // Arrange, Act and Assert
    assertEquals(QuoteStrategy.DISABLED, QuoteStrategy.fromValue(Boolean.FALSE.toString()));
  }

  /**
   * Test {@link QuoteStrategy#fromValue(String)}.
   *
   * <ul>
   *   <li>When {@code foo}.
   *   <li>Then return {@code DISABLED}.
   * </ul>
   *
   * <p>Method under test: {@link QuoteStrategy#fromValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QuoteStrategy QuoteStrategy.fromValue(String)"})
  public void testFromValue_whenFoo_thenReturnDisabled() {
    // Arrange, Act and Assert
    assertEquals(QuoteStrategy.DISABLED, QuoteStrategy.fromValue("foo"));
  }

  /**
   * Test {@link QuoteStrategy#fromValue(String)}.
   *
   * <ul>
   *   <li>When {@link Boolean#TRUE} toString.
   *   <li>Then return {@code ALL}.
   * </ul>
   *
   * <p>Method under test: {@link QuoteStrategy#fromValue(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"QuoteStrategy QuoteStrategy.fromValue(String)"})
  public void testFromValue_whenTrueToString_thenReturnAll() {
    // Arrange, Act and Assert
    assertEquals(QuoteStrategy.ALL, QuoteStrategy.fromValue(Boolean.TRUE.toString()));
  }
}
