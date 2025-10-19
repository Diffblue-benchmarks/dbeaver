package org.jkiss.dbeaver.runtime.properties;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.jkiss.dbeaver.model.DBPEvent;
import org.jkiss.dbeaver.model.impl.PropertyDescriptor;
import org.jkiss.dbeaver.model.impl.ProxyPropertyDescriptor;
import org.jkiss.dbeaver.model.preferences.DBPPropertyDescriptor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class PropertiesContributorDiffblueTest {
  /**
   * Test {@link PropertiesContributor#notifyPropertyLoad(Object, DBPPropertyDescriptor, Object,
   * boolean)}.
   *
   * <ul>
   *   <li>Given Instance.
   *   <li>Then calls {@link ConfigurationElementHandle#getAttribute(String)}.
   * </ul>
   *
   * <p>Method under test: {@link PropertiesContributor#notifyPropertyLoad(Object,
   * DBPPropertyDescriptor, Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertiesContributor.notifyPropertyLoad(Object, DBPPropertyDescriptor, Object, boolean)"
  })
  public void testNotifyPropertyLoad_givenInstance_thenCallsGetAttribute() {
    // Arrange
    PropertiesContributor instance = PropertiesContributor.getInstance();

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);

    // Act
    instance.notifyPropertyLoad(
        DBPEvent.RENAME, new ProxyPropertyDescriptor(original), DBPEvent.RENAME, true);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
  }

  /**
   * Test {@link PropertiesContributor#notifyPropertyLoad(Object, DBPPropertyDescriptor, Object,
   * boolean)}.
   *
   * <ul>
   *   <li>Given {@link PropertiesContributor} (default constructor).
   *   <li>Then calls {@link ConfigurationElementHandle#getAttribute(String)}.
   * </ul>
   *
   * <p>Method under test: {@link PropertiesContributor#notifyPropertyLoad(Object,
   * DBPPropertyDescriptor, Object, boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void PropertiesContributor.notifyPropertyLoad(Object, DBPPropertyDescriptor, Object, boolean)"
  })
  public void testNotifyPropertyLoad_givenPropertiesContributor_thenCallsGetAttribute() {
    // Arrange
    PropertiesContributor propertiesContributor = new PropertiesContributor();

    ConfigurationElementHandle config = mock(ConfigurationElementHandle.class);
    when(config.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    PropertyDescriptor original = new PropertyDescriptor("Category", config);

    // Act
    propertiesContributor.notifyPropertyLoad(
        DBPEvent.RENAME, new ProxyPropertyDescriptor(original), DBPEvent.RENAME, true);

    // Assert
    verify(config, atLeast(1)).getAttribute(Mockito.<String>any());
  }
}
