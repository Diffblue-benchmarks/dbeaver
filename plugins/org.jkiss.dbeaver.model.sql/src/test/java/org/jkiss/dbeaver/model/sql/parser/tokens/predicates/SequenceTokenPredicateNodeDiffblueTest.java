package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
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

public class SequenceTokenPredicateNodeDiffblueTest {
  /**
   * Test {@link SequenceTokenPredicateNode#SequenceTokenPredicateNode(TokenPredicateNode[])}.
   *
   * <ul>
   *   <li>Then return {@link GroupTokenPredicatesNode#childs} size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * SequenceTokenPredicateNode#SequenceTokenPredicateNode(TokenPredicateNode[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SequenceTokenPredicateNode.<init>(TokenPredicateNode[])"})
  public void testNewSequenceTokenPredicateNode_thenReturnChildsSizeIsOne() {
    // Arrange
    AlternativeTokenPredicateNode child =
        new AlternativeTokenPredicateNode(mock(TokenPredicateNode.class));
    OptionalTokenPredicateNode optionalTokenPredicateNode = new OptionalTokenPredicateNode(child);

    // Act and Assert
    List<TokenPredicateNode> tokenPredicateNodeList =
        new SequenceTokenPredicateNode(optionalTokenPredicateNode).childs;
    assertEquals(1, tokenPredicateNodeList.size());
    assertSame(optionalTokenPredicateNode, tokenPredicateNodeList.get(0));
  }

  /**
   * Test {@link SequenceTokenPredicateNode#applyImpl(TokenPredicateNodeVisitor, Object)}.
   *
   * <p>Method under test: {@link SequenceTokenPredicateNode#applyImpl(TokenPredicateNodeVisitor,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object SequenceTokenPredicateNode.applyImpl(TokenPredicateNodeVisitor, Object)"
  })
  public void testApplyImpl() {
    // Arrange
    OptionalTokenPredicateNode optionalTokenPredicateNode =
        new OptionalTokenPredicateNode(mock(TokenPredicateNode.class));
    SequenceTokenPredicateNode sequenceTokenPredicateNode =
        new SequenceTokenPredicateNode(optionalTokenPredicateNode);

    TokenPredicateNodeVisitor<Object, Object> visitor = mock(TokenPredicateNodeVisitor.class);
    when(visitor.visitSequence(Mockito.<SequenceTokenPredicateNode>any(), Mockito.<Object>any()))
        .thenReturn("Visit Sequence");

    // Act
    Object actualApplyImplResult = sequenceTokenPredicateNode.applyImpl(visitor, "Arg");

    // Assert
    verify(visitor).visitSequence(isA(SequenceTokenPredicateNode.class), isA(Object.class));
    assertEquals("Visit Sequence", actualApplyImplResult);
  }
}
