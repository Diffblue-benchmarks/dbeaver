package org.jkiss.dbeaver.ext.ocient.model.plan;

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

public class OcientPlanNodeJsonDiffblueTest {
  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, Map)}.
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, Map)"})
  public void testNewOcientPlanNodeJson() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Assert
    Collection<OcientPlanNodeJson> nested = actualOcientPlanNodeJson.getNested();
    assertTrue(nested instanceof List);
    assertNull(actualOcientPlanNodeJson.getNodeDuration());
    assertNull(actualOcientPlanNodeJson.getNodePercent());
    assertNull(actualOcientPlanNodeJson.getNodeName());
    assertNull(actualOcientPlanNodeJson.getNodeType());
    assertNull(actualOcientPlanNodeJson.getNodeCondition());
    assertNull(actualOcientPlanNodeJson.getNodeDescription());
    assertEquals(0, actualOcientPlanNodeJson.getProperties().length);
    assertEquals(0.0d, actualOcientPlanNodeJson.getNodeCost().doubleValue(), 0.0);
    assertEquals(0L, actualOcientPlanNodeJson.getNodeRowCount().longValue());
    assertEquals(DBCPlanNodeKind.DEFAULT, actualOcientPlanNodeJson.getNodeKind());
    assertTrue(nested.isEmpty());
    assertTrue(actualOcientPlanNodeJson.getNodeProps().isEmpty());
    assertSame(parent, actualOcientPlanNodeJson.getParent());
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then return NodeProps {@code 42} is {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_givenFalse_thenReturnNodeProps42IsFalseToString() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("42", false);
    object.add("Property", new JsonArray(3));

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("42", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("42", dbpPropertyDescriptor.getDisplayName());
    assertEquals("42", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals(1, properties.length);
    assertEquals(Boolean.FALSE.toString(), nodeProps.get("42"));
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Given {@code type}.
   *   <li>Then return NodeProps {@code type} is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_givenType_thenReturnNodePropsTypeIs42() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("type", "42");
    object.addProperty("42", Integer.valueOf(1));
    object.add("Property", new JsonArray(3));

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(2, nodeProps.size());
    assertEquals("42", nodeProps.get("type"));
    assertEquals("42", actualOcientPlanNodeJson.getNodeType());
    assertEquals("type", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("type", dbpPropertyDescriptor.getDisplayName());
    assertEquals("type", dbpPropertyDescriptor.getId());
    assertEquals(2, properties.length);
    assertTrue(nodeProps.containsKey("42"));
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return Nested Empty.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNestedEmpty() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.add("Property", new JsonArray(3));

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    Collection<OcientPlanNodeJson> nested = actualOcientPlanNodeJson.getNested();
    assertTrue(nested instanceof List);
    assertEquals(0, actualOcientPlanNodeJson.getProperties().length);
    assertTrue(nested.isEmpty());
    assertTrue(actualOcientPlanNodeJson.getNodeProps().isEmpty());
    assertEquals(nested, actualOcientPlanNodeJson.getParent().getNested());
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return Nested first NodeType is {@code Property}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNestedFirstNodeTypeIsProperty() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.add("Property", new JsonObject());

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    Collection<OcientPlanNodeJson> nested = actualOcientPlanNodeJson.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    Collection<OcientPlanNodeJson> nested2 = actualOcientPlanNodeJson.getParent().getNested();
    assertTrue(nested2 instanceof List);
    OcientPlanNodeJson getResult = ((List<OcientPlanNodeJson>) nested).get(0);
    assertEquals("Property", getResult.getNodeType());
    assertNull(getResult.getNodeDuration());
    assertNull(getResult.getNodePercent());
    assertNull(getResult.getNodeName());
    assertNull(getResult.getNodeCondition());
    assertNull(getResult.getNodeDescription());
    assertEquals(0, getResult.getProperties().length);
    assertEquals(0.0d, getResult.getNodeCost().doubleValue(), 0.0);
    assertEquals(0L, getResult.getNodeRowCount().longValue());
    assertEquals(DBCPlanNodeKind.DEFAULT, getResult.getNodeKind());
    assertTrue(nested2.isEmpty());
    assertTrue(getResult.getNodeProps().isEmpty());
    assertEquals(nested2, getResult.getNested());
    assertSame(actualOcientPlanNodeJson, getResult.getParent());
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return Nested first NodeType is {@code Property#1}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNestedFirstNodeTypeIsProperty1() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(new JsonObject());
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    Collection<OcientPlanNodeJson> nested = actualOcientPlanNodeJson.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    Collection<OcientPlanNodeJson> nested2 = actualOcientPlanNodeJson.getParent().getNested();
    assertTrue(nested2 instanceof List);
    OcientPlanNodeJson getResult = ((List<OcientPlanNodeJson>) nested).get(0);
    assertEquals("Property#1", getResult.getNodeType());
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[{},true]", nodeProps.get("Property"));
    assertNull(getResult.getNodeDuration());
    assertNull(getResult.getNodePercent());
    assertNull(getResult.getNodeName());
    assertNull(getResult.getNodeCondition());
    assertNull(getResult.getNodeDescription());
    assertEquals(0, getResult.getProperties().length);
    assertEquals(0.0d, getResult.getNodeCost().doubleValue(), 0.0);
    assertEquals(0L, getResult.getNodeRowCount().longValue());
    assertEquals(DBCPlanNodeKind.DEFAULT, getResult.getNodeKind());
    assertTrue(nested2.isEmpty());
    assertTrue(getResult.getNodeProps().isEmpty());
    assertEquals(nested2, getResult.getNested());
    assertSame(actualOcientPlanNodeJson, getResult.getParent());
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code 42} is {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNodeProps42Is1() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("42", Integer.valueOf(1));
    object.add("Property", new JsonArray(3));

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("1", nodeProps.get("42"));
    assertEquals("42", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("42", dbpPropertyDescriptor.getDisplayName());
    assertEquals("42", dbpPropertyDescriptor.getId());
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code 42} is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNodeProps42IsValue() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("42", "Value");
    object.add("Property", new JsonArray(3));

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("42", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("42", dbpPropertyDescriptor.getDisplayName());
    assertEquals("42", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("Value", nodeProps.get("42"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code indexMemUsage} is {@code 1.0E-9 GB}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNodePropsIndexMemUsageIs10e9Gb() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("indexMemUsage", Integer.valueOf(1));
    object.add("Property", new JsonArray(3));

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("1.0E-9 GB", nodeProps.get("indexMemUsage"));
    assertEquals("indexMemUsage", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("indexMemUsage", dbpPropertyDescriptor.getDisplayName());
    assertEquals("indexMemUsage", dbpPropertyDescriptor.getId());
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [1,true]}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNodePropsPropertyIs1True() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(Integer.valueOf(1));
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[1,true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code ["A",true]}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNodePropsPropertyIsATrue() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add('A');
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[\"A\",true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [false,true]}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNodePropsPropertyIsFalseTrue() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(false);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[false,true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [null,true]}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNodePropsPropertyIsNullTrue() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add((JsonElement) null);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[null,true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [true]}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNodePropsPropertyIsTrue() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [[],true]}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNodePropsPropertyIsTrue2() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(new JsonArray(3));
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[[],true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code ["\u0001"]}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNodePropsPropertyIsU0001() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add('\u0001');

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[\"\\u0001\"]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code queryMemUsage} is {@code 1.0E-9 GB}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNodePropsQueryMemUsageIs10e9Gb() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("queryMemUsage", Integer.valueOf(1));
    object.add("Property", new JsonArray(3));

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("1.0E-9 GB", nodeProps.get("queryMemUsage"));
    assertEquals("queryMemUsage", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("queryMemUsage", dbpPropertyDescriptor.getDisplayName());
    assertEquals("queryMemUsage", dbpPropertyDescriptor.getId());
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code totalCost} is {@code 0.001 seconds}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_thenReturnNodePropsTotalCostIs0001Seconds() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("totalCost", Integer.valueOf(1));
    object.addProperty("42", "Value");
    object.add("Property", new JsonArray(3));

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOcientPlanNodeJson.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    Map<String, String> nodeProps = actualOcientPlanNodeJson.getNodeProps();
    assertEquals(2, nodeProps.size());
    assertEquals("0.001 seconds", nodeProps.get("totalCost"));
    assertEquals("totalCost", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("totalCost", dbpPropertyDescriptor.getDisplayName());
    assertEquals("totalCost", dbpPropertyDescriptor.getId());
    assertEquals(2, properties.length);
    assertTrue(nodeProps.containsKey("42"));
  }

  /**
   * Test {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String, JsonObject)}.
   *
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then return Nested Empty.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   * JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OcientPlanNodeJson.<init>(OcientPlanNodeJson, String, JsonObject)"})
  public void testNewOcientPlanNodeJson_whenJsonObject_thenReturnNestedEmpty() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    // Act
    OcientPlanNodeJson actualOcientPlanNodeJson =
        new OcientPlanNodeJson(parent, "Name", new JsonObject());

    // Assert
    Collection<OcientPlanNodeJson> nested = actualOcientPlanNodeJson.getNested();
    assertTrue(nested instanceof List);
    assertEquals(0, actualOcientPlanNodeJson.getProperties().length);
    assertTrue(nested.isEmpty());
    assertTrue(actualOcientPlanNodeJson.getNodeProps().isEmpty());
    assertEquals(nested, actualOcientPlanNodeJson.getParent().getNested());
  }

  /**
   * Test {@link OcientPlanNodeJson#getNodeName()}.
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getNodeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.getNodeName()"})
  public void testGetNodeName() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Act and Assert
    assertNull(ocientPlanNodeJson.getNodeName());
  }

  /**
   * Test {@link OcientPlanNodeJson#getNodeCost()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code cost} is {@code 42}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getNodeCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number OcientPlanNodeJson.getNodeCost()"})
  public void testGetNodeCost_givenHashMapCostIs42_thenReturnDoubleValueIsFortyTwo() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("cost", "42");
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, attributes);

    // Act and Assert
    assertEquals(42.0d, ocientPlanNodeJson.getNodeCost().doubleValue(), 0.0);
  }

  /**
   * Test {@link OcientPlanNodeJson#getNodeCost()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code cost} is {@code cost}.
   *   <li>Then return doubleValue is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getNodeCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number OcientPlanNodeJson.getNodeCost()"})
  public void testGetNodeCost_givenHashMapCostIsCost_thenReturnDoubleValueIsNaN() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("cost", "cost");
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, attributes);

    // Act and Assert
    assertEquals(Double.NaN, ocientPlanNodeJson.getNodeCost().doubleValue(), 0.0);
  }

  /**
   * Test {@link OcientPlanNodeJson#getNodeCost()}.
   *
   * <ul>
   *   <li>Then return doubleValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getNodeCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number OcientPlanNodeJson.getNodeCost()"})
  public void testGetNodeCost_thenReturnDoubleValueIsZero() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Act and Assert
    assertEquals(0.0d, ocientPlanNodeJson.getNodeCost().doubleValue(), 0.0);
  }

  /**
   * Test {@link OcientPlanNodeJson#getNodeRowCount()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code outputCardinality} is {@code outputCardinality}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getNodeRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number OcientPlanNodeJson.getNodeRowCount()"})
  public void testGetNodeRowCount_givenHashMapOutputCardinalityIsOutputCardinality() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("outputCardinality", "outputCardinality");
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, attributes);

    // Act and Assert
    assertEquals(0L, ocientPlanNodeJson.getNodeRowCount().longValue());
  }

  /**
   * Test {@link OcientPlanNodeJson#getNodeRowCount()}.
   *
   * <ul>
   *   <li>Then return longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getNodeRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number OcientPlanNodeJson.getNodeRowCount()"})
  public void testGetNodeRowCount_thenReturnLongValueIsFortyTwo() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("outputCardinality", "42");
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, attributes);

    // Act and Assert
    assertEquals(42L, ocientPlanNodeJson.getNodeRowCount().longValue());
  }

  /**
   * Test {@link OcientPlanNodeJson#getNodeRowCount()}.
   *
   * <ul>
   *   <li>Then return longValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getNodeRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number OcientPlanNodeJson.getNodeRowCount()"})
  public void testGetNodeRowCount_thenReturnLongValueIsZero() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Act and Assert
    assertEquals(0L, ocientPlanNodeJson.getNodeRowCount().longValue());
  }

  /**
   * Test {@link OcientPlanNodeJson#getParent()}.
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getParent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OcientPlanNodeJson OcientPlanNodeJson.getParent()"})
  public void testGetParent() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Act and Assert
    assertSame(parent, ocientPlanNodeJson.getParent());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OcientPlanNodeJson#resetPropertyValue(DBRProgressMonitor, String)}
   *   <li>{@link OcientPlanNodeJson#resetPropertyValueToDefault(String)}
   *   <li>{@link OcientPlanNodeJson#setPropertyValue(DBRProgressMonitor, String, Object)}
   *   <li>{@link OcientPlanNodeJson#getNested()}
   *   <li>{@link OcientPlanNodeJson#getNodeDuration()}
   *   <li>{@link OcientPlanNodeJson#getNodePercent()}
   *   <li>{@link OcientPlanNodeJson#getNodeProps()}
   *   <li>{@link OcientPlanNodeJson#getNodeType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection OcientPlanNodeJson.getNested()",
    "Number OcientPlanNodeJson.getNodeDuration()",
    "Number OcientPlanNodeJson.getNodePercent()",
    "Map OcientPlanNodeJson.getNodeProps()",
    "String OcientPlanNodeJson.getNodeType()",
    "void OcientPlanNodeJson.resetPropertyValue(DBRProgressMonitor, String)",
    "void OcientPlanNodeJson.resetPropertyValueToDefault(String)",
    "void OcientPlanNodeJson.setPropertyValue(DBRProgressMonitor, String, Object)"
  })
  public void testGettersAndSetters() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Act
    ocientPlanNodeJson.resetPropertyValue(new LoggingProgressMonitor(), "42");
    ocientPlanNodeJson.resetPropertyValueToDefault("42");
    ocientPlanNodeJson.setPropertyValue(new LoggingProgressMonitor(), "42", "Value");
    Collection<OcientPlanNodeJson> actualNested = ocientPlanNodeJson.getNested();
    Number actualNodeDuration = ocientPlanNodeJson.getNodeDuration();
    Number actualNodePercent = ocientPlanNodeJson.getNodePercent();
    Map<String, String> actualNodeProps = ocientPlanNodeJson.getNodeProps();

    // Assert
    assertTrue(actualNested instanceof List);
    assertNull(actualNodeDuration);
    assertNull(actualNodePercent);
    assertNull(ocientPlanNodeJson.getNodeType());
    assertTrue(actualNodeProps.isEmpty());
  }

  /**
   * Test {@link OcientPlanNodeJson#getProperty(String)}.
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OcientPlanNodeJson.getProperty(String)"})
  public void testGetProperty() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Act and Assert
    assertNull(ocientPlanNodeJson.getProperty("Name"));
  }

  /**
   * Test {@link OcientPlanNodeJson#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code null}.
   *   <li>Then return {@code {"Property":[null]}}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddNull_thenReturnPropertyNull() {
    // Arrange
    JsonArray value = new JsonArray(3);
    value.add((Boolean) null);

    JsonObject object = new JsonObject();
    object.add("Property", value);
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":[null]}", ocientPlanNodeJson.toString());
  }

  /**
   * Test {@link OcientPlanNodeJson#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   *   <li>Then return {@code {"Property":[true]}}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddTrue_thenReturnPropertyTrue() {
    // Arrange
    JsonArray value = new JsonArray(3);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":[true]}", ocientPlanNodeJson.toString());
  }

  /**
   * Test {@link OcientPlanNodeJson#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and {@code false}.
   *   <li>Then return {@code {"42":false,"Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.toString()"})
  public void testToString_givenJsonObjectAddProperty42AndFalse_thenReturn42FalseProperty() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("42", false);
    object.add("Property", new JsonArray(3));
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":false,\"Property\":[]}", ocientPlanNodeJson.toString());
  }

  /**
   * Test {@link OcientPlanNodeJson#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and null.
   *   <li>Then return {@code {"42":"\u0000","Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.toString()"})
  public void testToString_givenJsonObjectAddProperty42AndNull_thenReturn42U0000Property() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("42", '\u0000');
    object.add("Property", new JsonArray(3));
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":\"\\u0000\",\"Property\":[]}", ocientPlanNodeJson.toString());
  }

  /**
   * Test {@link OcientPlanNodeJson#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and valueOf one.
   *   <li>Then return {@code {"42":1,"Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.toString()"})
  public void testToString_givenJsonObjectAddProperty42AndValueOfOne_thenReturn421Property() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("42", Integer.valueOf(1));
    object.add("Property", new JsonArray(3));
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":1,\"Property\":[]}", ocientPlanNodeJson.toString());
  }

  /**
   * Test {@link OcientPlanNodeJson#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and {@code Value}.
   *   <li>Then return {@code {"42":"Value","Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.toString()"})
  public void testToString_givenJsonObjectAddProperty42AndValue_thenReturn42ValueProperty() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("42", "Value");
    object.add("Property", new JsonArray(3));
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":\"Value\",\"Property\":[]}", ocientPlanNodeJson.toString());
  }

  /**
   * Test {@link OcientPlanNodeJson#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code Property} and {@code
   *       true}.
   *   <li>Then return {@code {"Property":true}}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.toString()"})
  public void testToString_givenJsonObjectAddPropertyPropertyAndTrue_thenReturnPropertyTrue() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("Property", true);
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":true}", ocientPlanNodeJson.toString());
  }

  /**
   * Test {@link OcientPlanNodeJson#toString()}.
   *
   * <ul>
   *   <li>Given {@link OcientPlanNodeJson#OcientPlanNodeJson(OcientPlanNodeJson, String,
   *       JsonObject)} with parent is {@code null} and {@code Name} and object is {@link
   *       JsonObject} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.toString()"})
  public void testToString_givenOcientPlanNodeJsonWithParentIsNullAndNameAndObjectIsJsonObject() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Act and Assert
    assertEquals("{}", ocientPlanNodeJson.toString());
  }

  /**
   * Test {@link OcientPlanNodeJson#toString()}.
   *
   * <ul>
   *   <li>Then return {@code {"42":[],"Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.toString()"})
  public void testToString_thenReturn42Property() {
    // Arrange
    JsonObject object = new JsonObject();
    object.add("42", new JsonArray(3));
    object.add("Property", new JsonArray(3));
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":[],\"Property\":[]}", ocientPlanNodeJson.toString());
  }

  /**
   * Test {@link OcientPlanNodeJson#toString()}.
   *
   * <ul>
   *   <li>Then return {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.toString()"})
  public void testToString_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());
    OcientPlanNodeJson ocientPlanNodeJson =
        new OcientPlanNodeJson(parent, "Name", new JsonObject());

    // Act and Assert
    assertEquals("{}", ocientPlanNodeJson.toString());
  }

  /**
   * Test {@link OcientPlanNodeJson#toString()}.
   *
   * <ul>
   *   <li>Then return {@code {"Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.toString()"})
  public void testToString_thenReturnProperty() {
    // Arrange
    JsonObject object = new JsonObject();
    object.add("Property", new JsonArray(3));
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":[]}", ocientPlanNodeJson.toString());
  }

  /**
   * Test {@link OcientPlanNodeJson#toString()}.
   *
   * <ul>
   *   <li>Then return {@code {"Property":[false,true]}}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OcientPlanNodeJson.toString()"})
  public void testToString_thenReturnPropertyFalseTrue() {
    // Arrange
    JsonArray value = new JsonArray(3);
    value.add(false);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, new HashMap<>());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":[false,true]}", ocientPlanNodeJson.toString());
  }

  /**
   * Test {@link OcientPlanNodeJson#getEditableValue()}.
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getEditableValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OcientPlanNodeJson.getEditableValue()"})
  public void testGetEditableValue() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Act
    Object actualEditableValue = ocientPlanNodeJson.getEditableValue();

    // Assert
    assertSame(ocientPlanNodeJson, actualEditableValue);
  }

  /**
   * Test {@link OcientPlanNodeJson#getProperties()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code foo} is {@code foo}.
   *   <li>Then first element return {@link PropertyDescriptor}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor[] OcientPlanNodeJson.getProperties()"})
  public void testGetProperties_givenHashMapFooIsFoo_thenFirstElementReturnPropertyDescriptor() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("foo", "foo");
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, attributes);

    // Act
    DBPPropertyDescriptor[] actualProperties = ocientPlanNodeJson.getProperties();

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
   * Test {@link OcientPlanNodeJson#getProperties()}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor[] OcientPlanNodeJson.getProperties()"})
  public void testGetProperties_thenReturnArrayLengthIsZero() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Act and Assert
    assertEquals(0, ocientPlanNodeJson.getProperties().length);
  }

  /**
   * Test {@link OcientPlanNodeJson#getPropertyValue(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link OcientPlanNodeJson#getPropertyValue(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OcientPlanNodeJson.getPropertyValue(DBRProgressMonitor, String)"})
  public void testGetPropertyValue() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Act and Assert
    assertNull(ocientPlanNodeJson.getPropertyValue(new LoggingProgressMonitor(), "42"));
  }

  /**
   * Test {@link OcientPlanNodeJson#isPropertySet(String)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code 42} is {@code foo}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OcientPlanNodeJson.isPropertySet(String)"})
  public void testIsPropertySet_givenHashMap42IsFoo_thenReturnTrue() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("42", "foo");
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());

    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, attributes);

    // Act and Assert
    assertTrue(ocientPlanNodeJson.isPropertySet("42"));
  }

  /**
   * Test {@link OcientPlanNodeJson#isPropertySet(String)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OcientPlanNodeJson#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OcientPlanNodeJson.isPropertySet(String)"})
  public void testIsPropertySet_thenReturnFalse() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Act and Assert
    assertFalse(ocientPlanNodeJson.isPropertySet("42"));
  }

  /**
   * Test {@link OcientPlanNodeJson#isPropertyResettable(String)}.
   *
   * <p>Method under test: {@link OcientPlanNodeJson#isPropertyResettable(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OcientPlanNodeJson.isPropertyResettable(String)"})
  public void testIsPropertyResettable() {
    // Arrange
    OcientPlanNodeJson parent = new OcientPlanNodeJson(null, "Name", new JsonObject());
    OcientPlanNodeJson ocientPlanNodeJson = new OcientPlanNodeJson(parent, new HashMap<>());

    // Act and Assert
    assertFalse(ocientPlanNodeJson.isPropertyResettable("42"));
  }
}
