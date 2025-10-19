package org.jkiss.dbeaver.model.lsm.sql.impl.syntax;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.ErrorNodeImpl;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.tree.pattern.RuleTagToken;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardParser.DefaultClauseContext;
import org.jkiss.dbeaver.model.stm.STMTreeNode;
import org.jkiss.dbeaver.model.stm.STMTreeTermErrorNode;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SyntaxParserTestDiffblueTest {
  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getRealInterval()).thenReturn(Interval.of(1, 1));

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getChildNode(anyInt())).thenReturn(defaultClauseContext);
    when(defaultClauseContext2.getChild(anyInt()))
        .thenReturn(new TerminalNodeImpl(new CommonToken(1)));
    when(defaultClauseContext2.getChildCount()).thenReturn(1);
    when(defaultClauseContext2.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext2.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(ctx.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext2, atLeast(1)).getChild(0);
    verify(defaultClauseContext2, atLeast(1)).getChildCount();
    verify(defaultClauseContext2).getAltNumber();
    verify(defaultClauseContext2).getRuleContext();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext2).getChildNode(0);
    verify(defaultClauseContext2).getRealInterval();
    verify(defaultClauseContext).getRealInterval();
    assertEquals(
        "\nPayload.defaultClause:10 [1..1]\n    DelimitedIdentifier \"null\" [1..1]\n",
        actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx2() {
    // Arrange
    TerminalNodeImpl terminalNodeImpl = mock(TerminalNodeImpl.class);
    when(terminalNodeImpl.getSymbol()).thenReturn(new RuleTagToken("Rule Name", 1));
    when(terminalNodeImpl.getPayload()).thenReturn(new CommonToken(1));

    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getRealInterval()).thenReturn(Interval.of(1, 1));

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getChildNode(anyInt())).thenReturn(defaultClauseContext);
    when(defaultClauseContext2.getChild(anyInt())).thenReturn(terminalNodeImpl);
    when(defaultClauseContext2.getChildCount()).thenReturn(1);
    when(defaultClauseContext2.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext2.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(ctx.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext2, atLeast(1)).getChild(0);
    verify(defaultClauseContext2, atLeast(1)).getChildCount();
    verify(defaultClauseContext2).getAltNumber();
    verify(defaultClauseContext2).getRuleContext();
    verify(terminalNodeImpl, atLeast(1)).getPayload();
    verify(terminalNodeImpl).getSymbol();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext2).getChildNode(0);
    verify(defaultClauseContext2).getRealInterval();
    verify(defaultClauseContext).getRealInterval();
    assertEquals(
        "\nPayload.defaultClause:10 [1..1]\n    DelimitedIdentifier \"<Rule Name>\" [1..1]\n",
        actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Given {@link TerminalNodeImpl} {@link TerminalNodeImpl#getSymbol()} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_givenTerminalNodeImplGetSymbolReturnNull() {
    // Arrange
    TerminalNodeImpl terminalNodeImpl = mock(TerminalNodeImpl.class);
    when(terminalNodeImpl.getSymbol()).thenReturn(null);
    when(terminalNodeImpl.getPayload()).thenReturn(new CommonToken(1));

    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getRealInterval()).thenReturn(Interval.of(1, 1));

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getChildNode(anyInt())).thenReturn(defaultClauseContext);
    when(defaultClauseContext2.getChild(anyInt())).thenReturn(terminalNodeImpl);
    when(defaultClauseContext2.getChildCount()).thenReturn(1);
    when(defaultClauseContext2.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext2.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(ctx.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext2, atLeast(1)).getChild(0);
    verify(defaultClauseContext2, atLeast(1)).getChildCount();
    verify(defaultClauseContext2).getAltNumber();
    verify(defaultClauseContext2).getRuleContext();
    verify(terminalNodeImpl, atLeast(1)).getPayload();
    verify(terminalNodeImpl).getSymbol();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext2).getChildNode(0);
    verify(defaultClauseContext2).getRealInterval();
    verify(defaultClauseContext).getRealInterval();
    assertEquals(
        "\nPayload.defaultClause:10 [1..1]\n    DelimitedIdentifier \"null\" [1..1]\n",
        actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Given {@code Text}.
   *   <li>When {@link Token} {@link Token#getStartIndex()} return one.
   *   <li>Then return {@code Text [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_givenText_whenTokenGetStartIndexReturnOne_thenReturnText11() {
    // Arrange
    Token symbol = mock(Token.class);
    when(symbol.getStartIndex()).thenReturn(1);
    when(symbol.getStopIndex()).thenReturn(1);
    when(symbol.getType()).thenReturn(1);
    when(symbol.getText()).thenReturn("Text");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(new STMTreeTermErrorNode(symbol));

    // Assert
    verify(symbol).getStartIndex();
    verify(symbol).getStopIndex();
    verify(symbol).getText();
    verify(symbol).getType();
    assertEquals("\nText [1..1]\n", actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Given three.
   *   <li>Then return {@code Payload [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_givenThree_thenReturnPayload11() {
    // Arrange
    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildCount()).thenReturn(3);
    when(ctx.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(ctx.getChildren()).thenReturn(new ArrayList<>());
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(ctx, atLeast(1)).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildren();
    verify(ctx).getRealInterval();
    assertEquals("\nPayload [1..1]\n", actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code " [0..0]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturn00() {
    // Arrange, Act and Assert
    assertEquals(
        "\n\" [0..0]\n",
        SyntaxParserTest.collect(new STMTreeTermErrorNode(new CommonToken(1, "\""))));
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code [1].defaultClause:10 [1..1] DelimitedIdentifier "null" [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturn1DefaultClause1011DelimitedIdentifierNull11() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getPayload()).thenReturn(ParserRuleContext.EMPTY);

    TerminalNodeImpl terminalNodeImpl = mock(TerminalNodeImpl.class);
    when(terminalNodeImpl.getSymbol()).thenReturn(new CommonToken(1));
    when(terminalNodeImpl.getPayload()).thenReturn(new CommonToken(1));

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));

    DefaultClauseContext defaultClauseContext3 = mock(DefaultClauseContext.class);
    when(defaultClauseContext3.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(defaultClauseContext3.getChild(anyInt())).thenReturn(terminalNodeImpl);
    when(defaultClauseContext3.getChildCount()).thenReturn(1);
    when(defaultClauseContext3.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext3.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext3.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext3);
    when(ctx.getChild(anyInt())).thenReturn(defaultClauseContext);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext3, atLeast(1)).getChild(0);
    verify(defaultClauseContext3, atLeast(1)).getChildCount();
    verify(defaultClauseContext3).getAltNumber();
    verify(defaultClauseContext).getPayload();
    verify(defaultClauseContext3).getRuleContext();
    verify(terminalNodeImpl, atLeast(1)).getPayload();
    verify(terminalNodeImpl).getSymbol();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext3).getChildNode(0);
    verify(defaultClauseContext3).getRealInterval();
    verify(defaultClauseContext2).getRealInterval();
    assertEquals(
        "\n[1].defaultClause:10 [1..1]\n    DelimitedIdentifier \"null\" [1..1]\n",
        actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code <">.defaultClause:10 [1..1] DelimitedIdentifier "null" [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnDefaultClause1011DelimitedIdentifierNull11() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getPayload()).thenReturn(ParserRuleContext.EMPTY);

    TerminalNodeImpl terminalNodeImpl = mock(TerminalNodeImpl.class);
    when(terminalNodeImpl.getSymbol()).thenReturn(new CommonToken(1));
    when(terminalNodeImpl.getPayload()).thenReturn(new CommonToken(1));

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));

    DefaultClauseContext defaultClauseContext3 = mock(DefaultClauseContext.class);
    when(defaultClauseContext3.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(defaultClauseContext3.getChild(anyInt())).thenReturn(terminalNodeImpl);
    when(defaultClauseContext3.getChildCount()).thenReturn(1);
    when(defaultClauseContext3.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext3.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext3.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext3);
    when(ctx.getChild(anyInt())).thenReturn(defaultClauseContext);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn(new RuleTagToken("\"", 1));

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext3, atLeast(1)).getChild(0);
    verify(defaultClauseContext3, atLeast(1)).getChildCount();
    verify(defaultClauseContext3).getAltNumber();
    verify(defaultClauseContext).getPayload();
    verify(defaultClauseContext3).getRuleContext();
    verify(terminalNodeImpl, atLeast(1)).getPayload();
    verify(terminalNodeImpl).getSymbol();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext3).getChildNode(0);
    verify(defaultClauseContext3).getRealInterval();
    verify(defaultClauseContext2).getRealInterval();
    assertEquals(
        "\n<\">.defaultClause:10 [1..1]\n    DelimitedIdentifier \"null\" [1..1]\n",
        actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code <EOF> [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnEof11() {
    // Arrange
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source = new Pair<>(sqlStandardLexer, new ANTLRInputStream("\""));
    CommonToken symbol = new CommonToken(source, 1, 1, 1, 1);

    // Act and Assert
    assertEquals("\n<EOF> [1..1]\n", SyntaxParserTest.collect(new STMTreeTermErrorNode(symbol)));
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code null.defaultClause:10 [1..1] DelimitedIdentifier "null" [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnNullDefaultClause1011DelimitedIdentifierNull11() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getPayload()).thenReturn(ParserRuleContext.EMPTY);

    TerminalNodeImpl terminalNodeImpl = mock(TerminalNodeImpl.class);
    when(terminalNodeImpl.getSymbol()).thenReturn(new CommonToken(1));
    when(terminalNodeImpl.getPayload()).thenReturn(new CommonToken(1));

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));

    DefaultClauseContext defaultClauseContext3 = mock(DefaultClauseContext.class);
    when(defaultClauseContext3.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(defaultClauseContext3.getChild(anyInt())).thenReturn(terminalNodeImpl);
    when(defaultClauseContext3.getChildCount()).thenReturn(1);
    when(defaultClauseContext3.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext3.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext3.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext3);
    when(ctx.getChild(anyInt())).thenReturn(defaultClauseContext);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn(new ErrorNodeImpl(new CommonToken(1)));

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext3, atLeast(1)).getChild(0);
    verify(defaultClauseContext3, atLeast(1)).getChildCount();
    verify(defaultClauseContext3).getAltNumber();
    verify(defaultClauseContext).getPayload();
    verify(defaultClauseContext3).getRuleContext();
    verify(terminalNodeImpl, atLeast(1)).getPayload();
    verify(terminalNodeImpl).getSymbol();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext3).getChildNode(0);
    verify(defaultClauseContext3).getRealInterval();
    verify(defaultClauseContext2).getRealInterval();
    assertEquals(
        "\nnull.defaultClause:10 [1..1]\n    DelimitedIdentifier \"null\" [1..1]\n",
        actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code Payload [1..1] DelimitedIdentifier "null" [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnPayload11DelimitedIdentifierNull11() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getRealInterval()).thenReturn(Interval.of(1, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext);
    when(ctx.getChild(anyInt())).thenReturn(new ErrorNodeImpl(new CommonToken(1)));
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(ctx, atLeast(1)).getChild(0);
    verify(ctx, atLeast(1)).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(ctx).getRealInterval();
    verify(defaultClauseContext).getRealInterval();
    assertEquals(
        "\nPayload [1..1]\n    DelimitedIdentifier \"null\" [1..1]\n", actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code Payload.defaultClause:10.defaultClause [-1..-2]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnPayloadDefaultClause10DefaultClause12() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getChildNode(anyInt()))
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));
    when(defaultClauseContext.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(defaultClauseContext.getChildCount()).thenReturn(1);
    when(defaultClauseContext.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext);
    when(ctx.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext).getChild(0);
    verify(defaultClauseContext).getChildCount();
    verify(defaultClauseContext).getAltNumber();
    verify(defaultClauseContext).getRuleContext();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext).getChildNode(0);
    assertEquals("\nPayload.defaultClause:10.defaultClause [-1..-2]\n", actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code Payload.defaultClause [-1..-2]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnPayloadDefaultClause12() {
    // Arrange
    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt()))
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));
    when(ctx.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    assertEquals("\nPayload.defaultClause [-1..-2]\n", actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code Payload.defaultClause:10 [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnPayloadDefaultClause1011() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getChildren()).thenReturn(new ArrayList<>());
    when(defaultClauseContext.getChildCount()).thenReturn(3);
    when(defaultClauseContext.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext);
    when(ctx.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext, atLeast(1)).getChildCount();
    verify(defaultClauseContext).getAltNumber();
    verify(defaultClauseContext).getRuleContext();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext).getChildren();
    verify(defaultClauseContext).getRealInterval();
    assertEquals("\nPayload.defaultClause:10 [1..1]\n", actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code Payload.defaultClause:10 [1..1] DelimitedIdentifier "<">" [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnPayloadDefaultClause1011DelimitedIdentifier11() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getRealInterval()).thenReturn(Interval.of(1, 1));

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getChildNode(anyInt())).thenReturn(defaultClauseContext);
    when(defaultClauseContext2.getChild(anyInt()))
        .thenReturn(new ErrorNodeImpl(new RuleTagToken("\"", 1)));
    when(defaultClauseContext2.getChildCount()).thenReturn(1);
    when(defaultClauseContext2.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext2.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(ctx.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext2, atLeast(1)).getChild(0);
    verify(defaultClauseContext2, atLeast(1)).getChildCount();
    verify(defaultClauseContext2).getAltNumber();
    verify(defaultClauseContext2).getRuleContext();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext2).getChildNode(0);
    verify(defaultClauseContext2).getRealInterval();
    verify(defaultClauseContext).getRealInterval();
    assertEquals(
        "\nPayload.defaultClause:10 [1..1]\n    DelimitedIdentifier \"<\">\" [1..1]\n",
        actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code Payload.defaultClause:10 [1..1] DelimitedIdentifier "null" [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnPayloadDefaultClause1011DelimitedIdentifierNull11() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getRealInterval()).thenReturn(Interval.of(1, 1));

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getChildNode(anyInt())).thenReturn(defaultClauseContext);
    when(defaultClauseContext2.getChild(anyInt()))
        .thenReturn(new ErrorNodeImpl(new CommonToken(1)));
    when(defaultClauseContext2.getChildCount()).thenReturn(1);
    when(defaultClauseContext2.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext2.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(ctx.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext2, atLeast(1)).getChild(0);
    verify(defaultClauseContext2, atLeast(1)).getChildCount();
    verify(defaultClauseContext2).getAltNumber();
    verify(defaultClauseContext2).getRuleContext();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext2).getChildNode(0);
    verify(defaultClauseContext2).getRealInterval();
    verify(defaultClauseContext).getRealInterval();
    assertEquals(
        "\nPayload.defaultClause:10 [1..1]\n    DelimitedIdentifier \"null\" [1..1]\n",
        actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code Payload.defaultClause:10 [1..1] DelimitedIdentifier "null" [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnPayloadDefaultClause1011DelimitedIdentifierNull112() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getPayload()).thenReturn(ParserRuleContext.EMPTY);

    TerminalNodeImpl terminalNodeImpl = mock(TerminalNodeImpl.class);
    when(terminalNodeImpl.getSymbol()).thenReturn(new CommonToken(1));
    when(terminalNodeImpl.getPayload()).thenReturn(new CommonToken(1));

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));

    DefaultClauseContext defaultClauseContext3 = mock(DefaultClauseContext.class);
    when(defaultClauseContext3.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(defaultClauseContext3.getChild(anyInt())).thenReturn(terminalNodeImpl);
    when(defaultClauseContext3.getChildCount()).thenReturn(1);
    when(defaultClauseContext3.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext3.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext3.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext3);
    when(ctx.getChild(anyInt())).thenReturn(defaultClauseContext);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext3, atLeast(1)).getChild(0);
    verify(defaultClauseContext3, atLeast(1)).getChildCount();
    verify(defaultClauseContext3).getAltNumber();
    verify(defaultClauseContext).getPayload();
    verify(defaultClauseContext3).getRuleContext();
    verify(terminalNodeImpl, atLeast(1)).getPayload();
    verify(terminalNodeImpl).getSymbol();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext3).getChildNode(0);
    verify(defaultClauseContext3).getRealInterval();
    verify(defaultClauseContext2).getRealInterval();
    assertEquals(
        "\nPayload.defaultClause:10 [1..1]\n    DelimitedIdentifier \"null\" [1..1]\n",
        actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code Payload.defaultClause:10 [1..1] DelimitedIdentifier "null" [null]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnPayloadDefaultClause1011DelimitedIdentifierNullNull() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getRealInterval()).thenReturn(null);

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getChildNode(anyInt())).thenReturn(defaultClauseContext);
    when(defaultClauseContext2.getChild(anyInt()))
        .thenReturn(new ErrorNodeImpl(new CommonToken(1)));
    when(defaultClauseContext2.getChildCount()).thenReturn(1);
    when(defaultClauseContext2.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext2.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(ctx.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext2, atLeast(1)).getChild(0);
    verify(defaultClauseContext2, atLeast(1)).getChildCount();
    verify(defaultClauseContext2).getAltNumber();
    verify(defaultClauseContext2).getRuleContext();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext2).getChildNode(0);
    verify(defaultClauseContext2).getRealInterval();
    verify(defaultClauseContext).getRealInterval();
    assertEquals(
        "\nPayload.defaultClause:10 [1..1]\n    DelimitedIdentifier \"null\" [null]\n",
        actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code Payload.defaultClause:10 [1..1] EOF "<EOF>" [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnPayloadDefaultClause1011EofEof11() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getRealInterval()).thenReturn(Interval.of(1, 1));

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getChildNode(anyInt())).thenReturn(defaultClauseContext);
    when(defaultClauseContext2.getChild(anyInt()))
        .thenReturn(new ErrorNodeImpl(new CommonToken(-1)));
    when(defaultClauseContext2.getChildCount()).thenReturn(1);
    when(defaultClauseContext2.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext2.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(ctx.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext2, atLeast(1)).getChild(0);
    verify(defaultClauseContext2, atLeast(1)).getChildCount();
    verify(defaultClauseContext2).getAltNumber();
    verify(defaultClauseContext2).getRuleContext();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext2).getChildNode(0);
    verify(defaultClauseContext2).getRealInterval();
    verify(defaultClauseContext).getRealInterval();
    assertEquals(
        "\nPayload.defaultClause:10 [1..1]\n    EOF \"<EOF>\" [1..1]\n", actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code Payload.defaultClause:10 [1..1] 0 "null" [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnPayloadDefaultClause10110Null11() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getRealInterval()).thenReturn(Interval.of(1, 1));

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getChildNode(anyInt())).thenReturn(defaultClauseContext);
    when(defaultClauseContext2.getChild(anyInt()))
        .thenReturn(new ErrorNodeImpl(new CommonToken(0)));
    when(defaultClauseContext2.getChildCount()).thenReturn(1);
    when(defaultClauseContext2.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext2.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(ctx.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext2, atLeast(1)).getChild(0);
    verify(defaultClauseContext2, atLeast(1)).getChildCount();
    verify(defaultClauseContext2).getAltNumber();
    verify(defaultClauseContext2).getRuleContext();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext2).getChildNode(0);
    verify(defaultClauseContext2).getRealInterval();
    verify(defaultClauseContext).getRealInterval();
    assertEquals("\nPayload.defaultClause:10 [1..1]\n    0 \"null\" [1..1]\n", actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code Payload.defaultClause:10 [1..1] -2147483648 "null" [1..1]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnPayloadDefaultClause10112147483648Null11() {
    // Arrange
    DefaultClauseContext defaultClauseContext = mock(DefaultClauseContext.class);
    when(defaultClauseContext.getRealInterval()).thenReturn(Interval.of(1, 1));

    DefaultClauseContext defaultClauseContext2 = mock(DefaultClauseContext.class);
    when(defaultClauseContext2.getChildNode(anyInt())).thenReturn(defaultClauseContext);
    when(defaultClauseContext2.getChild(anyInt()))
        .thenReturn(new ErrorNodeImpl(new CommonToken(Integer.MIN_VALUE)));
    when(defaultClauseContext2.getChildCount()).thenReturn(1);
    when(defaultClauseContext2.getAltNumber()).thenReturn(SQLStandardLexer.AND);
    when(defaultClauseContext2.getRealInterval()).thenReturn(Interval.of(1, 1));
    when(defaultClauseContext2.getRuleContext())
        .thenReturn(new DefaultClauseContext(ParserRuleContext.EMPTY, 1));

    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildNode(anyInt())).thenReturn(defaultClauseContext2);
    when(ctx.getChild(anyInt())).thenReturn(ParserRuleContext.EMPTY);
    when(ctx.getChildCount()).thenReturn(1);
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(defaultClauseContext2, atLeast(1)).getChild(0);
    verify(defaultClauseContext2, atLeast(1)).getChildCount();
    verify(defaultClauseContext2).getAltNumber();
    verify(defaultClauseContext2).getRuleContext();
    verify(ctx).getChild(0);
    verify(ctx).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildNode(0);
    verify(defaultClauseContext2).getChildNode(0);
    verify(defaultClauseContext2).getRealInterval();
    verify(defaultClauseContext).getRealInterval();
    assertEquals(
        "\nPayload.defaultClause:10 [1..1]\n    -2147483648 \"null\" [1..1]\n",
        actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>Then return {@code Payload [null]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_thenReturnPayloadNull() {
    // Arrange
    STMTreeNode ctx = mock(STMTreeNode.class);
    when(ctx.getChildCount()).thenReturn(3);
    when(ctx.getRealInterval()).thenReturn(null);
    when(ctx.getChildren()).thenReturn(new ArrayList<>());
    when(ctx.getPayload()).thenReturn("Payload");

    // Act
    String actualCollectResult = SyntaxParserTest.collect(ctx);

    // Assert
    verify(ctx, atLeast(1)).getChildCount();
    verify(ctx, atLeast(1)).getPayload();
    verify(ctx).getChildren();
    verify(ctx).getRealInterval();
    assertEquals("\nPayload [null]\n", actualCollectResult);
  }

  /**
   * Test {@link SyntaxParserTest#collect(STMTreeNode)} with {@code ctx}.
   *
   * <ul>
   *   <li>When {@link CommonToken#CommonToken(int)} with type is one.
   *   <li>Then return {@code null [0..0]}.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxParserTest#collect(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String SyntaxParserTest.collect(STMTreeNode)"})
  public void testCollectWithCtx_whenCommonTokenWithTypeIsOne_thenReturnNull00() {
    // Arrange, Act and Assert
    assertEquals(
        "\nnull [0..0]\n", SyntaxParserTest.collect(new STMTreeTermErrorNode(new CommonToken(1))));
  }
}
