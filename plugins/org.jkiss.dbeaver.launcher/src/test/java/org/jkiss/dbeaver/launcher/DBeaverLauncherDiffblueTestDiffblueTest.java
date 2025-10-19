package org.jkiss.dbeaver.launcher;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.verify;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.misusing.NullInsteadOfMockException;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DBeaverLauncherDiffblueTestDiffblueTest {
  @Mock private DBeaverLauncher dBeaverLauncher;

  @InjectMocks private DBeaverLauncherDiffblueTest dBeaverLauncherDiffblueTest;

  /**
   * Test {@link DBeaverLauncherDiffblueTest#testSplashHandlerRun()}.
   *
   * <ul>
   *   <li>Given {@link DBeaverLauncher}.
   *   <li>Then calls {@link DBeaverLauncher#takeDownSplash()}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncherDiffblueTest#testSplashHandlerRun()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBeaverLauncherDiffblueTest.testSplashHandlerRun()"})
  public void testTestSplashHandlerRun_givenDBeaverLauncher_thenCallsTakeDownSplash() {
    // Arrange and Act
    dBeaverLauncherDiffblueTest.testSplashHandlerRun();

    // Assert
    verify(dBeaverLauncher).takeDownSplash();
  }

  /**
   * Test {@link DBeaverLauncherDiffblueTest#testSplashHandlerRun()}.
   *
   * <ul>
   *   <li>Then throw {@link NullInsteadOfMockException}.
   * </ul>
   *
   * <p>Method under test: {@link DBeaverLauncherDiffblueTest#testSplashHandlerRun()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DBeaverLauncherDiffblueTest.testSplashHandlerRun()"})
  public void testTestSplashHandlerRun_thenThrowNullInsteadOfMockException() {
    // Arrange, Act and Assert
    assertThrows(
        NullInsteadOfMockException.class,
        () -> new DBeaverLauncherDiffblueTest().testSplashHandlerRun());
  }
}
