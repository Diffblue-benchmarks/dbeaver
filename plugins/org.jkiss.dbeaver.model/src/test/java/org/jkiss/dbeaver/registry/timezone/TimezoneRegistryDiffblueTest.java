package org.jkiss.dbeaver.registry.timezone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TimezoneRegistryDiffblueTest {
  /**
   * Test {@link TimezoneRegistry#getTimezoneNames()}.
   *
   * <p>Method under test: {@link TimezoneRegistry#getTimezoneNames()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Collection TimezoneRegistry.getTimezoneNames()"})
  public void testGetTimezoneNames() {
    // Arrange and Act
    Collection<String> actualTimezoneNames = TimezoneRegistry.getTimezoneNames();

    // Assert
    assertTrue(actualTimezoneNames instanceof List);
    assertEquals(603, actualTimezoneNames.size());
    assertEquals("Africa/Abidjan (UTCZ)", ((List<String>) actualTimezoneNames).get(0));
    assertEquals("Africa/Accra (UTCZ)", ((List<String>) actualTimezoneNames).get(1));
    assertEquals("Africa/Addis_Ababa (UTC+03:00)", ((List<String>) actualTimezoneNames).get(2));
    assertEquals("Africa/Asmara (UTC+03:00)", ((List<String>) actualTimezoneNames).get(4));
    assertEquals("Africa/Asmera (UTC+03:00)", ((List<String>) actualTimezoneNames).get(5));
    assertEquals("US/Samoa (UTC-11:00)", ((List<String>) actualTimezoneNames).get(597));
    assertEquals("UTC (UTCZ)", ((List<String>) actualTimezoneNames).get(598));
    assertEquals("Universal (UTCZ)", ((List<String>) actualTimezoneNames).get(599));
    assertEquals("W-SU (UTC+03:00)", ((List<String>) actualTimezoneNames).get(600));
    assertEquals("WET (UTC+01:00)", ((List<String>) actualTimezoneNames).get(601));
    assertEquals("Zulu (UTCZ)", ((List<String>) actualTimezoneNames).get(602));
  }

  /**
   * Test {@link TimezoneRegistry#getGMTString(String)}.
   *
   * <ul>
   *   <li>When {@link TimezoneRegistry#GMT_TIMEZONE}.
   *   <li>Then return {@code GMT (UTCZ)}.
   * </ul>
   *
   * <p>Method under test: {@link TimezoneRegistry#getGMTString(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TimezoneRegistry.getGMTString(String)"})
  public void testGetGMTString_whenGmt_timezone_thenReturnGmtUtcz() {
    // Arrange, Act and Assert
    assertEquals("GMT (UTCZ)", TimezoneRegistry.getGMTString(TimezoneRegistry.GMT_TIMEZONE));
  }

  /**
   * Test {@link TimezoneRegistry#extractTimezoneId(String)}.
   *
   * <ul>
   *   <li>When {@code UTC}.
   *   <li>Then return {@code UTC}.
   * </ul>
   *
   * <p>Method under test: {@link TimezoneRegistry#extractTimezoneId(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String TimezoneRegistry.extractTimezoneId(String)"})
  public void testExtractTimezoneId_whenUtc_thenReturnUtc() {
    // Arrange, Act and Assert
    assertEquals("UTC", TimezoneRegistry.extractTimezoneId("UTC"));
  }
}
