package org.jkiss.dbeaver.model.stm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardLexer;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardParser;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class STMParserOverridesDiffblueTest {
  /**
   * Test {@link STMParserOverrides#createErrorNode(ParserRuleContext, Token)}.
   *
   * <p>Method under test: {@link STMParserOverrides#createErrorNode(ParserRuleContext, Token)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ErrorNode STMParserOverrides.createErrorNode(ParserRuleContext, Token)"})
  public void testCreateErrorNode() {
    // Arrange
    SQLStandardLexer tokenSource = new SQLStandardLexer(new ANTLRInputStream("Input"));
    BufferedTokenStream input = new BufferedTokenStream(tokenSource);
    SQLStandardParser sqlStandardParser = new SQLStandardParser(input);
    CommonToken t = new CommonToken(1);

    // Act
    ErrorNode actualCreateErrorNodeResult =
        sqlStandardParser.createErrorNode(ParserRuleContext.EMPTY, t);

    // Assert
    Object payload = actualCreateErrorNodeResult.getPayload();
    assertTrue(payload instanceof CommonToken);
    assertTrue(actualCreateErrorNodeResult instanceof STMTreeTermErrorNode);
    assertEquals("error", ((STMTreeTermErrorNode) actualCreateErrorNodeResult).getNodeName());
    assertNull(actualCreateErrorNodeResult.getText());
    assertNull(actualCreateErrorNodeResult.toStringTree());
    assertNull(actualCreateErrorNodeResult.getParent());
    assertNull(((STMTreeTermErrorNode) actualCreateErrorNodeResult).findFirstNonErrorChild());
    assertNull(((STMTreeTermErrorNode) actualCreateErrorNodeResult).findLastNonErrorChild());
    assertNull(((STMTreeTermErrorNode) actualCreateErrorNodeResult).getParentNode());
    assertEquals(-1, ((STMTreeTermErrorNode) actualCreateErrorNodeResult).getNodeKindId());
    assertEquals(-1, ((STMTreeTermErrorNode) actualCreateErrorNodeResult).getAtnState());
    assertEquals(0, actualCreateErrorNodeResult.getChildCount());
    assertFalse(((STMTreeTermErrorNode) actualCreateErrorNodeResult).hasErrorChildren());
    assertTrue(((STMTreeTermErrorNode) actualCreateErrorNodeResult).getChildren().isEmpty());
    assertSame(t, actualCreateErrorNodeResult.getSymbol());
    assertSame(t, payload);
  }

  /**
   * Test {@link STMParserOverrides#createTerminalNode(ParserRuleContext, Token)}.
   *
   * <p>Method under test: {@link STMParserOverrides#createTerminalNode(ParserRuleContext, Token)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TerminalNode STMParserOverrides.createTerminalNode(ParserRuleContext, Token)"
  })
  public void testCreateTerminalNode() {
    // Arrange
    SQLStandardLexer tokenSource = new SQLStandardLexer(new ANTLRInputStream("Input"));
    BufferedTokenStream input = new BufferedTokenStream(tokenSource);
    SQLStandardParser sqlStandardParser = new SQLStandardParser(input);
    CommonToken t = new CommonToken(1);

    // Act
    TerminalNode actualCreateTerminalNodeResult =
        sqlStandardParser.createTerminalNode(ParserRuleContext.EMPTY, t);

    // Assert
    Object payload = actualCreateTerminalNodeResult.getPayload();
    assertTrue(payload instanceof CommonToken);
    assertTrue(actualCreateTerminalNodeResult instanceof STMTreeTermNode);
    assertNull(actualCreateTerminalNodeResult.getText());
    assertNull(actualCreateTerminalNodeResult.toStringTree());
    assertNull(((STMTreeTermNode) actualCreateTerminalNodeResult).getNodeName());
    assertNull(actualCreateTerminalNodeResult.getParent());
    assertNull(((STMTreeTermNode) actualCreateTerminalNodeResult).findFirstNonErrorChild());
    assertNull(((STMTreeTermNode) actualCreateTerminalNodeResult).findLastNonErrorChild());
    assertNull(((STMTreeTermNode) actualCreateTerminalNodeResult).getParentNode());
    assertEquals(-1, ((STMTreeTermNode) actualCreateTerminalNodeResult).getNodeKindId());
    assertEquals(-1, ((STMTreeTermNode) actualCreateTerminalNodeResult).getAtnState());
    assertEquals(0, actualCreateTerminalNodeResult.getChildCount());
    assertFalse(((STMTreeTermNode) actualCreateTerminalNodeResult).hasErrorChildren());
    assertTrue(((STMTreeTermNode) actualCreateTerminalNodeResult).getChildren().isEmpty());
    assertSame(t, actualCreateTerminalNodeResult.getSymbol());
    assertSame(t, payload);
  }
}
