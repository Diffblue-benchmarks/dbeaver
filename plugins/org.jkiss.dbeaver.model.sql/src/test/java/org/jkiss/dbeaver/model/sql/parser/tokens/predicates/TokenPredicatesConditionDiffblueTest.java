package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.sql.parser.SQLParserActionKind;
import org.jkiss.dbeaver.model.sql.parser.TokenEntry;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TokenPredicatesConditionDiffblueTest {
  /**
   * Test {@link TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind,
   * TokenPredicateNode, TokenPredicateNode)}.
   *
   * <p>Method under test: {@link
   * TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind, TokenPredicateNode,
   * TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TokenPredicatesCondition.<init>(SQLParserActionKind, TokenPredicateNode, TokenPredicateNode)"
  })
  public void testNewTokenPredicatesCondition() {
    // Arrange
    AlternativeTokenPredicateNode prefixPredicate = mock(AlternativeTokenPredicateNode.class);
    when(prefixPredicate.expand()).thenReturn(new ArrayList<>());
    CaptureTokenPredicateNode suffixPredicate =
        new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key");

    // Act
    TokenPredicatesCondition actualTokenPredicatesCondition =
        new TokenPredicatesCondition(
            SQLParserActionKind.SKIP_SUFFIX_TERM, prefixPredicate, suffixPredicate);

    // Assert
    verify(prefixPredicate).expand();
    List<List<TokenEntry>> suffixes = actualTokenPredicatesCondition.getSuffixes();
    assertEquals(1, suffixes.size());
    List<TokenEntry> getResult = suffixes.get(0);
    assertEquals(1, getResult.size());
    assertSame(suffixPredicate, getResult.get(0));
  }

  /**
   * Test {@link TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind,
   * TokenPredicateNode, TokenPredicateNode)}.
   *
   * <p>Method under test: {@link
   * TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind, TokenPredicateNode,
   * TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TokenPredicatesCondition.<init>(SQLParserActionKind, TokenPredicateNode, TokenPredicateNode)"
  })
  public void testNewTokenPredicatesCondition2() {
    // Arrange
    AlternativeTokenPredicateNode prefixPredicate = mock(AlternativeTokenPredicateNode.class);
    when(prefixPredicate.expand()).thenReturn(new ArrayList<>());
    AlternativeTokenPredicateNode suffixPredicate =
        new AlternativeTokenPredicateNode(
            new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key"));

    // Act
    TokenPredicatesCondition actualTokenPredicatesCondition =
        new TokenPredicatesCondition(
            SQLParserActionKind.SKIP_SUFFIX_TERM, prefixPredicate, suffixPredicate);

    // Assert
    verify(prefixPredicate).expand();
    List<List<TokenEntry>> suffixes = actualTokenPredicatesCondition.getSuffixes();
    assertEquals(1, suffixes.size());
    assertEquals(suffixPredicate.childs, suffixes.get(0));
  }

  /**
   * Test {@link TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind,
   * TokenPredicateNode, TokenPredicateNode, String)}.
   *
   * <p>Method under test: {@link
   * TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind, TokenPredicateNode,
   * TokenPredicateNode, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TokenPredicatesCondition.<init>(SQLParserActionKind, TokenPredicateNode, TokenPredicateNode, String)"
  })
  public void testNewTokenPredicatesCondition3() {
    // Arrange
    AlternativeTokenPredicateNode prefixPredicate = mock(AlternativeTokenPredicateNode.class);
    when(prefixPredicate.expand()).thenReturn(new ArrayList<>());
    CaptureTokenPredicateNode suffixPredicate =
        new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key");

    // Act
    TokenPredicatesCondition actualTokenPredicatesCondition =
        new TokenPredicatesCondition(
            SQLParserActionKind.SKIP_SUFFIX_TERM, prefixPredicate, suffixPredicate, "Parameter");

    // Assert
    verify(prefixPredicate).expand();
    List<List<TokenEntry>> suffixes = actualTokenPredicatesCondition.getSuffixes();
    assertEquals(1, suffixes.size());
    List<TokenEntry> getResult = suffixes.get(0);
    assertEquals(1, getResult.size());
    assertSame(suffixPredicate, getResult.get(0));
  }

  /**
   * Test {@link TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind,
   * TokenPredicateNode, TokenPredicateNode, String)}.
   *
   * <p>Method under test: {@link
   * TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind, TokenPredicateNode,
   * TokenPredicateNode, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TokenPredicatesCondition.<init>(SQLParserActionKind, TokenPredicateNode, TokenPredicateNode, String)"
  })
  public void testNewTokenPredicatesCondition4() {
    // Arrange
    AlternativeTokenPredicateNode prefixPredicate = mock(AlternativeTokenPredicateNode.class);
    when(prefixPredicate.expand()).thenReturn(new ArrayList<>());
    AlternativeTokenPredicateNode suffixPredicate =
        new AlternativeTokenPredicateNode(
            new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key"));

    // Act
    TokenPredicatesCondition actualTokenPredicatesCondition =
        new TokenPredicatesCondition(
            SQLParserActionKind.SKIP_SUFFIX_TERM, prefixPredicate, suffixPredicate, "Parameter");

    // Assert
    verify(prefixPredicate).expand();
    List<List<TokenEntry>> suffixes = actualTokenPredicatesCondition.getSuffixes();
    assertEquals(1, suffixes.size());
    assertEquals(suffixPredicate.childs, suffixes.get(0));
  }

  /**
   * Test {@link TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind,
   * TokenPredicateNode, TokenPredicateNode, String)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code Parameter}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind, TokenPredicateNode,
   * TokenPredicateNode, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TokenPredicatesCondition.<init>(SQLParserActionKind, TokenPredicateNode, TokenPredicateNode, String)"
  })
  public void testNewTokenPredicatesCondition_givenArrayList_thenReturnParameter() {
    // Arrange
    AlternativeTokenPredicateNode prefixPredicate = mock(AlternativeTokenPredicateNode.class);
    when(prefixPredicate.expand()).thenReturn(new ArrayList<>());

    AlternativeTokenPredicateNode suffixPredicate = mock(AlternativeTokenPredicateNode.class);
    when(suffixPredicate.expand()).thenReturn(new ArrayList<>());

    // Act
    TokenPredicatesCondition actualTokenPredicatesCondition =
        new TokenPredicatesCondition(
            SQLParserActionKind.SKIP_SUFFIX_TERM, prefixPredicate, suffixPredicate, "Parameter");

    // Assert
    verify(prefixPredicate).expand();
    verify(suffixPredicate).expand();
    assertEquals("Parameter", actualTokenPredicatesCondition.getParameter());
    assertEquals(0, actualTokenPredicatesCondition.getMaxSuffixLength());
    assertEquals(0, actualTokenPredicatesCondition.maxPrefixLength);
    assertEquals(
        SQLParserActionKind.SKIP_SUFFIX_TERM, actualTokenPredicatesCondition.getActionKind());
    assertTrue(actualTokenPredicatesCondition.getPrefixes().isEmpty());
    assertTrue(actualTokenPredicatesCondition.getSuffixes().isEmpty());
  }

  /**
   * Test {@link TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind,
   * TokenPredicateNode, TokenPredicateNode)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then return Parameter is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind, TokenPredicateNode,
   * TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TokenPredicatesCondition.<init>(SQLParserActionKind, TokenPredicateNode, TokenPredicateNode)"
  })
  public void testNewTokenPredicatesCondition_givenArrayList_thenReturnParameterIsNull() {
    // Arrange
    AlternativeTokenPredicateNode prefixPredicate = mock(AlternativeTokenPredicateNode.class);
    when(prefixPredicate.expand()).thenReturn(new ArrayList<>());

    AlternativeTokenPredicateNode suffixPredicate = mock(AlternativeTokenPredicateNode.class);
    when(suffixPredicate.expand()).thenReturn(new ArrayList<>());

    // Act
    TokenPredicatesCondition actualTokenPredicatesCondition =
        new TokenPredicatesCondition(
            SQLParserActionKind.SKIP_SUFFIX_TERM, prefixPredicate, suffixPredicate);

    // Assert
    verify(prefixPredicate).expand();
    verify(suffixPredicate).expand();
    assertNull(actualTokenPredicatesCondition.getParameter());
    assertEquals(0, actualTokenPredicatesCondition.getMaxSuffixLength());
    assertEquals(0, actualTokenPredicatesCondition.maxPrefixLength);
    assertEquals(
        SQLParserActionKind.SKIP_SUFFIX_TERM, actualTokenPredicatesCondition.getActionKind());
    assertTrue(actualTokenPredicatesCondition.getPrefixes().isEmpty());
    assertTrue(actualTokenPredicatesCondition.getSuffixes().isEmpty());
  }

  /**
   * Test {@link TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind,
   * TokenPredicateNode, TokenPredicateNode)}.
   *
   * <ul>
   *   <li>Then return MaxSuffixLength is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind, TokenPredicateNode,
   * TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TokenPredicatesCondition.<init>(SQLParserActionKind, TokenPredicateNode, TokenPredicateNode)"
  })
  public void testNewTokenPredicatesCondition_thenReturnMaxSuffixLengthIsOne() {
    // Arrange
    AlternativeTokenPredicateNode prefixPredicate = mock(AlternativeTokenPredicateNode.class);
    when(prefixPredicate.expand()).thenReturn(new ArrayList<>());
    SQLTokenEntry suffixPredicate = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    // Act
    TokenPredicatesCondition actualTokenPredicatesCondition =
        new TokenPredicatesCondition(
            SQLParserActionKind.SKIP_SUFFIX_TERM, prefixPredicate, suffixPredicate);

    // Assert
    verify(prefixPredicate).expand();
    List<List<TokenEntry>> suffixes = actualTokenPredicatesCondition.getSuffixes();
    assertEquals(1, suffixes.size());
    List<TokenEntry> getResult = suffixes.get(0);
    assertEquals(1, getResult.size());
    assertEquals(1, actualTokenPredicatesCondition.getMaxSuffixLength());
    assertSame(suffixPredicate, getResult.get(0));
  }

  /**
   * Test {@link TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind,
   * TokenPredicateNode, TokenPredicateNode, String)}.
   *
   * <ul>
   *   <li>Then return MaxSuffixLength is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * TokenPredicatesCondition#TokenPredicatesCondition(SQLParserActionKind, TokenPredicateNode,
   * TokenPredicateNode, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void TokenPredicatesCondition.<init>(SQLParserActionKind, TokenPredicateNode, TokenPredicateNode, String)"
  })
  public void testNewTokenPredicatesCondition_thenReturnMaxSuffixLengthIsOne2() {
    // Arrange
    AlternativeTokenPredicateNode prefixPredicate = mock(AlternativeTokenPredicateNode.class);
    when(prefixPredicate.expand()).thenReturn(new ArrayList<>());
    SQLTokenEntry suffixPredicate = new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true);

    // Act
    TokenPredicatesCondition actualTokenPredicatesCondition =
        new TokenPredicatesCondition(
            SQLParserActionKind.SKIP_SUFFIX_TERM, prefixPredicate, suffixPredicate, "Parameter");

    // Assert
    verify(prefixPredicate).expand();
    List<List<TokenEntry>> suffixes = actualTokenPredicatesCondition.getSuffixes();
    assertEquals(1, suffixes.size());
    List<TokenEntry> getResult = suffixes.get(0);
    assertEquals(1, getResult.size());
    assertEquals(1, actualTokenPredicatesCondition.getMaxSuffixLength());
    assertSame(suffixPredicate, getResult.get(0));
  }
}
