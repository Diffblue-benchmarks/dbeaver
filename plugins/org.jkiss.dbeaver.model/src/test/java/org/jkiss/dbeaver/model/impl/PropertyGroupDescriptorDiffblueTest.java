package org.jkiss.dbeaver.model.impl;

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
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBPEvent;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PropertyGroupDescriptorDiffblueTest {
  /**
   * Test {@link PropertyGroupDescriptor#PropertyGroupDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return DisplayName is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * PropertyGroupDescriptor#PropertyGroupDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void PropertyGroupDescriptor.<init>(IConfigurationElement)"})
  public void testNewPropertyGroupDescriptor_thenReturnDisplayNameIsAttribute() {
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

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);

    // Act
    PropertyGroupDescriptor<PropertyDescriptor> actualPropertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualPropertyGroupDescriptor.getDisplayName());
    assertEquals("Attribute", actualPropertyGroupDescriptor.getFullId());
    assertEquals("Attribute", actualPropertyGroupDescriptor.getId());
    assertEquals("Name", actualPropertyGroupDescriptor.getPluginId());
    assertNull(actualPropertyGroupDescriptor.getParentGroup());
    assertTrue(actualPropertyGroupDescriptor.getSettings().isEmpty());
    assertTrue(actualPropertyGroupDescriptor.getSubGroups().isEmpty());
  }

  /**
   * Test {@link PropertyGroupDescriptor#getSettings()}.
   *
   * <ul>
   *   <li>Then return Empty.
   * </ul>
   *
   * <p>Method under test: {@link PropertyGroupDescriptor#getSettings()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"List PropertyGroupDescriptor.getSettings()"})
  public void testGetSettings_thenReturnEmpty() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);

    // Act
    List<PropertyDescriptor> actualSettings = propertyGroupDescriptor.getSettings();

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertTrue(actualSettings.isEmpty());
  }

  /**
   * Test {@link PropertyGroupDescriptor#getDisplayName()}.
   *
   * <ul>
   *   <li>Then return {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link PropertyGroupDescriptor#getDisplayName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PropertyGroupDescriptor.getDisplayName()"})
  public void testGetDisplayName_thenReturnAttribute() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);

    // Act
    String actualDisplayName = propertyGroupDescriptor.getDisplayName();

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualDisplayName);
  }

  /**
   * Test {@link PropertyGroupDescriptor#getDisplayName()}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link PropertyGroupDescriptor#getDisplayName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String PropertyGroupDescriptor.getDisplayName()"})
  public void testGetDisplayName_thenReturnEmptyString() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);

    // Act
    String actualDisplayName = propertyGroupDescriptor.getDisplayName();

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("", actualDisplayName);
  }
}
