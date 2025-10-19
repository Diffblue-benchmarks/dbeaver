package org.jkiss.dbeaver.headless;

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
public class DBeaverTestActivatorDiffblueTest {
  @InjectMocks private DBeaverTestActivator dBeaverTestActivator;

  @Mock private ServiceTracker<DebugOptions, DebugOptions> serviceTracker;

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link DBeaverTestActivator}
   *   <li>{@link DBeaverTestActivator#getInstance()}
   *   <li>{@link DBeaverTestActivator#getPreferences()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBeaverTestActivator.<init>()",
    "DBeaverTestActivator DBeaverTestActivator.getInstance()",
    "org.jkiss.dbeaver.model.preferences.DBPPreferenceStore DBeaverTestActivator.getPreferences()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBeaverTestActivator actualDBeaverTestActivator = new DBeaverTestActivator();
    DBeaverTestActivator actualInstance = actualDBeaverTestActivator.getInstance();

    // Assert
    assertNull(actualInstance);
    assertNull(actualDBeaverTestActivator.getPreferences());
  }

  /**
   * Test {@link DBeaverTestActivator#stop(BundleContext)}.
   *
   * <ul>
   *   <li>Given {@link ServiceTracker} {@link ServiceTracker#close()} does nothing.
   *   <li>When {@code null}.
   *   <li>Then calls {@link ServiceTracker#close()}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverTestActivator#stop(BundleContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBeaverTestActivator.stop(BundleContext)"})
  public void testStop_givenServiceTrackerCloseDoesNothing_whenNull_thenCallsClose()
      throws Exception {
    // Arrange
    doNothing().when(serviceTracker).close();

    // Act
    dBeaverTestActivator.stop(null);

    // Assert
    verify(serviceTracker).close();
  }
}
