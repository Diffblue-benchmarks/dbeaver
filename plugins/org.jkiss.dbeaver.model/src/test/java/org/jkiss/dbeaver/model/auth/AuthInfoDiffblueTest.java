package org.jkiss.dbeaver.model.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AuthInfoDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link AuthInfo#AuthInfo(String, Map)}
   *   <li>{@link AuthInfo#setUserCredentials(Map)}
   *   <li>{@link AuthInfo#getAuthProvider()}
   *   <li>{@link AuthInfo#getUserCredentials()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthInfo.<init>(String, Map)",
    "String AuthInfo.getAuthProvider()",
    "Map AuthInfo.getUserCredentials()",
    "void AuthInfo.setUserCredentials(Map)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    AuthInfo actualAuthInfo = new AuthInfo("Auth Provider", new HashMap<>());
    HashMap<String, Object> userCredentials = new HashMap<>();
    actualAuthInfo.setUserCredentials(userCredentials);
    String actualAuthProvider = actualAuthInfo.getAuthProvider();
    Map<String, Object> actualUserCredentials = actualAuthInfo.getUserCredentials();

    // Assert
    assertEquals("Auth Provider", actualAuthProvider);
    assertTrue(actualUserCredentials.isEmpty());
    assertSame(userCredentials, actualUserCredentials);
  }
}
