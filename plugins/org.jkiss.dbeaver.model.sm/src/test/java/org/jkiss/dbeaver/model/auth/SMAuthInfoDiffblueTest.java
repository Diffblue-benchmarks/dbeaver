package org.jkiss.dbeaver.model.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import org.jkiss.dbeaver.model.security.user.SMAuthPermissions;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMAuthInfoDiffblueTest {
  /**
   * Test {@link SMAuthInfo#expired(String, Map, boolean, String)}.
   *
   * <p>Method under test: {@link SMAuthInfo#expired(String, Map, boolean, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMAuthInfo SMAuthInfo.expired(String, Map, boolean, String)"})
  public void testExpired() {
    // Arrange and Act
    SMAuthInfo actualExpiredResult = SMAuthInfo.expired("42", new HashMap<>(), true, "42");

    // Assert
    assertEquals("42", actualExpiredResult.getAppSessionId());
    assertEquals("42", actualExpiredResult.getAuthAttemptId());
    assertNull(actualExpiredResult.getAuthRole());
    assertNull(actualExpiredResult.getError());
    assertNull(actualExpiredResult.getErrorCode());
    assertNull(actualExpiredResult.getRedirectUrl());
    assertNull(actualExpiredResult.getSignInLink());
    assertNull(actualExpiredResult.getSignOutLink());
    assertNull(actualExpiredResult.getSmAccessToken());
    assertNull(actualExpiredResult.getSmRefreshToken());
    assertNull(actualExpiredResult.getAuthPermissions());
    assertEquals(SMAuthStatus.EXPIRED, actualExpiredResult.getAuthStatus());
    assertFalse(actualExpiredResult.isForceSessionsLogout());
    assertTrue(actualExpiredResult.getAuthData().isEmpty());
    assertTrue(actualExpiredResult.isMainAuth());
  }

  /**
   * Test {@link SMAuthInfo#error(String, String, boolean, String, String)}.
   *
   * <p>Method under test: {@link SMAuthInfo#error(String, String, boolean, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"SMAuthInfo SMAuthInfo.error(String, String, boolean, String, String)"})
  public void testError() {
    // Arrange and Act
    SMAuthInfo actualErrorResult =
        SMAuthInfo.error("42", "An error occurred", true, "An error occurred", "42");

    // Assert
    assertEquals("42", actualErrorResult.getAppSessionId());
    assertEquals("42", actualErrorResult.getAuthAttemptId());
    assertEquals("An error occurred", actualErrorResult.getError());
    assertEquals("An error occurred", actualErrorResult.getErrorCode());
    assertNull(actualErrorResult.getAuthRole());
    assertNull(actualErrorResult.getRedirectUrl());
    assertNull(actualErrorResult.getSignInLink());
    assertNull(actualErrorResult.getSignOutLink());
    assertNull(actualErrorResult.getSmAccessToken());
    assertNull(actualErrorResult.getSmRefreshToken());
    assertNull(actualErrorResult.getAuthData());
    assertNull(actualErrorResult.getAuthPermissions());
    assertEquals(SMAuthStatus.ERROR, actualErrorResult.getAuthStatus());
    assertFalse(actualErrorResult.isForceSessionsLogout());
    assertTrue(actualErrorResult.isMainAuth());
  }

  /**
   * Test {@link SMAuthInfo#inProgress(String, String, String, Map, boolean, boolean, String)}.
   *
   * <p>Method under test: {@link SMAuthInfo#inProgress(String, String, String, Map, boolean,
   * boolean, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SMAuthInfo SMAuthInfo.inProgress(String, String, String, Map, boolean, boolean, String)"
  })
  public void testInProgress() {
    // Arrange and Act
    SMAuthInfo actualInProgressResult =
        SMAuthInfo.inProgress(
            "42", "Sign In Link", "Sign Out Link", new HashMap<>(), true, true, "42");

    // Assert
    assertEquals("42", actualInProgressResult.getAppSessionId());
    assertEquals("42", actualInProgressResult.getAuthAttemptId());
    assertEquals("Sign In Link", actualInProgressResult.getRedirectUrl());
    assertEquals("Sign In Link", actualInProgressResult.getSignInLink());
    assertEquals("Sign Out Link", actualInProgressResult.getSignOutLink());
    assertNull(actualInProgressResult.getAuthRole());
    assertNull(actualInProgressResult.getError());
    assertNull(actualInProgressResult.getErrorCode());
    assertNull(actualInProgressResult.getSmAccessToken());
    assertNull(actualInProgressResult.getSmRefreshToken());
    assertNull(actualInProgressResult.getAuthPermissions());
    assertEquals(SMAuthStatus.IN_PROGRESS, actualInProgressResult.getAuthStatus());
    assertTrue(actualInProgressResult.getAuthData().isEmpty());
    assertTrue(actualInProgressResult.isForceSessionsLogout());
    assertTrue(actualInProgressResult.isMainAuth());
  }

  /**
   * Test {@link SMAuthInfo#successMainSession(String, String, String, SMAuthPermissions, Map,
   * String, String)}.
   *
   * <p>Method under test: {@link SMAuthInfo#successMainSession(String, String, String,
   * SMAuthPermissions, Map, String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SMAuthInfo SMAuthInfo.successMainSession(String, String, String, SMAuthPermissions, Map, String, String)"
  })
  public void testSuccessMainSession() {
    // Arrange
    SMAuthPermissions smAuthPermissions = new SMAuthPermissions("42", "42", new HashSet<>());

    // Act
    SMAuthInfo actualSuccessMainSessionResult =
        SMAuthInfo.successMainSession(
            "42", "ABC123", "ABC123", smAuthPermissions, new HashMap<>(), "Auth Role", "42");

    // Assert
    assertEquals("42", actualSuccessMainSessionResult.getAppSessionId());
    assertEquals("42", actualSuccessMainSessionResult.getAuthAttemptId());
    assertEquals("ABC123", actualSuccessMainSessionResult.getSmAccessToken());
    assertEquals("ABC123", actualSuccessMainSessionResult.getSmRefreshToken());
    assertEquals("Auth Role", actualSuccessMainSessionResult.getAuthRole());
    assertNull(actualSuccessMainSessionResult.getError());
    assertNull(actualSuccessMainSessionResult.getErrorCode());
    assertNull(actualSuccessMainSessionResult.getRedirectUrl());
    assertNull(actualSuccessMainSessionResult.getSignInLink());
    assertNull(actualSuccessMainSessionResult.getSignOutLink());
    assertEquals(SMAuthStatus.SUCCESS, actualSuccessMainSessionResult.getAuthStatus());
    assertFalse(actualSuccessMainSessionResult.isForceSessionsLogout());
    assertTrue(actualSuccessMainSessionResult.getAuthData().isEmpty());
    assertTrue(actualSuccessMainSessionResult.isMainAuth());
    assertSame(smAuthPermissions, actualSuccessMainSessionResult.getAuthPermissions());
  }

  /**
   * Test {@link SMAuthInfo#successChildSession(String, SMAuthPermissions, Map, String)}.
   *
   * <p>Method under test: {@link SMAuthInfo#successChildSession(String, SMAuthPermissions, Map,
   * String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "SMAuthInfo SMAuthInfo.successChildSession(String, SMAuthPermissions, Map, String)"
  })
  public void testSuccessChildSession() {
    // Arrange
    SMAuthPermissions permissions = new SMAuthPermissions("42", "42", new HashSet<>());

    // Act
    SMAuthInfo actualSuccessChildSessionResult =
        SMAuthInfo.successChildSession("42", permissions, new HashMap<>(), "42");

    // Assert
    assertEquals("42", actualSuccessChildSessionResult.getAppSessionId());
    assertEquals("42", actualSuccessChildSessionResult.getAuthAttemptId());
    assertNull(actualSuccessChildSessionResult.getAuthRole());
    assertNull(actualSuccessChildSessionResult.getError());
    assertNull(actualSuccessChildSessionResult.getErrorCode());
    assertNull(actualSuccessChildSessionResult.getRedirectUrl());
    assertNull(actualSuccessChildSessionResult.getSignInLink());
    assertNull(actualSuccessChildSessionResult.getSignOutLink());
    assertNull(actualSuccessChildSessionResult.getSmAccessToken());
    assertNull(actualSuccessChildSessionResult.getSmRefreshToken());
    assertEquals(SMAuthStatus.SUCCESS, actualSuccessChildSessionResult.getAuthStatus());
    assertFalse(actualSuccessChildSessionResult.isForceSessionsLogout());
    assertFalse(actualSuccessChildSessionResult.isMainAuth());
    assertTrue(actualSuccessChildSessionResult.getAuthData().isEmpty());
    assertSame(permissions, actualSuccessChildSessionResult.getAuthPermissions());
  }
}
