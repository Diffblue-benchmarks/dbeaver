package org.jkiss.dbeaver.runtime.properties;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.impl.ProxyPropertyDescriptor;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.jkiss.dbeaver.runtime.IVariableResolver;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PropertySourceCustomDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PropertySourceCustom#PropertySourceCustom()}
   *   <li>{@link PropertySourceCustom#setDefValueResolver(IVariableResolver)}
   *   <li>{@link PropertySourceCustom#setDefaultValues(Map)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceCustom.<init>()",
    "void PropertySourceCustom.setDefValueResolver(IVariableResolver)",
    "void PropertySourceCustom.setDefaultValues(Map)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    PropertySourceCustom actualPropertySourceCustom = new PropertySourceCustom();
    actualPropertySourceCustom.setDefValueResolver(mock(IVariableResolver.class));
    actualPropertySourceCustom.setDefaultValues(new HashMap<>());

    // Assert
    assertEquals(0, actualPropertySourceCustom.getProperties().length);
    assertTrue(actualPropertySourceCustom.getPropertiesWithDefaults().isEmpty());
    assertTrue(actualPropertySourceCustom.getPropertyValues().isEmpty());
  }

  /**
   * Test {@link PropertySourceCustom#PropertySourceCustom(Collection, Map)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#PropertySourceCustom(Collection, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.<init>(Collection, Map)"})
  public void testNewPropertySourceCustom_givenFoo_whenHashMapFooIsRename() {
    // Arrange
    ArrayList<DBPPropertyDescriptor> properties = new ArrayList<>();

    HashMap<String, Object> values = new HashMap<>();
    values.put("foo", DBPEvent.RENAME);

    // Act
    PropertySourceCustom actualPropertySourceCustom = new PropertySourceCustom(properties, values);

    // Assert
    assertEquals(0, actualPropertySourceCustom.getProperties().length);
    assertTrue(actualPropertySourceCustom.getPropertiesWithDefaults().isEmpty());
    assertTrue(actualPropertySourceCustom.getPropertyValues().isEmpty());
  }

  /**
   * Test {@link PropertySourceCustom#PropertySourceCustom(DBPPropertyDescriptor[], Map)}.
   *
   * <ul>
   *   <li>Then return first element Description is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#PropertySourceCustom(DBPPropertyDescriptor[],
   * Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.<init>(DBPPropertyDescriptor[], Map)"})
  public void testNewPropertySourceCustom_thenReturnFirstElementDescriptionIsAttribute()
      throws InvalidRegistryObjectException {
    // Arrange
    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};

    // Act
    PropertySourceCustom actualPropertySourceCustom =
        new PropertySourceCustom(properties, new HashMap<>());

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    DBPPropertyDescriptor[] properties2 = actualPropertySourceCustom.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties2[0];
    assertTrue(dbpPropertyDescriptor instanceof ProxyPropertyDescriptor);
    assertEquals("Attribute", dbpPropertyDescriptor.getDescription());
    assertEquals("Attribute", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Attribute", dbpPropertyDescriptor.getHint());
    assertEquals("Attribute", dbpPropertyDescriptor.getId());
    Map<String, Object> propertiesWithDefaults =
        actualPropertySourceCustom.getPropertiesWithDefaults();
    assertEquals(1, propertiesWithDefaults.size());
    assertEquals("Attribute", propertiesWithDefaults.get("Attribute"));
    assertEquals("Attribute", dbpPropertyDescriptor.getDefaultValue());
    assertEquals(1, properties2.length);
    assertArrayEquals(new String[] {"Attribute"}, dbpPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link PropertySourceCustom#PropertySourceCustom(DBPPropertyDescriptor[], Map)}.
   *
   * <ul>
   *   <li>Then return first element Description is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#PropertySourceCustom(DBPPropertyDescriptor[],
   * Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.<init>(DBPPropertyDescriptor[], Map)"})
  public void testNewPropertySourceCustom_thenReturnFirstElementDescriptionIsAttribute2()
      throws InvalidRegistryObjectException {
    // Arrange
    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};

    HashMap<String, Object> values = new HashMap<>();
    values.put("foo", DBPEvent.RENAME);

    // Act
    PropertySourceCustom actualPropertySourceCustom = new PropertySourceCustom(properties, values);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    DBPPropertyDescriptor[] properties2 = actualPropertySourceCustom.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties2[0];
    assertTrue(dbpPropertyDescriptor instanceof ProxyPropertyDescriptor);
    assertEquals("Attribute", dbpPropertyDescriptor.getDescription());
    assertEquals("Attribute", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Attribute", dbpPropertyDescriptor.getHint());
    assertEquals("Attribute", dbpPropertyDescriptor.getId());
    Map<String, Object> propertiesWithDefaults =
        actualPropertySourceCustom.getPropertiesWithDefaults();
    assertEquals(1, propertiesWithDefaults.size());
    assertEquals("Attribute", propertiesWithDefaults.get("Attribute"));
    assertEquals("Attribute", dbpPropertyDescriptor.getDefaultValue());
    assertEquals(1, properties2.length);
    assertArrayEquals(new String[] {"Attribute"}, dbpPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link PropertySourceCustom#PropertySourceCustom(DBPPropertyDescriptor[], Map)}.
   *
   * <ul>
   *   <li>Then return first element DisplayName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#PropertySourceCustom(DBPPropertyDescriptor[],
   * Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.<init>(DBPPropertyDescriptor[], Map)"})
  public void testNewPropertySourceCustom_thenReturnFirstElementDisplayNameIsEmptyString()
      throws InvalidRegistryObjectException {
    // Arrange
    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn(null);
    PropertyDescriptor original = new PropertyDescriptor("Category", config);
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};

    // Act
    PropertySourceCustom actualPropertySourceCustom =
        new PropertySourceCustom(properties, new HashMap<>());

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    DBPPropertyDescriptor[] properties2 = actualPropertySourceCustom.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties2[0];
    assertTrue(dbpPropertyDescriptor instanceof ProxyPropertyDescriptor);
    assertEquals("", dbpPropertyDescriptor.getDisplayName());
    assertNull(dbpPropertyDescriptor.getDefaultValue());
    assertNull(dbpPropertyDescriptor.getDescription());
    assertNull(dbpPropertyDescriptor.getHint());
    assertNull(dbpPropertyDescriptor.getId());
    assertNull(dbpPropertyDescriptor.getFeatures());
    assertEquals(1, properties2.length);
    assertTrue(actualPropertySourceCustom.getPropertiesWithDefaults().isEmpty());
  }

  /**
   * Test {@link PropertySourceCustom#PropertySourceCustom(DBPPropertyDescriptor[], Map)}.
   *
   * <ul>
   *   <li>Then return PropertyValues size is one.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#PropertySourceCustom(DBPPropertyDescriptor[],
   * Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.<init>(DBPPropertyDescriptor[], Map)"})
  public void testNewPropertySourceCustom_thenReturnPropertyValuesSizeIsOne()
      throws InvalidRegistryObjectException {
    // Arrange
    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("foo");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};

    HashMap<String, Object> values = new HashMap<>();
    values.put("foo", DBPEvent.RENAME);

    // Act
    PropertySourceCustom actualPropertySourceCustom = new PropertySourceCustom(properties, values);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    DBPPropertyDescriptor[] properties2 = actualPropertySourceCustom.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties2[0];
    assertTrue(dbpPropertyDescriptor instanceof ProxyPropertyDescriptor);
    assertEquals("foo", dbpPropertyDescriptor.getDescription());
    assertEquals("foo", dbpPropertyDescriptor.getDisplayName());
    assertEquals("foo", dbpPropertyDescriptor.getHint());
    assertEquals("foo", dbpPropertyDescriptor.getId());
    assertEquals("foo", dbpPropertyDescriptor.getDefaultValue());
    assertEquals(1, actualPropertySourceCustom.getPropertyValues().size());
    assertEquals(1, properties2.length);
    assertEquals(values, actualPropertySourceCustom.getPropertiesWithDefaults());
    assertArrayEquals(new String[] {"foo"}, dbpPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link PropertySourceCustom#PropertySourceCustom(DBPPropertyDescriptor[], Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is empty string.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#PropertySourceCustom(DBPPropertyDescriptor[],
   * Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.<init>(DBPPropertyDescriptor[], Map)"})
  public void testNewPropertySourceCustom_whenHashMapFooIsEmptyString()
      throws InvalidRegistryObjectException {
    // Arrange
    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("foo");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};

    HashMap<String, Object> values = new HashMap<>();
    values.put("foo", "");

    // Act
    PropertySourceCustom actualPropertySourceCustom = new PropertySourceCustom(properties, values);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    DBPPropertyDescriptor[] properties2 = actualPropertySourceCustom.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties2[0];
    assertTrue(dbpPropertyDescriptor instanceof ProxyPropertyDescriptor);
    assertEquals("foo", dbpPropertyDescriptor.getDescription());
    assertEquals("foo", dbpPropertyDescriptor.getDisplayName());
    assertEquals("foo", dbpPropertyDescriptor.getHint());
    assertEquals("foo", dbpPropertyDescriptor.getId());
    assertEquals("foo", dbpPropertyDescriptor.getDefaultValue());
    assertEquals(1, properties2.length);
    assertEquals(values, actualPropertySourceCustom.getPropertiesWithDefaults());
    assertEquals(values, actualPropertySourceCustom.getPropertyValues());
    assertArrayEquals(new String[] {"foo"}, dbpPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link PropertySourceCustom#PropertySourceCustom(DBPPropertyDescriptor[], Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()} {@code foo} is {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#PropertySourceCustom(DBPPropertyDescriptor[],
   * Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.<init>(DBPPropertyDescriptor[], Map)"})
  public void testNewPropertySourceCustom_whenHashMapFooIsFoo()
      throws InvalidRegistryObjectException {
    // Arrange
    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("foo");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};

    HashMap<String, Object> values = new HashMap<>();
    values.put("foo", "foo");

    // Act
    PropertySourceCustom actualPropertySourceCustom = new PropertySourceCustom(properties, values);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    DBPPropertyDescriptor[] properties2 = actualPropertySourceCustom.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties2[0];
    assertTrue(dbpPropertyDescriptor instanceof ProxyPropertyDescriptor);
    assertEquals("foo", dbpPropertyDescriptor.getDescription());
    assertEquals("foo", dbpPropertyDescriptor.getDisplayName());
    assertEquals("foo", dbpPropertyDescriptor.getHint());
    assertEquals("foo", dbpPropertyDescriptor.getId());
    assertEquals("foo", dbpPropertyDescriptor.getDefaultValue());
    assertEquals(1, properties2.length);
    assertEquals(values, actualPropertySourceCustom.getPropertiesWithDefaults());
    assertEquals(values, actualPropertySourceCustom.getPropertyValues());
    assertArrayEquals(new String[] {"foo"}, dbpPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link PropertySourceCustom#PropertySourceCustom(Collection, Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#PropertySourceCustom(Collection, Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.<init>(Collection, Map)"})
  public void testNewPropertySourceCustom_whenHashMap_thenReturnArrayLengthIsZero() {
    // Arrange
    ArrayList<DBPPropertyDescriptor> properties = new ArrayList<>();

    // Act
    PropertySourceCustom actualPropertySourceCustom =
        new PropertySourceCustom(properties, new HashMap<>());

    // Assert
    assertEquals(0, actualPropertySourceCustom.getProperties().length);
    assertTrue(actualPropertySourceCustom.getPropertiesWithDefaults().isEmpty());
    assertTrue(actualPropertySourceCustom.getPropertyValues().isEmpty());
  }

  /**
   * Test {@link PropertySourceCustom#setValues(Map)}.
   *
   * <ul>
   *   <li>Then calls {@link IConfigurationElement#getAttribute(String)}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#setValues(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.setValues(Map)"})
  public void testSetValues_thenCallsGetAttribute() throws InvalidRegistryObjectException {
    // Arrange
    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);

    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();
    propertySourceCustom.addProperties(
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)});

    HashMap<String, Object> values = new HashMap<>();
    values.put("foo", DBPEvent.RENAME);

    // Act
    propertySourceCustom.setValues(values);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
  }

  /**
   * Test {@link PropertySourceCustom#getPropertyValues()}.
   *
   * <p>Method under test: {@link PropertySourceCustom#getPropertyValues()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map PropertySourceCustom.getPropertyValues()"})
  public void testGetPropertyValues() {
    // Arrange, Act and Assert
    assertTrue(new PropertySourceCustom().getPropertyValues().isEmpty());
  }

  /**
   * Test {@link PropertySourceCustom#getPropertiesWithDefaults()}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return containsKey {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#getPropertiesWithDefaults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map PropertySourceCustom.getPropertiesWithDefaults()"})
  public void testGetPropertiesWithDefaults_givenJavaLangObject_thenReturnContainsKey42() {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();
    Class<Object> type = Object.class;
    Object[] validValues = new Object[] {DBPEvent.RENAME};

    PropertyDescriptor original =
        new PropertyDescriptor(
            "${U:U}",
            "42",
            "${U:U}",
            "The characteristics of someone or something",
            true,
            type,
            DBPEvent.RENAME,
            validValues);
    propertySourceCustom.addProperties(
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)});
    propertySourceCustom.setDefValueResolver(mock(IVariableResolver.class));

    // Act
    Map<String, Object> actualPropertiesWithDefaults =
        propertySourceCustom.getPropertiesWithDefaults();

    // Assert
    assertEquals(1, actualPropertiesWithDefaults.size());
    assertTrue(actualPropertiesWithDefaults.containsKey("42"));
  }

  /**
   * Test {@link PropertySourceCustom#getPropertiesWithDefaults()}.
   *
   * <ul>
   *   <li>Given {@link PropertySourceCustom#PropertySourceCustom()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#getPropertiesWithDefaults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map PropertySourceCustom.getPropertiesWithDefaults()"})
  public void testGetPropertiesWithDefaults_givenPropertySourceCustom_thenReturnEmpty() {
    // Arrange, Act and Assert
    assertTrue(new PropertySourceCustom().getPropertiesWithDefaults().isEmpty());
  }

  /**
   * Test {@link PropertySourceCustom#getPropertiesWithDefaults()}.
   *
   * <ul>
   *   <li>Then return {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#getPropertiesWithDefaults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map PropertySourceCustom.getPropertiesWithDefaults()"})
  public void testGetPropertiesWithDefaults_thenReturnAttribute()
      throws InvalidRegistryObjectException {
    // Arrange
    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);

    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();
    propertySourceCustom.addProperties(
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)});
    propertySourceCustom.setDefValueResolver(mock(IVariableResolver.class));

    // Act
    Map<String, Object> actualPropertiesWithDefaults =
        propertySourceCustom.getPropertiesWithDefaults();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    assertEquals(1, actualPropertiesWithDefaults.size());
    assertEquals("Attribute", actualPropertiesWithDefaults.get("Attribute"));
  }

  /**
   * Test {@link PropertySourceCustom#getPropertiesWithDefaults()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#getPropertiesWithDefaults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map PropertySourceCustom.getPropertiesWithDefaults()"})
  public void testGetPropertiesWithDefaults_thenReturnEmpty() {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();
    propertySourceCustom.setDefValueResolver(mock(IVariableResolver.class));

    // Act and Assert
    assertTrue(propertySourceCustom.getPropertiesWithDefaults().isEmpty());
  }

  /**
   * Test {@link PropertySourceCustom#getPropertiesWithDefaults()}.
   *
   * <ul>
   *   <li>Then return {@code ${U:U}} is {@code Get}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#getPropertiesWithDefaults()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map PropertySourceCustom.getPropertiesWithDefaults()"})
  public void testGetPropertiesWithDefaults_thenReturnUUIsGet()
      throws InvalidRegistryObjectException {
    // Arrange
    IVariableResolver defValueResolver = mock(IVariableResolver.class);
    when(defValueResolver.get(Mockito.<String>any())).thenReturn("Get");

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("${U:U}");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);

    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();
    propertySourceCustom.addProperties(
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)});
    propertySourceCustom.setDefValueResolver(defValueResolver);

    // Act
    Map<String, Object> actualPropertiesWithDefaults =
        propertySourceCustom.getPropertiesWithDefaults();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(defValueResolver).get("U");
    assertEquals(1, actualPropertiesWithDefaults.size());
    assertEquals("Get", actualPropertiesWithDefaults.get("${U:U}"));
  }

  /**
   * Test {@link PropertySourceCustom#addProperty(DBPPropertyDescriptor)}.
   *
   * <ul>
   *   <li>Then {@link PropertySourceCustom#PropertySourceCustom()} PropertiesWithDefaults Empty.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#addProperty(DBPPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.addProperty(DBPPropertyDescriptor)"})
  public void testAddProperty_thenPropertySourceCustomPropertiesWithDefaultsEmpty() {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();
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
            null,
            validValues);
    ProxyPropertyDescriptor property = new ProxyPropertyDescriptor(original);

    // Act
    propertySourceCustom.addProperty(property);

    // Assert
    DBPPropertyDescriptor[] properties = propertySourceCustom.getProperties();
    assertEquals(1, properties.length);
    assertTrue(propertySourceCustom.getPropertiesWithDefaults().isEmpty());
    assertSame(property, properties[0]);
  }

  /**
   * Test {@link PropertySourceCustom#addProperty(DBPPropertyDescriptor)}.
   *
   * <ul>
   *   <li>Then {@link PropertySourceCustom#PropertySourceCustom()} PropertiesWithDefaults size is
   *       one.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#addProperty(DBPPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.addProperty(DBPPropertyDescriptor)"})
  public void testAddProperty_thenPropertySourceCustomPropertiesWithDefaultsSizeIsOne() {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();
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
    ProxyPropertyDescriptor property = new ProxyPropertyDescriptor(original);

    // Act
    propertySourceCustom.addProperty(property);

    // Assert
    assertEquals(1, propertySourceCustom.getPropertiesWithDefaults().size());
    DBPPropertyDescriptor[] properties = propertySourceCustom.getProperties();
    assertEquals(1, properties.length);
    assertSame(property, properties[0]);
  }

  /**
   * Test {@link PropertySourceCustom#addProperties(Collection)} with {@code Collection}.
   *
   * <p>Method under test: {@link PropertySourceCustom#addProperties(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.addProperties(Collection)"})
  public void testAddPropertiesWithCollection() {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();

    ArrayList<DBPPropertyDescriptor> properties = new ArrayList<>();
    Class<Object> type = Object.class;
    Object[] validValues = new Object[] {DBPEvent.RENAME};

    PropertyDescriptor propertyDescriptor =
        new PropertyDescriptor(
            "Category",
            "42",
            "Name",
            "The characteristics of someone or something",
            true,
            type,
            DBPEvent.RENAME,
            validValues);
    properties.add(propertyDescriptor);

    // Act
    propertySourceCustom.addProperties(properties);

    // Assert
    assertEquals(1, propertySourceCustom.getPropertiesWithDefaults().size());
    DBPPropertyDescriptor[] properties2 = propertySourceCustom.getProperties();
    assertEquals(1, properties2.length);
    assertSame(propertyDescriptor, properties2[0]);
  }

  /**
   * Test {@link PropertySourceCustom#addProperties(Collection)} with {@code Collection}.
   *
   * <p>Method under test: {@link PropertySourceCustom#addProperties(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.addProperties(Collection)"})
  public void testAddPropertiesWithCollection2() {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();

    ArrayList<DBPPropertyDescriptor> properties = new ArrayList<>();
    Class<Object> type = Object.class;
    Object[] validValues = new Object[] {DBPEvent.RENAME};

    PropertyDescriptor propertyDescriptor =
        new PropertyDescriptor(
            "Category",
            "42",
            "Name",
            "The characteristics of someone or something",
            true,
            type,
            null,
            validValues);
    properties.add(propertyDescriptor);

    // Act
    propertySourceCustom.addProperties(properties);

    // Assert
    DBPPropertyDescriptor[] properties2 = propertySourceCustom.getProperties();
    assertEquals(1, properties2.length);
    assertTrue(propertySourceCustom.getPropertiesWithDefaults().isEmpty());
    assertSame(propertyDescriptor, properties2[0]);
  }

  /**
   * Test {@link PropertySourceCustom#addProperties(Collection)} with {@code Collection}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#addProperties(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.addProperties(Collection)"})
  public void testAddPropertiesWithCollection_whenArrayList_thenArrayLengthIsZero() {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();

    // Act
    propertySourceCustom.addProperties(new ArrayList<>());

    // Assert that nothing has changed
    assertEquals(0, propertySourceCustom.getProperties().length);
    assertTrue(propertySourceCustom.getPropertiesWithDefaults().isEmpty());
  }

  /**
   * Test {@link PropertySourceCustom#addProperties(DBPPropertyDescriptor[])} with {@code
   * DBPPropertyDescriptor[]}.
   *
   * <p>Method under test: {@link PropertySourceCustom#addProperties(DBPPropertyDescriptor[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.addProperties(DBPPropertyDescriptor[])"})
  public void testAddPropertiesWithDBPPropertyDescriptor() throws InvalidRegistryObjectException {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);
    ProxyPropertyDescriptor proxyPropertyDescriptor = new ProxyPropertyDescriptor(original);

    // Act
    propertySourceCustom.addProperties(new DBPPropertyDescriptor[] {proxyPropertyDescriptor});

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    Map<String, Object> propertiesWithDefaults = propertySourceCustom.getPropertiesWithDefaults();
    assertEquals(1, propertiesWithDefaults.size());
    assertEquals("Attribute", propertiesWithDefaults.get("Attribute"));
    DBPPropertyDescriptor[] properties = propertySourceCustom.getProperties();
    assertEquals(1, properties.length);
    assertSame(proxyPropertyDescriptor, properties[0]);
  }

  /**
   * Test {@link PropertySourceCustom#addProperties(DBPPropertyDescriptor[])} with {@code
   * DBPPropertyDescriptor[]}.
   *
   * <p>Method under test: {@link PropertySourceCustom#addProperties(DBPPropertyDescriptor[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCustom.addProperties(DBPPropertyDescriptor[])"})
  public void testAddPropertiesWithDBPPropertyDescriptor2() throws InvalidRegistryObjectException {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();

    IConfigurationElement config = mock(IConfigurationElement.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn(null);
    PropertyDescriptor original = new PropertyDescriptor("Category", config);
    ProxyPropertyDescriptor proxyPropertyDescriptor = new ProxyPropertyDescriptor(original);

    // Act
    propertySourceCustom.addProperties(new DBPPropertyDescriptor[] {proxyPropertyDescriptor});

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    DBPPropertyDescriptor[] properties = propertySourceCustom.getProperties();
    assertEquals(1, properties.length);
    assertTrue(propertySourceCustom.getPropertiesWithDefaults().isEmpty());
    assertSame(proxyPropertyDescriptor, properties[0]);
  }

  /**
   * Test {@link PropertySourceCustom#getEditableValue()}.
   *
   * <p>Method under test: {@link PropertySourceCustom#getEditableValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertySourceCustom.getEditableValue()"})
  public void testGetEditableValue() {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();

    // Act
    Object actualEditableValue = propertySourceCustom.getEditableValue();

    // Assert
    assertSame(propertySourceCustom, actualEditableValue);
  }

  /**
   * Test {@link PropertySourceCustom#getProperties()}.
   *
   * <p>Method under test: {@link PropertySourceCustom#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPPropertyDescriptor[] PropertySourceCustom.getProperties()"})
  public void testGetProperties() {
    // Arrange, Act and Assert
    assertEquals(0, new PropertySourceCustom().getProperties().length);
  }

  /**
   * Test {@link PropertySourceCustom#getPropertyValue(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#getPropertyValue(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertySourceCustom.getPropertyValue(DBRProgressMonitor, String)"})
  public void testGetPropertyValue_when42() {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();

    // Act and Assert
    assertNull(propertySourceCustom.getPropertyValue(new LoggingProgressMonitor(), "42"));
  }

  /**
   * Test {@link PropertySourceCustom#getPropertyValue(DBRProgressMonitor, String)}.
   *
   * <ul>
   *   <li>When {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#getPropertyValue(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertySourceCustom.getPropertyValue(DBRProgressMonitor, String)"})
  public void testGetPropertyValue_whenNull() {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();

    // Act and Assert
    assertNull(propertySourceCustom.getPropertyValue(new LoggingProgressMonitor(), null));
  }

  /**
   * Test {@link PropertySourceCustom#isPropertyResettable(String)}.
   *
   * <p>Method under test: {@link PropertySourceCustom#isPropertyResettable(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceCustom.isPropertyResettable(String)"})
  public void testIsPropertyResettable() {
    // Arrange, Act and Assert
    assertTrue(new PropertySourceCustom().isPropertyResettable("42"));
  }

  /**
   * Test {@link PropertySourceCustom#isPropertySet(String)}.
   *
   * <p>Method under test: {@link PropertySourceCustom#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceCustom.isPropertySet(String)"})
  public void testIsPropertySet() {
    // Arrange
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
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};
    PropertySourceCustom propertySourceCustom =
        new PropertySourceCustom(properties, new HashMap<>());

    // Act and Assert
    assertFalse(propertySourceCustom.isPropertySet("42"));
  }

  /**
   * Test {@link PropertySourceCustom#isPropertySet(String)}.
   *
   * <p>Method under test: {@link PropertySourceCustom#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceCustom.isPropertySet(String)"})
  public void testIsPropertySet2() {
    // Arrange
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
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};

    PropertySourceCustom propertySourceCustom =
        new PropertySourceCustom(properties, new HashMap<>());
    propertySourceCustom.setDefValueResolver(mock(IVariableResolver.class));

    // Act and Assert
    assertFalse(propertySourceCustom.isPropertySet("42"));
  }

  /**
   * Test {@link PropertySourceCustom#isPropertySet(String)}.
   *
   * <p>Method under test: {@link PropertySourceCustom#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceCustom.isPropertySet(String)"})
  public void testIsPropertySet3() {
    // Arrange
    HashMap<String, Object> values = new HashMap<>();
    values.put("42", DBPEvent.RENAME);
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
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};

    PropertySourceCustom propertySourceCustom = new PropertySourceCustom(properties, values);
    propertySourceCustom.setDefaultValues(new HashMap<>());

    // Act and Assert
    assertTrue(propertySourceCustom.isPropertySet("42"));
  }

  /**
   * Test {@link PropertySourceCustom#isPropertySet(String)}.
   *
   * <p>Method under test: {@link PropertySourceCustom#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceCustom.isPropertySet(String)"})
  public void testIsPropertySet4() {
    // Arrange
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
            "42",
            validValues);
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};

    PropertySourceCustom propertySourceCustom =
        new PropertySourceCustom(properties, new HashMap<>());
    propertySourceCustom.setDefValueResolver(mock(IVariableResolver.class));

    // Act and Assert
    assertFalse(propertySourceCustom.isPropertySet("42"));
  }

  /**
   * Test {@link PropertySourceCustom#isPropertySet(String)}.
   *
   * <p>Method under test: {@link PropertySourceCustom#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceCustom.isPropertySet(String)"})
  public void testIsPropertySet5() {
    // Arrange
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
            "",
            validValues);
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};

    PropertySourceCustom propertySourceCustom =
        new PropertySourceCustom(properties, new HashMap<>());
    propertySourceCustom.setDefValueResolver(mock(IVariableResolver.class));

    // Act and Assert
    assertFalse(propertySourceCustom.isPropertySet("42"));
  }

  /**
   * Test {@link PropertySourceCustom#isPropertySet(String)}.
   *
   * <p>Method under test: {@link PropertySourceCustom#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceCustom.isPropertySet(String)"})
  public void testIsPropertySet6() {
    // Arrange
    HashMap<String, Object> values = new HashMap<>();
    values.put("42", DBPEvent.RENAME);
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
            DBPEvent.REORDER,
            validValues);
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};

    PropertySourceCustom propertySourceCustom = new PropertySourceCustom(properties, values);

    // Act and Assert
    assertTrue(propertySourceCustom.isPropertySet("42"));
  }

  /**
   * Test {@link PropertySourceCustom#isPropertySet(String)}.
   *
   * <ul>
   *   <li>Given {@link HashMap#HashMap()} {@code 42} is {@link DBPEvent#RENAME}.
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceCustom.isPropertySet(String)"})
  public void testIsPropertySet_givenHashMap42IsRename_when42_thenReturnFalse() {
    // Arrange
    HashMap<String, Object> values = new HashMap<>();
    values.put("42", DBPEvent.RENAME);
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
    DBPPropertyDescriptor[] properties =
        new DBPPropertyDescriptor[] {new ProxyPropertyDescriptor(original)};

    PropertySourceCustom propertySourceCustom = new PropertySourceCustom(properties, values);

    // Act and Assert
    assertFalse(propertySourceCustom.isPropertySet("42"));
  }

  /**
   * Test {@link PropertySourceCustom#isPropertySet(String)}.
   *
   * <ul>
   *   <li>Given {@link PropertySourceCustom#PropertySourceCustom()}.
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceCustom.isPropertySet(String)"})
  public void testIsPropertySet_givenPropertySourceCustom_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new PropertySourceCustom().isPropertySet("42"));
  }

  /**
   * Test {@link PropertySourceCustom#isPropertySet(String)}.
   *
   * <ul>
   *   <li>Given {@link PropertySourceCustom#PropertySourceCustom()}.
   *   <li>When {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceCustom.isPropertySet(String)"})
  public void testIsPropertySet_givenPropertySourceCustom_whenNull_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(new PropertySourceCustom().isPropertySet(null));
  }

  /**
   * Test {@link PropertySourceCustom#setPropertyValue(DBRProgressMonitor, String, Object)}.
   *
   * <ul>
   *   <li>Then {@link PropertySourceCustom#PropertySourceCustom()} PropertiesWithDefaults {@code
   *       42} is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#setPropertyValue(DBRProgressMonitor, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceCustom.setPropertyValue(DBRProgressMonitor, String, Object)"
  })
  public void testSetPropertyValue_thenPropertySourceCustomPropertiesWithDefaults42IsNull() {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();

    // Act
    propertySourceCustom.setPropertyValue(new LoggingProgressMonitor(), "42", null);

    // Assert
    Map<String, Object> propertiesWithDefaults = propertySourceCustom.getPropertiesWithDefaults();
    assertEquals(1, propertiesWithDefaults.size());
    assertNull(propertiesWithDefaults.get("42"));
  }

  /**
   * Test {@link PropertySourceCustom#setPropertyValue(DBRProgressMonitor, String, Object)}.
   *
   * <ul>
   *   <li>Then {@link PropertySourceCustom#PropertySourceCustom()} PropertiesWithDefaults {@code
   *       42} is {@link DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCustom#setPropertyValue(DBRProgressMonitor, String,
   * Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceCustom.setPropertyValue(DBRProgressMonitor, String, Object)"
  })
  public void testSetPropertyValue_thenPropertySourceCustomPropertiesWithDefaults42IsRename() {
    // Arrange
    PropertySourceCustom propertySourceCustom = new PropertySourceCustom();
    Object object = DBPEvent.RENAME;

    // Act
    propertySourceCustom.setPropertyValue(new LoggingProgressMonitor(), "42", object);

    // Assert
    Map<String, Object> propertiesWithDefaults = propertySourceCustom.getPropertiesWithDefaults();
    assertEquals(1, propertiesWithDefaults.size());
    assertSame(object, propertiesWithDefaults.get("42"));
  }
}
