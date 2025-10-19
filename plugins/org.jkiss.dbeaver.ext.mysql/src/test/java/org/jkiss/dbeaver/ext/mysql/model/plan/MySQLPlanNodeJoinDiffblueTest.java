package org.jkiss.dbeaver.ext.mysql.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.List;
import org.jkiss.dbeaver.model.exec.plan.DBCPlanNodeKind;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MySQLPlanNodeJoinDiffblueTest {
  /**
   * Test {@link MySQLPlanNodeJoin#MySQLPlanNodeJoin(MySQLPlanNodePlain, MySQLPlanNodeJoin)}.
   *
   * <ul>
   *   <li>Then Nested first return {@link MySQLPlanNodeJoin}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJoin#MySQLPlanNodeJoin(MySQLPlanNodePlain,
   * MySQLPlanNodeJoin)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJoin.<init>(MySQLPlanNodePlain, MySQLPlanNodeJoin)"})
  public void testNewMySQLPlanNodeJoin_thenNestedFirstReturnMySQLPlanNodeJoin() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent2 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent3 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent4 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain left = new MySQLPlanNodePlain(new ArrayList<>());

    MySQLPlanNodeJoin source =
        new MySQLPlanNodeJoin(parent4, left, new MySQLPlanNodePlain(new ArrayList<>()));

    MySQLPlanNodeJoin left2 = new MySQLPlanNodeJoin(parent3, source);

    MySQLPlanNodeJoin source2 =
        new MySQLPlanNodeJoin(parent2, left2, new MySQLPlanNodePlain(new ArrayList<>()));

    // Act
    MySQLPlanNodeJoin actualMySQLPlanNodeJoin = new MySQLPlanNodeJoin(parent, source2);

    // Assert
    List<MySQLPlanNodePlain> nested = actualMySQLPlanNodeJoin.getNested();
    assertEquals(2, nested.size());
    MySQLPlanNodePlain getResult = nested.get(0);
    assertTrue(getResult instanceof MySQLPlanNodeJoin);
    assertEquals("JOIN", getResult.getNodeType());
    assertEquals("JOIN", getResult.getSelectType());
    assertEquals(2, getResult.getNested().size());
    assertEquals(DBCPlanNodeKind.JOIN, getResult.getNodeKind());
    assertSame(actualMySQLPlanNodeJoin, getResult.getParent());
    assertSame(actualMySQLPlanNodeJoin, nested.get(1).getParent());
  }

  /**
   * Test {@link MySQLPlanNodeJoin#MySQLPlanNodeJoin(MySQLPlanNodePlain, MySQLPlanNodeJoin)}.
   *
   * <ul>
   *   <li>Then return Nested first NodeType is {@code Type}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJoin#MySQLPlanNodeJoin(MySQLPlanNodePlain,
   * MySQLPlanNodeJoin)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJoin.<init>(MySQLPlanNodePlain, MySQLPlanNodeJoin)"})
  public void testNewMySQLPlanNodeJoin_thenReturnNestedFirstNodeTypeIsType() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent2 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain left =
        new MySQLPlanNodePlain(new MySQLPlanNodePlain(new ArrayList<>()), "Type");

    MySQLPlanNodeJoin source =
        new MySQLPlanNodeJoin(parent2, left, new MySQLPlanNodePlain(new ArrayList<>()));

    // Act
    MySQLPlanNodeJoin actualMySQLPlanNodeJoin = new MySQLPlanNodeJoin(parent, source);

    // Assert
    List<MySQLPlanNodePlain> nested = actualMySQLPlanNodeJoin.getNested();
    assertEquals(2, nested.size());
    MySQLPlanNodePlain getResult = nested.get(0);
    assertEquals("Type", getResult.getNodeType());
    assertEquals("Type", getResult.getSelectType());
    assertNull(getResult.getNested());
    assertSame(actualMySQLPlanNodeJoin, getResult.getParent());
    assertSame(actualMySQLPlanNodeJoin, nested.get(1).getParent());
  }

  /**
   * Test {@link MySQLPlanNodeJoin#MySQLPlanNodeJoin(MySQLPlanNodePlain, MySQLPlanNodeJoin)}.
   *
   * <ul>
   *   <li>Then return Nested first {@link MySQLPlanNodePlain#type} is {@code <plan>}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJoin#MySQLPlanNodeJoin(MySQLPlanNodePlain,
   * MySQLPlanNodeJoin)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJoin.<init>(MySQLPlanNodePlain, MySQLPlanNodeJoin)"})
  public void testNewMySQLPlanNodeJoin_thenReturnNestedFirstTypeIsPlan() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent2 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain left = new MySQLPlanNodePlain(new ArrayList<>());

    MySQLPlanNodeJoin source =
        new MySQLPlanNodeJoin(parent2, left, new MySQLPlanNodePlain(new ArrayList<>()));

    // Act
    MySQLPlanNodeJoin actualMySQLPlanNodeJoin = new MySQLPlanNodeJoin(parent, source);

    // Assert
    List<MySQLPlanNodePlain> nested = actualMySQLPlanNodeJoin.getNested();
    assertEquals(2, nested.size());
    MySQLPlanNodePlain getResult = nested.get(0);
    assertEquals("<plan>", getResult.type);
    assertNull(getResult.getNodeType());
    assertNull(getResult.getSelectType());
    assertTrue(getResult.getNested().isEmpty());
    assertSame(actualMySQLPlanNodeJoin, getResult.getParent());
    assertSame(actualMySQLPlanNodeJoin, nested.get(1).getParent());
  }

  /**
   * Test {@link MySQLPlanNodeJoin#MySQLPlanNodeJoin(MySQLPlanNodePlain, MySQLPlanNodePlain,
   * MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then return NodeType is {@code JOIN}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJoin#MySQLPlanNodeJoin(MySQLPlanNodePlain,
   * MySQLPlanNodePlain, MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MySQLPlanNodeJoin.<init>(MySQLPlanNodePlain, MySQLPlanNodePlain, MySQLPlanNodePlain)"
  })
  public void testNewMySQLPlanNodeJoin_thenReturnNodeTypeIsJoin() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain left = new MySQLPlanNodePlain(new ArrayList<>());

    // Act
    MySQLPlanNodeJoin actualMySQLPlanNodeJoin =
        new MySQLPlanNodeJoin(parent, left, new MySQLPlanNodePlain(new ArrayList<>()));

    // Assert
    assertEquals("JOIN", actualMySQLPlanNodeJoin.getNodeType());
    assertEquals("JOIN", actualMySQLPlanNodeJoin.getSelectType());
    assertNull(actualMySQLPlanNodeJoin.getId());
    assertNull(actualMySQLPlanNodeJoin.getFiltered());
    assertNull(actualMySQLPlanNodeJoin.getRowCount());
    assertNull(actualMySQLPlanNodeJoin.getNodeCost());
    assertNull(actualMySQLPlanNodeJoin.getNodeDuration());
    assertNull(actualMySQLPlanNodeJoin.getNodePercent());
    assertNull(actualMySQLPlanNodeJoin.getNodeRowCount());
    assertNull(actualMySQLPlanNodeJoin.getExtra());
    assertNull(actualMySQLPlanNodeJoin.getKey());
    assertNull(actualMySQLPlanNodeJoin.getKeyLength());
    assertNull(actualMySQLPlanNodeJoin.getNodeDescription());
    assertNull(actualMySQLPlanNodeJoin.getNodeName());
    assertNull(actualMySQLPlanNodeJoin.getPossibleKeys());
    assertNull(actualMySQLPlanNodeJoin.getRef());
    assertNull(actualMySQLPlanNodeJoin.getTable());
    assertNull(actualMySQLPlanNodeJoin.getNodeCondition());
    assertNull(actualMySQLPlanNodeJoin.type);
    assertEquals(2, actualMySQLPlanNodeJoin.getNested().size());
    assertEquals(DBCPlanNodeKind.JOIN, actualMySQLPlanNodeJoin.getNodeKind());
    assertFalse(actualMySQLPlanNodeJoin.isCompositeNode());
    assertSame(parent, actualMySQLPlanNodeJoin.getParent());
  }

  /**
   * Test {@link MySQLPlanNodeJoin#copyNode(MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then Nested first return {@link MySQLPlanNodeJoin}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJoin#copyNode(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MySQLPlanNodePlain MySQLPlanNodeJoin.copyNode(MySQLPlanNodePlain)"})
  public void testCopyNode_thenNestedFirstReturnMySQLPlanNodeJoin() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent2 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent3 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent4 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain left = new MySQLPlanNodePlain(new ArrayList<>());

    MySQLPlanNodeJoin source =
        new MySQLPlanNodeJoin(parent4, left, new MySQLPlanNodePlain(new ArrayList<>()));

    MySQLPlanNodeJoin left2 = new MySQLPlanNodeJoin(parent3, source);

    MySQLPlanNodeJoin source2 =
        new MySQLPlanNodeJoin(parent2, left2, new MySQLPlanNodePlain(new ArrayList<>()));

    MySQLPlanNodeJoin mySQLPlanNodeJoin = new MySQLPlanNodeJoin(parent, source2);

    // Act
    MySQLPlanNodePlain actualCopyNodeResult =
        mySQLPlanNodeJoin.copyNode(new MySQLPlanNodePlain(new ArrayList<>()));

    // Assert
    List<MySQLPlanNodePlain> nested = actualCopyNodeResult.getNested();
    assertEquals(2, nested.size());
    MySQLPlanNodePlain getResult = nested.get(0);
    assertTrue(getResult instanceof MySQLPlanNodeJoin);
    assertTrue(actualCopyNodeResult instanceof MySQLPlanNodeJoin);
    assertEquals("JOIN", getResult.getNodeType());
    assertEquals("JOIN", getResult.getSelectType());
    assertEquals(2, getResult.getNested().size());
    assertEquals(DBCPlanNodeKind.JOIN, getResult.getNodeKind());
    assertSame(actualCopyNodeResult, getResult.getParent());
    assertSame(actualCopyNodeResult, nested.get(1).getParent());
  }

  /**
   * Test {@link MySQLPlanNodeJoin#copyNode(MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then return Nested first NodeType is {@code Type}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJoin#copyNode(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MySQLPlanNodePlain MySQLPlanNodeJoin.copyNode(MySQLPlanNodePlain)"})
  public void testCopyNode_thenReturnNestedFirstNodeTypeIsType() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent2 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain left =
        new MySQLPlanNodePlain(new MySQLPlanNodePlain(new ArrayList<>()), "Type");

    MySQLPlanNodeJoin source =
        new MySQLPlanNodeJoin(parent2, left, new MySQLPlanNodePlain(new ArrayList<>()));

    MySQLPlanNodeJoin mySQLPlanNodeJoin = new MySQLPlanNodeJoin(parent, source);

    // Act
    MySQLPlanNodePlain actualCopyNodeResult =
        mySQLPlanNodeJoin.copyNode(new MySQLPlanNodePlain(new ArrayList<>()));

    // Assert
    assertTrue(actualCopyNodeResult instanceof MySQLPlanNodeJoin);
    List<MySQLPlanNodePlain> nested = actualCopyNodeResult.getNested();
    assertEquals(2, nested.size());
    MySQLPlanNodePlain getResult = nested.get(0);
    assertEquals("Type", getResult.getNodeType());
    assertEquals("Type", getResult.getSelectType());
    assertNull(getResult.getNested());
    assertSame(actualCopyNodeResult, getResult.getParent());
    assertSame(actualCopyNodeResult, nested.get(1).getParent());
  }

  /**
   * Test {@link MySQLPlanNodeJoin#copyNode(MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then return Nested first {@link MySQLPlanNodePlain#type} is {@code <plan>}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJoin#copyNode(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MySQLPlanNodePlain MySQLPlanNodeJoin.copyNode(MySQLPlanNodePlain)"})
  public void testCopyNode_thenReturnNestedFirstTypeIsPlan() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent2 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain left = new MySQLPlanNodePlain(new ArrayList<>());

    MySQLPlanNodeJoin source =
        new MySQLPlanNodeJoin(parent2, left, new MySQLPlanNodePlain(new ArrayList<>()));

    MySQLPlanNodeJoin mySQLPlanNodeJoin = new MySQLPlanNodeJoin(parent, source);

    // Act
    MySQLPlanNodePlain actualCopyNodeResult =
        mySQLPlanNodeJoin.copyNode(new MySQLPlanNodePlain(new ArrayList<>()));

    // Assert
    assertTrue(actualCopyNodeResult instanceof MySQLPlanNodeJoin);
    List<MySQLPlanNodePlain> nested = actualCopyNodeResult.getNested();
    assertEquals(2, nested.size());
    MySQLPlanNodePlain getResult = nested.get(0);
    assertEquals("<plan>", getResult.type);
    assertNull(getResult.getNodeType());
    assertNull(getResult.getSelectType());
    assertTrue(getResult.getNested().isEmpty());
    assertSame(actualCopyNodeResult, getResult.getParent());
    assertSame(actualCopyNodeResult, nested.get(1).getParent());
  }
}
