package org.jkiss.dbeaver.ext.oracle.oci;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import javax.management.loading.MLet;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class OCIClassLoaderDiffblueTest {
  /**
   * Test {@link OCIClassLoader#OCIClassLoader(OracleHomeDescriptor, ClassLoader)}.
   *
   * <ul>
   *   <li>Given {@code bin}.
   *   <li>When {@link OracleHomeDescriptor} {@link OracleHomeDescriptor#getName()} return {@code
   *       bin}.
   * </ul>
   *
   * <p>Method under test: {@link OCIClassLoader#OCIClassLoader(OracleHomeDescriptor, ClassLoader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OCIClassLoader.<init>(OracleHomeDescriptor, ClassLoader)"})
  public void testNewOCIClassLoader_givenBin_whenOracleHomeDescriptorGetNameReturnBin() {
    // Arrange
    OracleHomeDescriptor oracleHomeDescriptor = mock(OracleHomeDescriptor.class);
    when(oracleHomeDescriptor.getName()).thenReturn("bin");

    // Act
    OCIClassLoader actualOciClassLoader = new OCIClassLoader(oracleHomeDescriptor, new MLet());

    // Assert
    verify(oracleHomeDescriptor, atLeast(1)).getName();
    assertNotNull(actualOciClassLoader);
  }

  /**
   * Test {@link OCIClassLoader#OCIClassLoader(OracleHomeDescriptor, ClassLoader)}.
   *
   * <ul>
   *   <li>Given {@code Name}.
   *   <li>When {@link OracleHomeDescriptor} {@link OracleHomeDescriptor#getName()} return {@code
   *       Name}.
   * </ul>
   *
   * <p>Method under test: {@link OCIClassLoader#OCIClassLoader(OracleHomeDescriptor, ClassLoader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OCIClassLoader.<init>(OracleHomeDescriptor, ClassLoader)"})
  public void testNewOCIClassLoader_givenName_whenOracleHomeDescriptorGetNameReturnName() {
    // Arrange
    OracleHomeDescriptor oracleHomeDescriptor = mock(OracleHomeDescriptor.class);
    when(oracleHomeDescriptor.getName()).thenReturn("Name");

    // Act
    OCIClassLoader actualOciClassLoader = new OCIClassLoader(oracleHomeDescriptor, new MLet());

    // Assert
    verify(oracleHomeDescriptor, atLeast(1)).getName();
    assertNotNull(actualOciClassLoader);
  }

  /**
   * Test {@link OCIClassLoader#OCIClassLoader(OracleHomeDescriptor, ClassLoader)}.
   *
   * <ul>
   *   <li>Given {@code /}.
   *   <li>When {@link OracleHomeDescriptor} {@link OracleHomeDescriptor#getName()} return {@code
   *       /}.
   * </ul>
   *
   * <p>Method under test: {@link OCIClassLoader#OCIClassLoader(OracleHomeDescriptor, ClassLoader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void OCIClassLoader.<init>(OracleHomeDescriptor, ClassLoader)"})
  public void testNewOCIClassLoader_givenSlash_whenOracleHomeDescriptorGetNameReturnSlash() {
    // Arrange
    OracleHomeDescriptor oracleHomeDescriptor = mock(OracleHomeDescriptor.class);
    when(oracleHomeDescriptor.getName()).thenReturn("/");

    // Act
    OCIClassLoader actualOciClassLoader = new OCIClassLoader(oracleHomeDescriptor, new MLet());

    // Assert
    verify(oracleHomeDescriptor, atLeast(1)).getName();
    assertNotNull(actualOciClassLoader);
  }

  /**
   * Test {@link OCIClassLoader#findLibrary(String)}.
   *
   * <ul>
   *   <li>Given {@link OracleHomeDescriptor} {@link OracleHomeDescriptor#getName()} return empty
   *       string.
   *   <li>Then return {@code null}.
   * </ul>
   *
   * <p>Method under test: {@link OCIClassLoader#findLibrary(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"String OCIClassLoader.findLibrary(String)"})
  public void testFindLibrary_givenOracleHomeDescriptorGetNameReturnEmptyString_thenReturnNull() {
    // Arrange
    OracleHomeDescriptor oracleHomeDescriptor = mock(OracleHomeDescriptor.class);
    when(oracleHomeDescriptor.getName()).thenReturn("");
    OCIClassLoader ociClassLoader = new OCIClassLoader(oracleHomeDescriptor, new MLet());

    // Act
    String actualFindLibraryResult = ociClassLoader.findLibrary("Libname");

    // Assert
    verify(oracleHomeDescriptor, atLeast(1)).getName();
    assertNull(actualFindLibraryResult);
  }
}
