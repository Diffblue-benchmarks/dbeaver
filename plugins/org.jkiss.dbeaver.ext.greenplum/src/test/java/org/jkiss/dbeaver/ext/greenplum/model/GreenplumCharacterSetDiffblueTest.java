package org.jkiss.dbeaver.ext.greenplum.model;

import static org.junit.Assert.assertEquals;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class GreenplumCharacterSetDiffblueTest {
  /**
   * Test {@link GreenplumCharacterSet#getCharacterSetValue()}.
   *
   * <p>Method under test: {@link GreenplumCharacterSet#getCharacterSetValue()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"java.lang.String GreenplumCharacterSet.getCharacterSetValue()"})
  public void testGetCharacterSetValue() {
    // Arrange, Act and Assert
    assertEquals("BIG5", GreenplumCharacterSet.valueOf("BIG_FIVE").getCharacterSetValue());
  }
}
