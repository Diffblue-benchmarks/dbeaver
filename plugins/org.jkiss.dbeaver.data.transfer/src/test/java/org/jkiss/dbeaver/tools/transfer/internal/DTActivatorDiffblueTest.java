package org.jkiss.dbeaver.tools.transfer.internal;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.osgi.service.debug.DebugOptions;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

@RunWith(MockitoJUnitRunner.class)
public class DTActivatorDiffblueTest {
  @InjectMocks private DTActivator dTActivator;

  @Mock private ServiceTracker<DebugOptions, DebugOptions> serviceTracker;

  /**
   * Test {@link DTActivator#stop(BundleContext)}.
   *
   * <ul>
   *   <li>Given {@link ServiceTracker} {@link ServiceTracker#close()} does nothing.
   *   <li>When {@code null}.
   *   <li>Then calls {@link ServiceTracker#close()}.
   * </ul>
   *
   * <p>Method under test: {@link DTActivator#stop(BundleContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DTActivator.stop(BundleContext)"})
  public void testStop_givenServiceTrackerCloseDoesNothing_whenNull_thenCallsClose()
      throws Exception {
    // Arrange
    doNothing().when(serviceTracker).close();

    // Act
    dTActivator.stop(null);

    // Assert
    verify(serviceTracker).close();
  }

  /**
   * Test new {@link DTActivator} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link DTActivator}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DTActivator.<init>()"})
  public void testNewDTActivator() {
    // Arrange, Act and Assert
    assertNull(new DTActivator().getBundle());
  }
}
