package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor.PropertyType;
import org.jkiss.dbeaver.model.meta.PropertyLength;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProviderPropertyDescriptorDiffblueTest {
  @Mock private IConfigurationElement iConfigurationElement;

  /**
   * Test {@link ProviderPropertyDescriptor#ProviderPropertyDescriptor(String,
   * IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Description is empty string.
   * </ul>
   *
   * <p>Method under test: {@link ProviderPropertyDescriptor#ProviderPropertyDescriptor(String,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProviderPropertyDescriptor.<init>(String, IConfigurationElement)"})
  public void testNewProviderPropertyDescriptor_thenReturnDescriptionIsEmptyString()
      throws InvalidRegistryObjectException {
    // Arrange
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("");

    // Act
    ProviderPropertyDescriptor actualProviderPropertyDescriptor =
        new ProviderPropertyDescriptor("Category", iConfigurationElement);

    // Assert
    verify(iConfigurationElement, atLeast(1)).getAttribute(Mockito.<String>any());
    assertEquals("", actualProviderPropertyDescriptor.getDescription());
    assertEquals("", actualProviderPropertyDescriptor.getHint());
    assertEquals("", actualProviderPropertyDescriptor.getId());
    assertNull(actualProviderPropertyDescriptor.getPropertyType());
  }

  /**
   * Test {@link ProviderPropertyDescriptor#ProviderPropertyDescriptor(String,
   * IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return Description is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ProviderPropertyDescriptor#ProviderPropertyDescriptor(String,
   * IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ProviderPropertyDescriptor.<init>(String, IConfigurationElement)"})
  public void testNewProviderPropertyDescriptor_thenReturnDescriptionIsNull()
      throws InvalidRegistryObjectException {
    // Arrange
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn(null);

    // Act
    ProviderPropertyDescriptor actualProviderPropertyDescriptor =
        new ProviderPropertyDescriptor("Category", iConfigurationElement);

    // Assert
    verify(iConfigurationElement, atLeast(1)).getAttribute(Mockito.<String>any());
    assertNull(actualProviderPropertyDescriptor.getDescription());
    assertNull(actualProviderPropertyDescriptor.getHint());
    assertNull(actualProviderPropertyDescriptor.getId());
    assertEquals(PropertyType.t_string, actualProviderPropertyDescriptor.getPropertyType());
  }

  /**
   * Test {@link ProviderPropertyDescriptor#extractProviderProperties(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * ProviderPropertyDescriptor#extractProviderProperties(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List ProviderPropertyDescriptor.extractProviderProperties(IConfigurationElement)"
  })
  public void testExtractProviderProperties_givenAttribute() throws InvalidRegistryObjectException {
    // Arrange
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(iConfigurationElement.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(iConfigurationElement.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    // Act
    List<ProviderPropertyDescriptor> actualExtractProviderPropertiesResult =
        ProviderPropertyDescriptor.extractProviderProperties(iConfigurationElement);

    // Assert
    verify(iConfigurationElement, atLeast(1)).getAttribute("label");
    verify(iConfigurationElement).getChildren("property");
    verify(iConfigurationElement).getName();
    assertTrue(actualExtractProviderPropertiesResult.isEmpty());
  }

  /**
   * Test {@link ProviderPropertyDescriptor#extractProviderProperties(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * ProviderPropertyDescriptor#extractProviderProperties(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List ProviderPropertyDescriptor.extractProviderProperties(IConfigurationElement)"
  })
  public void testExtractProviderProperties_givenEmptyString()
      throws InvalidRegistryObjectException {
    // Arrange
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(iConfigurationElement.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(iConfigurationElement.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("");

    // Act
    List<ProviderPropertyDescriptor> actualExtractProviderPropertiesResult =
        ProviderPropertyDescriptor.extractProviderProperties(iConfigurationElement);

    // Assert
    verify(iConfigurationElement, atLeast(1)).getAttribute("label");
    verify(iConfigurationElement).getChildren("property");
    verify(iConfigurationElement).getName();
    assertTrue(actualExtractProviderPropertiesResult.isEmpty());
  }

  /**
   * Test {@link ProviderPropertyDescriptor#extractProviderProperties(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return size is one.
   * </ul>
   *
   * <p>Method under test: {@link
   * ProviderPropertyDescriptor#extractProviderProperties(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "List ProviderPropertyDescriptor.extractProviderProperties(IConfigurationElement)"
  })
  public void testExtractProviderProperties_thenReturnSizeIsOne()
      throws InvalidRegistryObjectException {
    // Arrange
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(iConfigurationElement.getName()).thenReturn(PropertyDescriptor.TAG_PROPERTY_GROUP);
    when(iConfigurationElement.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);
    when(iConfigurationElement.getAttribute(Mockito.<String>any())).thenReturn("");

    // Act
    List<ProviderPropertyDescriptor> actualExtractProviderPropertiesResult =
        ProviderPropertyDescriptor.extractProviderProperties(iConfigurationElement);

    // Assert
    verify(iConfigurationElement, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(iConfigurationElement).getChildren("property");
    verify(iConfigurationElement).getName();
    assertEquals(1, actualExtractProviderPropertiesResult.size());
    ProviderPropertyDescriptor getResult = actualExtractProviderPropertiesResult.get(0);
    assertEquals("", getResult.getDescription());
    assertEquals("", getResult.getDisplayName());
    assertEquals("", getResult.getHint());
    assertEquals("", getResult.getId());
    assertEquals("", getResult.getName());
    assertNull(getResult.getDefaultValue());
    assertNull(getResult.getFeatures());
    assertNull(getResult.getRequiredFeatures());
    assertNull(getResult.getPropertyType());
    assertEquals(2, getResult.getConfigurationTypes().size());
    assertEquals(PropertyLength.LONG, getResult.getLength());
    assertFalse(getResult.isRequired());
    Class<String> expectedDataType = String.class;
    assertEquals(expectedDataType, getResult.getDataType());
    assertEquals(PropertyDescriptor.NAME_UNDEFINED, getResult.getCategory());
  }
}
