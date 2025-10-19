package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AlternativeTokenPredicateNodeDiffblueTest {
  /**
   * Test {@link AlternativeTokenPredicateNode#AlternativeTokenPredicateNode(TokenPredicateNode[])}.
   *
   * <ul>
   *   <li>Then return {@link GroupTokenPredicatesNode#childs} size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * AlternativeTokenPredicateNode#AlternativeTokenPredicateNode(TokenPredicateNode[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AlternativeTokenPredicateNode.<init>(TokenPredicateNode[])"})
  public void testNewAlternativeTokenPredicateNode_thenReturnChildsSizeIsOne() {
    // Arrange
    OptionalTokenPredicateNode optionalTokenPredicateNode =
        new OptionalTokenPredicateNode(mock(TokenPredicateNode.class));

    // Act and Assert
    List<TokenPredicateNode> tokenPredicateNodeList =
        new AlternativeTokenPredicateNode(optionalTokenPredicateNode).childs;
    assertEquals(1, tokenPredicateNodeList.size());
    TokenPredicateNode getResult = tokenPredicateNodeList.get(0);
    assertTrue(getResult instanceof OptionalTokenPredicateNode);
    assertSame(optionalTokenPredicateNode, getResult);
    assertSame(optionalTokenPredicateNode.child, ((OptionalTokenPredicateNode) getResult).child);
  }

  /**
   * Test {@link AlternativeTokenPredicateNode#applyImpl(TokenPredicateNodeVisitor, Object)}.
   *
   * <p>Method under test: {@link AlternativeTokenPredicateNode#applyImpl(TokenPredicateNodeVisitor,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object AlternativeTokenPredicateNode.applyImpl(TokenPredicateNodeVisitor, Object)"
  })
  public void testApplyImpl() {
    // Arrange
    OptionalTokenPredicateNode optionalTokenPredicateNode =
        new OptionalTokenPredicateNode(mock(TokenPredicateNode.class));
    AlternativeTokenPredicateNode alternativeTokenPredicateNode =
        new AlternativeTokenPredicateNode(optionalTokenPredicateNode);

    TokenPredicateNodeVisitor<Object, Object> visitor = mock(TokenPredicateNodeVisitor.class);
    when(visitor.visitAlternative(
            Mockito.<AlternativeTokenPredicateNode>any(), Mockito.<Object>any()))
        .thenReturn("Visit Alternative");

    // Act
    Object actualApplyImplResult = alternativeTokenPredicateNode.applyImpl(visitor, "Arg");

    // Assert
    verify(visitor).visitAlternative(isA(AlternativeTokenPredicateNode.class), isA(Object.class));
    assertEquals("Visit Alternative", actualApplyImplResult);
  }
}
