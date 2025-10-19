package org.jkiss.dbeaver.model.sql.analyzer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.jkiss.dbeaver.model.text.parser.TPToken;
import org.jkiss.dbeaver.model.text.parser.TPTokenAbstract;
import org.jkiss.dbeaver.model.text.parser.TPTokenDefault;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TableReferencesAnalyzerDiffblueTest {
  /**
   * Test {@link TableReferencesAnalyzer#isNamePartToken(TPToken)}.
   *
   * <ul>
   *   <li>When {@link TPTokenAbstract#EOF}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link TableReferencesAnalyzer#isNamePartToken(TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TableReferencesAnalyzer.isNamePartToken(TPToken)"})
  public void testIsNamePartToken_whenEof_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(TableReferencesAnalyzer.isNamePartToken(TPTokenAbstract.EOF));
  }

  /**
   * Test {@link TableReferencesAnalyzer#isNamePartToken(TPToken)}.
   *
   * <ul>
   *   <li>When {@link TPTokenDefault#TPTokenDefault(TPTokenType)} with type is {@link
   *       SQLTokenType#T_KEYWORD}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TableReferencesAnalyzer#isNamePartToken(TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TableReferencesAnalyzer.isNamePartToken(TPToken)"})
  public void testIsNamePartToken_whenTPTokenDefaultWithTypeIsT_keyword_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(TableReferencesAnalyzer.isNamePartToken(new TPTokenDefault(SQLTokenType.T_KEYWORD)));
  }

  /**
   * Test {@link TableReferencesAnalyzer#isNamePartToken(TPToken)}.
   *
   * <ul>
   *   <li>When {@link TPTokenDefault#TPTokenDefault(TPTokenType)} with type is {@link
   *       SQLTokenType#T_OTHER}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TableReferencesAnalyzer#isNamePartToken(TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TableReferencesAnalyzer.isNamePartToken(TPToken)"})
  public void testIsNamePartToken_whenTPTokenDefaultWithTypeIsT_other_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(TableReferencesAnalyzer.isNamePartToken(new TPTokenDefault(SQLTokenType.T_OTHER)));
  }

  /**
   * Test {@link TableReferencesAnalyzer#isNamePartToken(TPToken)}.
   *
   * <ul>
   *   <li>When {@link TPTokenDefault#TPTokenDefault(TPTokenType)} with type is {@link
   *       SQLTokenType#T_QUOTED}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link TableReferencesAnalyzer#isNamePartToken(TPToken)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TableReferencesAnalyzer.isNamePartToken(TPToken)"})
  public void testIsNamePartToken_whenTPTokenDefaultWithTypeIsT_quoted_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(TableReferencesAnalyzer.isNamePartToken(new TPTokenDefault(SQLTokenType.T_QUOTED)));
  }
}
