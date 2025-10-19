package org.jkiss.dbeaver.model.impl.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AuthModelDatabaseNativeCredentialsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link AuthModelDatabaseNativeCredentials}
   *   <li>{@link AuthModelDatabaseNativeCredentials#setUserName(String)}
   *   <li>{@link AuthModelDatabaseNativeCredentials#setUserPassword(String)}
   *   <li>{@link AuthModelDatabaseNativeCredentials#getUserName()}
   *   <li>{@link AuthModelDatabaseNativeCredentials#getUserPassword()}
   *   <li>{@link AuthModelDatabaseNativeCredentials#isComplete()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthModelDatabaseNativeCredentials.<init>()",
    "String AuthModelDatabaseNativeCredentials.getUserName()",
    "String AuthModelDatabaseNativeCredentials.getUserPassword()",
    "boolean AuthModelDatabaseNativeCredentials.isComplete()",
    "void AuthModelDatabaseNativeCredentials.setUserName(String)",
    "void AuthModelDatabaseNativeCredentials.setUserPassword(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    AuthModelDatabaseNativeCredentials actualAuthModelDatabaseNativeCredentials =
        new AuthModelDatabaseNativeCredentials();
    actualAuthModelDatabaseNativeCredentials.setUserName("janedoe");
    actualAuthModelDatabaseNativeCredentials.setUserPassword("iloveyou");
    String actualUserName = actualAuthModelDatabaseNativeCredentials.getUserName();
    String actualUserPassword = actualAuthModelDatabaseNativeCredentials.getUserPassword();

    // Assert
    assertEquals("iloveyou", actualUserPassword);
    assertEquals("janedoe", actualUserName);
    assertTrue(actualAuthModelDatabaseNativeCredentials.isComplete());
  }
}
