package org.jkiss.dbeaver.runtime.properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.preferences.DBPPropertySource;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class ObjectAttributeDescriptorDiffblueTest {
  /**
   * Test {@link ObjectAttributeDescriptor#getObjectClass(Object)}.
   *
   * <p>Method under test: {@link ObjectAttributeDescriptor#getObjectClass(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class ObjectAttributeDescriptor.getObjectClass(Object)"})
  public void testGetObjectClass() {
    // Arrange and Act
    Class<?> actualObjectClass = ObjectAttributeDescriptor.getObjectClass(DBPEvent.RENAME);

    // Assert
    Class<Object> expectedObjectClass = Object.class;
    assertEquals(expectedObjectClass, actualObjectClass);
  }

  /**
   * Test {@link ObjectAttributeDescriptor#extractAnnotations(DBPPropertySource, Collection,
   * IPropertyFilter)} with {@code source}, {@code classList}, {@code filter}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()} add {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectAttributeDescriptor#extractAnnotations(DBPPropertySource,
   * Collection, IPropertyFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List ObjectAttributeDescriptor.extractAnnotations(DBPPropertySource, Collection, IPropertyFilter)"
  })
  public void testExtractAnnotationsWithSourceClassListFilter_whenArrayListAddObject() {
    // Arrange
    PropertySourceCustom source = new PropertySourceCustom();

    ArrayList<Class<?>> classList = new ArrayList<>();
    Class<Object> forNameResult = Object.class;
    classList.add(forNameResult);
    Class<Object> forNameResult2 = Object.class;
    classList.add(forNameResult2);

    // Act
    List<ObjectPropertyDescriptor> actualExtractAnnotationsResult =
        ObjectAttributeDescriptor.extractAnnotations(
            source, classList, mock(IPropertyFilter.class));

    // Assert
    assertTrue(actualExtractAnnotationsResult.isEmpty());
  }

  /**
   * Test {@link ObjectAttributeDescriptor#extractAnnotations(DBPPropertySource, Collection,
   * IPropertyFilter)} with {@code source}, {@code classList}, {@code filter}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ObjectAttributeDescriptor#extractAnnotations(DBPPropertySource,
   * Collection, IPropertyFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List ObjectAttributeDescriptor.extractAnnotations(DBPPropertySource, Collection, IPropertyFilter)"
  })
  public void testExtractAnnotationsWithSourceClassListFilter_whenArrayList_thenReturnEmpty() {
    // Arrange
    PropertySourceCustom source = new PropertySourceCustom();

    // Act
    List<ObjectPropertyDescriptor> actualExtractAnnotationsResult =
        ObjectAttributeDescriptor.extractAnnotations(
            source, new ArrayList<>(), mock(IPropertyFilter.class));

    // Assert
    assertTrue(actualExtractAnnotationsResult.isEmpty());
  }

  /**
   * Test {@link ObjectAttributeDescriptor#extractAnnotations(DBPPropertySource, Collection,
   * IPropertyFilter)} with {@code source}, {@code classList}, {@code filter}.
   *
   * <ul>
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectAttributeDescriptor#extractAnnotations(DBPPropertySource,
   * Collection, IPropertyFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List ObjectAttributeDescriptor.extractAnnotations(DBPPropertySource, Collection, IPropertyFilter)"
  })
  public void testExtractAnnotationsWithSourceClassListFilter_whenLinkedHashSetAddObject() {
    // Arrange
    LinkedHashSet<Class<?>> classList = new LinkedHashSet<>();
    Class<Object> forNameResult = Object.class;
    classList.add(forNameResult);

    // Act
    List<ObjectPropertyDescriptor> actualExtractAnnotationsResult =
        ObjectAttributeDescriptor.extractAnnotations(null, classList, null);

    // Assert
    assertTrue(actualExtractAnnotationsResult.isEmpty());
  }

  /**
   * Test {@link ObjectAttributeDescriptor#extractAnnotations(DBPPropertySource, Collection,
   * IPropertyFilter)} with {@code source}, {@code classList}, {@code filter}.
   *
   * <ul>
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectAttributeDescriptor#extractAnnotations(DBPPropertySource,
   * Collection, IPropertyFilter)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List ObjectAttributeDescriptor.extractAnnotations(DBPPropertySource, Collection, IPropertyFilter)"
  })
  public void testExtractAnnotationsWithSourceClassListFilter_whenLinkedHashSetAddObject2() {
    // Arrange
    PropertySourceCustom source = new PropertySourceCustom();

    LinkedHashSet<Class<?>> classList = new LinkedHashSet<>();
    Class<Object> forNameResult = Object.class;
    classList.add(forNameResult);

    // Act
    List<ObjectPropertyDescriptor> actualExtractAnnotationsResult =
        ObjectAttributeDescriptor.extractAnnotations(source, classList, null);

    // Assert
    assertTrue(actualExtractAnnotationsResult.isEmpty());
  }

  /**
   * Test {@link ObjectAttributeDescriptor#extractAnnotations(DBPPropertySource, Class,
   * IPropertyFilter, String)} with {@code source}, {@code theClass}, {@code filter}, {@code
   * locale}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ObjectAttributeDescriptor#extractAnnotations(DBPPropertySource,
   * Class, IPropertyFilter, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List ObjectAttributeDescriptor.extractAnnotations(DBPPropertySource, Class, IPropertyFilter, String)"
  })
  public void testExtractAnnotationsWithSourceTheClassFilterLocale_thenReturnEmpty() {
    // Arrange
    PropertySourceCustom source = new PropertySourceCustom();
    Class<Object> theClass = Object.class;

    // Act
    List<ObjectPropertyDescriptor> actualExtractAnnotationsResult =
        ObjectAttributeDescriptor.extractAnnotations(
            source, theClass, mock(IPropertyFilter.class), "en");

    // Assert
    assertTrue(actualExtractAnnotationsResult.isEmpty());
  }

  /**
   * Test {@link ObjectAttributeDescriptor#extractAnnotations(DBPPropertySource, Class,
   * IPropertyFilter, String)} with {@code source}, {@code theClass}, {@code filter}, {@code
   * locale}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link ObjectAttributeDescriptor#extractAnnotations(DBPPropertySource,
   * Class, IPropertyFilter, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List ObjectAttributeDescriptor.extractAnnotations(DBPPropertySource, Class, IPropertyFilter, String)"
  })
  public void testExtractAnnotationsWithSourceTheClassFilterLocale_whenNull_thenReturnEmpty() {
    // Arrange
    Class<Object> theClass = Object.class;

    // Act
    List<ObjectPropertyDescriptor> actualExtractAnnotationsResult =
        ObjectAttributeDescriptor.extractAnnotations(null, theClass, null, "en");

    // Assert
    assertTrue(actualExtractAnnotationsResult.isEmpty());
  }
}
