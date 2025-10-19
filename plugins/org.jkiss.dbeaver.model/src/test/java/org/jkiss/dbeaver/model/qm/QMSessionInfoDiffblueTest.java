package org.jkiss.dbeaver.model.qm;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class QMSessionInfoDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link QMSessionInfo#QMSessionInfo(String, String, String)}
   *   <li>{@link QMSessionInfo#getUserDomain()}
   *   <li>{@link QMSessionInfo#getUserIp()}
   *   <li>{@link QMSessionInfo#getUserName()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void QMSessionInfo.<init>(String, String, String)",
    "String QMSessionInfo.getUserDomain()",
    "String QMSessionInfo.getUserIp()",
    "String QMSessionInfo.getUserName()"
  })
  public void testGettersAndSetters() {
    // Arrange and Act
    QMSessionInfo actualQmSessionInfo = new QMSessionInfo("janedoe", "User Domain", "User Ip");
    String actualUserDomain = actualQmSessionInfo.getUserDomain();
    String actualUserIp = actualQmSessionInfo.getUserIp();

    // Assert
    assertEquals("User Domain", actualUserDomain);
    assertEquals("User Ip", actualUserIp);
    assertEquals("janedoe", actualQmSessionInfo.getUserName());
  }
}
