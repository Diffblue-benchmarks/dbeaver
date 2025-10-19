package org.jkiss.dbeaver.model.impl.net;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSource;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.connection.DBPConnectionConfiguration;
import org.jkiss.dbeaver.model.exec.DBCInvalidatePhase;
import org.jkiss.dbeaver.model.net.DBWHandlerConfiguration;
import org.jkiss.dbeaver.model.net.DBWHandlerDescriptor;
import org.jkiss.dbeaver.model.net.DBWTunnel;
import org.jkiss.dbeaver.model.net.DBWTunnel.AuthCredentials;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class HTTPTunnelImplDiffblueTest {
  /**
   * Test {@link HTTPTunnelImpl#getRequiredCredentials(DBWHandlerConfiguration)}.
   *
   * <p>Method under test: {@link HTTPTunnelImpl#getRequiredCredentials(DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "AuthCredentials HTTPTunnelImpl.getRequiredCredentials(DBWHandlerConfiguration)"
  })
  public void testGetRequiredCredentials() {
    // Arrange
    HTTPTunnelImpl httpTunnelImpl = new HTTPTunnelImpl();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    AuthCredentials actualRequiredCredentials =
        httpTunnelImpl.getRequiredCredentials(configuration);

    // Assert
    verify(descriptor).getId();
    assertEquals(AuthCredentials.NONE, actualRequiredCredentials);
  }

  /**
   * Test {@link HTTPTunnelImpl#initializeHandler(DBRProgressMonitor, DBWHandlerConfiguration,
   * DBPConnectionConfiguration)}.
   *
   * <p>Method under test: {@link HTTPTunnelImpl#initializeHandler(DBRProgressMonitor,
   * DBWHandlerConfiguration, DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPConnectionConfiguration HTTPTunnelImpl.initializeHandler(DBRProgressMonitor, DBWHandlerConfiguration, DBPConnectionConfiguration)"
  })
  public void testInitializeHandler() throws IOException, DBException {
    // Arrange
    HTTPTunnelImpl httpTunnelImpl = new HTTPTunnelImpl();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));
    DBPConnectionConfiguration connectionInfo = new DBPConnectionConfiguration();

    // Act
    DBPConnectionConfiguration actualInitializeHandlerResult =
        httpTunnelImpl.initializeHandler(monitor, configuration, connectionInfo);

    // Assert
    verify(descriptor).getId();
    assertSame(connectionInfo, actualInitializeHandlerResult);
  }

  /**
   * Test {@link HTTPTunnelImpl#matchesParameters(String, int)}.
   *
   * <p>Method under test: {@link HTTPTunnelImpl#matchesParameters(String, int)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean HTTPTunnelImpl.matchesParameters(String, int)"})
  public void testMatchesParameters() {
    // Arrange, Act and Assert
    assertFalse(new HTTPTunnelImpl().matchesParameters("https://example.org/example", 8080));
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link HTTPTunnelImpl}
   *   <li>{@link HTTPTunnelImpl#addCloseListener(Runnable)}
   *   <li>{@link HTTPTunnelImpl#closeTunnel(DBRProgressMonitor)}
   *   <li>{@link HTTPTunnelImpl#invalidateHandler(DBRProgressMonitor, DBPDataSource,
   *       DBCInvalidatePhase)}
   *   <li>{@link HTTPTunnelImpl#getImplementation()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void HTTPTunnelImpl.<init>()",
    "void HTTPTunnelImpl.addCloseListener(Runnable)",
    "void HTTPTunnelImpl.closeTunnel(DBRProgressMonitor)",
    "java.lang.Object HTTPTunnelImpl.getImplementation()",
    "void HTTPTunnelImpl.invalidateHandler(DBRProgressMonitor, DBPDataSource, DBCInvalidatePhase)"
  })
  public void testGettersAndSetters() throws IOException, DBException {
    // Arrange and Act
    HTTPTunnelImpl actualHttpTunnelImpl = new HTTPTunnelImpl();
    actualHttpTunnelImpl.addCloseListener(mock(Runnable.class));
    actualHttpTunnelImpl.closeTunnel(new LoggingProgressMonitor());
    actualHttpTunnelImpl.invalidateHandler(
        new LoggingProgressMonitor(),
        mock(DBPDataSource.class),
        DBCInvalidatePhase.BEFORE_INVALIDATE);

    // Assert
    assertNull(actualHttpTunnelImpl.getImplementation());
  }
}
