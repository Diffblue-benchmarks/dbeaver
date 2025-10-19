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
import java.util.List;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.Tree;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class STMTreeNodeDiffblueTest {
  /**
   * Test {@link STMTreeNode#getNodeKindId()}.
   *
   * <p>Method under test: {@link STMTreeNode#getNodeKindId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int STMTreeNode.getNodeKindId()"})
  public void testGetNodeKindId() {
    // Arrange, Act and Assert
    assertEquals(-1, new STMTreeTermErrorNode(new CommonToken(1)).getNodeKindId());
  }

  /**
   * Test {@link STMTreeNode#getParentNode()}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#getParentNode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.getParentNode()"})
  public void testGetParentNode_givenSTMTreeRuleNode_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new STMTreeRuleNode().getParentNode());
  }

  /**
   * Test {@link STMTreeNode#getParentNode()}.
   *
   * <ul>
   *   <li>Then return {@link STMTreeRuleNode#STMTreeRuleNode()}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#getParentNode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.getParentNode()"})
  public void testGetParentNode_thenReturnSTMTreeRuleNode() {
    // Arrange
    STMTreeRuleNode parent = new STMTreeRuleNode();

    // Act and Assert
    assertSame(parent, new STMTreeRuleNode(parent, 10).getParentNode());
  }

  /**
   * Test {@link STMTreeNode#getChildNode(int)}.
   *
   * <p>Method under test: {@link STMTreeNode#getChildNode(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.getChildNode(int)"})
  public void testGetChildNode() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> new STMTreeTermErrorNode(new CommonToken(1)).getChildNode(1));
  }

  /**
   * Test {@link STMTreeNode#findFirstNonErrorChild()}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findFirstNonErrorChild()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.findFirstNonErrorChild()"})
  public void testFindFirstNonErrorChild_givenSTMTreeRuleNode_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new STMTreeRuleNode().findFirstNonErrorChild());
  }

  /**
   * Test {@link STMTreeNode#findFirstNonErrorChild()}.
   *
   * <ul>
   *   <li>Then Payload return {@link CommonToken}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findFirstNonErrorChild()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.findFirstNonErrorChild()"})
  public void testFindFirstNonErrorChild_thenPayloadReturnCommonToken() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    CommonToken matchedToken = new CommonToken(3);
    stmTreeRuleNode.addChild(matchedToken);

    // Act
    STMTreeNode actualFindFirstNonErrorChildResult = stmTreeRuleNode.findFirstNonErrorChild();

    // Assert
    Object payload = actualFindFirstNonErrorChildResult.getPayload();
    assertTrue(payload instanceof CommonToken);
    Tree parent = actualFindFirstNonErrorChildResult.getParent();
    assertTrue(parent instanceof STMTreeRuleNode);
    assertTrue(actualFindFirstNonErrorChildResult instanceof STMTreeTermNode);
    assertNull(actualFindFirstNonErrorChildResult.toStringTree());
    assertNull(actualFindFirstNonErrorChildResult.getNodeName());
    assertNull(actualFindFirstNonErrorChildResult.getText());
    assertNull(actualFindFirstNonErrorChildResult.findLastNonErrorChild());
    assertEquals(-1, actualFindFirstNonErrorChildResult.getAtnState());
    assertEquals(-1, actualFindFirstNonErrorChildResult.getNodeKindId());
    assertEquals(0, actualFindFirstNonErrorChildResult.getChildCount());
    assertFalse(actualFindFirstNonErrorChildResult.hasErrorChildren());
    assertTrue(actualFindFirstNonErrorChildResult.getChildren().isEmpty());
    assertSame(matchedToken, ((STMTreeTermNode) actualFindFirstNonErrorChildResult).getSymbol());
    assertSame(matchedToken, payload);
    assertSame(stmTreeRuleNode, parent);
    assertSame(stmTreeRuleNode, actualFindFirstNonErrorChildResult.getParentNode());
  }

  /**
   * Test {@link STMTreeNode#findLastNonErrorChild()}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findLastNonErrorChild()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.findLastNonErrorChild()"})
  public void testFindLastNonErrorChild_givenSTMTreeRuleNode_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new STMTreeRuleNode().findLastNonErrorChild());
  }

  /**
   * Test {@link STMTreeNode#findLastNonErrorChild()}.
   *
   * <ul>
   *   <li>Then Payload return {@link CommonToken}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findLastNonErrorChild()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.findLastNonErrorChild()"})
  public void testFindLastNonErrorChild_thenPayloadReturnCommonToken() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    CommonToken matchedToken = new CommonToken(3);
    stmTreeRuleNode.addChild(matchedToken);

    // Act
    STMTreeNode actualFindLastNonErrorChildResult = stmTreeRuleNode.findLastNonErrorChild();

    // Assert
    Object payload = actualFindLastNonErrorChildResult.getPayload();
    assertTrue(payload instanceof CommonToken);
    Tree parent = actualFindLastNonErrorChildResult.getParent();
    assertTrue(parent instanceof STMTreeRuleNode);
    assertTrue(actualFindLastNonErrorChildResult instanceof STMTreeTermNode);
    assertNull(actualFindLastNonErrorChildResult.toStringTree());
    assertNull(actualFindLastNonErrorChildResult.getNodeName());
    assertNull(actualFindLastNonErrorChildResult.getText());
    assertNull(actualFindLastNonErrorChildResult.findFirstNonErrorChild());
    assertEquals(-1, actualFindLastNonErrorChildResult.getAtnState());
    assertEquals(-1, actualFindLastNonErrorChildResult.getNodeKindId());
    assertEquals(0, actualFindLastNonErrorChildResult.getChildCount());
    assertFalse(actualFindLastNonErrorChildResult.hasErrorChildren());
    assertTrue(actualFindLastNonErrorChildResult.getChildren().isEmpty());
    assertSame(matchedToken, ((STMTreeTermNode) actualFindLastNonErrorChildResult).getSymbol());
    assertSame(matchedToken, payload);
    assertSame(stmTreeRuleNode, parent);
    assertSame(stmTreeRuleNode, actualFindLastNonErrorChildResult.getParentNode());
  }

  /**
   * Test {@link STMTreeNode#findFirstChildOfName(String)}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>When {@code Node Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findFirstChildOfName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.findFirstChildOfName(String)"})
  public void testFindFirstChildOfName_givenSTMTreeRuleNode_whenNodeName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new STMTreeRuleNode().findFirstChildOfName("Node Name"));
  }

  /**
   * Test {@link STMTreeNode#findFirstChildOfName(String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findFirstChildOfName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.findFirstChildOfName(String)"})
  public void testFindFirstChildOfName_thenReturnNull() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    stmTreeRuleNode.addErrorNode(new CommonToken(1));

    // Act and Assert
    assertNull(stmTreeRuleNode.findFirstChildOfName("Node Name"));
  }

  /**
   * Test {@link STMTreeNode#findFirstChildOfName(String)}.
   *
   * <ul>
   *   <li>When {@code error}.
   *   <li>Then Payload return {@link CommonToken}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findFirstChildOfName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.findFirstChildOfName(String)"})
  public void testFindFirstChildOfName_whenError_thenPayloadReturnCommonToken() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    CommonToken badToken = new CommonToken(1);
    stmTreeRuleNode.addErrorNode(badToken);

    // Act
    STMTreeNode actualFindFirstChildOfNameResult = stmTreeRuleNode.findFirstChildOfName("error");

    // Assert
    Object payload = actualFindFirstChildOfNameResult.getPayload();
    assertTrue(payload instanceof CommonToken);
    assertTrue(actualFindFirstChildOfNameResult instanceof STMTreeTermErrorNode);
    assertEquals("error", actualFindFirstChildOfNameResult.getNodeName());
    assertNull(actualFindFirstChildOfNameResult.toStringTree());
    assertNull(actualFindFirstChildOfNameResult.getText());
    assertNull(actualFindFirstChildOfNameResult.getParent());
    assertNull(actualFindFirstChildOfNameResult.findFirstNonErrorChild());
    assertNull(actualFindFirstChildOfNameResult.findLastNonErrorChild());
    assertNull(actualFindFirstChildOfNameResult.getParentNode());
    assertEquals(-1, actualFindFirstChildOfNameResult.getAtnState());
    assertEquals(-1, actualFindFirstChildOfNameResult.getNodeKindId());
    assertEquals(0, actualFindFirstChildOfNameResult.getChildCount());
    assertFalse(actualFindFirstChildOfNameResult.hasErrorChildren());
    assertTrue(actualFindFirstChildOfNameResult.getChildren().isEmpty());
    assertSame(badToken, ((STMTreeTermErrorNode) actualFindFirstChildOfNameResult).getSymbol());
    assertSame(badToken, payload);
  }

  /**
   * Test {@link STMTreeNode#findLastChildOfName(String)}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>When {@code Node Name}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findLastChildOfName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.findLastChildOfName(String)"})
  public void testFindLastChildOfName_givenSTMTreeRuleNode_whenNodeName_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new STMTreeRuleNode().findLastChildOfName("Node Name"));
  }

  /**
   * Test {@link STMTreeNode#findLastChildOfName(String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findLastChildOfName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.findLastChildOfName(String)"})
  public void testFindLastChildOfName_thenReturnNull() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    stmTreeRuleNode.addErrorNode(new CommonToken(-1));

    // Act and Assert
    assertNull(stmTreeRuleNode.findLastChildOfName("Node Name"));
  }

  /**
   * Test {@link STMTreeNode#findLastChildOfName(String)}.
   *
   * <ul>
   *   <li>When {@code error}.
   *   <li>Then Payload return {@link CommonToken}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findLastChildOfName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMTreeNode STMTreeNode.findLastChildOfName(String)"})
  public void testFindLastChildOfName_whenError_thenPayloadReturnCommonToken() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    CommonToken badToken = new CommonToken(-1);
    stmTreeRuleNode.addErrorNode(badToken);

    // Act
    STMTreeNode actualFindLastChildOfNameResult = stmTreeRuleNode.findLastChildOfName("error");

    // Assert
    Object payload = actualFindLastChildOfNameResult.getPayload();
    assertTrue(payload instanceof CommonToken);
    assertTrue(actualFindLastChildOfNameResult instanceof STMTreeTermErrorNode);
    assertEquals("<EOF>", actualFindLastChildOfNameResult.toStringTree());
    assertEquals("error", actualFindLastChildOfNameResult.getNodeName());
    assertNull(actualFindLastChildOfNameResult.getText());
    assertNull(actualFindLastChildOfNameResult.getParent());
    assertNull(actualFindLastChildOfNameResult.findFirstNonErrorChild());
    assertNull(actualFindLastChildOfNameResult.findLastNonErrorChild());
    assertNull(actualFindLastChildOfNameResult.getParentNode());
    assertEquals(-1, actualFindLastChildOfNameResult.getAtnState());
    assertEquals(-1, actualFindLastChildOfNameResult.getNodeKindId());
    assertEquals(0, actualFindLastChildOfNameResult.getChildCount());
    assertFalse(actualFindLastChildOfNameResult.hasErrorChildren());
    assertTrue(actualFindLastChildOfNameResult.getChildren().isEmpty());
    assertSame(badToken, ((STMTreeTermErrorNode) actualFindLastChildOfNameResult).getSymbol());
    assertSame(badToken, payload);
  }

  /**
   * Test {@link STMTreeNode#getChildren()}.
   *
   * <p>Method under test: {@link STMTreeNode#getChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMTreeNode.getChildren()"})
  public void testGetChildren() {
    // Arrange, Act and Assert
    assertTrue(new STMTreeRuleNode().getChildren().isEmpty());
  }

  /**
   * Test {@link STMTreeNode#findChildrenOfName(String)}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>When {@code Node Name}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findChildrenOfName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMTreeNode.findChildrenOfName(String)"})
  public void testFindChildrenOfName_givenSTMTreeRuleNode_whenNodeName_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new STMTreeRuleNode().findChildrenOfName("Node Name").isEmpty());
  }

  /**
   * Test {@link STMTreeNode#findChildrenOfName(String)}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findChildrenOfName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMTreeNode.findChildrenOfName(String)"})
  public void testFindChildrenOfName_thenReturnEmpty() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    stmTreeRuleNode.addErrorNode(new CommonToken(1));

    // Act and Assert
    assertTrue(stmTreeRuleNode.findChildrenOfName("Node Name").isEmpty());
  }

  /**
   * Test {@link STMTreeNode#findChildrenOfName(String)}.
   *
   * <ul>
   *   <li>When {@code error}.
   *   <li>Then return {@link STMTreeRuleNode#STMTreeRuleNode()} {@link ParserRuleContext#children}.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findChildrenOfName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMTreeNode.findChildrenOfName(String)"})
  public void testFindChildrenOfName_whenError_thenReturnSTMTreeRuleNodeChildren() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    stmTreeRuleNode.addErrorNode(new CommonToken(1));

    // Act
    List<STMTreeNode> actualFindChildrenOfNameResult = stmTreeRuleNode.findChildrenOfName("error");

    // Assert
    assertEquals(stmTreeRuleNode.children, actualFindChildrenOfNameResult);
  }

  /**
   * Test {@link STMTreeNode#findNonErrorChildren()}.
   *
   * <ul>
   *   <li>Given {@link CommonToken#CommonToken(int)} with type is one.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findNonErrorChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMTreeNode.findNonErrorChildren()"})
  public void testFindNonErrorChildren_givenCommonTokenWithTypeIsOne_thenReturnEmpty() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    stmTreeRuleNode.addChild(new STMTreeTermErrorNode(new CommonToken(1)));

    // Act and Assert
    assertTrue(stmTreeRuleNode.findNonErrorChildren().isEmpty());
  }

  /**
   * Test {@link STMTreeNode#findNonErrorChildren()}.
   *
   * <ul>
   *   <li>Given {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findNonErrorChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMTreeNode.findNonErrorChildren()"})
  public void testFindNonErrorChildren_givenSTMTreeRuleNode_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new STMTreeRuleNode().findNonErrorChildren().isEmpty());
  }

  /**
   * Test {@link STMTreeNode#findNonErrorChildren()}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link STMTreeNode#findNonErrorChildren()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMTreeNode.findNonErrorChildren()"})
  public void testFindNonErrorChildren_thenReturnSizeIsOne() {
    // Arrange
    STMTreeRuleNode stmTreeRuleNode = new STMTreeRuleNode();
    CommonToken matchedToken = new CommonToken(3);
    stmTreeRuleNode.addChild(matchedToken);
    stmTreeRuleNode.addChild(new STMTreeTermErrorNode(new CommonToken(1)));

    // Act
    List<STMTreeNode> actualFindNonErrorChildrenResult = stmTreeRuleNode.findNonErrorChildren();

    // Assert
    assertEquals(1, actualFindNonErrorChildrenResult.size());
    STMTreeNode getResult = actualFindNonErrorChildrenResult.get(0);
    Object payload = getResult.getPayload();
    assertTrue(payload instanceof CommonToken);
    Tree parent = getResult.getParent();
    assertTrue(parent instanceof STMTreeRuleNode);
    assertTrue(getResult instanceof STMTreeTermNode);
    assertNull(getResult.toStringTree());
    assertNull(getResult.getNodeName());
    assertNull(getResult.getText());
    assertNull(getResult.findFirstNonErrorChild());
    assertNull(getResult.findLastNonErrorChild());
    assertEquals(-1, getResult.getAtnState());
    assertEquals(-1, getResult.getNodeKindId());
    assertEquals(0, getResult.getChildCount());
    assertFalse(getResult.hasErrorChildren());
    assertTrue(getResult.getChildren().isEmpty());
    assertSame(matchedToken, ((STMTreeTermNode) getResult).getSymbol());
    assertSame(matchedToken, payload);
    assertSame(stmTreeRuleNode, parent);
    assertSame(stmTreeRuleNode, getResult.getParentNode());
  }
}
