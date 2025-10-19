package org.jkiss.dbeaver.ext.oracle.model.dict;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OracleConnectionTypeDiffblueTest {
  /**
   * Test {@link OracleConnectionType#getTitle()}.
   *
   * <p>Method under test: {@link OracleConnectionType#getTitle()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OracleConnectionType.getTitle()"})
  public void testGetTitle() {
    // Arrange, Act and Assert
    assertEquals("SID", OracleConnectionType.valueOf("SID").getTitle());
  }

  /**
   * Test {@link OracleConnectionType#getTypeForTitle(String)}.
   *
   * <ul>
   *   <li>When {@code Dr}.
   * </ul>
   *
   * <p>Method under test: {@link OracleConnectionType#getTypeForTitle(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleConnectionType OracleConnectionType.getTypeForTitle(String)"})
  public void testGetTypeForTitle_whenDr() {
    // Arrange, Act and Assert
    assertEquals(OracleConnectionType.SID, OracleConnectionType.getTypeForTitle("Dr"));
  }

  /**
   * Test {@link OracleConnectionType#getTypeForTitle(String)}.
   *
   * <ul>
   *   <li>When {@code SID}.
   * </ul>
   *
   * <p>Method under test: {@link OracleConnectionType#getTypeForTitle(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"OracleConnectionType OracleConnectionType.getTypeForTitle(String)"})
  public void testGetTypeForTitle_whenSid() {
    // Arrange, Act and Assert
    assertEquals(OracleConnectionType.SID, OracleConnectionType.getTypeForTitle("SID"));
  }
}
