package org.jkiss.dbeaver;

import static org.junit.Assert.assertThrows;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class LogOutputStreamDiffblueTest {
  /**
   * Test {@link LogOutputStream#LogOutputStream(File)}.
   *
   * <p>Method under test: {@link LogOutputStream#LogOutputStream(File)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void LogOutputStream.<init>(File)"})
  public void testNewLogOutputStream() throws IOException {
    // Arrange, Act and Assert
    assertThrows(
        IOException.class,
        () ->
            new LogOutputStream(
                Paths.get(System.getProperty("java.io.tmpdir"), "test.txt").toFile()));
  }
}
