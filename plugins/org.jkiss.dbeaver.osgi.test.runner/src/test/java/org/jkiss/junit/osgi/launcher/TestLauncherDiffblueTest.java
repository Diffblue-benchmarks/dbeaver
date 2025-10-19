package org.jkiss.junit.osgi.launcher;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.eclipse.osgi.internal.framework.BundleContextImpl;
import org.eclipse.osgi.internal.framework.EquinoxConfiguration;
import org.eclipse.osgi.internal.framework.EquinoxContainer;
import org.eclipse.osgi.service.runnable.ParameterizedRunnable;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;

public class TestLauncherDiffblueTest {
  /**
   * Test {@link TestLauncher#TestLauncher(BundleContext)}.
   *
   * <ul>
   *   <li>Given array of {@link ServiceReference} with {@link ServiceReference}.
   * </ul>
   *
   * <p>Method under test: {@link TestLauncher#TestLauncher(BundleContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TestLauncher.<init>(BundleContext)"})
  public void testNewTestLauncher_givenArrayOfServiceReferenceWithServiceReference()
      throws InvalidSyntaxException {
    // Arrange
    BundleContextImpl context = mock(BundleContextImpl.class);
    Mockito.<ServiceReference<?>[]>when(
            context.getServiceReferences(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ServiceReference[] {mock(ServiceReference.class)});

    // Act
    new TestLauncher(context);

    // Assert
    verify(context)
        .getServiceReferences(
            "org.eclipse.osgi.service.runnable.ParameterizedRunnable",
            "(&(objectClass=org.eclipse.osgi.service.runnable.ParameterizedRunnable)(eclipse.application=*))");
  }

  /**
   * Test {@link TestLauncher#TestLauncher(BundleContext)}.
   *
   * <ul>
   *   <li>Given empty array of {@link ServiceReference}.
   * </ul>
   *
   * <p>Method under test: {@link TestLauncher#TestLauncher(BundleContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TestLauncher.<init>(BundleContext)"})
  public void testNewTestLauncher_givenEmptyArrayOfServiceReference()
      throws InvalidSyntaxException {
    // Arrange
    BundleContextImpl context = mock(BundleContextImpl.class);
    Mockito.<ServiceReference<?>[]>when(
            context.getServiceReferences(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ServiceReference[] {});

    // Act
    new TestLauncher(context);

    // Assert
    verify(context)
        .getServiceReferences(
            "org.eclipse.osgi.service.runnable.ParameterizedRunnable",
            "(&(objectClass=org.eclipse.osgi.service.runnable.ParameterizedRunnable)(eclipse.application=*))");
  }

  /**
   * Test {@link TestLauncher#TestLauncher(BundleContext)}.
   *
   * <ul>
   *   <li>Given {@link InvalidSyntaxException#InvalidSyntaxException(String, String)} with {@code
   *       Msg} and {@code Filter}.
   * </ul>
   *
   * <p>Method under test: {@link TestLauncher#TestLauncher(BundleContext)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TestLauncher.<init>(BundleContext)"})
  public void testNewTestLauncher_givenInvalidSyntaxExceptionWithMsgAndFilter()
      throws InvalidSyntaxException {
    // Arrange
    BundleContextImpl context = mock(BundleContextImpl.class);
    Mockito.<ServiceReference<?>[]>when(
            context.getServiceReferences(Mockito.<String>any(), Mockito.<String>any()))
        .thenThrow(new InvalidSyntaxException("Msg", "Filter"));

    // Act
    new TestLauncher(context);

    // Assert
    verify(context)
        .getServiceReferences(
            "org.eclipse.osgi.service.runnable.ParameterizedRunnable",
            "(&(objectClass=org.eclipse.osgi.service.runnable.ParameterizedRunnable)(eclipse.application=*))");
  }

  /**
   * Test {@link TestLauncher#launch(ParameterizedRunnable, Object)}.
   *
   * <ul>
   *   <li>Then calls {@link BundleContextImpl#getServiceReferences(String, String)}.
   * </ul>
   *
   * <p>Method under test: {@link TestLauncher#launch(ParameterizedRunnable, Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void TestLauncher.launch(ParameterizedRunnable, Object)"})
  public void testLaunch_thenCallsGetServiceReferences() throws InvalidSyntaxException {
    // Arrange
    BundleContextImpl context = mock(BundleContextImpl.class);
    Mockito.<ServiceReference<?>[]>when(
            context.getServiceReferences(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ServiceReference[] {mock(ServiceReference.class)});

    // Act
    new TestLauncher(context).launch(mock(ParameterizedRunnable.class), "Context");

    // Assert
    verify(context)
        .getServiceReferences(
            "org.eclipse.osgi.service.runnable.ParameterizedRunnable",
            "(&(objectClass=org.eclipse.osgi.service.runnable.ParameterizedRunnable)(eclipse.application=*))");
  }

  /**
   * Test {@link TestLauncher#start(String, String[])}.
   *
   * <ul>
   *   <li>Given {@link EquinoxConfiguration} {@link EquinoxConfiguration#setAllArgs(String[])} does
   *       nothing.
   *   <li>Then calls {@link EquinoxConfiguration#setAllArgs(String[])}.
   * </ul>
   *
   * <p>Method under test: {@link TestLauncher#start(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TestLauncher.start(String, String[])"})
  public void testStart_givenEquinoxConfigurationSetAllArgsDoesNothing_thenCallsSetAllArgs()
      throws Exception {
    // Arrange
    EquinoxConfiguration equinoxConfiguration = mock(EquinoxConfiguration.class);
    when(equinoxConfiguration.setConfiguration(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Configuration");
    doNothing().when(equinoxConfiguration).setAllArgs(Mockito.<String[]>any());

    EquinoxContainer equinoxContainer = mock(EquinoxContainer.class);
    when(equinoxContainer.getConfiguration()).thenReturn(equinoxConfiguration);

    BundleContextImpl context = mock(BundleContextImpl.class);
    when(context.getContainer()).thenReturn(equinoxContainer);
    Mockito.<ServiceReference<?>[]>when(
            context.getServiceReferences(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ServiceReference[] {mock(ServiceReference.class)});

    ParameterizedRunnable runnable = mock(ParameterizedRunnable.class);
    when(runnable.run(Mockito.<Object>any())).thenReturn("Run");

    TestLauncher testLauncher = new TestLauncher(context);
    testLauncher.launch(runnable, "Context");

    // Act
    Object actualStartResult = testLauncher.start("App ID", new String[] {"Args"});

    // Assert
    verify(context, atLeast(1)).getContainer();
    verify(context)
        .getServiceReferences(
            "org.eclipse.osgi.service.runnable.ParameterizedRunnable",
            "(&(objectClass=org.eclipse.osgi.service.runnable.ParameterizedRunnable)(eclipse.application=*))");
    verify(equinoxConfiguration).setAllArgs(isA(String[].class));
    verify(equinoxConfiguration).setConfiguration("eclipse.application", "App ID");
    verify(equinoxContainer, atLeast(1)).getConfiguration();
    verify(runnable).run(isA(Object.class));
    assertEquals("Run", actualStartResult);
  }

  /**
   * Test {@link TestLauncher#start(String, String[])}.
   *
   * <ul>
   *   <li>When empty array of {@link String}.
   *   <li>Then return {@code Run}.
   * </ul>
   *
   * <p>Method under test: {@link TestLauncher#start(String, String[])}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Object TestLauncher.start(String, String[])"})
  public void testStart_whenEmptyArrayOfString_thenReturnRun() throws Exception {
    // Arrange
    EquinoxConfiguration equinoxConfiguration = mock(EquinoxConfiguration.class);
    when(equinoxConfiguration.setConfiguration(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn("Configuration");

    EquinoxContainer equinoxContainer = mock(EquinoxContainer.class);
    when(equinoxContainer.getConfiguration()).thenReturn(equinoxConfiguration);

    BundleContextImpl context = mock(BundleContextImpl.class);
    when(context.getContainer()).thenReturn(equinoxContainer);
    Mockito.<ServiceReference<?>[]>when(
            context.getServiceReferences(Mockito.<String>any(), Mockito.<String>any()))
        .thenReturn(new ServiceReference[] {mock(ServiceReference.class)});

    ParameterizedRunnable runnable = mock(ParameterizedRunnable.class);
    when(runnable.run(Mockito.<Object>any())).thenReturn("Run");

    TestLauncher testLauncher = new TestLauncher(context);
    testLauncher.launch(runnable, "Context");

    // Act
    Object actualStartResult = testLauncher.start("App ID", new String[] {});

    // Assert
    verify(context).getContainer();
    verify(context)
        .getServiceReferences(
            "org.eclipse.osgi.service.runnable.ParameterizedRunnable",
            "(&(objectClass=org.eclipse.osgi.service.runnable.ParameterizedRunnable)(eclipse.application=*))");
    verify(equinoxConfiguration).setConfiguration("eclipse.application", "App ID");
    verify(equinoxContainer).getConfiguration();
    verify(runnable).run(isA(Object.class));
    assertEquals("Run", actualStartResult);
  }
}
