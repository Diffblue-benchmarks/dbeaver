package org.jkiss.junit.osgi.delegate;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.StoppedByUserException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class RunNotifierDelegateDiffblueTest {
  @Mock private Object object;

  @InjectMocks private RunNotifierDelegate runNotifierDelegate;

  /**
   * Test {@link RunNotifierDelegate#addListener(RunListener)}.
   *
   * <ul>
   *   <li>Given {@link Object}.
   *   <li>When {@link RunListener} (default constructor).
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#addListener(RunListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.addListener(RunListener)"})
  public void testAddListener_givenObject_whenRunListener_thenThrowRuntimeException() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> runNotifierDelegate.addListener(new RunListener()));
  }

  /**
   * Test {@link RunNotifierDelegate#addListener(RunListener)}.
   *
   * <ul>
   *   <li>Given {@link RunNotifierDelegate#RunNotifierDelegate(Object)} with delegate is {@link
   *       RunListener} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#addListener(RunListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.addListener(RunListener)"})
  public void testAddListener_givenRunNotifierDelegateWithDelegateIsRunListener() {
    // Arrange
    RunNotifierDelegate runNotifierDelegate = new RunNotifierDelegate(new RunListener());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> runNotifierDelegate.addListener(new RunListener()));
  }

  /**
   * Test {@link RunNotifierDelegate#addListener(RunListener)}.
   *
   * <ul>
   *   <li>When {@link RunListenerDelegate#RunListenerDelegate(Object)} with {@code Delegate}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#addListener(RunListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.addListener(RunListener)"})
  public void testAddListener_whenRunListenerDelegateWithDelegate() {
    // Arrange
    RunNotifierDelegate runNotifierDelegate = new RunNotifierDelegate(new RunListener());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> runNotifierDelegate.addListener(new RunListenerDelegate("Delegate")));
  }

  /**
   * Test {@link RunNotifierDelegate#removeListener(RunListener)}.
   *
   * <ul>
   *   <li>Given {@link Object}.
   *   <li>When {@link RunListener} (default constructor).
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#removeListener(RunListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.removeListener(RunListener)"})
  public void testRemoveListener_givenObject_whenRunListener_thenThrowRuntimeException() {
    // Arrange, Act and Assert
    assertThrows(
        RuntimeException.class, () -> runNotifierDelegate.removeListener(new RunListener()));
  }

  /**
   * Test {@link RunNotifierDelegate#removeListener(RunListener)}.
   *
   * <ul>
   *   <li>Given {@link RunNotifierDelegate#RunNotifierDelegate(Object)} with delegate is {@link
   *       RunListener} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#removeListener(RunListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.removeListener(RunListener)"})
  public void testRemoveListener_givenRunNotifierDelegateWithDelegateIsRunListener() {
    // Arrange
    RunNotifierDelegate runNotifierDelegate = new RunNotifierDelegate(new RunListener());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> runNotifierDelegate.removeListener(new RunListener()));
  }

  /**
   * Test {@link RunNotifierDelegate#removeListener(RunListener)}.
   *
   * <ul>
   *   <li>When {@link RunListenerDelegate#RunListenerDelegate(Object)} with {@code Delegate}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#removeListener(RunListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.removeListener(RunListener)"})
  public void testRemoveListener_whenRunListenerDelegateWithDelegate() {
    // Arrange
    RunNotifierDelegate runNotifierDelegate = new RunNotifierDelegate(new RunListener());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> runNotifierDelegate.removeListener(new RunListenerDelegate("Delegate")));
  }

  /**
   * Test {@link RunNotifierDelegate#fireTestSuiteStarted(Description)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#fireTestSuiteStarted(Description)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.fireTestSuiteStarted(Description)"})
  public void testFireTestSuiteStarted_whenJavaLangObject_thenThrowRuntimeException() {
    // Arrange
    Class<Object> testClass = Object.class;

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            runNotifierDelegate.fireTestSuiteStarted(
                Description.createSuiteDescription(testClass)));
  }

  /**
   * Test {@link RunNotifierDelegate#fireTestSuiteFinished(Description)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#fireTestSuiteFinished(Description)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.fireTestSuiteFinished(Description)"})
  public void testFireTestSuiteFinished_whenJavaLangObject_thenThrowRuntimeException() {
    // Arrange
    Class<Object> testClass = Object.class;

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            runNotifierDelegate.fireTestSuiteFinished(
                Description.createSuiteDescription(testClass)));
  }

  /**
   * Test {@link RunNotifierDelegate#pleaseStop()}.
   *
   * <ul>
   *   <li>Given {@link RunNotifierDelegate}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#pleaseStop()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.pleaseStop()"})
  public void testPleaseStop_givenRunNotifierDelegate() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> runNotifierDelegate.pleaseStop());
  }

  /**
   * Test {@link RunNotifierDelegate#pleaseStop()}.
   *
   * <ul>
   *   <li>Given {@link RunNotifierDelegate#RunNotifierDelegate(Object)} with {@code Delegate}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#pleaseStop()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.pleaseStop()"})
  public void testPleaseStop_givenRunNotifierDelegateWithDelegate() {
    // Arrange, Act and Assert
    assertThrows(RuntimeException.class, () -> new RunNotifierDelegate("Delegate").pleaseStop());
  }

  /**
   * Test {@link RunNotifierDelegate#addFirstListener(RunListener)}.
   *
   * <ul>
   *   <li>Given {@link Object}.
   *   <li>When {@link RunListener} (default constructor).
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#addFirstListener(RunListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.addFirstListener(RunListener)"})
  public void testAddFirstListener_givenObject_whenRunListener_thenThrowRuntimeException() {
    // Arrange, Act and Assert
    assertThrows(
        RuntimeException.class, () -> runNotifierDelegate.addFirstListener(new RunListener()));
  }

  /**
   * Test {@link RunNotifierDelegate#addFirstListener(RunListener)}.
   *
   * <ul>
   *   <li>Given {@link RunNotifierDelegate#RunNotifierDelegate(Object)} with delegate is {@link
   *       RunListener} (default constructor).
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#addFirstListener(RunListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.addFirstListener(RunListener)"})
  public void testAddFirstListener_givenRunNotifierDelegateWithDelegateIsRunListener() {
    // Arrange
    RunNotifierDelegate runNotifierDelegate = new RunNotifierDelegate(new RunListener());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> runNotifierDelegate.addFirstListener(new RunListener()));
  }

  /**
   * Test {@link RunNotifierDelegate#addFirstListener(RunListener)}.
   *
   * <ul>
   *   <li>When {@link RunListenerDelegate#RunListenerDelegate(Object)} with {@code Delegate}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#addFirstListener(RunListener)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.addFirstListener(RunListener)"})
  public void testAddFirstListener_whenRunListenerDelegateWithDelegate() {
    // Arrange
    RunNotifierDelegate runNotifierDelegate = new RunNotifierDelegate(new RunListener());

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> runNotifierDelegate.addFirstListener(new RunListenerDelegate("Delegate")));
  }

  /**
   * Test {@link RunNotifierDelegate#fireTestRunStarted(Description)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#fireTestRunStarted(Description)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.fireTestRunStarted(Description)"})
  public void testFireTestRunStarted_whenJavaLangObject_thenThrowRuntimeException() {
    // Arrange
    Class<Object> testClass = Object.class;

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () ->
            runNotifierDelegate.fireTestRunStarted(Description.createSuiteDescription(testClass)));
  }

  /**
   * Test {@link RunNotifierDelegate#fireTestRunFinished(Result)}.
   *
   * <ul>
   *   <li>When {@link Result#Result()}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#fireTestRunFinished(Result)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.fireTestRunFinished(Result)"})
  public void testFireTestRunFinished_whenResult_thenThrowRuntimeException() {
    // Arrange, Act and Assert
    assertThrows(
        RuntimeException.class, () -> runNotifierDelegate.fireTestRunFinished(new Result()));
  }

  /**
   * Test {@link RunNotifierDelegate#fireTestStarted(Description)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#fireTestStarted(Description)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.fireTestStarted(Description)"})
  public void testFireTestStarted_whenJavaLangObject_thenThrowRuntimeException()
      throws StoppedByUserException {
    // Arrange
    Class<Object> testClass = Object.class;

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> runNotifierDelegate.fireTestStarted(Description.createSuiteDescription(testClass)));
  }

  /**
   * Test {@link RunNotifierDelegate#fireTestFailure(Failure)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#fireTestFailure(Failure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.fireTestFailure(Failure)"})
  public void testFireTestFailure_whenJavaLangObject_thenThrowRuntimeException() {
    // Arrange
    Class<Object> testClass = Object.class;
    Description description = Description.createSuiteDescription(testClass);
    Failure failure = new Failure(description, new Throwable());

    // Act and Assert
    assertThrows(RuntimeException.class, () -> runNotifierDelegate.fireTestFailure(failure));
  }

  /**
   * Test {@link RunNotifierDelegate#fireTestAssumptionFailed(Failure)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#fireTestAssumptionFailed(Failure)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.fireTestAssumptionFailed(Failure)"})
  public void testFireTestAssumptionFailed_whenJavaLangObject_thenThrowRuntimeException() {
    // Arrange
    Class<Object> testClass = Object.class;
    Description description = Description.createSuiteDescription(testClass);
    Failure failure = new Failure(description, new Throwable());

    // Act and Assert
    assertThrows(
        RuntimeException.class, () -> runNotifierDelegate.fireTestAssumptionFailed(failure));
  }

  /**
   * Test {@link RunNotifierDelegate#fireTestIgnored(Description)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#fireTestIgnored(Description)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.fireTestIgnored(Description)"})
  public void testFireTestIgnored_whenJavaLangObject_thenThrowRuntimeException() {
    // Arrange
    Class<Object> testClass = Object.class;

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> runNotifierDelegate.fireTestIgnored(Description.createSuiteDescription(testClass)));
  }

  /**
   * Test {@link RunNotifierDelegate#fireTestFinished(Description)}.
   *
   * <ul>
   *   <li>When {@code Object}.
   *   <li>Then throw {@link RuntimeException}.
   * </ul>
   *
   * <p>Method under test: {@link RunNotifierDelegate#fireTestFinished(Description)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void RunNotifierDelegate.fireTestFinished(Description)"})
  public void testFireTestFinished_whenJavaLangObject_thenThrowRuntimeException() {
    // Arrange
    Class<Object> testClass = Object.class;

    // Act and Assert
    assertThrows(
        RuntimeException.class,
        () -> runNotifierDelegate.fireTestFinished(Description.createSuiteDescription(testClass)));
  }
}
