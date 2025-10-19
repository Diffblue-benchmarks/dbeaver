package org.jkiss.dbeaver.registry;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DataSourceMemoryStorageDiffblueTest {
  /**
   * Test {@link DataSourceMemoryStorage#getInputStream()}.
   *
   * <p>Method under test: {@link DataSourceMemoryStorage#getInputStream()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.io.InputStream DataSourceMemoryStorage.getInputStream()"})
  public void testGetInputStream() throws IOException {
    // Arrange, Act and Assert
    byte[] byteArray = new byte[8];
    assertEquals(
        8,
        new DataSourceMemoryStorage("AXAXAXAX".getBytes("UTF-8")).getInputStream().read(byteArray));
    assertArrayEquals("AXAXAXAX".getBytes("UTF-8"), byteArray);
  }
}
