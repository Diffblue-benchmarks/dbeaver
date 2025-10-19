package org.jkiss.dbeaver.model.sql.parser.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.registry.SQLCommandHandlerDescriptor;
import org.jkiss.dbeaver.model.text.parser.TPCharacterScanner;
import org.jkiss.dbeaver.model.text.parser.TPRuleBasedScanner;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SQLCommandRuleDiffblueTest {
  @Mock private SQLCommandHandlerDescriptor sQLCommandHandlerDescriptor;

  @InjectMocks private SQLCommandRule sQLCommandRule;

  /**
   * Test {@link SQLCommandRule#sequenceDetected(TPCharacterScanner, char[], boolean)}.
   *
   * <ul>
   *   <li>When {@code A A} toCharArray.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommandRule#sequenceDetected(TPCharacterScanner, char[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SQLCommandRule.sequenceDetected(TPCharacterScanner, char[], boolean)"
  })
  public void testSequenceDetected_whenAAToCharArray_thenReturnFalse() {
    // Arrange
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act and Assert
    assertFalse(sQLCommandRule.sequenceDetected(scanner, "A A ".toCharArray(), true));
  }

  /**
   * Test {@link SQLCommandRule#sequenceDetected(TPCharacterScanner, char[], boolean)}.
   *
   * <ul>
   *   <li>When a string toCharArray.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommandRule#sequenceDetected(TPCharacterScanner, char[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SQLCommandRule.sequenceDetected(TPCharacterScanner, char[], boolean)"
  })
  public void testSequenceDetected_whenAStringToCharArray_thenReturnFalse() {
    // Arrange
    TPRuleBasedScanner scanner = new TPRuleBasedScanner();

    // Act and Assert
    assertFalse(
        sQLCommandRule.sequenceDetected(
            scanner, "\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004".toCharArray(), true));
  }

  /**
   * Test {@link SQLCommandRule#sequenceDetected(TPCharacterScanner, char[], boolean)}.
   *
   * <ul>
   *   <li>When empty array of {@code char}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLCommandRule#sequenceDetected(TPCharacterScanner, char[],
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean SQLCommandRule.sequenceDetected(TPCharacterScanner, char[], boolean)"
  })
  public void testSequenceDetected_whenEmptyArrayOfChar_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(sQLCommandRule.sequenceDetected(new TPRuleBasedScanner(), new char[] {}, true));
  }
}
