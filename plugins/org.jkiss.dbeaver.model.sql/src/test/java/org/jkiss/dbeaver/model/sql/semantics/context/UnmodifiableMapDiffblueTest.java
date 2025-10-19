package org.jkiss.dbeaver.model.sql.semantics.context;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class UnmodifiableMapDiffblueTest {
  /**
   * Test {@link UnmodifiableMap#emptyMap()}.
   *
   * <p>Method under test: {@link UnmodifiableMap#emptyMap()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"UnmodifiableMap UnmodifiableMap.emptyMap()"})
  public void testEmptyMap() {
    // Arrange and Act
    UnmodifiableMap<Object, Object> actualEmptyMapResult = UnmodifiableMap.emptyMap();

    // Assert
    Collection<Entry<Object, Object>> entrySetResult = actualEmptyMapResult.entrySet();
    assertTrue(entrySetResult instanceof Set);
    assertTrue(entrySetResult.isEmpty());
    assertSame(entrySetResult, actualEmptyMapResult.values());
  }

  /**
   * Test {@link UnmodifiableMap#combine(UnmodifiableMap)}.
   *
   * <p>Method under test: {@link UnmodifiableMap#combine(UnmodifiableMap)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"UnmodifiableMap UnmodifiableMap.combine(UnmodifiableMap)"})
  public void testCombine() {
    // Arrange
    UnmodifiableMap<Object, Object> emptyMapResult = UnmodifiableMap.emptyMap();
    UnmodifiableMap<Object, Object> other = UnmodifiableMap.emptyMap();

    // Act
    UnmodifiableMap<Object, Object> actualCombineResult = emptyMapResult.combine(other);

    // Assert
    assertSame(other, actualCombineResult);
  }

  /**
   * Test {@link UnmodifiableMap#get(Object)}.
   *
   * <p>Method under test: {@link UnmodifiableMap#get(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object UnmodifiableMap.get(Object)"})
  public void testGet() {
    // Arrange
    UnmodifiableMap<Object, Object> emptyMapResult = UnmodifiableMap.emptyMap();

    // Act and Assert
    assertNull(emptyMapResult.get("Key"));
  }

  /**
   * Test {@link UnmodifiableMap#put(Collection)} with {@code entries}.
   *
   * <ul>
   *   <li>Then return entrySet is {@link LinkedHashSet#LinkedHashSet()}.
   * </ul>
   *
   * <p>Method under test: {@link UnmodifiableMap#put(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"UnmodifiableMap UnmodifiableMap.put(Collection)"})
  public void testPutWithEntries_thenReturnEntrySetIsLinkedHashSet() {
    // Arrange
    UnmodifiableMap<Object, Object> emptyMapResult = UnmodifiableMap.emptyMap();

    LinkedHashSet<Entry<Object, Object>> entries = new LinkedHashSet<>();
    entries.add(new SimpleEntry<>("42", "42"));

    // Act
    UnmodifiableMap<Object, Object> actualPutResult = emptyMapResult.put(entries);

    // Assert
    assertEquals(1, actualPutResult.values().size());
    assertEquals(entries, actualPutResult.entrySet());
  }

  /**
   * Test {@link UnmodifiableMap#put(Collection)} with {@code entries}.
   *
   * <ul>
   *   <li>Then return entrySet size is one.
   * </ul>
   *
   * <p>Method under test: {@link UnmodifiableMap#put(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"UnmodifiableMap UnmodifiableMap.put(Collection)"})
  public void testPutWithEntries_thenReturnEntrySetSizeIsOne() {
    // Arrange
    UnmodifiableMap<Object, Object> emptyMapResult = UnmodifiableMap.emptyMap();

    ArrayList<Entry<Object, Object>> entries = new ArrayList<>();
    entries.add(new SimpleEntry<>("42", "42"));
    entries.add(new SimpleEntry<>("42", "42"));

    // Act
    UnmodifiableMap<Object, Object> actualPutResult = emptyMapResult.put(entries);

    // Assert
    Collection<Entry<Object, Object>> entrySetResult = actualPutResult.entrySet();
    assertEquals(1, entrySetResult.size());
    assertTrue(entrySetResult instanceof Set);
    assertEquals(1, actualPutResult.values().size());
  }

  /**
   * Test {@link UnmodifiableMap#put(Collection)} with {@code entries}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return emptyMap.
   * </ul>
   *
   * <p>Method under test: {@link UnmodifiableMap#put(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"UnmodifiableMap UnmodifiableMap.put(Collection)"})
  public void testPutWithEntries_whenArrayList_thenReturnEmptyMap() {
    // Arrange
    UnmodifiableMap<Object, Object> emptyMapResult = UnmodifiableMap.emptyMap();

    // Act
    UnmodifiableMap<Object, Object> actualPutResult = emptyMapResult.put(new ArrayList<>());

    // Assert
    assertSame(emptyMapResult, actualPutResult);
  }

  /**
   * Test {@link UnmodifiableMap#put(Object, Object)} with {@code key}, {@code value}.
   *
   * <p>Method under test: {@link UnmodifiableMap#put(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"UnmodifiableMap UnmodifiableMap.put(Object, Object)"})
  public void testPutWithKeyValue() {
    // Arrange
    UnmodifiableMap<Object, Object> emptyMapResult = UnmodifiableMap.emptyMap();

    // Act
    UnmodifiableMap<Object, Object> actualPutResult = emptyMapResult.put("Key", "Value");

    // Assert
    Collection<Entry<Object, Object>> entrySetResult = actualPutResult.entrySet();
    assertEquals(1, entrySetResult.size());
    assertTrue(entrySetResult instanceof Set);
    assertEquals(1, actualPutResult.values().size());
  }

  /**
   * Test {@link UnmodifiableMap#remove(Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} add {@link SimpleEntry#SimpleEntry(Object, Object)}
   *       with {@code 42} and {@code 42}.
   *   <li>Then entrySet return {@link Set}.
   * </ul>
   *
   * <p>Method under test: {@link UnmodifiableMap#remove(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"UnmodifiableMap UnmodifiableMap.remove(Collection)"})
  public void testRemove_whenArrayListAddSimpleEntryWith42And42_thenEntrySetReturnSet() {
    // Arrange
    UnmodifiableMap<Object, Object> emptyMapResult = UnmodifiableMap.emptyMap();

    ArrayList<Entry<Object, Object>> entries = new ArrayList<>();
    entries.add(new SimpleEntry<>("42", "42"));
    entries.add(new SimpleEntry<>("42", "42"));

    // Act
    UnmodifiableMap<Object, Object> actualRemoveResult = emptyMapResult.remove(entries);

    // Assert
    Collection<Entry<Object, Object>> entrySetResult = actualRemoveResult.entrySet();
    assertTrue(entrySetResult instanceof Set);
    assertTrue(entrySetResult.isEmpty());
    assertTrue(actualRemoveResult.values().isEmpty());
  }

  /**
   * Test {@link UnmodifiableMap#remove(Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return emptyMap.
   * </ul>
   *
   * <p>Method under test: {@link UnmodifiableMap#remove(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"UnmodifiableMap UnmodifiableMap.remove(Collection)"})
  public void testRemove_whenArrayList_thenReturnEmptyMap() {
    // Arrange
    UnmodifiableMap<Object, Object> emptyMapResult = UnmodifiableMap.emptyMap();

    // Act
    UnmodifiableMap<Object, Object> actualRemoveResult = emptyMapResult.remove(new ArrayList<>());

    // Assert
    assertSame(emptyMapResult, actualRemoveResult);
  }

  /**
   * Test {@link UnmodifiableMap#remove(Collection)}.
   *
   * <ul>
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@link SimpleEntry#SimpleEntry(Object,
   *       Object)} with {@code 42} and {@code 42}.
   *   <li>Then entrySet return {@link Set}.
   * </ul>
   *
   * <p>Method under test: {@link UnmodifiableMap#remove(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"UnmodifiableMap UnmodifiableMap.remove(Collection)"})
  public void testRemove_whenLinkedHashSetAddSimpleEntryWith42And42_thenEntrySetReturnSet() {
    // Arrange
    UnmodifiableMap<Object, Object> emptyMapResult = UnmodifiableMap.emptyMap();

    LinkedHashSet<Entry<Object, Object>> entries = new LinkedHashSet<>();
    entries.add(new SimpleEntry<>("42", "42"));

    // Act
    UnmodifiableMap<Object, Object> actualRemoveResult = emptyMapResult.remove(entries);

    // Assert
    Collection<Entry<Object, Object>> entrySetResult = actualRemoveResult.entrySet();
    assertTrue(entrySetResult instanceof Set);
    assertTrue(entrySetResult.isEmpty());
    assertTrue(actualRemoveResult.values().isEmpty());
  }

  /**
   * Test {@link UnmodifiableMap#values()}.
   *
   * <p>Method under test: {@link UnmodifiableMap#values()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection UnmodifiableMap.values()"})
  public void testValues() {
    // Arrange
    UnmodifiableMap<Object, Object> emptyMapResult = UnmodifiableMap.emptyMap();

    // Act
    Collection<Object> actualValuesResult = emptyMapResult.values();

    // Assert
    assertTrue(actualValuesResult instanceof Set);
    assertTrue(actualValuesResult.isEmpty());
  }

  /**
   * Test {@link UnmodifiableMap#entrySet()}.
   *
   * <p>Method under test: {@link UnmodifiableMap#entrySet()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection UnmodifiableMap.entrySet()"})
  public void testEntrySet() {
    // Arrange
    UnmodifiableMap<Object, Object> emptyMapResult = UnmodifiableMap.emptyMap();

    // Act
    Collection<Entry<Object, Object>> actualEntrySetResult = emptyMapResult.entrySet();

    // Assert
    assertTrue(actualEntrySetResult instanceof Set);
    assertTrue(actualEntrySetResult.isEmpty());
  }
}
