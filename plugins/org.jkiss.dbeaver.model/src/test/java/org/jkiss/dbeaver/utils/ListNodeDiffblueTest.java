package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Iterator;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ListNodeDiffblueTest {
  /**
   * Test {@link ListNode#hasAny(ListNode)}.
   *
   * <ul>
   *   <li>When {@link ListNode} with data is {@link DBPEvent#RENAME}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ListNode#hasAny(ListNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ListNode.hasAny(ListNode)"})
  public void testHasAny_whenListNodeWithDataIsRename_thenReturnTrue() {
    // Arrange
    ListNode<Object> list = ListNode.of(DBPEvent.RENAME);

    // Act and Assert
    assertTrue(ListNode.hasAny(list));
  }

  /**
   * Test {@link ListNode#hasAny(ListNode)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ListNode#hasAny(ListNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ListNode.hasAny(ListNode)"})
  public void testHasAny_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ListNode.hasAny(null));
  }

  /**
   * Test {@link ListNode#hasOne(ListNode)}.
   *
   * <ul>
   *   <li>When {@link ListNode} with data1 is {@link DBPEvent#RENAME} and data2 is {@link
   *       DBPEvent#RENAME}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ListNode#hasOne(ListNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ListNode.hasOne(ListNode)"})
  public void testHasOne_whenListNodeWithData1IsRenameAndData2IsRename_thenReturnFalse() {
    // Arrange
    ListNode<Object> list = ListNode.of(DBPEvent.RENAME, DBPEvent.RENAME);

    // Act and Assert
    assertFalse(ListNode.hasOne(list));
  }

  /**
   * Test {@link ListNode#hasOne(ListNode)}.
   *
   * <ul>
   *   <li>When {@link ListNode} with data is {@link DBPEvent#RENAME}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ListNode#hasOne(ListNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ListNode.hasOne(ListNode)"})
  public void testHasOne_whenListNodeWithDataIsRename_thenReturnTrue() {
    // Arrange
    ListNode<Object> list = ListNode.of(DBPEvent.RENAME);

    // Act and Assert
    assertTrue(ListNode.hasOne(list));
  }

  /**
   * Test {@link ListNode#hasOne(ListNode)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ListNode#hasOne(ListNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ListNode.hasOne(ListNode)"})
  public void testHasOne_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ListNode.hasOne(null));
  }

  /**
   * Test {@link ListNode#hasMany(ListNode)}.
   *
   * <ul>
   *   <li>When {@link ListNode} with data1 is {@link DBPEvent#RENAME} and data2 is {@link
   *       DBPEvent#RENAME}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ListNode#hasMany(ListNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ListNode.hasMany(ListNode)"})
  public void testHasMany_whenListNodeWithData1IsRenameAndData2IsRename_thenReturnTrue() {
    // Arrange
    ListNode<Object> list = ListNode.of(DBPEvent.RENAME, DBPEvent.RENAME);

    // Act and Assert
    assertTrue(ListNode.hasMany(list));
  }

  /**
   * Test {@link ListNode#hasMany(ListNode)}.
   *
   * <ul>
   *   <li>When {@link ListNode} with data is {@link DBPEvent#RENAME}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ListNode#hasMany(ListNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ListNode.hasMany(ListNode)"})
  public void testHasMany_whenListNodeWithDataIsRename_thenReturnFalse() {
    // Arrange
    ListNode<Object> list = ListNode.of(DBPEvent.RENAME);

    // Act and Assert
    assertFalse(ListNode.hasMany(list));
  }

  /**
   * Test {@link ListNode#hasMany(ListNode)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ListNode#hasMany(ListNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ListNode.hasMany(ListNode)"})
  public void testHasMany_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(ListNode.hasMany(null));
  }

  /**
   * Test {@link ListNode#of(Object)} with {@code data}.
   *
   * <p>Method under test: {@link ListNode#of(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ListNode ListNode.of(Object)"})
  public void testOfWithData() {
    // Arrange and Act
    ListNode<Object> actualOfResult = ListNode.of(DBPEvent.RENAME);

    // Assert
    Object expectedNextResult = actualOfResult.data;
    assertNull(actualOfResult.next);
    Iterator<Object> actualIteratorResult = actualOfResult.iterator();
    Object actualNextResult = actualIteratorResult.next();
    assertFalse(actualIteratorResult.hasNext());
    assertSame(expectedNextResult, actualNextResult);
  }

  /**
   * Test {@link ListNode#of(Object, Object)} with {@code data1}, {@code data2}.
   *
   * <p>Method under test: {@link ListNode#of(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ListNode ListNode.of(Object, Object)"})
  public void testOfWithData1Data2() {
    // Arrange and Act
    ListNode<Object> actualOfResult = ListNode.of(DBPEvent.RENAME, DBPEvent.RENAME);

    // Assert
    ListNode<Object> listNode = actualOfResult.next;
    Object object = actualOfResult.data;
    assertSame(object, listNode.data);
    assertNull(listNode.next);
    Iterator<Object> actualIteratorResult = actualOfResult.iterator();
    Object actualNextResult = actualIteratorResult.next();
    Object actualNextResult2 = actualIteratorResult.next();
    assertFalse(actualIteratorResult.hasNext());
    assertSame(object, actualNextResult);
    assertSame(object, actualNextResult2);
  }

  /**
   * Test {@link ListNode#push(ListNode, Object)}.
   *
   * <p>Method under test: {@link ListNode#push(ListNode, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ListNode ListNode.push(ListNode, Object)"})
  public void testPush() {
    // Arrange
    ListNode<Object> node = ListNode.of(DBPEvent.RENAME);

    // Act
    ListNode<Object> actualPushResult = ListNode.push(node, DBPEvent.RENAME);

    // Assert
    ListNode<Object> listNode = actualPushResult.next;
    Object object = actualPushResult.data;
    assertSame(object, listNode.data);
    assertNull(listNode.next);
    Iterator<Object> actualIteratorResult = actualPushResult.iterator();
    Object actualNextResult = actualIteratorResult.next();
    Object actualNextResult2 = actualIteratorResult.next();
    assertFalse(actualIteratorResult.hasNext());
    assertSame(object, actualNextResult);
    assertSame(object, actualNextResult2);
  }

  /**
   * Test {@link ListNode#join(ListNode, ListNode)}.
   *
   * <ul>
   *   <li>When {@link ListNode} with data is {@link DBPEvent#RENAME}.
   *   <li>Then return {@link ListNode#next} {@link ListNode#data} is {@link ListNode#data}.
   * </ul>
   *
   * <p>Method under test: {@link ListNode#join(ListNode, ListNode)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"ListNode ListNode.join(ListNode, ListNode)"})
  public void testJoin_whenListNodeWithDataIsRename_thenReturnNextDataIsData() {
    // Arrange
    ListNode<Object> nodes = ListNode.of(DBPEvent.RENAME);
    ListNode<Object> joinList = ListNode.of(DBPEvent.RENAME);

    // Act
    ListNode<Object> actualJoinResult = ListNode.join(nodes, joinList);

    // Assert
    ListNode<Object> listNode = actualJoinResult.next;
    Object object = actualJoinResult.data;
    assertSame(object, listNode.data);
    assertNull(listNode.next);
    Iterator<Object> actualIteratorResult = actualJoinResult.iterator();
    Object actualNextResult = actualIteratorResult.next();
    Object actualNextResult2 = actualIteratorResult.next();
    assertFalse(actualIteratorResult.hasNext());
    assertSame(object, actualNextResult);
    assertSame(object, actualNextResult2);
  }

  /**
   * Test {@link ListNode#iterator()}.
   *
   * <p>Method under test: {@link ListNode#iterator()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Iterator ListNode.iterator()"})
  public void testIterator() {
    // Arrange
    ListNode<Object> ofResult = ListNode.of(DBPEvent.RENAME);

    // Act
    Iterator<Object> actualIteratorResult = ofResult.iterator();

    // Assert
    Object expectedNextResult = ofResult.data;
    Object actualNextResult = actualIteratorResult.next();
    assertFalse(actualIteratorResult.hasNext());
    assertSame(expectedNextResult, actualNextResult);
  }
}
