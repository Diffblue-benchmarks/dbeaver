package org.jkiss.dbeaver.ext.postgresql.model;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class AuthModelPgPassCredentialsDiffblueTest {
  /**
   * Test {@link AuthModelPgPassCredentials#getUserPassword()}.
   *
   * <p>Method under test: {@link AuthModelPgPassCredentials#getUserPassword()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String AuthModelPgPassCredentials.getUserPassword()"})
  public void testGetUserPassword() {
    // Arrange, Act and Assert
    assertNull(new AuthModelPgPassCredentials().getUserPassword());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link AuthModelPgPassCredentials}
   *   <li>{@link AuthModelPgPassCredentials#setParseError(Exception)}
   *   <li>{@link AuthModelPgPassCredentials#getParseError()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void AuthModelPgPassCredentials.<init>()",
    "Exception AuthModelPgPassCredentials.getParseError()",
    "void AuthModelPgPassCredentials.setParseError(Exception)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    AuthModelPgPassCredentials actualAuthModelPgPassCredentials = new AuthModelPgPassCredentials();
    Exception parseError = new Exception();
    actualAuthModelPgPassCredentials.setParseError(parseError);
    Exception actualParseError = actualAuthModelPgPassCredentials.getParseError();

    // Assert
    assertNull(actualAuthModelPgPassCredentials.getUserPassword());
    assertNull(actualAuthModelPgPassCredentials.getUserName());
    assertSame(parseError, actualParseError);
  }
}
