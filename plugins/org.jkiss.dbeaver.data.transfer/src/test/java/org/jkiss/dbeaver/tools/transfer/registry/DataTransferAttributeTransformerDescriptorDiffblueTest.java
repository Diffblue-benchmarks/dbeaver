package org.jkiss.dbeaver.tools.transfer.registry;

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
import java.util.List;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBIcon;
import org.jkiss.dbeaver.model.DBPImage;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DataTransferAttributeTransformerDescriptorDiffblueTest {
  /**
   * Test {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferAttributeTransformerDescriptor.<init>(IConfigurationElement)"
  })
  public void testNewDataTransferAttributeTransformerDescriptor() {
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

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any())).thenReturn(new IConfigurationElement[] {});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, "Master Token", "User Token");
    new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1);

    // Act
    DataTransferAttributeTransformerDescriptor actualDataTransferAttributeTransformerDescriptor =
        new DataTransferAttributeTransformerDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(iContributor).getName();
    DBPImage icon = actualDataTransferAttributeTransformerDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getDescription());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getId());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getName());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.toString());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
    assertTrue(actualDataTransferAttributeTransformerDescriptor.getProperties().isEmpty());
  }

  /**
   * Test {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferAttributeTransformerDescriptor.<init>(IConfigurationElement)"
  })
  public void testNewDataTransferAttributeTransformerDescriptor2()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");
    when(iConfigurationElement.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
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
    new ConfigurationElementHandle(new RegistryObjectManager(registry3), 1);

    // Act
    DataTransferAttributeTransformerDescriptor actualDataTransferAttributeTransformerDescriptor =
        new DataTransferAttributeTransformerDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(iConfigurationElement).getChildren("property");
    verify(iConfigurationElement).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDataTransferAttributeTransformerDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getDescription());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getId());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getName());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.toString());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
    assertTrue(actualDataTransferAttributeTransformerDescriptor.getProperties().isEmpty());
  }

  /**
   * Test {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferAttributeTransformerDescriptor.<init>(IConfigurationElement)"
  })
  public void testNewDataTransferAttributeTransformerDescriptor3()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    IConfigurationElement iConfigurationElement2 = mock(IConfigurationElement.class);
    when(iConfigurationElement2.getName()).thenReturn("Name");
    when(iConfigurationElement2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("#");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement2});
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
    DataTransferAttributeTransformerDescriptor actualDataTransferAttributeTransformerDescriptor =
        new DataTransferAttributeTransformerDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(iConfigurationElement, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(iConfigurationElement2).getChildren("property");
    verify(iConfigurationElement2).getName();
    verify(iContributor).getName();
    assertEquals("#", actualDataTransferAttributeTransformerDescriptor.getDescription());
    assertEquals("#", actualDataTransferAttributeTransformerDescriptor.getId());
    assertEquals("#", actualDataTransferAttributeTransformerDescriptor.getName());
    assertEquals("#", actualDataTransferAttributeTransformerDescriptor.toString());
  }

  /**
   * Test {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferAttributeTransformerDescriptor.<init>(IConfigurationElement)"
  })
  public void testNewDataTransferAttributeTransformerDescriptor4()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    IConfigurationElement iConfigurationElement2 = mock(IConfigurationElement.class);
    when(iConfigurationElement2.getName()).thenReturn("Name");
    when(iConfigurationElement2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("platform:");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement2});
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
    DataTransferAttributeTransformerDescriptor actualDataTransferAttributeTransformerDescriptor =
        new DataTransferAttributeTransformerDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(iConfigurationElement, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(iConfigurationElement2).getChildren("property");
    verify(iConfigurationElement2).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDataTransferAttributeTransformerDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    assertEquals("platform:", icon.getLocation());
    assertEquals("platform:", actualDataTransferAttributeTransformerDescriptor.getDescription());
    assertEquals("platform:", actualDataTransferAttributeTransformerDescriptor.getId());
    assertEquals("platform:", actualDataTransferAttributeTransformerDescriptor.getName());
    assertEquals("platform:", actualDataTransferAttributeTransformerDescriptor.toString());
  }

  /**
   * Test {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferAttributeTransformerDescriptor.<init>(IConfigurationElement)"
  })
  public void testNewDataTransferAttributeTransformerDescriptor5()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    IConfigurationElement iConfigurationElement2 = mock(IConfigurationElement.class);
    when(iConfigurationElement2.getName()).thenReturn("Name");
    when(iConfigurationElement2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement2});
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
    DataTransferAttributeTransformerDescriptor actualDataTransferAttributeTransformerDescriptor =
        new DataTransferAttributeTransformerDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(iConfigurationElement, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(iConfigurationElement2).getChildren("property");
    verify(iConfigurationElement2).getName();
    verify(iContributor).getName();
    assertEquals("", actualDataTransferAttributeTransformerDescriptor.getDescription());
    assertEquals("", actualDataTransferAttributeTransformerDescriptor.getId());
    assertEquals("", actualDataTransferAttributeTransformerDescriptor.getName());
    assertEquals("", actualDataTransferAttributeTransformerDescriptor.toString());
  }

  /**
   * Test {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferAttributeTransformerDescriptor.<init>(IConfigurationElement)"
  })
  public void testNewDataTransferAttributeTransformerDescriptor6()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    IConfigurationElement iConfigurationElement2 = mock(IConfigurationElement.class);
    when(iConfigurationElement2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(iConfigurationElement2.getName()).thenReturn("propertyGroup");
    when(iConfigurationElement2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement2});
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
    DataTransferAttributeTransformerDescriptor actualDataTransferAttributeTransformerDescriptor =
        new DataTransferAttributeTransformerDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(iConfigurationElement, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(iConfigurationElement2).getAttribute("label");
    verify(iConfigurationElement2).getChildren("property");
    verify(iConfigurationElement2).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDataTransferAttributeTransformerDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    List<DBPPropertyDescriptor> properties =
        actualDataTransferAttributeTransformerDescriptor.getProperties();
    assertEquals(1, properties.size());
    assertTrue(properties.get(0) instanceof PropertyDescriptor);
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getDescription());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getId());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getName());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.toString());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
  }

  /**
   * Test {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferAttributeTransformerDescriptor.<init>(IConfigurationElement)"
  })
  public void testNewDataTransferAttributeTransformerDescriptor7()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    IConfigurationElement iConfigurationElement2 = mock(IConfigurationElement.class);
    when(iConfigurationElement2.getAttribute(Mockito.<String>any())).thenReturn("");
    when(iConfigurationElement2.getName()).thenReturn("propertyGroup");
    when(iConfigurationElement2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement2});
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
    DataTransferAttributeTransformerDescriptor actualDataTransferAttributeTransformerDescriptor =
        new DataTransferAttributeTransformerDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(iConfigurationElement, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(iConfigurationElement2).getAttribute("label");
    verify(iConfigurationElement2).getChildren("property");
    verify(iConfigurationElement2).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDataTransferAttributeTransformerDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    List<DBPPropertyDescriptor> properties =
        actualDataTransferAttributeTransformerDescriptor.getProperties();
    assertEquals(1, properties.size());
    assertTrue(properties.get(0) instanceof PropertyDescriptor);
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getDescription());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getId());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getName());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.toString());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
  }

  /**
   * Test {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferAttributeTransformerDescriptor.<init>(IConfigurationElement)"
  })
  public void testNewDataTransferAttributeTransformerDescriptor8()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("");

    IConfigurationElement iConfigurationElement2 = mock(IConfigurationElement.class);
    when(iConfigurationElement2.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(iConfigurationElement2.getName()).thenReturn("propertyGroup");
    when(iConfigurationElement2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement2});
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
    DataTransferAttributeTransformerDescriptor actualDataTransferAttributeTransformerDescriptor =
        new DataTransferAttributeTransformerDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(iConfigurationElement, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(iConfigurationElement2).getAttribute("label");
    verify(iConfigurationElement2).getChildren("property");
    verify(iConfigurationElement2).getName();
    verify(iContributor).getName();
    List<DBPPropertyDescriptor> properties =
        actualDataTransferAttributeTransformerDescriptor.getProperties();
    assertEquals(1, properties.size());
    DBPPropertyDescriptor getResult = properties.get(0);
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
   * Test {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Properties size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataTransferAttributeTransformerDescriptor#DataTransferAttributeTransformerDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DataTransferAttributeTransformerDescriptor.<init>(IConfigurationElement)"
  })
  public void testNewDataTransferAttributeTransformerDescriptor_thenReturnPropertiesSizeIsOne()
      throws InvalidRegistryObjectException {
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

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    IConfigurationElement iConfigurationElement2 = mock(IConfigurationElement.class);
    when(iConfigurationElement2.getName()).thenReturn("Name");
    when(iConfigurationElement2.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement2});
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
    DataTransferAttributeTransformerDescriptor actualDataTransferAttributeTransformerDescriptor =
        new DataTransferAttributeTransformerDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("propertyGroup");
    verify(config).getContributor();
    verify(iConfigurationElement, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(iConfigurationElement2).getChildren("property");
    verify(iConfigurationElement2).getName();
    verify(iContributor).getName();
    DBPImage icon = actualDataTransferAttributeTransformerDescriptor.getIcon();
    assertTrue(icon instanceof DBIcon);
    List<DBPPropertyDescriptor> properties =
        actualDataTransferAttributeTransformerDescriptor.getProperties();
    assertEquals(1, properties.size());
    assertTrue(properties.get(0) instanceof PropertyDescriptor);
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getDescription());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getId());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.getName());
    assertEquals("Attribute", actualDataTransferAttributeTransformerDescriptor.toString());
    assertEquals("platform:/plugin/Name/Attribute", icon.getLocation());
  }
}
