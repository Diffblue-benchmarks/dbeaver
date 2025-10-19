package org.jkiss.dbeaver.ext.generic.model.meta;

import static org.junit.Assert.assertEquals;
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
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class GenericMetaObjectDiffblueTest {
  /**
   * Test {@link GenericMetaObject#GenericMetaObject(IConfigurationElement)}.
   *
   * <p>Method under test: {@link GenericMetaObject#GenericMetaObject(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GenericMetaObject.<init>(IConfigurationElement)"})
  public void testNewGenericMetaObject() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);

    // Act
    GenericMetaObject actualGenericMetaObject = new GenericMetaObject(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getChildren("column");
    assertEquals("Attribute", actualGenericMetaObject.getReadQuery());
    assertEquals("Attribute", actualGenericMetaObject.getType());
  }

  /**
   * Test {@link GenericMetaObject#GenericMetaObject(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@link ConfigurationElementHandle} {@link
   *       ConfigurationElementHandle#getAttribute(String)} return {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link GenericMetaObject#GenericMetaObject(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GenericMetaObject.<init>(IConfigurationElement)"})
  public void testNewGenericMetaObject_givenConfigurationElementHandleGetAttributeReturn42() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("42");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);

    // Act
    GenericMetaObject actualGenericMetaObject = new GenericMetaObject(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getChildren("column");
    assertEquals("Attribute", actualGenericMetaObject.getReadQuery());
    assertEquals("Attribute", actualGenericMetaObject.getType());
  }

  /**
   * Test {@link GenericMetaObject#GenericMetaObject(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty array of {@link IConfigurationElement}.
   * </ul>
   *
   * <p>Method under test: {@link GenericMetaObject#GenericMetaObject(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void GenericMetaObject.<init>(IConfigurationElement)"})
  public void testNewGenericMetaObject_givenEmptyArrayOfIConfigurationElement() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getChildren(Mockito.<String>any())).thenReturn(new IConfigurationElement[] {});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);

    // Act
    GenericMetaObject actualGenericMetaObject = new GenericMetaObject(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getChildren("column");
    assertEquals("Attribute", actualGenericMetaObject.getReadQuery());
    assertEquals("Attribute", actualGenericMetaObject.getType());
  }
}
