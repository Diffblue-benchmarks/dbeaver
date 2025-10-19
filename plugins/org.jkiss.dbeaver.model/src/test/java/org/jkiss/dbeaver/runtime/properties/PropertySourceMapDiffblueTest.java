package org.jkiss.dbeaver.runtime.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PropertySourceMapDiffblueTest {
  /**
   * Test {@link PropertySourceMap#PropertySourceMap(Map)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceMap#PropertySourceMap(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceMap.<init>(Map)"})
  public void testNewPropertySourceMap_givenFoo_thenReturnArrayLengthIsOne() {
    // Arrange
    HashMap<String, Object> map = new HashMap<>();
    map.put("foo", DBPEvent.RENAME);

    // Act and Assert
    assertEquals(1, new PropertySourceMap(map).getProperties().length);
  }

  /**
   * Test {@link PropertySourceMap#PropertySourceMap(Map)}.
   *
   * <ul>
   *   <li>When {@link HashMap#HashMap()}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceMap#PropertySourceMap(Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceMap.<init>(Map)"})
  public void testNewPropertySourceMap_whenHashMap_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, new PropertySourceMap(new HashMap<>()).getProperties().length);
  }

  /**
   * Test {@link PropertySourceMap#getEditableValue()}.
   *
   * <p>Method under test: {@link PropertySourceMap#getEditableValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertySourceMap.getEditableValue()"})
  public void testGetEditableValue() {
    // Arrange
    PropertySourceMap propertySourceMap = new PropertySourceMap(new HashMap<>());

    // Act
    Object actualEditableValue = propertySourceMap.getEditableValue();

    // Assert
    assertTrue(actualEditableValue instanceof PropertySourceMap);
    assertEquals(0, ((PropertySourceMap) actualEditableValue).getProperties().length);
    assertSame(propertySourceMap, actualEditableValue);
  }

  /**
   * Test {@link PropertySourceMap#getProperties()}.
   *
   * <p>Method under test: {@link PropertySourceMap#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor[] PropertySourceMap.getProperties()"
  })
  public void testGetProperties() {
    // Arrange, Act and Assert
    assertEquals(0, new PropertySourceMap(new HashMap<>()).getProperties().length);
  }

  /**
   * Test {@link PropertySourceMap#getPropertyValue(DBRProgressMonitor, String)}.
   *
   * <p>Method under test: {@link PropertySourceMap#getPropertyValue(DBRProgressMonitor, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertySourceMap.getPropertyValue(DBRProgressMonitor, String)"})
  public void testGetPropertyValue() {
    // Arrange
    PropertySourceMap propertySourceMap = new PropertySourceMap(new HashMap<>());

    // Act and Assert
    assertNull(propertySourceMap.getPropertyValue(new LoggingProgressMonitor(), "42"));
  }

  /**
   * Test {@link PropertySourceMap#isPropertySet(String)}.
   *
   * <p>Method under test: {@link PropertySourceMap#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceMap.isPropertySet(String)"})
  public void testIsPropertySet() {
    // Arrange, Act and Assert
    assertFalse(new PropertySourceMap(new HashMap<>()).isPropertySet("42"));
  }

  /**
   * Test {@link PropertySourceMap#isPropertyResettable(String)}.
   *
   * <p>Method under test: {@link PropertySourceMap#isPropertyResettable(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceMap.isPropertyResettable(String)"})
  public void testIsPropertyResettable() {
    // Arrange, Act and Assert
    assertFalse(new PropertySourceMap(new HashMap<>()).isPropertyResettable("42"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PropertySourceMap#resetPropertyValue(DBRProgressMonitor, String)}
   *   <li>{@link PropertySourceMap#resetPropertyValueToDefault(String)}
   *   <li>{@link PropertySourceMap#setPropertyValue(DBRProgressMonitor, String, Object)}
   *   <li>{@link PropertySourceMap#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceMap.resetPropertyValue(DBRProgressMonitor, String)",
    "void PropertySourceMap.resetPropertyValueToDefault(String)",
    "void PropertySourceMap.setPropertyValue(DBRProgressMonitor, String, Object)",
    "String PropertySourceMap.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    PropertySourceMap propertySourceMap = new PropertySourceMap(new HashMap<>());

    // Act
    propertySourceMap.resetPropertyValue(new LoggingProgressMonitor(), "42");
    propertySourceMap.resetPropertyValueToDefault("42");
    propertySourceMap.setPropertyValue(new LoggingProgressMonitor(), "42", DBPEvent.RENAME);

    // Assert
    assertEquals("<...>", propertySourceMap.toString());
  }
}
