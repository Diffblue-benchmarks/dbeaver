package org.jkiss.dbeaver.model.dashboard.registry;

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
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor.ObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DashboardMapQueryDescriptorDiffblueTest {
  /**
   * Test {@link DashboardMapQueryDescriptor#DashboardMapQueryDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then return Id is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * DashboardMapQueryDescriptor#DashboardMapQueryDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardMapQueryDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardMapQueryDescriptor_givenEmptyString_thenReturnIdIsEmptyString() {
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

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getValueAsIs()).thenReturn("42");
    when(config.getAttribute(Mockito.<String>any())).thenReturn("");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);

    // Act
    DashboardMapQueryDescriptor actualDashboardMapQueryDescriptor =
        new DashboardMapQueryDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("objectType");
    verify(config).getContributor();
    verify(config).getValueAsIs();
    verify(iContributor).getName();
    assertEquals("", actualDashboardMapQueryDescriptor.getId());
    assertEquals("", actualDashboardMapQueryDescriptor.toString());
    ObjectType[] objectTypes = actualDashboardMapQueryDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("", objectType.getImplName());
    assertEquals("", objectType.toString());
    assertEquals(2, objectTypes.length);
  }

  /**
   * Test {@link DashboardMapQueryDescriptor#DashboardMapQueryDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Id is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DashboardMapQueryDescriptor#DashboardMapQueryDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardMapQueryDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardMapQueryDescriptor_thenReturnIdIs42() {
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

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getValueAsIs()).thenReturn("42");
    when(config.getAttribute(Mockito.<String>any())).thenReturn("42");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);

    // Act
    DashboardMapQueryDescriptor actualDashboardMapQueryDescriptor =
        new DashboardMapQueryDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("objectType");
    verify(config).getContributor();
    verify(config).getValueAsIs();
    verify(iContributor).getName();
    assertEquals("42", actualDashboardMapQueryDescriptor.getId());
    assertEquals("42", actualDashboardMapQueryDescriptor.toString());
    ObjectType[] objectTypes = actualDashboardMapQueryDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("42", objectType.getImplName());
    assertEquals("42", objectType.toString());
    assertEquals(2, objectTypes.length);
    assertEquals(42L, actualDashboardMapQueryDescriptor.getUpdatePeriod());
  }

  /**
   * Test {@link DashboardMapQueryDescriptor#DashboardMapQueryDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return second element ImplName is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DashboardMapQueryDescriptor#DashboardMapQueryDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardMapQueryDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardMapQueryDescriptor_thenReturnSecondElementImplNameIsAttribute() {
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

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getValueAsIs()).thenReturn("42");
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);

    // Act
    DashboardMapQueryDescriptor actualDashboardMapQueryDescriptor =
        new DashboardMapQueryDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("objectType");
    verify(config).getContributor();
    verify(config).getValueAsIs();
    verify(iContributor).getName();
    assertEquals("Attribute", actualDashboardMapQueryDescriptor.getId());
    assertEquals("Attribute", actualDashboardMapQueryDescriptor.toString());
    ObjectType[] objectTypes = actualDashboardMapQueryDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("Attribute", objectType.getImplName());
    ObjectType objectType2 = objectTypes[1];
    assertEquals("Attribute", objectType2.getImplName());
    assertEquals("Attribute", objectType.toString());
    assertEquals("Attribute", objectType2.toString());
    assertEquals(2, objectTypes.length);
  }

  /**
   * Test {@link DashboardMapQueryDescriptor#DashboardMapQueryDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return second element ImplName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * DashboardMapQueryDescriptor#DashboardMapQueryDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardMapQueryDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardMapQueryDescriptor_thenReturnSecondElementImplNameIsEmptyString() {
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

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getValueAsIs()).thenReturn("42");
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);

    // Act
    DashboardMapQueryDescriptor actualDashboardMapQueryDescriptor =
        new DashboardMapQueryDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("objectType");
    verify(config).getContributor();
    verify(config).getValueAsIs();
    verify(iContributor).getName();
    ObjectType[] objectTypes = actualDashboardMapQueryDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[1];
    assertEquals("", objectType.getImplName());
    assertEquals("", objectType.toString());
    assertEquals("Attribute", actualDashboardMapQueryDescriptor.getId());
    assertEquals("Attribute", actualDashboardMapQueryDescriptor.toString());
    ObjectType objectType2 = objectTypes[0];
    assertEquals("Attribute", objectType2.getImplName());
    assertEquals("Attribute", objectType2.toString());
    assertEquals(2, objectTypes.length);
  }

  /**
   * Test {@link DashboardMapQueryDescriptor#DashboardMapQueryDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return second element ImplName is {@code if}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DashboardMapQueryDescriptor#DashboardMapQueryDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DashboardMapQueryDescriptor.<init>(IConfigurationElement)"})
  public void testNewDashboardMapQueryDescriptor_thenReturnSecondElementImplNameIsIf() {
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
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("if");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getValueAsIs()).thenReturn("42");
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new RegistryObjectManager(registry2);

    // Act
    DashboardMapQueryDescriptor actualDashboardMapQueryDescriptor =
        new DashboardMapQueryDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("objectType");
    verify(config).getContributor();
    verify(config).getValueAsIs();
    verify(iContributor).getName();
    assertEquals("Attribute", actualDashboardMapQueryDescriptor.getId());
    assertEquals("Attribute", actualDashboardMapQueryDescriptor.toString());
    ObjectType[] objectTypes = actualDashboardMapQueryDescriptor.getObjectTypes();
    ObjectType objectType = objectTypes[0];
    assertEquals("Attribute", objectType.getImplName());
    assertEquals("Attribute", objectType.toString());
    ObjectType objectType2 = objectTypes[1];
    assertEquals("if", objectType2.getImplName());
    assertEquals("if", objectType2.toString());
    assertEquals(2, objectTypes.length);
  }
}
