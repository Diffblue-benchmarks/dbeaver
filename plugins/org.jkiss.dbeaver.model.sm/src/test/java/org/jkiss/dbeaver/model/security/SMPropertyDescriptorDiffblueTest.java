package org.jkiss.dbeaver.model.security;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.meta.PropertyLength;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMPropertyDescriptorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMPropertyDescriptor#SMPropertyDescriptor()}
   *   <li>{@link SMPropertyDescriptor#setCategory(String)}
   *   <li>{@link SMPropertyDescriptor#setDescription(String)}
   *   <li>{@link SMPropertyDescriptor#setDisplayName(String)}
   *   <li>{@link SMPropertyDescriptor#setFeatures(String[])}
   *   <li>{@link SMPropertyDescriptor#setId(String)}
   *   <li>{@link SMPropertyDescriptor#setLength(PropertyLength)}
   *   <li>{@link SMPropertyDescriptor#setRequired(boolean)}
   *   <li>{@link SMPropertyDescriptor#getCategory()}
   *   <li>{@link SMPropertyDescriptor#getDescription()}
   *   <li>{@link SMPropertyDescriptor#getDisplayName()}
   *   <li>{@link SMPropertyDescriptor#getFeatures()}
   *   <li>{@link SMPropertyDescriptor#getId()}
   *   <li>{@link SMPropertyDescriptor#getLength()}
   *   <li>{@link SMPropertyDescriptor#isRequired()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMPropertyDescriptor.<init>()",
    "String SMPropertyDescriptor.getCategory()",
    "String SMPropertyDescriptor.getDescription()",
    "String SMPropertyDescriptor.getDisplayName()",
    "String[] SMPropertyDescriptor.getFeatures()",
    "String SMPropertyDescriptor.getId()",
    "PropertyLength SMPropertyDescriptor.getLength()",
    "boolean SMPropertyDescriptor.isRequired()",
    "void SMPropertyDescriptor.setCategory(String)",
    "void SMPropertyDescriptor.setDescription(String)",
    "void SMPropertyDescriptor.setDisplayName(String)",
    "void SMPropertyDescriptor.setFeatures(String[])",
    "void SMPropertyDescriptor.setId(String)",
    "void SMPropertyDescriptor.setLength(PropertyLength)",
    "void SMPropertyDescriptor.setRequired(boolean)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    SMPropertyDescriptor actualSmPropertyDescriptor = new SMPropertyDescriptor();
    actualSmPropertyDescriptor.setCategory("Category");
    actualSmPropertyDescriptor.setDescription("The characteristics of someone or something");
    actualSmPropertyDescriptor.setDisplayName("Display Name");
    String[] features = new String[] {"Features"};
    actualSmPropertyDescriptor.setFeatures(features);
    actualSmPropertyDescriptor.setId("42");
    actualSmPropertyDescriptor.setLength(PropertyLength.TINY);
    actualSmPropertyDescriptor.setRequired(true);
    String actualCategory = actualSmPropertyDescriptor.getCategory();
    String actualDescription = actualSmPropertyDescriptor.getDescription();
    String actualDisplayName = actualSmPropertyDescriptor.getDisplayName();
    String[] actualFeatures = actualSmPropertyDescriptor.getFeatures();
    String actualId = actualSmPropertyDescriptor.getId();
    PropertyLength actualLength = actualSmPropertyDescriptor.getLength();

    // Assert
    assertEquals("42", actualId);
    assertEquals("Category", actualCategory);
    assertEquals("Display Name", actualDisplayName);
    assertEquals("The characteristics of someone or something", actualDescription);
    assertEquals(PropertyLength.TINY, actualLength);
    assertTrue(actualSmPropertyDescriptor.isRequired());
    assertSame(features, actualFeatures);
    assertArrayEquals(new String[] {"Features"}, actualFeatures);
  }

  /**
   * Test {@link SMPropertyDescriptor#SMPropertyDescriptor(DBPPropertyDescriptor)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return Id is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link SMPropertyDescriptor#SMPropertyDescriptor(DBPPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMPropertyDescriptor.<init>(DBPPropertyDescriptor)"})
  public void testNewSMPropertyDescriptor_whenJavaLangObject_thenReturnIdIs42() {
    // Arrange
    Class<Object> type = Object.class;
    Object[] validValues = new Object[] {"Valid Values"};

    PropertyDescriptor descriptor =
        new PropertyDescriptor(
            "Category",
            "42",
            "Name",
            "The characteristics of someone or something",
            true,
            type,
            "Default Value",
            validValues);

    // Act
    SMPropertyDescriptor actualSmPropertyDescriptor = new SMPropertyDescriptor(descriptor);

    // Assert
    assertEquals("42", actualSmPropertyDescriptor.getId());
    assertEquals("Name", actualSmPropertyDescriptor.getDisplayName());
    assertEquals(
        "The characteristics of someone or something", actualSmPropertyDescriptor.getDescription());
    assertNull(actualSmPropertyDescriptor.getCategory());
    assertEquals(PropertyLength.LONG, actualSmPropertyDescriptor.getLength());
    assertTrue(actualSmPropertyDescriptor.isRequired());
    assertArrayEquals(new String[] {"required"}, actualSmPropertyDescriptor.getFeatures());
  }
}
