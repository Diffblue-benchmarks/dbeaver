package org.jkiss.dbeaver.model.ai.internal;

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
public class AIActivatorDiffblueTest {
  @InjectMocks private AIActivator aIActivator;

  @Mock private ServiceTracker<DebugOptions, DebugOptions> serviceTracker;

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link AIActivator}
   *   <li>{@link AIActivator#getInstance()}
   *   <li>{@link AIActivator#getPreferences()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AIActivator.<init>()",
    "AIActivator AIActivator.getInstance()",
    "org.jkiss.dbeaver.model.impl.preferences.BundlePreferenceStore AIActivator.getPreferences()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    AIActivator actualAiActivator = new AIActivator();
    AIActivator actualInstance = actualAiActivator.getInstance();

    // Assert
    assertNull(actualInstance);
    assertNull(actualAiActivator.getPreferences());
  }

  /**
   * Test {@link AIActivator#stop(BundleContext)}.
   *
   * <ul>
   *   <li>Given {@link ServiceTracker} {@link ServiceTracker#close()} does nothing.
   *   <li>When {@code null}.
   *   <li>Then calls {@link ServiceTracker#close()}.
   * </ul>
   *
   * <p>Method under test: {@link AIActivator#stop(BundleContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void AIActivator.stop(BundleContext)"})
  public void testStop_givenServiceTrackerCloseDoesNothing_whenNull_thenCallsClose()
      throws Exception {
    // Arrange
    doNothing().when(serviceTracker).close();

    // Act
    aIActivator.stop(null);

    // Assert
    verify(serviceTracker).close();
  }
}
