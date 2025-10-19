package org.jkiss.dbeaver.model.auth;

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
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class AuthPropertyDescriptorDiffblueTest {
  /**
   * Test {@link AuthPropertyDescriptor#AuthPropertyDescriptor(String, IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link AuthPropertyDescriptor#AuthPropertyDescriptor(String,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AuthPropertyDescriptor.<init>(String, IConfigurationElement)"})
  public void testNewAuthPropertyDescriptor_givenAttribute() {
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

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> new AuthPropertyDescriptor("Category", config));
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link AuthPropertyDescriptor#AuthPropertyDescriptor(String, IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link AuthPropertyDescriptor#AuthPropertyDescriptor(String,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AuthPropertyDescriptor.<init>(String, IConfigurationElement)"})
  public void testNewAuthPropertyDescriptor_givenEmptyString() {
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

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> new AuthPropertyDescriptor("Category", config));
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getContributor();
    verify(iContributor).getName();
  }
}
