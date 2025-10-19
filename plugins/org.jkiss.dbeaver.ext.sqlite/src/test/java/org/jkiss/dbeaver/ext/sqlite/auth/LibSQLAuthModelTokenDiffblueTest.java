package org.jkiss.dbeaver.ext.sqlite.auth;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LibSQLAuthModelTokenDiffblueTest {
  /**
   * Test {@link LibSQLAuthModelToken#createCredentials()}.
   *
   * <p>Method under test: {@link LibSQLAuthModelToken#createCredentials()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"LibSQLAuthModelTokenCredentials LibSQLAuthModelToken.createCredentials()"})
  public void testCreateCredentials() {
    // Arrange and Act
    LibSQLAuthModelTokenCredentials actualCreateCredentialsResult =
        new LibSQLAuthModelToken().createCredentials();

    // Assert
    assertNull(actualCreateCredentialsResult.getUserName());
    assertNull(actualCreateCredentialsResult.getUserPassword());
    assertTrue(actualCreateCredentialsResult.isComplete());
  }

  /**
   * Test new {@link LibSQLAuthModelToken} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link LibSQLAuthModelToken}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LibSQLAuthModelToken.<init>()"})
  public void testNewLibSQLAuthModelToken() {
    // Arrange and Act
    LibSQLAuthModelToken actualLibSQLAuthModelToken = new LibSQLAuthModelToken();

    // Assert
    assertTrue(actualLibSQLAuthModelToken.isUserNameApplicable());
    assertTrue(actualLibSQLAuthModelToken.isUserPasswordApplicable());
  }
}
