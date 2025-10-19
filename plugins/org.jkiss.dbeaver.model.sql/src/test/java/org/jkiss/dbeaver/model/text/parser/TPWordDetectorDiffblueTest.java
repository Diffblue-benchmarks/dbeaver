package org.jkiss.dbeaver.model.text.parser;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TPWordDetectorDiffblueTest {
  /**
   * Test {@link TPWordDetector#isWordStart(char)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TPWordDetector#isWordStart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPWordDetector.isWordStart(char)"})
  public void testIsWordStart_whenA_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new TPWordDetector().isWordStart('A'));
  }

  /**
   * Test {@link TPWordDetector#isWordStart(char)}.
   *
   * <ul>
   *   <li>When end of text.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TPWordDetector#isWordStart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPWordDetector.isWordStart(char)"})
  public void testIsWordStart_whenEndOfText_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new TPWordDetector().isWordStart('\u0003'));
  }

  /**
   * Test {@link TPWordDetector#isWordPart(char)}.
   *
   * <ul>
   *   <li>When {@code A}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TPWordDetector#isWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPWordDetector.isWordPart(char)"})
  public void testIsWordPart_whenA_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new TPWordDetector().isWordPart('A'));
  }

  /**
   * Test {@link TPWordDetector#isWordPart(char)}.
   *
   * <ul>
   *   <li>When {@code $}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TPWordDetector#isWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPWordDetector.isWordPart(char)"})
  public void testIsWordPart_whenDollarSign_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new TPWordDetector().isWordPart('$'));
  }

  /**
   * Test {@link TPWordDetector#isWordPart(char)}.
   *
   * <ul>
   *   <li>When {@code ?}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TPWordDetector#isWordPart(char)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TPWordDetector.isWordPart(char)"})
  public void testIsWordPart_whenQuestionMark_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new TPWordDetector().isWordPart('?'));
  }
}
