package org.jkiss.dbeaver.model.websocket.registry;

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
import org.jkiss.dbeaver.model.impl.AbstractDescriptor;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor.ObjectType;
import org.jkiss.dbeaver.model.websocket.event.WSEventController;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class WSServerEventDescriptorDiffblueTest {
  /**
   * Test {@link WSServerEventDescriptor#WSServerEventDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code if}.
   *   <li>Then return {@link WSAbstractEventDescriptor#implType} ImplName is {@code if}.
   * </ul>
   *
   * <p>Method under test: {@link
   * WSServerEventDescriptor#WSServerEventDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSServerEventDescriptor.<init>(IConfigurationElement)"})
  public void testNewWSServerEventDescriptor_givenIf_thenReturnImplTypeImplNameIsIf() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(
            registryStrategy,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("if");
    when(cfg.getContributor()).thenReturn(iContributor);

    // Act
    WSServerEventDescriptor actualWsServerEventDescriptor = new WSServerEventDescriptor(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    ObjectType objectType = actualWsServerEventDescriptor.implType;
    assertEquals("if", objectType.getImplName());
    assertEquals("if", objectType.toString());
    assertEquals("if", actualWsServerEventDescriptor.getId());
    assertEquals("if", actualWsServerEventDescriptor.getTopicId());
  }

  /**
   * Test {@link WSServerEventDescriptor#WSServerEventDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return {@link WSAbstractEventDescriptor#implType} ImplName is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link
   * WSServerEventDescriptor#WSServerEventDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSServerEventDescriptor.<init>(IConfigurationElement)"})
  public void testNewWSServerEventDescriptor_thenReturnImplTypeImplNameIsAttribute() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(
            registryStrategy,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);

    // Act
    WSServerEventDescriptor actualWsServerEventDescriptor = new WSServerEventDescriptor(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    ObjectType objectType = actualWsServerEventDescriptor.implType;
    assertEquals("Attribute", objectType.getImplName());
    assertEquals("Attribute", objectType.toString());
    assertEquals("Attribute", actualWsServerEventDescriptor.getId());
    assertEquals("Attribute", actualWsServerEventDescriptor.getTopicId());
  }

  /**
   * Test {@link WSServerEventDescriptor#WSServerEventDescriptor(IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return {@link WSAbstractEventDescriptor#implType} ImplName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link
   * WSServerEventDescriptor#WSServerEventDescriptor(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WSServerEventDescriptor.<init>(IConfigurationElement)"})
  public void testNewWSServerEventDescriptor_thenReturnImplTypeImplNameIsEmptyString() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(
            registryStrategy,
            WSEventController.JOB_EVENT_HANDLER_FAMILY,
            WSEventController.JOB_EVENT_HANDLER_FAMILY);
    new RegistryObjectManager(registry);

    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("");
    when(cfg.getContributor()).thenReturn(iContributor);

    // Act
    WSServerEventDescriptor actualWsServerEventDescriptor = new WSServerEventDescriptor(cfg);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    ObjectType objectType = actualWsServerEventDescriptor.implType;
    assertEquals("", objectType.getImplName());
    assertEquals("", objectType.toString());
    assertEquals("", actualWsServerEventDescriptor.getId());
    assertEquals("", actualWsServerEventDescriptor.getTopicId());
  }

  /**
   * Test {@link WSServerEventDescriptor#getEventClass()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link WSServerEventDescriptor#getEventClass()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.Class WSServerEventDescriptor.getEventClass()"})
  public void testGetEventClass_thenThrowIllegalStateException() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> new WSServerEventDescriptor(cfg).getEventClass());
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
  }
}
