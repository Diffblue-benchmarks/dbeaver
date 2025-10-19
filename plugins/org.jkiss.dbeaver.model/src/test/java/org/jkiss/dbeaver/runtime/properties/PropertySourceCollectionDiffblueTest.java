package org.jkiss.dbeaver.runtime.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class PropertySourceCollectionDiffblueTest {
  /**
   * Test {@link PropertySourceCollection#PropertySourceCollection(Collection)}.
   *
   * <ul>
   *   <li>Given {@link DBPEvent#RENAME}.
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCollection#PropertySourceCollection(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCollection.<init>(Collection)"})
  public void testNewPropertySourceCollection_givenRename_thenReturnArrayLengthIsOne() {
    // Arrange
    ArrayList<Object> collection = new ArrayList<>();
    collection.add(DBPEvent.RENAME);

    // Act and Assert
    assertEquals(1, new PropertySourceCollection(collection).getProperties().length);
  }

  /**
   * Test {@link PropertySourceCollection#PropertySourceCollection(Collection)}.
   *
   * <ul>
   *   <li>Given {@link DBPEvent#RENAME}.
   *   <li>Then return array length is two.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCollection#PropertySourceCollection(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCollection.<init>(Collection)"})
  public void testNewPropertySourceCollection_givenRename_thenReturnArrayLengthIsTwo() {
    // Arrange
    ArrayList<Object> collection = new ArrayList<>();
    collection.add(DBPEvent.RENAME);
    collection.add(DBPEvent.RENAME);

    // Act and Assert
    assertEquals(2, new PropertySourceCollection(collection).getProperties().length);
  }

  /**
   * Test {@link PropertySourceCollection#PropertySourceCollection(Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link PropertySourceCollection#PropertySourceCollection(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertySourceCollection.<init>(Collection)"})
  public void testNewPropertySourceCollection_whenArrayList_thenReturnArrayLengthIsZero() {
    // Arrange, Act and Assert
    assertEquals(0, new PropertySourceCollection(new ArrayList<>()).getProperties().length);
  }

  /**
   * Test {@link PropertySourceCollection#getEditableValue()}.
   *
   * <p>Method under test: {@link PropertySourceCollection#getEditableValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertySourceCollection.getEditableValue()"})
  public void testGetEditableValue() {
    // Arrange
    PropertySourceCollection propertySourceCollection =
        new PropertySourceCollection(new ArrayList<>());

    // Act
    Object actualEditableValue = propertySourceCollection.getEditableValue();

    // Assert
    assertTrue(actualEditableValue instanceof PropertySourceCollection);
    assertEquals(0, ((PropertySourceCollection) actualEditableValue).getProperties().length);
    assertSame(propertySourceCollection, actualEditableValue);
  }

  /**
   * Test {@link PropertySourceCollection#getProperties()}.
   *
   * <p>Method under test: {@link PropertySourceCollection#getProperties()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor[] PropertySourceCollection.getProperties()"
  })
  public void testGetProperties() {
    // Arrange, Act and Assert
    assertEquals(0, new PropertySourceCollection(new ArrayList<>()).getProperties().length);
  }

  /**
   * Test {@link PropertySourceCollection#isPropertySet(String)}.
   *
   * <p>Method under test: {@link PropertySourceCollection#isPropertySet(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceCollection.isPropertySet(String)"})
  public void testIsPropertySet() {
    // Arrange, Act and Assert
    assertFalse(new PropertySourceCollection(new ArrayList<>()).isPropertySet("42"));
  }

  /**
   * Test {@link PropertySourceCollection#isPropertyResettable(String)}.
   *
   * <p>Method under test: {@link PropertySourceCollection#isPropertyResettable(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertySourceCollection.isPropertyResettable(String)"})
  public void testIsPropertyResettable() {
    // Arrange, Act and Assert
    assertFalse(new PropertySourceCollection(new ArrayList<>()).isPropertyResettable("42"));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link PropertySourceCollection#resetPropertyValue(DBRProgressMonitor, String)}
   *   <li>{@link PropertySourceCollection#resetPropertyValueToDefault(String)}
   *   <li>{@link PropertySourceCollection#setPropertyValue(DBRProgressMonitor, String, Object)}
   *   <li>{@link PropertySourceCollection#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertySourceCollection.resetPropertyValue(DBRProgressMonitor, String)",
    "void PropertySourceCollection.resetPropertyValueToDefault(String)",
    "void PropertySourceCollection.setPropertyValue(DBRProgressMonitor, String, Object)",
    "String PropertySourceCollection.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    PropertySourceCollection propertySourceCollection =
        new PropertySourceCollection(new ArrayList<>());

    // Act
    propertySourceCollection.resetPropertyValue(new LoggingProgressMonitor(), "42");
    propertySourceCollection.resetPropertyValueToDefault("42");
    propertySourceCollection.setPropertyValue(new LoggingProgressMonitor(), "42", DBPEvent.RENAME);

    // Assert
    assertEquals("[...]", propertySourceCollection.toString());
  }
}
