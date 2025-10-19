package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import org.jkiss.dbeaver.model.sql.parser.TokenEntry;
import org.jkiss.dbeaver.model.sql.parser.tokens.SQLTokenType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TokenPredicateSetDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link TokenPredicateSet#getMaxPrefixLength()}
   *   <li>{@link TokenPredicateSet#getMaxSuffixLength()}
   *   <li>{@link TokenPredicateSet#hasCaptures()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int TokenPredicateSet.getMaxPrefixLength()",
    "int TokenPredicateSet.getMaxSuffixLength()",
    "boolean TokenPredicateSet.hasCaptures()"
  })
  public void testGettersAndSetters() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = new TokenPredicateSet();

    // Act
    int actualMaxPrefixLength = tokenPredicateSet.getMaxPrefixLength();
    int actualMaxSuffixLength = tokenPredicateSet.getMaxSuffixLength();

    // Assert
    assertEquals(0, actualMaxPrefixLength);
    assertEquals(0, actualMaxSuffixLength);
    assertFalse(tokenPredicateSet.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#add(TokenPredicatesCondition)}.
   *
   * <p>Method under test: {@link TokenPredicateSet#add(TokenPredicatesCondition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TokenPredicateSet.add(TokenPredicatesCondition)"})
  public void testAdd() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = new TokenPredicateSet();

    ArrayList<TokenEntry> tokenEntryList = new ArrayList<>();
    tokenEntryList.add(new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));

    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(tokenEntryList);

    TokenPredicatesCondition cond = mock(TokenPredicatesCondition.class);
    when(cond.getPrefixes()).thenReturn(listList);
    when(cond.getSuffixes()).thenReturn(new ArrayList<>());

    // Act
    tokenPredicateSet.add(cond);

    // Assert that nothing has changed
    verify(cond, atLeast(1)).getPrefixes();
    verify(cond).getSuffixes();
    assertFalse(tokenPredicateSet.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#add(TokenPredicatesCondition)}.
   *
   * <p>Method under test: {@link TokenPredicateSet#add(TokenPredicatesCondition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TokenPredicateSet.add(TokenPredicatesCondition)"})
  public void testAdd2() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = new TokenPredicateSet();

    ArrayList<TokenEntry> tokenEntryList = new ArrayList<>();
    tokenEntryList.add(new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));
    tokenEntryList.add(new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));

    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(tokenEntryList);

    TokenPredicatesCondition cond = mock(TokenPredicatesCondition.class);
    when(cond.getPrefixes()).thenReturn(listList);
    when(cond.getSuffixes()).thenReturn(new ArrayList<>());

    // Act
    tokenPredicateSet.add(cond);

    // Assert that nothing has changed
    verify(cond, atLeast(1)).getPrefixes();
    verify(cond).getSuffixes();
    assertFalse(tokenPredicateSet.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#add(TokenPredicatesCondition)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ArrayList#ArrayList()}.
   *   <li>Then not {@link TokenPredicateSet} (default constructor) hasCaptures.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateSet#add(TokenPredicatesCondition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TokenPredicateSet.add(TokenPredicatesCondition)"})
  public void testAdd_givenArrayListAddArrayList_thenNotTokenPredicateSetHasCaptures() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = new TokenPredicateSet();

    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(new ArrayList<>());

    TokenPredicatesCondition cond = mock(TokenPredicatesCondition.class);
    when(cond.getPrefixes()).thenReturn(listList);
    when(cond.getSuffixes()).thenReturn(new ArrayList<>());

    // Act
    tokenPredicateSet.add(cond);

    // Assert that nothing has changed
    verify(cond, atLeast(1)).getPrefixes();
    verify(cond).getSuffixes();
    assertFalse(tokenPredicateSet.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#add(TokenPredicatesCondition)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ArrayList#ArrayList()}.
   *   <li>Then not {@link TokenPredicateSet} (default constructor) hasCaptures.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateSet#add(TokenPredicatesCondition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TokenPredicateSet.add(TokenPredicatesCondition)"})
  public void testAdd_givenArrayListAddArrayList_thenNotTokenPredicateSetHasCaptures2() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = new TokenPredicateSet();

    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(new ArrayList<>());
    listList.add(new ArrayList<>());

    TokenPredicatesCondition cond = mock(TokenPredicatesCondition.class);
    when(cond.getPrefixes()).thenReturn(listList);
    when(cond.getSuffixes()).thenReturn(new ArrayList<>());

    // Act
    tokenPredicateSet.add(cond);

    // Assert that nothing has changed
    verify(cond, atLeast(1)).getPrefixes();
    verify(cond).getSuffixes();
    assertFalse(tokenPredicateSet.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#add(TokenPredicatesCondition)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ArrayList#ArrayList()}.
   *   <li>Then not {@link TokenPredicateSet} (default constructor) hasCaptures.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateSet#add(TokenPredicatesCondition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TokenPredicateSet.add(TokenPredicatesCondition)"})
  public void testAdd_givenArrayListAddArrayList_thenNotTokenPredicateSetHasCaptures3() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = new TokenPredicateSet();

    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(new ArrayList<>());

    TokenPredicatesCondition cond = mock(TokenPredicatesCondition.class);
    when(cond.getPrefixes()).thenReturn(new ArrayList<>());
    when(cond.getSuffixes()).thenReturn(listList);

    // Act
    tokenPredicateSet.add(cond);

    // Assert that nothing has changed
    verify(cond, atLeast(1)).getPrefixes();
    verify(cond).getSuffixes();
    assertFalse(tokenPredicateSet.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#add(TokenPredicatesCondition)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ArrayList#ArrayList()}.
   *   <li>Then not {@link TokenPredicateSet} (default constructor) hasCaptures.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateSet#add(TokenPredicatesCondition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TokenPredicateSet.add(TokenPredicatesCondition)"})
  public void testAdd_givenArrayListAddArrayList_thenNotTokenPredicateSetHasCaptures4() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = new TokenPredicateSet();

    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(new ArrayList<>());
    listList.add(new ArrayList<>());

    TokenPredicatesCondition cond = mock(TokenPredicatesCondition.class);
    when(cond.getPrefixes()).thenReturn(new ArrayList<>());
    when(cond.getSuffixes()).thenReturn(listList);

    // Act
    tokenPredicateSet.add(cond);

    // Assert that nothing has changed
    verify(cond, atLeast(1)).getPrefixes();
    verify(cond).getSuffixes();
    assertFalse(tokenPredicateSet.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#add(TokenPredicatesCondition)}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then not {@link TokenPredicateSet} (default constructor) hasCaptures.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateSet#add(TokenPredicatesCondition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TokenPredicateSet.add(TokenPredicatesCondition)"})
  public void testAdd_givenArrayList_thenNotTokenPredicateSetHasCaptures() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = new TokenPredicateSet();

    TokenPredicatesCondition cond = mock(TokenPredicatesCondition.class);
    when(cond.getPrefixes()).thenReturn(new ArrayList<>());
    when(cond.getSuffixes()).thenReturn(new ArrayList<>());

    // Act
    tokenPredicateSet.add(cond);

    // Assert that nothing has changed
    verify(cond, atLeast(1)).getPrefixes();
    verify(cond).getSuffixes();
    assertFalse(tokenPredicateSet.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#add(TokenPredicatesCondition)}.
   *
   * <ul>
   *   <li>Then {@link TokenPredicateSet} (default constructor) hasCaptures.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateSet#add(TokenPredicatesCondition)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TokenPredicateSet.add(TokenPredicatesCondition)"})
  public void testAdd_thenTokenPredicateSetHasCaptures() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = new TokenPredicateSet();

    ArrayList<TokenEntry> tokenEntryList = new ArrayList<>();
    tokenEntryList.add(new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key"));

    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(tokenEntryList);

    TokenPredicatesCondition cond = mock(TokenPredicatesCondition.class);
    when(cond.getPrefixes()).thenReturn(listList);
    when(cond.getSuffixes()).thenReturn(new ArrayList<>());

    // Act
    tokenPredicateSet.add(cond);

    // Assert
    verify(cond, atLeast(1)).getPrefixes();
    verify(cond).getSuffixes();
    assertTrue(tokenPredicateSet.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#of(TokenPredicatesCondition[])}.
   *
   * <p>Method under test: {@link TokenPredicateSet#of(TokenPredicatesCondition[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateSet TokenPredicateSet.of(TokenPredicatesCondition[])"})
  public void testOf() {
    // Arrange
    ArrayList<TokenEntry> tokenEntryList = new ArrayList<>();
    tokenEntryList.add(new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));

    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(tokenEntryList);

    TokenPredicatesCondition tokenPredicatesCondition = mock(TokenPredicatesCondition.class);
    when(tokenPredicatesCondition.getPrefixes()).thenReturn(listList);
    when(tokenPredicatesCondition.getSuffixes()).thenReturn(new ArrayList<>());

    // Act
    TokenPredicateSet actualOfResult = TokenPredicateSet.of(tokenPredicatesCondition);

    // Assert
    verify(tokenPredicatesCondition, atLeast(1)).getPrefixes();
    verify(tokenPredicatesCondition).getSuffixes();
    assertEquals(0, actualOfResult.getMaxPrefixLength());
    assertEquals(0, actualOfResult.getMaxSuffixLength());
    assertFalse(actualOfResult.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#of(TokenPredicatesCondition[])}.
   *
   * <p>Method under test: {@link TokenPredicateSet#of(TokenPredicatesCondition[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateSet TokenPredicateSet.of(TokenPredicatesCondition[])"})
  public void testOf2() {
    // Arrange
    ArrayList<TokenEntry> tokenEntryList = new ArrayList<>();
    tokenEntryList.add(new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));
    tokenEntryList.add(new SQLTokenEntry("String", SQLTokenType.T_KEYWORD, true));

    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(tokenEntryList);

    TokenPredicatesCondition tokenPredicatesCondition = mock(TokenPredicatesCondition.class);
    when(tokenPredicatesCondition.getPrefixes()).thenReturn(listList);
    when(tokenPredicatesCondition.getSuffixes()).thenReturn(new ArrayList<>());

    // Act
    TokenPredicateSet actualOfResult = TokenPredicateSet.of(tokenPredicatesCondition);

    // Assert
    verify(tokenPredicatesCondition, atLeast(1)).getPrefixes();
    verify(tokenPredicatesCondition).getSuffixes();
    assertEquals(0, actualOfResult.getMaxPrefixLength());
    assertEquals(0, actualOfResult.getMaxSuffixLength());
    assertFalse(actualOfResult.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#of(TokenPredicatesCondition[])}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ArrayList#ArrayList()}.
   *   <li>Then return not hasCaptures.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateSet#of(TokenPredicatesCondition[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateSet TokenPredicateSet.of(TokenPredicatesCondition[])"})
  public void testOf_givenArrayListAddArrayList_thenReturnNotHasCaptures() {
    // Arrange
    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(new ArrayList<>());

    TokenPredicatesCondition tokenPredicatesCondition = mock(TokenPredicatesCondition.class);
    when(tokenPredicatesCondition.getPrefixes()).thenReturn(listList);
    when(tokenPredicatesCondition.getSuffixes()).thenReturn(new ArrayList<>());

    // Act
    TokenPredicateSet actualOfResult = TokenPredicateSet.of(tokenPredicatesCondition);

    // Assert
    verify(tokenPredicatesCondition, atLeast(1)).getPrefixes();
    verify(tokenPredicatesCondition).getSuffixes();
    assertEquals(0, actualOfResult.getMaxPrefixLength());
    assertEquals(0, actualOfResult.getMaxSuffixLength());
    assertFalse(actualOfResult.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#of(TokenPredicatesCondition[])}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ArrayList#ArrayList()}.
   *   <li>Then return not hasCaptures.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateSet#of(TokenPredicatesCondition[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateSet TokenPredicateSet.of(TokenPredicatesCondition[])"})
  public void testOf_givenArrayListAddArrayList_thenReturnNotHasCaptures2() {
    // Arrange
    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(new ArrayList<>());
    listList.add(new ArrayList<>());

    TokenPredicatesCondition tokenPredicatesCondition = mock(TokenPredicatesCondition.class);
    when(tokenPredicatesCondition.getPrefixes()).thenReturn(listList);
    when(tokenPredicatesCondition.getSuffixes()).thenReturn(new ArrayList<>());

    // Act
    TokenPredicateSet actualOfResult = TokenPredicateSet.of(tokenPredicatesCondition);

    // Assert
    verify(tokenPredicatesCondition, atLeast(1)).getPrefixes();
    verify(tokenPredicatesCondition).getSuffixes();
    assertEquals(0, actualOfResult.getMaxPrefixLength());
    assertEquals(0, actualOfResult.getMaxSuffixLength());
    assertFalse(actualOfResult.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#of(TokenPredicatesCondition[])}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ArrayList#ArrayList()}.
   *   <li>Then return not hasCaptures.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateSet#of(TokenPredicatesCondition[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateSet TokenPredicateSet.of(TokenPredicatesCondition[])"})
  public void testOf_givenArrayListAddArrayList_thenReturnNotHasCaptures3() {
    // Arrange
    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(new ArrayList<>());

    TokenPredicatesCondition tokenPredicatesCondition = mock(TokenPredicatesCondition.class);
    when(tokenPredicatesCondition.getPrefixes()).thenReturn(new ArrayList<>());
    when(tokenPredicatesCondition.getSuffixes()).thenReturn(listList);

    // Act
    TokenPredicateSet actualOfResult = TokenPredicateSet.of(tokenPredicatesCondition);

    // Assert
    verify(tokenPredicatesCondition, atLeast(1)).getPrefixes();
    verify(tokenPredicatesCondition).getSuffixes();
    assertEquals(0, actualOfResult.getMaxPrefixLength());
    assertEquals(0, actualOfResult.getMaxSuffixLength());
    assertFalse(actualOfResult.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#of(TokenPredicatesCondition[])}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()} add {@link ArrayList#ArrayList()}.
   *   <li>Then return not hasCaptures.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateSet#of(TokenPredicatesCondition[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateSet TokenPredicateSet.of(TokenPredicatesCondition[])"})
  public void testOf_givenArrayListAddArrayList_thenReturnNotHasCaptures4() {
    // Arrange
    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(new ArrayList<>());
    listList.add(new ArrayList<>());

    TokenPredicatesCondition tokenPredicatesCondition = mock(TokenPredicatesCondition.class);
    when(tokenPredicatesCondition.getPrefixes()).thenReturn(new ArrayList<>());
    when(tokenPredicatesCondition.getSuffixes()).thenReturn(listList);

    // Act
    TokenPredicateSet actualOfResult = TokenPredicateSet.of(tokenPredicatesCondition);

    // Assert
    verify(tokenPredicatesCondition, atLeast(1)).getPrefixes();
    verify(tokenPredicatesCondition).getSuffixes();
    assertEquals(0, actualOfResult.getMaxPrefixLength());
    assertEquals(0, actualOfResult.getMaxSuffixLength());
    assertFalse(actualOfResult.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#of(TokenPredicatesCondition[])}.
   *
   * <ul>
   *   <li>Given {@link ArrayList#ArrayList()}.
   *   <li>Then return not hasCaptures.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateSet#of(TokenPredicatesCondition[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateSet TokenPredicateSet.of(TokenPredicatesCondition[])"})
  public void testOf_givenArrayList_thenReturnNotHasCaptures() {
    // Arrange
    TokenPredicatesCondition tokenPredicatesCondition = mock(TokenPredicatesCondition.class);
    when(tokenPredicatesCondition.getPrefixes()).thenReturn(new ArrayList<>());
    when(tokenPredicatesCondition.getSuffixes()).thenReturn(new ArrayList<>());

    // Act
    TokenPredicateSet actualOfResult = TokenPredicateSet.of(tokenPredicatesCondition);

    // Assert
    verify(tokenPredicatesCondition, atLeast(1)).getPrefixes();
    verify(tokenPredicatesCondition).getSuffixes();
    assertEquals(0, actualOfResult.getMaxPrefixLength());
    assertEquals(0, actualOfResult.getMaxSuffixLength());
    assertFalse(actualOfResult.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#of(TokenPredicatesCondition[])}.
   *
   * <ul>
   *   <li>Then return hasCaptures.
   * </ul>
   *
   * <p>Method under test: {@link TokenPredicateSet#of(TokenPredicatesCondition[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"TokenPredicateSet TokenPredicateSet.of(TokenPredicatesCondition[])"})
  public void testOf_thenReturnHasCaptures() {
    // Arrange
    ArrayList<TokenEntry> tokenEntryList = new ArrayList<>();
    tokenEntryList.add(new CaptureTokenPredicateNode("String", SQLTokenType.T_KEYWORD, "Key"));

    ArrayList<List<TokenEntry>> listList = new ArrayList<>();
    listList.add(tokenEntryList);

    TokenPredicatesCondition tokenPredicatesCondition = mock(TokenPredicatesCondition.class);
    when(tokenPredicatesCondition.getPrefixes()).thenReturn(listList);
    when(tokenPredicatesCondition.getSuffixes()).thenReturn(new ArrayList<>());

    // Act
    TokenPredicateSet actualOfResult = TokenPredicateSet.of(tokenPredicatesCondition);

    // Assert
    verify(tokenPredicatesCondition, atLeast(1)).getPrefixes();
    verify(tokenPredicatesCondition).getSuffixes();
    assertEquals(0, actualOfResult.getMaxPrefixLength());
    assertEquals(0, actualOfResult.getMaxSuffixLength());
    assertTrue(actualOfResult.hasCaptures());
  }

  /**
   * Test {@link TokenPredicateSet#matchSuffix(Deque)}.
   *
   * <p>Method under test: {@link TokenPredicateSet#matchSuffix(Deque)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set TokenPredicateSet.matchSuffix(Deque)"})
  public void testMatchSuffix() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = new TokenPredicateSet();

    // Act and Assert
    assertTrue(tokenPredicateSet.matchSuffix(new LinkedList<>()).isEmpty());
  }

  /**
   * Test {@link TokenPredicateSet#anyMatches(Deque, Deque)}.
   *
   * <p>Method under test: {@link TokenPredicateSet#anyMatches(Deque, Deque)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean TokenPredicateSet.anyMatches(Deque, Deque)"})
  public void testAnyMatches() {
    // Arrange
    TokenPredicateSet tokenPredicateSet = new TokenPredicateSet();
    LinkedList<TokenEntry> prefix = new LinkedList<>();

    // Act and Assert
    assertFalse(tokenPredicateSet.anyMatches(prefix, new LinkedList<>()));
  }

  /**
   * Test new {@link TokenPredicateSet} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link TokenPredicateSet}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TokenPredicateSet.<init>()"})
  public void testNewTokenPredicateSet() {
    // Arrange and Act
    TokenPredicateSet actualTokenPredicateSet = new TokenPredicateSet();

    // Assert
    assertEquals(0, actualTokenPredicateSet.getMaxPrefixLength());
    assertEquals(0, actualTokenPredicateSet.getMaxSuffixLength());
    assertFalse(actualTokenPredicateSet.hasCaptures());
  }
}
