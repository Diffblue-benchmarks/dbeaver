package org.jkiss.dbeaver.model.sql.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Deque;
import java.util.LinkedList;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EmptyTokenPredicateSetDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link EmptyTokenPredicateSet#getMaxPrefixLength()}
   *   <li>{@link EmptyTokenPredicateSet#getMaxSuffixLength()}
   *   <li>{@link EmptyTokenPredicateSet#getPrefixTreeRoot()}
   *   <li>{@link EmptyTokenPredicateSet#hasCaptures()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "int EmptyTokenPredicateSet.getMaxPrefixLength()",
    "int EmptyTokenPredicateSet.getMaxSuffixLength()",
    "org.jkiss.dbeaver.model.sql.parser.TrieNode EmptyTokenPredicateSet.getPrefixTreeRoot()",
    "boolean EmptyTokenPredicateSet.hasCaptures()"
  })
  public void testGettersAndSetters() {
    // Arrange
    EmptyTokenPredicateSet emptyTokenPredicateSet = EmptyTokenPredicateSet.INSTANCE;

    // Act
    int actualMaxPrefixLength = emptyTokenPredicateSet.getMaxPrefixLength();
    int actualMaxSuffixLength = emptyTokenPredicateSet.getMaxSuffixLength();
    emptyTokenPredicateSet.getPrefixTreeRoot();

    // Assert
    assertEquals(0, actualMaxPrefixLength);
    assertEquals(0, actualMaxSuffixLength);
    assertFalse(emptyTokenPredicateSet.hasCaptures());
  }

  /**
   * Test {@link EmptyTokenPredicateSet#anyMatches(Deque, Deque)}.
   *
   * <ul>
   *   <li>Given {@link TokenEntry}.
   *   <li>When {@link LinkedList#LinkedList()} add {@link TokenEntry}.
   * </ul>
   *
   * <p>Method under test: {@link EmptyTokenPredicateSet#anyMatches(Deque, Deque)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EmptyTokenPredicateSet.anyMatches(Deque, Deque)"})
  public void testAnyMatches_givenTokenEntry_whenLinkedListAddTokenEntry() {
    // Arrange
    LinkedList<TokenEntry> prefix = new LinkedList<>();
    prefix.add(mock(TokenEntry.class));

    // Act and Assert
    assertFalse(EmptyTokenPredicateSet.INSTANCE.anyMatches(prefix, new LinkedList<>()));
  }

  /**
   * Test {@link EmptyTokenPredicateSet#anyMatches(Deque, Deque)}.
   *
   * <ul>
   *   <li>Given {@link TokenEntry}.
   *   <li>When {@link LinkedList#LinkedList()} add {@link TokenEntry}.
   * </ul>
   *
   * <p>Method under test: {@link EmptyTokenPredicateSet#anyMatches(Deque, Deque)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EmptyTokenPredicateSet.anyMatches(Deque, Deque)"})
  public void testAnyMatches_givenTokenEntry_whenLinkedListAddTokenEntry2() {
    // Arrange
    LinkedList<TokenEntry> prefix = new LinkedList<>();
    prefix.add(mock(TokenEntry.class));
    prefix.add(mock(TokenEntry.class));

    // Act and Assert
    assertFalse(EmptyTokenPredicateSet.INSTANCE.anyMatches(prefix, new LinkedList<>()));
  }

  /**
   * Test {@link EmptyTokenPredicateSet#anyMatches(Deque, Deque)}.
   *
   * <ul>
   *   <li>Given {@link TokenEntry}.
   *   <li>When {@link LinkedList#LinkedList()} add {@link TokenEntry}.
   * </ul>
   *
   * <p>Method under test: {@link EmptyTokenPredicateSet#anyMatches(Deque, Deque)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EmptyTokenPredicateSet.anyMatches(Deque, Deque)"})
  public void testAnyMatches_givenTokenEntry_whenLinkedListAddTokenEntry3() {
    // Arrange
    LinkedList<TokenEntry> prefix = new LinkedList<>();

    LinkedList<TokenEntry> suffix = new LinkedList<>();
    suffix.add(mock(TokenEntry.class));

    // Act and Assert
    assertFalse(EmptyTokenPredicateSet.INSTANCE.anyMatches(prefix, suffix));
  }

  /**
   * Test {@link EmptyTokenPredicateSet#anyMatches(Deque, Deque)}.
   *
   * <ul>
   *   <li>Given {@link TokenEntry}.
   *   <li>When {@link LinkedList#LinkedList()} add {@link TokenEntry}.
   * </ul>
   *
   * <p>Method under test: {@link EmptyTokenPredicateSet#anyMatches(Deque, Deque)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EmptyTokenPredicateSet.anyMatches(Deque, Deque)"})
  public void testAnyMatches_givenTokenEntry_whenLinkedListAddTokenEntry4() {
    // Arrange
    LinkedList<TokenEntry> prefix = new LinkedList<>();

    LinkedList<TokenEntry> suffix = new LinkedList<>();
    suffix.add(mock(TokenEntry.class));
    suffix.add(mock(TokenEntry.class));

    // Act and Assert
    assertFalse(EmptyTokenPredicateSet.INSTANCE.anyMatches(prefix, suffix));
  }

  /**
   * Test {@link EmptyTokenPredicateSet#anyMatches(Deque, Deque)}.
   *
   * <ul>
   *   <li>When {@link LinkedList#LinkedList()}.
   * </ul>
   *
   * <p>Method under test: {@link EmptyTokenPredicateSet#anyMatches(Deque, Deque)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EmptyTokenPredicateSet.anyMatches(Deque, Deque)"})
  public void testAnyMatches_whenLinkedList() {
    // Arrange
    LinkedList<TokenEntry> prefix = new LinkedList<>();

    // Act and Assert
    assertFalse(EmptyTokenPredicateSet.INSTANCE.anyMatches(prefix, new LinkedList<>()));
  }

  /**
   * Test {@link EmptyTokenPredicateSet#matchSuffix(Deque)}.
   *
   * <ul>
   *   <li>Given {@link TokenEntry}.
   *   <li>When {@link LinkedList#LinkedList()} add {@link TokenEntry}.
   * </ul>
   *
   * <p>Method under test: {@link EmptyTokenPredicateSet#matchSuffix(Deque)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set EmptyTokenPredicateSet.matchSuffix(Deque)"})
  public void testMatchSuffix_givenTokenEntry_whenLinkedListAddTokenEntry() {
    // Arrange
    LinkedList<TokenEntry> suffix = new LinkedList<>();
    suffix.add(mock(TokenEntry.class));

    // Act and Assert
    assertTrue(EmptyTokenPredicateSet.INSTANCE.matchSuffix(suffix).isEmpty());
  }

  /**
   * Test {@link EmptyTokenPredicateSet#matchSuffix(Deque)}.
   *
   * <ul>
   *   <li>Given {@link TokenEntry}.
   *   <li>When {@link LinkedList#LinkedList()} add {@link TokenEntry}.
   * </ul>
   *
   * <p>Method under test: {@link EmptyTokenPredicateSet#matchSuffix(Deque)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set EmptyTokenPredicateSet.matchSuffix(Deque)"})
  public void testMatchSuffix_givenTokenEntry_whenLinkedListAddTokenEntry2() {
    // Arrange
    LinkedList<TokenEntry> suffix = new LinkedList<>();
    suffix.add(mock(TokenEntry.class));
    suffix.add(mock(TokenEntry.class));

    // Act and Assert
    assertTrue(EmptyTokenPredicateSet.INSTANCE.matchSuffix(suffix).isEmpty());
  }

  /**
   * Test {@link EmptyTokenPredicateSet#matchSuffix(Deque)}.
   *
   * <ul>
   *   <li>When {@link LinkedList#LinkedList()}.
   * </ul>
   *
   * <p>Method under test: {@link EmptyTokenPredicateSet#matchSuffix(Deque)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.util.Set EmptyTokenPredicateSet.matchSuffix(Deque)"})
  public void testMatchSuffix_whenLinkedList() {
    // Arrange, Act and Assert
    assertTrue(EmptyTokenPredicateSet.INSTANCE.matchSuffix(new LinkedList<>()).isEmpty());
  }
}
