package org.jkiss.dbeaver.runtime.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyBoolean;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.impl.ProxyPropertyDescriptor;
import org.jkiss.dbeaver.model.meta.PropertyLength;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PropertySourceAbstractDiffblueTest {
  /**
   * Test {@link PropertySourceAbstract#addProperty(String, String, String, Object)} with {@code
   * category}, {@code id}, {@code name}, {@code value}.
   *
   * <ul>
   *   <li>Then first element DataType is {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#addProperty(String, String, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceAbstract.addProperty(String, String, String, Object)"})
  public void testAddPropertyWithCategoryIdNameValue_thenFirstElementDataTypeIsObject() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    // Act
    propertyCollector.addProperty("Category", "42", "Name", DBPEvent.RENAME);

    // Assert
    DBPPropertyDescriptor[] properties = propertyCollector.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("42", dbpPropertyDescriptor.getId());
    assertEquals("Category", dbpPropertyDescriptor.getCategory());
    assertEquals("Name", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Name", dbpPropertyDescriptor.getDisplayName());
    assertNull(dbpPropertyDescriptor.getDefaultValue());
    assertNull(dbpPropertyDescriptor.getDescription());
    assertNull(dbpPropertyDescriptor.getHint());
    assertNull(dbpPropertyDescriptor.getFeatures());
    assertNull(dbpPropertyDescriptor.getRequiredFeatures());
    assertNull(((PropertyDescriptor) dbpPropertyDescriptor).getPropertyType());
    assertEquals(1, properties.length);
    assertEquals(PropertyLength.LONG, dbpPropertyDescriptor.getLength());
    assertFalse(dbpPropertyDescriptor.isRequired());
    assertFalse(propertyCollector.isEmpty());
    Class<Object> expectedDataType = Object.class;
    assertEquals(expectedDataType, dbpPropertyDescriptor.getDataType());
  }

  /**
   * Test {@link PropertySourceAbstract#addProperty(String, String, String, Object)} with {@code
   * category}, {@code id}, {@code name}, {@code value}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then first element DataType is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#addProperty(String, String, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceAbstract.addProperty(String, String, String, Object)"})
  public void testAddPropertyWithCategoryIdNameValue_whenNull_thenFirstElementDataTypeIsNull() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    // Act
    propertyCollector.addProperty("Category", "42", "Name", null);

    // Assert
    DBPPropertyDescriptor[] properties = propertyCollector.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("42", dbpPropertyDescriptor.getId());
    assertEquals("Category", dbpPropertyDescriptor.getCategory());
    assertEquals("Name", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Name", dbpPropertyDescriptor.getDisplayName());
    assertNull(dbpPropertyDescriptor.getDataType());
    assertNull(dbpPropertyDescriptor.getDefaultValue());
    assertNull(dbpPropertyDescriptor.getDescription());
    assertNull(dbpPropertyDescriptor.getHint());
    assertNull(dbpPropertyDescriptor.getFeatures());
    assertNull(dbpPropertyDescriptor.getRequiredFeatures());
    assertNull(((PropertyDescriptor) dbpPropertyDescriptor).getPropertyType());
    assertEquals(1, properties.length);
    assertEquals(PropertyLength.LONG, dbpPropertyDescriptor.getLength());
    assertFalse(dbpPropertyDescriptor.isRequired());
    assertFalse(propertyCollector.isEmpty());
  }

  /**
   * Test {@link PropertySourceAbstract#addProperty(DBPPropertyDescriptor)} with {@code prop}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then array length is one.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#addProperty(DBPPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceAbstract.addProperty(DBPPropertyDescriptor)"})
  public void testAddPropertyWithProp_whenJavaLangObject_thenArrayLengthIsOne() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    Class<Object> type = Object.class;
    Object[] validValues = new Object[] {DBPEvent.RENAME};

    PropertyDescriptor original =
        new PropertyDescriptor(
            "Category",
            "42",
            "Name",
            "The characteristics of someone or something",
            true,
            type,
            DBPEvent.RENAME,
            validValues);
    ProxyPropertyDescriptor prop = new ProxyPropertyDescriptor(original);

    // Act
    propertyCollector.addProperty(prop);

    // Assert
    DBPPropertyDescriptor[] properties = propertyCollector.getProperties();
    assertEquals(1, properties.length);
    assertFalse(propertyCollector.isEmpty());
    assertSame(prop, properties[0]);
  }

  /**
   * Test {@link PropertySourceAbstract#removeProperty(DBPPropertyDescriptor)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link ObjectPropertyDescriptor#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#removeProperty(DBPPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceAbstract.removeProperty(DBPPropertyDescriptor)"})
  public void testRemoveProperty_given42_thenCallsGetId() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    ObjectPropertyDescriptor original = mock(ObjectPropertyDescriptor.class);
    when(original.getId()).thenReturn("42");

    // Act
    propertyCollector.removeProperty(new ProxyPropertyDescriptor(original));

    // Assert
    verify(original, atLeast(1)).getId();
  }

  /**
   * Test {@link PropertySourceAbstract#removeProperty(DBPPropertyDescriptor)}.
   *
   * <ul>
   *   <li>Given {@code Attribute}.
   *   <li>Then calls {@link ConfigurationElementHandle#getAttribute(String)}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#removeProperty(DBPPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceAbstract.removeProperty(DBPPropertyDescriptor)"})
  public void testRemoveProperty_givenAttribute_thenCallsGetAttribute() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);

    // Act
    propertyCollector.removeProperty(new ProxyPropertyDescriptor(original));

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
  }

  /**
   * Test {@link PropertySourceAbstract#hasProperty(ObjectPropertyDescriptor)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#hasProperty(ObjectPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.hasProperty(ObjectPropertyDescriptor)"})
  public void testHasProperty_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new PropertyCollector(DBPEvent.RENAME, true).hasProperty(null));
  }

  /**
   * Test {@link PropertySourceAbstract#isEmpty()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.isEmpty()"})
  public void testIsEmpty_thenReturnFalse() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    propertyCollector.addProperty("Category", "42", "Name", DBPEvent.RENAME);

    // Act and Assert
    assertFalse(propertyCollector.isEmpty());
  }

  /**
   * Test {@link PropertySourceAbstract#isEmpty()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#isEmpty()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.isEmpty()"})
  public void testIsEmpty_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new PropertyCollector(DBPEvent.RENAME, true).isEmpty());
  }

  /**
   * Test {@link PropertySourceAbstract#getProperty(String)}.
   *
   * <ul>
   *   <li>Given {@link PropertyCollector#addProperty(String, String, String, Object)} with {@code
   *       Category} and {@code Id} and {@code Name} and value is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#getProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor PropertySourceAbstract.getProperty(String)"})
  public void testGetProperty_givenAddPropertyWithCategoryAndIdAndNameAndValueIsRename() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    propertyCollector.addProperty("Category", "Id", "Name", DBPEvent.RENAME);
    propertyCollector.addProperty("Category", "42", "Name", DBPEvent.RENAME);

    // Act
    DBPPropertyDescriptor actualProperty = propertyCollector.getProperty("42");

    // Assert
    assertTrue(actualProperty instanceof PropertyDescriptor);
    assertEquals("42", actualProperty.getId());
    assertEquals("Category", actualProperty.getCategory());
    assertEquals("Name", ((PropertyDescriptor) actualProperty).getName());
    assertEquals("Name", actualProperty.getDisplayName());
    assertNull(actualProperty.getDefaultValue());
    assertNull(actualProperty.getDescription());
    assertNull(actualProperty.getHint());
    assertNull(actualProperty.getFeatures());
    assertNull(actualProperty.getRequiredFeatures());
    assertNull(((PropertyDescriptor) actualProperty).getPropertyType());
    assertEquals(PropertyLength.LONG, actualProperty.getLength());
    assertFalse(actualProperty.isRequired());
    Class<Object> expectedDataType = Object.class;
    assertEquals(expectedDataType, actualProperty.getDataType());
  }

  /**
   * Test {@link PropertySourceAbstract#getProperty(String)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#getProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor PropertySourceAbstract.getProperty(String)"})
  public void testGetProperty_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(new PropertyCollector(DBPEvent.RENAME, true).getProperty("42"));
  }

  /**
   * Test {@link PropertySourceAbstract#getProperty(String)}.
   *
   * <ul>
   *   <li>Then return {@link PropertyDescriptor}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#getProperty(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor PropertySourceAbstract.getProperty(String)"})
  public void testGetProperty_thenReturnPropertyDescriptor() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    propertyCollector.addProperty("Category", "42", "Name", DBPEvent.RENAME);

    // Act
    DBPPropertyDescriptor actualProperty = propertyCollector.getProperty("42");

    // Assert
    assertTrue(actualProperty instanceof PropertyDescriptor);
    assertEquals("42", actualProperty.getId());
    assertEquals("Category", actualProperty.getCategory());
    assertEquals("Name", ((PropertyDescriptor) actualProperty).getName());
    assertEquals("Name", actualProperty.getDisplayName());
    assertNull(actualProperty.getDefaultValue());
    assertNull(actualProperty.getDescription());
    assertNull(actualProperty.getHint());
    assertNull(actualProperty.getFeatures());
    assertNull(actualProperty.getRequiredFeatures());
    assertNull(((PropertyDescriptor) actualProperty).getPropertyType());
    assertEquals(PropertyLength.LONG, actualProperty.getLength());
    assertFalse(actualProperty.isRequired());
    Class<Object> expectedDataType = Object.class;
    assertEquals(expectedDataType, actualProperty.getDataType());
  }

  /**
   * Test {@link PropertySourceAbstract#getProperties()}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor[] PropertySourceAbstract.getProperties()"})
  public void testGetProperties() {
    // Arrange, Act and Assert
    assertEquals(0, new PropertyCollector(DBPEvent.RENAME, true).getProperties().length);
  }

  /**
   * Test {@link PropertySourceAbstract#isPropertySet(String)} with {@code id}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.isPropertySet(String)"})
  public void testIsPropertySetWithId_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new PropertyCollector(DBPEvent.RENAME, true).isPropertySet("42"));
  }

  /**
   * Test {@link PropertySourceAbstract#isPropertySet(String)} with {@code id}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.isPropertySet(String)"})
  public void testIsPropertySetWithId_thenReturnTrue() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    propertyCollector.addProperty("Category", "42", "Name", DBPEvent.RENAME);

    // Act and Assert
    assertTrue(propertyCollector.isPropertySet("42"));
  }

  /**
   * Test {@link PropertySourceAbstract#isPropertySet(Object, ObjectPropertyDescriptor)} with {@code
   * object}, {@code prop}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link ObjectPropertyDescriptor#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#isPropertySet(Object,
   * ObjectPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PropertySourceAbstract.isPropertySet(Object, ObjectPropertyDescriptor)"
  })
  public void testIsPropertySetWithObjectProp_given42_thenCallsGetId() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.getId()).thenReturn("42");
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean()))
        .thenThrow(new UnsupportedOperationException());

    // Act
    boolean actualIsPropertySetResult = propertyCollector.isPropertySet(DBPEvent.RENAME, prop);

    // Assert
    verify(prop).getId();
    verify(prop).isLazy(isA(Object.class), eq(true));
    assertFalse(actualIsPropertySetResult);
  }

  /**
   * Test {@link PropertySourceAbstract#isPropertySet(Object, ObjectPropertyDescriptor)} with {@code
   * object}, {@code prop}.
   *
   * <ul>
   *   <li>Given {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#isPropertySet(Object,
   * ObjectPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PropertySourceAbstract.isPropertySet(Object, ObjectPropertyDescriptor)"
  })
  public void testIsPropertySetWithObjectProp_givenTrue() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean())).thenReturn(true);

    // Act
    boolean actualIsPropertySetResult = propertyCollector.isPropertySet(DBPEvent.RENAME, prop);

    // Assert
    verify(prop).isLazy(isA(Object.class), eq(true));
    assertFalse(actualIsPropertySetResult);
  }

  /**
   * Test {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor, String)} with {@code
   * monitor}, {@code id}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertySourceAbstract.getPropertyValue(DBRProgressMonitor, String)"})
  public void testGetPropertyValueWithMonitorId() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    // Act and Assert
    assertNull(propertyCollector.getPropertyValue(new LoggingProgressMonitor(), "42"));
  }

  /**
   * Test {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, boolean)} with {@code monitor}, {@code object}, {@code prop}, {@code
   * formatValue}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object PropertySourceAbstract.getPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, boolean)"
  })
  public void testGetPropertyValueWithMonitorObjectPropFormatValue()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.getId()).thenReturn("42");
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenThrow(new InvocationTargetException(new Throwable(), "foo"));

    // Act
    Object actualPropertyValue =
        propertyCollector.getPropertyValue(monitor, DBPEvent.RENAME, prop, true);

    // Assert
    verify(prop).getId();
    verify(prop).readValue(isA(Object.class), isA(DBRProgressMonitor.class), eq(true));
    assertNull(actualPropertyValue);
  }

  /**
   * Test {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, boolean)} with {@code monitor}, {@code object}, {@code prop}, {@code
   * formatValue}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object PropertySourceAbstract.getPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, boolean)"
  })
  public void testGetPropertyValueWithMonitorObjectPropFormatValue2()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.getId()).thenReturn("42");
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenThrow(new UnsupportedOperationException());

    // Act
    Object actualPropertyValue =
        propertyCollector.getPropertyValue(monitor, DBPEvent.RENAME, prop, true);

    // Assert
    verify(prop).getId();
    verify(prop).readValue(isA(Object.class), isA(DBRProgressMonitor.class), eq(true));
    assertNull(actualPropertyValue);
  }

  /**
   * Test {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, boolean)} with {@code monitor}, {@code object}, {@code prop}, {@code
   * formatValue}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object PropertySourceAbstract.getPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, boolean)"
  })
  public void testGetPropertyValueWithMonitorObjectPropFormatValue3() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean()))
        .thenThrow(new UnsupportedOperationException());
    when(prop.getId()).thenReturn("42");

    // Act
    Object actualPropertyValue =
        propertyCollector.getPropertyValue(null, DBPEvent.RENAME, prop, true);

    // Assert
    verify(prop).getId();
    verify(prop).isLazy(isA(Object.class), eq(true));
    assertNull(actualPropertyValue);
  }

  /**
   * Test {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, boolean)} with {@code monitor}, {@code object}, {@code prop}, {@code
   * formatValue}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object PropertySourceAbstract.getPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, boolean)"
  })
  public void testGetPropertyValueWithMonitorObjectPropFormatValue4()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.supportsPreview()).thenReturn(true);
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean())).thenReturn(true);
    when(prop.getId()).thenReturn("42");
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenThrow(new InvocationTargetException(new Throwable(), "foo"));

    // Act
    Object actualPropertyValue =
        propertyCollector.getPropertyValue(null, DBPEvent.RENAME, prop, true);

    // Assert
    verify(prop).getId();
    verify(prop).isLazy(isA(Object.class), eq(true));
    verify(prop).readValue(isA(Object.class), isNull(), eq(true));
    verify(prop).supportsPreview();
    assertNull(actualPropertyValue);
  }

  /**
   * Test {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, boolean)} with {@code monitor}, {@code object}, {@code prop}, {@code
   * formatValue}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object PropertySourceAbstract.getPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, boolean)"
  })
  public void testGetPropertyValueWithMonitorObjectPropFormatValue5() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.supportsPreview()).thenThrow(new UnsupportedOperationException());
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean())).thenReturn(true);
    when(prop.getId()).thenReturn("42");

    // Act
    Object actualPropertyValue =
        propertyCollector.getPropertyValue(null, DBPEvent.RENAME, prop, true);

    // Assert
    verify(prop).getId();
    verify(prop).isLazy(isA(Object.class), eq(true));
    verify(prop).supportsPreview();
    assertNull(actualPropertyValue);
  }

  /**
   * Test {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, boolean)} with {@code monitor}, {@code object}, {@code prop}, {@code
   * formatValue}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object PropertySourceAbstract.getPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, boolean)"
  })
  public void testGetPropertyValueWithMonitorObjectPropFormatValue6() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.supportsPreview()).thenReturn(false);
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean())).thenReturn(true);
    when(prop.getId()).thenReturn("42");

    // Act
    Object actualPropertyValue =
        propertyCollector.getPropertyValue(null, DBPEvent.RENAME, prop, true);

    // Assert
    verify(prop, atLeast(1)).getId();
    verify(prop).isLazy(isA(Object.class), eq(true));
    verify(prop).supportsPreview();
    assertEquals(
        "Cannot invoke \"org.osgi.framework.Bundle.getBundleContext()\" because \"bundle\" is null",
        actualPropertyValue);
  }

  /**
   * Test {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, boolean)} with {@code monitor}, {@code object}, {@code prop}, {@code
   * formatValue}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object PropertySourceAbstract.getPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, boolean)"
  })
  public void testGetPropertyValueWithMonitorObjectPropFormatValue7()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean())).thenReturn(false);
    when(prop.getId()).thenReturn("42");
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenThrow(new InvocationTargetException(new Throwable(), "foo"));

    // Act
    Object actualPropertyValue =
        propertyCollector.getPropertyValue(null, DBPEvent.RENAME, prop, true);

    // Assert
    verify(prop).getId();
    verify(prop).isLazy(isA(Object.class), eq(true));
    verify(prop).readValue(isA(Object.class), isNull(), eq(true));
    assertNull(actualPropertyValue);
  }

  /**
   * Test {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, boolean)} with {@code monitor}, {@code object}, {@code prop}, {@code
   * formatValue}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object PropertySourceAbstract.getPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, boolean)"
  })
  public void testGetPropertyValueWithMonitorObjectPropFormatValue8()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.supportsPreview()).thenReturn(true);
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean())).thenReturn(true);
    when(prop.getId()).thenReturn("42");
    Throwable throwable = new Throwable("org.jkiss.dbeaver.model", new Throwable());
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenThrow(new InvocationTargetException(throwable, "foo"));

    // Act
    Object actualPropertyValue =
        propertyCollector.getPropertyValue(null, DBPEvent.RENAME, prop, true);

    // Assert
    verify(prop).getId();
    verify(prop).isLazy(isA(Object.class), eq(true));
    verify(prop).readValue(isA(Object.class), isNull(), eq(true));
    verify(prop).supportsPreview();
    assertEquals("org.jkiss.dbeaver.model", actualPropertyValue);
  }

  /**
   * Test {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, boolean)} with {@code monitor}, {@code object}, {@code prop}, {@code
   * formatValue}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object PropertySourceAbstract.getPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, boolean)"
  })
  public void testGetPropertyValueWithMonitorObjectPropFormatValue9()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    Throwable throwable = new Throwable();
    throwable.initCause(new Throwable());
    InvocationTargetException invocationTargetException =
        new InvocationTargetException(throwable, "foo");

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.supportsPreview()).thenReturn(true);
    when(prop.isLazy(Mockito.<Object>any(), anyBoolean())).thenReturn(true);
    when(prop.getId()).thenReturn("42");
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenThrow(invocationTargetException);

    // Act
    Object actualPropertyValue =
        propertyCollector.getPropertyValue(null, DBPEvent.RENAME, prop, true);

    // Assert
    verify(prop).getId();
    verify(prop).isLazy(isA(Object.class), eq(true));
    verify(prop).readValue(isA(Object.class), isNull(), eq(true));
    verify(prop).supportsPreview();
    assertNull(actualPropertyValue);
  }

  /**
   * Test {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, boolean)} with {@code monitor}, {@code object}, {@code prop}, {@code
   * formatValue}.
   *
   * <ul>
   *   <li>Then return {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#getPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Object PropertySourceAbstract.getPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, boolean)"
  })
  public void testGetPropertyValueWithMonitorObjectPropFormatValue_thenReturnRename()
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    Object object = DBPEvent.RENAME;

    ObjectPropertyDescriptor prop = mock(ObjectPropertyDescriptor.class);
    when(prop.readValue(Mockito.<Object>any(), Mockito.<DBRProgressMonitor>any(), anyBoolean()))
        .thenReturn(DBPEvent.RENAME);

    // Act
    Object actualPropertyValue = propertyCollector.getPropertyValue(monitor, object, prop, true);

    // Assert
    verify(prop).readValue(isA(Object.class), isA(DBRProgressMonitor.class), eq(true));
    assertSame(object, actualPropertyValue);
  }

  /**
   * Test {@link PropertySourceAbstract#isPropertyResettable(String)} with {@code id}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#isPropertyResettable(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.isPropertyResettable(String)"})
  public void testIsPropertyResettableWithId() {
    // Arrange, Act and Assert
    assertFalse(new PropertyCollector(DBPEvent.RENAME, true).isPropertyResettable("42"));
  }

  /**
   * Test {@link PropertySourceAbstract#isPropertyResettable(Object, ObjectPropertyDescriptor)} with
   * {@code object}, {@code prop}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#isPropertyResettable(Object,
   * ObjectPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean PropertySourceAbstract.isPropertyResettable(Object, ObjectPropertyDescriptor)"
  })
  public void testIsPropertyResettableWithObjectProp_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        new PropertyCollector(DBPEvent.RENAME, true).isPropertyResettable(DBPEvent.RENAME, null));
  }

  /**
   * Test {@link PropertySourceAbstract#resetPropertyValue(DBRProgressMonitor, String)} with {@code
   * monitor}, {@code id}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#resetPropertyValue(DBRProgressMonitor,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceAbstract.resetPropertyValue(DBRProgressMonitor, String)"})
  public void testResetPropertyValueWithMonitorId() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> propertyCollector.resetPropertyValue(new LoggingProgressMonitor(), "42"));
  }

  /**
   * Test {@link PropertySourceAbstract#resetPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor)} with {@code monitor}, {@code object}, {@code id}.
   *
   * <ul>
   *   <li>Then throw {@link UnsupportedOperationException}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#resetPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceAbstract.resetPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor)"
  })
  public void testResetPropertyValueWithMonitorObjectId_thenThrowUnsupportedOperationException() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            propertyCollector.resetPropertyValue(
                new LoggingProgressMonitor(), DBPEvent.RENAME, null));
  }

  /**
   * Test {@link PropertySourceAbstract#resetPropertyValueToDefault(String)}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#resetPropertyValueToDefault(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceAbstract.resetPropertyValueToDefault(String)"})
  public void testResetPropertyValueToDefault() {
    // Arrange, Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () -> new PropertyCollector(DBPEvent.RENAME, true).resetPropertyValueToDefault("42"));
  }

  /**
   * Test {@link PropertySourceAbstract#setPropertyValue(DBRProgressMonitor, Object,
   * ObjectPropertyDescriptor, Object)} with {@code monitor}, {@code object}, {@code prop}, {@code
   * value}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#setPropertyValue(DBRProgressMonitor,
   * Object, ObjectPropertyDescriptor, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceAbstract.setPropertyValue(DBRProgressMonitor, Object, ObjectPropertyDescriptor, Object)"
  })
  public void testSetPropertyValueWithMonitorObjectPropValue() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    // Act and Assert
    assertThrows(
        UnsupportedOperationException.class,
        () ->
            propertyCollector.setPropertyValue(
                new LoggingProgressMonitor(), DBPEvent.RENAME, null, DBPEvent.RENAME));
  }

  /**
   * Test {@link PropertySourceAbstract#collectProperties()}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#collectProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.collectProperties()"})
  public void testCollectProperties() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(null, true);
    propertyCollector.setEnableFilters(false);

    // Act
    boolean actualCollectPropertiesResult = propertyCollector.collectProperties();

    // Assert
    assertEquals(0, propertyCollector.getProperties().length);
    assertFalse(actualCollectPropertiesResult);
    assertTrue(propertyCollector.isEmpty());
  }

  /**
   * Test {@link PropertySourceAbstract#collectProperties()}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#collectProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.collectProperties()"})
  public void testCollectProperties2() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    propertyCollector.setEnableFilters(false);

    // Act
    boolean actualCollectPropertiesResult = propertyCollector.collectProperties();

    // Assert
    assertEquals(0, propertyCollector.getProperties().length);
    assertFalse(actualCollectPropertiesResult);
    assertTrue(propertyCollector.isEmpty());
  }

  /**
   * Test {@link PropertySourceAbstract#collectProperties()}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#collectProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.collectProperties()"})
  public void testCollectProperties3() {
    // Arrange
    PropertyCollector propertyCollector =
        new PropertyCollector(new PropertyCollector(DBPEvent.RENAME, true), true);
    propertyCollector.setEnableFilters(false);

    // Act
    boolean actualCollectPropertiesResult = propertyCollector.collectProperties();

    // Assert
    assertEquals(0, propertyCollector.getProperties().length);
    assertFalse(actualCollectPropertiesResult);
    assertTrue(propertyCollector.isEmpty());
  }

  /**
   * Test {@link PropertySourceAbstract#collectProperties()}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#collectProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.collectProperties()"})
  public void testCollectProperties4() {
    // Arrange
    PropertyCollector propertyCollector =
        new PropertyCollector(new PropertySourceCollection(new ArrayList<>()), true);
    propertyCollector.setEnableFilters(false);

    // Act
    boolean actualCollectPropertiesResult = propertyCollector.collectProperties();

    // Assert
    assertEquals(0, propertyCollector.getProperties().length);
    assertFalse(actualCollectPropertiesResult);
    assertTrue(propertyCollector.isEmpty());
  }

  /**
   * Test {@link PropertySourceAbstract#collectProperties()}.
   *
   * <ul>
   *   <li>Then first element {@link PropertyDescriptor}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#collectProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.collectProperties()"})
  public void testCollectProperties_thenFirstElementPropertyDescriptor() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    propertyCollector.addProperty("Category", "42", "Name", DBPEvent.RENAME);

    PropertyCollector propertyCollector2 = new PropertyCollector(propertyCollector, true);
    propertyCollector2.setEnableFilters(false);

    // Act
    boolean actualCollectPropertiesResult = propertyCollector2.collectProperties();

    // Assert
    DBPPropertyDescriptor[] properties = propertyCollector2.getProperties();
    assertTrue(properties[0] instanceof PropertyDescriptor);
    assertEquals(1, properties.length);
    assertFalse(propertyCollector2.isEmpty());
    assertTrue(actualCollectPropertiesResult);
  }

  /**
   * Test {@link PropertySourceAbstract#isEnableFilters()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#isEnableFilters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.isEnableFilters()"})
  public void testIsEnableFilters_thenReturnFalse() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    propertyCollector.setEnableFilters(false);

    // Act and Assert
    assertFalse(propertyCollector.isEnableFilters());
  }

  /**
   * Test {@link PropertySourceAbstract#isEnableFilters()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#isEnableFilters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.isEnableFilters()"})
  public void testIsEnableFilters_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new PropertyCollector(DBPEvent.RENAME, true).isEnableFilters());
  }

  /**
   * Test {@link PropertySourceAbstract#getEnableFilters()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#getEnableFilters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.getEnableFilters()"})
  public void testGetEnableFilters_thenReturnFalse() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    propertyCollector.setEnableFilters(false);

    // Act and Assert
    assertFalse(propertyCollector.getEnableFilters());
  }

  /**
   * Test {@link PropertySourceAbstract#getEnableFilters()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceAbstract#getEnableFilters()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceAbstract.getEnableFilters()"})
  public void testGetEnableFilters_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(new PropertyCollector(DBPEvent.RENAME, true).getEnableFilters());
  }

  /**
   * Test {@link PropertySourceAbstract#addChangedProperties(DBPPropertyDescriptor, Object)}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#addChangedProperties(DBPPropertyDescriptor,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceAbstract.addChangedProperties(DBPPropertyDescriptor, Object)"
  })
  public void testAddChangedProperties() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);

    // Act
    propertyCollector.addChangedProperties(new ProxyPropertyDescriptor(null), DBPEvent.RENAME);

    // Assert
    assertEquals(1, propertyCollector.getChangedPropertiesValues().size());
  }

  /**
   * Test {@link PropertySourceAbstract#setChangedPropertiesMap(Map)}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#setChangedPropertiesMap(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceAbstract.setChangedPropertiesMap(Map)"})
  public void testSetChangedPropertiesMap() {
    // Arrange
    PropertyCollector propertyCollector = new PropertyCollector(DBPEvent.RENAME, true);
    HashMap<DBPPropertyDescriptor, Object> newMap = new HashMap<>();

    // Act
    propertyCollector.setChangedPropertiesMap(newMap);

    // Assert
    assertSame(newMap, propertyCollector.getChangedPropertiesValues());
  }

  /**
   * Test {@link PropertySourceAbstract#getChangedPropertiesValues()}.
   *
   * <p>Method under test: {@link PropertySourceAbstract#getChangedPropertiesValues()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map PropertySourceAbstract.getChangedPropertiesValues()"})
  public void testGetChangedPropertiesValues() {
    // Arrange, Act and Assert
    assertTrue(new PropertyCollector(DBPEvent.RENAME, true).getChangedPropertiesValues().isEmpty());
  }
}
