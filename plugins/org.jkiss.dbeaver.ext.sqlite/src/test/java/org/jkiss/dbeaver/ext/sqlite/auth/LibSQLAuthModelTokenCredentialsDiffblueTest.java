package org.jkiss.dbeaver.ext.sqlite.auth;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LibSQLAuthModelTokenCredentialsDiffblueTest {
  /**
   * Test {@link LibSQLAuthModelTokenCredentials#getUserPassword()}.
   *
   * <p>Method under test: {@link LibSQLAuthModelTokenCredentials#getUserPassword()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String LibSQLAuthModelTokenCredentials.getUserPassword()"})
  public void testGetUserPassword() {
    // Arrange, Act and Assert
    assertNull(new LibSQLAuthModelTokenCredentials().getUserPassword());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>default or parameterless constructor of {@link LibSQLAuthModelTokenCredentials}
   *   <li>{@link LibSQLAuthModelTokenCredentials#getUserName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void LibSQLAuthModelTokenCredentials.<init>()",
    "java.lang.String LibSQLAuthModelTokenCredentials.getUserName()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    LibSQLAuthModelTokenCredentials actualLibSQLAuthModelTokenCredentials =
        new LibSQLAuthModelTokenCredentials();

    // Assert
    assertNull(actualLibSQLAuthModelTokenCredentials.getUserName());
    assertNull(actualLibSQLAuthModelTokenCredentials.getUserPassword());
  }
}
