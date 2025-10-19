package org.jkiss.dbeaver.registry.maven.versioning;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DefaultArtifactVersionDiffblueTest {
  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion =
        new DefaultArtifactVersion("org.jkiss.dbeaver.registry.maven.versioning.ArtifactVersion");

    // Assert
    assertEquals(
        "org.jkiss.dbeaver.registry.maven.versioning.ArtifactVersion",
        actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>Then return Qualifier is {@code 1.0.2Number is invalid}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_thenReturnQualifierIs102NumberIsInvalid() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion =
        new DefaultArtifactVersion("1.0.2Number is invalid");

    // Assert
    assertEquals("1.0.2Number is invalid", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code -0}.
   *   <li>Then return Qualifier is {@code -0}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_when0_thenReturnQualifierIs0() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("-0");

    // Assert
    assertEquals("-0", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 0}.
   *   <li>Then return Qualifier is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_when0_thenReturnQualifierIsNull() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("0");

    // Assert
    assertNull(actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 42}.
   *   <li>Then return MajorVersion is forty-two.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_when42_thenReturnMajorVersionIsFortyTwo() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("42");

    // Assert
    assertNull(actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
    assertEquals(42, actualDefaultArtifactVersion.getMajorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2-Version}.
   *   <li>Then return Qualifier is {@code Version}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_when102Version_thenReturnQualifierIsVersion() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion =
        new DefaultArtifactVersion("1.0.2-Version");

    // Assert
    assertEquals("Version", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
    assertEquals(1, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(2, actualDefaultArtifactVersion.getIncrementalVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2.}.
   *   <li>Then return Qualifier is {@code 1.0.2.}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_when102_thenReturnQualifierIs102() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("1.0.2.");

    // Assert
    assertEquals("1.0.2.", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2..}.
   *   <li>Then return Qualifier is {@code 1.0.2..}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_when102_thenReturnQualifierIs1022() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("1.0.2..");

    // Assert
    assertEquals("1.0.2..", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code .1.0.2}.
   *   <li>Then return Qualifier is {@code .1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_when102_thenReturnQualifierIs1023() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion(".1.0.2");

    // Assert
    assertEquals(".1.0.2", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2-}.
   *   <li>Then return Qualifier is empty string.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_when102_thenReturnQualifierIsEmptyString() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("1.0.2-");

    // Assert
    assertEquals("", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
    assertEquals(1, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(2, actualDefaultArtifactVersion.getIncrementalVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2}.
   *   <li>Then return Qualifier is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_when102_thenReturnQualifierIsNull() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Assert
    assertNull(actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
    assertEquals(1, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(2, actualDefaultArtifactVersion.getIncrementalVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 01.0.2}.
   *   <li>Then return Qualifier is {@code 01.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_when0102_thenReturnQualifierIs0102() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("01.0.2");

    // Assert
    assertEquals("01.0.2", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code -01.0.2}.
   *   <li>Then return Qualifier is {@code -01.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_when0102_thenReturnQualifierIs01022() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("-01.0.2");

    // Assert
    assertEquals("-01.0.2", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.21.0.2}.
   *   <li>Then return Qualifier is {@code 1.0.21.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_when102102_thenReturnQualifierIs102102() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("1.0.21.0.2");

    // Assert
    assertEquals("1.0.21.0.2", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code --}.
   *   <li>Then return Qualifier is {@code --}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_whenDashDash_thenReturnQualifierIsDashDash() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("--");

    // Assert
    assertEquals("--", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code -}.
   *   <li>Then return Qualifier is {@code -}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_whenDash_thenReturnQualifierIsDash() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("-");

    // Assert
    assertEquals("-", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code .}.
   *   <li>Then return Qualifier is {@code .}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_whenDot_thenReturnQualifierIsDot() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion(".");

    // Assert
    assertEquals(".", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code Version1.0.2}.
   *   <li>Then return Qualifier is {@code Version1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_whenVersion102_thenReturnQualifierIsVersion102() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion =
        new DefaultArtifactVersion("Version1.0.2");

    // Assert
    assertEquals("Version1.0.2", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}.
   *
   * <ul>
   *   <li>When {@code Version}.
   *   <li>Then return Qualifier is {@code Version}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#DefaultArtifactVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.<init>(String)"})
  public void testNewDefaultArtifactVersion_whenVersion_thenReturnQualifierIsVersion() {
    // Arrange and Act
    DefaultArtifactVersion actualDefaultArtifactVersion = new DefaultArtifactVersion("Version");

    // Assert
    assertEquals("Version", actualDefaultArtifactVersion.getQualifier());
    assertEquals(0, actualDefaultArtifactVersion.getBuildNumber());
    assertEquals(0, actualDefaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMajorVersion());
    assertEquals(0, actualDefaultArtifactVersion.getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#equals(Object)}, and {@link
   * DefaultArtifactVersion#hashCode()}.
   *
   * <ul>
   *   <li>When other is equal.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DefaultArtifactVersion#equals(Object)}
   *   <li>{@link DefaultArtifactVersion#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DefaultArtifactVersion.equals(Object)",
    "int DefaultArtifactVersion.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");
    DefaultArtifactVersion defaultArtifactVersion2 = new DefaultArtifactVersion("1.0.2");

    // Act and Assert
    assertEquals(defaultArtifactVersion, defaultArtifactVersion2);
    assertEquals(defaultArtifactVersion.hashCode(), defaultArtifactVersion2.hashCode());
  }

  /**
   * Test {@link DefaultArtifactVersion#equals(Object)}, and {@link
   * DefaultArtifactVersion#hashCode()}.
   *
   * <ul>
   *   <li>When other is same.
   *   <li>Then return equal.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DefaultArtifactVersion#equals(Object)}
   *   <li>{@link DefaultArtifactVersion#hashCode()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DefaultArtifactVersion.equals(Object)",
    "int DefaultArtifactVersion.hashCode()"
  })
  public void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act and Assert
    assertEquals(defaultArtifactVersion, defaultArtifactVersion);
    int expectedHashCodeResult = defaultArtifactVersion.hashCode();
    assertEquals(expectedHashCodeResult, defaultArtifactVersion.hashCode());
  }

  /**
   * Test {@link DefaultArtifactVersion#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DefaultArtifactVersion.equals(Object)",
    "int DefaultArtifactVersion.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DefaultArtifactVersion("1.0.2"), 1);
  }

  /**
   * Test {@link DefaultArtifactVersion#equals(Object)}.
   *
   * <ul>
   *   <li>When other is different.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DefaultArtifactVersion.equals(Object)",
    "int DefaultArtifactVersion.hashCode()"
  })
  public void testEquals_whenOtherIsDifferent_thenReturnNotEqual2() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("Version");

    // Act and Assert
    assertNotEquals(defaultArtifactVersion, new DefaultArtifactVersion("1.0.2"));
  }

  /**
   * Test {@link DefaultArtifactVersion#equals(Object)}.
   *
   * <ul>
   *   <li>When other is {@code null}.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DefaultArtifactVersion.equals(Object)",
    "int DefaultArtifactVersion.hashCode()"
  })
  public void testEquals_whenOtherIsNull_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(new DefaultArtifactVersion("1.0.2"), null);
  }

  /**
   * Test {@link DefaultArtifactVersion#equals(Object)}.
   *
   * <ul>
   *   <li>When other is wrong type.
   *   <li>Then return not equal.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#equals(Object)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "boolean DefaultArtifactVersion.equals(Object)",
    "int DefaultArtifactVersion.hashCode()"
  })
  public void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
    // Arrange, Act and Assert
    assertNotEquals(
        new DefaultArtifactVersion("1.0.2"), "Different type to DefaultArtifactVersion");
  }

  /**
   * Test {@link DefaultArtifactVersion#compareTo(ArtifactVersion)} with {@code ArtifactVersion}.
   *
   * <p>Method under test: {@link DefaultArtifactVersion#compareTo(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.compareTo(ArtifactVersion)"})
  public void testCompareToWithArtifactVersion() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2Version");

    // Act
    int actualCompareToResult =
        defaultArtifactVersion.compareTo(new DefaultArtifactVersion("1.0.2"));

    // Assert
    assertEquals(2, actualCompareToResult);
  }

  /**
   * Test {@link DefaultArtifactVersion#compareTo(ArtifactVersion)} with {@code ArtifactVersion}.
   *
   * <p>Method under test: {@link DefaultArtifactVersion#compareTo(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.compareTo(ArtifactVersion)"})
  public void testCompareToWithArtifactVersion2() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    int actualCompareToResult =
        defaultArtifactVersion.compareTo(new DefaultArtifactVersion("1.0.2Version"));

    // Assert
    assertEquals(-2, actualCompareToResult);
  }

  /**
   * Test {@link DefaultArtifactVersion#compareTo(ArtifactVersion)} with {@code ArtifactVersion}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#compareTo(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.compareTo(ArtifactVersion)"})
  public void testCompareToWithArtifactVersion_thenReturnMinusOne() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("Version");

    // Act
    int actualCompareToResult =
        defaultArtifactVersion.compareTo(new DefaultArtifactVersion("1.0.2"));

    // Assert
    assertEquals(-1, actualCompareToResult);
  }

  /**
   * Test {@link DefaultArtifactVersion#compareTo(ArtifactVersion)} with {@code ArtifactVersion}.
   *
   * <ul>
   *   <li>Then return minus one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#compareTo(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.compareTo(ArtifactVersion)"})
  public void testCompareToWithArtifactVersion_thenReturnMinusOne2() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion(".");

    // Act
    int actualCompareToResult =
        defaultArtifactVersion.compareTo(new DefaultArtifactVersion("1.0.2"));

    // Assert
    assertEquals(-1, actualCompareToResult);
  }

  /**
   * Test {@link DefaultArtifactVersion#compareTo(ArtifactVersion)} with {@code ArtifactVersion}.
   *
   * <ul>
   *   <li>Then return minus two.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#compareTo(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.compareTo(ArtifactVersion)"})
  public void testCompareToWithArtifactVersion_thenReturnMinusTwo() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion(".");

    // Act
    int actualCompareToResult =
        defaultArtifactVersion.compareTo(new DefaultArtifactVersion("Version"));

    // Assert
    assertEquals(-2, actualCompareToResult);
  }

  /**
   * Test {@link DefaultArtifactVersion#compareTo(ArtifactVersion)} with {@code ArtifactVersion}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#compareTo(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.compareTo(ArtifactVersion)"})
  public void testCompareToWithArtifactVersion_thenReturnOne() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    int actualCompareToResult =
        defaultArtifactVersion.compareTo(new DefaultArtifactVersion("Version"));

    // Assert
    assertEquals(1, actualCompareToResult);
  }

  /**
   * Test {@link DefaultArtifactVersion#compareTo(ArtifactVersion)} with {@code ArtifactVersion}.
   *
   * <ul>
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#compareTo(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.compareTo(ArtifactVersion)"})
  public void testCompareToWithArtifactVersion_thenReturnOne2() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    int actualCompareToResult = defaultArtifactVersion.compareTo(new DefaultArtifactVersion("."));

    // Assert
    assertEquals(1, actualCompareToResult);
  }

  /**
   * Test {@link DefaultArtifactVersion#compareTo(ArtifactVersion)} with {@code ArtifactVersion}.
   *
   * <ul>
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#compareTo(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.compareTo(ArtifactVersion)"})
  public void testCompareToWithArtifactVersion_thenReturnTwo() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("Version");

    // Act
    int actualCompareToResult = defaultArtifactVersion.compareTo(new DefaultArtifactVersion("."));

    // Assert
    assertEquals(2, actualCompareToResult);
  }

  /**
   * Test {@link DefaultArtifactVersion#compareTo(ArtifactVersion)} with {@code ArtifactVersion}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#compareTo(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.compareTo(ArtifactVersion)"})
  public void testCompareToWithArtifactVersion_thenReturnZero() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    int actualCompareToResult =
        defaultArtifactVersion.compareTo(new DefaultArtifactVersion("1.0.2"));

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test {@link DefaultArtifactVersion#compareTo(ArtifactVersion)} with {@code ArtifactVersion}.
   *
   * <ul>
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#compareTo(ArtifactVersion)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.compareTo(ArtifactVersion)"})
  public void testCompareToWithArtifactVersion_thenReturnZero2() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("Version");

    // Act
    int actualCompareToResult =
        defaultArtifactVersion.compareTo(new DefaultArtifactVersion("Version"));

    // Assert
    assertEquals(0, actualCompareToResult);
  }

  /**
   * Test {@link DefaultArtifactVersion#getMajorVersion()}.
   *
   * <ul>
   *   <li>Given {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with version is
   *       {@code 1.0.2}.
   *   <li>Then return one.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#getMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.getMajorVersion()"})
  public void testGetMajorVersion_givenDefaultArtifactVersionWithVersionIs102_thenReturnOne() {
    // Arrange, Act and Assert
    assertEquals(1, new DefaultArtifactVersion("1.0.2").getMajorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#getMajorVersion()}.
   *
   * <ul>
   *   <li>Given {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with {@code Version}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#getMajorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.getMajorVersion()"})
  public void testGetMajorVersion_givenDefaultArtifactVersionWithVersion_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, new DefaultArtifactVersion("Version").getMajorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#getMinorVersion()}.
   *
   * <ul>
   *   <li>Given {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with {@code Version}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#getMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.getMinorVersion()"})
  public void testGetMinorVersion_givenDefaultArtifactVersionWithVersion() {
    // Arrange, Act and Assert
    assertEquals(0, new DefaultArtifactVersion("Version").getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#getMinorVersion()}.
   *
   * <ul>
   *   <li>Given {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with version is
   *       {@code 1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#getMinorVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.getMinorVersion()"})
  public void testGetMinorVersion_givenDefaultArtifactVersionWithVersionIs102() {
    // Arrange, Act and Assert
    assertEquals(0, new DefaultArtifactVersion("1.0.2").getMinorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#getIncrementalVersion()}.
   *
   * <ul>
   *   <li>Given {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with {@code Version}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#getIncrementalVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.getIncrementalVersion()"})
  public void testGetIncrementalVersion_givenDefaultArtifactVersionWithVersion_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, new DefaultArtifactVersion("Version").getIncrementalVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#getIncrementalVersion()}.
   *
   * <ul>
   *   <li>Then return two.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#getIncrementalVersion()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.getIncrementalVersion()"})
  public void testGetIncrementalVersion_thenReturnTwo() {
    // Arrange, Act and Assert
    assertEquals(2, new DefaultArtifactVersion("1.0.2").getIncrementalVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#getBuildNumber()}.
   *
   * <ul>
   *   <li>Given {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with version is
   *       {@code 1.0.2}.
   *   <li>Then return zero.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#getBuildNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.getBuildNumber()"})
  public void testGetBuildNumber_givenDefaultArtifactVersionWithVersionIs102_thenReturnZero() {
    // Arrange, Act and Assert
    assertEquals(0, new DefaultArtifactVersion("1.0.2").getBuildNumber());
  }

  /**
   * Test {@link DefaultArtifactVersion#getBuildNumber()}.
   *
   * <ul>
   *   <li>Then return forty-two.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#getBuildNumber()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int DefaultArtifactVersion.getBuildNumber()"})
  public void testGetBuildNumber_thenReturnFortyTwo() {
    // Arrange, Act and Assert
    assertEquals(42, new DefaultArtifactVersion("1.0.2-42").getBuildNumber());
  }

  /**
   * Test getters and setters.
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DefaultArtifactVersion#toString()}
   *   <li>{@link DefaultArtifactVersion#getQualifier()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "String DefaultArtifactVersion.getQualifier()",
    "String DefaultArtifactVersion.toString()"
  })
  public void testGettersAndSetters() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    String actualToStringResult = defaultArtifactVersion.toString();

    // Assert
    assertEquals("1.0.2", actualToStringResult);
    assertNull(defaultArtifactVersion.getQualifier());
  }

  /**
   * Test {@link DefaultArtifactVersion#parseVersion(String)}.
   *
   * <p>Method under test: {@link DefaultArtifactVersion#parseVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.parseVersion(String)"})
  public void testParseVersion() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    defaultArtifactVersion.parseVersion("42");

    // Assert
    assertNull(defaultArtifactVersion.getQualifier());
    assertEquals(2, defaultArtifactVersion.getIncrementalVersion());
    assertEquals(42, defaultArtifactVersion.getMajorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#parseVersion(String)}.
   *
   * <p>Method under test: {@link DefaultArtifactVersion#parseVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.parseVersion(String)"})
  public void testParseVersion2() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    defaultArtifactVersion.parseVersion(
        "org.jkiss.dbeaver.registry.maven.versioning.ArtifactVersion");

    // Assert
    assertEquals(
        "org.jkiss.dbeaver.registry.maven.versioning.ArtifactVersion",
        defaultArtifactVersion.getQualifier());
    assertEquals(0, defaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, defaultArtifactVersion.getMajorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#parseVersion(String)}.
   *
   * <p>Method under test: {@link DefaultArtifactVersion#parseVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.parseVersion(String)"})
  public void testParseVersion3() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    defaultArtifactVersion.parseVersion("1.0.2-");

    // Assert
    assertEquals("", defaultArtifactVersion.getQualifier());
    assertEquals(1, defaultArtifactVersion.getMajorVersion());
    assertEquals(2, defaultArtifactVersion.getIncrementalVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#parseVersion(String)}.
   *
   * <p>Method under test: {@link DefaultArtifactVersion#parseVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.parseVersion(String)"})
  public void testParseVersion4() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    defaultArtifactVersion.parseVersion("1.0.2Number is invalid");

    // Assert
    assertEquals("1.0.2Number is invalid", defaultArtifactVersion.getQualifier());
    assertEquals(0, defaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, defaultArtifactVersion.getMajorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#parseVersion(String)}.
   *
   * <ul>
   *   <li>Then {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with version is {@code
   *       1.0.2} Qualifier is {@code 1.0.2.}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#parseVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.parseVersion(String)"})
  public void testParseVersion_thenDefaultArtifactVersionWithVersionIs102QualifierIs102() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    defaultArtifactVersion.parseVersion("1.0.2.");

    // Assert
    assertEquals("1.0.2.", defaultArtifactVersion.getQualifier());
    assertEquals(0, defaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, defaultArtifactVersion.getMajorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#parseVersion(String)}.
   *
   * <ul>
   *   <li>Then {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with version is {@code
   *       1.0.2} Qualifier is {@code 1.0.2..}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#parseVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.parseVersion(String)"})
  public void testParseVersion_thenDefaultArtifactVersionWithVersionIs102QualifierIs1022() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    defaultArtifactVersion.parseVersion("1.0.2..");

    // Assert
    assertEquals("1.0.2..", defaultArtifactVersion.getQualifier());
    assertEquals(0, defaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, defaultArtifactVersion.getMajorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#parseVersion(String)}.
   *
   * <ul>
   *   <li>Then {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with version is {@code
   *       1.0.2} Qualifier is {@code 1.0.21.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#parseVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.parseVersion(String)"})
  public void testParseVersion_thenDefaultArtifactVersionWithVersionIs102QualifierIs102102() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    defaultArtifactVersion.parseVersion("1.0.21.0.2");

    // Assert
    assertEquals("1.0.21.0.2", defaultArtifactVersion.getQualifier());
    assertEquals(0, defaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, defaultArtifactVersion.getMajorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#parseVersion(String)}.
   *
   * <ul>
   *   <li>Then {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with version is {@code
   *       1.0.2} Qualifier is {@code -}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#parseVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.parseVersion(String)"})
  public void testParseVersion_thenDefaultArtifactVersionWithVersionIs102QualifierIsDash() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    defaultArtifactVersion.parseVersion("-");

    // Assert
    assertEquals("-", defaultArtifactVersion.getQualifier());
    assertEquals(1, defaultArtifactVersion.getMajorVersion());
    assertEquals(2, defaultArtifactVersion.getIncrementalVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#parseVersion(String)}.
   *
   * <ul>
   *   <li>Then {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with version is {@code
   *       1.0.2} Qualifier is {@code .}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#parseVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.parseVersion(String)"})
  public void testParseVersion_thenDefaultArtifactVersionWithVersionIs102QualifierIsDot() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    defaultArtifactVersion.parseVersion(".");

    // Assert
    assertEquals(".", defaultArtifactVersion.getQualifier());
    assertEquals(0, defaultArtifactVersion.getIncrementalVersion());
    assertEquals(0, defaultArtifactVersion.getMajorVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#parseVersion(String)}.
   *
   * <ul>
   *   <li>Then {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with version is {@code
   *       1.0.2} Qualifier is {@code Version}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#parseVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.parseVersion(String)"})
  public void testParseVersion_thenDefaultArtifactVersionWithVersionIs102QualifierIsVersion() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    defaultArtifactVersion.parseVersion("Version");

    // Assert
    assertEquals("Version", defaultArtifactVersion.getQualifier());
    assertEquals(1, defaultArtifactVersion.getMajorVersion());
    assertEquals(2, defaultArtifactVersion.getIncrementalVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#parseVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 0}.
   *   <li>Then {@link DefaultArtifactVersion#DefaultArtifactVersion(String)} with version is {@code
   *       1.0.2} Qualifier is {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#parseVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.parseVersion(String)"})
  public void testParseVersion_when0_thenDefaultArtifactVersionWithVersionIs102QualifierIsNull() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    defaultArtifactVersion.parseVersion("0");

    // Assert
    assertNull(defaultArtifactVersion.getQualifier());
    assertEquals(0, defaultArtifactVersion.getMajorVersion());
    assertEquals(2, defaultArtifactVersion.getIncrementalVersion());
  }

  /**
   * Test {@link DefaultArtifactVersion#parseVersion(String)}.
   *
   * <ul>
   *   <li>When {@code 1.0.2}.
   * </ul>
   *
   * <p>Method under test: {@link DefaultArtifactVersion#parseVersion(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void DefaultArtifactVersion.parseVersion(String)"})
  public void testParseVersion_when102() {
    // Arrange
    DefaultArtifactVersion defaultArtifactVersion = new DefaultArtifactVersion("1.0.2");

    // Act
    defaultArtifactVersion.parseVersion("1.0.2");

    // Assert that nothing has changed
    assertEquals(1, defaultArtifactVersion.getMajorVersion());
    assertEquals(2, defaultArtifactVersion.getIncrementalVersion());
  }
}
