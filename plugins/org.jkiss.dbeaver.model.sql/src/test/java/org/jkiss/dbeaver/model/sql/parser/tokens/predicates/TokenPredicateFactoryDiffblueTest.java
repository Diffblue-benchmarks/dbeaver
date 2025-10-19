package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

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
import java.util.List;
import org.jkiss.dbeaver.model.sql.SQLDialect;
import org.jkiss.dbeaver.model.sql.SQLSyntaxManager;
import org.jkiss.dbeaver.model.sql.parser.SQLRuleManager;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TokenPredicateFactoryDiffblueTest {
  /**
   * Test {@link TokenPredicateFactory#makeDefaultFactory()}.
   *
   * <p>Method under test: {@link TokenPredicateFactory#makeDefaultFactory()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateFactory TokenPredicateFactory.makeDefaultFactory()"})
  public void testMakeDefaultFactory() {
    // Arrange and Act
    TokenPredicateFactory actualMakeDefaultFactoryResult =
        TokenPredicateFactory.makeDefaultFactory();

    // Assert
    assertTrue(actualMakeDefaultFactoryResult instanceof DefaultTokenPredicateFactory);
    TokenPredicateNode notResult = actualMakeDefaultFactoryResult.not("Str");
    assertTrue(notResult instanceof SQLTokenEntry);
    assertEquals("Str", ((SQLTokenEntry) notResult).getString());
    assertEquals(SQLTokenType.T_UNKNOWN, ((SQLTokenEntry) notResult).getTokenType());
    assertEquals(SQLTokenType.T_UNKNOWN, actualMakeDefaultFactoryResult.classifyToken("ABC123"));
    assertTrue(((SQLTokenEntry) notResult).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#makeDialectSpecificFactory(SQLRuleManager)}.
   *
   * <ul>
   *   <li>Given {@link SQLDialect}.
   *   <li>Then not {@code Str} return {@link SQLTokenEntry}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#makeDialectSpecificFactory(SQLRuleManager)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TokenPredicateFactory TokenPredicateFactory.makeDialectSpecificFactory(SQLRuleManager)"
  })
  public void testMakeDialectSpecificFactory_givenSQLDialect_thenNotStrReturnSQLTokenEntry() {
    // Arrange
    SQLSyntaxManager syntaxManager = mock(SQLSyntaxManager.class);
    when(syntaxManager.getDialect()).thenReturn(mock(SQLDialect.class));

    // Act
    TokenPredicateFactory actualMakeDialectSpecificFactoryResult =
        TokenPredicateFactory.makeDialectSpecificFactory(new SQLRuleManager(syntaxManager));

    // Assert
    verify(syntaxManager).getDialect();
    TokenPredicateNode notResult = actualMakeDialectSpecificFactoryResult.not("Str");
    assertTrue(notResult instanceof SQLTokenEntry);
    assertTrue(actualMakeDialectSpecificFactoryResult instanceof SQLTokenPredicateFactory);
    assertEquals("Str", ((SQLTokenEntry) notResult).getString());
    assertNull(((SQLTokenEntry) notResult).getTokenType());
    assertNull(actualMakeDialectSpecificFactoryResult.classifyToken("String"));
    assertTrue(((SQLTokenEntry) notResult).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#token(Object)}.
   *
   * <p>Method under test: {@link TokenPredicateFactory#token(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.token(Object)"})
  public void testToken() {
    // Arrange
    DefaultTokenPredicateFactory defaultTokenPredicateFactory = new DefaultTokenPredicateFactory();
    OptionalTokenPredicateNode optionalTokenPredicateNode =
        new OptionalTokenPredicateNode(mock(TokenPredicateNode.class));
    AlternativeTokenPredicateNode alternativeTokenPredicateNode =
        new AlternativeTokenPredicateNode(optionalTokenPredicateNode);

    // Act
    TokenPredicateNode actualTokenResult =
        defaultTokenPredicateFactory.token(alternativeTokenPredicateNode);

    // Assert
    assertSame(alternativeTokenPredicateNode, actualTokenResult);
  }

  /**
   * Test {@link TokenPredicateFactory#token(Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return String is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#token(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.token(Object)"})
  public void testToken_whenNull_thenReturnStringIsNull() {
    // Arrange and Act
    TokenPredicateNode actualTokenResult = new DefaultTokenPredicateFactory().token(null);

    // Assert
    assertTrue(actualTokenResult instanceof SQLTokenEntry);
    assertNull(((SQLTokenEntry) actualTokenResult).getString());
    assertNull(((SQLTokenEntry) actualTokenResult).getTokenType());
    assertFalse(((SQLTokenEntry) actualTokenResult).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#token(Object)}.
   *
   * <ul>
   *   <li>When {@code Obj}.
   *   <li>Then return String is {@code Obj}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#token(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.token(Object)"})
  public void testToken_whenObj_thenReturnStringIsObj() {
    // Arrange and Act
    TokenPredicateNode actualTokenResult = new DefaultTokenPredicateFactory().token("Obj");

    // Assert
    assertTrue(actualTokenResult instanceof SQLTokenEntry);
    assertEquals("Obj", ((SQLTokenEntry) actualTokenResult).getString());
    assertEquals(SQLTokenType.T_UNKNOWN, ((SQLTokenEntry) actualTokenResult).getTokenType());
    assertFalse(((SQLTokenEntry) actualTokenResult).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#token(Object)}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#token(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.token(Object)"})
  public void testToken_whenOne_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(IllegalArgumentException.class, () -> new DefaultTokenPredicateFactory().token(1));
  }

  /**
   * Test {@link TokenPredicateFactory#captureToken(String, String)} with {@code string}, {@code
   * key}.
   *
   * <p>Method under test: {@link TokenPredicateFactory#captureToken(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.captureToken(String, String)"})
  public void testCaptureTokenWithStringKey() {
    // Arrange and Act
    TokenPredicateNode actualCaptureTokenResult =
        new DefaultTokenPredicateFactory().captureToken("String", "Key");

    // Assert
    assertTrue(actualCaptureTokenResult instanceof CaptureTokenPredicateNode);
    assertEquals("Key", ((CaptureTokenPredicateNode) actualCaptureTokenResult).key);
    assertEquals("String", ((CaptureTokenPredicateNode) actualCaptureTokenResult).getString());
    assertEquals(
        SQLTokenType.T_UNKNOWN,
        ((CaptureTokenPredicateNode) actualCaptureTokenResult).getTokenType());
    assertFalse(((CaptureTokenPredicateNode) actualCaptureTokenResult).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#captureToken(SQLTokenType, String)} with {@code type}, {@code
   * key}.
   *
   * <p>Method under test: {@link TokenPredicateFactory#captureToken(SQLTokenType, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.captureToken(SQLTokenType, String)"})
  public void testCaptureTokenWithTypeKey() {
    // Arrange and Act
    TokenPredicateNode actualCaptureTokenResult =
        new DefaultTokenPredicateFactory().captureToken(SQLTokenType.T_KEYWORD, "Key");

    // Assert
    assertTrue(actualCaptureTokenResult instanceof CaptureTokenPredicateNode);
    assertEquals("Key", ((CaptureTokenPredicateNode) actualCaptureTokenResult).key);
    assertNull(((CaptureTokenPredicateNode) actualCaptureTokenResult).getString());
    assertEquals(
        SQLTokenType.T_KEYWORD,
        ((CaptureTokenPredicateNode) actualCaptureTokenResult).getTokenType());
    assertFalse(((CaptureTokenPredicateNode) actualCaptureTokenResult).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#captureTokenClassifiedAs(String, String)}.
   *
   * <p>Method under test: {@link TokenPredicateFactory#captureTokenClassifiedAs(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "TokenPredicateNode TokenPredicateFactory.captureTokenClassifiedAs(String, String)"
  })
  public void testCaptureTokenClassifiedAs() {
    // Arrange and Act
    TokenPredicateNode actualCaptureTokenClassifiedAsResult =
        new DefaultTokenPredicateFactory().captureTokenClassifiedAs("Example String", "Key");

    // Assert
    assertTrue(actualCaptureTokenClassifiedAsResult instanceof CaptureTokenPredicateNode);
    assertEquals("Key", ((CaptureTokenPredicateNode) actualCaptureTokenClassifiedAsResult).key);
    assertNull(((CaptureTokenPredicateNode) actualCaptureTokenClassifiedAsResult).getString());
    assertEquals(
        SQLTokenType.T_UNKNOWN,
        ((CaptureTokenPredicateNode) actualCaptureTokenClassifiedAsResult).getTokenType());
    assertFalse(((CaptureTokenPredicateNode) actualCaptureTokenClassifiedAsResult).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#sequence(TokenPredicateNode[])} with {@code nodes}.
   *
   * <ul>
   *   <li>Then return {@link SequenceTokenPredicateNode}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#sequence(TokenPredicateNode[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.sequence(TokenPredicateNode[])"})
  public void testSequenceWithNodes_thenReturnSequenceTokenPredicateNode() {
    // Arrange
    DefaultTokenPredicateFactory defaultTokenPredicateFactory = new DefaultTokenPredicateFactory();
    AlternativeTokenPredicateNode child =
        new AlternativeTokenPredicateNode(mock(TokenPredicateNode.class));
    OptionalTokenPredicateNode optionalTokenPredicateNode = new OptionalTokenPredicateNode(child);

    // Act
    TokenPredicateNode actualSequenceResult =
        defaultTokenPredicateFactory.sequence(optionalTokenPredicateNode);

    // Assert
    assertTrue(actualSequenceResult instanceof SequenceTokenPredicateNode);
    List<TokenPredicateNode> tokenPredicateNodeList =
        ((SequenceTokenPredicateNode) actualSequenceResult).childs;
    assertEquals(1, tokenPredicateNodeList.size());
    assertSame(optionalTokenPredicateNode, tokenPredicateNodeList.get(0));
  }

  /**
   * Test {@link TokenPredicateFactory#sequence(Object[])} with {@code objs}.
   *
   * <p>Method under test: {@link TokenPredicateFactory#sequence(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.sequence(Object[])"})
  public void testSequenceWithObjs() {
    // Arrange
    DefaultTokenPredicateFactory defaultTokenPredicateFactory = new DefaultTokenPredicateFactory();
    OptionalTokenPredicateNode optionalTokenPredicateNode =
        new OptionalTokenPredicateNode(mock(TokenPredicateNode.class));
    AlternativeTokenPredicateNode alternativeTokenPredicateNode =
        new AlternativeTokenPredicateNode(optionalTokenPredicateNode);

    // Act
    TokenPredicateNode actualSequenceResult =
        defaultTokenPredicateFactory.sequence(alternativeTokenPredicateNode);

    // Assert
    assertTrue(actualSequenceResult instanceof SequenceTokenPredicateNode);
    List<TokenPredicateNode> tokenPredicateNodeList =
        ((SequenceTokenPredicateNode) actualSequenceResult).childs;
    assertEquals(1, tokenPredicateNodeList.size());
    assertSame(alternativeTokenPredicateNode, tokenPredicateNodeList.get(0));
  }

  /**
   * Test {@link TokenPredicateFactory#sequence(Object[])} with {@code objs}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link GroupTokenPredicatesNode#childs} first String is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#sequence(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.sequence(Object[])"})
  public void testSequenceWithObjs_whenNull_thenReturnChildsFirstStringIsNull() {
    // Arrange and Act
    TokenPredicateNode actualSequenceResult =
        new DefaultTokenPredicateFactory().sequence((Object) null);

    // Assert
    List<TokenPredicateNode> tokenPredicateNodeList =
        ((SequenceTokenPredicateNode) actualSequenceResult).childs;
    assertEquals(1, tokenPredicateNodeList.size());
    TokenPredicateNode getResult = tokenPredicateNodeList.get(0);
    assertTrue(getResult instanceof SQLTokenEntry);
    assertTrue(actualSequenceResult instanceof SequenceTokenPredicateNode);
    assertNull(((SQLTokenEntry) getResult).getString());
    assertNull(((SQLTokenEntry) getResult).getTokenType());
    assertFalse(((SQLTokenEntry) getResult).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#sequence(Object[])} with {@code objs}.
   *
   * <ul>
   *   <li>When {@code Objs}.
   *   <li>Then return {@link GroupTokenPredicatesNode#childs} first String is {@code Objs}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#sequence(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.sequence(Object[])"})
  public void testSequenceWithObjs_whenObjs_thenReturnChildsFirstStringIsObjs() {
    // Arrange and Act
    TokenPredicateNode actualSequenceResult = new DefaultTokenPredicateFactory().sequence("Objs");

    // Assert
    List<TokenPredicateNode> tokenPredicateNodeList =
        ((SequenceTokenPredicateNode) actualSequenceResult).childs;
    assertEquals(1, tokenPredicateNodeList.size());
    TokenPredicateNode getResult = tokenPredicateNodeList.get(0);
    assertTrue(getResult instanceof SQLTokenEntry);
    assertTrue(actualSequenceResult instanceof SequenceTokenPredicateNode);
    assertEquals("Objs", ((SQLTokenEntry) getResult).getString());
    assertEquals(SQLTokenType.T_UNKNOWN, ((SQLTokenEntry) getResult).getTokenType());
    assertFalse(((SQLTokenEntry) getResult).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#sequence(Object[])} with {@code objs}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#sequence(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.sequence(Object[])"})
  public void testSequenceWithObjs_whenOne_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new DefaultTokenPredicateFactory().sequence(1));
  }

  /**
   * Test {@link TokenPredicateFactory#alternative(TokenPredicateNode[])} with {@code nodes}.
   *
   * <ul>
   *   <li>Then return {@link AlternativeTokenPredicateNode}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#alternative(TokenPredicateNode[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.alternative(TokenPredicateNode[])"})
  public void testAlternativeWithNodes_thenReturnAlternativeTokenPredicateNode() {
    // Arrange
    DefaultTokenPredicateFactory defaultTokenPredicateFactory = new DefaultTokenPredicateFactory();
    AlternativeTokenPredicateNode child =
        new AlternativeTokenPredicateNode(mock(TokenPredicateNode.class));
    OptionalTokenPredicateNode optionalTokenPredicateNode = new OptionalTokenPredicateNode(child);

    // Act
    TokenPredicateNode actualAlternativeResult =
        defaultTokenPredicateFactory.alternative(optionalTokenPredicateNode);

    // Assert
    assertTrue(actualAlternativeResult instanceof AlternativeTokenPredicateNode);
    List<TokenPredicateNode> tokenPredicateNodeList =
        ((AlternativeTokenPredicateNode) actualAlternativeResult).childs;
    assertEquals(1, tokenPredicateNodeList.size());
    assertSame(optionalTokenPredicateNode, tokenPredicateNodeList.get(0));
  }

  /**
   * Test {@link TokenPredicateFactory#alternative(Object[])} with {@code objs}.
   *
   * <p>Method under test: {@link TokenPredicateFactory#alternative(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.alternative(Object[])"})
  public void testAlternativeWithObjs() {
    // Arrange
    DefaultTokenPredicateFactory defaultTokenPredicateFactory = new DefaultTokenPredicateFactory();
    OptionalTokenPredicateNode optionalTokenPredicateNode =
        new OptionalTokenPredicateNode(mock(TokenPredicateNode.class));
    AlternativeTokenPredicateNode alternativeTokenPredicateNode =
        new AlternativeTokenPredicateNode(optionalTokenPredicateNode);

    // Act
    TokenPredicateNode actualAlternativeResult =
        defaultTokenPredicateFactory.alternative(alternativeTokenPredicateNode);

    // Assert
    assertTrue(actualAlternativeResult instanceof AlternativeTokenPredicateNode);
    List<TokenPredicateNode> tokenPredicateNodeList =
        ((AlternativeTokenPredicateNode) actualAlternativeResult).childs;
    assertEquals(1, tokenPredicateNodeList.size());
    assertSame(alternativeTokenPredicateNode, tokenPredicateNodeList.get(0));
  }

  /**
   * Test {@link TokenPredicateFactory#alternative(Object[])} with {@code objs}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link GroupTokenPredicatesNode#childs} first String is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#alternative(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.alternative(Object[])"})
  public void testAlternativeWithObjs_whenNull_thenReturnChildsFirstStringIsNull() {
    // Arrange and Act
    TokenPredicateNode actualAlternativeResult =
        new DefaultTokenPredicateFactory().alternative((Object) null);

    // Assert
    assertTrue(actualAlternativeResult instanceof AlternativeTokenPredicateNode);
    List<TokenPredicateNode> tokenPredicateNodeList =
        ((AlternativeTokenPredicateNode) actualAlternativeResult).childs;
    assertEquals(1, tokenPredicateNodeList.size());
    TokenPredicateNode getResult = tokenPredicateNodeList.get(0);
    assertTrue(getResult instanceof SQLTokenEntry);
    assertNull(((SQLTokenEntry) getResult).getString());
    assertNull(((SQLTokenEntry) getResult).getTokenType());
    assertFalse(((SQLTokenEntry) getResult).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#alternative(Object[])} with {@code objs}.
   *
   * <ul>
   *   <li>When {@code Objs}.
   *   <li>Then return {@link GroupTokenPredicatesNode#childs} first String is {@code Objs}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#alternative(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.alternative(Object[])"})
  public void testAlternativeWithObjs_whenObjs_thenReturnChildsFirstStringIsObjs() {
    // Arrange and Act
    TokenPredicateNode actualAlternativeResult =
        new DefaultTokenPredicateFactory().alternative("Objs");

    // Assert
    assertTrue(actualAlternativeResult instanceof AlternativeTokenPredicateNode);
    List<TokenPredicateNode> tokenPredicateNodeList =
        ((AlternativeTokenPredicateNode) actualAlternativeResult).childs;
    assertEquals(1, tokenPredicateNodeList.size());
    TokenPredicateNode getResult = tokenPredicateNodeList.get(0);
    assertTrue(getResult instanceof SQLTokenEntry);
    assertEquals("Objs", ((SQLTokenEntry) getResult).getString());
    assertEquals(SQLTokenType.T_UNKNOWN, ((SQLTokenEntry) getResult).getTokenType());
    assertFalse(((SQLTokenEntry) getResult).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#alternative(Object[])} with {@code objs}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#alternative(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.alternative(Object[])"})
  public void testAlternativeWithObjs_whenOne_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new DefaultTokenPredicateFactory().alternative(1));
  }

  /**
   * Test {@link TokenPredicateFactory#optional(TokenPredicateNode)} with {@code node}.
   *
   * <p>Method under test: {@link TokenPredicateFactory#optional(TokenPredicateNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.optional(TokenPredicateNode)"})
  public void testOptionalWithNode() {
    // Arrange
    DefaultTokenPredicateFactory defaultTokenPredicateFactory = new DefaultTokenPredicateFactory();
    AlternativeTokenPredicateNode child =
        new AlternativeTokenPredicateNode(mock(TokenPredicateNode.class));
    OptionalTokenPredicateNode node = new OptionalTokenPredicateNode(child);

    // Act
    TokenPredicateNode actualOptionalResult = defaultTokenPredicateFactory.optional(node);

    // Assert
    assertTrue(actualOptionalResult instanceof OptionalTokenPredicateNode);
    TokenPredicateNode tokenPredicateNode =
        ((OptionalTokenPredicateNode) actualOptionalResult).child;
    assertTrue(tokenPredicateNode instanceof OptionalTokenPredicateNode);
    assertSame(node.child, ((OptionalTokenPredicateNode) tokenPredicateNode).child);
  }

  /**
   * Test {@link TokenPredicateFactory#optional(Object[])} with {@code obj}.
   *
   * <ul>
   *   <li>Then {@link UnaryTokenPredicateNode#child} return {@link AlternativeTokenPredicateNode}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#optional(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.optional(Object[])"})
  public void testOptionalWithObj_thenChildReturnAlternativeTokenPredicateNode() {
    // Arrange
    DefaultTokenPredicateFactory defaultTokenPredicateFactory = new DefaultTokenPredicateFactory();
    OptionalTokenPredicateNode optionalTokenPredicateNode =
        new OptionalTokenPredicateNode(mock(TokenPredicateNode.class));
    AlternativeTokenPredicateNode alternativeTokenPredicateNode =
        new AlternativeTokenPredicateNode(optionalTokenPredicateNode);
    Object[] obj = new Object[] {alternativeTokenPredicateNode};

    // Act
    TokenPredicateNode actualOptionalResult = defaultTokenPredicateFactory.optional(obj);

    // Assert
    TokenPredicateNode tokenPredicateNode =
        ((OptionalTokenPredicateNode) actualOptionalResult).child;
    assertTrue(tokenPredicateNode instanceof AlternativeTokenPredicateNode);
    assertTrue(actualOptionalResult instanceof OptionalTokenPredicateNode);
    assertEquals(1, obj.length);
    assertSame(
        alternativeTokenPredicateNode.childs,
        ((AlternativeTokenPredicateNode) tokenPredicateNode).childs);
    assertSame(((UnaryTokenPredicateNode) actualOptionalResult).child, obj[0]);
  }

  /**
   * Test {@link TokenPredicateFactory#optional(Object[])} with {@code obj}.
   *
   * <ul>
   *   <li>Then return {@link UnaryTokenPredicateNode#child} {@link GroupTokenPredicatesNode#childs}
   *       Empty.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#optional(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.optional(Object[])"})
  public void testOptionalWithObj_thenReturnChildChildsEmpty() {
    // Arrange and Act
    TokenPredicateNode actualOptionalResult = new DefaultTokenPredicateFactory().optional();

    // Assert
    assertTrue(actualOptionalResult instanceof OptionalTokenPredicateNode);
    TokenPredicateNode tokenPredicateNode =
        ((OptionalTokenPredicateNode) actualOptionalResult).child;
    assertTrue(tokenPredicateNode instanceof SequenceTokenPredicateNode);
    assertTrue(((SequenceTokenPredicateNode) tokenPredicateNode).childs.isEmpty());
  }

  /**
   * Test {@link TokenPredicateFactory#optional(Object[])} with {@code obj}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link UnaryTokenPredicateNode#child} String is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#optional(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.optional(Object[])"})
  public void testOptionalWithObj_whenNull_thenReturnChildStringIsNull() {
    // Arrange and Act
    TokenPredicateNode actualOptionalResult =
        new DefaultTokenPredicateFactory().optional((Object) null);

    // Assert
    assertTrue(actualOptionalResult instanceof OptionalTokenPredicateNode);
    TokenPredicateNode tokenPredicateNode =
        ((OptionalTokenPredicateNode) actualOptionalResult).child;
    assertTrue(tokenPredicateNode instanceof SQLTokenEntry);
    assertNull(((SQLTokenEntry) tokenPredicateNode).getString());
    assertNull(((SQLTokenEntry) tokenPredicateNode).getTokenType());
    assertFalse(((SQLTokenEntry) tokenPredicateNode).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#optional(Object[])} with {@code obj}.
   *
   * <ul>
   *   <li>When {@code Obj} and {@code Obj}.
   *   <li>Then return {@link UnaryTokenPredicateNode#child} {@link GroupTokenPredicatesNode#childs}
   *       size is two.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#optional(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.optional(Object[])"})
  public void testOptionalWithObj_whenObjAndObj_thenReturnChildChildsSizeIsTwo() {
    // Arrange and Act
    TokenPredicateNode actualOptionalResult =
        new DefaultTokenPredicateFactory().optional("Obj", "Obj");

    // Assert
    assertTrue(actualOptionalResult instanceof OptionalTokenPredicateNode);
    TokenPredicateNode tokenPredicateNode =
        ((OptionalTokenPredicateNode) actualOptionalResult).child;
    List<TokenPredicateNode> tokenPredicateNodeList =
        ((SequenceTokenPredicateNode) tokenPredicateNode).childs;
    assertEquals(2, tokenPredicateNodeList.size());
    TokenPredicateNode getResult = tokenPredicateNodeList.get(0);
    assertTrue(getResult instanceof SQLTokenEntry);
    TokenPredicateNode getResult2 = tokenPredicateNodeList.get(1);
    assertTrue(getResult2 instanceof SQLTokenEntry);
    assertTrue(tokenPredicateNode instanceof SequenceTokenPredicateNode);
    assertEquals("Obj", ((SQLTokenEntry) getResult).getString());
    assertEquals(SQLTokenType.T_UNKNOWN, ((SQLTokenEntry) getResult).getTokenType());
    assertFalse(((SQLTokenEntry) getResult).isInverted());
    assertEquals(getResult, getResult2);
  }

  /**
   * Test {@link TokenPredicateFactory#optional(Object[])} with {@code obj}.
   *
   * <ul>
   *   <li>When {@code Obj}.
   *   <li>Then return {@link UnaryTokenPredicateNode#child} String is {@code Obj}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#optional(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.optional(Object[])"})
  public void testOptionalWithObj_whenObj_thenReturnChildStringIsObj() {
    // Arrange and Act
    TokenPredicateNode actualOptionalResult = new DefaultTokenPredicateFactory().optional("Obj");

    // Assert
    assertTrue(actualOptionalResult instanceof OptionalTokenPredicateNode);
    TokenPredicateNode tokenPredicateNode =
        ((OptionalTokenPredicateNode) actualOptionalResult).child;
    assertTrue(tokenPredicateNode instanceof SQLTokenEntry);
    assertEquals("Obj", ((SQLTokenEntry) tokenPredicateNode).getString());
    assertEquals(SQLTokenType.T_UNKNOWN, ((SQLTokenEntry) tokenPredicateNode).getTokenType());
    assertFalse(((SQLTokenEntry) tokenPredicateNode).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#optional(Object[])} with {@code obj}.
   *
   * <ul>
   *   <li>When one and {@code Obj}.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#optional(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.optional(Object[])"})
  public void testOptionalWithObj_whenOneAndObj_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class,
        () -> new DefaultTokenPredicateFactory().optional(1, "Obj"));
  }

  /**
   * Test {@link TokenPredicateFactory#optional(Object[])} with {@code obj}.
   *
   * <ul>
   *   <li>When one.
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateFactory#optional(Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.optional(Object[])"})
  public void testOptionalWithObj_whenOne_thenThrowIllegalArgumentException() {
    // Arrange, Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> new DefaultTokenPredicateFactory().optional(1));
  }

  /**
   * Test {@link TokenPredicateFactory#not(String)} with {@code str}.
   *
   * <p>Method under test: {@link TokenPredicateFactory#not(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.not(String)"})
  public void testNotWithStr() {
    // Arrange and Act
    TokenPredicateNode actualNotResult = new DefaultTokenPredicateFactory().not("Str");

    // Assert
    assertTrue(actualNotResult instanceof SQLTokenEntry);
    assertEquals("Str", ((SQLTokenEntry) actualNotResult).getString());
    assertEquals(SQLTokenType.T_UNKNOWN, ((SQLTokenEntry) actualNotResult).getTokenType());
    assertTrue(((SQLTokenEntry) actualNotResult).isInverted());
  }

  /**
   * Test {@link TokenPredicateFactory#not(SQLTokenType)} with {@code token}.
   *
   * <p>Method under test: {@link TokenPredicateFactory#not(SQLTokenType)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateNode TokenPredicateFactory.not(SQLTokenType)"})
  public void testNotWithToken() {
    // Arrange and Act
    TokenPredicateNode actualNotResult =
        new DefaultTokenPredicateFactory().not(SQLTokenType.T_KEYWORD);

    // Assert
    assertTrue(actualNotResult instanceof SQLTokenEntry);
    assertNull(((SQLTokenEntry) actualNotResult).getString());
    assertEquals(SQLTokenType.T_KEYWORD, ((SQLTokenEntry) actualNotResult).getTokenType());
    assertTrue(((SQLTokenEntry) actualNotResult).isInverted());
  }
}
