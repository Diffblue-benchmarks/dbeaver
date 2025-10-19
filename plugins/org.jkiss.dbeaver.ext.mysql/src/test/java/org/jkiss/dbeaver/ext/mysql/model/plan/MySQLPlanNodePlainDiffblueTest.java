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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.model.exec.plan.DBCPlanNodeKind;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MySQLPlanNodePlainDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, String)}
   *   <li>{@link MySQLPlanNodePlain#toString()}
   *   <li>{@link MySQLPlanNodePlain#getExtra()}
   *   <li>{@link MySQLPlanNodePlain#getFiltered()}
   *   <li>{@link MySQLPlanNodePlain#getId()}
   *   <li>{@link MySQLPlanNodePlain#getKey()}
   *   <li>{@link MySQLPlanNodePlain#getKeyLength()}
   *   <li>{@link MySQLPlanNodePlain#getNodeCost()}
   *   <li>{@link MySQLPlanNodePlain#getNodeDescription()}
   *   <li>{@link MySQLPlanNodePlain#getNodeDuration()}
   *   <li>{@link MySQLPlanNodePlain#getNodeName()}
   *   <li>{@link MySQLPlanNodePlain#getNodePercent()}
   *   <li>{@link MySQLPlanNodePlain#getNodeRowCount()}
   *   <li>{@link MySQLPlanNodePlain#getNodeType()}
   *   <li>{@link MySQLPlanNodePlain#getPossibleKeys()}
   *   <li>{@link MySQLPlanNodePlain#getRef()}
   *   <li>{@link MySQLPlanNodePlain#getRowCount()}
   *   <li>{@link MySQLPlanNodePlain#getSelectType()}
   *   <li>{@link MySQLPlanNodePlain#getTable()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, String)",
    "String MySQLPlanNodePlain.getExtra()",
    "Long MySQLPlanNodePlain.getFiltered()",
    "Integer MySQLPlanNodePlain.getId()",
    "String MySQLPlanNodePlain.getKey()",
    "String MySQLPlanNodePlain.getKeyLength()",
    "Number MySQLPlanNodePlain.getNodeCost()",
    "String MySQLPlanNodePlain.getNodeDescription()",
    "Number MySQLPlanNodePlain.getNodeDuration()",
    "String MySQLPlanNodePlain.getNodeName()",
    "Number MySQLPlanNodePlain.getNodePercent()",
    "Number MySQLPlanNodePlain.getNodeRowCount()",
    "String MySQLPlanNodePlain.getNodeType()",
    "String MySQLPlanNodePlain.getPossibleKeys()",
    "String MySQLPlanNodePlain.getRef()",
    "Long MySQLPlanNodePlain.getRowCount()",
    "String MySQLPlanNodePlain.getSelectType()",
    "String MySQLPlanNodePlain.getTable()",
    "String MySQLPlanNodePlain.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(parent, "Type");
    String actualToStringResult = actualMySQLPlanNodePlain.toString();
    String actualExtra = actualMySQLPlanNodePlain.getExtra();
    Long actualFiltered = actualMySQLPlanNodePlain.getFiltered();
    Integer actualId = actualMySQLPlanNodePlain.getId();
    String actualKey = actualMySQLPlanNodePlain.getKey();
    String actualKeyLength = actualMySQLPlanNodePlain.getKeyLength();
    Number actualNodeCost = actualMySQLPlanNodePlain.getNodeCost();
    String actualNodeDescription = actualMySQLPlanNodePlain.getNodeDescription();
    Number actualNodeDuration = actualMySQLPlanNodePlain.getNodeDuration();
    String actualNodeName = actualMySQLPlanNodePlain.getNodeName();
    Number actualNodePercent = actualMySQLPlanNodePlain.getNodePercent();
    Number actualNodeRowCount = actualMySQLPlanNodePlain.getNodeRowCount();
    String actualNodeType = actualMySQLPlanNodePlain.getNodeType();
    String actualPossibleKeys = actualMySQLPlanNodePlain.getPossibleKeys();
    String actualRef = actualMySQLPlanNodePlain.getRef();
    Long actualRowCount = actualMySQLPlanNodePlain.getRowCount();
    String actualSelectType = actualMySQLPlanNodePlain.getSelectType();

    // Assert
    assertEquals("Type", actualNodeType);
    assertEquals("Type", actualSelectType);
    assertEquals("null Type null", actualToStringResult);
    assertNull(actualId);
    assertNull(actualFiltered);
    assertNull(actualRowCount);
    assertNull(actualNodeCost);
    assertNull(actualNodeDuration);
    assertNull(actualNodePercent);
    assertNull(actualNodeRowCount);
    assertNull(actualExtra);
    assertNull(actualKey);
    assertNull(actualKeyLength);
    assertNull(actualNodeDescription);
    assertNull(actualNodeName);
    assertNull(actualPossibleKeys);
    assertNull(actualRef);
    assertNull(actualMySQLPlanNodePlain.getTable());
    assertNull(actualMySQLPlanNodePlain.getNested());
    assertSame(parent, actualMySQLPlanNodePlain.getParent());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}.
   *
   * <ul>
   *   <li>Given {@code 1}.
   *   <li>When {@link HashMap#HashMap()} {@code id} is {@code 1}.
   *   <li>Then return Id intValue is one.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, Map)"})
  public void testNewMySQLPlanNodePlain_given1_whenHashMapIdIs1_thenReturnIdIntValueIsOne() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    HashMap<String, Object> props = new HashMap<>();
    props.put("select_type", null);
    props.put("table", null);
    props.put("type", null);
    props.put("possible_keys", null);
    props.put("key", null);
    props.put("key_len", null);
    props.put("ref", null);
    props.put("extra", null);
    props.put("rows", null);
    props.put("filtered", null);
    props.put("id", "1");

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(parent, props);

    // Assert
    assertEquals(0L, actualMySQLPlanNodePlain.getFiltered().longValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getRowCount().longValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getNodeRowCount().longValue());
    assertEquals(1, actualMySQLPlanNodePlain.getId().intValue());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link HashMap#HashMap()} {@code rows} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, Map)"})
  public void testNewMySQLPlanNodePlain_given42_whenHashMapRowsIs42() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    HashMap<String, Object> props = new HashMap<>();
    props.put("rows", "42");

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(parent, props);

    // Assert
    assertNull(actualMySQLPlanNodePlain.getId());
    assertNull(actualMySQLPlanNodePlain.getFiltered());
    assertEquals(42L, actualMySQLPlanNodePlain.getRowCount().longValue());
    assertEquals(42L, actualMySQLPlanNodePlain.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}.
   *
   * <ul>
   *   <li>Given {@code Props}.
   *   <li>When {@link HashMap#HashMap()} {@code filtered} is {@code Props}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, Map)"})
  public void testNewMySQLPlanNodePlain_givenProps_whenHashMapFilteredIsProps() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    HashMap<String, Object> props = new HashMap<>();
    props.put("select_type", null);
    props.put("table", null);
    props.put("type", null);
    props.put("possible_keys", null);
    props.put("key", null);
    props.put("key_len", null);
    props.put("ref", null);
    props.put("extra", null);
    props.put("rows", null);
    props.put("filtered", "Props");
    props.put("id", null);

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(parent, props);

    // Assert
    assertEquals(0, actualMySQLPlanNodePlain.getId().intValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getFiltered().longValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getRowCount().longValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}.
   *
   * <ul>
   *   <li>Given {@code Props}.
   *   <li>When {@link HashMap#HashMap()} {@code id} is {@code Props}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, Map)"})
  public void testNewMySQLPlanNodePlain_givenProps_whenHashMapIdIsProps() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    HashMap<String, Object> props = new HashMap<>();
    props.put("select_type", null);
    props.put("table", null);
    props.put("type", null);
    props.put("possible_keys", null);
    props.put("key", null);
    props.put("key_len", null);
    props.put("ref", null);
    props.put("extra", null);
    props.put("rows", null);
    props.put("filtered", null);
    props.put("id", "Props");

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(parent, props);

    // Assert
    assertEquals(0, actualMySQLPlanNodePlain.getId().intValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getFiltered().longValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getRowCount().longValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}.
   *
   * <ul>
   *   <li>Given space.
   *   <li>When {@link HashMap#HashMap()} {@code id} is space.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, Map)"})
  public void testNewMySQLPlanNodePlain_givenSpace_whenHashMapIdIsSpace() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    HashMap<String, Object> props = new HashMap<>();
    props.put("select_type", null);
    props.put("table", null);
    props.put("type", null);
    props.put("possible_keys", null);
    props.put("key", null);
    props.put("key_len", null);
    props.put("ref", null);
    props.put("extra", null);
    props.put("rows", null);
    props.put("filtered", null);
    props.put("id", " ");

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(parent, props);

    // Assert
    assertEquals(0, actualMySQLPlanNodePlain.getId().intValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getFiltered().longValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getRowCount().longValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then Nested first return {@link MySQLPlanNodeJoin}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain,
   * MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, MySQLPlanNodePlain)"})
  public void testNewMySQLPlanNodePlain_thenNestedFirstReturnMySQLPlanNodeJoin() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    ArrayList<MySQLPlanNodePlain> nodes = new ArrayList<>();
    MySQLPlanNodePlain parent2 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent3 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain left = new MySQLPlanNodePlain(new ArrayList<>());

    MySQLPlanNodeJoin source =
        new MySQLPlanNodeJoin(parent3, left, new MySQLPlanNodePlain(new ArrayList<>()));

    MySQLPlanNodeJoin mySQLPlanNodeJoin = new MySQLPlanNodeJoin(parent2, source);
    nodes.add(mySQLPlanNodeJoin);

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain =
        new MySQLPlanNodePlain(parent, new MySQLPlanNodePlain(nodes));

    // Assert
    List<MySQLPlanNodePlain> nested = actualMySQLPlanNodePlain.getNested();
    assertEquals(1, nested.size());
    MySQLPlanNodePlain getResult = nested.get(0);
    assertTrue(getResult instanceof MySQLPlanNodeJoin);
    assertEquals("JOIN", getResult.getNodeType());
    assertEquals("JOIN", getResult.getSelectType());
    assertNull(((MySQLPlanNodeJoin) getResult).type);
    assertEquals(2, getResult.getNested().size());
    assertEquals(DBCPlanNodeKind.JOIN, getResult.getNodeKind());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then return Nested first {@link MySQLPlanNodePlain#type} is {@code <plan>}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain,
   * MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, MySQLPlanNodePlain)"})
  public void testNewMySQLPlanNodePlain_thenReturnNestedFirstTypeIsPlan() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    ArrayList<MySQLPlanNodePlain> nodes = new ArrayList<>();
    nodes.add(new MySQLPlanNodePlain(new ArrayList<>()));

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain =
        new MySQLPlanNodePlain(parent, new MySQLPlanNodePlain(nodes));

    // Assert
    List<MySQLPlanNodePlain> nested = actualMySQLPlanNodePlain.getNested();
    assertEquals(1, nested.size());
    MySQLPlanNodePlain getResult = nested.get(0);
    assertEquals("<plan>", getResult.type);
    assertNull(getResult.getNodeType());
    assertNull(getResult.getSelectType());
    assertEquals(DBCPlanNodeKind.DEFAULT, getResult.getNodeKind());
    assertTrue(getResult.getNested().isEmpty());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(List)}.
   *
   * <ul>
   *   <li>Then return Nested size is one.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(List)"})
  public void testNewMySQLPlanNodePlain_thenReturnNestedSizeIsOne() {
    // Arrange
    ArrayList<MySQLPlanNodePlain> nodes = new ArrayList<>();
    MySQLPlanNodePlain mySQLPlanNodePlain = new MySQLPlanNodePlain(new ArrayList<>());
    nodes.add(mySQLPlanNodePlain);

    // Act and Assert
    List<MySQLPlanNodePlain> nested = new MySQLPlanNodePlain(nodes).getNested();
    assertEquals(1, nested.size());
    assertSame(nodes, nested);
    assertSame(mySQLPlanNodePlain, nested.get(0));
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(List)}.
   *
   * <ul>
   *   <li>Then return Nested size is two.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(List)"})
  public void testNewMySQLPlanNodePlain_thenReturnNestedSizeIsTwo() {
    // Arrange
    ArrayList<MySQLPlanNodePlain> nodes = new ArrayList<>();
    nodes.add(new MySQLPlanNodePlain(new ArrayList<>()));
    MySQLPlanNodePlain mySQLPlanNodePlain = new MySQLPlanNodePlain(new ArrayList<>());
    nodes.add(mySQLPlanNodePlain);

    // Act and Assert
    List<MySQLPlanNodePlain> nested = new MySQLPlanNodePlain(nodes).getNested();
    assertEquals(2, nested.size());
    assertSame(mySQLPlanNodePlain, nested.get(1));
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then return NodeType is {@code Type}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain,
   * MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, MySQLPlanNodePlain)"})
  public void testNewMySQLPlanNodePlain_thenReturnNodeTypeIsType() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain source =
        new MySQLPlanNodePlain(new MySQLPlanNodePlain(new ArrayList<>()), "Type");

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(parent, source);

    // Assert
    assertEquals("Type", actualMySQLPlanNodePlain.getNodeType());
    assertEquals("Type", actualMySQLPlanNodePlain.getSelectType());
    assertNull(actualMySQLPlanNodePlain.type);
    assertNull(actualMySQLPlanNodePlain.getNested());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then return {@link MySQLPlanNodePlain#type} is {@code <plan>}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain,
   * MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, MySQLPlanNodePlain)"})
  public void testNewMySQLPlanNodePlain_thenReturnTypeIsPlan() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain =
        new MySQLPlanNodePlain(parent, new MySQLPlanNodePlain(new ArrayList<>()));

    // Assert
    assertEquals("<plan>", actualMySQLPlanNodePlain.type);
    assertNull(actualMySQLPlanNodePlain.getNodeType());
    assertNull(actualMySQLPlanNodePlain.getSelectType());
    assertTrue(actualMySQLPlanNodePlain.getNested().isEmpty());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(List)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@link MySQLPlanNodePlain#type} is {@code <plan>}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(List)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(List)"})
  public void testNewMySQLPlanNodePlain_whenArrayList_thenReturnTypeIsPlan() {
    // Arrange and Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(new ArrayList<>());

    // Assert
    assertEquals("<plan>", actualMySQLPlanNodePlain.type);
    assertNull(actualMySQLPlanNodePlain.getId());
    assertNull(actualMySQLPlanNodePlain.getFiltered());
    assertNull(actualMySQLPlanNodePlain.getRowCount());
    assertNull(actualMySQLPlanNodePlain.getNodeCost());
    assertNull(actualMySQLPlanNodePlain.getNodeDuration());
    assertNull(actualMySQLPlanNodePlain.getNodePercent());
    assertNull(actualMySQLPlanNodePlain.getNodeRowCount());
    assertNull(actualMySQLPlanNodePlain.getExtra());
    assertNull(actualMySQLPlanNodePlain.getKey());
    assertNull(actualMySQLPlanNodePlain.getKeyLength());
    assertNull(actualMySQLPlanNodePlain.getNodeDescription());
    assertNull(actualMySQLPlanNodePlain.getNodeName());
    assertNull(actualMySQLPlanNodePlain.getNodeType());
    assertNull(actualMySQLPlanNodePlain.getPossibleKeys());
    assertNull(actualMySQLPlanNodePlain.getRef());
    assertNull(actualMySQLPlanNodePlain.getSelectType());
    assertNull(actualMySQLPlanNodePlain.getTable());
    assertNull(actualMySQLPlanNodePlain.getNodeCondition());
    assertNull(actualMySQLPlanNodePlain.getParent());
    assertEquals(DBCPlanNodeKind.DEFAULT, actualMySQLPlanNodePlain.getNodeKind());
    assertFalse(actualMySQLPlanNodePlain.isCompositeNode());
    assertTrue(actualMySQLPlanNodePlain.getNested().isEmpty());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code extra} is {@code Props}.
   *   <li>Then return Extra is {@code Props}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, Map)"})
  public void testNewMySQLPlanNodePlain_whenHashMapExtraIsProps_thenReturnExtraIsProps() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    HashMap<String, Object> props = new HashMap<>();
    props.put("select_type", null);
    props.put("table", null);
    props.put("type", null);
    props.put("possible_keys", null);
    props.put("key", null);
    props.put("key_len", null);
    props.put("ref", null);
    props.put("extra", "Props");
    props.put("rows", null);
    props.put("filtered", null);
    props.put("id", null);

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(parent, props);

    // Assert
    assertEquals("Props", actualMySQLPlanNodePlain.getExtra());
    assertEquals(0, actualMySQLPlanNodePlain.getId().intValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getFiltered().longValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getRowCount().longValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code id} is forty-two.
   *   <li>Then return Id intValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, Map)"})
  public void testNewMySQLPlanNodePlain_whenHashMapIdIsFortyTwo_thenReturnIdIntValueIsFortyTwo() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    HashMap<String, Object> props = new HashMap<>();
    props.put("id", 42);

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(parent, props);

    // Assert
    assertNull(actualMySQLPlanNodePlain.getFiltered());
    assertNull(actualMySQLPlanNodePlain.getRowCount());
    assertNull(actualMySQLPlanNodePlain.getNodeRowCount());
    assertEquals(42, actualMySQLPlanNodePlain.getId().intValue());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code id} is {@code null}.
   *   <li>Then return Id intValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, Map)"})
  public void testNewMySQLPlanNodePlain_whenHashMapIdIsNull_thenReturnIdIntValueIsZero() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    HashMap<String, Object> props = new HashMap<>();
    props.put("select_type", null);
    props.put("table", null);
    props.put("type", null);
    props.put("possible_keys", null);
    props.put("key", null);
    props.put("key_len", null);
    props.put("ref", null);
    props.put("extra", null);
    props.put("rows", null);
    props.put("filtered", null);
    props.put("id", null);

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(parent, props);

    // Assert
    assertEquals(0, actualMySQLPlanNodePlain.getId().intValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getFiltered().longValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getRowCount().longValue());
    assertEquals(0L, actualMySQLPlanNodePlain.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code rows} is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, Map)"})
  public void testNewMySQLPlanNodePlain_whenHashMapRowsIsFortyTwo() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    HashMap<String, Object> props = new HashMap<>();
    props.put("rows", 42);

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(parent, props);

    // Assert
    assertNull(actualMySQLPlanNodePlain.getId());
    assertNull(actualMySQLPlanNodePlain.getFiltered());
    assertEquals(42L, actualMySQLPlanNodePlain.getRowCount().longValue());
    assertEquals(42L, actualMySQLPlanNodePlain.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return RowCount is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#MySQLPlanNodePlain(MySQLPlanNodePlain, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.<init>(MySQLPlanNodePlain, Map)"})
  public void testNewMySQLPlanNodePlain_whenHashMap_thenReturnRowCountIsNull() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());

    // Act
    MySQLPlanNodePlain actualMySQLPlanNodePlain = new MySQLPlanNodePlain(parent, new HashMap<>());

    // Assert
    assertNull(actualMySQLPlanNodePlain.getId());
    assertNull(actualMySQLPlanNodePlain.getFiltered());
    assertNull(actualMySQLPlanNodePlain.getRowCount());
    assertNull(actualMySQLPlanNodePlain.getNodeRowCount());
  }

  /**
   * Test {@link MySQLPlanNodePlain#getParent()}.
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#getParent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MySQLPlanNodePlain MySQLPlanNodePlain.getParent()"})
  public void testGetParent() {
    // Arrange, Act and Assert
    assertNull(new MySQLPlanNodePlain(new ArrayList<>()).getParent());
  }

  /**
   * Test {@link MySQLPlanNodePlain#setParent(MySQLPlanNodePlain)}.
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#setParent(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.setParent(MySQLPlanNodePlain)"})
  public void testSetParent() {
    // Arrange
    MySQLPlanNodePlain mySQLPlanNodePlain = new MySQLPlanNodePlain(new ArrayList<>());
    mySQLPlanNodePlain.setParent(new MySQLPlanNodePlain(new ArrayList<>()));
    ArrayList<MySQLPlanNodePlain> nodes = new ArrayList<>();
    MySQLPlanNodePlain node = new MySQLPlanNodePlain(nodes);

    // Act
    mySQLPlanNodePlain.setParent(node);

    // Assert
    List<MySQLPlanNodePlain> nested = node.getNested();
    assertEquals(1, nested.size());
    assertSame(nodes, nested);
    assertSame(mySQLPlanNodePlain, nested.get(0));
  }

  /**
   * Test {@link MySQLPlanNodePlain#setParent(MySQLPlanNodePlain)}.
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#setParent(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.setParent(MySQLPlanNodePlain)"})
  public void testSetParent2() {
    // Arrange
    MySQLPlanNodePlain mySQLPlanNodePlain = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain node =
        new MySQLPlanNodePlain(new MySQLPlanNodePlain(new ArrayList<>()), "Type");

    // Act
    mySQLPlanNodePlain.setParent(node);

    // Assert
    List<MySQLPlanNodePlain> nested = node.getNested();
    assertEquals(1, nested.size());
    assertSame(mySQLPlanNodePlain, nested.get(0));
    assertSame(node, mySQLPlanNodePlain.getParent());
  }

  /**
   * Test {@link MySQLPlanNodePlain#setParent(MySQLPlanNodePlain)}.
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#setParent(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.setParent(MySQLPlanNodePlain)"})
  public void testSetParent3() {
    // Arrange
    MySQLPlanNodePlain parent =
        new MySQLPlanNodePlain(new MySQLPlanNodePlain(new ArrayList<>()), "Type");
    MySQLPlanNodePlain parent2 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain left = new MySQLPlanNodePlain(new ArrayList<>());

    MySQLPlanNodeJoin source =
        new MySQLPlanNodeJoin(parent2, left, new MySQLPlanNodePlain(new ArrayList<>()));

    MySQLPlanNodeJoin mySQLPlanNodeJoin = new MySQLPlanNodeJoin(parent, source);
    ArrayList<MySQLPlanNodePlain> nodes = new ArrayList<>();
    MySQLPlanNodePlain node = new MySQLPlanNodePlain(nodes);

    // Act
    mySQLPlanNodeJoin.setParent(node);

    // Assert
    assertSame(nodes, node.getNested());
    assertSame(node, mySQLPlanNodeJoin.getParent());
  }

  /**
   * Test {@link MySQLPlanNodePlain#setParent(MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then {@link MySQLPlanNodePlain#MySQLPlanNodePlain(List)} with nodes is {@link
   *       ArrayList#ArrayList()} Nested size is one.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#setParent(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.setParent(MySQLPlanNodePlain)"})
  public void testSetParent_thenMySQLPlanNodePlainWithNodesIsArrayListNestedSizeIsOne() {
    // Arrange
    MySQLPlanNodePlain mySQLPlanNodePlain = new MySQLPlanNodePlain(new ArrayList<>());
    ArrayList<MySQLPlanNodePlain> nodes = new ArrayList<>();
    MySQLPlanNodePlain node = new MySQLPlanNodePlain(nodes);

    // Act
    mySQLPlanNodePlain.setParent(node);

    // Assert
    List<MySQLPlanNodePlain> nested = node.getNested();
    assertEquals(1, nested.size());
    assertSame(nodes, nested);
    assertSame(mySQLPlanNodePlain, nested.get(0));
  }

  /**
   * Test {@link MySQLPlanNodePlain#setParent(MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then {@link MySQLPlanNodePlain#MySQLPlanNodePlain(List)} with nodes is {@link
   *       ArrayList#ArrayList()} Nested Empty.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#setParent(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.setParent(MySQLPlanNodePlain)"})
  public void testSetParent_whenNull_thenMySQLPlanNodePlainWithNodesIsArrayListNestedEmpty() {
    // Arrange
    MySQLPlanNodePlain mySQLPlanNodePlain = new MySQLPlanNodePlain(new ArrayList<>());

    // Act
    mySQLPlanNodePlain.setParent(null);

    // Assert that nothing has changed
    assertTrue(mySQLPlanNodePlain.getNested().isEmpty());
  }

  /**
   * Test {@link MySQLPlanNodePlain#getNodeKind()}.
   *
   * <ul>
   *   <li>Then return {@code DEFAULT}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#getNodeKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCPlanNodeKind MySQLPlanNodePlain.getNodeKind()"})
  public void testGetNodeKind_thenReturnDefault() {
    // Arrange, Act and Assert
    assertEquals(DBCPlanNodeKind.DEFAULT, new MySQLPlanNodePlain(new ArrayList<>()).getNodeKind());
  }

  /**
   * Test {@link MySQLPlanNodePlain#getNodeKind()}.
   *
   * <ul>
   *   <li>Then return {@code JOIN}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#getNodeKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCPlanNodeKind MySQLPlanNodePlain.getNodeKind()"})
  public void testGetNodeKind_thenReturnJoin() {
    // Arrange
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent2 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain left = new MySQLPlanNodePlain(new ArrayList<>());

    MySQLPlanNodeJoin source =
        new MySQLPlanNodeJoin(parent2, left, new MySQLPlanNodePlain(new ArrayList<>()));

    MySQLPlanNodeJoin mySQLPlanNodeJoin = new MySQLPlanNodeJoin(parent, source);

    // Act and Assert
    assertEquals(DBCPlanNodeKind.JOIN, mySQLPlanNodeJoin.getNodeKind());
  }

  /**
   * Test {@link MySQLPlanNodePlain#getNodeKind()}.
   *
   * <ul>
   *   <li>Then return {@code SELECT}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#getNodeKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCPlanNodeKind MySQLPlanNodePlain.getNodeKind()"})
  public void testGetNodeKind_thenReturnSelect() {
    // Arrange, Act and Assert
    assertEquals(
        DBCPlanNodeKind.SELECT,
        new MySQLPlanNodePlain(new MySQLPlanNodePlain(new ArrayList<>()), "SIMPLE").getNodeKind());
  }

  /**
   * Test {@link MySQLPlanNodePlain#getNodeKind()}.
   *
   * <ul>
   *   <li>Then return {@code UNION}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#getNodeKind()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBCPlanNodeKind MySQLPlanNodePlain.getNodeKind()"})
  public void testGetNodeKind_thenReturnUnion() {
    // Arrange, Act and Assert
    assertEquals(
        DBCPlanNodeKind.UNION,
        new MySQLPlanNodePlain(new MySQLPlanNodePlain(new ArrayList<>()), "UNION").getNodeKind());
  }

  /**
   * Test {@link MySQLPlanNodePlain#getNested()}.
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#getNested()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List MySQLPlanNodePlain.getNested()"})
  public void testGetNested() {
    // Arrange, Act and Assert
    assertTrue(new MySQLPlanNodePlain(new ArrayList<>()).getNested().isEmpty());
  }

  /**
   * Test {@link MySQLPlanNodePlain#isCompositeNode()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#isCompositeNode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MySQLPlanNodePlain.isCompositeNode()"})
  public void testIsCompositeNode_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new MySQLPlanNodePlain(new ArrayList<>()).isCompositeNode());
  }

  /**
   * Test {@link MySQLPlanNodePlain#isCompositeNode()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#isCompositeNode()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MySQLPlanNodePlain.isCompositeNode()"})
  public void testIsCompositeNode_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(
        new MySQLPlanNodePlain(new MySQLPlanNodePlain(new ArrayList<>()), "PRIMARY")
            .isCompositeNode());
  }

  /**
   * Test {@link MySQLPlanNodePlain#computeStats()}.
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#computeStats()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.computeStats()"})
  public void testComputeStats() {
    // Arrange
    MySQLPlanNodePlain mySQLPlanNodePlain = new MySQLPlanNodePlain(new ArrayList<>());

    // Act
    mySQLPlanNodePlain.computeStats();

    // Assert
    assertEquals(0L, mySQLPlanNodePlain.getRowCount().longValue());
    assertEquals(0L, mySQLPlanNodePlain.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodePlain#computeStats()}.
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#computeStats()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.computeStats()"})
  public void testComputeStats2() {
    // Arrange
    ArrayList<MySQLPlanNodePlain> nodes = new ArrayList<>();
    nodes.add(new MySQLPlanNodePlain(new ArrayList<>()));
    MySQLPlanNodePlain mySQLPlanNodePlain = new MySQLPlanNodePlain(nodes);

    // Act
    mySQLPlanNodePlain.computeStats();

    // Assert
    assertEquals(0L, mySQLPlanNodePlain.getRowCount().longValue());
    List<MySQLPlanNodePlain> nested = mySQLPlanNodePlain.getNested();
    assertEquals(1, nested.size());
    MySQLPlanNodePlain getResult = nested.get(0);
    assertEquals(0L, getResult.getRowCount().longValue());
    assertEquals(0L, mySQLPlanNodePlain.getNodeRowCount().longValue());
    assertEquals(0L, getResult.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodePlain#computeStats()}.
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#computeStats()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.computeStats()"})
  public void testComputeStats3() {
    // Arrange
    MySQLPlanNodePlain mySQLPlanNodePlain =
        new MySQLPlanNodePlain(new MySQLPlanNodePlain(new ArrayList<>()), "Type");

    // Act
    mySQLPlanNodePlain.computeStats();

    // Assert that nothing has changed
    assertNull(mySQLPlanNodePlain.getRowCount());
    assertNull(mySQLPlanNodePlain.getNodeRowCount());
  }

  /**
   * Test {@link MySQLPlanNodePlain#computeStats()}.
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#computeStats()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodePlain.computeStats()"})
  public void testComputeStats4() {
    // Arrange
    ArrayList<MySQLPlanNodePlain> nodes = new ArrayList<>();
    nodes.add(new MySQLPlanNodePlain(new MySQLPlanNodePlain(new ArrayList<>()), "Type"));
    MySQLPlanNodePlain mySQLPlanNodePlain = new MySQLPlanNodePlain(nodes);

    // Act
    mySQLPlanNodePlain.computeStats();

    // Assert
    List<MySQLPlanNodePlain> nested = mySQLPlanNodePlain.getNested();
    assertEquals(1, nested.size());
    MySQLPlanNodePlain getResult = nested.get(0);
    assertNull(getResult.getRowCount());
    assertNull(getResult.getNodeRowCount());
    assertEquals(0L, mySQLPlanNodePlain.getRowCount().longValue());
    assertEquals(0L, mySQLPlanNodePlain.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodePlain#copyNode(MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then Nested first return {@link MySQLPlanNodeJoin}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#copyNode(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MySQLPlanNodePlain MySQLPlanNodePlain.copyNode(MySQLPlanNodePlain)"})
  public void testCopyNode_thenNestedFirstReturnMySQLPlanNodeJoin() {
    // Arrange
    ArrayList<MySQLPlanNodePlain> nodes = new ArrayList<>();
    MySQLPlanNodePlain parent = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain parent2 = new MySQLPlanNodePlain(new ArrayList<>());
    MySQLPlanNodePlain left = new MySQLPlanNodePlain(new ArrayList<>());

    MySQLPlanNodeJoin source =
        new MySQLPlanNodeJoin(parent2, left, new MySQLPlanNodePlain(new ArrayList<>()));

    MySQLPlanNodeJoin mySQLPlanNodeJoin = new MySQLPlanNodeJoin(parent, source);
    nodes.add(mySQLPlanNodeJoin);
    MySQLPlanNodePlain mySQLPlanNodePlain = new MySQLPlanNodePlain(nodes);

    // Act and Assert
    List<MySQLPlanNodePlain> nested =
        mySQLPlanNodePlain.copyNode(new MySQLPlanNodePlain(new ArrayList<>())).getNested();
    assertEquals(1, nested.size());
    MySQLPlanNodePlain getResult = nested.get(0);
    assertTrue(getResult instanceof MySQLPlanNodeJoin);
    assertEquals("JOIN", getResult.getNodeType());
    assertEquals("JOIN", getResult.getSelectType());
    assertNull(((MySQLPlanNodeJoin) getResult).type);
    assertEquals(2, getResult.getNested().size());
    assertEquals(DBCPlanNodeKind.JOIN, getResult.getNodeKind());
  }

  /**
   * Test {@link MySQLPlanNodePlain#copyNode(MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then return {@link MySQLPlanNodeJoin}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#copyNode(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MySQLPlanNodePlain MySQLPlanNodePlain.copyNode(MySQLPlanNodePlain)"})
  public void testCopyNode_thenReturnMySQLPlanNodeJoin() {
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
    assertEquals("JOIN", actualCopyNodeResult.getNodeType());
    assertEquals("JOIN", actualCopyNodeResult.getSelectType());
    assertEquals(2, actualCopyNodeResult.getNested().size());
    assertEquals(DBCPlanNodeKind.JOIN, actualCopyNodeResult.getNodeKind());
  }

  /**
   * Test {@link MySQLPlanNodePlain#copyNode(MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then return Nested first {@link MySQLPlanNodePlain#type} is {@code <plan>}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#copyNode(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MySQLPlanNodePlain MySQLPlanNodePlain.copyNode(MySQLPlanNodePlain)"})
  public void testCopyNode_thenReturnNestedFirstTypeIsPlan() {
    // Arrange
    ArrayList<MySQLPlanNodePlain> nodes = new ArrayList<>();
    nodes.add(new MySQLPlanNodePlain(new ArrayList<>()));
    MySQLPlanNodePlain mySQLPlanNodePlain = new MySQLPlanNodePlain(nodes);

    // Act
    MySQLPlanNodePlain actualCopyNodeResult =
        mySQLPlanNodePlain.copyNode(new MySQLPlanNodePlain(new ArrayList<>()));

    // Assert
    List<MySQLPlanNodePlain> nested = actualCopyNodeResult.getNested();
    assertEquals(1, nested.size());
    MySQLPlanNodePlain getResult = nested.get(0);
    assertEquals("<plan>", getResult.type);
    assertNull(getResult.getNodeType());
    assertNull(getResult.getSelectType());
    assertEquals(DBCPlanNodeKind.DEFAULT, getResult.getNodeKind());
    assertTrue(getResult.getNested().isEmpty());
    assertSame(actualCopyNodeResult, getResult.getParent());
  }

  /**
   * Test {@link MySQLPlanNodePlain#copyNode(MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then return NodeType is {@code Type}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#copyNode(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MySQLPlanNodePlain MySQLPlanNodePlain.copyNode(MySQLPlanNodePlain)"})
  public void testCopyNode_thenReturnNodeTypeIsType() {
    // Arrange
    MySQLPlanNodePlain mySQLPlanNodePlain =
        new MySQLPlanNodePlain(new MySQLPlanNodePlain(new ArrayList<>()), "Type");

    // Act
    MySQLPlanNodePlain actualCopyNodeResult =
        mySQLPlanNodePlain.copyNode(new MySQLPlanNodePlain(new ArrayList<>()));

    // Assert
    assertEquals("Type", actualCopyNodeResult.getNodeType());
    assertEquals("Type", actualCopyNodeResult.getSelectType());
    assertNull(actualCopyNodeResult.type);
    assertNull(actualCopyNodeResult.getNested());
  }

  /**
   * Test {@link MySQLPlanNodePlain#copyNode(MySQLPlanNodePlain)}.
   *
   * <ul>
   *   <li>Then return {@link MySQLPlanNodePlain#type} is {@code <plan>}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodePlain#copyNode(MySQLPlanNodePlain)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MySQLPlanNodePlain MySQLPlanNodePlain.copyNode(MySQLPlanNodePlain)"})
  public void testCopyNode_thenReturnTypeIsPlan() {
    // Arrange
    MySQLPlanNodePlain mySQLPlanNodePlain = new MySQLPlanNodePlain(new ArrayList<>());

    // Act
    MySQLPlanNodePlain actualCopyNodeResult =
        mySQLPlanNodePlain.copyNode(new MySQLPlanNodePlain(new ArrayList<>()));

    // Assert
    assertEquals("<plan>", actualCopyNodeResult.type);
    assertNull(actualCopyNodeResult.getNodeType());
    assertNull(actualCopyNodeResult.getSelectType());
    assertTrue(actualCopyNodeResult.getNested().isEmpty());
  }
}
