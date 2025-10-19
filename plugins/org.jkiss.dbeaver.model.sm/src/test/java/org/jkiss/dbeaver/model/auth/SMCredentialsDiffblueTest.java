package org.jkiss.dbeaver.model.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SMCredentialsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SMCredentials#SMCredentials(String, String, String, Set)}
   *   <li>{@link SMCredentials#getPermissions()}
   *   <li>{@link SMCredentials#getSmAccessToken()}
   *   <li>{@link SMCredentials#getSmSessionId()}
   *   <li>{@link SMCredentials#getUserId()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SMCredentials.<init>(String, String, String, Set)",
    "Set SMCredentials.getPermissions()",
    "String SMCredentials.getSmAccessToken()",
    "String SMCredentials.getSmSessionId()",
    "String SMCredentials.getUserId()"
  })
  public void testGettersAndSetters() {
    // Arrange
    HashSet<String> permissions = new HashSet<>();

    // Act
    SMCredentials actualSmCredentials = new SMCredentials("ABC123", "42", "42", permissions);
    Set<String> actualPermissions = actualSmCredentials.getPermissions();
    String actualSmAccessToken = actualSmCredentials.getSmAccessToken();
    String actualSmSessionId = actualSmCredentials.getSmSessionId();

    // Assert
    assertEquals("42", actualSmSessionId);
    assertEquals("42", actualSmCredentials.getUserId());
    assertEquals("ABC123", actualSmAccessToken);
    assertTrue(actualPermissions.isEmpty());
    assertSame(permissions, actualPermissions);
  }

  /**
   * Test {@link SMCredentials#hasPermission(String)}.
   *
   * <p>Method under test: {@link SMCredentials#hasPermission(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean SMCredentials.hasPermission(String)"})
  public void testHasPermission() {
    // Arrange
    SMCredentials smCredentials = new SMCredentials("ABC123", "42", "42", new HashSet<>());

    // Act and Assert
    assertFalse(smCredentials.hasPermission("Permission"));
  }
}
