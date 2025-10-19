package org.jkiss.dbeaver.ext.snowflake.model.auth;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AuthModelSnowflakeCredentialsDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link AuthModelSnowflakeCredentials}
   *   <li>{@link AuthModelSnowflakeCredentials#setRole(String)}
   *   <li>{@link AuthModelSnowflakeCredentials#getRole()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthModelSnowflakeCredentials.<init>()",
    "String AuthModelSnowflakeCredentials.getRole()",
    "void AuthModelSnowflakeCredentials.setRole(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    AuthModelSnowflakeCredentials actualAuthModelSnowflakeCredentials =
        new AuthModelSnowflakeCredentials();
    actualAuthModelSnowflakeCredentials.setRole("Role");

    // Assert
    assertEquals("Role", actualAuthModelSnowflakeCredentials.getRole());
    assertNull(actualAuthModelSnowflakeCredentials.getUserName());
    assertNull(actualAuthModelSnowflakeCredentials.getUserPassword());
  }
}
