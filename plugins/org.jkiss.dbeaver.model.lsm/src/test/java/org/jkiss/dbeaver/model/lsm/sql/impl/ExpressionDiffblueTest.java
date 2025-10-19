package org.jkiss.dbeaver.model.lsm.sql.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.lsm.sql.impl.Expression.Div;
import org.jkiss.dbeaver.model.lsm.sql.impl.Expression.Mul;
import org.jkiss.dbeaver.model.lsm.sql.impl.Expression.Number;
import org.jkiss.dbeaver.model.lsm.sql.impl.Expression.RowSubqueryValue;
import org.jkiss.dbeaver.model.lsm.sql.impl.Expression.RowValueListExpression;
import org.jkiss.dbeaver.model.lsm.sql.impl.Expression.Sub;
import org.jkiss.dbeaver.model.lsm.sql.impl.Expression.Sum;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ExpressionDiffblueTest {
  /**
   * Test Div new {@link Div} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Div}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Div.<init>()"})
  public void testDivNewDiv() {
    // Arrange and Act
    Div actualDiv = new Div();

    // Assert
    assertNull(actualDiv.left);
    assertNull(actualDiv.right);
  }

  /**
   * Test Mul new {@link Mul} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Mul}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Mul.<init>()"})
  public void testMulNewMul() {
    // Arrange and Act
    Mul actualMul = new Mul();

    // Assert
    assertNull(actualMul.left);
    assertNull(actualMul.right);
  }

  /**
   * Test Number new {@link Number} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Number}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Number.<init>()"})
  public void testNumberNewNumber() {
    // Arrange, Act and Assert
    assertEquals(0.0d, new Number().value, 0.0);
  }

  /**
   * Test RowSubqueryValue new {@link RowSubqueryValue} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link RowSubqueryValue}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RowSubqueryValue.<init>()"})
  public void testRowSubqueryValueNewRowSubqueryValue() {
    // Arrange, Act and Assert
    assertNull(new RowSubqueryValue().subquery);
  }

  /**
   * Test RowValueListExpression new {@link RowValueListExpression} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link RowValueListExpression}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RowValueListExpression.<init>()"})
  public void testRowValueListExpressionNewRowValueListExpression() {
    // Arrange, Act and Assert
    assertNull(new RowValueListExpression().elements);
  }

  /**
   * Test Sub new {@link Sub} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Sub}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Sub.<init>()"})
  public void testSubNewSub() {
    // Arrange and Act
    Sub actualSub = new Sub();

    // Assert
    assertNull(actualSub.left);
    assertNull(actualSub.right);
  }

  /**
   * Test Sum new {@link Sum} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link Sum}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Sum.<init>()"})
  public void testSumNewSum() {
    // Arrange and Act
    Sum actualSum = new Sum();

    // Assert
    assertNull(actualSum.left);
    assertNull(actualSum.right);
  }
}
