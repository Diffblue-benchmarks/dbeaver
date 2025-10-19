package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.SQLTokenPredicateSet;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLTokenPredicateEvaluatorDiffblueTest {
  /**
   * Test {@link SQLTokenPredicateEvaluator#SQLTokenPredicateEvaluator(SQLTokenPredicateSet)}.
   *
   * <p>Method under test: {@link
   * SQLTokenPredicateEvaluator#SQLTokenPredicateEvaluator(SQLTokenPredicateSet)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLTokenPredicateEvaluator.<init>(SQLTokenPredicateSet)"})
  public void testNewSQLTokenPredicateEvaluator() {
    // Arrange, Act and Assert
    assertNull(new SQLTokenPredicateEvaluator(new TokenPredicateSet()).getLastMatchedPredicate());
  }

  /**
   * Test {@link SQLTokenPredicateEvaluator#evaluatePredicates()}.
   *
   * <p>Method under test: {@link SQLTokenPredicateEvaluator#evaluatePredicates()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.sql.parser.SQLParserActionKind SQLTokenPredicateEvaluator.evaluatePredicates()"
  })
  public void testEvaluatePredicates() {
    // Arrange, Act and Assert
    assertNull(new SQLTokenPredicateEvaluator(new TokenPredicateSet()).evaluatePredicates());
  }

  /**
   * Test {@link SQLTokenPredicateEvaluator#getLastMatchedPredicate()}.
   *
   * <p>Method under test: {@link SQLTokenPredicateEvaluator#getLastMatchedPredicate()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.sql.parser.SQLTokenPredicate SQLTokenPredicateEvaluator.getLastMatchedPredicate()"
  })
  public void testGetLastMatchedPredicate() {
    // Arrange, Act and Assert
    assertNull(new SQLTokenPredicateEvaluator(new TokenPredicateSet()).getLastMatchedPredicate());
  }

  /**
   * Test {@link SQLTokenPredicateEvaluator#obtainPrefixCaptures()}.
   *
   * <p>Method under test: {@link SQLTokenPredicateEvaluator#obtainPrefixCaptures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Map SQLTokenPredicateEvaluator.obtainPrefixCaptures()"})
  public void testObtainPrefixCaptures() {
    // Arrange, Act and Assert
    assertTrue(
        new SQLTokenPredicateEvaluator(new TokenPredicateSet()).obtainPrefixCaptures().isEmpty());
  }
}
