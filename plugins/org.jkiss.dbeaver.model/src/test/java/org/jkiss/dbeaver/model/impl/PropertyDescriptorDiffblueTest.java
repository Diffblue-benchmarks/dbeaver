package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor.PropertyType;
import org.jkiss.dbeaver.model.meta.PropertyLength;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PropertyDescriptorDiffblueTest {
  /**
   * Test {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}.
   *
   * <p>Method under test: {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPropertyDescriptor[] PropertyDescriptor.extractPropertyGroups(IConfigurationElement)"
  })
  public void testExtractPropertyGroups() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("");
    when(configurationElementHandle2.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);

    // Act
    DBPPropertyDescriptor[] actualExtractPropertyGroupsResult =
        PropertyDescriptor.extractPropertyGroups(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("label");
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getChildren("propertyGroup");
    verify(configurationElementHandle2).getName();
    DBPPropertyDescriptor dbpPropertyDescriptor = actualExtractPropertyGroupsResult[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Attribute", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Attribute", dbpPropertyDescriptor.getDescription());
    assertEquals("Attribute", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Attribute", dbpPropertyDescriptor.getHint());
    assertEquals("Attribute", dbpPropertyDescriptor.getId());
    assertEquals("Attribute", dbpPropertyDescriptor.getDefaultValue());
    assertEquals(1, actualExtractPropertyGroupsResult.length);
    assertEquals(PropertyDescriptor.NAME_UNDEFINED, dbpPropertyDescriptor.getCategory());
    assertArrayEquals(new String[] {"Attribute"}, dbpPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@link ConfigurationElementHandle} {@link
   *       ConfigurationElementHandle#getAttribute(String)} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPropertyDescriptor[] PropertyDescriptor.extractPropertyGroups(IConfigurationElement)"
  })
  public void testExtractPropertyGroups_givenConfigurationElementHandleGetAttributeReturnNull() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn(null);
    when(configurationElementHandle2.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);

    // Act
    DBPPropertyDescriptor[] actualExtractPropertyGroupsResult =
        PropertyDescriptor.extractPropertyGroups(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("label");
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getChildren("propertyGroup");
    verify(configurationElementHandle2).getName();
    DBPPropertyDescriptor dbpPropertyDescriptor = actualExtractPropertyGroupsResult[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Attribute", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Attribute", dbpPropertyDescriptor.getDescription());
    assertEquals("Attribute", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Attribute", dbpPropertyDescriptor.getHint());
    assertEquals("Attribute", dbpPropertyDescriptor.getId());
    assertEquals("Attribute", dbpPropertyDescriptor.getDefaultValue());
    assertEquals(1, actualExtractPropertyGroupsResult.length);
    assertEquals(PropertyDescriptor.NAME_UNDEFINED, dbpPropertyDescriptor.getCategory());
    assertArrayEquals(new String[] {"Attribute"}, dbpPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPropertyDescriptor[] PropertyDescriptor.extractPropertyGroups(IConfigurationElement)"
  })
  public void testExtractPropertyGroups_givenNull_thenReturnArrayLengthIsZero() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any())).thenReturn(null);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);

    // Act
    DBPPropertyDescriptor[] actualExtractPropertyGroupsResult =
        PropertyDescriptor.extractPropertyGroups(config);

    // Assert
    verify(config).getChildren("propertyGroup");
    assertEquals(0, actualExtractPropertyGroupsResult.length);
  }

  /**
   * Test {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first element Category is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPropertyDescriptor[] PropertyDescriptor.extractPropertyGroups(IConfigurationElement)"
  })
  public void testExtractPropertyGroups_thenReturnFirstElementCategoryIsAttribute() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle2.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);

    // Act
    DBPPropertyDescriptor[] actualExtractPropertyGroupsResult =
        PropertyDescriptor.extractPropertyGroups(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("label");
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getChildren("propertyGroup");
    verify(configurationElementHandle2).getName();
    DBPPropertyDescriptor dbpPropertyDescriptor = actualExtractPropertyGroupsResult[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Attribute", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Attribute", dbpPropertyDescriptor.getCategory());
    assertEquals("Attribute", dbpPropertyDescriptor.getDescription());
    assertEquals("Attribute", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Attribute", dbpPropertyDescriptor.getHint());
    assertEquals("Attribute", dbpPropertyDescriptor.getId());
    assertEquals("Attribute", dbpPropertyDescriptor.getDefaultValue());
    assertEquals(1, actualExtractPropertyGroupsResult.length);
    assertArrayEquals(new String[] {"Attribute"}, dbpPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first element Category is {@link PropertyDescriptor#NAME_UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPropertyDescriptor[] PropertyDescriptor.extractPropertyGroups(IConfigurationElement)"
  })
  public void testExtractPropertyGroups_thenReturnFirstElementCategoryIsName_undefined() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getName()).thenReturn("Name");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);

    // Act
    DBPPropertyDescriptor[] actualExtractPropertyGroupsResult =
        PropertyDescriptor.extractPropertyGroups(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getChildren("propertyGroup");
    verify(configurationElementHandle2).getName();
    DBPPropertyDescriptor dbpPropertyDescriptor = actualExtractPropertyGroupsResult[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Attribute", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Attribute", dbpPropertyDescriptor.getDescription());
    assertEquals("Attribute", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Attribute", dbpPropertyDescriptor.getHint());
    assertEquals("Attribute", dbpPropertyDescriptor.getId());
    assertEquals("Attribute", dbpPropertyDescriptor.getDefaultValue());
    assertEquals(1, actualExtractPropertyGroupsResult.length);
    assertEquals(PropertyDescriptor.NAME_UNDEFINED, dbpPropertyDescriptor.getCategory());
    assertArrayEquals(new String[] {"Attribute"}, dbpPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first element Description is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPropertyDescriptor[] PropertyDescriptor.extractPropertyGroups(IConfigurationElement)"
  })
  public void testExtractPropertyGroups_thenReturnFirstElementDescriptionIsNull() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn(null);

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle2.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);

    // Act
    DBPPropertyDescriptor[] actualExtractPropertyGroupsResult =
        PropertyDescriptor.extractPropertyGroups(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("label");
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getChildren("propertyGroup");
    verify(configurationElementHandle2).getName();
    DBPPropertyDescriptor dbpPropertyDescriptor = actualExtractPropertyGroupsResult[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertNull(dbpPropertyDescriptor.getDescription());
    assertNull(dbpPropertyDescriptor.getHint());
    assertNull(dbpPropertyDescriptor.getId());
    assertEquals(1, actualExtractPropertyGroupsResult.length);
    assertEquals(
        PropertyType.t_string, ((PropertyDescriptor) dbpPropertyDescriptor).getPropertyType());
  }

  /**
   * Test {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first element Name is empty string.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPropertyDescriptor[] PropertyDescriptor.extractPropertyGroups(IConfigurationElement)"
  })
  public void testExtractPropertyGroups_thenReturnFirstElementNameIsEmptyString() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("");

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle2.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);

    // Act
    DBPPropertyDescriptor[] actualExtractPropertyGroupsResult =
        PropertyDescriptor.extractPropertyGroups(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("label");
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getChildren("propertyGroup");
    verify(configurationElementHandle2).getName();
    DBPPropertyDescriptor dbpPropertyDescriptor = actualExtractPropertyGroupsResult[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("", dbpPropertyDescriptor.getDescription());
    assertEquals("", dbpPropertyDescriptor.getDisplayName());
    assertEquals("", dbpPropertyDescriptor.getHint());
    assertEquals("", dbpPropertyDescriptor.getId());
    assertNull(dbpPropertyDescriptor.getDefaultValue());
    assertNull(dbpPropertyDescriptor.getFeatures());
    assertEquals(1, actualExtractPropertyGroupsResult.length);
  }

  /**
   * Test {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractPropertyGroups(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPPropertyDescriptor[] PropertyDescriptor.extractPropertyGroups(IConfigurationElement)"
  })
  public void testExtractPropertyGroups_thenThrowIllegalArgumentException() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any()))
        .thenThrow(new IllegalArgumentException());
    when(configurationElementHandle.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry3);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> PropertyDescriptor.extractPropertyGroups(config));
    verify(configurationElementHandle).getAttribute("label");
    verify(config).getChildren("propertyGroup");
    verify(configurationElementHandle).getName();
  }

  /**
   * Test {@link PropertyDescriptor#extractProperties(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code Attribute}.
   *   <li>Then return first Category is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractProperties(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List PropertyDescriptor.extractProperties(IConfigurationElement)"})
  public void testExtractProperties_givenAttribute_thenReturnFirstCategoryIsAttribute() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    List<DBPPropertyDescriptor> actualExtractPropertiesResult =
        PropertyDescriptor.extractProperties(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getAttribute("label");
    verify(config).getChildren("property");
    verify(config).getName();
    assertEquals(1, actualExtractPropertiesResult.size());
    DBPPropertyDescriptor getResult = actualExtractPropertiesResult.get(0);
    assertTrue(getResult instanceof PropertyDescriptor);
    assertEquals("Attribute", ((PropertyDescriptor) getResult).getName());
    assertEquals("Attribute", getResult.getCategory());
    assertEquals("Attribute", getResult.getDescription());
    assertEquals("Attribute", getResult.getDisplayName());
    assertEquals("Attribute", getResult.getHint());
    assertEquals("Attribute", getResult.getId());
    assertEquals("Attribute", getResult.getDefaultValue());
    assertArrayEquals(new String[] {"Attribute"}, getResult.getFeatures());
  }

  /**
   * Test {@link PropertyDescriptor#extractProperties(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractProperties(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List PropertyDescriptor.extractProperties(IConfigurationElement)"})
  public void testExtractProperties_givenEmptyString() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("");
    when(config.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    List<DBPPropertyDescriptor> actualExtractPropertiesResult =
        PropertyDescriptor.extractProperties(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getAttribute("label");
    verify(config).getChildren("property");
    verify(config).getName();
    assertEquals(1, actualExtractPropertiesResult.size());
    DBPPropertyDescriptor getResult = actualExtractPropertiesResult.get(0);
    assertTrue(getResult instanceof PropertyDescriptor);
    assertEquals("Attribute", ((PropertyDescriptor) getResult).getName());
    assertEquals("Attribute", getResult.getDescription());
    assertEquals("Attribute", getResult.getDisplayName());
    assertEquals("Attribute", getResult.getHint());
    assertEquals("Attribute", getResult.getId());
    assertEquals("Attribute", getResult.getDefaultValue());
    assertEquals(PropertyDescriptor.NAME_UNDEFINED, getResult.getCategory());
    assertArrayEquals(new String[] {"Attribute"}, getResult.getFeatures());
  }

  /**
   * Test {@link PropertyDescriptor#extractProperties(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>Then return first Category is {@link PropertyDescriptor#NAME_UNDEFINED}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractProperties(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List PropertyDescriptor.extractProperties(IConfigurationElement)"})
  public void testExtractProperties_givenName_thenReturnFirstCategoryIsName_undefined() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getName()).thenReturn("Name");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    List<DBPPropertyDescriptor> actualExtractPropertiesResult =
        PropertyDescriptor.extractProperties(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("property");
    verify(config).getName();
    assertEquals(1, actualExtractPropertiesResult.size());
    DBPPropertyDescriptor getResult = actualExtractPropertiesResult.get(0);
    assertTrue(getResult instanceof PropertyDescriptor);
    assertEquals("Attribute", ((PropertyDescriptor) getResult).getName());
    assertEquals("Attribute", getResult.getDescription());
    assertEquals("Attribute", getResult.getDisplayName());
    assertEquals("Attribute", getResult.getHint());
    assertEquals("Attribute", getResult.getId());
    assertEquals("Attribute", getResult.getDefaultValue());
    assertEquals(PropertyDescriptor.NAME_UNDEFINED, getResult.getCategory());
    assertArrayEquals(new String[] {"Attribute"}, getResult.getFeatures());
  }

  /**
   * Test {@link PropertyDescriptor#extractProperties(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first Description is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractProperties(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List PropertyDescriptor.extractProperties(IConfigurationElement)"})
  public void testExtractProperties_thenReturnFirstDescriptionIsNull() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn(null);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    List<DBPPropertyDescriptor> actualExtractPropertiesResult =
        PropertyDescriptor.extractProperties(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getAttribute("label");
    verify(config).getChildren("property");
    verify(config).getName();
    assertEquals(1, actualExtractPropertiesResult.size());
    DBPPropertyDescriptor getResult = actualExtractPropertiesResult.get(0);
    assertTrue(getResult instanceof PropertyDescriptor);
    assertNull(getResult.getDescription());
    assertNull(getResult.getHint());
    assertNull(getResult.getId());
    assertEquals(PropertyType.t_string, ((PropertyDescriptor) getResult).getPropertyType());
  }

  /**
   * Test {@link PropertyDescriptor#extractProperties(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first Name is empty string.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractProperties(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List PropertyDescriptor.extractProperties(IConfigurationElement)"})
  public void testExtractProperties_thenReturnFirstNameIsEmptyString() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    List<DBPPropertyDescriptor> actualExtractPropertiesResult =
        PropertyDescriptor.extractProperties(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getAttribute("label");
    verify(config).getChildren("property");
    verify(config).getName();
    assertEquals(1, actualExtractPropertiesResult.size());
    DBPPropertyDescriptor getResult = actualExtractPropertiesResult.get(0);
    assertTrue(getResult instanceof PropertyDescriptor);
    assertEquals("", ((PropertyDescriptor) getResult).getName());
    assertEquals("", getResult.getDescription());
    assertEquals("", getResult.getDisplayName());
    assertEquals("", getResult.getHint());
    assertEquals("", getResult.getId());
    assertNull(getResult.getDefaultValue());
    assertNull(getResult.getFeatures());
  }

  /**
   * Test {@link PropertyDescriptor#extractProperties(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractProperties(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List PropertyDescriptor.extractProperties(IConfigurationElement)"})
  public void testExtractProperties_thenThrowIllegalArgumentException() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenThrow(new IllegalArgumentException());
    when(config.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> PropertyDescriptor.extractProperties(config));
    verify(config).getAttribute("label");
    verify(config).getName();
  }

  /**
   * Test {@link PropertyDescriptor#extractProperties(IConfigurationElement)}.
   *
   * <ul>
   *   <li>When {@link ConfigurationElementHandle} {@link
   *       ConfigurationElementHandle#getAttribute(String)} return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#extractProperties(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List PropertyDescriptor.extractProperties(IConfigurationElement)"})
  public void testExtractProperties_whenConfigurationElementHandleGetAttributeReturnNull() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn(null);
    when(config.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    List<DBPPropertyDescriptor> actualExtractPropertiesResult =
        PropertyDescriptor.extractProperties(config);

    // Assert
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getAttribute("label");
    verify(config).getChildren("property");
    verify(config).getName();
    assertEquals(1, actualExtractPropertiesResult.size());
    DBPPropertyDescriptor getResult = actualExtractPropertiesResult.get(0);
    assertTrue(getResult instanceof PropertyDescriptor);
    assertEquals("Attribute", ((PropertyDescriptor) getResult).getName());
    assertEquals("Attribute", getResult.getDescription());
    assertEquals("Attribute", getResult.getDisplayName());
    assertEquals("Attribute", getResult.getHint());
    assertEquals("Attribute", getResult.getId());
    assertEquals("Attribute", getResult.getDefaultValue());
    assertEquals(PropertyDescriptor.NAME_UNDEFINED, getResult.getCategory());
    assertArrayEquals(new String[] {"Attribute"}, getResult.getFeatures());
  }

  /**
   * Test {@link PropertyDescriptor#getPropertyCategory(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code Attribute}.
   *   <li>Then return {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#getPropertyCategory(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PropertyDescriptor.getPropertyCategory(IConfigurationElement)"})
  public void testGetPropertyCategory_givenAttribute_thenReturnAttribute() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);

    // Act
    String actualPropertyCategory = PropertyDescriptor.getPropertyCategory(config);

    // Assert
    verify(config).getAttribute("label");
    verify(config).getName();
    assertEquals("Attribute", actualPropertyCategory);
  }

  /**
   * Test {@link PropertyDescriptor#getPropertyCategory(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#getPropertyCategory(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PropertyDescriptor.getPropertyCategory(IConfigurationElement)"})
  public void testGetPropertyCategory_givenEmptyString() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("");
    when(config.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);

    // Act
    String actualPropertyCategory = PropertyDescriptor.getPropertyCategory(config);

    // Assert
    verify(config).getAttribute("label");
    verify(config).getName();
    assertEquals(PropertyDescriptor.NAME_UNDEFINED, actualPropertyCategory);
  }

  /**
   * Test {@link PropertyDescriptor#getPropertyCategory(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#getPropertyCategory(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PropertyDescriptor.getPropertyCategory(IConfigurationElement)"})
  public void testGetPropertyCategory_givenName() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getName()).thenReturn("Name");

    // Act
    String actualPropertyCategory = PropertyDescriptor.getPropertyCategory(config);

    // Assert
    verify(config).getName();
    assertEquals(PropertyDescriptor.NAME_UNDEFINED, actualPropertyCategory);
  }

  /**
   * Test {@link PropertyDescriptor#getPropertyCategory(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalArgumentException}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#getPropertyCategory(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PropertyDescriptor.getPropertyCategory(IConfigurationElement)"})
  public void testGetPropertyCategory_thenThrowIllegalArgumentException() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenThrow(new IllegalArgumentException());
    when(config.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);

    // Act and Assert
    assertThrows(
        IllegalArgumentException.class, () -> PropertyDescriptor.getPropertyCategory(config));
    verify(config).getAttribute("label");
    verify(config).getName();
  }

  /**
   * Test {@link PropertyDescriptor#PropertyDescriptor(String, String, String, String, Class,
   * boolean, Object, String[], boolean)}.
   *
   * <p>Method under test: {@link PropertyDescriptor#PropertyDescriptor(String, String, String,
   * String, Class, boolean, Object, String[], boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertyDescriptor.<init>(String, String, String, String, Class, boolean, Object, String[], boolean)"
  })
  public void testNewPropertyDescriptor() {
    // Arrange
    Class<Object> type = Object.class;
    Object object = DBPEvent.RENAME;
    String[] validValues = new String[] {"42"};

    // Act
    PropertyDescriptor actualPropertyDescriptor =
        new PropertyDescriptor(
            "Category",
            "42",
            "Name",
            "The characteristics of someone or something",
            type,
            true,
            object,
            validValues,
            true);

    // Assert
    assertEquals("42", actualPropertyDescriptor.getId());
    assertEquals("Category", actualPropertyDescriptor.getCategory());
    assertEquals("Name", actualPropertyDescriptor.getDisplayName());
    assertEquals("Name", actualPropertyDescriptor.getName());
    assertEquals(
        "The characteristics of someone or something", actualPropertyDescriptor.getDescription());
    assertNull(actualPropertyDescriptor.getHint());
    assertNull(actualPropertyDescriptor.getRequiredFeatures());
    assertNull(actualPropertyDescriptor.getPropertyType());
    assertEquals(1, actualPropertyDescriptor.getFeatures().length);
    assertEquals(PropertyLength.LONG, actualPropertyDescriptor.getLength());
    assertTrue(actualPropertyDescriptor.isRequired());
    Class<Object> expectedDataType = Object.class;
    assertEquals(expectedDataType, actualPropertyDescriptor.getDataType());
    assertSame(object, actualPropertyDescriptor.getDefaultValue());
  }

  /**
   * Test {@link PropertyDescriptor#PropertyDescriptor(String, String, String, String, boolean,
   * Class, Object, Object[])}.
   *
   * <p>Method under test: {@link PropertyDescriptor#PropertyDescriptor(String, String, String,
   * String, boolean, Class, Object, Object[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertyDescriptor.<init>(String, String, String, String, boolean, Class, Object, Object[])"
  })
  public void testNewPropertyDescriptor2() {
    // Arrange
    Class<Object> type = Object.class;
    Object object = DBPEvent.RENAME;
    Object[] validValues = new Object[] {DBPEvent.RENAME};

    // Act
    PropertyDescriptor actualPropertyDescriptor =
        new PropertyDescriptor(
            "Category",
            "42",
            "Name",
            "The characteristics of someone or something",
            true,
            type,
            object,
            validValues);

    // Assert
    assertEquals("42", actualPropertyDescriptor.getId());
    assertEquals("Category", actualPropertyDescriptor.getCategory());
    assertEquals("Name", actualPropertyDescriptor.getDisplayName());
    assertEquals("Name", actualPropertyDescriptor.getName());
    assertEquals(
        "The characteristics of someone or something", actualPropertyDescriptor.getDescription());
    assertNull(actualPropertyDescriptor.getHint());
    assertNull(actualPropertyDescriptor.getRequiredFeatures());
    assertNull(actualPropertyDescriptor.getPropertyType());
    assertEquals(1, actualPropertyDescriptor.getFeatures().length);
    assertEquals(PropertyLength.LONG, actualPropertyDescriptor.getLength());
    assertTrue(actualPropertyDescriptor.isRequired());
    Class<Object> expectedDataType = Object.class;
    assertEquals(expectedDataType, actualPropertyDescriptor.getDataType());
    assertSame(object, actualPropertyDescriptor.getDefaultValue());
  }

  /**
   * Test {@link PropertyDescriptor#PropertyDescriptor(String, IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code Attribute}.
   *   <li>Then return Description is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#PropertyDescriptor(String,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertyDescriptor.<init>(String, IConfigurationElement)"})
  public void testNewPropertyDescriptor_givenAttribute_thenReturnDescriptionIsAttribute() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    // Act
    PropertyDescriptor actualPropertyDescriptor = new PropertyDescriptor("Category", config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    assertEquals("Attribute", actualPropertyDescriptor.getDescription());
    assertEquals("Attribute", actualPropertyDescriptor.getDisplayName());
    assertEquals("Attribute", actualPropertyDescriptor.getHint());
    assertEquals("Attribute", actualPropertyDescriptor.getId());
    assertEquals("Attribute", actualPropertyDescriptor.getName());
    assertEquals("Attribute", actualPropertyDescriptor.getDefaultValue());
    assertArrayEquals(new String[] {"Attribute"}, actualPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link PropertyDescriptor#PropertyDescriptor(String, IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then return Description is empty string.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#PropertyDescriptor(String,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertyDescriptor.<init>(String, IConfigurationElement)"})
  public void testNewPropertyDescriptor_givenEmptyString_thenReturnDescriptionIsEmptyString() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("");

    // Act
    PropertyDescriptor actualPropertyDescriptor = new PropertyDescriptor("Category", config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    assertEquals("", actualPropertyDescriptor.getDescription());
    assertEquals("", actualPropertyDescriptor.getDisplayName());
    assertEquals("", actualPropertyDescriptor.getHint());
    assertEquals("", actualPropertyDescriptor.getId());
    assertEquals("", actualPropertyDescriptor.getName());
    assertNull(actualPropertyDescriptor.getDefaultValue());
    assertNull(actualPropertyDescriptor.getFeatures());
    assertNull(actualPropertyDescriptor.getPropertyType());
  }

  /**
   * Test {@link PropertyDescriptor#PropertyDescriptor(String, IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then return Description is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#PropertyDescriptor(String,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertyDescriptor.<init>(String, IConfigurationElement)"})
  public void testNewPropertyDescriptor_givenNull_thenReturnDescriptionIsNull() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn(null);

    // Act
    PropertyDescriptor actualPropertyDescriptor = new PropertyDescriptor("Category", config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    assertNull(actualPropertyDescriptor.getDescription());
    assertNull(actualPropertyDescriptor.getHint());
    assertNull(actualPropertyDescriptor.getId());
    assertEquals(PropertyType.t_string, actualPropertyDescriptor.getPropertyType());
  }

  /**
   * Test {@link PropertyDescriptor#isEditable(Object)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#isEditable(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertyDescriptor.isEditable(Object)"})
  public void testIsEditable_givenJavaLangObject_thenReturnTrue() {
    // Arrange
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

    // Act and Assert
    assertTrue(propertyDescriptor.isEditable(DBPEvent.RENAME));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenEmptyString_thenReturnNull() {
    // Arrange
    Class<Object> valueType = Object.class;

    // Act and Assert
    assertNull(PropertyDescriptor.convertString("", valueType));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenJavaLangObject_thenReturn42() {
    // Arrange
    Class<Object> valueType = Object.class;

    // Act and Assert
    assertEquals("42", PropertyDescriptor.convertString("42", valueType));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@code String}.
   *   <li>Then return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenJavaLangString_thenReturn42() {
    // Arrange
    Class<String> valueType = String.class;

    // Act and Assert
    assertEquals("42", PropertyDescriptor.convertString("42", valueType));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link PropertyDescriptor#CURRENT_DATE_STRING_VAR_PREFIX}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenNull_thenReturnCurrent_date_string_var_prefix() {
    // Arrange, Act and Assert
    assertEquals(
        PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX,
        PropertyDescriptor.convertString(PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX, null));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenNull_thenReturnNull() {
    // Arrange
    Class<Object> valueType = Object.class;

    // Act and Assert
    assertNull(PropertyDescriptor.convertString(null, valueType));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@code org.jkiss.dbeaver.model}.
   *   <li>Then return {@code org.jkiss.dbeaver.model}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenOrgJkissDbeaverModel_thenReturnOrgJkissDbeaverModel() {
    // Arrange, Act and Assert
    assertEquals(
        "org.jkiss.dbeaver.model",
        PropertyDescriptor.convertString("org.jkiss.dbeaver.model", Long.TYPE));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Byte#TYPE}.
   *   <li>Then return byteValue is {@code *}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnByteValueIsAsterisk() {
    // Arrange, Act and Assert
    assertEquals('*', ((Byte) PropertyDescriptor.convertString("42", Byte.TYPE)).byteValue());
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Long#TYPE}.
   *   <li>Then return {@link PropertyDescriptor#CURRENT_DATE_STRING_VAR_PREFIX}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnCurrent_date_string_var_prefix() {
    // Arrange, Act and Assert
    assertEquals(
        PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX,
        PropertyDescriptor.convertString(
            PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX, Long.TYPE));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Integer#TYPE}.
   *   <li>Then return {@link PropertyDescriptor#CURRENT_DATE_STRING_VAR_PREFIX}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnCurrent_date_string_var_prefix2() {
    // Arrange, Act and Assert
    assertEquals(
        PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX,
        PropertyDescriptor.convertString(
            PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX, Integer.TYPE));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Short#TYPE}.
   *   <li>Then return {@link PropertyDescriptor#CURRENT_DATE_STRING_VAR_PREFIX}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnCurrent_date_string_var_prefix3() {
    // Arrange, Act and Assert
    assertEquals(
        PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX,
        PropertyDescriptor.convertString(
            PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX, Short.TYPE));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Byte#TYPE}.
   *   <li>Then return {@link PropertyDescriptor#CURRENT_DATE_STRING_VAR_PREFIX}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnCurrent_date_string_var_prefix4() {
    // Arrange, Act and Assert
    assertEquals(
        PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX,
        PropertyDescriptor.convertString(
            PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX, Byte.TYPE));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Double#TYPE}.
   *   <li>Then return {@link PropertyDescriptor#CURRENT_DATE_STRING_VAR_PREFIX}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnCurrent_date_string_var_prefix5() {
    // Arrange, Act and Assert
    assertEquals(
        PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX,
        PropertyDescriptor.convertString(
            PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX, Double.TYPE));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Float#TYPE}.
   *   <li>Then return {@link PropertyDescriptor#CURRENT_DATE_STRING_VAR_PREFIX}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnCurrent_date_string_var_prefix6() {
    // Arrange, Act and Assert
    assertEquals(
        PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX,
        PropertyDescriptor.convertString(
            PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX, Float.TYPE));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Double#TYPE}.
   *   <li>Then return doubleValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnDoubleValueIsFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(
        42.0d, ((Double) PropertyDescriptor.convertString("42", Double.TYPE)).doubleValue(), 0.0);
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Boolean#TYPE}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(
        (Boolean)
            PropertyDescriptor.convertString(
                PropertyDescriptor.CURRENT_DATE_STRING_VAR_PREFIX, Boolean.TYPE));
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Float#TYPE}.
   *   <li>Then return floatValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnFloatValueIsFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(
        42.0f, ((Float) PropertyDescriptor.convertString("42", Float.TYPE)).floatValue(), 0.0f);
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Integer#TYPE}.
   *   <li>Then return intValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnIntValueIsFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42, ((Integer) PropertyDescriptor.convertString("42", Integer.TYPE)).intValue());
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Long#TYPE}.
   *   <li>Then return longValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnLongValueIsFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42L, ((Long) PropertyDescriptor.convertString("42", Long.TYPE)).longValue());
  }

  /**
   * Test {@link PropertyDescriptor#convertString(String, Class)}.
   *
   * <ul>
   *   <li>When {@link Short#TYPE}.
   *   <li>Then return shortValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#convertString(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object PropertyDescriptor.convertString(String, Class)"})
  public void testConvertString_whenType_thenReturnShortValueIsFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(
        (short) 42, ((Short) PropertyDescriptor.convertString("42", Short.TYPE)).shortValue());
  }

  /**
   * Test {@link PropertyDescriptor#allowCustomValue()}.
   *
   * <p>Method under test: {@link PropertyDescriptor#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertyDescriptor.allowCustomValue()"})
  public void testAllowCustomValue() {
    // Arrange
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

    // Act and Assert
    assertTrue(propertyDescriptor.allowCustomValue());
  }

  /**
   * Test {@link PropertyDescriptor#allowCustomValue()}.
   *
   * <p>Method under test: {@link PropertyDescriptor#allowCustomValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertyDescriptor.allowCustomValue()"})
  public void testAllowCustomValue2() {
    // Arrange
    Class<Object> type = Object.class;
    PropertyDescriptor propertyDescriptor =
        new PropertyDescriptor(
            "Category",
            "42",
            "Name",
            "The characteristics of someone or something",
            true,
            type,
            DBPEvent.RENAME,
            new Object[] {});

    // Act and Assert
    assertTrue(propertyDescriptor.allowCustomValue());
  }

  /**
   * Test {@link PropertyDescriptor#getPossibleValues(Object)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#getPossibleValues(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object[] PropertyDescriptor.getPossibleValues(Object)"})
  public void testGetPossibleValues_givenJavaLangObject_thenReturnArrayLengthIsOne() {
    // Arrange
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
    Object object = DBPEvent.RENAME;

    // Act
    Object[] actualPossibleValues = propertyDescriptor.getPossibleValues(object);

    // Assert
    assertEquals(1, actualPossibleValues.length);
    assertSame(object, actualPossibleValues[0]);
  }

  /**
   * Test {@link PropertyDescriptor#getFeatures()}.
   *
   * <ul>
   *   <li>Then return array of {@link String} with {@code required}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#getFeatures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] PropertyDescriptor.getFeatures()"})
  public void testGetFeatures_thenReturnArrayOfStringWithRequired() {
    // Arrange
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

    // Act and Assert
    assertArrayEquals(new String[] {"required"}, propertyDescriptor.getFeatures());
  }

  /**
   * Test {@link PropertyDescriptor#getFeatures()}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#getFeatures()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String[] PropertyDescriptor.getFeatures()"})
  public void testGetFeatures_thenReturnNull() {
    // Arrange
    Class<Object> type = Object.class;
    Object[] validValues = new Object[] {DBPEvent.RENAME};

    PropertyDescriptor propertyDescriptor =
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
    assertNull(propertyDescriptor.getFeatures());
  }

  /**
   * Test {@link PropertyDescriptor#hasFeature(String)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>When {@code Feature}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyDescriptor#hasFeature(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean PropertyDescriptor.hasFeature(String)"})
  public void testHasFeature_givenJavaLangObject_whenFeature_thenReturnFalse() {
    // Arrange
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

    // Act and Assert
    assertFalse(propertyDescriptor.hasFeature("Feature"));
  }

  /**
   * Test PropertyType {@link PropertyType#getValueType()}.
   *
   * <p>Method under test: {@link PropertyType#getValueType()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class PropertyType.getValueType()"})
  public void testPropertyTypeGetValueType() {
    // Arrange and Act
    Class<?> actualValueType = PropertyType.valueOf("t_string").getValueType();

    // Assert
    Class<String> expectedValueType = String.class;
    assertEquals(expectedValueType, actualValueType);
  }
}
