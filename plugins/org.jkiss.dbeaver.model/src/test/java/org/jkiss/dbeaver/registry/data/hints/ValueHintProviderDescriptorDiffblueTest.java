package org.jkiss.dbeaver.registry.data.hints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import org.jkiss.dbeaver.model.impl.AbstractDescriptor;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor.ObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class ValueHintProviderDescriptorDiffblueTest {
  /**
   * Test {@link ValueHintProviderDescriptor#ValueHintProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return {@link AbstractValueBindingDescriptor#implType} ImplName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * ValueHintProviderDescriptor#ValueHintProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ValueHintProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewValueHintProviderDescriptor_thenReturnImplTypeImplNameIsEmptyString() {
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

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("");
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    ValueHintProviderDescriptor actualValueHintProviderDescriptor =
        new ValueHintProviderDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("supports");
    verify(config).getContributor();
    verify(iContributor).getName();
    ObjectType objectType = actualValueHintProviderDescriptor.implType;
    assertEquals("", objectType.getImplName());
    assertEquals("", objectType.toString());
    assertEquals("", actualValueHintProviderDescriptor.getDescription());
    assertEquals("", actualValueHintProviderDescriptor.getId());
    assertEquals("", actualValueHintProviderDescriptor.toString());
    assertEquals("", actualValueHintProviderDescriptor.getLabel());
  }

  /**
   * Test {@link ValueHintProviderDescriptor#ValueHintProviderDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return {@link AbstractValueBindingDescriptor#implType} ObjectClass is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ValueHintProviderDescriptor#ValueHintProviderDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ValueHintProviderDescriptor.<init>(IConfigurationElement)"})
  public void testNewValueHintProviderDescriptor_thenReturnImplTypeObjectClassIsNull() {
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

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn(null);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {configurationElementHandle});
    when(config.getContributor()).thenReturn(iContributor);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    ValueHintProviderDescriptor actualValueHintProviderDescriptor =
        new ValueHintProviderDescriptor(config);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(config).getChildren("supports");
    verify(config).getContributor();
    verify(iContributor).getName();
    ObjectType objectType = actualValueHintProviderDescriptor.implType;
    assertNull(objectType.getObjectClass());
    assertNull(objectType.getImplName());
    assertNull(objectType.toString());
    assertNull(actualValueHintProviderDescriptor.getDescription());
    assertNull(actualValueHintProviderDescriptor.getId());
    assertNull(actualValueHintProviderDescriptor.toString());
    assertNull(actualValueHintProviderDescriptor.getLabel());
  }
}
