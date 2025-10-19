package org.jkiss.dbeaver.model.impl.net;

import static org.junit.Assert.assertEquals;
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
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SSLHandlerImplDiffblueTest {
  /**
   * Test {@link SSLHandlerImpl#initializeHandler(DBRProgressMonitor, DBWHandlerConfiguration,
   * DBPConnectionConfiguration)}.
   *
   * <p>Method under test: {@link SSLHandlerImpl#initializeHandler(DBRProgressMonitor,
   * DBWHandlerConfiguration, DBPConnectionConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "DBPConnectionConfiguration SSLHandlerImpl.initializeHandler(DBRProgressMonitor, DBWHandlerConfiguration, DBPConnectionConfiguration)"
  })
  public void testInitializeHandler() throws IOException, DBException {
    // Arrange
    SSLHandlerImpl sslHandlerImpl = new SSLHandlerImpl();
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));
    DBPConnectionConfiguration connectionInfo = new DBPConnectionConfiguration();

    // Act
    DBPConnectionConfiguration actualInitializeHandlerResult =
        sslHandlerImpl.initializeHandler(monitor, configuration, connectionInfo);

    // Assert
    verify(descriptor).getId();
    assertSame(connectionInfo, actualInitializeHandlerResult);
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link SSLHandlerImpl}
   *   <li>{@link SSLHandlerImpl#invalidateHandler(DBRProgressMonitor, DBPDataSource,
   *       DBCInvalidatePhase)}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SSLHandlerImpl.<init>()",
    "void SSLHandlerImpl.invalidateHandler(DBRProgressMonitor, DBPDataSource, DBCInvalidatePhase)"
  })
  public void testGettersAndSetters() throws DBException {
    // Arrange and Act
    SSLHandlerImpl actualSslHandlerImpl = new SSLHandlerImpl();
    actualSslHandlerImpl.invalidateHandler(
        new LoggingProgressMonitor(),
        mock(DBPDataSource.class),
        DBCInvalidatePhase.BEFORE_INVALIDATE);

    // Assert
    assertEquals(0, actualSslHandlerImpl.getDependentDataSources().length);
  }
}
