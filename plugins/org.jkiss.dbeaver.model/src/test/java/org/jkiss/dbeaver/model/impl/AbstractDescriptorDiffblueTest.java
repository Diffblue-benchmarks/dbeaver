package org.jkiss.dbeaver.model.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyByte;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.nio.file.Paths;
import org.apache.commons.jexl3.JexlEngine;
import org.apache.commons.jexl3.JexlException;
import org.apache.commons.jexl3.JexlException.Variable;
import org.apache.commons.jexl3.JexlExpression;
import org.apache.commons.jexl3.internal.Engine;
import org.apache.commons.jexl3.internal.Script;
import org.apache.commons.jexl3.internal.introspection.Uberspect;
import org.apache.commons.jexl3.parser.ASTAddNode;
import org.apache.commons.jexl3.parser.Token;
import org.eclipse.core.expressions.AndExpression;
import org.eclipse.core.expressions.EqualsExpression;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.WithExpression;
import org.eclipse.core.internal.expressions.ResolveExpression;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.internal.registry.IObjectManager;
import org.eclipse.core.internal.registry.RegistryObjectManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.spi.RegistryStrategy;
import org.eclipse.osgi.internal.framework.EquinoxBundle;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.AbstractDescriptor.ObjectType;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.osgi.framework.Bundle;

@RunWith(MockitoJUnitRunner.class)
public class AbstractDescriptorDiffblueTest {
  @Mock private Bundle bundle;

  /**
   * Test ObjectType {@link ObjectType#appliesTo(Object, Object)}.
   *
   * <ul>
   *   <li>Given {@link EquinoxBundle} {@link EquinoxBundle#getState()} return one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#appliesTo(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ObjectType.appliesTo(Object, Object)"})
  public void testObjectTypeAppliesTo_givenEquinoxBundleGetStateReturnOne_thenReturnFalse() {
    // Arrange
    EquinoxBundle equinoxBundle = mock(EquinoxBundle.class);
    when(equinoxBundle.getState()).thenReturn(1);

    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        mock(PropertyGroupDescriptor.class);
    when(propertyGroupDescriptor.getContributorBundle()).thenReturn(equinoxBundle);

    // Act
    boolean actualAppliesToResult =
        propertyGroupDescriptor.new ObjectType("foo").appliesTo(DBPEvent.RENAME, DBPEvent.RENAME);

    // Assert
    verify(equinoxBundle).getState();
    verify(propertyGroupDescriptor).getContributorBundle();
    assertFalse(actualAppliesToResult);
  }

  /**
   * Test ObjectType {@link ObjectType#appliesTo(Object, Object)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#appliesTo(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ObjectType.appliesTo(Object, Object)"})
  public void testObjectTypeAppliesTo_givenJavaLangObject_thenReturnTrue() {
    // Arrange
    EquinoxBundle equinoxBundle = mock(EquinoxBundle.class);
    when(equinoxBundle.getState()).thenReturn(Integer.SIZE);

    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        mock(PropertyGroupDescriptor.class);
    Class<Object> forNameResult = Object.class;
    when(propertyGroupDescriptor.getObjectClass(
            Mockito.<String>any(), Mockito.<Class<Object>>any()))
        .thenReturn(forNameResult);
    when(propertyGroupDescriptor.getContributorBundle()).thenReturn(equinoxBundle);

    // Act
    boolean actualAppliesToResult =
        propertyGroupDescriptor.new ObjectType("foo").appliesTo(DBPEvent.RENAME, DBPEvent.RENAME);

    // Assert
    verify(equinoxBundle).getState();
    verify(propertyGroupDescriptor).getContributorBundle();
    verify(propertyGroupDescriptor).getObjectClass(eq("foo"), isA(Class.class));
    assertTrue(actualAppliesToResult);
  }

  /**
   * Test ObjectType {@link ObjectType#appliesTo(Object, Object)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#appliesTo(Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ObjectType.appliesTo(Object, Object)"})
  public void testObjectTypeAppliesTo_thenThrowIllegalStateException() {
    // Arrange
    EquinoxBundle equinoxBundle = mock(EquinoxBundle.class);
    when(equinoxBundle.getState()).thenReturn(Integer.SIZE);

    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        mock(PropertyGroupDescriptor.class);
    when(propertyGroupDescriptor.getObjectClass(
            Mockito.<String>any(), Mockito.<Class<Object>>any()))
        .thenThrow(new IllegalStateException());
    when(propertyGroupDescriptor.getContributorBundle()).thenReturn(equinoxBundle);

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () ->
            propertyGroupDescriptor.new ObjectType("foo")
                .appliesTo(DBPEvent.RENAME, DBPEvent.RENAME));
    verify(equinoxBundle).getState();
    verify(propertyGroupDescriptor).getContributorBundle();
    verify(propertyGroupDescriptor).getObjectClass(eq("foo"), isA(Class.class));
  }

  /**
   * Test ObjectType {@link ObjectType#matchesType(Class)}.
   *
   * <ul>
   *   <li>Given {@link EquinoxBundle} {@link EquinoxBundle#getState()} return one.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#matchesType(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ObjectType.matchesType(Class)"})
  public void testObjectTypeMatchesType_givenEquinoxBundleGetStateReturnOne_thenReturnFalse() {
    // Arrange
    EquinoxBundle equinoxBundle = mock(EquinoxBundle.class);
    when(equinoxBundle.getState()).thenReturn(1);

    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        mock(PropertyGroupDescriptor.class);
    when(propertyGroupDescriptor.getContributorBundle()).thenReturn(equinoxBundle);
    ObjectType objectType = propertyGroupDescriptor.new ObjectType("foo");
    Class<Object> clazz = Object.class;

    // Act
    boolean actualMatchesTypeResult = objectType.matchesType(clazz);

    // Assert
    verify(equinoxBundle).getState();
    verify(propertyGroupDescriptor).getContributorBundle();
    assertFalse(actualMatchesTypeResult);
  }

  /**
   * Test ObjectType {@link ObjectType#matchesType(Class)}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>When {@code Object}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#matchesType(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ObjectType.matchesType(Class)"})
  public void testObjectTypeMatchesType_givenJavaLangObject_whenJavaLangObject_thenReturnTrue() {
    // Arrange
    EquinoxBundle equinoxBundle = mock(EquinoxBundle.class);
    when(equinoxBundle.getState()).thenReturn(Integer.SIZE);

    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        mock(PropertyGroupDescriptor.class);
    Class<Object> forNameResult = Object.class;
    when(propertyGroupDescriptor.getObjectClass(
            Mockito.<String>any(), Mockito.<Class<Object>>any()))
        .thenReturn(forNameResult);
    when(propertyGroupDescriptor.getContributorBundle()).thenReturn(equinoxBundle);
    ObjectType objectType = propertyGroupDescriptor.new ObjectType("foo");
    Class<Object> clazz = Object.class;

    // Act
    boolean actualMatchesTypeResult = objectType.matchesType(clazz);

    // Assert
    verify(equinoxBundle).getState();
    verify(propertyGroupDescriptor).getContributorBundle();
    verify(propertyGroupDescriptor).getObjectClass(eq("foo"), isA(Class.class));
    assertTrue(actualMatchesTypeResult);
  }

  /**
   * Test ObjectType {@link ObjectType#matchesType(Class)}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#matchesType(Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean ObjectType.matchesType(Class)"})
  public void testObjectTypeMatchesType_thenThrowIllegalStateException() {
    // Arrange
    EquinoxBundle equinoxBundle = mock(EquinoxBundle.class);
    when(equinoxBundle.getState()).thenReturn(Integer.SIZE);

    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        mock(PropertyGroupDescriptor.class);
    when(propertyGroupDescriptor.getObjectClass(
            Mockito.<String>any(), Mockito.<Class<Object>>any()))
        .thenThrow(new IllegalStateException());
    when(propertyGroupDescriptor.getContributorBundle()).thenReturn(equinoxBundle);
    ObjectType objectType = propertyGroupDescriptor.new ObjectType("foo");
    Class<Object> clazz = Object.class;

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> objectType.matchesType(clazz));
    verify(equinoxBundle).getState();
    verify(propertyGroupDescriptor).getContributorBundle();
    verify(propertyGroupDescriptor).getObjectClass(eq("foo"), isA(Class.class));
  }

  /**
   * Test ObjectType {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then return ImplName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectType.<init>(AbstractDescriptor, IConfigurationElement)"})
  public void testObjectTypeNewObjectType_givenEmptyString_thenReturnImplNameIsEmptyString() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("");

    // Act
    ObjectType actualObjectType =
        propertyGroupDescriptor.new ObjectType(configurationElementHandle);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("", actualObjectType.getImplName());
    assertEquals("", actualObjectType.toString());
  }

  /**
   * Test ObjectType {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement,
   * String)}.
   *
   * <ul>
   *   <li>Given empty string.
   *   <li>Then return ImplName is empty string.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectType.<init>(AbstractDescriptor, IConfigurationElement, String)"})
  public void testObjectTypeNewObjectType_givenEmptyString_thenReturnImplNameIsEmptyString2() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("");

    // Act
    ObjectType actualObjectType =
        propertyGroupDescriptor.new ObjectType(configurationElementHandle, "foo");

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("", actualObjectType.getImplName());
    assertEquals("", actualObjectType.toString());
  }

  /**
   * Test ObjectType {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement)}.
   *
   * <ul>
   *   <li>Given {@code if}.
   *   <li>Then return ImplName is {@code if}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectType.<init>(AbstractDescriptor, IConfigurationElement)"})
  public void testObjectTypeNewObjectType_givenIf_thenReturnImplNameIsIf() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("if");

    // Act
    ObjectType actualObjectType =
        propertyGroupDescriptor.new ObjectType(configurationElementHandle);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("if", actualObjectType.getImplName());
    assertEquals("if", actualObjectType.toString());
  }

  /**
   * Test ObjectType {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement,
   * String)}.
   *
   * <ul>
   *   <li>Given {@code if}.
   *   <li>Then return ImplName is {@code if}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectType.<init>(AbstractDescriptor, IConfigurationElement, String)"})
  public void testObjectTypeNewObjectType_givenIf_thenReturnImplNameIsIf2() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("if");

    // Act
    ObjectType actualObjectType =
        propertyGroupDescriptor.new ObjectType(configurationElementHandle, "foo");

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("if", actualObjectType.getImplName());
    assertEquals("if", actualObjectType.toString());
  }

  /**
   * Test ObjectType {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return ImplName is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectType.<init>(AbstractDescriptor, IConfigurationElement)"})
  public void testObjectTypeNewObjectType_thenReturnImplNameIsAttribute() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    // Act
    ObjectType actualObjectType =
        propertyGroupDescriptor.new ObjectType(configurationElementHandle);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualObjectType.getImplName());
    assertEquals("Attribute", actualObjectType.toString());
  }

  /**
   * Test ObjectType {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement,
   * String)}.
   *
   * <ul>
   *   <li>Then return ImplName is {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectType.<init>(AbstractDescriptor, IConfigurationElement, String)"})
  public void testObjectTypeNewObjectType_thenReturnImplNameIsAttribute2() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any())).thenReturn("Attribute");

    // Act
    ObjectType actualObjectType =
        propertyGroupDescriptor.new ObjectType(configurationElementHandle, "foo");

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualObjectType.getImplName());
    assertEquals("Attribute", actualObjectType.toString());
  }

  /**
   * Test ObjectType {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement)}.
   *
   * <ul>
   *   <li>Then return ImplName is {@code nameforceCheck}.
   * </ul>
   *
   * <p>Method under test: {@link ObjectType#ObjectType(AbstractDescriptor, IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ObjectType.<init>(AbstractDescriptor, IConfigurationElement)"})
  public void testObjectTypeNewObjectType_thenReturnImplNameIsNameforceCheck() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle configurationElementHandle = mock(ConfigurationElementHandle.class);
    when(configurationElementHandle.getAttribute(Mockito.<String>any()))
        .thenReturn("nameforceCheck");

    // Act
    ObjectType actualObjectType =
        propertyGroupDescriptor.new ObjectType(configurationElementHandle);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(configurationElementHandle, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("nameforceCheck", actualObjectType.getImplName());
    assertEquals("nameforceCheck", actualObjectType.toString());
  }

  /**
   * Test {@link AbstractDescriptor#parseExpression(String)}.
   *
   * <p>Method under test: {@link AbstractDescriptor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JexlExpression AbstractDescriptor.parseExpression(String)"})
  public void testParseExpression() throws DBException {
    // Arrange and Act
    JexlExpression actualParseExpressionResult =
        AbstractDescriptor.parseExpression(
            "org.jkiss.dbeaver.model.impl.AbstractDescriptororg.jkiss.dbeaver.model.impl.AbstractDescriptor");

    // Assert
    JexlEngine engine = ((Script) actualParseExpressionResult).getEngine();
    assertTrue(engine instanceof Engine);
    assertTrue(actualParseExpressionResult instanceof Script);
    assertTrue(engine.getUberspect() instanceof Uberspect);
    assertEquals(
        "org.jkiss.dbeaver.model.impl.AbstractDescriptororg.jkiss.dbeaver.model.impl.AbstractDescriptor",
        actualParseExpressionResult.getParsedText());
    assertEquals(
        "org.jkiss.dbeaver.model.impl.AbstractDescriptororg.jkiss.dbeaver.model.impl.AbstractDescriptor",
        actualParseExpressionResult.getSourceText());
    assertNull(((Script) actualParseExpressionResult).getLocalVariables());
    assertNull(((Script) actualParseExpressionResult).getParameters());
    assertEquals(1, ((Script) actualParseExpressionResult).getVariables().size());
    assertFalse(engine.isSilent());
    assertTrue(((Script) actualParseExpressionResult).getPragmas().isEmpty());
    assertTrue(engine.isCancellable());
    assertTrue(engine.isDebug());
    assertTrue(engine.isStrict());
  }

  /**
   * Test {@link AbstractDescriptor#parseExpression(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return ParsedText is {@code 42}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JexlExpression AbstractDescriptor.parseExpression(String)"})
  public void testParseExpression_when42_thenReturnParsedTextIs42() throws DBException {
    // Arrange and Act
    JexlExpression actualParseExpressionResult = AbstractDescriptor.parseExpression("42");

    // Assert
    JexlEngine engine = ((Script) actualParseExpressionResult).getEngine();
    assertTrue(engine instanceof Engine);
    assertTrue(actualParseExpressionResult instanceof Script);
    assertTrue(engine.getUberspect() instanceof Uberspect);
    assertEquals("42", actualParseExpressionResult.getParsedText());
    assertEquals("42", actualParseExpressionResult.getSourceText());
    assertNull(((Script) actualParseExpressionResult).getLocalVariables());
    assertNull(((Script) actualParseExpressionResult).getParameters());
    assertFalse(engine.isSilent());
    assertTrue(((Script) actualParseExpressionResult).getPragmas().isEmpty());
    assertTrue(((Script) actualParseExpressionResult).getVariables().isEmpty());
    assertTrue(engine.isCancellable());
    assertTrue(engine.isDebug());
    assertTrue(engine.isStrict());
  }

  /**
   * Test {@link AbstractDescriptor#parseExpression(String)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return ParsedText is empty string.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JexlExpression AbstractDescriptor.parseExpression(String)"})
  public void testParseExpression_whenEmptyString_thenReturnParsedTextIsEmptyString()
      throws DBException {
    // Arrange and Act
    JexlExpression actualParseExpressionResult = AbstractDescriptor.parseExpression("");

    // Assert
    JexlEngine engine = ((Script) actualParseExpressionResult).getEngine();
    assertTrue(engine instanceof Engine);
    assertTrue(actualParseExpressionResult instanceof Script);
    assertTrue(engine.getUberspect() instanceof Uberspect);
    assertEquals("", actualParseExpressionResult.getParsedText());
    assertEquals("", actualParseExpressionResult.getSourceText());
    assertNull(((Script) actualParseExpressionResult).getLocalVariables());
    assertNull(((Script) actualParseExpressionResult).getParameters());
    assertFalse(engine.isSilent());
    assertTrue(((Script) actualParseExpressionResult).getPragmas().isEmpty());
    assertTrue(((Script) actualParseExpressionResult).getVariables().isEmpty());
    assertTrue(engine.isCancellable());
    assertTrue(engine.isDebug());
    assertTrue(engine.isStrict());
  }

  /**
   * Test {@link AbstractDescriptor#parseExpression(String)}.
   *
   * <ul>
   *   <li>When {@code Expr String}.
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#parseExpression(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"JexlExpression AbstractDescriptor.parseExpression(String)"})
  public void testParseExpression_whenExprString_thenThrowDBException() throws DBException {
    // Arrange, Act and Assert
    assertThrows(DBException.class, () -> AbstractDescriptor.parseExpression("Expr String"));
  }

  /**
   * Test {@link AbstractDescriptor#evalExpression(String, Object, Object)}.
   *
   * <p>Method under test: {@link AbstractDescriptor#evalExpression(String, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractDescriptor.evalExpression(String, Object, Object)"})
  public void testEvalExpression() {
    // Arrange and Act
    Object actualEvalExpressionResult =
        AbstractDescriptor.evalExpression(
            "org.jkiss.dbeaver.model.impl.AbstractDescriptor.parseExpression@1:6 parsing error in 'String'",
            DBPEvent.RENAME,
            DBPEvent.RENAME);

    // Assert
    assertNull(actualEvalExpressionResult);
  }

  /**
   * Test {@link AbstractDescriptor#evalExpression(String, Object, Object)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return intValue is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#evalExpression(String, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractDescriptor.evalExpression(String, Object, Object)"})
  public void testEvalExpression_when42_thenReturnIntValueIsFortyTwo() {
    // Arrange and Act
    Object actualEvalExpressionResult =
        AbstractDescriptor.evalExpression("42", DBPEvent.RENAME, DBPEvent.RENAME);

    // Assert
    assertEquals(42, ((Integer) actualEvalExpressionResult).intValue());
  }

  /**
   * Test {@link AbstractDescriptor#evalExpression(String, Object, Object)}.
   *
   * <ul>
   *   <li>When empty string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#evalExpression(String, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractDescriptor.evalExpression(String, Object, Object)"})
  public void testEvalExpression_whenEmptyString_thenReturnNull() {
    // Arrange and Act
    Object actualEvalExpressionResult =
        AbstractDescriptor.evalExpression("", DBPEvent.RENAME, DBPEvent.RENAME);

    // Assert
    assertNull(actualEvalExpressionResult);
  }

  /**
   * Test {@link AbstractDescriptor#evalExpression(String, Object, Object)}.
   *
   * <ul>
   *   <li>When {@code Expr String}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#evalExpression(String, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractDescriptor.evalExpression(String, Object, Object)"})
  public void testEvalExpression_whenExprString_thenReturnNull() {
    // Arrange and Act
    Object actualEvalExpressionResult =
        AbstractDescriptor.evalExpression("Expr String", DBPEvent.RENAME, DBPEvent.RENAME);

    // Assert
    assertNull(actualEvalExpressionResult);
  }

  /**
   * Test {@link AbstractDescriptor#evalExpression(String, Object, Object)}.
   *
   * <ul>
   *   <li>When {@code org.jkiss.dbeaver.model}.
   *   <li>Then throw {@link Variable}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#evalExpression(String, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractDescriptor.evalExpression(String, Object, Object)"})
  public void testEvalExpression_whenOrgJkissDbeaverModel_thenThrowVariable() {
    // Arrange, Act and Assert
    assertThrows(
        Variable.class,
        () ->
            AbstractDescriptor.evalExpression(
                "org.jkiss.dbeaver.model", DBPEvent.RENAME, DBPEvent.RENAME));
  }

  /**
   * Test {@link AbstractDescriptor#evalExpression(String, Object, Object)}.
   *
   * <ul>
   *   <li>When {@link AbstractDescriptor#VAR_CONTEXT}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#evalExpression(String, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractDescriptor.evalExpression(String, Object, Object)"})
  public void testEvalExpression_whenVar_context_thenDoesNotThrow() {
    // Arrange and Act
    AbstractDescriptor.evalExpression(
        AbstractDescriptor.VAR_CONTEXT, DBPEvent.REORDER, DBPEvent.RENAME);

    // Assert
  }

  /**
   * Test {@link AbstractDescriptor#evalExpression(String, Object, Object)}.
   *
   * <ul>
   *   <li>When {@link AbstractDescriptor#VAR_CONTEXT}.
   *   <li>Then throw {@link Variable}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#evalExpression(String, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractDescriptor.evalExpression(String, Object, Object)"})
  public void testEvalExpression_whenVar_context_thenThrowVariable() {
    // Arrange, Act and Assert
    assertThrows(
        Variable.class,
        () ->
            AbstractDescriptor.evalExpression(
                AbstractDescriptor.VAR_CONTEXT, DBPEvent.REORDER, null));
  }

  /**
   * Test {@link AbstractDescriptor#evalExpression(String, Object, Object)}.
   *
   * <ul>
   *   <li>When {@link AbstractDescriptor#VAR_OBJECT}.
   *   <li>Then does not throw.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#evalExpression(String, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractDescriptor.evalExpression(String, Object, Object)"})
  public void testEvalExpression_whenVar_object_thenDoesNotThrow() {
    // Arrange and Act
    AbstractDescriptor.evalExpression(
        AbstractDescriptor.VAR_OBJECT, DBPEvent.REORDER, DBPEvent.RENAME);

    // Assert
  }

  /**
   * Test {@link AbstractDescriptor#evalExpression(String, Object, Object)}.
   *
   * <ul>
   *   <li>When {@link AbstractDescriptor#VAR_OBJECT}.
   *   <li>Then throw {@link Variable}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#evalExpression(String, Object, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object AbstractDescriptor.evalExpression(String, Object, Object)"})
  public void testEvalExpression_whenVar_object_thenThrowVariable() {
    // Arrange, Act and Assert
    assertThrows(
        Variable.class,
        () ->
            AbstractDescriptor.evalExpression(
                AbstractDescriptor.VAR_OBJECT, null, DBPEvent.RENAME));
  }

  /**
   * Test {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement)} with {@code
   * config}.
   *
   * <p>Method under test: {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Expression AbstractDescriptor.getEnablementExpression(IConfigurationElement)"
  })
  public void testGetEnablementExpressionWithConfig() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(
            new IConfigurationElement[] {
              new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1)
            });

    // Act
    Expression actualEnablementExpression = AbstractDescriptor.getEnablementExpression(config);

    // Assert
    verify(config).getChildren("enabledWhen");
    assertNull(actualEnablementExpression);
  }

  /**
   * Test {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement)} with {@code
   * config}.
   *
   * <p>Method under test: {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Expression AbstractDescriptor.getEnablementExpression(IConfigurationElement)"
  })
  public void testGetEnablementExpressionWithConfig2() throws InvalidRegistryObjectException {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    when(iConfigurationElement.getChildren())
        .thenReturn(
            new IConfigurationElement[] {
              new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1)
            });

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);

    // Act
    Expression actualEnablementExpression = AbstractDescriptor.getEnablementExpression(config);

    // Assert
    verify(config).getChildren("enabledWhen");
    verify(iConfigurationElement).getChildren();
    assertNull(actualEnablementExpression);
  }

  /**
   * Test {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement)} with {@code
   * config}.
   *
   * <p>Method under test: {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Expression AbstractDescriptor.getEnablementExpression(IConfigurationElement)"
  })
  public void testGetEnablementExpressionWithConfig3() throws InvalidRegistryObjectException {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getChildren()).thenReturn(new IConfigurationElement[] {});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1);

    // Act
    Expression actualEnablementExpression = AbstractDescriptor.getEnablementExpression(config);

    // Assert
    verify(config).getChildren("enabledWhen");
    verify(iConfigurationElement).getChildren();
    assertNull(actualEnablementExpression);
  }

  /**
   * Test {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement, String)} with
   * {@code config}, {@code expressionElementName}.
   *
   * <p>Method under test: {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Expression AbstractDescriptor.getEnablementExpression(IConfigurationElement, String)"
  })
  public void testGetEnablementExpressionWithConfigExpressionElementName() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(
            new IConfigurationElement[] {
              new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1)
            });

    // Act
    Expression actualEnablementExpression =
        AbstractDescriptor.getEnablementExpression(config, "Expression Element Name");

    // Assert
    verify(config).getChildren("Expression Element Name");
    assertNull(actualEnablementExpression);
  }

  /**
   * Test {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement, String)} with
   * {@code config}, {@code expressionElementName}.
   *
   * <p>Method under test: {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Expression AbstractDescriptor.getEnablementExpression(IConfigurationElement, String)"
  })
  public void testGetEnablementExpressionWithConfigExpressionElementName2() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any())).thenReturn(new IConfigurationElement[] {});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1);

    // Act
    Expression actualEnablementExpression =
        AbstractDescriptor.getEnablementExpression(config, "Expression Element Name");

    // Assert
    verify(config).getChildren("Expression Element Name");
    assertNull(actualEnablementExpression);
  }

  /**
   * Test {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement, String)} with
   * {@code config}, {@code expressionElementName}.
   *
   * <p>Method under test: {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Expression AbstractDescriptor.getEnablementExpression(IConfigurationElement, String)"
  })
  public void testGetEnablementExpressionWithConfigExpressionElementName3()
      throws InvalidRegistryObjectException {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    when(iConfigurationElement.getChildren())
        .thenReturn(
            new IConfigurationElement[] {
              new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1)
            });

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);

    // Act
    Expression actualEnablementExpression =
        AbstractDescriptor.getEnablementExpression(config, "Expression Element Name");

    // Assert
    verify(config).getChildren("Expression Element Name");
    verify(iConfigurationElement).getChildren();
    assertNull(actualEnablementExpression);
  }

  /**
   * Test {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement, String)} with
   * {@code config}, {@code expressionElementName}.
   *
   * <p>Method under test: {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Expression AbstractDescriptor.getEnablementExpression(IConfigurationElement, String)"
  })
  public void testGetEnablementExpressionWithConfigExpressionElementName4()
      throws InvalidRegistryObjectException {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getChildren()).thenReturn(new IConfigurationElement[] {});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1);

    // Act
    Expression actualEnablementExpression =
        AbstractDescriptor.getEnablementExpression(config, "Expression Element Name");

    // Assert
    verify(config).getChildren("Expression Element Name");
    verify(iConfigurationElement).getChildren();
    assertNull(actualEnablementExpression);
  }

  /**
   * Test {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement, String)} with
   * {@code config}, {@code expressionElementName}.
   *
   * <ul>
   *   <li>Then calls {@link IConfigurationElement#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Expression AbstractDescriptor.getEnablementExpression(IConfigurationElement, String)"
  })
  public void testGetEnablementExpressionWithConfigExpressionElementName_thenCallsGetName()
      throws InvalidRegistryObjectException {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");

    IConfigurationElement iConfigurationElement2 = mock(IConfigurationElement.class);
    when(iConfigurationElement2.getChildren())
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement2});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    Expression actualEnablementExpression =
        AbstractDescriptor.getEnablementExpression(config, "Expression Element Name");

    // Assert
    verify(config).getChildren("Expression Element Name");
    verify(iConfigurationElement2).getChildren();
    verify(iConfigurationElement).getName();
    assertNull(actualEnablementExpression);
  }

  /**
   * Test {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement, String)} with
   * {@code config}, {@code expressionElementName}.
   *
   * <ul>
   *   <li>Then calls {@link IObjectManager#getObject(int, byte)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Expression AbstractDescriptor.getEnablementExpression(IConfigurationElement, String)"
  })
  public void testGetEnablementExpressionWithConfigExpressionElementName_thenCallsGetObject() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IObjectManager objectManager = mock(IObjectManager.class);
    when(objectManager.getObject(anyInt(), anyByte()))
        .thenThrow(new JexlException(new ASTAddNode(1), "Msg"));

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {new ConfigurationElementHandle(objectManager, 1)});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);

    // Act
    Expression actualEnablementExpression =
        AbstractDescriptor.getEnablementExpression(config, "Expression Element Name");

    // Assert
    verify(config).getChildren("Expression Element Name");
    verify(objectManager).getObject(1, (byte) 1);
    assertNull(actualEnablementExpression);
  }

  /**
   * Test {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement)} with {@code
   * config}.
   *
   * <ul>
   *   <li>Given empty array of {@link IConfigurationElement}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Expression AbstractDescriptor.getEnablementExpression(IConfigurationElement)"
  })
  public void testGetEnablementExpressionWithConfig_givenEmptyArrayOfIConfigurationElement() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any())).thenReturn(new IConfigurationElement[] {});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    new ConfigurationElementHandle(new RegistryObjectManager(registry2), 1);

    // Act
    Expression actualEnablementExpression = AbstractDescriptor.getEnablementExpression(config);

    // Assert
    verify(config).getChildren("enabledWhen");
    assertNull(actualEnablementExpression);
  }

  /**
   * Test {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement)} with {@code
   * config}.
   *
   * <ul>
   *   <li>Then calls {@link IConfigurationElement#getName()}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Expression AbstractDescriptor.getEnablementExpression(IConfigurationElement)"
  })
  public void testGetEnablementExpressionWithConfig_thenCallsGetName()
      throws InvalidRegistryObjectException {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IConfigurationElement iConfigurationElement = mock(IConfigurationElement.class);
    when(iConfigurationElement.getName()).thenReturn("Name");

    IConfigurationElement iConfigurationElement2 = mock(IConfigurationElement.class);
    when(iConfigurationElement2.getChildren())
        .thenReturn(new IConfigurationElement[] {iConfigurationElement});

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {iConfigurationElement2});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);
    File[] storageDirs3 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy3 =
        new RegistryStrategy(storageDirs3, new boolean[] {true, false, true, false});
    ExtensionRegistry registry2 =
        new ExtensionRegistry(registryStrategy3, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry2);

    // Act
    Expression actualEnablementExpression = AbstractDescriptor.getEnablementExpression(config);

    // Assert
    verify(config).getChildren("enabledWhen");
    verify(iConfigurationElement2).getChildren();
    verify(iConfigurationElement).getName();
    assertNull(actualEnablementExpression);
  }

  /**
   * Test {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement)} with {@code
   * config}.
   *
   * <ul>
   *   <li>Then calls {@link IObjectManager#getObject(int, byte)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getEnablementExpression(IConfigurationElement)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "Expression AbstractDescriptor.getEnablementExpression(IConfigurationElement)"
  })
  public void testGetEnablementExpressionWithConfig_thenCallsGetObject() {
    // Arrange
    File[] storageDirs =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy =
        new RegistryStrategy(storageDirs, new boolean[] {true, false, true, false});
    ExtensionRegistry registry =
        new ExtensionRegistry(registryStrategy, DBPEvent.RENAME, DBPEvent.RENAME);
    new RegistryObjectManager(registry);

    IObjectManager objectManager = mock(IObjectManager.class);
    when(objectManager.getObject(anyInt(), anyByte()))
        .thenThrow(new JexlException(new ASTAddNode(1), "Msg"));

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getChildren(Mockito.<String>any()))
        .thenReturn(new IConfigurationElement[] {new ConfigurationElementHandle(objectManager, 1)});
    File[] storageDirs2 =
        new File[] {Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()};
    RegistryStrategy registryStrategy2 =
        new RegistryStrategy(storageDirs2, new boolean[] {true, false, true, false});
    new ExtensionRegistry(registryStrategy2, DBPEvent.RENAME, DBPEvent.RENAME);

    // Act
    Expression actualEnablementExpression = AbstractDescriptor.getEnablementExpression(config);

    // Assert
    verify(config).getChildren("enabledWhen");
    verify(objectManager).getObject(1, (byte) 1);
    assertNull(actualEnablementExpression);
  }

  /**
   * Test {@link AbstractDescriptor#isExpressionTrue(Expression, Object)}.
   *
   * <ul>
   *   <li>Given {@link AndExpression} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#isExpressionTrue(Expression, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractDescriptor.isExpressionTrue(Expression, Object)"})
  public void testIsExpressionTrue_givenAndExpression() {
    // Arrange
    Object[] args = new Object[] {DBPEvent.RENAME};

    ResolveExpression expression = new ResolveExpression("Variable", args);
    expression.add(new AndExpression());

    // Act and Assert
    assertFalse(AbstractDescriptor.isExpressionTrue(expression, DBPEvent.RENAME));
  }

  /**
   * Test {@link AbstractDescriptor#isExpressionTrue(Expression, Object)}.
   *
   * <ul>
   *   <li>When {@link AndExpression} (default constructor).
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#isExpressionTrue(Expression, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractDescriptor.isExpressionTrue(Expression, Object)"})
  public void testIsExpressionTrue_whenAndExpression_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(AbstractDescriptor.isExpressionTrue(new AndExpression(), DBPEvent.RENAME));
  }

  /**
   * Test {@link AbstractDescriptor#isExpressionTrue(Expression, Object)}.
   *
   * <ul>
   *   <li>When {@link EqualsExpression#EqualsExpression(Object)} with expectedValue is {@link
   *       DBPEvent#RENAME}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#isExpressionTrue(Expression, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractDescriptor.isExpressionTrue(Expression, Object)"})
  public void testIsExpressionTrue_whenEqualsExpressionWithExpectedValueIsRename() {
    // Arrange, Act and Assert
    assertTrue(
        AbstractDescriptor.isExpressionTrue(
            new EqualsExpression(DBPEvent.RENAME), DBPEvent.RENAME));
  }

  /**
   * Test {@link AbstractDescriptor#isExpressionTrue(Expression, Object)}.
   *
   * <ul>
   *   <li>When {@link EqualsExpression#EqualsExpression(Object)} with expectedValue is {@link
   *       DBPEvent#REORDER}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#isExpressionTrue(Expression, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractDescriptor.isExpressionTrue(Expression, Object)"})
  public void testIsExpressionTrue_whenEqualsExpressionWithExpectedValueIsReorder() {
    // Arrange, Act and Assert
    assertFalse(
        AbstractDescriptor.isExpressionTrue(
            new EqualsExpression(DBPEvent.REORDER), DBPEvent.RENAME));
  }

  /**
   * Test {@link AbstractDescriptor#isExpressionTrue(Expression, Object)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#isExpressionTrue(Expression, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractDescriptor.isExpressionTrue(Expression, Object)"})
  public void testIsExpressionTrue_whenNull_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(AbstractDescriptor.isExpressionTrue(null, null));
  }

  /**
   * Test {@link AbstractDescriptor#isExpressionTrue(Expression, Object)}.
   *
   * <ul>
   *   <li>When {@link WithExpression#WithExpression(String)} with variable is {@code
   *       0123456789ABCDEF}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#isExpressionTrue(Expression, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean AbstractDescriptor.isExpressionTrue(Expression, Object)"})
  public void testIsExpressionTrue_whenWithExpressionWithVariableIs0123456789abcdef() {
    // Arrange, Act and Assert
    assertFalse(
        AbstractDescriptor.isExpressionTrue(
            new WithExpression("0123456789ABCDEF"), DBPEvent.RENAME));
  }

  /**
   * Test {@link AbstractDescriptor#getPluginId()}.
   *
   * <ul>
   *   <li>Given {@link IContributor} {@link IContributor#getName()} return {@code Name}.
   *   <li>Then return {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getPluginId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String AbstractDescriptor.getPluginId()"})
  public void testGetPluginId_givenIContributorGetNameReturnName_thenReturnName() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);

    // Act
    String actualPluginId = propertyGroupDescriptor.getPluginId();

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Name", actualPluginId);
  }

  /**
   * Test {@link AbstractDescriptor#getContributorBundle()}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getContributorBundle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Bundle AbstractDescriptor.getContributorBundle()"})
  public void testGetContributorBundle_thenThrowIllegalStateException() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);

    // Act and Assert
    assertThrows(IllegalStateException.class, () -> propertyGroupDescriptor.getContributorBundle());
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link AbstractDescriptor#replaceContributor(IContributor)}.
   *
   * <ul>
   *   <li>Given {@link IContributor} {@link IContributor#getName()} return {@code Name}.
   *   <li>Then calls {@link ConfigurationElementHandle#getAttribute(String)}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#replaceContributor(IContributor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AbstractDescriptor.replaceContributor(IContributor)"})
  public void testReplaceContributor_givenIContributorGetNameReturnName_thenCallsGetAttribute() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);

    IContributor contributor = mock(IContributor.class);
    when(contributor.getName()).thenReturn("Name");

    // Act
    propertyGroupDescriptor.replaceContributor(contributor);

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    verify(contributor).getName();
  }

  /**
   * Test {@link AbstractDescriptor#getImplClass(String, Class)} with {@code className}, {@code
   * type}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getImplClass(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getImplClass(String, Class)"})
  public void testGetImplClassWithClassNameType_thenThrowIllegalStateException() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);
    Class<Object> type = Object.class;

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> propertyGroupDescriptor.getImplClass("Class Name", type));
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link AbstractDescriptor#getImplClass(String)} with {@code className}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getImplClass(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getImplClass(String)"})
  public void testGetImplClassWithClassName_thenThrowIllegalStateException() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> propertyGroupDescriptor.getImplClass("Class Name"));
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link AbstractDescriptor#getImplClass(Bundle, String, Class)} with {@code fromBundle},
   * {@code className}, {@code type}.
   *
   * <p>Method under test: {@link AbstractDescriptor#getImplClass(Bundle, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getImplClass(Bundle, String, Class)"})
  public void testGetImplClassWithFromBundleClassNameType() throws ClassNotFoundException {
    // Arrange
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<?>>when(bundle.loadClass(Mockito.<String>any())).thenReturn(forNameResult);
    Class<AbstractDescriptor> type = AbstractDescriptor.class;

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> AbstractDescriptor.getImplClass(bundle, "Class Name", type));
    verify(bundle).loadClass("Class Name");
  }

  /**
   * Test {@link AbstractDescriptor#getImplClass(Bundle, String, Class)} with {@code fromBundle},
   * {@code className}, {@code type}.
   *
   * <ul>
   *   <li>Given {@link ASTAddNode#ASTAddNode(int)} with id is one.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getImplClass(Bundle, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getImplClass(Bundle, String, Class)"})
  public void testGetImplClassWithFromBundleClassNameType_givenASTAddNodeWithIdIsOne()
      throws ClassNotFoundException {
    // Arrange
    Mockito.<Class<?>>when(bundle.loadClass(Mockito.<String>any()))
        .thenThrow(new JexlException(new ASTAddNode(1), "Msg"));
    Class<Object> type = Object.class;

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> AbstractDescriptor.getImplClass(bundle, "Class Name", type));
    verify(bundle).loadClass("Class Name");
  }

  /**
   * Test {@link AbstractDescriptor#getImplClass(Bundle, String, Class)} with {@code fromBundle},
   * {@code className}, {@code type}.
   *
   * <ul>
   *   <li>Given {@code Object}.
   *   <li>Then return {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getImplClass(Bundle, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getImplClass(Bundle, String, Class)"})
  public void testGetImplClassWithFromBundleClassNameType_givenJavaLangObject_thenReturnObject()
      throws ClassNotFoundException {
    // Arrange
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<?>>when(bundle.loadClass(Mockito.<String>any())).thenReturn(forNameResult);
    Class<Object> type = Object.class;

    // Act
    Class<Object> actualImplClass = AbstractDescriptor.getImplClass(bundle, "Class Name", type);

    // Assert
    verify(bundle).loadClass("Class Name");
    Class<Object> expectedImplClass = Object.class;
    assertEquals(expectedImplClass, actualImplClass);
  }

  /**
   * Test {@link AbstractDescriptor#getImplClass(Bundle, String, Class)} with {@code fromBundle},
   * {@code className}, {@code type}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getImplClass(Bundle, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getImplClass(Bundle, String, Class)"})
  public void testGetImplClassWithFromBundleClassNameType_whenNull_thenReturnObject()
      throws ClassNotFoundException {
    // Arrange
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<?>>when(bundle.loadClass(Mockito.<String>any())).thenReturn(forNameResult);

    // Act
    Class<Object> actualImplClass = AbstractDescriptor.getImplClass(bundle, "Class Name", null);

    // Assert
    verify(bundle).loadClass("Class Name");
    Class<Object> expectedImplClass = Object.class;
    assertEquals(expectedImplClass, actualImplClass);
  }

  /**
   * Test {@link AbstractDescriptor#getObjectClass(String, Class)} with {@code className}, {@code
   * type}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getObjectClass(String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getObjectClass(String, Class)"})
  public void testGetObjectClassWithClassNameType_thenThrowIllegalStateException() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);
    Class<Object> type = Object.class;

    // Act and Assert
    assertThrows(
        IllegalStateException.class,
        () -> propertyGroupDescriptor.getObjectClass("Class Name", type));
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link AbstractDescriptor#getObjectClass(String)} with {@code className}.
   *
   * <ul>
   *   <li>Then throw {@link IllegalStateException}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getObjectClass(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getObjectClass(String)"})
  public void testGetObjectClassWithClassName_thenThrowIllegalStateException() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);
    PropertyGroupDescriptor<PropertyDescriptor> propertyGroupDescriptor =
        new PropertyGroupDescriptor<>(cfg);

    // Act and Assert
    assertThrows(
        IllegalStateException.class, () -> propertyGroupDescriptor.getObjectClass("Class Name"));
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
  }

  /**
   * Test {@link AbstractDescriptor#getObjectClass(Bundle, String, Class)} with {@code fromBundle},
   * {@code className}, {@code type}.
   *
   * <p>Method under test: {@link AbstractDescriptor#getObjectClass(Bundle, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getObjectClass(Bundle, String, Class)"})
  public void testGetObjectClassWithFromBundleClassNameType() throws ClassNotFoundException {
    // Arrange
    Mockito.<Class<?>>when(bundle.loadClass(Mockito.<String>any()))
        .thenThrow(new IllegalStateException());
    Class<Object> type = Object.class;

    // Act
    Class<Object> actualObjectClass = AbstractDescriptor.getObjectClass(bundle, "Class Name", type);

    // Assert
    verify(bundle).loadClass("Class Name");
    assertNull(actualObjectClass);
  }

  /**
   * Test {@link AbstractDescriptor#getObjectClass(Bundle, String, Class)} with {@code fromBundle},
   * {@code className}, {@code type}.
   *
   * <p>Method under test: {@link AbstractDescriptor#getObjectClass(Bundle, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getObjectClass(Bundle, String, Class)"})
  public void testGetObjectClassWithFromBundleClassNameType2() throws ClassNotFoundException {
    // Arrange
    ASTAddNode node = new ASTAddNode(1);
    node.jjtSetFirstToken(Token.newToken(4, "Can't determine object class 'Class Name'"));
    Mockito.<Class<?>>when(bundle.loadClass(Mockito.<String>any()))
        .thenThrow(new JexlException(node, "Msg"));
    Class<Object> type = Object.class;

    // Act
    Class<Object> actualObjectClass = AbstractDescriptor.getObjectClass(bundle, "Class Name", type);

    // Assert
    verify(bundle).loadClass("Class Name");
    assertNull(actualObjectClass);
  }

  /**
   * Test {@link AbstractDescriptor#getObjectClass(Bundle, String, Class)} with {@code fromBundle},
   * {@code className}, {@code type}.
   *
   * <ul>
   *   <li>Given {@link ASTAddNode#ASTAddNode(int)} with id is one.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getObjectClass(Bundle, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getObjectClass(Bundle, String, Class)"})
  public void testGetObjectClassWithFromBundleClassNameType_givenASTAddNodeWithIdIsOne()
      throws ClassNotFoundException {
    // Arrange
    Mockito.<Class<?>>when(bundle.loadClass(Mockito.<String>any()))
        .thenThrow(new JexlException(new ASTAddNode(1), "Msg"));
    Class<Object> type = Object.class;

    // Act
    Class<Object> actualObjectClass = AbstractDescriptor.getObjectClass(bundle, "Class Name", type);

    // Assert
    verify(bundle).loadClass("Class Name");
    assertNull(actualObjectClass);
  }

  /**
   * Test {@link AbstractDescriptor#getObjectClass(Bundle, String, Class)} with {@code fromBundle},
   * {@code className}, {@code type}.
   *
   * <ul>
   *   <li>Then return {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getObjectClass(Bundle, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getObjectClass(Bundle, String, Class)"})
  public void testGetObjectClassWithFromBundleClassNameType_thenReturnObject()
      throws ClassNotFoundException {
    // Arrange
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<?>>when(bundle.loadClass(Mockito.<String>any())).thenReturn(forNameResult);
    Class<Object> type = Object.class;

    // Act
    Class<Object> actualObjectClass = AbstractDescriptor.getObjectClass(bundle, "Class Name", type);

    // Assert
    verify(bundle).loadClass("Class Name");
    Class<Object> expectedObjectClass = Object.class;
    assertEquals(expectedObjectClass, actualObjectClass);
  }

  /**
   * Test {@link AbstractDescriptor#getObjectClass(Bundle, String, Class)} with {@code fromBundle},
   * {@code className}, {@code type}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getObjectClass(Bundle, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getObjectClass(Bundle, String, Class)"})
  public void testGetObjectClassWithFromBundleClassNameType_whenNull_thenReturnNull() {
    // Arrange
    Class<Object> type = Object.class;

    // Act
    Class<Object> actualObjectClass = AbstractDescriptor.getObjectClass(null, "Class Name", type);

    // Assert
    assertNull(actualObjectClass);
  }

  /**
   * Test {@link AbstractDescriptor#getObjectClass(Bundle, String, Class)} with {@code fromBundle},
   * {@code className}, {@code type}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@link Object}.
   * </ul>
   *
   * <p>Method under test: {@link AbstractDescriptor#getObjectClass(Bundle, String, Class)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Class AbstractDescriptor.getObjectClass(Bundle, String, Class)"})
  public void testGetObjectClassWithFromBundleClassNameType_whenNull_thenReturnObject()
      throws ClassNotFoundException {
    // Arrange
    Class<Object> forNameResult = Object.class;
    Mockito.<Class<?>>when(bundle.loadClass(Mockito.<String>any())).thenReturn(forNameResult);

    // Act
    Class<Object> actualObjectClass = AbstractDescriptor.getObjectClass(bundle, "Class Name", null);

    // Assert
    verify(bundle).loadClass("Class Name");
    Class<Object> expectedObjectClass = Object.class;
    assertEquals(expectedObjectClass, actualObjectClass);
  }
}
