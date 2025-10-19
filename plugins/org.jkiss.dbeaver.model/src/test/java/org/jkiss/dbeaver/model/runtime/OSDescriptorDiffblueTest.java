package org.jkiss.dbeaver.model.runtime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OSDescriptorDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link OSDescriptor#OSDescriptor(String, String)}
   *   <li>{@link OSDescriptor#toString()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OSDescriptor.<init>(String, String)", "String OSDescriptor.toString()"})
  public void testGettersAndSetters() {
    // Arrange, Act and Assert
    assertEquals("Family Arch", new OSDescriptor("Family", "Arch").toString());
  }

  /**
   * Test {@link OSDescriptor#matches(OSDescriptor)}.
   *
   * <ul>
   *   <li>Given {@link OSDescriptor#OSDescriptor(String, String)} with {@code Family} and arch is
   *       {@code Family}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OSDescriptor#matches(OSDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OSDescriptor.matches(OSDescriptor)"})
  public void testMatches_givenOSDescriptorWithFamilyAndArchIsFamily_thenReturnFalse() {
    // Arrange
    OSDescriptor osDescriptor = new OSDescriptor("Family", "Family");

    // Act
    boolean actualMatchesResult = osDescriptor.matches(new OSDescriptor("Family", "Arch"));

    // Assert
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link OSDescriptor#matches(OSDescriptor)}.
   *
   * <ul>
   *   <li>Given {@link OSDescriptor#OSDescriptor(String, String)} with {@code Family} and arch is
   *       {@code null}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OSDescriptor#matches(OSDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OSDescriptor.matches(OSDescriptor)"})
  public void testMatches_givenOSDescriptorWithFamilyAndArchIsNull_thenReturnTrue() {
    // Arrange
    OSDescriptor osDescriptor = new OSDescriptor("Family", null);
    OSDescriptor os = new OSDescriptor("Family", null);

    // Act
    boolean actualMatchesResult = osDescriptor.matches(os);

    // Assert
    assertTrue(actualMatchesResult);
  }

  /**
   * Test {@link OSDescriptor#matches(OSDescriptor)}.
   *
   * <ul>
   *   <li>Given {@link OSDescriptor#OSDescriptor(String, String)} with {@code Family} and {@code
   *       Arch}.
   *   <li>Then return {@code true}.
   * </ul>
   *
   * <p>Method under test: {@link OSDescriptor#matches(OSDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OSDescriptor.matches(OSDescriptor)"})
  public void testMatches_givenOSDescriptorWithFamilyAndArch_thenReturnTrue() {
    // Arrange
    OSDescriptor osDescriptor = new OSDescriptor("Family", "Arch");

    // Act
    boolean actualMatchesResult = osDescriptor.matches(new OSDescriptor("Family", "Arch"));

    // Assert
    assertTrue(actualMatchesResult);
  }

  /**
   * Test {@link OSDescriptor#matches(OSDescriptor)}.
   *
   * <ul>
   *   <li>Given {@link OSDescriptor#OSDescriptor(String, String)} with family is {@code Arch} and
   *       {@code Arch}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OSDescriptor#matches(OSDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OSDescriptor.matches(OSDescriptor)"})
  public void testMatches_givenOSDescriptorWithFamilyIsArchAndArch_thenReturnFalse() {
    // Arrange
    OSDescriptor osDescriptor = new OSDescriptor("Arch", "Arch");

    // Act
    boolean actualMatchesResult = osDescriptor.matches(new OSDescriptor("Family", "Arch"));

    // Assert
    assertFalse(actualMatchesResult);
  }

  /**
   * Test {@link OSDescriptor#matches(OSDescriptor)}.
   *
   * <ul>
   *   <li>When {@link OSDescriptor#OSDescriptor(String, String)} with {@code Family} and arch is
   *       {@code null}.
   *   <li>Then return {@code false}.
   * </ul>
   *
   * <p>Method under test: {@link OSDescriptor#matches(OSDescriptor)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean OSDescriptor.matches(OSDescriptor)"})
  public void testMatches_whenOSDescriptorWithFamilyAndArchIsNull_thenReturnFalse() {
    // Arrange
    OSDescriptor osDescriptor = new OSDescriptor("Family", "Arch");
    OSDescriptor os = new OSDescriptor("Family", null);

    // Act
    boolean actualMatchesResult = osDescriptor.matches(os);

    // Assert
    assertFalse(actualMatchesResult);
  }
}
