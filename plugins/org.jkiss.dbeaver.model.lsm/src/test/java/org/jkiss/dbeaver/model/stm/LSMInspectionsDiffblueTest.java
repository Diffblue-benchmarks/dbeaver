package org.jkiss.dbeaver.model.stm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.pattern.RuleTagToken;
import org.jkiss.dbeaver.model.lsm.sql.impl.syntax.SQLStandardLexer;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.stm.LSMInspections.NameInspectionResult;
import org.jkiss.dbeaver.model.stm.LSMInspections.SyntaxInspectionResult;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LSMInspectionsDiffblueTest {
  /**
   * Test {@link LSMInspections#matchesAnyWord(String)}.
   *
   * <ul>
   *   <li>When {@code Boolean}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#matchesAnyWord(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LSMInspections.matchesAnyWord(String)"})
  public void testMatchesAnyWord_whenJavaLangBoolean_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(LSMInspections.matchesAnyWord("java.lang.Boolean"));
  }

  /**
   * Test {@link LSMInspections#matchesAnyWord(String)}.
   *
   * <ul>
   *   <li>When {@code Str}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#matchesAnyWord(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean LSMInspections.matchesAnyWord(String)"})
  public void testMatchesAnyWord_whenStr_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(LSMInspections.matchesAnyWord("Str"));
  }

  /**
   * Test {@link LSMInspections#matchAnyWordHead(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#matchAnyWordHead(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Interval LSMInspections.matchAnyWordHead(String)"})
  public void testMatchAnyWordHead_whenEmptyString_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(LSMInspections.matchAnyWordHead(""));
  }

  /**
   * Test {@link LSMInspections#matchAnyWordHead(String)}.
   *
   * <ul>
   *   <li>When {@code Str}.
   *   <li>Then return {@link Interval#a} is zero.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#matchAnyWordHead(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Interval LSMInspections.matchAnyWordHead(String)"})
  public void testMatchAnyWordHead_whenStr_thenReturnAIsZero() {
    // Arrange and Act
    Interval actualMatchAnyWordHeadResult = LSMInspections.matchAnyWordHead("Str");

    // Assert
    assertEquals(0, actualMatchAnyWordHeadResult.a);
    assertEquals(2, actualMatchAnyWordHeadResult.b);
    assertEquals(3, actualMatchAnyWordHeadResult.length());
  }

  /**
   * Test {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}.
   *
   * <p>Method under test: {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LSMInspections.<init>(SQLDialect, STMTreeNode)"})
  public void testNewLSMInspections() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 1, 1);

    // Act
    LSMInspections actualLsmInspections =
        new LSMInspections(dialect, new STMTreeTermErrorNode(symbol));

    // Assert
    NameInspectionResult collectNameNodesResult = actualLsmInspections.collectNameNodes(1);
    assertNull(collectNameNodesResult.currentTerm());
    assertEquals(1, collectNameNodesResult.positionToInspect());
    assertFalse(collectNameNodesResult.hasPeriod());
    assertTrue(collectNameNodesResult.nameNodes().isEmpty());
  }

  /**
   * Test {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}.
   *
   * <p>Method under test: {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LSMInspections.<init>(SQLDialect, STMTreeNode)"})
  public void testNewLSMInspections2() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, -1, 1);

    // Act
    LSMInspections actualLsmInspections =
        new LSMInspections(dialect, new STMTreeTermErrorNode(symbol));

    // Assert
    NameInspectionResult collectNameNodesResult = actualLsmInspections.collectNameNodes(1);
    assertNull(collectNameNodesResult.currentTerm());
    assertEquals(1, collectNameNodesResult.positionToInspect());
    assertFalse(collectNameNodesResult.hasPeriod());
    assertTrue(collectNameNodesResult.nameNodes().isEmpty());
  }

  /**
   * Test {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}.
   *
   * <p>Method under test: {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LSMInspections.<init>(SQLDialect, STMTreeNode)"})
  public void testNewLSMInspections3() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 1, -1);

    // Act
    LSMInspections actualLsmInspections =
        new LSMInspections(dialect, new STMTreeTermErrorNode(symbol));

    // Assert
    NameInspectionResult collectNameNodesResult = actualLsmInspections.collectNameNodes(1);
    assertNull(collectNameNodesResult.currentTerm());
    assertEquals(1, collectNameNodesResult.positionToInspect());
    assertFalse(collectNameNodesResult.hasPeriod());
    assertTrue(collectNameNodesResult.nameNodes().isEmpty());
  }

  /**
   * Test {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}.
   *
   * <ul>
   *   <li>Given {@link ParserRuleContext#EMPTY}.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LSMInspections.<init>(SQLDialect, STMTreeNode)"})
  public void testNewLSMInspections_givenEmpty() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);

    STMTreeTermNode root = new STMTreeTermNode(new RuleTagToken("Rule Name", 1));
    root.setParent(ParserRuleContext.EMPTY);

    // Act
    LSMInspections actualLsmInspections = new LSMInspections(dialect, root);

    // Assert
    NameInspectionResult collectNameNodesResult = actualLsmInspections.collectNameNodes(1);
    assertNull(collectNameNodesResult.currentTerm());
    assertEquals(1, collectNameNodesResult.positionToInspect());
    assertFalse(collectNameNodesResult.hasPeriod());
    assertTrue(collectNameNodesResult.nameNodes().isEmpty());
  }

  /**
   * Test {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}.
   *
   * <ul>
   *   <li>Then return collectNameNodes one nameNodes size is one.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LSMInspections.<init>(SQLDialect, STMTreeNode)"})
  public void testNewLSMInspections_thenReturnCollectNameNodesOneNameNodesSizeIsOne() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 0, 1);
    STMTreeTermErrorNode root = new STMTreeTermErrorNode(symbol);

    // Act
    LSMInspections actualLsmInspections = new LSMInspections(dialect, root);

    // Assert
    NameInspectionResult collectNameNodesResult = actualLsmInspections.collectNameNodes(1);
    assertEquals(1, collectNameNodesResult.nameNodes().size());
    assertSame(root, collectNameNodesResult.currentTerm());
  }

  /**
   * Test {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}.
   *
   * <ul>
   *   <li>When {@link CommonToken#CommonToken(int, String)} with type is one and text is empty
   *       string.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LSMInspections.<init>(SQLDialect, STMTreeNode)"})
  public void testNewLSMInspections_whenCommonTokenWithTypeIsOneAndTextIsEmptyString() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);

    // Act
    LSMInspections actualLsmInspections =
        new LSMInspections(dialect, new STMTreeTermErrorNode(new CommonToken(1, "")));

    // Assert
    assertNull(actualLsmInspections.prepareAbstractSyntaxInspection(1));
  }

  /**
   * Test {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}.
   *
   * <ul>
   *   <li>When {@link CommonToken#CommonToken(int, String)} with type is one and text is {@code U}.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LSMInspections.<init>(SQLDialect, STMTreeNode)"})
  public void testNewLSMInspections_whenCommonTokenWithTypeIsOneAndTextIsU() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);

    // Act
    LSMInspections actualLsmInspections =
        new LSMInspections(dialect, new STMTreeTermErrorNode(new CommonToken(1, "U")));

    // Assert
    assertNull(actualLsmInspections.prepareAbstractSyntaxInspection(1));
  }

  /**
   * Test {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}.
   *
   * <ul>
   *   <li>When {@link STMTreeRuleNode#STMTreeRuleNode()}.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#LSMInspections(SQLDialect, STMTreeNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LSMInspections.<init>(SQLDialect, STMTreeNode)"})
  public void testNewLSMInspections_whenSTMTreeRuleNode() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);

    // Act
    LSMInspections actualLsmInspections = new LSMInspections(dialect, new STMTreeRuleNode());

    // Assert
    NameInspectionResult collectNameNodesResult = actualLsmInspections.collectNameNodes(1);
    assertNull(collectNameNodesResult.currentTerm());
    assertEquals(1, collectNameNodesResult.positionToInspect());
    assertFalse(collectNameNodesResult.hasPeriod());
    assertTrue(collectNameNodesResult.nameNodes().isEmpty());
  }

  /**
   * Test {@link LSMInspections#prepareOffquerySyntaxInspection()}.
   *
   * <p>Method under test: {@link LSMInspections#prepareOffquerySyntaxInspection()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SyntaxInspectionResult LSMInspections.prepareOffquerySyntaxInspection()"})
  public void testPrepareOffquerySyntaxInspection() {
    // Arrange and Act
    SyntaxInspectionResult actualPrepareOffquerySyntaxInspectionResult =
        LSMInspections.prepareOffquerySyntaxInspection();

    // Assert
    assertEquals(17, actualPrepareOffquerySyntaxInspectionResult.predictedTokenIds().size());
    assertEquals(17, actualPrepareOffquerySyntaxInspectionResult.predictedWords().size());
    Map<Integer, Boolean> reachabilityTestsResult =
        actualPrepareOffquerySyntaxInspectionResult.reachabilityTests();
    assertEquals(7, reachabilityTestsResult.size());
    assertFalse(actualPrepareOffquerySyntaxInspectionResult.expectingColumnIntroduction());
    assertFalse(actualPrepareOffquerySyntaxInspectionResult.expectingColumnName());
    assertFalse(actualPrepareOffquerySyntaxInspectionResult.expectingColumnReference());
    assertFalse(actualPrepareOffquerySyntaxInspectionResult.expectingJoinCondition());
    assertFalse(actualPrepareOffquerySyntaxInspectionResult.expectingValue());
    assertTrue(reachabilityTestsResult.containsKey(113));
    assertTrue(reachabilityTestsResult.containsKey(121));
    assertTrue(reachabilityTestsResult.containsKey(19));
    assertTrue(reachabilityTestsResult.containsKey(33));
    assertTrue(reachabilityTestsResult.containsKey(73));
    assertTrue(reachabilityTestsResult.containsKey(87));
    assertTrue(actualPrepareOffquerySyntaxInspectionResult.expectingIdentifier());
    assertTrue(actualPrepareOffquerySyntaxInspectionResult.expectingTableReference());
    assertTrue(actualPrepareOffquerySyntaxInspectionResult.expectingTableSourceIntroduction());
  }

  /**
   * Test {@link LSMInspections#prepareAbstractSyntaxInspection(int)}.
   *
   * <p>Method under test: {@link LSMInspections#prepareAbstractSyntaxInspection(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SyntaxInspectionResult LSMInspections.prepareAbstractSyntaxInspection(int)"})
  public void testPrepareAbstractSyntaxInspection() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 2, 1);
    LSMInspections lsmInspections = new LSMInspections(dialect, new STMTreeTermNode(symbol));

    // Act and Assert
    assertNull(lsmInspections.prepareAbstractSyntaxInspection(1));
  }

  /**
   * Test {@link LSMInspections#prepareAbstractSyntaxInspection(int)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#prepareAbstractSyntaxInspection(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SyntaxInspectionResult LSMInspections.prepareAbstractSyntaxInspection(int)"})
  public void testPrepareAbstractSyntaxInspection_thenReturnNull() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    LSMInspections lsmInspections = new LSMInspections(dialect, new STMTreeRuleNode());

    // Act and Assert
    assertNull(lsmInspections.prepareAbstractSyntaxInspection(1));
  }

  /**
   * Test {@link LSMInspections#collectNameNodes(int)}.
   *
   * <p>Method under test: {@link LSMInspections#collectNameNodes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NameInspectionResult LSMInspections.collectNameNodes(int)"})
  public void testCollectNameNodes() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    LSMInspections lsmInspections = new LSMInspections(dialect, new STMTreeRuleNode());

    // Act
    NameInspectionResult actualCollectNameNodesResult = lsmInspections.collectNameNodes(1);

    // Assert
    assertNull(actualCollectNameNodesResult.currentTerm());
    assertEquals(1, actualCollectNameNodesResult.positionToInspect());
    assertFalse(actualCollectNameNodesResult.hasPeriod());
    assertTrue(actualCollectNameNodesResult.nameNodes().isEmpty());
  }

  /**
   * Test {@link LSMInspections#collectNameNodes(int)}.
   *
   * <p>Method under test: {@link LSMInspections#collectNameNodes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NameInspectionResult LSMInspections.collectNameNodes(int)"})
  public void testCollectNameNodes2() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 1, 1);
    LSMInspections lsmInspections = new LSMInspections(dialect, new STMTreeTermNode(symbol));

    // Act
    NameInspectionResult actualCollectNameNodesResult = lsmInspections.collectNameNodes(1);

    // Assert
    assertNull(actualCollectNameNodesResult.currentTerm());
    assertEquals(1, actualCollectNameNodesResult.positionToInspect());
    assertFalse(actualCollectNameNodesResult.hasPeriod());
    assertTrue(actualCollectNameNodesResult.nameNodes().isEmpty());
  }

  /**
   * Test {@link LSMInspections#collectNameNodes(int)}.
   *
   * <p>Method under test: {@link LSMInspections#collectNameNodes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NameInspectionResult LSMInspections.collectNameNodes(int)"})
  public void testCollectNameNodes3() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getReservedWords()).thenReturn(new ArrayList<>());
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 0, 1);
    STMTreeTermNode root = new STMTreeTermNode(symbol);

    LSMInspections lsmInspections = new LSMInspections(dialect, root);

    // Act
    NameInspectionResult actualCollectNameNodesResult = lsmInspections.collectNameNodes(1);

    // Assert
    verify(dialect).getReservedWords();
    assertSame(root, actualCollectNameNodesResult.currentTerm());
  }

  /**
   * Test {@link LSMInspections#collectNameNodes(int)}.
   *
   * <p>Method under test: {@link LSMInspections#collectNameNodes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NameInspectionResult LSMInspections.collectNameNodes(int)"})
  public void testCollectNameNodes4() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getReservedWords()).thenReturn(new ArrayList<>());
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, -1, 1, 0, 1);
    STMTreeTermNode root = new STMTreeTermNode(symbol);

    LSMInspections lsmInspections = new LSMInspections(dialect, root);

    // Act
    NameInspectionResult actualCollectNameNodesResult = lsmInspections.collectNameNodes(1);

    // Assert
    verify(dialect).getReservedWords();
    assertSame(root, actualCollectNameNodesResult.currentTerm());
  }

  /**
   * Test {@link LSMInspections#collectNameNodes(int)}.
   *
   * <ul>
   *   <li>Then return nameNodes size is one.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#collectNameNodes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NameInspectionResult LSMInspections.collectNameNodes(int)"})
  public void testCollectNameNodes_thenReturnNameNodesSizeIsOne() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getReservedWords()).thenReturn(new ArrayList<>());
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source = new Pair<>(sqlStandardLexer, new ANTLRInputStream());
    CommonToken symbol = new CommonToken(source, 1, 1, 0, 1);
    LSMInspections lsmInspections = new LSMInspections(dialect, new STMTreeTermNode(symbol));

    // Act
    NameInspectionResult actualCollectNameNodesResult = lsmInspections.collectNameNodes(1);

    // Assert
    verify(dialect).getReservedWords();
    assertNull(actualCollectNameNodesResult.currentTerm());
    assertEquals(1, actualCollectNameNodesResult.nameNodes().size());
    assertEquals(1, actualCollectNameNodesResult.positionToInspect());
    assertFalse(actualCollectNameNodesResult.hasPeriod());
  }

  /**
   * Test {@link LSMInspections#collectNameNodes(int)}.
   *
   * <ul>
   *   <li>Then return positionToInspect is zero.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#collectNameNodes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NameInspectionResult LSMInspections.collectNameNodes(int)"})
  public void testCollectNameNodes_thenReturnPositionToInspectIsZero() {
    // Arrange
    ArrayList<String> stringList = new ArrayList<>();
    stringList.add("");
    stringList.add("foo");

    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getReservedWords()).thenReturn(stringList);
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source = new Pair<>(sqlStandardLexer, new ANTLRInputStream());
    CommonToken symbol = new CommonToken(source, 1, 1, 0, 1);
    LSMInspections lsmInspections = new LSMInspections(dialect, new STMTreeTermNode(symbol));

    // Act
    NameInspectionResult actualCollectNameNodesResult = lsmInspections.collectNameNodes(1);

    // Assert
    verify(dialect).getReservedWords();
    assertNull(actualCollectNameNodesResult.currentTerm());
    assertEquals(0, actualCollectNameNodesResult.positionToInspect());
    assertEquals(1, actualCollectNameNodesResult.nameNodes().size());
    assertFalse(actualCollectNameNodesResult.hasPeriod());
  }

  /**
   * Test {@link LSMInspections#collectNameNodes(int)}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#collectNameNodes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NameInspectionResult LSMInspections.collectNameNodes(int)"})
  public void testCollectNameNodes_thenThrowUnsupportedOperationException() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    when(dialect.getReservedWords()).thenThrow(new UnsupportedOperationException());
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 0, 1);
    LSMInspections lsmInspections = new LSMInspections(dialect, new STMTreeTermNode(symbol));

    // Act and Assert
    assertThrows(UnsupportedOperationException.class, () -> lsmInspections.collectNameNodes(1));
    verify(dialect).getReservedWords();
  }

  /**
   * Test {@link LSMInspections#collectNameNodes(int)}.
   *
   * <ul>
   *   <li>When minus one.
   *   <li>Then return positionToInspect is minus one.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#collectNameNodes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NameInspectionResult LSMInspections.collectNameNodes(int)"})
  public void testCollectNameNodes_whenMinusOne_thenReturnPositionToInspectIsMinusOne() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 0, 1);
    LSMInspections lsmInspections = new LSMInspections(dialect, new STMTreeTermNode(symbol));

    // Act
    NameInspectionResult actualCollectNameNodesResult = lsmInspections.collectNameNodes(-1);

    // Assert
    assertNull(actualCollectNameNodesResult.currentTerm());
    assertEquals(-1, actualCollectNameNodesResult.positionToInspect());
    assertFalse(actualCollectNameNodesResult.hasPeriod());
    assertTrue(actualCollectNameNodesResult.nameNodes().isEmpty());
  }

  /**
   * Test {@link LSMInspections#collectNameNodes(int)}.
   *
   * <ul>
   *   <li>When seven.
   *   <li>Then return positionToInspect is seven.
   * </ul>
   *
   * <p>Method under test: {@link LSMInspections#collectNameNodes(int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"NameInspectionResult LSMInspections.collectNameNodes(int)"})
  public void testCollectNameNodes_whenSeven_thenReturnPositionToInspectIsSeven() {
    // Arrange
    SQLDialect dialect = mock(SQLDialect.class);
    SQLStandardLexer sqlStandardLexer = new SQLStandardLexer(new ANTLRInputStream());
    Pair<TokenSource, CharStream> source =
        new Pair<>(sqlStandardLexer, new ANTLRInputStream("Input"));
    CommonToken symbol = new CommonToken(source, 1, 1, 0, 1);
    LSMInspections lsmInspections = new LSMInspections(dialect, new STMTreeTermNode(symbol));

    // Act
    NameInspectionResult actualCollectNameNodesResult = lsmInspections.collectNameNodes(7);

    // Assert
    assertNull(actualCollectNameNodesResult.currentTerm());
    assertEquals(7, actualCollectNameNodesResult.positionToInspect());
    assertFalse(actualCollectNameNodesResult.hasPeriod());
    assertTrue(actualCollectNameNodesResult.nameNodes().isEmpty());
  }

  /**
   * Test SyntaxInspectionResult {@link SyntaxInspectionResult#getReachabilityByName()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxInspectionResult#getReachabilityByName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SyntaxInspectionResult.getReachabilityByName()"})
  public void testSyntaxInspectionResultGetReachabilityByName_thenReturnEmpty() {
    // Arrange
    HashSet<Integer> predictedTokenIds = new HashSet<>();
    HashSet<String> predictedWords = new HashSet<>();

    SyntaxInspectionResult syntaxInspectionResult =
        new SyntaxInspectionResult(
            predictedTokenIds,
            predictedWords,
            new HashMap<>(),
            true,
            true,
            true,
            true,
            true,
            true,
            true,
            true);

    // Act and Assert
    assertTrue(syntaxInspectionResult.getReachabilityByName().isEmpty());
  }

  /**
   * Test SyntaxInspectionResult {@link SyntaxInspectionResult#getReachabilityByName()}.
   *
   * <ul>
   *   <li>Then return size is seven.
   * </ul>
   *
   * <p>Method under test: {@link SyntaxInspectionResult#getReachabilityByName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map SyntaxInspectionResult.getReachabilityByName()"})
  public void testSyntaxInspectionResultGetReachabilityByName_thenReturnSizeIsSeven() {
    // Arrange and Act
    Map<String, Boolean> actualReachabilityByName =
        LSMInspections.prepareOffquerySyntaxInspection().getReachabilityByName();

    // Assert
    assertEquals(7, actualReachabilityByName.size());
    assertFalse(actualReachabilityByName.get(STMKnownRuleNames.columnName));
    assertFalse(actualReachabilityByName.get(STMKnownRuleNames.columnReference));
    assertFalse(actualReachabilityByName.get(STMKnownRuleNames.derivedColumn));
    assertFalse(actualReachabilityByName.get(STMKnownRuleNames.pattern));
    assertTrue(actualReachabilityByName.get(STMKnownRuleNames.identifier));
    assertTrue(actualReachabilityByName.get(STMKnownRuleNames.nonjoinedTableReference));
    assertTrue(actualReachabilityByName.get(STMKnownRuleNames.tableName));
  }
}
