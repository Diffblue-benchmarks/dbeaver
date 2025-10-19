package org.jkiss.dbeaver.tools.transfer.registry;

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

public class DataTransferEventProcessorDescriptorDiffblueTest {
  /**
   * Test {@link
   * DataTransferEventProcessorDescriptor#DataTransferEventProcessorDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then return Type ImplName is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataTransferEventProcessorDescriptor#DataTransferEventProcessorDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTransferEventProcessorDescriptor.<init>(IConfigurationElement)"})
  public void testNewDataTransferEventProcessorDescriptor_given42_thenReturnTypeImplNameIs42() {
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
    when(config.getAttribute(Mockito.<String>any())).thenReturn("42");
    when(config.getContributor()).thenReturn(iContributor);

    // Act
    DataTransferEventProcessorDescriptor actualDataTransferEventProcessorDescriptor =
        new DataTransferEventProcessorDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    ObjectType type = actualDataTransferEventProcessorDescriptor.getType();
    assertEquals("42", type.getImplName());
    assertEquals("42", type.toString());
    assertEquals("42", actualDataTransferEventProcessorDescriptor.getDescription());
    assertEquals("42", actualDataTransferEventProcessorDescriptor.getId());
    assertEquals("42", actualDataTransferEventProcessorDescriptor.getLabel());
    assertEquals(42, actualDataTransferEventProcessorDescriptor.getOrder());
  }

  /**
   * Test {@link
   * DataTransferEventProcessorDescriptor#DataTransferEventProcessorDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Type ImplName is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataTransferEventProcessorDescriptor#DataTransferEventProcessorDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTransferEventProcessorDescriptor.<init>(IConfigurationElement)"})
  public void testNewDataTransferEventProcessorDescriptor_thenReturnTypeImplNameIsAttribute() {
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
    when(config.getContributor()).thenReturn(iContributor);

    // Act
    DataTransferEventProcessorDescriptor actualDataTransferEventProcessorDescriptor =
        new DataTransferEventProcessorDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    ObjectType type = actualDataTransferEventProcessorDescriptor.getType();
    assertEquals("Attribute", type.getImplName());
    assertEquals("Attribute", type.toString());
    assertEquals("Attribute", actualDataTransferEventProcessorDescriptor.getDescription());
    assertEquals("Attribute", actualDataTransferEventProcessorDescriptor.getId());
    assertEquals("Attribute", actualDataTransferEventProcessorDescriptor.getLabel());
  }

  /**
   * Test {@link
   * DataTransferEventProcessorDescriptor#DataTransferEventProcessorDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Type ImplName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * DataTransferEventProcessorDescriptor#DataTransferEventProcessorDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DataTransferEventProcessorDescriptor.<init>(IConfigurationElement)"})
  public void testNewDataTransferEventProcessorDescriptor_thenReturnTypeImplNameIsEmptyString() {
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
    when(config.getAttribute(Mockito.<String>any())).thenReturn("");
    when(config.getContributor()).thenReturn(iContributor);

    // Act
    DataTransferEventProcessorDescriptor actualDataTransferEventProcessorDescriptor =
        new DataTransferEventProcessorDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    ObjectType type = actualDataTransferEventProcessorDescriptor.getType();
    assertEquals("", type.getImplName());
    assertEquals("", type.toString());
    assertEquals("", actualDataTransferEventProcessorDescriptor.getDescription());
    assertEquals("", actualDataTransferEventProcessorDescriptor.getId());
    assertEquals("", actualDataTransferEventProcessorDescriptor.getLabel());
  }
}
