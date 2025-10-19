package org.jkiss.dbeaver.model.connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPAuthInfoDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBPAuthInfo#DBPAuthInfo()}
   *   <li>{@link DBPAuthInfo#setSavePassword(boolean)}
   *   <li>{@link DBPAuthInfo#setUserName(String)}
   *   <li>{@link DBPAuthInfo#setUserPassword(String)}
   *   <li>{@link DBPAuthInfo#getUserName()}
   *   <li>{@link DBPAuthInfo#getUserPassword()}
   *   <li>{@link DBPAuthInfo#isSavePassword()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPAuthInfo.<init>()",
    "void DBPAuthInfo.<init>(String, String, boolean)",
    "String DBPAuthInfo.getUserName()",
    "String DBPAuthInfo.getUserPassword()",
    "boolean DBPAuthInfo.isSavePassword()",
    "void DBPAuthInfo.setSavePassword(boolean)",
    "void DBPAuthInfo.setUserName(String)",
    "void DBPAuthInfo.setUserPassword(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBPAuthInfo actualDbpAuthInfo = new DBPAuthInfo();
    actualDbpAuthInfo.setSavePassword(true);
    actualDbpAuthInfo.setUserName("janedoe");
    actualDbpAuthInfo.setUserPassword("iloveyou");
    String actualUserName = actualDbpAuthInfo.getUserName();
    String actualUserPassword = actualDbpAuthInfo.getUserPassword();

    // Assert
    assertEquals("iloveyou", actualUserPassword);
    assertEquals("janedoe", actualUserName);
    assertTrue(actualDbpAuthInfo.isSavePassword());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code janedoe}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBPAuthInfo#DBPAuthInfo(String, String, boolean)}
   *   <li>{@link DBPAuthInfo#setSavePassword(boolean)}
   *   <li>{@link DBPAuthInfo#setUserName(String)}
   *   <li>{@link DBPAuthInfo#setUserPassword(String)}
   *   <li>{@link DBPAuthInfo#getUserName()}
   *   <li>{@link DBPAuthInfo#getUserPassword()}
   *   <li>{@link DBPAuthInfo#isSavePassword()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPAuthInfo.<init>()",
    "void DBPAuthInfo.<init>(String, String, boolean)",
    "String DBPAuthInfo.getUserName()",
    "String DBPAuthInfo.getUserPassword()",
    "boolean DBPAuthInfo.isSavePassword()",
    "void DBPAuthInfo.setSavePassword(boolean)",
    "void DBPAuthInfo.setUserName(String)",
    "void DBPAuthInfo.setUserPassword(String)"
  })
  public void testGettersAndSetters_whenJanedoe() {
    // Arrange and Act
    DBPAuthInfo actualDbpAuthInfo = new DBPAuthInfo("janedoe", "iloveyou", true);
    actualDbpAuthInfo.setSavePassword(true);
    actualDbpAuthInfo.setUserName("janedoe");
    actualDbpAuthInfo.setUserPassword("iloveyou");
    String actualUserName = actualDbpAuthInfo.getUserName();
    String actualUserPassword = actualDbpAuthInfo.getUserPassword();

    // Assert
    assertEquals("iloveyou", actualUserPassword);
    assertEquals("janedoe", actualUserName);
    assertTrue(actualDbpAuthInfo.isSavePassword());
  }
}
