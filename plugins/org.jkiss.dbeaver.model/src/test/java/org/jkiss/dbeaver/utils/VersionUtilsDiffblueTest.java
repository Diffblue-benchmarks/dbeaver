package org.jkiss.dbeaver.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class VersionUtilsDiffblueTest {
  /**
   * Test {@link VersionUtils#isBetaVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#isBetaVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionUtils.isBetaVersion(String)"})
  public void testIsBetaVersion_when102_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(VersionUtils.isBetaVersion("1.0.2"));
  }

  /**
   * Test {@link VersionUtils#isBetaVersion(String)}.
   *
   * <ul>
   *   <li>When {@code alpha}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#isBetaVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionUtils.isBetaVersion(String)"})
  public void testIsBetaVersion_whenAlpha_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(VersionUtils.isBetaVersion("alpha"));
  }

  /**
   * Test {@link VersionUtils#isBetaVersion(String)}.
   *
   * <ul>
   *   <li>When {@code beta}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#isBetaVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionUtils.isBetaVersion(String)"})
  public void testIsBetaVersion_whenBeta_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(VersionUtils.isBetaVersion("beta"));
  }

  /**
   * Test {@link VersionUtils#findLatestVersion(Collection)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#findLatestVersion(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String VersionUtils.findLatestVersion(Collection)"})
  public void testFindLatestVersion_given42_whenArrayListAdd42_thenReturnFoo() {
    // Arrange
    ArrayList<String> allVersions = new ArrayList<>();
    allVersions.add("42");
    allVersions.add("foo");

    // Act and Assert
    assertEquals("foo", VersionUtils.findLatestVersion(allVersions));
  }

  /**
   * Test {@link VersionUtils#findLatestVersion(Collection)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#findLatestVersion(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String VersionUtils.findLatestVersion(Collection)"})
  public void testFindLatestVersion_given42_whenArrayListAdd42_thenReturnFoo2() {
    // Arrange
    ArrayList<String> allVersions = new ArrayList<>();
    allVersions.add("foo");
    allVersions.add("42");
    allVersions.add("foo");

    // Act and Assert
    assertEquals("foo", VersionUtils.findLatestVersion(allVersions));
  }

  /**
   * Test {@link VersionUtils#findLatestVersion(Collection)}.
   *
   * <ul>
   *   <li>Given {@code 42}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code 42}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#findLatestVersion(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String VersionUtils.findLatestVersion(Collection)"})
  public void testFindLatestVersion_given42_whenArrayListAdd42_thenReturnFoo3() {
    // Arrange
    ArrayList<String> allVersions = new ArrayList<>();
    allVersions.add("42");
    allVersions.add("42");
    allVersions.add("foo");

    // Act and Assert
    assertEquals("foo", VersionUtils.findLatestVersion(allVersions));
  }

  /**
   * Test {@link VersionUtils#findLatestVersion(Collection)}.
   *
   * <ul>
   *   <li>Given {@code 1.0.2}.
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@code 1.0.2}.
   *   <li>Then return {@code 1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#findLatestVersion(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String VersionUtils.findLatestVersion(Collection)"})
  public void testFindLatestVersion_given102_whenLinkedHashSetAdd102_thenReturn102() {
    // Arrange
    LinkedHashSet<String> allVersions = new LinkedHashSet<>();
    allVersions.add("1.0.2");

    // Act and Assert
    assertEquals("1.0.2", VersionUtils.findLatestVersion(allVersions));
  }

  /**
   * Test {@link VersionUtils#findLatestVersion(Collection)}.
   *
   * <ul>
   *   <li>Given {@code alpha}.
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@code alpha}.
   *   <li>Then return {@code alpha}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#findLatestVersion(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String VersionUtils.findLatestVersion(Collection)"})
  public void testFindLatestVersion_givenAlpha_whenLinkedHashSetAddAlpha_thenReturnAlpha() {
    // Arrange
    LinkedHashSet<String> allVersions = new LinkedHashSet<>();
    allVersions.add("alpha");

    // Act and Assert
    assertEquals("alpha", VersionUtils.findLatestVersion(allVersions));
  }

  /**
   * Test {@link VersionUtils#findLatestVersion(Collection)}.
   *
   * <ul>
   *   <li>Given {@code beta}.
   *   <li>When {@link LinkedHashSet#LinkedHashSet()} add {@code beta}.
   *   <li>Then return {@code beta}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#findLatestVersion(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String VersionUtils.findLatestVersion(Collection)"})
  public void testFindLatestVersion_givenBeta_whenLinkedHashSetAddBeta_thenReturnBeta() {
    // Arrange
    LinkedHashSet<String> allVersions = new LinkedHashSet<>();
    allVersions.add("beta");

    // Act and Assert
    assertEquals("beta", VersionUtils.findLatestVersion(allVersions));
  }

  /**
   * Test {@link VersionUtils#findLatestVersion(Collection)}.
   *
   * <ul>
   *   <li>Given {@code .-_}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code .-_}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#findLatestVersion(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String VersionUtils.findLatestVersion(Collection)"})
  public void testFindLatestVersion_givenDotDashUnderscore_whenArrayListAddDotDashUnderscore() {
    // Arrange
    ArrayList<String> allVersions = new ArrayList<>();
    allVersions.add(".-_");
    allVersions.add("42");
    allVersions.add("foo");

    // Act and Assert
    assertEquals("foo", VersionUtils.findLatestVersion(allVersions));
  }

  /**
   * Test {@link VersionUtils#findLatestVersion(Collection)}.
   *
   * <ul>
   *   <li>Given {@code .-_}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code .-_}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#findLatestVersion(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String VersionUtils.findLatestVersion(Collection)"})
  public void testFindLatestVersion_givenDotDashUnderscore_whenArrayListAddDotDashUnderscore2() {
    // Arrange
    ArrayList<String> allVersions = new ArrayList<>();
    allVersions.add("foo");
    allVersions.add(".-_");
    allVersions.add("42");
    allVersions.add("foo");

    // Act and Assert
    assertEquals("foo", VersionUtils.findLatestVersion(allVersions));
  }

  /**
   * Test {@link VersionUtils#findLatestVersion(Collection)}.
   *
   * <ul>
   *   <li>Given {@code foo}.
   *   <li>When {@link ArrayList#ArrayList()} add {@code foo}.
   *   <li>Then return {@code foo}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#findLatestVersion(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String VersionUtils.findLatestVersion(Collection)"})
  public void testFindLatestVersion_givenFoo_whenArrayListAddFoo_thenReturnFoo() {
    // Arrange
    ArrayList<String> allVersions = new ArrayList<>();
    allVersions.add("foo");
    allVersions.add("foo");

    // Act and Assert
    assertEquals("foo", VersionUtils.findLatestVersion(allVersions));
  }

  /**
   * Test {@link VersionUtils#findLatestVersion(Collection)}.
   *
   * <ul>
   *   <li>When {@link ArrayList#ArrayList()}.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#findLatestVersion(Collection)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String VersionUtils.findLatestVersion(Collection)"})
  public void testFindLatestVersion_whenArrayList_thenReturnNull() {
    // Arrange, Act and Assert
    assertNull(VersionUtils.findLatestVersion(new ArrayList<>()));
  }

  /**
   * Test {@link VersionUtils#isVersionLessThan(String, String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#isVersionLessThan(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionUtils.isVersionLessThan(String, String)"})
  public void testIsVersionLessThan_when42_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(VersionUtils.isVersionLessThan("42", "42"));
  }

  /**
   * Test {@link VersionUtils#isVersionLessThan(String, String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#isVersionLessThan(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionUtils.isVersionLessThan(String, String)"})
  public void testIsVersionLessThan_when42_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(VersionUtils.isVersionLessThan("42", "V2"));
  }

  /**
   * Test {@link VersionUtils#isVersionLessThan(String, String)}.
   *
   * <ul>
   *   <li>When {@code alpha}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#isVersionLessThan(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionUtils.isVersionLessThan(String, String)"})
  public void testIsVersionLessThan_whenAlpha_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(VersionUtils.isVersionLessThan("alpha", "V2"));
  }

  /**
   * Test {@link VersionUtils#isVersionLessThan(String, String)}.
   *
   * <ul>
   *   <li>When {@code alpha}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#isVersionLessThan(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionUtils.isVersionLessThan(String, String)"})
  public void testIsVersionLessThan_whenAlpha_thenReturnFalse2() {
    // Arrange, Act and Assert
    assertFalse(VersionUtils.isVersionLessThan("alpha", "alpha"));
  }

  /**
   * Test {@link VersionUtils#isVersionLessThan(String, String)}.
   *
   * <ul>
   *   <li>When {@code .-_}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#isVersionLessThan(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionUtils.isVersionLessThan(String, String)"})
  public void testIsVersionLessThan_whenDotDashUnderscore_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(VersionUtils.isVersionLessThan(".-_", ".-_"));
  }

  /**
   * Test {@link VersionUtils#isVersionLessThan(String, String)}.
   *
   * <ul>
   *   <li>When {@code .-_}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#isVersionLessThan(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionUtils.isVersionLessThan(String, String)"})
  public void testIsVersionLessThan_whenDotDashUnderscore_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(VersionUtils.isVersionLessThan(".-_", "V2"));
  }

  /**
   * Test {@link VersionUtils#isVersionLessThan(String, String)}.
   *
   * <ul>
   *   <li>When {@code V1}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#isVersionLessThan(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionUtils.isVersionLessThan(String, String)"})
  public void testIsVersionLessThan_whenV1_thenReturnFalse() {
    // Arrange, Act and Assert
    assertFalse(VersionUtils.isVersionLessThan("V1", ".-_"));
  }

  /**
   * Test {@link VersionUtils#isVersionLessThan(String, String)}.
   *
   * <ul>
   *   <li>When {@code V1}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#isVersionLessThan(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean VersionUtils.isVersionLessThan(String, String)"})
  public void testIsVersionLessThan_whenV1_thenReturnTrue() {
    // Arrange, Act and Assert
    assertTrue(VersionUtils.isVersionLessThan("V1", "V2"));
  }

  /**
   * Test {@link VersionUtils#compareVersions(String, String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return minus thirty-four.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#compareVersions(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int VersionUtils.compareVersions(String, String)"})
  public void testCompareVersions_when42_thenReturnMinusThirtyFour() {
    // Arrange, Act and Assert
    assertEquals(-34, VersionUtils.compareVersions("42", "V2"));
  }

  /**
   * Test {@link VersionUtils#compareVersions(String, String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#compareVersions(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int VersionUtils.compareVersions(String, String)"})
  public void testCompareVersions_when42_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, VersionUtils.compareVersions("42", "42"));
  }

  /**
   * Test {@link VersionUtils#compareVersions(String, String)}.
   *
   * <ul>
   *   <li>When {@code alpha}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#compareVersions(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int VersionUtils.compareVersions(String, String)"})
  public void testCompareVersions_whenAlpha_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, VersionUtils.compareVersions("alpha", "alpha"));
  }

  /**
   * Test {@link VersionUtils#compareVersions(String, String)}.
   *
   * <ul>
   *   <li>When {@code .-_}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#compareVersions(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int VersionUtils.compareVersions(String, String)"})
  public void testCompareVersions_whenDotDashUnderscore_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, VersionUtils.compareVersions(".-_", "V2"));
  }

  /**
   * Test {@link VersionUtils#compareVersions(String, String)}.
   *
   * <ul>
   *   <li>When {@code .-_}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#compareVersions(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int VersionUtils.compareVersions(String, String)"})
  public void testCompareVersions_whenDotDashUnderscore_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, VersionUtils.compareVersions(".-_", ".-_"));
  }

  /**
   * Test {@link VersionUtils#compareVersions(String, String)}.
   *
   * <ul>
   *   <li>When {@code V1}.
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#compareVersions(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int VersionUtils.compareVersions(String, String)"})
  public void testCompareVersions_whenV1_thenReturnMinusOne() {
    // Arrange, Act and Assert
    assertEquals(-1, VersionUtils.compareVersions("V1", "V2"));
  }

  /**
   * Test {@link VersionUtils#compareVersions(String, String)}.
   *
   * <ul>
   *   <li>When {@code V1}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link VersionUtils#compareVersions(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int VersionUtils.compareVersions(String, String)"})
  public void testCompareVersions_whenV1_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, VersionUtils.compareVersions("V1", ".-_"));
  }
}
