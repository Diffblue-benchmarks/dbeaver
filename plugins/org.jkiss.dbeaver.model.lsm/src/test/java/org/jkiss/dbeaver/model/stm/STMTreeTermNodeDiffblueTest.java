package org.jkiss.dbeaver.model.stm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Pair;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardLexer;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class STMTreeTermNodeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link CommonToken#CommonToken(int)} with type is one.
   *   <li>Then return AtnState is minus one.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link STMTreeTermNode#STMTreeTermNode(Token)}
   *   <li>{@link STMTreeTermNode#getAtnState()}
   *   <li>{@link STMTreeTermNode#getNodeName()}
   *   <li>{@link STMTreeTermNode#hasErrorChildren()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void STMTreeTermNode.<init>(Token)",
    "void STMTreeTermNode.<init>(Token, int)",
    "int STMTreeTermNode.getAtnState()",
    "String STMTreeTermNode.getNodeName()",
    "boolean STMTreeTermNode.hasErrorChildren()"
  })
  public void testGettersAndSetters_whenCommonTokenWithTypeIsOne_thenReturnAtnStateIsMinusOne() {
    // Arrange
    CommonToken symbol = new CommonToken(1);

    // Act
    STMTreeTermNode actualStmTreeTermNode = new STMTreeTermNode(symbol);
    int actualAtnState = actualStmTreeTermNode.getAtnState();
    String actualNodeName = actualStmTreeTermNode.getNodeName();
    boolean actualHasErrorChildrenResult = actualStmTreeTermNode.hasErrorChildren();

    // Assert
    assertNull(actualNodeName);
    assertNull(actualStmTreeTermNode.getParent());
    assertEquals(-1, actualAtnState);
    assertFalse(actualHasErrorChildrenResult);
    assertSame(symbol, actualStmTreeTermNode.getPayload());
    assertSame(symbol, actualStmTreeTermNode.getSymbol());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then return AtnState is one.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link STMTreeTermNode#STMTreeTermNode(Token, int)}
   *   <li>{@link STMTreeTermNode#getAtnState()}
   *   <li>{@link STMTreeTermNode#getNodeName()}
   *   <li>{@link STMTreeTermNode#hasErrorChildren()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void STMTreeTermNode.<init>(Token)",
    "void STMTreeTermNode.<init>(Token, int)",
    "int STMTreeTermNode.getAtnState()",
    "String STMTreeTermNode.getNodeName()",
    "boolean STMTreeTermNode.hasErrorChildren()"
  })
  public void testGettersAndSetters_whenOne_thenReturnAtnStateIsOne() {
    // Arrange
    CommonToken symbol = new CommonToken(1);

    // Act
    STMTreeTermNode actualStmTreeTermNode = new STMTreeTermNode(symbol, 1);
    int actualAtnState = actualStmTreeTermNode.getAtnState();
    String actualNodeName = actualStmTreeTermNode.getNodeName();
    boolean actualHasErrorChildrenResult = actualStmTreeTermNode.hasErrorChildren();

    // Assert
    assertNull(actualNodeName);
    assertNull(actualStmTreeTermNode.getParent());
    assertEquals(1, actualAtnState);
    assertFalse(actualHasErrorChildrenResult);
    assertSame(symbol, actualStmTreeTermNode.getPayload());
    assertSame(symbol, actualStmTreeTermNode.getSymbol());
  }

  /**
   * Test {@link STMTreeTermNode#getRealInterval()}.
   *
   * <ul>
   *   <li>Given {@link CommonToken#CommonToken(int)} with type is one.
   *   <li>Then return {@link Interval#a} is zero.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeTermNode#getRealInterval()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Interval STMTreeTermNode.getRealInterval()"})
  public void testGetRealInterval_givenCommonTokenWithTypeIsOne_thenReturnAIsZero() {
    // Arrange and Act
    Interval actualRealInterval = new STMTreeTermNode(new CommonToken(1)).getRealInterval();

    // Assert
    assertEquals(0, actualRealInterval.a);
    assertEquals(0, actualRealInterval.b);
    assertEquals(1, actualRealInterval.length());
  }

  /**
   * Test {@link STMTreeTermNode#getTextContent()}.
   *
   * <ul>
   *   <li>Given {@link SQLStandardLexer#SQLStandardLexer(CharStream)} with input is {@link
   *       ANTLRInputStream#ANTLRInputStream()}.
   *   <li>Then return {@code n}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeTermNode#getTextContent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String STMTreeTermNode.getTextContent()"})
  public void testGetTextContent_givenSQLStandardLexerWithInputIsANTLRInputStream_thenReturnN() {
    // Arrange
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 1, 1);

    // Act and Assert
    assertEquals("n", new STMTreeTermNode(symbol).getTextContent());
  }
}
