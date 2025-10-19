package org.jkiss.dbeaver.launcher;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;
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
public class DBeaverLauncherDiffblueTestDiffblueTestDiffblueTest {
  @Mock private DBeaverLauncherDiffblueTest dBeaverLauncherDiffblueTest;

  @InjectMocks
  private DBeaverLauncherDiffblueTestDiffblueTest dBeaverLauncherDiffblueTestDiffblueTest;

  /**
   * Test {@link
   * DBeaverLauncherDiffblueTestDiffblueTest#testTestSplashHandlerRun_givenDBeaverLauncher_thenCallsTakeDownSplash()}.
   *
   * <p>Method under test: {@link
   * DBeaverLauncherDiffblueTestDiffblueTest#testTestSplashHandlerRun_givenDBeaverLauncher_thenCallsTakeDownSplash()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBeaverLauncherDiffblueTestDiffblueTest.testTestSplashHandlerRun_givenDBeaverLauncher_thenCallsTakeDownSplash()"
  })
  public void testTestTestSplashHandlerRun_givenDBeaverLauncher_thenCallsTakeDownSplash() {
    // Arrange
    doThrow(new NullInsteadOfMockException("An error occurred"))
        .when(dBeaverLauncherDiffblueTest)
        .testSplashHandlerRun();

    // Act and Assert
    assertThrows(
        NullInsteadOfMockException.class,
        () ->
            dBeaverLauncherDiffblueTestDiffblueTest
                .testTestSplashHandlerRun_givenDBeaverLauncher_thenCallsTakeDownSplash());
    verify(dBeaverLauncherDiffblueTest).testSplashHandlerRun();
  }
}
