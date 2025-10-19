package org.jkiss.dbeaver.model.stm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Pair;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardLexer;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardParser;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class STMTreeTermErrorNodeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link STMTreeTermErrorNode#STMTreeTermErrorNode(Token)}
   *   <li>{@link STMTreeTermErrorNode#fixup(STMParserOverrides)}
   *   <li>{@link STMTreeTermErrorNode#getAtnState()}
   *   <li>{@link STMTreeTermErrorNode#getNodeName()}
   *   <li>{@link STMTreeTermErrorNode#hasErrorChildren()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void STMTreeTermErrorNode.<init>(Token)",
    "void STMTreeTermErrorNode.fixup(STMParserOverrides)",
    "int STMTreeTermErrorNode.getAtnState()",
    "String STMTreeTermErrorNode.getNodeName()",
    "boolean STMTreeTermErrorNode.hasErrorChildren()"
  })
  public void testGettersAndSetters() {
    // Arrange
    CommonToken symbol = new CommonToken(1);

    // Act
    STMTreeTermErrorNode actualStmTreeTermErrorNode = new STMTreeTermErrorNode(symbol);
    SQLStandardLexer tokenSource = new SQLStandardLexer(new ANTLRInputStream("Input"));
    BufferedTokenStream input = new BufferedTokenStream(tokenSource);
    actualStmTreeTermErrorNode.fixup(new SQLStandardParser(input));
    int actualAtnState = actualStmTreeTermErrorNode.getAtnState();
    String actualNodeName = actualStmTreeTermErrorNode.getNodeName();
    boolean actualHasErrorChildrenResult = actualStmTreeTermErrorNode.hasErrorChildren();

    // Assert
    assertEquals("error", actualNodeName);
    assertNull(actualStmTreeTermErrorNode.getParent());
    assertEquals(-1, actualAtnState);
    assertFalse(actualHasErrorChildrenResult);
    assertSame(symbol, actualStmTreeTermErrorNode.getPayload());
    assertSame(symbol, actualStmTreeTermErrorNode.getSymbol());
  }

  /**
   * Test {@link STMTreeTermErrorNode#getRealInterval()}.
   *
   * <ul>
   *   <li>Given {@link CommonToken#CommonToken(int)} with type is one.
   *   <li>Then return {@link Interval#a} is zero.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeTermErrorNode#getRealInterval()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Interval STMTreeTermErrorNode.getRealInterval()"})
  public void testGetRealInterval_givenCommonTokenWithTypeIsOne_thenReturnAIsZero() {
    // Arrange and Act
    Interval actualRealInterval = new STMTreeTermErrorNode(new CommonToken(1)).getRealInterval();

    // Assert
    assertEquals(0, actualRealInterval.a);
    assertEquals(0, actualRealInterval.b);
    assertEquals(1, actualRealInterval.length());
  }

  /**
   * Test {@link STMTreeTermErrorNode#getTextContent()}.
   *
   * <ul>
   *   <li>Given {@link SQLStandardLexer#SQLStandardLexer(CharStream)} with input is {@link
   *       ANTLRInputStream#ANTLRInputStream()}.
   *   <li>Then return {@code n}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeTermErrorNode#getTextContent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String STMTreeTermErrorNode.getTextContent()"})
  public void testGetTextContent_givenSQLStandardLexerWithInputIsANTLRInputStream_thenReturnN() {
    // Arrange
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 1, 1);

    // Act and Assert
    assertEquals("n", new STMTreeTermErrorNode(symbol).getTextContent());
  }
}
