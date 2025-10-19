package org.jkiss.dbeaver.debug.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.Launch;
import org.eclipse.debug.core.model.IProcess;
import org.jkiss.dbeaver.debug.core.model.DatabaseProcess;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPDriver;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

public class DatabaseLaunchDelegateDiffblueTest {
  /**
   * Test {@link DatabaseLaunchDelegate#createController(DBPDataSourceContainer, Map)}.
   *
   * <p>Method under test: {@link DatabaseLaunchDelegate#createController(DBPDataSourceContainer,
   * Map)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "org.jkiss.dbeaver.debug.DBGController DatabaseLaunchDelegate.createController(DBPDataSourceContainer, Map)"
  })
  public void testCreateController() throws CoreException {
    // Arrange
    DatabaseLaunchDelegate databaseLaunchDelegate = new DatabaseLaunchDelegate();

    DBPDriver dbpDriver = mock(DBPDriver.class);
    when(dbpDriver.getProviderId()).thenReturn("42");

    DBPDataSourceContainer dataSourceContainer = mock(DBPDataSourceContainer.class);
    when(dataSourceContainer.getDriver()).thenReturn(dbpDriver);

    // Act and Assert
    assertThrows(
        CoreException.class,
        () -> databaseLaunchDelegate.createController(dataSourceContainer, new HashMap<>()));
    verify(dataSourceContainer).getDriver();
    verify(dbpDriver).getProviderId();
  }

  /**
   * Test {@link DatabaseLaunchDelegate#createProcess(ILaunch, String)}.
   *
   * <ul>
   *   <li>When {@link Launch} {@link Launch#addProcess(IProcess)} does nothing.
   *   <li>Then return Label is {@code Name}.
   * </ul>
   *
   * <p>Method under test: {@link DatabaseLaunchDelegate#createProcess(ILaunch, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DatabaseProcess DatabaseLaunchDelegate.createProcess(ILaunch, String)"})
  public void testCreateProcess_whenLaunchAddProcessDoesNothing_thenReturnLabelIsName()
      throws DebugException {
    // Arrange
    DatabaseLaunchDelegate databaseLaunchDelegate = new DatabaseLaunchDelegate();

    Launch launch = mock(Launch.class);
    doNothing().when(launch).addProcess(Mockito.<IProcess>any());

    // Act
    DatabaseProcess actualCreateProcessResult =
        databaseLaunchDelegate.createProcess(launch, "Name");

    // Assert
    verify(launch).addProcess(isA(IProcess.class));
    assertEquals("Name", actualCreateProcessResult.getLabel());
    assertNull(actualCreateProcessResult.getStreamsProxy());
    assertEquals(0, actualCreateProcessResult.getExitValue());
    assertFalse(actualCreateProcessResult.isTerminated());
    assertSame(launch, actualCreateProcessResult.getLaunch());
  }
}
