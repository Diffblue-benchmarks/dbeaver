package org.jkiss.dbeaver.model.connection;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.connection.DBPDriverLibrary.FileType;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPDriverLibraryDiffblueTest {
  /**
   * Test FileType {@link FileType#getFileTypeByFileName(String)}.
   *
   * <ul>
   *   <li>When {@code foo.txt}.
   *   <li>Then return {@code lib}.
   * </ul>
   *
   * <p>Method under test: {@link FileType#getFileTypeByFileName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FileType FileType.getFileTypeByFileName(String)"})
  public void testFileTypeGetFileTypeByFileName_whenFooTxt_thenReturnLib() {
    // Arrange, Act and Assert
    assertEquals(FileType.lib, FileType.getFileTypeByFileName("foo.txt"));
  }

  /**
   * Test FileType {@link FileType#getFileTypeByFileName(String)}.
   *
   * <ul>
   *   <li>When {@code .jar}.
   *   <li>Then return {@code jar}.
   * </ul>
   *
   * <p>Method under test: {@link FileType#getFileTypeByFileName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FileType FileType.getFileTypeByFileName(String)"})
  public void testFileTypeGetFileTypeByFileName_whenJar_thenReturnJar() {
    // Arrange, Act and Assert
    assertEquals(FileType.jar, FileType.getFileTypeByFileName(".jar"));
  }

  /**
   * Test FileType {@link FileType#getFileTypeByFileName(String)}.
   *
   * <ul>
   *   <li>When {@code .zip}.
   *   <li>Then return {@code jar}.
   * </ul>
   *
   * <p>Method under test: {@link FileType#getFileTypeByFileName(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"FileType FileType.getFileTypeByFileName(String)"})
  public void testFileTypeGetFileTypeByFileName_whenZip_thenReturnJar() {
    // Arrange, Act and Assert
    assertEquals(FileType.jar, FileType.getFileTypeByFileName(".zip"));
  }
}
