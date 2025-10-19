package org.jkiss.dbeaver.model.security;

import static org.junit.Assert.assertEquals;
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
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class SMAuthCredentialsProfileDiffblueTest {
  /**
   * Test {@link SMAuthCredentialsProfile#SMAuthCredentialsProfile(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Description is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * SMAuthCredentialsProfile#SMAuthCredentialsProfile(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SMAuthCredentialsProfile.<init>(IConfigurationElement)"})
  public void testNewSMAuthCredentialsProfile_thenReturnDescriptionIsAttribute() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, "Master Token", "User Token");
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(configurationElementHandle.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {});

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
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry3 =
        new ExtensionRegistry(registryStrategy3, "Master Token", "User Token");
    new RegistryObjectManager(registry3);

    // Act
    SMAuthCredentialsProfile actualSmAuthCredentialsProfile = new SMAuthCredentialsProfile(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle).getAttribute("label");
    verify(configurationElementHandle).getChildren("property");
    verify(cfg).getChildren("propertyGroup");
    assertEquals("Attribute", actualSmAuthCredentialsProfile.getDescription());
    assertEquals("Attribute", actualSmAuthCredentialsProfile.getId());
    assertEquals("Attribute", actualSmAuthCredentialsProfile.getLabel());
    assertTrue(actualSmAuthCredentialsProfile.getCredentialParameters().isEmpty());
  }
}
