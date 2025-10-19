package org.jkiss.dbeaver.ext.oceanbase.model.plan;

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

public class OceanbasePlanNodeJSONDiffblueTest {
  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, Map)}.
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, Map)"})
  public void testNewOceanbasePlanNodeJSON() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Assert
    Collection<OceanbasePlanNodeJSON> nested = actualOceanbasePlanNodeJSON.getNested();
    assertTrue(nested instanceof List);
    assertNull(actualOceanbasePlanNodeJSON.getNodeDuration());
    assertNull(actualOceanbasePlanNodeJSON.getNodePercent());
    assertNull(actualOceanbasePlanNodeJSON.getNodeName());
    assertNull(actualOceanbasePlanNodeJSON.getNodeType());
    assertNull(actualOceanbasePlanNodeJSON.getNodeCondition());
    assertNull(actualOceanbasePlanNodeJSON.getNodeDescription());
    assertEquals(0, actualOceanbasePlanNodeJSON.getProperties().length);
    assertEquals(0L, actualOceanbasePlanNodeJSON.getNodeCost().longValue());
    assertEquals(0L, actualOceanbasePlanNodeJSON.getNodeRowCount().longValue());
    assertEquals(DBCPlanNodeKind.DEFAULT, actualOceanbasePlanNodeJSON.getNodeKind());
    assertTrue(nested.isEmpty());
    assertTrue(actualOceanbasePlanNodeJSON.getNodeProps().isEmpty());
    assertSame(parent, actualOceanbasePlanNodeJSON.getParent());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Given {@code false}.
   *   <li>Then return NodeProps {@code 42} is {@link Boolean#FALSE} toString.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_givenFalse_thenReturnNodeProps42IsFalseToString() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("42", false);
    object.add("Property", new JsonArray(3));

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOceanbasePlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("42", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("42", dbpPropertyDescriptor.getDisplayName());
    assertEquals("42", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals(1, properties.length);
    assertEquals(Boolean.FALSE.toString(), nodeProps.get("42"));
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Given valueOf one.
   *   <li>Then return NodeProps {@code 42} is {@code 1}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_givenValueOfOne_thenReturnNodeProps42Is1() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("42", Integer.valueOf(1));
    object.add("Property", new JsonArray(3));

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOceanbasePlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("1", nodeProps.get("42"));
    assertEquals("42", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("42", dbpPropertyDescriptor.getDisplayName());
    assertEquals("42", dbpPropertyDescriptor.getId());
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Given {@code Value}.
   *   <li>Then return NodeProps {@code 42} is {@code Value}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_givenValue_thenReturnNodeProps42IsValue() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.addProperty("42", "Value");
    object.add("Property", new JsonArray(3));

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOceanbasePlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("42", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("42", dbpPropertyDescriptor.getDisplayName());
    assertEquals("42", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("Value", nodeProps.get("42"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Then return Nested Empty.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_thenReturnNestedEmpty() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.add("Property", new JsonArray(3));

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    Collection<OceanbasePlanNodeJSON> nested = actualOceanbasePlanNodeJSON.getNested();
    assertTrue(nested instanceof List);
    assertEquals(0, actualOceanbasePlanNodeJSON.getProperties().length);
    assertTrue(nested.isEmpty());
    assertTrue(actualOceanbasePlanNodeJSON.getNodeProps().isEmpty());
    assertEquals(nested, actualOceanbasePlanNodeJSON.getParent().getNested());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Then return Nested first NodeType is {@code Property}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_thenReturnNestedFirstNodeTypeIsProperty() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonObject object = new JsonObject();
    object.add("Property", new JsonObject());

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    Collection<OceanbasePlanNodeJSON> nested = actualOceanbasePlanNodeJSON.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    Collection<OceanbasePlanNodeJSON> nested2 = actualOceanbasePlanNodeJSON.getParent().getNested();
    assertTrue(nested2 instanceof List);
    OceanbasePlanNodeJSON getResult = ((List<OceanbasePlanNodeJSON>) nested).get(0);
    assertEquals("Property", getResult.getNodeType());
    assertNull(getResult.getNodeDuration());
    assertNull(getResult.getNodePercent());
    assertNull(getResult.getNodeName());
    assertNull(getResult.getNodeCondition());
    assertNull(getResult.getNodeDescription());
    assertEquals(0, actualOceanbasePlanNodeJSON.getProperties().length);
    assertEquals(0, getResult.getProperties().length);
    assertEquals(0L, getResult.getNodeCost().longValue());
    assertEquals(0L, getResult.getNodeRowCount().longValue());
    assertEquals(DBCPlanNodeKind.DEFAULT, getResult.getNodeKind());
    assertTrue(nested2.isEmpty());
    assertTrue(actualOceanbasePlanNodeJSON.getNodeProps().isEmpty());
    assertTrue(getResult.getNodeProps().isEmpty());
    assertEquals(nested2, getResult.getNested());
    assertSame(actualOceanbasePlanNodeJSON, getResult.getParent());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [1,true]}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_thenReturnNodePropsPropertyIs1True() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(Integer.valueOf(1));
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOceanbasePlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[1,true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [{"42":[]},true]}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_thenReturnNodePropsPropertyIs42True() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonObject element = new JsonObject();
    element.add("42", new JsonArray(3));

    JsonArray value = new JsonArray(3);
    value.add(element);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    Collection<OceanbasePlanNodeJSON> nested = actualOceanbasePlanNodeJSON.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    Collection<OceanbasePlanNodeJSON> nested2 = actualOceanbasePlanNodeJSON.getParent().getNested();
    assertTrue(nested2 instanceof List);
    OceanbasePlanNodeJSON getResult = ((List<OceanbasePlanNodeJSON>) nested).get(0);
    assertEquals("Property#1", getResult.getNodeType());
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
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
    assertSame(actualOceanbasePlanNodeJSON, getResult.getParent());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [{"":[],"42":[]},true]}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_thenReturnNodePropsPropertyIs42True2() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonObject element = new JsonObject();
    element.add("", new JsonArray(3));
    element.add("42", new JsonArray(3));

    JsonArray value = new JsonArray(3);
    value.add(element);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    Collection<OceanbasePlanNodeJSON> nested = actualOceanbasePlanNodeJSON.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    Collection<OceanbasePlanNodeJSON> nested2 = actualOceanbasePlanNodeJSON.getParent().getNested();
    assertTrue(nested2 instanceof List);
    OceanbasePlanNodeJSON getResult = ((List<OceanbasePlanNodeJSON>) nested).get(0);
    assertEquals("Property#1", getResult.getNodeType());
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
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
    assertSame(actualOceanbasePlanNodeJSON, getResult.getParent());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code ["A",true]}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_thenReturnNodePropsPropertyIsATrue() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add('A');
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOceanbasePlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[\"A\",true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [false,true]}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_thenReturnNodePropsPropertyIsFalseTrue() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(false);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOceanbasePlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[false,true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [null,true]}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_thenReturnNodePropsPropertyIsNullTrue() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add((JsonElement) null);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOceanbasePlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[null,true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [true]}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_thenReturnNodePropsPropertyIsTrue() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOceanbasePlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [[],true]}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_thenReturnNodePropsPropertyIsTrue2() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(new JsonArray(3));
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOceanbasePlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[[],true]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code [{},true]}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_thenReturnNodePropsPropertyIsTrue3() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add(new JsonObject());
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    Collection<OceanbasePlanNodeJSON> nested = actualOceanbasePlanNodeJSON.getNested();
    assertEquals(1, nested.size());
    assertTrue(nested instanceof List);
    Collection<OceanbasePlanNodeJSON> nested2 = actualOceanbasePlanNodeJSON.getParent().getNested();
    assertTrue(nested2 instanceof List);
    OceanbasePlanNodeJSON getResult = ((List<OceanbasePlanNodeJSON>) nested).get(0);
    assertEquals("Property#1", getResult.getNodeType());
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
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
    assertSame(actualOceanbasePlanNodeJSON, getResult.getParent());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>Then return NodeProps {@code Property} is {@code ["\u0001"]}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_thenReturnNodePropsPropertyIsU0001() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    JsonArray value = new JsonArray(3);
    value.add('\u0001');

    JsonObject object = new JsonObject();
    object.add("Property", value);

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", object);

    // Assert
    DBPPropertyDescriptor[] properties = actualOceanbasePlanNodeJSON.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Property", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Property", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Property", dbpPropertyDescriptor.getId());
    Map<String, String> nodeProps = actualOceanbasePlanNodeJSON.getNodeProps();
    assertEquals(1, nodeProps.size());
    assertEquals("[\"\\u0001\"]", nodeProps.get("Property"));
    assertEquals(1, properties.length);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON, String,
   * JsonObject)}.
   *
   * <ul>
   *   <li>When {@link JsonObject} (default constructor).
   *   <li>Then return Nested Empty.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#OceanbasePlanNodeJSON(OceanbasePlanNodeJSON,
   * String, JsonObject)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void OceanbasePlanNodeJSON.<init>(OceanbasePlanNodeJSON, String, JsonObject)"
  })
  public void testNewOceanbasePlanNodeJSON_whenJsonObject_thenReturnNestedEmpty() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    // Act
    OceanbasePlanNodeJSON actualOceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", new JsonObject());

    // Assert
    Collection<OceanbasePlanNodeJSON> nested = actualOceanbasePlanNodeJSON.getNested();
    assertTrue(nested instanceof List);
    assertEquals(0, actualOceanbasePlanNodeJSON.getProperties().length);
    assertTrue(nested.isEmpty());
    assertTrue(actualOceanbasePlanNodeJSON.getNodeProps().isEmpty());
    assertEquals(nested, actualOceanbasePlanNodeJSON.getParent().getNested());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getNodeProps()}.
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getNodeProps()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map OceanbasePlanNodeJSON.getNodeProps()"})
  public void testGetNodeProps() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertTrue(oceanbasePlanNodeJSON.getNodeProps().isEmpty());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getProperty(String)}.
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OceanbasePlanNodeJSON.getProperty(String)"})
  public void testGetProperty() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertNull(oceanbasePlanNodeJSON.getProperty("Name"));
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getNodeName()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code access_type} is {@code access_type}.
   *   <li>Then return {@code table_name (access_type)}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getNodeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.getNodeName()"})
  public void testGetNodeName_givenHashMapAccessTypeIsAccessType_thenReturnTableNameAccessType() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("access_type", "access_type");
    attributes.put("table_name", "table_name");
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals("table_name (access_type)", oceanbasePlanNodeJSON.getNodeName());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getNodeName()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code table_name} is {@code table_name}.
   *   <li>Then return {@code table_name}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getNodeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.getNodeName()"})
  public void testGetNodeName_givenHashMapTableNameIsTableName_thenReturnTableName() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("table_name", "table_name");
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals("table_name", oceanbasePlanNodeJSON.getNodeName());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getNodeName()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getNodeName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.getNodeName()"})
  public void testGetNodeName_thenReturnNull() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertNull(oceanbasePlanNodeJSON.getNodeName());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getNodeCost()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code COST} is {@code 42}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getNodeCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number OceanbasePlanNodeJSON.getNodeCost()"})
  public void testGetNodeCost_givenHashMapCostIs42_thenReturnDoubleValueIsFortyTwo() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("COST", "42");
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals(42.0d, oceanbasePlanNodeJSON.getNodeCost().doubleValue(), 0.0);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getNodeCost()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code COST} is {@code COST}.
   *   <li>Then return doubleValue is {@link Double#NaN}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getNodeCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number OceanbasePlanNodeJSON.getNodeCost()"})
  public void testGetNodeCost_givenHashMapCostIsCost_thenReturnDoubleValueIsNaN() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("COST", "COST");
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals(Double.NaN, oceanbasePlanNodeJSON.getNodeCost().doubleValue(), 0.0);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getNodeCost()}.
   *
   * <ul>
   *   <li>Then return longValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getNodeCost()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number OceanbasePlanNodeJSON.getNodeCost()"})
  public void testGetNodeCost_thenReturnLongValueIsZero() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertEquals(0L, oceanbasePlanNodeJSON.getNodeCost().longValue());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getNodeRowCount()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code EST.ROWS} is {@code 42}.
   *   <li>Then return longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getNodeRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number OceanbasePlanNodeJSON.getNodeRowCount()"})
  public void testGetNodeRowCount_givenHashMapEstRowsIs42_thenReturnLongValueIsFortyTwo() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("EST.ROWS", "42");
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals(42L, oceanbasePlanNodeJSON.getNodeRowCount().longValue());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getNodeRowCount()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code EST.ROWS} is {@code EST.ROWS}.
   *   <li>Then return longValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getNodeRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number OceanbasePlanNodeJSON.getNodeRowCount()"})
  public void testGetNodeRowCount_givenHashMapEstRowsIsEstRows_thenReturnLongValueIsZero() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("EST.ROWS", "EST.ROWS");
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, attributes);

    // Act and Assert
    assertEquals(0L, oceanbasePlanNodeJSON.getNodeRowCount().longValue());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getNodeRowCount()}.
   *
   * <ul>
   *   <li>Then return longValue is zero.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getNodeRowCount()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Number OceanbasePlanNodeJSON.getNodeRowCount()"})
  public void testGetNodeRowCount_thenReturnLongValueIsZero() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertEquals(0L, oceanbasePlanNodeJSON.getNodeRowCount().longValue());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getParent()}.
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getParent()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OceanbasePlanNodeJSON OceanbasePlanNodeJSON.getParent()"})
  public void testGetParent() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertSame(parent, oceanbasePlanNodeJSON.getParent());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OceanbasePlanNodeJSON#resetPropertyValue(DBRProgressMonitor, String)}
   *   <li>{@link OceanbasePlanNodeJSON#resetPropertyValueToDefault(String)}
   *   <li>{@link OceanbasePlanNodeJSON#setPropertyValue(DBRProgressMonitor, String, Object)}
   *   <li>{@link OceanbasePlanNodeJSON#getNested()}
   *   <li>{@link OceanbasePlanNodeJSON#getNodeDuration()}
   *   <li>{@link OceanbasePlanNodeJSON#getNodePercent()}
   *   <li>{@link OceanbasePlanNodeJSON#getNodeType()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Collection OceanbasePlanNodeJSON.getNested()",
    "Number OceanbasePlanNodeJSON.getNodeDuration()",
    "Number OceanbasePlanNodeJSON.getNodePercent()",
    "String OceanbasePlanNodeJSON.getNodeType()",
    "void OceanbasePlanNodeJSON.resetPropertyValue(DBRProgressMonitor, String)",
    "void OceanbasePlanNodeJSON.resetPropertyValueToDefault(String)",
    "void OceanbasePlanNodeJSON.setPropertyValue(DBRProgressMonitor, String, Object)"
  })
  public void testGettersAndSetters() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act
    oceanbasePlanNodeJSON.resetPropertyValue(new LoggingProgressMonitor(), "42");
    oceanbasePlanNodeJSON.resetPropertyValueToDefault("42");
    oceanbasePlanNodeJSON.setPropertyValue(new LoggingProgressMonitor(), "42", "Value");
    Collection<OceanbasePlanNodeJSON> actualNested = oceanbasePlanNodeJSON.getNested();
    Number actualNodeDuration = oceanbasePlanNodeJSON.getNodeDuration();
    Number actualNodePercent = oceanbasePlanNodeJSON.getNodePercent();

    // Assert
    assertTrue(actualNested instanceof List);
    assertNull(actualNodeDuration);
    assertNull(actualNodePercent);
    assertNull(oceanbasePlanNodeJSON.getNodeType());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#toString()}.
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.toString()"})
  public void testToString() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertEquals("{}", oceanbasePlanNodeJSON.toString());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code null}.
   *   <li>Then return {@code {"Property":[null]}}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddNull_thenReturnPropertyNull() {
    // Arrange
    JsonArray value = new JsonArray(3);
    value.add((Boolean) null);

    JsonObject object = new JsonObject();
    object.add("Property", value);
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":[null]}", oceanbasePlanNodeJSON.toString());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonArray#JsonArray(int)} with capacity is three add {@code true}.
   *   <li>Then return {@code {"Property":[true]}}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.toString()"})
  public void testToString_givenJsonArrayWithCapacityIsThreeAddTrue_thenReturnPropertyTrue() {
    // Arrange
    JsonArray value = new JsonArray(3);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":[true]}", oceanbasePlanNodeJSON.toString());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and {@code false}.
   *   <li>Then return {@code {"42":false,"Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.toString()"})
  public void testToString_givenJsonObjectAddProperty42AndFalse_thenReturn42FalseProperty() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("42", false);
    object.add("Property", new JsonArray(3));
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":false,\"Property\":[]}", oceanbasePlanNodeJSON.toString());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and null.
   *   <li>Then return {@code {"42":"\u0000","Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.toString()"})
  public void testToString_givenJsonObjectAddProperty42AndNull_thenReturn42U0000Property() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("42", '\u0000');
    object.add("Property", new JsonArray(3));
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":\"\\u0000\",\"Property\":[]}", oceanbasePlanNodeJSON.toString());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and valueOf one.
   *   <li>Then return {@code {"42":1,"Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.toString()"})
  public void testToString_givenJsonObjectAddProperty42AndValueOfOne_thenReturn421Property() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("42", Integer.valueOf(1));
    object.add("Property", new JsonArray(3));
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":1,\"Property\":[]}", oceanbasePlanNodeJSON.toString());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code 42} and {@code Value}.
   *   <li>Then return {@code {"42":"Value","Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.toString()"})
  public void testToString_givenJsonObjectAddProperty42AndValue_thenReturn42ValueProperty() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("42", "Value");
    object.add("Property", new JsonArray(3));
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":\"Value\",\"Property\":[]}", oceanbasePlanNodeJSON.toString());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Given {@link JsonObject} (default constructor) addProperty {@code Property} and {@code
   *       true}.
   *   <li>Then return {@code {"Property":true}}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.toString()"})
  public void testToString_givenJsonObjectAddPropertyPropertyAndTrue_thenReturnPropertyTrue() {
    // Arrange
    JsonObject object = new JsonObject();
    object.addProperty("Property", true);
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":true}", oceanbasePlanNodeJSON.toString());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Then return {@code {"42":[],"Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.toString()"})
  public void testToString_thenReturn42Property() {
    // Arrange
    JsonObject object = new JsonObject();
    object.add("42", new JsonArray(3));
    object.add("Property", new JsonArray(3));
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"42\":[],\"Property\":[]}", oceanbasePlanNodeJSON.toString());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Then return {@code {}}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.toString()"})
  public void testToString_thenReturnLeftCurlyBracketRightCurlyBracket() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, "Name", new JsonObject());

    // Act and Assert
    assertEquals("{}", oceanbasePlanNodeJSON.toString());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Then return {@code {"Property":[]}}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.toString()"})
  public void testToString_thenReturnProperty() {
    // Arrange
    JsonObject object = new JsonObject();
    object.add("Property", new JsonArray(3));
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":[]}", oceanbasePlanNodeJSON.toString());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#toString()}.
   *
   * <ul>
   *   <li>Then return {@code {"Property":[false,true]}}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#toString()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OceanbasePlanNodeJSON.toString()"})
  public void testToString_thenReturnPropertyFalseTrue() {
    // Arrange
    JsonArray value = new JsonArray(3);
    value.add(false);
    value.add(true);

    JsonObject object = new JsonObject();
    object.add("Property", value);
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, new HashMap<>());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, "Name", object);

    // Act and Assert
    assertEquals("{\"Property\":[false,true]}", oceanbasePlanNodeJSON.toString());
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getEditableValue()}.
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getEditableValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OceanbasePlanNodeJSON.getEditableValue()"})
  public void testGetEditableValue() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act
    Object actualEditableValue = oceanbasePlanNodeJSON.getEditableValue();

    // Assert
    assertSame(oceanbasePlanNodeJSON, actualEditableValue);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getProperties()}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code foo} is {@code foo}.
   *   <li>Then first element return {@link PropertyDescriptor}.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor[] OceanbasePlanNodeJSON.getProperties()"})
  public void testGetProperties_givenHashMapFooIsFoo_thenFirstElementReturnPropertyDescriptor() {
    // Arrange
    HashMap<String, String> attributes = new HashMap<>();
    attributes.put("foo", "foo");
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());

    OceanbasePlanNodeJSON oceanbasePlanNodeJSON = new OceanbasePlanNodeJSON(parent, attributes);

    // Act
    DBPPropertyDescriptor[] actualProperties = oceanbasePlanNodeJSON.getProperties();

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
   * Test {@link OceanbasePlanNodeJSON#getProperties()}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor[] OceanbasePlanNodeJSON.getProperties()"})
  public void testGetProperties_thenReturnArrayLengthIsZero() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertEquals(0, oceanbasePlanNodeJSON.getProperties().length);
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#getPropertyValue(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#getPropertyValue(DBRProgressMonitor,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object OceanbasePlanNodeJSON.getPropertyValue(DBRProgressMonitor, String)"})
  public void testGetPropertyValue() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertNull(oceanbasePlanNodeJSON.getPropertyValue(new LoggingProgressMonitor(), "42"));
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#isPropertySet(String)}.
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OceanbasePlanNodeJSON.isPropertySet(String)"})
  public void testIsPropertySet() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertFalse(oceanbasePlanNodeJSON.isPropertySet("42"));
  }

  /**
   * Test {@link OceanbasePlanNodeJSON#isPropertyResettable(String)}.
   *
   * <p>Method under test: {@link OceanbasePlanNodeJSON#isPropertyResettable(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OceanbasePlanNodeJSON.isPropertyResettable(String)"})
  public void testIsPropertyResettable() {
    // Arrange
    OceanbasePlanNodeJSON parent = new OceanbasePlanNodeJSON(null, "Name", new JsonObject());
    OceanbasePlanNodeJSON oceanbasePlanNodeJSON =
        new OceanbasePlanNodeJSON(parent, new HashMap<>());

    // Act and Assert
    assertFalse(oceanbasePlanNodeJSON.isPropertyResettable("42"));
  }
}
