package org.jkiss.dbeaver.model.net.ssh;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.model.DBPDataSourceContainer;
import org.jkiss.dbeaver.model.net.DBWHandlerConfiguration;
import org.jkiss.dbeaver.model.net.DBWHandlerDescriptor;
import org.jkiss.dbeaver.model.net.ssh.AbstractSessionController.DelegateSession;
import org.jkiss.dbeaver.model.net.ssh.AbstractSessionController.DirectSession;
import org.jkiss.dbeaver.model.net.ssh.AbstractSessionController.JumpSession;
import org.jkiss.dbeaver.model.net.ssh.AbstractSessionController.ShareableSession;
import org.jkiss.dbeaver.model.net.ssh.AbstractSessionController.ShareableSession.PortForwardInfo;
import org.jkiss.dbeaver.model.net.ssh.AbstractSessionController.WrapperSession;
import org.jkiss.dbeaver.model.net.ssh.config.SSHAuthConfiguration;
import org.jkiss.dbeaver.model.net.ssh.config.SSHAuthConfiguration.Agent;
import org.jkiss.dbeaver.model.net.ssh.config.SSHHostConfiguration;
import org.jkiss.dbeaver.model.net.ssh.config.SSHPortForwardConfiguration;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.runtime.LoggingProgressMonitor;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AbstractSessionControllerDiffblueTest {
  @Mock private DelegateSession delegateSession;

  @InjectMocks private JumpSession<AbstractSession> jumpSession;

  /**
   * Test {@link
   * AbstractSessionController#canShareSessionForConfiguration(DBWHandlerConfiguration)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractSessionController#canShareSessionForConfiguration(DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean AbstractSessionController.canShareSessionForConfiguration(DBWHandlerConfiguration)"
  })
  public void testCanShareSessionForConfiguration_thenReturnFalse() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration = new DBWHandlerConfiguration(descriptor, null);

    // Act
    boolean actualCanShareSessionForConfigurationResult =
        AbstractSessionController.canShareSessionForConfiguration(configuration);

    // Assert
    verify(descriptor).getId();
    assertFalse(actualCanShareSessionForConfigurationResult);
  }

  /**
   * Test {@link
   * AbstractSessionController#canShareSessionForConfiguration(DBWHandlerConfiguration)}.
   *
   * <ul>
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link
   * AbstractSessionController#canShareSessionForConfiguration(DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean AbstractSessionController.canShareSessionForConfiguration(DBWHandlerConfiguration)"
  })
  public void testCanShareSessionForConfiguration_thenReturnTrue() {
    // Arrange
    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    boolean actualCanShareSessionForConfigurationResult =
        AbstractSessionController.canShareSessionForConfiguration(configuration);

    // Assert
    verify(descriptor).getId();
    assertTrue(actualCanShareSessionForConfigurationResult);
  }

  /**
   * Test DelegateSession {@link DelegateSession#connect(DBRProgressMonitor, SSHHostConfiguration,
   * DBWHandlerConfiguration)}.
   *
   * <p>Method under test: {@link DelegateSession#connect(DBRProgressMonitor, SSHHostConfiguration,
   * DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DelegateSession.connect(DBRProgressMonitor, SSHHostConfiguration, DBWHandlerConfiguration)"
  })
  public void testDelegateSessionConnect() throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doNothing()
        .when(inner)
        .connect(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<SSHHostConfiguration>any(),
            Mockito.<DBWHandlerConfiguration>any());
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);

    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    doNothing()
        .when(controller)
        .registerSession(
            Mockito.<ShareableSession<AbstractSession>>any(),
            Mockito.<DBWHandlerConfiguration>any());
    when(controller.createSession()).thenReturn(wrapperSession);
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> inner2 = new ShareableSession<>(controller, destination);
    WrapperSession<AbstractSession> wrapperSession2 = new WrapperSession<>(inner2);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    SSHHostConfiguration destination2 =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    wrapperSession2.connect(monitor, destination2, configuration);

    // Assert
    verify(descriptor).getId();
    verify(controller).createSession();
    verify(controller)
        .registerSession(isA(ShareableSession.class), isA(DBWHandlerConfiguration.class));
    verify(inner)
        .connect(
            isA(DBRProgressMonitor.class),
            isA(SSHHostConfiguration.class),
            isA(DBWHandlerConfiguration.class));
    AbstractSession session = wrapperSession2.getSession();
    assertTrue(session instanceof ShareableSession);
    Map<DBPDataSourceContainer, AtomicInteger> dbpDataSourceContainerAtomicIntegerMap =
        ((ShareableSession<AbstractSession>) session).dataSources;
    assertEquals(1, dbpDataSourceContainerAtomicIntegerMap.size());
    assertEquals(1, ((ShareableSession<AbstractSession>) session).getDataSources().length);
    assertEquals(1, wrapperSession2.getDataSources().length);
    assertSame(inner2.dataSources, dbpDataSourceContainerAtomicIntegerMap);
  }

  /**
   * Test DelegateSession {@link DelegateSession#getClientVersion()}.
   *
   * <p>Method under test: {@link DelegateSession#getClientVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DelegateSession.getClientVersion()"})
  public void testDelegateSessionGetClientVersion() {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    when(inner.getClientVersion()).thenReturn("1.0.2");
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);

    // Act
    String actualClientVersion = wrapperSession.getClientVersion();

    // Assert
    verify(inner).getClientVersion();
    assertEquals("1.0.2", actualClientVersion);
  }

  /**
   * Test DelegateSession {@link DelegateSession#getClientVersion()}.
   *
   * <ul>
   *   <li>Then calls {@link AbstractSessionController#createSession()}.
   * </ul>
   *
   * <p>Method under test: {@link DelegateSession#getClientVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DelegateSession.getClientVersion()"})
  public void testDelegateSessionGetClientVersion_thenCallsCreateSession() {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    when(inner.getClientVersion()).thenReturn("1.0.2");

    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(new WrapperSession<>(inner));
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> inner2 = new ShareableSession<>(controller, destination);
    SSHPortForwardConfiguration portForward =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    DirectSession<AbstractSession> directSession = new DirectSession<>(inner2, portForward);

    // Act
    String actualClientVersion = directSession.getClientVersion();

    // Assert
    verify(controller).createSession();
    verify(inner).getClientVersion();
    assertEquals("1.0.2", actualClientVersion);
  }

  /**
   * Test DelegateSession {@link DelegateSession#getFile(String, OutputStream, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link ShareableSession} {@link ShareableSession#getFile(String, OutputStream,
   *       DBRProgressMonitor)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link DelegateSession#getFile(String, OutputStream, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DelegateSession.getFile(String, OutputStream, DBRProgressMonitor)"})
  public void testDelegateSessionGetFile_givenShareableSessionGetFileDoesNothing()
      throws IOException, DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doNothing()
        .when(inner)
        .getFile(
            Mockito.<String>any(), Mockito.<OutputStream>any(), Mockito.<DBRProgressMonitor>any());
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);
    ByteArrayOutputStream dst = new ByteArrayOutputStream();

    // Act
    wrapperSession.getFile("Src", dst, new LoggingProgressMonitor());

    // Assert
    verify(inner).getFile(eq("Src"), isA(OutputStream.class), isA(DBRProgressMonitor.class));
  }

  /**
   * Test DelegateSession {@link DelegateSession#getFile(String, OutputStream, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DelegateSession#getFile(String, OutputStream, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DelegateSession.getFile(String, OutputStream, DBRProgressMonitor)"})
  public void testDelegateSessionGetFile_thenThrowDBException() throws IOException, DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doThrow(new DBException("An error occurred"))
        .when(inner)
        .getFile(
            Mockito.<String>any(), Mockito.<OutputStream>any(), Mockito.<DBRProgressMonitor>any());
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);
    ByteArrayOutputStream dst = new ByteArrayOutputStream();

    // Act and Assert
    assertThrows(
        DBException.class, () -> wrapperSession.getFile("Src", dst, new LoggingProgressMonitor()));
    verify(inner).getFile(eq("Src"), isA(OutputStream.class), isA(DBRProgressMonitor.class));
  }

  /**
   * Test DelegateSession {@link DelegateSession#getServerVersion()}.
   *
   * <p>Method under test: {@link DelegateSession#getServerVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DelegateSession.getServerVersion()"})
  public void testDelegateSessionGetServerVersion() {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    when(inner.getServerVersion()).thenReturn("1.0.2");
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);

    // Act
    String actualServerVersion = wrapperSession.getServerVersion();

    // Assert
    verify(inner).getServerVersion();
    assertEquals("1.0.2", actualServerVersion);
  }

  /**
   * Test DelegateSession {@link DelegateSession#getServerVersion()}.
   *
   * <ul>
   *   <li>Then calls {@link AbstractSessionController#createSession()}.
   * </ul>
   *
   * <p>Method under test: {@link DelegateSession#getServerVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String DelegateSession.getServerVersion()"})
  public void testDelegateSessionGetServerVersion_thenCallsCreateSession() {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    when(inner.getServerVersion()).thenReturn("1.0.2");

    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(new WrapperSession<>(inner));
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> inner2 = new ShareableSession<>(controller, destination);
    SSHPortForwardConfiguration portForward =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    DirectSession<AbstractSession> directSession = new DirectSession<>(inner2, portForward);

    // Act
    String actualServerVersion = directSession.getServerVersion();

    // Assert
    verify(controller).createSession();
    verify(inner).getServerVersion();
    assertEquals("1.0.2", actualServerVersion);
  }

  /**
   * Test DelegateSession {@link DelegateSession#putFile(InputStream, String, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Given {@link ShareableSession} {@link ShareableSession#putFile(InputStream, String,
   *       DBRProgressMonitor)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link DelegateSession#putFile(InputStream, String, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DelegateSession.putFile(InputStream, String, DBRProgressMonitor)"})
  public void testDelegateSessionPutFile_givenShareableSessionPutFileDoesNothing()
      throws IOException, DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doNothing()
        .when(inner)
        .putFile(
            Mockito.<InputStream>any(), Mockito.<String>any(), Mockito.<DBRProgressMonitor>any());
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);
    ByteArrayInputStream src = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act
    wrapperSession.putFile(src, "Dst", new LoggingProgressMonitor());

    // Assert
    verify(inner).putFile(isA(InputStream.class), eq("Dst"), isA(DBRProgressMonitor.class));
  }

  /**
   * Test DelegateSession {@link DelegateSession#putFile(InputStream, String, DBRProgressMonitor)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DelegateSession#putFile(InputStream, String, DBRProgressMonitor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DelegateSession.putFile(InputStream, String, DBRProgressMonitor)"})
  public void testDelegateSessionPutFile_thenThrowDBException() throws IOException, DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doThrow(new DBException("An error occurred"))
        .when(inner)
        .putFile(
            Mockito.<InputStream>any(), Mockito.<String>any(), Mockito.<DBRProgressMonitor>any());
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);
    ByteArrayInputStream src = new ByteArrayInputStream("AXAXAXAX".getBytes("UTF-8"));

    // Act and Assert
    assertThrows(
        DBException.class, () -> wrapperSession.putFile(src, "Dst", new LoggingProgressMonitor()));
    verify(inner).putFile(isA(InputStream.class), eq("Dst"), isA(DBRProgressMonitor.class));
  }

  /**
   * Test DirectSession {@link DirectSession#connect(DBRProgressMonitor, SSHHostConfiguration,
   * DBWHandlerConfiguration)}.
   *
   * <p>Method under test: {@link DirectSession#connect(DBRProgressMonitor, SSHHostConfiguration,
   * DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DirectSession.connect(DBRProgressMonitor, SSHHostConfiguration, DBWHandlerConfiguration)"
  })
  public void testDirectSessionConnect() throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doNothing()
        .when(inner)
        .connect(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<SSHHostConfiguration>any(),
            Mockito.<DBWHandlerConfiguration>any());
    DirectSession<AbstractSession> directSession = new DirectSession<>(inner, null);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    directSession.connect(monitor, destination, configuration);

    // Assert
    verify(descriptor).getId();
    verify(inner)
        .connect(
            isA(DBRProgressMonitor.class),
            isA(SSHHostConfiguration.class),
            isA(DBWHandlerConfiguration.class));
  }

  /**
   * Test DirectSession {@link DirectSession#connect(DBRProgressMonitor, SSHHostConfiguration,
   * DBWHandlerConfiguration)}.
   *
   * <ul>
   *   <li>Then calls {@link ShareableSession#setupPortForward(SSHPortForwardConfiguration)}.
   * </ul>
   *
   * <p>Method under test: {@link DirectSession#connect(DBRProgressMonitor, SSHHostConfiguration,
   * DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DirectSession.connect(DBRProgressMonitor, SSHHostConfiguration, DBWHandlerConfiguration)"
  })
  public void testDirectSessionConnect_thenCallsSetupPortForward() throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    SSHPortForwardConfiguration sshPortForwardConfiguration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);
    when(inner.setupPortForward(Mockito.<SSHPortForwardConfiguration>any()))
        .thenReturn(sshPortForwardConfiguration);
    doNothing()
        .when(inner)
        .connect(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<SSHHostConfiguration>any(),
            Mockito.<DBWHandlerConfiguration>any());
    SSHPortForwardConfiguration portForward =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    DirectSession<AbstractSession> directSession = new DirectSession<>(inner, portForward);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    directSession.connect(monitor, destination, configuration);

    // Assert
    verify(descriptor).getId();
    verify(inner)
        .connect(
            isA(DBRProgressMonitor.class),
            isA(SSHHostConfiguration.class),
            isA(DBWHandlerConfiguration.class));
    verify(inner).setupPortForward(isA(SSHPortForwardConfiguration.class));
  }

  /**
   * Test DirectSession {@link DirectSession#connect(DBRProgressMonitor, SSHHostConfiguration,
   * DBWHandlerConfiguration)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link DirectSession#connect(DBRProgressMonitor, SSHHostConfiguration,
   * DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DirectSession.connect(DBRProgressMonitor, SSHHostConfiguration, DBWHandlerConfiguration)"
  })
  public void testDirectSessionConnect_thenThrowDBException() throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doThrow(new DBException("An error occurred"))
        .when(inner)
        .connect(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<SSHHostConfiguration>any(),
            Mockito.<DBWHandlerConfiguration>any());
    SSHPortForwardConfiguration portForward =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    DirectSession<AbstractSession> directSession = new DirectSession<>(inner, portForward);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act and Assert
    assertThrows(
        DBException.class, () -> directSession.connect(monitor, destination, configuration));
    verify(descriptor).getId();
    verify(inner)
        .connect(
            isA(DBRProgressMonitor.class),
            isA(SSHHostConfiguration.class),
            isA(DBWHandlerConfiguration.class));
  }

  /**
   * Test DirectSession {@link DirectSession#disconnect(DBRProgressMonitor, DBWHandlerConfiguration,
   * long)}.
   *
   * <p>Method under test: {@link DirectSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DirectSession.disconnect(DBRProgressMonitor, DBWHandlerConfiguration, long)"
  })
  public void testDirectSessionDisconnect() throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doThrow(new DBException("An error occurred"))
        .when(inner)
        .removePortForward(Mockito.<SSHPortForwardConfiguration>any());
    SSHPortForwardConfiguration portForward =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    DirectSession<AbstractSession> directSession = new DirectSession<>(inner, portForward);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act and Assert
    assertThrows(DBException.class, () -> directSession.disconnect(monitor, configuration, 10L));
    verify(descriptor).getId();
    verify(inner).removePortForward(isA(SSHPortForwardConfiguration.class));
  }

  /**
   * Test DirectSession {@link DirectSession#disconnect(DBRProgressMonitor, DBWHandlerConfiguration,
   * long)}.
   *
   * <p>Method under test: {@link DirectSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DirectSession.disconnect(DBRProgressMonitor, DBWHandlerConfiguration, long)"
  })
  public void testDirectSessionDisconnect2() throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doThrow(new DBException("An error occurred"))
        .when(inner)
        .disconnect(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBWHandlerConfiguration>any(), anyLong());
    DirectSession<AbstractSession> directSession = new DirectSession<>(inner, null);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act and Assert
    assertThrows(DBException.class, () -> directSession.disconnect(monitor, configuration, 10L));
    verify(descriptor).getId();
    verify(inner)
        .disconnect(isA(DBRProgressMonitor.class), isA(DBWHandlerConfiguration.class), eq(10L));
  }

  /**
   * Test DirectSession {@link DirectSession#disconnect(DBRProgressMonitor, DBWHandlerConfiguration,
   * long)}.
   *
   * <ul>
   *   <li>Given {@link ShareableSession} {@link ShareableSession#disconnect(DBRProgressMonitor,
   *       DBWHandlerConfiguration, long)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link DirectSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DirectSession.disconnect(DBRProgressMonitor, DBWHandlerConfiguration, long)"
  })
  public void testDirectSessionDisconnect_givenShareableSessionDisconnectDoesNothing()
      throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doNothing()
        .when(inner)
        .disconnect(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBWHandlerConfiguration>any(), anyLong());
    DirectSession<AbstractSession> directSession = new DirectSession<>(inner, null);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    directSession.disconnect(monitor, configuration, 10L);

    // Assert
    verify(descriptor).getId();
    verify(inner)
        .disconnect(isA(DBRProgressMonitor.class), isA(DBWHandlerConfiguration.class), eq(10L));
  }

  /**
   * Test DirectSession {@link DirectSession#disconnect(DBRProgressMonitor, DBWHandlerConfiguration,
   * long)}.
   *
   * <ul>
   *   <li>Given {@link ShareableSession} {@link
   *       ShareableSession#removePortForward(SSHPortForwardConfiguration)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link DirectSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DirectSession.disconnect(DBRProgressMonitor, DBWHandlerConfiguration, long)"
  })
  public void testDirectSessionDisconnect_givenShareableSessionRemovePortForwardDoesNothing()
      throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doNothing()
        .when(inner)
        .disconnect(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBWHandlerConfiguration>any(), anyLong());
    doNothing().when(inner).removePortForward(Mockito.<SSHPortForwardConfiguration>any());
    SSHPortForwardConfiguration portForward =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    DirectSession<AbstractSession> directSession = new DirectSession<>(inner, portForward);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    directSession.disconnect(monitor, configuration, 10L);

    // Assert
    verify(descriptor).getId();
    verify(inner)
        .disconnect(isA(DBRProgressMonitor.class), isA(DBWHandlerConfiguration.class), eq(10L));
    verify(inner).removePortForward(isA(SSHPortForwardConfiguration.class));
  }

  /**
   * Test DirectSession {@link DirectSession#DirectSession(ShareableSession,
   * SSHPortForwardConfiguration)}.
   *
   * <ul>
   *   <li>Then return ClientVersion is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DirectSession#DirectSession(ShareableSession,
   * SSHPortForwardConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DirectSession.<init>(ShareableSession, SSHPortForwardConfiguration)"})
  public void testDirectSessionNewDirectSession_thenReturnClientVersionIsNull() {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    SSHPortForwardConfiguration portForward =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    // Act
    DirectSession<AbstractSession> actualDirectSession = new DirectSession<>(inner, portForward);

    // Assert
    assertNull(actualDirectSession.getClientVersion());
    assertNull(actualDirectSession.getServerVersion());
    assertNull(actualDirectSession.getDataSources());
    assertNull(actualDirectSession.destination);
    assertSame(inner, actualDirectSession.getSession());
  }

  /**
   * Test JumpSession {@link JumpSession#disconnect(DBRProgressMonitor, DBWHandlerConfiguration,
   * long)}.
   *
   * <p>Method under test: {@link JumpSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JumpSession.disconnect(DBRProgressMonitor, DBWHandlerConfiguration, long)"
  })
  public void testJumpSessionDisconnect() throws DBException {
    // Arrange
    doThrow(new DBException("An error occurred"))
        .when(delegateSession)
        .disconnect(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBWHandlerConfiguration>any(), anyLong());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    jumpSession.disconnect(monitor, configuration, 10L);

    // Assert
    verify(descriptor).getId();
    verify(delegateSession)
        .disconnect(isA(DBRProgressMonitor.class), isA(DBWHandlerConfiguration.class), eq(10L));
  }

  /**
   * Test JumpSession {@link JumpSession#disconnect(DBRProgressMonitor, DBWHandlerConfiguration,
   * long)}.
   *
   * <ul>
   *   <li>Given {@link DelegateSession} {@link DelegateSession#disconnect(DBRProgressMonitor,
   *       DBWHandlerConfiguration, long)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link JumpSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void JumpSession.disconnect(DBRProgressMonitor, DBWHandlerConfiguration, long)"
  })
  public void testJumpSessionDisconnect_givenDelegateSessionDisconnectDoesNothing()
      throws DBException {
    // Arrange
    doNothing()
        .when(delegateSession)
        .disconnect(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBWHandlerConfiguration>any(), anyLong());
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    jumpSession.disconnect(monitor, configuration, 10L);

    // Assert
    verify(descriptor).getId();
    verify(delegateSession)
        .disconnect(isA(DBRProgressMonitor.class), isA(DBWHandlerConfiguration.class), eq(10L));
  }

  /**
   * Test JumpSession {@link JumpSession#getDataSources()}.
   *
   * <p>Method under test: {@link JumpSession#getDataSources()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer[] JumpSession.getDataSources()"})
  public void testJumpSessionGetDataSources() {
    // Arrange
    when(delegateSession.getDataSources())
        .thenReturn(new DBPDataSourceContainer[] {mock(DBPDataSourceContainer.class)});

    // Act
    DBPDataSourceContainer[] actualDataSources = jumpSession.getDataSources();

    // Assert
    verify(delegateSession).getDataSources();
    assertEquals(1, actualDataSources.length);
  }

  /**
   * Test ShareableSession {@link ShareableSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>Then calls {@link DBWHandlerDescriptor#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link ShareableSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShareableSession.disconnect(DBRProgressMonitor, DBWHandlerConfiguration, long)"
  })
  public void testShareableSessionDisconnect_given42_thenCallsGetId() throws DBException {
    // Arrange
    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(null);
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> shareableSession =
        new ShareableSession<>(controller, destination);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act and Assert
    assertThrows(DBException.class, () -> shareableSession.disconnect(monitor, configuration, 10L));
    verify(descriptor).getId();
    verify(controller).createSession();
  }

  /**
   * Test ShareableSession {@link ShareableSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}.
   *
   * <ul>
   *   <li>Then calls {@link DBWHandlerConfiguration#getDataSource()}.
   * </ul>
   *
   * <p>Method under test: {@link ShareableSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShareableSession.disconnect(DBRProgressMonitor, DBWHandlerConfiguration, long)"
  })
  public void testShareableSessionDisconnect_thenCallsGetDataSource() throws DBException {
    // Arrange
    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(null);
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> shareableSession =
        new ShareableSession<>(controller, destination);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBWHandlerConfiguration configuration = mock(DBWHandlerConfiguration.class);
    when(configuration.getDataSource()).thenReturn(mock(DBPDataSourceContainer.class));

    // Act and Assert
    assertThrows(DBException.class, () -> shareableSession.disconnect(monitor, configuration, 10L));
    verify(configuration).getDataSource();
    verify(controller).createSession();
  }

  /**
   * Test ShareableSession {@link ShareableSession#getConsumerInfo()}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link ShareableSession#getConsumerInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ShareableSession.getConsumerInfo()"})
  public void testShareableSessionGetConsumerInfo_thenReturnEmptyString() {
    // Arrange
    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(null);
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> shareableSession =
        new ShareableSession<>(controller, destination);

    // Act
    String actualConsumerInfo = shareableSession.getConsumerInfo();

    // Assert
    verify(controller).createSession();
    assertEquals("", actualConsumerInfo);
  }

  /**
   * Test ShareableSession {@link ShareableSession#getDataSources()}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link ShareableSession#getDataSources()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer[] ShareableSession.getDataSources()"})
  public void testShareableSessionGetDataSources_thenReturnArrayLengthIsZero() {
    // Arrange
    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(null);
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> shareableSession =
        new ShareableSession<>(controller, destination);

    // Act
    DBPDataSourceContainer[] actualDataSources = shareableSession.getDataSources();

    // Assert
    verify(controller).createSession();
    assertEquals(0, actualDataSources.length);
  }

  /**
   * Test ShareableSession {@link ShareableSession#getDestinationInfo()}.
   *
   * <ul>
   *   <li>Then return {@code janedoe@localhost:8080}.
   * </ul>
   *
   * <p>Method under test: {@link ShareableSession#getDestinationInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ShareableSession.getDestinationInfo()"})
  public void testShareableSessionGetDestinationInfo_thenReturnJanedoeLocalhost8080() {
    // Arrange
    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(null);
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> shareableSession =
        new ShareableSession<>(controller, destination);

    // Act
    String actualDestinationInfo = shareableSession.getDestinationInfo();

    // Assert
    verify(controller).createSession();
    assertEquals("janedoe@localhost:8080", actualDestinationInfo);
  }

  /**
   * Test ShareableSession {@link ShareableSession#getPortForwardingInfo()}.
   *
   * <ul>
   *   <li>Then return empty string.
   * </ul>
   *
   * <p>Method under test: {@link ShareableSession#getPortForwardingInfo()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String ShareableSession.getPortForwardingInfo()"})
  public void testShareableSessionGetPortForwardingInfo_thenReturnEmptyString() {
    // Arrange
    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(null);
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> shareableSession =
        new ShareableSession<>(controller, destination);

    // Act
    String actualPortForwardingInfo = shareableSession.getPortForwardingInfo();

    // Assert
    verify(controller).createSession();
    assertEquals("", actualPortForwardingInfo);
  }

  /**
   * Test ShareableSession {@link ShareableSession#ShareableSession(AbstractSessionController,
   * SSHHostConfiguration)}.
   *
   * <ul>
   *   <li>Given {@code null}.
   *   <li>Then {@link DelegateSession#destination} auth return {@link SSHAuthConfiguration.Agent}.
   * </ul>
   *
   * <p>Method under test: {@link ShareableSession#ShareableSession(AbstractSessionController,
   * SSHHostConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void ShareableSession.<init>(AbstractSessionController, SSHHostConfiguration)"
  })
  public void testShareableSessionNewShareableSession_givenNull_thenDestinationAuthReturnAgent() {
    // Arrange
    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(null);
    Agent auth = new Agent();
    SSHHostConfiguration destination = new SSHHostConfiguration("janedoe", "localhost", 8080, auth);

    // Act
    ShareableSession<AbstractSession> actualShareableSession =
        new ShareableSession<>(controller, destination);

    // Assert
    verify(controller).createSession();
    SSHHostConfiguration sshHostConfiguration = actualShareableSession.destination;
    SSHAuthConfiguration authResult = sshHostConfiguration.auth();
    assertTrue(authResult instanceof Agent);
    assertEquals("", actualShareableSession.getConsumerInfo());
    assertEquals("", actualShareableSession.getPortForwardingInfo());
    assertEquals("janedoe", sshHostConfiguration.username());
    assertEquals("janedoe@localhost:8080", actualShareableSession.getDestinationInfo());
    assertEquals("localhost", sshHostConfiguration.hostname());
    assertNull(actualShareableSession.getSession());
    assertEquals(0, actualShareableSession.getDataSources().length);
    assertEquals(8080, sshHostConfiguration.port());
    assertTrue(actualShareableSession.dataSources.isEmpty());
    assertTrue(actualShareableSession.portForwards.isEmpty());
    assertSame(auth, authResult);
  }

  /**
   * Test ShareableSession {@link ShareableSession#removePortForward(SSHPortForwardConfiguration)}.
   *
   * <ul>
   *   <li>Then calls {@link AbstractSessionController#createSession()}.
   * </ul>
   *
   * <p>Method under test: {@link ShareableSession#removePortForward(SSHPortForwardConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void ShareableSession.removePortForward(SSHPortForwardConfiguration)"})
  public void testShareableSessionRemovePortForward_thenCallsCreateSession() throws DBException {
    // Arrange
    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(null);
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> shareableSession =
        new ShareableSession<>(controller, destination);
    SSHPortForwardConfiguration configuration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    // Act
    shareableSession.removePortForward(configuration);

    // Assert
    verify(controller).createSession();
  }

  /**
   * Test ShareableSession {@link ShareableSession#setupPortForward(SSHPortForwardConfiguration)}.
   *
   * <p>Method under test: {@link ShareableSession#setupPortForward(SSHPortForwardConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SSHPortForwardConfiguration ShareableSession.setupPortForward(SSHPortForwardConfiguration)"
  })
  public void testShareableSessionSetupPortForward() throws DBException {
    // Arrange
    DirectSession<AbstractSession> directSession = mock(DirectSession.class);
    SSHPortForwardConfiguration sshPortForwardConfiguration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);
    when(directSession.setupPortForward(Mockito.<SSHPortForwardConfiguration>any()))
        .thenReturn(sshPortForwardConfiguration);

    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(directSession);
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> shareableSession =
        new ShareableSession<>(controller, destination);
    SSHPortForwardConfiguration configuration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    // Act
    SSHPortForwardConfiguration actualSetupPortForwardResult =
        shareableSession.setupPortForward(configuration);

    // Assert
    verify(controller).createSession();
    verify(directSession).setupPortForward(isA(SSHPortForwardConfiguration.class));
    assertEquals("localhost:8080 <- localhost:8080 (1)", shareableSession.getPortForwardingInfo());
    assertEquals(1, shareableSession.portForwards.size());
    assertSame(sshPortForwardConfiguration, actualSetupPortForwardResult);
  }

  /**
   * Test ShareableSession {@link ShareableSession#setupPortForward(SSHPortForwardConfiguration)}.
   *
   * <p>Method under test: {@link ShareableSession#setupPortForward(SSHPortForwardConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SSHPortForwardConfiguration ShareableSession.setupPortForward(SSHPortForwardConfiguration)"
  })
  public void testShareableSessionSetupPortForward2() throws DBException {
    // Arrange
    DirectSession<AbstractSession> directSession = mock(DirectSession.class);
    SSHPortForwardConfiguration sshPortForwardConfiguration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);
    when(directSession.setupPortForward(Mockito.<SSHPortForwardConfiguration>any()))
        .thenReturn(sshPortForwardConfiguration);

    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(directSession);
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> shareableSession =
        new ShareableSession<>(controller, destination);
    SSHPortForwardConfiguration configuration =
        new SSHPortForwardConfiguration("42", 8080, "localhost", 8080);

    // Act
    SSHPortForwardConfiguration actualSetupPortForwardResult =
        shareableSession.setupPortForward(configuration);

    // Assert
    verify(controller).createSession();
    verify(directSession).setupPortForward(isA(SSHPortForwardConfiguration.class));
    assertEquals("localhost", actualSetupPortForwardResult.localHost());
    assertEquals("localhost", actualSetupPortForwardResult.remoteHost());
    assertEquals(8080, actualSetupPortForwardResult.localPort());
    assertEquals(8080, actualSetupPortForwardResult.remotePort());
  }

  /**
   * Test ShareableSession {@link ShareableSession#setupPortForward(SSHPortForwardConfiguration)}.
   *
   * <ul>
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link ShareableSession#setupPortForward(SSHPortForwardConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SSHPortForwardConfiguration ShareableSession.setupPortForward(SSHPortForwardConfiguration)"
  })
  public void testShareableSessionSetupPortForward_thenReturnNull() throws DBException {
    // Arrange
    DirectSession<AbstractSession> directSession = mock(DirectSession.class);
    when(directSession.setupPortForward(Mockito.<SSHPortForwardConfiguration>any()))
        .thenReturn(null);

    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(directSession);
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> shareableSession =
        new ShareableSession<>(controller, destination);
    SSHPortForwardConfiguration configuration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    // Act
    SSHPortForwardConfiguration actualSetupPortForwardResult =
        shareableSession.setupPortForward(configuration);

    // Assert
    assertNull(actualSetupPortForwardResult);
    verify(controller).createSession();
    verify(directSession).setupPortForward(isA(SSHPortForwardConfiguration.class));
    Map<SSHPortForwardConfiguration, PortForwardInfo>
        sshPortForwardConfigurationPortForwardInfoMap = shareableSession.portForwards;
    assertEquals(1, sshPortForwardConfigurationPortForwardInfoMap.size());
    PortForwardInfo getResult = sshPortForwardConfigurationPortForwardInfoMap.get(null);
    assertNull(getResult.resolved());
    AtomicInteger usagesResult = getResult.usages();
    assertEquals(1, usagesResult.get());
    assertEquals(1, usagesResult.getAndDecrement());
    assertEquals(0, usagesResult.getAndIncrement());
  }

  /**
   * Test ShareableSession {@link ShareableSession#setupPortForward(SSHPortForwardConfiguration)}.
   *
   * <ul>
   *   <li>When {@code null}.
   *   <li>Then return {@code localhost}.
   * </ul>
   *
   * <p>Method under test: {@link ShareableSession#setupPortForward(SSHPortForwardConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SSHPortForwardConfiguration ShareableSession.setupPortForward(SSHPortForwardConfiguration)"
  })
  public void testShareableSessionSetupPortForward_whenNull_thenReturnLocalhost()
      throws DBException {
    // Arrange
    DirectSession<AbstractSession> directSession = mock(DirectSession.class);
    SSHPortForwardConfiguration sshPortForwardConfiguration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);
    when(directSession.setupPortForward(Mockito.<SSHPortForwardConfiguration>any()))
        .thenReturn(sshPortForwardConfiguration);

    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(directSession);
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> shareableSession =
        new ShareableSession<>(controller, destination);

    // Act
    SSHPortForwardConfiguration actualSetupPortForwardResult =
        shareableSession.setupPortForward(null);

    // Assert
    verify(controller).createSession();
    verify(directSession).setupPortForward((SSHPortForwardConfiguration) isNull());
    assertEquals("localhost", actualSetupPortForwardResult.localHost());
    assertEquals("localhost", actualSetupPortForwardResult.remoteHost());
    assertEquals(8080, actualSetupPortForwardResult.localPort());
    assertEquals(8080, actualSetupPortForwardResult.remotePort());
  }

  /**
   * Test WrapperSession {@link WrapperSession#connect(DBRProgressMonitor, SSHHostConfiguration,
   * DBWHandlerConfiguration)}.
   *
   * <ul>
   *   <li>Given {@link ShareableSession} {@link ShareableSession#connect(DBRProgressMonitor,
   *       SSHHostConfiguration, DBWHandlerConfiguration)} does nothing.
   *   <li>Then calls {@link DBWHandlerDescriptor#getId()}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperSession#connect(DBRProgressMonitor, SSHHostConfiguration,
   * DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WrapperSession.connect(DBRProgressMonitor, SSHHostConfiguration, DBWHandlerConfiguration)"
  })
  public void testWrapperSessionConnect_givenShareableSessionConnectDoesNothing_thenCallsGetId()
      throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doNothing()
        .when(inner)
        .connect(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<SSHHostConfiguration>any(),
            Mockito.<DBWHandlerConfiguration>any());
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    wrapperSession.connect(monitor, destination, configuration);

    // Assert
    verify(descriptor).getId();
    verify(inner)
        .connect(
            isA(DBRProgressMonitor.class),
            isA(SSHHostConfiguration.class),
            isA(DBWHandlerConfiguration.class));
  }

  /**
   * Test WrapperSession {@link WrapperSession#connect(DBRProgressMonitor, SSHHostConfiguration,
   * DBWHandlerConfiguration)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperSession#connect(DBRProgressMonitor, SSHHostConfiguration,
   * DBWHandlerConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WrapperSession.connect(DBRProgressMonitor, SSHHostConfiguration, DBWHandlerConfiguration)"
  })
  public void testWrapperSessionConnect_thenThrowDBException() throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doThrow(new DBException("An error occurred"))
        .when(inner)
        .connect(
            Mockito.<DBRProgressMonitor>any(),
            Mockito.<SSHHostConfiguration>any(),
            Mockito.<DBWHandlerConfiguration>any());
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act and Assert
    assertThrows(
        DBException.class, () -> wrapperSession.connect(monitor, destination, configuration));
    verify(descriptor).getId();
    verify(inner)
        .connect(
            isA(DBRProgressMonitor.class),
            isA(SSHHostConfiguration.class),
            isA(DBWHandlerConfiguration.class));
  }

  /**
   * Test WrapperSession {@link WrapperSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}.
   *
   * <ul>
   *   <li>Given {@link ShareableSession} {@link ShareableSession#disconnect(DBRProgressMonitor,
   *       DBWHandlerConfiguration, long)} does nothing.
   * </ul>
   *
   * <p>Method under test: {@link WrapperSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WrapperSession.disconnect(DBRProgressMonitor, DBWHandlerConfiguration, long)"
  })
  public void testWrapperSessionDisconnect_givenShareableSessionDisconnectDoesNothing()
      throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doNothing()
        .when(inner)
        .disconnect(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBWHandlerConfiguration>any(), anyLong());
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act
    wrapperSession.disconnect(monitor, configuration, 10L);

    // Assert
    verify(descriptor).getId();
    verify(inner)
        .disconnect(isA(DBRProgressMonitor.class), isA(DBWHandlerConfiguration.class), eq(10L));
  }

  /**
   * Test WrapperSession {@link WrapperSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperSession#disconnect(DBRProgressMonitor,
   * DBWHandlerConfiguration, long)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void WrapperSession.disconnect(DBRProgressMonitor, DBWHandlerConfiguration, long)"
  })
  public void testWrapperSessionDisconnect_thenThrowDBException() throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doThrow(new DBException("An error occurred"))
        .when(inner)
        .disconnect(
            Mockito.<DBRProgressMonitor>any(), Mockito.<DBWHandlerConfiguration>any(), anyLong());
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);
    LoggingProgressMonitor monitor = new LoggingProgressMonitor();

    DBWHandlerDescriptor descriptor = mock(DBWHandlerDescriptor.class);
    when(descriptor.getId()).thenReturn("42");
    DBWHandlerConfiguration configuration =
        new DBWHandlerConfiguration(descriptor, mock(DBPDataSourceContainer.class));

    // Act and Assert
    assertThrows(DBException.class, () -> wrapperSession.disconnect(monitor, configuration, 10L));
    verify(descriptor).getId();
    verify(inner)
        .disconnect(isA(DBRProgressMonitor.class), isA(DBWHandlerConfiguration.class), eq(10L));
  }

  /**
   * Test WrapperSession {@link WrapperSession#getDataSources()}.
   *
   * <ul>
   *   <li>Then return array length is one.
   * </ul>
   *
   * <p>Method under test: {@link WrapperSession#getDataSources()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer[] WrapperSession.getDataSources()"})
  public void testWrapperSessionGetDataSources_thenReturnArrayLengthIsOne() {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    when(inner.getDataSources())
        .thenReturn(new DBPDataSourceContainer[] {mock(DBPDataSourceContainer.class)});
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);

    // Act
    DBPDataSourceContainer[] actualDataSources = wrapperSession.getDataSources();

    // Assert
    verify(inner).getDataSources();
    assertEquals(1, actualDataSources.length);
  }

  /**
   * Test WrapperSession {@link WrapperSession#getDataSources()}.
   *
   * <ul>
   *   <li>Then return array length is zero.
   * </ul>
   *
   * <p>Method under test: {@link WrapperSession#getDataSources()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"DBPDataSourceContainer[] WrapperSession.getDataSources()"})
  public void testWrapperSessionGetDataSources_thenReturnArrayLengthIsZero() {
    // Arrange
    AbstractSessionController<AbstractSession> controller = mock(AbstractSessionController.class);
    when(controller.createSession()).thenReturn(new WrapperSession<>(mock(ShareableSession.class)));
    SSHHostConfiguration destination =
        new SSHHostConfiguration("janedoe", "localhost", 8080, new Agent());

    ShareableSession<AbstractSession> inner = new ShareableSession<>(controller, destination);
    SSHPortForwardConfiguration portForward =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    DirectSession<AbstractSession> directSession = new DirectSession<>(inner, portForward);

    // Act
    DBPDataSourceContainer[] actualDataSources = directSession.getDataSources();

    // Assert
    verify(controller).createSession();
    assertEquals(0, actualDataSources.length);
  }

  /**
   * Test WrapperSession {@link WrapperSession#WrapperSession(ShareableSession)}.
   *
   * <ul>
   *   <li>Then return ClientVersion is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperSession#WrapperSession(ShareableSession)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperSession.<init>(ShareableSession)"})
  public void testWrapperSessionNewWrapperSession_thenReturnClientVersionIsNull() {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);

    // Act
    WrapperSession<AbstractSession> actualWrapperSession = new WrapperSession<>(inner);

    // Assert
    assertNull(actualWrapperSession.getClientVersion());
    assertNull(actualWrapperSession.getServerVersion());
    assertNull(actualWrapperSession.getDataSources());
    assertNull(actualWrapperSession.destination);
    assertSame(inner, actualWrapperSession.getSession());
  }

  /**
   * Test WrapperSession {@link WrapperSession#removePortForward(SSHPortForwardConfiguration)}.
   *
   * <p>Method under test: {@link WrapperSession#removePortForward(SSHPortForwardConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperSession.removePortForward(SSHPortForwardConfiguration)"})
  public void testWrapperSessionRemovePortForward() throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doNothing().when(inner).removePortForward(Mockito.<SSHPortForwardConfiguration>any());
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);
    SSHPortForwardConfiguration configuration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    // Act
    wrapperSession.removePortForward(configuration);

    // Assert
    verify(inner).removePortForward(isA(SSHPortForwardConfiguration.class));
  }

  /**
   * Test WrapperSession {@link WrapperSession#removePortForward(SSHPortForwardConfiguration)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperSession#removePortForward(SSHPortForwardConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void WrapperSession.removePortForward(SSHPortForwardConfiguration)"})
  public void testWrapperSessionRemovePortForward_thenThrowDBException() throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    doThrow(new DBException("An error occurred"))
        .when(inner)
        .removePortForward(Mockito.<SSHPortForwardConfiguration>any());
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);
    SSHPortForwardConfiguration configuration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    // Act and Assert
    assertThrows(DBException.class, () -> wrapperSession.removePortForward(configuration));
    verify(inner).removePortForward(isA(SSHPortForwardConfiguration.class));
  }

  /**
   * Test WrapperSession {@link WrapperSession#setupPortForward(SSHPortForwardConfiguration)}.
   *
   * <p>Method under test: {@link WrapperSession#setupPortForward(SSHPortForwardConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SSHPortForwardConfiguration WrapperSession.setupPortForward(SSHPortForwardConfiguration)"
  })
  public void testWrapperSessionSetupPortForward() throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    SSHPortForwardConfiguration sshPortForwardConfiguration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);
    when(inner.setupPortForward(Mockito.<SSHPortForwardConfiguration>any()))
        .thenReturn(sshPortForwardConfiguration);
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);
    SSHPortForwardConfiguration configuration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    // Act
    SSHPortForwardConfiguration actualSetupPortForwardResult =
        wrapperSession.setupPortForward(configuration);

    // Assert
    verify(inner).setupPortForward(isA(SSHPortForwardConfiguration.class));
    assertSame(sshPortForwardConfiguration, actualSetupPortForwardResult);
  }

  /**
   * Test WrapperSession {@link WrapperSession#setupPortForward(SSHPortForwardConfiguration)}.
   *
   * <ul>
   *   <li>Then throw {@link DBException}.
   * </ul>
   *
   * <p>Method under test: {@link WrapperSession#setupPortForward(SSHPortForwardConfiguration)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SSHPortForwardConfiguration WrapperSession.setupPortForward(SSHPortForwardConfiguration)"
  })
  public void testWrapperSessionSetupPortForward_thenThrowDBException() throws DBException {
    // Arrange
    ShareableSession<AbstractSession> inner = mock(ShareableSession.class);
    when(inner.setupPortForward(Mockito.<SSHPortForwardConfiguration>any()))
        .thenThrow(new DBException("An error occurred"));
    WrapperSession<AbstractSession> wrapperSession = new WrapperSession<>(inner);
    SSHPortForwardConfiguration configuration =
        new SSHPortForwardConfiguration("localhost", 8080, "localhost", 8080);

    // Act and Assert
    assertThrows(DBException.class, () -> wrapperSession.setupPortForward(configuration));
    verify(inner).setupPortForward(isA(SSHPortForwardConfiguration.class));
  }
}
