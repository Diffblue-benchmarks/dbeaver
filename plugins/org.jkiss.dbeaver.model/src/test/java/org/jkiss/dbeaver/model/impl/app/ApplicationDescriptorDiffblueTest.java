package org.jkiss.dbeaver.model.impl.app;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ApplicationDescriptorDiffblueTest {
  /**
   * Test {@link ApplicationDescriptor#ApplicationDescriptor(IConfigurationElement)}.
   *
   * <p>Method under test: {@link
   * ApplicationDescriptor#ApplicationDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ApplicationDescriptor.<init>(IConfigurationElement)"})
  public void testNewApplicationDescriptor() {
    // Arrange
    File[] storageDirs =
        new File[] {
          Paths.get(System.getProperty("java.io.tmpdir"), BaseProjectImpl.PROP_PROJECT_ID).toFile()
        };
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getContributor()).thenReturn(iContributor);

    // Act
    ApplicationDescriptor actualApplicationDescriptor = new ApplicationDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualApplicationDescriptor.getDescription());
    assertEquals("Attribute", actualApplicationDescriptor.getId());
    assertEquals("Attribute", actualApplicationDescriptor.getLicenseProductId());
    assertEquals("Attribute", actualApplicationDescriptor.getName());
    assertEquals("Attribute", actualApplicationDescriptor.getParentId());
    assertEquals("Attribute", actualApplicationDescriptor.getProductFamily());
    assertArrayEquals(
        new String[] {"Attribute"}, actualApplicationDescriptor.getUmbrellaProductIds());
  }

  /**
   * Test {@link ApplicationDescriptor#ApplicationDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code Attribute}.
   *   <li>Then return Description is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ApplicationDescriptor#ApplicationDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ApplicationDescriptor.<init>(IConfigurationElement)"})
  public void testNewApplicationDescriptor_givenAttribute_thenReturnDescriptionIsAttribute() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getContributor()).thenReturn(iContributor);

    // Act
    ApplicationDescriptor actualApplicationDescriptor = new ApplicationDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualApplicationDescriptor.getDescription());
    assertEquals("Attribute", actualApplicationDescriptor.getId());
    assertEquals("Attribute", actualApplicationDescriptor.getLicenseProductId());
    assertEquals("Attribute", actualApplicationDescriptor.getName());
    assertEquals("Attribute", actualApplicationDescriptor.getParentId());
    assertEquals("Attribute", actualApplicationDescriptor.getProductFamily());
    assertArrayEquals(
        new String[] {"Attribute"}, actualApplicationDescriptor.getUmbrellaProductIds());
  }

  /**
   * Test {@link ApplicationDescriptor#ApplicationDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code ,}.
   *   <li>Then return Description is {@code ,}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ApplicationDescriptor#ApplicationDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ApplicationDescriptor.<init>(IConfigurationElement)"})
  public void testNewApplicationDescriptor_givenComma_thenReturnDescriptionIsComma() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn(",");
    when(config.getContributor()).thenReturn(iContributor);

    // Act
    ApplicationDescriptor actualApplicationDescriptor = new ApplicationDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    assertEquals(",", actualApplicationDescriptor.getDescription());
    assertEquals(",", actualApplicationDescriptor.getId());
    assertEquals(",", actualApplicationDescriptor.getLicenseProductId());
    assertEquals(",", actualApplicationDescriptor.getName());
    assertEquals(",", actualApplicationDescriptor.getParentId());
    assertEquals(",", actualApplicationDescriptor.getProductFamily());
  }

  /**
   * Test {@link ApplicationDescriptor#ApplicationDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then return Description is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * ApplicationDescriptor#ApplicationDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ApplicationDescriptor.<init>(IConfigurationElement)"})
  public void testNewApplicationDescriptor_givenEmptyString_thenReturnDescriptionIsEmptyString() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("");
    when(config.getContributor()).thenReturn(iContributor);

    // Act
    ApplicationDescriptor actualApplicationDescriptor = new ApplicationDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
    assertEquals("", actualApplicationDescriptor.getDescription());
    assertEquals("", actualApplicationDescriptor.getId());
    assertEquals("", actualApplicationDescriptor.getLicenseProductId());
    assertEquals("", actualApplicationDescriptor.getName());
    assertEquals("", actualApplicationDescriptor.getParentId());
    assertEquals("", actualApplicationDescriptor.getProductFamily());
  }

  /**
   * Test {@link ApplicationDescriptor#getInstance()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link ApplicationDescriptor#getInstance()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.model.app.DBPApplication ApplicationDescriptor.getInstance()"
  })
  public void testGetInstance_thenThrowIllegalStateException() throws Exception {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(config.getContributor()).thenReturn(iContributor);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new ApplicationDescriptor(config).getInstance());
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
  }
}
