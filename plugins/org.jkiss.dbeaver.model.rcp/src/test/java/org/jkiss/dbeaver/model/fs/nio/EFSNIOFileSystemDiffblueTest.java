package org.jkiss.dbeaver.model.fs.nio;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class EFSNIOFileSystemDiffblueTest {
  /**
   * Test new {@link EFSNIOFileSystem} (default constructor).
   *
   * <p>Method under test: default or parameterless constructor of {@link EFSNIOFileSystem}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void EFSNIOFileSystem.<init>()"})
  public void testNewEFSNIOFileSystem() {
    // Arrange, Act and Assert
    assertNull(new EFSNIOFileSystem().getScheme());
  }

  /**
   * Test {@link EFSNIOFileSystem#attributes()}.
   *
   * <p>Method under test: {@link EFSNIOFileSystem#attributes()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"int EFSNIOFileSystem.attributes()"})
  public void testAttributes() {
    // Arrange, Act and Assert
    assertEquals(2143289344, new EFSNIOFileSystem().attributes());
  }

  /**
   * Test {@link EFSNIOFileSystem#canDelete()}.
   *
   * <p>Method under test: {@link EFSNIOFileSystem#canDelete()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EFSNIOFileSystem.canDelete()"})
  public void testCanDelete() {
    // Arrange, Act and Assert
    assertTrue(new EFSNIOFileSystem().canDelete());
  }

  /**
   * Test {@link EFSNIOFileSystem#canWrite()}.
   *
   * <p>Method under test: {@link EFSNIOFileSystem#canWrite()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"boolean EFSNIOFileSystem.canWrite()"})
  public void testCanWrite() {
    // Arrange, Act and Assert
    assertTrue(new EFSNIOFileSystem().canWrite());
  }
}
