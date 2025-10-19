package org.jkiss.dbeaver.model.access;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBAPasswordChangeInfoDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBAPasswordChangeInfo#DBAPasswordChangeInfo()}
   *   <li>{@link DBAPasswordChangeInfo#setNewPassword(String)}
   *   <li>{@link DBAPasswordChangeInfo#setOldPassword(String)}
   *   <li>{@link DBAPasswordChangeInfo#setUserName(String)}
   *   <li>{@link DBAPasswordChangeInfo#getNewPassword()}
   *   <li>{@link DBAPasswordChangeInfo#getOldPassword()}
   *   <li>{@link DBAPasswordChangeInfo#getUserName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBAPasswordChangeInfo.<init>()",
    "void DBAPasswordChangeInfo.<init>(String, String)",
    "String DBAPasswordChangeInfo.getNewPassword()",
    "String DBAPasswordChangeInfo.getOldPassword()",
    "String DBAPasswordChangeInfo.getUserName()",
    "void DBAPasswordChangeInfo.setNewPassword(String)",
    "void DBAPasswordChangeInfo.setOldPassword(String)",
    "void DBAPasswordChangeInfo.setUserName(String)"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    DBAPasswordChangeInfo actualDbaPasswordChangeInfo = new DBAPasswordChangeInfo();
    actualDbaPasswordChangeInfo.setNewPassword("iloveyou");
    actualDbaPasswordChangeInfo.setOldPassword("iloveyou");
    actualDbaPasswordChangeInfo.setUserName("janedoe");
    String actualNewPassword = actualDbaPasswordChangeInfo.getNewPassword();
    String actualOldPassword = actualDbaPasswordChangeInfo.getOldPassword();

    // Assert
    assertEquals("iloveyou", actualNewPassword);
    assertEquals("iloveyou", actualOldPassword);
    assertEquals("janedoe", actualDbaPasswordChangeInfo.getUserName());
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
   *   <li>{@link DBAPasswordChangeInfo#DBAPasswordChangeInfo(String, String)}
   *   <li>{@link DBAPasswordChangeInfo#setNewPassword(String)}
   *   <li>{@link DBAPasswordChangeInfo#setOldPassword(String)}
   *   <li>{@link DBAPasswordChangeInfo#setUserName(String)}
   *   <li>{@link DBAPasswordChangeInfo#getNewPassword()}
   *   <li>{@link DBAPasswordChangeInfo#getOldPassword()}
   *   <li>{@link DBAPasswordChangeInfo#getUserName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBAPasswordChangeInfo.<init>()",
    "void DBAPasswordChangeInfo.<init>(String, String)",
    "String DBAPasswordChangeInfo.getNewPassword()",
    "String DBAPasswordChangeInfo.getOldPassword()",
    "String DBAPasswordChangeInfo.getUserName()",
    "void DBAPasswordChangeInfo.setNewPassword(String)",
    "void DBAPasswordChangeInfo.setOldPassword(String)",
    "void DBAPasswordChangeInfo.setUserName(String)"
  })
  public void testGettersAndSetters_whenJanedoe() {
    // Arrange and Act
    DBAPasswordChangeInfo actualDbaPasswordChangeInfo =
        new DBAPasswordChangeInfo("janedoe", "iloveyou");
    actualDbaPasswordChangeInfo.setNewPassword("iloveyou");
    actualDbaPasswordChangeInfo.setOldPassword("iloveyou");
    actualDbaPasswordChangeInfo.setUserName("janedoe");
    String actualNewPassword = actualDbaPasswordChangeInfo.getNewPassword();
    String actualOldPassword = actualDbaPasswordChangeInfo.getOldPassword();

    // Assert
    assertEquals("iloveyou", actualNewPassword);
    assertEquals("iloveyou", actualOldPassword);
    assertEquals("janedoe", actualDbaPasswordChangeInfo.getUserName());
  }
}
