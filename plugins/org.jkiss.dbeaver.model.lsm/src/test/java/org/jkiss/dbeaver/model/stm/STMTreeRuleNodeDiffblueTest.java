package org.jkiss.dbeaver.model.stm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.InterpreterRuleContext;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ErrorNodeImpl;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.pattern.RuleTagToken;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardLexer;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardParser;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardParser.DefaultClauseContext;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class STMTreeRuleNodeDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Parent is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link STMTreeRuleNode#STMTreeRuleNode()}
   *   <li>{@link STMTreeRuleNode#getAtnState()}
   *   <li>{@link STMTreeRuleNode#getNodeName()}
   *   <li>{@link STMTreeRuleNode#hasErrorChildren()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void STMTreeRuleNode.<init>()",
    "void STMTreeRuleNode.<init>(ParserRuleContext, int)",
    "int STMTreeRuleNode.getAtnState()",
    "String STMTreeRuleNode.getNodeName()",
    "boolean STMTreeRuleNode.hasErrorChildren()"
  })
  public void testGettersAndSetters_thenReturnParentIsNull() {
    // Arrange and Act
    STMTreeRuleNode actualStmTreeRuleNode = new STMTreeRuleNode();
    int actualAtnState = actualStmTreeRuleNode.getAtnState();
    String actualNodeName = actualStmTreeRuleNode.getNodeName();
    boolean actualHasErrorChildrenResult = actualStmTreeRuleNode.hasErrorChildren();

    // Assert
    assertNull(actualNodeName);
    assertNull(actualStmTreeRuleNode.getParent());
    assertNull(actualStmTreeRuleNode.getStart());
    assertNull(actualStmTreeRuleNode.getStop());
    assertEquals(-1, actualAtnState);
    assertFalse(actualHasErrorChildrenResult);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link ParserRuleContext#EMPTY}.
   *   <li>Then return AtnState is ten.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link STMTreeRuleNode#STMTreeRuleNode(ParserRuleContext, int)}
   *   <li>{@link STMTreeRuleNode#getAtnState()}
   *   <li>{@link STMTreeRuleNode#getNodeName()}
   *   <li>{@link STMTreeRuleNode#hasErrorChildren()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void STMTreeRuleNode.<init>()",
    "void STMTreeRuleNode.<init>(ParserRuleContext, int)",
    "int STMTreeRuleNode.getAtnState()",
    "String STMTreeRuleNode.getNodeName()",
    "boolean STMTreeRuleNode.hasErrorChildren()"
  })
  public void testGettersAndSetters_whenEmpty_thenReturnAtnStateIsTen() {
    // Arrange and Act
    STMTreeRuleNode actualStmTreeRuleNode = new STMTreeRuleNode(ParserRuleContext.EMPTY, 10);
    int actualAtnState = actualStmTreeRuleNode.getAtnState();
    String actualNodeName = actualStmTreeRuleNode.getNodeName();
    boolean actualHasErrorChildrenResult = actualStmTreeRuleNode.hasErrorChildren();

    // Assert
    assertNull(actualNodeName);
    assertNull(actualStmTreeRuleNode.getStart());
    assertNull(actualStmTreeRuleNode.getStop());
    assertEquals(10, actualAtnState);
    assertFalse(actualHasErrorChildrenResult);
    assertSame(ParserRuleContext.EMPTY, actualStmTreeRuleNode.getParent());
  }

  /**
   * Test {@link STMTreeRuleNode#getNodeKindId()}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#getNodeKindId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int STMTreeRuleNode.getNodeKindId()"})
  public void testGetNodeKindId_givenSTMTreeRuleNode_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, new STMTreeRuleNode().getNodeKindId());
  }

  /**
   * Test {@link STMTreeRuleNode#getNodeKindId()}.
   *
   * <ul>
   *   <li>Then return thirty-four.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#getNodeKindId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int STMTreeRuleNode.getNodeKindId()"})
  public void testGetNodeKindId_thenReturnThirtyFour() {
    // Arrange, Act and Assert
    assertEquals(34, new DefaultClauseContext(ParserRuleContext.EMPTY, 1).getNodeKindId());
  }

  /**
   * Test {@link STMTreeRuleNode#fixup(STMParserOverrides)}.
   *
   * <p>Method under test: {@link STMTreeRuleNode#fixup(STMParserOverrides)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void STMTreeRuleNode.fixup(STMParserOverrides)"})
  public void testFixup() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode(ParserRuleContext.EMPTY, 10);

    // Act
    stmTreeRuleNode.fixup(null);

    // Assert
    assertEquals("[10]", stmTreeRuleNode.getNodeName());
  }

  /**
   * Test {@link STMTreeRuleNode#fixup(STMParserOverrides)}.
   *
   * <p>Method under test: {@link STMTreeRuleNode#fixup(STMParserOverrides)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void STMTreeRuleNode.fixup(STMParserOverrides)"})
  public void testFixup2() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode =
        new STMTreeRuleNode(new InterpreterRuleContext(ParserRuleContext.EMPTY, 10, 1), 10);

    // Act
    stmTreeRuleNode.fixup(null);

    // Assert
    assertEquals("[10 10]", stmTreeRuleNode.getNodeName());
  }

  /**
   * Test {@link STMTreeRuleNode#fixup(STMParserOverrides)}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()} addErrorNode {@link
   *       CommonToken#CommonToken(int)} with type is one.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#fixup(STMParserOverrides)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void STMTreeRuleNode.fixup(STMParserOverrides)"})
  public void testFixup_givenSTMTreeRuleNodeAddErrorNodeCommonTokenWithTypeIsOne() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    stmTreeRuleNode.addErrorNode(new CommonToken(1));

    // Act
    stmTreeRuleNode.fixup(null);

    // Assert
    assertEquals("[]", stmTreeRuleNode.getNodeName());
  }

  /**
   * Test {@link STMTreeRuleNode#fixup(STMParserOverrides)}.
   *
   * <ul>
   *   <li>Then {@link STMTreeRuleNode#STMTreeRuleNode()} NodeName is {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#fixup(STMParserOverrides)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void STMTreeRuleNode.fixup(STMParserOverrides)"})
  public void testFixup_thenSTMTreeRuleNodeNodeNameIsLeftSquareBracketRightSquareBracket() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();

    // Act
    stmTreeRuleNode.fixup(null);

    // Assert
    assertEquals("[]", stmTreeRuleNode.getNodeName());
  }

  /**
   * Test {@link STMTreeRuleNode#getRealInterval()}.
   *
   * <p>Method under test: {@link STMTreeRuleNode#getRealInterval()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Interval STMTreeRuleNode.getRealInterval()"})
  public void testGetRealInterval() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode(ParserRuleContext.EMPTY, 10);
    stmTreeRuleNode.start = new CommonToken(1);
    stmTreeRuleNode.stop = null;

    // Act
    Interval actualRealInterval = stmTreeRuleNode.getRealInterval();

    // Assert
    Interval interval = Interval.INVALID;
    assertEquals(interval, stmTreeRuleNode.getSourceInterval());
    assertSame(interval, actualRealInterval);
  }

  /**
   * Test {@link STMTreeRuleNode#getRealInterval()}.
   *
   * <p>Method under test: {@link STMTreeRuleNode#getRealInterval()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Interval STMTreeRuleNode.getRealInterval()"})
  public void testGetRealInterval2() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode(ParserRuleContext.EMPTY, 10);
    stmTreeRuleNode.start = new CommonToken(1);
    stmTreeRuleNode.stop = new CommonToken(1);

    // Act
    Interval actualRealInterval = stmTreeRuleNode.getRealInterval();

    // Assert
    assertEquals(0, actualRealInterval.a);
    assertEquals(0, actualRealInterval.b);
    assertEquals(1, actualRealInterval.length());
  }

  /**
   * Test {@link STMTreeRuleNode#getRealInterval()}.
   *
   * <p>Method under test: {@link STMTreeRuleNode#getRealInterval()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Interval STMTreeRuleNode.getRealInterval()"})
  public void testGetRealInterval3() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode(ParserRuleContext.EMPTY, 10);
    stmTreeRuleNode.start = new CommonToken(1);
    stmTreeRuleNode.stop = new RuleTagToken("Rule Name", 1);

    // Act
    Interval actualRealInterval = stmTreeRuleNode.getRealInterval();

    // Assert
    assertEquals(0, actualRealInterval.a);
    assertEquals(0, actualRealInterval.b);
    assertEquals(1, actualRealInterval.length());
  }

  /**
   * Test {@link STMTreeRuleNode#getRealInterval()}.
   *
   * <ul>
   *   <li>Then {@link STMTreeRuleNode#STMTreeRuleNode()} SourceInterval is {@link
   *       Interval#INVALID}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#getRealInterval()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Interval STMTreeRuleNode.getRealInterval()"})
  public void testGetRealInterval_thenSTMTreeRuleNodeSourceIntervalIsInvalid() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();

    // Act
    Interval actualRealInterval = stmTreeRuleNode.getRealInterval();

    // Assert
    Interval interval = Interval.INVALID;
    assertSame(interval, stmTreeRuleNode.getSourceInterval());
    assertSame(interval, actualRealInterval);
  }

  /**
   * Test {@link STMTreeRuleNode#getTextContent()}.
   *
   * <ul>
   *   <li>Given {@link ANTLRInputStream#ANTLRInputStream(String)} with {@code Input}.
   *   <li>Then return {@code n}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#getTextContent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String STMTreeRuleNode.getTextContent()"})
  public void testGetTextContent_givenANTLRInputStreamWithInput_thenReturnN() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode(ParserRuleContext.EMPTY, 10);
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken commonToken = new CommonToken(source, 1, 1, 1, 1);
    stmTreeRuleNode.start = commonToken;
    stmTreeRuleNode.stop = new CommonToken(1);

    // Act and Assert
    assertEquals("n", stmTreeRuleNode.getTextContent());
  }

  /**
   * Test {@link STMTreeRuleNode#addChild(Token)} with {@code matchedToken}.
   *
   * <p>Method under test: {@link STMTreeRuleNode#addChild(Token)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TerminalNode STMTreeRuleNode.addChild(Token)"})
  public void testAddChildWithMatchedToken() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode(ParserRuleContext.EMPTY, 10);
    stmTreeRuleNode.children = new ArrayList<>();

    // Act
    TerminalNode actualAddChildResult = stmTreeRuleNode.addChild(new CommonToken(1));

    // Assert
    assertTrue(actualAddChildResult instanceof STMTreeTermNode);
    assertSame(stmTreeRuleNode, actualAddChildResult.getParent());
    assertSame(stmTreeRuleNode, ((STMTreeTermNode) actualAddChildResult).getParentNode());
  }

  /**
   * Test {@link STMTreeRuleNode#addChild(Token)} with {@code matchedToken}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then {@link STMTreeRuleNode#STMTreeRuleNode()} Text is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#addChild(Token)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TerminalNode STMTreeRuleNode.addChild(Token)"})
  public void testAddChildWithMatchedToken_givenSTMTreeRuleNode_thenSTMTreeRuleNodeTextIsNull() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();

    // Act
    TerminalNode actualAddChildResult = stmTreeRuleNode.addChild(new CommonToken(1));

    // Assert
    assertTrue(actualAddChildResult instanceof STMTreeTermNode);
    assertEquals("null", stmTreeRuleNode.getText());
    assertEquals(1, stmTreeRuleNode.getChildren().size());
    assertEquals(1, stmTreeRuleNode.children.size());
    assertEquals(1, stmTreeRuleNode.getChildCount());
    assertSame(stmTreeRuleNode, actualAddChildResult.getParent());
    assertSame(stmTreeRuleNode, ((STMTreeTermNode) actualAddChildResult).getParentNode());
  }

  /**
   * Test {@link STMTreeRuleNode#addChild(RuleContext)} with {@code ruleInvocation}.
   *
   * <p>Method under test: {@link STMTreeRuleNode#addChild(RuleContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RuleContext STMTreeRuleNode.addChild(RuleContext)"})
  public void testAddChildWithRuleInvocation() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    stmTreeRuleNode.addChild(new CommonToken(1));
    STMTreeRuleNode ruleInvocation = new STMTreeRuleNode();

    // Act
    RuleContext actualAddChildResult = stmTreeRuleNode.addChild(ruleInvocation);

    // Assert
    assertSame(ruleInvocation, actualAddChildResult);
  }

  /**
   * Test {@link STMTreeRuleNode#addChild(RuleContext)} with {@code ruleInvocation}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then return {@link STMTreeRuleNode#STMTreeRuleNode()}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#addChild(RuleContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RuleContext STMTreeRuleNode.addChild(RuleContext)"})
  public void testAddChildWithRuleInvocation_givenSTMTreeRuleNode_thenReturnSTMTreeRuleNode() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    STMTreeRuleNode ruleInvocation = new STMTreeRuleNode();

    // Act
    RuleContext actualAddChildResult = stmTreeRuleNode.addChild(ruleInvocation);

    // Assert
    assertSame(ruleInvocation, actualAddChildResult);
  }

  /**
   * Test {@link STMTreeRuleNode#addChild(RuleContext)} with {@code ruleInvocation}.
   *
   * <ul>
   *   <li>When {@link ParserRuleContext#EMPTY}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#addChild(RuleContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"RuleContext STMTreeRuleNode.addChild(RuleContext)"})
  public void testAddChildWithRuleInvocation_whenEmpty_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new STMTreeRuleNode().addChild(ParserRuleContext.EMPTY));
  }

  /**
   * Test {@link STMTreeRuleNode#addChild(TerminalNode)} with {@code t}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then {@link STMTreeRuleNode#STMTreeRuleNode()} Text is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#addChild(TerminalNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TerminalNode STMTreeRuleNode.addChild(TerminalNode)"})
  public void testAddChildWithT_givenSTMTreeRuleNode_thenSTMTreeRuleNodeTextIsNull() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    STMTreeTermErrorNode t = new STMTreeTermErrorNode(new CommonToken(1));

    // Act
    TerminalNode actualAddChildResult = stmTreeRuleNode.addChild(t);

    // Assert
    ParseTree parent = actualAddChildResult.getParent();
    assertTrue(parent instanceof STMTreeRuleNode);
    assertTrue(actualAddChildResult instanceof STMTreeTermErrorNode);
    assertEquals("null", stmTreeRuleNode.getText());
    assertEquals("null", parent.getText());
    assertNull(((STMTreeRuleNode) parent).findFirstNonErrorChild());
    assertNull(((STMTreeRuleNode) parent).findLastNonErrorChild());
    List<STMTreeNode> children = stmTreeRuleNode.getChildren();
    assertEquals(1, children.size());
    assertEquals(1, ((STMTreeRuleNode) parent).getChildren().size());
    List<ParseTree> parseTreeList = stmTreeRuleNode.children;
    assertEquals(1, parseTreeList.size());
    assertEquals(1, ((STMTreeRuleNode) parent).children.size());
    assertEquals(1, stmTreeRuleNode.getChildCount());
    assertEquals(1, parent.getChildCount());
    assertSame(t, parseTreeList.get(0));
    assertSame(t, children.get(0));
  }

  /**
   * Test {@link STMTreeRuleNode#addChild(TerminalNode)} with {@code t}.
   *
   * <ul>
   *   <li>Then Parent findFirstNonErrorChild return {@link STMTreeTermNode}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#addChild(TerminalNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TerminalNode STMTreeRuleNode.addChild(TerminalNode)"})
  public void testAddChildWithT_thenParentFindFirstNonErrorChildReturnSTMTreeTermNode() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    stmTreeRuleNode.addChild(new CommonToken(1));
    STMTreeTermErrorNode t = new STMTreeTermErrorNode(new CommonToken(1));

    // Act
    TerminalNode actualAddChildResult = stmTreeRuleNode.addChild(t);

    // Assert
    ParseTree parent = actualAddChildResult.getParent();
    assertTrue(parent instanceof STMTreeRuleNode);
    assertTrue(actualAddChildResult instanceof STMTreeTermErrorNode);
    STMTreeNode findFirstNonErrorChildResult = ((STMTreeRuleNode) parent).findFirstNonErrorChild();
    assertTrue(findFirstNonErrorChildResult instanceof STMTreeTermNode);
    assertEquals("nullnull", stmTreeRuleNode.getText());
    assertEquals("nullnull", parent.getText());
    List<STMTreeNode> children = stmTreeRuleNode.getChildren();
    assertEquals(2, children.size());
    assertEquals(2, ((STMTreeRuleNode) parent).getChildren().size());
    List<ParseTree> parseTreeList = stmTreeRuleNode.children;
    assertEquals(2, parseTreeList.size());
    assertEquals(2, ((STMTreeRuleNode) parent).children.size());
    assertEquals(2, stmTreeRuleNode.getChildCount());
    assertEquals(2, parent.getChildCount());
    assertSame(t, parseTreeList.get(1));
    assertSame(t, children.get(1));
    assertSame(findFirstNonErrorChildResult, ((STMTreeRuleNode) parent).findLastNonErrorChild());
  }

  /**
   * Test {@link STMTreeRuleNode#addChild(TerminalNode)} with {@code t}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#addChild(TerminalNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TerminalNode STMTreeRuleNode.addChild(TerminalNode)"})
  public void testAddChildWithT_thenThrowIllegalStateException() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> stmTreeRuleNode.addChild(new ErrorNodeImpl(new CommonToken(1))));
  }

  /**
   * Test {@link STMTreeRuleNode#addAnyChild(ParseTree)}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()} addChild {@link
   *       CommonToken#CommonToken(int)} with type is one.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#addAnyChild(ParseTree)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ParseTree STMTreeRuleNode.addAnyChild(ParseTree)"})
  public void testAddAnyChild_givenSTMTreeRuleNodeAddChildCommonTokenWithTypeIsOne() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    stmTreeRuleNode.addChild(new CommonToken(1));
    STMTreeRuleNode stmTreeRuleNode2 = new STMTreeRuleNode();

    // Act
    ParseTree actualAddAnyChildResult = stmTreeRuleNode.addAnyChild(stmTreeRuleNode2);

    // Assert
    assertSame(stmTreeRuleNode2, actualAddAnyChildResult);
  }

  /**
   * Test {@link STMTreeRuleNode#addAnyChild(ParseTree)}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then return {@link STMTreeRuleNode#STMTreeRuleNode()}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#addAnyChild(ParseTree)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ParseTree STMTreeRuleNode.addAnyChild(ParseTree)"})
  public void testAddAnyChild_givenSTMTreeRuleNode_thenReturnSTMTreeRuleNode() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    STMTreeRuleNode stmTreeRuleNode2 = new STMTreeRuleNode();

    // Act
    ParseTree actualAddAnyChildResult = stmTreeRuleNode.addAnyChild(stmTreeRuleNode2);

    // Assert
    assertSame(stmTreeRuleNode2, actualAddAnyChildResult);
  }

  /**
   * Test {@link STMTreeRuleNode#addAnyChild(ParseTree)}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>When {@link ParserRuleContext#EMPTY}.
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#addAnyChild(ParseTree)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ParseTree STMTreeRuleNode.addAnyChild(ParseTree)"})
  public void testAddAnyChild_givenSTMTreeRuleNode_whenEmpty_thenThrowIllegalStateException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> new STMTreeRuleNode().addAnyChild(ParserRuleContext.EMPTY));
  }

  /**
   * Test {@link STMTreeRuleNode#addErrorNode(Token)} with {@code badToken}.
   *
   * <p>Method under test: {@link STMTreeRuleNode#addErrorNode(Token)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ErrorNode STMTreeRuleNode.addErrorNode(Token)"})
  public void testAddErrorNodeWithBadToken() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode(ParserRuleContext.EMPTY, 10);
    stmTreeRuleNode.children = new ArrayList<>();
    CommonToken badToken = new CommonToken(1);

    // Act
    ErrorNode actualAddErrorNodeResult = stmTreeRuleNode.addErrorNode(badToken);

    // Assert
    Object payload = actualAddErrorNodeResult.getPayload();
    assertTrue(payload instanceof CommonToken);
    assertTrue(actualAddErrorNodeResult instanceof STMTreeTermErrorNode);
    assertEquals("error", ((STMTreeTermErrorNode) actualAddErrorNodeResult).getNodeName());
    assertEquals("null", stmTreeRuleNode.getText());
    assertNull(actualAddErrorNodeResult.getText());
    assertNull(actualAddErrorNodeResult.toStringTree());
    assertNull(actualAddErrorNodeResult.getParent());
    assertNull(((STMTreeTermErrorNode) actualAddErrorNodeResult).findFirstNonErrorChild());
    assertNull(((STMTreeTermErrorNode) actualAddErrorNodeResult).findLastNonErrorChild());
    assertNull(((STMTreeTermErrorNode) actualAddErrorNodeResult).getParentNode());
    assertEquals(-1, ((STMTreeTermErrorNode) actualAddErrorNodeResult).getNodeKindId());
    assertEquals(-1, ((STMTreeTermErrorNode) actualAddErrorNodeResult).getAtnState());
    assertEquals(0, actualAddErrorNodeResult.getChildCount());
    assertEquals(1, stmTreeRuleNode.getChildren().size());
    assertEquals(1, stmTreeRuleNode.children.size());
    assertEquals(1, stmTreeRuleNode.getChildCount());
    assertFalse(((STMTreeTermErrorNode) actualAddErrorNodeResult).hasErrorChildren());
    assertTrue(((STMTreeTermErrorNode) actualAddErrorNodeResult).getChildren().isEmpty());
    assertSame(badToken, actualAddErrorNodeResult.getSymbol());
    assertSame(badToken, payload);
  }

  /**
   * Test {@link STMTreeRuleNode#addErrorNode(Token)} with {@code badToken}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then {@link STMTreeRuleNode#STMTreeRuleNode()} Text is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#addErrorNode(Token)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ErrorNode STMTreeRuleNode.addErrorNode(Token)"})
  public void testAddErrorNodeWithBadToken_givenSTMTreeRuleNode_thenSTMTreeRuleNodeTextIsNull() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    CommonToken badToken = new CommonToken(1);

    // Act
    ErrorNode actualAddErrorNodeResult = stmTreeRuleNode.addErrorNode(badToken);

    // Assert
    Object payload = actualAddErrorNodeResult.getPayload();
    assertTrue(payload instanceof CommonToken);
    assertTrue(actualAddErrorNodeResult instanceof STMTreeTermErrorNode);
    assertEquals("error", ((STMTreeTermErrorNode) actualAddErrorNodeResult).getNodeName());
    assertEquals("null", stmTreeRuleNode.getText());
    assertNull(actualAddErrorNodeResult.getText());
    assertNull(actualAddErrorNodeResult.toStringTree());
    assertNull(actualAddErrorNodeResult.getParent());
    assertNull(((STMTreeTermErrorNode) actualAddErrorNodeResult).findFirstNonErrorChild());
    assertNull(((STMTreeTermErrorNode) actualAddErrorNodeResult).findLastNonErrorChild());
    assertNull(((STMTreeTermErrorNode) actualAddErrorNodeResult).getParentNode());
    assertEquals(-1, ((STMTreeTermErrorNode) actualAddErrorNodeResult).getNodeKindId());
    assertEquals(-1, ((STMTreeTermErrorNode) actualAddErrorNodeResult).getAtnState());
    assertEquals(0, actualAddErrorNodeResult.getChildCount());
    assertEquals(1, stmTreeRuleNode.getChildren().size());
    assertEquals(1, stmTreeRuleNode.children.size());
    assertEquals(1, stmTreeRuleNode.getChildCount());
    assertFalse(((STMTreeTermErrorNode) actualAddErrorNodeResult).hasErrorChildren());
    assertTrue(((STMTreeTermErrorNode) actualAddErrorNodeResult).getChildren().isEmpty());
    assertSame(badToken, actualAddErrorNodeResult.getSymbol());
    assertSame(badToken, payload);
  }

  /**
   * Test {@link STMTreeRuleNode#addErrorNode(ErrorNode)} with {@code errorNode}.
   *
   * <p>Method under test: {@link STMTreeRuleNode#addErrorNode(ErrorNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ErrorNode STMTreeRuleNode.addErrorNode(ErrorNode)"})
  public void testAddErrorNodeWithErrorNode() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    stmTreeRuleNode.addChild(new CommonToken(1));
    STMTreeTermErrorNode errorNode = new STMTreeTermErrorNode(new CommonToken(1));

    // Act
    ErrorNode actualAddErrorNodeResult = stmTreeRuleNode.addErrorNode(errorNode);

    // Assert
    ParseTree parent = actualAddErrorNodeResult.getParent();
    assertTrue(parent instanceof STMTreeRuleNode);
    assertTrue(actualAddErrorNodeResult instanceof STMTreeTermErrorNode);
    STMTreeNode findFirstNonErrorChildResult = ((STMTreeRuleNode) parent).findFirstNonErrorChild();
    assertTrue(findFirstNonErrorChildResult instanceof STMTreeTermNode);
    assertEquals("nullnull", stmTreeRuleNode.getText());
    assertEquals("nullnull", parent.getText());
    List<STMTreeNode> children = stmTreeRuleNode.getChildren();
    assertEquals(2, children.size());
    assertEquals(2, ((STMTreeRuleNode) parent).getChildren().size());
    List<ParseTree> parseTreeList = stmTreeRuleNode.children;
    assertEquals(2, parseTreeList.size());
    assertEquals(2, ((STMTreeRuleNode) parent).children.size());
    assertEquals(2, stmTreeRuleNode.getChildCount());
    assertEquals(2, parent.getChildCount());
    assertSame(errorNode, parseTreeList.get(1));
    assertSame(errorNode, children.get(1));
    assertSame(findFirstNonErrorChildResult, ((STMTreeRuleNode) parent).findLastNonErrorChild());
  }

  /**
   * Test {@link STMTreeRuleNode#addErrorNode(ErrorNode)} with {@code errorNode}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then {@link STMTreeRuleNode#STMTreeRuleNode()} Text is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#addErrorNode(ErrorNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ErrorNode STMTreeRuleNode.addErrorNode(ErrorNode)"})
  public void testAddErrorNodeWithErrorNode_givenSTMTreeRuleNode_thenSTMTreeRuleNodeTextIsNull() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    STMTreeTermErrorNode errorNode = new STMTreeTermErrorNode(new CommonToken(1));

    // Act
    ErrorNode actualAddErrorNodeResult = stmTreeRuleNode.addErrorNode(errorNode);

    // Assert
    ParseTree parent = actualAddErrorNodeResult.getParent();
    assertTrue(parent instanceof STMTreeRuleNode);
    assertTrue(actualAddErrorNodeResult instanceof STMTreeTermErrorNode);
    assertEquals("null", stmTreeRuleNode.getText());
    assertEquals("null", parent.getText());
    assertNull(((STMTreeRuleNode) parent).findFirstNonErrorChild());
    assertNull(((STMTreeRuleNode) parent).findLastNonErrorChild());
    List<STMTreeNode> children = stmTreeRuleNode.getChildren();
    assertEquals(1, children.size());
    assertEquals(1, ((STMTreeRuleNode) parent).getChildren().size());
    List<ParseTree> parseTreeList = stmTreeRuleNode.children;
    assertEquals(1, parseTreeList.size());
    assertEquals(1, ((STMTreeRuleNode) parent).children.size());
    assertEquals(1, stmTreeRuleNode.getChildCount());
    assertEquals(1, parent.getChildCount());
    assertSame(errorNode, parseTreeList.get(0));
    assertSame(errorNode, children.get(0));
  }

  /**
   * Test {@link STMTreeRuleNode#addErrorNode(ErrorNode)} with {@code errorNode}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#addErrorNode(ErrorNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ErrorNode STMTreeRuleNode.addErrorNode(ErrorNode)"})
  public void testAddErrorNodeWithErrorNode_thenThrowIllegalStateException() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> stmTreeRuleNode.addErrorNode(new ErrorNodeImpl(new CommonToken(1))));
  }

  /**
   * Test {@link STMTreeRuleNode#getChildNode(int)}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>When one.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#getChildNode(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeRuleNode.getChildNode(int)"})
  public void testGetChildNode_givenSTMTreeRuleNode_whenOne_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new STMTreeRuleNode().getChildNode(1));
  }

  /**
   * Test {@link STMTreeRuleNode#getChildNode(int)}.
   *
   * <ul>
   *   <li>Then Payload return {@link CommonToken}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#getChildNode(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeRuleNode.getChildNode(int)"})
  public void testGetChildNode_thenPayloadReturnCommonToken() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    stmTreeRuleNode.addChild(new CommonToken(1));
    CommonToken matchedToken = new CommonToken(1);
    stmTreeRuleNode.addChild(matchedToken);

    // Act
    STMTreeNode actualChildNode = stmTreeRuleNode.getChildNode(1);

    // Assert
    Object payload = actualChildNode.getPayload();
    assertTrue(payload instanceof CommonToken);
    Tree parent = actualChildNode.getParent();
    assertTrue(parent instanceof STMTreeRuleNode);
    assertTrue(actualChildNode instanceof STMTreeTermNode);
    assertNull(actualChildNode.toStringTree());
    assertNull(actualChildNode.getNodeName());
    assertNull(actualChildNode.getText());
    assertNull(actualChildNode.findFirstNonErrorChild());
    assertNull(actualChildNode.findLastNonErrorChild());
    assertEquals(-1, actualChildNode.getAtnState());
    assertEquals(-1, actualChildNode.getNodeKindId());
    assertEquals(0, actualChildNode.getChildCount());
    assertFalse(actualChildNode.hasErrorChildren());
    assertTrue(actualChildNode.getChildren().isEmpty());
    assertSame(matchedToken, ((STMTreeTermNode) actualChildNode).getSymbol());
    assertSame(matchedToken, payload);
    assertSame(stmTreeRuleNode, parent);
    assertSame(stmTreeRuleNode, actualChildNode.getParentNode());
  }

  /**
   * Test {@link STMTreeRuleNode#getChildNode(int)}.
   *
   * <ul>
   *   <li>When minus one.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#getChildNode(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeRuleNode.getChildNode(int)"})
  public void testGetChildNode_whenMinusOne() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode(ParserRuleContext.EMPTY, 10);
    stmTreeRuleNode.children = new ArrayList<>();

    // Act and Assert
    assertNull(stmTreeRuleNode.getChildNode(-1));
  }

  /**
   * Test {@link STMTreeRuleNode#getChildNode(int)}.
   *
   * <ul>
   *   <li>When zero.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeRuleNode#getChildNode(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeRuleNode.getChildNode(int)"})
  public void testGetChildNode_whenZero() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode(ParserRuleContext.EMPTY, 10);
    stmTreeRuleNode.children = new ArrayList<>();

    // Act and Assert
    assertNull(stmTreeRuleNode.getChildNode(0));
  }
}
