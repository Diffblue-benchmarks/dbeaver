package org.jkiss.dbeaver.model.lsm.sql.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.lsm.sql.impl.SearchCondition.AndExpression;
import org.jkiss.dbeaver.model.lsm.sql.impl.SearchCondition.BetweenPredicate;
import org.jkiss.dbeaver.model.lsm.sql.impl.SearchCondition.ComparisonPredicate;
import org.jkiss.dbeaver.model.lsm.sql.impl.SearchCondition.ExistsPredicate;
import org.jkiss.dbeaver.model.lsm.sql.impl.SearchCondition.InPredicate;
import org.jkiss.dbeaver.model.lsm.sql.impl.SearchCondition.LikePredicate;
import org.jkiss.dbeaver.model.lsm.sql.impl.SearchCondition.MatchPredicate;
import org.jkiss.dbeaver.model.lsm.sql.impl.SearchCondition.NullPredicate;
import org.jkiss.dbeaver.model.lsm.sql.impl.SearchCondition.OrExpression;
import org.jkiss.dbeaver.model.lsm.sql.impl.SearchCondition.OverlapsPredicate;
import org.jkiss.dbeaver.model.lsm.sql.impl.SearchCondition.QuantifiedComparisonPredicate;
import org.jkiss.dbeaver.model.lsm.sql.impl.SearchCondition.SubconditionExpression;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SearchConditionDiffblueTest {
  /**
   * Test AndExpression new {@link AndExpression} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link AndExpression}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AndExpression.<init>()"})
  public void testAndExpressionNewAndExpression() {
    // Arrange, Act and Assert
    assertNull(new AndExpression().children);
  }

  /**
   * Test BetweenPredicate new {@link BetweenPredicate} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link BetweenPredicate}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void BetweenPredicate.<init>()"})
  public void testBetweenPredicateNewBetweenPredicate() {
    // Arrange and Act
    BetweenPredicate actualBetweenPredicate = new BetweenPredicate();

    // Assert
    assertNull(actualBetweenPredicate.clarification);
    assertFalse(actualBetweenPredicate.isInverted);
  }

  /**
   * Test ComparisonPredicate new {@link ComparisonPredicate} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ComparisonPredicate}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ComparisonPredicate.<init>()"})
  public void testComparisonPredicateNewComparisonPredicate() {
    // Arrange and Act
    ComparisonPredicate actualComparisonPredicate = new ComparisonPredicate();

    // Assert
    assertNull(actualComparisonPredicate.left);
    assertNull(actualComparisonPredicate.right);
    assertNull(actualComparisonPredicate.clarification);
    assertFalse(actualComparisonPredicate.isInverted);
  }

  /**
   * Test ExistsPredicate new {@link ExistsPredicate} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link ExistsPredicate}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ExistsPredicate.<init>()"})
  public void testExistsPredicateNewExistsPredicate() {
    // Arrange and Act
    ExistsPredicate actualExistsPredicate = new ExistsPredicate();

    // Assert
    assertNull(actualExistsPredicate.clarification);
    assertFalse(actualExistsPredicate.isInverted);
  }

  /**
   * Test InPredicate new {@link InPredicate} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link InPredicate}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void InPredicate.<init>()"})
  public void testInPredicateNewInPredicate() {
    // Arrange and Act
    InPredicate actualInPredicate = new InPredicate();

    // Assert
    assertNull(actualInPredicate.clarification);
    assertFalse(actualInPredicate.isInverted);
  }

  /**
   * Test LikePredicate new {@link LikePredicate} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link LikePredicate}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LikePredicate.<init>()"})
  public void testLikePredicateNewLikePredicate() {
    // Arrange and Act
    LikePredicate actualLikePredicate = new LikePredicate();

    // Assert
    assertNull(actualLikePredicate.clarification);
    assertFalse(actualLikePredicate.isInverted);
  }

  /**
   * Test MatchPredicate new {@link MatchPredicate} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link MatchPredicate}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MatchPredicate.<init>()"})
  public void testMatchPredicateNewMatchPredicate() {
    // Arrange and Act
    MatchPredicate actualMatchPredicate = new MatchPredicate();

    // Assert
    assertNull(actualMatchPredicate.clarification);
    assertFalse(actualMatchPredicate.isInverted);
  }

  /**
   * Test NullPredicate new {@link NullPredicate} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link NullPredicate}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void NullPredicate.<init>()"})
  public void testNullPredicateNewNullPredicate() {
    // Arrange and Act
    NullPredicate actualNullPredicate = new NullPredicate();

    // Assert
    assertNull(actualNullPredicate.clarification);
    assertFalse(actualNullPredicate.isInverted);
  }

  /**
   * Test OrExpression new {@link OrExpression} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OrExpression}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OrExpression.<init>()"})
  public void testOrExpressionNewOrExpression() {
    // Arrange, Act and Assert
    assertNull(new OrExpression().children);
  }

  /**
   * Test OverlapsPredicate new {@link OverlapsPredicate} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OverlapsPredicate}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OverlapsPredicate.<init>()"})
  public void testOverlapsPredicateNewOverlapsPredicate() {
    // Arrange and Act
    OverlapsPredicate actualOverlapsPredicate = new OverlapsPredicate();

    // Assert
    assertNull(actualOverlapsPredicate.clarification);
    assertFalse(actualOverlapsPredicate.isInverted);
  }

  /**
   * Test QuantifiedComparisonPredicate new {@link QuantifiedComparisonPredicate} (default
   * constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link
   * QuantifiedComparisonPredicate}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void QuantifiedComparisonPredicate.<init>()"})
  public void testQuantifiedComparisonPredicateNewQuantifiedComparisonPredicate() {
    // Arrange and Act
    QuantifiedComparisonPredicate actualQuantifiedComparisonPredicate =
        new QuantifiedComparisonPredicate();

    // Assert
    assertNull(actualQuantifiedComparisonPredicate.clarification);
    assertFalse(actualQuantifiedComparisonPredicate.isInverted);
  }

  /**
   * Test SubconditionExpression new {@link SubconditionExpression} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link SubconditionExpression}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SubconditionExpression.<init>()"})
  public void testSubconditionExpressionNewSubconditionExpression() {
    // Arrange and Act
    SubconditionExpression actualSubconditionExpression = new SubconditionExpression();

    // Assert
    assertNull(actualSubconditionExpression.child);
    assertNull(actualSubconditionExpression.clarification);
    assertFalse(actualSubconditionExpression.isInverted);
  }
}
