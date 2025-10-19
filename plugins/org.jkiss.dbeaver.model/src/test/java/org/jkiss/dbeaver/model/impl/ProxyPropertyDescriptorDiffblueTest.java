package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.meta.PropertyLength;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ProxyPropertyDescriptorDiffblueTest {
  /**
   * Test {@link ProxyPropertyDescriptor#getId()}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#getId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ProxyPropertyDescriptor.getId()"})
  public void testGetId_givenJavaLangObject_thenReturn42() {
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

    // Act and Assert
    assertEquals("42", new ProxyPropertyDescriptor(original).getId());
  }

  /**
   * Test {@link ProxyPropertyDescriptor#getCategory()}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code Category}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#getCategory()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ProxyPropertyDescriptor.getCategory()"})
  public void testGetCategory_givenJavaLangObject_thenReturnCategory() {
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

    // Act and Assert
    assertEquals("Category", new ProxyPropertyDescriptor(original).getCategory());
  }

  /**
   * Test {@link ProxyPropertyDescriptor#getDescription()}.
   *
   * <ul>
   *   <li>Then return {@code The characteristics of someone or something}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#getDescription()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ProxyPropertyDescriptor.getDescription()"})
  public void testGetDescription_thenReturnTheCharacteristicsOfSomeoneOrSomething() {
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

    // Act and Assert
    assertEquals(
        "The characteristics of someone or something",
        new ProxyPropertyDescriptor(original).getDescription());
  }

  /**
   * Test {@link ProxyPropertyDescriptor#getHint()}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#getHint()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ProxyPropertyDescriptor.getHint()"})
  public void testGetHint_givenJavaLangObject_thenReturnNull() {
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

    // Act and Assert
    assertNull(new ProxyPropertyDescriptor(original).getHint());
  }

  /**
   * Test {@link ProxyPropertyDescriptor#getDataType()}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#getDataType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class ProxyPropertyDescriptor.getDataType()"})
  public void testGetDataType_givenJavaLangObject_thenReturnObject() {
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

    // Act
    Class<?> actualDataType = new ProxyPropertyDescriptor(original).getDataType();

    // Assert
    Class<Object> expectedDataType = Object.class;
    assertEquals(expectedDataType, actualDataType);
  }

  /**
   * Test {@link ProxyPropertyDescriptor#isRequired()}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#isRequired()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProxyPropertyDescriptor.isRequired()"})
  public void testIsRequired_thenReturnFalse() {
    // Arrange
    Class<Object> type = Object.class;
    Object[] validValues = new Object[] {DBPEvent.RENAME};

    PropertyDescriptor original =
        new PropertyDescriptor(
            "Category",
            "42",
            "Name",
            "The characteristics of someone or something",
            false,
            type,
            DBPEvent.RENAME,
            validValues);

    // Act and Assert
    assertFalse(new ProxyPropertyDescriptor(original).isRequired());
  }

  /**
   * Test {@link ProxyPropertyDescriptor#isRequired()}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#isRequired()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProxyPropertyDescriptor.isRequired()"})
  public void testIsRequired_thenReturnTrue() {
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

    // Act and Assert
    assertTrue(new ProxyPropertyDescriptor(original).isRequired());
  }

  /**
   * Test {@link ProxyPropertyDescriptor#getDefaultValue()}.
   *
   * <ul>
   *   <li>Then return {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#getDefaultValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object ProxyPropertyDescriptor.getDefaultValue()"})
  public void testGetDefaultValue_thenReturnAttribute() {
    // Arrange
    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);

    // Act
    Object actualDefaultValue = new ProxyPropertyDescriptor(original).getDefaultValue();

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    assertEquals("Attribute", actualDefaultValue);
  }

  /**
   * Test {@link ProxyPropertyDescriptor#isEditable(Object)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#isEditable(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProxyPropertyDescriptor.isEditable(Object)"})
  public void testIsEditable_givenJavaLangObject_thenReturnTrue() {
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

    // Act and Assert
    assertTrue(new ProxyPropertyDescriptor(original).isEditable(DBPEvent.RENAME));
  }

  /**
   * Test {@link ProxyPropertyDescriptor#getLength()}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code LONG}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#getLength()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"PropertyLength ProxyPropertyDescriptor.getLength()"})
  public void testGetLength_givenJavaLangObject_thenReturnLong() {
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

    // Act and Assert
    assertEquals(PropertyLength.LONG, new ProxyPropertyDescriptor(original).getLength());
  }

  /**
   * Test {@link ProxyPropertyDescriptor#getFeatures()}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return array of {@link String} with {@code required}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#getFeatures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] ProxyPropertyDescriptor.getFeatures()"})
  public void testGetFeatures_givenJavaLangObject_thenReturnArrayOfStringWithRequired() {
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

    // Act and Assert
    assertArrayEquals(
        new String[] {"required"}, new ProxyPropertyDescriptor(original).getFeatures());
  }

  /**
   * Test {@link ProxyPropertyDescriptor#getRequiredFeatures()}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#getRequiredFeatures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] ProxyPropertyDescriptor.getRequiredFeatures()"})
  public void testGetRequiredFeatures_givenJavaLangObject_thenReturnNull() {
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

    // Act and Assert
    assertNull(new ProxyPropertyDescriptor(original).getRequiredFeatures());
  }

  /**
   * Test {@link ProxyPropertyDescriptor#hasFeature(String)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#hasFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ProxyPropertyDescriptor.hasFeature(String)"})
  public void testHasFeature_givenJavaLangObject_thenReturnFalse() {
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

    // Act and Assert
    assertFalse(new ProxyPropertyDescriptor(original).hasFeature("Feature"));
  }

  /**
   * Test {@link ProxyPropertyDescriptor#getDisplayName()}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link ProxyPropertyDescriptor#getDisplayName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ProxyPropertyDescriptor.getDisplayName()"})
  public void testGetDisplayName_givenJavaLangObject_thenReturnName() {
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

    // Act and Assert
    assertEquals("Name", new ProxyPropertyDescriptor(original).getDisplayName());
  }
}
