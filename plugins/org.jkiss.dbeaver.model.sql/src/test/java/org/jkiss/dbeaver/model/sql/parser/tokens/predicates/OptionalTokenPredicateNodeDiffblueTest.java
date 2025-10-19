package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class OptionalTokenPredicateNodeDiffblueTest {
  /**
   * Test {@link OptionalTokenPredicateNode#applyImpl(TokenPredicateNodeVisitor, Object)}.
   *
   * <p>Method under test: {@link OptionalTokenPredicateNode#applyImpl(TokenPredicateNodeVisitor,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object OptionalTokenPredicateNode.applyImpl(TokenPredicateNodeVisitor, Object)"
  })
  public void testApplyImpl() {
    // Arrange
    AlternativeTokenPredicateNode child =
        new AlternativeTokenPredicateNode(mock(TokenPredicateNode.class));
    OptionalTokenPredicateNode optionalTokenPredicateNode = new OptionalTokenPredicateNode(child);

    TokenPredicateNodeVisitor<Object, Object> visitor = mock(TokenPredicateNodeVisitor.class);
    when(visitor.visitOptional(Mockito.<OptionalTokenPredicateNode>any(), Mockito.<Object>any()))
        .thenReturn("Visit Optional");

    // Act
    Object actualApplyImplResult = optionalTokenPredicateNode.applyImpl(visitor, "Arg");

    // Assert
    verify(visitor).visitOptional(isA(OptionalTokenPredicateNode.class), isA(Object.class));
    assertEquals("Visit Optional", actualApplyImplResult);
  }
}
