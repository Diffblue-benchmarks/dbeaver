package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TokenPredicateFormatterDiffblueTest {
  /**
   * Test {@link TokenPredicateFormatter#format(TokenPredicateNode)}.
   *
   * <p>Method under test: {@link TokenPredicateFormatter#format(TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateFormatter.format(TokenPredicateNode)"})
  public void testFormat() {
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
    String actualFormatResult =
        TokenPredicateFormatter.format(new OptionalTokenPredicateNode(child2));

    // Assert
    verify(tokenPredicateNode)
        .apply(isA(TokenPredicateNodeVisitor.class), isA(StringBuilder.class));
    assertEquals("(()?)?", actualFormatResult);
  }

  /**
   * Test {@link TokenPredicateFormatter#format(TokenPredicateNode)}.
   *
   * <p>Method under test: {@link TokenPredicateFormatter#format(TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateFormatter.format(TokenPredicateNode)"})
  public void testFormat2() {
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
    String actualFormatResult =
        TokenPredicateFormatter.format(new OptionalTokenPredicateNode(child2));

    // Assert
    verify(child).apply(isA(TokenPredicateNodeVisitor.class), isA(StringBuilder.class));
    assertEquals("((?))?", actualFormatResult);
  }

  /**
   * Test {@link TokenPredicateFormatter#format(TokenPredicateNode)}.
   *
   * <p>Method under test: {@link TokenPredicateFormatter#format(TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateFormatter.format(TokenPredicateNode)"})
  public void testFormat3() {
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
    String actualFormatResult =
        TokenPredicateFormatter.format(new OptionalTokenPredicateNode(child2));

    // Assert
    verify(child).apply(isA(TokenPredicateNodeVisitor.class), isA(StringBuilder.class));
    assertEquals("((?))?", actualFormatResult);
  }

  /**
   * Test {@link TokenPredicateFormatter#format(TokenPredicateNode)}.
   *
   * <p>Method under test: {@link TokenPredicateFormatter#format(TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateFormatter.format(TokenPredicateNode)"})
  public void testFormat4() {
    // Arrange
    SequenceTokenPredicateNode child =
        new SequenceTokenPredicateNode(
            new AlternativeTokenPredicateNode(new AlternativeTokenPredicateNode()));

    // Act and Assert
    assertEquals("(())?", TokenPredicateFormatter.format(new OptionalTokenPredicateNode(child)));
  }

  /**
   * Test {@link TokenPredicateFormatter#format(TokenPredicateNode)}.
   *
   * <p>Method under test: {@link TokenPredicateFormatter#format(TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateFormatter.format(TokenPredicateNode)"})
  public void testFormat5() {
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
    String actualFormatResult =
        TokenPredicateFormatter.format(new OptionalTokenPredicateNode(child3));

    // Assert
    verify(tokenPredicateNode)
        .apply(isA(TokenPredicateNodeVisitor.class), isA(StringBuilder.class));
    verify(tokenPredicateNode2)
        .apply(isA(TokenPredicateNodeVisitor.class), isA(StringBuilder.class));
    assertEquals("((()?|()?))?", actualFormatResult);
  }

  /**
   * Test {@link TokenPredicateFormatter#format(TokenPredicateNode)}.
   *
   * <ul>
   *   <li>Then return {@code ()?}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFormatter#format(TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateFormatter.format(TokenPredicateNode)"})
  public void testFormat_thenReturnLeftParenthesisRightParenthesisQuestionMark() {
    // Arrange
    TokenPredicateNode tokenPredicateNode = mock(TokenPredicateNode.class);
    when(tokenPredicateNode.apply(
            Mockito.<TokenPredicateNodeVisitor<StringBuilder, StringBuilder>>any(),
            Mockito.<StringBuilder>any()))
        .thenReturn(new StringBuilder("foo"));
    AlternativeTokenPredicateNode child = new AlternativeTokenPredicateNode(tokenPredicateNode);

    // Act
    String actualFormatResult =
        TokenPredicateFormatter.format(new OptionalTokenPredicateNode(child));

    // Assert
    verify(tokenPredicateNode)
        .apply(isA(TokenPredicateNodeVisitor.class), isA(StringBuilder.class));
    assertEquals("()?", actualFormatResult);
  }

  /**
   * Test {@link TokenPredicateFormatter#format(TokenPredicateNode)}.
   *
   * <ul>
   *   <li>Then return {@code ($<T_KEYWORD>'('?)?}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFormatter#format(TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateFormatter.format(TokenPredicateNode)"})
  public void testFormat_thenReturnTKeyword() {
    // Arrange
    SequenceTokenPredicateNode child =
        new SequenceTokenPredicateNode(
            new OptionalTokenPredicateNode(
                new CaptureTokenPredicateNode("(", SQLTokenType.T_KEYWORD, "(")));

    // Act and Assert
    assertEquals(
        "($<T_KEYWORD>'('?)?",
        TokenPredicateFormatter.format(new OptionalTokenPredicateNode(child)));
  }

  /**
   * Test {@link TokenPredicateFormatter#format(TokenPredicateNode)}.
   *
   * <ul>
   *   <li>Then return {@code (!<T_KEYWORD>'('?)?}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFormatter#format(TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateFormatter.format(TokenPredicateNode)"})
  public void testFormat_thenReturnTKeyword2() {
    // Arrange
    SequenceTokenPredicateNode child =
        new SequenceTokenPredicateNode(
            new OptionalTokenPredicateNode(new SQLTokenEntry("(", SQLTokenType.T_KEYWORD, true)));

    // Act and Assert
    assertEquals(
        "(!<T_KEYWORD>'('?)?",
        TokenPredicateFormatter.format(new OptionalTokenPredicateNode(child)));
  }

  /**
   * Test {@link TokenPredicateFormatter#format(TokenPredicateNode)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code <NULL>}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFormatter#format(TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TokenPredicateFormatter.format(TokenPredicateNode)"})
  public void testFormat_whenNull_thenReturnNull() {
    // Arrange, Act and Assert
    assertEquals("<NULL>", TokenPredicateFormatter.format(null));
  }
}
