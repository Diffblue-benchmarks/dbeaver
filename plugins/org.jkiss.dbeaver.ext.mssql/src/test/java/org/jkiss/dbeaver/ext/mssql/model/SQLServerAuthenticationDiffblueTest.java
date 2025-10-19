package org.jkiss.dbeaver.ext.mssql.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLServerAuthenticationDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLServerAuthentication#getDescription()}
   *   <li>{@link SQLServerAuthentication#getReplacedByAuthModelId()}
   *   <li>{@link SQLServerAuthentication#getTitle()}
   *   <li>{@link SQLServerAuthentication#isAllowsPassword()}
   *   <li>{@link SQLServerAuthentication#isAllowsUserName()}
   *   <li>{@link SQLServerAuthentication#isSupportsJTDS()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String SQLServerAuthentication.getDescription()",
    "String SQLServerAuthentication.getReplacedByAuthModelId()",
    "String SQLServerAuthentication.getTitle()",
    "boolean SQLServerAuthentication.isAllowsPassword()",
    "boolean SQLServerAuthentication.isAllowsUserName()",
    "boolean SQLServerAuthentication.isSupportsJTDS()"
  })
  public void testGettersAndSetters() {
    // Arrange
    SQLServerAuthentication valueOfResult = SQLServerAuthentication.valueOf("SQL_SERVER_PASSWORD");

    // Act
    String actualDescription = valueOfResult.getDescription();
    String actualReplacedByAuthModelId = valueOfResult.getReplacedByAuthModelId();
    String actualTitle = valueOfResult.getTitle();
    boolean actualIsAllowsPasswordResult = valueOfResult.isAllowsPassword();
    boolean actualIsAllowsUserNameResult = valueOfResult.isAllowsUserName();

    // Assert
    assertEquals("SQL Server Authentication", actualTitle);
    assertEquals("SQL Server password based authentication", actualDescription);
    assertEquals("sqlserver_database", actualReplacedByAuthModelId);
    assertTrue(actualIsAllowsPasswordResult);
    assertTrue(actualIsAllowsUserNameResult);
    assertTrue(valueOfResult.isSupportsJTDS());
  }
}
