package org.jkiss.dbeaver.model.sql.parser.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.text.parser.TPCharacterScanner;
import org.jkiss.dbeaver.model.text.parser.TPRuleBasedScanner;
import org.jkiss.dbeaver.model.text.parser.TPToken;
import org.jkiss.dbeaver.model.text.parser.TPTokenAbstract;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLWordRuleDiffblueTest {
  /**
   * Test {@link SQLWordRule#hasWord(String)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordRule#hasWord(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLWordRule.hasWord(String)"})
  public void testHasWord_thenReturnFalse() {
    // Arrange
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule delimRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);
    SQLWordRule sqlWordRule =
        new SQLWordRule(
            delimRule, TPTokenAbstract.EOF, TPTokenAbstract.EOF, mock(SQLDialect.class));

    // Act and Assert
    assertFalse(sqlWordRule.hasWord("Word"));
  }

  /**
   * Test {@link SQLWordRule#hasWord(String)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordRule#hasWord(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLWordRule.hasWord(String)"})
  public void testHasWord_thenReturnTrue() {
    // Arrange
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule delimRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);

    SQLWordRule sqlWordRule =
        new SQLWordRule(
            delimRule, TPTokenAbstract.EOF, TPTokenAbstract.EOF, mock(SQLDialect.class));
    sqlWordRule.addWord("Word", TPTokenAbstract.EOF);

    // Act and Assert
    assertTrue(sqlWordRule.hasWord("Word"));
  }

  /**
   * Test {@link SQLWordRule#addWord(String, TPToken)}.
   *
   * <p>Method under test: {@link SQLWordRule#addWord(String, TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLWordRule.addWord(String, TPToken)"})
  public void testAddWord() {
    // Arrange
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule delimRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);
    SQLWordRule sqlWordRule =
        new SQLWordRule(
            delimRule, TPTokenAbstract.EOF, TPTokenAbstract.EOF, mock(SQLDialect.class));

    // Act
    sqlWordRule.addWord("Word", TPTokenAbstract.EOF);

    // Assert
    assertTrue(sqlWordRule.hasWord("Word"));
  }

  /**
   * Test {@link SQLWordRule#hasFunction(String)}.
   *
   * <p>Method under test: {@link SQLWordRule#hasFunction(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SQLWordRule.hasFunction(String)"})
  public void testHasFunction() {
    // Arrange
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule delimRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);
    SQLWordRule sqlWordRule =
        new SQLWordRule(
            delimRule, TPTokenAbstract.EOF, TPTokenAbstract.EOF, mock(SQLDialect.class));

    // Act and Assert
    assertFalse(sqlWordRule.hasFunction("Function"));
  }

  /**
   * Test {@link SQLWordRule#evaluate(TPCharacterScanner)}.
   *
   * <ul>
   *   <li>Then return {@link TPTokenAbstract#UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link SQLWordRule#evaluate(TPCharacterScanner)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TPToken SQLWordRule.evaluate(TPCharacterScanner)"})
  public void testEvaluate_thenReturnUndefined() {
    // Arrange
    String[] delimiters = new String[] {"Delimiters"};
    SQLDelimiterRule delimRule = new SQLDelimiterRule(delimiters, TPTokenAbstract.EOF);
    SQLWordRule sqlWordRule =
        new SQLWordRule(
            delimRule, TPTokenAbstract.EOF, TPTokenAbstract.EOF, mock(SQLDialect.class));

    // Act
    TPToken actualEvaluateResult = sqlWordRule.evaluate(new TPRuleBasedScanner());

    // Assert
    assertSame(((TPTokenAbstract) actualEvaluateResult).UNDEFINED, actualEvaluateResult);
  }
}
