package org.jkiss.dbeaver.model.sql.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.parser.SimpleNode;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class CustomExpressionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link CustomExpression#CustomExpression(String)}
   *   <li>{@link CustomExpression#setASTNode(SimpleNode)}
   *   <li>{@link CustomExpression#getASTNode()}
   *   <li>{@link CustomExpression#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void CustomExpression.<init>(String)",
    "SimpleNode CustomExpression.getASTNode()",
    "void CustomExpression.setASTNode(SimpleNode)",
    "String CustomExpression.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    CustomExpression actualCustomExpression = new CustomExpression("Expression");
    SimpleNode simpleNode = new SimpleNode(1);
    actualCustomExpression.setASTNode(simpleNode);
    SimpleNode actualASTNode = actualCustomExpression.getASTNode();

    // Assert
    assertEquals("Expression", actualCustomExpression.toString());
    assertEquals(1, actualASTNode.getId());
    assertSame(simpleNode, actualASTNode);
  }

  /**
   * Test {@link CustomExpression#accept(ExpressionVisitor)}.
   *
   * <ul>
   *   <li>Then {@link ExpressionDeParser#ExpressionDeParser()} Builder toString is {@code
   *       'Expression'}.
   * </ul>
   *
   * <p>Method under test: {@link CustomExpression#accept(ExpressionVisitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void CustomExpression.accept(ExpressionVisitor)"})
  public void testAccept_thenExpressionDeParserBuilderToStringIsExpression() {
    // Arrange
    CustomExpression customExpression = new CustomExpression("Expression");
    ExpressionDeParser expressionVisitor = new ExpressionDeParser();

    // Act
    customExpression.accept(expressionVisitor);

    // Assert
    assertEquals("'Expression'", expressionVisitor.getBuilder().toString());
  }

  /**
   * Test {@link CustomExpression#accept(ExpressionVisitor, Object)}.
   *
   * <ul>
   *   <li>When {@link ExpressionVisitorAdapter} (default constructor).
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link CustomExpression#accept(ExpressionVisitor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object CustomExpression.accept(ExpressionVisitor, Object)"})
  public void testAccept_whenExpressionVisitorAdapter_thenReturnNull() {
    // Arrange
    CustomExpression customExpression = new CustomExpression("Expression");

    // Act and Assert
    assertNull(customExpression.accept(new ExpressionVisitorAdapter<>(), DBPEvent.RENAME));
  }
}
