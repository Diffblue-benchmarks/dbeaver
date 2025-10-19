package org.jkiss.dbeaver.ext.postgresql.model.fdw;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class FDWConfigDescriptorDiffblueTest {
  /**
   * Test {@link FDWConfigDescriptor#FDWConfigDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first element Category is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link FDWConfigDescriptor#FDWConfigDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FDWConfigDescriptor.<init>(IConfigurationElement)"})
  public void testNewFDWConfigDescriptor_thenReturnFirstElementCategoryIsAttribute() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle2.getName()).thenReturn("propertyGroup");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);

    // Act
    FDWConfigDescriptor actualFdwConfigDescriptor = new FDWConfigDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("label");
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    DBPPropertyDescriptor[] properties = actualFdwConfigDescriptor.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("Attribute", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Attribute", dbpPropertyDescriptor.getCategory());
    assertEquals("Attribute", dbpPropertyDescriptor.getDescription());
    assertEquals("Attribute", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Attribute", dbpPropertyDescriptor.getHint());
    assertEquals("Attribute", dbpPropertyDescriptor.getId());
    assertEquals("Attribute", dbpPropertyDescriptor.getDefaultValue());
    assertEquals(1, properties.length);
    assertArrayEquals(new String[] {"Attribute"}, dbpPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link FDWConfigDescriptor#FDWConfigDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first element Category is {@code <undefined>}.
   * </ul>
   *
   * <p>Method under test: {@link FDWConfigDescriptor#FDWConfigDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FDWConfigDescriptor.<init>(IConfigurationElement)"})
  public void testNewFDWConfigDescriptor_thenReturnFirstElementCategoryIsUndefined() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getName()).thenReturn("Name");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);

    // Act
    FDWConfigDescriptor actualFdwConfigDescriptor = new FDWConfigDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    DBPPropertyDescriptor[] properties = actualFdwConfigDescriptor.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("<undefined>", dbpPropertyDescriptor.getCategory());
    assertEquals("Attribute", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Attribute", dbpPropertyDescriptor.getDescription());
    assertEquals("Attribute", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Attribute", dbpPropertyDescriptor.getHint());
    assertEquals("Attribute", dbpPropertyDescriptor.getId());
    assertEquals("Attribute", dbpPropertyDescriptor.getDefaultValue());
    assertEquals(1, properties.length);
    assertArrayEquals(new String[] {"Attribute"}, dbpPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link FDWConfigDescriptor#FDWConfigDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first element Category is {@code <undefined>}.
   * </ul>
   *
   * <p>Method under test: {@link FDWConfigDescriptor#FDWConfigDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FDWConfigDescriptor.<init>(IConfigurationElement)"})
  public void testNewFDWConfigDescriptor_thenReturnFirstElementCategoryIsUndefined2() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("");
    when(configurationElementHandle2.getName()).thenReturn("propertyGroup");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);

    // Act
    FDWConfigDescriptor actualFdwConfigDescriptor = new FDWConfigDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("label");
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    DBPPropertyDescriptor[] properties = actualFdwConfigDescriptor.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("<undefined>", dbpPropertyDescriptor.getCategory());
    assertEquals("Attribute", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("Attribute", dbpPropertyDescriptor.getDescription());
    assertEquals("Attribute", dbpPropertyDescriptor.getDisplayName());
    assertEquals("Attribute", dbpPropertyDescriptor.getHint());
    assertEquals("Attribute", dbpPropertyDescriptor.getId());
    assertEquals("Attribute", dbpPropertyDescriptor.getDefaultValue());
    assertEquals(1, properties.length);
    assertArrayEquals(new String[] {"Attribute"}, dbpPropertyDescriptor.getFeatures());
  }

  /**
   * Test {@link FDWConfigDescriptor#FDWConfigDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return first element Name is empty string.
   * </ul>
   *
   * <p>Method under test: {@link FDWConfigDescriptor#FDWConfigDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void FDWConfigDescriptor.<init>(IConfigurationElement)"})
  public void testNewFDWConfigDescriptor_thenReturnFirstElementNameIsEmptyString() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("");

    ConfigurationElementHandle configurationElementHandle2 = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle2.getName()).thenReturn("propertyGroup");
    when(configurationElementHandle2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle2});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);

    // Act
    FDWConfigDescriptor actualFdwConfigDescriptor = new FDWConfigDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle2).getAttribute("label");
    verify(configurationElementHandle2).getChildren("property");
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(configurationElementHandle2).getName();
    verify(iContributor).getName();
    DBPPropertyDescriptor[] properties = actualFdwConfigDescriptor.getProperties();
    DBPPropertyDescriptor dbpPropertyDescriptor = properties[0];
    assertTrue(dbpPropertyDescriptor instanceof PropertyDescriptor);
    assertEquals("", ((PropertyDescriptor) dbpPropertyDescriptor).getName());
    assertEquals("", dbpPropertyDescriptor.getDescription());
    assertEquals("", dbpPropertyDescriptor.getDisplayName());
    assertEquals("", dbpPropertyDescriptor.getHint());
    assertEquals("", dbpPropertyDescriptor.getId());
    assertNull(dbpPropertyDescriptor.getDefaultValue());
    assertNull(dbpPropertyDescriptor.getFeatures());
    assertEquals(1, properties.length);
  }
}
