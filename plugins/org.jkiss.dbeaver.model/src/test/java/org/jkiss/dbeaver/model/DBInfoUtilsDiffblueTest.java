package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import org.jkiss.dbeaver.model.connection.LocalNativeClientLocation;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.impl.ProxyPropertyDescriptor;
import org.jkiss.dbeaver.model.impl.data.DBDDocumentXML;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.jkiss.dbeaver.runtime.properties.PropertyCollector;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBInfoUtilsDiffblueTest {
  /**
   * Test {@link DBInfoUtils#getPropertyString(PropertyCollector, DBPPropertyDescriptor)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link DBInfoUtils#getPropertyString(PropertyCollector,
   * DBPPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBInfoUtils.getPropertyString(PropertyCollector, DBPPropertyDescriptor)"
  })
  public void testGetPropertyString_givenEmptyString() {
    // Arrange
    PropertyCollector collector = new PropertyCollector(DBPEvent.RENAME, true);
    collector.addProperty("Category", "42", "Name", "");
    collector.addProperty("42", "Id", "42", DBPEvent.RENAME);
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
    String actualPropertyString =
        DBInfoUtils.getPropertyString(collector, new ProxyPropertyDescriptor(original));

    // Assert
    assertNull(actualPropertyString);
  }

  /**
   * Test {@link DBInfoUtils#getPropertyString(PropertyCollector, DBPPropertyDescriptor)}.
   *
   * <ul>
   *   <li>Given {@link LocalNativeClientLocation#LocalNativeClientLocation(String, String)} with id
   *       is {@code 42} and path is {@code java.lang}.
   * </ul>
   *
   * <p>Method under test: {@link DBInfoUtils#getPropertyString(PropertyCollector,
   * DBPPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBInfoUtils.getPropertyString(PropertyCollector, DBPPropertyDescriptor)"
  })
  public void testGetPropertyString_givenLocalNativeClientLocationWithIdIs42AndPathIsJavaLang() {
    // Arrange
    PropertyCollector collector = new PropertyCollector(DBPEvent.RENAME, true);
    collector.addProperty(
        "Category", "42", "Name", new LocalNativeClientLocation("42", "java.lang"));
    collector.addProperty("42", "Id", "42", DBPEvent.RENAME);
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
    String actualPropertyString =
        DBInfoUtils.getPropertyString(collector, new ProxyPropertyDescriptor(original));

    // Assert
    assertEquals("42", actualPropertyString);
  }

  /**
   * Test {@link DBInfoUtils#getPropertyString(PropertyCollector, DBPPropertyDescriptor)}.
   *
   * <ul>
   *   <li>Then return {@code []}.
   * </ul>
   *
   * <p>Method under test: {@link DBInfoUtils#getPropertyString(PropertyCollector,
   * DBPPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBInfoUtils.getPropertyString(PropertyCollector, DBPPropertyDescriptor)"
  })
  public void testGetPropertyString_thenReturnLeftSquareBracketRightSquareBracket() {
    // Arrange
    PropertyCollector collector = new PropertyCollector(DBPEvent.RENAME, true);
    collector.addProperty("Category", "42", "Name", new ArrayList<>());
    collector.addProperty("42", "Id", "42", DBPEvent.RENAME);
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
    String actualPropertyString =
        DBInfoUtils.getPropertyString(collector, new ProxyPropertyDescriptor(original));

    // Assert
    assertEquals("[]", actualPropertyString);
  }

  /**
   * Test {@link DBInfoUtils#getPropertyString(PropertyCollector, DBPPropertyDescriptor)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DBInfoUtils#getPropertyString(PropertyCollector,
   * DBPPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBInfoUtils.getPropertyString(PropertyCollector, DBPPropertyDescriptor)"
  })
  public void testGetPropertyString_thenReturnNull() {
    // Arrange
    PropertyCollector collector = new PropertyCollector(DBPEvent.RENAME, true);
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
    String actualPropertyString =
        DBInfoUtils.getPropertyString(collector, new ProxyPropertyDescriptor(original));

    // Assert
    assertNull(actualPropertyString);
  }

  /**
   * Test {@link DBInfoUtils#getPropertyString(PropertyCollector, DBPPropertyDescriptor)}.
   *
   * <ul>
   *   <li>Then return {@link DBConstants#NULL_VALUE_LABEL}.
   * </ul>
   *
   * <p>Method under test: {@link DBInfoUtils#getPropertyString(PropertyCollector,
   * DBPPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBInfoUtils.getPropertyString(PropertyCollector, DBPPropertyDescriptor)"
  })
  public void testGetPropertyString_thenReturnNull_value_label() {
    // Arrange
    PropertyCollector collector = new PropertyCollector(DBPEvent.RENAME, true);
    collector.addProperty("Category", "42", "Name", new DBDDocumentXML(null));
    collector.addProperty("42", "Id", "42", DBPEvent.RENAME);
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
    String actualPropertyString =
        DBInfoUtils.getPropertyString(collector, new ProxyPropertyDescriptor(original));

    // Assert
    assertEquals(DBConstants.NULL_VALUE_LABEL, actualPropertyString);
  }

  /**
   * Test {@link DBInfoUtils#getPropertyString(PropertyCollector, DBPPropertyDescriptor)}.
   *
   * <ul>
   *   <li>When {@link PropertyCollector#addProperty(String, String, String, Object)} with {@code
   *       Category} and id is {@code 42} and {@code Name} and value is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link DBInfoUtils#getPropertyString(PropertyCollector,
   * DBPPropertyDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DBInfoUtils.getPropertyString(PropertyCollector, DBPPropertyDescriptor)"
  })
  public void testGetPropertyString_whenAddPropertyWithCategoryAndIdIs42AndNameAndValueIs42() {
    // Arrange
    PropertyCollector collector = new PropertyCollector(DBPEvent.RENAME, true);
    collector.addProperty("Category", "42", "Name", "42");
    collector.addProperty("42", "Id", "42", DBPEvent.RENAME);
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
    String actualPropertyString =
        DBInfoUtils.getPropertyString(collector, new ProxyPropertyDescriptor(original));

    // Assert
    assertEquals("42", actualPropertyString);
  }
}
