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
import org.jkiss.dbeaver.model.sql.parser.TokenEntry;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TokenPredicateNodeDiffblueTest {
  /**
   * Test {@link TokenPredicateNode#apply(TokenPredicateNodeVisitor, Object)}.
   *
   * <p>Method under test: {@link TokenPredicateNode#apply(TokenPredicateNodeVisitor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TokenPredicateNode.apply(TokenPredicateNodeVisitor, Object)"})
  public void testApply() {
    // Arrange
    AlternativeTokenPredicateNode child =
        new AlternativeTokenPredicateNode(mock(TokenPredicateNode.class));
    OptionalTokenPredicateNode optionalTokenPredicateNode = new OptionalTokenPredicateNode(child);

    TokenPredicateNodeVisitor<Object, Object> visitor = mock(TokenPredicateNodeVisitor.class);
    when(visitor.visitOptional(Mockito.<OptionalTokenPredicateNode>any(), Mockito.<Object>any()))
        .thenReturn("Visit Optional");

    // Act
    Object actualApplyResult = optionalTokenPredicateNode.apply(visitor, "Arg");

    // Assert
    verify(visitor).visitOptional(isA(OptionalTokenPredicateNode.class), isA(Object.class));
    assertEquals("Visit Optional", actualApplyResult);
  }

  /**
   * Test {@link TokenPredicateNode#expand()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateNode#expand()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List TokenPredicateNode.expand()"})
  public void testExpand_thenReturnSizeIsOne() {
    // Arrange
    OptionalTokenPredicateNode optionalTokenPredicateNode =
        new OptionalTokenPredicateNode(
            new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key"));

    // Act
    List<List<TokenEntry>> actualExpandResult = optionalTokenPredicateNode.expand();

    // Assert
    assertEquals(1, actualExpandResult.size());
    List<TokenEntry> getResult = actualExpandResult.get(0);
    assertEquals(1, getResult.size());
    assertSame(optionalTokenPredicateNode.child, getResult.get(0));
  }

  /**
   * Test {@link TokenPredicateNode#toString()}.
   *
   * <p>Method under test: {@link TokenPredicateNode#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateNode.toString()"})
  public void testToString() {
    // Arrange
    TokenPredicateNode tokenPredicateNode = mock(TokenPredicateNode.class);
    when(tokenPredicateNode.apply(
            Mockito.<TokenPredicateNodeVisitor<StringBuilder, StringBuilder>>any(),
            Mockito.<StringBuilder>any()))
        .thenReturn(new StringBuilder("foo"));
    AlternativeTokenPredicateNode child = new AlternativeTokenPredicateNode(tokenPredicateNode);
    OptionalTokenPredicateNode optionalTokenPredicateNode = new OptionalTokenPredicateNode(child);
    SequenceTokenPredicateNode child2 = new SequenceTokenPredicateNode(optionalTokenPredicateNode);

    // Act
    String actualToStringResult = new OptionalTokenPredicateNode(child2).toString();

    // Assert
    verify(tokenPredicateNode)
        .apply(isA(TokenPredicateNodeVisitor.class), isA(StringBuilder.class));
    assertEquals("(()?)?", actualToStringResult);
  }

  /**
   * Test {@link TokenPredicateNode#toString()}.
   *
   * <p>Method under test: {@link TokenPredicateNode#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateNode.toString()"})
  public void testToString2() {
    // Arrange
    TokenPredicateNode child = mock(TokenPredicateNode.class);
    when(child.apply(
            Mockito.<TokenPredicateNodeVisitor<StringBuilder, StringBuilder>>any(),
            Mockito.<StringBuilder>any()))
        .thenReturn(new StringBuilder("foo"));
    OptionalTokenPredicateNode optionalTokenPredicateNode = new OptionalTokenPredicateNode(child);
    AlternativeTokenPredicateNode alternativeTokenPredicateNode =
        new AlternativeTokenPredicateNode(optionalTokenPredicateNode);
    SequenceTokenPredicateNode child2 =
        new SequenceTokenPredicateNode(alternativeTokenPredicateNode);

    // Act
    String actualToStringResult = new OptionalTokenPredicateNode(child2).toString();

    // Assert
    verify(child).apply(isA(TokenPredicateNodeVisitor.class), isA(StringBuilder.class));
    assertEquals("((?))?", actualToStringResult);
  }

  /**
   * Test {@link TokenPredicateNode#toString()}.
   *
   * <p>Method under test: {@link TokenPredicateNode#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateNode.toString()"})
  public void testToString3() {
    // Arrange
    TokenPredicateNode child = mock(TokenPredicateNode.class);
    when(child.apply(
            Mockito.<TokenPredicateNodeVisitor<StringBuilder, StringBuilder>>any(),
            Mockito.<StringBuilder>any()))
        .thenReturn(new StringBuilder("foo"));
    OptionalTokenPredicateNode optionalTokenPredicateNode = new OptionalTokenPredicateNode(child);
    AlternativeTokenPredicateNode alternativeTokenPredicateNode =
        new AlternativeTokenPredicateNode(optionalTokenPredicateNode);
    AlternativeTokenPredicateNode alternativeTokenPredicateNode2 =
        new AlternativeTokenPredicateNode(alternativeTokenPredicateNode);
    SequenceTokenPredicateNode child2 =
        new SequenceTokenPredicateNode(alternativeTokenPredicateNode2);

    // Act
    String actualToStringResult = new OptionalTokenPredicateNode(child2).toString();

    // Assert
    verify(child).apply(isA(TokenPredicateNodeVisitor.class), isA(StringBuilder.class));
    assertEquals("((?))?", actualToStringResult);
  }

  /**
   * Test {@link TokenPredicateNode#toString()}.
   *
   * <p>Method under test: {@link TokenPredicateNode#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateNode.toString()"})
  public void testToString4() {
    // Arrange
    SequenceTokenPredicateNode child =
        new SequenceTokenPredicateNode(
            new AlternativeTokenPredicateNode(new AlternativeTokenPredicateNode()));

    // Act and Assert
    assertEquals("(())?", new OptionalTokenPredicateNode(child).toString());
  }

  /**
   * Test {@link TokenPredicateNode#toString()}.
   *
   * <p>Method under test: {@link TokenPredicateNode#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateNode.toString()"})
  public void testToString5() {
    // Arrange
    TokenPredicateNode tokenPredicateNode = mock(TokenPredicateNode.class);
    when(tokenPredicateNode.apply(
            Mockito.<TokenPredicateNodeVisitor<StringBuilder, StringBuilder>>any(),
            Mockito.<StringBuilder>any()))
        .thenReturn(new StringBuilder("foo"));
    AlternativeTokenPredicateNode child = new AlternativeTokenPredicateNode(tokenPredicateNode);
    OptionalTokenPredicateNode optionalTokenPredicateNode = new OptionalTokenPredicateNode(child);

    TokenPredicateNode tokenPredicateNode2 = mock(TokenPredicateNode.class);
    when(tokenPredicateNode2.apply(
            Mockito.<TokenPredicateNodeVisitor<StringBuilder, StringBuilder>>any(),
            Mockito.<StringBuilder>any()))
        .thenReturn(new StringBuilder("foo"));
    AlternativeTokenPredicateNode child2 = new AlternativeTokenPredicateNode(tokenPredicateNode2);
    OptionalTokenPredicateNode optionalTokenPredicateNode2 = new OptionalTokenPredicateNode(child2);

    AlternativeTokenPredicateNode alternativeTokenPredicateNode =
        new AlternativeTokenPredicateNode(optionalTokenPredicateNode, optionalTokenPredicateNode2);
    SequenceTokenPredicateNode child3 =
        new SequenceTokenPredicateNode(
            new AlternativeTokenPredicateNode(alternativeTokenPredicateNode));

    // Act
    String actualToStringResult = new OptionalTokenPredicateNode(child3).toString();

    // Assert
    verify(tokenPredicateNode)
        .apply(isA(TokenPredicateNodeVisitor.class), isA(StringBuilder.class));
    verify(tokenPredicateNode2)
        .apply(isA(TokenPredicateNodeVisitor.class), isA(StringBuilder.class));
    assertEquals("((()?|()?))?", actualToStringResult);
  }

  /**
   * Test {@link TokenPredicateNode#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ()?}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateNode#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateNode.toString()"})
  public void testToString_thenReturnLeftParenthesisRightParenthesisQuestionMark() {
    // Arrange
    TokenPredicateNode tokenPredicateNode = mock(TokenPredicateNode.class);
    when(tokenPredicateNode.apply(
            Mockito.<TokenPredicateNodeVisitor<StringBuilder, StringBuilder>>any(),
            Mockito.<StringBuilder>any()))
        .thenReturn(new StringBuilder("foo"));
    AlternativeTokenPredicateNode child = new AlternativeTokenPredicateNode(tokenPredicateNode);

    // Act
    String actualToStringResult = new OptionalTokenPredicateNode(child).toString();

    // Assert
    verify(tokenPredicateNode)
        .apply(isA(TokenPredicateNodeVisitor.class), isA(StringBuilder.class));
    assertEquals("()?", actualToStringResult);
  }

  /**
   * Test {@link TokenPredicateNode#toString()}.
   *
   * <ul>
   *   <li>Then return {@code ($<T_KEYWORD>'('?)?}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateNode#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateNode.toString()"})
  public void testToString_thenReturnTKeyword() {
    // Arrange
    SequenceTokenPredicateNode child =
        new SequenceTokenPredicateNode(
            new OptionalTokenPredicateNode(
                new CaptureTokenPredicateNode("(", SQLTokenType.T_KEYWORD, "(")));

    // Act and Assert
    assertEquals("($<T_KEYWORD>'('?)?", new OptionalTokenPredicateNode(child).toString());
  }

  /**
   * Test {@link TokenPredicateNode#toString()}.
   *
   * <ul>
   *   <li>Then return {@code (!<T_KEYWORD>'('?)?}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateNode#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateNode.toString()"})
  public void testToString_thenReturnTKeyword2() {
    // Arrange
    SequenceTokenPredicateNode child =
        new SequenceTokenPredicateNode(
            new OptionalTokenPredicateNode(new SQLTokenEntry("(", SQLTokenType.T_KEYWORD, true)));

    // Act and Assert
    assertEquals("(!<T_KEYWORD>'('?)?", new OptionalTokenPredicateNode(child).toString());
  }
}
