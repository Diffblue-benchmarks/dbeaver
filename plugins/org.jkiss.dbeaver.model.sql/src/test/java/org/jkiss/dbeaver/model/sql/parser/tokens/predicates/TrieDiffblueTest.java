package org.jkiss.dbeaver.model.sql.parser.tokens.predicates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class TrieDiffblueTest {
  /**
   * Test {@link Trie#add(Iterable, Object)} with {@code Iterable}, {@code Object}.
   *
   * <ul>
   *   <li>Then calls {@link TrieLookupComparator#isPartiallyComparable(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link Trie#add(Iterable, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Trie.add(Iterable, Object)"})
  public void testAddWithIterableObject_thenCallsIsPartiallyComparable() {
    // Arrange
    TrieLookupComparator<Object> lookupComparer = mock(TrieLookupComparator.class);
    when(lookupComparer.isPartiallyComparable(Mockito.<Object>any())).thenReturn(true);
    when(lookupComparer.isStronglyComparable(Mockito.<Object>any())).thenReturn(true);
    Trie<Object, Object> trie = new Trie<>(mock(Comparator.class), lookupComparer);

    ArrayList<Object> key = new ArrayList<>();
    key.add("42");

    // Act
    trie.add(key, "Value");

    // Assert
    verify(lookupComparer).isPartiallyComparable(isA(Object.class));
    verify(lookupComparer).isStronglyComparable(isA(Object.class));
  }

  /**
   * Test {@link Trie#add(Iterator, Object)} with {@code Iterator}, {@code Object}.
   *
   * <ul>
   *   <li>Then calls {@link TrieLookupComparator#isPartiallyComparable(Object)}.
   * </ul>
   *
   * <p>Method under test: {@link Trie#add(Iterator, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Trie.add(Iterator, Object)"})
  public void testAddWithIteratorObject_thenCallsIsPartiallyComparable() {
    // Arrange
    TrieLookupComparator<Object> lookupComparer = mock(TrieLookupComparator.class);
    when(lookupComparer.isPartiallyComparable(Mockito.<Object>any())).thenReturn(true);
    when(lookupComparer.isStronglyComparable(Mockito.<Object>any())).thenReturn(true);
    Trie<Object, Object> trie = new Trie<>(mock(Comparator.class), lookupComparer);

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    Iterator<Object> key = objectList.iterator();

    // Act
    trie.add(key, "Value");

    // Assert
    verify(lookupComparer).isPartiallyComparable(isA(Object.class));
    verify(lookupComparer).isStronglyComparable(isA(Object.class));
    assertFalse(key.hasNext());
  }

  /**
   * Test {@link Trie#add(Iterator, Object)} with {@code Iterator}, {@code Object}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} iterator.
   * </ul>
   *
   * <p>Method under test: {@link Trie#add(Iterator, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void Trie.add(Iterator, Object)"})
  public void testAddWithIteratorObject_whenArrayListIterator() {
    // Arrange
    Trie<Object, Object> trie =
        new Trie<>(mock(Comparator.class), mock(TrieLookupComparator.class));

    ArrayList<Object> objectList = new ArrayList<>();
    Iterator<Object> key = objectList.iterator();

    // Act
    trie.add(key, "Value");

    // Assert that nothing has changed
    assertFalse(key.hasNext());
  }

  /**
   * Test {@link Trie#collectValuesOnPath(Iterator)}.
   *
   * <ul>
   *   <li>Given {@link Comparator} {@link Comparator#compare(Object, Object)} return zero.
   *   <li>Then calls {@link Comparator#compare(Object, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link Trie#collectValuesOnPath(Iterator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set Trie.collectValuesOnPath(Iterator)"})
  public void testCollectValuesOnPath_givenComparatorCompareReturnZero_thenCallsCompare() {
    // Arrange
    Comparator<Object> strongComparer = mock(Comparator.class);
    when(strongComparer.compare(Mockito.<Object>any(), Mockito.<Object>any())).thenReturn(0);

    TrieLookupComparator<Object> lookupComparer = mock(TrieLookupComparator.class);
    when(lookupComparer.isPartiallyComparable(Mockito.<Object>any())).thenReturn(true);
    when(lookupComparer.isStronglyComparable(Mockito.<Object>any())).thenReturn(true);

    ArrayList<Object> key = new ArrayList<>();
    key.add("42");

    Trie<Object, Object> trie = new Trie<>(strongComparer, lookupComparer);
    trie.add(key, "Value");

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    Iterator<Object> key2 = objectList.iterator();

    // Act
    Set<Object> actualCollectValuesOnPathResult = trie.collectValuesOnPath(key2);

    // Assert
    verify(strongComparer).compare(isA(Object.class), isA(Object.class));
    verify(lookupComparer).isPartiallyComparable(isA(Object.class));
    verify(lookupComparer, atLeast(1)).isStronglyComparable(isA(Object.class));
    assertEquals(1, actualCollectValuesOnPathResult.size());
    assertFalse(key2.hasNext());
  }

  /**
   * Test {@link Trie#collectValuesOnPath(Iterator)}.
   *
   * <ul>
   *   <li>Given {@link Comparator} {@link Comparator#compare(Object, Object)} return zero.
   *   <li>Then calls {@link Comparator#compare(Object, Object)}.
   * </ul>
   *
   * <p>Method under test: {@link Trie#collectValuesOnPath(Iterator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set Trie.collectValuesOnPath(Iterator)"})
  public void testCollectValuesOnPath_givenComparatorCompareReturnZero_thenCallsCompare2() {
    // Arrange
    Comparator<Object> strongComparer = mock(Comparator.class);
    when(strongComparer.compare(Mockito.<Object>any(), Mockito.<Object>any())).thenReturn(0);

    TrieLookupComparator<Object> lookupComparer = mock(TrieLookupComparator.class);
    when(lookupComparer.isPartiallyComparable(Mockito.<Object>any())).thenReturn(true);
    when(lookupComparer.isStronglyComparable(Mockito.<Object>any())).thenReturn(true);

    ArrayList<Object> key = new ArrayList<>();
    key.add("42");

    Trie<Object, Object> trie = new Trie<>(strongComparer, lookupComparer);
    trie.add(key, "Value");

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    objectList.add("42");
    Iterator<Object> key2 = objectList.iterator();

    // Act
    Set<Object> actualCollectValuesOnPathResult = trie.collectValuesOnPath(key2);

    // Assert
    verify(strongComparer).compare(isA(Object.class), isA(Object.class));
    verify(lookupComparer).isPartiallyComparable(isA(Object.class));
    verify(lookupComparer, atLeast(1)).isStronglyComparable(isA(Object.class));
    assertEquals(1, actualCollectValuesOnPathResult.size());
    assertFalse(key2.hasNext());
  }

  /**
   * Test {@link Trie#collectValuesOnPath(Iterator)}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link Trie#collectValuesOnPath(Iterator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set Trie.collectValuesOnPath(Iterator)"})
  public void testCollectValuesOnPath_thenReturnEmpty() {
    // Arrange
    Trie<Object, Object> trie =
        new Trie<>(mock(Comparator.class), mock(TrieLookupComparator.class));

    ArrayList<Object> objectList = new ArrayList<>();
    Iterator<Object> key = objectList.iterator();

    // Act
    Set<Object> actualCollectValuesOnPathResult = trie.collectValuesOnPath(key);

    // Assert
    assertFalse(key.hasNext());
    assertTrue(actualCollectValuesOnPathResult.isEmpty());
  }

  /**
   * Test {@link Trie#collectValuesOnPath(Iterator)}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link Trie#collectValuesOnPath(Iterator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set Trie.collectValuesOnPath(Iterator)"})
  public void testCollectValuesOnPath_thenReturnEmpty2() {
    // Arrange
    TrieLookupComparator<Object> lookupComparer = mock(TrieLookupComparator.class);
    when(lookupComparer.isStronglyComparable(Mockito.<Object>any())).thenReturn(true);
    Trie<Object, Object> trie = new Trie<>(mock(Comparator.class), lookupComparer);

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    Iterator<Object> key = objectList.iterator();

    // Act
    Set<Object> actualCollectValuesOnPathResult = trie.collectValuesOnPath(key);

    // Assert
    verify(lookupComparer).isStronglyComparable(isA(Object.class));
    assertFalse(key.hasNext());
    assertTrue(actualCollectValuesOnPathResult.isEmpty());
  }

  /**
   * Test {@link Trie#collectValuesOnPath(Iterator)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Trie#collectValuesOnPath(Iterator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set Trie.collectValuesOnPath(Iterator)"})
  public void testCollectValuesOnPath_thenReturnSizeIsOne() {
    // Arrange
    Trie<Object, Object> trie =
        new Trie<>(mock(Comparator.class), mock(TrieLookupComparator.class));
    trie.add(new ArrayList<>(), "Value");

    ArrayList<Object> objectList = new ArrayList<>();
    Iterator<Object> key = objectList.iterator();

    // Act and Assert
    assertEquals(1, trie.collectValuesOnPath(key).size());
    assertFalse(key.hasNext());
  }

  /**
   * Test {@link Trie#collectValuesOnPath(Iterator)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link Trie#collectValuesOnPath(Iterator)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Set Trie.collectValuesOnPath(Iterator)"})
  public void testCollectValuesOnPath_thenReturnSizeIsOne2() {
    // Arrange
    TrieLookupComparator<Object> lookupComparer = mock(TrieLookupComparator.class);
    when(lookupComparer.isStronglyComparable(Mockito.<Object>any())).thenReturn(true);

    Trie<Object, Object> trie = new Trie<>(mock(Comparator.class), lookupComparer);
    trie.add(new ArrayList<>(), "Value");

    ArrayList<Object> objectList = new ArrayList<>();
    objectList.add("42");
    Iterator<Object> key = objectList.iterator();

    // Act
    Set<Object> actualCollectValuesOnPathResult = trie.collectValuesOnPath(key);

    // Assert
    verify(lookupComparer).isStronglyComparable(isA(Object.class));
    assertEquals(1, actualCollectValuesOnPathResult.size());
    assertFalse(key.hasNext());
  }
}
