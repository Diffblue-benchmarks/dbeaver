package org.jkiss.dbeaver.model.stm;

import static org.junit.Assert.assertTrue;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class STMSourceDiffblueTest {
  /**
   * Test {@link STMSource#fromReader(Reader)}.
   *
   * <ul>
   *   <li>When {@link StringReader#StringReader(String)} with {@code foo}.
   *   <li>Then return {@link STMSourceImpl}.
   * </ul>
   *
   * <p>Method under test: {@link STMSource#fromReader(Reader)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"STMSource STMSource.fromReader(Reader)"})
  public void testFromReader_whenStringReaderWithFoo_thenReturnSTMSourceImpl() throws IOException {
    // Arrange and Act
    STMSource actualFromReaderResult = STMSource.fromReader(new StringReader("foo"));
    actualFromReaderResult.getStream();

    // Assert
    assertTrue(actualFromReaderResult instanceof STMSourceImpl);
  }
}
