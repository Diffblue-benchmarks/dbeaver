package org.jkiss.dbeaver.ext.mysql.model.plan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jkiss.dbeaver.model.exec.plan.DBCPlanNodeKind;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.meta.PropertyLength;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class MySQLPlanNodeJSONDiffblueTest {
  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, Map)}.
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, Map)"})
  public void testNewMySQLPlanNodeJSON() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Assert
    Collection<MySQLPlanNodeJSON> nested = actualMySQLPlanNodeJSON.getNested();
    assertTrue(nested instanceof List);
    assertNull(actualMySQLPlanNodeJSON.getNodeDuration());
    assertNull(actualMySQLPlanNodeJSON.getNodePercent());
    assertNull(actualMySQLPlanNodeJSON.getNodeName());
    assertNull(actualMySQLPlanNodeJSON.getNodeType());
    assertNull(actualMySQLPlanNodeJSON.getNodeCondition());
    assertNull(actualMySQLPlanNodeJSON.getNodeDescription());
    assertEquals(0, actualMySQLPlanNodeJSON.getProperties().length);
    assertEquals(0L, actualMySQLPlanNodeJSON.getNodeCost().longValue());
    assertEquals(0L, actualMySQLPlanNodeJSON.getNodeRowCount().longValue());
    assertEquals(DBCPlanNodeKind.DEFAULT, actualMySQLPlanNodeJSON.getNodeKind());
    assertTrue(nested.isEmpty());
    assertTrue(actualMySQLPlanNodeJSON.getNodeProps().isEmpty());
    assertSame(parent, actualMySQLPlanNodeJSON.getParent());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then return NodeProps {@code 42} is {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_givenFalse_thenReturnNodeProps42IsFalseToString() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("42", false);
    object.add("Property", new JsonArray(3));

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualMySQLPlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("42", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("42", dbpPropertyDescriptor.getDisplayName());
    assertEquals("42", dbpPropertyDescriptor.getId());
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals(1, properties.length);
    assertEquals(Boolean.FALSE.toString(), nodeProps.get("42"));
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three.
   *   <li>Then return Nested Empty.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_givenJsonArrayWithCapacityIsThree_thenReturnNestedEmpty() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.add("Property", new JsonArray(3));

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    Collection<MySQLPlanNodeJSON> nested = actualMySQLPlanNodeJSON.getNested();
    assertTrue(nested instanceof List);
    assertEquals(0, actualMySQLPlanNodeJSON.getProperties().length);
    assertTrue(nested.isEmpty());
    assertTrue(actualMySQLPlanNodeJSON.getNodeProps().isEmpty());
    assertEquals(nested, actualMySQLPlanNodeJSON.getParent().getNested());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor).
   *   <li>Then return Nested first NodeType is {@code Property}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_givenJsonObject_thenReturnNestedFirstNodeTypeIsProperty() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.add("Property", new JsonObject());

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    Collection<MySQLPlanNodeJSON> nested = actualMySQLPlanNodeJSON.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    Collection<MySQLPlanNodeJSON> nested2 = actualMySQLPlanNodeJSON.getParent().getNested();
    assertTrue(nested2 instanceof List);
    MySQLPlanNodeJSON getResult = ((List<MySQLPlanNodeJSON>) nested).get(0);
    assertEquals("Property", getResult.getNodeType());
    assertNull(getResult.getNodeDuration());
    assertNull(getResult.getNodePercent());
    assertNull(getResult.getNodeName());
    assertNull(getResult.getNodeCondition());
    assertNull(getResult.getNodeDescription());
    assertEquals(0, actualMySQLPlanNodeJSON.getProperties().length);
    assertEquals(0, getResult.getProperties().length);
    assertEquals(0L, getResult.getNodeCost().longValue());
    assertEquals(0L, getResult.getNodeRowCount().longValue());
    assertEquals(DBCPlanNodeKind.DEFAULT, getResult.getNodeKind());
    assertTrue(nested2.isEmpty());
    assertTrue(actualMySQLPlanNodeJSON.getNodeProps().isEmpty());
    assertTrue(getResult.getNodeProps().isEmpty());
    assertEquals(nested2, getResult.getNested());
    assertSame(actualMySQLPlanNodeJSON, getResult.getParent());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Given valueOf one.
   *   <li>Then return NodeProps {@code 42} is {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_givenValueOfOne_thenReturnNodeProps42Is1() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("42", Integer.valueOf(1));
    object.add("Property", new JsonArray(3));

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualMySQLPlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("1", nodeProps.get("42"));
    assertEquals("42", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("42", dbpPropertyDescriptor.getDisplayName());
    assertEquals("42", dbpPropertyDescriptor.getId());
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Given {@code Value}.
   *   <li>Then return NodeProps {@code 42} is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_givenValue_thenReturnNodeProps42IsValue() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("42", "Value");
    object.add("Property", new JsonArray(3));

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualMySQLPlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("42", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("42", dbpPropertyDescriptor.getDisplayName());
    assertEquals("42", dbpPropertyDescriptor.getId());
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("Value", nodeProps.get("42"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [1,true]}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_thenReturnNodePropsPropertyIs1True() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(Integer.valueOf(1));
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualMySQLPlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[1,true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [{"42":[]},true]}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_thenReturnNodePropsPropertyIs42True() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonObject element = new JsonObject();
    element.add("42", new JsonArray(3));

    JsonArray value = new JsonArray(3);
    value.add(element);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    Collection<MySQLPlanNodeJSON> nested = actualMySQLPlanNodeJSON.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    Collection<MySQLPlanNodeJSON> nested2 = actualMySQLPlanNodeJSON.getParent().getNested();
    assertTrue(nested2 instanceof List);
    MySQLPlanNodeJSON getResult = ((List<MySQLPlanNodeJSON>) nested).get(0);
    assertEquals("Property#1", getResult.getNodeType());
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[{\"42\":[]},true]", nodeProps.get("Property"));
    assertNull(getResult.getNodeDuration());
    assertNull(getResult.getNodePercent());
    assertNull(getResult.getNodeName());
    assertNull(getResult.getNodeCondition());
    assertNull(getResult.getNodeDescription());
    assertEquals(0, getResult.getProperties().length);
    assertEquals(0L, getResult.getNodeCost().longValue());
    assertEquals(0L, getResult.getNodeRowCount().longValue());
    assertEquals(DBCPlanNodeKind.DEFAULT, getResult.getNodeKind());
    assertTrue(nested2.isEmpty());
    assertTrue(getResult.getNodeProps().isEmpty());
    assertEquals(nested2, getResult.getNested());
    assertSame(actualMySQLPlanNodeJSON, getResult.getParent());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [{"":[],"42":[]},true]}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_thenReturnNodePropsPropertyIs42True2() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonObject element = new JsonObject();
    element.add("", new JsonArray(3));
    element.add("42", new JsonArray(3));

    JsonArray value = new JsonArray(3);
    value.add(element);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    Collection<MySQLPlanNodeJSON> nested = actualMySQLPlanNodeJSON.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    Collection<MySQLPlanNodeJSON> nested2 = actualMySQLPlanNodeJSON.getParent().getNested();
    assertTrue(nested2 instanceof List);
    MySQLPlanNodeJSON getResult = ((List<MySQLPlanNodeJSON>) nested).get(0);
    assertEquals("Property#1", getResult.getNodeType());
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[{\"\":[],\"42\":[]},true]", nodeProps.get("Property"));
    assertNull(getResult.getNodeDuration());
    assertNull(getResult.getNodePercent());
    assertNull(getResult.getNodeName());
    assertNull(getResult.getNodeCondition());
    assertNull(getResult.getNodeDescription());
    assertEquals(0, getResult.getProperties().length);
    assertEquals(0L, getResult.getNodeCost().longValue());
    assertEquals(0L, getResult.getNodeRowCount().longValue());
    assertEquals(DBCPlanNodeKind.DEFAULT, getResult.getNodeKind());
    assertTrue(nested2.isEmpty());
    assertTrue(getResult.getNodeProps().isEmpty());
    assertEquals(nested2, getResult.getNested());
    assertSame(actualMySQLPlanNodeJSON, getResult.getParent());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code ["A",true]}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_thenReturnNodePropsPropertyIsATrue() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add('A');
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualMySQLPlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[\"A\",true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [false,true]}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_thenReturnNodePropsPropertyIsFalseTrue() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(false);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualMySQLPlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[false,true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [null,true]}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_thenReturnNodePropsPropertyIsNullTrue() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add((JsonElement) null);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualMySQLPlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[null,true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [true]}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_thenReturnNodePropsPropertyIsTrue() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualMySQLPlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [[],true]}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_thenReturnNodePropsPropertyIsTrue2() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(new JsonArray(3));
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualMySQLPlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[[],true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [{},true]}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_thenReturnNodePropsPropertyIsTrue3() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(new JsonObject());
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    Collection<MySQLPlanNodeJSON> nested = actualMySQLPlanNodeJSON.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    Collection<MySQLPlanNodeJSON> nested2 = actualMySQLPlanNodeJSON.getParent().getNested();
    assertTrue(nested2 instanceof List);
    MySQLPlanNodeJSON getResult = ((List<MySQLPlanNodeJSON>) nested).get(0);
    assertEquals("Property#1", getResult.getNodeType());
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[{},true]", nodeProps.get("Property"));
    assertNull(getResult.getNodeDuration());
    assertNull(getResult.getNodePercent());
    assertNull(getResult.getNodeName());
    assertNull(getResult.getNodeCondition());
    assertNull(getResult.getNodeDescription());
    assertEquals(0, getResult.getProperties().length);
    assertEquals(0L, getResult.getNodeCost().longValue());
    assertEquals(0L, getResult.getNodeRowCount().longValue());
    assertEquals(DBCPlanNodeKind.DEFAULT, getResult.getNodeKind());
    assertTrue(nested2.isEmpty());
    assertTrue(getResult.getNodeProps().isEmpty());
    assertEquals(nested2, getResult.getNested());
    assertSame(actualMySQLPlanNodeJSON, getResult.getParent());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code ["\u0001"]}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_thenReturnNodePropsPropertyIsU0001() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add('\u0001');

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualMySQLPlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, Object> nodeProps = actualMySQLPlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[\"\\u0001\"]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}.
   *
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then return Nested Empty.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void MySQLPlanNodeJSON.<init>(MySQLPlanNodeJSON, String, JsonObject)"})
  public void testNewMySQLPlanNodeJSON_whenJsonObject_thenReturnNestedEmpty() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    // Act
    MySQLPlanNodeJSON actualMySQLPlanNodeJSON =
        new MySQLPlanNodeJSON(parent, "Name", new JsonObject());

    // Assert
    Collection<MySQLPlanNodeJSON> nested = actualMySQLPlanNodeJSON.getNested();
    assertTrue(nested instanceof List);
    assertEquals(0, actualMySQLPlanNodeJSON.getProperties().length);
    assertTrue(nested.isEmpty());
    assertTrue(actualMySQLPlanNodeJSON.getNodeProps().isEmpty());
    assertEquals(nested, actualMySQLPlanNodeJSON.getParent().getNested());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getNodeName()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code access_type} is {@code 42}.
   *   <li>Then return {@code 42 (42)}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getNodeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.getNodeName()"})
  public void testGetNodeName_givenHashMapAccessTypeIs42_thenReturn4242() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("access_type", "42");
    attributes.put("table_name", "42");
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals("42 (42)", mySQLPlanNodeJSON.getNodeName());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getNodeName()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code table_name} is {@code 42}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getNodeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.getNodeName()"})
  public void testGetNodeName_givenHashMapTableNameIs42_thenReturn42() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("table_name", "42");
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals("42", mySQLPlanNodeJSON.getNodeName());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getNodeName()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getNodeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.getNodeName()"})
  public void testGetNodeName_thenReturnNull() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertNull(mySQLPlanNodeJSON.getNodeName());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getNodeCost()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code read_cost} is {@code 42}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getNodeCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLPlanNodeJSON.getNodeCost()"})
  public void testGetNodeCost_givenHashMapReadCostIs42_thenReturnDoubleValueIsFortyTwo() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("read_cost", "42");
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals(42.0d, mySQLPlanNodeJSON.getNodeCost().doubleValue(), 0.0);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getNodeCost()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code read_cost} is {@code A}.
   *   <li>Then return doubleValue is sixty-five.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getNodeCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLPlanNodeJSON.getNodeCost()"})
  public void testGetNodeCost_givenHashMapReadCostIsA_thenReturnDoubleValueIsSixtyFive() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("read_cost", (byte) 'A');
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals(65.0d, mySQLPlanNodeJSON.getNodeCost().doubleValue(), 0.0);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getNodeCost()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code read_cost} is {@code read_cost}.
   *   <li>Then return doubleValue is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getNodeCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLPlanNodeJSON.getNodeCost()"})
  public void testGetNodeCost_givenHashMapReadCostIsReadCost_thenReturnDoubleValueIsNaN() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("read_cost", "read_cost");
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals(Double.NaN, mySQLPlanNodeJSON.getNodeCost().doubleValue(), 0.0);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getNodeCost()}.
   *
   * <ul>
   *   <li>Then return longValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getNodeCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLPlanNodeJSON.getNodeCost()"})
  public void testGetNodeCost_thenReturnLongValueIsZero() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertEquals(0L, mySQLPlanNodeJSON.getNodeCost().longValue());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getNodeRowCount()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code rows_examined_per_scan} is {@code
   *       rows_examined_per_scan}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getNodeRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLPlanNodeJSON.getNodeRowCount()"})
  public void testGetNodeRowCount_givenHashMapRowsExaminedPerScanIsRowsExaminedPerScan() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("rows_examined_per_scan", "rows_examined_per_scan");
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals(0L, mySQLPlanNodeJSON.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getNodeRowCount()}.
   *
   * <ul>
   *   <li>Then return longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getNodeRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLPlanNodeJSON.getNodeRowCount()"})
  public void testGetNodeRowCount_thenReturnLongValueIsFortyTwo() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("rows_examined_per_scan", "42");
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals(42L, mySQLPlanNodeJSON.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getNodeRowCount()}.
   *
   * <ul>
   *   <li>Then return longValue is sixty-five.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getNodeRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLPlanNodeJSON.getNodeRowCount()"})
  public void testGetNodeRowCount_thenReturnLongValueIsSixtyFive() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("rows_examined_per_scan", (byte) 'A');
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals(65L, mySQLPlanNodeJSON.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getNodeRowCount()}.
   *
   * <ul>
   *   <li>Then return longValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getNodeRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number MySQLPlanNodeJSON.getNodeRowCount()"})
  public void testGetNodeRowCount_thenReturnLongValueIsZero() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertEquals(0L, mySQLPlanNodeJSON.getNodeRowCount().longValue());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getParent()}.
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getParent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"MySQLPlanNodeJSON MySQLPlanNodeJSON.getParent()"})
  public void testGetParent() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertSame(parent, mySQLPlanNodeJSON.getParent());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link MySQLPlanNodeJSON#resetPropertyValue(DBRProgressMonitor, String)}
   *   <li>{@link MySQLPlanNodeJSON#resetPropertyValueToDefault(String)}
   *   <li>{@link MySQLPlanNodeJSON#setPropertyValue(DBRProgressMonitor, String, Object)}
   *   <li>{@link MySQLPlanNodeJSON#getNested()}
   *   <li>{@link MySQLPlanNodeJSON#getNodeDuration()}
   *   <li>{@link MySQLPlanNodeJSON#getNodePercent()}
   *   <li>{@link MySQLPlanNodeJSON#getNodeProps()}
   *   <li>{@link MySQLPlanNodeJSON#getNodeType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection MySQLPlanNodeJSON.getNested()",
    "Number MySQLPlanNodeJSON.getNodeDuration()",
    "Number MySQLPlanNodeJSON.getNodePercent()",
    "Map MySQLPlanNodeJSON.getNodeProps()",
    "String MySQLPlanNodeJSON.getNodeType()",
    "void MySQLPlanNodeJSON.resetPropertyValue(DBRProgressMonitor, String)",
    "void MySQLPlanNodeJSON.resetPropertyValueToDefault(String)",
    "void MySQLPlanNodeJSON.setPropertyValue(DBRProgressMonitor, String, Object)"
  })
  public void testGettersAndSetters() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Act
    mySQLPlanNodeJSON.resetPropertyValue(new LoggingProgressMonitor(), "42");
    mySQLPlanNodeJSON.resetPropertyValueToDefault("42");
    mySQLPlanNodeJSON.setPropertyValue(new LoggingProgressMonitor(), "42", "Value");
    Collection<MySQLPlanNodeJSON> actualNested = mySQLPlanNodeJSON.getNested();
    Number actualNodeDuration = mySQLPlanNodeJSON.getNodeDuration();
    Number actualNodePercent = mySQLPlanNodeJSON.getNodePercent();
    Map<String, Object> actualNodeProps = mySQLPlanNodeJSON.getNodeProps();

    // Assert
    assertTrue(actualNested instanceof List);
    assertNull(actualNodeDuration);
    assertNull(actualNodePercent);
    assertNull(mySQLPlanNodeJSON.getNodeType());
    assertTrue(actualNodeProps.isEmpty());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getProperty(String)}.
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object MySQLPlanNodeJSON.getProperty(String)"})
  public void testGetProperty() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertNull(mySQLPlanNodeJSON.getProperty("Name"));
  }

  /**
   * Test {@link MySQLPlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code null}.
   *   <li>Then return {@code {"Property":[null]}}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddNull_thenReturnPropertyNull() {
    // Arrange
    JsonArray value = new JsonArray(3);
    value.add((Boolean) null);

    JsonObject object = new JsonObject();
    object.add("Property", value);
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":[null]}", mySQLPlanNodeJSON.toString());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   *   <li>Then return {@code {"Property":[true]}}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddTrue_thenReturnPropertyTrue() {
    // Arrange
    JsonArray value = new JsonArray(3);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":[true]}", mySQLPlanNodeJSON.toString());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and {@code false}.
   *   <li>Then return {@code {"42":false,"Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.toString()"})
  public void testToString_givenJsonObjectAddProperty42AndFalse_thenReturn42FalseProperty() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("42", false);
    object.add("Property", new JsonArray(3));
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":false,\"Property\":[]}", mySQLPlanNodeJSON.toString());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and null.
   *   <li>Then return {@code {"42":"\u0000","Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.toString()"})
  public void testToString_givenJsonObjectAddProperty42AndNull_thenReturn42U0000Property() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("42", '\u0000');
    object.add("Property", new JsonArray(3));
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":\"\\u0000\",\"Property\":[]}", mySQLPlanNodeJSON.toString());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and valueOf one.
   *   <li>Then return {@code {"42":1,"Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.toString()"})
  public void testToString_givenJsonObjectAddProperty42AndValueOfOne_thenReturn421Property() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("42", Integer.valueOf(1));
    object.add("Property", new JsonArray(3));
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":1,\"Property\":[]}", mySQLPlanNodeJSON.toString());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and {@code Value}.
   *   <li>Then return {@code {"42":"Value","Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.toString()"})
  public void testToString_givenJsonObjectAddProperty42AndValue_thenReturn42ValueProperty() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("42", "Value");
    object.add("Property", new JsonArray(3));
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":\"Value\",\"Property\":[]}", mySQLPlanNodeJSON.toString());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code Property} and {@code
   *       true}.
   *   <li>Then return {@code {"Property":true}}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.toString()"})
  public void testToString_givenJsonObjectAddPropertyPropertyAndTrue_thenReturnPropertyTrue() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("Property", true);
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":true}", mySQLPlanNodeJSON.toString());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link MySQLPlanNodeJSON#MySQLPlanNodeJSON(MySQLPlanNodeJSON, String, JsonObject)}
   *       with parent is {@code null} and {@code Name} and object is {@link JsonObject} (default
   *       constructor).
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.toString()"})
  public void testToString_givenMySQLPlanNodeJSONWithParentIsNullAndNameAndObjectIsJsonObject() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertEquals("{}", mySQLPlanNodeJSON.toString());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Then return {@code {"42":[],"Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.toString()"})
  public void testToString_thenReturn42Property() {
    // Arrange
    JsonObject object = new JsonObject();
    object.add("42", new JsonArray(3));
    object.add("Property", new JsonArray(3));
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":[],\"Property\":[]}", mySQLPlanNodeJSON.toString());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Then return {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.toString()"})
  public void testToString_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", new JsonObject());

    // Act and Assert
    assertEquals("{}", mySQLPlanNodeJSON.toString());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Then return {@code {"Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.toString()"})
  public void testToString_thenReturnProperty() {
    // Arrange
    JsonObject object = new JsonObject();
    object.add("Property", new JsonArray(3));
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":[]}", mySQLPlanNodeJSON.toString());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Then return {@code {"Property":[false,true]}}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String MySQLPlanNodeJSON.toString()"})
  public void testToString_thenReturnPropertyFalseTrue() {
    // Arrange
    JsonArray value = new JsonArray(3);
    value.add(false);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, new HashMap<>());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":[false,true]}", mySQLPlanNodeJSON.toString());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getEditableValue()}.
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getEditableValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object MySQLPlanNodeJSON.getEditableValue()"})
  public void testGetEditableValue() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Act
    Object actualEditableValue = mySQLPlanNodeJSON.getEditableValue();

    // Assert
    assertSame(mySQLPlanNodeJSON, actualEditableValue);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getProperties()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code foo} is {@code 42}.
   *   <li>Then first element return {@link PropertyDescriptor}.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor[] MySQLPlanNodeJSON.getProperties()"})
  public void testGetProperties_givenHashMapFooIs42_thenFirstElementReturnPropertyDescriptor() {
    // Arrange
    HashMap<String, Object> attributes = new HashMap<>();
    attributes.put("foo", "42");
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());

    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, attributes);

    // Act
    DBPPropertyDescriptor[] actualProperties = mySQLPlanNodeJSON.getProperties();

    // Assert
    DBPPropertyDescriptor dbpPropertyDescriptor = actualProperties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Details", dbpPropertyDescriptor.getCategory());
    assertEquals("foo", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("foo", dbpPropertyDescriptor.getDisplayName());
    assertEquals("foo", dbpPropertyDescriptor.getId());
    assertNull(dbpPropertyDescriptor.getDefaultValue());
    assertNull(dbpPropertyDescriptor.getDescription());
    assertNull(dbpPropertyDescriptor.getHint());
    assertNull(dbpPropertyDescriptor.getFeatures());
    assertNull(dbpPropertyDescriptor.getRequiredFeatures());
    assertNull(((PropertyDescriptor) dbpPropertyDescriptor).getPropertyType());
    assertEquals(1, actualProperties.length);
    assertEquals(PropertyLength.LONG, dbpPropertyDescriptor.getLength());
    assertFalse(dbpPropertyDescriptor.isRequired());
    Class<String> expectedDataType = String.class;
    assertEquals(expectedDataType, dbpPropertyDescriptor.getDataType());
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getProperties()}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor[] MySQLPlanNodeJSON.getProperties()"})
  public void testGetProperties_thenReturnArrayLengthIsZero() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertEquals(0, mySQLPlanNodeJSON.getProperties().length);
  }

  /**
   * Test {@link MySQLPlanNodeJSON#getPropertyValue(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#getPropertyValue(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object MySQLPlanNodeJSON.getPropertyValue(DBRProgressMonitor, String)"})
  public void testGetPropertyValue() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertNull(mySQLPlanNodeJSON.getPropertyValue(new LoggingProgressMonitor(), "42"));
  }

  /**
   * Test {@link MySQLPlanNodeJSON#isPropertySet(String)}.
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MySQLPlanNodeJSON.isPropertySet(String)"})
  public void testIsPropertySet() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertFalse(mySQLPlanNodeJSON.isPropertySet("42"));
  }

  /**
   * Test {@link MySQLPlanNodeJSON#isPropertyResettable(String)}.
   *
   * <p>Method under test: {@link MySQLPlanNodeJSON#isPropertyResettable(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean MySQLPlanNodeJSON.isPropertyResettable(String)"})
  public void testIsPropertyResettable() {
    // Arrange
    MySQLPlanNodeJSON parent = new MySQLPlanNodeJSON(null, "Name", new JsonObject());
    MySQLPlanNodeJSON mySQLPlanNodeJSON = new MySQLPlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertFalse(mySQLPlanNodeJSON.isPropertyResettable("42"));
  }
}
