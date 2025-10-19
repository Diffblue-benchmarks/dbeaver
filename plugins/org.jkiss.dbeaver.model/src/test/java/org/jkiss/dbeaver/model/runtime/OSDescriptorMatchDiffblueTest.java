package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OSDescriptorMatchDiffblueTest {
  /**
   * Test {@link OSDescriptorMatch#matches(OSDescriptor)}.
   *
   * <ul>
   *   <li>Given {@link OSDescriptorMatch#OSDescriptorMatch(String, String, boolean)} with {@code
   *       Family} and {@code Arch} and exclude is {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OSDescriptorMatch#matches(OSDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OSDescriptorMatch.matches(OSDescriptor)"})
  public void testMatches_givenOSDescriptorMatchWithFamilyAndArchAndExcludeIsFalse() {
    // Arrange
    OSDescriptorMatch osDescriptorMatch = new OSDescriptorMatch("Family", "Arch", false);
    OSDescriptor os = new OSDescriptor("Family", null);

    // Act
    boolean actualMatchesResult = osDescriptorMatch.matches(os);

    // Assert
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link OSDescriptorMatch#matches(OSDescriptor)}.
   *
   * <ul>
   *   <li>Given {@link OSDescriptorMatch#OSDescriptorMatch(String, String, boolean)} with {@code
   *       Family} and {@code Arch} and exclude is {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OSDescriptorMatch#matches(OSDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OSDescriptorMatch.matches(OSDescriptor)"})
  public void testMatches_givenOSDescriptorMatchWithFamilyAndArchAndExcludeIsFalse2() {
    // Arrange
    OSDescriptorMatch osDescriptorMatch = new OSDescriptorMatch("Family", "Arch", false);

    // Act
    boolean actualMatchesResult = osDescriptorMatch.matches(new OSDescriptor("Family", "Arch"));

    // Assert
    assertTrue(actualMatchesResult);
  }

  /**
   * Test {@link OSDescriptorMatch#matches(OSDescriptor)}.
   *
   * <ul>
   *   <li>Given {@link OSDescriptorMatch#OSDescriptorMatch(String, String, boolean)} with {@code
   *       Family} and {@code Arch} and exclude is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OSDescriptorMatch#matches(OSDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OSDescriptorMatch.matches(OSDescriptor)"})
  public void testMatches_givenOSDescriptorMatchWithFamilyAndArchAndExcludeIsTrue() {
    // Arrange
    OSDescriptorMatch osDescriptorMatch = new OSDescriptorMatch("Family", "Arch", true);
    OSDescriptor os = new OSDescriptor("Family", null);

    // Act
    boolean actualMatchesResult = osDescriptorMatch.matches(os);

    // Assert
    assertTrue(actualMatchesResult);
  }

  /**
   * Test {@link OSDescriptorMatch#matches(OSDescriptor)}.
   *
   * <ul>
   *   <li>Given {@link OSDescriptorMatch#OSDescriptorMatch(String, String, boolean)} with {@code
   *       Family} and arch is {@code Family} and exclude is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OSDescriptorMatch#matches(OSDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OSDescriptorMatch.matches(OSDescriptor)"})
  public void testMatches_givenOSDescriptorMatchWithFamilyAndArchIsFamilyAndExcludeIsTrue() {
    // Arrange
    OSDescriptorMatch osDescriptorMatch = new OSDescriptorMatch("Family", "Family", true);

    // Act
    boolean actualMatchesResult = osDescriptorMatch.matches(new OSDescriptor("Family", "Arch"));

    // Assert
    assertTrue(actualMatchesResult);
  }

  /**
   * Test {@link OSDescriptorMatch#matches(OSDescriptor)}.
   *
   * <ul>
   *   <li>Given {@link OSDescriptorMatch#OSDescriptorMatch(String, String, boolean)} with {@code
   *       Family} and arch is {@code null} and exclude is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OSDescriptorMatch#matches(OSDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OSDescriptorMatch.matches(OSDescriptor)"})
  public void testMatches_givenOSDescriptorMatchWithFamilyAndArchIsNullAndExcludeIsTrue() {
    // Arrange
    OSDescriptorMatch osDescriptorMatch = new OSDescriptorMatch("Family", null, true);
    OSDescriptor os = new OSDescriptor("Family", null);

    // Act
    boolean actualMatchesResult = osDescriptorMatch.matches(os);

    // Assert
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link OSDescriptorMatch#matches(OSDescriptor)}.
   *
   * <ul>
   *   <li>Given {@link OSDescriptorMatch#OSDescriptorMatch(String, String, boolean)} with family is
   *       {@code Arch} and {@code Arch} and exclude is {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OSDescriptorMatch#matches(OSDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OSDescriptorMatch.matches(OSDescriptor)"})
  public void testMatches_givenOSDescriptorMatchWithFamilyIsArchAndArchAndExcludeIsTrue() {
    // Arrange
    OSDescriptorMatch osDescriptorMatch = new OSDescriptorMatch("Arch", "Arch", true);

    // Act
    boolean actualMatchesResult = osDescriptorMatch.matches(new OSDescriptor("Family", "Arch"));

    // Assert
    assertTrue(actualMatchesResult);
  }

  /**
   * Test {@link OSDescriptorMatch#matches(OSDescriptor)}.
   *
   * <ul>
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OSDescriptorMatch#matches(OSDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OSDescriptorMatch.matches(OSDescriptor)"})
  public void testMatches_thenReturnFalse() {
    // Arrange
    OSDescriptorMatch osDescriptorMatch = new OSDescriptorMatch("Family", "Arch", true);

    // Act
    boolean actualMatchesResult = osDescriptorMatch.matches(new OSDescriptor("Family", "Arch"));

    // Assert
    assertFalse(actualMatchesResult);
  }
}
