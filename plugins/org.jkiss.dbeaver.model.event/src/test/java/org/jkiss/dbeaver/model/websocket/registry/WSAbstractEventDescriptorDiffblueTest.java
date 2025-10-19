package org.jkiss.dbeaver.model.websocket.registry;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.core.internal.registry.ConfigurationElementHandle;
import org.eclipse.core.runtime.IContributor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class WSAbstractEventDescriptorDiffblueTest {
  /**
   * Test {@link WSAbstractEventDescriptor#getId()}.
   *
   * <ul>
   *   <li>Given {@link IContributor} {@link IContributor#getName()} return {@code Name}.
   *   <li>Then return {@code Attribute}.
   * </ul>
   *
   * <p>Method under test: {@link WSAbstractEventDescriptor#getId()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String WSAbstractEventDescriptor.getId()"})
  public void testGetId_givenIContributorGetNameReturnName_thenReturnAttribute() {
    // Arrange
    IContributor iContributor = mock(IContributor.class);
    when(iContributor.getName()).thenReturn("Name");

    ConfigurationElementHandle cfg = mock(ConfigurationElementHandle.class);
    when(cfg.getAttribute(Mockito.<String>any())).thenReturn("Attribute");
    when(cfg.getContributor()).thenReturn(iContributor);

    // Act
    String actualId = new WSClientEventDescriptor(cfg).getId();

    // Assert
    verify(cfg, atLeast(1)).getAttribute(Mockito.<String>any());
    verify(cfg).getContributor();
    verify(iContributor).getName();
    assertEquals("Attribute", actualId);
  }
}
