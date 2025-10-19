package org.jkiss.dbeaver.model.stm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.UnbufferedCharStream;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.ErrorNodeImpl;
import org.antlr.v4.runtime.tree.Tree;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardLexer;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class STMUtilsDiffblueTest {
  /**
   * Test {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashSet#HashSet()} add {@code 42}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandSubtree(STMTreeNode, Set, Set)"})
  public void testExpandSubtree_given42_whenHashSetAdd42_thenReturnEmpty() {
    // Arrange
    STMTreeRuleNode root = new STMTreeRuleNode();

    HashSet<String> toExpand = new HashSet<>();
    toExpand.add("42");
    toExpand.add("foo");

    // Act
    List<STMTreeNode> actualExpandSubtreeResult =
        STMUtils.expandSubtree(root, toExpand, new HashSet<>());

    // Assert
    assertTrue(actualExpandSubtreeResult.isEmpty());
  }

  /**
   * Test {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashSet#HashSet()} add {@code 42}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandSubtree(STMTreeNode, Set, Set)"})
  public void testExpandSubtree_given42_whenHashSetAdd42_thenReturnEmpty2() {
    // Arrange
    STMTreeRuleNode root = new STMTreeRuleNode();
    HashSet<String> toExpand = new HashSet<>();

    HashSet<String> toCollect = new HashSet<>();
    toCollect.add("42");
    toCollect.add("foo");

    // Act
    List<STMTreeNode> actualExpandSubtreeResult = STMUtils.expandSubtree(root, toExpand, toCollect);

    // Assert
    assertTrue(actualExpandSubtreeResult.isEmpty());
  }

  /**
   * Test {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}.
   *
   * <ul>
   *   <li>Given {@link CommonToken#CommonToken(int)} with type is one.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandSubtree(STMTreeNode, Set, Set)"})
  public void testExpandSubtree_givenCommonTokenWithTypeIsOne() {
    // Arrange
    STMTreeRuleNode root = new STMTreeRuleNode();
    root.addChild(new CommonToken(1));

    HashSet<String> toExpand = new HashSet<>();
    toExpand.add(null);

    // Act
    List<STMTreeNode> actualExpandSubtreeResult =
        STMUtils.expandSubtree(root, toExpand, new HashSet<>());

    // Assert
    assertTrue(actualExpandSubtreeResult.isEmpty());
  }

  /**
   * Test {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link HashSet#HashSet()} add {@code foo}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandSubtree(STMTreeNode, Set, Set)"})
  public void testExpandSubtree_givenFoo_whenHashSetAddFoo_thenReturnEmpty() {
    // Arrange
    STMTreeRuleNode root = new STMTreeRuleNode();

    HashSet<String> toExpand = new HashSet<>();
    toExpand.add("foo");

    // Act
    List<STMTreeNode> actualExpandSubtreeResult =
        STMUtils.expandSubtree(root, toExpand, new HashSet<>());

    // Assert
    assertTrue(actualExpandSubtreeResult.isEmpty());
  }

  /**
   * Test {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link HashSet#HashSet()} add {@code foo}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandSubtree(STMTreeNode, Set, Set)"})
  public void testExpandSubtree_givenFoo_whenHashSetAddFoo_thenReturnEmpty2() {
    // Arrange
    STMTreeRuleNode root = new STMTreeRuleNode();
    HashSet<String> toExpand = new HashSet<>();

    HashSet<String> toCollect = new HashSet<>();
    toCollect.add("foo");

    // Act
    List<STMTreeNode> actualExpandSubtreeResult = STMUtils.expandSubtree(root, toExpand, toCollect);

    // Assert
    assertTrue(actualExpandSubtreeResult.isEmpty());
  }

  /**
   * Test {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link HashSet#HashSet()} add {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandSubtree(STMTreeNode, Set, Set)"})
  public void testExpandSubtree_givenNull_whenHashSetAddNull_thenReturnEmpty() {
    // Arrange
    STMTreeRuleNode root = new STMTreeRuleNode();

    HashSet<String> toExpand = new HashSet<>();
    toExpand.add(null);

    // Act
    List<STMTreeNode> actualExpandSubtreeResult =
        STMUtils.expandSubtree(root, toExpand, new HashSet<>());

    // Assert
    assertTrue(actualExpandSubtreeResult.isEmpty());
  }

  /**
   * Test {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>When {@link HashSet#HashSet()} add {@code null}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandSubtree(STMTreeNode, Set, Set)"})
  public void testExpandSubtree_givenNull_whenHashSetAddNull_thenReturnSizeIsOne() {
    // Arrange
    STMTreeRuleNode root = new STMTreeRuleNode();
    HashSet<String> toExpand = new HashSet<>();

    HashSet<String> toCollect = new HashSet<>();
    toCollect.add(null);

    // Act
    List<STMTreeNode> actualExpandSubtreeResult = STMUtils.expandSubtree(root, toExpand, toCollect);

    // Assert
    assertEquals(1, actualExpandSubtreeResult.size());
    STMTreeNode getResult = actualExpandSubtreeResult.get(0);
    assertTrue(getResult instanceof STMTreeRuleNode);
    assertSame(root, getResult);
  }

  /**
   * Test {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandSubtree(STMTreeNode, Set, Set)"})
  public void testExpandSubtree_whenNull_thenReturnEmpty() {
    // Arrange
    STMTreeRuleNode root = new STMTreeRuleNode();

    // Act
    List<STMTreeNode> actualExpandSubtreeResult =
        STMUtils.expandSubtree(root, null, new HashSet<>());

    // Assert
    assertTrue(actualExpandSubtreeResult.isEmpty());
  }

  /**
   * Test {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}.
   *
   * <ul>
   *   <li>When {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandSubtree(STMTreeNode, Set, Set)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandSubtree(STMTreeNode, Set, Set)"})
  public void testExpandSubtree_whenSTMTreeRuleNode_thenReturnEmpty() {
    // Arrange
    STMTreeRuleNode root = new STMTreeRuleNode();
    HashSet<String> toExpand = new HashSet<>();

    // Act
    List<STMTreeNode> actualExpandSubtreeResult =
        STMUtils.expandSubtree(root, toExpand, new HashSet<>());

    // Assert
    assertTrue(actualExpandSubtreeResult.isEmpty());
  }

  /**
   * Test {@link STMUtils#expandTerms(STMTreeNode)}.
   *
   * <ul>
   *   <li>Given {@link CommonToken#CommonToken(int)} with type is one.
   *   <li>Then return {@link STMTreeRuleNode#STMTreeRuleNode()} {@link ParserRuleContext#children}.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandTerms(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandTerms(STMTreeNode)"})
  public void testExpandTerms_givenCommonTokenWithTypeIsOne_thenReturnSTMTreeRuleNodeChildren() {
    // Arrange
    STMTreeRuleNode root = new STMTreeRuleNode();
    root.addChild(new CommonToken(1));

    // Act
    List<STMTreeTermNode> actualExpandTermsResult = STMUtils.expandTerms(root);

    // Assert
    assertEquals(root.children, actualExpandTermsResult);
  }

  /**
   * Test {@link STMUtils#expandTerms(STMTreeNode)}.
   *
   * <ul>
   *   <li>When {@link CommonToken#CommonToken(int)} with type is one.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandTerms(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandTerms(STMTreeNode)"})
  public void testExpandTerms_whenCommonTokenWithTypeIsOne_thenReturnSizeIsOne() {
    // Arrange
    STMTreeTermNode root = new STMTreeTermNode(new CommonToken(1));

    // Act
    List<STMTreeTermNode> actualExpandTermsResult = STMUtils.expandTerms(root);

    // Assert
    assertEquals(1, actualExpandTermsResult.size());
    assertSame(root, actualExpandTermsResult.get(0));
  }

  /**
   * Test {@link STMUtils#expandTerms(STMTreeNode)}.
   *
   * <ul>
   *   <li>When {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandTerms(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandTerms(STMTreeNode)"})
  public void testExpandTerms_whenSTMTreeRuleNode_thenReturnEmpty() {
    // Arrange and Act
    List<STMTreeTermNode> actualExpandTermsResult = STMUtils.expandTerms(new STMTreeRuleNode());

    // Assert
    assertTrue(actualExpandTermsResult.isEmpty());
  }

  /**
   * Test {@link STMUtils#expandTermStrings(STMTreeNode)}.
   *
   * <ul>
   *   <li>Given {@link CommonToken#CommonToken(int)} with type is one.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandTermStrings(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandTermStrings(STMTreeNode)"})
  public void testExpandTermStrings_givenCommonTokenWithTypeIsOne() {
    // Arrange
    STMTreeRuleNode root = new STMTreeRuleNode();
    root.addChild(new CommonToken(1));

    // Act
    List<String> actualExpandTermStringsResult = STMUtils.expandTermStrings(root);

    // Assert
    assertEquals(1, actualExpandTermStringsResult.size());
    assertNull(actualExpandTermStringsResult.get(0));
  }

  /**
   * Test {@link STMUtils#expandTermStrings(STMTreeNode)}.
   *
   * <ul>
   *   <li>Then return first is {@code In}.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandTermStrings(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandTermStrings(STMTreeNode)"})
  public void testExpandTermStrings_thenReturnFirstIsIn() {
    // Arrange
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 0, 1);

    // Act
    List<String> actualExpandTermStringsResult =
        STMUtils.expandTermStrings(new STMTreeTermNode(symbol));

    // Assert
    assertEquals(1, actualExpandTermStringsResult.size());
    assertEquals("In", actualExpandTermStringsResult.get(0));
  }

  /**
   * Test {@link STMUtils#expandTermStrings(STMTreeNode)}.
   *
   * <ul>
   *   <li>Then return first is {@code n}.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandTermStrings(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandTermStrings(STMTreeNode)"})
  public void testExpandTermStrings_thenReturnFirstIsN() {
    // Arrange
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 1, 1);

    // Act
    List<String> actualExpandTermStringsResult =
        STMUtils.expandTermStrings(new STMTreeTermNode(symbol));

    // Assert
    assertEquals(1, actualExpandTermStringsResult.size());
    assertEquals("n", actualExpandTermStringsResult.get(0));
  }

  /**
   * Test {@link STMUtils#expandTermStrings(STMTreeNode)}.
   *
   * <ul>
   *   <li>When {@link CommonToken#CommonToken(int)} with type is one.
   *   <li>Then return first is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandTermStrings(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandTermStrings(STMTreeNode)"})
  public void testExpandTermStrings_whenCommonTokenWithTypeIsOne_thenReturnFirstIsNull() {
    // Arrange and Act
    List<String> actualExpandTermStringsResult =
        STMUtils.expandTermStrings(new STMTreeTermNode(new CommonToken(1)));

    // Assert
    assertEquals(1, actualExpandTermStringsResult.size());
    assertNull(actualExpandTermStringsResult.get(0));
  }

  /**
   * Test {@link STMUtils#expandTermStrings(STMTreeNode)}.
   *
   * <ul>
   *   <li>When {@link STMTreeRuleNode#STMTreeRuleNode()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#expandTermStrings(STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.expandTermStrings(STMTreeNode)"})
  public void testExpandTermStrings_whenSTMTreeRuleNode_thenReturnEmpty() {
    // Arrange and Act
    List<String> actualExpandTermStringsResult = STMUtils.expandTermStrings(new STMTreeRuleNode());

    // Assert
    assertTrue(actualExpandTermStringsResult.isEmpty());
  }

  /**
   * Test {@link STMUtils#binarySearchByKey(List, Function, Object, Comparator)}.
   *
   * <ul>
   *   <li>Given minus one.
   *   <li>Then return minus two.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#binarySearchByKey(List, Function, Object, Comparator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int STMUtils.binarySearchByKey(List, Function, Object, Comparator)"})
  public void testBinarySearchByKey_givenMinusOne_thenReturnMinusTwo() {
    // Arrange
    ArrayList<Object> list = new ArrayList<>();
    list.add("42");

    Function<Object, Object> keyGetter = mock(Function.class);
    when(keyGetter.apply(Mockito.<Object>any())).thenReturn("Apply");

    Comparator<Object> comparator = mock(Comparator.class);
    when(comparator.compare(Mockito.<Object>any(), Mockito.<Object>any())).thenReturn(-1);

    // Act
    int actualBinarySearchByKeyResult =
        STMUtils.binarySearchByKey(list, keyGetter, "Key", comparator);

    // Assert
    verify(comparator).compare(isA(Object.class), isA(Object.class));
    verify(keyGetter).apply(isA(Object.class));
    assertEquals(-2, actualBinarySearchByKeyResult);
  }

  /**
   * Test {@link STMUtils#binarySearchByKey(List, Function, Object, Comparator)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link Comparator} {@link Comparator#compare(Object, Object)} return one.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#binarySearchByKey(List, Function, Object, Comparator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int STMUtils.binarySearchByKey(List, Function, Object, Comparator)"})
  public void testBinarySearchByKey_givenOne_whenComparatorCompareReturnOne_thenReturnMinusOne() {
    // Arrange
    ArrayList<Object> list = new ArrayList<>();
    list.add("42");

    Function<Object, Object> keyGetter = mock(Function.class);
    when(keyGetter.apply(Mockito.<Object>any())).thenReturn("Apply");

    Comparator<Object> comparator = mock(Comparator.class);
    when(comparator.compare(Mockito.<Object>any(), Mockito.<Object>any())).thenReturn(1);

    // Act
    int actualBinarySearchByKeyResult =
        STMUtils.binarySearchByKey(list, keyGetter, "Key", comparator);

    // Assert
    verify(comparator).compare(isA(Object.class), isA(Object.class));
    verify(keyGetter).apply(isA(Object.class));
    assertEquals(-1, actualBinarySearchByKeyResult);
  }

  /**
   * Test {@link STMUtils#binarySearchByKey(List, Function, Object, Comparator)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link Comparator} {@link Comparator#compare(Object, Object)} return one.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#binarySearchByKey(List, Function, Object, Comparator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int STMUtils.binarySearchByKey(List, Function, Object, Comparator)"})
  public void testBinarySearchByKey_givenOne_whenComparatorCompareReturnOne_thenReturnMinusOne2() {
    // Arrange
    ArrayList<Object> list = new ArrayList<>();
    list.add("42");
    list.add("42");

    Function<Object, Object> keyGetter = mock(Function.class);
    when(keyGetter.apply(Mockito.<Object>any())).thenReturn("Apply");

    Comparator<Object> comparator = mock(Comparator.class);
    when(comparator.compare(Mockito.<Object>any(), Mockito.<Object>any())).thenReturn(1);

    // Act
    int actualBinarySearchByKeyResult =
        STMUtils.binarySearchByKey(list, keyGetter, "Key", comparator);

    // Assert
    verify(comparator).compare(isA(Object.class), isA(Object.class));
    verify(keyGetter).apply(isA(Object.class));
    assertEquals(-1, actualBinarySearchByKeyResult);
  }

  /**
   * Test {@link STMUtils#binarySearchByKey(List, Function, Object, Comparator)}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>When {@link Comparator} {@link Comparator#compare(Object, Object)} return zero.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#binarySearchByKey(List, Function, Object, Comparator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int STMUtils.binarySearchByKey(List, Function, Object, Comparator)"})
  public void testBinarySearchByKey_givenZero_whenComparatorCompareReturnZero_thenReturnZero() {
    // Arrange
    ArrayList<Object> list = new ArrayList<>();
    list.add("42");

    Function<Object, Object> keyGetter = mock(Function.class);
    when(keyGetter.apply(Mockito.<Object>any())).thenReturn("Apply");

    Comparator<Object> comparator = mock(Comparator.class);
    when(comparator.compare(Mockito.<Object>any(), Mockito.<Object>any())).thenReturn(0);

    // Act
    int actualBinarySearchByKeyResult =
        STMUtils.binarySearchByKey(list, keyGetter, "Key", comparator);

    // Assert
    verify(comparator).compare(isA(Object.class), isA(Object.class));
    verify(keyGetter).apply(isA(Object.class));
    assertEquals(0, actualBinarySearchByKeyResult);
  }

  /**
   * Test {@link STMUtils#binarySearchByKey(List, Function, Object, Comparator)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#binarySearchByKey(List, Function, Object, Comparator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int STMUtils.binarySearchByKey(List, Function, Object, Comparator)"})
  public void testBinarySearchByKey_whenArrayList_thenReturnMinusOne() {
    // Arrange and Act
    int actualBinarySearchByKeyResult =
        STMUtils.binarySearchByKey(
            new ArrayList<>(), mock(Function.class), "Key", mock(Comparator.class));

    // Assert
    assertEquals(-1, actualBinarySearchByKeyResult);
  }

  /**
   * Test {@link STMUtils#orderedInsert(List, Function, Object, Comparator)}.
   *
   * <ul>
   *   <li>Given minus one.
   *   <li>When {@link Comparator} {@link Comparator#compare(Object, Object)} return minus one.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#orderedInsert(List, Function, Object, Comparator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.orderedInsert(List, Function, Object, Comparator)"})
  public void testOrderedInsert_givenMinusOne_whenComparatorCompareReturnMinusOne() {
    // Arrange
    ArrayList<Object> list = new ArrayList<>();
    list.add("42");

    Function<Object, Object> keyGetter = mock(Function.class);
    when(keyGetter.apply(Mockito.<Object>any())).thenReturn("Apply");

    Comparator<Object> comparator = mock(Comparator.class);
    when(comparator.compare(Mockito.<Object>any(), Mockito.<Object>any())).thenReturn(-1);

    // Act
    List<Object> actualOrderedInsertResult =
        STMUtils.orderedInsert(list, keyGetter, "Value", comparator);

    // Assert
    verify(comparator, atLeast(1)).compare(isA(Object.class), isA(Object.class));
    verify(keyGetter, atLeast(1)).apply(Mockito.<Object>any());
    assertEquals(2, list.size());
    assertEquals("42", list.get(0));
    assertEquals(2, actualOrderedInsertResult.size());
    assertEquals("42", actualOrderedInsertResult.get(0));
    assertEquals("Value", list.get(1));
    assertEquals("Value", actualOrderedInsertResult.get(1));
  }

  /**
   * Test {@link STMUtils#orderedInsert(List, Function, Object, Comparator)}.
   *
   * <ul>
   *   <li>Given one.
   *   <li>When {@link Comparator} {@link Comparator#compare(Object, Object)} return one.
   *   <li>Then {@link ArrayList#ArrayList()} first is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#orderedInsert(List, Function, Object, Comparator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.orderedInsert(List, Function, Object, Comparator)"})
  public void testOrderedInsert_givenOne_whenComparatorCompareReturnOne_thenArrayListFirstIs42() {
    // Arrange
    ArrayList<Object> list = new ArrayList<>();
    list.add("42");

    Function<Object, Object> keyGetter = mock(Function.class);
    when(keyGetter.apply(Mockito.<Object>any())).thenReturn("Apply");

    Comparator<Object> comparator = mock(Comparator.class);
    when(comparator.compare(Mockito.<Object>any(), Mockito.<Object>any())).thenReturn(1);

    // Act
    List<Object> actualOrderedInsertResult =
        STMUtils.orderedInsert(list, keyGetter, "Value", comparator);

    // Assert
    verify(comparator).compare(isA(Object.class), isA(Object.class));
    verify(keyGetter, atLeast(1)).apply(Mockito.<Object>any());
    assertEquals(2, list.size());
    assertEquals("42", list.get(0));
    assertEquals(2, actualOrderedInsertResult.size());
    assertEquals("42", actualOrderedInsertResult.get(0));
    assertEquals("Value", list.get(1));
    assertEquals("Value", actualOrderedInsertResult.get(1));
  }

  /**
   * Test {@link STMUtils#orderedInsert(List, Function, Object, Comparator)}.
   *
   * <ul>
   *   <li>Given zero.
   *   <li>Then {@link ArrayList#ArrayList()} second is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#orderedInsert(List, Function, Object, Comparator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.orderedInsert(List, Function, Object, Comparator)"})
  public void testOrderedInsert_givenZero_thenArrayListSecondIs42() {
    // Arrange
    ArrayList<Object> list = new ArrayList<>();
    list.add("42");

    Function<Object, Object> keyGetter = mock(Function.class);
    when(keyGetter.apply(Mockito.<Object>any())).thenReturn("Apply");

    Comparator<Object> comparator = mock(Comparator.class);
    when(comparator.compare(Mockito.<Object>any(), Mockito.<Object>any())).thenReturn(0);

    // Act
    List<Object> actualOrderedInsertResult =
        STMUtils.orderedInsert(list, keyGetter, "Value", comparator);

    // Assert
    verify(comparator, atLeast(1)).compare(isA(Object.class), isA(Object.class));
    verify(keyGetter, atLeast(1)).apply(Mockito.<Object>any());
    assertEquals(2, list.size());
    assertEquals("42", list.get(1));
    assertEquals(2, actualOrderedInsertResult.size());
    assertEquals("42", actualOrderedInsertResult.get(1));
    assertEquals("Value", list.get(0));
    assertEquals("Value", actualOrderedInsertResult.get(0));
  }

  /**
   * Test {@link STMUtils#orderedInsert(List, Function, Object, Comparator)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then {@link ArrayList#ArrayList()} size is one.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#orderedInsert(List, Function, Object, Comparator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.orderedInsert(List, Function, Object, Comparator)"})
  public void testOrderedInsert_whenArrayList_thenArrayListSizeIsOne() {
    // Arrange
    ArrayList<Object> list = new ArrayList<>();

    // Act
    List<Object> actualOrderedInsertResult =
        STMUtils.orderedInsert(list, mock(Function.class), "Value", mock(Comparator.class));

    // Assert
    assertEquals(1, list.size());
    assertEquals("Value", list.get(0));
    assertEquals(1, actualOrderedInsertResult.size());
    assertEquals("Value", actualOrderedInsertResult.get(0));
  }

  /**
   * Test {@link STMUtils#combineLists(List, List)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#combineLists(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.combineLists(List, List)"})
  public void testCombineLists_given42_whenArrayListAdd42_thenReturnArrayList() {
    // Arrange
    ArrayList<Object> leftColumns = new ArrayList<>();
    leftColumns.add("42");

    // Act
    List<Object> actualCombineListsResult = STMUtils.combineLists(leftColumns, new ArrayList<>());

    // Assert
    assertEquals(leftColumns, actualCombineListsResult);
  }

  /**
   * Test {@link STMUtils#combineLists(List, List)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@link ArrayList#ArrayList()}.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#combineLists(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.combineLists(List, List)"})
  public void testCombineLists_given42_whenArrayListAdd42_thenReturnArrayList2() {
    // Arrange
    ArrayList<Object> leftColumns = new ArrayList<>();
    leftColumns.add("42");
    leftColumns.add("42");

    // Act
    List<Object> actualCombineListsResult = STMUtils.combineLists(leftColumns, new ArrayList<>());

    // Assert
    assertEquals(leftColumns, actualCombineListsResult);
  }

  /**
   * Test {@link STMUtils#combineLists(List, List)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#combineLists(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.combineLists(List, List)"})
  public void testCombineLists_given42_whenArrayListAdd42_thenReturnSizeIsOne() {
    // Arrange
    ArrayList<Object> leftColumns = new ArrayList<>();

    ArrayList<Object> rightColumns = new ArrayList<>();
    rightColumns.add("42");

    // Act
    List<Object> actualCombineListsResult = STMUtils.combineLists(leftColumns, rightColumns);

    // Assert
    assertEquals(1, actualCombineListsResult.size());
    assertEquals("42", actualCombineListsResult.get(0));
  }

  /**
   * Test {@link STMUtils#combineLists(List, List)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return size is two.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#combineLists(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.combineLists(List, List)"})
  public void testCombineLists_given42_whenArrayListAdd42_thenReturnSizeIsTwo() {
    // Arrange
    ArrayList<Object> leftColumns = new ArrayList<>();

    ArrayList<Object> rightColumns = new ArrayList<>();
    rightColumns.add("42");
    rightColumns.add("42");

    // Act
    List<Object> actualCombineListsResult = STMUtils.combineLists(leftColumns, rightColumns);

    // Assert
    assertEquals(2, actualCombineListsResult.size());
    assertEquals("42", actualCombineListsResult.get(0));
    assertEquals("42", actualCombineListsResult.get(1));
  }

  /**
   * Test {@link STMUtils#combineLists(List, List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#combineLists(List, List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List STMUtils.combineLists(List, List)"})
  public void testCombineLists_whenArrayList_thenReturnEmpty() {
    // Arrange
    ArrayList<Object> leftColumns = new ArrayList<>();

    // Act
    List<Object> actualCombineListsResult = STMUtils.combineLists(leftColumns, new ArrayList<>());

    // Assert
    assertTrue(actualCombineListsResult.isEmpty());
  }

  /**
   * Test {@link STMUtils#getTextContent(Tree)}.
   *
   * <p>Method under test: {@link STMUtils#getTextContent(Tree)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String STMUtils.getTextContent(Tree)"})
  public void testGetTextContent() {
    // Arrange
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken token = new CommonToken(source, 1, 1, 0, 1);

    // Act and Assert
    assertEquals("", STMUtils.getTextContent(new ErrorNodeImpl(token)));
  }

  /**
   * Test {@link STMUtils#getTextContent(Tree)}.
   *
   * <p>Method under test: {@link STMUtils#getTextContent(Tree)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String STMUtils.getTextContent(Tree)"})
  public void testGetTextContent2() throws UnsupportedEncodingException {
    // Arrange
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    UnbufferedCharStream unbufferedCharStream =
        new UnbufferedCharStream(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    Pair<TokenSource, CharStream> source = new Pair<>(sqlStandardLexer, unbufferedCharStream);
    CommonToken symbol = new CommonToken(source, 1, 1, 1, 0);

    // Act and Assert
    assertEquals("", STMUtils.getTextContent(new STMTreeTermErrorNode(symbol)));
  }

  /**
   * Test {@link STMUtils#getTextContent(Tree)}.
   *
   * <ul>
   *   <li>Then return {@code A}.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#getTextContent(Tree)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String STMUtils.getTextContent(Tree)"})
  public void testGetTextContent_thenReturnA() throws UnsupportedEncodingException {
    // Arrange
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    UnbufferedCharStream unbufferedCharStream =
        new UnbufferedCharStream(new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8")));

    Pair<TokenSource, CharStream> source = new Pair<>(sqlStandardLexer, unbufferedCharStream);
    CommonToken symbol = new CommonToken(source, 1, 1, 0, 0);

    // Act and Assert
    assertEquals("A", STMUtils.getTextContent(new STMTreeTermErrorNode(symbol)));
  }

  /**
   * Test {@link STMUtils#getTextContent(Tree)}.
   *
   * <ul>
   *   <li>When {@link ANTLRInputStream#ANTLRInputStream(String)} with {@code Input}.
   *   <li>Then return {@code n}.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#getTextContent(Tree)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String STMUtils.getTextContent(Tree)"})
  public void testGetTextContent_whenANTLRInputStreamWithInput_thenReturnN() {
    // Arrange
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 1, 1);

    // Act and Assert
    assertEquals("n", STMUtils.getTextContent(new STMTreeTermErrorNode(symbol)));
  }

  /**
   * Test {@link STMUtils#getTextContent(Tree)}.
   *
   * <ul>
   *   <li>When {@link ParserRuleContext#EMPTY}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#getTextContent(Tree)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String STMUtils.getTextContent(Tree)"})
  public void testGetTextContent_whenEmpty_thenReturnEmptyString() {
    // Arrange, Act and Assert
    assertEquals("", STMUtils.getTextContent(ParserRuleContext.EMPTY));
  }

  /**
   * Test {@link STMUtils#getTextContent(Tree)}.
   *
   * <ul>
   *   <li>When {@link ErrorNodeImpl#ErrorNodeImpl(Token)} with token is {@link
   *       CommonToken#CommonToken(Pair, int, int, int, int)}.
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link STMUtils#getTextContent(Tree)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String STMUtils.getTextContent(Tree)"})
  public void testGetTextContent_whenErrorNodeImplWithTokenIsCommonToken_thenReturnEmptyString() {
    // Arrange
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken token = new CommonToken(source, 1, 1, 1, 1);

    // Act and Assert
    assertEquals("", STMUtils.getTextContent(new ErrorNodeImpl(token)));
  }
}
