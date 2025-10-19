package org.jkiss.dbeaver.ext.oracle.model.auth;

import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleAuthOSCredentialsDiffblueTest {
  /**
   * Test {@link OracleAuthOSCredentials#getUserName()}.
   *
   * <p>Method under test: {@link OracleAuthOSCredentials#getUserName()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OracleAuthOSCredentials.getUserName()"})
  public void testGetUserName() {
    // Arrange, Act and Assert
    assertNull(new OracleAuthOSCredentials().getUserName());
  }

  /**
   * Test {@link OracleAuthOSCredentials#getUserPassword()}.
   *
   * <p>Method under test: {@link OracleAuthOSCredentials#getUserPassword()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String OracleAuthOSCredentials.getUserPassword()"})
  public void testGetUserPassword() {
    // Arrange, Act and Assert
    assertNull(new OracleAuthOSCredentials().getUserPassword());
  }

  /**
   * Test new {@link OracleAuthOSCredentials} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link OracleAuthOSCredentials}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OracleAuthOSCredentials.<init>()"})
  public void testNewOracleAuthOSCredentials() {
    // Arrange and Act
    OracleAuthOSCredentials actualOracleAuthOSCredentials = new OracleAuthOSCredentials();

    // Assert
    assertNull(actualOracleAuthOSCredentials.getUserName());
    assertNull(actualOracleAuthOSCredentials.getUserPassword());
  }
}
